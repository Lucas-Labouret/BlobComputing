package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.obj.field.intField.*;
import language.ref.field.intField.*;

class SubV extends Procedure {
    public SubV(IntVRef a, IntVRef b, IntVRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntVs of different sizes.");

        IntVRef negB = IntVRef.of(new IntV(a.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubVe extends Procedure {
    public SubVe(IntVeRef a, IntVeRef b, IntVeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntVs of different sizes.");

        IntVeRef negB = IntVeRef.of(new IntVe(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubVf extends Procedure {
    public SubVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntVs of different sizes.");

        IntVfRef negB = IntVfRef.of(new IntVf(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubE extends Procedure {
    public SubE(IntERef a, IntERef b, IntERef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntERef negB = IntERef.of(new IntE(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubEv extends Procedure {
    public SubEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntEvRef negB = IntEvRef.of(new IntEv(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubEf extends Procedure {
    public SubEf(IntEfRef a, IntEfRef b, IntEfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntEfRef negB = IntEfRef.of(new IntEf(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubF extends Procedure {
    public SubF(IntFRef a, IntFRef b, IntFRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntFs of different sizes.");

        IntFRef negB = IntFRef.of(new IntF(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubFv extends Procedure {
    public SubFv(IntFvRef a, IntFvRef b, IntFvRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntFs of different sizes.");

        IntFvRef negB = IntFvRef.of(new IntFv(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}

class SubFe extends Procedure {
    public SubFe(IntFeRef a, IntFeRef b, IntFeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot subtract IntEs of different sizes.");

        IntFeRef negB = IntFeRef.of(new IntFe(b.get().n));
        neg(b, negB);
        add(a, negB, res);
    }
}
