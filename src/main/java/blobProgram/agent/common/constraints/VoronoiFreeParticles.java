package blobProgram.agent.common.constraints;

import blobProgram.BlobV;
import blobProgram.QuasiParticle;
import blobProgram.agent.Constraint;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.instruction.Procedure;

public class VoronoiFreeParticles extends Constraint {
    private final QuasiParticle particles;

    public VoronoiFreeParticles(BlobV state, QuasiParticle particles) {
        super(state);
        this.particles = particles;
    }

    private class Verify extends Procedure {
        public Verify(BoolV flip, IntV priority, IntV prioRand) {
            BlobV notParticles = tmp(new BlobV());
            not(particles, notParticles);
            and(notParticles, flip, flip);
        }
    }

    @Override
    public Procedure verify(BoolV flip, IntV priority, IntV prioRand) {
        return new Verify(flip, priority, prioRand);
    }
}
