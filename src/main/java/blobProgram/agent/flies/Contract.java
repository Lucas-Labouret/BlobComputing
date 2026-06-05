package blobProgram.agent.flies;

import blobProgram.QuasiParticle;
import blobProgram.agent.Force;
import language.field.intField.IntV;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.intField.IntVRef;
import language.instruction.Procedure;

public class Contract extends Force {
    private final QuasiParticle state;

    public Contract(QuasiParticle state) {
        super(new IntVRef(IntV.of(0, Force.prioRandBits)));
        this.state = state;
    }

    @Override
    protected Procedure _compute(BoolVRef target) { return null; }
}
