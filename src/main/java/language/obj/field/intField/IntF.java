package language.obj.field.intField;

import language.instruction.Procedure;
import language.instruction.basicInstruction.BasicInstruction;
import language.obj.field.boolField.fieldS.BoolF;
import language.ref.field.boolField.fieldS.BoolFRef;
import language.ref.field.boolField.fieldT.BoolFeRef;
import language.ref.field.boolField.fieldT.BoolFvRef;
import language.ref.field.intField.IntFRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusS.Face;

import java.util.HashMap;

/** IntF represents an integer language.obj.field on faces. */
public class IntF extends IntField<BoolF> {
    public IntF(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntF(int n, Border border) {
        super(n, new BoolFRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolFRef.of(BoolF.zeroes(border));
    }

    public static IntF of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }

    public static IntF of(int value, int n, Border border) {
        IntF intF = new IntF(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intF.bits[n - i] = (value & 1) == 0 ? BoolFRef.of(BoolF.zeroes(border)) : BoolFRef.of(BoolF.ones(border));
            value = value >> 1;
        }
        intF.bits[0] = value >>> 31 == 0 ? BoolFRef.of(BoolF.zeroes(border)) : BoolFRef.of(BoolF.ones(border));
        return intF;
    }

    public static IntF of(BoolFRef boolF, int n) {
        IntF intF = new IntF(n, boolF.get().border);
        for (int i = 1; i < n; i++) intF.bits[i] = BoolFRef.of(BoolF.zeroes(boolF.get().border));
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
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolFRef.of(BoolF.zeroes(a.border));
    }

    protected static void rShift(IntF a, IntF res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolFRef.of(BoolF.zeroes(a.border));
    }

    public IntF copy() {
        IntF copy = new IntF(n, border);
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
    public IntNotF(IntFRef a, IntFRef res) {
        BoolFRef[] bitsA = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsRes = new BoolFRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFRef();
            bitsRes[i] = new BoolFRef();
        }

        split(a, bitsA);
        for (int i = 0; i <= a.get().n; i++) not(bitsA[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntAndF extends Procedure {
    public IntAndF(IntFRef a, IntFRef b, IntFRef res) {
        BoolFRef[] bitsA = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsB = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsRes = new BoolFRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFRef();
            bitsB[i] = new BoolFRef();
            bitsRes[i] = new BoolFRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntOrF extends Procedure {
    public IntOrF(IntFRef a, IntFRef b, IntFRef res) {
        BoolFRef[] bitsA = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsB = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsRes = new BoolFRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFRef();
            bitsB[i] = new BoolFRef();
            bitsRes[i] = new BoolFRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class IntXorF extends Procedure {
    public IntXorF(IntFRef a, IntFRef b, IntFRef res) {
        BoolFRef[] bitsA = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsB = new BoolFRef[a.get().n + 1];
        BoolFRef[] bitsRes = new BoolFRef[a.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            bitsA[i] = new BoolFRef();
            bitsB[i] = new BoolFRef();
            bitsRes[i] = new BoolFRef();
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
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
    public IntRedAddFv(BoolFvRef orig, IntFRef res) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFRef[] stack = new BoolFRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolFRef();
        redStack0(orig, stack);

        set(IntFRef.of(IntF.of(0, res.get().n)), res);
        IntFRef current = IntFRef.of(new IntF(res.get().n));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }
}

class IntRedAddFe extends Procedure {
    public IntRedAddFe(BoolFeRef orig, IntFRef res) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFRef[] stack = new BoolFRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolFRef();
        redStack0(orig, stack);

        set(IntFRef.of(IntF.of(0, res.get().n)), res);
        IntFRef current = IntFRef.of(new IntF(res.get().n));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }
}

class IntAddF extends Procedure {
    public IntAddF(IntFRef a, IntFRef b, IntFRef res) {
        IntFRef carry = new IntFRef();
        IntFRef tmp = new IntFRef();
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class IntNegF extends Procedure {
    public IntNegF(IntFRef a, IntFRef res) {
        not(a, res);
        add(res, IntFRef.of(IntF.of(1, res.get().n)), res);
    }
}

class IntSubF extends Procedure {
    public IntSubF(IntFRef a, IntFRef b, IntFRef res) {
        IntFRef negB = IntFRef.of(new IntF(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class GTF extends Procedure {
    public GTF(IntFRef a, IntFRef b, BoolFRef res) {
        IntFRef diff = new IntFRef();
        sub(a, b, diff);

        BoolFRef[] bits = new BoolFRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolFRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
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
        for (int i = 0; i <= a.get().n; i++) res[i].set(a.get().getBits()[i].copy().get());
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
        IntF tmp = new IntF(res.get().n);
        for (int i = 0; i <= res.get().n; i++) tmp.bits[i] = a[i].copy();
        res.set(tmp);
        return true;
    }
}
