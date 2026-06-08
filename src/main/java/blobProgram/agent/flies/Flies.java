package blobProgram.agent.flies;

import blobProgram.QuasiParticle;
import blobProgram.Rand;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;

public class Flies extends Agent {
    private static final Rand rand = new Rand();

    public Flies(QuasiParticle state) {
        super(
            state,
            new Flip()
                .addYes(new Expand(state))
                .addYes(new Contract(state))
        );
    }

    public static Flies rand() { return rand(0); }
    public static Flies rand(int sparsity) { return new Flies(QuasiParticle.rand(sparsity)); }
}
