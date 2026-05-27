package language.ref.field.boolField.fieldT;

import language.Ref;

import language.obj.field.boolField.fieldT.BoolVf;

/** Represents a reference to a BoolVf value */
public class BoolVfRef extends Ref<BoolVf> {
    private BoolVf field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolVf value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolVf get() { return field; }

    /** @return a reference to the given BoolVf. */
    public static BoolVfRef of(BoolVf value) {
        BoolVfRef ref = new BoolVfRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolVfRef copy() { 
        if (field != null) return BoolVfRef.of(field.copy());
        return new BoolVfRef();
    }
}
