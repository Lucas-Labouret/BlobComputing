package language.field.boolField;

import language.field.BoolFieldLine;
import language.field.BoolFieldManager;
import language.field.Border;
import language.field.Coord2D;
import medium.locusT.Ef;

import java.util.HashMap;
import java.util.HashSet;

/** Represents a boolean transfer field from edge to face orientation. */
public non-sealed class BoolEf extends BoolFieldT<BoolEf> {
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
    public BoolEf() { this(BoolFieldManager.DEFAULT_BORDER()); }
    public BoolEf(Border border) { this(border, true); }
    public BoolEf(boolean register) { this(BoolFieldManager.DEFAULT_BORDER(), register); }
    public BoolEf(Border border, boolean register) { super(HEIGHT, SPAN, BREADTH, border, register); }
    public BoolEf(BoolEf other) {
        super(HEIGHT, SPAN, BREADTH, other.border);
        for (int i = 0; i < lines.length; i++) lines[i] = new BoolFieldLine(other.lines[i]);
    }

    private static BoolEf DATA_POS;
    private static HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> MASKS;

    /** Configures the precomputed masks used for transfers. */
    public static void SET_MASKS(BoolEf pos, HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks){
        DATA_POS = pos;
        MASKS = masks;
    }

    private static HashSet<Ef> MIRROR;
    private static HashMap<Ef, Ef> TORUS;

    public static void setBorder(HashMap<Ef, Ef> torus, HashSet<Ef> mirror) {
        MIRROR = mirror;
        TORUS = torus;
    }

    /** @return a new BoolEf from the given broadcast field. */
    public BoolEf broadcast(BoolE orig){
        broadcastGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** Fills this BoolEf with zeroes. */
    public BoolEf zeroes(){ zeroesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Fills this BoolEf with ones. */
    public BoolEf ones(){ onesGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Randomly fills this BoolEf. */
    public BoolEf rand(){ randGeneric(HEIGHT, SPAN, BREADTH, this); return this; }

    /** Sets a bit in this BoolEf. */
    public void setBit(int y, int x, int t, int s, boolean bit){
        setBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, t, s, bit);
    }

    /** Sets a bit in this BoolEf. */
    public void setBit(Ef locus, boolean bit){
        setBit(locus.y, locus.x, locus.t, locus.s, bit);
    }

    /** Gets a bit in this BoolEf */
    public boolean getBit(int y, int x, int t, int s){
        return getBitGeneric(HEIGHT, SPAN, BREADTH, this, y, x, t, s);
    }

    /** Gets a bit in this BoolEf */
    public boolean getBit(Ef locus){
        return getBit(locus.y, locus.x, locus.t, locus.s);
    }

    /** Decode this BoolEf into a HashMap mapping each Ef locus in the given set to its corresponding bit value in the BoolEf. */
    public HashMap<Ef, Boolean> decode(HashSet<Ef> loci) {
        HashMap<Ef, Boolean> res = new HashMap<>();
        for (Ef e : loci)
            res.put(e, getBit(e));
        return res;
    }

    /** Set this BoolEf to the bitwise not of orig. */
    public BoolEf not(BoolEf orig){
        notGeneric(HEIGHT, SPAN, BREADTH, orig, this);
        return this;
    }

    /** Set this BoolEf to the bitwise AND of a and b. */
    public BoolEf and(BoolEf a, BoolEf b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::and);
        return this;
    }

    /** Set this BoolEf to the bitwise OR of a and b. */
    public BoolEf or(BoolEf a, BoolEf b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::or);
        return this;
    }

    /** Set this BoolEf to the bitwise XOR of a and b. */
    public BoolEf xor(BoolEf a, BoolEf b){
        binopGeneric(HEIGHT, SPAN, BREADTH, a, b, this, BoolFieldLine::xor);
        return this;
    }

    private static BoolEf maskData(BoolEf mask, BoolEf data, BoolEf neutral){
        return new BoolEf(false).or(
                new BoolEf(false).and(mask, data),
                new BoolEf(false).and(new BoolEf(false).not(mask), neutral)
        );
    }

    /** Set this BoolEf to the AND reduction of the given transfer field. */
    public void redAnd(BoolE target){
        target.ones();
        BoolEf maskedData = maskData(DATA_POS, this, new BoolEf(false).ones());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::and);

        if (this.border == Border.TORUS) for (Ef ef: TORUS.keySet()) {
            Ef torus = TORUS.get(ef);
            boolean bit = this.getBit(ef.y, ef.x, ef.t, ef.s) & this.getBit(torus.y, torus.x, torus.t, torus.s);
            target.setBit(ef.y, ef.x, ef.t, bit);
            target.setBit(torus.y, torus.x, torus.t, bit);

        }
    }

    /** Set this BoolEf to the OR reduction of the given transfer field. */
    public void redOr(BoolE target){
        target.zeroes();
        BoolEf maskedData = maskData(DATA_POS, this, new BoolEf(false).zeroes());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::or);

        if (this.border == Border.TORUS) for (Ef ef: TORUS.keySet()) {
            Ef torus = TORUS.get(ef);
            boolean bit = this.getBit(ef.y, ef.x, ef.t, ef.s) | this.getBit(torus.y, torus.x, torus.t, torus.s);
            target.setBit(ef.y, ef.x, ef.t, bit);
            target.setBit(torus.y, torus.x, torus.t, bit);
        }
    }

    /** Set this BoolEf to the XOR reduction of the given transfer field. */
    public void redXor(BoolE target){
        target.zeroes();
        BoolEf maskedData = maskData(DATA_POS, this, new BoolEf(false).zeroes());
        redGeneric(HEIGHT, SPAN, BREADTH, target, maskedData, BoolFieldLine::xor);

        if (this.border == Border.MIRROR) for (Ef m: MIRROR) target.setBit(m.y, m.x, m.t, false);
        if (this.border == Border.TORUS) for (Ef ef: TORUS.keySet()) {
            Ef torus = TORUS.get(ef);
            boolean bit = this.getBit(ef.y, ef.x, ef.t, ef.s) ^ this.getBit(torus.y, torus.x, torus.t, torus.s);
            target.setBit(ef.y, ef.x, ef.t, bit);
            target.setBit(torus.y, torus.x, torus.t, bit);
        }
    }

    private BoolE[] redStack(BoolEf neutrals) {
        BoolEf maskedData = maskData(DATA_POS, this, neutrals);
        BoolE[] target = new BoolE[BREADTH];
        for (int i = 0; i < BREADTH; i++) target[i] = new BoolE(false);
        redStackGeneric(HEIGHT, SPAN, BREADTH, target, maskedData);

        if (this.border == Border.MIRROR) applyMirrorStack(target);
        if (this.border == Border.TORUS) applyTorusStack(this, target);

        return target;
    }

    private void applyMirrorStack(BoolE[] target) {
        for (Ef m: MIRROR) {
            boolean bit = this.getBit(m.y, m.x, m.t, m.s);
            for (int i = 0; i < BREADTH; i++) target[i].setBit(m.y, m.x, m.t, bit);
        }
    }

    private static void applyTorusStack(BoolEf orig, BoolE[] target) {
        for (Ef ef: TORUS.keySet()) {
            Ef torus = TORUS.get(ef);
            target[1-ef.s].setBit(ef.y, ef.x, ef.t, orig.getBit(torus.y, torus.x, torus.t, torus.s));
            target[1-torus.s].setBit(torus.y, torus.x, torus.t, orig.getBit(ef.y, ef.x, ef.t, ef.s));
        }
    }

    /** @return an array of BoolE s.t. the n-th BoolE contains the bits of Efs (*, *, *, n), or a 0 if it doesn't exist */
    public BoolE[] redStack0(){
        return redStack(new BoolEf(false).zeroes());
    }

    /** @return an array of BoolE s.t. the n-th BoolE contains the bits of Efs (*, *, *, n), or a 1 if it doesn't exist */
    public BoolE[] redStack1(){
        return redStack(new BoolEf(false).ones());
    }

    /** Sets target to the clockwise rotation of this BoolEf. */
    public void _rotateCW(BoolEv target) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[blockIndex + k] = this.lines[blockIndex + (k + 1) % BREADTH].copy();
            }
        }
    }
    /** Sets this BoolEf to the clockwise rotation of orig. */
    public BoolEf rotateCW(BoolEv orig){ orig._rotateCW(this); return this; }

    /** Sets target to the counterclockwise rotation of this BoolEf. */
    public void _rotateCCW(BoolEv target){
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int blockIndex = (i * SPAN + j) * BREADTH;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[blockIndex + k] = this.lines[blockIndex + k].copy();
            }
        }
    }
    /** Sets this BoolEf to the counterclockwise rotation of orig. */
    public BoolEf rotateCCW(BoolEv orig){ orig._rotateCCW(this); return this; }

    /** Simulates a mirrored border for rotations , both CW and CCW*/
    private static void applyMirrorRotation(BoolEf orig, BoolEv target) {
        for (Ef ef: MIRROR) {
            boolean bit = orig.getBit(ef);
            target.setBit(ef.y, ef.x, ef.t, 0, bit);
            target.setBit(ef.y, ef.x, ef.t, 1, bit);
        }
    }

    /** Sets res to the transfer of this BoolEf. */
    public void _transfer(BoolFe res){
        res.zeroes();
        transferGeneric(this, res,  MASKS);
    }
    /** Sets this BoolEf to the transfer of orig. */
    public BoolEf transfer(BoolFe orig){ orig._transfer(this); return this; }

    /**
     * Two BoolEf objects are equal if their existing bits are equal, or they are both empty.
     * @return whether this BoolEf is equal to the given object.
     */
    @Override
    public boolean equals(Object o){
        if (!(o instanceof BoolEf other)) return false;

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

    @Override
    public String toString() {
        BoolEf clean = new BoolEf(false).and(this, DATA_POS);
        return toString(clean);
    }

    private BoolEf copy(boolean register) {
        BoolEf newBoolEf = new BoolEf(border, register);
        for (int i = 0; i < lines.length; i++)
            newBoolEf.lines[i] = this.lines[i] == null ? null : this.lines[i].copy();
        return newBoolEf;
    }

    @Override
    public BoolEf copy(){ return copy(true); }

    @Override
    public BoolEf cache() { return copy(false); }
}
