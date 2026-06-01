package language.instruction.instructionSet.boolOp;

import language.instruction.BasicInstruction;
import language.obj.field.boolField.*;
import language.ref.field.boolField.*;

/** Rotates a BoolVe counterclockwise into a BoolVf. */
class RotVeCCW implements BasicInstruction {
    private final BoolVeRef orig;
    private final BoolVfRef res;

    /** Creates a new RotVeCCW. */
    public RotVeCCW(BoolVeRef orig, BoolVfRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVe.rotateCCW(orig.get()));
        return true;
    }
}

/** Rotates a BoolVf counterclockwise into a BoolVe. */
class RotVfCCW implements BasicInstruction {
    private final BoolVfRef orig;
    private final BoolVeRef res;

    /** Creates a new RotVfCCW. */
    public RotVfCCW(BoolVfRef orig, BoolVeRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVf.rotateCCW(orig.get()));
        return true;
    }
}

/** Rotates a BoolEv counterclockwise into a BoolEf. */
class RotEvCCW implements BasicInstruction {
    private final BoolEvRef orig;
    private final BoolEfRef res;

    /** Creates a new RotEvCCW. */
    public RotEvCCW(BoolEvRef orig, BoolEfRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEv.rotateCCW(orig.get()));
        return true;
    }
}

/** Rotates a BoolEf counterclockwise into a BoolEv. */
class RotEfCCW implements BasicInstruction {
    private final BoolEfRef orig;
    private final BoolEvRef res;

    /** Creates a new RotEfCCW. */
    public RotEfCCW(BoolEfRef orig, BoolEvRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEf.rotateCCW(orig.get()));
        return true;
    }
}

/** Rotates a BoolFv counterclockwise into a BoolFe. */
class RotFvCCW implements BasicInstruction {
    private final BoolFvRef orig;
    private final BoolFeRef res;

    /** Creates a new RotFvCCW. */
    public RotFvCCW(BoolFvRef orig, BoolFeRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFv.rotateCCW(orig.get()));
        return true;
    }
}

/** Rotates a BoolFe counterclockwise into a BoolFv. */
class RotFeCCW implements BasicInstruction {
    private final BoolFeRef orig;
    private final BoolFvRef res;

    /** Creates a new RotFeCCW. */
    public RotFeCCW(BoolFeRef orig, BoolFvRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFe.rotateCCW(orig.get()));
        return true;
    }
}
