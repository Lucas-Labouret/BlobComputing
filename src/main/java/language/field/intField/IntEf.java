package language.field.intField;

import language.field.boolField.BoolEf;
import medium.Medium;
import medium.locusT.Ef;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

/** IntEf represents an integer language.field on Ef loci. */
public non-sealed class IntEf extends IntField<BoolEf, IntEf> {
    private static final Supplier<BoolEf> zeroes = () -> new BoolEf().zeroes();
    private static final Supplier<BoolEf> ones = () -> new BoolEf().ones();
    private static final Supplier<BoolEf> rand = () -> new BoolEf().rand();
    
    public IntEf(int n) {
        super(n, new BoolEf[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolEf();
    }
    public IntEf(int n, BoolEf[] bits) {
        super(n, bits);
    }

    @Override
    public BoolEf[] getBits() { return bits; }

    public static IntEf of(int value, int n) {
        IntEf intEf = new IntEf(n);
        of(intEf, value, zeroes, ones);
        return intEf;
    }

    public static IntEf maxValue(int n) {
        IntEf intEf = new IntEf(n);
        maxValue(intEf, zeroes, ones);
        return intEf;
    }

    public static IntEf minValue(int n) {
        IntEf intEf = new IntEf(n);
        minValue(intEf, zeroes, ones);
        return intEf;
    }

    public static IntEf rand(int n) {
        IntEf res = new IntEf(n);
        IntField.rand(res, rand);
        return res;
    }
    public static IntEf randNonNegative(int n) {
        IntEf res = new IntEf(n);
        IntField.randNonNegative(res, rand);
        return res;
    }

    /** Indicates that this IntEf should be decoded as an unsigned integer */
    public IntEf decodeAsUnsigned() {
        decodeAsSigned = false;
        return this;
    }

    /** Converts this IntEf to a HashMap<Ef, Integer>. */
    public HashMap<Ef, Integer> decode(Medium m) {
        HashMap<Ef, Integer> res = new HashMap<>();
        decode(this, res, m.efs, (loci, field) -> field.decode(loci), decodeAsSigned);
        return res;
    }

    @Override
    public IntEf copy() {
        IntEf copy = new IntEf(n);
        copy(this, copy);
        return copy;
    }
}
