package blobProgram.agent.common.forces;

import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.fieldRef.boolField.BoolVRef;
import language.instruction.Procedure;

public class All extends Force {
    private class Compute extends Procedure {
        public Compute(BoolVRef yes, BoolVRef no) {
            set(new BoolVRef(BoolV.ones()), yes);
            set(new BoolVRef(BoolV.zeroes()), no);
        }
    }

    @Override
    protected Procedure compute(BoolVRef yes, BoolVRef no) {
        return new Compute(yes, no);
    }
}
