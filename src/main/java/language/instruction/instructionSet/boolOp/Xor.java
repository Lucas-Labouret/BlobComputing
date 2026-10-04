package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;

/** Performs an XOR operation on two BoolV. */
class XorV implements BasicInstruction {
    private final BoolV a;
    private final BoolV b;
    private final BoolV res;

    /** Creates a new XorV. */
    public XorV(BoolV a, BoolV b, BoolV res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.xor(a, b);
        return true;
    }
}

/** Performs an XOR operation on two BoolVe. */
class XorVe implements BasicInstruction {
    private final BoolVe a;
    private final BoolVe b;
    private final BoolVe res;

    /** Creates a new XorVe. */
    public XorVe(BoolVe a, BoolVe b, BoolVe res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.xor(a, b);
        return true;
    }
}

/** Performs an XOR operation on two BoolVf. */
class XorVf implements BasicInstruction {
    private final BoolVf a;
    private final BoolVf b;
    private final BoolVf res;

    /** Creates a new XorVf. */
    public XorVf(BoolVf a, BoolVf b, BoolVf res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.xor(a, b);
        return true;
    }
}

/** Performs an XOR operation on two BoolE. */
class XorE implements BasicInstruction {
    private final BoolE a;
    private final BoolE b;
    private final BoolE res;

    /** Creates a new XorE. */
    public XorE(BoolE a, BoolE b, BoolE res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.xor(a, b);
        return true;
    }
}

/** Performs an XOR operation on two BoolEv. */
class XorEv implements BasicInstruction {
    private final BoolEv a;
    private final BoolEv b;
    private final BoolEv res;

    /** Creates a new XorEv. */
    public XorEv(BoolEv a, BoolEv b, BoolEv res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.xor(a, b);
        return true;
    }
}

/** Performs an XOR operation on two BoolEf. */
class XorEf implements BasicInstruction {
    private final BoolEf a;
    private final BoolEf b;
    private final BoolEf res;

    /** Creates a new XorEf. */
    public XorEf(BoolEf a, BoolEf b, BoolEf res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.xor(a, b);
        return true;
    }
}

/** Performs an XOR operation on two BoolF. */
class XorF implements BasicInstruction {
    private final BoolF a;
    private final BoolF b;
    private final BoolF res;

    /** Creates a new XorF. */
    public XorF(BoolF a, BoolF b, BoolF res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.xor(a, b);
        return true;
    }
}

/** Performs an XOR operation on two BoolFv. */
class XorFv implements BasicInstruction {
    private final BoolFv a;
    private final BoolFv b;
    private final BoolFv res;

    /** Creates a new XorFv. */
    public XorFv(BoolFv a, BoolFv b, BoolFv res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.xor(a, b);
        return true;
    }
}

/** Performs an XOR operation on two BoolFe. */
class XorFe implements BasicInstruction {
    private final BoolFe a;
    private final BoolFe b;
    private final BoolFe res;

    /** Creates a new XorFe. */
    public XorFe(BoolFe a, BoolFe b, BoolFe res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.xor(a, b);
        return true;
    }
}
