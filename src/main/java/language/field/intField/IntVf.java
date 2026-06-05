package language.field.intField;

import language.field.boolField.BoolVf;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.boolField.BoolVfRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusT.Vf;

import java.util.Arrays;
import java.util.HashMap;

/** IntVf represents an integer language.field on Vf loci. */
public class IntVf extends IntField<BoolVf> {
    public IntVf(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntVf(int n, Border border) {
        super(n, new BoolVfRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolVfRef(BoolVf.zeroes(border));
    }
    private IntVf(int n, BoolVfRef[] bits) {
        super(n, bits);
    }

    public static IntVf of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }
    public static IntVf of(int value, int n, Border border) {
        IntVf intVf = new IntVf(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intVf.bits[n - i] = (value & 1) == 0 ? new BoolVfRef(BoolVf.zeroes(border)) : new BoolVfRef(BoolVf.ones(border));
            value = value >> 1;
        }
        intVf.bits[0] = value >>> 31 == 0 ? new BoolVfRef(BoolVf.zeroes(border)) : new BoolVfRef(BoolVf.ones(border));
        return intVf;
    }
    public static IntVf of(BoolVfRef boolVf, int n) {
        IntVf intVf = new IntVf(n, boolVf.get().border);
        for (int i = 1; i < n; i++) intVf.bits[i] = new BoolVfRef(BoolVf.zeroes(boolVf.get().border));
        intVf.bits[n] = boolVf.copy();
        return intVf;
    }

    public static IntVf rand(int n) {
        IntVf rand = new IntVf(n);
        IntField.rand(rand, BoolVf::rand);
        return rand;
    }
    public static IntVf randNonNegative(int n) {
        IntVf rand = new IntVf(n);
        IntField.randNonNegative(rand, BoolVf::rand);
        return rand;
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
        res.replaceAll((vf, val) -> {
            if (bitMap.get(vf))
                for (int i = n; i < 32; i++) val |= (1 << i);
            return val;
        });

        return res;
    }

    public static void lShift(IntVf a, IntVf res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = new BoolVfRef(BoolVf.zeroes(a.border));
    }

    public static void rShift(IntVf a, IntVf res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = new BoolVfRef(BoolVf.zeroes(a.border));
    }

    @Override
    public IntVf copy() {
        IntVf copy = new IntVf(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
    
    @Override
    public IntVf cache() {
        return new IntVf(n, (BoolVfRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
