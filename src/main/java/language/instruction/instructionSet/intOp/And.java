package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

class AndV extends Procedure {
    public AndV(IntV a, IntV b, IntV res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolV[] bitsA = a.getBits();
        BoolV[] bitsB = b.getBits();
        BoolV[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndVe extends Procedure {
    public AndVe(IntVe a, IntVe b, IntVe res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolVe[] bitsA = a.getBits();
        BoolVe[] bitsB = b.getBits();
        BoolVe[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndVf extends Procedure {
    public AndVf(IntVf a, IntVf b, IntVf res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolVf[] bitsA = a.getBits();
        BoolVf[] bitsB = b.getBits();
        BoolVf[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndE extends Procedure {
    public AndE(IntE a, IntE b, IntE res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolE[] bitsA = a.getBits();
        BoolE[] bitsB = b.getBits();
        BoolE[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndEv extends Procedure {
    public AndEv(IntEv a, IntEv b, IntEv res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolEv[] bitsA = a.getBits();
        BoolEv[] bitsB = b.getBits();
        BoolEv[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndEf extends Procedure {
    public AndEf(IntEf a, IntEf b, IntEf res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolEf[] bitsA = a.getBits();
        BoolEf[] bitsB = b.getBits();
        BoolEf[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndF extends Procedure {
    public AndF(IntF a, IntF b, IntF res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot AND IntFs of different sizes.");

        BoolF[] bitsA = a.getBits();
        BoolF[] bitsB = b.getBits();
        BoolF[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndFv extends Procedure {
    public AndFv(IntFv a, IntFv b, IntFv res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot AND IntVs of different sizes.");

        BoolFv[] bitsA = a.getBits();
        BoolFv[] bitsB = b.getBits();
        BoolFv[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class AndFe extends Procedure {
    public AndFe(IntFe a, IntFe b, IntFe res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot AND IntEs of different sizes.");

        BoolFe[] bitsA = a.getBits();
        BoolFe[] bitsB = b.getBits();
        BoolFe[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) and(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

