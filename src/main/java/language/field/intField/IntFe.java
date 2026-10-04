package language.field.intField;

import language.field.boolField.BoolFe;
import medium.Medium;
import medium.locusT.Fe;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

/** IntFe represents an integer language.field on Fe loci. */
public non-sealed class IntFe extends IntField<BoolFe, IntFe> {
    private static final Supplier<BoolFe> zeroes = () -> new BoolFe().zeroes();
    private static final Supplier<BoolFe> ones = () -> new BoolFe().ones();
    private static final Supplier<BoolFe> rand = () -> new BoolFe().rand();
    
    public IntFe(int n) {
        super(n, new BoolFe[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolFe();
    }
    public IntFe(int n, BoolFe[] bits) {
        super(n, bits);
    }

    @Override
    public BoolFe[] getBits() { return bits; }

    public static IntFe of(int value, int n) {
        IntFe intFe = new IntFe(n);
        of(intFe, value, zeroes, ones);
        return intFe;
    }

    public static IntFe maxValue(int n) {
        IntFe intFe = new IntFe(n);
        maxValue(intFe, zeroes, ones);
        return intFe;
    }

    public static IntFe minValue(int n) {
        IntFe intFe = new IntFe(n);
        minValue(intFe, zeroes, ones);
        return intFe;
    }

    public static IntFe rand(int n) {
        IntFe res = new IntFe(n);
        IntField.rand(res, rand);
        return res;
    }
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

    /** Converts this IntFe to a HashMap<Fe, Integer>. */
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
