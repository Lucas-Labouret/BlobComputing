package prog.obj.intField;

import field.boolField.fieldT.BoolFv;
import language.Procedure;
import language.basicInstruction.*;
import language.basicInstruction.bitOp.BitOp;
import language.fieldRef.BoolFvRef;
import medium.Medium;
import medium.locusT.Fv;
import prog.ref.intField.IntFvRef;
import prog.ref.intField.IntFvRef;

import java.util.HashMap;

/** IntFv represents an integer field on Fv loci. */
public class IntFv extends IntField<BoolFv> {
    public IntFv(int n) {
        super(n, new BoolFvRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolFvRef.zeroes();
    }

    public static IntFv of(int value, int n) {
        IntFv intFv = new IntFv(n);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intFv.bits[n - i] = (value & 1) == 0 ? BoolFvRef.zeroes() : BoolFvRef.ones();
            value = value >> 1;
        }
        intFv.bits[0] = value >>> 31 == 0 ? BoolFvRef.zeroes() : BoolFvRef.ones();
        return intFv;
    }

    public static IntFv of(BoolFvRef boolFv, int n) {
        IntFv intFv = new IntFv(n);
        for (int i = 1; i < n; i++) intFv.bits[i] = BoolFvRef.zeroes();
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
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolFvRef.zeroes();
    }

    protected static void rShift(IntFv a, IntFv res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolFvRef.zeroes();
    }

    public IntFv copy() {
        IntFv copy = new IntFv(n);
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
    private final IntFvRef a;
    private final IntFvRef res;

    public IntNotFv(IntFvRef a, IntFvRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.not(a.get().getBits()[i], res.get().getBits()[i]));
    }
}

class IntAndFv extends Procedure {
    private final IntFvRef a;
    private final IntFvRef b;
    private final IntFvRef res;

    public IntAndFv(IntFvRef a, IntFvRef b, IntFvRef res) {
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

class IntOrFv extends Procedure {
    private final IntFvRef a;
    private final IntFvRef b;
    private final IntFvRef res;

    public IntOrFv(IntFvRef a, IntFvRef b, IntFvRef res) {
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

class IntXorFv extends Procedure {
    private final IntFvRef a;
    private final IntFvRef b;
    private final IntFvRef res;

    public IntXorFv(IntFvRef a, IntFvRef b, IntFvRef res) {
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
    private final IntFvRef a;
    private final IntFvRef b;
    private final IntFvRef res;
    private final int n;

    private final IntFvRef carry = new IntFvRef();
    private final IntFvRef tmp = new IntFvRef();

    public IntAddFv(IntFvRef a, IntFvRef b, IntFvRef res) {
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

class IntNegFv extends Procedure {
    private final IntFvRef a;
    private final IntFvRef res;

    public IntNegFv(IntFvRef a, IntFvRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        add(IntField.not(a, res));
        add(IntField.add(res, IntFvRef.of(IntFv.of(1, res.get().n)), res));
    }
}

class IntSubFv extends Procedure {
    private final IntFvRef a;
    private final IntFvRef b;
    private final IntFvRef res;

    private final IntFvRef negB = new IntFvRef();

    public IntSubFv(IntFvRef a, IntFvRef b, IntFvRef res) {
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

class GTFv extends Procedure {
    private final IntFvRef a;
    private final IntFvRef b;
    private final BoolFvRef res;

    public GTFv(IntFvRef a, IntFvRef b, BoolFvRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        IntFvRef diff = new IntFvRef();
        add(IntField.sub(a, b, diff));

        BoolFvRef sign = new BoolFvRef();
        add(new SetRef<>(diff.get().getBits()[0], sign));

        BoolFvRef anyLower = new BoolFvRef();
        add(new SetRef<>(diff.get().getBits()[1], anyLower));
        for (int i = 2; i <= a.get().n; i++) add(BitOp.or(anyLower, diff.get().getBits()[i], anyLower));

        BoolFvRef notSign = new BoolFvRef();
        add(BitOp.not(sign, notSign));
        add(BitOp.and(notSign, anyLower, res));
    }
}

