package prog.obj.intField;

import field.boolField.fieldT.BoolEv;
import language.Procedure;
import language.basicInstruction.*;
import language.basicInstruction.bitOp.BitOp;
import language.fieldRef.BoolEvRef;
import medium.Medium;
import medium.locusT.Ev;
import prog.ref.intField.IntEvRef;
import prog.ref.intField.IntEvRef;

import java.util.HashMap;

/** IntEv represents an integer field on Ev loci. */
public class IntEv extends IntField<BoolEv> {
    public IntEv(int n) {
        super(n, new BoolEvRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolEvRef.zeroes();
    }

    public static IntEv of(int value, int n) {
        IntEv intEv = new IntEv(n);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intEv.bits[n - i] = (value & 1) == 0 ? BoolEvRef.zeroes() : BoolEvRef.ones();
            value = value >> 1;
        }
        intEv.bits[0] = value >>> 31 == 0 ? BoolEvRef.zeroes() : BoolEvRef.ones();
        return intEv;
    }

    public static IntEv of(BoolEvRef boolEv, int n) {
        IntEv intEv = new IntEv(n);
        for (int i = 1; i < n; i++) intEv.bits[i] = BoolEvRef.zeroes();
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
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolEvRef.zeroes();
    }

    protected static void rShift(IntEv a, IntEv res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolEvRef.zeroes();
    }

    public IntEv copy() {
        IntEv copy = new IntEv(n);
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
    private final IntEvRef a;
    private final IntEvRef res;

    public IntNotEv(IntEvRef a, IntEvRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.not(a.get().getBits()[i], res.get().getBits()[i]));
    }
}

class IntAndEv extends Procedure {
    private final IntEvRef a;
    private final IntEvRef b;
    private final IntEvRef res;

    public IntAndEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.and(a.get().getBits()[i], b.get().getBits()[i], res.get().getBits()[i]));
    }
}

class IntOrEv extends Procedure {
    private final IntEvRef a;
    private final IntEvRef b;
    private final IntEvRef res;

    public IntOrEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.or(a.get().getBits()[i], b.get().getBits()[i], res.get().getBits()[i]));
    }
}

class IntXorEv extends Procedure {
    private final IntEvRef a;
    private final IntEvRef b;
    private final IntEvRef res;

    public IntXorEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.xor(a.get().getBits()[i], b.get().getBits()[i], res.get().getBits()[i]));
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
    private final IntEvRef a;
    private final IntEvRef b;
    private final IntEvRef res;
    private final int n;
    private final IntEvRef carry = new IntEvRef();
    private final IntEvRef tmp = new IntEvRef();

    public IntAddEv(IntEvRef a, IntEvRef b, IntEvRef res) {
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

class IntNegEv extends Procedure {
    private final IntEvRef a;
    private final IntEvRef res;

    public IntNegEv(IntEvRef a, IntEvRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        add(IntField.not(a, res));
        add(IntField.add(res, IntEvRef.of(IntEv.of(1, res.get().n)), res));
    }
}

class IntSubEv extends Procedure {
    private final IntEvRef a;
    private final IntEvRef b;
    private final IntEvRef res;

    private final IntEvRef negB = new IntEvRef();

    public IntSubEv(IntEvRef a, IntEvRef b, IntEvRef res) {
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

class GTEv extends Procedure {
    private final IntEvRef a;
    private final IntEvRef b;
    private final BoolEvRef res;

    public GTEv(IntEvRef a, IntEvRef b, BoolEvRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        IntEvRef diff = new IntEvRef();
        add(IntField.sub(a, b, diff));

        BoolEvRef sign = new BoolEvRef();
        add(new SetRef<>(diff.get().getBits()[0], sign));

        BoolEvRef anyLower = new BoolEvRef();
        add(new SetRef<>(diff.get().getBits()[1], anyLower));
        for (int i = 2; i <= a.get().n; i++) add(BitOp.or(anyLower, diff.get().getBits()[i], anyLower));

        BoolEvRef notSign = new BoolEvRef();
        add(BitOp.not(sign, notSign));
        add(BitOp.and(notSign, anyLower, res));
    }
}

