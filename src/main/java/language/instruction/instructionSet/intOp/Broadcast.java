package language.instruction.instructionSet.intOp;

import language.fieldRef.Ref;
import language.instruction.BasicInstruction;
import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class BroadcastVe extends Procedure {
    public BroadcastVe(IntVRef orig, IntVeRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntV to an IntVe of different size.");

        BoolVRef[] bitsOrig = new BoolVRef[orig.get().n + 1];
        BoolVeRef[] bitsRes = new BoolVeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = new BoolVRef();
            bitsRes[i] = new BoolVeRef();
        }
        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class BroadcastVf extends Procedure {
    public BroadcastVf(IntVRef orig, IntVfRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntV to an IntVf of different size.");

        BoolVRef[] bitsOrig = new BoolVRef[orig.get().n + 1];
        BoolVfRef[] bitsRes = new BoolVfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = new BoolVRef();
            bitsRes[i] = new BoolVfRef();
        }
        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class BroadcastEv extends Procedure {
    public BroadcastEv(IntERef orig, IntEvRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntE to an IntEv of different size.");

        BoolERef[] bitsOrig = new BoolERef[orig.get().n + 1];
        BoolEvRef[] bitsRes = new BoolEvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = new BoolERef();
            bitsRes[i] = new BoolEvRef();
        }
        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class BroadcastEf extends Procedure {
    public BroadcastEf(IntERef orig, IntEfRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntE to an IntEf of different size.");

        BoolERef[] bitsOrig = new BoolERef[orig.get().n + 1];
        BoolEfRef[] bitsRes = new BoolEfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = new BoolERef();
            bitsRes[i] = new BoolEfRef();
        }
        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class BroadcastFv extends Procedure {
    public BroadcastFv(IntFRef orig, IntFvRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntF to an IntFv of different size.");

        BoolFRef[] bitsOrig = new BoolFRef[orig.get().n + 1];
        BoolFvRef[] bitsRes = new BoolFvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = new BoolFRef();
            bitsRes[i] = new BoolFvRef();
        }
        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}

class BroadcastFe extends Procedure {
    public BroadcastFe(IntFRef orig, IntFeRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntF to an IntFe of different size.");

        BoolFRef[] bitsOrig = new BoolFRef[orig.get().n + 1];
        BoolFeRef[] bitsRes = new BoolFeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) {
            bitsOrig[i] = new BoolFRef();
            bitsRes[i] = new BoolFeRef();
        }
        split(orig, bitsOrig);
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
        join(bitsRes, res);
    }
}
