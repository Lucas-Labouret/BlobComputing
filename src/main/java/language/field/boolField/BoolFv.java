package language.field.boolField;

import language.field.BoolFieldLine;
import language.field.BoolFieldManager;
import language.field.Border;
import language.field.Coord2D;
import medium.locusT.Fv;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer field from face to vertex orientation. */
public non-sealed class BoolFv extends BoolFieldT<BoolFv> {
    static int HEIGHT = -1;
    static int SPAN = -1;
    static final int BREADTH = 3;

    /** Configures the dimensions used by BoolFv fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn, int maxBelongingFacePerVertex) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        if (maxBelongingFacePerVertex < 1) throw new IllegalArgumentException("There must exist at least one edge per vertex.");
        HEIGHT = maxVerticesPerColumn;
        SPAN = maxBelongingFacePerVertex;
    }

    /** Creates a new BoolFv. */
    public BoolFv() { this(BoolFieldManager.DEFAULT_BORDER()); }
    public BoolFv(Border border) { this(border, true); }
    public BoolFv(boolean register) { this(BoolFieldManager.DEFAULT_BORDER(), register); }
    public BoolFv(Border border, boolean register) { super(HEIGHT, SPAN, BREADTH, border, register); }
    public BoolFv(BoolFv other) {
        super(HEIGHT, SPAN, BREADTH, other.border);
        for (int i = 0; i < lines.length; i++) lines[i] = new BoolFieldLine(other.lines[i]);
    }

    private static BoolFv DATA_POS;
    private static HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolFv pos, HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks){
        DATA_POS = pos;
        MASKS = masks;
    }

    /** @return a new BoolFv from the given broadcast field. */
    public BoolFv broadcast(BoolF orig){
        broadcastGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** Fills this BoolFv with zeroes. */
    public BoolFv zeroes(){ zeroesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Fills this BoolFv with ones. */
    public BoolFv ones(){ onesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Randomly fills this BoolFv. */
    public BoolFv rand(){ randGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Sets a bit in this BoolFv. */
    public void setBit(int y, int x, int t, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, t, s, bit);
    }

    /** Sets a bit in this BoolFv. */
    public void setBit(Fv locus, boolean bit){
        setBit(locus.y, locus.x, locus.t, locus.s, bit);
    }

    /** Gets a bit in this BoolFv */
    public boolean getBit(int y, int x, int t, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, t, s);
    }

    /** Gets a bit in this BoolFv */
    public boolean getBit(Fv locus){
        return getBit(locus.y, locus.x, locus.t, locus.s);
    }

    /** Decode this BoolFv into a HashMap mapping each Fv locus in the given set to its corresponding bit value in the BoolFv. */
    public HashMap<Fv, Boolean> decode() {
        HashMap<Fv, Boolean> res = new HashMap<>();
        for (Fv f : medium.fvs)
            res.put(f, getBit(f));
        return res;
    }

    /** Set this BoolFv to the bitwise not of orig. */
    public BoolFv not(BoolFv orig){
        notGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** Set this BoolFv to the bitwise AND of a and b. */
    public BoolFv and(BoolFv a, BoolFv b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::and);
        return this;
    }

    /** Set this BoolFv to the bitwise OR of a and b. */
    public BoolFv or(BoolFv a, BoolFv b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::or);
        return this;
    }

    /** Set this BoolFv to the bitwise XOR of a and b. */
    public BoolFv xor(BoolFv a, BoolFv b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::xor);
        return this;
    }

    private static BoolFv maskData(BoolFv mask, BoolFv data, BoolFv neutral){
        return new BoolFv(false).or(
                new BoolFv(false).and(mask, data),
                new BoolFv(false).and(new BoolFv(false).not(mask), neutral)
        );
    }

    /** Set this BoolFv to the OR reduction of the given transfer field. */
    public void redOr(BoolF target){
        target.zeroes();
        BoolFv maskedData = maskData(DATA_POS, this, new BoolFv(false).zeroes());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::or);
    }

    /** Set this BoolFv to the AND reduction of the given transfer field. */
    public void redAnd(BoolF target){
        target.ones();
        BoolFv maskedData = maskData(DATA_POS, this, new BoolFv(false).ones());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::and);
    }

    /** Set this BoolFv to the XOR reduction of the given transfer field. */
    public void redXor(BoolF target){
        target.zeroes();
        BoolFv maskedData = maskData(DATA_POS, this, new BoolFv(false).zeroes());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::xor);
    }

    private BoolF[] redStack(BoolFv neutral) {
        BoolFv maskedData = maskData(DATA_POS, this, neutral);
        BoolF[] target = new BoolF[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = new BoolF(false);
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolF s.t. the n-th BoolF contains the bits of Fvs (*, *, *, n), or a 0 if it doesn't exist */
    public BoolF[] redStack0(){
        return redStack(new BoolFv(false).zeroes());
    }

    /** @return an array of BoolF s.t. the n-th BoolF contains the bits of Fvs (*, *, *, n), or a 1 if it doesn't exist */
    public BoolF[] redStack1(){
        return redStack(new BoolFv(false).ones());
    }

    /** Sets target to the clockwise rotation of this BoolFv. */
    public void _rotateCW(BoolFe target){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            target.lines[index] = this.lines[index].copy();
        }
    }
    /** Sets this BoolFv to the clockwise rotation of orig. */
    public BoolFv rotateCW(BoolFe orig) { orig._rotateCW(this); return this; }

    /** Sets target to the counterclockwise rotation of this BoolFv. */
    public void _rotateCCW(BoolFe target){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[blockIndex + k] = this.lines[blockIndex + (k + 1) % BREADTH].copy();
            }
        }
    }
    /** Sets this BoolFv to the counterclockwise rotation of orig. */
    public BoolFv rotateCCW(BoolFe orig) { orig._rotateCCW(this); return this; }

    /** Sets res to the transfer of this BoolFv. */
    public void _transfer(BoolVf res){
        res.zeroes();
        transferGeneric(this, res,  MASKS);
    }
    /** Sets this BoolFv to the transfer of orig. */
    public BoolFv transfer(BoolVf orig){ orig._transfer(this); return this; }

    /**
     * Two BoolFv objects are equal if their existing bits are equal, or they are both empty.
     * @return whether this BoolFv is equal to the given object.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolFv other)) return false;

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
        BoolFv clean = new BoolFv(false).and(this, DATA_POS);
        return toString(clean);
    }

    private BoolFv copy(boolean register) {
        BoolFv newBoolFv = new BoolFv(border, register);
        for (int i = 0; i < lines.length; i++)
            newBoolFv.lines[i] = this.lines[i] == null ? null : this.lines[i].copy();
        return newBoolFv;
    }

    @Override
    public BoolFv copy(){ return copy(true); }

    @Override
    public BoolFv cache() { return copy(false); }
}
