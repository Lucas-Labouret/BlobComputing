package language.instruction.instructionSet.intOp;

import language.field.intField.*;
import language.instruction.Procedure;

class SubV extends Procedure {
    public SubV(IntV a, IntV b, IntV res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot subtract IntVs of different sizes.");

        IntV negB = tmp(new IntV(b.n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubVe extends Procedure {
    public SubVe(IntVe a, IntVe b, IntVe res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot subtract IntVs of different sizes.");

        IntVe negB = tmp(new IntVe(b.n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubVf extends Procedure {
    public SubVf(IntVf a, IntVf b, IntVf res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot subtract IntVs of different sizes.");

        IntVf negB = tmp(new IntVf(b.n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubE extends Procedure {
    public SubE(IntE a, IntE b, IntE res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntE negB = tmp(new IntE(b.n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubEv extends Procedure {
    public SubEv(IntEv a, IntEv b, IntEv res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntEv negB = tmp(new IntEv(b.n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubEf extends Procedure {
    public SubEf(IntEf a, IntEf b, IntEf res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntEf negB = tmp(new IntEf(b.n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubF extends Procedure {
    public SubF(IntF a, IntF b, IntF res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot subtract IntFs of different sizes.");

        IntF negB = tmp(new IntF(b.n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubFv extends Procedure {
    public SubFv(IntFv a, IntFv b, IntFv res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot subtract IntFs of different sizes.");

        IntFv negB = tmp(new IntFv(b.n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubFe extends Procedure {
    public SubFe(IntFe a, IntFe b, IntFe res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntFe negB = tmp(new IntFe(b.n));
        neg(b, negB);
        add(a, negB, res);
    }
}
