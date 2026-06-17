package language.instruction.instructionSet.intOp;

import language.instruction.Instruction;
import language.instruction.Procedure;
import language.field.intField.IntField;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;
import language.utils.BoolFieldManager;

class RedStack_Ve extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolVeRef orig, BoolVRef[] res); }
    RedStack_Ve(IntVeRef orig, IntVRef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVeRef[] bits = orig.get().getBits();

        BoolVRef[][] veStacks = new BoolVRef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            veStacks[i] = new BoolVRef[breadth];
            for (int j = 0; j < breadth; j++) veStacks[i][j] = tmp(new BoolVRef());
            call(redStacks[i].stack(bits[i], veStacks[i]));
        }

        BoolVRef[][] bitStacks = new BoolVRef[breadth][orig.get().n + 1];
        IntField.transpose(veStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) {
            BoolVRef[] resBits = res[i].get().getBits();
            for (int j = 0; j <= orig.get().n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}

class RedStack_Vf extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolVfRef orig, BoolVRef[] res); }
    RedStack_Vf(IntVfRef orig, IntVRef[] res, RedStack[] redStack) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVfRef[] bits = orig.get().getBits();

        BoolVRef[][] vfStacks = new BoolVRef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            vfStacks[i] = new BoolVRef[breadth];
            for (int j = 0; j < breadth; j++) vfStacks[i][j] = tmp(new BoolVRef());
            call(redStack[i].stack(bits[i], vfStacks[i]));
        }

        BoolVRef[][] bitStacks = new BoolVRef[breadth][orig.get().n + 1];
        IntField.transpose(vfStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) {
            BoolVRef[] resBits = res[i].get().getBits();
            for (int j = 0; j <= orig.get().n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}

class RedStack_Ev extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolEvRef orig, BoolERef[] res); }
    RedStack_Ev(IntEvRef orig, IntERef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolEvRef[] bits = orig.get().getBits();

        BoolERef[][] evStacks = new BoolERef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            evStacks[i] = new BoolERef[breadth];
            for (int j = 0; j < breadth; j++) evStacks[i][j] = tmp(new BoolERef());
            call(redStacks[i].stack(bits[i], evStacks[i]));
        }

        BoolERef[][] bitStacks = new BoolERef[breadth][orig.get().n + 1];
        IntField.transpose(evStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) {
            BoolERef[] resBits = res[i].get().getBits();
            for (int j = 0; j <= orig.get().n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}

class RedStack_Ef extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolEfRef orig, BoolERef[] res); }
    RedStack_Ef(IntEfRef orig, IntERef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolEfRef[] bits = orig.get().getBits();

        BoolERef[][] efStacks = new BoolERef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            efStacks[i] = new BoolERef[breadth];
            for (int j = 0; j < breadth; j++) efStacks[i][j] = tmp(new BoolERef());
            call(redStacks[i].stack(bits[i], efStacks[i]));
        }

        BoolERef[][] bitStacks = new BoolERef[breadth][orig.get().n + 1];
        IntField.transpose(efStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) {
            BoolERef[] resBits = res[i].get().getBits();
            for (int j = 0; j <= orig.get().n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}

class RedStack_Fv extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolFvRef orig, BoolFRef[] res); }
    RedStack_Fv(IntFvRef orig, IntFRef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFvRef[] bits = orig.get().getBits();

        BoolFRef[][] fvStacks = new BoolFRef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            fvStacks[i] = new BoolFRef[breadth];
            for (int j = 0; j < breadth; j++) fvStacks[i][j] = tmp(new BoolFRef());
            call(redStacks[i].stack(bits[i], fvStacks[i]));
        }

        BoolFRef[][] bitStacks = new BoolFRef[breadth][orig.get().n + 1];
        IntField.transpose(fvStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) {
            BoolFRef[] resBits = res[i].get().getBits();
            for (int j = 0; j <= orig.get().n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}

class RedStack_Fe extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolFeRef orig, BoolFRef[] res); }
    RedStack_Fe(IntFeRef orig, IntFRef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFeRef[] bits = orig.get().getBits();

        BoolFRef[][] feStacks = new BoolFRef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            feStacks[i] = new BoolFRef[breadth];
            for (int j = 0; j < breadth; j++) feStacks[i][j] = tmp(new BoolFRef());
            call(redStacks[i].stack(bits[i], feStacks[i]));
        }

        BoolFRef[][] bitStacks = new BoolFRef[breadth][orig.get().n + 1];
        IntField.transpose(feStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) {
            BoolFRef[] resBits = res[i].get().getBits();
            for (int j = 0; j <= orig.get().n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}
