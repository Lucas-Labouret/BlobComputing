package language.fieldRef.intField;

import language.fieldRef.Ref;

import language.field.intField.IntVe;

/** Represents a reference to a IntVe value */
public class IntVeRef extends Ref<IntVe> {
    private IntVe field;
    
    public IntVeRef(){}
    public IntVeRef(IntVe field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(IntVe value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntVe get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntVeRef copy() { 
        if (field != null) return new IntVeRef(field.copy());
        return new IntVeRef();
    }
}
