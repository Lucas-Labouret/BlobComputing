package language.obj.field.boolField.fieldS;

import language.utils.Border;
import language.utils.BoolFieldManager;
import language.obj.field.boolField.fieldT.BoolFe;
import language.obj.field.boolField.fieldT.BoolFv;
import medium.locusS.Face;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean language.obj.field over face loci. */
public class BoolF extends BoolFieldS {
    private static int HEIGHT = -1;
    private static int SPAN = -1;
    private static final int BREADTH = 1;

    /** Configures the dimensions used by face fields. */
    public static void SET_PARAMS(int maxVerticesPerColumn, int maxBelongingFacePerVertex) {
        if (HEIGHT != -1) throw new IllegalStateException("Size has already been set.");
        if (maxVerticesPerColumn < 1) throw new IllegalArgumentException("There must exist at least one vertex.");
        if (maxBelongingFacePerVertex < 1) throw new IllegalArgumentException("There must exist at least one edge per vertex.");
        HEIGHT = maxVerticesPerColumn;
        SPAN = maxBelongingFacePerVertex;
    }

    private static BoolF DATA_POS;

    public static void setDataPos(BoolF dataPos) {
        if (DATA_POS != null) throw new IllegalStateException("Data position has already been set.");
        DATA_POS = dataPos;
    }

    /** Creates a new BoolF. */
    public BoolF() { this(BoolFieldManager.DEFAULT_BORDER()); }
    public BoolF(Border border) { super(HEIGHT, SPAN, BREADTH, border); }

    /** @return the corresponding broadcast of the given BoolF. */
    public static BoolFv broadcastFv(BoolF orig) { return BoolFv.fromBroadcast(orig); }
    /** @return the corresponding broadcast of the given BoolF. */
    public static BoolFe broadcastFe(BoolF orig) { return BoolFe.fromBroadcast(orig); }

    /** @return a new zero-filled BoolF. */
    public static BoolF zeroes(Border border){
        BoolF res = new BoolF(border);
        zeroesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new zero-filled BoolF using the default border. */
    public static BoolF zeroes(){ return zeroes(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new one-filled BoolF. */
    public static BoolF ones(Border border){
        BoolF res = new BoolF(border);
        onesGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new one-filled BoolF using the default border. */
    public static BoolF ones(){ return ones(BoolFieldManager.DEFAULT_BORDER()); }

    /** @return a new randomly initialized BoolF. */
    public static BoolF rand(Border border){
        BoolF res = new BoolF(border);
        randGeneric(HEIGHT, SPAN, BREADTH, res);
        return res;
    }

    /** @return a new randomly initialized BoolF using the default border. */
    public static BoolF rand(){ return rand(BoolFieldManager.DEFAULT_BORDER()); }

    /** Sets a bit in the given BoolF. */
    public static void setBit(BoolF target, int y, int x, int t, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, 0, bit);
    }

    /** Sets a bit in the given BoolF. */
    public static void setBit(BoolF target, Face face, boolean bit){
        setBit(target, face.y, face.x, face.t, bit);
    }

    /** Gets a bit in the given BoolF */
    public static boolean getBit(BoolF target, int y, int x, int t){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, target, y, x, t, 0);
    }

    /** Gets a bit in the given BoolF */
    public static boolean getBit(BoolF target, Face face){
        return getBit(target, face.y, face.x, face.t);
    }

    /** Decode the given BoolF into a HashMap mapping each face in the given set to its corresponding bit value in the BoolF. */
    public static HashMap<Face, Boolean> decode(HashSet<Face> faces, BoolF field) {
        HashMap<Face, Boolean> res = new HashMap<>();
        for (Face f : faces)
            res.put(f, getBit(field, f));
        return res;
    }

    /** @return the bitwise NOT of the given BoolF. */
    public static BoolF not(BoolF orig){
        BoolF res = new BoolF(orig.border);
        notGeneric(HEIGHT, SPAN, BREADTH, orig, res);
        return res;
    }

    /** @return the bitwise AND of the given BoolF values. */
    public static BoolF and(BoolF a, BoolF b){
        BoolF res = new BoolF(a.border);
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise OR of the given BoolF values. */
    public static BoolF or(BoolF a, BoolF b){
        BoolF res = new BoolF(a.border);
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return the bitwise XOR of the given BoolF values. */
    public static BoolF xor(BoolF a, BoolF b){
        BoolF res = new BoolF(a.border);
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, res);
        return res;
    }

    /** @return a left-shifted BoolF. */
    public static BoolF lShift(BoolF orig, int n){
        BoolF res = new BoolF(orig.border);
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a right-shifted BoolF. */
    public static BoolF rShift(BoolF orig, int n){
        BoolF res = new BoolF(orig.border);
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a up-shifted BoolF. */
    public static BoolF uShift(BoolF orig, int n){
        BoolF res = new BoolF(orig.border);
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return a down-shifted BoolF. */
    public static BoolF dShift(BoolF orig, int n){
        BoolF res = new BoolF(orig.border);
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, res);
        return res;
    }

    /** @return the OR reduction of the given transfer language.obj.field. */
    public static BoolF redOrFv(BoolFv orig) { return BoolFv.redOrF(orig); }
    /** @return the OR reduction of the given transfer language.obj.field. */
    public static BoolF redOrFe(BoolFe orig) { return BoolFe.redOrF(orig); }

    /** @return the AND reduction of the given transfer language.obj.field. */
    public static BoolF redAndFv(BoolFv orig) { return BoolFv.redAndF(orig); }
    /** @return the AND reduction of the given transfer language.obj.field. */
    public static BoolF redAndFe(BoolFe orig) { return BoolFe.redAndF(orig); }

    /** @return the XOR reduction of the given transfer language.obj.field. */
    public static BoolF redXorFv(BoolFv orig) { return BoolFv.redXorF(orig); }
    /** @return the XOR reduction of the given transfer language.obj.field. */
    public static BoolF redXorFe(BoolFe orig) { return BoolFe.redXorF(orig); }

    /** @return the stack reduction 0 of the given transfer language.obj.field */
    public static BoolF[] redStackF0(BoolFv orig) { return BoolFv.redStackF0(orig); }
    /** @return the stack reduction 1 of the given transfer language.obj.field */
    public static BoolF[] redStackF1(BoolFv orig) { return BoolFv.redStackF1(orig); }
    /** @return the stack reduction 0 of the given transfer language.obj.field */
    public static BoolF[] redStackF0(BoolFe orig) { return BoolFe.redStackF0(orig); }
    /** @return the stack reduction 1 of the given transfer language.obj.field */
    public static BoolF[] redStackF1(BoolFe orig) { return BoolFe.redStackF1(orig); }

    /** @return a deep copy of this BoolF. */
    @Override
    public BoolF copy(){
        BoolF newBoolF = new BoolF(border);
        for (int i = 0; i < lines.length; i++)
            newBoolF.lines[i] = this.lines[i].copy();
        return newBoolF;
    }

    /** @return whether this BoolF is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolF other)) return false;
        for (int i = 0; i < HEIGHT * SPAN * BREADTH; i++)
            if (!this.lines[i].equals(other.lines[i])) return false;
        return true;
    }
}
