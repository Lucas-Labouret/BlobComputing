package language.obj.field.intField;

import language.ref.field.intField.IntVeRef;
import language.ref.field.intField.IntVfRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import language.obj.field.boolField.fieldS.BoolV;
import language.instruction.Procedure;
import language.instruction.basicInstruction.*;
import language.ref.field.boolField.fieldS.BoolVRef;
import language.ref.field.boolField.fieldT.BoolVeRef;
import language.ref.field.boolField.fieldT.BoolVfRef;
import language.ref.field.intField.IntVRef;
import medium.Medium;
import medium.locusS.Vertex;

import java.util.HashMap;

/**
 * IntV represents an integer language.obj.field on vertices.
 * <p>
 * It is represented as an array of BoolVRefs, where each BoolVRef represents a bit of the integer.
 * The bits are stored most significant bit first as an array of BoolV.
 * Negative integers are stored using 2's complement, with bits[0] being the sign bit.
 */
public class IntV extends IntField<BoolV> {
    public IntV(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntV(int n, Border border) {
        super(n, new BoolVRef[n+1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolVRef.of(BoolV.zeroes(border));
    }

    public static IntV of(int value, int n) { return of(value, n, BoolFieldManager.DEFAULT_BORDER()); }
    public static IntV of(int value, int n, Border border) {
        IntV intV = new IntV(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i+1 == n) throw new IllegalArgumentException("Value "+value+" cannot be represented in "+n+" bits.");
            intV.bits[n - i] = (value & 1) == 0 ? BoolVRef.of(BoolV.zeroes(border)) : BoolVRef.of(BoolV.ones(border));
            value = value >> 1;
        }
        intV.bits[0] = value >>> 31 == 0 ? BoolVRef.of(BoolV.zeroes(border)) : BoolVRef.of(BoolV.ones(border));
        return intV;
    }

    public static IntV of(BoolVRef boolV, int n) {
        IntV intV = new IntV(n, boolV.get().border);
        for (int i = 1; i < n; i++) intV.bits[i] = BoolVRef.of(BoolV.zeroes(boolV.get().border));
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
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolVRef.of(BoolV.zeroes(a.border));
    }

    protected static void rShift(IntV a, IntV res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolVRef.of(BoolV.zeroes(a.border));
    }

    public IntV copy() {
        IntV copy = new IntV(n, border);
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
        int breadth = BoolFieldManager.getBreadthV();

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

    public IntRedAddVe(IntVeRef orig, IntVRef res) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVeRef[] bits = new BoolVeRef[breadth];
        for (int i = 0; i < breadth; i++) bits[i] = new BoolVeRef();
        split(orig, bits);

        BoolVRef[][] veStacks = new BoolVRef[breadth][orig.get().n + 1];
        for (int i = 0; i < breadth; i++) redStack0(bits[i], veStacks[i]);

        BoolVRef[][] bitStacks = new BoolVRef[orig.get().n + 1][breadth];
        IntField.transpose(veStacks, bitStacks, orig.get().n + 1, breadth);

        IntVRef[] intStack = new IntVRef[breadth];
        for (int i = 0; i < breadth; i++) {
            intStack[i] = new IntVRef();
            join(bitStacks[i], intStack[i]);
        }

        res.set(IntV.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, intStack[i], res);
    }
}

class IntRedAddVf extends Procedure {
    public IntRedAddVf(BoolVfRef orig, IntVRef res) {
        int breadth = BoolFieldManager.getBreadthV();

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

    public IntRedAddVf(IntVfRef orig, IntVRef res) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVfRef[] bits = new BoolVfRef[breadth];
        for (int i = 0; i < breadth; i++) bits[i] = new BoolVfRef();
        split(orig, bits);

        BoolVRef[][] vfStacks = new BoolVRef[breadth][orig.get().n + 1];
        for (int i = 0; i < breadth; i++) redStack0(bits[i], vfStacks[i]);

        BoolVRef[][] bitStacks = new BoolVRef[orig.get().n + 1][breadth];
        IntField.transpose(vfStacks, bitStacks, orig.get().n + 1, breadth);

        IntVRef[] intStack = new IntVRef[breadth];
        for (int i = 0; i < breadth; i++) {
            intStack[i] = IntVRef.of(new IntV(res.get().n));
            join(bitStacks[i], intStack[i]);
        }

        res.set(IntV.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, intStack[i], res);
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
