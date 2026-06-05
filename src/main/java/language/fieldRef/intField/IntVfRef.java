package language.fieldRef.intField;

import language.fieldRef.Ref;

import language.field.intField.IntVf;

/** Represents a reference to a IntVf value */
public class IntVfRef extends Ref<IntVf> {
    private IntVf field;
    
    public IntVfRef(){}
    public IntVfRef(IntVf field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(IntVf value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntVf get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntVfRef copy() { 
        if (field != null) return new IntVfRef(field.copy());
        return new IntVfRef();
    }
}
