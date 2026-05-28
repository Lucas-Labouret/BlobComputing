package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.obj.field.intField.IntE;
import language.obj.field.intField.IntF;
import language.obj.field.intField.IntV;
import language.ref.field.boolField.*;
import language.ref.field.intField.*;
import language.utils.BoolFieldManager;

class RedAddVe extends Procedure {
    public RedAddVe(BoolVeRef orig, IntVRef res) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVRef[] stack = new BoolVRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolVRef();
        redStack0(orig, stack);

        set(IntVRef.of(IntV.of(0, res.get().n)), res);
        IntVRef current = IntVRef.of(new IntV(res.get().n));
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
        for (int i = 0; i < breadth; i++) stack[i] = IntVRef.of(new IntV(res.get().n));
        redStack0(orig, stack);

        res.set(IntV.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddVf extends Procedure {
    public RedAddVf(BoolVfRef orig, IntVRef res) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVRef[] stack = new BoolVRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolVRef();
        redStack0(orig, stack);

        set(IntVRef.of(IntV.of(0, res.get().n)), res);
        for (int i = 0; i < breadth; i++) {
            IntVRef current = IntVRef.of(new IntV(res.get().n));
            fromBool(stack[i], current);
            add(res, current, res);
        }
    }

    public RedAddVf(IntVfRef orig, IntVRef res) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot reduce an IntVf to an IntV of different size.");

        int breadth = BoolFieldManager.getBreadthV();

        IntVRef[] stack = new IntVRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = IntVRef.of(new IntV(res.get().n));
        redStack0(orig, stack);

        res.set(IntV.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddEv extends Procedure {
    public RedAddEv(BoolEvRef orig, IntERef res) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolERef[] stack = new BoolERef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolERef();
        redStack0(orig, stack);

        set(IntERef.of(IntE.of(0, res.get().n)), res);
        IntERef current = IntERef.of(new IntE(res.get().n));
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
        for (int i = 0; i < breadth; i++) stack[i] = IntERef.of(new IntE(res.get().n));
        redStack0(orig, stack);

        res.set(IntE.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddEf extends Procedure {
    public RedAddEf(BoolEfRef orig, IntERef res) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolERef[] stack = new BoolERef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolERef();
        redStack0(orig, stack);

        set(IntERef.of(IntE.of(0, res.get().n)), res);
        IntERef current = IntERef.of(new IntE(res.get().n));
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
        for (int i = 0; i < breadth; i++) stack[i] = IntERef.of(new IntE(res.get().n));
        redStack0(orig, stack);

        res.set(IntE.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddFv extends Procedure {
    public RedAddFv(BoolFvRef orig, IntFRef res) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFRef[] stack = new BoolFRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolFRef();
        redStack0(orig, stack);

        set(IntFRef.of(IntF.of(0, res.get().n)), res);
        IntFRef current = IntFRef.of(new IntF(res.get().n));
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
        for (int i = 0; i < breadth; i++) stack[i] = IntFRef.of(new IntF(res.get().n));
        redStack0(orig, stack);

        res.set(IntF.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}

class RedAddFe extends Procedure {
    public RedAddFe(BoolFeRef orig, IntFRef res) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFRef[] stack = new BoolFRef[breadth];
        for (int i = 0; i < breadth; i++) stack[i] = new BoolFRef();
        redStack0(orig, stack);

        set(IntFRef.of(IntF.of(0, res.get().n)), res);
        IntFRef current = IntFRef.of(new IntF(res.get().n));
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
        for (int i = 0; i < breadth; i++) stack[i] = IntFRef.of(new IntF(res.get().n));
        redStack0(orig, stack);

        res.set(IntF.of(0, res.get().n));
        for (int i = 0; i < breadth; i++) add(res, stack[i], res);
    }
}
