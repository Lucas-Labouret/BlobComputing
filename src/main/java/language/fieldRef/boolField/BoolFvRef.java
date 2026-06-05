package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolFv;

/** Represents a reference to a BoolFv value */
public class BoolFvRef extends Ref<BoolFv> {
    private BoolFv field;
    
    public BoolFvRef(){}
    public BoolFvRef(BoolFv field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolFv value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolFv get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFvRef copy() { 
        if (field != null) return new BoolFvRef(field.copy());
        return new BoolFvRef();
    }
}
