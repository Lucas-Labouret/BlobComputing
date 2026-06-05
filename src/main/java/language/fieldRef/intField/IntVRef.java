package language.fieldRef.intField;

import language.fieldRef.Ref;

import language.field.intField.IntV;

/** Represents a reference to a IntV value */
public class IntVRef extends Ref<IntV> {
    private IntV field;
    
    public IntVRef(){}
    public IntVRef(IntV field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(IntV value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntV get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntVRef copy() { 
        if (field != null) return new IntVRef(field.copy());
        return new IntVRef();
    }
}
