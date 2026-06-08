package language.instruction.instructionSet.intOp;

import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;
import language.instruction.Procedure;

class TransferVe extends Procedure {
    public TransferVe(IntVeRef in, IntEvRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolVeRef[] inBits = new BoolVeRef[in.get().n + 1];
        BoolEvRef[] outBits = new BoolEvRef[in.get().n + 1];
        for (int i = 0; i <= in.get().n; i++) {
            inBits[i] = tmp(new BoolVeRef());
            outBits[i] = tmp(new BoolEvRef());
        }
        split(in, inBits);
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
        join(outBits, out);
    }
}

class TransferVf extends Procedure {
    public TransferVf(IntVfRef in, IntFvRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolVfRef[] inBits = new BoolVfRef[in.get().n + 1];
        BoolFvRef[] outBits = new BoolFvRef[in.get().n + 1];
        for (int i = 0; i <= in.get().n; i++) {
            inBits[i] = tmp(new BoolVfRef());
            outBits[i] = tmp(new BoolFvRef());
        }
        split(in, inBits);
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
        join(outBits, out);
    }
}

class TransferEv extends Procedure {
    public TransferEv(IntEvRef in, IntVeRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolEvRef[] inBits = new BoolEvRef[in.get().n + 1];
        BoolVeRef[] outBits = new BoolVeRef[in.get().n + 1];
        for (int i = 0; i <= in.get().n; i++) {
            inBits[i] = tmp(new BoolEvRef());
            outBits[i] = tmp(new BoolVeRef());
        }
        split(in, inBits);
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
        join(outBits, out);
    }
}

class TransferEf extends Procedure {
    public TransferEf(IntEfRef in, IntFeRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolEfRef[] inBits = new BoolEfRef[in.get().n + 1];
        BoolFeRef[] outBits = new BoolFeRef[in.get().n + 1];
        for (int i = 0; i <= in.get().n; i++) {
            inBits[i] = tmp(new BoolEfRef());
            outBits[i] = tmp(new BoolFeRef());
        }
        split(in, inBits);
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
        join(outBits, out);
    }
}

class TransferFv extends Procedure {
    public TransferFv(IntFvRef in, IntVfRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolFvRef[] inBits = new BoolFvRef[in.get().n + 1];
        BoolVfRef[] outBits = new BoolVfRef[in.get().n + 1];
        for (int i = 0; i <= in.get().n; i++) {
            inBits[i] = tmp(new BoolFvRef());
            outBits[i] = tmp(new BoolVfRef());
        }
        split(in, inBits);
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
        join(outBits, out);
    }
}

class TransferFe extends Procedure {
    public TransferFe(IntFeRef in, IntEfRef out) {
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Int transfer input and output must have the same number of bits.");

        BoolFeRef[] inBits = new BoolFeRef[in.get().n + 1];
        BoolEfRef[] outBits = new BoolEfRef[in.get().n + 1];
        for (int i = 0; i <= in.get().n; i++) {
            inBits[i] = tmp(new BoolFeRef());
            outBits[i] = tmp(new BoolEfRef());
        }
        split(in, inBits);
        for (int i = 0; i <= in.get().n; i++) transfer(inBits[i], outBits[i]);
        join(outBits, out);
    }
}