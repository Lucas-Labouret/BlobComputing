package language.instruction.instructionSet.intOp;

import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;
import language.instruction.Procedure;

class BroadcastVe extends Procedure {
    public BroadcastVe(IntVRef orig, IntVeRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntV to an IntVe of different size.");

        BoolVRef[] bitsOrig = orig.get().getBits();
        BoolVeRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}

class BroadcastVf extends Procedure {
    public BroadcastVf(IntVRef orig, IntVfRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntV to an IntVf of different size.");

        BoolVRef[] bitsOrig = orig.get().getBits();
        BoolVfRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}

class BroadcastEv extends Procedure {
    public BroadcastEv(IntERef orig, IntEvRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntE to an IntEv of different size.");

        BoolERef[] bitsOrig = orig.get().getBits();
        BoolEvRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}

class BroadcastEf extends Procedure {
    public BroadcastEf(IntERef orig, IntEfRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntE to an IntEf of different size.");

        BoolERef[] bitsOrig = orig.get().getBits();
        BoolEfRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}

class BroadcastFv extends Procedure {
    public BroadcastFv(IntFRef orig, IntFvRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntF to an IntFv of different size.");

        BoolFRef[] bitsOrig = orig.get().getBits();
        BoolFvRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}

class BroadcastFe extends Procedure {
    public BroadcastFe(IntFRef orig, IntFeRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot broadcast an IntF to an IntFe of different size.");

        BoolFRef[] bitsOrig = orig.get().getBits();
        BoolFeRef[] bitsRes = res.get().getBits();
        for (int i = 0; i <= orig.get().n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}
