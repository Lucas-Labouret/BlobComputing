package language.fieldRef;

import field.boolField.fieldS.BoolE;
import language.Ref;

/** Represents a mutable reference to a BoolE value. */
public final class BoolERef extends Ref<BoolE> {
    private BoolE field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolE value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolE get() { return field; }

    /** @return a reference to a zero-initialized BoolE. */
    public static BoolERef zeroes() { return BoolERef.of(BoolE.zeroes()); }
    public static BoolERef ones() { return BoolERef.of(BoolE.ones()); }

    /** @return a reference to the given BoolE. */
    public static BoolERef of(BoolE value) {
        BoolERef ref = new BoolERef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public BoolERef copy() { return BoolERef.of(field.copy()); }
}
