package field.boolField.fieldT;

import field.boolField.BoolFieldLine;
import field.boolField.fieldS.BoolE;
import medium.locusT.Ev;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer field from edge to vertex orientation. */
public class BoolEv extends BoolFieldT {
    static int HEIGHT = -1;
    static int SPAN = -1;
    static final int BREADTH = 2;

    /** Configures the dimensions used by BoolEv fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn, int maxBelongingEdgePerVertex) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        if (maxBelongingEdgePerVertex < 1) throw new IllegalArgumentException("There must exist at least one edge per vertex.");
        HEIGHT = maxVerticesPerColumn;
        SPAN = maxBelongingEdgePerVertex;
    }

    /** Creates a new BoolEv. */
    public BoolEv() {
        super(HEIGHT, SPAN, BREADTH);
    }

    private static BoolEv DATA_POS;
    private static HashMap<Integer, HashMap<Integer, BoolEv>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolEv pos, HashMap<Integer, HashMap<Integer, BoolEv>> masks){
        DATA_POS = pos;
        MASKS = masks;
    }

    /** @return a new BoolEv from the given broadcast field. */
    public static BoolEv fromBroadcast(BoolE orig){
        BoolEv res = new BoolEv();
        fromBroadcastGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return a new zero-filled BoolEv. */
    public static BoolEv zeroes(){
        BoolEv res = new BoolEv();
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolEv. */
    public static BoolEv ones(){
        BoolEv res = new BoolEv();
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new randomly initialized BoolEv. */
    public static BoolEv rand(){
        BoolEv res = new BoolEv();
        randGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** Sets a bit in the given BoolEv. */
    public static void setBit(BoolEv target, int y, int x, int t, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, s, bit);
    }

    /** Gets a bit in the given BoolEv */
    public static boolean getBit(BoolEv target, int y, int x, int t, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, s);
    }

    /** Decode the given BoolEv into a HashMap mapping each Ev locus in the given set to its corresponding bit value in the BoolEv. */
    public static HashMap<Ev, Boolean> decode(HashSet<Ev> loci, BoolEv field) {
        HashMap<Ev, Boolean> res = new HashMap<>();
        for (Ev e : loci)
            res.put(e, getBit(field, e.y, e.x, e.t, e.s));
        return res;
    }

    /** @return the bitwise NOT of the given BoolEv. */
    public static BoolEv not(BoolEv orig){
        BoolEv res = new BoolEv();
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolEv values. */
    public static BoolEv and(BoolEv a, BoolEv b){
        BoolEv res = new BoolEv();
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolEv values. */
    public static BoolEv or(BoolEv a, BoolEv b){
        BoolEv res = new BoolEv();
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolEv values. */
    public static BoolEv xor(BoolEv a, BoolEv b){
        BoolEv res = new BoolEv();
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolEv. */
    public static BoolEv lShift(BoolEv orig, int n){
        BoolEv res = new BoolEv();
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolEv. */
    public static BoolEv rShift(BoolEv orig, int n){
        BoolEv res = new BoolEv();
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a up-shifted BoolEv. */
    public static BoolEv uShift(BoolEv orig, int n){
        BoolEv res = new BoolEv();
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolEv. */
    public static BoolEv dShift(BoolEv orig, int n){
        BoolEv res = new BoolEv();
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    private static BoolEv maskData(BoolEv mask, BoolEv data, BoolEv neutral){
        return BoolEv.or(
                BoolEv.and(mask, data),
                BoolEv.and(BoolEv.not(mask), neutral)
        );
    }

    /** @return the OR reduction of the given transfer field. */
    public static BoolE redOrE(BoolEv orig){
        BoolEv maskedData = maskData(DATA_POS, orig, BoolEv.zeroes());
        BoolE target = BoolE.zeroes();
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the AND reduction of the given transfer field. */
    public static BoolE redAndE(BoolEv orig){
        BoolEv maskedData = maskData(DATA_POS, orig, BoolEv.ones());
        BoolE target = BoolE.ones();
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the XOR reduction of the given transfer field. */
    public static BoolE redXorE(BoolEv orig){
        BoolEv maskedData = maskData(DATA_POS, orig, BoolEv.zeroes());
        BoolE target = BoolE.zeroes();
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolE s.t. the n-th BoolE contains the bits of Evs (*, *, *, n), or a 0 if it doesn't exist */
    public static BoolE[] redStackE0(BoolEv orig){
        BoolEv maskedData = maskData(DATA_POS, orig, BoolEv.zeroes());
        BoolE[] target = new BoolE[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolE.zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolE s.t. the n-th BoolE contains the bits of Evs (*, *, *, n), or a 1 if it doesn't exist */
    public static BoolE[] redStackE1(BoolEv orig){
        BoolEv maskedData = maskData(DATA_POS, orig, BoolEv.ones());
        BoolE[] target = new BoolE[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolE.zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the clockwise rotation of the given BoolEv. */
    public static BoolEf rotateCW(BoolEv orig){
        BoolEf target = new BoolEf();
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            target.lines[index] = new BoolFieldLine(orig.lines[index]);
        }
        return target;
    }

    /** @return the counterclockwise rotation of the given BoolEv. */
    public static BoolEf rotateCCW(BoolEv orig){
        BoolEf target = new BoolEf();
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH - 1; k++) {
                target.lines[blockIndex + k] = new BoolFieldLine(orig.lines[blockIndex + (k + 1) % BREADTH]);
            }
        }
        return target;
    }

    /** @return the transfer result for the given BoolEv. */
    public static BoolVe transfer(BoolEv orig){
        BoolVe res = BoolVe.zeroes();

        int origLen = HEIGHT * SPAN * BREADTH;
        int targetLen = BoolVe.HEIGHT * BoolVe.BREADTH;

        for (int dy: MASKS.keySet()) for (int dx: MASKS.get(dy).keySet()){
            BoolEv mask = MASKS.get(dy).get(dx);
            applyTransferMask(origLen, targetLen, dy, dx, mask, orig, res);
        }

        return res;
    }

    /** @return a deep copy of this BoolEv. */
    @Override
    public BoolEv copy(){
        BoolEv newBoolEv = new BoolEv();
        for (int i = 0; i < lines.length; i++)
            newBoolEv.lines[i] = this.lines[i].copy();
        return newBoolEv;
    }

    /** @return whether this BoolEv is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolEv other)) return false;

        BoolEv thisClean = BoolEv.and(this, DATA_POS);
        BoolEv otherClean = BoolEv.and(other, DATA_POS);
        for (int i = 0; i < HEIGHT * SPAN * BREADTH; i++) {
            if (!thisClean.lines[i].equals(otherClean.lines[i])) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        BoolEv clean = BoolEv.and(this, DATA_POS);
        return toString(clean);
    }
}
