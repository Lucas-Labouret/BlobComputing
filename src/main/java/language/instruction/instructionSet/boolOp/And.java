package language.instruction.instructionSet.boolOp;

import language.obj.field.boolField.fieldS.*;
import language.obj.field.boolField.fieldT.*;
import language.instruction.instructionSet.BasicInstruction;
import language.ref.field.boolField.fieldS.*;
import language.ref.field.boolField.fieldT.*;

/** Performs an AND operation on two BoolV. */
class AndV implements BasicInstruction {
    private final BoolVRef a;
    private final BoolVRef b;
    private final BoolVRef res;

    /** Creates a new AndV. */
    public AndV(BoolVRef a, BoolVRef b, BoolVRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolV.and(a.get(), b.get()));
        return true;
    }
}

/** Performs an AND operation on two BoolVe. */
class AndVe implements BasicInstruction {
    private final BoolVeRef a;
    private final BoolVeRef b;
    private final BoolVeRef res;

    /** Creates a new AndVe. */
    public AndVe(BoolVeRef a, BoolVeRef b, BoolVeRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVe.and(a.get(), b.get()));
        return true;
    }
}

/** Performs an AND operation on two BoolVf. */
class AndVf implements BasicInstruction {
    private final BoolVfRef a;
    private final BoolVfRef b;
    private final BoolVfRef res;

    /** Creates a new AndVf. */
    public AndVf(BoolVfRef a, BoolVfRef b, BoolVfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVf.and(a.get(), b.get()));
        return true;
    }
}

/** Performs an AND operation on two BoolE. */
class AndE implements BasicInstruction {
    private final BoolERef a;
    private final BoolERef b;
    private final BoolERef res;

    /** Creates a new AndE. */
    public AndE(BoolERef a, BoolERef b, BoolERef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolE.and(a.get(), b.get()));
        return true;
    }
}

/** Performs an AND operation on two BoolEv. */
class AndEv implements BasicInstruction {
    private final BoolEvRef a;
    private final BoolEvRef b;
    private final BoolEvRef res;

    /** Creates a new AndEv. */
    public AndEv(BoolEvRef a, BoolEvRef b, BoolEvRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEv.and(a.get(), b.get()));
        return true;
    }
}

/** Performs an AND operation on two BoolEf. */
class AndEf implements BasicInstruction {
    private final BoolEfRef a;
    private final BoolEfRef b;
    private final BoolEfRef res;

    /** Creates a new AndEf. */
    public AndEf(BoolEfRef a, BoolEfRef b, BoolEfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEf.and(a.get(), b.get()));
        return true;
    }
}

/** Performs an AND operation on two BoolF. */
class AndF implements BasicInstruction {
    private final BoolFRef a;
    private final BoolFRef b;
    private final BoolFRef res;

    /** Creates a new AndF. */
    public AndF(BoolFRef a, BoolFRef b, BoolFRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolF.and(a.get(), b.get()));
        return true;
    }
}

/** Performs an AND operation on two BoolFv. */
class AndFv implements BasicInstruction {
    private final BoolFvRef a;
    private final BoolFvRef b;
    private final BoolFvRef res;

    /** Creates a new AndFv. */
    public AndFv(BoolFvRef a, BoolFvRef b, BoolFvRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFv.and(a.get(), b.get()));
        return true;
    }
}

/** Performs an AND operation on two BoolFe. */
class AndFe implements BasicInstruction {
    private final BoolFeRef a;
    private final BoolFeRef b;
    private final BoolFeRef res;

    /** Creates a new AndFe. */
    public AndFe(BoolFeRef a, BoolFeRef b, BoolFeRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFe.and(a.get(), b.get()));
        return true;
    }
}
