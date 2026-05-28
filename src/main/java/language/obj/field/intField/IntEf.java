package language.obj.field.intField;

import language.utils.BoolFieldManager;
import language.utils.Border;
import language.obj.field.boolField.fieldT.BoolEf;
import language.instruction.Procedure;
import language.instruction.instructionSet.*;
import language.ref.field.boolField.fieldT.BoolEfRef;
import medium.Medium;
import medium.locusT.Ef;
import language.ref.field.intField.IntEfRef;

import java.util.HashMap;

/** IntEf represents an integer language.obj.field on Ef loci. */
public class IntEf extends IntField<BoolEf> {
    public IntEf(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntEf(int n, Border border) {
        super(n, new BoolEfRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = BoolEfRef.of(BoolEf.zeroes(border));
    }

    public static IntEf of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }

    public static IntEf of(int value, int n, Border border) {
        IntEf intEf = new IntEf(n, border);
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i + 1 == n) throw new IllegalArgumentException("Value " + value + " cannot be represented in " + n + " bits.");
            intEf.bits[n - i] = (value & 1) == 0 ? BoolEfRef.of(BoolEf.zeroes(border)) : BoolEfRef.of(BoolEf.ones(border));
            value = value >> 1;
        }
        intEf.bits[0] = value >>> 31 == 0 ? BoolEfRef.of(BoolEf.zeroes(border)) : BoolEfRef.of(BoolEf.ones(border));
        return intEf;
    }

    public static IntEf of(BoolEfRef boolEf, int n) {
        IntEf intEf = new IntEf(n, boolEf.get().border);
        for (int i = 1; i < n; i++) intEf.bits[i] = BoolEfRef.of(BoolEf.zeroes(boolEf.get().border));
        intEf.bits[n] = boolEf.copy();
        return intEf;
    }

    @Override
    public BoolEfRef[] getBits() {
        return (BoolEfRef[]) bits;
    }

    /** Converts this IntEf to a HashMap<Ef, Integer>. */
    public HashMap<Ef, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntEf as a Java int");
        HashMap<Ef, Integer> res = new HashMap<>();
        for (Ef ef : m.efs) res.put(ef, 0);
        for (int i = 1; i <= n; i++) {
            BoolEfRef bit = (BoolEfRef) bits[i];
            HashMap<Ef, Boolean> bitMap = BoolEf.decode(m.efs, bit.get());
            final int pos = n - i;
            res.replaceAll((ef, val) -> bitMap.get(ef) ? val | (1 << pos) : val);
        }

        BoolEfRef bit = (BoolEfRef) bits[0];
        HashMap<Ef, Boolean> bitMap = BoolEf.decode(m.efs, bit.get());
        res.replaceAll((ef, val) -> bitMap.get(ef) ? val | (1 << 31) : val);

        return res;
    }

    public static void lShift(IntEf a, IntEf res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        for (int i = 0; i <= n - k; i++) res.bits[i] = a.bits[i + k].copy();
        for (int i = n - k + 1; i <= n; i++) res.bits[i] = BoolEfRef.of(BoolEf.zeroes(a.border));
    }

    public static void rShift(IntEf a, IntEf res, int k, int n) {
        if (k > n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        for (int i = k; i <= n; i++) res.bits[i] = a.bits[i - k].copy();
        for (int i = 0; i < k; i++) res.bits[i] = BoolEfRef.of(BoolEf.zeroes(a.border));
    }

    public IntEf copy() {
        IntEf copy = new IntEf(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }
}
