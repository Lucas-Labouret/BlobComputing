package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolFe;

/** Represents a reference to a BoolFe value */
public class BoolFeRef extends Ref<BoolFe> {
    private BoolFe field;
    
    public BoolFeRef(){}
    public BoolFeRef(BoolFe field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolFe value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolFe get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFeRef copy() { 
        if (field != null) return new BoolFeRef(field.copy());
        return new BoolFeRef();
    }
}
