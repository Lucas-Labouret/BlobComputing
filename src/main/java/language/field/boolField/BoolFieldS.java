package language.field.boolField;

import language.utils.Border;

/** Represents the abstract base type for simplicial boolean fields. */
public abstract class BoolFieldS extends BoolField {
    /** Creates a new simplicial boolean language.field base instance. */
    protected BoolFieldS(int HEIGHT, int SPAN, int BREADTH, Border border) { super(HEIGHT, SPAN, BREADTH, border); }

    public abstract BoolFieldS copy();
}
