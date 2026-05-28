package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.instruction.instructionSet.boolOp.BoolOp;
import language.obj.field.boolField.*;
import language.obj.field.intField.*;
import language.ref.field.boolField.*;
import language.ref.field.intField.*;

class EqV extends Procedure {
    public EqV(IntVRef a, IntVRef b, BoolVRef res) {
        IntVRef test = IntVRef.of(new IntV(a.get().n));
        xor(a, b, test);

        set(res, BoolVRef.of(BoolV.zeroes()));
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqVe extends Procedure {
    public EqVe(IntVeRef a, IntVeRef b, BoolVeRef res) {
        IntVeRef test = IntVeRef.of(new IntVe(a.get().n));
        xor(a, b, test);

        set(res, BoolVeRef.of(BoolVe.zeroes()));
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqVf extends Procedure {
    public EqVf(IntVfRef a, IntVfRef b, BoolVfRef res) {
        IntVfRef test = IntVfRef.of(new IntVf(a.get().n));
        xor(a, b, test);

        set(res, BoolVfRef.of(BoolVf.zeroes()));
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqE extends Procedure {
    public EqE(IntERef a, IntERef b, BoolERef res) {
        IntERef test = IntERef.of(new IntE(a.get().n));
        xor(a, b, test);

        set(res, BoolERef.of(BoolE.zeroes()));
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqEv extends Procedure {
    public EqEv(IntEvRef a, IntEvRef b, BoolEvRef res) {
        IntEvRef test = IntEvRef.of(new IntEv(a.get().n));
        xor(a, b, test);

        set(res, BoolEvRef.of(BoolEv.zeroes()));
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqEf extends Procedure {
    public EqEf(IntEfRef a, IntEfRef b, BoolEfRef res) {
        IntEfRef test = IntEfRef.of(new IntEf(a.get().n));
        xor(a, b, test);

        set(res, BoolEfRef.of(BoolEf.zeroes()));
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqF extends Procedure {
    public EqF(IntFRef a, IntFRef b, BoolFRef res) {
        IntFRef test = IntFRef.of(new IntF(a.get().n));
        xor(a, b, test);

        set(res, BoolFRef.of(BoolF.zeroes()));
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqFv extends Procedure {
    public EqFv(IntFvRef a, IntFvRef b, BoolFvRef res) {
        IntFvRef test = IntFvRef.of(new IntFv(a.get().n));
        xor(a, b, test);

        set(res, BoolFvRef.of(BoolFv.zeroes()));
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}

class EqFe extends Procedure {
    public EqFe(IntFeRef a, IntFeRef b, BoolFeRef res) {
        IntFeRef test = IntFeRef.of(new IntFe(a.get().n));
        xor(a, b, test);

        set(res, BoolFeRef.of(BoolFe.zeroes()));
        call(IntOp.scanLeft(test, res, (bit, acc) -> BoolOp.or(bit, acc, acc)));
        not(res, res);
    }
}