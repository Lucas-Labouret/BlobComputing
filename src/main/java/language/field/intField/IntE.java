package language.field.intField;

import language.field.boolField.BoolE;
import language.fieldRef.boolField.BoolERef;
import language.fieldRef.boolField.BoolVRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusS.Edge;

import java.util.Arrays;
import java.util.HashMap;

/** IntE represents an integer language.field on edges. */
public class IntE extends IntField<BoolE> {
    public IntE(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntE(int n, Border border) {
        super(n, new BoolERef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolERef(BoolE.zeroes(border));
    }
    private IntE(int n, BoolERef[] bits) {
        super(n, bits);
    }

    public static IntE of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }
    public static IntE of(int value, int n, Border border) {
        IntE intE = new IntE(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intE.bits[n - i] = (value & 1) == 0 ? new BoolERef(BoolE.zeroes(border)) : new BoolERef(BoolE.ones(border));
            value = value >> 1;
        }
        intE.bits[0] = value >>> 31 == 0 ? new BoolERef(BoolE.zeroes(border)) : new BoolERef(BoolE.ones(border));
        return intE;
    }
    public static IntE of(BoolERef boolE, int n) {
        IntE intE = new IntE(n, boolE.get().border);
        for (int i = 1; i < n; i++) intE.bits[i] = new BoolERef(BoolE.zeroes(boolE.get().border));
        intE.bits[n] = boolE.copy();
        return intE;
    }

    public static IntE rand(int n) {
        IntE rand = new IntE(n);
        IntField.rand(rand, BoolE::rand);
        return rand;
    }
    public static IntE randNonNegative(int n) {
        IntE rand = new IntE(n);
        IntField.randNonNegative(rand, BoolE::rand);
        return rand;
    }

    @Override
    public BoolERef[] getBits() {
        return (BoolERef[]) bits;
    }

    /** Converts this IntE to a HashMap<Edge, Integer>. */
    public HashMap<Edge, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntE as a Java int");
        HashMap<Edge, Integer> res = new HashMap<>();
        for (Edge e : m.edges) res.put(e, 0);
        for (int i = 1; i <= n; i++) {
            BoolERef bit = (BoolERef) bits[i];
            HashMap<Edge, Boolean> bitMap = BoolE.decode(m.edges, bit.get());
            final int pos = n - i;
            res.replaceAll((e, val) -> bitMap.get(e) ? val | (1 << pos) : val);
        }

        BoolERef bit = (BoolERef) bits[0];
        HashMap<Edge, Boolean> bitMap = BoolE.decode(m.edges, bit.get());
        res.replaceAll((e, val) -> {
            if (bitMap.get(e))
                for (int i = n; i < 32; i++) val |= (1 << i);
            return val;
        });

        return res;
    }

    public static void lShift(IntE a, IntE res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = new BoolERef(BoolE.zeroes(a.border));
    }

    public static void rShift(IntE a, IntE res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = new BoolERef(BoolE.zeroes(a.border));
    }

    @Override
    public IntE copy() {
        IntE copy = new IntE(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }

    @Override
    public IntE cache() {
        return new IntE(n, (BoolERef[]) Arrays.copyOf(this.bits, n+1));
    }
}
