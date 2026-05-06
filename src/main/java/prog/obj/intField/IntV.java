package prog.obj.intField;

import field.FieldManager;
import field.boolField.fieldS.BoolV;
import language.Procedure;
import language.basicInstruction.*;
import language.basicInstruction.bitOp.BitOp;
import language.basicInstruction.commOp.CommOp;
import language.fieldRef.BoolVRef;
import language.fieldRef.BoolVeRef;
import language.fieldRef.BoolVfRef;
import medium.Medium;
import medium.locusS.Vertex;
import prog.ref.intField.IntVRef;

import java.util.HashMap;

/**
 * IntV represents an integer field on vertices.
 * <p>
 * It is represented as an array of BoolVRefs, where each BoolVRef represents a bit of the integer.
 * The bits are stored most significant bit first as an array of BoolV.
 * Negative integers are stored using 2's complement, with bits[0] being the sign bit.
 */
public class IntV extends IntField<BoolV> {
    public IntV(int n) {
        super(n, new BoolVRef[n+1]);
        // Initialize refs to avoid null entries in the bits array. Without this,
        // methods that copy or iterate bits (e.g. copy()) will get NPEs when
        // attempting to call methods on null array slots.
        for (int i = 0; i <= n; i++) this.bits[i] = BoolVRef.zeroes();
    }

    public static IntV of(int value, int n) {
        IntV intV = new IntV(n);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i+1 == n) throw new IllegalArgumentException("Value "+value+" cannot be represented in "+n+" bits.");
            intV.bits[n - i] = (value & 1) == 0 ? BoolVRef.zeroes() : BoolVRef.ones();
            value = value >> 1;
        }
        intV.bits[0] = value >>> 31 == 0 ? BoolVRef.zeroes() : BoolVRef.ones();
        return intV;
    }

    public static IntV of(BoolVRef boolV, int n) {
        IntV intV = new IntV(n);
        for (int i = 1; i < n; i++) intV.bits[i] = BoolVRef.zeroes();
        intV.bits[n] = boolV.copy();
        return intV;
    }

    public static BasicInstruction of(BoolVRef boolV, IntVRef res) {
        return new boolToIntV(boolV, res);
    }

    public BoolVRef[] getBits() {
        return (BoolVRef[]) bits;
    }

    /** Converts this IntV to a HashMap<Vertex, Integer> by decoding each bit and combining them into an integer. */
    public HashMap<Vertex, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntV as a Java int");
        HashMap<Vertex, Integer> res = new HashMap<>();
        for (Vertex v: m.vertices) res.put(v, 0);
        for (int i = 1; i <= n; i++) {
            BoolVRef bit = (BoolVRef) bits[i];
            HashMap<Vertex, Boolean> bitMap = BoolV.decode(m.vertices, bit.get());
            final int pos = n - i;
            res.replaceAll((v, val) -> {
                if (bitMap.get(v)) return val | (1 << (pos));
                else return val;
            });
        }

        BoolVRef bit = (BoolVRef) bits[0];
        HashMap<Vertex, Boolean> bitMap = BoolV.decode(m.vertices, bit.get());
        res.replaceAll((v, val) -> {
            if (bitMap.get(v)) return val | (1 << 31);
            else return val;
        });

        return res;
    }

    protected static void lShift(IntV a, IntV res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolVRef.zeroes();
    }

    protected static void rShift(IntV a, IntV res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolVRef.zeroes();
    }

    public IntV copy() {
        IntV copy = new IntV(n);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}

class LShiftV implements BasicInstruction {
    private final IntVRef a;
    private final IntVRef res;
    private final int k;

    public LShiftV(IntVRef a, IntVRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntV.lShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class RShiftV implements BasicInstruction {
    private final IntVRef a;
    private final IntVRef res;
    private final int k;

    public RShiftV(IntVRef a, IntVRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntV.rShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class IntNotV extends Procedure {
    private final IntVRef a;
    private final IntVRef res;

    public IntNotV(IntVRef a, IntVRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.not(a.get().getBits()[i], res.get().getBits()[i]));
    }
}

class IntAndV extends Procedure {
    private final IntVRef a;
    private final IntVRef b;
    private final IntVRef res;

    public IntAndV(IntVRef a, IntVRef b, IntVRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.and(a.get().getBits()[i], b.get().getBits()[i], res.get().getBits()[i]));
    }
}

class IntOrV extends Procedure {
    private final IntVRef a;
    private final IntVRef b;
    private final IntVRef res;

    public IntOrV(IntVRef a, IntVRef b, IntVRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.or(a.get().getBits()[i], b.get().getBits()[i], res.get().getBits()[i]));
    }
}

class IntXorV extends Procedure {
    private final IntVRef a;
    private final IntVRef b;
    private final IntVRef res;

    public IntXorV(IntVRef a, IntVRef b, IntVRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.xor(a.get().getBits()[i], b.get().getBits()[i], res.get().getBits()[i]));
    }
}

class boolToIntV implements BasicInstruction {
    private final BoolVRef boolV;
    private final IntVRef res;

    public boolToIntV(BoolVRef boolV, IntVRef res) {
        this.boolV = boolV;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntV.of(boolV, res.get().n));
        return true;
    }
}

class IntNegV extends Procedure {
    private final IntVRef a;
    private final IntVRef res;

    public IntNegV(IntVRef a, IntVRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        add(IntField.not(a, res));
        add(IntField.add(res, IntVRef.of(IntV.of(1, res.get().n)), res));
    }
}

class IntRedAddVe extends Procedure {
    private final BoolVeRef orig;
    private final IntVRef res;
    private final int breadth;

    public IntRedAddVe(BoolVeRef orig, IntVRef res) {
        this.orig = orig;
        this.res = res;
        this.breadth = FieldManager.getBreadthV();
    }

    @Override
    protected void setInstructions() {
        BoolVRef[] stack = new BoolVRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolVRef();
        add(CommOp.redStack0(orig, stack));

        res.set(IntV.of(0, res.get().n));
        IntVRef current = IntVRef.of(new IntV(res.get().n));
        for (int i = 0; i < breadth; i++) {
            add(IntField.fromBool(stack[i], current));
            add(IntField.add(res, current, res));
        }
    }
}

class IntRedAddVf extends Procedure {
    private final BoolVfRef orig;
    private final IntVRef res;
    private final int breadth;

    public IntRedAddVf(BoolVfRef orig, IntVRef res) {
        this.orig = orig;
        this.res = res;
        this.breadth = FieldManager.getBreadthV();
    }

    @Override
    protected void setInstructions() {
        BoolVRef[] stack = new BoolVRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolVRef();
        add(CommOp.redStack0(orig, stack));

        res.set(IntV.of(0, res.get().n));
        IntVRef current = new IntVRef();
        for (int i = 0; i < breadth; i++) {
            add(IntField.fromBool(stack[i], current));
            add(IntField.add(res, current, res));
        }
    }
}

class IntAddV extends Procedure {
    private final IntVRef a;
    private final IntVRef b;
    private final IntVRef res;
    private final int n;

    private final IntVRef carry;
    private final IntVRef tmp;

    public IntAddV(IntVRef a, IntVRef b, IntVRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
        this.n = a.get().n;

        carry = IntVRef.of(new IntV(n));
        tmp = IntVRef.of(new IntV(n));
    }

    @Override
    public void setInstructions() {
        add(new SetRef<>(a, res));
        add(new SetRef<>(b, tmp));

        for (int i = 0; i <= n; i++){
            add(IntField.and(res, tmp, carry));
            add(IntField.xor(res, tmp, res));
            add(IntField.lShift(carry, tmp, 1));
        }
    }
}

class IntSubV extends Procedure {
    private final IntVRef a;
    private final IntVRef b;
    private final IntVRef res;

    public IntSubV(IntVRef a, IntVRef b, IntVRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        IntVRef negB = IntVRef.of(new IntV(a.get().n));
        add(IntField.neg(b, negB));
        add(IntField.add(a, negB, res));
    }
}

class GTV extends Procedure {
    private final IntVRef a;
    private final IntVRef b;
    private final BoolVRef res;

    public GTV(IntVRef a, IntVRef b, BoolVRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        IntVRef diff = IntVRef.of(new IntV(a.get().n));
        add(IntField.sub(a, b, diff));

        BoolVRef[] bits = new BoolVRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolVRef();
        add(IntField.split(diff, bits));

        add(new SetRef<>(bits[0], res));
        add(BitOp.not(res, res));
    }
}

class SplitV extends Procedure {
    private final IntVRef a;
    private final BoolVRef[] res;

    public SplitV(IntVRef a, BoolVRef res[]) {
        this.a = a;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        for (int i = 0; i <= a.get().n; i++)
            add(new SetRef<>(a.get().getBits()[i], res[i]));
    }
}