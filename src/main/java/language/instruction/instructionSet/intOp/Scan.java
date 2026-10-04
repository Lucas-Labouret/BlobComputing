package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Instruction;
import language.instruction.Procedure;

class ScanLeftV extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolV a, BoolV res); }
    public ScanLeftV(IntV orig, BoolV res, Scan scan) {
        BoolV[] bits = orig.getBits();
        set(bits[0], res);
        for (int i = 1; i <= orig.n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightV extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolV a, BoolV res); }
    public ScanRightV(IntV orig, BoolV res, Scan scan) {
        BoolV[] bits = orig.getBits();
        set(bits[orig.n], res);
        for (int i = orig.n - 1; i >= 0; i--) call(scan.apply(bits[i], res));
    }
}

class ScanLeftVe extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVe a, BoolVe res); }
    public ScanLeftVe(IntVe orig, BoolVe res, Scan scan) {
        BoolVe[] bits = orig.getBits();
        set(bits[0], res);
        for (int i = 1; i <= orig.n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightVe extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVe a, BoolVe res); }
    public ScanRightVe(IntVe orig, BoolVe res, Scan scan) {
        BoolVe[] bits = orig.getBits();
        set(bits[orig.n], res);
        for (int i = orig.n - 1; i >= 0; i--) call(scan.apply(bits[i], res));
    }
}

class ScanLeftVf extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVf a, BoolVf res); }
    public ScanLeftVf(IntVf orig, BoolVf res, Scan scan) {
        BoolVf[] bits = orig.getBits();
        set(bits[0], res);
        for (int i = 1; i <= orig.n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightVf extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVf a, BoolVf res); }
    public ScanRightVf(IntVf orig, BoolVf res, Scan scan) {
        BoolVf[] bits = orig.getBits();
        set(bits[orig.n], res);
        for (int i = orig.n - 1; i >= 0; i--) call(scan.apply(bits[i], res));
    }
}

class ScanLeftE extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolE a, BoolE res); }
    public ScanLeftE(IntE orig, BoolE res, Scan scan) {
        BoolE[] bits = orig.getBits();
        set(bits[0], res);
        for (int i = 1; i <= orig.n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightE extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolE a, BoolE res); }
    public ScanRightE(IntE orig, BoolE res, Scan scan) {
        BoolE[] bits = orig.getBits();
        set(bits[orig.n], res);
        for (int i = orig.n - 1; i >= 0; i--) call(scan.apply(bits[i], res));
    }
}

class ScanLeftEv extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolEv a, BoolEv res); }
    public ScanLeftEv(IntEv orig, BoolEv res, Scan scan) {
        BoolEv[] bits = orig.getBits();
        set(bits[0], res);
        for (int i = 1; i <= orig.n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightEv extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolEv a, BoolEv res); }
    public ScanRightEv(IntEv orig, BoolEv res, Scan scan) {
        BoolEv[] bits = orig.getBits();
        set(bits[orig.n], res);
        for (int i = orig.n - 1; i >= 0; i--) call(scan.apply(bits[i], res));
    }
}

class ScanLeftEf extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolEf a, BoolEf res); }
    public ScanLeftEf(IntEf orig, BoolEf res, Scan scan) {
        BoolEf[] bits = orig.getBits();
        set(bits[0], res);
        for (int i = 1; i <= orig.n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightEf extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolEf a, BoolEf res); }
    public ScanRightEf(IntEf orig, BoolEf res, Scan scan) {
        BoolEf[] bits = orig.getBits();
        set(bits[orig.n], res);
        for (int i = orig.n - 1; i >= 0; i--) call(scan.apply(bits[i], res));
    }
}

class ScanLeftF extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolF a, BoolF res); }
    public ScanLeftF(IntF orig, BoolF res, Scan scan) {
        BoolF[] bits = orig.getBits();
        set(bits[0], res);
        for (int i = 1; i <= orig.n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightF extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolF a, BoolF res); }
    public ScanRightF(IntF orig, BoolF res, Scan scan) {
        BoolF[] bits = orig.getBits();
        set(bits[orig.n], res);
        for (int i = orig.n - 1; i >= 0; i--) call(scan.apply(bits[i], res));
    }
}

class ScanLeftFv extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolFv a, BoolFv res); }
    public ScanLeftFv(IntFv orig, BoolFv res, Scan scan) {
        BoolFv[] bits = orig.getBits();
        set(bits[0], res);
        for (int i = 1; i <= orig.n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightFv extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolFv a, BoolFv res); }
    public ScanRightFv(IntFv orig, BoolFv res, Scan scan) {
        BoolFv[] bits = orig.getBits();
        set(bits[orig.n], res);
        for (int i = orig.n - 1; i >= 0; i--) call(scan.apply(bits[i], res));
    }
}

class ScanLeftFe extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolFe a, BoolFe res); }
    public ScanLeftFe(IntFe orig, BoolFe res, Scan scan) {
        BoolFe[] bits = orig.getBits();
        set(bits[0], res);
        for (int i = 1; i <= orig.n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightFe extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolFe a, BoolFe res); }
    public ScanRightFe(IntFe orig, BoolFe res, Scan scan) {
        BoolFe[] bits = orig.getBits();
        set(bits[orig.n], res);
        for (int i = orig.n - 1; i >= 0; i--) call(scan.apply(bits[i], res));
    }
}
