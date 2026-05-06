package language.fieldRef;

import field.boolField.fieldS.BoolF;
import language.Ref;

/** Represents a mutable reference to a BoolF value. */
public final class BoolFRef extends Ref<BoolF> {
    private BoolF field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolF value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolF get() { return field; }

    /** @return a reference to a zero-initialized BoolF. */
    public static BoolFRef zeroes() { return BoolFRef.of(BoolF.zeroes()); }
    public static BoolFRef ones() { return BoolFRef.of(BoolF.ones()); }

    /** @return a reference to the given BoolF. */
    public static BoolFRef of(BoolF value) {
        BoolFRef ref = new BoolFRef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFRef copy() { return BoolFRef.of(field.copy()); }
}
