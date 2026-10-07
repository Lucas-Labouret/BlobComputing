package language.field.boolField;

import language.cache.Cache;
import language.field.Field;
import language.field.BoolFieldLine;
import language.field.Border;
import language.field.Coord2D;

import java.util.Arrays;
import java.util.function.BiFunction;

/** Represents the abstract base type for boolean fields backed by a BoolFieldLine array. */
public sealed abstract class BoolField<F extends BoolField<F>> extends Field<F> permits BoolFieldS, BoolFieldT {
    /** The array of BoolFieldLine instances representing the boolean field. */
    public final BoolFieldLine[] lines;
    /** The border type of the boolean field. */
    public final Border border;

    /**
     * Creates a new boolean field base instance.
     * @param HEIGHT the height of the field
     * @param SPAN the span of the field
     * @param BREADTH the breadth of the field
     * @param border the border type of the field
     * @param register whether to register the field with the cache
     */
    protected BoolField(int HEIGHT, int SPAN, int BREADTH, Border border, boolean register) {
        if(HEIGHT < 1 || SPAN < 1 || BREADTH < 1) throw new IllegalStateException("All dimensions must be positive.");
        lines = new BoolFieldLine[HEIGHT * SPAN * BREADTH];
        this.border = border;

        if (register) Cache.register(this);
    }

    /**
     * Creates a new boolean field base instance. This constructor will register the field with the cache.
     * @param HEIGHT the height of the field
     * @param SPAN the span of the field
     * @param BREADTH the breadth of the field
     * @param border the border type of the field
     */
    protected BoolField(int HEIGHT, int SPAN, int BREADTH, Border border) {
        this(HEIGHT, SPAN, BREADTH, border, true);
    }

    /** Fills the target field with zeroes */
    protected static <F extends BoolField<F>>
    void zeroesGeneric(int HEIGHT, int SPAN, int BREADTH, F res) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            res.lines[(i * SPAN + j) * BREADTH + k] = BoolFieldLine.zeroes();
    }

    /** Fills the target field with ones. */
    protected static <F extends BoolField<F>>
    void onesGeneric(int HEIGHT, int SPAN, int BREADTH, F res) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            res.lines[(i * SPAN + j) * BREADTH + k] = BoolFieldLine.ones();
    }

    /** Randomly fills the target field. */
    protected static <F extends BoolField<F>>
    void randGeneric(int HEIGHT, int SPAN, int BREADTH, F res) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            res.lines[(i * SPAN + j) * BREADTH + k] = BoolFieldLine.rand();
    }

    /** Sets a bit in the target field at the given coordinates. */
    protected static <F extends BoolField<F>>
    void setBitGeneric(int HEIGHT, int SPAN, int BREADTH,
                       F field,
                       int y, int x, int t, int s,
                       boolean bit) {
        if (y >= HEIGHT || t >= SPAN || s >= BREADTH)
            throw new IllegalArgumentException("Bit coordinates out of bounds: ("+y+", "+t+", "+s+") for dimensions ("+HEIGHT+", "+SPAN+", "+BREADTH+").");
        int lineIndex = (y * SPAN + t) * BREADTH + s;
        BoolFieldLine.setBit(field.lines[lineIndex], x, bit);
    }

    /**
     * Gets a bit in the target field at the given coordinates.
     * @implNote There are no guarantees that one returned value if the coordinates do not correspond to an existing locus.
     */
    protected static <F extends BoolField<F>>
    boolean getBitGeneric(int HEIGHT, int SPAN, int BREADTH,
                          F field,
                          int y, int x, int t, int s) {
        if (y >= HEIGHT || t >= SPAN || s >= BREADTH)
            throw new IllegalArgumentException("Bit coordinates out of bounds: ("+y+", "+t+", "+s+") for dimensions ("+HEIGHT+", "+SPAN+", "+BREADTH+").");
        int lineIndex = (y * SPAN + t) * BREADTH + s;
        return BoolFieldLine.getBit(field.lines[lineIndex], x);
    }

    /** Writes the bitwise complement of the simplicial field into the result field. */
    protected static <F extends BoolField<F>>
    void notGeneric(int HEIGHT, int SPAN, int BREADTH, F orig, F res) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.not(orig.lines[index]);
        }
    }

    /** Ensures TORUS-first multi-operand operations do not mix with MIRROR operands. */
    protected static void validateBorderCompatibility(BoolField<?> first, BoolField<?>... others) {
        if (first.border == Border.MIRROR) return;
        for (BoolField<?> other : others) if (other.border == Border.MIRROR)
            throw new IllegalArgumentException("A MIRROR cannot be combined into TORUS");
    }

    /** Writes the binop of the fields a and b into the res field. */
    protected static <F extends BoolField<F>>
    void binopGeneric(int HEIGHT, int SPAN, int BREADTH, F a, F b, F res, BiFunction<BoolFieldLine, BoolFieldLine, BoolFieldLine> binop) {
        validateBorderCompatibility(a, b, res);
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = binop.apply(a.lines[index], b.lines[index]);
        }
    }

    /**
     * Place the given integer into the lines of the field at the specified coordinates.
     * Do note that value is used to carry up to 32 boolean values, not as an actual number.
     */
    protected void setInt(int value, Coord2D coord) { lines[coord.y()].setInt(value, coord.x()); }

    /**
     * Retrieves the integer stored in the lines of the field at the specified coordinates.
     * Do note that the returned value is used to carry up to 32 boolean values, not as an actual number.
     */
    protected int getInt(Coord2D coord) { return lines[coord.y()].getInt(coord.x()); }


    public static String toString(BoolField<?> field) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < field.lines.length; i++) {
            sb.append(field.lines[i].toString());
            if (i < field.lines.length - 1) sb.append("\n");
        }
        return sb.toString();
    }

    /** @return the string representation of this BoolField. */
    @Override
    public String toString() {
        return toString(this);
    }

    /**
     * Used to store copies of the field into the cache without storing copies of those copies in the cache,
     * which would lead to an infinite loop of copies.
     * @return a copy of this BoolField that is not registered with the cache.
     */
    public abstract F cache();

    /** Copies the contents of the given BoolField into this BoolField. */
    @Override
    public void set(F other) {
        for (int i = 0; i < lines.length; i++) lines[i] = other.lines[i] == null ? null : other.lines[i].copy();
    }

    /** Clears the contents of this BoolField. */
    @Override
    public void clear() { Arrays.fill(lines, null); }
}
