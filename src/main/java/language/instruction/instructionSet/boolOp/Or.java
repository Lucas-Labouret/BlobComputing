package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Performs an OR operation on two BoolV. */
class OrV implements BasicInstruction {
    private final BoolV a;
    private final BoolV b;
    private final BoolV res;

    /** Creates a new OrV. */
    public OrV(BoolV a, BoolV b, BoolV res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.or(a, b);
        return true;
    }
}

/** Performs an OR operation on two BoolVe. */
class OrVe implements BasicInstruction {
    private final BoolVe a;
    private final BoolVe b;
    private final BoolVe res;

    /** Creates a new OrVe. */
    public OrVe(BoolVe a, BoolVe b, BoolVe res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.or(a, b);
        return true;
    }
}

/** Performs an OR operation on two BoolVf. */
class OrVf implements BasicInstruction {
    private final BoolVf a;
    private final BoolVf b;
    private final BoolVf res;

    /** Creates a new OrVf. */
    public OrVf(BoolVf a, BoolVf b, BoolVf res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.or(a, b);
        return true;
    }
}

/** Performs an OR operation on two BoolE. */
class OrE implements BasicInstruction {
    private final BoolE a;
    private final BoolE b;
    private final BoolE res;

    /** Creates a new OrE. */
    public OrE(BoolE a, BoolE b, BoolE res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.or(a, b);
        return true;
    }
}

/** Performs an OR operation on two BoolEv. */
class OrEv implements BasicInstruction {
    private final BoolEv a;
    private final BoolEv b;
    private final BoolEv res;

    /** Creates a new OrEv. */
    public OrEv(BoolEv a, BoolEv b, BoolEv res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.or(a, b);
        return true;
    }
}

/** Performs an OR operation on two BoolEf. */
class OrEf implements BasicInstruction {
    private final BoolEf a;
    private final BoolEf b;
    private final BoolEf res;

    /** Creates a new OrEf. */
    public OrEf(BoolEf a, BoolEf b, BoolEf res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.or(a, b);
        return true;
    }
}

/** Performs an OR operation on two BoolF. */
class OrF implements BasicInstruction {
    private final BoolF a;
    private final BoolF b;
    private final BoolF res;

    /** Creates a new OrF. */
    public OrF(BoolF a, BoolF b, BoolF res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.or(a, b);
        return true;
    }
}

/** Performs an OR operation on two BoolFv. */
class OrFv implements BasicInstruction {
    private final BoolFv a;
    private final BoolFv b;
    private final BoolFv res;

    /** Creates a new OrFv. */
    public OrFv(BoolFv a, BoolFv b, BoolFv res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.or(a, b);
        return true;
    }
}

/** Performs an OR operation on two BoolFe. */
class OrFe implements BasicInstruction {
    private final BoolFe a;
    private final BoolFe b;
    private final BoolFe res;

    /** Creates a new OrFe. */
    public OrFe(BoolFe a, BoolFe b, BoolFe res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.or(a, b);
        return true;
    }
}
