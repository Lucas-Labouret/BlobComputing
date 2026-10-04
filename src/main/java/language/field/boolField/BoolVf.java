package language.field.boolField;

import language.utils.BoolFieldLine;
import language.utils.BoolFieldManager;
import language.utils.Border;
import language.utils.Coord2D;
import medium.locusT.Ve;
import medium.locusT.Vf;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer language.field from vertex to face orientation. */
public non-sealed class BoolVf extends BoolFieldT<BoolVf> {
    static int HEIGHT = -1;
    static final int SPAN = 1;
    static int BREADTH = -1;

    /** Configures the dimensions used by BoolVf fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn, int maxVfPerVertex) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        if (maxVfPerVertex < 1) throw new IllegalArgumentException("There must exist at least one edge per vertex.");
        HEIGHT = maxVerticesPerColumn;
        BREADTH = maxVfPerVertex;
    }

    /** Creates a new BoolVf. */
    public BoolVf() { this(BoolFieldManager.DEFAULT_BORDER()); }
    public BoolVf(Border border) { this(border, true); }
    public BoolVf(boolean register) { this(BoolFieldManager.DEFAULT_BORDER(), register); }
    public BoolVf(Border border, boolean register) { super(HEIGHT, SPAN, BREADTH, border, register); }
    public BoolVf(BoolVf other) {
        super(HEIGHT, SPAN, BREADTH, other.border);
        for (int i = 0; i < lines.length; i++) lines[i] = other.lines[i] == null ? null : other.lines[i].copy();
    }

    private static BoolVf DATA_POS;
    private static BoolVf DATA_END;
    private static HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolVf pos, HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks){
        DATA_POS = pos;

        DATA_END = new BoolVf();
        computeDataEnd(HEIGHT, SPAN, BREADTH, DATA_POS, DATA_END);

        MASKS = masks;
    }

    private static HashMap<Vf, Integer> MIRROR;
    private static HashMap<Vf, Ve> MIRROR_CW;
    private static HashMap<Vf, Ve> MIRROR_CCW;

    public static void setBorder(HashMap<Vf, Integer> mirror, HashMap<Vf, Ve> mirrorCW, HashMap<Vf, Ve> mirrorCCW) {
        MIRROR = mirror;
        MIRROR_CW = mirrorCW;
        MIRROR_CCW = mirrorCCW;
    }

    /** @return a new BoolVf from the given broadcast language.field. */
    public BoolVf broadcast(BoolV orig){
        broadcastGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** @return a new zero-filled BoolVf. */
    public BoolVf zeroes(){ zeroesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    public BoolVf ones(){ onesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    public BoolVf rand(){ randGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Sets a bit in the given BoolVf. */
    public void setBit(int y, int x, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, 0, s, bit);
    }

    /** Sets a bit in the given BoolVf. */
    public void setBit(Vf locus, boolean bit){
        setBit(locus.y, locus.x, locus.s, bit);
    }

    /** Gets a bit in the given BoolVf */
    public boolean getBit(int y, int x, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, 0, s);
    }

    /** Gets a bit in the given BoolVf */
    public boolean getBit(Vf locus){
        return getBit(locus.y, locus.x, locus.s);
    }

    /** Decode the given BoolVf into a HashMap mapping each Vf locus in the given set to its corresponding bit value in the BoolVf. */
    public HashMap<Vf, Boolean> decode(HashSet<Vf> loci) {
        HashMap<Vf, Boolean> res = new HashMap<>();
        for (Vf v : loci)
            res.put(v, getBit(v));
        return res;
    }

    /** @return the bitwise NOT of the given BoolVf. */
    public BoolVf not(BoolVf orig){
        notGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** @return the bitwise AND of the given BoolVf values. */
    public BoolVf and(BoolVf a, BoolVf b){
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return the bitwise OR of the given BoolVf values. */
    public BoolVf or(BoolVf a, BoolVf b){
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return the bitwise XOR of the given BoolVf values. */
    public BoolVf xor(BoolVf a, BoolVf b){
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return a left-shifted BoolVf. */
    public BoolVf lShift(BoolVf orig, int n){
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a right-shifted BoolVf. */
    public BoolVf rShift(BoolVf orig, int n){
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a up-shifted BoolVf. */
    public BoolVf uShift(BoolVf orig, int n){
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a down-shifted BoolVf. */
    public BoolVf dShift(BoolVf orig, int n){
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    private static BoolVf maskData(BoolVf mask, BoolVf data, BoolVf neutral){
        return new BoolVf(false).or(
                new BoolVf(false).and(mask, data),
                new BoolVf(false).and(new BoolVf(false).not(mask), neutral)
        );
    }

    /** @return the OR reduction of the given transfer language.field. */
    public void redOr(BoolV target){
        target.zeroes();
        BoolVf maskedData = maskData(DATA_POS, this, new BoolVf(false).zeroes());
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
    }

    /** @return the AND reduction of the given transfer language.field. */
    public void redAnd(BoolV target){
        target.ones();
        BoolVf maskedData = maskData(DATA_POS, this, new BoolVf(false).ones());
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
    }

    /** @return the XOR reduction of the given transfer language.field. */
    public void redXor(BoolV target){
        target.zeroes();
        BoolVf maskedData = maskData(DATA_POS, this, new BoolVf(false).zeroes());
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (this.border == Border.MIRROR) for (Vf m: MIRROR.keySet())
            target.setBit(m.y, m.x, target.getBit(m.y, m.x) ^ this.getBit(m));
    }

    private BoolV[] redStack(boolean neutral) {
        BoolVf neutrals = neutral ? new BoolVf(false).ones() : new BoolVf(false).zeroes();
        BoolVf maskedData = maskData(DATA_POS, this, neutrals);
        BoolV[] target = new BoolV[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = new BoolV(false).zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (this.border == Border.MIRROR) this.redStackMirror(target, neutral);

        return target;
    }
    private void redStackMirror(BoolV[] target, boolean neutral) {
        for (Vf m: MIRROR.keySet()) {
            int s = MIRROR.get(m);
            try { target[s].setBit(m.y, m.x, this.getBit(m)); }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Mirror locus (" + m.y + "," + m.x +")" + " with Vf s=" + s + " is out of bounds for target array of length " + target.length);
                throw e;
            }
        }
    }

    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Vfs (*, *, n), or a 0 if it doesn't exist */
    public BoolV[] redStack0(){
        return redStack(false);
    }

    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Vfs (*, *, n), or a 1 if it doesn't exist */
    public BoolV[] redStack1(){
        return redStack(true);
    }

    /** @return the clockwise rotation of the given BoolVf. */
    public void _rotateCW(BoolVe res) {
        for (int i = 0; i < HEIGHT; i++) {
            res.lines[i * BREADTH] = BoolFieldLine.zeroes();
            for (int j = 0; j < BREADTH - 1; j++) {
                int pos = i * BREADTH + j;
                res.lines[pos + 1] = this.lines[pos].copy();
                res.lines[i * BREADTH] = BoolFieldLine.or(
                        res.lines[i * BREADTH],
                        BoolFieldLine.and(this.lines[pos], DATA_END.lines[pos])
                );
            }
        }

        if (this.border == Border.MIRROR) for (Vf m: MIRROR_CW.keySet()) {
            Ve mirrorVe = MIRROR_CW.get(m);
            res.setBit(mirrorVe, this.getBit(m));
        }
    }
    public BoolVf rotateCW(BoolVe orig) { orig._rotateCW(this); return this; }

    /** @return the counterclockwise rotation of the given BoolVf. */
    public void _rotateCCW(BoolVe res){
        for (int i = 0; i<lines.length; i++) { res.lines[i] = this.lines[i].copy(); }

        if (this.border == Border.MIRROR) for (Vf m: MIRROR_CCW.keySet()) {
            Ve mirrorVe = MIRROR_CCW.get(m);
            res.setBit(mirrorVe, this.getBit(m));
        }
    }
    public BoolVf rotateCCW(BoolVe orig) { orig._rotateCCW(this); return this; }


    /** @return the transfer result for the given BoolVf. */
    public void _transfer(BoolFv res){
        res.zeroes();
        transferGeneric(this, res, MASKS);
    }
    public BoolVf transfer(BoolFv orig) { orig._transfer(this); return this; }

    /** @return whether this BoolVf is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolVf other)) return false;

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
        BoolVf clean = new BoolVf(false).and(this, DATA_POS);
        return toString(clean);
    }

    private BoolVf copy(Boolean register) {
        BoolVf newBoolVf = new BoolVf(border, register);
        for (int i = 0; i < lines.length; i++)
            newBoolVf.lines[i] = this.lines[i] == null ? null : this.lines[i].copy();
        return newBoolVf;
    }

    @Override
    public BoolVf copy(){ return copy(true); }

    @Override
    public  BoolVf cache() { return copy(false); }
}
