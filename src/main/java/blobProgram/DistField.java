package blobProgram;

import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntE;
import language.field.intField.IntEv;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.IntERef;
import language.fieldRef.intField.IntEvRef;
import language.fieldRef.intField.IntVRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;

public class DistField {
    int nbits;

    private final BoolVRef sources_t1;
    private final BoolVRef sources_t0;

    private final IntVRef distField_t1;
    private final IntVRef distField_t0;

    private final IntVeRef gradient;

    private final IntVRef delta;

    private final IntVeRef deltaPlus1;
    private final IntVeRef minusDeltaMinus1;

    public DistField(BoolVRef sources, int nbits) {
        this.nbits = nbits;

        this.sources_t1 = sources.copy();
        this.sources_t0 = sources;

        this.distField_t1 = new IntVRef(IntV.of(0,  nbits));
        this.distField_t0 = new IntVRef(IntV.of(0,  nbits));

        this.gradient = new IntVeRef(new IntVe(nbits));

        this.delta = new IntVRef(IntV.maxValue(nbits));
        this.delta.get().getBits()[1].set(BoolV.zeroes());

        this.deltaPlus1 = new IntVeRef(IntVe.of(0, nbits));
        this.deltaPlus1.get().getBits()[1].set(BoolVe.ones());

        this.minusDeltaMinus1 = new IntVeRef(IntVe.of(0, nbits));
        this.minusDeltaMinus1.get().getBits()[0].set(BoolVe.ones());
        this.minusDeltaMinus1.get().getBits()[1].set(BoolVe.ones());
    }

    private class Mod extends Procedure {
        public Mod(IntVRef a) {
            IntVRef max = new IntVRef(IntV.maxValue(nbits));
            IntVRef min = new IntVRef(IntV.minValue(nbits));

            BoolVRef[] bits = a.get().getBits();
            BoolVRef sign = tmp(new BoolVRef());
            set(bits[0], sign);

            BoolVRef isMax =  tmp(new BoolVRef());
            eq(a, max, isMax);

            BoolVRef isMin =  tmp(new BoolVRef());
            eq(a, min, isMin);

            IntVRef aPlusMax = new IntVRef(new IntV(nbits));
            add(a, max, aPlusMax);

            fif(isMax, new IntVRef(IntV.of(0, nbits)), a, a);
            fif(isMin, new IntVRef(IntV.of(1, nbits)), a, a);

            BoolVRef negNotMin =  tmp(new BoolVRef());
            not(isMin, negNotMin);
            and(sign, negNotMin, negNotMin);

            fif(negNotMin, aPlusMax, a, a);
        }
    }

    private class Update extends Procedure {
        public Update() {
            //-- Compute N+(i) --
            BoolVeRef nPlus = tmp(new BoolVeRef());
            IntVRef deltaDist =  tmp(new IntVRef(new IntV(nbits)));
            sub(distField_t1, delta, deltaDist);
            call(new Mod(deltaDist));

            IntVeRef deltaDistVe = tmp(new IntVeRef(new IntVe(nbits)));
            broadcast(deltaDist, deltaDistVe);

            IntVeRef neighborDist = tmp(new IntVeRef(new IntVe(nbits)));
            broadcast(distField_t1, neighborDist);
            call(BlobV.send(neighborDist, neighborDist));
            gt(neighborDist, deltaDistVe, nPlus);

            set(distField_t0, distField_t1);

            //-- Compute updated distance, disregarding sources --
            BoolVRef nPlusNonEmpty = tmp(new BoolVRef());
            BoolVeRef nPlusNonEmptyVe = tmp(new BoolVeRef());
            redOr(nPlus, nPlusNonEmpty);
            broadcast(nPlusNonEmpty, nPlusNonEmptyVe);

            BoolVeRef notNPlus = tmp(new BoolVeRef());
            not(nPlus, notNPlus);

            BoolVeRef shouldBeMax = tmp(new BoolVeRef());
            and(notNPlus, nPlusNonEmptyVe,  shouldBeMax);

            fif(shouldBeMax, new IntVeRef(IntVe.maxValue(nbits)), neighborDist, neighborDist);
            redMin(neighborDist, distField_t0);

            add(distField_t0, new IntVRef(IntV.of(2, nbits)), distField_t0);
            call(new Mod(distField_t0));

            //-- Combine with sources --
            BoolVRef notS0 = tmp(new BoolVRef());
            BoolVRef s1notS0 = tmp(new BoolVRef());

            not(sources_t0, notS0);
            and(sources_t1, notS0, s1notS0);

            set(sources_t0, sources_t1);

            fif(sources_t0, new IntVRef(IntV.of(0, nbits)), distField_t0, distField_t0);
            fif(s1notS0, new IntVRef(IntV.of(1, nbits)), distField_t0, distField_t0);

            //-- Compute the gradient --
            IntVeRef fi = new IntVeRef(new IntVe(nbits));
            IntVeRef fj = new IntVeRef(new IntVe(nbits));

            broadcast(distField_t0, fi);
            call(BlobV.send(fi, fj));
            sub(fj, fi, gradient);

            IntVeRef rawGradient = new IntVeRef(new IntVe(nbits));
            set(gradient, rawGradient);

            BoolVeRef gradientBugPos =  tmp(new BoolVeRef());
            BoolVeRef gradientBugNeg = tmp(new BoolVeRef());
            gt(gradient, deltaPlus1, gradientBugPos);
            gt(minusDeltaMinus1, gradient, gradientBugNeg);

            IntVeRef correctedGradientPos = tmp(new IntVeRef(new IntVe(nbits)));
            IntVeRef correctedGradientNeg = tmp(new IntVeRef(new IntVe(nbits)));
            sub(gradient, new IntVeRef(IntVe.maxValue(nbits)), correctedGradientPos);
            add(gradient, new IntVeRef(IntVe.maxValue(nbits)), correctedGradientNeg);

            fif(gradientBugPos, correctedGradientPos, gradient, gradient);
            fif(gradientBugNeg, correctedGradientNeg, gradient, gradient);
        }
    }
    public Procedure update() { return new Update(); }

    private class GetDist extends Procedure {
        public GetDist(IntVRef distField) { set(distField_t0, distField); }
    }
    public Procedure getDist(IntVRef distField) { return new GetDist(distField); }

    private class GetGradient extends Procedure {
        public GetGradient(IntVeRef grad) { set(gradient, grad); }
    }
    public Procedure getGradient(IntVeRef grad) { return new GetGradient(grad); }
}
