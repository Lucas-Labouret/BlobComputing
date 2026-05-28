package language.ref.field.boolField;

import language.Ref;

import language.obj.field.boolField.BoolF;

/** Represents a reference to a BoolF value */
public class BoolFRef extends Ref<BoolF> {
    private BoolF field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolF value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolF get() { return field; }

    /** @return a reference to the given BoolF. */
    public static BoolFRef of(BoolF value) {
        BoolFRef ref = new BoolFRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFRef copy() { 
        if (field != null) return BoolFRef.of(field.copy());
        return new BoolFRef();
    }
}
