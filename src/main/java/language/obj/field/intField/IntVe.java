package language.obj.field.intField;

import language.obj.field.boolField.BoolVe;
import language.ref.field.boolField.BoolVeRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusT.Ve;

import java.util.HashMap;

/** IntVe represents an integer language.obj.field on Ve loci. */
public class IntVe extends IntField<BoolVe> {
    public IntVe(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntVe(int n, Border border) {
        super(n, new BoolVeRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolVeRef.of(BoolVe.zeroes(border));
    }

    public static IntVe of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }

    public static IntVe of(int value, int n, Border border) {
        IntVe intVe = new IntVe(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intVe.bits[n - i] = (value & 1) == 0 ? BoolVeRef.of(BoolVe.zeroes(border)) : BoolVeRef.of(BoolVe.ones(border));
            value = value >> 1;
        }
        intVe.bits[0] = value >>> 31 == 0 ? BoolVeRef.of(BoolVe.zeroes(border)) : BoolVeRef.of(BoolVe.ones(border));
        return intVe;
    }

    public static IntVe of(BoolVeRef boolVe, int n) {
        IntVe intVe = new IntVe(n, boolVe.get().border);
        for (int i = 1; i < n; i++) intVe.bits[i] = BoolVeRef.of(BoolVe.zeroes(boolVe.get().border));
        intVe.bits[n] = boolVe.copy();
        return intVe;
    }

    @Override
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

    public static void lShift(IntVe a, IntVe res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolVeRef.of(BoolVe.zeroes(a.border));
    }

    public static void rShift(IntVe a, IntVe res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolVeRef.of(BoolVe.zeroes(a.border));
    }

    public IntVe copy() {
        IntVe copy = new IntVe(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}
