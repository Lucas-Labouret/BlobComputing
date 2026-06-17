package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class IfV extends Procedure {
    public IfV(BoolVRef cond, IntVRef t, IntVRef f, IntVRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolVRef[] tBits = t.get().getBits();
        BoolVRef[] fBits = f.get().getBits();
        BoolVRef[] resBits = res.get().getBits();
        for (int i=0; i<=t.get().n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfVe extends Procedure {
    public IfVe(BoolVeRef cond, IntVeRef t, IntVeRef f, IntVeRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolVeRef[] tBits = t.get().getBits();
        BoolVeRef[] fBits = f.get().getBits();
        BoolVeRef[] resBits = res.get().getBits();
        for (int i=0; i<=t.get().n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfVf extends Procedure {
    public IfVf(BoolVfRef cond, IntVfRef t, IntVfRef f, IntVfRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolVfRef[] tBits = t.get().getBits();
        BoolVfRef[] fBits = f.get().getBits();
        BoolVfRef[] resBits = res.get().getBits();
        for (int i=0; i<=t.get().n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfE extends Procedure {
    public IfE(BoolERef cond, IntERef t, IntERef f, IntERef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolERef[] tBits = t.get().getBits();
        BoolERef[] fBits = f.get().getBits();
        BoolERef[] resBits = res.get().getBits();
        for (int i=0; i<=t.get().n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfEv extends Procedure {
    public IfEv(BoolEvRef cond, IntEvRef t, IntEvRef f, IntEvRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolEvRef[] tBits = t.get().getBits();
        BoolEvRef[] fBits = f.get().getBits();
        BoolEvRef[] resBits = res.get().getBits();
        for (int i=0; i<=t.get().n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfEf extends Procedure {
    public IfEf(BoolEfRef cond, IntEfRef t, IntEfRef f, IntEfRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolEfRef[] tBits = t.get().getBits();
        BoolEfRef[] fBits = f.get().getBits();
        BoolEfRef[] resBits = res.get().getBits();
        for (int i=0; i<=t.get().n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfF extends Procedure {
    public IfF(BoolFRef cond, IntFRef t, IntFRef f, IntFRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolFRef[] tBits = t.get().getBits();
        BoolFRef[] fBits = f.get().getBits();
        BoolFRef[] resBits = res.get().getBits();
        for (int i=0; i<=t.get().n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfFv extends Procedure {
    public IfFv(BoolFvRef cond, IntFvRef t, IntFvRef f, IntFvRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolFvRef[] tBits = t.get().getBits();
        BoolFvRef[] fBits = f.get().getBits();
        BoolFvRef[] resBits = res.get().getBits();
        for (int i=0; i<=t.get().n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

class IfFe extends Procedure {
    public IfFe(BoolFeRef cond, IntFeRef t, IntFeRef f, IntFeRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolFeRef[] tBits = t.get().getBits();
        BoolFeRef[] fBits = f.get().getBits();
        BoolFeRef[] resBits = res.get().getBits();
        for (int i=0; i<=t.get().n; i++) fif(cond, tBits[i], fBits[i], resBits[i]);
    }
}

