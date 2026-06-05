package language.fieldRef.boolField;

import language.fieldRef.Ref;

import language.field.boolField.BoolF;

/** Represents a reference to a BoolF value */
public class BoolFRef extends Ref<BoolF> {
    private BoolF field;
    
    public BoolFRef(){}
    public BoolFRef(BoolF field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(BoolF value) { field = value; }

    /** @return the referenced value. */
    @Override
    public BoolF get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public BoolFRef copy() { 
        if (field != null) return new BoolFRef(field.copy());
        return new BoolFRef();
    }
}
