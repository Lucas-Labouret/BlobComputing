package prog.obj.intField;

import field.FieldManager;
import field.boolField.fieldS.BoolF;
import language.Procedure;
import language.basicInstruction.*;
import language.basicInstruction.bitOp.BitOp;
import language.basicInstruction.commOp.CommOp;
import language.fieldRef.BoolFRef;
import language.fieldRef.BoolFeRef;
import language.fieldRef.BoolFvRef;
import medium.Medium;
import medium.locusS.Face;
import prog.ref.intField.*;

import java.util.HashMap;

/** IntF represents an integer field on faces. */
public class IntF extends IntField<BoolF> {
    public IntF(int n) {
        super(n, new BoolFRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolFRef.zeroes();
    }

    public static IntF of(int value, int n) {
        IntF intF = new IntF(n);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intF.bits[n - i] = (value & 1) == 0 ? BoolFRef.zeroes() : BoolFRef.ones();
            value = value >> 1;
        }
        intF.bits[0] = value >>> 31 == 0 ? BoolFRef.zeroes() : BoolFRef.ones();
        return intF;
    }

    public static IntF of(BoolFRef boolF, int n) {
        IntF intF = new IntF(n);
        for (int i = 1; i < n; i++) intF.bits[i] = BoolFRef.zeroes();
        intF.bits[n] = boolF.copy();
        return intF;
    }

    public static BasicInstruction of(BoolFRef boolF, IntFRef res) {
        return new boolToIntF(boolF, res);
    }

    public BoolFRef[] getBits() {
        return (BoolFRef[]) bits;
    }

    /** Converts this IntF to a HashMap<Face, Integer>. */
    public HashMap<Face, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntF as a Java int");
        HashMap<Face, Integer> res = new HashMap<>();
        for (Face f : m.faces) res.put(f, 0);
        for (int i = 1; i <= n; i++) {
            BoolFRef bit = (BoolFRef) bits[i];
            HashMap<Face, Boolean> bitMap = BoolF.decode(m.faces, bit.get());
            final int pos = n - i;
            res.replaceAll((f, val) -> bitMap.get(f) ? val | (1 << pos) : val);
        }

        BoolFRef bit = (BoolFRef) bits[0];
        HashMap<Face, Boolean> bitMap = BoolF.decode(m.faces, bit.get());
        res.replaceAll((f, val) -> bitMap.get(f) ? val | (1 << 31) : val);

        return res;
    }

    protected static void lShift(IntF a, IntF res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolFRef.zeroes();
    }

    protected static void rShift(IntF a, IntF res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolFRef.zeroes();
    }

    public IntF copy() {
        IntF copy = new IntF(n);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}

class LShiftF implements BasicInstruction {
    private final IntFRef a;
    private final IntFRef res;
    private final int k;

    public LShiftF(IntFRef a, IntFRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntF.lShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class RShiftF implements BasicInstruction {
    private final IntFRef a;
    private final IntFRef res;
    private final int k;

    public RShiftF(IntFRef a, IntFRef res, int k) {
        this.a = a;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntF.rShift(a.get(), res.get(), k, a.get().n);
        return true;
    }
}

class IntNotF extends Procedure {
    private final IntFRef a;
    private final IntFRef res;

    public IntNotF(IntFRef a, IntFRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolFRef[] bitsA = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsRes = new BoolFRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFRef();
            bitsRes[i] = new BoolFRef();
        }

        add(IntField.split(a, bitsA));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.not(bitsA[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntAndF extends Procedure {
    private final IntFRef a;
    private final IntFRef b;
    private final IntFRef res;

    public IntAndF(IntFRef a, IntFRef b, IntFRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolFRef[] bitsA = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsB = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsRes = new BoolFRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFRef();
            bitsB[i] = new BoolFRef();
            bitsRes[i] = new BoolFRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.and(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntOrF extends Procedure {
    private final IntFRef a;
    private final IntFRef b;
    private final IntFRef res;

    public IntOrF(IntFRef a, IntFRef b, IntFRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolFRef[] bitsA = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsB = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsRes = new BoolFRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFRef();
            bitsB[i] = new BoolFRef();
            bitsRes[i] = new BoolFRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.or(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class IntXorF extends Procedure {
    private final IntFRef a;
    private final IntFRef b;
    private final IntFRef res;

    public IntXorF(IntFRef a, IntFRef b, IntFRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    public void setInstructions() {
        BoolFRef[] bitsA = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsB = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsRes = new BoolFRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFRef();
            bitsB[i] = new BoolFRef();
            bitsRes[i] = new BoolFRef();
        }

        add(IntField.split(a, bitsA));
        add(IntField.split(b, bitsB));

        for (int i = 0; i <= a.get().n; i++)
            add(BitOp.xor(bitsA[i], bitsB[i], bitsRes[i]));

        add(IntField.join(bitsRes, res));
    }
}

class boolToIntF implements BasicInstruction {
    private final BoolFRef boolF;
    private final IntFRef res;

    public boolToIntF(BoolFRef boolF, IntFRef res) {
        this.boolF = boolF;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntF.of(boolF, res.get().n));
        return true;
    }
}

class IntRedAddFv extends Procedure {
    private final BoolFvRef orig;
    private final IntFRef res;
    private final int breadth;

    public IntRedAddFv(BoolFvRef orig, IntFRef res) {
        this.orig = orig;
        this.res = res;
        this.breadth = FieldManager.getBreadthF();
    }

    @Override
    protected void setInstructions() {
        BoolFRef[] stack = new BoolFRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolFRef();
        add(CommOp.redStack0(orig, stack));

        add(new SetRef<>(IntFRef.of(IntF.of(0, res.get().n)), res));
        IntFRef current = IntFRef.of(new IntF(res.get().n));
        for (int i = 0; i < breadth; i++) {
            add(IntField.fromBool(stack[i], current));
            add(IntField.add(res, current, res));
        }
    }
}

class IntRedAddFe extends Procedure {
    private final BoolFeRef orig;
    private final IntFRef res;
    private final int breadth;

    public IntRedAddFe(BoolFeRef orig, IntFRef res) {
        this.orig = orig;
        this.res = res;
        this.breadth = FieldManager.getBreadthV();
    }

    @Override
    protected void setInstructions() {
        BoolFRef[] stack = new BoolFRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolFRef();
        add(CommOp.redStack0(orig, stack));

        add(new SetRef<>(IntFRef.of(IntF.of(0, res.get().n)), res));
        IntFRef current = IntFRef.of(new IntF(res.get().n));
        for (int i = 0; i < breadth; i++) {
            add(IntField.fromBool(stack[i], current));
            add(IntField.add(res, current, res));
        }
    }
}

class IntAddF extends Procedure {
    private final IntFRef a;
    private final IntFRef b;
    private final IntFRef res;
    private final int n;

    private final IntFRef carry = new IntFRef();
    private final IntFRef tmp = new IntFRef();

    public IntAddF(IntFRef a, IntFRef b, IntFRef res) {
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

class IntNegF extends Procedure {
    private final IntFRef a;
    private final IntFRef res;

    public IntNegF(IntFRef a, IntFRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        add(IntField.not(a, res));
        add(IntField.add(res, IntFRef.of(IntF.of(1, res.get().n)), res));
    }
}

class IntSubF extends Procedure {
    private final IntFRef a;
    private final IntFRef b;
    private final IntFRef res;

    private final IntFRef negB = new IntFRef();

    public IntSubF(IntFRef a, IntFRef b, IntFRef res) {
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

class GTF extends Procedure {
    private final IntFRef a;
    private final IntFRef b;
    private final BoolFRef res;

    public GTF(IntFRef a, IntFRef b, BoolFRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    @Override
    protected void setInstructions() {
        IntFRef diff = new IntFRef();
        add(IntField.sub(a, b, diff));

        BoolFRef[] bits = new BoolFRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolFRef();
        add(IntField.split(diff, bits));

        add(new SetRef<>(bits[0], res));
        add(BitOp.not(res, res));
    }
}

class SplitF implements BasicInstruction {
    private final IntFRef a;
    private final BoolFRef[] res;

    public SplitF(IntFRef a, BoolFRef[] res) {
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

class JoinF implements BasicInstruction {
    private final BoolFRef[] a;
    private final IntFRef res;

    public JoinF(BoolFRef[] a, IntFRef res) {
        this.a = a;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i < a.length; i++) res.get().bits[i] = a[i].copy();
        return true;
    }
}
