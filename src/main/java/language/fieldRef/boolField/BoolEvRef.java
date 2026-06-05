package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolEv;

/** Represents a reference to a BoolEv value */
public class BoolEvRef extends Ref<BoolEv> {
    private BoolEv field;
    
    public BoolEvRef(){}
    public BoolEvRef(BoolEv field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolEv value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolEv get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolEvRef copy() { 
        if (field != null) return new BoolEvRef(field.copy());
        return new BoolEvRef();
    }
}
