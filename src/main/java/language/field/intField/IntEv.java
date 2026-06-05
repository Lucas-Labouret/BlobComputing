package language.field.intField;

import language.field.boolField.BoolEv;
import language.fieldRef.boolField.BoolEvRef;
import language.fieldRef.boolField.BoolVRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusT.Ev;

import java.util.Arrays;
import java.util.HashMap;

/** IntEv represents an integer language.field on Ev loci. */
public class IntEv extends IntField<BoolEv> {
    public IntEv(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntEv(int n, Border border) {
        super(n, new BoolEvRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolEvRef(BoolEv.zeroes(border));
    }
    private IntEv(int n, BoolEvRef[] bits) {
        super(n, bits);
    }

    public static IntEv of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }
    public static IntEv of(int value, int n, Border border) {
        IntEv intEv = new IntEv(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intEv.bits[n - i] = (value & 1) == 0 ? new BoolEvRef(BoolEv.zeroes(border)) : new BoolEvRef(BoolEv.ones(border));
            value = value >> 1;
        }
        intEv.bits[0] = value >>> 31 == 0 ? new BoolEvRef(BoolEv.zeroes(border)) : new BoolEvRef(BoolEv.ones(border));
        return intEv;
    }
    public static IntEv of(BoolEvRef boolEv, int n) {
        IntEv intEv = new IntEv(n, boolEv.get().border);
        for (int i = 1; i < n; i++) intEv.bits[i] = new BoolEvRef(BoolEv.zeroes(boolEv.get().border));
        intEv.bits[n] = boolEv.copy();
        return intEv;
    }

    public static IntEv rand(int n) {
        IntEv rand = new IntEv(n);
        IntField.rand(rand, BoolEv::rand);
        return rand;
    }
    public static IntEv randNonNegative(int n) {
        IntEv rand = new IntEv(n);
        IntField.randNonNegative(rand, BoolEv::rand);
        return rand;
    }

    @Override
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
        res.replaceAll((ev, val) -> {
            if (bitMap.get(ev))
                for (int i = n; i < 32; i++) val |= (1 << i);
            return val;
        });

        return res;
    }

    public static void lShift(IntEv a, IntEv res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = new BoolEvRef(BoolEv.zeroes(a.border));
    }

    public static void rShift(IntEv a, IntEv res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = new BoolEvRef(BoolEv.zeroes(a.border));
    }

    @Override
    public IntEv copy() {
        IntEv copy = new IntEv(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }

    @Override
    public IntEv cache() {
        return new IntEv(n, (BoolEvRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
