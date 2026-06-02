package language.obj.field.intField;

import language.obj.field.boolField.BoolFv;
import language.obj.field.boolField.BoolV;
import language.ref.field.boolField.BoolFvRef;
import language.ref.field.boolField.BoolVRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusS.Vertex;
import medium.locusT.Fv;

import java.util.HashMap;

/** IntFv represents an integer language.obj.field on Fv loci. */
public class IntFv extends IntField<BoolFv> {
    public IntFv(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntFv(int n, Border border) {
        super(n, new BoolFvRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolFvRef.of(BoolFv.zeroes(border));
    }

    public static IntFv of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }
    public static IntFv of(int value, int n, Border border) {
        IntFv intFv = new IntFv(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intFv.bits[n - i] = (value & 1) == 0 ? BoolFvRef.of(BoolFv.zeroes(border)) : BoolFvRef.of(BoolFv.ones(border));
            value = value >> 1;
        }
        intFv.bits[0] = value >>> 31 == 0 ? BoolFvRef.of(BoolFv.zeroes(border)) : BoolFvRef.of(BoolFv.ones(border));
        return intFv;
    }
    public static IntFv of(BoolFvRef boolFv, int n) {
        IntFv intFv = new IntFv(n, boolFv.get().border);
        for (int i = 1; i < n; i++) intFv.bits[i] = BoolFvRef.of(BoolFv.zeroes(boolFv.get().border));
        intFv.bits[n] = boolFv.copy();
        return intFv;
    }

    public static IntFv rand(int n) {
        IntFv rand = new IntFv(n);
        IntField.rand(rand, BoolFv::rand);
        return rand;
    }
    public static IntFv randNonNegative(int n) {
        IntFv rand = new IntFv(n);
        IntField.randNonNegative(rand, BoolFv::rand);
        return rand;
    }

    @Override
    public BoolFvRef[] getBits() {
        return (BoolFvRef[]) bits;
    }

    /** Converts this IntFv to a HashMap<Fv, Integer>. */
    public HashMap<Fv, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntFv as a Java int");
        HashMap<Fv, Integer> res = new HashMap<>();
        for (Fv fv : m.fvs) res.put(fv, 0);
        for (int i = 1; i <= n; i++) {
            BoolFvRef bit = (BoolFvRef) bits[i];
            HashMap<Fv, Boolean> bitMap = BoolFv.decode(m.fvs, bit.get());
            final int pos = n - i;
            res.replaceAll((fv, val) -> bitMap.get(fv) ? val | (1 << pos) : val);
        }

        BoolFvRef bit = (BoolFvRef) bits[0];
        HashMap<Fv, Boolean> bitMap = BoolFv.decode(m.fvs, bit.get());
        res.replaceAll((fv, val) -> {
            if (bitMap.get(fv))
                for (int i = n; i < 32; i++) val |= (1 << i);
            return val;
        });

        return res;
    }

    public static void lShift(IntFv a, IntFv res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolFvRef.of(BoolFv.zeroes(a.border));
    }

    public static void rShift(IntFv a, IntFv res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolFvRef.of(BoolFv.zeroes(a.border));
    }

    public IntFv copy() {
        IntFv copy = new IntFv(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}
