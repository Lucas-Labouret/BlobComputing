package language.ref.field.boolField;

import language.Ref;

import language.obj.field.boolField.BoolFieldT;

/** Represents a reference to a BoolFieldT value */
public class BoolFieldTRef extends Ref<BoolFieldT> {
    private BoolFieldT field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolFieldT value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolFieldT get() { return field; }

    /** @return a reference to the given BoolFieldT. */
    public static BoolFieldTRef of(BoolFieldT value) {
        BoolFieldTRef ref = new BoolFieldTRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFieldTRef copy() { 
        if (field != null) return BoolFieldTRef.of(field.copy());
        return new BoolFieldTRef();
    }
}
