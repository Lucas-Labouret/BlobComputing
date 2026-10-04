package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.BasicInstruction;

class BoolToIntV implements BasicInstruction {
    private final BoolV orig;
    private final IntV res;

    public BoolToIntV(BoolV orig, IntV res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.n; i++) res.getBits()[i].zeroes();
        res.getBits()[res.n].set(orig);
        return true;
    }
}

class BoolToIntVe implements BasicInstruction {
    private final BoolVe orig;
    private final IntVe res;

    public BoolToIntVe(BoolVe orig, IntVe res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.n; i++) res.getBits()[i].zeroes();
        res.getBits()[res.n].set(orig);
        return true;
    }
}

class BoolToIntVf implements BasicInstruction {
    private final BoolVf orig;
    private final IntVf res;

    public BoolToIntVf(BoolVf orig, IntVf res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.n; i++) res.getBits()[i].zeroes();
        res.getBits()[res.n].set(orig);
        return true;
    }
}

class BoolToIntE implements BasicInstruction {
    private final BoolE orig;
    private final IntE res;

    public BoolToIntE(BoolE orig, IntE res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.n; i++) res.getBits()[i].zeroes();
        res.getBits()[res.n].set(orig);
        return true;
    }
}

class BoolToIntEv implements BasicInstruction {
    private final BoolEv orig;
    private final IntEv res;

    public BoolToIntEv(BoolEv orig, IntEv res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.n; i++) res.getBits()[i].zeroes();
        res.getBits()[res.n].set(orig);
        return true;
    }
}

class BoolToIntEf implements BasicInstruction {
    private final BoolEf orig;
    private final IntEf res;

    public BoolToIntEf(BoolEf orig, IntEf res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.n; i++) res.getBits()[i].zeroes();
        res.getBits()[res.n].set(orig);
        return true;
    }
}

class BoolToIntF implements BasicInstruction {
    private final BoolF orig;
    private final IntF res;

    public BoolToIntF(BoolF orig, IntF res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.n; i++) res.getBits()[i].zeroes();
        res.getBits()[res.n].set(orig);
        return true;
    }
}

class BoolToIntFv implements BasicInstruction {
    private final BoolFv orig;
    private final IntFv res;

    public BoolToIntFv(BoolFv orig, IntFv res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.n; i++) res.getBits()[i].zeroes();
        res.getBits()[res.n].set(orig);
        return true;
    }
}

class BoolToIntFe implements BasicInstruction {
    private final BoolFe orig;
    private final IntFe res;

    public BoolToIntFe(BoolFe orig, IntFe res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i=0; i < res.n; i++) res.getBits()[i].zeroes();
        res.getBits()[res.n].set(orig);
        return true;
    }
}
