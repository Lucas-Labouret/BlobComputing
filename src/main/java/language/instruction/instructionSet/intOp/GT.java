package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.field.boolField.*;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class GTV extends Procedure {
    public GTV(IntVRef a, IntVRef b, BoolVRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolVRef[] aBits = new BoolVRef[a.get().n + 1];
        BoolVRef[] bBits = new BoolVRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            aBits[i] = tmp(new BoolVRef());
            bBits[i] = tmp(new BoolVRef());
        }
        split(a, aBits);
        split(b, bBits);

        BoolVRef diffSign = tmp(new BoolVRef());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolVRef(BoolV.ones()), res);

        BoolVRef diffBits = tmp(new BoolVRef());
        for (int i=a.get().n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTVe extends Procedure {
    public GTVe(IntVeRef a, IntVeRef b, BoolVeRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolVeRef[] aBits = new BoolVeRef[a.get().n + 1];
        BoolVeRef[] bBits = new BoolVeRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            aBits[i] = tmp(new BoolVeRef());
            bBits[i] = tmp(new BoolVeRef());
        }
        split(a, aBits);
        split(b, bBits);

        BoolVeRef diffSign = tmp(new BoolVeRef());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolVeRef(BoolVe.ones()), res);

        BoolVeRef diffBits = tmp(new BoolVeRef());
        for (int i=a.get().n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTVf extends Procedure {
    public GTVf(IntVfRef a, IntVfRef b, BoolVfRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolVfRef[] aBits = new BoolVfRef[a.get().n + 1];
        BoolVfRef[] bBits = new BoolVfRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            aBits[i] = tmp(new BoolVfRef());
            bBits[i] = tmp(new BoolVfRef());
        }
        split(a, aBits);
        split(b, bBits);

        BoolVfRef diffSign = tmp(new BoolVfRef());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolVfRef(BoolVf.ones()), res);

        BoolVfRef diffBits = tmp(new BoolVfRef());
        for (int i=a.get().n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTE extends Procedure {
    public GTE(IntERef a, IntERef b, BoolERef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolERef[] aBits = new BoolERef[a.get().n + 1];
        BoolERef[] bBits = new BoolERef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            aBits[i] = tmp(new BoolERef());
            bBits[i] = tmp(new BoolERef());
        }
        split(a, aBits);
        split(b, bBits);

        BoolERef diffSign = tmp(new BoolERef());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolERef(BoolE.ones()), res);

        BoolERef diffBits = tmp(new BoolERef());
        for (int i=a.get().n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTEv extends Procedure {
    public GTEv(IntEvRef a, IntEvRef b, BoolEvRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolEvRef[] aBits = new BoolEvRef[a.get().n + 1];
        BoolEvRef[] bBits = new BoolEvRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            aBits[i] = tmp(new BoolEvRef());
            bBits[i] = tmp(new BoolEvRef());
        }
        split(a, aBits);
        split(b, bBits);

        BoolEvRef diffSign = tmp(new BoolEvRef());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolEvRef(BoolEv.ones()), res);

        BoolEvRef diffBits = tmp(new BoolEvRef());
        for (int i=a.get().n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTEf extends Procedure {
    public GTEf(IntEfRef a, IntEfRef b, BoolEfRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolEfRef[] aBits = new BoolEfRef[a.get().n + 1];
        BoolEfRef[] bBits = new BoolEfRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            aBits[i] = tmp(new BoolEfRef());
            bBits[i] = tmp(new BoolEfRef());
        }
        split(a, aBits);
        split(b, bBits);

        BoolEfRef diffSign = tmp(new BoolEfRef());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolEfRef(BoolEf.ones()), res);

        BoolEfRef diffBits = tmp(new BoolEfRef());
        for (int i=a.get().n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTF extends Procedure {
    public GTF(IntFRef a, IntFRef b, BoolFRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolFRef[] aBits = new BoolFRef[a.get().n + 1];
        BoolFRef[] bBits = new BoolFRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            aBits[i] = tmp(new BoolFRef());
            bBits[i] = tmp(new BoolFRef());
        }
        split(a, aBits);
        split(b, bBits);

        BoolFRef diffSign = tmp(new BoolFRef());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolFRef(BoolF.ones()), res);

        BoolFRef diffBits = tmp(new BoolFRef());
        for (int i=a.get().n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTFv extends Procedure {
    public GTFv(IntFvRef a, IntFvRef b, BoolFvRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolFvRef[] aBits = new BoolFvRef[a.get().n + 1];
        BoolFvRef[] bBits = new BoolFvRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            aBits[i] = tmp(new BoolFvRef());
            bBits[i] = tmp(new BoolFvRef());
        }
        split(a, aBits);
        split(b, bBits);

        BoolFvRef diffSign = tmp(new BoolFvRef());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolFvRef(BoolFv.ones()), res);

        BoolFvRef diffBits = tmp(new BoolFvRef());
        for (int i=a.get().n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTFe extends Procedure {
    public GTFe(IntFeRef a, IntFeRef b, BoolFeRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolFeRef[] aBits = new BoolFeRef[a.get().n + 1];
        BoolFeRef[] bBits = new BoolFeRef[b.get().n + 1];
        for (int i = 0; i <= a.get().n; i++) {
            aBits[i] = tmp(new BoolFeRef());
            bBits[i] = tmp(new BoolFeRef());
        }
        split(a, aBits);
        split(b, bBits);

        BoolFeRef diffSign = tmp(new BoolFeRef());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolFeRef(BoolFe.ones()), res);

        BoolFeRef diffBits = tmp(new BoolFeRef());
        for (int i=a.get().n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}