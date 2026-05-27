package language.obj.field.intField;

import language.instruction.Procedure;
import language.instruction.basicInstruction.BasicInstruction;
import language.obj.field.boolField.fieldT.BoolFe;
import language.ref.field.boolField.fieldT.BoolFeRef;
import language.ref.field.intField.IntFeRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusT.Fe;

import java.util.HashMap;

/** IntFe represents an integer language.obj.field on Fe loci. */
public class IntFe extends IntField<BoolFe> {
    public IntFe(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntFe(int n, Border border) {
        super(n, new BoolFeRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolFeRef.of(BoolFe.zeroes(border));
    }

    public static IntFe of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }
    public static IntFe of(int value, int n, Border border) {
        IntFe intFe = new IntFe(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intFe.bits[n - i] = (value & 1) == 0 ? BoolFeRef.of(BoolFe.zeroes(border)) : BoolFeRef.of(BoolFe.ones(border));
            value = value >> 1;
        }
        intFe.bits[0] = value >>> 31 == 0 ? BoolFeRef.of(BoolFe.zeroes(border)) : BoolFeRef.of(BoolFe.ones(border));
        return intFe;
    }

    public static IntFe of(BoolFeRef boolFe, int n) {
        IntFe intFe = new IntFe(n, boolFe.get().border);
        for (int i = 1; i < n; i++) intFe.bits[i] = BoolFeRef.of(BoolFe.zeroes(boolFe.get().border));
        intFe.bits[n] = boolFe.copy();
        return intFe;
    }

    public static BasicInstruction of(BoolFeRef boolFe, IntFeRef res) {
        return new boolToIntFe(boolFe, res);
    }

    public BoolFeRef[] getBits() {
        return (BoolFeRef[]) bits;
    }

    /** Converts this IntFe to a HashMap<Fe, Integer>. */
    public HashMap<Fe, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntFe as a Java int");
        HashMap<Fe, Integer> res = new HashMap<>();
        for (Fe fe : m.fes) res.put(fe, 0);
        for (int i = 1; i <= n; i++) {
            BoolFeRef bit = (BoolFeRef) bits[i];
            HashMap<Fe, Boolean> bitMap = BoolFe.decode(m.fes, bit.get());
            final int pos = n - i;
            res.replaceAll((fe, val) -> bitMap.get(fe) ? val | (1 << pos) : val);
        }

        BoolFeRef bit = (BoolFeRef) bits[0];
        HashMap<Fe, Boolean> bitMap = BoolFe.decode(m.fes, bit.get());
        res.replaceAll((fe, val) -> bitMap.get(fe) ? val | (1 << 31) : val);

        return res;
    }

    protected static void lShift(IntFe a, IntFe res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolFeRef.of(BoolFe.zeroes(a.border));
    }

    protected static void rShift(IntFe a, IntFe res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolFeRef.of(BoolFe.zeroes(a.border));
    }

    public IntFe copy() {
        IntFe copy = new IntFe(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}

class LShiftFe implements BasicInstruction {
    private final IntFeRef a;
    private final IntFeRef res;
    private final int k;

    public LShiftFe(IntFeRef a, IntFeRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntFe.lShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class RShiftFe implements BasicInstruction {
    private final IntFeRef a;
    private final IntFeRef res;
    private final int k;

    public RShiftFe(IntFeRef a, IntFeRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntFe.rShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class IntNotFe extends Procedure {
    public IntNotFe(IntFeRef a, IntFeRef res) {
        BoolFeRef[] bitsA = new BoolFeRef[a.get().n + 1];
        BoolFeRef[] bitsRes = new BoolFeRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFeRef();
            bitsRes[i] = new BoolFeRef();
        }

        split(a, bitsA);
        for (int i = 0; i <= a.get().n; i++) not(bitsA[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntAndFe extends Procedure {
    public IntAndFe(IntFeRef a, IntFeRef b, IntFeRef res) {
        BoolFeRef[] bitsA = new BoolFeRef[a.get().n + 1];
        BoolFeRef[] bitsB = new BoolFeRef[a.get().n + 1];
        BoolFeRef[] bitsRes = new BoolFeRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFeRef();
            bitsB[i] = new BoolFeRef();
            bitsRes[i] = new BoolFeRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntOrFe extends Procedure {
    public IntOrFe(IntFeRef a, IntFeRef b, IntFeRef res) {
        BoolFeRef[] bitsA = new BoolFeRef[a.get().n + 1];
        BoolFeRef[] bitsB = new BoolFeRef[a.get().n + 1];
        BoolFeRef[] bitsRes = new BoolFeRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFeRef();
            bitsB[i] = new BoolFeRef();
            bitsRes[i] = new BoolFeRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntXorFe extends Procedure {
    public IntXorFe(IntFeRef a, IntFeRef b, IntFeRef res) {
        BoolFeRef[] bitsA = new BoolFeRef[a.get().n + 1];
        BoolFeRef[] bitsB = new BoolFeRef[a.get().n + 1];
        BoolFeRef[] bitsRes = new BoolFeRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFeRef();
            bitsB[i] = new BoolFeRef();
            bitsRes[i] = new BoolFeRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class boolToIntFe implements BasicInstruction {
    private final BoolFeRef boolFe;
    private final IntFeRef res;

    public boolToIntFe(BoolFeRef boolFe, IntFeRef res) {
        this.boolFe = boolFe;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntFe.of(boolFe, res.get().n));
        return true;
    }
}

class IntAddFe extends Procedure {
    public IntAddFe(IntFeRef a, IntFeRef b, IntFeRef res) {
        IntFeRef carry = new IntFeRef();
        IntFeRef tmp = new IntFeRef();
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class IntNegFe extends Procedure {
    public IntNegFe(IntFeRef a, IntFeRef res) {
        not(a, res);
        add(res, IntFeRef.of(IntFe.of(1, res.get().n)), res);
    }
}

class IntSubFe extends Procedure {
    public IntSubFe(IntFeRef a, IntFeRef b, IntFeRef res) {
        IntFeRef negB = IntFeRef.of(new IntFe(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class GTFe extends Procedure {
    public GTFe(IntFeRef a, IntFeRef b, BoolFeRef res) {
        IntFeRef diff = new IntFeRef();
        sub(a, b, diff);

        BoolFeRef[] bits = new BoolFeRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolFeRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class SplitFe implements BasicInstruction {
    private final IntFeRef a;
    private final BoolFeRef[] res;

    public SplitFe(IntFeRef a, BoolFeRef[] res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= a.get().n; i++) res[i].set(a.get().getBits()[i].copy().get());
        return true;
    }
}

class JoinFe implements BasicInstruction {
    private final BoolFeRef[] a;
    private final IntFeRef res;

    public JoinFe(BoolFeRef[] a, IntFeRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        IntFe tmp = new IntFe(res.get().n);
        for (int i = 0; i <= res.get().n; i++) tmp.bits[i] = a[i].copy();
        res.set(tmp);
        return true;
    }
}
