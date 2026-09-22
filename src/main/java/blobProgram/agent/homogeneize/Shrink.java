package blobProgram.agent.homogeneize;

import blobProgram.QuasiParticle;
import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.field.intField.IntVe;
import language.fieldRef.boolField.BoolERef;
import language.fieldRef.boolField.BoolEvRef;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.boolField.BoolVeRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;

public class Shrink extends Force {
    private final QuasiParticle seeds;
    private final IntVeRef gradient;

    public Shrink(QuasiParticle seeds, IntVeRef gradient) {
        this.seeds = seeds;
        this.gradient = gradient;
    }

    private class _Compute extends Procedure {
        public _Compute(BoolVRef yes, BoolVRef no) {
            QuasiParticle two = tmp(new QuasiParticle());
            QuasiParticle three = tmp(new QuasiParticle());

            call(seeds.twoParticle(two));
            call(seeds.threeParticle(three));

            BoolVeRef posGrad = tmp(new BoolVeRef());
            gt(gradient, new IntVeRef(IntVe.of(1, gradient.get().n)), posGrad);

            call(computeTwo(two, posGrad, yes));
            show("Shrink Yes", yes);
            set(new BoolVRef(BoolV.zeroes()), no);
        }
    }

    private class _ComputeTwo extends Procedure {
        public _ComputeTwo(QuasiParticle two, BoolVeRef posGrad, BoolVRef res) {
            BoolVeRef inward = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef middle = tmp(new BoolERef());
            broadcast(two, inward);
            transfer(inward, ev);
            redAnd(ev, middle);
            broadcast(middle, ev);
            transfer(ev, inward);

            and(inward, posGrad, inward);
            redOr(inward, res);
        }
    }
    private Procedure computeTwo(QuasiParticle two, BoolVeRef posGrad, BoolVRef res) {
        return new _ComputeTwo(two, posGrad, res);
    }

    @Override
    protected Procedure _compute(BoolVRef yes, BoolVRef no) {
        return new _Compute(yes, no);
    }
}
