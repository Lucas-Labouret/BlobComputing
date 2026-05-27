package language.ref.field.boolField.fieldS;

import language.Ref;

import language.obj.field.boolField.fieldS.BoolV;

/** Represents a reference to a BoolV value */
public class BoolVRef extends Ref<BoolV> {
    private BoolV field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolV value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolV get() { return field; }

    /** @return a reference to the given BoolV. */
    public static BoolVRef of(BoolV value) {
        BoolVRef ref = new BoolVRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolVRef copy() { 
        if (field != null) return BoolVRef.of(field.copy());
        return new BoolVRef();
    }
}
