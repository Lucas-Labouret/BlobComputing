package prog.obj.intField;

import field.boolField.fieldT.BoolVe;
import language.Procedure;
import language.basicInstruction.*;
import language.basicInstruction.bitOp.BitOp;
import language.fieldRef.BoolVeRef;
import medium.Medium;
import medium.locusT.Ve;
import prog.ref.intField.IntVeRef;
import prog.ref.intField.IntVeRef;

import java.util.HashMap;

/** IntVe represents an integer field on Ve loci. */
public class IntVe extends IntField<BoolVe> {
    public IntVe(int n) {
        super(n, new BoolVeRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolVeRef.zeroes();
    }

    public static IntVe of(int value, int n) {
        IntVe intVe = new IntVe(n);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intVe.bits[n - i] = (value & 1) == 0 ? BoolVeRef.zeroes() : BoolVeRef.ones();
            value = value >> 1;
        }
        intVe.bits[0] = value >>> 31 == 0 ? BoolVeRef.zeroes() : BoolVeRef.ones();
        return intVe;
    }

    public static BasicInstruction of(BoolVeRef boolVe, IntVeRef res) {
        return new boolToIntVe(boolVe, res);
    }

    public static IntVe of(BoolVeRef boolVe, int n) {
        IntVe intVe = new IntVe(n);
        for (int i = 1; i < n; i++) intVe.bits[i] = BoolVeRef.zeroes();
        intVe.bits[n] = boolVe.copy();
        return intVe;
    }

    public BoolVeRef[] getBits() {
        return (BoolVeRef[]) bits;
    }

    /** Converts this IntVe to a HashMap<Ve, Integer>. */
    public HashMap<Ve, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntVe as a Java int");
        HashMap<Ve, Integer> res = new HashMap<>();
        for (Ve ve : m.ves) res.put(ve, 0);
        for (int i = 1; i <= n; i++) {
            BoolVeRef bit = (BoolVeRef) bits[i];
            HashMap<Ve, Boolean> bitMap = BoolVe.decode(m.ves, bit.get());
            final int pos = n - i;
            res.replaceAll((ve, val) -> bitMap.get(ve) ? val | (1 << pos) : val);
        }

        BoolVeRef bit = (BoolVeRef) bits[0];
        HashMap<Ve, Boolean> bitMap = BoolVe.decode(m.ves, bit.get());
        res.replaceAll((ve, val) -> bitMap.get(ve) ? val | (1 << 31) : val);

        return res;
    }

    protected static void lShift(IntVe a, IntVe res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolVeRef.zeroes();
    }

    protected static void rShift(IntVe a, IntVe res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolVeRef.zeroes();
    }

    public IntVe copy() {
        IntVe copy = new IntVe(n);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}

class LShiftVe implements BasicInstruction {
    private final IntVeRef a;
    private final IntVeRef res;
    private final int k;

    public LShiftVe(IntVeRef a, IntVeRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntVe.lShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class RShiftVe implements BasicInstruction {
    private final IntVeRef a;
    private final IntVeRef res;
    private final int k;

    public RShiftVe(IntVeRef a, IntVeRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntVe.rShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class IntNotVe extends Procedure {
    private final IntVeRef a;
    private final IntVeRef res;

    public IntNotVe(IntVeRef a, IntVeRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolVeRef[] bitsA = new BoolVeRef[a.get().n + 1];
        BoolVeRef[] bitsRes = new BoolVeRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVeRef();
            bitsRes[i] = new BoolVeRef();
        }

        add(IntField.split(a, bitsA));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.not(bitsA[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntAndVe extends Procedure {
    private final IntVeRef a;
    private final IntVeRef b;
    private final IntVeRef res;

    public IntAndVe(IntVeRef a, IntVeRef b, IntVeRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolVeRef[] bitsA = new BoolVeRef[a.get().n + 1];
        BoolVeRef[] bitsB = new BoolVeRef[a.get().n + 1];
        BoolVeRef[] bitsRes = new BoolVeRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVeRef();
            bitsB[i] = new BoolVeRef();
            bitsRes[i] = new BoolVeRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.and(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntOrVe extends Procedure {
    private final IntVeRef a;
    private final IntVeRef b;
    private final IntVeRef res;

    public IntOrVe(IntVeRef a, IntVeRef b, IntVeRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolVeRef[] bitsA = new BoolVeRef[a.get().n + 1];
        BoolVeRef[] bitsB = new BoolVeRef[a.get().n + 1];
        BoolVeRef[] bitsRes = new BoolVeRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVeRef();
            bitsB[i] = new BoolVeRef();
            bitsRes[i] = new BoolVeRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.or(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntXorVe extends Procedure {
    private final IntVeRef a;
    private final IntVeRef b;
    private final IntVeRef res;

    public IntXorVe(IntVeRef a, IntVeRef b, IntVeRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolVeRef[] bitsA = new BoolVeRef[a.get().n + 1];
        BoolVeRef[] bitsB = new BoolVeRef[a.get().n + 1];
        BoolVeRef[] bitsRes = new BoolVeRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVeRef();
            bitsB[i] = new BoolVeRef();
            bitsRes[i] = new BoolVeRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.xor(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class boolToIntVe implements BasicInstruction {
    private final BoolVeRef boolVe;
    private final IntVeRef res;

    public boolToIntVe(BoolVeRef boolVe, IntVeRef res) {
        this.boolVe = boolVe;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntVe.of(boolVe, res.get().n));
        return true;
    }
}

class IntAddVe extends Procedure {
    private final IntVeRef a;
    private final IntVeRef b;
    private final IntVeRef res;
    private final int n;

    private final IntVeRef carry = new IntVeRef();
    private final IntVeRef tmp = new IntVeRef();

    public IntAddVe(IntVeRef a, IntVeRef b, IntVeRef res) {
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

class IntNegVe extends Procedure {
    private final IntVeRef a;
    private final IntVeRef res;

    public IntNegVe(IntVeRef a, IntVeRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        add(IntField.not(a, res));
        add(IntField.add(res, IntVeRef.of(IntVe.of(1, res.get().n)), res));
    }
}

class IntSubVe extends Procedure {
    private final IntVeRef a;
    private final IntVeRef b;
    private final IntVeRef res;

    private final IntVeRef negB = new IntVeRef();

    public IntSubVe(IntVeRef a, IntVeRef b, IntVeRef res) {
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

class GTVe extends Procedure {
    private final IntVeRef a;
    private final IntVeRef b;
    private final BoolVeRef res;

    public GTVe(IntVeRef a, IntVeRef b, BoolVeRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        IntVeRef diff = new IntVeRef();
        add(IntField.sub(a, b, diff));

        BoolVeRef[] bits = new BoolVeRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolVeRef();
        add(IntField.split(diff, bits));

        add(new SetRef<>(bits[0], res));
        add(BitOp.not(res, res));
    }
}

class SplitVe implements BasicInstruction {
    private final IntVeRef a;
    private final BoolVeRef[] res;

    public SplitVe(IntVeRef a, BoolVeRef[] res) {
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

class JoinVe implements BasicInstruction {
    private final BoolVeRef[] a;
    private final IntVeRef res;

    public JoinVe(BoolVeRef[] a, IntVeRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i < a.length; i++) res.get().bits[i] = a[i].copy();
        return true;
    }
}


