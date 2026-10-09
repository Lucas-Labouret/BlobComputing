package language.field.intField;

import language.field.boolField.BoolF;
import medium.Medium;
import medium.locusS.Face;

import java.util.HashMap;
import java.util.function.Supplier;

/** An integer field over the faces */
public non-sealed class IntF extends IntField<BoolF, IntF> {
    private static final Supplier<BoolF> zeroes = () -> new BoolF().zeroes();
    private static final Supplier<BoolF> ones = () -> new BoolF().ones();
    private static final Supplier<BoolF> rand = () -> new BoolF().rand();
    
    /** Creates a new IntF with 1 sign bit + n value bits */
    public IntF(int n) {
        super(n, new BoolF[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolF();
    }
    /** Creates a new IntF with the given bits. The first bit is the sign bit, and the rest are the value bits. */
    public IntF(BoolF[] bits) {
        super(bits.length - 1, bits);
    }

    @Override
    public BoolF[] getBits() { return bits; }

    /** Creates a new IntF with the given value and number of value bits. */
    public static IntF of(int value, int n) {
        IntF intF = new IntF(n);
        of(intF, value, zeroes, ones);
        return intF;
    }

    /** Creates a new IntF with the maximum value representable in the given number of bits. */
    public static IntF maxValue(int n) {
        IntF intF = new IntF(n);
        maxValue(intF, zeroes, ones);
        return intF;
    }

    /** Creates a new IntF with the minimum value representable in the given number of bits. */
    public static IntF minValue(int n) {
        IntF intF = new IntF(n);
        minValue(intF, zeroes, ones);
        return intF;
    }

    /** Creates a new IntF with a random value representable in the given number of bits. */
    public static IntF rand(int n) {
        IntF res = new IntF(n);
        IntField.rand(res, rand);
        return res;
    }
    /** Creates a new IntF with a random non-negative value representable in the given number of bits. */
    public static IntF randNonNegative(int n) {
        IntF res = new IntF(n);
        IntField.randNonNegative(res, rand);
        return res;
    }

    /** Indicates that this IntF should be decoded as an unsigned integer */
    public IntF decodeAsUnsigned() {
        decodeAsSigned = false;
        return this;
    }

    /** Converts this IntF to a map from faces to integers. */
    public HashMap<Face, Integer> decode() {
        HashMap<Face, Integer> res = new HashMap<>();
        decode(this, res, medium.faces, BoolF::decode, decodeAsSigned);
        return res;
    }

    @Override
    public IntF copy() {
        IntF copy = new IntF(n);
        copy(this, copy);
        return copy;
    }
}
