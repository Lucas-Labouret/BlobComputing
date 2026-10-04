package language.instruction.instructionSet.intOp;

import language.field.boolField.BoolE;
import language.field.boolField.BoolF;
import language.field.boolField.BoolV;
import language.field.intField.*;
import language.instruction.Procedure;
import language.utils.BoolFieldManager;

class RedMinVe extends Procedure {
    public RedMinVe(IntVe orig, IntV res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntV[] stack = new IntV[BoolFieldManager.getBreadthV()];
        for (int i=0; i<stack.length; i++) stack[i] = tmp(new IntV(orig.n));
        redStackMax(orig, stack);
        
        set(stack[0], res);
        BoolV greater = tmp(new BoolV());
        for (int i=1; i<stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxVe extends Procedure {
    public RedMaxVe(IntVe orig, IntV res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntV[] stack = new IntV[BoolFieldManager.getBreadthV()];
        for (int i=0; i<stack.length; i++) stack[i] = tmp(new IntV(orig.n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolV greater = tmp(new BoolV());
        for (int i=1; i<stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

class RedMinVf extends Procedure {
    public RedMinVf(IntVf orig, IntV res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntV[] stack = new IntV[BoolFieldManager.getBreadthV()];
        for (int i = 0; i < stack.length; i++) stack[i] = tmp(new IntV(orig.n));
        redStackMax(orig, stack);

        set(stack[0], res);
        BoolV greater = tmp(new BoolV());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxVf extends Procedure {
    public RedMaxVf(IntVf orig, IntV res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntV[] stack = new IntV[BoolFieldManager.getBreadthV()];
        for (int i = 0; i < stack.length; i++) stack[i] = tmp(new IntV(orig.n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolV greater = tmp(new BoolV());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

class RedMinEv extends Procedure {
    public RedMinEv(IntEv orig, IntE res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntE[] stack = new IntE[BoolFieldManager.getBreadthE()];
        for (int i = 0; i < stack.length; i++) stack[i] = tmp(new IntE(orig.n));
        redStackMax(orig, stack);

        set(stack[0], res);
        BoolE greater = tmp(new BoolE());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxEv extends Procedure {
    public RedMaxEv(IntEv orig, IntE res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntE[] stack = new IntE[BoolFieldManager.getBreadthE()];
        for (int i = 0; i < stack.length; i++) stack[i] = tmp(new IntE(orig.n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolE greater = tmp(new BoolE());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

class RedMinEf extends Procedure {
    public RedMinEf(IntEf orig, IntE res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntE[] stack = new IntE[BoolFieldManager.getBreadthE()];
        for (int i = 0; i < stack.length; i++) stack[i] = tmp(new IntE(orig.n));
        redStackMax(orig, stack);

        set(stack[0], res);
        BoolE greater = tmp(new BoolE());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxEf extends Procedure {
    public RedMaxEf(IntEf orig, IntE res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntE[] stack = new IntE[BoolFieldManager.getBreadthE()];
        for (int i = 0; i < stack.length; i++) stack[i] = tmp(new IntE(orig.n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolE greater = tmp(new BoolE());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

class RedMinFv extends Procedure {
    public RedMinFv(IntFv orig, IntF res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntF[] stack = new IntF[BoolFieldManager.getBreadthF()];
        for (int i = 0; i < stack.length; i++) stack[i] = tmp(new IntF(orig.n));
        redStackMax(orig, stack);

        set(stack[0], res);
        BoolF greater = tmp(new BoolF());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxFv extends Procedure {
    public RedMaxFv(IntFv orig, IntF res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntF[] stack = new IntF[BoolFieldManager.getBreadthF()];
        for (int i = 0; i < stack.length; i++) stack[i] = tmp(new IntF(orig.n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolF greater = tmp(new BoolF());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

class RedMinFe extends Procedure {
    public RedMinFe(IntFe orig, IntF res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntF[] stack = new IntF[BoolFieldManager.getBreadthF()];
        for (int i = 0; i < stack.length; i++) stack[i] = tmp(new IntF(orig.n));
        redStackMax(orig, stack);

        set(stack[0], res);
        BoolF greater = tmp(new BoolF());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, stack[i], res, res);
        }
    }
}

class RedMaxFe extends Procedure {
    public RedMaxFe(IntFe orig, IntF res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("orig and res must have the same number of bits");

        IntF[] stack = new IntF[BoolFieldManager.getBreadthF()];
        for (int i = 0; i < stack.length; i++) stack[i] = tmp(new IntF(orig.n));
        redStackMin(orig, stack);

        set(stack[0], res);
        BoolF greater = tmp(new BoolF());
        for (int i = 1; i < stack.length; i++) {
            gt(res, stack[i], greater);
            fif(greater, res, stack[i], res);
        }
    }
}

