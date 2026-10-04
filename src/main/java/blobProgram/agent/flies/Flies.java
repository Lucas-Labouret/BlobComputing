package blobProgram.agent.flies;

import blobProgram.QuasiParticle;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;
import blobProgram.agent.common.constraints.MaintainBlob;
import blobProgram.agent.common.constraints.MaintainQuasiParticle;
import blobProgram.agent.common.forces.All;
import blobProgram.agent.common.forces.SlowDown;

public class Flies extends Agent {
    public Flies(QuasiParticle state) {
        super(
            state,
            new Flip()
                    .addForce(new SlowDown(2, 2).setPriority(2))
                    .addForce(new All().setPriority(1))

                    .addConstraint(new MaintainQuasiParticle(state))
                    .addConstraint(new MaintainBlob(state))
        );
    }

    public static Flies rand() { return rand(0); }
    public static Flies rand(int sparsity) { return new Flies(QuasiParticle.random(sparsity)); }
}
