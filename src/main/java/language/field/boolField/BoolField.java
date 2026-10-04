package language.field.boolField;

import language.cache.Cache;
import language.field.Field;
import language.utils.BoolFieldLine;
import language.utils.Border;
import language.utils.Coord2D;

import java.util.Arrays;

/** Represents the abstract base type for boolean fields backed by a BoolFieldLine array. */
public sealed abstract class BoolField<F extends BoolField<F>> extends Field<F> permits BoolFieldS, BoolFieldT {
    public final BoolFieldLine[] lines;
    public final Border border;

    /** Creates a new boolean language.field base instance. */
    protected BoolField(int HEIGHT, int SPAN, int BREADTH, Border border, boolean register) {
        if(HEIGHT < 1 || SPAN < 1 || BREADTH < 1) throw new IllegalStateException("All dimensions must be positive.");
        lines = new BoolFieldLine[HEIGHT * SPAN * BREADTH];
        this.border = border;

        if (register) {
            System.out.println("Registering " + this.getClass().getSimpleName() + " with Cache.");
            Cache.register(this);
        }
    }

    protected BoolField(int HEIGHT, int SPAN, int BREADTH, Border border) {
        this(HEIGHT, SPAN, BREADTH, border, true);
    }

    /** Fills the target language.field with zero-valued lines. */
    protected static <F extends BoolField<F>>
    void zeroesGeneric(int HEIGHT, int SPAN, int BREADTH, F res) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            res.lines[(i * SPAN + j) * BREADTH + k] = BoolFieldLine.zeroes();
    }

    /** Fills the target language.field with one-valued lines. */
    protected static <F extends BoolField<F>>
    void onesGeneric(int HEIGHT, int SPAN, int BREADTH, F res) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            res.lines[(i * SPAN + j) * BREADTH + k] = BoolFieldLine.ones();
    }

    /** Fills the target language.field with randomly initialized lines. */
    protected static <F extends BoolField<F>>
    void randGeneric(int HEIGHT, int SPAN, int BREADTH, F res) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            res.lines[(i * SPAN + j) * BREADTH + k] = BoolFieldLine.rand();
    }

    /** Sets a bit in the target language.field at the given coordinates. */
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

    /** Gets a bit in the target language.field at the given coordinates. */
    protected static <F extends BoolField<F>>
    boolean getBitGeneric(int HEIGHT, int SPAN, int BREADTH,
                          F field,
                          int y, int x, int t, int s) {
        if (y >= HEIGHT || t >= SPAN || s >= BREADTH)
            throw new IllegalArgumentException("Bit coordinates out of bounds: ("+y+", "+t+", "+s+") for dimensions ("+HEIGHT+", "+SPAN+", "+BREADTH+").");
        int lineIndex = (y * SPAN + t) * BREADTH + s;
        return BoolFieldLine.getBit(field.lines[lineIndex], x);
    }

    /** Writes the bitwise complement of the simplicial language.field into the result language.field. */
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

    /** Writes the bitwise AND of the simplicial fields into the result language.field. */
    protected static <F extends BoolField<F>>
    void andGeneric(int HEIGHT, int SPAN, int BREADTH, F a, F b, F res) {
        validateBorderCompatibility(a, b, res);
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.and(a.lines[index], b.lines[index]);
        }
    }

    /** Writes the bitwise OR of the simplicial fields into the result language.field. */
    protected static <F extends BoolField<F>>
    void orGeneric(int HEIGHT, int SPAN, int BREADTH, F a, F b, F res) {
        validateBorderCompatibility(a, b, res);
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.or(a.lines[index], b.lines[index]);
        }
    }

    /** Writes the bitwise XOR of the simplicial fields into the result language.field. */
    protected static <F extends BoolField<F>>
    void xorGeneric(int HEIGHT, int SPAN, int BREADTH, F a, F b, F res) {
        validateBorderCompatibility(a, b, res);
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.xor(a.lines[index], b.lines[index]);
        }
    }

    /** Writes a left-shifted copy of the simplicial language.field into the result language.field. */
    protected static <F extends BoolField<F>>
    void lShiftGeneric(int HEIGHT, int SPAN, int BREADTH, F orig, int n, F res){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.lShift(orig.lines[index], n);
        }
    }

    /** Writes a right-shifted copy of the simplicial language.field into the result language.field. */
    protected static <F extends BoolField<F>>
    void rShiftGeneric(int HEIGHT, int SPAN, int BREADTH, F orig, int n, F res){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.rShift(orig.lines[index], n);
        }
    }

    /** Writes an up-shifted copy of the simplicial language.field into the result language.field. */
    protected static <F extends BoolField<F>>
    void uShiftGeneric(int HEIGHT, int SPAN, int BREADTH, F orig, int n, F res){
        for (int index = 0; index < HEIGHT * SPAN * BREADTH; index++) {
            int sourceIndex = index + n;
            if (sourceIndex < HEIGHT * SPAN * BREADTH) res.lines[index] = new BoolFieldLine(orig.lines[sourceIndex]);
            else res.lines[index] = BoolFieldLine.zeroes();
        }
    }

    /** Writes a down-shifted copy of the simplicial language.field into the result language.field. */
    protected static <F extends BoolField<F>>
    void dShiftGeneric(int HEIGHT, int SPAN, int BREADTH, F orig, int n, F res){
        for (int index = 0; index < HEIGHT * SPAN * BREADTH; index++) {
            int sourceIndex = index - n;
            if (sourceIndex >= 0) res.lines[index] = new BoolFieldLine(orig.lines[sourceIndex]);
            else res.lines[index] = BoolFieldLine.zeroes();
        }
    }

    protected void setInt(int value, Coord2D coord) {
        lines[coord.y()].setInt(value, coord.x());
    }
    protected int getInt(Coord2D coord) {
        return lines[coord.y()].getInt(coord.x());
    }

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

    /** @return a deep copy of this BoolField. */
    @Override
    public abstract F copy();

    public abstract F cache();

    @Override
    public void set(F other) {
        for (int i = 0; i < lines.length; i++) lines[i] = other.lines[i] == null ? null : other.lines[i].copy();
    }

    @Override
    public void clear() { Arrays.fill(lines, null); }
}
