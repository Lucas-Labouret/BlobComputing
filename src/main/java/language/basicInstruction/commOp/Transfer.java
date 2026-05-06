package language.basicInstruction.commOp;

import language.basicInstruction.BasicInstruction;
import language.fieldRef.*;
import field.boolField.fieldT.*;

/** Represents a transfer instruction for the Ve/Ev transfer-field pairing. */
class TransferVe implements BasicInstruction {
    private final BoolVeRef orig;
    private final BoolEvRef dest;

    /** Creates a new TransferVe. */
    public TransferVe(BoolVeRef orig, BoolEvRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolVe.transfer(orig.get()));
        return true;
    }
}

/** Represents a transfer instruction for the Ev/Ve transfer-field pairing. */
class TransferEv implements BasicInstruction {
    private final BoolEvRef orig;
    private final BoolVeRef dest;

    /** Creates a new TransferEv. */
    public TransferEv(BoolEvRef orig, BoolVeRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolEv.transfer(orig.get()));
        return true;
    }
}

/** Represents a transfer instruction for the Vf/Fv transfer-field pairing. */
class TransferVf implements BasicInstruction {
    private final BoolVfRef orig;
    private final BoolFvRef dest;

    /** Creates a new TransferVf. */
    public TransferVf(BoolVfRef orig, BoolFvRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolVf.transfer(orig.get()));
        return true;
    }
}

/** Represents a transfer instruction for the Fv/Vf transfer-field pairing. */
class TransferFv implements BasicInstruction {
    private final BoolFvRef orig;
    private final BoolVfRef dest;

    /** Creates a new TransferFv. */
    public TransferFv(BoolFvRef orig, BoolVfRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolFv.transfer(orig.get()));
        return true;
    }
}

/** Represents a transfer instruction for the Ef/Fe transfer-field pairing. */
class TransferEf implements BasicInstruction {
    private final BoolEfRef orig;
    private final BoolFeRef dest;

    /** Creates a new TransferEf. */
    public TransferEf(BoolEfRef orig, BoolFeRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolEf.transfer(orig.get()));
        return true;
    }
}

/** Represents a transfer instruction for the Fe/Ef transfer-field pairing. */
class TransferFe implements BasicInstruction {
    private final BoolFeRef orig;
    private final BoolEfRef dest;

    /** Creates a new TransferFe. */
    public TransferFe(BoolFeRef orig, BoolEfRef dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.set(BoolFe.transfer(orig.get()));
        return true;
    }
}
