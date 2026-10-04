package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Performs an AND operation on two BoolV. */
class AndV implements BasicInstruction {
    private final BoolV a;
    private final BoolV b;
    private final BoolV res;

    /** Creates a new AndV. */
    public AndV(BoolV a, BoolV b, BoolV res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.and(a, b);
        return true;
    }
}

/** Performs an AND operation on two BoolVe. */
class AndVe implements BasicInstruction {
    private final BoolVe a;
    private final BoolVe b;
    private final BoolVe res;

    /** Creates a new AndVe. */
    public AndVe(BoolVe a, BoolVe b, BoolVe res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.and(a, b);
        return true;
    }
}

/** Performs an AND operation on two BoolVf. */
class AndVf implements BasicInstruction {
    private final BoolVf a;
    private final BoolVf b;
    private final BoolVf res;

    /** Creates a new AndVf. */
    public AndVf(BoolVf a, BoolVf b, BoolVf res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.and(a, b);
        return true;
    }
}

/** Performs an AND operation on two BoolE. */
class AndE implements BasicInstruction {
    private final BoolE a;
    private final BoolE b;
    private final BoolE res;

    /** Creates a new AndE. */
    public AndE(BoolE a, BoolE b, BoolE res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.and(a, b);
        return true;
    }
}

/** Performs an AND operation on two BoolEv. */
class AndEv implements BasicInstruction {
    private final BoolEv a;
    private final BoolEv b;
    private final BoolEv res;

    /** Creates a new AndEv. */
    public AndEv(BoolEv a, BoolEv b, BoolEv res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.and(a, b);
        return true;
    }
}

/** Performs an AND operation on two BoolEf. */
class AndEf implements BasicInstruction {
    private final BoolEf a;
    private final BoolEf b;
    private final BoolEf res;

    /** Creates a new AndEf. */
    public AndEf(BoolEf a, BoolEf b, BoolEf res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.and(a, b);
        return true;
    }
}

/** Performs an AND operation on two BoolF. */
class AndF implements BasicInstruction {
    private final BoolF a;
    private final BoolF b;
    private final BoolF res;

    /** Creates a new AndF. */
    public AndF(BoolF a, BoolF b, BoolF res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.and(a, b);
        return true;
    }
}

/** Performs an AND operation on two BoolFv. */
class AndFv implements BasicInstruction {
    private final BoolFv a;
    private final BoolFv b;
    private final BoolFv res;

    /** Creates a new AndFv. */
    public AndFv(BoolFv a, BoolFv b, BoolFv res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.and(a, b);
        return true;
    }
}

/** Performs an AND operation on two BoolFe. */
class AndFe implements BasicInstruction {
    private final BoolFe a;
    private final BoolFe b;
    private final BoolFe res;

    /** Creates a new AndFe. */
    public AndFe(BoolFe a, BoolFe b, BoolFe res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.and(a, b);
        return true;
    }
}
