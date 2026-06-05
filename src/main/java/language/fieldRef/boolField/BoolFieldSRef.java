package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolFieldS;

/** Represents a reference to a BoolFieldS value */
public class BoolFieldSRef extends Ref<BoolFieldS> {
    private BoolFieldS field;
    
    public BoolFieldSRef(){}
    public BoolFieldSRef(BoolFieldS field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolFieldS value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolFieldS get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFieldSRef copy() { 
        if (field != null) return new BoolFieldSRef(field.copy());
        return new BoolFieldSRef();
    }
}
