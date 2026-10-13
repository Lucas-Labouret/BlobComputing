package blobProgram;

import language.field.VoidF;
import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.instruction.Procedure;

/**
 * A distance field computes the distance to the nearest moving source modulo 2^nbits.
 * <p>
 * The distance field has two components :<br>
 * - The distance field itself, which is an IntV of size nbits and stores the distance directly.<br>
 * - The gradient of the distance field, which is an IntVe of size nbits
 * and stores the difference between the distance of a cell and its neighbors.
 */
public class DistanceField {
    public final int nbits;

    private final BoolV sources_t1;
    private final BoolV sources_t0;

    private final IntV distField;

    private final IntVe gradient;

    private final IntV min;
    private final IntV delta;

    private final IntVe minVe;
    private final IntVe deltaVe;
    private final IntVe negDeltaVe;

    public DistanceField(BoolV sources, int nbits) {
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

    private class Update extends Procedure<VoidF> {
        public Update() {
            BoolV oldSources = and(sources_t0, sources_t1);
            BoolV newSources = and(not(oldSources), sources_t0);

            // Compute N+(i)
            IntV deltaDist = sub(distField, delta);
            set(new BoolV().zeroes(), deltaDist.getBits()[0]); // Does modulo 2^nbits

            IntVe neighborDist = call(BlobV.send(broadcastVe(distField)));

            BoolVe nPlus = gt(neighborDist, broadcastVe(deltaDist));

            // Compute updated distance, disregarding sources
            BoolVe nPlusNonEmptyVe = broadcastVe(redOr(nPlus));
            BoolVe shouldBeMax = and(not(nPlus), nPlusNonEmptyVe);

            set(fif(shouldBeMax, IntVe.maxValue(nbits), neighborDist), neighborDist);
            set(redMin(neighborDist), distField);

            set(add(distField, IntV.of(2, nbits)), distField);

            IntV distFieldBis = add(distField, min);
            set(fif(gt(distField, IntV.of(0, nbits)), distField, distFieldBis), distField);

            // Combine with sources
            set(fif(oldSources, IntV.of(0, nbits), distField), distField);
            set(fif(newSources, IntV.of(1, nbits), distField), distField);

            // Compute the gradient
            IntVe fi = broadcastVe(distField);
            IntVe fj = call(BlobV.send(fi));

            set(sub(fj, fi), gradient);

            BoolVe gradientBugPos = gt(gradient, deltaVe);
            BoolVe gradientBugNeg = gt(negDeltaVe, gradient);

            IntVe correctedGradientPos = sub(gradient, minVe);
            IntVe correctedGradientNeg = add(gradient, minVe);

            set(fif(gradientBugPos, correctedGradientPos, gradient), gradient);
            set(fif(gradientBugNeg, correctedGradientNeg, gradient), gradient);

            // Update sources for next iteration
            set(sources_t0, sources_t1);

            returnValue(new VoidF());
        }
    }
    public Procedure<VoidF> update() { return new Update(); }

    private class GetDist extends Procedure<IntV> {
        public GetDist() {
            IntV result = new IntV(nbits);
            set(distField, result);
            returnValue(result);
        }
    }
    public Procedure<IntV> getDist() { return new GetDist(); }

    private class GetGradient extends Procedure<IntVe> {
        public GetGradient() {
            IntVe result = new IntVe(nbits);
            set(gradient, result);
            returnValue(result);
        }
    }
    public Procedure<IntVe> getGradient() { return new GetGradient(); }
}
