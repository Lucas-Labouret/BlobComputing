package language.field.intField;

import language.field.boolField.BoolVe;
import medium.Medium;
import medium.locusT.Ve;

import java.util.HashMap;
import java.util.function.Supplier;

/** An integer field over the Ve loci */
public non-sealed class IntVe extends IntField<BoolVe, IntVe> {
    private static final Supplier<BoolVe> zeroes = () -> new BoolVe().zeroes();
    private static final Supplier<BoolVe> ones = () -> new BoolVe().ones();
    private static final Supplier<BoolVe> rand = () -> new BoolVe().rand();
    
    /** Creates a new IntVe with 1 sign bit + n value bits */
    public IntVe(int n) {
        super(n, new BoolVe[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolVe().zeroes();
    }
    /** Creates a new IntVe with the given bits. The first bit is the sign bit, and the rest are the value bits. */
    public IntVe(BoolVe[] bits) {
        super(bits.length - 1, bits);
    }

    @Override
    public BoolVe[] getBits() { return bits; }

    /** Creates a new IntVe with the given value and number of value bits. */
    public static IntVe of(int value, int n) {
        IntVe intVe = new IntVe(n);
        of(intVe, value, zeroes, ones);
        return intVe;
    }

    /** Creates a new IntVe with the maximum value representable in the given number of bits. */
    public static IntVe maxValue(int n) {
        IntVe intVe = new IntVe(n);
        maxValue(intVe, zeroes, ones);
        return intVe;
    }

    /** Creates a new IntVe with the minimum value representable in the given number of bits. */
    public static IntVe minValue(int n) {
        IntVe intVe = new IntVe(n);
        minValue(intVe, zeroes, ones);
        return intVe;
    }

    /** Creates a new IntVe with a random value representable in the given number of bits. */
    public static IntVe rand(int n) {
        IntVe res = new IntVe(n);
        IntField.rand(res, rand);
        return res;
    }
    /** Creates a new IntVe with a random non-negative value representable in the given number of bits. */
    public static IntVe randNonNegative(int n) {
        IntVe res = new IntVe(n);
        IntField.randNonNegative(res, rand);
        return res;
    }

    /** Indicates that this IntVe should be decoded as an unsigned integer */
    public IntVe decodeAsUnsigned() {
        decodeAsSigned = false;
        return this;
    }

    /** Converts this IntVe to a map from Ve loci to integers. */
    public HashMap<Ve, Integer> decode(Medium m) {
        HashMap<Ve, Integer> res = new HashMap<>();
        decode(this, res, m.ves, (loci, field) -> field.decode(loci), decodeAsSigned);
        return res;
    }

    @Override
    public IntVe copy() {
        IntVe copy = new IntVe(n);
        copy(this, copy);
        return copy;
    }
}
