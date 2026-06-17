package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;
import language.field.intField.*;
import language.fieldRef.intField.*;

class LShiftV implements BasicInstruction {
    private final IntVRef orig;
    private final IntVRef res;
    private final int k;

    public LShiftV(IntVRef orig, IntVRef res, int k) {
        if (orig.get().n != res.get().n)
            throw new IllegalArgumentException("Cannot shift an IntV to an IntV of different size.");
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n - k; i++) res.get().getBits()[i].set(orig.get().getBits()[i + k].get().copy());
        for (int i = orig.get().n - k + 1; i <= orig.get().n; i++) res.get().getBits()[i].set(BoolV.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.get().n; i >= k ; i--) res.get().getBits()[i].set(orig.get().getBits()[i - k].get().copy());
        for (int i = 0; i < k; i++) res.get().getBits()[i].set(BoolV.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n - k; i++) res.get().getBits()[i].set(orig.get().getBits()[i + k].get().copy());
        for (int i = orig.get().n - k + 1; i <= orig.get().n; i++) res.get().getBits()[i].set(BoolVe.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.get().n; i >= k ; i--) res.get().getBits()[i].set(orig.get().getBits()[i - k].get().copy());
        for (int i = 0; i < k; i++) res.get().getBits()[i].set(BoolVe.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n - k; i++) res.get().getBits()[i].set(orig.get().getBits()[i + k].get().copy());
        for (int i = orig.get().n - k + 1; i <= orig.get().n; i++) res.get().getBits()[i].set(BoolVf.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.get().n; i >= k ; i--) res.get().getBits()[i].set(orig.get().getBits()[i - k].get().copy());
        for (int i = 0; i < k; i++) res.get().getBits()[i].set(BoolVf.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n - k; i++) res.get().getBits()[i].set(orig.get().getBits()[i + k].get().copy());
        for (int i = orig.get().n - k + 1; i <= orig.get().n; i++) res.get().getBits()[i].set(BoolE.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.get().n; i >= k ; i--) res.get().getBits()[i].set(orig.get().getBits()[i - k].get().copy());
        for (int i = 0; i < k; i++) res.get().getBits()[i].set(BoolE.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n - k; i++) res.get().getBits()[i].set(orig.get().getBits()[i + k].get().copy());
        for (int i = orig.get().n - k + 1; i <= orig.get().n; i++) res.get().getBits()[i].set(BoolEv.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.get().n; i >= k ; i--) res.get().getBits()[i].set(orig.get().getBits()[i - k].get().copy());
        for (int i = 0; i < k; i++) res.get().getBits()[i].set(BoolEv.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n - k; i++) res.get().getBits()[i].set(orig.get().getBits()[i + k].get().copy());
        for (int i = orig.get().n - k + 1; i <= orig.get().n; i++) res.get().getBits()[i].set(BoolEf.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = k; i <= orig.get().n; i++) res.get().getBits()[i].set(orig.get().getBits()[i - k].get().copy());
        for (int i = 0; i < k; i++) res.get().getBits()[i].set(BoolEf.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n - k; i++) res.get().getBits()[i].set(orig.get().getBits()[i + k].get().copy());
        for (int i = orig.get().n - k + 1; i <= orig.get().n; i++) res.get().getBits()[i].set(BoolF.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.get().n; i >= k ; i--) res.get().getBits()[i].set(orig.get().getBits()[i - k].get().copy());
        for (int i = 0; i < k; i++) res.get().getBits()[i].set(BoolF.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n - k; i++) res.get().getBits()[i].set(orig.get().getBits()[i + k].get().copy());
        for (int i = orig.get().n - k + 1; i <= orig.get().n; i++) res.get().getBits()[i].set(BoolFv.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.get().n; i >= k ; i--) res.get().getBits()[i].set(orig.get().getBits()[i - k].get().copy());
        for (int i = 0; i < k; i++) res.get().getBits()[i].set(BoolFv.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n - k; i++) res.get().getBits()[i].set(orig.get().getBits()[i + k].get().copy());
        for (int i = orig.get().n - k + 1; i <= orig.get().n; i++) res.get().getBits()[i].set(BoolFe.zeroes());
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
        if (k > orig.get().n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.get().n; i >= k ; i--) res.get().getBits()[i].set(orig.get().getBits()[i - k].get().copy());
        for (int i = 0; i < k; i++) res.get().getBits()[i].set(BoolFe.zeroes());
        return true;
    }
}