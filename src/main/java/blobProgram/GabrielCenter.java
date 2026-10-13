package blobProgram;

import language.field.VoidF;
import language.field.boolField.*;
import language.field.intField.IntE;
import language.field.intField.IntEv;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.instruction.Procedure;

public class GabrielCenter {
    private final int nbits;
    public final DistanceField distField;

    private final BoolV center = new BoolV();

    public GabrielCenter(BoolV seeds) {
        this.nbits = 3;
        this.distField = new DistanceField(seeds, nbits);
    }

    public GabrielCenter(DistanceField distField) {
        this.nbits = distField.nbits;
        this.distField = distField;
    }

    private class GetCenter extends Procedure<VoidF> { public GetCenter(BoolV out) { set(center, out); } }
    public Procedure<VoidF> getCenter(BoolV out) { return new GetCenter(out); }

    private class Update extends Procedure<VoidF> { public Update() { call(distField.update()); } }
    public Procedure<VoidF> update() { return new Update(); }

    ///////////////////////////////////////
    /// Detection through saddle points ///
    ///////////////////////////////////////

    private class SaddleV extends Procedure<BoolV> {
        public SaddleV() {
            IntVe gradient = call(distField.getGradient());

            BoolVe negGrad = gradient.getBits()[0];
            IntV components = call(BlobV.connectedComponents(negGrad, 4));

            returnValue(gt(components, IntV.of(2, 4)));
        }
    }
    public Procedure<BoolV> saddleV() { return new SaddleV(); }

    private class SaddleE extends Procedure<BoolE> {
        public SaddleE() {
            IntVe gradient = call(distField.getGradient());

            BoolVe negGrad = gradient.getBits()[0];
            BoolVe nulGrad = eq(gradient, IntVe.of(0, nbits));
            BoolVe posGrad = and(not(negGrad), not(nulGrad));

            BoolE flatEdges = redAnd(transfer(nulGrad));

            IntV negComponents = call(BlobV.connectedComponents(negGrad, 4));
            IntV nulComponents = call(BlobV.connectedComponents(nulGrad, 4));
            IntV posComponents = call(BlobV.connectedComponents(posGrad, 4));

            IntEv partialComponents = transfer(broadcastVe(negComponents));
            IntE componentSum = redAdd(partialComponents);

            IntE apexCount = redAdd(transfer(rotCW(rotCW(negGrad))), 2);

            BoolE oneApex = eq(apexCount, IntE.of(1, 2));
            BoolE twoApex = eq(apexCount, IntE.of(2, 2));

            BoolEv onePartialComponent = and(eq(partialComponents, IntEv.of(1, 4)), broadcastEv(twoApex));
            BoolE fullLoop = redAnd(onePartialComponent);

            IntE componentsM1 = sub(componentSum, IntE.of(1, 4));
            IntE componentsM2 = sub(componentSum, IntE.of(2, 4));

            BoolV oneNeg = eq(negComponents, IntV.of(1, 4));
            BoolV oneNul = eq(nulComponents, IntV.of(1, 4));
            BoolV noPos = eq(posComponents, IntV.of(0, 4));
            BoolV deadEnd = and(noPos, and(oneNeg, oneNul));

            BoolEf apexDeadEnd = rotCW(transfer(rotCW(rotCW(call(BlobV.send(broadcastVe(deadEnd)))))));
            set(and(apexDeadEnd, rotCCW(transfer(rotCCW(rotCCW(rotCCW(rotCCW(negGrad))))))), apexDeadEnd);
            set(and(apexDeadEnd, rotCW (transfer(rotCW (rotCW (rotCW (rotCW (negGrad))))))), apexDeadEnd);
            IntE apexDeadEndCount = redAdd(apexDeadEnd, 4);

            IntE componentsE = fif(oneApex, componentsM1, componentSum);
            set(fif(twoApex, componentsM2, componentsE), componentsE);
            set(fif(fullLoop, componentsM1, componentsE), componentsE);

            set(sub(componentsE, apexDeadEndCount), componentsE);

            returnValue(and(gt(componentsE, IntE.of(2, 4)), flatEdges));
        }
    }
    public Procedure<BoolE> saddleE() { return new SaddleE(); }

    private class Saddle extends Procedure<BoolV> {
        public Saddle() {
            BoolV saddleV = call(saddleV());
            BoolE saddleE = call(saddleE());

            returnValue(or(saddleV, redOr(transfer(broadcastEv(saddleE)))));
        }
    }
    public Procedure<BoolV> saddle() { return new Saddle(); }



    //////////////////////////////////
    /// Detection through flooding ///
    //////////////////////////////////

    private class FloodOnce extends Procedure<BoolV> {
        public FloodOnce(BoolV in) {
            IntVe grad = call(distField.getGradient());
            BoolVe gradOk = gt(grad, IntVe.of(0, nbits));
            returnValue(redOr(call(BlobV.send(and(broadcastVe(in), gradOk)))));
        }
    }
    private Procedure<BoolV> floodOnce(BoolV in) { return new FloodOnce(in); }

    private static class ForAll extends Procedure<BoolV> {
        public ForAll(BoolV in) {
            returnValue(redAnd(call(BlobV.send(broadcastVe(in)))));
        }
    }
    private Procedure<BoolV> forAll(BoolV in) { return new ForAll(in); }

    private class Flood extends Procedure<VoidF> {
        public Flood() {
            int mod = Math.powExact(2, nbits)-1;
            set(new BoolV().zeroes(), center);

            IntV dist = call(distField.getDist());

            BoolV flood = tmp(new BoolV());
            BoolE centerE = tmp(new BoolE());
            
            for (int i=0; i<mod; i++) {
                set(eq(dist, IntV.of(i, nbits)), flood);

                for (int j=0; j<mod-2; j++) set(call(floodOnce(flood)), flood);
                for (int j=0; j<mod-3; j++) set(call(forAll(flood)), flood);

                set(or(flood, center), center);

                set(call(forAll(flood)), flood);
                set(redAnd(transfer(broadcastVe(flood))), centerE);

                set(or(redOr(transfer(broadcastEv(centerE))), center), center);
            }

            returnValue(new VoidF());
        }
    }
    public Procedure<VoidF> flood() { return new Flood(); }


}
