package language.field.intField;

import language.field.boolField.BoolFe;
import medium.Medium;
import medium.locusT.Fe;

import java.util.HashMap;
import java.util.function.Supplier;

/** An integer field over the Fe loci */
public non-sealed class IntFe extends IntField<BoolFe, IntFe> {
    private static final Supplier<BoolFe> zeroes = () -> new BoolFe().zeroes();
    private static final Supplier<BoolFe> ones = () -> new BoolFe().ones();
    private static final Supplier<BoolFe> rand = () -> new BoolFe().rand();
    
    /** Creates a new IntFe with 1 sign bit + n value bits */
    public IntFe(int n) {
        super(n, new BoolFe[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolFe();
    }
    /** Creates a new IntFe with the given bits. The first bit is the sign bit, and the rest are the value bits. */
    public IntFe(BoolFe[] bits) {
        super(bits.length - 1, bits);
    }

    @Override
    public BoolFe[] getBits() { return bits; }

    /** Creates a new IntFe with the given value and number of value bits. */
    public static IntFe of(int value, int n) {
        IntFe intFe = new IntFe(n);
        of(intFe, value, zeroes, ones);
        return intFe;
    }

    /** Creates a new IntFe with the maximum value representable in the given number of bits. */
    public static IntFe maxValue(int n) {
        IntFe intFe = new IntFe(n);
        maxValue(intFe, zeroes, ones);
        return intFe;
    }

    /** Creates a new IntFe with the minimum value representable in the given number of bits. */
    public static IntFe minValue(int n) {
        IntFe intFe = new IntFe(n);
        minValue(intFe, zeroes, ones);
        return intFe;
    }

    /** Creates a new IntFe with a random value representable in the given number of bits. */
    public static IntFe rand(int n) {
        IntFe res = new IntFe(n);
        IntField.rand(res, rand);
        return res;
    }
    /** Creates a new IntFe with a random non-negative value representable in the given number of bits. */
    public static IntFe randNonNegative(int n) {
        IntFe res = new IntFe(n);
        IntField.randNonNegative(res, rand);
        return res;
    }

    /** Indicates that this IntFe should be decoded as an unsigned integer */
    public IntFe decodeAsUnsigned() {
        decodeAsSigned = false;
        return this;
    }

    /** Converts this IntFe to a map from Fe loci to integers. */
    public HashMap<Fe, Integer> decode(Medium m) {
        HashMap<Fe, Integer> res = new HashMap<>();
        decode(this, res, m.fes, (loci, field) -> field.decode(loci), decodeAsSigned);
        return res;
    }

    @Override
    public IntFe copy() {
        IntFe copy = new IntFe(n);
        copy(this, copy);
        return copy;
    }
}
