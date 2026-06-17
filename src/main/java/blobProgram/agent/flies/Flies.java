package blobProgram.agent.flies;

import blobProgram.QuasiParticle;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;

public class Flies extends Agent {
    public Flies(QuasiParticle state) {
        super(
            state,
            new Flip()
                .addYes(new SlowConstraint(new Expand(state)))
                .addYes(new SlowConstraint(new Contract(state)))
        );
    }

    public static Flies rand() { return rand(0); }
    public static Flies rand(int sparsity) { return new Flies(QuasiParticle.rand(sparsity)); }
}
