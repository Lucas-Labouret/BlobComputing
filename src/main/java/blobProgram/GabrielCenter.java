package blobProgram;

import language.field.boolField.*;
import language.field.intField.IntE;
import language.field.intField.IntEv;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.instruction.Procedure;

public class GabrielCenter {
    private final int nbits;
    public final DistField distField;

    private final BoolV center = new BoolV();
    private class GetCenter extends Procedure { public GetCenter(BoolV out) { set(center, out); } }
    public Procedure getCenter(BoolV out) { return new GetCenter(out); }

    public GabrielCenter(BoolV seeds) {
        this.nbits = 3;
        this.distField = new DistField(seeds, nbits);
    }

    public GabrielCenter(DistField distField) {
        this.nbits = distField.nbits;
        this.distField = distField;
    }

    private class Update extends Procedure { public Update() { call(distField.update()); } }
    public Procedure update() { return new Update(); }

    ///////////////////////////////////////
    /// Detection through saddle points ///
    ///////////////////////////////////////

    private class SaddleV extends Procedure {
        public SaddleV(BoolV saddleV) {
            IntVe gradient = new IntVe(nbits);
            call(distField.getGradient(gradient));

            BoolVe negGrad = gradient.getBits()[0];
            IntV components = new IntV(nbits);
            call(BlobV.connectedComponents(negGrad, components));

            gt(components, IntV.of(2, nbits), saddleV);
        }
    }
    public Procedure saddleV(BoolV saddleV) { return new SaddleV(saddleV); }

    private class SaddleE extends Procedure {
        public SaddleE(BoolE saddleE) {
            IntVe gradient = new IntVe(nbits);
            call(distField.getGradient(gradient));

            BoolEv evB = tmp(new BoolEv());
            BoolVe veB = tmp(new BoolVe());
            BoolVf vfB = tmp(new BoolVf());
            BoolEf efB = tmp(new BoolEf());
            IntVe veI4 = tmp(new IntVe(4));

            BoolVe negGrad = gradient.getBits()[0];

            BoolVe nulGrad = tmp(new BoolVe());
            eq(gradient, IntVe.of(0, nbits), nulGrad);

            BoolVe posGrad = tmp(new BoolVe());
            BoolVe notNeg = tmp(new BoolVe());
            BoolVe notNul = tmp(new BoolVe());
            not(negGrad, notNeg);
            not(nulGrad, notNul);
            and(notNeg, notNul, posGrad);

            BoolE flatEdges = tmp(new BoolE());
            transfer(nulGrad, evB);
            redAnd(evB, flatEdges);

            IntV negComponents = tmp(new IntV(4));
            IntV nulComponents = tmp(new IntV(4));
            IntV posComponents = tmp(new IntV(4));
            call(BlobV.connectedComponents(negGrad, negComponents));
            call(BlobV.connectedComponents(nulGrad, nulComponents));
            call(BlobV.connectedComponents(posGrad, posComponents));

            IntEv partialComponents = tmp(new IntEv(4));
            IntE componentSum = tmp(new IntE(4));
            broadcast(negComponents, veI4);
            transfer(veI4, partialComponents);
            redAdd(partialComponents, componentSum);

            IntE apexCount = tmp(new IntE(2));
            rotCW(negGrad, vfB);
            rotCW(vfB, veB);
            transfer(veB, evB);
            redAdd(evB, apexCount);

            BoolE oneApex = tmp(new BoolE());
            BoolE twoApex = tmp(new BoolE());
            eq(apexCount, IntE.of(1, 2), oneApex);
            eq(apexCount, IntE.of(2, 2), twoApex);

            BoolE fullLoop = tmp(new BoolE());
            BoolEv onePartialComponent = tmp(new BoolEv());
            eq(partialComponents, IntEv.of(1, 4), onePartialComponent);
            broadcast(twoApex, evB);
            and(onePartialComponent, evB, onePartialComponent);
            redAnd(onePartialComponent, fullLoop);

            IntE componentsM1 = tmp(new IntE(4));
            IntE componentsM2 = tmp(new IntE(4));
            sub(componentSum, IntE.of(1, 4), componentsM1);
            sub(componentSum, IntE.of(2, 4), componentsM2);

            BoolV deadEnd = tmp(new BoolV());
            BoolV oneNeg = tmp(new BoolV());
            BoolV oneNul = tmp(new BoolV());
            BoolV noPos = tmp(new BoolV());
            eq(negComponents, IntV.of(1, 4), oneNeg);
            eq(nulComponents, IntV.of(1, 4), oneNul);
            eq(posComponents, IntV.of(0, 4), noPos);
            and(oneNeg, oneNul, deadEnd);
            and(deadEnd, noPos, deadEnd);

            BoolEf apexDeadEnd = tmp(new BoolEf());
            broadcast(deadEnd, veB);
            call(BlobV.send(veB, veB));
            rotCW(veB, vfB);
            rotCW(vfB, veB);
            transfer(veB, evB);
            rotCW(evB, apexDeadEnd);

            rotCCW(negGrad, vfB);
            rotCCW(vfB, veB);
            rotCCW(veB, vfB);
            rotCCW(vfB, veB);
            transfer(veB, evB);
            rotCCW(evB, efB);
            and(apexDeadEnd, efB, apexDeadEnd);

            rotCW(negGrad, vfB);
            rotCW(vfB, veB);
            rotCW(veB, vfB);
            rotCW(vfB, veB);
            transfer(veB, evB);
            rotCW(evB, efB);
            and(apexDeadEnd, efB, apexDeadEnd);

            IntE apexDeadEndCount = tmp(new IntE(4));
            redAdd(apexDeadEnd, apexDeadEndCount);

            IntE componentsE = tmp(new IntE(4));
            fif(oneApex, componentsM1, componentSum, componentsE);
            fif(twoApex, componentsM2, componentsE, componentsE);
            fif(fullLoop, componentsM1, componentsE, componentsE);

            sub(componentsE, apexDeadEndCount, componentsE);

            gt(componentsE, IntE.of(2, 4), saddleE);
            and(saddleE, flatEdges, saddleE);
        }
    }
    public Procedure saddleE(BoolE saddleE) { return new SaddleE(saddleE); }

    private class Saddle extends Procedure {
        public Saddle(BoolV saddle) {
            BoolE saddleE = tmp(new BoolE());
            call(saddleV(saddle));
            call(saddleE(saddleE));

            BoolEv ev = tmp(new BoolEv());
            BoolVe ve = tmp(new BoolVe());
            BoolV saddleV = tmp(new BoolV());
            broadcast(saddleE, ev);
            transfer(ev, ve);
            redOr(ve, saddleV);

            or(saddle, saddleV, saddle);
        }
    }
    public Procedure saddle(BoolV saddle) { return new Saddle(saddle); }



    //////////////////////////////////
    /// Detection through flooding ///
    //////////////////////////////////

    private class FloodOnce extends Procedure {
        public FloodOnce(BoolV in, BoolV out) {
            IntVe grad = tmp(new IntVe(nbits));
            call(distField.getGradient(grad));

            BoolVe gradOk = tmp(new BoolVe());
            gt(grad, IntVe.of(0, nbits), gradOk);

            BoolVe ve = tmp(new BoolVe());
            broadcast(in, ve);
            and(ve, gradOk, ve);
            call(BlobV.send(ve, ve));
            redOr(ve, out);
        }
    }
    private Procedure floodOnce(BoolV in, BoolV out) { return new FloodOnce(in, out); }

    private class ForAll extends Procedure {
        public ForAll(BoolV in, BoolVe ve, BoolV out) {
            broadcast(in, ve);
            call(BlobV.send(ve, ve));
            redAnd(ve, out);
        }
    }
    private Procedure forAll(BoolV in, BoolVe ve, BoolV out) { return new ForAll(in, ve, out); }

    private class Flood extends Procedure {
        public Flood() {
            int mod = Math.powExact(2, nbits)-1;
            set(new BoolV().zeroes(), center);

            IntV dist = new IntV(nbits);
            call(distField.getDist(dist));

            BoolV flood = tmp(new BoolV());
            BoolVe ve = tmp(new BoolVe());

            BoolE centerE = tmp(new BoolE());
            BoolEv ev = tmp(new BoolEv());
            BoolV v = tmp(new BoolV());
            
            for (int i=0; i<mod; i++) {
                eq(dist, IntV.of(i, nbits), flood);

                if (i == 0) {
                    BoolV flood0 = new BoolV();
                    set(flood, flood0);
                    show("Flood Start 0", flood0);
                }

                for (int j=0; j<mod-2; j++) call(floodOnce(flood, flood));

                if (i == 0) {
                    BoolV flood0 = new BoolV();
                    set(flood, flood0);
                    show("Flood 0", flood0);
                }

                for (int j=0; j<mod-3; j++) call(forAll(flood, ve, flood));

                if (i == 0) {
                    BoolV flood0 = new BoolV();
                    set(flood, flood0);
                    show("Centers 0", flood0);
                }

                or(flood, center, center);

                call(forAll(flood, ve, flood));
                broadcast(flood, ve);
                transfer(ve, ev);
                redAnd(ev, centerE);

                broadcast(centerE, ev);
                transfer(ev, ve);
                redOr(ve, v);
                or(v, center, center);
            }
        }
    }
    public Procedure flood() { return new Flood(); }


}
