package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolField;

/** Represents a reference to a BoolField value */
public class BoolFieldRef extends Ref<BoolField> {
    private BoolField field;
    
    public BoolFieldRef(){}
    public BoolFieldRef(BoolField field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolField value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolField get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFieldRef copy() { 
        if (field != null) return new BoolFieldRef(field.copy());
        return new BoolFieldRef();
    }
}
