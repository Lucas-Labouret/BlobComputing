package language.ref.field.boolField;

import language.Ref;

import language.obj.field.boolField.BoolField;

/** Represents a reference to a BoolField value */
public class BoolFieldRef extends Ref<BoolField> {
    private BoolField field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolField value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolField get() { return field; }

    /** @return a reference to the given BoolField. */
    public static BoolFieldRef of(BoolField value) {
        BoolFieldRef ref = new BoolFieldRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFieldRef copy() { 
        if (field != null) return BoolFieldRef.of(field.copy());
        return new BoolFieldRef();
    }
}
