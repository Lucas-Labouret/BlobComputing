package language.instruction.instructionSet.intOp;

import language.field.intField.*;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;
import language.instruction.Procedure;

class AbsV extends Procedure {
    public AbsV(IntVRef a, IntVRef res) {
        if (a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntVs of different sizes.");

        IntVRef negA = tmp(new IntVRef(new IntV(a.get().n)));
        neg(a, negA);

        BoolVRef sign = new BoolVRef();
        set(a.get().getBits()[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsVe extends Procedure {
    public AbsVe(IntVeRef a, IntVeRef res) {
        if (a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntVes of different sizes.");

        IntVeRef negA = tmp(new IntVeRef(new IntVe(a.get().n)));
        neg(a, negA);

        BoolVeRef sign = new BoolVeRef();
        set(a.get().getBits()[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsVf extends Procedure {
    public AbsVf(IntVfRef a, IntVfRef res) {
        if (a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntVfs of different sizes.");

        IntVfRef negA = tmp(new IntVfRef(new IntVf(a.get().n)));
        neg(a, negA);

        BoolVfRef sign = new BoolVfRef();
        set(a.get().getBits()[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsE extends Procedure {
    public AbsE(IntERef a, IntERef res) {
        if (a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntEs of different sizes.");

        IntERef negA = tmp(new IntERef(new IntE(a.get().n)));
        neg(a, negA);

        BoolERef sign = new BoolERef();
        set(a.get().getBits()[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsEv extends Procedure {
    public AbsEv(IntEvRef a, IntEvRef res) {
        if (a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntEvs of different sizes.");

        IntEvRef negA = tmp(new IntEvRef(new IntEv(a.get().n)));
        neg(a, negA);

        BoolEvRef sign = new BoolEvRef();
        set(a.get().getBits()[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsEf extends Procedure {
    public AbsEf(IntEfRef a, IntEfRef res) {
        if (a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntEfs of different sizes.");

        IntEfRef negA = tmp(new IntEfRef(new IntEf(a.get().n)));
        neg(a, negA);

        BoolEfRef sign = new BoolEfRef();
        set(a.get().getBits()[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsF extends Procedure {
    public AbsF(IntFRef a, IntFRef res) {
        if (a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntFs of different sizes.");

        IntFRef negA = tmp(new IntFRef(new IntF(a.get().n)));
        neg(a, negA);

        BoolFRef sign = new BoolFRef();
        set(a.get().getBits()[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsFv extends Procedure {
    public AbsFv(IntFvRef a, IntFvRef res) {
        if (a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntFvs of different sizes.");

        IntFvRef negA = tmp(new IntFvRef(new IntFv(a.get().n)));
        neg(a, negA);

        BoolFvRef sign = new BoolFvRef();
        set(a.get().getBits()[0], sign);
        fif(sign, negA, a, res);
    }
}

class AbsFe extends Procedure {
    public AbsFe(IntFeRef a, IntFeRef res) {
        if (a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot compute absolute value of IntFes of different sizes.");

        IntFeRef negA = tmp(new IntFeRef(new IntFe(a.get().n)));
        neg(a, negA);

        BoolFeRef sign = new BoolFeRef();
        set(a.get().getBits()[0], sign);
        fif(sign, negA, a, res);
    }
}