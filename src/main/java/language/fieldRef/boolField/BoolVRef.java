package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolV;

/** Represents a reference to a BoolV value */
public class BoolVRef extends Ref<BoolV> {
    private BoolV field;
    
    public BoolVRef(){}
    public BoolVRef(BoolV field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolV value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolV get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolVRef copy() { 
        if (field != null) return new BoolVRef(field.copy());
        return new BoolVRef();
    }
}
