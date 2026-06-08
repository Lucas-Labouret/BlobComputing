package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class AndV extends Procedure {
    public AndV(IntVRef a, IntVRef b, IntVRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolVRef[] bitsA = new BoolVRef[a.get().n + 1];
        BoolVRef[] bitsB = new BoolVRef[a.get().n + 1];
        BoolVRef[] bitsRes = new BoolVRef[a.get().n + 1];
        for(int i = 0; i <= a.get().n; i++) {
            bitsA[i] = tmp(new BoolVRef());
            bitsB[i] = tmp(new BoolVRef());
            bitsRes[i] = tmp(new BoolVRef());
        }

        split(a, bitsA);
        split(b, bitsB);
        for (int i = 0; i <= a.get().n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class AndVe extends Procedure {
    public AndVe(IntVeRef orig, IntVeRef b, IntVeRef res) {
        if (orig.get().n != b.get().n || orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolVeRef[] bitsOrig = new BoolVeRef[orig.get().n + 1];
        BoolVeRef[] bitsB = new BoolVeRef[orig.get().n + 1];
        BoolVeRef[] bitsRes = new BoolVeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolVeRef());
            bitsB[i] = tmp(new BoolVeRef());
            bitsRes[i] = tmp(new BoolVeRef());
        }

        split(orig, bitsOrig);
        split(b, bitsB);
        for (int i = 0; i <= orig.get().n; i++) and(bitsOrig[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class AndVf extends Procedure {
    public AndVf(IntVfRef orig, IntVfRef b, IntVfRef res) {
        if (orig.get().n != b.get().n || orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolVfRef[] bitsOrig = new BoolVfRef[orig.get().n + 1];
        BoolVfRef[] bitsB = new BoolVfRef[orig.get().n + 1];
        BoolVfRef[] bitsRes = new BoolVfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolVfRef());
            bitsB[i] = tmp(new BoolVfRef());
            bitsRes[i] = tmp(new BoolVfRef());
        }

        split(orig, bitsOrig);
        split(b, bitsB);
        for (int i = 0; i <= orig.get().n; i++) and(bitsOrig[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class AndE extends Procedure {
    public AndE(IntERef orig, IntERef b, IntERef res) {
        if (orig.get().n != b.get().n || orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolERef[] bitsOrig = new BoolERef[orig.get().n + 1];
        BoolERef[] bitsB = new BoolERef[orig.get().n + 1];
        BoolERef[] bitsRes = new BoolERef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolERef());
            bitsB[i] = tmp(new BoolERef());
            bitsRes[i] = tmp(new BoolERef());
        }

        split(orig, bitsOrig);
        split(b, bitsB);
        for (int i = 0; i <= orig.get().n; i++) and(bitsOrig[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class AndEv extends Procedure {
    public AndEv(IntEvRef orig, IntEvRef b, IntEvRef res) {
        if (orig.get().n != b.get().n || orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolEvRef[] bitsOrig = new BoolEvRef[orig.get().n + 1];
        BoolEvRef[] bitsB = new BoolEvRef[orig.get().n + 1];
        BoolEvRef[] bitsRes = new BoolEvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolEvRef());
            bitsB[i] = tmp(new BoolEvRef());
            bitsRes[i] = tmp(new BoolEvRef());
        }

        split(orig, bitsOrig);
        split(b, bitsB);
        for (int i = 0; i <= orig.get().n; i++) and(bitsOrig[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class AndEf extends Procedure {
    public AndEf(IntEfRef orig, IntEfRef b, IntEfRef res) {
        if (orig.get().n != b.get().n || orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolEfRef[] bitsOrig = new BoolEfRef[orig.get().n + 1];
        BoolEfRef[] bitsB = new BoolEfRef[orig.get().n + 1];
        BoolEfRef[] bitsRes = new BoolEfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolEfRef());
            bitsB[i] = tmp(new BoolEfRef());
            bitsRes[i] = tmp(new BoolEfRef());
        }

        split(orig, bitsOrig);
        split(b, bitsB);
        for (int i = 0; i <= orig.get().n; i++) and(bitsOrig[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class AndF extends Procedure {
    public AndF(IntFRef orig, IntFRef b, IntFRef res) {
        if (orig.get().n != b.get().n || orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntFs of different sizes.");

        BoolFRef[] bitsOrig = new BoolFRef[orig.get().n + 1];
        BoolFRef[] bitsB = new BoolFRef[orig.get().n + 1];
        BoolFRef[] bitsRes = new BoolFRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolFRef());
            bitsB[i] = tmp(new BoolFRef());
            bitsRes[i] = tmp(new BoolFRef());
        }

        split(orig, bitsOrig);
        split(b, bitsB);
        for (int i = 0; i <= orig.get().n; i++) and(bitsOrig[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class AndFv extends Procedure {
    public AndFv(IntFvRef orig, IntFvRef b, IntFvRef res) {
        if (orig.get().n != b.get().n || orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolFvRef[] bitsOrig = new BoolFvRef[orig.get().n + 1];
        BoolFvRef[] bitsB = new BoolFvRef[orig.get().n + 1];
        BoolFvRef[] bitsRes = new BoolFvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolFvRef());
            bitsB[i] = tmp(new BoolFvRef());
            bitsRes[i] = tmp(new BoolFvRef());
        }

        split(orig, bitsOrig);
        split(b, bitsB);
        for (int i = 0; i <= orig.get().n; i++) and(bitsOrig[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class AndFe extends Procedure {
    public AndFe(IntFeRef orig, IntFeRef b, IntFeRef res) {
        if (orig.get().n != b.get().n || orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolFeRef[] bitsOrig = new BoolFeRef[orig.get().n + 1];
        BoolFeRef[] bitsB = new BoolFeRef[orig.get().n + 1];
        BoolFeRef[] bitsRes = new BoolFeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = tmp(new BoolFeRef());
            bitsB[i] = tmp(new BoolFeRef());
            bitsRes[i] = tmp(new BoolFeRef());
        }

        split(orig, bitsOrig);
        split(b, bitsB);
        for (int i = 0; i <= orig.get().n; i++) and(bitsOrig[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

