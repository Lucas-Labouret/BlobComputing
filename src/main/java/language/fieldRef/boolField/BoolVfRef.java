package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolVf;

/** Represents a reference to a BoolVf value */
public class BoolVfRef extends Ref<BoolVf> {
    private BoolVf field;
    
    public BoolVfRef(){}
    public BoolVfRef(BoolVf field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolVf value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolVf get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolVfRef copy() { 
        if (field != null) return new BoolVfRef(field.copy());
        return new BoolVfRef();
    }
}
