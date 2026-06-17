package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class RotVeCW extends Procedure {
    public RotVeCW(IntVeRef orig, IntVfRef res) {
        BoolVeRef[] origBits = orig.get().getBits();
        BoolVfRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotVeCCW extends Procedure {
    public RotVeCCW(IntVeRef orig, IntVfRef res) {
        BoolVeRef[] origBits = orig.get().getBits();
        BoolVfRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}

class RotVfCW extends Procedure {
    public RotVfCW(IntVfRef orig, IntVeRef res) {
        BoolVfRef[] origBits = orig.get().getBits();
        BoolVeRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotVfCCW extends Procedure {
    public RotVfCCW(IntVfRef orig, IntVeRef res) {
        BoolVfRef[] origBits = orig.get().getBits();
        BoolVeRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}

class RotEvCW extends Procedure {
    public RotEvCW(IntEvRef orig, IntEfRef res) {
        BoolEvRef[] origBits = orig.get().getBits();
        BoolEfRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotEvCCW extends Procedure {
    public RotEvCCW(IntEvRef orig, IntEfRef res) {
        BoolEvRef[] origBits = orig.get().getBits();
        BoolEfRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}

class RotEfCW extends Procedure {
    public RotEfCW(IntEfRef orig, IntEvRef res) {
        BoolEfRef[] origBits = orig.get().getBits();
        BoolEvRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotEfCCW extends Procedure {
    public RotEfCCW(IntEfRef orig, IntEvRef res) {
        BoolEfRef[] origBits = orig.get().getBits();
        BoolEvRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}

class RotFvCW extends Procedure {
    public RotFvCW(IntFvRef orig, IntFeRef res) {
        BoolFvRef[] origBits = orig.get().getBits();
        BoolFeRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotFvCCW extends Procedure {
    public RotFvCCW(IntFvRef orig, IntFeRef res) {
        BoolFvRef[] origBits = orig.get().getBits();
        BoolFeRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}

class RotFeCW extends Procedure {
    public RotFeCW(IntFeRef orig, IntFvRef res) {
        BoolFeRef[] origBits = orig.get().getBits();
        BoolFvRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotFeCCW extends Procedure {
    public RotFeCCW(IntFeRef orig, IntFvRef res) {
        BoolFeRef[] origBits = orig.get().getBits();
        BoolFvRef[] resBits  = res.get().getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}
