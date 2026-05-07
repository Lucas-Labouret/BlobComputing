package prog.obj.intField;

import field.FieldManager;
import field.boolField.fieldS.BoolE;
import language.Procedure;
import language.basicInstruction.*;
import language.basicInstruction.bitOp.BitOp;
import language.basicInstruction.commOp.CommOp;
import language.fieldRef.BoolERef;
import language.fieldRef.BoolEfRef;
import language.fieldRef.BoolEvRef;
import medium.Medium;
import medium.locusS.Edge;
import prog.ref.intField.IntERef;
import prog.ref.intField.IntERef;
import prog.ref.intField.IntVRef;

import java.util.HashMap;

/** IntE represents an integer field on edges. */
public class IntE extends IntField<BoolE> {
    public IntE(int n) {
        super(n, new BoolERef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolERef.zeroes();
    }

    public static IntE of(int value, int n) {
        IntE intE = new IntE(n);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intE.bits[n - i] = (value & 1) == 0 ? BoolERef.zeroes() : BoolERef.ones();
            value = value >> 1;
        }
        intE.bits[0] = value >>> 31 == 0 ? BoolERef.zeroes() : BoolERef.ones();
        return intE;
    }

    public static IntE of(BoolERef boolE, int n) {
        IntE intE = new IntE(n);
        for (int i = 1; i < n; i++) intE.bits[i] = BoolERef.zeroes();
        intE.bits[n] = boolE.copy();
        return intE;
    }

    public static BasicInstruction of(BoolERef boolE, IntERef res) {
        return new boolToIntE(boolE, res);
    }

    public BoolERef[] getBits() {
        return (BoolERef[]) bits;
    }

    /** Converts this IntE to a HashMap<Edge, Integer>. */
    public HashMap<Edge, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntE as a Java int");
        HashMap<Edge, Integer> res = new HashMap<>();
        for (Edge e : m.edges) res.put(e, 0);
        for (int i = 1; i <= n; i++) {
            BoolERef bit = (BoolERef) bits[i];
            HashMap<Edge, Boolean> bitMap = BoolE.decode(m.edges, bit.get());
            final int pos = n - i;
            res.replaceAll((e, val) -> bitMap.get(e) ? val | (1 << pos) : val);
        }

        BoolERef bit = (BoolERef) bits[0];
        HashMap<Edge, Boolean> bitMap = BoolE.decode(m.edges, bit.get());
        res.replaceAll((e, val) -> bitMap.get(e) ? val | (1 << 31) : val);

        return res;
    }

    public IntE copy() {
        IntE copy = new IntE(n);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }

    protected static void lShift(IntE a, IntE res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolERef.zeroes();
    }

    protected static void rShift(IntE a, IntE res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolERef.zeroes();
    }
}

class LShiftE implements BasicInstruction {
    private final IntERef a;
    private final IntERef res;
    private final int k;

    public LShiftE(IntERef a, IntERef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntE.lShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class RShiftE implements BasicInstruction {
    private final IntERef a;
    private final IntERef res;
    private final int k;

    public RShiftE(IntERef a, IntERef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntE.rShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class IntNotE extends Procedure {
    private final IntERef a;
    private final IntERef res;

    public IntNotE(IntERef a, IntERef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolERef[] bitsA = new BoolERef[a.get().n + 1];
        BoolERef[] bitsRes = new BoolERef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolERef();
            bitsRes[i] = new BoolERef();
        }

        add(IntField.split(a, bitsA));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.not(bitsA[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntAndE extends Procedure {
    private final IntERef a;
    private final IntERef b;
    private final IntERef res;

    public IntAndE(IntERef a, IntERef b, IntERef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolERef[] bitsA = new BoolERef[a.get().n + 1];
        BoolERef[] bitsB = new BoolERef[a.get().n + 1];
        BoolERef[] bitsRes = new BoolERef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolERef();
            bitsB[i] = new BoolERef();
            bitsRes[i] = new BoolERef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.and(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntOrE extends Procedure {
    private final IntERef a;
    private final IntERef b;
    private final IntERef res;

    public IntOrE(IntERef a, IntERef b, IntERef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolERef[] bitsA = new BoolERef[a.get().n + 1];
        BoolERef[] bitsB = new BoolERef[a.get().n + 1];
        BoolERef[] bitsRes = new BoolERef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolERef();
            bitsB[i] = new BoolERef();
            bitsRes[i] = new BoolERef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.or(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntXorE extends Procedure {
    private final IntERef a;
    private final IntERef b;
    private final IntERef res;

    public IntXorE(IntERef a, IntERef b, IntERef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolERef[] bitsA = new BoolERef[a.get().n + 1];
        BoolERef[] bitsB = new BoolERef[a.get().n + 1];
        BoolERef[] bitsRes = new BoolERef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolERef();
            bitsB[i] = new BoolERef();
            bitsRes[i] = new BoolERef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.xor(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class boolToIntE implements BasicInstruction {
    private final BoolERef boolE;
    private final IntERef res;

    public boolToIntE(BoolERef boolE, IntERef res) {
        this.boolE = boolE;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntE.of(boolE, res.get().n));
        return true;
    }
}

class IntRedAddEv extends Procedure {
    private final BoolEvRef orig;
    private final IntERef res;
    private final int breadth;

    public IntRedAddEv(BoolEvRef orig, IntERef res) {
        this.orig = orig;
        this.res = res;
        this.breadth = FieldManager.getBreadthE();
    }

    @Override
    protected void setInstructions() {
        BoolERef[] stack = new BoolERef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolERef();
        add(CommOp.redStack0(orig, stack));

        add(new SetRef<>(IntERef.of(IntE.of(0, res.get().n)), res));
        IntERef current = IntERef.of(new IntE(res.get().n));
        for (int i = 0; i < breadth; i++) {
            add(IntField.fromBool(stack[i], current));
            add(IntField.add(res, current, res));
        }
    }
}

class IntRedAddEf extends Procedure {
    private final BoolEfRef orig;
    private final IntERef res;
    private final int breadth;

    public IntRedAddEf(BoolEfRef orig, IntERef res) {
        this.orig = orig;
        this.res = res;
        this.breadth = FieldManager.getBreadthE();
    }

    @Override
    protected void setInstructions() {
        BoolERef[] stack = new BoolERef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolERef();
        add(CommOp.redStack0(orig, stack));

        add(new SetRef<>(IntERef.of(IntE.of(0, res.get().n)), res));
        IntERef current = IntERef.of(new IntE(res.get().n));
        for (int i = 0; i < breadth; i++) {
            add(IntField.fromBool(stack[i], current));
            add(IntField.add(res, current, res));
        }
    }
}

class IntAddE extends Procedure {
    private final IntERef a;
    private final IntERef b;
    private final IntERef res;
    private final int n;

    private final IntERef carry = new IntERef();
    private final IntERef tmp = new IntERef();

    public IntAddE(IntERef a, IntERef b, IntERef res) {
        this.a = a;
        this.b = b;
        this.res = res;
        this.n = a.get().n;
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

class IntNegE extends Procedure {
    private final IntERef a;
    private final IntERef res;

    public IntNegE(IntERef a, IntERef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        add(IntField.not(a, res));
        add(IntField.add(res, IntERef.of(IntE.of(1, res.get().n)), res));
    }
}

class IntSubE extends Procedure {
    private final IntERef a;
    private final IntERef b;
    private final IntERef res;

    private final IntERef negB = new IntERef();

    public IntSubE(IntERef a, IntERef b, IntERef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        add(IntField.neg(b, negB));
        add(IntField.add(a, negB, res));
    }
}

class GTE extends Procedure {
    private final IntERef a;
    private final IntERef b;
    private final BoolERef res;

    public GTE(IntERef a, IntERef b, BoolERef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        IntERef diff = new IntERef();
        add(IntField.sub(a, b, diff));

        BoolERef[] bits = new BoolERef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolERef();
        add(IntField.split(diff, bits));

        add(new SetRef<>(bits[0], res));
        add(BitOp.not(res, res));
    }
}

class SplitE implements BasicInstruction {
    private final IntERef a;
    private final BoolERef[] res;

    public SplitE(IntERef a, BoolERef[] res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= a.get().n; i++)
            res[i].set(a.get().getBits()[i].copy().get());
        return true;
    }
}

class JoinE implements BasicInstruction {
    private final BoolERef[] a;
    private final IntERef res;

    public JoinE(BoolERef[] a, IntERef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i < a.length; i++) res.get().bits[i] = a[i].copy();
        return true;
    }
}


