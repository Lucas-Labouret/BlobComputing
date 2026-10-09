package language.field.boolField;

import language.field.BoolFieldLine;
import language.field.BoolFieldManager;
import language.field.Border;
import language.field.Coord2D;
import medium.locusT.Ev;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer field from edge to vertex orientation. */
public non-sealed class BoolEv extends BoolFieldT<BoolEv> {
    static int HEIGHT = -1;
    static int SPAN = -1;
    static final int BREADTH = 2;

    /** Configures the dimensions used by BoolEv fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn, int maxBelongingEdgePerVertex) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        if (maxBelongingEdgePerVertex < 1) throw new IllegalArgumentException("There must exist at least one edge per vertex.");
        HEIGHT = maxVerticesPerColumn;
        SPAN = maxBelongingEdgePerVertex;
    }

    /** Creates a new BoolEv. */
    public BoolEv() { this(BoolFieldManager.DEFAULT_BORDER()); }
    public BoolEv(Border border) { this(border, true); }
    public BoolEv(boolean register) { this(BoolFieldManager.DEFAULT_BORDER(), register); }
    public BoolEv(Border border, boolean register) { super(HEIGHT, SPAN, BREADTH, border, register); }
    public BoolEv(BoolEv other) {
        super(HEIGHT, SPAN, BREADTH, other.border);
        for (int i = 0; i < lines.length; i++) lines[i] = new BoolFieldLine(other.lines[i]);
    }

    private static BoolEv DATA_POS;
    private static HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolEv pos, HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks){
        DATA_POS = pos;
        MASKS = masks;
    }

    private static HashMap<Ev, Ev> TORUS;

    public static void setBorder(HashMap<Ev, Ev> torus){
        TORUS = torus;
    }

    /** @return a new BoolEv from the given broadcast field. */
    public BoolEv broadcast(BoolE orig){
        broadcastGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** Fills this BoolEv with zeroes. */
    public BoolEv zeroes(){ zeroesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Fills this BoolEv with ones. */
    public BoolEv ones(){ onesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Randomly fills this BoolEv. */
    public BoolEv rand(){
        randGeneric(HEIGHT, SPAN, BREADTH, this);

        if (border == Border.TORUS) for (Ev ev : TORUS.keySet()) {
            Ev torus = TORUS.get(ev);
            setBit(torus, getBit(ev));
        }

        return this;
    }

    /** Sets a bit in this BoolEv. */
    public void setBit(int y, int x, int t, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, t, s, bit);

        if (border == Border.TORUS) for (Ev ev : TORUS.keySet()) {
            Ev torus = TORUS.get(ev);
            if (ev.y == y && ev.x == x && ev.t == t && ev.s == s) {
                setBitGeneric(HEIGHT, SPAN, BREADTH, this, torus.y, torus.x, torus.t, torus.s, bit);
            } else if (torus.y == y && torus.x == x && torus.t == t && torus.s == s) {
                setBitGeneric(HEIGHT, SPAN, BREADTH, this, ev.y, ev.x, ev.t, ev.s, bit);
            }
        }
    }

    /** Sets a bit in this BoolEv. */
    public void setBit(Ev locus, boolean bit){
        setBit(locus.y, locus.x, locus.t, locus.s, bit);
    }

    /** Gets a bit in this BoolEv */
    public boolean getBit(int y, int x, int t, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, t, s);
    }

    /** Gets a bit in this BoolEv */
    public boolean getBit(Ev locus){
        return getBit(locus.y, locus.x, locus.t, locus.s);
    }

    /** Decode this BoolEv into a HashMap mapping each Ev locus in the given set to its corresponding bit value in the BoolEv. */
    public HashMap<Ev, Boolean> decode() {
        HashMap<Ev, Boolean> res = new HashMap<>();
        for (Ev e : medium.evs)
            res.put(e, getBit(e));
        return res;
    }

    /** Set this BoolEv to the bitwise not of orig. */
    public BoolEv not(BoolEv orig){
        notGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** Set this BoolEv to the bitwise AND of a and b. */
    public BoolEv and(BoolEv a, BoolEv b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::and);
        return this;
    }

    /** Set this BoolEv to the bitwise OR of a and b. */
    public BoolEv or(BoolEv a, BoolEv b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::or);
        return this;
    }

    /** Set this BoolEv to the bitwise XOR of a and b. */
    public BoolEv xor(BoolEv a, BoolEv b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::xor);
        return this;
    }

    private static BoolEv maskData(BoolEv mask, BoolEv data, BoolEv neutral){
        return new BoolEv(false).or(
                new BoolEv(false).and(mask, data),
                new BoolEv(false).and(new BoolEv(false).not(mask), neutral)
        );
    }

    /** Set this BoolEv to the OR reduction of the given transfer field. */
    public void redOr(BoolE target){
        target.zeroes();
        BoolEv maskedData = maskData(DATA_POS, this, new BoolEv(false).zeroes());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::or);
    }

    /** Set this BoolEv to the AND reduction of the given transfer field. */
    public void redAnd(BoolE target){
        target.ones();
        BoolEv maskedData = maskData(DATA_POS, this, new BoolEv(false).ones());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::and);
    }

    /** Set this BoolEv to the XOR reduction of the given transfer field. */
    public void redXor(BoolE target){
        target.zeroes();
        BoolEv maskedData = maskData(DATA_POS, this, new BoolEv(false).zeroes());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::xor);
    }

    private BoolE[] redStack(BoolEv neutrals) {
        BoolEv maskedData = maskData(DATA_POS, this, neutrals);
        BoolE[] target = new BoolE[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = new BoolE(false);
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolE s.t. the n-th BoolE contains the bits of Evs (*, *, *, n), or a 0 if it doesn't exist */
    public BoolE[] redStack0(){
        return redStack(new BoolEv(false).zeroes());
    }

    /** @return an array of BoolE s.t. the n-th BoolE contains the bits of Evs (*, *, *, n), or a 1 if it doesn't exist */
    public BoolE[] redStack1(){
        return redStack(new BoolEv(false).ones());
    }

    /** Sets target to the clockwise rotation of this BoolEv. */
    public void _rotateCW(BoolEf target){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            target.lines[index] = this.lines[index].copy();
        }
    }
    /** Sets this BoolEv to the clockwise rotation of orig. */
    public BoolEv rotateCW(BoolEf orig){ orig._rotateCW(this); return this; }

    /** Sets target to the counterclockwise rotation of this BoolEv. */
    public void _rotateCCW(BoolEf target){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[blockIndex + k] = this.lines[blockIndex + (k + 1) % BREADTH].copy();
            }
        }
    }
    /** Sets this BoolEv to the counterclockwise rotation of orig. */
    public BoolEv rotateCCW(BoolEf orig){ orig._rotateCCW(this); return this; }

    /** Sets res to the transfer of this BoolEv. */
    public void _transfer(BoolVe res){
        res.zeroes();
        transferGeneric(this, res,  MASKS);
    }
    /** Sets this BoolEv to the transfer of orig. */
    public BoolEv transfer(BoolVe orig){ orig._transfer(this); return this; }

    /**
     * Two BoolEv objects are equal if their existing bits are equal, or they are both empty.
     * @return whether this BoolEv is equal to the given object.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolEv other)) return false;

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

    @Override
    public String toString() {
        BoolEv clean = new BoolEv(false).and(this, DATA_POS);
        return toString(clean);
    }

    private BoolEv copy(boolean register) {
        BoolEv newBoolEv = new BoolEv(border, register);
        for (int i = 0; i < lines.length; i++)
            newBoolEv.lines[i] = this.lines[i] == null ? null : this.lines[i].copy();
        return newBoolEv;
    }

    @Override
    public BoolEv copy(){ return copy(true); }

    @Override
    public BoolEv cache() { return copy(false); }
}
