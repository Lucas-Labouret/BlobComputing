package language.obj.field.intField;

import language.instruction.Procedure;
import language.instruction.basicInstruction.BasicInstruction;
import language.obj.field.boolField.fieldT.BoolFv;
import language.ref.field.boolField.fieldT.BoolFvRef;
import language.ref.field.intField.IntFvRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusT.Fv;

import java.util.HashMap;

/** IntFv represents an integer language.obj.field on Fv loci. */
public class IntFv extends IntField<BoolFv> {
    public IntFv(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntFv(int n, Border border) {
        super(n, new BoolFvRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolFvRef.of(BoolFv.zeroes(border));
    }

    public static IntFv of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }

    public static IntFv of(int value, int n, Border border) {
        IntFv intFv = new IntFv(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intFv.bits[n - i] = (value & 1) == 0 ? BoolFvRef.of(BoolFv.zeroes(border)) : BoolFvRef.of(BoolFv.ones(border));
            value = value >> 1;
        }
        intFv.bits[0] = value >>> 31 == 0 ? BoolFvRef.of(BoolFv.zeroes(border)) : BoolFvRef.of(BoolFv.ones(border));
        return intFv;
    }

    public static IntFv of(BoolFvRef boolFv, int n) {
        IntFv intFv = new IntFv(n, boolFv.get().border);
        for (int i = 1; i < n; i++) intFv.bits[i] = BoolFvRef.of(BoolFv.zeroes(boolFv.get().border));
        intFv.bits[n] = boolFv.copy();
        return intFv;
    }

    public static BasicInstruction of(BoolFvRef boolFv, IntFvRef res) {
        return new boolToIntFv(boolFv, res);
    }

    public BoolFvRef[] getBits() {
        return (BoolFvRef[]) bits;
    }

    /** Converts this IntFv to a HashMap<Fv, Integer>. */
    public HashMap<Fv, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntFv as a Java int");
        HashMap<Fv, Integer> res = new HashMap<>();
        for (Fv fv : m.fvs) res.put(fv, 0);
        for (int i = 1; i <= n; i++) {
            BoolFvRef bit = (BoolFvRef) bits[i];
            HashMap<Fv, Boolean> bitMap = BoolFv.decode(m.fvs, bit.get());
            final int pos = n - i;
            res.replaceAll((fv, val) -> bitMap.get(fv) ? val | (1 << pos) : val);
        }

        BoolFvRef bit = (BoolFvRef) bits[0];
        HashMap<Fv, Boolean> bitMap = BoolFv.decode(m.fvs, bit.get());
        res.replaceAll((fv, val) -> bitMap.get(fv) ? val | (1 << 31) : val);

        return res;
    }

    protected static void lShift(IntFv a, IntFv res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolFvRef.of(BoolFv.zeroes(a.border));
    }

    protected static void rShift(IntFv a, IntFv res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolFvRef.of(BoolFv.zeroes(a.border));
    }

    public IntFv copy() {
        IntFv copy = new IntFv(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}

class LShiftFv implements BasicInstruction {
    private final IntFvRef a;
    private final IntFvRef res;
    private final int k;

    public LShiftFv(IntFvRef a, IntFvRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntFv.lShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class RShiftFv implements BasicInstruction {
    private final IntFvRef a;
    private final IntFvRef res;
    private final int k;

    public RShiftFv(IntFvRef a, IntFvRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntFv.rShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class IntNotFv extends Procedure {
    public IntNotFv(IntFvRef a, IntFvRef res) {
        BoolFvRef[] bitsA = new BoolFvRef[a.get().n + 1];
        BoolFvRef[] bitsRes = new BoolFvRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFvRef();
            bitsRes[i] = new BoolFvRef();
        }

        split(a, bitsA);
        for (int i = 0; i <= a.get().n; i++) not(bitsA[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntAndFv extends Procedure {
    public IntAndFv(IntFvRef a, IntFvRef b, IntFvRef res) {
        BoolFvRef[] bitsA = new BoolFvRef[a.get().n + 1];
        BoolFvRef[] bitsB = new BoolFvRef[a.get().n + 1];
        BoolFvRef[] bitsRes = new BoolFvRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFvRef();
            bitsB[i] = new BoolFvRef();
            bitsRes[i] = new BoolFvRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntOrFv extends Procedure {
    public IntOrFv(IntFvRef a, IntFvRef b, IntFvRef res) {
        BoolFvRef[] bitsA = new BoolFvRef[a.get().n + 1];
        BoolFvRef[] bitsB = new BoolFvRef[a.get().n + 1];
        BoolFvRef[] bitsRes = new BoolFvRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFvRef();
            bitsB[i] = new BoolFvRef();
            bitsRes[i] = new BoolFvRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntXorFv extends Procedure {
    public IntXorFv(IntFvRef a, IntFvRef b, IntFvRef res) {
        BoolFvRef[] bitsA = new BoolFvRef[a.get().n + 1];
        BoolFvRef[] bitsB = new BoolFvRef[a.get().n + 1];
        BoolFvRef[] bitsRes = new BoolFvRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFvRef();
            bitsB[i] = new BoolFvRef();
            bitsRes[i] = new BoolFvRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class boolToIntFv implements BasicInstruction {
    private final BoolFvRef boolFv;
    private final IntFvRef res;

    public boolToIntFv(BoolFvRef boolFv, IntFvRef res) {
        this.boolFv = boolFv;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntFv.of(boolFv, res.get().n));
        return true;
    }
}

class IntAddFv extends Procedure {
    public IntAddFv(IntFvRef a, IntFvRef b, IntFvRef res) {
        IntFvRef carry = IntFvRef.of(new IntFv(a.get().n));
        IntFvRef tmp = IntFvRef.of(new IntFv(a.get().n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class IntNegFv extends Procedure {
    public IntNegFv(IntFvRef a, IntFvRef res) {
        not(a, res);
        add(res, IntFvRef.of(IntFv.of(1, res.get().n)), res);
    }
}

class IntSubFv extends Procedure {
    public IntSubFv(IntFvRef a, IntFvRef b, IntFvRef res) {
        IntFvRef negB = new IntFvRef();
        neg(b, negB);
        add(a, negB, res);
    }
}

class GTFv extends Procedure {
    public GTFv(IntFvRef a, IntFvRef b, BoolFvRef res) {
        IntFvRef diff = new IntFvRef();
        sub(a, b, diff);

        BoolFvRef[] bits = new BoolFvRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolFvRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class SplitFv implements BasicInstruction {
    private final IntFvRef a;
    private final BoolFvRef[] res;

    public SplitFv(IntFvRef a, BoolFvRef[] res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= a.get().n; i++) res[i].set(a.get().getBits()[i].copy().get());
        return true;
    }
}

class JoinFv implements BasicInstruction {
    private final BoolFvRef[] a;
    private final IntFvRef res;

    public JoinFv(BoolFvRef[] a, IntFvRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i < res.get().n; i++) res.get().bits[i] = a[i].copy();
        return true;
    }
}
