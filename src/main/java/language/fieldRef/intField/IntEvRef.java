package language.fieldRef.intField;

import language.fieldRef.Ref;

import language.field.intField.IntEv;

/** Represents a reference to a IntEv value */
public class IntEvRef extends Ref<IntEv> {
    private IntEv field;
    
    public IntEvRef(){}
    public IntEvRef(IntEv field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(IntEv value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntEv get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntEvRef copy() { 
        if (field != null) return new IntEvRef(field.copy());
        return new IntEvRef();
    }
}
