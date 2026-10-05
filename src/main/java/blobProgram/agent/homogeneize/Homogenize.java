package blobProgram.agent.homogeneize;

import blobProgram.DistanceField;
import blobProgram.QuasiParticle;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;
import blobProgram.agent.common.constraints.MaintainBlob;
import blobProgram.agent.common.constraints.MaintainQuasiParticle;
import blobProgram.agent.common.constraints.Exclude;
import blobProgram.agent.common.forces.Repulse;
import blobProgram.agent.common.constraints.SlowDown;
import blobProgram.agent.common.forces.Unblock;
import blobProgram.agent.voronoi.Voronoi;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.instruction.Procedure;
import ui.display.Styles;

/**
 * Homogenizes a set of quasi-particles by repeatedly moving them towards the center of their Voronoi cell.
 */
public class Homogenize extends Agent {
    private final Voronoi voronoi;

    private static final int nbits = 3;
    private final DistanceField distToVoronoi;
    private final IntVe gradient;

    public Homogenize(QuasiParticle seeds, Flip flip, Voronoi voronoi, DistanceField distToVoronoi, IntVe gradient) {
        super(seeds, flip);
        this.voronoi = voronoi;
        this.distToVoronoi = distToVoronoi;
        this.gradient = gradient;
    }

    public static Homogenize make(QuasiParticle particles) {
        Voronoi voronoi = Voronoi.make(particles);
        DistanceField distToVoronoi = new DistanceField(voronoi.state, nbits);
        IntVe gradient = new IntVe(nbits);

        Flip flip = new Flip()
                .addForce(new Repulse(particles, gradient).setPriority(2))
                .addForce(new Unblock(particles, gradient).setPriority(1))

                .addConstraint(new SlowDown(2, 2))
                .addConstraint(new Exclude(voronoi.state))
                .addConstraint(new MaintainQuasiParticle(particles))
                .addConstraint(new MaintainBlob(particles))
            ;

        return new Homogenize(particles, flip, voronoi, distToVoronoi, gradient);
    }

    private class Precompute extends Procedure {
        public Precompute() {
            call(voronoi.flip());
            show("Voronoi", voronoi.state, Styles.VORONOI);
            call(distToVoronoi.update());
            call(distToVoronoi.getGradient(gradient));

            IntV distance = new IntV(nbits);
            call(distToVoronoi.getDist(distance));
            show("Distance from Voronoi", distance);
            show("Gradient from Voronoi", gradient);
        }
    }
    @Override
    protected Procedure precompute() { return new Precompute(); }
}
