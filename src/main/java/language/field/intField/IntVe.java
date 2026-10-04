package language.field.intField;

import language.field.boolField.BoolVe;
import medium.Medium;
import medium.locusT.Ve;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

/** IntVe represents an integer language.field on Ve loci. */
public non-sealed class IntVe extends IntField<BoolVe, IntVe> {
    private static final Supplier<BoolVe> zeroes = () -> new BoolVe().zeroes();
    private static final Supplier<BoolVe> ones = () -> new BoolVe().ones();
    private static final Supplier<BoolVe> rand = () -> new BoolVe().rand();
    
    public IntVe(int n) {
        super(n, new BoolVe[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolVe().zeroes();
    }
    public IntVe(int n, BoolVe[] bits) {
        super(n, bits);
    }

    @Override
    public BoolVe[] getBits() { return bits; }

    public static IntVe of(int value, int n) {
        IntVe intVe = new IntVe(n);
        of(intVe, value, zeroes, ones);
        return intVe;
    }

    public static IntVe maxValue(int n) {
        IntVe intVe = new IntVe(n);
        maxValue(intVe, zeroes, ones);
        return intVe;
    }

    public static IntVe minValue(int n) {
        IntVe intVe = new IntVe(n);
        minValue(intVe, zeroes, ones);
        return intVe;
    }

    public static IntVe rand(int n) {
        IntVe res = new IntVe(n);
        IntField.rand(res, rand);
        return res;
    }
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

    /** Converts this IntVe to a HashMap<Ve, Integer>. */
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
