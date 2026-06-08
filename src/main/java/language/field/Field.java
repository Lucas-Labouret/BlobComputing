package language.field;

import language.field.boolField.BoolField;
import language.field.intField.IntField;

public abstract sealed class Field permits BoolField, IntField {
    public abstract Field copy();
    public abstract Field cache();
}
