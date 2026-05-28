package language.obj.field.boolField;

import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.locusS.Vertex;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean language.obj.field over vertex loci. */
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

    private static BoolV DATA_POS;

    public static void setDataPos(BoolV dataPos) {
        if (DATA_POS != null) throw new IllegalStateException("Data position has already been set.");
        DATA_POS = dataPos;
    }

    private static HashMap<Vertex, Vertex> TORUS;
    private static HashSet<Vertex> CORNERS;

    public static void setBorder(HashMap<Vertex, Vertex> torusBorder, HashSet<Vertex> torusCorners) {
        if (TORUS != null || CORNERS != null) throw new IllegalStateException("Border has already been set.");
        TORUS = torusBorder;
        CORNERS = torusCorners;
    }

    /** Creates a new BoolV. */
    public BoolV() { this(BoolFieldManager.DEFAULT_BORDER()); }
    public BoolV(Border border) { super(HEIGHT, SPAN, BREADTH, border); }

    /** @return the corresponding broadcast of the given BoolV. */
    public static BoolVe broadcastVe(BoolV orig) { return BoolVe.fromBroadcast(orig); }
    /** @return the corresponding broadcast of the given BoolV. */
    public static BoolVf broadcastVf(BoolV orig) { return BoolVf.fromBroadcast(orig); }

    /** @return a new zero-filled BoolV. */
    public static BoolV zeroes(Border border){
        BoolV res = new BoolV(border);
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new zero-filled BoolV using the default border. */
    public static BoolV zeroes(){ return zeroes(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new one-filled BoolV. */
    public static BoolV ones(Border border){
        BoolV res = new BoolV(border);
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolV using the default border. */
    public static BoolV ones(){ return ones(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new randomly initialized BoolV. */
    public static BoolV rand(Border border){
        BoolV res = new BoolV(border);
        randGeneric(HEIGHT, SPAN, BREADTH, res);

        if (border == Border.TORUS) {
            for (Vertex v : TORUS.keySet()) {
                Vertex torus = TORUS.get(v);
                setBit(res, torus, getBit(res, v));
            }

            boolean[] bits = new boolean[4];
            int index = 0;
            for (Vertex v : CORNERS) bits[index++] = getBit(res, v);
            boolean bit = bits[0] ^ bits[1] ^ bits[2] ^ bits[3];
            for (Vertex v : CORNERS) setBit(res, v, bit);
        }

        return res;
    }

    /** @return a new randomly initialized BoolV using the default border. */
    public static BoolV rand(){ return rand(BoolFieldManager.DEFAULT_BORDER()); }

    /** Sets a bit in the given BoolV. */
    public static void setBit(BoolV target, int y, int x, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, 0, bit);

        if (target.border == Border.TORUS) {
            for (Vertex v : TORUS.keySet()) {
                Vertex torus = TORUS.get(v);
                if (v.y == y && v.x == x) {
                    setBitGeneric(HEIGHT, SPAN, BREADTH, target, torus.y, torus.x, 0, 0, bit);
                } else if (torus.y == y && torus.x == x) {
                    setBitGeneric(HEIGHT, SPAN, BREADTH, target, v.y, v.x, 0, 0, bit);
                }
            }

            for (Vertex v : CORNERS) {
                if (v.y == y && v.x == x) for (Vertex corner : CORNERS)
                    setBitGeneric(HEIGHT, SPAN, BREADTH, target, corner.y, corner.x, 0, 0, bit);
            }
        }
    }

    /** Sets a bit in the given BoolV. */
    public static void setBit(BoolV target, Vertex vertex, boolean bit){
        setBit(target, vertex.y, vertex.x, bit);
    }

    /** Gets a bit in the given BoolV */
    public static boolean getBit(BoolV target, int y, int x){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, 0, 0);
    }

    /** Gets a bit in the given BoolV */
    public static boolean getBit(BoolV target, Vertex vertex){
        return getBit(target, vertex.y, vertex.x);
    }

    /** Decode the given BoolV into a HashMap mapping each vertex in the given set to its corresponding bit value in the BoolV. */
    public static HashMap<Vertex, Boolean> decode(HashSet<Vertex> vertices, BoolV field) {
        HashMap<Vertex, Boolean> res = new HashMap<>();
        for (Vertex v : vertices)
            res.put(v, getBit(field, v));
        return res;
    }

    /** @return the bitwise NOT of the given BoolV. */
    public static BoolV not(BoolV orig){
        BoolV res = new BoolV(orig.border);
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolV values. */
    public static BoolV and(BoolV a, BoolV b){
        BoolV res = new BoolV(a.border);
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolV values. */
    public static BoolV or(BoolV a, BoolV b){
        BoolV res = new BoolV(a.border);
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolV values. */
    public static BoolV xor(BoolV a, BoolV b){
        BoolV res = new BoolV(a.border);
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolV. */
    public static BoolV lShift(BoolV orig, int n){
        BoolV res = new BoolV(orig.border);
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolV. */
    public static BoolV rShift(BoolV orig, int n){
        BoolV res = new BoolV(orig.border);
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return an up-shifted BoolV. */
    public static BoolV uShift(BoolV orig, int n){
        BoolV res = new BoolV(orig.border);
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolV. */
    public static BoolV dShift(BoolV orig, int n){
        BoolV res = new BoolV(orig.border);
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return the OR reduction of the given transfer language.obj.field. */
    public static BoolV redOrVe(BoolVe orig) { return BoolVe.redOrV(orig); }
    /** @return the OR reduction of the given transfer language.obj.field. */
    public static BoolV redOrVf(BoolVf orig) { return BoolVf.redOrV(orig); }

    /** @return the AND reduction of the given transfer language.obj.field. */
    public static BoolV redAndVe(BoolVe orig) { return BoolVe.redAndV(orig); }
    /** @return the AND reduction of the given transfer language.obj.field. */
    public static BoolV redAndVf(BoolVf orig) { return BoolVf.redAndV(orig); }

    /** @return the XOR reduction of the given transfer language.obj.field. */
    public static BoolV redXorVe(BoolVe orig) { return BoolVe.redXorV(orig); }
    /** @return the XOR reduction of the given transfer language.obj.field. */
    public static BoolV redXorVf(BoolVf orig) { return BoolVf.redXorV(orig); }

    /** @return the stack reduction 0 of the given transfer language.obj.field */
    public static BoolV[] redStackV0(BoolVe orig) { return BoolVe.redStackV0(orig); }
    /** @return the stack reduction 1 of the given transfer language.obj.field */
    public static BoolV[] redStackV1(BoolVe orig) { return BoolVe.redStackV1(orig); }
    /** @return the stack reduction 0 of the given transfer language.obj.field */
    public static BoolV[] redStackV0(BoolVf orig) { return BoolVf.redStackV0(orig); }
    /** @return the stack reduction 1 of the given transfer language.obj.field */
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
        BoolV newBoolV = new BoolV(border);
        for (int i = 0; i < lines.length; i++)
            newBoolV.lines[i] = this.lines[i].copy();
        return newBoolV;
    }
}
