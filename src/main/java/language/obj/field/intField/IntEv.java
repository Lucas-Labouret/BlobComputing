package language.obj.field.intField;

import language.utils.BoolFieldManager;
import language.utils.Border;
import language.obj.field.boolField.fieldT.BoolEv;
import language.instruction.Procedure;
import language.instruction.basicInstruction.*;
import language.ref.field.boolField.fieldT.BoolEvRef;
import medium.Medium;
import medium.locusT.Ev;
import language.ref.field.intField.IntEvRef;

import java.util.HashMap;

/** IntEv represents an integer language.obj.field on Ev loci. */
public class IntEv extends IntField<BoolEv> {
    public IntEv(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntEv(int n, Border border) {
        super(n, new BoolEvRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolEvRef.of(BoolEv.zeroes(border));
    }

    public static IntEv of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }

    public static IntEv of(int value, int n, Border border) {
        IntEv intEv = new IntEv(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intEv.bits[n - i] = (value & 1) == 0 ? BoolEvRef.of(BoolEv.zeroes(border)) : BoolEvRef.of(BoolEv.ones(border));
            value = value >> 1;
        }
        intEv.bits[0] = value >>> 31 == 0 ? BoolEvRef.of(BoolEv.zeroes(border)) : BoolEvRef.of(BoolEv.ones(border));
        return intEv;
    }

    public static IntEv of(BoolEvRef boolEv, int n) {
        IntEv intEv = new IntEv(n, boolEv.get().border);
        for (int i = 1; i < n; i++) intEv.bits[i] = BoolEvRef.of(BoolEv.zeroes(boolEv.get().border));
        intEv.bits[n] = boolEv.copy();
        return intEv;
    }

    public static BasicInstruction of(BoolEvRef boolEv, IntEvRef res) {
        return new boolToIntEv(boolEv, res);
    }

    public BoolEvRef[] getBits() {
        return (BoolEvRef[]) bits;
    }

    /** Converts this IntEv to a HashMap<Ev, Integer>. */
    public HashMap<Ev, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntEv as a Java int");
        HashMap<Ev, Integer> res = new HashMap<>();
        for (Ev ev : m.evs) res.put(ev, 0);
        for (int i = 1; i <= n; i++) {
            BoolEvRef bit = (BoolEvRef) bits[i];
            HashMap<Ev, Boolean> bitMap = BoolEv.decode(m.evs, bit.get());
            final int pos = n - i;
            res.replaceAll((ev, val) -> bitMap.get(ev) ? val | (1 << pos) : val);
        }

        BoolEvRef bit = (BoolEvRef) bits[0];
        HashMap<Ev, Boolean> bitMap = BoolEv.decode(m.evs, bit.get());
        res.replaceAll((ev, val) -> bitMap.get(ev) ? val | (1 << 31) : val);

        return res;
    }

    protected static void lShift(IntEv a, IntEv res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolEvRef.of(BoolEv.zeroes(a.border));
    }

    protected static void rShift(IntEv a, IntEv res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolEvRef.of(BoolEv.zeroes(a.border));
    }

    public IntEv copy() {
        IntEv copy = new IntEv(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}

class LShiftEv implements BasicInstruction {
    private final IntEvRef a;
    private final IntEvRef res;
    private final int k;

    public LShiftEv(IntEvRef a, IntEvRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntEv.lShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class RShiftEv implements BasicInstruction {
    private final IntEvRef a;
    private final IntEvRef res;
    private final int k;

    public RShiftEv(IntEvRef a, IntEvRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntEv.rShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class IntNotEv extends Procedure {
    public IntNotEv(IntEvRef a, IntEvRef res) {
        BoolEvRef[] bitsA = new BoolEvRef[a.get().n + 1];
        BoolEvRef[] bitsRes = new BoolEvRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolEvRef();
            bitsRes[i] = new BoolEvRef();
        }

        split(a, bitsA);
        for (int i = 0; i <= a.get().n; i++) not(bitsA[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntAndEv extends Procedure {
    public IntAndEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        BoolEvRef[] bitsA = new BoolEvRef[a.get().n + 1];
        BoolEvRef[] bitsB = new BoolEvRef[a.get().n + 1];
        BoolEvRef[] bitsRes = new BoolEvRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolEvRef();
            bitsB[i] = new BoolEvRef();
            bitsRes[i] = new BoolEvRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntOrEv extends Procedure {
    public IntOrEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        BoolEvRef[] bitsA = new BoolEvRef[a.get().n + 1];
        BoolEvRef[] bitsB = new BoolEvRef[a.get().n + 1];
        BoolEvRef[] bitsRes = new BoolEvRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolEvRef();
            bitsB[i] = new BoolEvRef();
            bitsRes[i] = new BoolEvRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntXorEv extends Procedure {
    public IntXorEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        BoolEvRef[] bitsA = new BoolEvRef[a.get().n + 1];
        BoolEvRef[] bitsB = new BoolEvRef[a.get().n + 1];
        BoolEvRef[] bitsRes = new BoolEvRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolEvRef();
            bitsB[i] = new BoolEvRef();
            bitsRes[i] = new BoolEvRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class boolToIntEv implements BasicInstruction {
    private final BoolEvRef boolEv;
    private final IntEvRef res;

    public boolToIntEv(BoolEvRef boolEv, IntEvRef res) {
        this.boolEv = boolEv;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntEv.of(boolEv, res.get().n));
        return true;
    }
}

class IntAddEv extends Procedure {
    public IntAddEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        IntEvRef carry = IntEvRef.of(new IntEv(a.get().n));
        IntEvRef tmp = IntEvRef.of(new IntEv(a.get().n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class IntNegEv extends Procedure {
    public IntNegEv(IntEvRef a, IntEvRef res) {
        not(a, res);
        add(res, IntEvRef.of(IntEv.of(1, res.get().n)), res);
    }
}

class IntSubEv extends Procedure {
    public IntSubEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        IntEvRef negB = new IntEvRef();
        neg(b, negB);
        add(a, negB, res);
    }
}

class GTEv extends Procedure {
    public GTEv(IntEvRef a, IntEvRef b, BoolEvRef res) {
        IntEvRef diff = new IntEvRef();
        sub(a, b, diff);

        BoolEvRef[] bits = new BoolEvRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolEvRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class SplitEv implements BasicInstruction {
    private final IntEvRef a;
    private final BoolEvRef[] res;

    public SplitEv(IntEvRef a, BoolEvRef[] res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= a.get().n; i++) res[i].set(a.get().getBits()[i].copy().get());
        return true;
    }
}

class JoinEv implements BasicInstruction {
    private final BoolEvRef[] a;
    private final IntEvRef res;

    public JoinEv(BoolEvRef[] a, IntEvRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        IntEv tmp = new IntEv(res.get().n);
        for (int i = 0; i <= res.get().n; i++) tmp.bits[i] = a[i].copy();
        res.set(tmp);
        return true;
    }
}
