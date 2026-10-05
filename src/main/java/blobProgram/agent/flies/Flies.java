package blobProgram.agent.flies;

import blobProgram.QuasiParticle;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;
import blobProgram.agent.common.constraints.MaintainBlob;
import blobProgram.agent.common.constraints.MaintainQuasiParticle;
import blobProgram.agent.common.forces.All;
import blobProgram.agent.common.constraints.SlowDown;

/**
 * Randomly moves quasi-particles around the medium.
 */
public class Flies extends Agent {
    public Flies(QuasiParticle state) {
        super(
            state,
            new Flip()
                    .addForce(new All().setPriority(1))

                    .addConstraint(new SlowDown(2, 2))
                    .addConstraint(new MaintainQuasiParticle(state))
                    .addConstraint(new MaintainBlob(state))
        );
    }

    public static Flies rand() { return rand(0); }
    public static Flies rand(int sparsity) { return new Flies(QuasiParticle.random(sparsity)); }
}
