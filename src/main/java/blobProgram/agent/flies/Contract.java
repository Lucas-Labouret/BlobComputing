package blobProgram.agent.flies;

import blobProgram.QuasiParticle;
import blobProgram.agent.Force;
import language.field.intField.*;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;
import language.instruction.Procedure;

public class Contract extends Force {
    private final QuasiParticle state;

    public Contract(QuasiParticle state) {
        super(new IntVRef(IntV.of(0, Force.priorityBits)));
        this.state = state;
    }

    private class _Compute extends Procedure {
        public _Compute(BoolVRef target) {
            QuasiParticle two = tmp(new QuasiParticle());
            QuasiParticle three = tmp(new QuasiParticle());

            call(state.twoParticle(two));
            call(state.threeParticle(three));

            BoolVRef contractTwo = tmp(new BoolVRef());
            call(computeTwo(two, contractTwo));

            BoolVRef contractThree = tmp(new BoolVRef());
            call(computeThree(three, contractThree));

            or(contractTwo, contractThree, target);
        }
    }

    private class ComputeTwo extends Procedure {
        public ComputeTwo(QuasiParticle two, BoolVRef target) {
            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef middle = tmp(new BoolERef());

            broadcast(two, ve);
            transfer(ve, ev);
            redAnd(ev, middle);

            IntVeRef randVe = new IntVeRef(new IntVe(prioRandBits));
            IntEvRef randEv = new IntEvRef(new IntEv(prioRandBits));
            IntERef maxE = new IntERef(new IntE(prioRandBits));

            broadcast(prioRand, randVe);
            transfer(randVe, randEv);
            redMax(randEv, maxE);

            IntEvRef maxEv = new IntEvRef(new IntEv(prioRandBits));
            broadcast(maxE, maxEv);

            BoolEvRef isMax = tmp(new BoolEvRef());
            eq(randEv, maxEv, isMax);

            IntERef nbOfMax = new IntERef(new IntE(2));
            redAdd(isMax, nbOfMax);

            BoolERef oneMax = tmp(new BoolERef());
            eq(nbOfMax, new IntERef(IntE.of(1, 2)), oneMax);
            and(middle, oneMax, oneMax);

            broadcast(oneMax, ev);
            and(ev, isMax, isMax);
            transfer(isMax, ve);
            redOr(ve, target);
        }
    }
    private Procedure computeTwo(QuasiParticle two, BoolVRef target) { return new ComputeTwo(two, target); }

    private class ComputeThree extends Procedure {
        public ComputeThree(QuasiParticle three, BoolVRef target) {
            BoolVfRef vf = tmp(new BoolVfRef());
            BoolFvRef fv = tmp(new BoolFvRef());
            BoolFRef middle = tmp(new BoolFRef());

            broadcast(three, vf);
            transfer(vf, fv);
            redAnd(fv, middle);

            IntVfRef randVf = new IntVfRef(new IntVf(prioRandBits));
            IntFvRef randFv = new IntFvRef(new IntFv(prioRandBits));
            IntFRef maxF = new IntFRef(new IntF(prioRandBits));

            broadcast(prioRand, randVf);
            transfer(randVf, randFv);
            redMax(randFv, maxF);

            IntFvRef maxFv = new IntFvRef(new IntFv(prioRandBits));
            broadcast(maxF, maxFv);

            BoolFvRef isMax = tmp(new BoolFvRef());
            eq(randFv, maxFv, isMax);

            IntFRef nbOfMax = new IntFRef(new IntF(2));
            redAdd(isMax, nbOfMax);

            BoolFRef oneMax = tmp(new BoolFRef());
            BoolFRef twoMax = tmp(new BoolFRef());
            BoolFRef okMax = tmp(new BoolFRef());
            eq(nbOfMax, new IntFRef(IntF.of(1, 2)), oneMax);
            eq(nbOfMax, new IntFRef(IntF.of(2, 2)), twoMax);
            or(oneMax, twoMax, okMax);

            and(middle, okMax, okMax);
            broadcast(okMax, fv);
            and(fv, isMax, isMax);
            transfer(isMax, vf);
            redOr(vf, target);
        }
    }
    private Procedure computeThree(QuasiParticle three, BoolVRef target) { return new ComputeThree(three, target); }

    @Override
    protected Procedure _compute(BoolVRef target) { return new _Compute(target); }
}
