package field.boolField.fieldT;

import field.boolField.BoolFieldLine;
import field.boolField.fieldS.BoolE;
import medium.locusT.Ef;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer field from edge to face orientation. */
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
    public BoolEf() {
        super(HEIGHT, SPAN, BREADTH);
    }

    private static BoolEf DATA_POS;
    private static HashMap<Integer, HashMap<Integer, BoolEf>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolEf pos, HashMap<Integer, HashMap<Integer, BoolEf>> masks){
        DATA_POS = pos;
        MASKS = masks;
    }

    /** @return a new BoolEf from the given broadcast field. */
    public static BoolEf fromBroadcast(BoolE orig){
        BoolEf res = new BoolEf();
        fromBroadcastGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return a new zero-filled BoolEf. */
    public static BoolEf zeroes(){
        BoolEf res = new BoolEf();
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolEf. */
    public static BoolEf ones(){
        BoolEf res = new BoolEf();
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new randomly initialized BoolEf. */
    public static BoolEf rand(){
        BoolEf res = new BoolEf();
        randGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** Sets a bit in the given BoolEf. */
    public static void setBit(BoolEf target, int y, int x, int t, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, s, bit);
    }

    /** Gets a bit in the given BoolEf */
    public static boolean getBit(BoolEf target, int y, int x, int t, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, s);
    }

    /** Decode the given BoolEf into a HashMap mapping each Ef locus in the given set to its corresponding bit value in the BoolEf. */
    public static HashMap<Ef, Boolean> decode(HashSet<Ef> loci, BoolEf field) {
        HashMap<Ef, Boolean> res = new HashMap<>();
        for (Ef e : loci)
            res.put(e, getBit(field, e.y, e.x, e.t, e.s));
        return res;
    }

    /** @return the bitwise NOT of the given BoolEf. */
    public static BoolEf not(BoolEf orig){
        BoolEf res = new BoolEf();
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolEf values. */
    public static BoolEf and(BoolEf a, BoolEf b){
        BoolEf res = new BoolEf();
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolEf values. */
    public static BoolEf or(BoolEf a, BoolEf b){
        BoolEf res = new BoolEf();
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolEf values. */
    public static BoolEf xor(BoolEf a, BoolEf b){
        BoolEf res = new BoolEf();
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolEf. */
    public static BoolEf lShift(BoolEf orig, int n){
        BoolEf res = new BoolEf();
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolEf. */
    public static BoolEf rShift(BoolEf orig, int n){
        BoolEf res = new BoolEf();
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a up-shifted BoolEf. */
    public static BoolEf uShift(BoolEf orig, int n){
        BoolEf res = new BoolEf();
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolEf. */
    public static BoolEf dShift(BoolEf orig, int n){
        BoolEf res = new BoolEf();
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    private static BoolEf maskData(BoolEf mask, BoolEf data, BoolEf neutral){
        return BoolEf.or(
                BoolEf.and(mask, data),
                BoolEf.and(BoolEf.not(mask), neutral)
        );
    }

    /** @return the OR reduction of the given transfer field. */
    public static BoolE redOrE(BoolEf orig){
        BoolEf maskedData = maskData(DATA_POS, orig, BoolEf.zeroes());
        BoolE target = BoolE.zeroes();
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the AND reduction of the given transfer field. */
    public static BoolE redAndE(BoolEf orig){
        BoolEf maskedData = maskData(DATA_POS, orig, BoolEf.ones());
        BoolE target = BoolE.ones();
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the XOR reduction of the given transfer field. */
    public static BoolE redXorE(BoolEf orig){
        BoolEf maskedData = maskData(DATA_POS, orig, BoolEf.zeroes());
        BoolE target = BoolE.zeroes();
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolE s.t. the n-th BoolE contains the bits of Efs (*, *, *, n), or a 0 if it doesn't exist */
    public static BoolE[] redStackE0(BoolEf orig){
        BoolEf maskedData = maskData(DATA_POS, orig, BoolEf.zeroes());
        BoolE[] target = new BoolE[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolE.zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolE s.t. the n-th BoolE contains the bits of Efs (*, *, *, n), or a 1 if it doesn't exist */
    public static BoolE[] redStackE1(BoolEf orig){
        BoolEf maskedData = maskData(DATA_POS, orig, BoolEf.ones());
        BoolE[] target = new BoolE[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolE.zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the clockwise rotation of the given BoolEf. */
    public static BoolEv rotateCW(BoolEf orig) {
        BoolEv target = new BoolEv();
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[blockIndex + k] = new BoolFieldLine(orig.lines[blockIndex + (k - 1) % BREADTH]);
            }
        }
        return target;
    }

    /** @return the counterclockwise rotation of the given BoolEf. */
    public static BoolEv rotateCCW(BoolEf orig){
        BoolEv target = new BoolEv();
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[blockIndex + k] = new BoolFieldLine(orig.lines[blockIndex + (k + 1) % BREADTH]);
            }
        }
        return target;
    }

    /** @return the transfer result for the given BoolEf. */
    public static BoolFe transfer(BoolEf orig){
        BoolFe res = BoolFe.zeroes();

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
        BoolEf newBoolEf = new BoolEf();
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
}
