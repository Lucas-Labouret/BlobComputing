package language.ref.field.boolField;

import language.Ref;

import language.obj.field.boolField.BoolVe;

/** Represents a reference to a BoolVe value */
public class BoolVeRef extends Ref<BoolVe> {
    private BoolVe field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolVe value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolVe get() { return field; }

    /** @return a reference to the given BoolVe. */
    public static BoolVeRef of(BoolVe value) {
        BoolVeRef ref = new BoolVeRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolVeRef copy() { 
        if (field != null) return BoolVeRef.of(field.copy());
        return new BoolVeRef();
    }
}
