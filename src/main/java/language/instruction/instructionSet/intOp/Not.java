package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

class NotV extends Procedure {
    public NotV(IntV orig, IntV res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntV to an IntV of different size.");

        BoolV[] bitsOrig = orig.getBits();
        BoolV[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotVe extends Procedure {
    public NotVe(IntVe orig, IntVe res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntVe to an IntVe of different size.");

        BoolVe[] bitsOrig = orig.getBits();
        BoolVe[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotVf extends Procedure {
    public NotVf(IntVf orig, IntVf res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntVf to an IntVf of different size.");

        BoolVf[] bitsOrig = orig.getBits();
        BoolVf[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotE extends Procedure {
    public NotE(IntE orig, IntE res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntE to an IntE of different size.");

        BoolE[] bitsOrig = orig.getBits();
        BoolE[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotEv extends Procedure {
    public NotEv(IntEv orig, IntEv res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntEv to an IntEv of different size.");

        BoolEv[] bitsOrig = orig.getBits();
        BoolEv[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotEf extends Procedure {
    public NotEf(IntEf orig, IntEf res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntEf to an IntEf of different size.");

        BoolEf[] bitsOrig = orig.getBits();
        BoolEf[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotF extends Procedure {
    public NotF(IntF orig, IntF res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntF to an IntF of different size.");

        BoolF[] bitsOrig = orig.getBits();
        BoolF[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotFv extends Procedure {
    public NotFv(IntFv orig, IntFv res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntFv to an IntFv of different size.");

        BoolFv[] bitsOrig = orig.getBits();
        BoolFv[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}

class NotFe extends Procedure {
    public NotFe(IntFe orig, IntFe res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntFe to an IntFe of different size.");

        BoolFe[] bitsOrig = orig.getBits();
        BoolFe[] bitsRes = res.getBits();
        for (int i = 0; i <= orig.n; i++) not(bitsOrig[i], bitsRes[i]);
    }
}
