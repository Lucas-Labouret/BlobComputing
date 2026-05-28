package language.instruction.instructionSet.boolOp;

import language.instruction.instructionSet.BasicInstruction;
import language.obj.field.boolField.*;
import language.ref.field.boolField.*;

/** Performs an OR operation on two BoolV. */
class OrV implements BasicInstruction {
    private final BoolVRef a;
    private final BoolVRef b;
    private final BoolVRef res;

    /** Creates a new OrV. */
    public OrV(BoolVRef a, BoolVRef b, BoolVRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolV.or(a.get(), b.get()));
        return true;
    }
}

/** Performs an OR operation on two BoolVe. */
class OrVe implements BasicInstruction {
    private final BoolVeRef a;
    private final BoolVeRef b;
    private final BoolVeRef res;

    /** Creates a new OrVe. */
    public OrVe(BoolVeRef a, BoolVeRef b, BoolVeRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVe.or(a.get(), b.get()));
        return true;
    }
}

/** Performs an OR operation on two BoolVf. */
class OrVf implements BasicInstruction {
    private final BoolVfRef a;
    private final BoolVfRef b;
    private final BoolVfRef res;

    /** Creates a new OrVf. */
    public OrVf(BoolVfRef a, BoolVfRef b, BoolVfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVf.or(a.get(), b.get()));
        return true;
    }
}

/** Performs an OR operation on two BoolE. */
class OrE implements BasicInstruction {
    private final BoolERef a;
    private final BoolERef b;
    private final BoolERef res;

    /** Creates a new OrE. */
    public OrE(BoolERef a, BoolERef b, BoolERef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolE.or(a.get(), b.get()));
        return true;
    }
}

/** Performs an OR operation on two BoolEv. */
class OrEv implements BasicInstruction {
    private final BoolEvRef a;
    private final BoolEvRef b;
    private final BoolEvRef res;

    /** Creates a new OrEv. */
    public OrEv(BoolEvRef a, BoolEvRef b, BoolEvRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEv.or(a.get(), b.get()));
        return true;
    }
}

/** Performs an OR operation on two BoolEf. */
class OrEf implements BasicInstruction {
    private final BoolEfRef a;
    private final BoolEfRef b;
    private final BoolEfRef res;

    /** Creates a new OrEf. */
    public OrEf(BoolEfRef a, BoolEfRef b, BoolEfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEf.or(a.get(), b.get()));
        return true;
    }
}

/** Performs an OR operation on two BoolF. */
class OrF implements BasicInstruction {
    private final BoolFRef a;
    private final BoolFRef b;
    private final BoolFRef res;

    /** Creates a new OrF. */
    public OrF(BoolFRef a, BoolFRef b, BoolFRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolF.or(a.get(), b.get()));
        return true;
    }
}

/** Performs an OR operation on two BoolFv. */
class OrFv implements BasicInstruction {
    private final BoolFvRef a;
    private final BoolFvRef b;
    private final BoolFvRef res;

    /** Creates a new OrFv. */
    public OrFv(BoolFvRef a, BoolFvRef b, BoolFvRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFv.or(a.get(), b.get()));
        return true;
    }
}

/** Performs an OR operation on two BoolFe. */
class OrFe implements BasicInstruction {
    private final BoolFeRef a;
    private final BoolFeRef b;
    private final BoolFeRef res;

    /** Creates a new OrFe. */
    public OrFe(BoolFeRef a, BoolFeRef b, BoolFeRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFe.or(a.get(), b.get()));
        return true;
    }
}
