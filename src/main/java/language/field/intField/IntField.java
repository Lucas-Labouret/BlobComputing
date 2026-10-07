package language.field.intField;

import language.field.Field;
import language.field.boolField.BoolField;
import medium.Locus;

import java.util.HashSet;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Base class for all integer fields.
 * An integer field is represented as an array of boolean fields, where each boolean field represents a bit of the integer.
 * <p>
 * Integer are signed and represented in two's complement. The first bit is the sign bit, and the rest are the value bits.
 * The size n of the integer field is the number of value bits, excluding the sign bit.
 *
 * @param <B> The type of the boolean field used to represent the bits of the integer field.
 *            For example, an IntV (integer field over the vertices) uses BoolV (boolean field over the vertices) as its boolean field type.
 * @param <I> The type of the integer field.
 */
public sealed abstract class IntField<B extends BoolField<B>, I extends IntField<B, I>> extends Field<I> permits IntV, IntVe, IntVf, IntE, IntEv, IntEf, IntF, IntFv, IntFe {
    /** The number of value bits in the integer field. The total number of bits is n + 1, including the sign bit. */
    public final int n;
    /** The array of boolean fields representing the bits of the integer field. The first element is the sign bit, and the rest are the value bits. */
    public final B[] bits;
    /** @return the array of bits of this field. */
    public abstract B[] getBits();

    protected IntField(int n, B[] bits) {
        if (n < 1) throw new IllegalArgumentException("IntV must have at least 1 bit.");
        this.n = n;
        this.bits = bits;
    }

    /**
     * Sets the value of res to the given value everywhere.
     * @param res the integer field that will be set to the given value
     * @param value the value to set
     * @param zeroes a supplier that returns a bit set to zero with the correct domain (e.g., a BoolV for an IntV)
     * @param ones a supplier that returns a bit set to one with the correct domain (e.g., a BoolV for an IntV)
     * @param <B> the type of the bits of the integer field
     * @param <I> the type of the integer field
     */
    protected static <B extends BoolField<B>, I extends IntField<B, I>>
    void of(IntField<B, I> res, int value, Supplier<B> zeroes, Supplier<B> ones) {
        final int oVal = value;
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i+1 > res.n) throw new IllegalArgumentException("Value "+ oVal +" cannot be represented in " + res.n + " bits.");
            res.bits[res.n - i].set((value & 1) == 0 ? zeroes.get() : ones.get());
            value = value >> 1;
        }
        res.bits[0].set(value >>> 31 == 0 ? zeroes.get() : ones.get());

        for (int i = 1; i <= res.n; i++) if (res.bits[i].lines[0] == null) res.bits[i].set(zeroes.get());
    }

    /** Sets the value of res to the maximum value representable in the given number of bits. */
    protected static <B extends BoolField<B>, I extends IntField<B, I>>
    void maxValue(IntField<B, I> res, Supplier<B> zeroes, Supplier<B> ones) {
        res.bits[0].set(zeroes.get());
        for (int i=1; i<= res.n; i++) res.bits[i].set(ones.get());
    }

    /** Sets the value of res to the minimum value representable in the given number of bits. */
    protected static <B extends BoolField<B>, I extends IntField<B, I>>
    void minValue(IntField<B, I> res, Supplier<B> zeroes, Supplier<B> ones) {
        res.bits[0].set(ones.get());
        for (int i=1; i<= res.n; i++) res.bits[i].set(zeroes.get());
    }

    /** Sets the value of res to a random value representable in the given number of bits. */
    protected static <B extends BoolField<B>, I extends IntField<B, I>>
    void rand(IntField<B, I> intField, Supplier<B> randBitFactory) {
        for (int i=0; i<= intField.n; i++)
            intField.bits[i].set(randBitFactory.get());
    }
    /** Sets the value of res to a random non-negative value representable in the given number of bits. */
    protected static <B extends BoolField<B>, I extends IntField<B, I>>
    void randNonNegative(IntField<B, I> intField, Supplier<B> randBitFactory) {
        for (int i=1; i<= intField.n; i++)
            intField.bits[i].set(randBitFactory.get());
    }

    /** Indicates whether the integer field should be decoded as signed or unsigned. */
    protected boolean decodeAsSigned = true;
    /** Decode the integer field into a Hashmap of loci to integer values. */
    protected static <L extends Locus, B extends BoolField<B>, I extends IntField<B, I>>
    void decode(IntField<B, I> field, Map<L, Integer> res, HashSet<L> loci, BiFunction<HashSet<L>, B, Map<L, Boolean>> bitDecoder, boolean asSigned) {
        if (field.n > 31) throw new RuntimeException("Cannot represent a >31 bits IntV as a Java int");
        for (L locus: loci) res.put(locus, 0);
        for (int i = 1; i <= field.n; i++) {
            Map<L, Boolean> bitMap = bitDecoder.apply(loci, field.bits[i]);
            final int pos = field.n - i;
            res.replaceAll((v, val) -> {
                if (bitMap.get(v)) return val | (1 << (pos));
                else return val;
            });
        }

        Map<L, Boolean> bitMap = bitDecoder.apply(loci, field.bits[0]);

        if (asSigned) res.replaceAll((v, val) -> {
            if (bitMap.get(v))
                for (int i = field.n; i < 32; i++) val |= (1 << i);
            return val;
        }); else res.replaceAll((v, val) -> {
            if (bitMap.get(v)) return val | (1 << field.n);
            else return val;
        });
    }

    /** Transposes a 2D array of boolean fields. */
    public static <F extends BoolField<F>, R extends F> void transpose(R[][] in, R[][] out, int I, int J) {
        for (int i = 0; i <= I; i++) for (int j = 0; j <= J; j++) out[i][j] = in[j][i];
    }

    @Override
    public abstract I copy();

    protected static <B extends BoolField<B>, I extends IntField<B, I>>
    void copy(I from, I to) {
        for (int i = 0; i <= from.n; i++) to.bits[i].set(from.bits[i]);
    }

    @Override
    public void clear() {
        for (int i = 0; i <= n; i++) bits[i].clear();
    }

    @Override
    public void set(I other) {
        for (int i = 0; i <= n; i++) bits[i].set(other.bits[i]);
    }
}
