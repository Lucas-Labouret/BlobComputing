package language.field.boolField;

import language.utils.BoolFieldLine;
import language.utils.BoolFieldManager;
import language.utils.Border;
import language.utils.Coord2D;
import medium.locusS.Vertex;
import medium.locusT.Ve;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer language.field from vertex to edge orientation. */
public non-sealed class BoolVe extends BoolFieldT {
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
    public BoolVe(Border border) { super(HEIGHT, SPAN, BREADTH, border); }

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

    /** @return a new BoolVe from the given broadcast language.field. */
    public static BoolVe fromBroadcast(BoolV orig){
        BoolVe res = new BoolVe(orig.border);
        fromBroadcastGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return a new zero-filled BoolVe. */
    public static BoolVe zeroes(Border border){
        BoolVe res = new BoolVe(border);
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new zero-filled BoolVe using the default border. */
    public static BoolVe zeroes(){ return zeroes(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new one-filled BoolVe. */
    public static BoolVe ones(Border border){
        BoolVe res = new BoolVe(border);
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolVe using the default border. */
    public static BoolVe ones(){ return ones(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new randomly initialized BoolVe. */
    public static BoolVe rand(Border border){
        BoolVe res = new BoolVe(border);
        randGeneric(HEIGHT, SPAN, BREADTH, res);

        if (border == Border.TORUS) for (Ve ve : TORUS_BORDER.keySet()) {
            Ve torus = TORUS_BORDER.get(ve);
            setBit(res, torus, getBit(res, ve));
        }

        return res;
    }

    /** @return a new randomly initialized BoolVe using the default border. */
    public static BoolVe rand(){ return rand(BoolFieldManager.DEFAULT_BORDER()); }

    /** Sets a bit in the given BoolVe. */
    public static void setBit(BoolVe target, int y, int x, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, s, bit);

        if (target.border == Border.TORUS) for (Ve ve : TORUS_BORDER.keySet()) {
            Ve torus = TORUS_BORDER.get(ve);
            if (ve.y == y && ve.x == x && ve.s == s) {
                setBitGeneric(HEIGHT, SPAN, BREADTH, target, torus.y, torus.x, 0, torus.s, bit);
            } else if (torus.y == y && torus.x == x && torus.s == s) {
                setBitGeneric(HEIGHT, SPAN, BREADTH, target, ve.y, ve.x, 0, ve.s, bit);
            }
        }
    }

    /** Sets a bit in the given BoolVe. */
    public static void setBit(BoolVe target, Ve locus, boolean bit){
        setBit(target, locus.y, locus.x, locus.s, bit);
    }

    /** Gets a bit in the given BoolVe */
    public static boolean getBit(BoolVe target, int y, int x, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, s);
    }

    /** Gets a bit in the given BoolVe */
    public static boolean getBit(BoolVe target, Ve locus){
        return getBit(target, locus.y, locus.x, locus.s);
    }

    /** Decode the given BoolVe into a HashMap mapping each Ve locus in the given set to its corresponding bit value in the BoolVe. */
    public static HashMap<Ve, Boolean> decode(HashSet<Ve> loci, BoolVe field) {
        HashMap<Ve, Boolean> res = new HashMap<>();
        for (Ve v : loci)
            res.put(v, getBit(field, v));
        return res;
    }

    /** @return the bitwise NOT of the given BoolVe. */
    public static BoolVe not(BoolVe orig){
        BoolVe res = new BoolVe(orig.border);
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolVe values. */
    public static BoolVe and(BoolVe a, BoolVe b){
        BoolVe res = new BoolVe(a.border);
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolVe values. */
    public static BoolVe or(BoolVe a, BoolVe b){
        BoolVe res = new BoolVe(a.border);
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolVe values. */
    public static BoolVe xor(BoolVe a, BoolVe b){
        BoolVe res = new BoolVe(a.border);
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolVe. */
    public static BoolVe lShift(BoolVe orig, int n){
        BoolVe res = new BoolVe(orig.border);
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolVe. */
    public static BoolVe rShift(BoolVe orig, int n){
        BoolVe res = new BoolVe(orig.border);
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a up-shifted BoolVe. */
    public static BoolVe uShift(BoolVe orig, int n){
        BoolVe res = new BoolVe(orig.border);
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolVe. */
    public static BoolVe dShift(BoolVe orig, int n){
        BoolVe res = new BoolVe(orig.border);
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    private static BoolVe maskData(BoolVe mask, BoolVe data, BoolVe neutral){
        return BoolVe.or(
                BoolVe.and(mask, data),
                BoolVe.and(BoolVe.not(mask), neutral)
        );
    }

    /** @return the OR reduction of the given transfer language.field. */
    public static BoolV redOrV(BoolVe orig){
        BoolVe maskedData = maskData(DATA_POS, orig, BoolVe.zeroes(orig.border));
        BoolV target = BoolV.zeroes(orig.border);
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the AND reduction of the given transfer language.field. */
    public static BoolV redAndV(BoolVe orig){
        BoolVe maskedData = maskData(DATA_POS, orig, BoolVe.ones(orig.border));
        BoolV target = BoolV.ones(orig.border);
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the XOR reduction of the given transfer language.field. */
    public static BoolV redXorV(BoolVe orig){
        BoolVe maskedData = maskData(DATA_POS, orig, BoolVe.zeroes(orig.border));
        BoolV target = BoolV.zeroes(orig.border);
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (orig.border == Border.MIRROR) for (Ve m: MIRROR.keySet())
            BoolV.setBit(target, m.y, m.x, BoolV.getBit(target, m.y, m.x) ^ BoolVe.getBit(orig, m));

        return target;
    }

    private static BoolV[] redStackV(BoolVe orig, boolean neutral) {
        BoolVe neutrals = neutral ? BoolVe.ones(orig.border) : BoolVe.zeroes(orig.border);
        BoolVe maskedData = maskData(DATA_POS, orig, neutrals);
        BoolV[] target = new BoolV[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolV.zeroes(orig.border);
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (orig.border == Border.MIRROR) redStackMirror(orig, target, neutral);

        return target;
    }

    private static void redStackMirror(BoolVe orig, BoolV[] target, boolean neutral) {
        for (Ve m: MIRROR.keySet()) {
            int s = MIRROR.get(m);
            BoolV.setBit(target[s], m.y, m.x, getBit(orig, m));
        }
    }

    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Ves (*, *, n), or a 0 if it doesn't exist */
    public static BoolV[] redStackV0(BoolVe orig){
        return redStackV(orig, false);
    }

    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Ves (*, *, n), or a 1 if it doesn't exist */
    public static BoolV[] redStackV1(BoolVe orig){
        return redStackV(orig, true);
    }

    /** @return the clockwise rotation of the given BoolVe. */
    public static BoolVf rotateCW(BoolVe orig) {
        BoolVf res = new BoolVf(orig.border);
        System.arraycopy(orig.lines, 0, res.lines, 0, orig.lines.length);
        return res;
    }

    /** @return the counterclockwise rotation of the given BoolVe. */
    public static BoolVf rotateCCW(BoolVe orig){
        BoolVf res = BoolVf.zeroes(orig.border);

        for (int i = 0; i < HEIGHT; i++) {
            res.lines[(i+1) * BREADTH - 1] = orig.lines[i * BREADTH];
            for (int j = 1; j < BREADTH; j++) {
                int pos = i * BREADTH + j;
                res.lines[pos - 1] = orig.lines[pos];
                res.lines[pos - 1] = BoolFieldLine.or(
                        BoolFieldLine.and(res.lines[pos - 1], BoolFieldLine.not(DATA_END.lines[pos - 1])),
                        BoolFieldLine.and(orig.lines[i * BREADTH], DATA_END.lines[pos - 1])
                );
            }
        }

        return res;
    }

    /** @return the transfer result for the given BoolVe. */
    public static BoolEv transfer(BoolVe orig){
        BoolEv res = BoolEv.zeroes(orig.border);
        transferGeneric(orig, res,  MASKS);
        return res;
    }

    /** @return a deep copy of this BoolVe. */
    public BoolVe copy(){
        BoolVe newBoolVe = new BoolVe(border);
        for (int i = 0; i < lines.length; i++)
            newBoolVe.lines[i] = this.lines[i].copy();
        return newBoolVe;
    }

    /** @return whether this BoolVe is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolVe other)) return false;

        BoolVe thisClean = BoolVe.and(this, DATA_POS);
        BoolVe otherClean = BoolVe.and(other, DATA_POS);
        for (int i = 0; i < HEIGHT * SPAN * BREADTH; i++) {
            if (!thisClean.lines[i].equals(otherClean.lines[i])) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        BoolVe clean = BoolVe.and(this, DATA_POS);
        return toString(clean);
    }

    @Override
    public BoolVe cache() { return copy(); }
}
