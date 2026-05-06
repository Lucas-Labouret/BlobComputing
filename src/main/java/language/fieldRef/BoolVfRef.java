package language.fieldRef;

import field.boolField.fieldT.BoolVf;
import language.Ref;

/** Represents a mutable reference to a BoolVf value. */
public final class BoolVfRef extends Ref<BoolVf> {
    private BoolVf field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolVf value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolVf get() { return field; }

    /** @return a reference to a zero-initialized BoolVf. */
    public static BoolVfRef zeroes() { return BoolVfRef.of(BoolVf.zeroes()); }
    public static BoolVfRef ones() { return BoolVfRef.of(BoolVf.ones()); }

    /** @return a reference to the given BoolVf. */
    public static BoolVfRef of(BoolVf value) {
        BoolVfRef ref = new BoolVfRef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public BoolVfRef copy() { return BoolVfRef.of(field.copy()); }
}
