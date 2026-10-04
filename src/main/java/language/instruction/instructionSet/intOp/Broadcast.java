package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

class BroadcastVe extends Procedure {
    public BroadcastVe(IntV orig, IntVe res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot broadcast an IntV to an IntVe of different size.");

        BoolV[] bitsOrig = orig.getBits();
        BoolVe[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}

class BroadcastVf extends Procedure {
    public BroadcastVf(IntV orig, IntVf res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot broadcast an IntV to an IntVf of different size.");

        BoolV[] bitsOrig = orig.getBits();
        BoolVf[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}

class BroadcastEv extends Procedure {
    public BroadcastEv(IntE orig, IntEv res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot broadcast an IntE to an IntEv of different size.");

        BoolE[] bitsOrig = orig.getBits();
        BoolEv[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}

class BroadcastEf extends Procedure {
    public BroadcastEf(IntE orig, IntEf res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot broadcast an IntE to an IntEf of different size.");

        BoolE[] bitsOrig = orig.getBits();
        BoolEf[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}

class BroadcastFv extends Procedure {
    public BroadcastFv(IntF orig, IntFv res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot broadcast an IntF to an IntFv of different size.");

        BoolF[] bitsOrig = orig.getBits();
        BoolFv[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}

class BroadcastFe extends Procedure {
    public BroadcastFe(IntF orig, IntFe res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot broadcast an IntF to an IntFe of different size.");

        BoolF[] bitsOrig = orig.getBits();
        BoolFe[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) broadcast(bitsOrig[i], bitsRes[i]);
    }
}
