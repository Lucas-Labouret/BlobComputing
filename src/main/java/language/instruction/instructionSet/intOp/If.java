package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

class IfV extends Procedure {
    public IfV(BoolV cond, IntV t, IntV f, IntV res) {
        if (t.n != f.n || t.n != res.n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolV[] tBits = t.getBits();
        BoolV[] fBits = f.getBits();
        BoolV[] resBits = res.getBits();
        for (int i=0; i<=t.n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfVe extends Procedure {
    public IfVe(BoolVe cond, IntVe t, IntVe f, IntVe res) {
        if (t.n != f.n || t.n != res.n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolVe[] tBits = t.getBits();
        BoolVe[] fBits = f.getBits();
        BoolVe[] resBits = res.getBits();
        for (int i=0; i<=t.n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfVf extends Procedure {
    public IfVf(BoolVf cond, IntVf t, IntVf f, IntVf res) {
        if (t.n != f.n || t.n != res.n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolVf[] tBits = t.getBits();
        BoolVf[] fBits = f.getBits();
        BoolVf[] resBits = res.getBits();
        for (int i=0; i<=t.n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfE extends Procedure {
    public IfE(BoolE cond, IntE t, IntE f, IntE res) {
        if (t.n != f.n || t.n != res.n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolE[] tBits = t.getBits();
        BoolE[] fBits = f.getBits();
        BoolE[] resBits = res.getBits();
        for (int i=0; i<=t.n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfEv extends Procedure {
    public IfEv(BoolEv cond, IntEv t, IntEv f, IntEv res) {
        if (t.n != f.n || t.n != res.n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolEv[] tBits = t.getBits();
        BoolEv[] fBits = f.getBits();
        BoolEv[] resBits = res.getBits();
        for (int i=0; i<=t.n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfEf extends Procedure {
    public IfEf(BoolEf cond, IntEf t, IntEf f, IntEf res) {
        if (t.n != f.n || t.n != res.n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolEf[] tBits = t.getBits();
        BoolEf[] fBits = f.getBits();
        BoolEf[] resBits = res.getBits();
        for (int i=0; i<=t.n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfF extends Procedure {
    public IfF(BoolF cond, IntF t, IntF f, IntF res) {
        if (t.n != f.n || t.n != res.n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolF[] tBits = t.getBits();
        BoolF[] fBits = f.getBits();
        BoolF[] resBits = res.getBits();
        for (int i=0; i<=t.n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfFv extends Procedure {
    public IfFv(BoolFv cond, IntFv t, IntFv f, IntFv res) {
        if (t.n != f.n || t.n != res.n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolFv[] tBits = t.getBits();
        BoolFv[] fBits = f.getBits();
        BoolFv[] resBits = res.getBits();
        for (int i=0; i<=t.n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfFe extends Procedure {
    public IfFe(BoolFe cond, IntFe t, IntFe f, IntFe res) {
        if (t.n != f.n || t.n != res.n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolFe[] tBits = t.getBits();
        BoolFe[] fBits = f.getBits();
        BoolFe[] resBits = res.getBits();
        for (int i=0; i<=t.n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

