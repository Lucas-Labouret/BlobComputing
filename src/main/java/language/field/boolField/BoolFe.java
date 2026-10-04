package language.field.boolField;

import language.utils.BoolFieldLine;
import language.utils.BoolFieldManager;
import language.utils.Border;
import language.utils.Coord2D;
import medium.locusT.Fe;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer language.field from face to edge orientation. */
public non-sealed class BoolFe extends BoolFieldT<BoolFe> {
    static int HEIGHT = -1;
    static int SPAN = -1;
    static final int BREADTH = 3;

    /** Configures the dimensions used by BoolFe fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn, int maxBelongingFacePerVertex) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        if (maxBelongingFacePerVertex < 1) throw new IllegalArgumentException("There must exist at least one edge per vertex.");
        HEIGHT = maxVerticesPerColumn;
        SPAN = maxBelongingFacePerVertex;
    }

    /** Creates a new BoolFe. */
    public BoolFe() { this(BoolFieldManager.DEFAULT_BORDER()); }
    public BoolFe(Border border) { this(border, true); }
    public BoolFe(boolean register) { this(BoolFieldManager.DEFAULT_BORDER(), register); }
    public BoolFe(Border border, boolean register) { super(HEIGHT, SPAN, BREADTH, border, register); }
    public BoolFe(BoolFe other) {
        super(HEIGHT, SPAN, BREADTH, other.border);
        for (int i = 0; i < lines.length; i++) lines[i] = new BoolFieldLine(other.lines[i]);
    }

    private static BoolFe DATA_POS;
    private static HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolFe pos, HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks){
        DATA_POS = pos;
        MASKS = masks;
    }

    /** @return a new BoolFe from the given broadcast language.field. */
    public BoolFe broadcast(BoolF orig){
        broadcastGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** @return a new zero-filled BoolFe. */
    public BoolFe zeroes(){ zeroesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    public BoolFe ones(){ onesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    public BoolFe rand(){ randGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Sets a bit in the given BoolFe. */
    public void setBit(int y, int x, int t, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, t, s, bit);
    }

    /** Sets a bit in the given BoolFe. */
    public void setBit(Fe locus, boolean bit){
        setBit(locus.y, locus.x, locus.t, locus.s, bit);
    }

    /** Gets a bit in the given BoolFe */
    public boolean getBit(int y, int x, int t, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, t, s);
    }

    /** Gets a bit in the given BoolFe */
    public boolean getBit(Fe locus){
        return getBit(locus.y, locus.x, locus.t, locus.s);
    }

    /** Decode the given BoolFe into a HashMap mapping each Fe locus in the given set to its corresponding bit value in the BoolFe. */
    public HashMap<Fe, Boolean> decode(HashSet<Fe> loci) {
        HashMap<Fe, Boolean> res = new HashMap<>();
        for (Fe f : loci)
            res.put(f, getBit(f));
        return res;
    }

    /** @return the bitwise NOT of the given BoolFe. */
    public BoolFe not(BoolFe orig){
        notGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** @return the bitwise AND of the given BoolFe values. */
    public BoolFe and(BoolFe a, BoolFe b){
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return the bitwise OR of the given BoolFe values. */
    public BoolFe or(BoolFe a, BoolFe b){
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return the bitwise XOR of the given BoolFe values. */
    public BoolFe xor(BoolFe a, BoolFe b){
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return a left-shifted BoolFe. */
    public BoolFe lShift(BoolFe orig, int n){
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a right-shifted BoolFe. */
    public BoolFe rShift(BoolFe orig, int n){
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a up-shifted BoolFe. */
    public BoolFe uShift(BoolFe orig, int n){
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a down-shifted BoolFe. */
    public BoolFe dShift(BoolFe orig, int n){
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    private static BoolFe maskData(BoolFe mask, BoolFe data, BoolFe neutral){
        return new BoolFe(false).or(
                new BoolFe(false).and(mask, data),
                new BoolFe(false).and(new BoolFe(false).not(mask), neutral)
        );
    }

    /** @return the OR reduction of the given transfer language.field. */
    public void redOr(BoolF target){
        target.zeroes();
        BoolFe maskedData = maskData(DATA_POS, this, new BoolFe(false).zeroes());
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
    }

    /** @return the AND reduction of the given transfer language.field. */
    public void redAnd(BoolF target){
        target.ones();
        BoolFe maskedData = maskData(DATA_POS, this, new BoolFe(false).ones());
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
    }

    /** @return the XOR reduction of the given transfer language.field. */
    public void redXor(BoolF target){
        target.zeroes();
        BoolFe maskedData = maskData(DATA_POS, this, new BoolFe(false).zeroes());
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
    }

    private BoolF[] redStack(BoolFe neutrals) {
        BoolFe maskedData = maskData(DATA_POS, this, neutrals);
        BoolF[] target = new BoolF[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = new BoolF(false).zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolF s.t. the n-th BoolF contains the bits of Fes (*, *, *, n), or a 0 if it doesn't exist */
    public BoolF[] redStack0(){
        return redStack(new BoolFe(false).zeroes());
    }

    /** @return an array of BoolF s.t. the n-th BoolF contains the bits of Fes (*, *, *, n), or a 1 if it doesn't exist */
    public BoolF[] redStack1(){
        return redStack(new BoolFe(false).ones());
    }

    /** @return the clockwise rotation of the given BoolFe. */
    public void _rotateCW(BoolFv target) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[blockIndex + k] = this.lines[blockIndex + (k - 1 + BREADTH) % BREADTH].copy();
            }
        }
    }
    public BoolFe rotateCW(BoolFv orig) { orig._rotateCW(this); return this; }

    /** @return the counterclockwise rotation of the given BoolFe. */
    public void _rotateCCW(BoolFv target){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            target.lines[index] = new BoolFieldLine(this.lines[index]).copy();
        }
    }
    public BoolFe rotateCCW(BoolFv orig) { orig._rotateCCW(this); return this; }

    /** @return the transfer result for the given BoolFe. */
    public void _transfer(BoolEf res){
        res.zeroes();
        transferGeneric(this, res,  MASKS);
    }
    public BoolFe transfer(BoolEf orig){ orig._transfer(this); return this; }

    /** @return whether this BoolFe is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolFe other)) return false;

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
        BoolFe clean = new BoolFe(false).and(this, DATA_POS);
        return toString(clean);
    }

    private BoolFe copy(boolean register) {
        BoolFe newBoolFe = new BoolFe(border, register);
        for (int i = 0; i < lines.length; i++)
            newBoolFe.lines[i] = this.lines[i] == null ? null : this.lines[i].copy();
        return newBoolFe;
    }

    @Override
    public BoolFe copy(){ return copy(true); }

    @Override
    public BoolFe cache() { return copy(false); }
}
