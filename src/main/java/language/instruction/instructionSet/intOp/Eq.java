package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;
import language.instruction.instructionSet.boolOp.BoolOp;

class EqV extends Procedure {
    public EqV(IntV a, IntV b, BoolV res) {
        IntV test = tmp(new IntV(a.n));
        xor(a, b, test);

        set(new BoolV().zeroes(), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqVe extends Procedure {
    public EqVe(IntVe a, IntVe b, BoolVe res) {
        IntVe test = tmp(new IntVe(a.n));
        xor(a, b, test);

        set(new BoolVe().zeroes(), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqVf extends Procedure {
    public EqVf(IntVf a, IntVf b, BoolVf res) {
        IntVf test = tmp(new IntVf(a.n));
        xor(a, b, test);

        set(new BoolVf().zeroes(), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqE extends Procedure {
    public EqE(IntE a, IntE b, BoolE res) {
        IntE test = tmp(new IntE(a.n));
        xor(a, b, test);

        set(new BoolE().zeroes(), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqEv extends Procedure {
    public EqEv(IntEv a, IntEv b, BoolEv res) {
        IntEv test = tmp(new IntEv(a.n));
        xor(a, b, test);

        set(new BoolEv().zeroes(), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqEf extends Procedure {
    public EqEf(IntEf a, IntEf b, BoolEf res) {
        IntEf test = tmp(new IntEf(a.n));
        xor(a, b, test);

        set(new BoolEf().zeroes(), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqF extends Procedure {
    public EqF(IntF a, IntF b, BoolF res) {
        IntF test = tmp(new IntF(a.n));
        xor(a, b, test);

        set(new BoolF().zeroes(), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqFv extends Procedure {
    public EqFv(IntFv a, IntFv b, BoolFv res) {
        IntFv test = tmp(new IntFv(a.n));
        xor(a, b, test);

        set(new BoolFv().zeroes(), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqFe extends Procedure {
    public EqFe(IntFe a, IntFe b, BoolFe res) {
        IntFe test = tmp(new IntFe(a.n));
        xor(a, b, test);

        set(new BoolFe().zeroes(), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}