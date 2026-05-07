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
    public IntNotV(IntVRef a, IntVRef res) {
        BoolVRef[] bitsA = new BoolVRef[a.get().n + 1];
        BoolVRef[] bitsRes = new BoolVRef[a.get().n + 1];
        for(int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVRef();
            bitsRes[i] = new BoolVRef();
        }

        split(a, bitsA);
        for (int i = 0; i <= a.get().n; i++) not(bitsA[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntAndV extends Procedure {
    public IntAndV(IntVRef a, IntVRef b, IntVRef res) {
        BoolVRef[] bitsA = new BoolVRef[a.get().n + 1];
        BoolVRef[] bitsB = new BoolVRef[a.get().n + 1];
        BoolVRef[] bitsRes = new BoolVRef[a.get().n + 1];
        for(int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVRef();
            bitsB[i] = new BoolVRef();
            bitsRes[i] = new BoolVRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntOrV extends Procedure {
    public IntOrV(IntVRef a, IntVRef b, IntVRef res) {
        BoolVRef[] bitsA = new BoolVRef[a.get().n + 1];
        BoolVRef[] bitsB = new BoolVRef[a.get().n + 1];
        BoolVRef[] bitsRes = new BoolVRef[a.get().n + 1];
        for(int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVRef();
            bitsB[i] = new BoolVRef();
            bitsRes[i] = new BoolVRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntXorV extends Procedure {
    public IntXorV(IntVRef a, IntVRef b, IntVRef res) {
        BoolVRef[] bitsA = new BoolVRef[a.get().n + 1];
        BoolVRef[] bitsB = new BoolVRef[a.get().n + 1];
        BoolVRef[] bitsRes = new BoolVRef[a.get().n + 1];
        for(int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVRef();
            bitsB[i] = new BoolVRef();
            bitsRes[i] = new BoolVRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
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
    public IntNegV(IntVRef a, IntVRef res) {
        not(a, res);
        add(res, IntVRef.of(IntV.of(1, res.get().n)), res);
    }
}

class IntRedAddVe extends Procedure {
    public IntRedAddVe(BoolVeRef orig, IntVRef res) {
        int breadth = FieldManager.getBreadthV();

        BoolVRef[] stack = new BoolVRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolVRef();
        redStack0(orig, stack);

        set(IntVRef.of(IntV.of(0, res.get().n)), res);
        IntVRef current = IntVRef.of(new IntV(res.get().n));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }
}

class IntRedAddVf extends Procedure {
    public IntRedAddVf(BoolVfRef orig, IntVRef res) {
        int breadth = FieldManager.getBreadthV();

        BoolVRef[] stack = new BoolVRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolVRef();
        redStack0(orig, stack);

        set(IntVRef.of(IntV.of(0, res.get().n)), res);
        for (int i = 0; i < breadth; i++) {
            IntVRef current = IntVRef.of(new IntV(res.get().n));
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }
}

class IntAddV extends Procedure {
    public IntAddV(IntVRef a, IntVRef b, IntVRef res) {
        IntVRef carry = IntVRef.of(new IntV(a.get().n));
        IntVRef tmp = IntVRef.of(new IntV(a.get().n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class IntSubV extends Procedure {
    public IntSubV(IntVRef a, IntVRef b, IntVRef res) {
        IntVRef negB = IntVRef.of(new IntV(a.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class GTV extends Procedure {
    public GTV(IntVRef a, IntVRef b, BoolVRef res) {
        IntVRef diff = IntVRef.of(new IntV(a.get().n));
        sub(a, b, diff);

        BoolVRef[] bits = new BoolVRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolVRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class SplitV implements BasicInstruction {
    private final IntVRef a;
    private final BoolVRef[] res;

    public SplitV(IntVRef a, BoolVRef[] res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= a.get().n; i++) res[i].set(a.get().getBits()[i].copy().get());
        return true;
    }
}

class JoinV implements BasicInstruction {
    private final BoolVRef[] a;
    private final IntVRef res;

    public JoinV(BoolVRef[] a, IntVRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        IntV tmp = new IntV(res.get().n);
        for (int i = 0; i <= res.get().n; i++) tmp.bits[i] = a[i].copy();
        res.set(tmp);
        return true;
    }
}