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

        BoolVeRef[] bits = new BoolVeRef[breadth];
        for (int i = 0; i < breadth; i++) bits[i] = new BoolVeRef();
        split(orig, bits);

        BoolVRef[][] veStacks = new BoolVRef[breadth][orig.get().n + 1];
        for (int i = 0; i < breadth; i++) call(redStacks[i].stack(bits[i], veStacks[i]));

        BoolVRef[][] bitStacks = new BoolVRef[orig.get().n + 1][breadth];
        IntField.transpose(veStacks, bitStacks, orig.get().n + 1, breadth);

        for (int i = 0; i < breadth; i++) join(bitStacks[i], res[i]);
    }
}

class RedStack_Vf extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolVfRef orig, BoolVRef[] res); }
    RedStack_Vf(IntVfRef orig, IntVRef[] res, RedStack[] redStack) {
        int breadth = BoolFieldManager.getBreadthV();

        BoolVfRef[] bits = new BoolVfRef[breadth];
        for (int i = 0; i < breadth; i++) bits[i] = new BoolVfRef();
        split(orig, bits);

        BoolVRef[][] vfStacks = new BoolVRef[breadth][orig.get().n + 1];
        for (int i = 0; i < breadth; i++) call(redStack[i].stack(bits[i], vfStacks[i]));

        BoolVRef[][] bitStacks = new BoolVRef[orig.get().n + 1][breadth];
        IntField.transpose(vfStacks, bitStacks, orig.get().n + 1, breadth);

        for (int i = 0; i < breadth; i++) join(bitStacks[i], res[i]);
    }
}

class RedStack_Ev extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolEvRef orig, BoolERef[] res); }
    RedStack_Ev(IntEvRef orig, IntERef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolEvRef[] bits = new BoolEvRef[breadth];
        for (int i = 0; i < breadth; i++) bits[i] = new BoolEvRef();
        split(orig, bits);

        BoolERef[][] evStacks = new BoolERef[breadth][orig.get().n + 1];
        for (int i = 0; i < breadth; i++) { call(redStacks[i].stack(bits[i], evStacks[i])); }

        BoolERef[][] bitStacks = new BoolERef[orig.get().n + 1][breadth];
        IntField.transpose(evStacks, bitStacks, orig.get().n + 1, breadth);

        for (int i = 0; i < breadth; i++) {
            join(bitStacks[i], res[i]);
        }
    }
}

class RedStack_Ef extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolEfRef orig, BoolERef[] res); }
    RedStack_Ef(IntEfRef orig, IntERef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthE();

        BoolEfRef[] bits = new BoolEfRef[breadth];
        for (int i = 0; i < breadth; i++) bits[i] = new BoolEfRef();
        split(orig, bits);

        BoolERef[][] efStacks = new BoolERef[breadth][orig.get().n + 1];
        for (int i = 0; i < breadth; i++) { call(redStacks[i].stack(bits[i], efStacks[i])); }

        BoolERef[][] bitStacks = new BoolERef[orig.get().n + 1][breadth];
        IntField.transpose(efStacks, bitStacks, orig.get().n + 1, breadth);

        for (int i = 0; i < breadth; i++) {
            join(bitStacks[i], res[i]);
        }
    }
}

class RedStack_Fv extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolFvRef orig, BoolFRef[] res); }
    RedStack_Fv(IntFvRef orig, IntFRef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFvRef[] bits = new BoolFvRef[breadth];
        for (int i = 0; i < breadth; i++) bits[i] = new BoolFvRef();
        split(orig, bits);

        BoolFRef[][] fvStacks = new BoolFRef[breadth][orig.get().n + 1];
        for (int i = 0; i < breadth; i++) { call(redStacks[i].stack(bits[i], fvStacks[i])); }

        BoolFRef[][] bitStacks = new BoolFRef[orig.get().n + 1][breadth];
        IntField.transpose(fvStacks, bitStacks, orig.get().n + 1, breadth);

        for (int i = 0; i < breadth; i++) join(bitStacks[i], res[i]);
    }
}

class RedStack_Fe extends Procedure {
    @FunctionalInterface public interface RedStack { Instruction stack(BoolFeRef orig, BoolFRef[] res); }
    RedStack_Fe(IntFeRef orig, IntFRef[] res, RedStack[] redStacks) {
        int breadth = BoolFieldManager.getBreadthF();

        BoolFeRef[] bits = new BoolFeRef[breadth];
        for (int i = 0; i < breadth; i++) bits[i] = new BoolFeRef();
        split(orig, bits);

        BoolFRef[][] feStacks = new BoolFRef[breadth][orig.get().n + 1];
        for (int i = 0; i < breadth; i++) { call(redStacks[i].stack(bits[i], feStacks[i])); }

        BoolFRef[][] bitStacks = new BoolFRef[orig.get().n + 1][breadth];
        IntField.transpose(feStacks, bitStacks, orig.get().n + 1, breadth);

        for (int i = 0; i < breadth; i++) join(bitStacks[i], res[i]);
    }
}
