package blobProgram.agent.homogeneize;

import blobProgram.DistField;
import blobProgram.QuasiParticle;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;
import blobProgram.agent.common.constraints.MaintainBlob;
import blobProgram.agent.common.constraints.MaintainQuasiParticle;
import blobProgram.agent.common.constraints.ParticleFreeVoronoi;
import blobProgram.agent.common.forces.All;
import blobProgram.agent.common.forces.Unblock;
import blobProgram.agent.common.forces.Repulse;
import blobProgram.agent.common.forces.SlowDown;
import blobProgram.agent.voronoi.Voronoi;
import javafx.scene.paint.Color;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.fieldRef.intField.IntVRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;
import ui.display.Styles;

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

    public static Homogenize make(QuasiParticle particles) {
        Voronoi voronoi = Voronoi.make(particles);
        DistField distToVoronoi = new DistField(voronoi.state, nbits);
        IntVeRef gradient = new IntVeRef(new IntVe(nbits));

        Flip flip = new Flip()
                .addForce(new SlowDown(2, 3).setPriority(3))
                .addForce(new Repulse(particles, gradient).setPriority(2))
                .addForce(new Unblock(particles, gradient).setPriority(1))

                .addConstraint(new ParticleFreeVoronoi(particles, voronoi.state))
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

            IntVRef distance = new IntVRef(new IntV(nbits));
            call(distToVoronoi.getDist(distance));
            show("Distance from Voronoi", distance);
            show("Gradient from Voronoi", gradient);
        }
    }
    @Override
    protected Procedure precompute() { return new Precompute(); }
}
