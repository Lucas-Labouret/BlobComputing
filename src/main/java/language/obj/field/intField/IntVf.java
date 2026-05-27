package language.obj.field.intField;

import language.ref.field.boolField.fieldT.*;
import language.utils.BoolFieldManager;
import language.utils.Border;
import language.obj.field.boolField.fieldT.BoolVf;
import language.instruction.Procedure;
import language.instruction.basicInstruction.*;
import language.ref.field.intField.*;
import medium.Medium;
import medium.locusT.Vf;
import java.util.HashMap;

/** IntVf represents an integer language.obj.field on Vf loci. */
public class IntVf extends IntField<BoolVf> {
    public IntVf(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntVf(int n, Border border) {
        super(n, new BoolVfRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolVfRef.of(BoolVf.zeroes(border));
    }

    public static IntVf of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }

    public static IntVf of(int value, int n, Border border) {
        IntVf intVf = new IntVf(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intVf.bits[n - i] = (value & 1) == 0 ? BoolVfRef.of(BoolVf.zeroes(border)) : BoolVfRef.of(BoolVf.ones(border));
            value = value >> 1;
        }
        intVf.bits[0] = value >>> 31 == 0 ? BoolVfRef.of(BoolVf.zeroes(border)) : BoolVfRef.of(BoolVf.ones(border));
        return intVf;
    }

    public static IntVf of(BoolVfRef boolVf, int n) {
        IntVf intVf = new IntVf(n, boolVf.get().border);
        for (int i = 1; i < n; i++) intVf.bits[i] = BoolVfRef.of(BoolVf.zeroes(boolVf.get().border));
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
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolVfRef.of(BoolVf.zeroes(a.border));
    }

    protected static void rShift(IntVf a, IntVf res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolVfRef.of(BoolVf.zeroes(a.border));
    }

    public IntVf copy() {
        IntVf copy = new IntVf(n, border);
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
    public IntNotVf(IntVfRef a, IntVfRef res) {
        BoolVfRef[] bitsA = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsRes = new BoolVfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVfRef();
            bitsRes[i] = new BoolVfRef();
        }

        split(a, bitsA);
        for (int i = 0; i <= a.get().n; i++) not(bitsA[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntAndVf extends Procedure {
    public IntAndVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        BoolVfRef[] bitsA = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsB = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsRes = new BoolVfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVfRef();
            bitsB[i] = new BoolVfRef();
            bitsRes[i] = new BoolVfRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntOrVf extends Procedure {
    public IntOrVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        BoolVfRef[] bitsA = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsB = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsRes = new BoolVfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVfRef();
            bitsB[i] = new BoolVfRef();
            bitsRes[i] = new BoolVfRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntXorVf extends Procedure {
    public IntXorVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        BoolVfRef[] bitsA = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsB = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bitsRes = new BoolVfRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolVfRef();
            bitsB[i] = new BoolVfRef();
            bitsRes[i] = new BoolVfRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
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
    public IntAddVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        IntVfRef carry = new IntVfRef();
        IntVfRef tmp = new IntVfRef();

        set(a, res);
        set(b, tmp);
        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class IntNegVf extends Procedure {
    public IntNegVf(IntVfRef a, IntVfRef res) {
        not(a, res);
        add(res, IntVfRef.of(IntVf.of(1, res.get().n)), res);
    }
}

class IntSubVf extends Procedure {
    public IntSubVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        IntVfRef negB = IntVfRef.of(new IntVf(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class GTVf extends Procedure {
    public GTVf(IntVfRef a, IntVfRef b, BoolVfRef res) {
        IntVfRef diff = new IntVfRef();
        sub(a, b, diff);

        BoolVfRef[] bits = new BoolVfRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolVfRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
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
        for (int i = 0; i <= a.get().n; i++) res[i].set(a.get().getBits()[i].copy().get());
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
        IntVf tmp = new IntVf(res.get().n);
        for (int i = 0; i <= res.get().n; i++) tmp.bits[i] = a[i].copy();
        res.set(tmp);
        return true;
    }
}


