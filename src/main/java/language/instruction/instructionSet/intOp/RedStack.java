package language.instruction.instructionSet.intOp;

import language.instruction.Instruction;
import language.instruction.Procedure;
import language.obj.field.intField.IntField;
import language.ref.field.boolField.*;
import language.ref.field.intField.*;
import language.utils.BoolFieldManager;

class RedStack_Ve extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolVeRef orig, BoolVRef[] res); }
    RedStack_Ve(IntVeRef orig, IntVRef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVeRef[] bits = new BoolVeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolVeRef();
        split(orig, bits);

        BoolVRef[][] veStacks = new BoolVRef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            veStacks[i] = new BoolVRef[breadth];
            for (int j = 0; j < breadth; j++) veStacks[i][j] = new BoolVRef();
            call(redStacks[i].stack(bits[i], veStacks[i]));
        }

        BoolVRef[][] bitStacks = new BoolVRef[breadth][orig.get().n + 1];
        IntField.transpose(veStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) join(bitStacks[i], res[i]);
    }
}

class RedStack_Vf extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolVfRef orig, BoolVRef[] res); }
    RedStack_Vf(IntVfRef orig, IntVRef[] res, RedStack[] redStack) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVfRef[] bits = new BoolVfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolVfRef();
        split(orig, bits);

        BoolVRef[][] vfStacks = new BoolVRef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            vfStacks[i] = new BoolVRef[breadth];
            for (int j = 0; j < breadth; j++) vfStacks[i][j] = new BoolVRef();
            call(redStack[i].stack(bits[i], vfStacks[i]));
        }

        BoolVRef[][] bitStacks = new BoolVRef[breadth][orig.get().n + 1];
        IntField.transpose(vfStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) join(bitStacks[i], res[i]);
    }
}

class RedStack_Ev extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolEvRef orig, BoolERef[] res); }
    RedStack_Ev(IntEvRef orig, IntERef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolEvRef[] bits = new BoolEvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolEvRef();
        split(orig, bits);

        BoolERef[][] evStacks = new BoolERef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            evStacks[i] = new BoolERef[breadth];
            for (int j = 0; j < breadth; j++) evStacks[i][j] = new BoolERef();
            call(redStacks[i].stack(bits[i], evStacks[i]));
        }

        BoolERef[][] bitStacks = new BoolERef[breadth][orig.get().n + 1];
        IntField.transpose(evStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) {
            join(bitStacks[i], res[i]);
        }
    }
}

class RedStack_Ef extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolEfRef orig, BoolERef[] res); }
    RedStack_Ef(IntEfRef orig, IntERef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolEfRef[] bits = new BoolEfRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolEfRef();
        split(orig, bits);

        BoolERef[][] efStacks = new BoolERef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            efStacks[i] = new BoolERef[breadth];
            for (int j = 0; j < breadth; j++) efStacks[i][j] = new BoolERef();
            call(redStacks[i].stack(bits[i], efStacks[i]));
        }

        BoolERef[][] bitStacks = new BoolERef[breadth][orig.get().n + 1];
        IntField.transpose(efStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) {
            join(bitStacks[i], res[i]);
        }
    }
}

class RedStack_Fv extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolFvRef orig, BoolFRef[] res); }
    RedStack_Fv(IntFvRef orig, IntFRef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFvRef[] bits = new BoolFvRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolFvRef();
        split(orig, bits);

        BoolFRef[][] fvStacks = new BoolFRef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            fvStacks[i] = new BoolFRef[breadth];
            for (int j = 0; j < breadth; j++) fvStacks[i][j] = new BoolFRef();
            call(redStacks[i].stack(bits[i], fvStacks[i]));
        }

        BoolFRef[][] bitStacks = new BoolFRef[breadth][orig.get().n + 1];
        IntField.transpose(fvStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) join(bitStacks[i], res[i]);
    }
}

class RedStack_Fe extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolFeRef orig, BoolFRef[] res); }
    RedStack_Fe(IntFeRef orig, IntFRef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFeRef[] bits = new BoolFeRef[orig.get().n + 1];
        for (int i = 0; i <= orig.get().n; i++) bits[i] = new BoolFeRef();
        split(orig, bits);

        BoolFRef[][] feStacks = new BoolFRef[orig.get().n + 1][breadth];
        for (int i = 0; i <= orig.get().n; i++) {
            feStacks[i] = new BoolFRef[breadth];
            for (int j = 0; j < breadth; j++) feStacks[i][j] = new BoolFRef();
            call(redStacks[i].stack(bits[i], feStacks[i]));
        }

        BoolFRef[][] bitStacks = new BoolFRef[breadth][orig.get().n + 1];
        IntField.transpose(feStacks, bitStacks, breadth - 1, orig.get().n);

        for (int i = 0; i < breadth; i++) join(bitStacks[i], res[i]);
    }
}
