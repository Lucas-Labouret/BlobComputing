package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Performs an AND reduction from a BoolVe to a BoolV. */
class RedAndVe implements BasicInstruction {
    private final BoolVe orig;
    private final BoolV dest;

    /** Creates a new RedAndVe. */
    public RedAndVe(BoolVe orig, BoolV dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redAnd(orig);
        return true;
    }
}

/** Performs an AND reduction from a BoolVf to a BoolV. */
class RedAndVf implements BasicInstruction {
    private final BoolVf orig;
    private final BoolV dest;

    /** Creates a new RedAndVf. */
    public RedAndVf(BoolVf orig, BoolV dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redAnd(orig);
        return true;
    }
}

/** Performs an AND reduction from a BoolEv to a BoolE. */
class RedAndEv implements BasicInstruction {
    private final BoolEv orig;
    private final BoolE dest;

    /** Creates a new RedAndEv. */
    public RedAndEv(BoolEv orig, BoolE dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redAnd(orig);
        return true;
    }
}

/** Performs an AND reduction from a BoolEf to a BoolE. */
class RedAndEf implements BasicInstruction {
    private final BoolEf orig;
    private final BoolE dest;

    /** Creates a new RedAndEf. */
    public RedAndEf(BoolEf orig, BoolE dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redAnd(orig);
        return true;
    }
}

/** Performs an AND reduction from a BoolFv to a BoolF. */
class RedAndFv implements BasicInstruction {
    private final BoolFv orig;
    private final BoolF dest;

    /** Creates a new RedAndFv. */
    public RedAndFv(BoolFv orig, BoolF dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redAnd(orig);
        return true;
    }
}

/** Performs an AND reduction from a BoolFe to a BoolF. */
class RedAndFe implements BasicInstruction {
    private final BoolFe orig;
    private final BoolF dest;

    /** Creates a new RedAndFe. */
    public RedAndFe(BoolFe orig, BoolF dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redAnd(orig);
        return true;
    }
}
