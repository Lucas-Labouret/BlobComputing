package language.field.boolField;

import language.utils.BoolFieldLine;
import language.utils.BoolFieldManager;
import language.utils.Border;
import medium.locusS.Face;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean language.field over face loci. */
public non-sealed class BoolF extends BoolFieldS<BoolF> {
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
    public BoolF(Border border) { this(border, true); }
    public BoolF(boolean register) { this(BoolFieldManager.DEFAULT_BORDER(), register); }
    public BoolF(Border border, boolean register) { super(HEIGHT, SPAN, BREADTH, border, register); }
    public BoolF(BoolF other) {
        super(HEIGHT, SPAN, BREADTH, other.border);
        for (int i = 0; i < lines.length; i++) lines[i] = new BoolFieldLine(other.lines[i]);
    }

    /** @return a new zero-filled BoolF. */
    public BoolF zeroes(){ zeroesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    public BoolF ones(){ onesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    public BoolF rand(){ randGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Sets a bit in the given BoolF. */
    public void setBit(int[] coord, boolean bit){
        if (coord == null || coord.length != 3) throw new IllegalArgumentException("Coordinates must have three elements.");
        setBitGeneric(HEIGHT, SPAN, BREADTH, this, coord[0], coord[1], coord[2], 0, bit);
    }

    /** Sets a bit in the given BoolF. */
    public void setBit(Face face, boolean bit){
        setBit(new int[]{face.y, face.x, face.t}, bit);
    }

    /** Gets a bit in the given BoolF */
    public boolean getBit(int[] coord){
        if (coord == null || coord.length != 3) throw new IllegalArgumentException("Coordinates must have three elements.");
        return getBitGeneric(HEIGHT, SPAN, BREADTH, this, coord[0], coord[1], coord[2], 0);
    }

    /** Gets a bit in the given BoolF */
    public boolean getBit(Face face){
        return getBit(new int[]{face.y, face.x, face.t});
    }

    /** Decode the given BoolF into a HashMap mapping each face in the given set to its corresponding bit value in the BoolF. */
    public HashMap<Face, Boolean> decode(HashSet<Face> faces) {
        HashMap<Face, Boolean> res = new HashMap<>();
        for (Face f : faces)
            res.put(f, getBit(f));
        return res;
    }

    /** @return the bitwise NOT of the given BoolF. */
    public BoolF not(BoolF orig){
        notGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** @return the bitwise AND of the given BoolF values. */
    public BoolF and(BoolF a, BoolF b){
        andGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return the bitwise OR of the given BoolF values. */
    public BoolF or(BoolF a, BoolF b){
        orGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return the bitwise XOR of the given BoolF values. */
    public BoolF xor(BoolF a, BoolF b){
        xorGeneric(HEIGHT, SPAN, BREADTH, a, b, this);
        return this;
    }

    /** @return a left-shifted BoolF. */
    public BoolF lShift(BoolF orig, int n){
        lShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a right-shifted BoolF. */
    public BoolF rShift(BoolF orig, int n){
        rShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a up-shifted BoolF. */
    public BoolF uShift(BoolF orig, int n){
        uShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return a down-shifted BoolF. */
    public BoolF dShift(BoolF orig, int n){
        dShiftGeneric(HEIGHT, SPAN, BREADTH, orig, n, this);
        return this;
    }

    /** @return the OR reduction of the given transfer language.field. */
    public BoolF redOr(BoolFv orig) { orig.redOr(this); return this; }
    /** @return the OR reduction of the given transfer language.field. */
    public BoolF redOr(BoolFe orig) { orig.redOr(this); return this; }

    /** @return the AND reduction of the given transfer language.field. */
    public BoolF redAnd(BoolFv orig) { orig.redAnd(this); return this; }
    /** @return the AND reduction of the given transfer language.field. */
    public BoolF redAnd(BoolFe orig) { orig.redAnd(this); return this; }

    /** @return the XOR reduction of the given transfer language.field. */
    public BoolF redXor(BoolFv orig) { orig.redXor(this); return this; }
    /** @return the XOR reduction of the given transfer language.field. */
    public BoolF redXor(BoolFe orig) { orig.redXor(this); return this; }

    /** @return the stack reduction 0 of the given transfer language.field */
    public static BoolF[] redStack0(BoolFv orig) { return orig.redStack0(); }
    /** @return the stack reduction 1 of the given transfer language.field */
    public static BoolF[] redStack1(BoolFv orig) { return orig.redStack1(); }
    /** @return the stack reduction 0 of the given transfer language.field */
    public static BoolF[] redStack0(BoolFe orig) { return orig.redStack0(); }
    /** @return the stack reduction 1 of the given transfer language.field */
    public static BoolF[] redStack1(BoolFe orig) { return orig.redStack1(); }

    /** @return whether this BoolF is equal to the given object. */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolF other)) return false;

        for (int i = 0; i < HEIGHT * SPAN * BREADTH; i++) {
            if (this.lines[i] == null && other.lines[i] == null) continue;
            if (this.lines[i] == null && other.lines[i] != null) return false;
            if (this.lines[i] != null && other.lines[i] == null) return false;

            BoolFieldLine thisLine = BoolFieldLine.and(this.lines[i], DATA_POS.lines[i]);
            BoolFieldLine otherLine = BoolFieldLine.and(other.lines[i], DATA_POS.lines[i]);
            if (!thisLine.equals(otherLine)) return false;
        }

        return true;
    }

    private BoolF copy(boolean register) {
        BoolF newBoolF = new BoolF(border, register);
        for (int i = 0; i < lines.length; i++)
            newBoolF.lines[i] = this.lines[i] == null ? null : this.lines[i].copy();
        return newBoolF;
    }

    @Override
    public BoolF copy(){ return copy(true); }

    @Override
    public BoolF cache() { return copy(false); }
}
