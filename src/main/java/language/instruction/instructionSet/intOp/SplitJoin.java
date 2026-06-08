package language.instruction.instructionSet.intOp;

import language.instruction.BasicInstruction;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class SplitV implements BasicInstruction {
    private final IntVRef orig;
    private final BoolVRef[] res;

    public SplitV(IntVRef orig, BoolVRef[] res) {
        if (orig.get().n+1 != res.length)
            throw new IllegalArgumentException("Cannot split IntVs of " + (orig.get().n + 1) + "bits " +
                    "into an array of " + res.length + ".");

        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n; i++) res[i].set(orig.get().getBits()[i].get() == null ? null : orig.get().getBits()[i].get().copy());
        return true;
    }
}

class JoinV implements BasicInstruction {
    private final BoolVRef[] orig;
    private final IntVRef res;

    public JoinV(BoolVRef[] orig, IntVRef res) {
        if (orig.length != res.get().n + 1)
            throw new IllegalArgumentException("Cannot join IntVs of " + (orig.length + 1) + "bits.");

        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= res.get().n; i++) res.get().getBits()[i].set(orig[i].get() == null ? null : orig[i].get().copy());
        return true;
    }
}

class SplitVe implements BasicInstruction {
    private final IntVeRef orig;
    private final BoolVeRef[] res;

    public SplitVe(IntVeRef orig, BoolVeRef[] res) {
        if (orig.get().n + 1 != res.length)
            throw new IllegalArgumentException("Cannot split IntVs of " + (orig.get().n + 1) + "bits into an array of " + res.length + ".");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n; i++) res[i].set(orig.get().getBits()[i].get() == null ? null : orig.get().getBits()[i].get().copy());
        return true;
    }
}

class JoinVe implements BasicInstruction {
    private final BoolVeRef[] orig;
    private final IntVeRef res;

    public JoinVe(BoolVeRef[] orig, IntVeRef res) {
        if (orig.length != res.get().n + 1)
            throw new IllegalArgumentException("Cannot join IntVs of " + (orig.length + 1) + "bits.");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= res.get().n; i++) res.get().getBits()[i].set(orig[i].get() == null ? null : orig[i].get().copy());
        return true;
    }
}

class SplitVf implements BasicInstruction {
    private final IntVfRef orig;
    private final BoolVfRef[] res;

    public SplitVf(IntVfRef orig, BoolVfRef[] res) {
        if (orig.get().n + 1 != res.length)
            throw new IllegalArgumentException("Cannot split IntVs of " + (orig.get().n + 1) + "bits into an array of " + res.length + ".");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n; i++) res[i].set(orig.get().getBits()[i].get() == null ? null : orig.get().getBits()[i].get().copy());
        return true;
    }
}

class JoinVf implements BasicInstruction {
    private final BoolVfRef[] orig;
    private final IntVfRef res;

    public JoinVf(BoolVfRef[] orig, IntVfRef res) {
        if (orig.length != res.get().n + 1)
            throw new IllegalArgumentException("Cannot join IntVs of " + (orig.length + 1) + "bits.");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= res.get().n; i++) res.get().getBits()[i].set(orig[i].get() == null ? null : orig[i].get().copy());
        return true;
    }
}

class SplitE implements BasicInstruction {
    private final IntERef orig;
    private final BoolERef[] res;

    public SplitE(IntERef orig, BoolERef[] res) {
        if (orig.get().n + 1 != res.length)
            throw new IllegalArgumentException("Cannot split IntEs of " + (orig.get().n + 1) + "bits into an array of " + res.length + ".");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n; i++) res[i].set(orig.get().getBits()[i].get() == null ? null : orig.get().getBits()[i].get().copy());
        return true;
    }
}

class JoinE implements BasicInstruction {
    private final BoolERef[] orig;
    private final IntERef res;

    public JoinE(BoolERef[] orig, IntERef res) {
        if (orig.length != res.get().n + 1)
            throw new IllegalArgumentException("Cannot join IntEs of " + (orig.length + 1) + "bits.");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= res.get().n; i++) res.get().getBits()[i].set(orig[i].get() == null ? null : orig[i].get().copy());
        return true;
    }
}

class SplitEv implements BasicInstruction {
    private final IntEvRef orig;
    private final BoolEvRef[] res;

    public SplitEv(IntEvRef orig, BoolEvRef[] res) {
        if (orig.get().n + 1 != res.length)
            throw new IllegalArgumentException("Cannot split IntEs of " + (orig.get().n + 1) + "bits into an array of " + res.length + ".");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n; i++) res[i].set(orig.get().getBits()[i].get() == null ? null : orig.get().getBits()[i].get().copy());
        return true;
    }
}

class JoinEv implements BasicInstruction {
    private final BoolEvRef[] orig;
    private final IntEvRef res;

    public JoinEv(BoolEvRef[] orig, IntEvRef res) {
        if (orig.length != res.get().n + 1)
            throw new IllegalArgumentException("Cannot join IntEs of " + (orig.length + 1) + "bits.");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= res.get().n; i++) res.get().getBits()[i].set(orig[i].get() == null ? null : orig[i].get().copy());
        return true;
    }
}

class SplitEf implements BasicInstruction {
    private final IntEfRef orig;
    private final BoolEfRef[] res;

    public SplitEf(IntEfRef orig, BoolEfRef[] res) {
        if (orig.get().n + 1 != res.length)
            throw new IllegalArgumentException("Cannot split IntEs of " + (orig.get().n + 1) + "bits into an array of " + res.length + ".");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n; i++) res[i].set(orig.get().getBits()[i].get() == null ? null : orig.get().getBits()[i].get().copy());
        return true;
    }
}

class JoinEf implements BasicInstruction {
    private final BoolEfRef[] orig;
    private final IntEfRef res;

    public JoinEf(BoolEfRef[] orig, IntEfRef res) {
        if (orig.length != res.get().n + 1)
            throw new IllegalArgumentException("Cannot join IntEs of " + (orig.length + 1) + "bits.");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= res.get().n; i++) res.get().getBits()[i].set(orig[i].get() == null ? null : orig[i].get().copy());
        return true;
    }
}

class SplitF implements BasicInstruction {
    private final IntFRef orig;
    private final BoolFRef[] res;

    public SplitF(IntFRef orig, BoolFRef[] res) {
        if (orig.get().n + 1 != res.length)
            throw new IllegalArgumentException("Cannot split IntFs of " + (orig.get().n + 1) + "bits into an array of " + res.length + ".");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n; i++) res[i].set(orig.get().getBits()[i].get() == null ? null : orig.get().getBits()[i].get().copy());
        return true;
    }
}

class JoinF implements BasicInstruction {
    private final BoolFRef[] orig;
    private final IntFRef res;

    public JoinF(BoolFRef[] orig, IntFRef res) {
        if (orig.length != res.get().n + 1)
            throw new IllegalArgumentException("Cannot join IntFs of " + (orig.length + 1) + "bits.");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= res.get().n; i++) res.get().getBits()[i].set(orig[i].get() == null ? null : orig[i].get().copy());
        return true;
    }
}

class SplitFv implements BasicInstruction {
    private final IntFvRef orig;
    private final BoolFvRef[] res;

    public SplitFv(IntFvRef orig, BoolFvRef[] res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n; i++) res[i].set(orig.get().getBits()[i].get() == null ? null : orig.get().getBits()[i].get().copy());
        return true;
    }
}

class JoinFv implements BasicInstruction {
    private final BoolFvRef[] orig;
    private final IntFvRef res;

    public JoinFv(BoolFvRef[] orig, IntFvRef res) {
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= res.get().n; i++) res.get().getBits()[i].set(orig[i].get() == null ? null : orig[i].get().copy());
        return true;
    }
}

class SplitFe implements BasicInstruction {
    private final IntFeRef orig;
    private final BoolFeRef[] res;

    public SplitFe(IntFeRef orig, BoolFeRef[] res) {
        if (orig.get().n + 1 != res.length)
            throw new IllegalArgumentException("Cannot split IntEs of " + (orig.get().n + 1) + "bits into an array of " + res.length + ".");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= orig.get().n; i++) res[i].set(orig.get().getBits()[i].get() == null ? null : orig.get().getBits()[i].get().copy());
        return true;
    }
}

class JoinFe implements BasicInstruction {
    private final BoolFeRef[] orig;
    private final IntFeRef res;

    public JoinFe(BoolFeRef[] orig, IntFeRef res) {
        if (orig.length != res.get().n + 1)
            throw new IllegalArgumentException("Cannot join IntEs of " + (orig.length + 1) + "bits.");
        this.orig = orig;
        this.res = res;
    }

    @Override
    public boolean exec() {
        for (int i = 0; i <= res.get().n; i++) res.get().getBits()[i].set(orig[i].get() == null ? null : orig[i].get().copy());
        return true;
    }
}
