package language.instruction.instructionSet.intOp;

import language.field.intField.*;
import language.instruction.Procedure;

class NegV extends Procedure {
    public NegV(IntV orig, IntV res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntV to an IntV of different size.");

        not(orig, res);
        add(res, IntV.of(1, res.n), res);
    }
}

class NegVe extends Procedure {
    public NegVe(IntVe orig, IntVe res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntVe to an IntVe of different size.");

        not(orig, res);
        add(res, IntVe.of(1, res.n), res);
    }
}

class NegVf extends Procedure {
    public NegVf(IntVf orig, IntVf res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntVf to an IntVf of different size.");

        not(orig, res);
        add(res, IntVf.of(1, res.n), res);
    }
}

class NegE extends Procedure {
    public NegE(IntE orig, IntE res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntE to an IntE of different size.");

        not(orig, res);
        add(res, IntE.of(1, res.n), res);
    }
}

class NegEv extends Procedure {
    public NegEv(IntEv orig, IntEv res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntEv to an IntEv of different size.");

        not(orig, res);
        add(res, IntEv.of(1, res.n), res);
    }
}

class NegEf extends Procedure {
    public NegEf(IntEf orig, IntEf res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntEf to an IntEf of different size.");

        not(orig, res);
        add(res, IntEf.of(1, res.n), res);
    }
}

class NegF extends Procedure {
    public NegF(IntF orig, IntF res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntF to an IntF of different size.");

        not(orig, res);
        add(res, IntF.of(1, res.n), res);
    }
}

class NegFv extends Procedure {
    public NegFv(IntFv orig, IntFv res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntFv to an IntFv of different size.");

        not(orig, res);
        add(res, IntFv.of(1, res.n), res);
    }
}

class NegFe extends Procedure {
    public NegFe(IntFe orig, IntFe res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot negate an IntFe to an IntFe of different size.");

        not(orig, res);
        add(res, IntFe.of(1, res.n), res);
    }
}