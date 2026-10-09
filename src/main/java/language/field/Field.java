package language.field;

import language.field.boolField.BoolField;
import language.field.intField.IntField;
import medium.Medium;

/**
 * The base class for all fields.
 * A field is a map from the loci  of a given medium of one spatial type, to a set of scalar values of a given type.
 * For example, a BoolV field is a map from the vertices of the medium to the set {true, false}.
 * @param <F> the type of the field.
 */
public abstract sealed class Field<F extends Field<F>> permits BoolField, IntField {
    protected static Medium medium;
    public static void setMedium(Medium medium) {
        if (Field.medium != null) throw new IllegalStateException("Medium has already been set.");
        Field.medium = medium;
    }
    /** @return a deep copy of this field. */
    public abstract F copy();
    /** Copies the state of the given field into this field. */
    public abstract void set(F other);
    /** Wipes the state of this field. */
    public abstract void clear();
}
