package language.field.intField;

import language.field.boolField.BoolEv;
import language.fieldRef.boolField.BoolEvRef;
import medium.Medium;
import medium.locusT.Ev;

import java.util.Arrays;
import java.util.HashMap;

/** IntEv represents an integer language.field on Ev loci. */
public non-sealed class IntEv extends IntField<BoolEv> {
    public IntEv(int n) {
        super(n, new BoolEvRef[n + 1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolEvRef();
    }
    public IntEv(int n, BoolEvRef[] bits) {
        super(n, bits);
    }

    public static IntEv of(int value, int n) {
        IntEv intEv = new IntEv(n);
        of(intEv, value, BoolEv::zeroes, BoolEv::ones);
        return intEv;
    }

    public static IntEv maxValue(int n) {
        IntEv intEv = new IntEv(n);
        maxValue(intEv, BoolEv::zeroes, BoolEv::ones);
        return intEv;
    }

    public static IntEv minValue(int n) {
        IntEv intEv = new IntEv(n);
        minValue(intEv, BoolEv::zeroes, BoolEv::ones);
        return intEv;
    }

    public static IntEv rand(int n) {
        IntEv rand = new IntEv(n);
        IntField.rand(rand, BoolEv::rand);
        return rand;
    }
    public static IntEv randNonNegative(int n) {
        IntEv rand = new IntEv(n);
        IntField.randNonNegative(rand, BoolEv::rand);
        return rand;
    }

    @Override
    public BoolEvRef[] getBits() {
        return (BoolEvRef[]) bits;
    }

    /** Converts this IntEv to a HashMap<Ev, Integer>. */
    public HashMap<Ev, Integer> decode(Medium m) {
        HashMap<Ev, Integer> res = new HashMap<>();
        decode(this, res, m.evs, BoolEv::decode);
        return res;
    }

    @Override
    public IntEv copy() {
        IntEv copy = new IntEv(n);
        copy(this, copy);
        return copy;
    }

    @Override
    public IntEv cache() {
        return new IntEv(n, (BoolEvRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
