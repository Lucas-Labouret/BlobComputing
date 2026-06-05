package language.fieldRef.intField;

import language.fieldRef.Ref;

import language.field.intField.IntF;

/** Represents a reference to a IntF value */
public class IntFRef extends Ref<IntF> {
    private IntF field;
    
    public IntFRef(){}
    public IntFRef(IntF field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(IntF value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntF get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntFRef copy() { 
        if (field != null) return new IntFRef(field.copy());
        return new IntFRef();
    }
}
