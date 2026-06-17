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
        public Compute(BoolVRef target) {
            call(origin.compute(target));
            and(slow, target, target);
            not(slow, slow);
        }
    }

    @Override
    protected Procedure _compute(BoolVRef target) { return new Compute(target); }
}
