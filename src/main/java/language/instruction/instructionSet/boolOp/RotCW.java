package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Rotates a BoolVe clockwise into a BoolVf. */
class RotVeCW implements BasicInstruction {
    private final BoolVe orig;
    private final BoolVf dest;

    /** Creates a new RotVeCW. */
    public RotVeCW(BoolVe orig, BoolVf dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCW(orig);
        return true;
    }
}

/** Rotates a BoolVf clockwise into a BoolVe. */
class RotVfCW implements BasicInstruction {
    private final BoolVf orig;
    private final BoolVe dest;

    /** Creates a new RotVfCW. */
    public RotVfCW(BoolVf orig, BoolVe dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCW(orig);
        return true;
    }
}

/** Rotates a BoolEv clockwise into a BoolEf. */
class RotEvCW implements BasicInstruction {
    private final BoolEv orig;
    private final BoolEf dest;

    /** Creates a new RotEvCW. */
    public RotEvCW(BoolEv orig, BoolEf dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCW(orig);
        return true;
    }
}

/** Rotates a BoolEf clockwise into a BoolEv. */
class RotEfCW implements BasicInstruction {
    private final BoolEf orig;
    private final BoolEv dest;

    /** Creates a new RotEfCW. */
    public RotEfCW(BoolEf orig, BoolEv dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCW(orig);
        return true;
    }
}

/** Rotates a BoolFv clockwise into a BoolFe. */
class RotFvCW implements BasicInstruction {
    private final BoolFv orig;
    private final BoolFe dest;

    /** Creates a new RotFvCW. */
    public RotFvCW(BoolFv orig, BoolFe dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCW(orig);
        return true;
    }
}

/** Rotates a BoolFe clockwise into a BoolFv. */
class RotFeCW implements BasicInstruction {
    private final BoolFe orig;
    private final BoolFv dest;

    /** Creates a new RotFeCW. */
    public RotFeCW(BoolFe orig, BoolFv dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.rotateCW(orig);
        return true;
    }
}
