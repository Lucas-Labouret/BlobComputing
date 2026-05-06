package field.boolField.fieldT;

import field.boolField.BoolFieldLine;
import field.boolField.fieldS.BoolF;
import medium.locusT.Fv;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer field from face to vertex orientation. */
public class BoolFv extends BoolFieldT {
    static int HEIGHT = -1;
    static int SPAN = -1;
    static final int BREADTH = 3;

    /** Configures the dimensions used by BoolFv fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn, int maxBelongingFacePerVertex) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        if (maxBelongingFacePerVertex < 1) throw new IllegalArgumentException("There must exist at least one edge per vertex.");
        HEIGHT = maxVerticesPerColumn;
        SPAN = maxBelongingFacePerVertex;
    }

    /** Creates a new BoolFv. */
    public BoolFv() {
        super(HEIGHT, SPAN, BREADTH);
    }

    private static BoolFv DATA_POS;
    private static HashMap<Integer, HashMap<Integer, BoolFv>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolFv pos, HashMap<Integer, HashMap<Integer, BoolFv>> masks){
        DATA_POS = pos;
        MASKS = masks;
    }

    /** @return a new BoolFv from the given broadcast field. */
    public static BoolFv fromBroadcast(BoolF orig){
        BoolFv res = new BoolFv();
        fromBroadcastGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return a new zero-filled BoolFv. */
    public static BoolFv zeroes(){
        BoolFv res = new BoolFv();
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolFv. */
    public static BoolFv ones(){
        BoolFv res = new BoolFv();
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new randomly initialized BoolFv. */
    public static BoolFv rand(){
        BoolFv res = new BoolFv();
        randGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** Sets a bit in the given BoolFv. */
    public static void setBit(BoolFv target, int y, int x, int t, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, s, bit);
    }

    /** Gets a bit in the given BoolFv */
    public static boolean getBit(BoolFv target, int y, int x, int t, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, s);
    }

    /** Decode the given BoolFv into a HashMap mapping each Fv locus in the given set to its corresponding bit value in the BoolFv. */
    public static HashMap<Fv, Boolean> decode(HashSet<Fv> loci, BoolFv field) {
        HashMap<Fv, Boolean> res = new HashMap<>();
        for (Fv f : loci)
            res.put(f, getBit(field, f.y, f.x, f.t, f.s));
        return res;
    }

    /** @return the bitwise NOT of the given BoolFv. */
    public static BoolFv not(BoolFv orig){
        BoolFv res = new BoolFv();
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolFv values. */
    public static BoolFv and(BoolFv a, BoolFv b){
        BoolFv res = new BoolFv();
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolFv values. */
    public static BoolFv or(BoolFv a, BoolFv b){
        BoolFv res = new BoolFv();
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolFv values. */
    public static BoolFv xor(BoolFv a, BoolFv b){
        BoolFv res = new BoolFv();
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolFv. */
    public static BoolFv lShift(BoolFv orig, int n){
        BoolFv res = new BoolFv();
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolFv. */
    public static BoolFv rShift(BoolFv orig, int n){
        BoolFv res = new BoolFv();
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a up-shifted BoolFv. */
    public static BoolFv uShift(BoolFv orig, int n){
        BoolFv res = new BoolFv();
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolFv. */
    public static BoolFv dShift(BoolFv orig, int n){
        BoolFv res = new BoolFv();
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    private static BoolFv maskData(BoolFv mask, BoolFv data, BoolFv neutral){
        return BoolFv.or(
                BoolFv.and(mask, data),
                BoolFv.and(BoolFv.not(mask), neutral)
        );
    }

    /** @return the OR reduction of the given transfer field. */
    public static BoolF redOrF(BoolFv orig){
        BoolFv maskedData = maskData(DATA_POS, orig, BoolFv.zeroes());
        BoolF target = BoolF.zeroes();
        redOrGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the AND reduction of the given transfer field. */
    public static BoolF redAndF(BoolFv orig){
        BoolFv maskedData = maskData(DATA_POS, orig, BoolFv.ones());
        BoolF target = BoolF.ones();
        redAndGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the XOR reduction of the given transfer field. */
    public static BoolF redXorF(BoolFv orig){
        BoolFv maskedData = maskData(DATA_POS, orig, BoolFv.zeroes());
        BoolF target = BoolF.zeroes();
        redXorGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolF s.t. the n-th BoolF contains the bits of Fvs (*, *, *, n), or a 0 if it doesn't exist */
    public static BoolF[] redStackF0(BoolFv orig){
        BoolFv maskedData = maskData(DATA_POS, orig, BoolFv.zeroes());
        BoolF[] target = new BoolF[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolF.zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return an array of BoolF s.t. the n-th BoolF contains the bits of Fvs (*, *, *, n), or a 1 if it doesn't exist */
    public static BoolF[] redStackF1(BoolFv orig){
        BoolFv maskedData = maskData(DATA_POS, orig, BoolFv.ones());
        BoolF[] target = new BoolF[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = BoolF.zeroes();
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);
        return target;
    }

    /** @return the clockwise rotation of the given BoolFv. */
    public static BoolFe rotateCW(BoolFv orig){
        BoolFe target = new BoolFe();
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++) {
            int index = (i * SPAN + j) * BREADTH + k;
            target.lines[index] = new BoolFieldLine(orig.lines[index]);
        }
        return target;
    }

    /** @return the counterclockwise rotation of the given BoolFv. */
    public static BoolFe rotateCCW(BoolFv orig){
        BoolFe target = new BoolFe();
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH - 1; k++) {
                target.lines[blockIndex + k] = new BoolFieldLine(orig.lines[blockIndex + (k + 1) % BREADTH]);
            }
        }
        return target;
    }

    /** @return the transfer result for the given BoolFv. */
    public static BoolVf transfer(BoolFv orig){
        BoolVf res = BoolVf.zeroes();

        int origLen = HEIGHT * SPAN * BREADTH;
        int targetLen = BoolVf.HEIGHT * BoolVf.BREADTH;

        for (int dy: MASKS.keySet()) for (int dx: MASKS.get(dy).keySet()){
            BoolFv mask = MASKS.get(dy).get(dx);
            applyTransferMask(origLen, targetLen, dy, dx, mask, orig, res);
        }

        return res;
    }

    /** @return a deep copy of this BoolFv. */
    @Override
    public BoolFv copy(){
        BoolFv newBoolFv = new BoolFv();
        for (int i = 0; i < lines.length; i++)
            newBoolFv.lines[i] = this.lines[i].copy();
        return newBoolFv;
    }

    /** @return whether this BoolFv is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolFv other)) return false;

        BoolFv thisClean = BoolFv.and(this, DATA_POS);
        BoolFv otherClean = BoolFv.and(other, DATA_POS);
        for (int i = 0; i < HEIGHT * SPAN * BREADTH; i++) {
            if (!thisClean.lines[i].equals(otherClean.lines[i])) return false;
        }
        return true;
    }

    @Override
    public String toString() {
        BoolFv clean = BoolFv.and(this, DATA_POS);
        return toString(clean);
    }
}
