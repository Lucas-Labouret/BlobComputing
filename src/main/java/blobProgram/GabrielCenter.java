package blobProgram;

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

public class GabrielCenter {
    private final static int nbits = 4;

    private final BoolVRef seeds;
    public final DistField distField;

    private final BoolVRef center = new BoolVRef();
    private class GetCenter extends Procedure { public GetCenter(BoolVRef out) { set(center, out); } }
    public Procedure getCenter(BoolVRef out) { return new GetCenter(out); }

    public GabrielCenter(BoolVRef seeds) {
        this.seeds = seeds;
        this.distField = new DistField(seeds, nbits);
    }

    private class Update extends Procedure { public Update() { call(distField.update()); } }
    public Procedure update() { return new Update(); }

    private class SaddleV extends Procedure {
        public SaddleV(BoolVRef saddleV) {
            IntVeRef gradient = new IntVeRef(new IntVe(nbits));
            call(distField.getGradient(gradient));

            BoolVeRef negGrad = gradient.get().getBits()[0];
            IntVRef components = new IntVRef(new IntV(3));
            call(BlobV.connectedComponents(negGrad, components));

            gt(components, new IntVRef(IntV.of(2, 3)), saddleV);
        }
    }
    public Procedure saddleV(BoolVRef saddleV) { return new SaddleV(saddleV); }

    private class SaddleE extends Procedure {
        public SaddleE(BoolERef saddleE) {
            IntVeRef gradient = new IntVeRef(new IntVe(nbits));
            call(distField.getGradient(gradient));

            BoolEvRef evB = tmp(new BoolEvRef());
            BoolVeRef veB = tmp(new BoolVeRef());
            BoolVfRef vfB = tmp(new BoolVfRef());
            IntVeRef veI4 = tmp(new IntVeRef(new IntVe(4)));

            BoolVeRef negGrad = gradient.get().getBits()[0];

            BoolVeRef nulGrad = tmp(new BoolVeRef());
            eq(gradient, new IntVeRef(IntVe.of(0, nbits)), nulGrad);

            BoolVeRef posGrad = tmp(new BoolVeRef());
            BoolVeRef notNeg = tmp(new BoolVeRef());
            BoolVeRef notNul = tmp(new BoolVeRef());
            not(negGrad, notNeg);
            not(nulGrad, notNul);
            and(notNeg, notNul, posGrad);

            BoolERef flatEdges = tmp(new BoolERef());
            transfer(nulGrad, evB);
            redAnd(evB, flatEdges);

            IntVRef negComponents = tmp(new IntVRef(new IntV(4)));
            IntVRef nulComponents = tmp(new IntVRef(new IntV(4)));
            IntVRef posComponents = tmp(new IntVRef(new IntV(4)));
            call(BlobV.connectedComponents(negGrad, negComponents));
            call(BlobV.connectedComponents(nulGrad, nulComponents));
            call(BlobV.connectedComponents(posGrad, posComponents));

            IntEvRef partialComponents = tmp(new IntEvRef(new IntEv(4)));
            IntERef componentSum = tmp(new IntERef(new IntE(4)));
            broadcast(negComponents, veI4);
            transfer(veI4, partialComponents);
            redAdd(partialComponents, componentSum);

            BoolVRef deadEnd = tmp(new BoolVRef());
            BoolVRef oneNeg = tmp(new BoolVRef());
            BoolVRef oneNul = tmp(new BoolVRef());
            BoolVRef noPos = tmp(new BoolVRef());
            eq(negComponents, new IntVRef(IntV.of(1, 4)), oneNeg);
            eq(nulComponents, new IntVRef(IntV.of(1, 4)), oneNul);
            eq(posComponents, new IntVRef(IntV.of(0, 4)), noPos);
            and(oneNeg, oneNul, deadEnd);
            and(deadEnd, noPos, deadEnd);

            BoolVRef deadEndCopy = new BoolVRef();
            set(deadEnd, deadEndCopy);
            show("deadEnd", deadEndCopy);

            IntERef apexCount = tmp(new IntERef(new IntE(2)));
            BoolVeRef apexOrDeadEnd = tmp(new BoolVeRef());
            broadcast(deadEnd, apexOrDeadEnd);
            or(apexOrDeadEnd, negGrad, apexOrDeadEnd);
            rotCW(apexOrDeadEnd, vfB);
            rotCW(vfB, veB);
            transfer(veB, evB);
            redAdd(evB, apexCount);

            IntERef apexCountCopy = new IntERef(new IntE(2));
            set(apexCount, apexCountCopy);
            show("apexCount", apexCountCopy);

            BoolERef oneApex = tmp(new BoolERef());
            BoolERef twoApex = tmp(new BoolERef());
            eq(apexCount, new IntERef(IntE.of(1, 2)), oneApex);
            eq(apexCount, new IntERef(IntE.of(2, 2)), twoApex);

            BoolERef fullLoop = tmp(new BoolERef());
            BoolEvRef onePartialComponent = tmp(new BoolEvRef());
            eq(partialComponents, new IntEvRef(IntEv.of(1, 4)), onePartialComponent);
            broadcast(twoApex, evB);
            and(onePartialComponent, evB, onePartialComponent);
            redAnd(onePartialComponent, fullLoop);

            IntERef componentsM1 = tmp(new IntERef(new IntE(4)));
            IntERef componentsM2 = tmp(new IntERef(new IntE(4)));
            sub(componentSum, new IntERef(IntE.of(1, 4)), componentsM1);
            sub(componentSum, new IntERef(IntE.of(2, 4)), componentsM2);

            IntERef componentsE = tmp(new IntERef(new IntE(4)));
            fif(oneApex, componentsM1, componentSum, componentsE);
            fif(twoApex, componentsM2, componentsE, componentsE);
            fif(fullLoop, componentsM1, componentsE, componentsE);

            gt(componentsE, new IntERef(IntE.of(2, 4)), saddleE);
            and(saddleE, flatEdges, saddleE);

            BoolERef saddleECopy = new BoolERef();
            set(saddleE, saddleECopy);
            show("saddleE", saddleECopy);
        }
    }
    public Procedure saddleE(BoolERef saddleE) { return new SaddleE(saddleE); }

    private class Saddle extends Procedure {
        public Saddle(BoolVRef saddle) {
            BoolERef saddleE = tmp(new BoolERef());
            call(saddleV(saddle));
            call(saddleE(saddleE));

            BoolEvRef ev = tmp(new BoolEvRef());
            BoolVeRef ve = tmp(new BoolVeRef());
            BoolVRef saddleV = tmp(new BoolVRef());
            broadcast(saddleE, ev);
            transfer(ev, ve);
            redOr(ve, saddleV);

            or(saddle, saddleV, saddle);
        }
    }
    public Procedure saddle(BoolVRef saddle) { return new Saddle(saddle); }
}
