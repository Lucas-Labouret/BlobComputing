package language.field.intField;

import language.field.boolField.BoolE;
import medium.Medium;
import medium.locusS.Edge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

/** IntE represents an integer language.field on edges. */
public non-sealed class IntE extends IntField<BoolE, IntE> {
    private static final Supplier<BoolE> zeroes = () -> new BoolE().zeroes();
    private static final Supplier<BoolE> ones = () -> new BoolE().ones();
    private static final Supplier<BoolE> rand = () -> new BoolE().rand();
    
    public IntE(int n) {
        super(n, new BoolE[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolE();
    }
    public IntE(int n, BoolE[] bits) {
        super(n, bits);
    }

    @Override
    public BoolE[] getBits() { return bits; }

    public static IntE of(int value, int n) {
        IntE intE = new IntE(n);
        of(intE, value, zeroes, ones);
        return intE;
    }

    public static IntE maxValue(int n) {
        IntE intE = new IntE(n);
        maxValue(intE, zeroes, ones);
        return intE;
    }

    public static IntE minValue(int n) {
        IntE intE = new IntE(n);
        minValue(intE, zeroes, ones);
        return intE;
    }

    public static IntE rand(int n) {
        IntE res = new IntE(n);
        IntField.rand(res, rand);
        return res;
    }
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

    /** Converts this IntE to a HashMap<Edge, Integer>. */
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
