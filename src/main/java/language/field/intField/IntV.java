package language.field.intField;

import language.field.boolField.BoolV;
import medium.Medium;
import medium.locusS.Vertex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

/**
 * IntV represents an integer language.field on vertices.
 * <p>
 * It is represented as an array of BoolVs, where each BoolV represents a bit of the integer.
 * The bits are stored most significant bit first as an array of BoolV.
 * Negative integers are stored using 2's complement, with bits[0] being the sign bit.
 */
public non-sealed class IntV extends IntField<BoolV, IntV> {
    private static final Supplier<BoolV> zeroes = () -> new BoolV().zeroes();
    private static final Supplier<BoolV> ones = () -> new BoolV().ones();
    private static final Supplier<BoolV> rand = () -> new BoolV().rand();

    public IntV(int n) {
        super(n, new BoolV[n+1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolV();
    }
    public IntV(int n, BoolV[] bits) {
        super(n, bits);
    }

    @Override
    public BoolV[] getBits() { return bits; }

    public static IntV of(int value, int n) {
        IntV intV = new IntV(n);
        of(intV, value, zeroes, ones);
        return intV;
    }

    public static IntV maxValue(int n) {
        IntV intV = new IntV(n);
        maxValue(intV, zeroes, ones);
        return intV;
    }

    public static IntV minValue(int n) {
        IntV intV = new IntV(n);
        minValue(intV, zeroes, ones);
        return intV;
    }

    public static IntV rand(int n) {
        IntV res = new IntV(n);
        IntField.rand(res, rand);
        return res;
    }
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

    /** Converts this IntV to a HashMap<Vertex, Integer> by decoding each bit and combining them into an integer. */
    public HashMap<Vertex, Integer> decode(Medium m) {
        HashMap<Vertex, Integer> res = new HashMap<>();
        decode(this, res, m.vertices, (loci, field) -> field.decode(loci), decodeAsSigned);
        return res;
    }

    @Override
    public IntV copy() {
        IntV copy = new IntV(n);
        copy(this, copy);
        return copy;
    }
}
