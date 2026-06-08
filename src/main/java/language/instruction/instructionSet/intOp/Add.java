package language.instruction.instructionSet.intOp;

import language.field.intField.*;
import language.instruction.Procedure;
import language.fieldRef.intField.*;

class AddV extends Procedure {
    public AddV(IntVRef a, IntVRef b, IntVRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot add IntVs of different sizes.");

        IntVRef carry = new IntVRef(new IntV(a.get().n));
        IntVRef tmp = new IntVRef(new IntV(a.get().n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddVe extends Procedure {
    public AddVe(IntVeRef a, IntVeRef b, IntVeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot add IntVs of different sizes.");

        IntVeRef carry = new IntVeRef(new IntVe(a.get().n));
        IntVeRef tmp = new IntVeRef(new IntVe(a.get().n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddVf extends Procedure {
    public AddVf(IntVfRef a, IntVfRef b, IntVfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot add IntVs of different sizes.");

        IntVfRef carry = new IntVfRef(new IntVf(a.get().n));
        IntVfRef tmp = new IntVfRef(new IntVf(a.get().n));

        set(a, res);
        set(b, tmp);
        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddE extends Procedure {
    public AddE(IntERef a, IntERef b, IntERef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot add IntEs of different sizes.");

        IntERef carry = new IntERef(new IntE(a.get().n));
        IntERef tmp = new IntERef(new IntE(a.get().n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddEv extends Procedure {
    public AddEv(IntEvRef a, IntEvRef b, IntEvRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot add IntEs of different sizes.");

        IntEvRef carry = new IntEvRef(new IntEv(a.get().n));
        IntEvRef tmp = new IntEvRef(new IntEv(a.get().n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddEf extends Procedure {
    public AddEf(IntEfRef a, IntEfRef b, IntEfRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot add IntEs of different sizes.");

        IntEfRef carry = new IntEfRef(new IntEf(a.get().n));
        IntEfRef tmp = new IntEfRef(new IntEf(a.get().n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddF extends Procedure {
    public AddF(IntFRef a, IntFRef b, IntFRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot add IntFs of different sizes.");

        IntFRef carry = new IntFRef(new IntF(a.get().n));
        IntFRef tmp = new IntFRef(new IntF(a.get().n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddFv extends Procedure {
    public AddFv(IntFvRef a, IntFvRef b, IntFvRef res) {
        IntFvRef carry = new IntFvRef(new IntFv(a.get().n));
        IntFvRef tmp = new IntFvRef(new IntFv(a.get().n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddFe extends Procedure {
    public AddFe(IntFeRef a, IntFeRef b, IntFeRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot add IntEs of different sizes.");

        IntFeRef carry = new IntFeRef(new IntFe(a.get().n));
        IntFeRef tmp = new IntFeRef(new IntFe(a.get().n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}
