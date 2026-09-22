package blobProgram.agent.flies;

import blobProgram.BlobV;
import blobProgram.QuasiParticle;
import blobProgram.agent.Force;
import language.field.boolField.BoolV;
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

public class Expand extends Force {
    private final QuasiParticle state;

    public Expand(QuasiParticle state) {
        this.state = state;
    }

    private class _Compute extends Procedure {
        public _Compute(BoolVRef yes, BoolVRef no) {
            QuasiParticle one = tmp(new QuasiParticle());
            QuasiParticle two = tmp(new QuasiParticle());

            call(state.oneParticle(one));
            call(state.twoParticle(two));

            BoolVRef meetV = tmp(new BoolVRef());
            BoolVeRef forbiddenVe = tmp(new BoolVeRef());
            BoolVeRef authorizedVe = tmp(new BoolVeRef());
            call(state.meetV(meetV));
            broadcast(meetV, forbiddenVe);
            call(BlobV.send(forbiddenVe, forbiddenVe));
            not(forbiddenVe, authorizedVe);

            BoolVRef oneExpand = tmp(new BoolVRef());
            call(computeOne(one, authorizedVe, oneExpand));

            BoolVRef twoExpand = tmp(new BoolVRef());
            call(computeTwo(two, authorizedVe, twoExpand));

            BoolVRef expand = tmp(new BoolVRef());
            or(oneExpand, twoExpand, expand);

            BoolERef meetE = tmp(new BoolERef());
            call(state.meetE(meetE));
            call(resolveMeetEConflict(meetE, expand, yes));
            set(new BoolVRef(BoolV.zeroes()), no);
        }
    }

    private class ResolveMeetEConflict extends Procedure {
        public ResolveMeetEConflict(BoolERef meetE, BoolVRef expand, BoolVRef target) {
            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef e = tmp(new BoolERef());
            BoolERef conflict = tmp(new BoolERef());
            BoolEvRef conflictEv = tmp(new BoolEvRef());

            broadcast(expand, ve);
            transfer(ve, ev);
            redAnd(ev, e);
            and(e, meetE, conflict);
            broadcast(conflict, conflictEv);

            IntVeRef randVe = tmp(new IntVeRef(new IntVe(prioRandBits)));
            IntEvRef randEv = tmp(new IntEvRef(new IntEv(prioRandBits)));
            broadcast(prioRand, randVe);
            transfer(randVe, randEv);

            IntERef maxE = tmp(new IntERef(new IntE(prioRandBits)));
            redMax(randEv, maxE);

            IntEvRef maxEv = tmp(new IntEvRef(new IntEv(prioRandBits)));
            broadcast(maxE, maxEv);

            BoolEvRef isMax = tmp(new BoolEvRef());
            BoolEvRef isNotMax = tmp(new BoolEvRef());
            eq(randEv, maxEv, isMax);
            not(isMax, isNotMax);

            and(conflictEv, isNotMax, conflictEv);
            transfer(conflictEv, ve);
            redOr(ve, target);
            not(target, target);
            and(expand, target, target);
        }
    }
    private Procedure resolveMeetEConflict(BoolERef meetE, BoolVRef expand, BoolVRef target)
        { return new ResolveMeetEConflict(meetE, expand, target); }

    private class ComputeOne extends Procedure {
        public ComputeOne(QuasiParticle one, BoolVeRef authorizedVe, BoolVRef target) {
            BoolVeRef oneOut = tmp(new BoolVeRef());
            broadcast(one, oneOut);
            and(oneOut, authorizedVe, oneOut);

            IntVeRef randVe = tmp(new IntVeRef(new IntVe(prioRandBits)));
            broadcast(prioRand, randVe);
            call(BlobV.send(randVe, randVe));

            IntVeRef min = new IntVeRef(IntVe.minValue(prioRandBits));
            fif(oneOut, randVe, min, randVe);

            IntVRef maxV = tmp(new IntVRef(new IntV(prioRandBits)));
            redMax(randVe, maxV);

            IntVeRef maxVe = tmp(new IntVeRef(new IntVe(prioRandBits)));
            broadcast(maxV, maxVe);

            BoolVeRef isMax = tmp(new BoolVeRef());
            eq(randVe, maxVe, isMax);

            IntVRef nbOfMax = tmp(new IntVRef(new IntV(4)));
            redAdd(isMax, nbOfMax);

            BoolVRef oneMax = tmp(new BoolVRef());
            BoolVRef twoMax = tmp(new BoolVRef());
            BoolVRef okMax = tmp(new BoolVRef());
            eq(nbOfMax, new IntVRef(IntV.of(1, 4)), oneMax);
            eq(nbOfMax, new IntVRef(IntV.of(2, 4)), twoMax);
            or(oneMax, twoMax, okMax);

            BoolVeRef okVe = tmp(new BoolVeRef());
            broadcast(okMax, okVe);
            and(okVe, isMax, okVe);

            IntVRef connectedComponents = tmp(new IntVRef(new IntV(4)));
            BoolVRef oneConnected = tmp(new BoolVRef());
            call(BlobV.connectedComponents(okVe, connectedComponents));

            eq(connectedComponents, new IntVRef(IntV.of(1, 4)), oneConnected);
            broadcast(oneConnected, isMax);
            and(okVe, isMax, okVe);

            call(BlobV.send(okVe, okVe));
            redOr(okVe, target);
        }
    }
    private Procedure computeOne(QuasiParticle one, BoolVeRef authorizedVe, BoolVRef target)
        { return new ComputeOne(one, authorizedVe, target); }

    private class ComputeTwo extends Procedure {
        public ComputeTwo(QuasiParticle two, BoolVeRef authorizedVe, BoolVRef target) {
            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef e = tmp(new BoolERef());
            BoolEfRef ef = tmp(new BoolEfRef());
            BoolFeRef fe = tmp(new BoolFeRef());
            BoolFvRef fv = tmp(new BoolFvRef());
            BoolVfRef vf = tmp(new BoolVfRef());
            BoolVRef potentialExpand = tmp(new BoolVRef());

            broadcast(two, ve);
            transfer(ve, ev);
            redAnd(ev, e);
            broadcast(e, ef);
            transfer(ef, fe);
            rotCW(fe, fv);
            rotCW(fv, fe);
            rotCW(fe, fv);
            transfer(fv, vf);
            redOr(vf, potentialExpand);

            broadcast(potentialExpand, ve);

            IntVeRef randVe = tmp(new IntVeRef(new IntVe(prioRandBits)));
            broadcast(prioRand, randVe);

            call(BlobV.send(ve, ve));
            call(BlobV.send(randVe, randVe));

            BoolVeRef twoOut = tmp(new BoolVeRef());
            broadcast(two, twoOut);
            and(ve, twoOut, twoOut);
            and(twoOut, authorizedVe, twoOut);

            IntVeRef min = new IntVeRef(IntVe.minValue(prioRandBits));
            fif(twoOut, randVe, min, randVe);

            IntVRef maxV = tmp(new IntVRef(new IntV(prioRandBits)));
            redMax(randVe, maxV);

            IntVeRef maxVe = tmp(new IntVeRef(new IntVe(prioRandBits)));
            broadcast(maxV, maxVe);

            BoolVeRef isMax = tmp(new BoolVeRef());
            eq(randVe, maxVe, isMax);

            IntVRef nbOfMax = tmp(new IntVRef(new IntV(4)));
            redAdd(isMax, nbOfMax);

            BoolVRef oneMax = tmp(new BoolVRef());
            eq(nbOfMax, new IntVRef(IntV.of(1, 4)), oneMax);

            BoolVeRef oneMaxVe = tmp(new BoolVeRef());
            broadcast(oneMax, oneMaxVe);
            and(isMax, oneMaxVe, isMax);

            call(BlobV.send(isMax, ve));

            redOr(ve, target);
        }
    }
    private Procedure computeTwo(QuasiParticle two, BoolVeRef authorizedVe, BoolVRef target)
        { return new ComputeTwo(two, authorizedVe, target); }

    @Override
    public Procedure _compute(BoolVRef yes, BoolVRef no) {
        return new _Compute(yes, no);
    }
}
