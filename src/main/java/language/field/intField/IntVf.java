package language.field.intField;

import language.field.boolField.BoolVf;
import medium.Medium;
import medium.locusT.Vf;

import java.util.HashMap;
import java.util.function.Supplier;

/** An integer field over the Vf loci */
public non-sealed class IntVf extends IntField<BoolVf, IntVf> {
    private static final Supplier<BoolVf> zeroes = () -> new BoolVf().zeroes();
    private static final Supplier<BoolVf> ones = () -> new BoolVf().ones();
    private static final Supplier<BoolVf> rand = () -> new BoolVf().rand();

    /** Creates a new IntVf with 1 sign bit + n value bits */
    public IntVf(int n) {
        super(n, new BoolVf[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolVf();
    }
    /** Creates a new IntVf with the given bits. The first bit is the sign bit, and the rest are the value bits. */
    public IntVf(BoolVf[] bits) {
        super(bits.length - 1, bits);
    }

    @Override
    public BoolVf[] getBits() { return bits; }

    /** Creates a new IntVf with the given value and number of value bits. */
    public static IntVf of(int value, int n) {
        IntVf intVf = new IntVf(n);
        of(intVf, value, zeroes, ones);
        return intVf;
    }

    /** Creates a new IntVf with the maximum value representable in the given number of bits. */
    public static IntVf maxValue(int n) {
        IntVf intVf = new IntVf(n);
        maxValue(intVf, zeroes, ones);
        return intVf;
    }

    /** Creates a new IntVf with the minimum value representable in the given number of bits. */
    public static IntVf minValue(int n) {
        IntVf intVf = new IntVf(n);
        minValue(intVf, zeroes, ones);
        return intVf;
    }

    /** Creates a new IntVf with a random value representable in the given number of bits. */
    public static IntVf rand(int n) {
        IntVf res = new IntVf(n);
        IntField.rand(res, rand);
        return res;
    }
    /** Creates a new IntVf with a random non-negative value representable in the given number of bits. */
    public static IntVf randNonNegative(int n) {
        IntVf res = new IntVf(n);
        IntField.randNonNegative(res, rand);
        return res;
    }

    /** Indicates that this IntVf should be decoded as an unsigned integer */
    public IntVf decodeAsUnsigned() {
        decodeAsSigned = false;
        return this;
    }

    /** Converts this IntVf to a map from Vf loci to integers. */
    public HashMap<Vf, Integer> decode(Medium m) {
        HashMap<Vf, Integer> res = new HashMap<>();
        decode(this, res, m.vfs, (loci, field) -> field.decode(loci), decodeAsSigned);
        return res;
    }

    @Override
    public IntVf copy() {
        IntVf copy = new IntVf(n);
        copy(this, copy);
        return copy;
    }
}
