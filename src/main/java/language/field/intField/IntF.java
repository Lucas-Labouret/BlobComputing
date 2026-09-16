package language.field.intField;

import language.field.boolField.BoolF;
import language.fieldRef.boolField.BoolFRef;
import medium.Medium;
import medium.locusS.Face;

import java.util.Arrays;
import java.util.HashMap;

/** IntF represents an integer language.field on faces. */
public non-sealed class IntF extends IntField<BoolF> {
    public IntF(int n) {
        super(n, new BoolFRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolFRef();
    }
    public IntF(int n, BoolFRef[] bits) {
        super(n, bits);
    }

    public static IntF of(int value, int n) {
        IntF intF = new IntF(n);
        of(intF, value, BoolF::zeroes, BoolF::ones);
        return intF;
    }

    public static IntF maxValue(int n) {
        IntF intF = new IntF(n);
        maxValue(intF, BoolF::zeroes, BoolF::ones);
        return intF;
    }

    public static IntF minValue(int n) {
        IntF intF = new IntF(n);
        minValue(intF, BoolF::zeroes, BoolF::ones);
        return intF;
    }

    public static IntF rand(int n) {
        IntF rand = new IntF(n);
        IntField.rand(rand, BoolF::rand);
        return rand;
    }
    public static IntF randNonNegative(int n) {
        IntF rand = new IntF(n);
        IntField.randNonNegative(rand, BoolF::rand);
        return rand;
    }

    @Override
    public BoolFRef[] getBits() {
        return (BoolFRef[]) bits;
    }

    /** Indicates that this IntF should be decoded as an unsigned integer */
    public IntF decodeAsUnsigned() {
        decodeAsSigned = false;
        return this;
    }

    /** Converts this IntF to a HashMap<Face, Integer>. */
    public HashMap<Face, Integer> decode(Medium m) {
        HashMap<Face, Integer> res = new HashMap<>();
        decode(this, res, m.faces, BoolF::decode, decodeAsSigned);
        return res;
    }

    @Override
    public IntF copy() {
        IntF copy = new IntF(n);
        copy(this, copy);
        return copy;
    }

    @Override
    public IntF cache() {
        return new IntF(n, (BoolFRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
