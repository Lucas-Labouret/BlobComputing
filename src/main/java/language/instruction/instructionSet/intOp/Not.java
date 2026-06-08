package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class NotV extends Procedure {
    public NotV(IntVRef orig, IntVRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntV to an IntV of different size.");

        BoolVRef[] bitsA = new BoolVRef[orig.get().n + 1];
        BoolVRef[] bitsRes = new BoolVRef[orig.get().n + 1];
        for(int i = 0; i <= orig.get().n; i++) {
            bitsA[i] = tmp(new BoolVRef());
            bitsRes[i] = tmp(new BoolVRef());
        }

        split(orig, bitsA);
        for (int i = 0; i <= orig.get().n; i++) not(bitsA[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class NotVe extends Procedure {
    public NotVe(IntVeRef orig, IntVeRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntVe to an IntVe of different size.");

        BoolVeRef[] bitsOrig = new BoolVeRef[orig.get().n + 1];
        BoolVeRef[] bitsRes = new BoolVeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolVeRef());
            bitsRes[i] = tmp(new BoolVeRef());
        }

        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class NotVf extends Procedure {
    public NotVf(IntVfRef orig, IntVfRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntVf to an IntVf of different size.");

        BoolVfRef[] bitsOrig = new BoolVfRef[orig.get().n + 1];
        BoolVfRef[] bitsRes = new BoolVfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolVfRef());
            bitsRes[i] = tmp(new BoolVfRef());
        }

        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class NotE extends Procedure {
    public NotE(IntERef orig, IntERef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntE to an IntE of different size.");

        BoolERef[] bitsOrig = new BoolERef[orig.get().n + 1];
        BoolERef[] bitsRes = new BoolERef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolERef());
            bitsRes[i] = tmp(new BoolERef());
        }

        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class NotEv extends Procedure {
    public NotEv(IntEvRef orig, IntEvRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntEv to an IntEv of different size.");

        BoolEvRef[] bitsOrig = new BoolEvRef[orig.get().n + 1];
        BoolEvRef[] bitsRes = new BoolEvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolEvRef());
            bitsRes[i] = tmp(new BoolEvRef());
        }

        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class NotEf extends Procedure {
    public NotEf(IntEfRef orig, IntEfRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntEf to an IntEf of different size.");

        BoolEfRef[] bitsOrig = new BoolEfRef[orig.get().n + 1];
        BoolEfRef[] bitsRes = new BoolEfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolEfRef());
            bitsRes[i] = tmp(new BoolEfRef());
        }

        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class NotF extends Procedure {
    public NotF(IntFRef orig, IntFRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntF to an IntF of different size.");

        BoolFRef[] bitsOrig = new BoolFRef[orig.get().n + 1];
        BoolFRef[] bitsRes = new BoolFRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolFRef());
            bitsRes[i] = tmp(new BoolFRef());
        }

        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class NotFv extends Procedure {
    public NotFv(IntFvRef orig, IntFvRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntFv to an IntFv of different size.");

        BoolFvRef[] bitsOrig = new BoolFvRef[orig.get().n + 1];
        BoolFvRef[] bitsRes = new BoolFvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolFvRef());
            bitsRes[i] = tmp(new BoolFvRef());
        }

        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class NotFe extends Procedure {
    public NotFe(IntFeRef orig, IntFeRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntFe to an IntFe of different size.");

        BoolFeRef[] bitsOrig = new BoolFeRef[orig.get().n + 1];
        BoolFeRef[] bitsRes = new BoolFeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolFeRef());
            bitsRes[i] = tmp(new BoolFeRef());
        }

        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) not(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}
