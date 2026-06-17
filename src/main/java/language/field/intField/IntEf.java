package language.field.intField;

import language.field.boolField.BoolEf;
import language.fieldRef.boolField.BoolEfRef;
import medium.Medium;
import medium.locusT.Ef;

import java.util.Arrays;
import java.util.HashMap;

/** IntEf represents an integer language.field on Ef loci. */
public non-sealed class IntEf extends IntField<BoolEf> {
    public IntEf(int n) {
        super(n, new BoolEfRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolEfRef();
    }
    private IntEf(int n, BoolEfRef[] bits) {
        super(n, bits);
    }

    public static IntEf of(int value, int n) {
        IntEf intEf = new IntEf(n);
        of(intEf, value, BoolEf::zeroes, BoolEf::ones);
        return intEf;
    }

    public static IntEf maxValue(int n) {
        IntEf intEf = new IntEf(n);
        maxValue(intEf, BoolEf::zeroes, BoolEf::ones);
        return intEf;
    }

    public static IntEf minValue(int n) {
        IntEf intEf = new IntEf(n);
        minValue(intEf, BoolEf::zeroes, BoolEf::ones);
        return intEf;
    }

    public static IntEf rand(int n) {
        IntEf rand = new IntEf(n);
        IntField.rand(rand, BoolEf::rand);
        return rand;
    }
    public static IntEf randNonNegative(int n) {
        IntEf rand = new IntEf(n);
        IntField.randNonNegative(rand, BoolEf::rand);
        return rand;
    }

    @Override
    public BoolEfRef[] getBits() {
        return (BoolEfRef[]) bits;
    }

    /** Converts this IntEf to a HashMap<Ef, Integer>. */
    public HashMap<Ef, Integer> decode(Medium m) {
        HashMap<Ef, Integer> res = new HashMap<>();
        decode(this, res, m.efs, BoolEf::decode);
        return res;
    }

    @Override
    public IntEf copy() {
        IntEf copy = new IntEf(n);
        copy(this, copy);
        return copy;
    }

    @Override
    public IntEf cache() {
        return new IntEf(n, (BoolEfRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
