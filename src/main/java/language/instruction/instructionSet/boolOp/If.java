package language.instruction.instructionSet.boolOp;

import language.instruction.Procedure;
import language.fieldRef.boolField.*;

class IfV extends Procedure {
    public IfV(BoolVRef cond, BoolVRef t, BoolVRef f, BoolVRef res) {
        BoolVRef notCond = tmp(new BoolVRef());
        BoolVRef tmpTrue = tmp(new BoolVRef());
        BoolVRef tmpFalse = tmp(new BoolVRef());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfVe extends Procedure {
    public IfVe(BoolVeRef cond, BoolVeRef t, BoolVeRef f, BoolVeRef res) {
        BoolVeRef notCond = tmp(new BoolVeRef());
        BoolVeRef tmpTrue = tmp(new BoolVeRef());
        BoolVeRef tmpFalse = tmp(new BoolVeRef());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfVf extends Procedure {
    public IfVf(BoolVfRef cond, BoolVfRef t, BoolVfRef f, BoolVfRef res) {
        BoolVfRef notCond = tmp(new BoolVfRef());
        BoolVfRef tmpTrue = tmp(new BoolVfRef());
        BoolVfRef tmpFalse = tmp(new BoolVfRef());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfE extends Procedure {
    public IfE(BoolERef cond, BoolERef t, BoolERef f, BoolERef res) {
        BoolERef notCond = tmp(new BoolERef());
        BoolERef tmpTrue = tmp(new BoolERef());
        BoolERef tmpFalse = tmp(new BoolERef());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfEv extends Procedure {
    public IfEv(BoolEvRef cond, BoolEvRef t, BoolEvRef f, BoolEvRef res) {
        BoolEvRef notCond = tmp(new BoolEvRef());
        BoolEvRef tmpTrue = tmp(new BoolEvRef());
        BoolEvRef tmpFalse = tmp(new BoolEvRef());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfEf extends Procedure {
    public IfEf(BoolEfRef cond, BoolEfRef t, BoolEfRef f, BoolEfRef res) {
        BoolEfRef notCond = tmp(new BoolEfRef());
        BoolEfRef tmpTrue = tmp(new BoolEfRef());
        BoolEfRef tmpFalse = tmp(new BoolEfRef());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfF extends Procedure {
    public IfF(BoolFRef cond, BoolFRef t, BoolFRef f, BoolFRef res) {
        BoolFRef notCond = tmp(new BoolFRef());
        BoolFRef tmpTrue = tmp(new BoolFRef());
        BoolFRef tmpFalse = tmp(new BoolFRef());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfFv extends Procedure {
    public IfFv(BoolFvRef cond, BoolFvRef t, BoolFvRef f, BoolFvRef res) {
        BoolFvRef notCond = tmp(new BoolFvRef());
        BoolFvRef tmpTrue = tmp(new BoolFvRef());
        BoolFvRef tmpFalse = tmp(new BoolFvRef());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfFe extends Procedure {
    public IfFe(BoolFeRef cond, BoolFeRef t, BoolFeRef f, BoolFeRef res) {
        BoolFeRef notCond = tmp(new BoolFeRef());
        BoolFeRef tmpTrue = tmp(new BoolFeRef());
        BoolFeRef tmpFalse = tmp(new BoolFeRef());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}