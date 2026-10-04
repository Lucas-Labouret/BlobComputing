package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

class AbsV extends Procedure {
    public AbsV(IntV a, IntV res) {
        if (a.n != res.n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntVs of different sizes.");

        IntV negA = tmp(new IntV(a.n));
        neg(a, negA);

        BoolV sign = new BoolV();
        set(a.bits[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsVe extends Procedure {
    public AbsVe(IntVe a, IntVe res) {
        if (a.n != res.n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntVes of different sizes.");

        IntVe negA = tmp(new IntVe(a.n));
        neg(a, negA);

        BoolVe sign = new BoolVe();
        set(a.bits[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsVf extends Procedure {
    public AbsVf(IntVf a, IntVf res) {
        if (a.n != res.n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntVfs of different sizes.");

        IntVf negA = tmp(new IntVf(a.n));
        neg(a, negA);

        BoolVf sign = new BoolVf();
        set(a.bits[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsE extends Procedure {
    public AbsE(IntE a, IntE res) {
        if (a.n != res.n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntEs of different sizes.");

        IntE negA = tmp(new IntE(a.n));
        neg(a, negA);

        BoolE sign = new BoolE();
        set(a.bits[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsEv extends Procedure {
    public AbsEv(IntEv a, IntEv res) {
        if (a.n != res.n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntEvs of different sizes.");

        IntEv negA = tmp(new IntEv(a.n));
        neg(a, negA);

        BoolEv sign = new BoolEv();
        set(a.bits[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsEf extends Procedure {
    public AbsEf(IntEf a, IntEf res) {
        if (a.n != res.n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntEfs of different sizes.");

        IntEf negA = tmp(new IntEf(a.n));
        neg(a, negA);

        BoolEf sign = new BoolEf();
        set(a.bits[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsF extends Procedure {
    public AbsF(IntF a, IntF res) {
        if (a.n != res.n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntFs of different sizes.");

        IntF negA = tmp(new IntF(a.n));
        neg(a, negA);

        BoolF sign = new BoolF();
        set(a.bits[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsFv extends Procedure {
    public AbsFv(IntFv a, IntFv res) {
        if (a.n != res.n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntFvs of different sizes.");

        IntFv negA = tmp(new IntFv(a.n));
        neg(a, negA);

        BoolFv sign = new BoolFv();
        set(a.bits[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsFe extends Procedure {
    public AbsFe(IntFe a, IntFe res) {
        if (a.n != res.n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntFes of different sizes.");

        IntFe negA = tmp(new IntFe(a.n));
        neg(a, negA);

        BoolFe sign = new BoolFe();
        set(a.bits[0], sign);
        fif(sign, negA, a, res);
    }
}