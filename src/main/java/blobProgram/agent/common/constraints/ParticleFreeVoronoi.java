package blobProgram.agent.common.constraints;

import blobProgram.BlobV;
import blobProgram.agent.Constraint;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.intField.IntVRef;
import language.instruction.Procedure;

public class ParticleFreeVoronoi extends Constraint {
    BlobV voronoi;

    public ParticleFreeVoronoi(BlobV state, BlobV voronoi) {
        super(state);
        this.voronoi = voronoi;
    }

    private class Verify extends Procedure {
        public Verify(BoolVRef flip, IntVRef priority, IntVRef prioRand) {
            BlobV notVoronoi = tmp(new BlobV());
            not(voronoi, notVoronoi);
            and(notVoronoi, flip, flip);
        }
    }

    public Procedure verify(BoolVRef flip, IntVRef priority, IntVRef prioRand) {
        return new Verify(flip, priority, prioRand);
    }
}
