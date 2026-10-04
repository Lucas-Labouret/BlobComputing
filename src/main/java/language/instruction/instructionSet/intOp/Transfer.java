package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

class TransferVe extends Procedure {
    public TransferVe(IntVe in, IntEv out) {
        if (in.n != out.n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolVe[] inBits = in.getBits();
        BoolEv[] outBits = out.getBits();
        for (int i = 0; i <= in.n; i++) transfer(inBits[i], outBits[i]);
    }
}

class TransferVf extends Procedure {
    public TransferVf(IntVf in, IntFv out) {
        if (in.n != out.n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolVf[] inBits = in.getBits();
        BoolFv[] outBits = out.getBits();
        for (int i = 0; i <= in.n; i++) transfer(inBits[i], outBits[i]);
    }
}

class TransferEv extends Procedure {
    public TransferEv(IntEv in, IntVe out) {
        if (in.n != out.n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolEv[] inBits = in.getBits();
        BoolVe[] outBits = out.getBits();
        for (int i = 0; i <= in.n; i++) transfer(inBits[i], outBits[i]);
    }
}

class TransferEf extends Procedure {
    public TransferEf(IntEf in, IntFe out) {
        if (in.n != out.n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolEf[] inBits = in.getBits();
        BoolFe[] outBits = out.getBits();
        for (int i = 0; i <= in.n; i++) transfer(inBits[i], outBits[i]);
    }
}

class TransferFv extends Procedure {
    public TransferFv(IntFv in, IntVf out) {
        if (in.n != out.n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolFv[] inBits = in.getBits();
        BoolVf[] outBits = out.getBits();
        for (int i = 0; i <= in.n; i++) transfer(inBits[i], outBits[i]);
    }
}

class TransferFe extends Procedure {
    public TransferFe(IntFe in, IntEf out) {
        if (in.n != out.n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolFe[] inBits = in.getBits();
        BoolEf[] outBits = out.getBits();
        for (int i = 0; i <= in.n; i++) transfer(inBits[i], outBits[i]);
    }
}