package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

class GTV extends Procedure {
    public GTV(IntV a, IntV b, BoolV res) {
        if  (a.n != b.n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolV[] aBits = a.getBits();
        BoolV[] bBits = b.getBits();

        BoolV diffSign = tmp(new BoolV());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolV().ones(), res);

        BoolV diffBits = tmp(new BoolV());
        for (int i=a.n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTVe extends Procedure {
    public GTVe(IntVe a, IntVe b, BoolVe res) {
        if  (a.n != b.n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolVe[] aBits = a.getBits();
        BoolVe[] bBits = b.getBits();

        BoolVe diffSign = tmp(new BoolVe());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolVe().ones(), res);

        BoolVe diffBits = tmp(new BoolVe());
        for (int i=a.n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTVf extends Procedure {
    public GTVf(IntVf a, IntVf b, BoolVf res) {
        if  (a.n != b.n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolVf[] aBits = a.getBits();
        BoolVf[] bBits = b.getBits();

        BoolVf diffSign = tmp(new BoolVf());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolVf().ones(), res);

        BoolVf diffBits = tmp(new BoolVf());
        for (int i=a.n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTE extends Procedure {
    public GTE(IntE a, IntE b, BoolE res) {
        if  (a.n != b.n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolE[] aBits = a.getBits();
        BoolE[] bBits = b.getBits();

        BoolE diffSign = tmp(new BoolE());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolE().ones(), res);

        BoolE diffBits = tmp(new BoolE());
        for (int i=a.n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTEv extends Procedure {
    public GTEv(IntEv a, IntEv b, BoolEv res) {
        if  (a.n != b.n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolEv[] aBits = a.getBits();
        BoolEv[] bBits = b.getBits();

        BoolEv diffSign = tmp(new BoolEv());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolEv().ones(), res);

        BoolEv diffBits = tmp(new BoolEv());
        for (int i=a.n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTEf extends Procedure {
    public GTEf(IntEf a, IntEf b, BoolEf res) {
        if  (a.n != b.n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolEf[] aBits = a.getBits();
        BoolEf[] bBits = b.getBits();

        BoolEf diffSign = tmp(new BoolEf());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolEf().ones(), res);

        BoolEf diffBits = tmp(new BoolEf());
        for (int i=a.n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTF extends Procedure {
    public GTF(IntF a, IntF b, BoolF res) {
        if  (a.n != b.n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolF[] aBits = a.getBits();
        BoolF[] bBits = b.getBits();

        BoolF diffSign = tmp(new BoolF());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolF().ones(), res);

        BoolF diffBits = tmp(new BoolF());
        for (int i=a.n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTFv extends Procedure {
    public GTFv(IntFv a, IntFv b, BoolFv res) {
        if  (a.n != b.n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolFv[] aBits = a.getBits();
        BoolFv[] bBits = b.getBits();

        BoolFv diffSign = tmp(new BoolFv());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolFv().ones(), res);

        BoolFv diffBits = tmp(new BoolFv());
        for (int i=a.n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}

class GTFe extends Procedure {
    public GTFe(IntFe a, IntFe b, BoolFe res) {
        if  (a.n != b.n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        BoolFe[] aBits = a.getBits();
        BoolFe[] bBits = b.getBits();

        BoolFe diffSign = tmp(new BoolFe());
        xor(aBits[0], bBits[0], diffSign);

        set(new BoolFe().ones(), res);

        BoolFe diffBits = tmp(new BoolFe());
        for (int i=a.n; i>=1; i--) {
            xor(aBits[i], bBits[i], diffBits);
            fif(diffBits, aBits[i], res, res);
        }

        fif(diffSign, bBits[0], res, res);
    }
}