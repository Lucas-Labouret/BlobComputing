package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Performs a NOT operation on a BoolV. */
class NotV implements BasicInstruction {
    private final BoolV orig;
    private final BoolV dest;

    /** Creates a new NotV. */
    public NotV(BoolV orig, BoolV dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.not(orig);
        return true;
    }
}

/** Performs a NOT operation on a BoolVe. */
class NotVe implements BasicInstruction {
    private final BoolVe orig;
    private final BoolVe dest;

    /** Creates orig new NotVe. */
    public NotVe(BoolVe orig, BoolVe dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.not(orig);
        return true;
    }
}

/** Performs a NOT operation on a BoolVf. */
class NotVf implements BasicInstruction {
    private final BoolVf orig;
    private final BoolVf dest;

    /** Creates a new NotVf. */
    public NotVf(BoolVf orig, BoolVf dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.not(orig);
        return true;
    }
}

/** Performs a NOT operation on a BoolE. */
class NotE implements BasicInstruction {
    private final BoolE orig;
    private final BoolE dest;

    /** Creates a new NotE. */
    public NotE(BoolE orig, BoolE dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.not(orig);
        return true;
    }
}

/** Performs a NOT operation on a BoolEv. */
class NotEv implements BasicInstruction {
    private final BoolEv orig;
    private final BoolEv dest;

    /** Creates a new NotEv. */
    public NotEv(BoolEv orig, BoolEv dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.not(orig);
        return true;
    }
}

/** Performs a NOT operation on a BoolEf. */
class NotEf implements BasicInstruction {
    private final BoolEf orig;
    private final BoolEf dest;

    /** Creates a new NotEf. */
    public NotEf(BoolEf orig, BoolEf dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.not(orig);
        return true;
    }
}

/** Performs a NOT operation on a BoolF. */
class NotF implements BasicInstruction {
    private final BoolF orig;
    private final BoolF dest;

    /** Creates a new NotF. */
    public NotF(BoolF orig, BoolF dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.not(orig);
        return true;
    }
}

/** Performs a NOT operation on a BoolFv. */
class NotFv implements BasicInstruction {
    private final BoolFv orig;
    private final BoolFv dest;

    /** Creates a new NotFv. */
    public NotFv(BoolFv orig, BoolFv dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.not(orig);
        return true;
    }
}

/** Performs a NOT operation on a BoolFe. */
class NotFe implements BasicInstruction {
    private final BoolFe orig;
    private final BoolFe dest;

    /** Creates a new NotFe. */
    public NotFe(BoolFe orig, BoolFe dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.not(orig);
        return true;
    }
}

