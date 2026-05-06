package field.boolField.fieldT;

import field.boolField.BoolFieldLine;
import field.boolField.fieldS.BoolV;
import medium.locusT.Vf;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer field from vertex to face orientation. */
public class BoolVf extends BoolFieldT {
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
    public BoolVf() {
        super(HEIGHT, SPAN, BREADTH);
    }

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

    /** @return a new BoolVf from the given broadcast field. */
    public static BoolVf fromBroadcast(BoolV orig){
        BoolVf res = new BoolVf();
        fromBroadcastGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return a new zero-filled BoolVf. */
    public static BoolVf zeroes(){
        BoolVf res = new BoolVf();
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolVf. */
    public static BoolVf ones(){
        BoolVf res = new BoolVf();
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new randomly initialized BoolVf. */
    public static BoolVf rand(){
        BoolVf res = new BoolVf();
        randGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** Sets a bit in the given BoolVf. */
    public static void setBit(BoolVf target, int y, int x, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, s, bit);
    }

    /** Gets a bit in the given BoolVf */
    public static boolean getBit(BoolVf target, int y, int x, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, s);
    }

    /** Decode the given BoolVf into a HashMap mapping each Vf locus in the given set to its corresponding bit value in the BoolVf. */
    public static HashMap<Vf, Boolean> decode(HashSet<Vf> loci, BoolVf field) {
        HashMap<Vf, Boolean> res = new HashMap<>();
        for (Vf v : loci)
            res.put(v, getBit(field, v.y, v.x, v.t));
        return res;
    }

    /** @return the bitwise NOT of the given BoolVf. */
    public static BoolVf not(BoolVf orig){
        BoolVf res = new BoolVf();
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolVf values. */
    public static BoolVf and(BoolVf a, BoolVf b){
        BoolVf res = new BoolVf();
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolVf values. */
    public static BoolVf or(BoolVf a, BoolVf b){
        BoolVf res = new BoolVf();
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolVf values. */
    public static BoolVf xor(BoolVf a, BoolVf b){
        BoolVf res = new BoolVf();
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolVf. */
    public static BoolVf lShift(BoolVf orig, int n){
        BoolVf res = new BoolVf();
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolVf. */
    public static BoolVf rShift(BoolVf orig, int n){
        BoolVf res = new BoolVf();
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a up-shifted BoolVf. */
    public static BoolVf uShift(BoolVf orig, int n){
        BoolVf res = new BoolVf();
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolVf. */
    public static BoolVf dShift(BoolVf orig, int n){
        BoolVf res = new BoolVf();
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    private static BoolVf maskData(BoolVf mask, BoolVf data, BoolVf neutral){
        return BoolVf.or(
                BoolVf.and(mask, data),
                BoolVf.and(BoolVf.not(mask), neutral)
        );
    }

    /** @return the OR reduction of the given transfer field. */
    public static BoolV redOrV(BoolVf orig){
        BoolVf maskedData = maskData(DATA_POS, orig, BoolVf.zeroes());
        BoolV target = BoolV.zeroes();
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the AND reduction of the given transfer field. */
    public static BoolV redAndV(BoolVf orig){
        BoolVf maskedData = maskData(DATA_POS, orig, BoolVf.ones());
        BoolV target = BoolV.ones();
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the XOR reduction of the given transfer field. */
    public static BoolV redXorV(BoolVf orig){
        BoolVf maskedData = maskData(DATA_POS, orig, BoolVf.zeroes());
        BoolV target = BoolV.zeroes();
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Vfs (*, *, n), or a 0 if it doesn't exist */
    public static BoolV[] redStackV0(BoolVf orig){
        BoolVf maskedData = maskData(DATA_POS, orig, BoolVf.zeroes());
        BoolV[] target = new BoolV[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolV.zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Vfs (*, *, n), or a 1 if it doesn't exist */
    public static BoolV[] redStackV1(BoolVf orig){
        BoolVf maskedData = maskData(DATA_POS, orig, BoolVf.ones());
        BoolV[] target = new BoolV[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolV.zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the clockwise rotation of the given BoolVf. */
    public static BoolVe rotateCW(BoolVf orig) {
        BoolVe res = new BoolVe();

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

        return res;
    }

    /** @return the counterclockwise rotation of the given BoolVf. */
    public static BoolVe rotateCCW(BoolVf orig){
        BoolVe res = new BoolVe();
        System.arraycopy(orig.lines, 0, res.lines, 0, orig.lines.length);
        return res;
    }


    /** @return the transfer result for the given BoolVf. */
    public static BoolFv transfer(BoolVf orig){
        BoolFv res = BoolFv.zeroes();

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
        BoolVf newBoolVf = new BoolVf();
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
}
