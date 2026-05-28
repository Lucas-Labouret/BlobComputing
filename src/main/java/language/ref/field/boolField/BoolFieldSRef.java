package language.ref.field.boolField;

import language.Ref;

import language.obj.field.boolField.BoolFieldS;

/** Represents a reference to a BoolFieldS value */
public class BoolFieldSRef extends Ref<BoolFieldS> {
    private BoolFieldS field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolFieldS value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolFieldS get() { return field; }

    /** @return a reference to the given BoolFieldS. */
    public static BoolFieldSRef of(BoolFieldS value) {
        BoolFieldSRef ref = new BoolFieldSRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFieldSRef copy() { 
        if (field != null) return BoolFieldSRef.of(field.copy());
        return new BoolFieldSRef();
    }
}
