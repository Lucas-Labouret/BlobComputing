package language.fieldRef;

import field.boolField.fieldT.BoolFv;
import language.Ref;

/** Represents a mutable reference to a BoolFv value. */
public final class BoolFvRef extends Ref<BoolFv> {
    private BoolFv field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolFv value) { field = value;}

    /** @return the referenced value. */
    @Override
    public BoolFv get() { return field; }

    /** @return a reference to a zero-initialized BoolFv. */
    public static BoolFvRef zeroes() { return BoolFvRef.of(BoolFv.zeroes()); }
    public static BoolFvRef ones() { return BoolFvRef.of(BoolFv.ones()); }

    /** @return a reference to the given BoolFv. */
    public static BoolFvRef of(BoolFv value) {
        BoolFvRef ref = new BoolFvRef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFvRef copy() { return BoolFvRef.of(field.copy()); }
}
