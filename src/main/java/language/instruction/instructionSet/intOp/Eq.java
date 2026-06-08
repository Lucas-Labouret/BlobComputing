package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.instruction.instructionSet.boolOp.BoolOp;
import language.field.boolField.*;
import language.field.intField.*;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class EqV extends Procedure {
    public EqV(IntVRef a, IntVRef b, BoolVRef res) {
        IntVRef test = tmp(new IntVRef(new IntV(a.get().n)));
        xor(a, b, test);

        set(new BoolVRef(BoolV.zeroes()), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqVe extends Procedure {
    public EqVe(IntVeRef a, IntVeRef b, BoolVeRef res) {
        IntVeRef test = tmp(new IntVeRef(new IntVe(a.get().n)));
        xor(a, b, test);

        set(new BoolVeRef(BoolVe.zeroes()), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqVf extends Procedure {
    public EqVf(IntVfRef a, IntVfRef b, BoolVfRef res) {
        IntVfRef test = tmp(new IntVfRef(new IntVf(a.get().n)));
        xor(a, b, test);

        set(new BoolVfRef(BoolVf.zeroes()), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqE extends Procedure {
    public EqE(IntERef a, IntERef b, BoolERef res) {
        IntERef test = tmp(new IntERef(new IntE(a.get().n)));
        xor(a, b, test);

        set(new BoolERef(BoolE.zeroes()), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqEv extends Procedure {
    public EqEv(IntEvRef a, IntEvRef b, BoolEvRef res) {
        IntEvRef test = tmp(new IntEvRef(new IntEv(a.get().n)));
        xor(a, b, test);

        set(new BoolEvRef(BoolEv.zeroes()), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqEf extends Procedure {
    public EqEf(IntEfRef a, IntEfRef b, BoolEfRef res) {
        IntEfRef test = tmp(new IntEfRef(new IntEf(a.get().n)));
        xor(a, b, test);

        set(new BoolEfRef(BoolEf.zeroes()), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqF extends Procedure {
    public EqF(IntFRef a, IntFRef b, BoolFRef res) {
        IntFRef test = tmp(new IntFRef(new IntF(a.get().n)));
        xor(a, b, test);

        set(new BoolFRef(BoolF.zeroes()), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqFv extends Procedure {
    public EqFv(IntFvRef a, IntFvRef b, BoolFvRef res) {
        IntFvRef test = tmp(new IntFvRef(new IntFv(a.get().n)));
        xor(a, b, test);

        set(new BoolFvRef(BoolFv.zeroes()), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqFe extends Procedure {
    public EqFe(IntFeRef a, IntFeRef b, BoolFeRef res) {
        IntFeRef test = tmp(new IntFeRef(new IntFe(a.get().n)));
        xor(a, b, test);

        set(new BoolFeRef(BoolFe.zeroes()), res);
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}