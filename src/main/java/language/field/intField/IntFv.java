package language.field.intField;

import language.field.boolField.BoolFv;
import medium.Medium;
import medium.locusT.Fv;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

/** IntFv represents an integer language.field on Fv loci. */
public non-sealed class IntFv extends IntField<BoolFv, IntFv> {
    private static final Supplier<BoolFv> zeroes = () -> new BoolFv().zeroes();
    private static final Supplier<BoolFv> ones = () -> new BoolFv().ones();
    private static final Supplier<BoolFv> rand = () -> new BoolFv().rand();
    
    public IntFv(int n) {
        super(n, new BoolFv[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolFv();
    }
    public IntFv(int n, BoolFv[] bits) {
        super(n, bits);
    }

    @Override
    public BoolFv[] getBits() { return bits; }

    public static IntFv of(int value, int n) {
        IntFv intFv = new IntFv(n);
        of(intFv, value, zeroes, ones);
        return intFv;
    }
    
    public static IntFv maxValue(int n) {
        IntFv intFv = new IntFv(n);
        maxValue(intFv, zeroes, ones);
        return intFv;
    }
    
    public static IntFv minValue(int n) {
        IntFv intFv = new IntFv(n);
        minValue(intFv, zeroes, ones);
        return intFv;
    }

    public static IntFv rand(int n) {
        IntFv res = new IntFv(n);
        IntField.rand(res, rand);
        return res;
    }
    public static IntFv randNonNegative(int n) {
        IntFv res = new IntFv(n);
        IntField.randNonNegative(res, rand);
        return res;
    }

    /** Indicates that this IntFv should be decoded as an unsigned integer */
    public IntFv decodeAsUnsigned() {
        decodeAsSigned = false;
        return this;
    }

    /** Converts this IntFv to a HashMap<Fv, Integer>. */
    public HashMap<Fv, Integer> decode(Medium m) {
        HashMap<Fv, Integer> res = new HashMap<>();
        decode(this, res, m.fvs, (loci, field) -> field.decode(loci), decodeAsSigned);
        return res;
    }

    @Override
    public IntFv copy() {
        IntFv copy = new IntFv(n);
        copy(this, copy);
        return copy;
    }
}
