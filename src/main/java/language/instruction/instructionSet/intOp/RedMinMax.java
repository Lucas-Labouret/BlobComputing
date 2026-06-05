package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.field.intField.IntE;
import language.field.intField.IntF;
import language.field.intField.IntV;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;
import language.utils.BoolFieldManager;

class RedMinVe extends Procedure {
    public RedMinVe(IntVeRef orig, IntVRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntVRef[] stack = new IntVRef[BoolFieldManager.getBreadthV()];
        for (int i=0; i<stack.length; i++) stack[i] = new IntVRef(new IntV(orig.get().n));
        redStackMax(orig, stack);
        
        set(stack[0], res);
        BoolVRef greater = tmp(new BoolVRef());
        for (int i=1; i<stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxVe extends Procedure {
    public RedMaxVe(IntVeRef orig, IntVRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntVRef[] stack = new IntVRef[BoolFieldManager.getBreadthV()];
        for (int i=0; i<stack.length; i++) stack[i] = new IntVRef(new IntV(orig.get().n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolVRef greater = tmp(new BoolVRef());
        for (int i=1; i<stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

class RedMinVf extends Procedure {
    public RedMinVf(IntVfRef orig, IntVRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntVRef[] stack = new IntVRef[BoolFieldManager.getBreadthV()];
        for (int i = 0; i < stack.length; i++) stack[i] = new IntVRef(new IntV(orig.get().n));
        redStackMax(orig, stack);

        set(stack[0], res);
        BoolVRef greater = tmp(new BoolVRef());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxVf extends Procedure {
    public RedMaxVf(IntVfRef orig, IntVRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntVRef[] stack = new IntVRef[BoolFieldManager.getBreadthV()];
        for (int i = 0; i < stack.length; i++) stack[i] = new IntVRef(new IntV(orig.get().n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolVRef greater = tmp(new BoolVRef());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

class RedMinEv extends Procedure {
    public RedMinEv(IntEvRef orig, IntERef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntERef[] stack = new IntERef[BoolFieldManager.getBreadthE()];
        for (int i = 0; i < stack.length; i++) stack[i] = new IntERef(new IntE(orig.get().n));
        redStackMax(orig, stack);

        set(stack[0], res);
        BoolERef greater = tmp(new BoolERef());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxEv extends Procedure {
    public RedMaxEv(IntEvRef orig, IntERef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntERef[] stack = new IntERef[BoolFieldManager.getBreadthE()];
        for (int i = 0; i < stack.length; i++) stack[i] = new IntERef(new IntE(orig.get().n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolERef greater = tmp(new BoolERef());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

class RedMinEf extends Procedure {
    public RedMinEf(IntEfRef orig, IntERef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntERef[] stack = new IntERef[BoolFieldManager.getBreadthE()];
        for (int i = 0; i < stack.length; i++) stack[i] = new IntERef(new IntE(orig.get().n));
        redStackMax(orig, stack);

        set(stack[0], res);
        BoolERef greater = tmp(new BoolERef());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxEf extends Procedure {
    public RedMaxEf(IntEfRef orig, IntERef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntERef[] stack = new IntERef[BoolFieldManager.getBreadthE()];
        for (int i = 0; i < stack.length; i++) stack[i] = new IntERef(new IntE(orig.get().n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolERef greater = tmp(new BoolERef());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

class RedMinFv extends Procedure {
    public RedMinFv(IntFvRef orig, IntFRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntFRef[] stack = new IntFRef[BoolFieldManager.getBreadthF()];
        for (int i = 0; i < stack.length; i++) stack[i] = new IntFRef(new IntF(orig.get().n));
        redStackMax(orig, stack);

        set(stack[0], res);
        BoolFRef greater = tmp(new BoolFRef());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxFv extends Procedure {
    public RedMaxFv(IntFvRef orig, IntFRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntFRef[] stack = new IntFRef[BoolFieldManager.getBreadthF()];
        for (int i = 0; i < stack.length; i++) stack[i] = new IntFRef(new IntF(orig.get().n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolFRef greater = tmp(new BoolFRef());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

class RedMinFe extends Procedure {
    public RedMinFe(IntFeRef orig, IntFRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntFRef[] stack = new IntFRef[BoolFieldManager.getBreadthF()];
        for (int i = 0; i < stack.length; i++) stack[i] = new IntFRef(new IntF(orig.get().n));
        redStackMax(orig, stack);

        set(stack[0], res);
        BoolFRef greater = tmp(new BoolFRef());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxFe extends Procedure {
    public RedMaxFe(IntFeRef orig, IntFRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntFRef[] stack = new IntFRef[BoolFieldManager.getBreadthF()];
        for (int i = 0; i < stack.length; i++) stack[i] = new IntFRef(new IntF(orig.get().n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolFRef greater = tmp(new BoolFRef());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

