package language.field.boolField;

import language.utils.BoolFieldLine;
import language.utils.BoolFieldManager;
import language.utils.Border;
import language.utils.Coord2D;
import medium.locusT.Fv;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer language.field from face to vertex orientation. */
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

    /** @return a new BoolFv from the given broadcast language.field. */
    public BoolFv broadcast(BoolF orig){
        broadcastGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** @return a new zero-filled BoolFv. */
    public BoolFv zeroes(){ zeroesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    public BoolFv ones(){ onesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    public BoolFv rand(){ randGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Sets a bit in the given BoolFv. */
    public void setBit(int y, int x, int t, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, t, s, bit);
    }

    /** Sets a bit in the given BoolFv. */
    public void setBit(Fv locus, boolean bit){
        setBit(locus.y, locus.x, locus.t, locus.s, bit);
    }

    /** Gets a bit in the given BoolFv */
    public boolean getBit(int y, int x, int t, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, t, s);
    }

    /** Gets a bit in the given BoolFv */
    public boolean getBit(Fv locus){
        return getBit(locus.y, locus.x, locus.t, locus.s);
    }

    /** Decode the given BoolFv into a HashMap mapping each Fv locus in the given set to its corresponding bit value in the BoolFv. */
    public HashMap<Fv, Boolean> decode(HashSet<Fv> loci) {
        HashMap<Fv, Boolean> res = new HashMap<>();
        for (Fv f : loci)
            res.put(f, getBit(f));
        return res;
    }

    /** @return the bitwise NOT of the given BoolFv. */
    public BoolFv not(BoolFv orig){
        notGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** @return the bitwise AND of the given BoolFv values. */
    public BoolFv and(BoolFv a, BoolFv b){
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return the bitwise OR of the given BoolFv values. */
    public BoolFv or(BoolFv a, BoolFv b){
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return the bitwise XOR of the given BoolFv values. */
    public BoolFv xor(BoolFv a, BoolFv b){
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return a left-shifted BoolFv. */
    public BoolFv lShift(BoolFv orig, int n){
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a right-shifted BoolFv. */
    public BoolFv rShift(BoolFv orig, int n){
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a up-shifted BoolFv. */
    public BoolFv uShift(BoolFv orig, int n){
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a down-shifted BoolFv. */
    public BoolFv dShift(BoolFv orig, int n){
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    private static BoolFv maskData(BoolFv mask, BoolFv data, BoolFv neutral){
        return new BoolFv(false).or(
                new BoolFv(false).and(mask, data),
                new BoolFv(false).and(new BoolFv(false).not(mask), neutral)
        );
    }

    /** @return the OR reduction of the given transfer language.field. */
    public void redOr(BoolF target){
        target.zeroes();
        BoolFv maskedData = maskData(DATA_POS, this, new BoolFv(false).zeroes());
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
    }

    /** @return the AND reduction of the given transfer language.field. */
    public void redAnd(BoolF target){
        target.ones();
        BoolFv maskedData = maskData(DATA_POS, this, new BoolFv(false).ones());
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
    }

    /** @return the XOR reduction of the given transfer language.field. */
    public void redXor(BoolF target){
        target.zeroes();
        BoolFv maskedData = maskData(DATA_POS, this, new BoolFv(false).zeroes());
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
    }

    private BoolF[] redStack(BoolFv neutral) {
        BoolFv maskedData = maskData(DATA_POS, this, neutral);
        BoolF[] target = new BoolF[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = new BoolF(false).zeroes();
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

    /** @return the clockwise rotation of the given BoolFv. */
    public void _rotateCW(BoolFe target){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            target.lines[index] = this.lines[index].copy();
        }
    }
    public BoolFv rotateCW(BoolFe orig) { orig._rotateCW(this); return this; }

    /** @return the counterclockwise rotation of the given BoolFv. */
    public void _rotateCCW(BoolFe target){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[blockIndex + k] = this.lines[blockIndex + (k + 1) % BREADTH].copy();
            }
        }
    }
    public BoolFv rotateCCW(BoolFe orig) { orig._rotateCCW(this); return this; }

    /** @return the transfer result for the given BoolFv. */
    public void _transfer(BoolVf res){
        res.zeroes();
        transferGeneric(this, res,  MASKS);
    }
    public BoolFv transfer(BoolVf orig){ orig._transfer(this); return this; }

    /** @return whether this BoolFv is equal to the given object. */
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
