package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.obj.field.intField.IntEv;
import language.obj.field.intField.IntFv;
import language.obj.field.intField.IntV;
import language.ref.field.intField.*;

class AddV extends Procedure {
    public AddV(IntVRef a, IntVRef b, IntVRef res) {
        if (a.get().n != b.get().n || a.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot add IntVs of different sizes.");

        IntVRef carry = IntVRef.of(new IntV(a.get().n));
        IntVRef tmp = IntVRef.of(new IntV(a.get().n));
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

        IntVeRef carry = new IntVeRef();
        IntVeRef tmp = new IntVeRef();
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

        IntVfRef carry = new IntVfRef();
        IntVfRef tmp = new IntVfRef();

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

        IntERef carry = new IntERef();
        IntERef tmp = new IntERef();
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

        IntEvRef carry = IntEvRef.of(new IntEv(a.get().n));
        IntEvRef tmp = IntEvRef.of(new IntEv(a.get().n));
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

        IntEfRef carry = new IntEfRef();
        IntEfRef tmp = new IntEfRef();
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

        IntFRef carry = new IntFRef();
        IntFRef tmp = new IntFRef();
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
        IntFvRef carry = IntFvRef.of(new IntFv(a.get().n));
        IntFvRef tmp = IntFvRef.of(new IntFv(a.get().n));
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

        IntFeRef carry = new IntFeRef();
        IntFeRef tmp = new IntFeRef();
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.get().n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}
