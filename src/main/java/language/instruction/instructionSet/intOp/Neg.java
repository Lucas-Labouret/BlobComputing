package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.obj.field.intField.*;
import language.ref.field.intField.*;

class NegV extends Procedure {
    public NegV(IntVRef orig, IntVRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntV to an IntV of different size.");

        not(orig, res);
        add(res, IntVRef.of(IntV.of(1, res.get().n)), res);
    }
}

class NegVe extends Procedure {
    public NegVe(IntVeRef orig, IntVeRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntVe to an IntVe of different size.");

        not(orig, res);
        add(res, IntVeRef.of(IntVe.of(1, res.get().n)), res);
    }
}

class NegVf extends Procedure {
    public NegVf(IntVfRef orig, IntVfRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntVf to an IntVf of different size.");

        not(orig, res);
        add(res, IntVfRef.of(IntVf.of(1, res.get().n)), res);
    }
}

class NegE extends Procedure {
    public NegE(IntERef orig, IntERef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntE to an IntE of different size.");

        not(orig, res);
        add(res, IntERef.of(IntE.of(1, res.get().n)), res);
    }
}

class NegEv extends Procedure {
    public NegEv(IntEvRef orig, IntEvRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntEv to an IntEv of different size.");

        not(orig, res);
        add(res, IntEvRef.of(IntEv.of(1, res.get().n)), res);
    }
}

class NegEf extends Procedure {
    public NegEf(IntEfRef orig, IntEfRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntEf to an IntEf of different size.");

        not(orig, res);
        add(res, IntEfRef.of(IntEf.of(1, res.get().n)), res);
    }
}

class NegF extends Procedure {
    public NegF(IntFRef orig, IntFRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntF to an IntF of different size.");

        not(orig, res);
        add(res, IntFRef.of(IntF.of(1, res.get().n)), res);
    }
}

class NegFv extends Procedure {
    public NegFv(IntFvRef orig, IntFvRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntFv to an IntFv of different size.");

        not(orig, res);
        add(res, IntFvRef.of(IntFv.of(1, res.get().n)), res);
    }
}

class NegFe extends Procedure {
    public NegFe(IntFeRef orig, IntFeRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot negate an IntFe to an IntFe of different size.");

        not(orig, res);
        add(res, IntFeRef.of(IntFe.of(1, res.get().n)), res);
    }
}