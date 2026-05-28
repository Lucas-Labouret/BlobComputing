package language.ref.field.boolField;

import language.Ref;

import language.obj.field.boolField.BoolEv;

/** Represents a reference to a BoolEv value */
public class BoolEvRef extends Ref<BoolEv> {
    private BoolEv field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolEv value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolEv get() { return field; }

    /** @return a reference to the given BoolEv. */
    public static BoolEvRef of(BoolEv value) {
        BoolEvRef ref = new BoolEvRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolEvRef copy() { 
        if (field != null) return BoolEvRef.of(field.copy());
        return new BoolEvRef();
    }
}
