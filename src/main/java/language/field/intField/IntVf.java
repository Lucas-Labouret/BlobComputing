package language.field.intField;

import language.field.boolField.BoolVf;
import medium.Medium;
import medium.locusT.Vf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

/** IntVf represents an integer language.field on Vf loci. */
public non-sealed class IntVf extends IntField<BoolVf, IntVf> {
    private static final Supplier<BoolVf> zeroes = () -> new BoolVf().zeroes();
    private static final Supplier<BoolVf> ones = () -> new BoolVf().ones();
    private static final Supplier<BoolVf> rand = () -> new BoolVf().rand();

    public IntVf(int n) {
        super(n, new BoolVf[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolVf();
    }
    public IntVf(int n, BoolVf[] bits) {
        super(n, bits);
    }

    @Override
    public BoolVf[] getBits() { return bits; }

    public static IntVf of(int value, int n) {
        IntVf intVf = new IntVf(n);
        of(intVf, value, zeroes, ones);
        return intVf;
    }

    public static IntVf maxValue(int n) {
        IntVf intVf = new IntVf(n);
        maxValue(intVf, zeroes, ones);
        return intVf;
    }

    public static IntVf minValue(int n) {
        IntVf intVf = new IntVf(n);
        minValue(intVf, zeroes, ones);
        return intVf;
    }

    public static IntVf rand(int n) {
        IntVf res = new IntVf(n);
        IntField.rand(res, rand);
        return res;
    }
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

    /** Converts this IntVf to a HashMap<Vf, Integer>. */
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
