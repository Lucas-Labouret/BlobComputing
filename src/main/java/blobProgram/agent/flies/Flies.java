package blobProgram.agent.flies;

import blobProgram.QuasiParticle;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;
import blobProgram.agent.common.constraints.MaintainBlob;
import blobProgram.agent.common.constraints.MaintainQuasiParticle;
import blobProgram.agent.common.forces.All;
import language.field.VoidF;
import language.instruction.Procedure;

/**
 * Randomly moves quasi-particles around the medium.
 */
public class Flies extends Agent {
    public Flies(QuasiParticle state) {
        super(
            state,
            new Flip()
                    .addForce(new All().setPriority(1))

                    .addConstraint(new MaintainQuasiParticle(state))
                    .addConstraint(new MaintainBlob(state))
        );
    }

    private static class Precompute extends Procedure<VoidF> {
        public Precompute() { returnValue(new VoidF()); }
    }
    @Override
    protected Procedure<VoidF> precompute() { return new Precompute(); }

    public static Flies rand() { return rand(0); }
    public static Flies rand(int sparsity) { return new Flies(QuasiParticle.random(sparsity)); }
}
