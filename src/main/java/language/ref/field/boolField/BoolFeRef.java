package language.ref.field.boolField;

import language.Ref;

import language.obj.field.boolField.BoolFe;

/** Represents a reference to a BoolFe value */
public class BoolFeRef extends Ref<BoolFe> {
    private BoolFe field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolFe value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolFe get() { return field; }

    /** @return a reference to the given BoolFe. */
    public static BoolFeRef of(BoolFe value) {
        BoolFeRef ref = new BoolFeRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFeRef copy() { 
        if (field != null) return BoolFeRef.of(field.copy());
        return new BoolFeRef();
    }
}
