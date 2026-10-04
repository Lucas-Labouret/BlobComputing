package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Instruction;
import language.instruction.Procedure;
import language.utils.BoolFieldManager;

class RedStack_Ve extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolVe orig, BoolV[] res); }
    RedStack_Ve(IntVe orig, IntV[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVe[] bits = orig.getBits();

        BoolV[][] veStacks = new BoolV[orig.n + 1][breadth];
        for (int i = 0; i <= orig.n; i++) {
            veStacks[i] = new BoolV[breadth];
            for (int j = 0; j < breadth; j++) veStacks[i][j] = tmp(new BoolV());
            call(redStacks[i].stack(bits[i], veStacks[i]));
        }

        BoolV[][] bitStacks = new BoolV[breadth][orig.n + 1];
        IntField.transpose(veStacks, bitStacks, breadth - 1, orig.n);

        for (int i = 0; i < breadth; i++) {
            BoolV[] resBits = res[i].getBits();
            for (int j = 0; j <= orig.n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}

class RedStack_Vf extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolVf orig, BoolV[] res); }
    RedStack_Vf(IntVf orig, IntV[] res, RedStack[] redStack) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVf[] bits = orig.getBits();

        BoolV[][] vfStacks = new BoolV[orig.n + 1][breadth];
        for (int i = 0; i <= orig.n; i++) {
            vfStacks[i] = new BoolV[breadth];
            for (int j = 0; j < breadth; j++) vfStacks[i][j] = tmp(new BoolV());
            call(redStack[i].stack(bits[i], vfStacks[i]));
        }

        BoolV[][] bitStacks = new BoolV[breadth][orig.n + 1];
        IntField.transpose(vfStacks, bitStacks, breadth - 1, orig.n);

        for (int i = 0; i < breadth; i++) {
            BoolV[] resBits = res[i].getBits();
            for (int j = 0; j <= orig.n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}

class RedStack_Ev extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolEv orig, BoolE[] res); }
    RedStack_Ev(IntEv orig, IntE[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolEv[] bits = orig.getBits();

        BoolE[][] evStacks = new BoolE[orig.n + 1][breadth];
        for (int i = 0; i <= orig.n; i++) {
            evStacks[i] = new BoolE[breadth];
            for (int j = 0; j < breadth; j++) evStacks[i][j] = tmp(new BoolE());
            call(redStacks[i].stack(bits[i], evStacks[i]));
        }

        BoolE[][] bitStacks = new BoolE[breadth][orig.n + 1];
        IntField.transpose(evStacks, bitStacks, breadth - 1, orig.n);

        for (int i = 0; i < breadth; i++) {
            BoolE[] resBits = res[i].getBits();
            for (int j = 0; j <= orig.n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}

class RedStack_Ef extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolEf orig, BoolE[] res); }
    RedStack_Ef(IntEf orig, IntE[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolEf[] bits = orig.getBits();

        BoolE[][] efStacks = new BoolE[orig.n + 1][breadth];
        for (int i = 0; i <= orig.n; i++) {
            efStacks[i] = new BoolE[breadth];
            for (int j = 0; j < breadth; j++) efStacks[i][j] = tmp(new BoolE());
            call(redStacks[i].stack(bits[i], efStacks[i]));
        }

        BoolE[][] bitStacks = new BoolE[breadth][orig.n + 1];
        IntField.transpose(efStacks, bitStacks, breadth - 1, orig.n);

        for (int i = 0; i < breadth; i++) {
            BoolE[] resBits = res[i].getBits();
            for (int j = 0; j <= orig.n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}

class RedStack_Fv extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolFv orig, BoolF[] res); }
    RedStack_Fv(IntFv orig, IntF[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFv[] bits = orig.getBits();

        BoolF[][] fvStacks = new BoolF[orig.n + 1][breadth];
        for (int i = 0; i <= orig.n; i++) {
            fvStacks[i] = new BoolF[breadth];
            for (int j = 0; j < breadth; j++) fvStacks[i][j] = tmp(new BoolF());
            call(redStacks[i].stack(bits[i], fvStacks[i]));
        }

        BoolF[][] bitStacks = new BoolF[breadth][orig.n + 1];
        IntField.transpose(fvStacks, bitStacks, breadth - 1, orig.n);

        for (int i = 0; i < breadth; i++) {
            BoolF[] resBits = res[i].getBits();
            for (int j = 0; j <= orig.n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}

class RedStack_Fe extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolFe orig, BoolF[] res); }
    RedStack_Fe(IntFe orig, IntF[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFe[] bits = orig.getBits();

        BoolF[][] feStacks = new BoolF[orig.n + 1][breadth];
        for (int i = 0; i <= orig.n; i++) {
            feStacks[i] = new BoolF[breadth];
            for (int j = 0; j < breadth; j++) feStacks[i][j] = tmp(new BoolF());
            call(redStacks[i].stack(bits[i], feStacks[i]));
        }

        BoolF[][] bitStacks = new BoolF[breadth][orig.n + 1];
        IntField.transpose(feStacks, bitStacks, breadth - 1, orig.n);

        for (int i = 0; i < breadth; i++) {
            BoolF[] resBits = res[i].getBits();
            for (int j = 0; j <= orig.n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}
