package language.instruction.instructionSet.intOp;

import language.instruction.Instruction;
import language.instruction.Procedure;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

class ScanLeftV extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVRef a, BoolVRef res); }
    public ScanLeftV(IntVRef orig, BoolVRef res, Scan scan) {
        BoolVRef[] bits = new BoolVRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolVRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightV extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVRef a, BoolVRef res); }
    public ScanRightV(IntVRef orig, BoolVRef res, Scan scan) {
        BoolVRef[] bits = new BoolVRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolVRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanLeftVe extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVeRef a, BoolVeRef res); }
    public ScanLeftVe(IntVeRef orig, BoolVeRef res, Scan scan) {
        BoolVeRef[] bits = new BoolVeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolVeRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightVe extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVeRef a, BoolVeRef res); }
    public ScanRightVe(IntVeRef orig, BoolVeRef res, Scan scan) {
        BoolVeRef[] bits = new BoolVeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolVeRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanLeftVf extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVfRef a, BoolVfRef res); }
    public ScanLeftVf(IntVfRef orig, BoolVfRef res, Scan scan) {
        BoolVfRef[] bits = new BoolVfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolVfRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightVf extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVfRef a, BoolVfRef res); }
    public ScanRightVf(IntVfRef orig, BoolVfRef res, Scan scan) {
        BoolVfRef[] bits = new BoolVfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolVfRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanLeftE extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolERef a, BoolERef res); }
    public ScanLeftE(IntERef orig, BoolERef res, Scan scan) {
        BoolERef[] bits = new BoolERef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolERef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightE extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolERef a, BoolERef res); }
    public ScanRightE(IntERef orig, BoolERef res, Scan scan) {
        BoolERef[] bits = new BoolERef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolERef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanLeftEv extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolEvRef a, BoolEvRef res); }
    public ScanLeftEv(IntEvRef orig, BoolEvRef res, Scan scan) {
        BoolEvRef[] bits = new BoolEvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolEvRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightEv extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolEvRef a, BoolEvRef res); }
    public ScanRightEv(IntEvRef orig, BoolEvRef res, Scan scan) {
        BoolEvRef[] bits = new BoolEvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolEvRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanLeftEf extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolEfRef a, BoolEfRef res); }
    public ScanLeftEf(IntEfRef orig, BoolEfRef res, Scan scan) {
        BoolEfRef[] bits = new BoolEfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolEfRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightEf extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolEfRef a, BoolEfRef res); }
    public ScanRightEf(IntEfRef orig, BoolEfRef res, Scan scan) {
        BoolEfRef[] bits = new BoolEfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolEfRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanLeftF extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolFRef a, BoolFRef res); }
    public ScanLeftF(IntFRef orig, BoolFRef res, Scan scan) {
        BoolFRef[] bits = new BoolFRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolFRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightF extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolFRef a, BoolFRef res); }
    public ScanRightF(IntFRef orig, BoolFRef res, Scan scan) {
        BoolFRef[] bits = new BoolFRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolFRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanLeftFv extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolFvRef a, BoolFvRef res); }
    public ScanLeftFv(IntFvRef orig, BoolFvRef res, Scan scan) {
        BoolFvRef[] bits = new BoolFvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolFvRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightFv extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolFvRef a, BoolFvRef res); }
    public ScanRightFv(IntFvRef orig, BoolFvRef res, Scan scan) {
        BoolFvRef[] bits = new BoolFvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolFvRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanLeftFe extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolFeRef a, BoolFeRef res); }
    public ScanLeftFe(IntFeRef orig, BoolFeRef res, Scan scan) {
        BoolFeRef[] bits = new BoolFeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolFeRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightFe extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolFeRef a, BoolFeRef res); }
    public ScanRightFe(IntFeRef orig, BoolFeRef res, Scan scan) {
        BoolFeRef[] bits = new BoolFeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolFeRef();
        split(orig, bits);

        set(bits[0], res);
        for (int i = 1; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}
