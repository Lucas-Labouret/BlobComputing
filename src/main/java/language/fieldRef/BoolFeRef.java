package language.fieldRef;

import field.boolField.fieldT.BoolFe;
import language.Ref;

/** Represents a mutable reference to a BoolFe value. */
public final class BoolFeRef extends Ref<BoolFe> {
    private BoolFe field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolFe value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolFe get() { return field; }

    /** @return a reference to a zero-initialized BoolFe. */
    public static BoolFeRef zeroes() { return BoolFeRef.of(BoolFe.zeroes()); }
    public static BoolFeRef ones() { return BoolFeRef.of(BoolFe.ones()); }

    /** @return a reference to the given BoolFe. */
    public static BoolFeRef of(BoolFe value) {
        BoolFeRef ref = new BoolFeRef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFeRef copy() { return BoolFeRef.of(field.copy()); }
}
