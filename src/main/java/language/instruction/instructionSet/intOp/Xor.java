package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class XorV extends Procedure {
    public XorV(IntVRef a, IntVRef b, IntVRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot XOR IntVs of different sizes.");

        BoolVRef[] bitsA = a.get().getBits();
        BoolVRef[] bitsB = b.get().getBits();
        BoolVRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorVe extends Procedure {
    public XorVe(IntVeRef a, IntVeRef b, IntVeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot XOR IntVs of different sizes.");

        BoolVeRef[] bitsA = a.get().getBits();
        BoolVeRef[] bitsB = b.get().getBits();
        BoolVeRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorVf extends Procedure {
    public XorVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot XOR IntVs of different sizes.");

        BoolVfRef[] bitsA = a.get().getBits();
        BoolVfRef[] bitsB = b.get().getBits();
        BoolVfRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorE extends Procedure {
    public XorE(IntERef a, IntERef b, IntERef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot XOR IntEs of different sizes.");

        BoolERef[] bitsA = a.get().getBits();
        BoolERef[] bitsB = b.get().getBits();
        BoolERef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorEv extends Procedure {
    public XorEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot XOR IntEs of different sizes.");

        BoolEvRef[] bitsA = a.get().getBits();
        BoolEvRef[] bitsB = b.get().getBits();
        BoolEvRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorEf extends Procedure {
    public XorEf(IntEfRef a, IntEfRef b, IntEfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot XOR IntEs of different sizes.");

        BoolEfRef[] bitsA = a.get().getBits();
        BoolEfRef[] bitsB = b.get().getBits();
        BoolEfRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorF extends Procedure {
    public XorF(IntFRef a, IntFRef b, IntFRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot XOR IntFs of different sizes.");

        BoolFRef[] bitsA = a.get().getBits();
        BoolFRef[] bitsB = b.get().getBits();
        BoolFRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorFv extends Procedure {
    public XorFv(IntFvRef a, IntFvRef b, IntFvRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot XOR IntVs of different sizes.");

        BoolFvRef[] bitsA = a.get().getBits();
        BoolFvRef[] bitsB = b.get().getBits();
        BoolFvRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorFe extends Procedure {
    public XorFe(IntFeRef a, IntFeRef b, IntFeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot XOR IntEs of different sizes.");

        BoolFeRef[] bitsA = a.get().getBits();
        BoolFeRef[] bitsB = b.get().getBits();
        BoolFeRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= a.get().n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}
