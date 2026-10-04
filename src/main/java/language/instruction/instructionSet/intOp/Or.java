package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

class OrV extends Procedure {
    public OrV(IntV a, IntV b, IntV res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot OR IntVs of different sizes.");

        BoolV[] bitsA = a.getBits();
        BoolV[] bitsB = b.getBits();
        BoolV[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrVe extends Procedure {
    public OrVe(IntVe a, IntVe b, IntVe res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot OR IntVs of different sizes.");

        BoolVe[] bitsA = a.getBits();
        BoolVe[] bitsB = b.getBits();
        BoolVe[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrVf extends Procedure {
    public OrVf(IntVf a, IntVf b, IntVf res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot OR IntVs of different sizes.");

        BoolVf[] bitsA = a.getBits();
        BoolVf[] bitsB = b.getBits();
        BoolVf[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrE extends Procedure {
    public OrE(IntE a, IntE b, IntE res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot OR IntEs of different sizes.");

        BoolE[] bitsA = a.getBits();
        BoolE[] bitsB = b.getBits();
        BoolE[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrEv extends Procedure {
    public OrEv(IntEv a, IntEv b, IntEv res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot OR IntEs of different sizes.");

        BoolEv[] bitsA = a.getBits();
        BoolEv[] bitsB = b.getBits();
        BoolEv[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrEf extends Procedure {
    public OrEf(IntEf a, IntEf b, IntEf res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot OR IntEs of different sizes.");

        BoolEf[] bitsA = a.getBits();
        BoolEf[] bitsB = b.getBits();
        BoolEf[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrF extends Procedure {
    public OrF(IntF a, IntF b, IntF res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot OR IntFs of different sizes.");

        BoolF[] bitsA = a.getBits();
        BoolF[] bitsB = b.getBits();
        BoolF[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrFv extends Procedure {
    public OrFv(IntFv a, IntFv b, IntFv res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot OR IntVs of different sizes.");

        BoolFv[] bitsA = a.getBits();
        BoolFv[] bitsB = b.getBits();
        BoolFv[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class OrFe extends Procedure {
    public OrFe(IntFe a, IntFe b, IntFe res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot OR IntEs of different sizes.");

        BoolFe[] bitsA = a.getBits();
        BoolFe[] bitsB = b.getBits();
        BoolFe[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) or(bitsA[i], bitsB[i], bitsRes[i]);
    }
}
