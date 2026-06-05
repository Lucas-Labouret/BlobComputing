package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolFieldT;

/** Represents a reference to a BoolFieldT value */
public class BoolFieldTRef extends Ref<BoolFieldT> {
    private BoolFieldT field;
    
    public BoolFieldTRef(){}
    public BoolFieldTRef(BoolFieldT field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolFieldT value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolFieldT get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFieldTRef copy() { 
        if (field != null) return new BoolFieldTRef(field.copy());
        return new BoolFieldTRef();
    }
}
