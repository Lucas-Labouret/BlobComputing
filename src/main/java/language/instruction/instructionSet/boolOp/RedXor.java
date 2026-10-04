package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Performs an XOR reduction from a BoolVe to a BoolV. */
class RedXorVe implements BasicInstruction {
    private final BoolVe orig;
    private final BoolV dest;

    /** Creates a new RedXorVe. */
    public RedXorVe(BoolVe orig, BoolV dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redXor(orig);
        return true;
    }
}

/** Performs an XOR reduction from a BoolVf to a BoolV. */
class RedXorVf implements BasicInstruction {
    private final BoolVf orig;
    private final BoolV dest;

    /** Creates a new RedXorVf. */
    public RedXorVf(BoolVf orig, BoolV dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redXor(orig);
        return true;
    }
}

/** Performs an XOR reduction from a BoolEv to a BoolE. */
class RedXorEv implements BasicInstruction {
    private final BoolEv orig;
    private final BoolE dest;

    /** Creates a new RedXorEv. */
    public RedXorEv(BoolEv orig, BoolE dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redXor(orig);
        return true;
    }
}

/** Performs an XOR reduction from a BoolEf to a BoolE. */
class RedXorEf implements BasicInstruction {
    private final BoolEf orig;
    private final BoolE dest;

    /** Creates a new RedXorEf. */
    public RedXorEf(BoolEf orig, BoolE dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redXor(orig);
        return true;
    }
}

/** Performs an XOR reduction from a BoolFv to a BoolF. */
class RedXorFv implements BasicInstruction {
    private final BoolFv orig;
    private final BoolF dest;

    /** Creates a new RedXorFv. */
    public RedXorFv(BoolFv orig, BoolF dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redXor(orig);
        return true;
    }
}

/** Performs an XOR reduction from a BoolFe to a BoolF. */
class RedXorFe implements BasicInstruction {
    private final BoolFe orig;
    private final BoolF dest;

    /** Creates a new RedXorFe. */
    public RedXorFe(BoolFe orig, BoolF dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.redXor(orig);
        return true;
    }
}
