package blobProgram.agent.common.constraints;

import blobProgram.BlobV;
import blobProgram.QuasiParticle;
import blobProgram.agent.Constraint;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.intField.IntVRef;
import language.instruction.Procedure;

public class VoronoiFreeParticles extends Constraint {
    private final QuasiParticle particles;

    public VoronoiFreeParticles(BlobV state, QuasiParticle particles) {
        super(state);
        this.particles = particles;
    }

    private class Verify extends Procedure {
        public Verify(BoolVRef flip, IntVRef priority, IntVRef prioRand) {
            BlobV notParticles = tmp(new BlobV());
            not(particles, notParticles);
            and(notParticles, flip, flip);
        }
    }

    @Override
    public Procedure verify(BoolVRef flip, IntVRef priority, IntVRef prioRand) {
        return new Verify(flip, priority, prioRand);
    }
}
