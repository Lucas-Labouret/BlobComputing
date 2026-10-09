package language.field.boolField;

import language.field.BoolFieldLine;
import language.field.BoolFieldManager;
import language.field.Border;
import medium.locusS.Vertex;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean field over the vertices. */
public non-sealed class BoolV extends BoolFieldS<BoolV> {
    private static int HEIGHT = -1;
    private static final int SPAN = 1;
    private static final int BREADTH = 1;

    /** Configures the dimensions used by vertex fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        HEIGHT = maxVerticesPerColumn;
    }

    private static BoolV DATA_POS;

    public static void setDataPos(BoolV dataPos) {
        if (DATA_POS != null) throw new IllegalStateException("Data position has already been set.");
        DATA_POS = dataPos;
    }

    private static HashMap<Vertex, Vertex> TORUS;
    private static HashSet<Vertex> CORNERS;

    public static void setBorder(HashMap<Vertex, Vertex> torusBorder, HashSet<Vertex> torusCorners) {
        if (TORUS != null || CORNERS != null) throw new IllegalStateException("Border has already been set.");
        TORUS = torusBorder;
        CORNERS = torusCorners;
    }

    /** Creates a new BoolV. */
    public BoolV() { this(BoolFieldManager.DEFAULT_BORDER()); }
    public BoolV(Border border) { this(border, true); }
    public BoolV(boolean register) { this(BoolFieldManager.DEFAULT_BORDER(), register); }
    public BoolV(Border border, boolean register) { super(HEIGHT, SPAN, BREADTH, border, register); }
    public BoolV(BoolV other) {
        super(HEIGHT, SPAN, BREADTH, other.border);
        for (int i = 0; i < lines.length; i++) lines[i] = new BoolFieldLine(other.lines[i]);
    }

    /** Fills this BoolV with zeros. */
    public BoolV zeroes(){ zeroesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Fills this BoolV with ones. */
    public BoolV ones(){ onesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Randomly fills this BoolV. */
    public BoolV rand(){
        randGeneric(HEIGHT, SPAN, BREADTH, this);

        if (border == Border.TORUS) {
            for (Vertex v : TORUS.keySet()) {
                Vertex torus = TORUS.get(v);
                setBit(torus, getBit(v));
            }

            boolean[] bits = new boolean[4];
            int index = 0;
            for (Vertex v : CORNERS) bits[index++] = getBit(v);
            boolean bit = bits[0] ^ bits[1] ^ bits[2] ^ bits[3];
            for (Vertex v : CORNERS) setBit(v, bit);
        }

        return this;
    }

    /** Sets a bit in this BoolV. */
    public void setBit(int y, int x, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, 0, 0, bit);

        if (this.border == Border.TORUS) {
            for (Vertex v : TORUS.keySet()) {
                Vertex torus = TORUS.get(v);
                if (v.y == y && v.x == x) {
                    setBitGeneric(HEIGHT, SPAN, BREADTH, this, torus.y, torus.x, 0, 0, bit);
                } else if (torus.y == y && torus.x == x) {
                    setBitGeneric(HEIGHT, SPAN, BREADTH, this, v.y, v.x, 0, 0, bit);
                }
            }

            for (Vertex v : CORNERS) {
                if (v.y == y && v.x == x) for (Vertex corner : CORNERS)
                    setBitGeneric(HEIGHT, SPAN, BREADTH, this, corner.y, corner.x, 0, 0, bit);
            }
        }
    }

    /** Sets a bit in this BoolV. */
    public void setBit(Vertex vertex, boolean bit){ setBit(vertex.y, vertex.x, bit); }

    /** Gets a bit in this BoolV */
    public boolean getBit(int y, int x){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, 0, 0);
    }

    /** Gets a bit in this BoolV */
    public boolean getBit(Vertex vertex){
        return getBit(vertex.y, vertex.x);
    }

    /** Decode this BoolV into a HashMap mapping each vertex in the given set to its corresponding bit value in the BoolV. */
    public HashMap<Vertex, Boolean> decode() {
        HashMap<Vertex, Boolean> res = new HashMap<>();
        for (Vertex v : medium.vertices)
            res.put(v, getBit(v));
        return res;
    }

    /** Set this BoolV to the bitwise not of orig. */
    public BoolV not(BoolV orig){
        notGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** Set this BoolV to the bitwise AND of a and b. */
    public BoolV and(BoolV a, BoolV b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::and);
        return this;
    }

    /** Set this BoolV to the bitwise OR of a and b. */
    public BoolV or(BoolV a, BoolV b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::or);
        return this;
    }

    /** Set this BoolV to the bitwise XOR of a and b. */
    public BoolV xor(BoolV a, BoolV b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::xor);
        return this;
    }

    /** @return the OR reduction of the given transfer language.field. */
    public BoolV redOr(BoolVe orig) { orig.redOr(this); return this; }
    /** @return the OR reduction of the given transfer language.field. */
    public BoolV redOr(BoolVf orig) { orig.redOr(this); return this; }

    /** @return the AND reduction of the given transfer language.field. */
    public BoolV redAnd(BoolVe orig) { orig.redAnd(this); return this; }
    /** @return the AND reduction of the given transfer language.field. */
    public BoolV redAnd(BoolVf orig) { orig.redAnd(this); return this; }

    /** @return the XOR reduction of the given transfer language.field. */
    public BoolV redXor(BoolVe orig) { orig.redXor(this); return this; }
    /** @return the XOR reduction of the given transfer language.field. */
    public BoolV redXor(BoolVf orig) { orig.redXor(this); return this; }

    /** @return the stack reduction 0 of the given transfer language.field */
    public BoolV[] redStack0(BoolVe orig) { return orig.redStack0(); }
    /** @return the stack reduction 1 of the given transfer language.field */
    public BoolV[] redStack1(BoolVe orig) { return orig.redStack1(); }
    /** @return the stack reduction 0 of the given transfer language.field */
    public BoolV[] redStack0(BoolVf orig) { return orig.redStack0(); }
    /** @return the stack reduction 1 of the given transfer language.field */
    public BoolV[] redStack1(BoolVf orig) { return orig.redStack1(); }

    /**
     * Two BoolV objects are equal if their existing bits are equal, or they are both empty.
     * @return whether this BoolV is equal to the given object.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolV other)) return false;

        for (int i = 0; i < HEIGHT * SPAN * BREADTH; i++) {
            if (this.lines[i] == null && other.lines[i] == null) continue;
            if (this.lines[i] == null && other.lines[i] != null) return false;
            if (this.lines[i] != null && other.lines[i] == null) return false;

            BoolFieldLine thisLine = BoolFieldLine.and(this.lines[i], DATA_POS.lines[i]);
            BoolFieldLine otherLine = BoolFieldLine.and(other.lines[i], DATA_POS.lines[i]);
            if (!thisLine.equals(otherLine)) return false;
        }

        return true;
    }

    private BoolV copy(boolean register) {
        BoolV newBoolV = new BoolV(border, register);
        for (int i = 0; i < lines.length; i++)
            newBoolV.lines[i] = this.lines[i] == null ? null : this.lines[i].copy();
        return newBoolV;
    }

    /** @return a deep copy of this BoolV. */
    @Override
    public BoolV copy(){ return copy(true); }

    @Override
    public BoolV cache() { return copy(false); }
}
