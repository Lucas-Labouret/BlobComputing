package language.ref.agents;

import language.Ref;

import language.obj.agents.Constraint;

/** Represents a reference to a Constraint value */
public class ConstraintRef extends Ref<Constraint> {
    private Constraint field;

    /** Sets the referenced value. */
    @Override
    public void set(Constraint value) { field = value; }

    /** @return the referenced value. */
    @Override
    public Constraint get() { return field; }

    /** @return a reference to the given Constraint. */
    public static ConstraintRef of(Constraint value) {
        ConstraintRef ref = new ConstraintRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public ConstraintRef copy() { 
        if (field != null) return ConstraintRef.of(field.copy());
        return new ConstraintRef();
    }
}
