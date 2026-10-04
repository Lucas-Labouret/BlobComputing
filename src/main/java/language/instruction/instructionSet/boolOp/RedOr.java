package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Performs an OR reduction from a BoolVe to a BoolV. */
class RedOrVe implements BasicInstruction {
    private final BoolVe orig;
    private final BoolV dest;

    /** Creates a new RedOrVe. */
    public RedOrVe(BoolVe orig, BoolV dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redOr(orig);
        return true;
    }
}

/** Performs an OR reduction from a BoolVf to a BoolV. */
class RedOrVf implements BasicInstruction {
    private final BoolVf orig;
    private final BoolV dest;

    /** Creates a new RedOrVf. */
    public RedOrVf(BoolVf orig, BoolV dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redOr(orig);
        return true;
    }
}

/** Performs an OR reduction from a BoolEv to a BoolE. */
class RedOrEv implements BasicInstruction {
    private final BoolEv orig;
    private final BoolE dest;

    /** Creates a new RedOrEv. */
    public RedOrEv(BoolEv orig, BoolE dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redOr(orig);
        return true;
    }
}

/** Performs an OR reduction from a BoolEf to a BoolE. */
class RedOrEf implements BasicInstruction {
    private final BoolEf orig;
    private final BoolE dest;

    /** Creates a new RedOrEf. */
    public RedOrEf(BoolEf orig, BoolE dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redOr(orig);
        return true;
    }
}

/** Performs an OR reduction from a BoolFv to a BoolF. */
class RedOrFv implements BasicInstruction {
    private final BoolFv orig;
    private final BoolF dest;

    /** Creates a new RedOrFv. */
    public RedOrFv(BoolFv orig, BoolF dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redOr(orig);
        return true;
    }
}

/** Performs an OR reduction from a BoolFe to a BoolF. */
class RedOrFe implements BasicInstruction {
    private final BoolFe orig;
    private final BoolF dest;

    /** Creates a new RedOrFe. */
    public RedOrFe(BoolFe orig, BoolF dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redOr(orig);
        return true;
    }
}
