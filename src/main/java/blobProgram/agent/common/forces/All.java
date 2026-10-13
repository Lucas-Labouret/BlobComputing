package blobProgram.agent.common.forces;

import blobProgram.agent.Force;
import language.field.VoidF;
import language.field.boolField.BoolV;
import language.instruction.Procedure;

/** Causes movement everywhere. */
public class All extends Force {
    private static class Compute extends Procedure<VoidF> {
        public Compute(BoolV yes, BoolV no) {
            set(new BoolV().ones(), yes);
            set(new BoolV().zeroes(), no);
            returnValue(new VoidF());
        }
    }

    @Override
    protected Procedure<VoidF> compute(BoolV yes, BoolV no) {
        return new Compute(yes, no);
    }
}
