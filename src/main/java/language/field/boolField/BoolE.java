package language.field.boolField;

import language.utils.BoolFieldLine;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.locusS.Edge;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean language.field over edge loci. */
public non-sealed class BoolE extends BoolFieldS<BoolE> {
    private static int HEIGHT = -1;
    private static int SPAN = -1;
    private static final int BREADTH = 1;
    
    /** Configures the dimensions used by edge fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn, int maxBelongingEdgePerVertex) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        if (maxBelongingEdgePerVertex < 1) throw new IllegalArgumentException("There must exist at least one edge per vertex.");
        HEIGHT = maxVerticesPerColumn;
        SPAN = maxBelongingEdgePerVertex;
    }

    private static BoolE DATA_POS;

    public static void setDataPos(BoolE dataPos) {
        if (DATA_POS != null) throw new IllegalStateException("Data position has already been set.");
        DATA_POS = dataPos;
    }

    private static HashMap<Edge, Edge> TORUS;

    public static void setBorder(HashMap<Edge, Edge> torusBorder) {
        if (TORUS != null) throw new IllegalStateException("Border has already been set.");
        TORUS = torusBorder;
    }
    
    /** Creates a new BoolE. */
    public BoolE() { this(BoolFieldManager.DEFAULT_BORDER()); }
    public BoolE(Border border) { this(border, true); }
    public BoolE(boolean register) { this(BoolFieldManager.DEFAULT_BORDER(), register); }
    public BoolE(Border border, boolean register) { super(HEIGHT, SPAN, BREADTH, border, register); }
    public BoolE(BoolE other) {
        super(HEIGHT, SPAN, BREADTH, other.border);
        for (int i = 0; i < lines.length; i++) lines[i] = new BoolFieldLine(other.lines[i]);
    }

    /** Fills this BoolE with zeroes. */
    public BoolE zeroes(){ zeroesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Fills this BoolE with ones. */
    public BoolE ones(){ onesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Randomly fills this BoolE. */
    public BoolE rand(){
        randGeneric(HEIGHT, SPAN, BREADTH, this);

        if (border == Border.TORUS) for (Edge e : TORUS.keySet()) {
            Edge torus = TORUS.get(e);
            setBit(torus, getBit(e));
            return this;
        }

        return this;
    }

    /** Sets a bit in the given BoolE. */
    public void setBit(int y, int x, int t, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, t, 0, bit);

        if (border == Border.TORUS) for (Edge e : TORUS.keySet()) {
            Edge torus = TORUS.get(e);
            if (e.y == y && e.x == x && e.t == t) {
                setBitGeneric(HEIGHT, SPAN, BREADTH, this, torus.y, torus.x, torus.t, 0, bit);
            } else if (torus.y == y && torus.x == x && torus.t == t) {
                setBitGeneric(HEIGHT, SPAN, BREADTH, this, e.y, e.x, e.t, 0, bit);
            }
        }
    }

    /** Sets a bit in the given BoolE. */
    public void setBit(Edge edge, boolean bit){
        setBit(edge.y, edge.x, edge.t, bit);
    }

    /** Gets a bit in the given BoolE */
    public boolean getBit(int[] coord){
        if (coord == null || coord.length != 3) throw new IllegalArgumentException("Coordinates must have three elements.");
        return getBitGeneric(HEIGHT, SPAN, BREADTH, this, coord[0], coord[1], coord[2], 0);
    }

    /** Gets a bit in the given BoolE */
    public boolean getBit(Edge edge){
        return getBit(new int[]{edge.y, edge.x, edge.t});
    }

    /** Decode the given BoolE into a HashMap mapping each edge in the given set to its corresponding bit value in the BoolE. */
    public HashMap<Edge, Boolean> decode(HashSet<Edge> edges) {
        HashMap<Edge, Boolean> res = new HashMap<>();
        for (Edge e : edges)
            res.put(e, getBit(e));
        return res;
    }

    /** @return the bitwise NOT of the given BoolE. */
    public BoolE not(BoolE orig){
        notGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** @return the bitwise AND of the given BoolE values. */
    public BoolE and(BoolE a, BoolE b){
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return the bitwise OR of the given BoolE values. */
    public BoolE or(BoolE a, BoolE b){
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return the bitwise XOR of the given BoolE values. */
    public BoolE xor(BoolE a, BoolE b){
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return a left-shifted BoolE. */
    public BoolE lShift(BoolE orig, int n){
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a right-shifted BoolE. */
    public BoolE rShift(BoolE orig, int n){
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return an up-shifted BoolE. */
    public BoolE uShift(BoolE orig, int n){
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a down-shifted BoolE. */
    public BoolE dShift(BoolE orig, int n){
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return the OR reduction of the given transfer language.field. */
    public BoolE redOr(BoolEv orig) {  orig.redOr(this); return this; }
    /** @return the OR reduction of the given transfer language.field. */
    public BoolE redOr(BoolEf orig) { orig.redOr(this); return this; }

    /** @return the AND reduction of the given transfer language.field. */
    public BoolE redAnd(BoolEv orig) { orig.redAnd(this); return this; }
    /** @return the AND reduction of the given transfer language.field. */
    public BoolE redAnd(BoolEf orig) { orig.redAnd(this); return this; }

    /** @return the XOR reduction of the given transfer language.field. */
    public BoolE redXor(BoolEv orig) { orig.redXor(this); return this; }
    /** @return the XOR reduction of the given transfer language.field. */
    public BoolE redXor(BoolEf orig) { orig.redXor(this); return this; }

    /** @return the stack reduction 0 of the given transfer language.field */
    public BoolE[] redStack0(BoolEv orig) { return orig.redStack0(); }
    /** @return the stack reduction 1 of the given transfer language.field */
    public BoolE[] redStack1(BoolEv orig) { return orig.redStack1(); }
    /** @return the stack reduction 0 of the given transfer language.field */
    public BoolE[] redStack0(BoolEf orig) { return orig.redStack0(); }
    /** @return the stack reduction 1 of the given transfer language.field */
    public BoolE[] redStack1(BoolEf orig) { return orig.redStack1(); }

    /** @return whether this BoolE is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolE other)) return false;

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

    private BoolE copy(boolean register) {
        BoolE newBoolE = new BoolE(border, register);
        for (int i = 0; i < lines.length; i++)
            newBoolE.lines[i] = this.lines[i] == null ? null : this.lines[i].copy();
        return newBoolE;
    }

    @Override
    public BoolE copy(){ return copy(true); }

    @Override
    public BoolE cache() { return copy(false); }
}
