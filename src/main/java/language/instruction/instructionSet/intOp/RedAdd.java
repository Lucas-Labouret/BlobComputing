package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.field.intField.IntE;
import language.field.intField.IntF;
import language.field.intField.IntV;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;
import language.utils.BoolFieldManager;

class RedAddVe extends Procedure {
    public RedAddVe(BoolVeRef orig, IntVRef res) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVRef[] stack = new BoolVRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolVRef());
        redStack0(orig, stack);

        set(new IntVRef(IntV.of(0, res.get().n)), res);
        IntVRef current = tmp(new IntVRef(new IntV(res.get().n)));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddVe(IntVeRef orig, IntVRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot reduce an IntVe to an IntV of different size.");

        int breadth = BoolFieldManager.getBreadthV();

        IntVRef[] stack = new IntVRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntVRef(new IntV(res.get().n)));
        redStack0(orig, stack);

        res.set(IntV.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddVf extends Procedure {
    public RedAddVf(BoolVfRef orig, IntVRef res) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVRef[] stack = new BoolVRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolVRef());
        redStack0(orig, stack);

        set(new IntVRef(IntV.of(0, res.get().n)), res);
        for (int i = 0; i < breadth; i++) {
            IntVRef current = tmp(new IntVRef(new IntV(res.get().n)));
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddVf(IntVfRef orig, IntVRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot reduce an IntVf to an IntV of different size.");

        int breadth = BoolFieldManager.getBreadthV();

        IntVRef[] stack = new IntVRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntVRef(new IntV(res.get().n)));
        redStack0(orig, stack);

        res.set(IntV.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddEv extends Procedure {
    public RedAddEv(BoolEvRef orig, IntERef res) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolERef[] stack = new BoolERef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolERef());
        redStack0(orig, stack);

        set(new IntERef(IntE.of(0, res.get().n)), res);
        IntERef current = tmp(new IntERef(new IntE(res.get().n)));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddEv(IntEvRef orig, IntERef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot reduce an IntEv to an IntE of different size.");

        int breadth = BoolFieldManager.getBreadthE();

        IntERef[] stack = new IntERef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntERef(new IntE(res.get().n)));
        redStack0(orig, stack);

        res.set(IntE.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddEf extends Procedure {
    public RedAddEf(BoolEfRef orig, IntERef res) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolERef[] stack = new BoolERef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolERef());
        redStack0(orig, stack);

        set(new IntERef(IntE.of(0, res.get().n)), res);
        IntERef current = tmp(new IntERef(new IntE(res.get().n)));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddEf(IntEfRef orig, IntERef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot reduce an IntEf to an IntE of different size.");

        int breadth = BoolFieldManager.getBreadthE();

        IntERef[] stack = new IntERef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntERef(new IntE(res.get().n)));
        redStack0(orig, stack);

        res.set(IntE.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddFv extends Procedure {
    public RedAddFv(BoolFvRef orig, IntFRef res) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFRef[] stack = new BoolFRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolFRef());
        redStack0(orig, stack);

        set(new IntFRef(IntF.of(0, res.get().n)), res);
        IntFRef current = tmp(new IntFRef(new IntF(res.get().n)));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddFv(IntFvRef orig, IntFRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot reduce an IntFv to an IntF of different size.");

        int breadth = BoolFieldManager.getBreadthF();

        IntFRef[] stack = new IntFRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntFRef(new IntF(res.get().n)));
        redStack0(orig, stack);

        res.set(IntF.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddFe extends Procedure {
    public RedAddFe(BoolFeRef orig, IntFRef res) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFRef[] stack = new BoolFRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new BoolFRef());
        redStack0(orig, stack);

        set(new IntFRef(IntF.of(0, res.get().n)), res);
        IntFRef current = tmp(new IntFRef(new IntF(res.get().n)));
        for (int i = 0; i < breadth; i++) {
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddFe(IntFeRef orig, IntFRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot reduce an IntFe to an IntF of different size.");

        int breadth = BoolFieldManager.getBreadthF();

        IntFRef[] stack = new IntFRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = tmp(new IntFRef(new IntF(res.get().n)));
        redStack0(orig, stack);

        res.set(IntF.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}
