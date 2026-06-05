package language.instruction.instructionSet.boolOp;

import language.instruction.BasicInstruction;
import language.field.boolField.BoolE;
import language.field.boolField.BoolF;
import language.field.boolField.BoolV;
import language.fieldRef.boolField.*;

/** Performs an XOR reduction from a BoolVe to a BoolV. */
class RedXorVe implements BasicInstruction {
    private final BoolVeRef orig;
    private final BoolVRef dest;

    /** Creates a new RedXorVe. */
    public RedXorVe(BoolVeRef orig, BoolVRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolV.redXorVe(orig.get()));
        return true;
    }
}

/** Performs an XOR reduction from a BoolVf to a BoolV. */
class RedXorVf implements BasicInstruction {
    private final BoolVfRef orig;
    private final BoolVRef dest;

    /** Creates a new RedXorVf. */
    public RedXorVf(BoolVfRef orig, BoolVRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolV.redXorVf(orig.get()));
        return true;
    }
}

/** Performs an XOR reduction from a BoolEv to a BoolE. */
class RedXorEv implements BasicInstruction {
    private final BoolEvRef orig;
    private final BoolERef dest;

    /** Creates a new RedXorEv. */
    public RedXorEv(BoolEvRef orig, BoolERef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolE.redXorEv(orig.get()));
        return true;
    }
}

/** Performs an XOR reduction from a BoolEf to a BoolE. */
class RedXorEf implements BasicInstruction {
    private final BoolEfRef orig;
    private final BoolERef dest;

    /** Creates a new RedXorEf. */
    public RedXorEf(BoolEfRef orig, BoolERef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolE.redXorEf(orig.get()));
        return true;
    }
}

/** Performs an XOR reduction from a BoolFv to a BoolF. */
class RedXorFv implements BasicInstruction {
    private final BoolFvRef orig;
    private final BoolFRef dest;

    /** Creates a new RedXorFv. */
    public RedXorFv(BoolFvRef orig, BoolFRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolF.redXorFv(orig.get()));
        return true;
    }
}

/** Performs an XOR reduction from a BoolFe to a BoolF. */
class RedXorFe implements BasicInstruction {
    private final BoolFeRef orig;
    private final BoolFRef dest;

    /** Creates a new RedXorFe. */
    public RedXorFe(BoolFeRef orig, BoolFRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolF.redXorFe(orig.get()));
        return true;
    }
}
