package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class RotVeCW extends Procedure {
    public RotVeCW(IntVeRef orig, IntVfRef res) {
        BoolVeRef[] origBits = new BoolVeRef[orig.get().n + 1];
        BoolVfRef[] resBits  = new BoolVfRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolVeRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolVfRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}

class RotVeCCW extends Procedure {
    public RotVeCCW(IntVeRef orig, IntVfRef res) {
        BoolVeRef[] origBits = new BoolVeRef[orig.get().n + 1];
        BoolVfRef[] resBits  = new BoolVfRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolVeRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolVfRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}

class RotVfCW extends Procedure {
    public RotVfCW(IntVfRef orig, IntVeRef res) {
        BoolVfRef[] origBits = new BoolVfRef[orig.get().n + 1];
        BoolVeRef[] resBits  = new BoolVeRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolVfRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolVeRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}

class RotVfCCW extends Procedure {
    public RotVfCCW(IntVfRef orig, IntVeRef res) {
        BoolVfRef[] origBits = new BoolVfRef[orig.get().n + 1];
        BoolVeRef[] resBits  = new BoolVeRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolVfRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolVeRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}

class RotEvCW extends Procedure {
    public RotEvCW(IntEvRef orig, IntEfRef res) {
        BoolEvRef[] origBits = new BoolEvRef[orig.get().n + 1];
        BoolEfRef[] resBits  = new BoolEfRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolEvRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolEfRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}

class RotEvCCW extends Procedure {
    public RotEvCCW(IntEvRef orig, IntEfRef res) {
        BoolEvRef[] origBits = new BoolEvRef[orig.get().n + 1];
        BoolEfRef[] resBits  = new BoolEfRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolEvRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolEfRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}

class RotEfCW extends Procedure {
    public RotEfCW(IntEfRef orig, IntEvRef res) {
        BoolEfRef[] origBits = new BoolEfRef[orig.get().n + 1];
        BoolEvRef[] resBits  = new BoolEvRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolEfRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolEvRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}

class RotEfCCW extends Procedure {
    public RotEfCCW(IntEfRef orig, IntEvRef res) {
        BoolEfRef[] origBits = new BoolEfRef[orig.get().n + 1];
        BoolEvRef[] resBits  = new BoolEvRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolEfRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolEvRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}

class RotFvCW extends Procedure {
    public RotFvCW(IntFvRef orig, IntFeRef res) {
        BoolFvRef[] origBits = new BoolFvRef[orig.get().n + 1];
        BoolFeRef[] resBits  = new BoolFeRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolFvRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolFeRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}

class RotFvCCW extends Procedure {
    public RotFvCCW(IntFvRef orig, IntFeRef res) {
        BoolFvRef[] origBits = new BoolFvRef[orig.get().n + 1];
        BoolFeRef[] resBits  = new BoolFeRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolFvRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolFeRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}

class RotFeCW extends Procedure {
    public RotFeCW(IntFeRef orig, IntFvRef res) {
        BoolFeRef[] origBits = new BoolFeRef[orig.get().n + 1];
        BoolFvRef[] resBits  = new BoolFvRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolFeRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolFvRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}

class RotFeCCW extends Procedure {
    public RotFeCCW(IntFeRef orig, IntFvRef res) {
        BoolFeRef[] origBits = new BoolFeRef[orig.get().n + 1];
        BoolFvRef[] resBits  = new BoolFvRef[res .get().n + 1];
        for (int i = 0; i < origBits.length; i++) { origBits[i] = tmp(new BoolFeRef()); }
        for (int i = 0; i < resBits .length; i++) { resBits [i] = tmp(new BoolFvRef()); }

        split(orig, origBits);
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
        join(resBits, res);
    }
}
