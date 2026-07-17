package language.field.intField;

import language.field.boolField.BoolV;
import language.fieldRef.boolField.BoolVRef;
import medium.Medium;
import medium.locusS.Vertex;

import java.util.Arrays;
import java.util.HashMap;

/**
 * IntV represents an integer language.field on vertices.
 * <p>
 * It is represented as an array of BoolVRefs, where each BoolVRef represents a bit of the integer.
 * The bits are stored most significant bit first as an array of BoolV.
 * Negative integers are stored using 2's complement, with bits[0] being the sign bit.
 */
public non-sealed class IntV extends IntField<BoolV> {
    public IntV(int n) {
        super(n, new BoolVRef[n+1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolVRef();
    }
    public IntV(int n, BoolVRef[] bits) {
        super(n, bits);
    }

    public static IntV of(int value, int n) {
        IntV intV = new IntV(n);
        of(intV, value, BoolV::zeroes, BoolV::ones);
        return intV;
    }

    public static IntV maxValue(int n) {
        IntV intV = new IntV(n);
        maxValue(intV, BoolV::zeroes, BoolV::ones);
        return intV;
    }

    public static IntV minValue(int n) {
        IntV intV = new IntV(n);
        minValue(intV, BoolV::zeroes, BoolV::ones);
        return intV;
    }

    public static IntV rand(int n) {
        IntV rand = new IntV(n);
        IntField.rand(rand, BoolV::rand);
        return rand;
    }
    public static IntV randNonNegative(int n) {
        IntV rand = new IntV(n);
        IntField.randNonNegative(rand, BoolV::rand);
        return rand;
    }

    @Override
    public BoolVRef[] getBits() {
        return (BoolVRef[]) bits;
    }

    /** Converts this IntV to a HashMap<Vertex, Integer> by decoding each bit and combining them into an integer. */
    public HashMap<Vertex, Integer> decode(Medium m) {
        HashMap<Vertex, Integer> res = new HashMap<>();
        decode(this, res, m.vertices, BoolV::decode);
        return res;
    }

    @Override
    public IntV copy() {
        IntV copy = new IntV(n);
        copy(this, copy);
        return copy;
    }

    @Override
    public IntV cache() {
        return new IntV(n, (BoolVRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
