package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolVe;

/** Represents a reference to a BoolVe value */
public class BoolVeRef extends Ref<BoolVe> {
    private BoolVe field;
    
    public BoolVeRef(){}
    public BoolVeRef(BoolVe field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolVe value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolVe get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolVeRef copy() { 
        if (field != null) return new BoolVeRef(field.copy());
        return new BoolVeRef();
    }
}
