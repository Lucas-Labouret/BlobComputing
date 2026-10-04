package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;
import language.utils.BoolFieldManager;

class RedAddVe extends Procedure {
    public RedAddVe(BoolVe orig, IntV res) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolV[] stack = new BoolV[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolV());
        redStack0(orig, stack);

        set(IntV.of(0, res.n), res);
        IntV current = tmp(new IntV(res.n));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddVe(IntVe orig, IntV res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot reduce an IntVe to an IntV of different size.");

        int breadth = BoolFieldManager.getBreadthV();

        IntV[] stack = new IntV[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntV(res.n));
        redStack0(orig, stack);

        set(IntV.of(0, res.n), res);
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddVf extends Procedure {
    public RedAddVf(BoolVf orig, IntV res) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolV[] stack = new BoolV[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolV());
        redStack0(orig, stack);

        set(IntV.of(0, res.n), res);
        for (int i = 0; i < breadth; i++) {
            IntV current = tmp(new IntV(res.n));
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddVf(IntVf orig, IntV res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot reduce an IntVf to an IntV of different size.");

        int breadth = BoolFieldManager.getBreadthV();

        IntV[] stack = new IntV[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntV(res.n));
        redStack0(orig, stack);

        set(IntV.of(0, res.n), res);
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddEv extends Procedure {
    public RedAddEv(BoolEv orig, IntE res) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolE[] stack = new BoolE[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolE());
        redStack0(orig, stack);

        set(IntE.of(0, res.n), res);
        IntE current = tmp(new IntE(res.n));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddEv(IntEv orig, IntE res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot reduce an IntEv to an IntE of different size.");

        int breadth = BoolFieldManager.getBreadthE();

        IntE[] stack = new IntE[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntE(res.n));
        redStack0(orig, stack);

        set(IntE.of(0, res.n), res);
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddEf extends Procedure {
    public RedAddEf(BoolEf orig, IntE res) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolE[] stack = new BoolE[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolE());
        redStack0(orig, stack);

        set(IntE.of(0, res.n), res);
        IntE current = tmp(new IntE(res.n));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddEf(IntEf orig, IntE res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot reduce an IntEf to an IntE of different size.");

        int breadth = BoolFieldManager.getBreadthE();

        IntE[] stack = new IntE[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntE(res.n));
        redStack0(orig, stack);

        set(IntE.of(0, res.n), res);
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddFv extends Procedure {
    public RedAddFv(BoolFv orig, IntF res) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolF[] stack = new BoolF[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolF());
        redStack0(orig, stack);

        set(IntF.of(0, res.n), res);
        IntF current = tmp(new IntF(res.n));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddFv(IntFv orig, IntF res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot reduce an IntFv to an IntF of different size.");

        int breadth = BoolFieldManager.getBreadthF();

        IntF[] stack = new IntF[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntF(res.n));
        redStack0(orig, stack);

        set(IntF.of(0, res.n), res);
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddFe extends Procedure {
    public RedAddFe(BoolFe orig, IntF res) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolF[] stack = new BoolF[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolF());
        redStack0(orig, stack);

        set(IntF.of(0, res.n), res);
        IntF current = tmp(new IntF(res.n));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddFe(IntFe orig, IntF res) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot reduce an IntFe to an IntF of different size.");

        int breadth = BoolFieldManager.getBreadthF();

        IntF[] stack = new IntF[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntF(res.n));
        redStack0(orig, stack);

        set(IntF.of(0, res.n), res);
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}
