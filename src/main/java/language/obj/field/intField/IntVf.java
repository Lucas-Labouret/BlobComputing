package language.obj.field.intField;

import language.obj.field.boolField.BoolVf;
import language.ref.field.boolField.BoolVfRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusT.Vf;

import java.util.HashMap;

/** IntVf represents an integer language.obj.field on Vf loci. */
public class IntVf extends IntField<BoolVf> {
    public IntVf(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntVf(int n, Border border) {
        super(n, new BoolVfRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolVfRef.of(BoolVf.zeroes(border));
    }

    public static IntVf of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }

    public static IntVf of(int value, int n, Border border) {
        IntVf intVf = new IntVf(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intVf.bits[n - i] = (value & 1) == 0 ? BoolVfRef.of(BoolVf.zeroes(border)) : BoolVfRef.of(BoolVf.ones(border));
            value = value >> 1;
        }
        intVf.bits[0] = value >>> 31 == 0 ? BoolVfRef.of(BoolVf.zeroes(border)) : BoolVfRef.of(BoolVf.ones(border));
        return intVf;
    }

    public static IntVf of(BoolVfRef boolVf, int n) {
        IntVf intVf = new IntVf(n, boolVf.get().border);
        for (int i = 1; i < n; i++) intVf.bits[i] = BoolVfRef.of(BoolVf.zeroes(boolVf.get().border));
        intVf.bits[n] = boolVf.copy();
        return intVf;
    }

    @Override
    public BoolVfRef[] getBits() {
        return (BoolVfRef[]) bits;
    }

    /** Converts this IntVf to a HashMap<Vf, Integer>. */
    public HashMap<Vf, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntVf as a Java int");
        HashMap<Vf, Integer> res = new HashMap<>();
        for (Vf vf : m.vfs) res.put(vf, 0);
        for (int i = 1; i <= n; i++) {
            BoolVfRef bit = (BoolVfRef) bits[i];
            HashMap<Vf, Boolean> bitMap = BoolVf.decode(m.vfs, bit.get());
            final int pos = n - i;
            res.replaceAll((vf, val) -> bitMap.get(vf) ? val | (1 << pos) : val);
        }

        BoolVfRef bit = (BoolVfRef) bits[0];
        HashMap<Vf, Boolean> bitMap = BoolVf.decode(m.vfs, bit.get());
        res.replaceAll((vf, val) -> bitMap.get(vf) ? val | (1 << 31) : val);

        return res;
    }

    public static void lShift(IntVf a, IntVf res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolVfRef.of(BoolVf.zeroes(a.border));
    }

    public static void rShift(IntVf a, IntVf res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolVfRef.of(BoolVf.zeroes(a.border));
    }

    public IntVf copy() {
        IntVf copy = new IntVf(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}
