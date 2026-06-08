package language.field.boolField;

import language.utils.BoolFieldLine;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.locusT.Ve;
import medium.locusT.Vf;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer language.field from vertex to face orientation. */
public non-sealed class BoolVf extends BoolFieldT {
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
    public BoolVf(Border border) { super(HEIGHT, SPAN, BREADTH, border); }

    private static BoolVf DATA_POS;
    private static BoolVf DATA_END;
    private static HashMap<Integer, HashMap<Integer, BoolVf>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolVf pos, HashMap<Integer, HashMap<Integer, BoolVf>> masks){
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
    public static BoolVf fromBroadcast(BoolV orig){
        BoolVf res = new BoolVf(orig.border);
        fromBroadcastGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return a new zero-filled BoolVf. */
    public static BoolVf zeroes(Border border){
        BoolVf res = new BoolVf(border);
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new zero-filled BoolVf using the default border. */
    public static BoolVf zeroes(){ return zeroes(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new one-filled BoolVf. */
    public static BoolVf ones(Border border){
        BoolVf res = new BoolVf(border);
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolVf using the default border. */
    public static BoolVf ones(){ return ones(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new randomly initialized BoolVf. */
    public static BoolVf rand(Border border){
        BoolVf res = new BoolVf(border);
        randGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new randomly initialized BoolVf using the default border. */
    public static BoolVf rand(){ return rand(BoolFieldManager.DEFAULT_BORDER()); }

    /** Sets a bit in the given BoolVf. */
    public static void setBit(BoolVf target, int y, int x, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, s, bit);
    }

    /** Sets a bit in the given BoolVf. */
    public static void setBit(BoolVf target, Vf locus, boolean bit){
        setBit(target, locus.y, locus.x, locus.s, bit);
    }

    /** Gets a bit in the given BoolVf */
    public static boolean getBit(BoolVf target, int y, int x, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, s);
    }

    /** Gets a bit in the given BoolVf */
    public static boolean getBit(BoolVf target, Vf locus){
        return getBit(target, locus.y, locus.x, locus.s);
    }

    /** Decode the given BoolVf into a HashMap mapping each Vf locus in the given set to its corresponding bit value in the BoolVf. */
    public static HashMap<Vf, Boolean> decode(HashSet<Vf> loci, BoolVf field) {
        HashMap<Vf, Boolean> res = new HashMap<>();
        for (Vf v : loci)
            res.put(v, getBit(field, v));
        return res;
    }

    /** @return the bitwise NOT of the given BoolVf. */
    public static BoolVf not(BoolVf orig){
        BoolVf res = new BoolVf(orig.border);
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolVf values. */
    public static BoolVf and(BoolVf a, BoolVf b){
        BoolVf res = new BoolVf(a.border);
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolVf values. */
    public static BoolVf or(BoolVf a, BoolVf b){
        BoolVf res = new BoolVf(a.border);
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolVf values. */
    public static BoolVf xor(BoolVf a, BoolVf b){
        BoolVf res = new BoolVf(a.border);
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolVf. */
    public static BoolVf lShift(BoolVf orig, int n){
        BoolVf res = new BoolVf(orig.border);
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolVf. */
    public static BoolVf rShift(BoolVf orig, int n){
        BoolVf res = new BoolVf(orig.border);
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a up-shifted BoolVf. */
    public static BoolVf uShift(BoolVf orig, int n){
        BoolVf res = new BoolVf(orig.border);
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolVf. */
    public static BoolVf dShift(BoolVf orig, int n){
        BoolVf res = new BoolVf(orig.border);
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    private static BoolVf maskData(BoolVf mask, BoolVf data, BoolVf neutral){
        return BoolVf.or(
                BoolVf.and(mask, data),
                BoolVf.and(BoolVf.not(mask), neutral)
        );
    }

    /** @return the OR reduction of the given transfer language.field. */
    public static BoolV redOrV(BoolVf orig){
        BoolVf maskedData = maskData(DATA_POS, orig, BoolVf.zeroes(orig.border));
        BoolV target = BoolV.zeroes(orig.border);
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the AND reduction of the given transfer language.field. */
    public static BoolV redAndV(BoolVf orig){
        BoolVf maskedData = maskData(DATA_POS, orig, BoolVf.ones(orig.border));
        BoolV target = BoolV.ones(orig.border);
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the XOR reduction of the given transfer language.field. */
    public static BoolV redXorV(BoolVf orig){
        BoolVf maskedData = maskData(DATA_POS, orig, BoolVf.zeroes(orig.border));
        BoolV target = BoolV.zeroes(orig.border);
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (orig.border == Border.MIRROR) for (Vf m: MIRROR.keySet())
            BoolV.setBit(target, m.y, m.x, BoolV.getBit(target, m.y, m.x) ^ BoolVf.getBit(orig, m));

        return target;
    }

    private static BoolV[] redStackV(BoolVf orig, boolean neutral) {
        BoolVf neutrals = neutral ? BoolVf.ones(orig.border) : BoolVf.zeroes(orig.border);
        BoolVf maskedData = maskData(DATA_POS, orig, neutrals);
        BoolV[] target = new BoolV[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolV.zeroes(orig.border);
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (orig.border == Border.MIRROR) redStackMirror(orig, target, neutral);

        return target;
    }
    private static void redStackMirror(BoolVf orig, BoolV[] target, boolean neutral) {
        for (Vf m: MIRROR.keySet()) {
            int s = MIRROR.get(m);
            try { BoolV.setBit(target[s], m.y, m.x, getBit(orig, m)); }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Mirror locus (" + m.y + "," + m.x +")" + " with Vf s=" + s + " is out of bounds for target array of length " + target.length);
                throw e;
            }
        }
    }


    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Vfs (*, *, n), or a 0 if it doesn't exist */
    public static BoolV[] redStackV0(BoolVf orig){
        return redStackV(orig, false);
    }

    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Vfs (*, *, n), or a 1 if it doesn't exist */
    public static BoolV[] redStackV1(BoolVf orig){
        return redStackV(orig, true);
    }

    /** @return the clockwise rotation of the given BoolVf. */
    public static BoolVe rotateCW(BoolVf orig) {
        BoolVe res = new BoolVe(orig.border);

        for (int i = 0; i < HEIGHT; i++) {
            res.lines[i * BREADTH] = BoolFieldLine.zeroes();
            for (int j = 0; j < BREADTH - 1; j++) {
                int pos = i * BREADTH + j;
                res.lines[pos + 1] = orig.lines[pos];
                res.lines[i * BREADTH] = BoolFieldLine.or(
                        res.lines[i * BREADTH],
                        BoolFieldLine.and(orig.lines[pos], DATA_END.lines[pos])
                );
            }
        }

        if (orig.border == Border.MIRROR) for (Vf m: MIRROR_CW.keySet()) {
            Ve mirrorVe = MIRROR_CW.get(m);
            BoolVe.setBit(res, mirrorVe, BoolVf.getBit(orig, m));
        }

        return res;
    }

    /** @return the counterclockwise rotation of the given BoolVf. */
    public static BoolVe rotateCCW(BoolVf orig){
        BoolVe res = new BoolVe(orig.border);
        System.arraycopy(orig.lines, 0, res.lines, 0, orig.lines.length);

         if (orig.border == Border.MIRROR) for (Vf m: MIRROR_CCW.keySet()) {
            Ve mirrorVe = MIRROR_CCW.get(m);
            BoolVe.setBit(res, mirrorVe, BoolVf.getBit(orig, m));
        }

        return res;
    }


    /** @return the transfer result for the given BoolVf. */
    public static BoolFv transfer(BoolVf orig){
        BoolFv res = BoolFv.zeroes(orig.border);

        int origLen = HEIGHT * BREADTH;
        int targetLen = BoolFv.HEIGHT * BoolFv.SPAN * BoolFv.BREADTH;

        for (int dy: MASKS.keySet()) for (int dx: MASKS.get(dy).keySet()){
            BoolVf mask = MASKS.get(dy).get(dx);
            applyTransferMask(origLen, targetLen, dy, dx, mask, orig, res);
        }

        return res;
    }

    /** @return a deep copy of this BoolVf. */
    @Override
    public BoolVf copy(){
        BoolVf newBoolVf = new BoolVf(border);
        for (int i = 0; i < lines.length; i++)
            newBoolVf.lines[i] = this.lines[i].copy();
        return newBoolVf;
    }

    /** @return whether this BoolVf is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolVf other)) return false;

        BoolVf thisClean = BoolVf.and(this, DATA_POS);
        BoolVf otherClean = BoolVf.and(other, DATA_POS);
        for (int i = 0; i < HEIGHT * SPAN * BREADTH; i++) {
            if (!thisClean.lines[i].equals(otherClean.lines[i])) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        BoolVf clean = BoolVf.and(this, DATA_POS);
        return toString(clean);
    }

    @Override
    public  BoolVf cache() { return copy(); }
}
