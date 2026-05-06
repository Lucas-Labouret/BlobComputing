package language.fieldRef;

import field.boolField.fieldT.BoolEf;
import language.Ref;

/** Represents a mutable reference to a BoolEf value. */
public final class BoolEfRef extends Ref<BoolEf> {
    private BoolEf field;

    /** Sets the referenced value. */
    @Override
    public void set(BoolEf value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolEf get() { return field; }

    /** @return a reference to a zero-initialized BoolEf. */
    public static BoolEfRef zeroes() { return BoolEfRef.of(BoolEf.zeroes()); }
    public static BoolEfRef ones() { return BoolEfRef.of(BoolEf.ones()); }

    /** @return a reference to the given BoolEf. */
    public static BoolEfRef of(BoolEf value) {
        BoolEfRef ref = new BoolEfRef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public BoolEfRef copy() { return BoolEfRef.of(field.copy()); }
}
