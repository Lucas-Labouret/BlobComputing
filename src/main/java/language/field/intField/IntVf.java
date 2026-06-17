package language.field.intField;

import language.field.boolField.BoolVf;
import language.fieldRef.boolField.BoolVfRef;
import language.utils.Border;
import medium.Medium;
import medium.locusT.Vf;

import java.util.Arrays;
import java.util.HashMap;

/** IntVf represents an integer language.field on Vf loci. */
public non-sealed class IntVf extends IntField<BoolVf> {
    public IntVf(int n) {
        super(n, new BoolVfRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolVfRef();
    }
    private IntVf(int n, BoolVfRef[] bits) {
        super(n, bits);
    }

    public static IntVf of(int value, int n) {
        IntVf intVf = new IntVf(n);
        of(intVf, value, BoolVf::zeroes, BoolVf::ones);
        return intVf;
    }

    public static IntVf maxValue(int n) {
        IntVf intVf = new IntVf(n);
        maxValue(intVf, BoolVf::zeroes, BoolVf::ones);
        return intVf;
    }

    public static IntVf minValue(int n) {
        IntVf intVf = new IntVf(n);
        minValue(intVf, BoolVf::zeroes, BoolVf::ones);
        return intVf;
    }

    public static IntVf rand(int n) {
        IntVf rand = new IntVf(n);
        IntField.rand(rand, BoolVf::rand);
        return rand;
    }
    public static IntVf randNonNegative(int n) {
        IntVf rand = new IntVf(n);
        IntField.randNonNegative(rand, BoolVf::rand);
        return rand;
    }

    @Override
    public BoolVfRef[] getBits() {
        return (BoolVfRef[]) bits;
    }

    /** Converts this IntVf to a HashMap<Vf, Integer>. */
    public HashMap<Vf, Integer> decode(Medium m) {
        HashMap<Vf, Integer> res = new HashMap<>();
        decode(this, res, m.vfs, BoolVf::decode);
        return res;
    }

    @Override
    public IntVf copy() {
        IntVf copy = new IntVf(n);
        copy(this, copy);
        return copy;
    }
    
    @Override
    public IntVf cache() {
        return new IntVf(n, (BoolVfRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
