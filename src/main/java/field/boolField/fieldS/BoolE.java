package field.boolField.fieldS;

import field.boolField.fieldT.BoolEf;
import field.boolField.fieldT.BoolEv;
import medium.locusS.Edge;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean field over edge loci. */
public class BoolE extends BoolFieldS {
    private static int HEIGHT = -1;
    private static int SPAN = -1;
    private static final int BREADTH = 1;
    
    /** Configures the dimensions used by edge fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn, int maxBelongingEdgePerVertex) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        if (maxBelongingEdgePerVertex < 1) throw new IllegalArgumentException("There must exist at least one edge per vertex.");
        HEIGHT = maxVerticesPerColumn;
        SPAN = maxBelongingEdgePerVertex;
    }
    
    /** Creates a new BoolE. */
    public BoolE() {
        super(HEIGHT, SPAN, BREADTH);
    }

    /** @return the corresponding broadcast of the given BoolE. */
    public static BoolEv broadcastEv(BoolE orig) { return BoolEv.fromBroadcast(orig); }
    /** @return the corresponding broadcast of the given BoolE. */
    public static BoolEf broadcastEf(BoolE orig) { return BoolEf.fromBroadcast(orig); }

    /** @return a new zero-filled BoolE. */
    public static BoolE zeroes(){
        BoolE res = new BoolE();
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolE. */
    public static BoolE ones(){
        BoolE res = new BoolE();
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new randomly initialized BoolE. */
    public static BoolE rand(){
        BoolE res = new BoolE();
        randGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** Sets a bit in the given BoolE. */
    public static void setBit(BoolE target, int y, int x, int t, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, 0, bit);
    }

    /** Gets a bit in the given BoolE */
    public static boolean getBit(BoolE target, int y, int x, int t){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, 0);
    }

    /** Decode the given BoolE into a HashMap mapping each edge in the given set to its corresponding bit value in the BoolE. */
    public static HashMap<Edge, Boolean> decode(HashSet<Edge> edges, BoolE field) {
        HashMap<Edge, Boolean> res = new HashMap<>();
        for (Edge e : edges)
            res.put(e, getBit(field, e.y, e.x, e.t));
        return res;
    }

    /** @return the bitwise NOT of the given BoolE. */
    public static BoolE not(BoolE orig){
        BoolE res = new BoolE();
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolE values. */
    public static BoolE and(BoolE a, BoolE b){
        BoolE res = new BoolE();
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolE values. */
    public static BoolE or(BoolE a, BoolE b){
        BoolE res = new BoolE();
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolE values. */
    public static BoolE xor(BoolE a, BoolE b){
        BoolE res = new BoolE();
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolE. */
    public static BoolE lShift(BoolE orig, int n){
        BoolE res = new BoolE();
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolE. */
    public static BoolE rShift(BoolE orig, int n){
        BoolE res = new BoolE();
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return an up-shifted BoolE. */
    public static BoolE uShift(BoolE orig, int n){
        BoolE res = new BoolE();
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolE. */
    public static BoolE dShift(BoolE orig, int n){
        BoolE res = new BoolE();
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return the OR reduction of the given transfer field. */
    public static BoolE redOrEv(BoolEv orig) { return BoolEv.redOrE(orig); }
    /** @return the OR reduction of the given transfer field. */
    public static BoolE redOrEf(BoolEf orig) { return BoolEf.redOrE(orig); }

    /** @return the AND reduction of the given transfer field. */
    public static BoolE redAndEv(BoolEv orig) { return BoolEv.redAndE(orig); }
    /** @return the AND reduction of the given transfer field. */
    public static BoolE redAndEf(BoolEf orig) { return BoolEf.redAndE(orig); }

    /** @return the XOR reduction of the given transfer field. */
    public static BoolE redXorEv(BoolEv orig) { return BoolEv.redXorE(orig); }
    /** @return the XOR reduction of the given transfer field. */
    public static BoolE redXorEf(BoolEf orig) { return BoolEf.redXorE(orig); }

    /** @return the stack reduction 0 of the given transfer field */
    public static BoolE[] redStackE0(BoolEv orig) { return BoolEv.redStackE0(orig); }
    /** @return the stack reduction 1 of the given transfer field */
    public static BoolE[] redStackE1(BoolEv orig) { return BoolEv.redStackE1(orig); }
    /** @return the stack reduction 0 of the given transfer field */
    public static BoolE[] redStackE0(BoolEf orig) { return BoolEf.redStackE0(orig); }
    /** @return the stack reduction 1 of the given transfer field */
    public static BoolE[] redStackE1(BoolEf orig) { return BoolEf.redStackE1(orig); }

    /** @return a deep copy of this BoolE. */
    @Override
    public BoolE copy(){
        BoolE newBoolE = new BoolE();
        for (int i = 0; i < lines.length; i++)
            newBoolE.lines[i] = this.lines[i].copy();
        return newBoolE;
    }

    /** @return whether this BoolE is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolE other)) return false;
        for (int i = 0; i < HEIGHT * SPAN * BREADTH; i++)
            if (!this.lines[i].equals(other.lines[i])) return false;
        return true;
    }
}
