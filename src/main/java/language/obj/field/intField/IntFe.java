package language.obj.field.intField;

import language.obj.field.boolField.BoolFe;
import language.obj.field.boolField.BoolV;
import language.ref.field.boolField.BoolFeRef;
import language.ref.field.boolField.BoolVRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusS.Vertex;
import medium.locusT.Fe;

import java.util.HashMap;

/** IntFe represents an integer language.obj.field on Fe loci. */
public class IntFe extends IntField<BoolFe> {
    public IntFe(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntFe(int n, Border border) {
        super(n, new BoolFeRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolFeRef.of(BoolFe.zeroes(border));
    }

    public static IntFe of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }
    public static IntFe of(int value, int n, Border border) {
        IntFe intFe = new IntFe(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intFe.bits[n - i] = (value & 1) == 0 ? BoolFeRef.of(BoolFe.zeroes(border)) : BoolFeRef.of(BoolFe.ones(border));
            value = value >> 1;
        }
        intFe.bits[0] = value >>> 31 == 0 ? BoolFeRef.of(BoolFe.zeroes(border)) : BoolFeRef.of(BoolFe.ones(border));
        return intFe;
    }
    public static IntFe of(BoolFeRef boolFe, int n) {
        IntFe intFe = new IntFe(n, boolFe.get().border);
        for (int i = 1; i < n; i++) intFe.bits[i] = BoolFeRef.of(BoolFe.zeroes(boolFe.get().border));
        intFe.bits[n] = boolFe.copy();
        return intFe;
    }

    public static IntFe rand(int n) {
        IntFe rand = new IntFe(n);
        IntField.rand(rand, BoolFe::rand);
        return rand;
    }
    public static IntFe randNonNegative(int n) {
        IntFe rand = new IntFe(n);
        IntField.randNonNegative(rand, BoolFe::rand);
        return rand;
    }

    @Override
    public BoolFeRef[] getBits() {
        return (BoolFeRef[]) bits;
    }

    /** Converts this IntFe to a HashMap<Fe, Integer>. */
    public HashMap<Fe, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntFe as a Java int");
        HashMap<Fe, Integer> res = new HashMap<>();
        for (Fe fe : m.fes) res.put(fe, 0);
        for (int i = 1; i <= n; i++) {
            BoolFeRef bit = (BoolFeRef) bits[i];
            HashMap<Fe, Boolean> bitMap = BoolFe.decode(m.fes, bit.get());
            final int pos = n - i;
            res.replaceAll((fe, val) -> bitMap.get(fe) ? val | (1 << pos) : val);
        }

        BoolFeRef bit = (BoolFeRef) bits[0];
        HashMap<Fe, Boolean> bitMap = BoolFe.decode(m.fes, bit.get());
        res.replaceAll((fe, val) -> {
            if (bitMap.get(fe))
                for (int i = n; i < 32; i++) val |= (1 << i);
            return val;
        });

        return res;
    }

    public static void lShift(IntFe a, IntFe res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolFeRef.of(BoolFe.zeroes(a.border));
    }

    public static void rShift(IntFe a, IntFe res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolFeRef.of(BoolFe.zeroes(a.border));
    }

    public IntFe copy() {
        IntFe copy = new IntFe(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}
