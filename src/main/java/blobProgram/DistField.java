package blobProgram;

import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.instruction.Procedure;

public class DistField {
    int nbits;

    private final BoolV sources_t1;
    private final BoolV sources_t0;

    private final IntV distField;

    private final IntVe gradient;

    private final IntV min;
    private final IntV delta;

    private final IntVe minVe;
    private final IntVe deltaVe;
    private final IntVe negDeltaVe;

    public DistField(BoolV sources, int nbits) {
        this.nbits = nbits;

        this.sources_t0 = sources;
        this.sources_t1 = sources.copy();

        this.distField = IntV.of(0,  nbits);
        this.gradient = IntVe.of(0,  nbits);

        this.min = IntV.minValue(nbits);
        this.delta = IntV.of(0,  nbits);
        this.delta.getBits()[1].ones();

        this.minVe = IntVe.minValue(nbits);
        this.deltaVe = IntVe.of(0,  nbits);
        this.deltaVe.getBits()[1].ones();
        this.negDeltaVe = IntVe.of(0,  nbits);
        this.negDeltaVe.getBits()[0].ones();
        this.negDeltaVe.getBits()[1].ones();
    }

    private class Update extends Procedure {
        public Update() {
            BoolV oldSources = tmp(new BoolV());
            BoolV newSources = tmp(new BoolV());

            and(sources_t0, sources_t1, oldSources);

            not(oldSources, newSources);
            and(newSources, sources_t0, newSources);

            // Compute N+(i)
            BoolVe nPlus = tmp(new BoolVe());
            IntV deltaDist =  tmp(new IntV(nbits));
            sub(distField, delta, deltaDist);
            set(new BoolV().zeroes(), deltaDist.getBits()[0]); // Does modulo 2^nbits

            IntVe deltaDistVe = tmp(new IntVe(nbits));
            broadcast(deltaDist, deltaDistVe);

            IntVe neighborDist = tmp(new IntVe(nbits));
            broadcast(distField, neighborDist);
            call(BlobV.send(neighborDist, neighborDist));
            gt(neighborDist, deltaDistVe, nPlus);

            // Compute updated distance, disregarding sources
            BoolV nPlusNonEmpty = tmp(new BoolV());
            BoolVe nPlusNonEmptyVe = tmp(new BoolVe());
            redOr(nPlus, nPlusNonEmpty);
            broadcast(nPlusNonEmpty, nPlusNonEmptyVe);

            BoolVe notNPlus = tmp(new BoolVe());
            not(nPlus, notNPlus);

            BoolVe shouldBeMax = tmp(new BoolVe());
            and(notNPlus, nPlusNonEmptyVe,  shouldBeMax);

            fif(shouldBeMax, IntVe.maxValue(nbits), neighborDist, neighborDist);
            redMin(neighborDist, distField);

            add(distField, IntV.of(2, nbits), distField);
            set(new BoolV().zeroes(), deltaDist.getBits()[0]);

            IntV distFieldBis = new IntV(nbits);
            add(distField, min, distFieldBis);

            BoolV posDist = new BoolV();
            gt(distField, IntV.of(0, nbits), posDist);
            fif(posDist, distField, distFieldBis, distField);

            // Combine with sources
            fif(oldSources, IntV.of(0, nbits), distField, distField);
            fif(newSources, IntV.of(1, nbits), distField, distField);

            // Compute the gradient
            IntVe fi = tmp(new IntVe(nbits));
            IntVe fj = tmp(new IntVe(nbits));

            broadcast(distField, fi);
            call(BlobV.send(fi, fj));
            sub(fj, fi, gradient);

            BoolVe gradientBugPos =  tmp(new BoolVe());
            BoolVe gradientBugNeg = tmp(new BoolVe());
            gt(gradient, deltaVe, gradientBugPos);
            gt(negDeltaVe, gradient, gradientBugNeg);

            IntVe correctedGradientPos = tmp(new IntVe(nbits));
            IntVe correctedGradientNeg = tmp(new IntVe(nbits));
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
        public GetDist(IntV distField) { set(DistField.this.distField, distField); }
    }
    public Procedure getDist(IntV distField) { return new GetDist(distField); }

    private class GetGradient extends Procedure {
        public GetGradient(IntVe grad) { set(gradient, grad); }
    }
    public Procedure getGradient(IntVe grad) { return new GetGradient(grad); }
}
