package language.field.intField;

import language.field.boolField.BoolFv;
import language.fieldRef.boolField.BoolFvRef;
import medium.Medium;
import medium.locusT.Fv;

import java.util.Arrays;
import java.util.HashMap;

/** IntFv represents an integer language.field on Fv loci. */
public non-sealed class IntFv extends IntField<BoolFv> {
    public IntFv(int n) {
        super(n, new BoolFvRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolFvRef();
    }
    private IntFv(int n, BoolFvRef[] bits) {
        super(n, bits);
    }

    public static IntFv of(int value, int n) {
        IntFv intFv = new IntFv(n);
        of(intFv, value, BoolFv::zeroes, BoolFv::ones);
        return intFv;
    }
    
    public static IntFv maxValue(int n) {
        IntFv intFv = new IntFv(n);
        maxValue(intFv, BoolFv::zeroes, BoolFv::ones);
        return intFv;
    }
    
    public static IntFv minValue(int n) {
        IntFv intFv = new IntFv(n);
        minValue(intFv, BoolFv::zeroes, BoolFv::ones);
        return intFv;
    }

    public static IntFv rand(int n) {
        IntFv rand = new IntFv(n);
        IntField.rand(rand, BoolFv::rand);
        return rand;
    }
    public static IntFv randNonNegative(int n) {
        IntFv rand = new IntFv(n);
        IntField.randNonNegative(rand, BoolFv::rand);
        return rand;
    }

    @Override
    public BoolFvRef[] getBits() {
        return (BoolFvRef[]) bits;
    }

    /** Converts this IntFv to a HashMap<Fv, Integer>. */
    public HashMap<Fv, Integer> decode(Medium m) {
        HashMap<Fv, Integer> res = new HashMap<>();
        decode(this, res, m.fvs, BoolFv::decode);
        return res;
    }

    @Override
    public IntFv copy() {
        IntFv copy = new IntFv(n);
        copy(this, copy);
        return copy;
    }

    @Override
    public IntFv cache() {
        return new IntFv(n, (BoolFvRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
