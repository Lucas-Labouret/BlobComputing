package language.ref;

import language.Ref;

import language.obj.GrowV;

/** Represents a reference to a GrowV value */
public class GrowVRef extends Ref<GrowV> {
    private GrowV field;

    /** Sets the referenced value. */
    @Override
    public void set(GrowV value) { field = value; }

    /** @return the referenced value. */
    @Override
    public GrowV get() { return field; }

    /** @return a reference to the given GrowV. */
    public static GrowVRef of(GrowV value) {
        GrowVRef ref = new GrowVRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public GrowVRef copy() { 
        if (field != null) return GrowVRef.of(field.copy());
        return new GrowVRef();
    }
}
