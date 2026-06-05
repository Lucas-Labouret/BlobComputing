package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class IfV extends Procedure {
    public IfV(BoolVRef cond, IntVRef t, IntVRef f, IntVRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolVRef[] tBits = new BoolVRef[t.get().n + 1];
        BoolVRef[] fBits = new BoolVRef[f.get().n + 1];
        BoolVRef[] resBits = new BoolVRef[res.get().n + 1];
        for (int i=0; i<=t.get().n; i++) {
            tBits[i] = tmp(new BoolVRef());
            fBits[i] = tmp(new BoolVRef());
            resBits[i] = tmp(new BoolVRef());
        }
        split(t, tBits);
        split(f, fBits);

        for (int i=0; i<=t.get().n; i++)
            fif(cond, tBits[i], fBits[i], resBits[i]);

        join(resBits, res);
    }
}

class IfVe extends Procedure {
    public IfVe(BoolVeRef cond, IntVeRef t, IntVeRef f, IntVeRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolVeRef[] tBits = new BoolVeRef[t.get().n + 1];
        BoolVeRef[] fBits = new BoolVeRef[f.get().n + 1];
        BoolVeRef[] resBits = new BoolVeRef[res.get().n + 1];
        for (int i=0; i<=t.get().n; i++) {
            tBits[i] = tmp(new BoolVeRef());
            fBits[i] = tmp(new BoolVeRef());
            resBits[i] = tmp(new BoolVeRef());
        }
        split(t, tBits);
        split(f, fBits);

        for (int i=0; i<=t.get().n; i++)
            fif(cond, tBits[i], fBits[i], resBits[i]);

        join(resBits, res);
    }
}

class IfVf extends Procedure {
    public IfVf(BoolVfRef cond, IntVfRef t, IntVfRef f, IntVfRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolVfRef[] tBits = new BoolVfRef[t.get().n + 1];
        BoolVfRef[] fBits = new BoolVfRef[f.get().n + 1];
        BoolVfRef[] resBits = new BoolVfRef[res.get().n + 1];
        for (int i=0; i<=t.get().n; i++) {
            tBits[i] = tmp(new BoolVfRef());
            fBits[i] = tmp(new BoolVfRef());
            resBits[i] = tmp(new BoolVfRef());
        }
        split(t, tBits);
        split(f, fBits);

        for (int i=0; i<=t.get().n; i++)
            fif(cond, tBits[i], fBits[i], resBits[i]);

        join(resBits, res);
    }
}

class IfE extends Procedure {
    public IfE(BoolERef cond, IntERef t, IntERef f, IntERef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolERef[] tBits = new BoolERef[t.get().n + 1];
        BoolERef[] fBits = new BoolERef[f.get().n + 1];
        BoolERef[] resBits = new BoolERef[res.get().n + 1];
        for (int i=0; i<=t.get().n; i++) {
            tBits[i] = tmp(new BoolERef());
            fBits[i] = tmp(new BoolERef());
            resBits[i] = tmp(new BoolERef());
        }
        split(t, tBits);
        split(f, fBits);

        for (int i=0; i<=t.get().n; i++)
            fif(cond, tBits[i], fBits[i], resBits[i]);

        join(resBits, res);
    }
}

class IfEv extends Procedure {
    public IfEv(BoolEvRef cond, IntEvRef t, IntEvRef f, IntEvRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolEvRef[] tBits = new BoolEvRef[t.get().n + 1];
        BoolEvRef[] fBits = new BoolEvRef[f.get().n + 1];
        BoolEvRef[] resBits = new BoolEvRef[res.get().n + 1];
        for (int i=0; i<=t.get().n; i++) {
            tBits[i] = tmp(new BoolEvRef());
            fBits[i] = tmp(new BoolEvRef());
            resBits[i] = tmp(new BoolEvRef());
        }
        split(t, tBits);
        split(f, fBits);

        for (int i=0; i<=t.get().n; i++)
            fif(cond, tBits[i], fBits[i], resBits[i]);

        join(resBits, res);
    }
}

class IfEf extends Procedure {
    public IfEf(BoolEfRef cond, IntEfRef t, IntEfRef f, IntEfRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolEfRef[] tBits = new BoolEfRef[t.get().n + 1];
        BoolEfRef[] fBits = new BoolEfRef[f.get().n + 1];
        BoolEfRef[] resBits = new BoolEfRef[res.get().n + 1];
        for (int i=0; i<=t.get().n; i++) {
            tBits[i] = tmp(new BoolEfRef());
            fBits[i] = tmp(new BoolEfRef());
            resBits[i] = tmp(new BoolEfRef());
        }
        split(t, tBits);
        split(f, fBits);

        for (int i=0; i<=t.get().n; i++)
            fif(cond, tBits[i], fBits[i], resBits[i]);

        join(resBits, res);
    }
}

class IfF extends Procedure {
    public IfF(BoolFRef cond, IntFRef t, IntFRef f, IntFRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolFRef[] tBits = new BoolFRef[t.get().n + 1];
        BoolFRef[] fBits = new BoolFRef[f.get().n + 1];
        BoolFRef[] resBits = new BoolFRef[res.get().n + 1];
        for (int i=0; i<=t.get().n; i++) {
            tBits[i] = tmp(new BoolFRef());
            fBits[i] = tmp(new BoolFRef());
            resBits[i] = tmp(new BoolFRef());
        }
        split(t, tBits);
        split(f, fBits);

        for (int i=0; i<=t.get().n; i++)
            fif(cond, tBits[i], fBits[i], resBits[i]);

        join(resBits, res);
    }
}

class IfFv extends Procedure {
    public IfFv(BoolFvRef cond, IntFvRef t, IntFvRef f, IntFvRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolFvRef[] tBits = new BoolFvRef[t.get().n + 1];
        BoolFvRef[] fBits = new BoolFvRef[f.get().n + 1];
        BoolFvRef[] resBits = new BoolFvRef[res.get().n + 1];
        for (int i=0; i<=t.get().n; i++) {
            tBits[i] = tmp(new BoolFvRef());
            fBits[i] = tmp(new BoolFvRef());
            resBits[i] = tmp(new BoolFvRef());
        }
        split(t, tBits);
        split(f, fBits);

        for (int i=0; i<=t.get().n; i++)
            fif(cond, tBits[i], fBits[i], resBits[i]);

        join(resBits, res);
    }
}

class IfFe extends Procedure {
    public IfFe(BoolFeRef cond, IntFeRef t, IntFeRef f, IntFeRef res) {
        if (t.get().n != f.get().n || t.get().n != res.get().n)
            throw new IllegalArgumentException("t, f, and res must have the same number of bits");

        BoolFeRef[] tBits = new BoolFeRef[t.get().n + 1];
        BoolFeRef[] fBits = new BoolFeRef[f.get().n + 1];
        BoolFeRef[] resBits = new BoolFeRef[res.get().n + 1];
        for (int i=0; i<=t.get().n; i++) {
            tBits[i] = tmp(new BoolFeRef());
            fBits[i] = tmp(new BoolFeRef());
            resBits[i] = tmp(new BoolFeRef());
        }
        split(t, tBits);
        split(f, fBits);

        for (int i=0; i<=t.get().n; i++)
            fif(cond, tBits[i], fBits[i], resBits[i]);

        join(resBits, res);
    }
}

