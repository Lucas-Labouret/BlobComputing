package language.fieldRef.intField;

import language.fieldRef.Ref;

import language.field.intField.IntField;

/** Represents a reference to a IntField value */
public class IntFieldRef extends Ref<IntField> {
    private IntField field;
    
    public IntFieldRef(){}
    public IntFieldRef(IntField field){
        this();
        this.field = field;
    }

    /** Sets the referenced value. */
    @Override
    public void set(IntField value) { field = value; }

    /** @return the referenced value. */
    @Override
    public IntField get() { return field; }

/** @return a reference to a copy of the referenced value. */
    @Override
    public IntFieldRef copy() { 
        if (field != null) return new IntFieldRef(field.copy());
        return new IntFieldRef();
    }
}
