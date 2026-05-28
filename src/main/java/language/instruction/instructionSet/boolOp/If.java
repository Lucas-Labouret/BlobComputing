package language.instruction.instructionSet.boolOp;

import language.instruction.Procedure;
import language.ref.field.boolField.fieldS.*;
import language.ref.field.boolField.fieldT.*;

class IfV extends Procedure {
    public IfV(BoolVRef cond, BoolVRef t, BoolVRef f, BoolVRef res) {
        BoolVRef notCond = new BoolVRef();
        BoolVRef tmpTrue = new BoolVRef();
        BoolVRef tmpFalse = new BoolVRef();

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfVe extends Procedure {
    public IfVe(BoolVeRef cond, BoolVeRef t, BoolVeRef f, BoolVeRef res) {
        BoolVeRef notCond = new BoolVeRef();
        BoolVeRef tmpTrue = new BoolVeRef();
        BoolVeRef tmpFalse = new BoolVeRef();

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfVf extends Procedure {
    public IfVf(BoolVfRef cond, BoolVfRef t, BoolVfRef f, BoolVfRef res) {
        BoolVfRef notCond = new BoolVfRef();
        BoolVfRef tmpTrue = new BoolVfRef();
        BoolVfRef tmpFalse = new BoolVfRef();

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfE extends Procedure {
    public IfE(BoolERef cond, BoolERef t, BoolERef f, BoolERef res) {
        BoolERef notCond = new BoolERef();
        BoolERef tmpTrue = new BoolERef();
        BoolERef tmpFalse = new BoolERef();

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfEv extends Procedure {
    public IfEv(BoolEvRef cond, BoolEvRef t, BoolEvRef f, BoolEvRef res) {
        BoolEvRef notCond = new BoolEvRef();
        BoolEvRef tmpTrue = new BoolEvRef();
        BoolEvRef tmpFalse = new BoolEvRef();

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfEf extends Procedure {
    public IfEf(BoolEfRef cond, BoolEfRef t, BoolEfRef f, BoolEfRef res) {
        BoolEfRef notCond = new BoolEfRef();
        BoolEfRef tmpTrue = new BoolEfRef();
        BoolEfRef tmpFalse = new BoolEfRef();

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfF extends Procedure {
    public IfF(BoolFRef cond, BoolFRef t, BoolFRef f, BoolFRef res) {
        BoolFRef notCond = new BoolFRef();
        BoolFRef tmpTrue = new BoolFRef();
        BoolFRef tmpFalse = new BoolFRef();

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfFv extends Procedure {
    public IfFv(BoolFvRef cond, BoolFvRef t, BoolFvRef f, BoolFvRef res) {
        BoolFvRef notCond = new BoolFvRef();
        BoolFvRef tmpTrue = new BoolFvRef();
        BoolFvRef tmpFalse = new BoolFvRef();

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfFe extends Procedure {
    public IfFe(BoolFeRef cond, BoolFeRef t, BoolFeRef f, BoolFeRef res) {
        BoolFeRef notCond = new BoolFeRef();
        BoolFeRef tmpTrue = new BoolFeRef();
        BoolFeRef tmpFalse = new BoolFeRef();

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}