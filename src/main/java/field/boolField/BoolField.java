package field.boolField;

import language.Obj;

/** Represents the abstract base type for boolean fields backed by a BoolFieldLine array. */
public abstract class BoolField extends Obj {
    public final BoolFieldLine[] lines;

    /** Creates a new boolean field base instance. */
    protected BoolField(int HEIGHT, int SPAN, int BREADTH) {
        if(HEIGHT < 1 || SPAN < 1 || BREADTH < 1) throw new IllegalStateException("All dimensions must be positive.");
        lines = new BoolFieldLine[HEIGHT * SPAN * BREADTH];
    }

    /** Fills the target field with zero-valued lines. */
    protected static void zeroesGeneric(int HEIGHT, int SPAN, int BREADTH,
                                        BoolField res) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            res.lines[(i * SPAN + j) * BREADTH + k] = BoolFieldLine.zeroes();
    }

    /** Fills the target field with one-valued lines. */
    protected static void onesGeneric(int HEIGHT, int SPAN, int BREADTH,
                                      BoolField res) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            res.lines[(i * SPAN + j) * BREADTH + k] = BoolFieldLine.ones();
    }

    /** Fills the target field with randomly initialized lines. */
    protected static void randGeneric(int HEIGHT, int SPAN, int BREADTH,
                                      BoolField res) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            res.lines[(i * SPAN + j) * BREADTH + k] = BoolFieldLine.rand();
    }

    /** Sets a bit in the target field at the given coordinates. */
    protected static void setBitGeneric(int HEIGHT, int SPAN, int BREADTH,
                                        BoolField field,
                                        int y, int x, int t, int s,
                                        boolean bit) {
        if (y >= HEIGHT || t >= SPAN || s >= BREADTH)
            throw new IllegalArgumentException("Bit coordinates out of bounds: ("+y+", "+t+", "+s+") for dimensions ("+HEIGHT+", "+SPAN+", "+BREADTH+").");
        int lineIndex = (y * SPAN + t) * BREADTH + s;
        BoolFieldLine.setBit(field.lines[lineIndex], x, bit);
    }

    /** Gets a bit in the target field at the given coordinates. */
    protected static boolean getBitGeneric(int HEIGHT, int SPAN, int BREADTH,
                                           BoolField field,
                                           int y, int x, int t, int s) {
        if (y >= HEIGHT || t >= SPAN || s >= BREADTH)
            throw new IllegalArgumentException("Bit coordinates out of bounds: ("+y+", "+t+", "+s+") for dimensions ("+HEIGHT+", "+SPAN+", "+BREADTH+").");
        int lineIndex = (y * SPAN + t) * BREADTH + s;
        return BoolFieldLine.getBit(field.lines[lineIndex], x);
    }

    /** Writes the bitwise complement of the simplicial field into the result field. */
    protected static void notGeneric(int HEIGHT, int SPAN, int BREADTH,
                                     BoolField orig, BoolField res) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.not(orig.lines[index]);
        }
    }

    /** Writes the bitwise AND of the simplicial fields into the result field. */
    protected static void andGeneric(int HEIGHT, int SPAN, int BREADTH,
                                     BoolField a, BoolField b, BoolField res) {
        if(a.getClass() != b.getClass() || a.getClass() != res.getClass()) throw new IllegalArgumentException("All fields must be of the same type.");
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.and(a.lines[index], b.lines[index]);
        }
    }

    /** Writes the bitwise OR of the simplicial fields into the result field. */
    protected static void orGeneric(int HEIGHT, int SPAN, int BREADTH,
                                    BoolField a, BoolField b, BoolField res) {
        if(a.getClass() != b.getClass() || a.getClass() != res.getClass()) throw new IllegalArgumentException("All fields must be of the same type.");
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.or(a.lines[index], b.lines[index]);
        }
    }

    /** Writes the bitwise XOR of the simplicial fields into the result field. */
    protected static void xorGeneric(int HEIGHT, int SPAN, int BREADTH,
                                     BoolField a, BoolField b, BoolField res) {
        if(a.getClass() != b.getClass() || a.getClass() != res.getClass()) throw new IllegalArgumentException("All fields must be of the same type.");
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.xor(a.lines[index], b.lines[index]);
        }
    }

    /** Writes a left-shifted copy of the simplicial field into the result field. */
    protected static void lShiftGeneric(int HEIGHT, int SPAN, int BREADTH,
                                        BoolField orig, int n, BoolField res){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.lShift(orig.lines[index], n);
        }
    }

    /** Writes a right-shifted copy of the simplicial field into the result field. */
    protected static void rShiftGeneric(int HEIGHT, int SPAN, int BREADTH,
                                        BoolField orig, int n, BoolField res){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            res.lines[index] = BoolFieldLine.rShift(orig.lines[index], n);
        }
    }

    /** Writes an up-shifted copy of the simplicial field into the result field. */
    protected static void uShiftGeneric(int HEIGHT, int SPAN, int BREADTH,
                                        BoolField orig, int n, BoolField res){
        for (int index = 0; index < HEIGHT * SPAN * BREADTH; index++) {
            int sourceIndex = index + n;
            if (sourceIndex < HEIGHT * SPAN * BREADTH) res.lines[index] = new BoolFieldLine(orig.lines[sourceIndex]);
            else res.lines[index] = BoolFieldLine.zeroes();
        }
    }

    /** Writes a down-shifted copy of the simplicial field into the result field. */
    protected static void dShiftGeneric(int HEIGHT, int SPAN, int BREADTH,
                                        BoolField orig, int n, BoolField res){
        for (int index = 0; index < HEIGHT * SPAN * BREADTH; index++) {
            int sourceIndex = index - n;
            if (sourceIndex >= 0) res.lines[index] = new BoolFieldLine(orig.lines[sourceIndex]);
            else res.lines[index] = BoolFieldLine.zeroes();
        }
    }

    public static String toString(BoolField field) {
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
    public abstract BoolField copy();
}
