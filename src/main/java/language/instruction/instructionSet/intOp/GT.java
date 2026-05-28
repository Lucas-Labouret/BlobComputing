package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.obj.field.intField.IntV;
import language.ref.field.boolField.*;
import language.ref.field.intField.*;

class GTV extends Procedure {
    public GTV(IntVRef a, IntVRef b, BoolVRef res) {
        if  (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        IntVRef diff = IntVRef.of(new IntV(a.get().n));
        sub(a, b, diff);

        BoolVRef[] bits = new BoolVRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolVRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTVe extends Procedure {
    public GTVe(IntVeRef a, IntVeRef b, BoolVeRef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        IntVeRef diff = new IntVeRef();
        sub(a, b, diff);

        BoolVeRef[] bits = new BoolVeRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolVeRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTVf extends Procedure {
    public GTVf(IntVfRef a, IntVfRef b, BoolVfRef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        IntVfRef diff = new IntVfRef();
        sub(a, b, diff);

        BoolVfRef[] bits = new BoolVfRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolVfRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTE extends Procedure {
    public GTE(IntERef a, IntERef b, BoolERef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntEs of different sizes.");

        IntERef diff = new IntERef();
        sub(a, b, diff);

        BoolERef[] bits = new BoolERef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolERef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTEv extends Procedure {
    public GTEv(IntEvRef a, IntEvRef b, BoolEvRef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntEs of different sizes.");

        IntEvRef diff = new IntEvRef();
        sub(a, b, diff);

        BoolEvRef[] bits = new BoolEvRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolEvRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTEf extends Procedure {
    public GTEf(IntEfRef a, IntEfRef b, BoolEfRef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntEs of different sizes.");

        IntEfRef diff = new IntEfRef();
        sub(a, b, diff);

        BoolEfRef[] bits = new BoolEfRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolEfRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTF extends Procedure {
    public GTF(IntFRef a, IntFRef b, BoolFRef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntFs of different sizes.");

        IntFRef diff = new IntFRef();
        sub(a, b, diff);

        BoolFRef[] bits = new BoolFRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolFRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTFv extends Procedure {
    public GTFv(IntFvRef a, IntFvRef b, BoolFvRef res) {
        IntFvRef diff = new IntFvRef();
        sub(a, b, diff);

        BoolFvRef[] bits = new BoolFvRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolFvRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}

class GTFe extends Procedure {
    public GTFe(IntFeRef a, IntFeRef b, BoolFeRef res) {
        if (a.get().n != b.get().n)
            throw new IllegalArgumentException("Cannot compare IntEs of different sizes.");

        IntFeRef diff = new IntFeRef();
        sub(a, b, diff);

        BoolFeRef[] bits = new BoolFeRef[diff.get().n + 1];
        for (int i = 0; i <= diff.get().n; i++) bits[i] = new BoolFeRef();
        split(diff, bits);

        set(bits[0], res);
        not(res, res);
    }
}