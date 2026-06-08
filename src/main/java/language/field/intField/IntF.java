package language.field.intField;

import language.field.boolField.BoolF;
import language.fieldRef.boolField.BoolFRef;
import language.fieldRef.boolField.BoolVRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusS.Face;

import java.util.Arrays;
import java.util.HashMap;

/** IntF represents an integer language.field on faces. */
public non-sealed class IntF extends IntField<BoolF> {
    public IntF(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntF(int n, Border border) {
        super(n, new BoolFRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolFRef(BoolF.zeroes(border));
    }
    private IntF(int n, BoolFRef[] bits) {
        super(n, bits);
    }

    public static IntF of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }
    public static IntF of(int value, int n, Border border) {
        IntF intF = new IntF(n, border);
        final int oVal = value;
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i+1 > n) throw new IllegalArgumentException("Value "+ oVal +" cannot be represented in "+ n +" bits.");
            intF.bits[n - i] = (value & 1) == 0 ? new BoolFRef(BoolF.zeroes(border)) : new BoolFRef(BoolF.ones(border));
            value = value >> 1;
        }
        intF.bits[0] = value >>> 31 == 0 ? new BoolFRef(BoolF.zeroes(border)) : new BoolFRef(BoolF.ones(border));
        return intF;
    }
    public static IntF of(BoolFRef boolF, int n) {
        IntF intF = new IntF(n, boolF.get().border);
        for (int i = 1; i < n; i++) intF.bits[i] = new BoolFRef(BoolF.zeroes(boolF.get().border));
        intF.bits[n] = boolF.copy();
        return intF;
    }

    public static IntF rand(int n) {
        IntF rand = new IntF(n);
        IntField.rand(rand, BoolF::rand);
        return rand;
    }
    public static IntF randNonNegative(int n) {
        IntF rand = new IntF(n);
        IntField.randNonNegative(rand, BoolF::rand);
        return rand;
    }

    @Override
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
        res.replaceAll((f, val) -> {
            if (bitMap.get(f))
                for (int i = n; i < 32; i++) val |= (1 << i);
            return val;
        });

        return res;
    }

    @Override
    public IntF copy() {
        IntF copy = new IntF(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }

    @Override
    public IntF cache() {
        return new IntF(n, (BoolFRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
