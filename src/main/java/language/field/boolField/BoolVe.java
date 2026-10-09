package language.field.boolField;

import language.field.BoolFieldLine;
import language.field.BoolFieldManager;
import language.field.Border;
import language.field.Coord2D;
import medium.locusS.Vertex;
import medium.locusT.Ve;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer field from vertex to edge orientation. */
public non-sealed class BoolVe extends BoolFieldT<BoolVe> {
    static int HEIGHT = -1;
    static final int SPAN = 1;
    static int BREADTH = -1;

    /** Configures the dimensions used by BoolVe fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn, int maxVePerVertex) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        if (maxVePerVertex < 1) throw new IllegalArgumentException("There must exist at least one edge per vertex.");
        HEIGHT = maxVerticesPerColumn;
        BREADTH = maxVePerVertex;
    }

    /** Creates a new BoolVe. */
    public BoolVe() { this(BoolFieldManager.DEFAULT_BORDER()); }
    public BoolVe(Border border) { this(border, true); }
    public BoolVe(boolean register) { this(BoolFieldManager.DEFAULT_BORDER(), register); }
    public BoolVe(Border border, boolean register) { super(HEIGHT, SPAN, BREADTH, border, register); }
    public BoolVe(BoolVe other) {
        super(HEIGHT, SPAN, BREADTH, other.border);
        for (int i = 0; i < lines.length; i++) lines[i] = new BoolFieldLine(other.lines[i]);
    }

    private static BoolVe DATA_POS;
    private static BoolVe DATA_END;
    private static HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolVe pos, HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks){
        DATA_POS = pos;

        DATA_END = new BoolVe();
        computeDataEnd(HEIGHT, SPAN, BREADTH, DATA_POS, DATA_END);

        MASKS = masks;
    }

    private static HashMap<Ve, Integer> MIRROR;
    private static HashMap<Ve, Ve> TORUS_BORDER;
    private static HashMap<Vertex, HashSet<Ve>> TORUS_INTERIOR;

    public static void setBorder(HashMap<Ve, Ve> torusBorder, HashMap<Vertex, HashSet<Ve>> torusInterior, HashMap<Ve, Integer> mirror) {
        TORUS_BORDER = torusBorder;
        TORUS_INTERIOR = torusInterior;
        MIRROR = mirror;
    }

    /** @return a new BoolVe from the given broadcast field. */
    public BoolVe broadcast(BoolV orig){
        broadcastGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** Fills this BoolVe with zeroes. */
    public BoolVe zeroes(){ zeroesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Fills this BoolVe with ones. */
    public BoolVe ones(){ onesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Randomly fills this BoolVe. */
    public BoolVe rand(){
        randGeneric(HEIGHT, SPAN, BREADTH, this);

        if (border == Border.TORUS) for (Ve ve : TORUS_BORDER.keySet()) {
            Ve torus = TORUS_BORDER.get(ve);
            setBit(torus, getBit(ve));
        }

        return this;
    }

    /** Sets a bit in this BoolVe. */
    public void setBit(int y, int x, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, 0, s, bit);

        if (border == Border.TORUS) for (Ve ve : TORUS_BORDER.keySet()) {
            Ve torus = TORUS_BORDER.get(ve);
            if (ve.y == y && ve.x == x && ve.s == s) {
                setBitGeneric(HEIGHT, SPAN, BREADTH, this, torus.y, torus.x, 0, torus.s, bit);
            } else if (torus.y == y && torus.x == x && torus.s == s) {
                setBitGeneric(HEIGHT, SPAN, BREADTH, this, ve.y, ve.x, 0, ve.s, bit);
            }
        }
    }

    /** Sets a bit in this BoolVe. */
    public void setBit(Ve locus, boolean bit){
        setBit(locus.y, locus.x, locus.s, bit);
    }

    /** Gets a bit in this BoolVe */
    public boolean getBit(int y, int x, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, 0, s);
    }

    /** Gets a bit in this BoolVe */
    public boolean getBit(Ve locus){
        return getBit(locus.y, locus.x, locus.s);
    }

    /** Decode this BoolVe into a HashMap mapping each Ve locus in the given set to its corresponding bit value in the BoolVe. */
    public HashMap<Ve, Boolean> decode() {
        HashMap<Ve, Boolean> res = new HashMap<>();
        for (Ve v : medium.ves)
            res.put(v, getBit(v));
        return res;
    }

    /** Set this BoolVe to the bitwise not of orig. */
    public BoolVe not(BoolVe orig){
        notGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** Set this BoolVe to the bitwise AND of a and b. */
    public BoolVe and(BoolVe a, BoolVe b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::and);
        return this;
    }

    /** Set this BoolVe to the bitwise OR of a and b. */
    public BoolVe or(BoolVe a, BoolVe b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::or);
        return this;
    }

    /** Set this BoolVe to the bitwise XOR of a and b. */
    public BoolVe xor(BoolVe a, BoolVe b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::xor);
        return this;
    }

    private static BoolVe maskData(BoolVe mask, BoolVe data, BoolVe neutral){
        return new BoolVe(false).or(
                new BoolVe(false).and(mask, data),
                new BoolVe(false).and(new BoolVe(false).not(mask), neutral)        );
    }

    /** Set this BoolVe to the OR reduction of the given transfer field. */
    public void redOr(BoolV target){
        target.zeroes();
        BoolVe maskedData = maskData(DATA_POS, this, new BoolVe(false).zeroes());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::or);
    }

    /** Set this BoolVe to the AND reduction of the given transfer field. */
    public void redAnd(BoolV target){
        target.ones();
        BoolVe maskedData = maskData(DATA_POS, this, new BoolVe(false).ones());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::and);
    }

    /** Set this BoolVe to the XOR reduction of the given transfer field. */
    public void redXor(BoolV target){
        target.zeroes();
        BoolVe maskedData = maskData(DATA_POS, this, new BoolVe(false).zeroes());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::xor);

        if (this.border == Border.MIRROR) for (Ve m: MIRROR.keySet())
            target.setBit(m.y, m.x, target.getBit(m.y, m.x) ^ this.getBit(m));
    }

    private BoolV[] redStack(boolean neutral) {
        BoolVe neutrals = neutral ? new BoolVe(false).ones() : new BoolVe(false).zeroes();
        BoolVe maskedData = maskData(DATA_POS, this, neutrals);
        BoolV[] target = new BoolV[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = new BoolV(false);
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (this.border == Border.MIRROR) redStackMirror(target);

        return target;
    }

    private void redStackMirror(BoolV[] target) {
        for (Ve m: MIRROR.keySet()) {
            int s = MIRROR.get(m);
            target[s].setBit(m.y, m.x, this.getBit(m));
        }
    }

    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Ves (*, *, n), or a 0 if it doesn't exist */
    public BoolV[] redStack0(){
        return this.redStack(false);
    }

    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Ves (*, *, n), or a 1 if it doesn't exist */
    public BoolV[] redStack1(){
        return this.redStack(true);
    }

    /** Sets target to the clockwise rotation of this BoolVe. */
    public void _rotateCW(BoolVf res) {
        for (int i=0; i< lines.length; i++) { res.lines[i] = this.lines[i].copy(); }
    }
    /** Sets this BoolVe to the clockwise rotation of orig. */
    public BoolVe rotateCW(BoolVf orig) { orig._rotateCW(this); return this; }

    /** Sets target to the counterclockwise rotation of this BoolVe. */
    public void _rotateCCW(BoolVf res){
        for (int i = 0; i < HEIGHT; i++) {
            res.lines[(i+1) * BREADTH - 1] = this.lines[i * BREADTH].copy();
            for (int j = 1; j < BREADTH; j++) {
                int pos = i * BREADTH + j;
                res.lines[pos - 1] = this.lines[pos].copy();
                res.lines[pos - 1] = BoolFieldLine.or(
                        BoolFieldLine.and(res.lines[pos - 1], BoolFieldLine.not(DATA_END.lines[pos - 1])),
                        BoolFieldLine.and(this.lines[i * BREADTH], DATA_END.lines[pos - 1])
                );
            }
        }
    }
    /** Sets this BoolVe to the counterclockwise rotation of orig. */
    public BoolVe rotateCCW(BoolVf orig) { orig._rotateCCW(this); return this; }

    /** Sets res to the transfer of this BoolVe. */
    public void _transfer(BoolEv res){
        res.zeroes();
        transferGeneric(this, res,  MASKS);
    }
    /** Sets this BoolVe to the transfer of orig. */
    public BoolVe transfer(BoolEv orig) { orig._transfer(this); return this; }

    /**
     * Two BoolVe objects are equal if their existing bits are equal, or they are both empty.
     * @return whether this BoolVe is equal to the given object.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolVe other)) return false;

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

    private BoolVe copy(boolean register) {
        BoolVe newBoolVe = new BoolVe(border, register);
        for (int i = 0; i < lines.length; i++)
            newBoolVe.lines[i] = this.lines[i] == null ? null : this.lines[i].copy();
        return newBoolVe;
    }

    /** @return a deep copy of this BoolVe. */
    @Override
    public BoolVe copy(){ return copy(true); }

    @Override
    public BoolVe cache() { return copy(false); }
}
