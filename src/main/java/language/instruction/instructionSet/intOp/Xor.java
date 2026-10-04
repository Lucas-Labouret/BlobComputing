package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

class XorV extends Procedure {
    public XorV(IntV a, IntV b, IntV res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot XOR IntVs of different sizes.");

        BoolV[] bitsA = a.getBits();
        BoolV[] bitsB = b.getBits();
        BoolV[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorVe extends Procedure {
    public XorVe(IntVe a, IntVe b, IntVe res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot XOR IntVs of different sizes.");

        BoolVe[] bitsA = a.getBits();
        BoolVe[] bitsB = b.getBits();
        BoolVe[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorVf extends Procedure {
    public XorVf(IntVf a, IntVf b, IntVf res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot XOR IntVs of different sizes.");

        BoolVf[] bitsA = a.getBits();
        BoolVf[] bitsB = b.getBits();
        BoolVf[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorE extends Procedure {
    public XorE(IntE a, IntE b, IntE res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot XOR IntEs of different sizes.");

        BoolE[] bitsA = a.getBits();
        BoolE[] bitsB = b.getBits();
        BoolE[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorEv extends Procedure {
    public XorEv(IntEv a, IntEv b, IntEv res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot XOR IntEs of different sizes.");

        BoolEv[] bitsA = a.getBits();
        BoolEv[] bitsB = b.getBits();
        BoolEv[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorEf extends Procedure {
    public XorEf(IntEf a, IntEf b, IntEf res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot XOR IntEs of different sizes.");

        BoolEf[] bitsA = a.getBits();
        BoolEf[] bitsB = b.getBits();
        BoolEf[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorF extends Procedure {
    public XorF(IntF a, IntF b, IntF res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot XOR IntFs of different sizes.");

        BoolF[] bitsA = a.getBits();
        BoolF[] bitsB = b.getBits();
        BoolF[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorFv extends Procedure {
    public XorFv(IntFv a, IntFv b, IntFv res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot XOR IntVs of different sizes.");

        BoolFv[] bitsA = a.getBits();
        BoolFv[] bitsB = b.getBits();
        BoolFv[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}

class XorFe extends Procedure {
    public XorFe(IntFe a, IntFe b, IntFe res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot XOR IntEs of different sizes.");

        BoolFe[] bitsA = a.getBits();
        BoolFe[] bitsB = b.getBits();
        BoolFe[] bitsRes = res.getBits();
        for (int i = 0; i <= a.n; i++) xor(bitsA[i], bitsB[i], bitsRes[i]);
    }
}
