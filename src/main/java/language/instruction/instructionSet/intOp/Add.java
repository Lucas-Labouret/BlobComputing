package language.instruction.instructionSet.intOp;

import language.field.intField.*;
import language.instruction.Procedure;

class AddV extends Procedure {
    public AddV(IntV a, IntV b, IntV res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot add IntVs of different sizes.");

        IntV carry = tmp(new IntV(a.n));
        IntV tmp = tmp(new IntV(a.n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddVe extends Procedure {
    public AddVe(IntVe a, IntVe b, IntVe res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot add IntVes of different sizes.");

        IntVe carry = tmp(new IntVe(a.n));
        IntVe tmp = tmp(new IntVe(a.n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddVf extends Procedure {
    public AddVf(IntVf a, IntVf b, IntVf res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot add IntVfs of different sizes.");

        IntVf carry = tmp(new IntVf(a.n));
        IntVf tmp = tmp(new IntVf(a.n));

        set(a, res);
        set(b, tmp);
        for (int i = 0; i <= a.n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddE extends Procedure {
    public AddE(IntE a, IntE b, IntE res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot add IntEs of different sizes.");

        IntE carry = tmp(new IntE(a.n));
        IntE tmp = tmp(new IntE(a.n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddEv extends Procedure {
    public AddEv(IntEv a, IntEv b, IntEv res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot add IntEvs of different sizes.");

        IntEv carry = tmp(new IntEv(a.n));
        IntEv tmp = tmp(new IntEv(a.n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddEf extends Procedure {
    public AddEf(IntEf a, IntEf b, IntEf res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot add IntEfs of different sizes.");

        IntEf carry = tmp(new IntEf(a.n));
        IntEf tmp = tmp(new IntEf(a.n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddF extends Procedure {
    public AddF(IntF a, IntF b, IntF res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot add IntFs of different sizes.");

        IntF carry = tmp(new IntF(a.n));
        IntF tmp = tmp(new IntF(a.n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddFv extends Procedure {
    public AddFv(IntFv a, IntFv b, IntFv res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot add IntFvs of different sizes.");

        IntFv carry = tmp(new IntFv(a.n));
        IntFv tmp = tmp(new IntFv(a.n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}

class AddFe extends Procedure {
    public AddFe(IntFe a, IntFe b, IntFe res) {
        if (a.n != b.n || a.n != res.n)
            throw new IllegalArgumentException("Cannot add IntFes of different sizes.");

        IntFe carry = tmp(new IntFe(a.n));
        IntFe tmp = tmp(new IntFe(a.n));
        set(a, res);
        set(b, tmp);

        for (int i = 0; i <= a.n; i++){
            and(res, tmp, carry);
            xor(res, tmp, res);
            lShift(carry, tmp, 1);
        }
    }
}
