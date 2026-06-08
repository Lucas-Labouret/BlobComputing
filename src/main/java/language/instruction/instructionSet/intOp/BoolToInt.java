package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class BoolToIntV implements BasicInstruction {
    private final BoolVRef orig;
    private final IntVRef res;

    public BoolToIntV(BoolVRef orig, IntVRef res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.get().n; i++) res.get().getBits()[i].set(BoolV.zeroes());
        res.get().getBits()[res.get().n].set(orig.get());
        return true;
    }
}

class BoolToIntVe implements BasicInstruction {
    private final BoolVeRef orig;
    private final IntVeRef res;

    public BoolToIntVe(BoolVeRef orig, IntVeRef res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.get().n; i++) res.get().getBits()[i].set(BoolVe.zeroes());
        res.get().getBits()[res.get().n].set(orig.get());
        return true;
    }
}

class BoolToIntVf implements BasicInstruction {
    private final BoolVfRef orig;
    private final IntVfRef res;

    public BoolToIntVf(BoolVfRef orig, IntVfRef res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.get().n; i++) res.get().getBits()[i].set(BoolVf.zeroes());
        res.get().getBits()[res.get().n].set(orig.get());
        return true;
    }
}

class BoolToIntE implements BasicInstruction {
    private final BoolERef orig;
    private final IntERef res;

    public BoolToIntE(BoolERef orig, IntERef res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.get().n; i++) res.get().getBits()[i].set(BoolE.zeroes());
        res.get().getBits()[res.get().n].set(orig.get());
        return true;
    }
}

class BoolToIntEv implements BasicInstruction {
    private final BoolEvRef orig;
    private final IntEvRef res;

    public BoolToIntEv(BoolEvRef orig, IntEvRef res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.get().n; i++) res.get().getBits()[i].set(BoolEv.zeroes());
        res.get().getBits()[res.get().n].set(orig.get());
        return true;
    }
}

class BoolToIntEf implements BasicInstruction {
    private final BoolEfRef orig;
    private final IntEfRef res;

    public BoolToIntEf(BoolEfRef orig, IntEfRef res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.get().n; i++) res.get().getBits()[i].set(BoolEf.zeroes());
        res.get().getBits()[res.get().n].set(orig.get());
        return true;
    }
}

class BoolToIntF implements BasicInstruction {
    private final BoolFRef orig;
    private final IntFRef res;

    public BoolToIntF(BoolFRef orig, IntFRef res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.get().n; i++) res.get().getBits()[i].set(BoolF.zeroes());
        res.get().getBits()[res.get().n].set(orig.get());
        return true;
    }
}

class BoolToIntFv implements BasicInstruction {
    private final BoolFvRef orig;
    private final IntFvRef res;

    public BoolToIntFv(BoolFvRef orig, IntFvRef res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.get().n; i++) res.get().getBits()[i].set(BoolFv.zeroes());
        res.get().getBits()[res.get().n].set(orig.get());
        return true;
    }
}

class BoolToIntFe implements BasicInstruction {
    private final BoolFeRef orig;
    private final IntFeRef res;

    public BoolToIntFe(BoolFeRef orig, IntFeRef res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.get().n; i++) res.get().getBits()[i].set(BoolFe.zeroes());
        res.get().getBits()[res.get().n].set(orig.get());
        return true;
    }
}
