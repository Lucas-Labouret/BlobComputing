package language.field.intField;

import language.field.boolField.BoolEv;
import medium.Medium;
import medium.locusT.Ev;

import java.util.HashMap;
import java.util.function.Supplier;

/** An integer field over the Ev loci */
public non-sealed class IntEv extends IntField<BoolEv, IntEv> {
    private static final Supplier<BoolEv> zeroes = () -> new BoolEv().zeroes();
    private static final Supplier<BoolEv> ones = () -> new BoolEv().ones();
    private static final Supplier<BoolEv> rand = () -> new BoolEv().rand();
    
    /** Creates a new IntEv with 1 sign bit + n value bits */
    public IntEv(int n) {
        super(n, new BoolEv[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolEv();
    }
    /** Creates a new IntEv with the given bits. The first bit is the sign bit, and the rest are the value bits. */
    public IntEv(BoolEv[] bits) {
        super(bits.length - 1, bits);
    }

    @Override
    public BoolEv[] getBits() { return bits; }

    /** Creates a new IntEv with the given value and number of value bits. */
    public static IntEv of(int value, int n) {
        IntEv intEv = new IntEv(n);
        of(intEv, value, zeroes, ones);
        return intEv;
    }

    /** Creates a new IntEv with the maximum value representable in the given number of bits. */
    public static IntEv maxValue(int n) {
        IntEv intEv = new IntEv(n);
        maxValue(intEv, zeroes, ones);
        return intEv;
    }

    /** Creates a new IntEv with the minimum value representable in the given number of bits. */
    public static IntEv minValue(int n) {
        IntEv intEv = new IntEv(n);
        minValue(intEv, zeroes, ones);
        return intEv;
    }

    /** Creates a new IntEv with a random value representable in the given number of bits. */
    public static IntEv rand(int n) {
        IntEv res = new IntEv(n);
        IntField.rand(res, rand);
        return res;
    }
    /** Creates a new IntEv with a random non-negative value representable in the given number of bits. */
    public static IntEv randNonNegative(int n) {
        IntEv res = new IntEv(n);
        IntField.randNonNegative(res, rand);
        return res;
    }

    /** Indicates that this IntEv should be decoded as an unsigned integer */
    public IntEv decodeAsUnsigned() {
        decodeAsSigned = false;
        return this;
    }

    /** Converts this IntEv to a map from Ev loci to integers. */
    public HashMap<Ev, Integer> decode() {
        HashMap<Ev, Integer> res = new HashMap<>();
        decode(this, res, medium.evs, BoolEv::decode, decodeAsSigned);
        return res;
    }

    @Override
    public IntEv copy() {
        IntEv copy = new IntEv(n);
        copy(this, copy);
        return copy;
    }
}
