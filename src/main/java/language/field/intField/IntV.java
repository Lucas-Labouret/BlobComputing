package language.field.intField;

import language.field.boolField.BoolE;
import language.field.boolField.BoolV;
import language.fieldRef.boolField.BoolVRef;
import language.utils.BoolFieldManager;
import language.utils.Border;
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
    public IntV(int n) { this(n, BoolFieldManager.DEFAULT_BORDER()); }
    public IntV(int n, Border border) {
        super(n, new BoolVRef[n+1]);
        for (int i = 0; i <= n; i++) this.bits[i] = new BoolVRef(BoolV.zeroes(border));
    }
    private IntV(int n, BoolVRef[] bits) {
        super(n, bits);
    }

    public static IntV of(int value, int n) { return of(value, n, BoolFieldManager.DEFAULT_BORDER()); }
    public static IntV of(int value, int n, Border border) {
        IntV intV = new IntV(n, border);
        final int oVal = value;
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i+1 > n) throw new IllegalArgumentException("Value "+ oVal +" cannot be represented in "+ n +" bits.");
            intV.bits[n - i] = (value & 1) == 0 ? new BoolVRef(BoolV.zeroes(border)) : new BoolVRef(BoolV.ones(border));
            value = value >> 1;
        }
        intV.bits[0] = value >>> 31 == 0 ? new BoolVRef(BoolV.zeroes(border)) : new BoolVRef(BoolV.ones(border));
        return intV;
    }
    public static IntV of(BoolVRef boolV, int n) {
        IntV intV = new IntV(n, boolV.get().border);
        for (int i = 1; i < n; i++) intV.bits[i] = new BoolVRef(BoolV.zeroes(boolV.get().border));
        intV.bits[n] = boolV.copy();
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

    public static IntV minValue(int n) {
        IntV intV = new IntV(n);
        intV.bits[0] = new BoolVRef(BoolV.ones(intV.border));
        for (int i = 1; i <= n; i++) intV.bits[i] = new BoolVRef(BoolV.zeroes(intV.border));
        return intV;
    }

    @Override
    public BoolVRef[] getBits() {
        return (BoolVRef[]) bits;
    }

    /** Converts this IntV to a HashMap<Vertex, Integer> by decoding each bit and combining them into an integer. */
    public HashMap<Vertex, Integer> decode(Medium m) {
        if (n > 31) throw new RuntimeException("Cannot represent a >31 bits IntV as a Java int");
        HashMap<Vertex, Integer> res = new HashMap<>();
        for (Vertex v: m.vertices) res.put(v, 0);
        for (int i = 1; i <= n; i++) {
            BoolVRef bit = (BoolVRef) bits[i];
            HashMap<Vertex, Boolean> bitMap = BoolV.decode(m.vertices, bit.get());
            final int pos = n - i;
            res.replaceAll((v, val) -> {
                if (bitMap.get(v)) return val | (1 << (pos));
                else return val;
            });
        }

        BoolVRef bit = (BoolVRef) bits[0];
        HashMap<Vertex, Boolean> bitMap = BoolV.decode(m.vertices, bit.get());
        res.replaceAll((v, val) -> {
            if (bitMap.get(v))
                for (int i = n; i < 32; i++) val |= (1 << i);
            return val;
        });

        return res;
    }

    @Override
    public IntV copy() {
        IntV copy = new IntV(n, border);
        for (int i = 0; i <= n; i++) {
            copy.bits[i] = this.bits[i].copy();
        }
        return copy;
    }

    @Override
    public IntV cache() {
        return new IntV(n, (BoolVRef[]) Arrays.copyOf(this.bits, n+1));
    }
}
