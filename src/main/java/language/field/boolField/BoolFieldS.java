package language.field.boolField;

import language.field.Border;

/**
 * Represents the abstract base type for simplicial boolean fields.<br>
 * A simplicial field is a field on the Vertices, Edges, or Faces.
 */
public abstract sealed class BoolFieldS<F extends BoolFieldS<F>> extends BoolField<F> permits BoolV, BoolE, BoolF {
    /**
     * Creates a new boolean simplicial field base instance.
     * @param HEIGHT the height of the field
     * @param SPAN the span of the field
     * @param BREADTH the breadth of the field
     * @param border the border type of the field
     * @param register whether to register the field with the cache
     */
    protected BoolFieldS(int HEIGHT, int SPAN, int BREADTH, Border border, boolean register) {
        super(HEIGHT, SPAN, BREADTH, border, register);
    }

    /**
     * Creates a new boolean simplicial field base instance. This constructor will register the field with the cache.
     * @param HEIGHT the height of the field
     * @param SPAN the span of the field
     * @param BREADTH the breadth of the field
     * @param border the border type of the field
     */
    protected BoolFieldS(int HEIGHT, int SPAN, int BREADTH, Border border) {
        this(HEIGHT, SPAN, BREADTH, border, true);
    }
}
