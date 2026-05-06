package language.basicInstruction.bitOp;

import field.boolField.fieldS.*;
import field.boolField.fieldT.*;
import language.basicInstruction.BasicInstruction;
import language.fieldRef.*;

/** Performs a NOT operation on a BoolV. */
class NotV implements BasicInstruction {
    private final BoolVRef a;
    private final BoolVRef res;

    /** Creates a new NotV. */
    public NotV(BoolVRef a, BoolVRef res) {
        this.a = a;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolV.not(a.get()));
        return true;
    }
}

/** Performs a NOT operation on a BoolVe. */
class NotVe implements BasicInstruction {
    private final BoolVeRef a;
    private final BoolVeRef res;

    /** Creates a new NotVe. */
    public NotVe(BoolVeRef a, BoolVeRef res) {
        this.a = a;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVe.not(a.get()));
        return true;
    }
}

/** Performs a NOT operation on a BoolVf. */
class NotVf implements BasicInstruction {
    private final BoolVfRef a;
    private final BoolVfRef res;

    /** Creates a new NotVf. */
    public NotVf(BoolVfRef a, BoolVfRef res) {
        this.a = a;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVf.not(a.get()));
        return true;
    }
}

/** Performs a NOT operation on a BoolE. */
class NotE implements BasicInstruction {
    private final BoolERef a;
    private final BoolERef res;

    /** Creates a new NotE. */
    public NotE(BoolERef a, BoolERef res) {
        this.a = a;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolE.not(a.get()));
        return true;
    }
}

/** Performs a NOT operation on a BoolEv. */
class NotEv implements BasicInstruction {
    private final BoolEvRef a;
    private final BoolEvRef res;

    /** Creates a new NotEv. */
    public NotEv(BoolEvRef a, BoolEvRef res) {
        this.a = a;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEv.not(a.get()));
        return true;
    }
}

/** Performs a NOT operation on a BoolEf. */
class NotEf implements BasicInstruction {
    private final BoolEfRef a;
    private final BoolEfRef res;

    /** Creates a new NotEf. */
    public NotEf(BoolEfRef a, BoolEfRef res) {
        this.a = a;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEf.not(a.get()));
        return true;
    }
}

/** Performs a NOT operation on a BoolF. */
class NotF implements BasicInstruction {
    private final BoolFRef a;
    private final BoolFRef res;

    /** Creates a new NotF. */
    public NotF(BoolFRef a, BoolFRef res) {
        this.a = a;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolF.not(a.get()));
        return true;
    }
}

/** Performs a NOT operation on a BoolFv. */
class NotFv implements BasicInstruction {
    private final BoolFvRef a;
    private final BoolFvRef res;

    /** Creates a new NotFv. */
    public NotFv(BoolFvRef a, BoolFvRef res) {
        this.a = a;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFv.not(a.get()));
        return true;
    }
}

/** Performs a NOT operation on a BoolFe. */
class NotFe implements BasicInstruction {
    private final BoolFeRef a;
    private final BoolFeRef res;

    /** Creates a new NotFe. */
    public NotFe(BoolFeRef a, BoolFeRef res) {
        this.a = a;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFe.not(a.get()));
        return true;
    }
}

