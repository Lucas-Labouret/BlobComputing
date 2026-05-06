package field.boolField.fieldT;

import field.boolField.BoolFieldLine;
import field.boolField.fieldS.BoolV;
import medium.locusT.Ve;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer field from vertex to edge orientation. */
public class BoolVe extends BoolFieldT {
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

    private static HashMap<Integer, HashMap<Integer, BoolVe>> MASKS;

    private static BoolVe DATA_POS;
    private static BoolVe DATA_END;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolVe pos, HashMap<Integer, HashMap<Integer, BoolVe>> masks){
        DATA_POS = pos;

        DATA_END = new BoolVe();
        computeDataEnd(HEIGHT, SPAN, BREADTH, DATA_POS, DATA_END);

        MASKS = masks;
    }

    /** Creates a new BoolVe. */
    public BoolVe() {
        super(HEIGHT, SPAN, BREADTH);
    }

    /** @return a new BoolVe from the given broadcast field. */
    public static BoolVe fromBroadcast(BoolV orig){
        BoolVe res = new BoolVe();
        fromBroadcastGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return a new zero-filled BoolVe. */
    public static BoolVe zeroes(){
        BoolVe res = new BoolVe();
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolVe. */
    public static BoolVe ones(){
        BoolVe res = new BoolVe();
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new randomly initialized BoolVe. */
    public static BoolVe rand(){
        BoolVe res = new BoolVe();
        randGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** Sets a bit in the given BoolVe. */
    public static void setBit(BoolVe target, int y, int x, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, s, bit);
    }

    /** Gets a bit in the given BoolVe */
    public static boolean getBit(BoolVe target, int y, int x, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, s);
    }

    /** Decode the given BoolVe into a HashMap mapping each Ve locus in the given set to its corresponding bit value in the BoolVe. */
    public static HashMap<Ve, Boolean> decode(HashSet<Ve> loci, BoolVe field) {
        HashMap<Ve, Boolean> res = new HashMap<>();
        for (Ve v : loci)
            res.put(v, getBit(field, v.y, v.x, v.t));
        return res;
    }

    /** @return the bitwise NOT of the given BoolVe. */
    public static BoolVe not(BoolVe orig){
        BoolVe res = new BoolVe();
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolVe values. */
    public static BoolVe and(BoolVe a, BoolVe b){
        BoolVe res = new BoolVe();
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolVe values. */
    public static BoolVe or(BoolVe a, BoolVe b){
        BoolVe res = new BoolVe();
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolVe values. */
    public static BoolVe xor(BoolVe a, BoolVe b){
        BoolVe res = new BoolVe();
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolVe. */
    public static BoolVe lShift(BoolVe orig, int n){
        BoolVe res = new BoolVe();
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolVe. */
    public static BoolVe rShift(BoolVe orig, int n){
        BoolVe res = new BoolVe();
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a up-shifted BoolVe. */
    public static BoolVe uShift(BoolVe orig, int n){
        BoolVe res = new BoolVe();
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolVe. */
    public static BoolVe dShift(BoolVe orig, int n){
        BoolVe res = new BoolVe();
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    private static BoolVe maskData(BoolVe mask, BoolVe data, BoolVe neutral){
        return BoolVe.or(
                BoolVe.and(mask, data),
                BoolVe.and(BoolVe.not(mask), neutral)
        );
    }

    /** @return the OR reduction of the given transfer field. */
    public static BoolV redOrV(BoolVe orig){
        BoolVe maskedData = maskData(DATA_POS, orig, BoolVe.zeroes());
        BoolV target = BoolV.zeroes();
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the AND reduction of the given transfer field. */
    public static BoolV redAndV(BoolVe orig){
        BoolVe maskedData = maskData(DATA_POS, orig, BoolVe.ones());
        BoolV target = BoolV.ones();
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the XOR reduction of the given transfer field. */
    public static BoolV redXorV(BoolVe orig){
        BoolVe maskedData = maskData(DATA_POS, orig, BoolVe.zeroes());
        BoolV target = BoolV.zeroes();
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Ves (*, *, n), or a 0 if it doesn't exist */
    public static BoolV[] redStackV0(BoolVe orig){
        BoolVe maskedData = maskData(DATA_POS, orig, BoolVe.zeroes());
        BoolV[] target = new BoolV[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolV.zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolV s.t. the n-th BoolV contains the bits of Ves (*, *, n), or a 1 if it doesn't exist */
    public static BoolV[] redStackV1(BoolVe orig){
        BoolVe maskedData = maskData(DATA_POS, orig, BoolVe.ones());
        BoolV[] target = new BoolV[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolV.zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the clockwise rotation of the given BoolVe. */
    public static BoolVf rotateCW(BoolVe orig) {
        BoolVf res = new BoolVf();
        System.arraycopy(orig.lines, 0, res.lines, 0, orig.lines.length);
        return res;
    }

    /** @return the counterclockwise rotation of the given BoolVe. */
    public static BoolVf rotateCCW(BoolVe orig){
        BoolVf res = BoolVf.zeroes();

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
        BoolEv res = BoolEv.zeroes();

        int origLen = HEIGHT * BREADTH;
        int targetLen = BoolEv.HEIGHT * BoolEv.SPAN * BoolEv.BREADTH;

        for (int dy: MASKS.keySet()) for (int dx: MASKS.get(dy).keySet()){
            BoolVe mask = MASKS.get(dy).get(dx);
            applyTransferMask(origLen, targetLen, dy, dx, mask, orig, res);
        }

        return res;
    }

    /** @return a deep copy of this BoolVe. */
    public BoolVe copy(){
        BoolVe newBoolVe = new BoolVe();
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
}
