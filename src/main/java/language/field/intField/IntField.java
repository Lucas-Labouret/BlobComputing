package language.field.intField;

import language.field.Field;
import language.fieldRef.Ref;
import language.field.boolField.BoolField;
import language.utils.BoolFieldManager;
import language.utils.Border;

public sealed abstract class IntField<F extends BoolField> extends Field permits IntV, IntVe, IntVf, IntE, IntEv, IntEf, IntF, IntFv, IntFe {
    public final int n;
    public final Border border;
    protected final Ref<F>[] bits;

    /** @param n number of bits of this IntV, excluding the sign bit */
    protected IntField(int n, Ref<F>[] bits) { this(n, bits, BoolFieldManager.DEFAULT_BORDER()); }
    protected IntField(int n, Ref<F>[] bits, Border border) {
        if (n < 1) throw new IllegalArgumentException("IntV must have at least 1 bit.");
        this.n = n;
        this.bits = bits;
        this.border = border;
    }

    protected interface RandBitFactory<F extends BoolField> { F rand(); }
    protected static <F extends BoolField> void rand(IntField<F> intField, RandBitFactory<F> randBitFactory) {
        for (int i=0; i<= intField.n; i++)
            intField.bits[i].set(randBitFactory.rand());
    }
    protected static <F extends BoolField> void randNonNegative(IntField<F> intField, RandBitFactory<F> randBitFactory) {
        for (int i=1; i<= intField.n; i++)
            intField.bits[i].set(randBitFactory.rand());
    }

    public abstract Ref<F>[] getBits();

    public static <F extends BoolField, R extends Ref<F>> void transpose(R[][] in, R[][] out, int n, int N) {
        for (int i = 0; i <= n; i++) for (int j = 0; j <= N; j++) out[i][j] = in[j][i];
    }

    @Override
    public abstract IntField<F> copy();
}
