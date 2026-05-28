package language.instruction.instructionSet.boolOp;

import language.instruction.instructionSet.BasicInstruction;
import language.obj.field.boolField.BoolE;
import language.obj.field.boolField.BoolF;
import language.obj.field.boolField.BoolV;
import language.ref.field.boolField.*;

/** Performs an AND reduction from a BoolVe to a BoolV. */
class RedAndVe implements BasicInstruction {
    private final BoolVeRef orig;
    private final BoolVRef dest;

    /** Creates a new RedAndVe. */
    public RedAndVe(BoolVeRef orig, BoolVRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolV.redAndVe(orig.get()));
        return true;
    }
}

/** Performs an AND reduction from a BoolVf to a BoolV. */
class RedAndVf implements BasicInstruction {
    private final BoolVfRef orig;
    private final BoolVRef dest;

    /** Creates a new RedAndVf. */
    public RedAndVf(BoolVfRef orig, BoolVRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolV.redAndVf(orig.get()));
        return true;
    }
}

/** Performs an AND reduction from a BoolEv to a BoolE. */
class RedAndEv implements BasicInstruction {
    private final BoolEvRef orig;
    private final BoolERef dest;

    /** Creates a new RedAndEv. */
    public RedAndEv(BoolEvRef orig, BoolERef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolE.redAndEv(orig.get()));
        return true;
    }
}

/** Performs an AND reduction from a BoolEf to a BoolE. */
class RedAndEf implements BasicInstruction {
    private final BoolEfRef orig;
    private final BoolERef dest;

    /** Creates a new RedAndEf. */
    public RedAndEf(BoolEfRef orig, BoolERef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolE.redAndEf(orig.get()));
        return true;
    }
}

/** Performs an AND reduction from a BoolFv to a BoolF. */
class RedAndFv implements BasicInstruction {
    private final BoolFvRef orig;
    private final BoolFRef dest;

    /** Creates a new RedAndFv. */
    public RedAndFv(BoolFvRef orig, BoolFRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolF.redAndFv(orig.get()));
        return true;
    }
}

/** Performs an AND reduction from a BoolFe to a BoolF. */
class RedAndFe implements BasicInstruction {
    private final BoolFeRef orig;
    private final BoolFRef dest;

    /** Creates a new RedAndFe. */
    public RedAndFe(BoolFeRef orig, BoolFRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolF.redAndFe(orig.get()));
        return true;
    }
}
