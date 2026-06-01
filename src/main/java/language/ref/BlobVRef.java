package language.ref;

import language.Ref;

import language.obj.BlobV;

/** Represents a reference to a BlobV value */
public class BlobVRef extends Ref<BlobV> {
    private BlobV field;

    /** Sets the referenced value. */
    @Override
    public void set(BlobV value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BlobV get() { return field; }

    /** @return a reference to the given BlobV. */
    public static BlobVRef of(BlobV value) {
        BlobVRef ref = new BlobVRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BlobVRef copy() { 
        if (field != null) return BlobVRef.of(field.copy());
        return new BlobVRef();
    }
}
