package language.field.boolField;

import language.utils.Border;

/** Represents the abstract base type for simplicial boolean fields. */
public abstract sealed class BoolFieldS<F extends BoolFieldS<F>> extends BoolField<F> permits BoolV, BoolE, BoolF {
    /** Creates a new simplicial boolean language.field base instance. */
    protected BoolFieldS(int HEIGHT, int SPAN, int BREADTH, Border border, boolean register) {
        super(HEIGHT, SPAN, BREADTH, border, register);
    }

    protected BoolFieldS(int HEIGHT, int SPAN, int BREADTH, Border border) {
        this(HEIGHT, SPAN, BREADTH, border, true);
    }

    public abstract F copy();
}
