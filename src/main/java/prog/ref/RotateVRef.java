package prog.ref;

import language.Ref;

import prog.obj.RotateV;

/** Represents a reference to a RotateV value */
public class RotateVRef extends Ref<RotateV> {
    private RotateV field;

    /** Sets the referenced value. */
    @Override
    public void set(RotateV value) { field = value; }

    /** @return the referenced value. */
    @Override
    public RotateV get() { return field; }

    /** @return a reference to the given RotateV. */
    public static RotateVRef of(RotateV value) {
        RotateVRef ref = new RotateVRef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public RotateVRef copy() { return RotateVRef.of(field.copy()); }
}
