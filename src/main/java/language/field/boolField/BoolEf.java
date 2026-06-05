package language.field.boolField;

import language.utils.BoolFieldLine;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.locusT.Ef;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer language.field from edge to face orientation. */
public class BoolEf extends BoolFieldT {
    static int HEIGHT = -1;
    static int SPAN = -1;
    static final int BREADTH = 2;

    /** Configures the dimensions used by BoolEf fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn, int maxBelongingEdgePerVertex) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        if (maxBelongingEdgePerVertex < 1) throw new IllegalArgumentException("There must exist at least one edge per vertex.");
        HEIGHT = maxVerticesPerColumn;
        SPAN = maxBelongingEdgePerVertex;
    }

    /** Creates a new BoolEf. */
    public BoolEf() { this(BoolFieldManager.DEFAULT_BORDER()); }
    public BoolEf(Border border) { super(HEIGHT, SPAN, BREADTH, border); }

    private static BoolEf DATA_POS;
    private static HashMap<Integer, HashMap<Integer, BoolEf>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolEf pos, HashMap<Integer, HashMap<Integer, BoolEf>> masks){
        DATA_POS = pos;
        MASKS = masks;
    }

    private static HashSet<Ef> MIRROR;
    private static HashMap<Ef, Ef> TORUS;

    public static void setBorder(HashMap<Ef, Ef> torus, HashSet<Ef> mirror) {
        MIRROR = mirror;
        TORUS = torus;
    }

    /** @return a new BoolEf from the given broadcast language.field. */
    public static BoolEf fromBroadcast(BoolE orig){
        BoolEf res = new BoolEf(orig.border);
        fromBroadcastGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return a new zero-filled BoolEf. */
    public static BoolEf zeroes(Border border) {
        BoolEf res = new BoolEf(border);
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolEf using the default border. */
    public static BoolEf zeroes(){ return ones(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new one-filled BoolEf. */
    public static BoolEf ones(Border border){
        BoolEf res = new BoolEf(border);
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolEf using the default border. */
    public static BoolEf ones(){ return ones(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new randomly initialized BoolEf. */
    public static BoolEf rand(Border border){
        BoolEf res = new BoolEf(border);
        randGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolEf using the default border. */
    public static BoolEf rand(){ return rand(BoolFieldManager.DEFAULT_BORDER()); }

    /** Sets a bit in the given BoolEf. */
    public static void setBit(BoolEf target, int y, int x, int t, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, s, bit);
    }

    /** Sets a bit in the given BoolEf. */
    public static void setBit(BoolEf target, Ef locus, boolean bit){
        setBit(target, locus.y, locus.x, locus.t, locus.s, bit);
    }

    /** Gets a bit in the given BoolEf */
    public static boolean getBit(BoolEf target, int y, int x, int t, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, s);
    }

    /** Gets a bit in the given BoolEf */
    public static boolean getBit(BoolEf target, Ef locus){
        return getBit(target, locus.y, locus.x, locus.t, locus.s);
    }

    /** Decode the given BoolEf into a HashMap mapping each Ef locus in the given set to its corresponding bit value in the BoolEf. */
    public static HashMap<Ef, Boolean> decode(HashSet<Ef> loci, BoolEf field) {
        HashMap<Ef, Boolean> res = new HashMap<>();
        for (Ef e : loci)
            res.put(e, getBit(field, e));
        return res;
    }

    /** @return the bitwise NOT of the given BoolEf. */
    public static BoolEf not(BoolEf orig){
        BoolEf res = new BoolEf(orig.border);
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolEf values. */
    public static BoolEf and(BoolEf a, BoolEf b){
        BoolEf res = new BoolEf(a.border);
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolEf values. */
    public static BoolEf or(BoolEf a, BoolEf b){
        BoolEf res = new BoolEf(a.border);
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolEf values. */
    public static BoolEf xor(BoolEf a, BoolEf b){
        BoolEf res = new BoolEf(a.border);
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolEf. */
    public static BoolEf lShift(BoolEf orig, int n){
        BoolEf res = new BoolEf(orig.border);
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolEf. */
    public static BoolEf rShift(BoolEf orig, int n){
        BoolEf res = new BoolEf(orig.border);
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a up-shifted BoolEf. */
    public static BoolEf uShift(BoolEf orig, int n){
        BoolEf res = new BoolEf(orig.border);
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolEf. */
    public static BoolEf dShift(BoolEf orig, int n){
        BoolEf res = new BoolEf(orig.border);
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    private static BoolEf maskData(BoolEf mask, BoolEf data, BoolEf neutral){
        return BoolEf.or(
                BoolEf.and(mask, data),
                BoolEf.and(BoolEf.not(mask), neutral)
        );
    }

    /** @return the AND reduction of the given transfer language.field. */
    public static BoolE redAndE(BoolEf orig){
        BoolEf maskedData = maskData(DATA_POS, orig, BoolEf.ones(orig.border));
        BoolE target = BoolE.ones(orig.border);
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (orig.border == Border.TORUS) for (Ef ef: TORUS.keySet()) {
            Ef torus = TORUS.get(ef);
            boolean bit = getBit(orig, ef.y, ef.x, ef.t, ef.s) & getBit(orig, torus.y, torus.x, torus.t, torus.s);
            BoolE.setBit(target, ef.y, ef.x, ef.t, bit);
            BoolE.setBit(target, torus.y, torus.x, torus.t, bit);

        }

        return target;
    }

    /** @return the OR reduction of the given transfer language.field. */
    public static BoolE redOrE(BoolEf orig){
        BoolEf maskedData = maskData(DATA_POS, orig, BoolEf.zeroes(orig.border));
        BoolE target = BoolE.zeroes(orig.border);
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (orig.border == Border.TORUS) for (Ef ef: TORUS.keySet()) {
            Ef torus = TORUS.get(ef);
            boolean bit = getBit(orig, ef.y, ef.x, ef.t, ef.s) | getBit(orig, torus.y, torus.x, torus.t, torus.s);
            BoolE.setBit(target, ef.y, ef.x, ef.t, bit);
            BoolE.setBit(target, torus.y, torus.x, torus.t, bit);
        }

        return target;
    }

    /** @return the XOR reduction of the given transfer language.field. */
    public static BoolE redXorE(BoolEf orig){
        BoolEf maskedData = maskData(DATA_POS, orig, BoolEf.zeroes(orig.border));
        BoolE target = BoolE.zeroes(orig.border);
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (orig.border == Border.MIRROR) for (Ef m: MIRROR) BoolE.setBit(target, m.y, m.x, m.t, false);
        if (orig.border == Border.TORUS) for (Ef ef: TORUS.keySet()) {
            Ef torus = TORUS.get(ef);
            boolean bit = getBit(orig, ef.y, ef.x, ef.t, ef.s) ^ getBit(orig, torus.y, torus.x, torus.t, torus.s);
            BoolE.setBit(target, ef.y, ef.x, ef.t, bit);
            BoolE.setBit(target, torus.y, torus.x, torus.t, bit);
        }

        return target;
    }

    private static BoolE[] redStackE(BoolEf orig, BoolEf neutrals) {
        BoolEf maskedData = maskData(DATA_POS, orig, neutrals);
        BoolE[] target = new BoolE[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolE.zeroes(orig.border);
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (orig.border == Border.MIRROR) applyMirrorStack(orig, target);
        if (orig.border == Border.TORUS) applyTorusStack(orig, target);

        return target;
    }

    private static void applyMirrorStack(BoolEf orig, BoolE[] target) {
        for (Ef m: MIRROR) {
            boolean bit = getBit(orig, m.y, m.x, m.t, m.s);
            for (int i = 0; i < BREADTH; i++) BoolE.setBit(target[i], m.y, m.x, m.t, bit);
        }
    }

    private static void applyTorusStack(BoolEf orig, BoolE[] target) {
        for (Ef ef: TORUS.keySet()) {
            Ef torus = TORUS.get(ef);
            BoolE.setBit(target[1-ef.s], ef.y, ef.x, ef.t, getBit(orig, torus.y, torus.x, torus.t, torus.s));
            BoolE.setBit(target[1-torus.s], torus.y, torus.x, torus.t, getBit(orig, ef.y, ef.x, ef.t, ef.s));
        }
    }

    /** @return an array of BoolE s.t. the n-th BoolE contains the bits of Efs (*, *, *, n), or a 0 if it doesn't exist */
    public static BoolE[] redStackE0(BoolEf orig){
        return redStackE(orig, BoolEf.zeroes(orig.border));
    }

    /** @return an array of BoolE s.t. the n-th BoolE contains the bits of Efs (*, *, *, n), or a 1 if it doesn't exist */
    public static BoolE[] redStackE1(BoolEf orig){
        return redStackE(orig, BoolEf.ones(orig.border));
    }

    /** @return the clockwise rotation of the given BoolEf. */
    public static BoolEv rotateCW(BoolEf orig) {
        BoolEv target = new BoolEv(orig.border);
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[blockIndex + k] = new BoolFieldLine(orig.lines[blockIndex + (k + 1) % BREADTH]);
            }
        }
        //if (orig.border == Border.MIRROR) applyMirrorRotation(orig, target);
        return target;
    }

    /** @return the counterclockwise rotation of the given BoolEf. */
    public static BoolEv rotateCCW(BoolEf orig){
        BoolEv target = new BoolEv(orig.border);
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[blockIndex + k] = new BoolFieldLine(orig.lines[blockIndex + k]);
            }
        }
        //if (orig.border == Border.MIRROR) applyMirrorRotation(orig, target);
        return target;
    }

    /** Simulates a mirrored border for rotations , both CW and CCW*/
    private static void applyMirrorRotation(BoolEf orig, BoolEv target) {
        for (Ef ef: MIRROR) {
            boolean bit = BoolEf.getBit(orig, ef);
            BoolEv.setBit(target, ef.y, ef.x, ef.t, 0, bit);
            BoolEv.setBit(target, ef.y, ef.x, ef.t, 1, bit);
        }
    }

    /** @return the transfer result for the given BoolEf. */
    public static BoolFe transfer(BoolEf orig){
        BoolFe res = BoolFe.zeroes(orig.border);

        int origLen = HEIGHT * SPAN * BREADTH;
        int targetLen = BoolFe.HEIGHT * BoolFe.SPAN * BoolFe.BREADTH;

        for (int dy: MASKS.keySet()) for (int dx: MASKS.get(dy).keySet()){
            BoolEf mask = MASKS.get(dy).get(dx);
            applyTransferMask(origLen, targetLen, dy, dx, mask, orig, res);
        }

        return res;
    }

    /** @return a deep copy of this BoolEf. */
    @Override
    public BoolEf copy(){
        BoolEf newBoolEf = new BoolEf(border);
        for (int i = 0; i < lines.length; i++)
            newBoolEf.lines[i] = this.lines[i].copy();
        return newBoolEf;
    }

    /** @return whether this BoolEf is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolEf other)) return false;

        BoolEf thisClean = BoolEf.and(this, DATA_POS);
        BoolEf otherClean = BoolEf.and(other, DATA_POS);
        for (int i = 0; i < HEIGHT * SPAN * BREADTH; i++) {
            if (!thisClean.lines[i].equals(otherClean.lines[i])) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        BoolEf clean = BoolEf.and(this, DATA_POS);
        return toString(clean);
    }

    @Override
    public BoolEf cache() { return copy(); }
}
