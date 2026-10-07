package language.field.intField;

import language.field.boolField.BoolE;
import medium.Medium;
import medium.locusS.Edge;

import java.util.HashMap;
import java.util.function.Supplier;

/** An integer field over the edges */
public non-sealed class IntE extends IntField<BoolE, IntE> {
    private static final Supplier<BoolE> zeroes = () -> new BoolE().zeroes();
    private static final Supplier<BoolE> ones = () -> new BoolE().ones();
    private static final Supplier<BoolE> rand = () -> new BoolE().rand();
    
    /** Creates a new IntE with 1 sign bit + n value bits */
    public IntE(int n) {
        super(n, new BoolE[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolE();
    }
    /** Creates a new IntE with the given bits. The first bit is the sign bit, and the rest are the value bits. */
    public IntE(BoolE[] bits) {
        super(bits.length - 1, bits);
    }

    @Override
    public BoolE[] getBits() { return bits; }

    /** Creates a new IntE with the given value and number of value bits. */
    public static IntE of(int value, int n) {
        IntE intE = new IntE(n);
        of(intE, value, zeroes, ones);
        return intE;
    }

    /** Creates a new IntE with the maximum value representable in the given number of bits. */
    public static IntE maxValue(int n) {
        IntE intE = new IntE(n);
        maxValue(intE, zeroes, ones);
        return intE;
    }

    /** Creates a new IntE with the minimum value representable in the given number of bits. */
    public static IntE minValue(int n) {
        IntE intE = new IntE(n);
        minValue(intE, zeroes, ones);
        return intE;
    }

    /** Creates a new IntE with a random value representable in the given number of bits. */
    public static IntE rand(int n) {
        IntE res = new IntE(n);
        IntField.rand(res, rand);
        return res;
    }
    /** Creates a new IntE with a random non-negative value representable in the given number of bits. */
    public static IntE randNonNegative(int n) {
        IntE res = new IntE(n);
        IntField.randNonNegative(res, rand);
        return res;
    }

    /** Indicates that this IntE should be decoded as an unsigned integer */
    public IntE decodeAsUnsigned() {
        decodeAsSigned = false;
        return this;
    }

    /** Converts this IntE to a map from edges to integers. */
    public HashMap<Edge, Integer> decode(Medium m) {
        HashMap<Edge, Integer> res = new HashMap<>();
        decode(this, res, m.edges, (loci, field) -> field.decode(loci), decodeAsSigned);
        return res;
    }

    @Override
    public IntE copy() {
        IntE copy = new IntE(n);
        copy(this, copy);
        return copy;
    }
}
