package blobProgram.agent.voronoi;

import blobProgram.BlobV;
import blobProgram.DistField;
import blobProgram.GabrielCenter;
import blobProgram.QuasiParticle;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;
import blobProgram.agent.common.constraints.MaintainBlob;
import blobProgram.agent.common.constraints.VoronoiFreeParticles;
import blobProgram.agent.common.forces.IncludeGCenters;
import blobProgram.agent.common.forces.Repulse;
import blobProgram.agent.common.forces.SlowDown;
import blobProgram.agent.common.forces.Vote;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.intField.IntVRef;
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
        GabrielCenter gCenters = new GabrielCenter(distToSeeds);

        Flip flip = new Flip()
                .addForce(new SlowDown(4, 3).setPriority(3))
                .addForce(new IncludeGCenters(state, gCenters).setPriority(2))
                .addForce(new Repulse(state, gradient).setPriority(1))
                .addForce(new Vote(state).setPriority(0))

                .addConstraint(new VoronoiFreeParticles(state, sources))
                .addConstraint(new MaintainBlob(state))
            ;

        return new Voronoi(state, flip, distToSeeds, gradient);
    }

    private class Precompute extends Procedure {
        public Precompute() {
            call(distToSeeds.update());
            call(distToSeeds.getGradient(gradient));
            IntVRef distance = new IntVRef(new IntV(nbits));
            call(distToSeeds.getDist(distance));
            show("Distance from Seeds", distance);
            show("Gradient from Seeds", gradient);
        }
    }
    @Override
    protected Procedure precompute() { return new Precompute(); }
}
