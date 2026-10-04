package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Represents a transfer instruction for the Ve/Ev transfer-language.field pairing. */
class TransferVe implements BasicInstruction {
    private final BoolVe orig;
    private final BoolEv dest;

    /** Creates a new TransferVe. */
    public TransferVe(BoolVe orig, BoolEv dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.transfer(orig);
        return true;
    }
}

/** Represents a transfer instruction for the Ev/Ve transfer-language.field pairing. */
class TransferEv implements BasicInstruction {
    private final BoolEv orig;
    private final BoolVe dest;

    /** Creates a new TransferEv. */
    public TransferEv(BoolEv orig, BoolVe dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.transfer(orig);
        return true;
    }
}

/** Represents a transfer instruction for the Vf/Fv transfer-language.field pairing. */
class TransferVf implements BasicInstruction {
    private final BoolVf orig;
    private final BoolFv dest;

    /** Creates a new TransferVf. */
    public TransferVf(BoolVf orig, BoolFv dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.transfer(orig);
        return true;
    }
}

/** Represents a transfer instruction for the Fv/Vf transfer-language.field pairing. */
class TransferFv implements BasicInstruction {
    private final BoolFv orig;
    private final BoolVf dest;

    /** Creates a new TransferFv. */
    public TransferFv(BoolFv orig, BoolVf dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.transfer(orig);
        return true;
    }
}

/** Represents a transfer instruction for the Ef/Fe transfer-language.field pairing. */
class TransferEf implements BasicInstruction {
    private final BoolEf orig;
    private final BoolFe dest;

    /** Creates a new TransferEf. */
    public TransferEf(BoolEf orig, BoolFe dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.transfer(orig);
        return true;
    }
}

/** Represents a transfer instruction for the Fe/Ef transfer-language.field pairing. */
class TransferFe implements BasicInstruction {
    private final BoolFe orig;
    private final BoolEf dest;

    /** Creates a new TransferFe. */
    public TransferFe(BoolFe orig, BoolEf dest) {
        this.orig = orig;
        this.dest = dest;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        dest.transfer(orig);
        return true;
    }
}
