package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class NotV extends Procedure {
    public NotV(IntVRef orig, IntVRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntV to an IntV of different size.");

        BoolVRef[] bitsOrig = orig.get().getBits();
        BoolVRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotVe extends Procedure {
    public NotVe(IntVeRef orig, IntVeRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntVe to an IntVe of different size.");

        BoolVeRef[] bitsOrig = orig.get().getBits();
        BoolVeRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotVf extends Procedure {
    public NotVf(IntVfRef orig, IntVfRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntVf to an IntVf of different size.");

        BoolVfRef[] bitsOrig = orig.get().getBits();
        BoolVfRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotE extends Procedure {
    public NotE(IntERef orig, IntERef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntE to an IntE of different size.");

        BoolERef[] bitsOrig = orig.get().getBits();
        BoolERef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotEv extends Procedure {
    public NotEv(IntEvRef orig, IntEvRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntEv to an IntEv of different size.");

        BoolEvRef[] bitsOrig = orig.get().getBits();
        BoolEvRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotEf extends Procedure {
    public NotEf(IntEfRef orig, IntEfRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntEf to an IntEf of different size.");

        BoolEfRef[] bitsOrig = orig.get().getBits();
        BoolEfRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotF extends Procedure {
    public NotF(IntFRef orig, IntFRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntF to an IntF of different size.");

        BoolFRef[] bitsOrig = orig.get().getBits();
        BoolFRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotFv extends Procedure {
    public NotFv(IntFvRef orig, IntFvRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntFv to an IntFv of different size.");

        BoolFvRef[] bitsOrig = orig.get().getBits();
        BoolFvRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotFe extends Procedure {
    public NotFe(IntFeRef orig, IntFeRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntFe to an IntFe of different size.");

        BoolFeRef[] bitsOrig = orig.get().getBits();
        BoolFeRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}
