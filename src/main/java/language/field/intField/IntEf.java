package language.field.intField;

import language.field.boolField.BoolEf;
import medium.Medium;
import medium.locusT.Ef;

import java.util.HashMap;
import java.util.function.Supplier;

/** An integer field over the Ef loci */
public non-sealed class IntEf extends IntField<BoolEf, IntEf> {
    private static final Supplier<BoolEf> zeroes = () -> new BoolEf().zeroes();
    private static final Supplier<BoolEf> ones = () -> new BoolEf().ones();
    private static final Supplier<BoolEf> rand = () -> new BoolEf().rand();
    
    /** Creates a new IntEf with 1 sign bit + n value bits */
    public IntEf(int n) {
        super(n, new BoolEf[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolEf();
    }
    /** Creates a new IntEf with the given bits. The first bit is the sign bit, and the rest are the value bits. */
    public IntEf(BoolEf[] bits) {
        super(bits.length - 1, bits);
    }

    @Override
    public BoolEf[] getBits() { return bits; }

    /** Creates a new IntEf with the given value and number of value bits. */
    public static IntEf of(int value, int n) {
        IntEf intEf = new IntEf(n);
        of(intEf, value, zeroes, ones);
        return intEf;
    }

    /** Creates a new IntEf with the maximum value representable in the given number of bits. */
    public static IntEf maxValue(int n) {
        IntEf intEf = new IntEf(n);
        maxValue(intEf, zeroes, ones);
        return intEf;
    }

    /** Creates a new IntEf with the minimum value representable in the given number of bits. */
    public static IntEf minValue(int n) {
        IntEf intEf = new IntEf(n);
        minValue(intEf, zeroes, ones);
        return intEf;
    }

    /** Creates a new IntEf with a random value representable in the given number of bits. */
    public static IntEf rand(int n) {
        IntEf res = new IntEf(n);
        IntField.rand(res, rand);
        return res;
    }
    /** Creates a new IntEf with a random non-negative value representable in the given number of bits. */
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

    /** Converts this IntEf to a map from Ef loci to integers. */
    public HashMap<Ef, Integer> decode() {
        HashMap<Ef, Integer> res = new HashMap<>();
        decode(this, res, medium.efs, BoolEf::decode, decodeAsSigned);
        return res;
    }

    @Override
    public IntEf copy() {
        IntEf copy = new IntEf(n);
        copy(this, copy);
        return copy;
    }
}
