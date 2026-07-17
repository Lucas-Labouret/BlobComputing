package language.field.intField;

import language.field.boolField.BoolE;
import language.fieldRef.boolField.BoolERef;
import medium.Medium;
import medium.locusS.Edge;

import java.util.Arrays;
import java.util.HashMap;

/** IntE represents an integer language.field on edges. */
public non-sealed class IntE extends IntField<BoolE> {
    public IntE(int n) {
        super(n, new BoolERef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolERef();
    }
    public IntE(int n, BoolERef[] bits) {
        super(n, bits);
    }

    public static IntE of(int value, int n) {
        IntE intE = new IntE(n);
        of(intE, value, BoolE::zeroes, BoolE::ones);
        return intE;
    }

    public static IntE maxValue(int n) {
        IntE intE = new IntE(n);
        maxValue(intE, BoolE::zeroes, BoolE::ones);
        return intE;
    }

    public static IntE minValue(int n) {
        IntE intE = new IntE(n);
        minValue(intE, BoolE::zeroes, BoolE::ones);
        return intE;
    }

    public static IntE rand(int n) {
        IntE rand = new IntE(n);
        IntField.rand(rand, BoolE::rand);
        return rand;
    }
    public static IntE randNonNegative(int n) {
        IntE rand = new IntE(n);
        IntField.randNonNegative(rand, BoolE::rand);
        return rand;
    }

    @Override
    public BoolERef[] getBits() {
        return (BoolERef[]) bits;
    }

    /** Converts this IntE to a HashMap<Edge, Integer>. */
    public HashMap<Edge, Integer> decode(Medium m) {
        HashMap<Edge, Integer> res = new HashMap<>();
        decode(this, res, m.edges, BoolE::decode);
        return res;
    }

    @Override
    public IntE copy() {
        IntE copy = new IntE(n);
        copy(this, copy);
        return copy;
    }

    @Override
    public IntE cache() {
        return new IntE(n, (BoolERef[]) Arrays.copyOf(this.bits, n+1));
    }
}
