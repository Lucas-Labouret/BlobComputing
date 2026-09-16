package language.field.intField;

import language.field.Field;
import language.field.boolField.BoolField;
import language.fieldRef.Ref;
import medium.Locus;

import java.util.HashSet;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public sealed abstract class IntField<F extends BoolField> extends Field permits IntV, IntVe, IntVf, IntE, IntEv, IntEf, IntF, IntFv, IntFe {
    public final int n;
    protected final Ref<F>[] bits;

    /** @param n number of bits of this IntV, excluding the sign bit */
    protected IntField(int n, Ref<F>[] bits) {
        if (n < 1) throw new IllegalArgumentException("IntV must have at least 1 bit.");
        this.n = n;
        this.bits = bits;
    }

    protected static <B extends BoolField>
    void of(IntField<B> res, int value, Supplier<B> zeroes, Supplier<B> ones) {
        final int oVal = value;
        for (int i = 0; value != 0 && value != Integer.MIN_VALUE; i++) {
            if (i+1 > res.n) throw new IllegalArgumentException("Value "+ oVal +" cannot be represented in " + res.n + " bits.");
            res.bits[res.n - i].set((value & 1) == 0 ? zeroes.get() : ones.get());
            value = value >> 1;
        }
        res.bits[0].set(value >>> 31 == 0 ? zeroes.get() : ones.get());

        for  (int i = 1; i <= res.n; i++) if (res.bits[i].get() == null) res.bits[i].set(zeroes.get());
    }

    protected static <B extends BoolField>
    void maxValue(IntField<B> res, Supplier<B> zeroes, Supplier<B> ones) {
        res.bits[0].set(zeroes.get());
        for (int i=1; i<= res.n; i++) res.bits[i].set(ones.get());
    }

    protected static <B extends BoolField>
    void minValue(IntField<B> res, Supplier<B> zeroes, Supplier<B> ones) {
        res.bits[0].set(ones.get());
        for (int i=1; i<= res.n; i++) res.bits[i].set(zeroes.get());
    }

    protected static <F extends BoolField>
    void rand(IntField<F> intField, Supplier<F> randBitFactory) {
        for (int i=0; i<= intField.n; i++)
            intField.bits[i].set(randBitFactory.get());
    }
    protected static <F extends BoolField>
    void randNonNegative(IntField<F> intField, Supplier<F> randBitFactory) {
        for (int i=1; i<= intField.n; i++)
            intField.bits[i].set(randBitFactory.get());
    }

    public abstract Ref<F>[] getBits();

    protected boolean decodeAsSigned = true;
    protected static <L extends Locus, B extends BoolField>
    void decode(IntField<B> field, Map<L, Integer> res, HashSet<L> loci, BiFunction<HashSet<L>, B, Map<L, Boolean>> bitDecoder, boolean asSigned) {
        if (field.n > 31) throw new RuntimeException("Cannot represent a >31 bits IntV as a Java int");
        for (L locus: loci) res.put(locus, 0);
        for (int i = 1; i <= field.n; i++) {
            Map<L, Boolean> bitMap = bitDecoder.apply(loci, field.bits[i].get());
            final int pos = field.n - i;
            res.replaceAll((v, val) -> {
                if (bitMap.get(v)) return val | (1 << (pos));
                else return val;
            });
        }

        Map<L, Boolean> bitMap = bitDecoder.apply(loci, field.bits[0].get());

        if (asSigned) res.replaceAll((v, val) -> {
            if (bitMap.get(v))
                for (int i = field.n; i < 32; i++) val |= (1 << i);
            return val;
        }); else res.replaceAll((v, val) -> {
            if (bitMap.get(v)) return val | (1 << field.n);
            else return val;
        });
    }

    public static <F extends BoolField, R extends Ref<F>> void transpose(R[][] in, R[][] out, int n, int N) {
        for (int i = 0; i <= n; i++) for (int j = 0; j <= N; j++) out[i][j] = in[j][i];
    }

    @Override
    public abstract IntField<F> copy();
    @SuppressWarnings("unchecked")
    protected static <B extends BoolField, I extends IntField<B>> void copy(I from, I to) {
        for (int i = 0; i <= from.n; i++) to.bits[i].set(from.bits[i].get() == null ? null : (B) from.bits[i].get().copy());
    }
}
