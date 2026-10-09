package language.field.intField;

import language.field.boolField.BoolV;
import medium.Medium;
import medium.locusS.Vertex;

import java.util.HashMap;
import java.util.function.Supplier;

/** An integer field over the vertices */
public non-sealed class IntV extends IntField<BoolV, IntV> {
    private static final Supplier<BoolV> zeroes = () -> new BoolV().zeroes();
    private static final Supplier<BoolV> ones = () -> new BoolV().ones();
    private static final Supplier<BoolV> rand = () -> new BoolV().rand();

    /** Creates a new IntV with 1 sign bit + n value bits */
    public IntV(int n) {
        super(n, new BoolV[n+1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolV();
    }
    /** Creates a new IntV with the given bits. The first bit is the sign bit, and the rest are the value bits. */
    public IntV(BoolV[] bits) {
        super(bits.length - 1, bits);
    }

    @Override
    public BoolV[] getBits() { return bits; }

    /** Creates a new IntV with the given value and number of value bits. */
    public static IntV of(int value, int n) {
        IntV intV = new IntV(n);
        of(intV, value, zeroes, ones);
        return intV;
    }

    /** Creates a new IntV with the maximum value representable in the given number of bits. */
    public static IntV maxValue(int n) {
        IntV intV = new IntV(n);
        maxValue(intV, zeroes, ones);
        return intV;
    }

    /** Creates a new IntV with the minimum value representable in the given number of bits. */
    public static IntV minValue(int n) {
        IntV intV = new IntV(n);
        minValue(intV, zeroes, ones);
        return intV;
    }

    /** Creates a new IntV with a random value representable in the given number of bits. */
    public static IntV rand(int n) {
        IntV res = new IntV(n);
        IntField.rand(res, rand);
        return res;
    }
    /** Creates a new IntV with a random non-negative value representable in the given number of bits. */
    public static IntV randNonNegative(int n) {
        IntV res = new IntV(n);
        IntField.randNonNegative(res, rand);
        return res;
    }

    /** Indicates that this IntV should be decoded as an unsigned integer */
    public IntV decodeAsUnsigned() {
        decodeAsSigned = false;
        return this;
    }

    /** Converts this IntV to map a from vertices to integers. */
    public HashMap<Vertex, Integer> decode() {
        HashMap<Vertex, Integer> res = new HashMap<>();
        decode(this, res, medium.vertices, BoolV::decode, decodeAsSigned);
        return res;
    }

    @Override
    public IntV copy() {
        IntV copy = new IntV(n);
        copy(this, copy);
        return copy;
    }
}
