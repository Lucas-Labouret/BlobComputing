package language.ref.field.boolField;

import language.Ref;

import language.obj.field.boolField.BoolE;

/** Represents a reference to a BoolE value */
public class BoolERef extends Ref<BoolE> {
    private BoolE field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolE value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolE get() { return field; }

    /** @return a reference to the given BoolE. */
    public static BoolERef of(BoolE value) {
        BoolERef ref = new BoolERef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolERef copy() { 
        if (field != null) return BoolERef.of(field.copy());
        return new BoolERef();
    }
}
