package language.field.intField;

import language.field.boolField.BoolFv;
import medium.Medium;
import medium.locusT.Fv;

import java.util.HashMap;
import java.util.function.Supplier;

/** An integer field over the Fv loci */
public non-sealed class IntFv extends IntField<BoolFv, IntFv> {
    private static final Supplier<BoolFv> zeroes = () -> new BoolFv().zeroes();
    private static final Supplier<BoolFv> ones = () -> new BoolFv().ones();
    private static final Supplier<BoolFv> rand = () -> new BoolFv().rand();
    
    /** Creates a new IntFv with 1 sign bit + n value bits */
    public IntFv(int n) {
        super(n, new BoolFv[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolFv();
    }
    /** Creates a new IntFv with the given bits. The first bit is the sign bit, and the rest are the value bits. */
    public IntFv(BoolFv[] bits) {
        super(bits.length - 1, bits);
    }

    @Override
    public BoolFv[] getBits() { return bits; }

    /** Creates a new IntFv with the given value and number of value bits. */
    public static IntFv of(int value, int n) {
        IntFv intFv = new IntFv(n);
        of(intFv, value, zeroes, ones);
        return intFv;
    }
    
    /** Creates a new IntFv with the maximum value representable in the given number of bits. */
    public static IntFv maxValue(int n) {
        IntFv intFv = new IntFv(n);
        maxValue(intFv, zeroes, ones);
        return intFv;
    }
    
    /** Creates a new IntFv with the minimum value representable in the given number of bits. */
    public static IntFv minValue(int n) {
        IntFv intFv = new IntFv(n);
        minValue(intFv, zeroes, ones);
        return intFv;
    }

    /** Creates a new IntFv with a random value representable in the given number of bits. */
    public static IntFv rand(int n) {
        IntFv res = new IntFv(n);
        IntField.rand(res, rand);
        return res;
    }
    /** Creates a new IntFv with a random non-negative value representable in the given number of bits. */
    public static IntFv randNonNegative(int n) {
        IntFv res = new IntFv(n);
        IntField.randNonNegative(res, rand);
        return res;
    }

    /** Indicates that this IntFv should be decoded as an unsigned integer */
    public IntFv decodeAsUnsigned() {
        decodeAsSigned = false;
        return this;
    }

    /** Converts this IntFv to a map from Fv loci to integers. */
    public HashMap<Fv, Integer> decode(Medium m) {
        HashMap<Fv, Integer> res = new HashMap<>();
        decode(this, res, m.fvs, (loci, field) -> field.decode(loci), decodeAsSigned);
        return res;
    }

    @Override
    public IntFv copy() {
        IntFv copy = new IntFv(n);
        copy(this, copy);
        return copy;
    }
}
