package language.field.boolField;

import language.utils.BoolFieldLine;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.locusT.Fe;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer language.field from face to edge orientation. */
public non-sealed class BoolFe extends BoolFieldT {
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
    public BoolFe(Border border) { super(HEIGHT, SPAN, BREADTH, border); }

    private static BoolFe DATA_POS;
    private static HashMap<Integer, HashMap<Integer, BoolFe>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolFe pos, HashMap<Integer, HashMap<Integer, BoolFe>> masks){
        DATA_POS = pos;
        MASKS = masks;
    }

    /** @return a new BoolFe from the given broadcast language.field. */
    public static BoolFe fromBroadcast(BoolF orig){
        BoolFe res = new BoolFe(orig.border);
        fromBroadcastGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return a new zero-filled BoolFe. */
    public static BoolFe zeroes(Border border){
        BoolFe res = new BoolFe(border);
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new zero-filled BoolFe using the default border. */
    public static BoolFe zeroes(){ return zeroes(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new one-filled BoolFe. */
    public static BoolFe ones(Border border){
        BoolFe res = new BoolFe(border);
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolFe using the default border. */
    public static BoolFe ones(){ return ones(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new randomly initialized BoolFe. */
    public static BoolFe rand(Border border){
        BoolFe res = new BoolFe(border);
        randGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new randomly initialized BoolFe using the default border. */
    public static BoolFe rand(){ return rand(BoolFieldManager.DEFAULT_BORDER()); }

    /** Sets a bit in the given BoolFe. */
    public static void setBit(BoolFe target, int y, int x, int t, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, s, bit);
    }

    /** Sets a bit in the given BoolFe. */
    public static void setBit(BoolFe target, Fe locus, boolean bit){
        setBit(target, locus.y, locus.x, locus.t, locus.s, bit);
    }

    /** Gets a bit in the given BoolFe */
    public static boolean getBit(BoolFe target, int y, int x, int t, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, s);
    }

    /** Gets a bit in the given BoolFe */
    public static boolean getBit(BoolFe target, Fe locus){
        return getBit(target, locus.y, locus.x, locus.t, locus.s);
    }

    /** Decode the given BoolFe into a HashMap mapping each Fe locus in the given set to its corresponding bit value in the BoolFe. */
    public static HashMap<Fe, Boolean> decode(HashSet<Fe> loci, BoolFe field) {
        HashMap<Fe, Boolean> res = new HashMap<>();
        for (Fe f : loci)
            res.put(f, getBit(field, f));
        return res;
    }

    /** @return the bitwise NOT of the given BoolFe. */
    public static BoolFe not(BoolFe orig){
        BoolFe res = new BoolFe(orig.border);
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolFe values. */
    public static BoolFe and(BoolFe a, BoolFe b){
        BoolFe res = new BoolFe(a.border);
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolFe values. */
    public static BoolFe or(BoolFe a, BoolFe b){
        BoolFe res = new BoolFe(a.border);
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolFe values. */
    public static BoolFe xor(BoolFe a, BoolFe b){
        BoolFe res = new BoolFe(a.border);
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolFe. */
    public static BoolFe lShift(BoolFe orig, int n){
        BoolFe res = new BoolFe(orig.border);
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolFe. */
    public static BoolFe rShift(BoolFe orig, int n){
        BoolFe res = new BoolFe(orig.border);
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a up-shifted BoolFe. */
    public static BoolFe uShift(BoolFe orig, int n){
        BoolFe res = new BoolFe(orig.border);
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolFe. */
    public static BoolFe dShift(BoolFe orig, int n){
        BoolFe res = new BoolFe(orig.border);
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    private static BoolFe maskData(BoolFe mask, BoolFe data, BoolFe neutral){
        return BoolFe.or(
                BoolFe.and(mask, data),
                BoolFe.and(BoolFe.not(mask), neutral)
        );
    }

    /** @return the OR reduction of the given transfer language.field. */
    public static BoolF redOrF(BoolFe orig){
        BoolFe maskedData = maskData(DATA_POS, orig, BoolFe.zeroes(orig.border));
        BoolF target = BoolF.zeroes(orig.border);
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the AND reduction of the given transfer language.field. */
    public static BoolF redAndF(BoolFe orig){
        BoolFe maskedData = maskData(DATA_POS, orig, BoolFe.ones(orig.border));
        BoolF target = BoolF.ones(orig.border);
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the XOR reduction of the given transfer language.field. */
    public static BoolF redXorF(BoolFe orig){
        BoolFe maskedData = maskData(DATA_POS, orig, BoolFe.zeroes(orig.border));
        BoolF target = BoolF.zeroes(orig.border);
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    private static BoolF[] redStackF(BoolFe orig, BoolFe neutrals) {
        BoolFe maskedData = maskData(DATA_POS, orig, neutrals);
        BoolF[] target = new BoolF[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolF.zeroes(orig.border);
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolF s.t. the n-th BoolF contains the bits of Fes (*, *, *, n), or a 0 if it doesn't exist */
    public static BoolF[] redStackF0(BoolFe orig){
        return redStackF(orig, BoolFe.zeroes(orig.border));
    }

    /** @return an array of BoolF s.t. the n-th BoolF contains the bits of Fes (*, *, *, n), or a 1 if it doesn't exist */
    public static BoolF[] redStackF1(BoolFe orig){
        return redStackF(orig, BoolFe.ones(orig.border));
    }

    /** @return the clockwise rotation of the given BoolFe. */
    public static BoolFv rotateCW(BoolFe orig) {
        BoolFv target = new BoolFv(orig.border);
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[blockIndex + k] = new BoolFieldLine(orig.lines[blockIndex + (k - 1 + BREADTH) % BREADTH]);
            }
        }
        return target;
    }

    /** @return the counterclockwise rotation of the given BoolFe. */
    public static BoolFv rotateCCW(BoolFe orig){
        BoolFv target = new BoolFv(orig.border);
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            target.lines[index] = new BoolFieldLine(orig.lines[index]);
        }
        return target;
    }

    /** @return the transfer result for the given BoolFe. */
    public static BoolEf transfer(BoolFe orig){
        BoolEf res = BoolEf.zeroes(orig.border);

        int origLen = HEIGHT * SPAN * BREADTH;
        int targetLen = BoolEf.HEIGHT * BoolEf.SPAN * BoolEf.BREADTH;

        for (int dy: MASKS.keySet()) for (int dx: MASKS.get(dy).keySet()){
            BoolFe mask = MASKS.get(dy).get(dx);
            applyTransferMask(origLen, targetLen, dy, dx, mask, orig, res);
        }

        return res;
    }

    /** @return a deep copy of this BoolFe. */
    @Override
    public BoolFe copy(){
        BoolFe newBoolFe = new BoolFe(border);
        for (int i = 0; i < lines.length; i++)
            newBoolFe.lines[i] = this.lines[i].copy();
        return newBoolFe;
    }

    /** @return whether this BoolFe is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolFe other)) return false;

        BoolFe thisClean = BoolFe.and(this, DATA_POS);
        BoolFe otherClean = BoolFe.and(other, DATA_POS);
        for (int i = 0; i < HEIGHT * SPAN * BREADTH; i++) {
            if (!thisClean.lines[i].equals(otherClean.lines[i])) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        BoolFe clean = BoolFe.and(this, DATA_POS);
        return toString(clean);
    }

    @Override
    public BoolFe cache() { return copy(); }
}
