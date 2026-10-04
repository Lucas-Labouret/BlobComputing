package language.field.intField;

import language.field.boolField.BoolF;
import medium.Medium;
import medium.locusS.Face;

import java.util.Arrays;
import java.util.HashMap;
import java.util.function.Supplier;

/** IntF represents an integer language.field on faces. */
public non-sealed class IntF extends IntField<BoolF, IntF> {
    private static final Supplier<BoolF> zeroes = () -> new BoolF().zeroes();
    private static final Supplier<BoolF> ones = () -> new BoolF().ones();
    private static final Supplier<BoolF> rand = () -> new BoolF().rand();
    
    public IntF(int n) {
        super(n, new BoolF[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolF();
    }
    public IntF(int n, BoolF[] bits) {
        super(n, bits);
    }

    @Override
    public BoolF[] getBits() { return bits; }

    public static IntF of(int value, int n) {
        IntF intF = new IntF(n);
        of(intF, value, zeroes, ones);
        return intF;
    }

    public static IntF maxValue(int n) {
        IntF intF = new IntF(n);
        maxValue(intF, zeroes, ones);
        return intF;
    }

    public static IntF minValue(int n) {
        IntF intF = new IntF(n);
        minValue(intF, zeroes, ones);
        return intF;
    }

    public static IntF rand(int n) {
        IntF res = new IntF(n);
        IntField.rand(res, rand);
        return res;
    }
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

    /** Converts this IntF to a HashMap<Face, Integer>. */
    public HashMap<Face, Integer> decode(Medium m) {
        HashMap<Face, Integer> res = new HashMap<>();
        decode(this, res, m.faces, (loci, field) -> field.decode(loci), decodeAsSigned);
        return res;
    }

    @Override
    public IntF copy() {
        IntF copy = new IntF(n);
        copy(this, copy);
        return copy;
    }
}
