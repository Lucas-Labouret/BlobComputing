package field.boolField.fieldS;

import field.boolField.fieldT.BoolVe;
import field.boolField.fieldT.BoolVf;
import medium.locusS.Vertex;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean field over vertex loci. */
public class BoolV extends BoolFieldS {
    private static int HEIGHT = -1;
    private static final int SPAN = 1;
    private static final int BREADTH = 1;

    /** Configures the dimensions used by vertex fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        HEIGHT = maxVerticesPerColumn;
    }

    /** Creates a new BoolV. */
    public BoolV() { super(HEIGHT, SPAN, BREADTH); }

    /** @return the corresponding broadcast of the given BoolV. */
    public static BoolVe broadcastVe(BoolV orig) { return BoolVe.fromBroadcast(orig); }
    /** @return the corresponding broadcast of the given BoolV. */
    public static BoolVf broadcastVf(BoolV orig) { return BoolVf.fromBroadcast(orig); }

    /** @return a new zero-filled BoolV. */
    public static BoolV zeroes(){
        BoolV res = new BoolV();
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolV. */
    public static BoolV ones(){
        BoolV res = new BoolV();
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new randomly initialized BoolV. */
    public static BoolV rand(){
        BoolV res = new BoolV();
        randGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** Sets a bit in the given BoolV. */
    public static void setBit(BoolV target, int y, int x, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, 0, bit);
    }

    /** Gets a bit in the given BoolV */
    public static boolean getBit(BoolV target, int y, int x){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, 0);
    }

    /** Decode the given BoolV into a HashMap mapping each vertex in the given set to its corresponding bit value in the BoolV. */
    public static HashMap<Vertex, Boolean> decode(HashSet<Vertex> vertices, BoolV field) {
        HashMap<Vertex, Boolean> res = new HashMap<>();
        for (Vertex v : vertices)
            res.put(v, getBit(field, v.y, v.x));
        return res;
    }

    /** @return the bitwise NOT of the given BoolV. */
    public static BoolV not(BoolV orig){
        BoolV res = new BoolV();
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolV values. */
    public static BoolV and(BoolV a, BoolV b){
        BoolV res = new BoolV();
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolV values. */
    public static BoolV or(BoolV a, BoolV b){
        BoolV res = new BoolV();
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolV values. */
    public static BoolV xor(BoolV a, BoolV b){
        BoolV res = new BoolV();
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolV. */
    public static BoolV lShift(BoolV orig, int n){
        BoolV res = new BoolV();
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolV. */
    public static BoolV rShift(BoolV orig, int n){
        BoolV res = new BoolV();
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a up-shifted BoolV. */
    public static BoolV uShift(BoolV orig, int n){
        BoolV res = new BoolV();
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolV. */
    public static BoolV dShift(BoolV orig, int n){
        BoolV res = new BoolV();
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return the OR reduction of the given transfer field. */
    public static BoolV redOrVe(BoolVe orig) { return BoolVe.redOrV(orig); }
    /** @return the OR reduction of the given transfer field. */
    public static BoolV redOrVf(BoolVf orig) { return BoolVf.redOrV(orig); }

    /** @return the AND reduction of the given transfer field. */
    public static BoolV redAndVe(BoolVe orig) { return BoolVe.redAndV(orig); }
    /** @return the AND reduction of the given transfer field. */
    public static BoolV redAndVf(BoolVf orig) { return BoolVf.redAndV(orig); }

    /** @return the XOR reduction of the given transfer field. */
    public static BoolV redXorVe(BoolVe orig) { return BoolVe.redXorV(orig); }
    /** @return the XOR reduction of the given transfer field. */
    public static BoolV redXorVf(BoolVf orig) { return BoolVf.redXorV(orig); }

    /** @return the stack reduction 0 of the given transfer field */
    public static BoolV[] redStackV0(BoolVe orig) { return BoolVe.redStackV0(orig); }
    /** @return the stack reduction 1 of the given transfer field */
    public static BoolV[] redStackV1(BoolVe orig) { return BoolVe.redStackV1(orig); }
    /** @return the stack reduction 0 of the given transfer field */
    public static BoolV[] redStackV0(BoolVf orig) { return BoolVf.redStackV0(orig); }
    /** @return the stack reduction 1 of the given transfer field */
    public static BoolV[] redStackV1(BoolVf orig) { return BoolVf.redStackV1(orig); }

    /** @return whether this BoolV is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolV other)) return false;
        for (int i = 0; i < HEIGHT * SPAN * BREADTH; i++)
            if (!this.lines[i].equals(other.lines[i])) return false;
        return true;
    }

    /** @return a deep copy of this BoolV. */
    @Override
    public BoolV copy(){
        BoolV newBoolV = new BoolV();
        for (int i = 0; i < lines.length; i++)
            newBoolV.lines[i] = this.lines[i].copy();
        return newBoolV;
    }
}
