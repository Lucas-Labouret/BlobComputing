package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.ref.field.boolField.*;
import language.ref.field.intField.*;

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
