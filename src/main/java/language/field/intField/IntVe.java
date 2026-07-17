package language.field.intField;

import language.field.boolField.BoolVe;
import language.fieldRef.boolField.BoolVeRef;
import medium.Medium;
import medium.locusT.Ve;

import java.util.Arrays;
import java.util.HashMap;

/** IntVe represents an integer language.field on Ve loci. */
public non-sealed class IntVe extends IntField<BoolVe> {
    public IntVe(int n) {
        super(n, new BoolVeRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolVeRef();
    }
    public IntVe(int n, BoolVeRef[] bits) {
        super(n, bits);
    }

    public static IntVe of(int value, int n) {
        IntVe intVe = new IntVe(n);
        of(intVe, value, BoolVe::zeroes, BoolVe::ones);
        return intVe;
    }

    public static IntVe maxValue(int n) {
        IntVe intVe = new IntVe(n);
        maxValue(intVe, BoolVe::zeroes, BoolVe::ones);
        return intVe;
    }

    public static IntVe minValue(int n) {
        IntVe intVe = new IntVe(n);
        minValue(intVe, BoolVe::zeroes, BoolVe::ones);
        return intVe;
    }

    public static IntVe rand(int n) {
        IntVe rand = new IntVe(n);
        IntField.rand(rand, BoolVe::rand);
        return rand;
    }
    public static IntVe randNonNegative(int n) {
        IntVe rand = new IntVe(n);
        IntField.randNonNegative(rand, BoolVe::rand);
        return rand;
    }

    @Override
    public BoolVeRef[] getBits() {
        return (BoolVeRef[]) bits;
    }

    /** Converts this IntVe to a HashMap<Ve, Integer>. */
    public HashMap<Ve, Integer> decode(Medium m) {
        HashMap<Ve, Integer> res = new HashMap<>();
        decode(this, res, m.ves, BoolVe::decode);
        return res;
    }

    @Override
    public IntVe copy() {
        IntVe copy = new IntVe(n);
        copy(this, copy);
        return copy;
    }

    @Override
    public IntVe cache() {
        return new IntVe(n, (BoolVeRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
