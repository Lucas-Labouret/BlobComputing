package blobProgram.agent.voronoi;

import blobProgram.BlobV;
import blobProgram.DistField;
import blobProgram.QuasiParticle;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;
import language.field.boolField.BoolV;
import language.field.intField.IntVe;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;

public class Voronoi extends Agent {
    private static final int nbits = 3;
    private final DistField distToSeeds;
    private final IntVeRef gradient;

    private Voronoi(BlobV state, Flip flip, DistField distToSeeds, IntVeRef gradient) {
        super(state, flip);
        this.distToSeeds = distToSeeds;
        this.gradient = gradient;
    }

    public static Voronoi make(QuasiParticle sources) {
        BlobV state = new BlobV(BoolV.not(sources.get()));
        DistField distToSeeds = new DistField(sources, nbits);
        IntVeRef gradient = new IntVeRef(new IntVe(nbits));

        Flip flip = new Flip()
                .addForce(new SlowDown(2, 1).setPriority(3))
                .addForce(new MaintainBlob(state)    .setPriority(2))
                .addForce(new Shrink(state, gradient).setPriority(1))
                .addForce(new Expand(state, gradient).setPriority(0))
            ;

        return new Voronoi(state, flip, distToSeeds, gradient);
    }

    private class Precompute extends Procedure {
        public Precompute() {
            call(distToSeeds.update());
            call(distToSeeds.getGradient(gradient));
            show("Gradient from Seeds", gradient);
        }
    }
    @Override
    protected Procedure precompute() { return new Precompute(); }
}
