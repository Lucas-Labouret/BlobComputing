package language.instruction.instructionSet.intOp;

import language.instruction.instructionSet.BasicInstruction;
import language.obj.field.intField.*;
import language.ref.field.boolField.*;
import language.ref.field.intField.*;

class BoolToIntV implements BasicInstruction {
    private final BoolVRef orig;
    private final IntVRef res;

    public BoolToIntV(BoolVRef orig, IntVRef res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntV.of(orig, res.get().n));
        return true;
    }
}

class BoolToIntVe implements BasicInstruction {
    private final BoolVeRef boolVe;
    private final IntVeRef res;

    public BoolToIntVe(BoolVeRef boolVe, IntVeRef res) {
        this.boolVe = boolVe;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntVe.of(boolVe, res.get().n));
        return true;
    }
}

class BoolToIntVf implements BasicInstruction {
    private final BoolVfRef boolVf;
    private final IntVfRef res;

    public BoolToIntVf(BoolVfRef boolVf, IntVfRef res) {
        this.boolVf = boolVf;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntVf.of(boolVf, res.get().n));
        return true;
    }
}

class BoolToIntE implements BasicInstruction {
    private final BoolERef boolE;
    private final IntERef res;

    public BoolToIntE(BoolERef boolE, IntERef res) {
        this.boolE = boolE;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntE.of(boolE, res.get().n));
        return true;
    }
}

class BoolToIntEv implements BasicInstruction {
    private final BoolEvRef boolEv;
    private final IntEvRef res;

    public BoolToIntEv(BoolEvRef boolEv, IntEvRef res) {
        this.boolEv = boolEv;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntEv.of(boolEv, res.get().n));
        return true;
    }
}

class BoolToIntEf implements BasicInstruction {
    private final BoolEfRef boolEf;
    private final IntEfRef res;

    public BoolToIntEf(BoolEfRef boolEf, IntEfRef res) {
        this.boolEf = boolEf;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntEf.of(boolEf, res.get().n));
        return true;
    }
}

class BoolToIntF implements BasicInstruction {
    private final BoolFRef boolF;
    private final IntFRef res;

    public BoolToIntF(BoolFRef boolF, IntFRef res) {
        this.boolF = boolF;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntF.of(boolF, res.get().n));
        return true;
    }
}

class BoolToIntFv implements BasicInstruction {
    private final BoolFvRef boolFv;
    private final IntFvRef res;

    public BoolToIntFv(BoolFvRef boolFv, IntFvRef res) {
        this.boolFv = boolFv;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntFv.of(boolFv, res.get().n));
        return true;
    }
}

class BoolToIntFe implements BasicInstruction {
    private final BoolFeRef boolFe;
    private final IntFeRef res;

    public BoolToIntFe(BoolFeRef boolFe, IntFeRef res) {
        this.boolFe = boolFe;
        this.res = res;
    }

    @Override
    public boolean exec() {
        res.set(IntFe.of(boolFe, res.get().n));
        return true;
    }
}
