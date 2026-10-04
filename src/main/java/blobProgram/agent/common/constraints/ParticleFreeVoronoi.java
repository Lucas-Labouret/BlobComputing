package blobProgram.agent.common.constraints;

import blobProgram.BlobV;
import blobProgram.agent.Constraint;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.instruction.Procedure;

public class ParticleFreeVoronoi extends Constraint {
    BlobV voronoi;

    public ParticleFreeVoronoi(BlobV state, BlobV voronoi) {
        super(state);
        this.voronoi = voronoi;
    }

    private class Verify extends Procedure {
        public Verify(BoolV flip, IntV priority, IntV prioRand) {
            BlobV notVoronoi = tmp(new BlobV());
            not(voronoi, notVoronoi);
            and(notVoronoi, flip, flip);
        }
    }

    public Procedure verify(BoolV flip, IntV priority, IntV prioRand) {
        return new Verify(flip, priority, prioRand);
    }
}
