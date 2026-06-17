package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class OrV extends Procedure {
    public OrV(IntVRef a, IntVRef b, IntVRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot OR IntVs of different sizes.");

        BoolVRef[] bitsA = a.get().getBits();
        BoolVRef[] bitsB = b.get().getBits();
        BoolVRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrVe extends Procedure {
    public OrVe(IntVeRef a, IntVeRef b, IntVeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot OR IntVs of different sizes.");

        BoolVeRef[] bitsA = a.get().getBits();
        BoolVeRef[] bitsB = b.get().getBits();
        BoolVeRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrVf extends Procedure {
    public OrVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot OR IntVs of different sizes.");

        BoolVfRef[] bitsA = a.get().getBits();
        BoolVfRef[] bitsB = b.get().getBits();
        BoolVfRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrE extends Procedure {
    public OrE(IntERef a, IntERef b, IntERef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot OR IntEs of different sizes.");

        BoolERef[] bitsA = a.get().getBits();
        BoolERef[] bitsB = b.get().getBits();
        BoolERef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrEv extends Procedure {
    public OrEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot OR IntEs of different sizes.");

        BoolEvRef[] bitsA = a.get().getBits();
        BoolEvRef[] bitsB = b.get().getBits();
        BoolEvRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrEf extends Procedure {
    public OrEf(IntEfRef a, IntEfRef b, IntEfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot OR IntEs of different sizes.");

        BoolEfRef[] bitsA = a.get().getBits();
        BoolEfRef[] bitsB = b.get().getBits();
        BoolEfRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrF extends Procedure {
    public OrF(IntFRef a, IntFRef b, IntFRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot OR IntFs of different sizes.");

        BoolFRef[] bitsA = a.get().getBits();
        BoolFRef[] bitsB = b.get().getBits();
        BoolFRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrFv extends Procedure {
    public OrFv(IntFvRef a, IntFvRef b, IntFvRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot OR IntVs of different sizes.");

        BoolFvRef[] bitsA = a.get().getBits();
        BoolFvRef[] bitsB = b.get().getBits();
        BoolFvRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrFe extends Procedure {
    public OrFe(IntFeRef a, IntFeRef b, IntFeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot OR IntEs of different sizes.");

        BoolFeRef[] bitsA = a.get().getBits();
        BoolFeRef[] bitsB = b.get().getBits();
        BoolFeRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}
