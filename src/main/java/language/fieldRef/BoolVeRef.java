package language.fieldRef;

import field.boolField.fieldT.BoolVe;
import language.Ref;

/** Represents a mutable reference to a BoolVe value. */
public final class BoolVeRef extends Ref<BoolVe> {
    private BoolVe field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolVe value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolVe get() { return field; }

    /** @return a reference to a zero-initialized BoolVe. */
    public static BoolVeRef zeroes() { return BoolVeRef.of(BoolVe.zeroes()); }
    public static BoolVeRef ones() { return BoolVeRef.of(BoolVe.ones()); }

    /** @return a reference to the given BoolVe. */
    public static BoolVeRef of(BoolVe value) {
        BoolVeRef ref = new BoolVeRef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public BoolVeRef copy() { return BoolVeRef.of(field.copy()); }
}
