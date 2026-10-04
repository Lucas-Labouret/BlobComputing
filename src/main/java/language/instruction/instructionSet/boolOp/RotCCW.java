package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Rotates a BoolVe counterclockwise into a BoolVf. */
class RotVeCCW implements BasicInstruction {
    private final BoolVe orig;
    private final BoolVf dest;

    /** Creates a new RotVeCCW. */
    public RotVeCCW(BoolVe orig, BoolVf dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCCW(orig);
        return true;
    }
}

/** Rotates a BoolVf counterclockwise into a BoolVe. */
class RotVfCCW implements BasicInstruction {
    private final BoolVf orig;
    private final BoolVe dest;

    /** Creates a new RotVfCCW. */
    public RotVfCCW(BoolVf orig, BoolVe dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCCW(orig);
        return true;
    }
}

/** Rotates a BoolEv counterclockwise into a BoolEf. */
class RotEvCCW implements BasicInstruction {
    private final BoolEv orig;
    private final BoolEf dest;

    /** Creates a new RotEvCCW. */
    public RotEvCCW(BoolEv orig, BoolEf dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCCW(orig);
        return true;
    }
}

/** Rotates a BoolEf counterclockwise into a BoolEv. */
class RotEfCCW implements BasicInstruction {
    private final BoolEf orig;
    private final BoolEv dest;

    /** Creates a new RotEfCCW. */
    public RotEfCCW(BoolEf orig, BoolEv dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCCW(orig);
        return true;
    }
}

/** Rotates a BoolFv counterclockwise into a BoolFe. */
class RotFvCCW implements BasicInstruction {
    private final BoolFv orig;
    private final BoolFe dest;

    /** Creates a new RotFvCCW. */
    public RotFvCCW(BoolFv orig, BoolFe dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCCW(orig);
        return true;
    }
}

/** Rotates a BoolFe counterclockwise into a BoolFv. */
class RotFeCCW implements BasicInstruction {
    private final BoolFe orig;
    private final BoolFv dest;

    /** Creates a new RotFeCCW. */
    public RotFeCCW(BoolFe orig, BoolFv dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCCW(orig);
        return true;
    }
}
