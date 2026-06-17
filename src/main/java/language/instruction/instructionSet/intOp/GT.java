package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.field.boolField.*;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class GTV extends Procedure {
    public GTV(IntVRef a, IntVRef b, BoolVRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolVRef[] aBits = a.get().getBits();
        BoolVRef[] bBits = b.get().getBits();

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

        BoolVeRef[] aBits = a.get().getBits();
        BoolVeRef[] bBits = b.get().getBits();

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

        BoolVfRef[] aBits = a.get().getBits();
        BoolVfRef[] bBits = b.get().getBits();

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

        BoolERef[] aBits = a.get().getBits();
        BoolERef[] bBits = b.get().getBits();

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

        BoolEvRef[] aBits = a.get().getBits();
        BoolEvRef[] bBits = b.get().getBits();

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

        BoolEfRef[] aBits = a.get().getBits();
        BoolEfRef[] bBits = b.get().getBits();

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

        BoolFRef[] aBits = a.get().getBits();
        BoolFRef[] bBits = b.get().getBits();

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

        BoolFvRef[] aBits = a.get().getBits();
        BoolFvRef[] bBits = b.get().getBits();

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

        BoolFeRef[] aBits = a.get().getBits();
        BoolFeRef[] bBits = b.get().getBits();

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