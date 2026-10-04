package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.Procedure;

class IfV extends Procedure {
    public IfV(BoolV cond, BoolV t, BoolV f, BoolV res) {
        BoolV notCond = tmp(new BoolV());
        BoolV tmpTrue = tmp(new BoolV());
        BoolV tmpFalse = tmp(new BoolV());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfVe extends Procedure {
    public IfVe(BoolVe cond, BoolVe t, BoolVe f, BoolVe res) {
        BoolVe notCond = tmp(new BoolVe());
        BoolVe tmpTrue = tmp(new BoolVe());
        BoolVe tmpFalse = tmp(new BoolVe());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfVf extends Procedure {
    public IfVf(BoolVf cond, BoolVf t, BoolVf f, BoolVf res) {
        BoolVf notCond = tmp(new BoolVf());
        BoolVf tmpTrue = tmp(new BoolVf());
        BoolVf tmpFalse = tmp(new BoolVf());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfE extends Procedure {
    public IfE(BoolE cond, BoolE t, BoolE f, BoolE res) {
        BoolE notCond = tmp(new BoolE());
        BoolE tmpTrue = tmp(new BoolE());
        BoolE tmpFalse = tmp(new BoolE());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfEv extends Procedure {
    public IfEv(BoolEv cond, BoolEv t, BoolEv f, BoolEv res) {
        BoolEv notCond = tmp(new BoolEv());
        BoolEv tmpTrue = tmp(new BoolEv());
        BoolEv tmpFalse = tmp(new BoolEv());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfEf extends Procedure {
    public IfEf(BoolEf cond, BoolEf t, BoolEf f, BoolEf res) {
        BoolEf notCond = tmp(new BoolEf());
        BoolEf tmpTrue = tmp(new BoolEf());
        BoolEf tmpFalse = tmp(new BoolEf());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfF extends Procedure {
    public IfF(BoolF cond, BoolF t, BoolF f, BoolF res) {
        BoolF notCond = tmp(new BoolF());
        BoolF tmpTrue = tmp(new BoolF());
        BoolF tmpFalse = tmp(new BoolF());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfFv extends Procedure {
    public IfFv(BoolFv cond, BoolFv t, BoolFv f, BoolFv res) {
        BoolFv notCond = tmp(new BoolFv());
        BoolFv tmpTrue = tmp(new BoolFv());
        BoolFv tmpFalse = tmp(new BoolFv());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}

class IfFe extends Procedure {
    public IfFe(BoolFe cond, BoolFe t, BoolFe f, BoolFe res) {
        BoolFe notCond = tmp(new BoolFe());
        BoolFe tmpTrue = tmp(new BoolFe());
        BoolFe tmpFalse = tmp(new BoolFe());

        and(cond, t, tmpTrue);

        not(cond, notCond);
        and(notCond, f, tmpFalse);

        or(tmpTrue, tmpFalse, res);
    }
}