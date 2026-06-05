package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolEf;

/** Represents a reference to a BoolEf value */
public class BoolEfRef extends Ref<BoolEf> {
    private BoolEf field;
    
    public BoolEfRef(){}
    public BoolEfRef(BoolEf field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolEf value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolEf get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolEfRef copy() { 
        if (field != null) return new BoolEfRef(field.copy());
        return new BoolEfRef();
    }
}
