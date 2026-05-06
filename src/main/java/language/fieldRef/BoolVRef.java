package language.fieldRef;

import field.boolField.fieldS.BoolV;
import language.Ref;

/** Represents a mutable reference to a BoolV value. */
public final class BoolVRef extends Ref<BoolV> {
    private BoolV field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolV value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolV get() { return field; }

    /** @return a reference to a zero-initialized BoolV. */
    public static BoolVRef zeroes() { return BoolVRef.of(BoolV.zeroes()); }
    public static BoolVRef ones() { return BoolVRef.of(BoolV.ones()); }

    /** @return a reference to the given BoolV. */
    public static BoolVRef of(BoolV value) {
        BoolVRef ref = new BoolVRef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public BoolVRef copy() { return BoolVRef.of(field.copy()); }
}
