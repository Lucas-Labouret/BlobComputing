package language.instruction.instructionSet.boolOp;

import language.instruction.BasicInstruction;
import language.obj.field.boolField.BoolE;
import language.obj.field.boolField.BoolF;
import language.obj.field.boolField.BoolV;
import language.ref.field.boolField.*;

/** Performs an OR reduction from a BoolVe to a BoolV. */
class RedOrVe implements BasicInstruction {
    private final BoolVeRef orig;
    private final BoolVRef dest;

    /** Creates a new RedOrVe. */
    public RedOrVe(BoolVeRef orig, BoolVRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolV.redOrVe(orig.get()));
        return true;
    }
}

/** Performs an OR reduction from a BoolVf to a BoolV. */
class RedOrVf implements BasicInstruction {
    private final BoolVfRef orig;
    private final BoolVRef dest;

    /** Creates a new RedOrVf. */
    public RedOrVf(BoolVfRef orig, BoolVRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolV.redOrVf(orig.get()));
        return true;
    }
}

/** Performs an OR reduction from a BoolEv to a BoolE. */
class RedOrEv implements BasicInstruction {
    private final BoolEvRef orig;
    private final BoolERef dest;

    /** Creates a new RedOrEv. */
    public RedOrEv(BoolEvRef orig, BoolERef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolE.redOrEv(orig.get()));
        return true;
    }
}

/** Performs an OR reduction from a BoolEf to a BoolE. */
class RedOrEf implements BasicInstruction {
    private final BoolEfRef orig;
    private final BoolERef dest;

    /** Creates a new RedOrEf. */
    public RedOrEf(BoolEfRef orig, BoolERef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolE.redOrEf(orig.get()));
        return true;
    }
}

/** Performs an OR reduction from a BoolFv to a BoolF. */
class RedOrFv implements BasicInstruction {
    private final BoolFvRef orig;
    private final BoolFRef dest;

    /** Creates a new RedOrFv. */
    public RedOrFv(BoolFvRef orig, BoolFRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolF.redOrFv(orig.get()));
        return true;
    }
}

/** Performs an OR reduction from a BoolFe to a BoolF. */
class RedOrFe implements BasicInstruction {
    private final BoolFeRef orig;
    private final BoolFRef dest;

    /** Creates a new RedOrFe. */
    public RedOrFe(BoolFeRef orig, BoolFRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolF.redOrFe(orig.get()));
        return true;
    }
}
