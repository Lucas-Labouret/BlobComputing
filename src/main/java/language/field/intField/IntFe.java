package language.field.intField;

import language.field.boolField.BoolFe;
import language.fieldRef.boolField.BoolFeRef;
import medium.Medium;
import medium.locusT.Fe;

import java.util.Arrays;
import java.util.HashMap;

/** IntFe represents an integer language.field on Fe loci. */
public non-sealed class IntFe extends IntField<BoolFe> {
    public IntFe(int n) {
        super(n, new BoolFeRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolFeRef();
    }
    public IntFe(int n, BoolFeRef[] bits) {
        super(n, bits);
    }

    public static IntFe of(int value, int n) {
        IntFe intFe = new IntFe(n);
        of(intFe, value, BoolFe::zeroes, BoolFe::ones);
        return intFe;
    }

    public static IntFe maxValue(int n) {
        IntFe intFe = new IntFe(n);
        maxValue(intFe, BoolFe::zeroes, BoolFe::ones);
        return intFe;
    }

    public static IntFe minValue(int n) {
        IntFe intFe = new IntFe(n);
        minValue(intFe, BoolFe::zeroes, BoolFe::ones);
        return intFe;
    }

    public static IntFe rand(int n) {
        IntFe rand = new IntFe(n);
        IntField.rand(rand, BoolFe::rand);
        return rand;
    }
    public static IntFe randNonNegative(int n) {
        IntFe rand = new IntFe(n);
        IntField.randNonNegative(rand, BoolFe::rand);
        return rand;
    }

    @Override
    public BoolFeRef[] getBits() {
        return (BoolFeRef[]) bits;
    }

    /** Converts this IntFe to a HashMap<Fe, Integer>. */
    public HashMap<Fe, Integer> decode(Medium m) {
        HashMap<Fe, Integer> res = new HashMap<>();
        decode(this, res, m.fes, BoolFe::decode);
        return res;
    }

    @Override
    public IntFe copy() {
        IntFe copy = new IntFe(n);
        copy(this, copy);
        return copy;
    }

    @Override
    public IntFe cache() {
        return new IntFe(n, (BoolFeRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
