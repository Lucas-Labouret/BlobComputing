package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolE;

/** Represents a reference to a BoolE value */
public class BoolERef extends Ref<BoolE> {
    private BoolE field;
    
    public BoolERef(){}
    public BoolERef(BoolE field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolE value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolE get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolERef copy() { 
        if (field != null) return new BoolERef(field.copy());
        return new BoolERef();
    }
}
