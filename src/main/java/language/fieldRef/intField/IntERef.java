package language.fieldRef.intField;

import language.fieldRef.Ref;

import language.field.intField.IntE;

/** Represents a reference to a IntE value */
public class IntERef extends Ref<IntE> {
    private IntE field;
    
    public IntERef(){}
    public IntERef(IntE field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(IntE value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntE get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntERef copy() { 
        if (field != null) return new IntERef(field.copy());
        return new IntERef();
    }
}
