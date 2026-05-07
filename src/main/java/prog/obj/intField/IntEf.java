package prog.obj.intField;

import field.boolField.fieldT.BoolEf;
import language.Procedure;
import language.basicInstruction.*;
import language.basicInstruction.bitOp.BitOp;
import language.fieldRef.BoolEfRef;
import medium.Medium;
import medium.locusT.Ef;
import prog.ref.intField.IntEfRef;
import prog.ref.intField.IntEfRef;

import java.util.HashMap;

/** IntEf represents an integer field on Ef loci. */
public class IntEf extends IntField<BoolEf> {
    public IntEf(int n) {
        super(n, new BoolEfRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolEfRef.zeroes();
    }

    public static IntEf of(int value, int n) {
        IntEf intEf = new IntEf(n);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intEf.bits[n - i] = (value & 1) == 0 ? BoolEfRef.zeroes() : BoolEfRef.ones();
            value = value >> 1;
        }
        intEf.bits[0] = value >>> 31 == 0 ? BoolEfRef.zeroes() : BoolEfRef.ones();
        return intEf;
    }

    public static IntEf of(BoolEfRef boolEf, int n) {
        IntEf intEf = new IntEf(n);
        for (int i = 1; i < n; i++) intEf.bits[i] = BoolEfRef.zeroes();
        intEf.bits[n] = boolEf.copy();
        return intEf;
    }

    public static BasicInstruction of(BoolEfRef boolEf, IntEfRef res) {
        return new boolToIntEf(boolEf, res);
    }

    public BoolEfRef[] getBits() {
        return (BoolEfRef[]) bits;
    }

    /** Converts this IntEf to a HashMap<Ef, Integer>. */
    public HashMap<Ef, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntEf as a Java int");
        HashMap<Ef, Integer> res = new HashMap<>();
        for (Ef ef : m.efs) res.put(ef, 0);
        for (int i = 1; i <= n; i++) {
            BoolEfRef bit = (BoolEfRef) bits[i];
            HashMap<Ef, Boolean> bitMap = BoolEf.decode(m.efs, bit.get());
            final int pos = n - i;
            res.replaceAll((ef, val) -> bitMap.get(ef) ? val | (1 << pos) : val);
        }

        BoolEfRef bit = (BoolEfRef) bits[0];
        HashMap<Ef, Boolean> bitMap = BoolEf.decode(m.efs, bit.get());
        res.replaceAll((ef, val) -> bitMap.get(ef) ? val | (1 << 31) : val);

        return res;
    }

    protected static void lShift(IntEf a, IntEf res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolEfRef.zeroes();
    }

    protected static void rShift(IntEf a, IntEf res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolEfRef.zeroes();
    }

    public IntEf copy() {
        IntEf copy = new IntEf(n);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}

class LShiftEf implements BasicInstruction {
    private final IntEfRef a;
    private final IntEfRef res;
    private final int k;

    public LShiftEf(IntEfRef a, IntEfRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntEf.lShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class RShiftEf implements BasicInstruction {
    private final IntEfRef a;
    private final IntEfRef res;
    private final int k;

    public RShiftEf(IntEfRef a, IntEfRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntEf.rShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class IntNotEf extends Procedure {
    private final IntEfRef a;
    private final IntEfRef res;

    public IntNotEf(IntEfRef a, IntEfRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolEfRef[] bitsA = new BoolEfRef[a.get().n + 1];
        BoolEfRef[] bitsRes = new BoolEfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolEfRef();
            bitsRes[i] = new BoolEfRef();
        }

        add(IntField.split(a, bitsA));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.not(bitsA[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntAndEf extends Procedure {
    private final IntEfRef a;
    private final IntEfRef b;
    private final IntEfRef res;

    public IntAndEf(IntEfRef a, IntEfRef b, IntEfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolEfRef[] bitsA = new BoolEfRef[a.get().n + 1];
        BoolEfRef[] bitsB = new BoolEfRef[a.get().n + 1];
        BoolEfRef[] bitsRes = new BoolEfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolEfRef();
            bitsB[i] = new BoolEfRef();
            bitsRes[i] = new BoolEfRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.and(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntOrEf extends Procedure {
    private final IntEfRef a;
    private final IntEfRef b;
    private final IntEfRef res;

    public IntOrEf(IntEfRef a, IntEfRef b, IntEfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolEfRef[] bitsA = new BoolEfRef[a.get().n + 1];
        BoolEfRef[] bitsB = new BoolEfRef[a.get().n + 1];
        BoolEfRef[] bitsRes = new BoolEfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolEfRef();
            bitsB[i] = new BoolEfRef();
            bitsRes[i] = new BoolEfRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.or(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntXorEf extends Procedure {
    private final IntEfRef a;
    private final IntEfRef b;
    private final IntEfRef res;

    public IntXorEf(IntEfRef a, IntEfRef b, IntEfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolEfRef[] bitsA = new BoolEfRef[a.get().n + 1];
        BoolEfRef[] bitsB = new BoolEfRef[a.get().n + 1];
        BoolEfRef[] bitsRes = new BoolEfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolEfRef();
            bitsB[i] = new BoolEfRef();
            bitsRes[i] = new BoolEfRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.xor(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class boolToIntEf implements BasicInstruction {
    private final BoolEfRef boolEf;
    private final IntEfRef res;

    public boolToIntEf(BoolEfRef boolEf, IntEfRef res) {
        this.boolEf = boolEf;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntEf.of(boolEf, res.get().n));
        return true;
    }
}

class IntAddEf extends Procedure {
    private final IntEfRef a;
    private final IntEfRef b;
    private final IntEfRef res;
    private final int n;

    private final IntEfRef carry = new IntEfRef();
    private final IntEfRef tmp = new IntEfRef();

    public IntAddEf(IntEfRef a, IntEfRef b, IntEfRef res) {
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

class IntNegEf extends Procedure {
    private final IntEfRef a;
    private final IntEfRef res;

    public IntNegEf(IntEfRef a, IntEfRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        add(IntField.not(a, res));
        add(IntField.add(res, IntEfRef.of(IntEf.of(1, res.get().n)), res));
    }
}

class IntSubEf extends Procedure {
    private final IntEfRef a;
    private final IntEfRef b;
    private final IntEfRef res;

    private final IntEfRef negB = new IntEfRef();

    public IntSubEf(IntEfRef a, IntEfRef b, IntEfRef res) {
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

class GTEf extends Procedure {
    private final IntEfRef a;
    private final IntEfRef b;
    private final BoolEfRef res;

    public GTEf(IntEfRef a, IntEfRef b, BoolEfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        IntEfRef diff = new IntEfRef();
        add(IntField.sub(a, b, diff));

        BoolEfRef[] bits = new BoolEfRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolEfRef();
        add(IntField.split(diff, bits));

        add(new SetRef<>(bits[0], res));
        add(BitOp.not(res, res));
    }
}

class SplitEf implements BasicInstruction {
    private final IntEfRef a;
    private final BoolEfRef[] res;

    public SplitEf(IntEfRef a, BoolEfRef[] res) {
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

class JoinEf implements BasicInstruction {
    private final BoolEfRef[] a;
    private final IntEfRef res;

    public JoinEf(BoolEfRef[] a, IntEfRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i < a.length; i++) res.get().bits[i] = a[i].copy();
        return true;
    }
}
