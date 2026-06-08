package language.field.intField;

import language.field.boolField.BoolEf;
import language.fieldRef.boolField.BoolEfRef;
import language.fieldRef.boolField.BoolVRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.Medium;
import medium.locusT.Ef;

import java.util.Arrays;
import java.util.HashMap;

/** IntEf represents an integer language.field on Ef loci. */
public non-sealed class IntEf extends IntField<BoolEf> {
    public IntEf(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntEf(int n, Border border) {
        super(n, new BoolEfRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolEfRef(BoolEf.zeroes(border));
    }
    private IntEf(int n, BoolEfRef[] bits) {
        super(n, bits);
    }

    public static IntEf of(int value, int n) {
        return of(value, n, BoolFieldManager.DEFAULT_BORDER());
    }
    public static IntEf of(int value, int n, Border border) {
        IntEf intEf = new IntEf(n, border);
        final int oVal = value;
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i+1 > n) throw new IllegalArgumentException("Value "+ oVal +" cannot be represented in "+ n +" bits.");
            intEf.bits[n - i] = (value & 1) == 0 ? new BoolEfRef(BoolEf.zeroes(border)) : new BoolEfRef(BoolEf.ones(border));
            value = value >> 1;
        }
        intEf.bits[0] = value >>> 31 == 0 ? new BoolEfRef(BoolEf.zeroes(border)) : new BoolEfRef(BoolEf.ones(border));
        return intEf;
    }
    public static IntEf of(BoolEfRef boolEf, int n) {
        IntEf intEf = new IntEf(n, boolEf.get().border);
        for (int i = 1; i < n; i++) intEf.bits[i] = new BoolEfRef(BoolEf.zeroes(boolEf.get().border));
        intEf.bits[n] = boolEf.copy();
        return intEf;
    }

    public static IntEf rand(int n) {
        IntEf rand = new IntEf(n);
        IntField.rand(rand, BoolEf::rand);
        return rand;
    }
    public static IntEf randNonNegative(int n) {
        IntEf rand = new IntEf(n);
        IntField.randNonNegative(rand, BoolEf::rand);
        return rand;
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
        res.replaceAll((ef, val) -> {
            if (bitMap.get(ef))
                for (int i = n; i < 32; i++) val |= (1 << i);
            return val;
        });

        return res;
    }

    @Override
    public IntEf copy() {
        IntEf copy = new IntEf(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }

    @Override
    public IntEf cache() {
        return new IntEf(n, (BoolEfRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
