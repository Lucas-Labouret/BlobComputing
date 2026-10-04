package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

class RotVeCW extends Procedure {
    public RotVeCW(IntVe orig, IntVf res) {
        BoolVe[] origBits = orig.getBits();
        BoolVf[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotVeCCW extends Procedure {
    public RotVeCCW(IntVe orig, IntVf res) {
        BoolVe[] origBits = orig.getBits();
        BoolVf[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}

class RotVfCW extends Procedure {
    public RotVfCW(IntVf orig, IntVe res) {
        BoolVf[] origBits = orig.getBits();
        BoolVe[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotVfCCW extends Procedure {
    public RotVfCCW(IntVf orig, IntVe res) {
        BoolVf[] origBits = orig.getBits();
        BoolVe[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}

class RotEvCW extends Procedure {
    public RotEvCW(IntEv orig, IntEf res) {
        BoolEv[] origBits = orig.getBits();
        BoolEf[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotEvCCW extends Procedure {
    public RotEvCCW(IntEv orig, IntEf res) {
        BoolEv[] origBits = orig.getBits();
        BoolEf[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}

class RotEfCW extends Procedure {
    public RotEfCW(IntEf orig, IntEv res) {
        BoolEf[] origBits = orig.getBits();
        BoolEv[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotEfCCW extends Procedure {
    public RotEfCCW(IntEf orig, IntEv res) {
        BoolEf[] origBits = orig.getBits();
        BoolEv[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}

class RotFvCW extends Procedure {
    public RotFvCW(IntFv orig, IntFe res) {
        BoolFv[] origBits = orig.getBits();
        BoolFe[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotFvCCW extends Procedure {
    public RotFvCCW(IntFv orig, IntFe res) {
        BoolFv[] origBits = orig.getBits();
        BoolFe[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}

class RotFeCW extends Procedure {
    public RotFeCW(IntFe orig, IntFv res) {
        BoolFe[] origBits = orig.getBits();
        BoolFv[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCW(origBits[i], resBits[i]);
    }
}

class RotFeCCW extends Procedure {
    public RotFeCCW(IntFe orig, IntFv res) {
        BoolFe[] origBits = orig.getBits();
        BoolFv[] resBits  = res.getBits();
        for (int i = 0; i < resBits.length; i++) rotCCW(origBits[i], resBits[i]);
    }
}
