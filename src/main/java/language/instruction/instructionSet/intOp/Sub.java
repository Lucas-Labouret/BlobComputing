package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.field.intField.*;
import language.fieldRef.intField.*;

class SubV extends Procedure {
    public SubV(IntVRef a, IntVRef b, IntVRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntVs of different sizes.");

        IntVRef negB = new IntVRef(new IntV(a.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubVe extends Procedure {
    public SubVe(IntVeRef a, IntVeRef b, IntVeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntVs of different sizes.");

        IntVeRef negB = new IntVeRef(new IntVe(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubVf extends Procedure {
    public SubVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntVs of different sizes.");

        IntVfRef negB = new IntVfRef(new IntVf(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubE extends Procedure {
    public SubE(IntERef a, IntERef b, IntERef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntERef negB = new IntERef(new IntE(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubEv extends Procedure {
    public SubEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntEvRef negB = new IntEvRef(new IntEv(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubEf extends Procedure {
    public SubEf(IntEfRef a, IntEfRef b, IntEfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntEfRef negB = new IntEfRef(new IntEf(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubF extends Procedure {
    public SubF(IntFRef a, IntFRef b, IntFRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntFs of different sizes.");

        IntFRef negB = new IntFRef(new IntF(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubFv extends Procedure {
    public SubFv(IntFvRef a, IntFvRef b, IntFvRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntFs of different sizes.");

        IntFvRef negB = new IntFvRef(new IntFv(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubFe extends Procedure {
    public SubFe(IntFeRef a, IntFeRef b, IntFeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntFeRef negB = new IntFeRef(new IntFe(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}
