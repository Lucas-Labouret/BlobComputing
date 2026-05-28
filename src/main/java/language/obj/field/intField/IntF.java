package language.obj.field.intField;

import language.obj.field.boolField.BoolF;
import language.ref.field.boolField.BoolFRef;
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
        res.replaceAll((f, val) -> bitMap.get(f) ? val | (1 << 31) : val);

        return res;
    }

    public static void lShift(IntF a, IntF res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolFRef.of(BoolF.zeroes(a.border));
    }

    public static void rShift(IntF a, IntF res, int k, int n) {
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
