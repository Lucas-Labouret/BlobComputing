package language.instruction.instructionSet.intOp;

import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;
import language.instruction.Procedure;

class TransferVe extends Procedure {
    public TransferVe(IntVeRef in, IntEvRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolVeRef[] inBits = in.get().getBits();
        BoolEvRef[] outBits = out.get().getBits();
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
    }
}

class TransferVf extends Procedure {
    public TransferVf(IntVfRef in, IntFvRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolVfRef[] inBits = in.get().getBits();
        BoolFvRef[] outBits = out.get().getBits();
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
    }
}

class TransferEv extends Procedure {
    public TransferEv(IntEvRef in, IntVeRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolEvRef[] inBits = in.get().getBits();
        BoolVeRef[] outBits = out.get().getBits();
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
    }
}

class TransferEf extends Procedure {
    public TransferEf(IntEfRef in, IntFeRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolEfRef[] inBits = in.get().getBits();
        BoolFeRef[] outBits = out.get().getBits();
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
    }
}

class TransferFv extends Procedure {
    public TransferFv(IntFvRef in, IntVfRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolFvRef[] inBits = in.get().getBits();
        BoolVfRef[] outBits = out.get().getBits();
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
    }
}

class TransferFe extends Procedure {
    public TransferFe(IntFeRef in, IntEfRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolFeRef[] inBits = in.get().getBits();
        BoolEfRef[] outBits = out.get().getBits();
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
    }
}