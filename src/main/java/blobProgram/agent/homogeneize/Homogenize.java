package blobProgram.agent.homogeneize;

import blobProgram.DistField;
import blobProgram.QuasiParticle;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;
import blobProgram.agent.voronoi.Voronoi;
import language.field.intField.IntVe;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;

public class Homogenize extends Agent {
    private final Voronoi voronoi;

    private static final int nbits = 3;
    private final DistField distToVoronoi;
    private final IntVeRef gradient;

    public Homogenize(QuasiParticle seeds, Flip flip, Voronoi voronoi, DistField distToVoronoi, IntVeRef gradient) {
        super(seeds, flip);
        this.voronoi = voronoi;
        this.distToVoronoi = distToVoronoi;
        this.gradient = gradient;
    }

    public static Homogenize make(QuasiParticle seeds) {
        Voronoi voronoi = Voronoi.make(seeds);
        DistField distToVoronoi = new DistField(voronoi.state, nbits);
        IntVeRef gradient = new IntVeRef(new IntVe(nbits));

        Flip flip = new Flip()
                .addForce(new Shrink(seeds, gradient).setPriority(1))
                .addForce(new Expand(seeds, gradient).setPriority(0))
            ;

        return new Homogenize(seeds, flip, voronoi, distToVoronoi, gradient);
    }

    private class Precompute extends Procedure {
        public Precompute() {
            call(voronoi.flip());
            show("Voronoi", voronoi.state);
            call(distToVoronoi.update());
            call(distToVoronoi.getGradient(gradient));
            show("Gradient from Voronoi", gradient);
        }
    }
    @Override
    protected Procedure precompute() { return new Precompute(); }
}
