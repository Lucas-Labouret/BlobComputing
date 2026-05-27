package language.ref.field.boolField.fieldT;

import language.Ref;

import language.obj.field.boolField.fieldT.BoolFv;

/** Represents a reference to a BoolFv value */
public class BoolFvRef extends Ref<BoolFv> {
    private BoolFv field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolFv value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolFv get() { return field; }

    /** @return a reference to the given BoolFv. */
    public static BoolFvRef of(BoolFv value) {
        BoolFvRef ref = new BoolFvRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFvRef copy() { 
        if (field != null) return BoolFvRef.of(field.copy());
        return new BoolFvRef();
    }
}
