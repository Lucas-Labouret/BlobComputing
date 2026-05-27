package language.instruction.basicInstruction.commOp;

import language.instruction.basicInstruction.BasicInstruction;
import language.ref.field.boolField.fieldT.*;
import language.obj.field.boolField.fieldT.*;

/** Rotates a BoolVe clockwise into a BoolVf. */
class RotVeCW implements BasicInstruction {
    private final BoolVeRef orig;
    private final BoolVfRef res;

    /** Creates a new RotVeCW. */
    public RotVeCW(BoolVeRef orig, BoolVfRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVe.rotateCW(orig.get()));
        return true;
    }
}

/** Rotates a BoolVf clockwise into a BoolVe. */
class RotVfCW implements BasicInstruction {
    private final BoolVfRef orig;
    private final BoolVeRef res;

    /** Creates a new RotVfCW. */
    public RotVfCW(BoolVfRef orig, BoolVeRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVf.rotateCW(orig.get()));
        return true;
    }
}

/** Rotates a BoolEv clockwise into a BoolEf. */
class RotEvCW implements BasicInstruction {
    private final BoolEvRef orig;
    private final BoolEfRef res;

    /** Creates a new RotEvCW. */
    public RotEvCW(BoolEvRef orig, BoolEfRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEv.rotateCW(orig.get()));
        return true;
    }
}

/** Rotates a BoolEf clockwise into a BoolEv. */
class RotEfCW implements BasicInstruction {
    private final BoolEfRef orig;
    private final BoolEvRef res;

    /** Creates a new RotEfCW. */
    public RotEfCW(BoolEfRef orig, BoolEvRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEf.rotateCW(orig.get()));
        return true;
    }
}

/** Rotates a BoolFv clockwise into a BoolFe. */
class RotFvCW implements BasicInstruction {
    private final BoolFvRef orig;
    private final BoolFeRef res;

    /** Creates a new RotFvCW. */
    public RotFvCW(BoolFvRef orig, BoolFeRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFv.rotateCW(orig.get()));
        return true;
    }
}

/** Rotates a BoolFe clockwise into a BoolFv. */
class RotFeCW implements BasicInstruction {
    private final BoolFeRef orig;
    private final BoolFvRef res;

    /** Creates a new RotFeCW. */
    public RotFeCW(BoolFeRef orig, BoolFvRef res) {
        this.orig = orig;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFe.rotateCW(orig.get()));
        return true;
    }
}
