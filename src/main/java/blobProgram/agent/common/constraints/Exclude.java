package blobProgram.agent.common.constraints;

import blobProgram.BlobV;
import blobProgram.agent.Constraint;
import language.field.VoidF;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.instruction.Procedure;

/** Exclude movement from a specific BlobV. */
public class Exclude extends Constraint {
    private final BlobV from;

    public Exclude(BlobV from) {
        this.from = from;
    }

    private class Verify extends Procedure<VoidF> {
        public Verify(BoolV flip, IntV priority, IntV prioRand) {
            set(and(not(from), flip), flip);
            returnValue(new VoidF());
        }
    }

    public Procedure<VoidF> verify(BoolV flip, IntV priority, IntV prioRand) {
        return new Verify(flip, priority, prioRand);
    }
}
