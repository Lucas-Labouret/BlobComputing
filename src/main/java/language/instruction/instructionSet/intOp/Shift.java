package language.instruction.instructionSet.intOp;

import language.field.intField.*;
import language.instruction.BasicInstruction;

class LShiftV implements BasicInstruction {
    private final IntV orig;
    private final IntV res;
    private final int k;

    public LShiftV(IntV orig, IntV res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntV to an IntV of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.n - k; i++) res.getBits()[i].set(orig.getBits()[i + k]);
        for (int i = orig.n - k + 1; i <= orig.n; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class RShiftV implements BasicInstruction {
    private final IntV orig;
    private final IntV res;
    private final int k;

    public RShiftV(IntV orig, IntV res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntV to an IntV of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.n; i >= k ; i--) res.getBits()[i].set(orig.getBits()[i - k]);
        for (int i = 0; i < k; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class LShiftVe implements BasicInstruction {
    private final IntVe orig;
    private final IntVe res;
    private final int k;

    public LShiftVe(IntVe orig, IntVe res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntVe to an IntVe of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.n - k; i++) res.getBits()[i].set(orig.getBits()[i + k]);
        for (int i = orig.n - k + 1; i <= orig.n; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class RShiftVe implements BasicInstruction {
    private final IntVe orig;
    private final IntVe res;
    private final int k;

    public RShiftVe(IntVe orig, IntVe res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntVe to an IntVe of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.n; i >= k ; i--) res.getBits()[i].set(orig.getBits()[i - k]);
        for (int i = 0; i < k; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class LShiftVf implements BasicInstruction {
    private final IntVf orig;
    private final IntVf res;
    private final int k;

    public LShiftVf(IntVf orig, IntVf res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntVf to an IntVf of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.n - k; i++) res.getBits()[i].set(orig.getBits()[i + k]);
        for (int i = orig.n - k + 1; i <= orig.n; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class RShiftVf implements BasicInstruction {
    private final IntVf orig;
    private final IntVf res;
    private final int k;

    public RShiftVf(IntVf orig, IntVf res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntVf to an IntVf of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.n; i >= k ; i--) res.getBits()[i].set(orig.getBits()[i - k]);
        for (int i = 0; i < k; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class LShiftE implements BasicInstruction {
    private final IntE orig;
    private final IntE res;
    private final int k;

    public LShiftE(IntE orig, IntE res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntE to an IntE of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.n - k; i++) res.getBits()[i].set(orig.getBits()[i + k]);
        for (int i = orig.n - k + 1; i <= orig.n; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class RShiftE implements BasicInstruction {
    private final IntE orig;
    private final IntE res;
    private final int k;

    public RShiftE(IntE orig, IntE res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntE to an IntE of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.n; i >= k ; i--) res.getBits()[i].set(orig.getBits()[i - k]);
        for (int i = 0; i < k; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class LShiftEv implements BasicInstruction {
    private final IntEv orig;
    private final IntEv res;
    private final int k;

    public LShiftEv(IntEv orig, IntEv res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntEv to an IntEv of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.n - k; i++) res.getBits()[i].set(orig.getBits()[i + k]);
        for (int i = orig.n - k + 1; i <= orig.n; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class RShiftEv implements BasicInstruction {
    private final IntEv orig;
    private final IntEv res;
    private final int k;

    public RShiftEv(IntEv orig, IntEv res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntEv to an IntEv of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.n; i >= k ; i--) res.getBits()[i].set(orig.getBits()[i - k]);
        for (int i = 0; i < k; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class LShiftEf implements BasicInstruction {
    private final IntEf orig;
    private final IntEf res;
    private final int k;

    public LShiftEf(IntEf orig, IntEf res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntEf to an IntEf of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.n - k; i++) res.getBits()[i].set(orig.getBits()[i + k]);
        for (int i = orig.n - k + 1; i <= orig.n; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class RShiftEf implements BasicInstruction {
    private final IntEf orig;
    private final IntEf res;
    private final int k;

    public RShiftEf(IntEf orig, IntEf res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntEf to an IntEf of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = k; i <= orig.n; i++) res.getBits()[i].set(orig.getBits()[i - k]);
        for (int i = 0; i < k; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class LShiftF implements BasicInstruction {
    private final IntF orig;
    private final IntF res;
    private final int k;

    public LShiftF(IntF orig, IntF res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntF to an IntF of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.n - k; i++) res.getBits()[i].set(orig.getBits()[i + k]);
        for (int i = orig.n - k + 1; i <= orig.n; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class RShiftF implements BasicInstruction {
    private final IntF orig;
    private final IntF res;
    private final int k;

    public RShiftF(IntF orig, IntF res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntF to an IntF of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.n; i >= k ; i--) res.getBits()[i].set(orig.getBits()[i - k]);
        for (int i = 0; i < k; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class LShiftFv implements BasicInstruction {
    private final IntFv orig;
    private final IntFv res;
    private final int k;

    public LShiftFv(IntFv orig, IntFv res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntFv to an IntFv of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.n - k; i++) res.getBits()[i].set(orig.getBits()[i + k]);
        for (int i = orig.n - k + 1; i <= orig.n; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class RShiftFv implements BasicInstruction {
    private final IntFv orig;
    private final IntFv res;
    private final int k;

    public RShiftFv(IntFv orig, IntFv res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntFv to an IntFv of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.n; i >= k ; i--) res.getBits()[i].set(orig.getBits()[i - k]);
        for (int i = 0; i < k; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class LShiftFe implements BasicInstruction {
    private final IntFe orig;
    private final IntFe res;
    private final int k;

    public LShiftFe(IntFe orig, IntFe res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntFe to an IntFe of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.n - k; i++) res.getBits()[i].set(orig.getBits()[i + k]);
        for (int i = orig.n - k + 1; i <= orig.n; i++) res.getBits()[i].zeroes();
        return true;
    }
}

class RShiftFe implements BasicInstruction {
    private final IntFe orig;
    private final IntFe res;
    private final int k;

    public RShiftFe(IntFe orig, IntFe res, int k) {
        if (orig.n != res.n)
            throw new IllegalArgumentException("Cannot shift an IntFe to an IntFe of different size.");
        if (k > orig.n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");

        this.orig = orig;
        this.res = res;
        this.k = k;
    }

    @Override
    public boolean exec() {
        for (int i = orig.n; i >= k ; i--) res.getBits()[i].set(orig.getBits()[i - k]);
        for (int i = 0; i < k; i++) res.getBits()[i].zeroes();
        return true;
    }
}