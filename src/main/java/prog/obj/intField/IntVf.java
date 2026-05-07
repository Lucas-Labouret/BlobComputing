package prog.obj.intField;

import field.boolField.fieldT.BoolVf;
import language.Procedure;
import language.basicInstruction.*;
import language.basicInstruction.bitOp.BitOp;
import language.fieldRef.BoolVfRef;
import medium.Medium;
import medium.locusT.Vf;
import prog.ref.intField.IntVfRef;
import prog.ref.intField.IntVfRef;
import prog.ref.intField.IntVfRef;

import java.util.HashMap;

/** IntVf represents an integer field on Vf loci. */
public class IntVf extends IntField<BoolVf> {
    public IntVf(int n) {
        super(n, new BoolVfRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolVfRef.zeroes();
    }

    public static IntVf of(int value, int n) {
        IntVf intVf = new IntVf(n);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intVf.bits[n - i] = (value & 1) == 0 ? BoolVfRef.zeroes() : BoolVfRef.ones();
            value = value >> 1;
        }
        intVf.bits[0] = value >>> 31 == 0 ? BoolVfRef.zeroes() : BoolVfRef.ones();
        return intVf;
    }

    public static IntVf of(BoolVfRef boolVf, int n) {
        IntVf intVf = new IntVf(n);
        for (int i = 1; i < n; i++) intVf.bits[i] = BoolVfRef.zeroes();
        intVf.bits[n] = boolVf.copy();
        return intVf;
    }

    public static BasicInstruction of(BoolVfRef boolVf, IntVfRef res) {
        return new boolToIntVf(boolVf, res);
    }

    public BoolVfRef[] getBits() {
        return (BoolVfRef[]) bits;
    }

    /** Converts this IntVf to a HashMap<Vf, Integer>. */
    public HashMap<Vf, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntVf as a Java int");
        HashMap<Vf, Integer> res = new HashMap<>();
        for (Vf vf : m.vfs) res.put(vf, 0);
        for (int i = 1; i <= n; i++) {
            BoolVfRef bit = (BoolVfRef) bits[i];
            HashMap<Vf, Boolean> bitMap = BoolVf.decode(m.vfs, bit.get());
            final int pos = n - i;
            res.replaceAll((vf, val) -> bitMap.get(vf) ? val | (1 << pos) : val);
        }

        BoolVfRef bit = (BoolVfRef) bits[0];
        HashMap<Vf, Boolean> bitMap = BoolVf.decode(m.vfs, bit.get());
        res.replaceAll((vf, val) -> bitMap.get(vf) ? val | (1 << 31) : val);

        return res;
    }

    protected static void lShift(IntVf a, IntVf res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolVfRef.zeroes();
    }

    protected static void rShift(IntVf a, IntVf res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolVfRef.zeroes();
    }

    public IntVf copy() {
        IntVf copy = new IntVf(n);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}

class LShiftVf implements BasicInstruction {
    private final IntVfRef a;
    private final IntVfRef res;
    private final int k;

    public LShiftVf(IntVfRef a, IntVfRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntVf.lShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class RShiftVf implements BasicInstruction {
    private final IntVfRef a;
    private final IntVfRef res;
    private final int k;

    public RShiftVf(IntVfRef a, IntVfRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntVf.rShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class IntNotVf extends Procedure {
    private final IntVfRef a;
    private final IntVfRef res;

    public IntNotVf(IntVfRef a, IntVfRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolVfRef[] bitsA = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsRes = new BoolVfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVfRef();
            bitsRes[i] = new BoolVfRef();
        }

        add(IntField.split(a, bitsA));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.not(bitsA[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntAndVf extends Procedure {
    private final IntVfRef a;
    private final IntVfRef b;
    private final IntVfRef res;

    public IntAndVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolVfRef[] bitsA = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsB = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsRes = new BoolVfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVfRef();
            bitsB[i] = new BoolVfRef();
            bitsRes[i] = new BoolVfRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.and(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntOrVf extends Procedure {
    private final IntVfRef a;
    private final IntVfRef b;
    private final IntVfRef res;

    public IntOrVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolVfRef[] bitsA = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsB = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsRes = new BoolVfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVfRef();
            bitsB[i] = new BoolVfRef();
            bitsRes[i] = new BoolVfRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.or(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntXorVf extends Procedure {
    private final IntVfRef a;
    private final IntVfRef b;
    private final IntVfRef res;

    public IntXorVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolVfRef[] bitsA = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsB = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsRes = new BoolVfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVfRef();
            bitsB[i] = new BoolVfRef();
            bitsRes[i] = new BoolVfRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.xor(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class boolToIntVf implements BasicInstruction {
    private final BoolVfRef boolVf;
    private final IntVfRef res;

    public boolToIntVf(BoolVfRef boolVf, IntVfRef res) {
        this.boolVf = boolVf;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntVf.of(boolVf, res.get().n));
        return true;
    }
}

class IntAddVf extends Procedure {
    private final IntVfRef a;
    private final IntVfRef b;
    private final IntVfRef res;
    private final int n;

    private final IntVfRef carry = new IntVfRef();
    private final IntVfRef tmp = new IntVfRef();

    public IntAddVf(IntVfRef a, IntVfRef b, IntVfRef res) {
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

class IntNegVf extends Procedure {
    private final IntVfRef a;
    private final IntVfRef res;

    public IntNegVf(IntVfRef a, IntVfRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        add(IntField.not(a, res));
        add(IntField.add(res, IntVfRef.of(IntVf.of(1, res.get().n)), res));
    }
}

class IntSubVf extends Procedure {
    private final IntVfRef a;
    private final IntVfRef b;
    private final IntVfRef res;

    private final IntVfRef negB = new IntVfRef();

    public IntSubVf(IntVfRef a, IntVfRef b, IntVfRef res) {
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

class GTVf extends Procedure {
    private final IntVfRef a;
    private final IntVfRef b;
    private final BoolVfRef res;

    public GTVf(IntVfRef a, IntVfRef b, BoolVfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        IntVfRef diff = new IntVfRef();
        add(IntField.sub(a, b, diff));

        BoolVfRef[] bits = new BoolVfRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolVfRef();
        add(IntField.split(diff, bits));

        add(new SetRef<>(bits[0], res));
        add(BitOp.not(res, res));
    }
}

class SplitVf implements BasicInstruction {
    private final IntVfRef a;
    private final BoolVfRef[] res;

    public SplitVf(IntVfRef a, BoolVfRef[] res) {
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

class JoinVf implements BasicInstruction {
    private final BoolVfRef[] a;
    private final IntVfRef res;

    public JoinVf(BoolVfRef[] a, IntVfRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i < a.length; i++) res.get().bits[i] = a[i].copy();
        return true;
    }
}


