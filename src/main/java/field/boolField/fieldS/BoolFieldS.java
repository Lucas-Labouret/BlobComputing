package field.boolField.fieldS;

import field.boolField.BoolField;

/** Represents the abstract base type for simplicial boolean fields. */
public abstract class BoolFieldS extends BoolField {
    /** Creates a new simplicial boolean field base instance. */
    protected BoolFieldS(int HEIGHT, int SPAN, int BREADTH) { super(HEIGHT, SPAN, BREADTH); }
}
