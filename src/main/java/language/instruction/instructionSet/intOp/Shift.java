package language.instruction.instructionSet.intOp;

import language.instruction.instructionSet.BasicInstruction;
import language.obj.field.intField.*;
import language.ref.field.intField.*;

class LShiftV implements BasicInstruction {
    private final IntVRef orig;
    private final IntVRef res;
    private final int k;

    public LShiftV(IntVRef orig, IntVRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntV to an IntV of different size.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntV.lShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class RShiftV implements BasicInstruction {
    private final IntVRef orig;
    private final IntVRef res;
    private final int k;

    public RShiftV(IntVRef orig, IntVRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntV to an IntV of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntV.rShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class LShiftVe implements BasicInstruction {
    private final IntVeRef orig;
    private final IntVeRef res;
    private final int k;

    public LShiftVe(IntVeRef orig, IntVeRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntVe to an IntVe of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntVe.lShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class RShiftVe implements BasicInstruction {
    private final IntVeRef orig;
    private final IntVeRef res;
    private final int k;

    public RShiftVe(IntVeRef orig, IntVeRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntVe to an IntVe of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntVe.rShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class LShiftVf implements BasicInstruction {
    private final IntVfRef orig;
    private final IntVfRef res;
    private final int k;

    public LShiftVf(IntVfRef orig, IntVfRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntVf to an IntVf of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntVf.lShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class RShiftVf implements BasicInstruction {
    private final IntVfRef orig;
    private final IntVfRef res;
    private final int k;

    public RShiftVf(IntVfRef orig, IntVfRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntVf to an IntVf of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntVf.rShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class LShiftE implements BasicInstruction {
    private final IntERef orig;
    private final IntERef res;
    private final int k;

    public LShiftE(IntERef orig, IntERef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntE to an IntE of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntE.lShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class RShiftE implements BasicInstruction {
    private final IntERef orig;
    private final IntERef res;
    private final int k;

    public RShiftE(IntERef orig, IntERef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntE to an IntE of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntE.rShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class LShiftEv implements BasicInstruction {
    private final IntEvRef orig;
    private final IntEvRef res;
    private final int k;

    public LShiftEv(IntEvRef orig, IntEvRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntEv to an IntEv of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntEv.lShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class RShiftEv implements BasicInstruction {
    private final IntEvRef orig;
    private final IntEvRef res;
    private final int k;

    public RShiftEv(IntEvRef orig, IntEvRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntEv to an IntEv of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntEv.rShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class LShiftEf implements BasicInstruction {
    private final IntEfRef orig;
    private final IntEfRef res;
    private final int k;

    public LShiftEf(IntEfRef orig, IntEfRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntEf to an IntEf of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntEf.lShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class RShiftEf implements BasicInstruction {
    private final IntEfRef orig;
    private final IntEfRef res;
    private final int k;

    public RShiftEf(IntEfRef orig, IntEfRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntEf to an IntEf of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntEf.rShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class LShiftF implements BasicInstruction {
    private final IntFRef orig;
    private final IntFRef res;
    private final int k;

    public LShiftF(IntFRef orig, IntFRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntF to an IntF of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntF.lShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class RShiftF implements BasicInstruction {
    private final IntFRef orig;
    private final IntFRef res;
    private final int k;

    public RShiftF(IntFRef orig, IntFRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntF to an IntF of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntF.rShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class LShiftFv implements BasicInstruction {
    private final IntFvRef orig;
    private final IntFvRef res;
    private final int k;

    public LShiftFv(IntFvRef orig, IntFvRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntFv to an IntFv of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntFv.lShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class RShiftFv implements BasicInstruction {
    private final IntFvRef orig;
    private final IntFvRef res;
    private final int k;

    public RShiftFv(IntFvRef orig, IntFvRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntFv to an IntFv of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntFv.rShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class LShiftFe implements BasicInstruction {
    private final IntFeRef orig;
    private final IntFeRef res;
    private final int k;

    public LShiftFe(IntFeRef orig, IntFeRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntFe to an IntFe of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntFe.lShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}

class RShiftFe implements BasicInstruction {
    private final IntFeRef orig;
    private final IntFeRef res;
    private final int k;

    public RShiftFe(IntFeRef orig, IntFeRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntFe to an IntFe of different size.");
        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        IntFe.rShift(orig.get(), res.get(), k, orig.get().n);
        return true;
    }
}