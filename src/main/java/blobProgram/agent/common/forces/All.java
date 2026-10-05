package blobProgram.agent.common.forces;

import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.instruction.Procedure;

/**
 * Causes movement everywhere.
 */
public class All extends Force {
    private class Compute extends Procedure {
        public Compute(BoolV yes, BoolV no) {
            set(new BoolV().ones(), yes);
            set(new BoolV().zeroes(), no);
        }
    }

    @Override
    protected Procedure compute(BoolV yes, BoolV no) {
        return new Compute(yes, no);
    }
}
