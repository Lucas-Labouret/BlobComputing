package blobProgram.agent.voronoi;

import blobProgram.BlobV;
import blobProgram.DistanceField;
import blobProgram.GabrielCenter;
import blobProgram.QuasiParticle;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;
import blobProgram.agent.common.constraints.Exclude;
import blobProgram.agent.common.constraints.MaintainBlob;
import blobProgram.agent.common.forces.IncludeGCenters;
import blobProgram.agent.common.forces.Repulse;
import blobProgram.agent.common.constraints.SlowDown;
import blobProgram.agent.common.forces.Vote;
import language.field.VoidF;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.instruction.Procedure;

/** Computes and maintain the discrete Voronoi diagram of a moving set of quasi-particles. */
public class Voronoi extends Agent {
    private static final int nbits = 3;
    private final DistanceField distToSeeds;
    private final IntVe gradient;

    private Voronoi(BlobV state, Flip flip, DistanceField distToSeeds, IntVe gradient) {
        super(state, flip);
        this.distToSeeds = distToSeeds;
        this.gradient = gradient;
    }

    public static Voronoi make(QuasiParticle sources) {
        BlobV state = new BlobV(new BoolV().not(sources));
        DistanceField distToSeeds = new DistanceField(sources, nbits);
        IntVe gradient = new IntVe(nbits);
        GabrielCenter gCenters = new GabrielCenter(distToSeeds);

        Flip flip = new Flip()
                .addForce(new IncludeGCenters(state, gCenters).setPriority(2))
                .addForce(new Repulse(state, gradient).setPriority(1))
                .addForce(new Vote(state).setPriority(0))

                .addConstraint(new SlowDown(3, 2))
                //.addConstraint(new SlowDown(state, gradient))
                .addConstraint(new Exclude(sources))
                .addConstraint(new MaintainBlob(state))
            ;

        return new Voronoi(state, flip, distToSeeds, gradient);
    }

    private class Precompute extends Procedure<VoidF> {
        public Precompute() {
            call(distToSeeds.update());
            set(call(distToSeeds.getGradient()), gradient);

            IntV distance = call(distToSeeds.getDist());
            show("Distance from Seeds", distance);
            show("Gradient from Seeds", gradient);

            returnValue(new VoidF());
        }
    }
    @Override
    protected Procedure<VoidF> precompute() { return new Precompute(); }
}
