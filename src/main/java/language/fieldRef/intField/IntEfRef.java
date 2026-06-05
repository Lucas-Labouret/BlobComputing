package language.fieldRef.intField;

import language.fieldRef.Ref;

import language.field.intField.IntEf;

/** Represents a reference to a IntEf value */
public class IntEfRef extends Ref<IntEf> {
    private IntEf field;
    
    public IntEfRef(){}
    public IntEfRef(IntEf field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(IntEf value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntEf get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntEfRef copy() { 
        if (field != null) return new IntEfRef(field.copy());
        return new IntEfRef();
    }
}
