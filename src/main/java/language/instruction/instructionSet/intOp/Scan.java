package language.instruction.instructionSet.intOp;

import language.instruction.Instruction;
import language.instruction.Procedure;
import language.instruction.instructionSet.boolOp.BoolOp;
import language.obj.field.boolField.fieldS.BoolV;
import language.obj.field.intField.IntV;
import language.ref.field.boolField.fieldS.BoolVRef;
import language.ref.field.intField.IntVRef;

class ScanLeftV extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVRef a, BoolVRef res); }
    public ScanLeftV(IntVRef orig, BoolVRef res, Scan scan) {
        BoolVRef[] bits = new BoolVRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolVRef();
        split(orig, bits);

        for (int i = 0; i <= orig.get().n; i++) call(scan.apply(bits[i], res));
    }
}

class ScanRightV extends Procedure {
    @FunctionalInterface public interface Scan { Instruction apply(BoolVRef a, BoolVRef res); }
    public ScanRightV(IntVRef orig, BoolVRef res, Scan scan) {
        BoolVRef[] bits = new BoolVRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolVRef();
        split(orig, bits);

        for (int i = orig.get().n; i >= 0; i--) call(scan.apply(bits[i], res));
    }
}

class EqV extends Procedure {
    public EqV(IntVRef a, IntVRef b, BoolVRef res) {
        IntVRef test = IntVRef.of(new IntV(a.get().n));
        xor(a, b, test);

        set(res, BoolVRef.of(BoolV.zeroes()));
        call(new ScanLeftV(test, res, (a1, res1) -> BoolOp.or(a1, res1, res1)));
        not(res, res);
    }
}