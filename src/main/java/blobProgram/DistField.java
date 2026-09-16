package blobProgram;

import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.IntVRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;

public class DistField {
    int nbits;

    private final BoolVRef sources_t1;
    private final BoolVRef sources_t0;

    private final IntVRef distField;

    private final IntVeRef gradient;

    private final IntVRef min;
    private final IntVRef delta;

    private final IntVeRef minVe;
    private final IntVeRef deltaVe;
    private final IntVeRef negDeltaVe;

    public DistField(BoolVRef sources, int nbits) {
        this.nbits = nbits;

        this.sources_t0 = sources;
        this.sources_t1 = sources.copy();

        this.distField = new IntVRef(IntV.of(0,  nbits));
        this.gradient = new IntVeRef(IntVe.of(0,  nbits));

        this.min = new IntVRef(IntV.minValue(nbits));
        this.delta = new IntVRef(IntV.of(0,  nbits));
        this.delta.get().getBits()[1].set(BoolV.ones());

        this.minVe = new IntVeRef(IntVe.minValue(nbits));
        this.deltaVe = new IntVeRef(IntVe.of(0,  nbits));
        this.deltaVe.get().getBits()[1].set(BoolVe.ones());
        this.negDeltaVe = new IntVeRef(IntVe.of(0,  nbits));
        this.negDeltaVe.get().getBits()[0].set(BoolVe.ones());
        this.negDeltaVe.get().getBits()[1].set(BoolVe.ones());
    }

    private class Update extends Procedure {
        public Update() {
            BoolVRef oldSources = tmp(new BoolVRef());
            BoolVRef newSources = tmp(new BoolVRef());
            BoolVRef notSources = tmp(new BoolVRef());

            and(sources_t0, sources_t1, oldSources);

            not(oldSources, newSources);
            and(newSources, sources_t0, newSources);

            or(oldSources, newSources, notSources);
            not(notSources, notSources);

            // Compute N+(i)
            BoolVeRef nPlus = tmp(new BoolVeRef());
            IntVRef deltaDist =  tmp(new IntVRef(new IntV(nbits)));
            sub(distField, delta, deltaDist);
            set(new BoolVRef(BoolV.zeroes()), deltaDist.get().getBits()[0]); // Does modulo 2^nbits

            IntVeRef deltaDistVe = tmp(new IntVeRef(new IntVe(nbits)));
            broadcast(deltaDist, deltaDistVe);

            IntVeRef neighborDist = tmp(new IntVeRef(new IntVe(nbits)));
            broadcast(distField, neighborDist);
            call(BlobV.send(neighborDist, neighborDist));
            gt(neighborDist, deltaDistVe, nPlus);

            // Compute updated distance, disregarding sources
            BoolVRef nPlusNonEmpty = tmp(new BoolVRef());
            BoolVeRef nPlusNonEmptyVe = tmp(new BoolVeRef());
            redOr(nPlus, nPlusNonEmpty);
            broadcast(nPlusNonEmpty, nPlusNonEmptyVe);

            BoolVeRef notNPlus = tmp(new BoolVeRef());
            not(nPlus, notNPlus);

            BoolVeRef shouldBeMax = tmp(new BoolVeRef());
            and(notNPlus, nPlusNonEmptyVe,  shouldBeMax);

            fif(shouldBeMax, new IntVeRef(IntVe.maxValue(nbits)), neighborDist, neighborDist);
            redMin(neighborDist, distField);

            add(distField, new IntVRef(IntV.of(2, nbits)), distField);
            set(new BoolVRef(BoolV.zeroes()), deltaDist.get().getBits()[0]);

            IntVRef distFieldBis = new IntVRef(new IntV(nbits));
            add(distField, min, distFieldBis);

            BoolVRef posDist = new BoolVRef();
            gt(distField, new IntVRef(IntV.of(0, nbits)), posDist);
            fif(posDist, distField, distFieldBis, distField);

            // Combine with sources
            fif(oldSources, new IntVRef(IntV.of(0, nbits)), distField, distField);
            fif(newSources, new IntVRef(IntV.of(1, nbits)), distField, distField);

            // Compute the gradient
            IntVeRef fi = tmp(new IntVeRef(new IntVe(nbits)));
            IntVeRef fj = tmp(new IntVeRef(new IntVe(nbits)));

            broadcast(distField, fi);
            call(BlobV.send(fi, fj));
            sub(fj, fi, gradient);

            BoolVeRef gradientBugPos =  tmp(new BoolVeRef());
            BoolVeRef gradientBugNeg = tmp(new BoolVeRef());
            gt(gradient, deltaVe, gradientBugPos);
            gt(negDeltaVe, gradient, gradientBugNeg);

            IntVeRef correctedGradientPos = tmp(new IntVeRef(new IntVe(nbits)));
            IntVeRef correctedGradientNeg = tmp(new IntVeRef(new IntVe(nbits)));
            sub(gradient, minVe, correctedGradientPos);
            add(gradient, minVe, correctedGradientNeg);

            fif(gradientBugPos, correctedGradientPos, gradient, gradient);
            fif(gradientBugNeg, correctedGradientNeg, gradient, gradient);

            // Update sources for next iteration
            set(sources_t0, sources_t1);
        }
    }
    public Procedure update() { return new Update(); }

    private class GetDist extends Procedure {
        public GetDist(IntVRef distField) { set(DistField.this.distField, distField); }
    }
    public Procedure getDist(IntVRef distField) { return new GetDist(distField); }

    private class GetGradient extends Procedure {
        public GetGradient(IntVeRef grad) { set(gradient, grad); }
    }
    public Procedure getGradient(IntVeRef grad) { return new GetGradient(grad); }
}
