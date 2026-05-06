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
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.not(a.get().getBits()[i], res.get().getBits()[i]));
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
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.and(a.get().getBits()[i], b.get().getBits()[i], res.get().getBits()[i]));
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
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.or(a.get().getBits()[i], b.get().getBits()[i], res.get().getBits()[i]));
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
        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.xor(a.get().getBits()[i], b.get().getBits()[i], res.get().getBits()[i]));
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

        BoolVeRef sign = new BoolVeRef();
        add(new SetRef<>(diff.get().getBits()[0], sign));

        BoolVeRef anyLower = new BoolVeRef();
        add(new SetRef<>(diff.get().getBits()[1], anyLower));
        for (int i = 2; i <= a.get().n; i++) add(BitOp.or(anyLower, diff.get().getBits()[i], anyLower));

        BoolVeRef notSign = new BoolVeRef();
        add(BitOp.not(sign, notSign));
        add(BitOp.and(notSign, anyLower, res));
    }
}

