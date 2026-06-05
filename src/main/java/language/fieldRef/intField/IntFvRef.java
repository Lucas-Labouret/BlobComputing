package language.fieldRef.intField;

import language.fieldRef.Ref;

import language.field.intField.IntFv;

/** Represents a reference to a IntFv value */
public class IntFvRef extends Ref<IntFv> {
    private IntFv field;
    
    public IntFvRef(){}
    public IntFvRef(IntFv field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(IntFv value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntFv get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntFvRef copy() { 
        if (field != null) return new IntFvRef(field.copy());
        return new IntFvRef();
    }
}
