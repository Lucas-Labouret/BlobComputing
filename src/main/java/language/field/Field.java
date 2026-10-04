package language.field;

import language.field.boolField.BoolField;
import language.field.intField.IntField;

public abstract sealed class Field<F extends Field<F>> permits BoolField, IntField {
    public abstract F copy();
    public abstract void clear();
    public abstract void set(F other);
}
