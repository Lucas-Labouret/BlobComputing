package language.instruction.instructionSet.boolOp;

import language.instruction.BasicInstruction;
import language.field.boolField.*;
import language.fieldRef.boolField.*;

/** Performs an XOR operation on two BoolV. */
class XorV implements BasicInstruction {
    private final BoolVRef a;
    private final BoolVRef b;
    private final BoolVRef res;

    /** Creates a new XorV. */
    public XorV(BoolVRef a, BoolVRef b, BoolVRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolV.xor(a.get(), b.get()));
        return true;
    }
}

/** Performs an XOR operation on two BoolVe. */
class XorVe implements BasicInstruction {
    private final BoolVeRef a;
    private final BoolVeRef b;
    private final BoolVeRef res;

    /** Creates a new XorVe. */
    public XorVe(BoolVeRef a, BoolVeRef b, BoolVeRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVe.xor(a.get(), b.get()));
        return true;
    }
}

/** Performs an XOR operation on two BoolVf. */
class XorVf implements BasicInstruction {
    private final BoolVfRef a;
    private final BoolVfRef b;
    private final BoolVfRef res;

    /** Creates a new XorVf. */
    public XorVf(BoolVfRef a, BoolVfRef b, BoolVfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolVf.xor(a.get(), b.get()));
        return true;
    }
}

/** Performs an XOR operation on two BoolE. */
class XorE implements BasicInstruction {
    private final BoolERef a;
    private final BoolERef b;
    private final BoolERef res;

    /** Creates a new XorE. */
    public XorE(BoolERef a, BoolERef b, BoolERef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolE.xor(a.get(), b.get()));
        return true;
    }
}

/** Performs an XOR operation on two BoolEv. */
class XorEv implements BasicInstruction {
    private final BoolEvRef a;
    private final BoolEvRef b;
    private final BoolEvRef res;

    /** Creates a new XorEv. */
    public XorEv(BoolEvRef a, BoolEvRef b, BoolEvRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEv.xor(a.get(), b.get()));
        return true;
    }
}

/** Performs an XOR operation on two BoolEf. */
class XorEf implements BasicInstruction {
    private final BoolEfRef a;
    private final BoolEfRef b;
    private final BoolEfRef res;

    /** Creates a new XorEf. */
    public XorEf(BoolEfRef a, BoolEfRef b, BoolEfRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolEf.xor(a.get(), b.get()));
        return true;
    }
}

/** Performs an XOR operation on two BoolF. */
class XorF implements BasicInstruction {
    private final BoolFRef a;
    private final BoolFRef b;
    private final BoolFRef res;

    /** Creates a new XorF. */
    public XorF(BoolFRef a, BoolFRef b, BoolFRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolF.xor(a.get(), b.get()));
        return true;
    }
}

/** Performs an XOR operation on two BoolFv. */
class XorFv implements BasicInstruction {
    private final BoolFvRef a;
    private final BoolFvRef b;
    private final BoolFvRef res;

    /** Creates a new XorFv. */
    public XorFv(BoolFvRef a, BoolFvRef b, BoolFvRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFv.xor(a.get(), b.get()));
        return true;
    }
}

/** Performs an XOR operation on two BoolFe. */
class XorFe implements BasicInstruction {
    private final BoolFeRef a;
    private final BoolFeRef b;
    private final BoolFeRef res;

    /** Creates a new XorFe. */
    public XorFe(BoolFeRef a, BoolFeRef b, BoolFeRef res) {
        this.a = a;
        this.b = b;
        this.res = res;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        res.set(BoolFe.xor(a.get(), b.get()));
        return true;
    }
}
