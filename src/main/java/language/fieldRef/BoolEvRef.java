package language.fieldRef;

import field.boolField.fieldT.BoolEv;
import language.Ref;

/** Represents a mutable reference to a BoolEv value. */
public final class BoolEvRef extends Ref<BoolEv> {
    private BoolEv field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolEv value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolEv get() { return field; }

    /** @return a reference to a zero-initialized BoolEv. */
    public static BoolEvRef zeroes() { return BoolEvRef.of(BoolEv.zeroes()); }
    public static BoolEvRef ones() { return BoolEvRef.of(BoolEv.ones()); }

    /** @return a reference to the given BoolEv. */
    public static BoolEvRef of(BoolEv value) {
        BoolEvRef ref = new BoolEvRef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public BoolEvRef copy() { return BoolEvRef.of(field.copy()); }
}
