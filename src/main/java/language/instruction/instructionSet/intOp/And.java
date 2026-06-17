package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class AndV extends Procedure {
    public AndV(IntVRef a, IntVRef b, IntVRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolVRef[] bitsA = a.get().getBits();
        BoolVRef[] bitsB = b.get().getBits();
        BoolVRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndVe extends Procedure {
    public AndVe(IntVeRef a, IntVeRef b, IntVeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolVeRef[] bitsA = a.get().getBits();
        BoolVeRef[] bitsB = b.get().getBits();
        BoolVeRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndVf extends Procedure {
    public AndVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolVfRef[] bitsA = a.get().getBits();
        BoolVfRef[] bitsB = b.get().getBits();
        BoolVfRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndE extends Procedure {
    public AndE(IntERef a, IntERef b, IntERef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolERef[] bitsA = a.get().getBits();
        BoolERef[] bitsB = b.get().getBits();
        BoolERef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndEv extends Procedure {
    public AndEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolEvRef[] bitsA = a.get().getBits();
        BoolEvRef[] bitsB = b.get().getBits();
        BoolEvRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndEf extends Procedure {
    public AndEf(IntEfRef a, IntEfRef b, IntEfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolEfRef[] bitsA = a.get().getBits();
        BoolEfRef[] bitsB = b.get().getBits();
        BoolEfRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndF extends Procedure {
    public AndF(IntFRef a, IntFRef b, IntFRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntFs of different sizes.");

        BoolFRef[] bitsA = a.get().getBits();
        BoolFRef[] bitsB = b.get().getBits();
        BoolFRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndFv extends Procedure {
    public AndFv(IntFvRef a, IntFvRef b, IntFvRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolFvRef[] bitsA = a.get().getBits();
        BoolFvRef[] bitsB = b.get().getBits();
        BoolFvRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndFe extends Procedure {
    public AndFe(IntFeRef a, IntFeRef b, IntFeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolFeRef[] bitsA = a.get().getBits();
        BoolFeRef[] bitsB = b.get().getBits();
        BoolFeRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

