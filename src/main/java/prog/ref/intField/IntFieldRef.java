package prog.ref.intField;

import language.Ref;

import prog.obj.intField.IntField;

/** Represents a reference to a IntField value */
public class IntFieldRef extends Ref<IntField> {
    private IntField field;

    /** Sets the referenced value. */
    @Override
    public void set(IntField value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntField get() { return field; }

    /** @return a reference to the given IntField. */
    public static IntFieldRef of(IntField value) {
        IntFieldRef ref = new IntFieldRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntFieldRef copy() { return IntFieldRef.of(field.copy()); }
}
