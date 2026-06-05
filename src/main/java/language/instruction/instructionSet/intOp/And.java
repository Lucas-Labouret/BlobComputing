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
            bitsA[i] = new BoolVRef();
            bitsB[i] = new BoolVRef();
            bitsRes[i] = new BoolVRef();
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
            bitsOrig[i] = new BoolVeRef();
            bitsB[i] = new BoolVeRef();
            bitsRes[i] = new BoolVeRef();
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
            bitsOrig[i] = new BoolVfRef();
            bitsB[i] = new BoolVfRef();
            bitsRes[i] = new BoolVfRef();
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
            bitsOrig[i] = new BoolERef();
            bitsB[i] = new BoolERef();
            bitsRes[i] = new BoolERef();
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
            bitsOrig[i] = new BoolEvRef();
            bitsB[i] = new BoolEvRef();
            bitsRes[i] = new BoolEvRef();
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
            bitsOrig[i] = new BoolEfRef();
            bitsB[i] = new BoolEfRef();
            bitsRes[i] = new BoolEfRef();
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
            bitsOrig[i] = new BoolFRef();
            bitsB[i] = new BoolFRef();
            bitsRes[i] = new BoolFRef();
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
            bitsOrig[i] = new BoolFvRef();
            bitsB[i] = new BoolFvRef();
            bitsRes[i] = new BoolFvRef();
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
            bitsOrig[i] = new BoolFeRef();
            bitsB[i] = new BoolFeRef();
            bitsRes[i] = new BoolFeRef();
        }

        split(orig, bitsOrig);
        split(b, bitsB);
        for (int i = 0; i <= orig.get().n; i++) and(bitsOrig[i], bitsB[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

