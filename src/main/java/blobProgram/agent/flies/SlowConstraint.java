package blobProgram.agent.flies;

import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.fieldRef.boolField.BoolVRef;
import language.instruction.Procedure;

public class SlowConstraint extends Force {
    Force origin;
    BoolVRef slow = new BoolVRef(BoolV.zeroes());

    protected SlowConstraint(Force origin) {
        super(origin.priority);
        this.origin = origin;
    }

    private class Compute extends  Procedure {
        public Compute(BoolVRef yes, BoolVRef no) {
            call(origin.compute(yes, no));
            and(slow, yes, yes);
            and(slow, no, no);
            not(slow, slow);
        }
    }

    @Override
    protected Procedure _compute(BoolVRef yes, BoolVRef no) { return new Compute(yes, no); }
}
