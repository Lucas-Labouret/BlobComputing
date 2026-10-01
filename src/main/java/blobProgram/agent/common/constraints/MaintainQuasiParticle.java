package blobProgram.agent.common.constraints;

import blobProgram.BlobV;
import blobProgram.QuasiParticle;
import blobProgram.agent.Constraint;
import blobProgram.agent.Force;
import language.field.intField.*;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;
import language.instruction.Procedure;
import ui.display.Styles;

public class MaintainQuasiParticle extends Constraint {
    private final QuasiParticle state;

    public MaintainQuasiParticle(QuasiParticle qp) {
        super(qp);
        this.state = qp;
    }

    private class Verify extends Procedure {
        public Verify(BoolVRef flip, IntVRef priority, IntVRef prioRand) {
            show("QP / flip before", flip, Styles.FLIP);

            BoolVRef cancelOne = tmp(new BoolVRef());
            BoolVRef cancelTwo = tmp(new BoolVRef());
            BoolVRef cancelThree = tmp(new BoolVRef());
            call(new VerifyOne(flip, priority, prioRand, cancelOne));
            call(new VerifyTwo(flip, priority, prioRand, cancelTwo));
            call(new VerifyThree(flip, priority, prioRand, cancelThree));
            not(cancelOne, cancelOne);
            not(cancelTwo, cancelTwo);
            not(cancelThree, cancelThree);
            and(cancelOne, flip, flip);
            and(cancelTwo, flip, flip);
            and(cancelThree, flip, flip);

            show("QP / flip after", flip, Styles.FLIP);
        }
    }

    @Override
    public Procedure verify(BoolVRef flip, IntVRef priority, IntVRef prioRand) {
        return new Verify(flip, priority, prioRand);
    }

    private class VerifyOne extends Procedure {
        public VerifyOne(BoolVRef flip, IntVRef priority, IntVRef prioRand, BoolVRef cancelOne) {
            QuasiParticle one = new QuasiParticle();
            call(state.oneParticle(one));

            // Mark the Ve that lead to the particle
            BoolVeRef particleVe  = tmp(new BoolVeRef());
            broadcast(one, particleVe);
            call(BlobV.send(particleVe, particleVe));

            // Mark the Ve around flip
            BoolVeRef flipVe = tmp(new BoolVeRef());
            broadcast(flip, flipVe);

            // Find the maximum priority among the neighbors that will flip
            IntVeRef priorityVe = tmp(new IntVeRef(new IntVe(Force.priorityBits)));
            IntVRef maxPriority = tmp(new IntVRef(new IntV(Force.priorityBits)));
            IntVeRef maxPriorityVe = tmp(new IntVeRef(new IntVe(Force.priorityBits)));
            BoolVeRef hasMaxPriorityVe = tmp(new BoolVeRef());

            broadcast(priority, priorityVe);
            fif(flipVe, priorityVe, new IntVeRef(IntVe.minValue(Force.priorityBits)), priorityVe);
            call(BlobV.send(priorityVe, priorityVe));
            redMax(priorityVe, maxPriority);
            broadcast(maxPriority, maxPriorityVe);
            eq(priorityVe, maxPriorityVe, hasMaxPriorityVe);

            // Find the maximum prioRand among the neighbors that have the maximum priority
            IntVeRef prioRandVe = tmp(new IntVeRef(new IntVe(Force.prioRandBits)));
            IntVRef maxPrioRand = tmp(new IntVRef(new IntV(Force.prioRandBits)));
            IntVeRef maxPrioRandVe = tmp(new IntVeRef(new IntVe(Force.prioRandBits)));
            BoolVeRef hasMaxPrioRandVe = tmp(new BoolVeRef());

            broadcast(prioRand, prioRandVe);
            call(BlobV.send(prioRandVe, prioRandVe));
            fif(hasMaxPriorityVe, prioRandVe, new IntVeRef(IntVe.minValue(Force.prioRandBits)), prioRandVe);
            redMax(prioRandVe, maxPrioRand);
            broadcast(maxPrioRand, maxPrioRandVe);
            eq(prioRandVe, maxPrioRandVe, hasMaxPrioRandVe);

            BoolVeRef isMaxVe = tmp(new BoolVeRef());
            and(hasMaxPriorityVe, hasMaxPrioRandVe, isMaxVe);

            // Only flip if there is exactly one neighbor with the maximum priority and prioRand
            IntVRef nbMax = tmp(new IntVRef(new IntV(3)));
            BoolVRef oneMax = tmp(new BoolVRef());
            redAdd(isMaxVe, nbMax);
            eq(nbMax, new IntVRef(IntV.of(1, 3)), oneMax);

            BoolVeRef oneMaxVe = tmp(new BoolVeRef());
            broadcast(oneMax, oneMaxVe);
            and(isMaxVe, oneMaxVe, isMaxVe);

            call(BlobV.send(isMaxVe, isMaxVe));
            and(isMaxVe, particleVe, isMaxVe);

            BlobV selected = tmp(new BlobV());
            redOr(isMaxVe, selected);

            // Cancel the unselected flips
            BlobV frontier = tmp(new BlobV());
            call(one.frontierV(frontier));
            and(frontier, flip, cancelOne);
            not(selected, selected);
            and(selected, cancelOne, cancelOne);
            or(one, cancelOne, cancelOne);
        }
    }

    private class VerifyTwo extends Procedure {
        public VerifyTwo(BoolVRef flip, IntVRef priority, IntVRef prioRand, BoolVRef cancelTwo) {
            QuasiParticle two = new QuasiParticle();
            call(state.twoParticle(two));

            show("QP / two-particle", two, Styles.PARTICLE);

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolEfRef ef = tmp(new BoolEfRef());
            BoolFeRef fe = tmp(new BoolFeRef());
            BoolFvRef fv = tmp(new BoolFvRef());
            BoolVfRef vf = tmp(new BoolVfRef());

            // Identify the centers of the two-particles
            BoolERef particleCenter = tmp(new BoolERef());
            broadcast(two, ve);
            transfer(ve, ev);
            redAnd(ev, particleCenter);

            // Identify the apexes of the two-particles
            BoolVRef apexes = tmp(new BoolVRef());
            broadcast(particleCenter, ef);
            transfer(ef, fe);
            rotCW(fe, fv);
            rotCW(fv, fe);
            rotCW(fe, fv);
            transfer(fv, vf);
            redOr(vf, apexes);

            // Cancel the flips around the two-particles, except for the apexes
            call(two.frontierV(cancelTwo));
            not(apexes, apexes);
            and(apexes, cancelTwo, cancelTwo);

            // Identify the centers where both apexes will flip
            BoolERef bothApexesFlip = tmp(new BoolERef());
            broadcast(flip, vf);
            transfer(vf, fv);
            rotCW(fv, fe);
            rotCW(fe, fv);
            rotCW(fv, fe);
            transfer(fe, ef);
            redAnd(ef, bothApexesFlip);
            and(bothApexesFlip, particleCenter, bothApexesFlip);

            BoolVRef cancelApexes = tmp(new BoolVRef());
            call(mutApex(bothApexesFlip, priority, prioRand, cancelApexes));
            or(cancelApexes, cancelTwo, cancelTwo);

            // Identify the centers of the two-particles that will completely disappear
            BoolVRef emptying = tmp(new BoolVRef());

            and(two, flip, emptying);
            broadcast(emptying, ve);
            transfer(ve, ev);
            redAnd(ev, particleCenter);

            BoolVRef cancelMinVertex = tmp(new BoolVRef());
            call(mutex(particleCenter, priority, prioRand, cancelMinVertex));

            or(cancelMinVertex, cancelTwo, cancelTwo);
        }
    }

    private class VerifyThree extends Procedure {
        public VerifyThree(BoolVRef flip, IntVRef priority, IntVRef prioRand, BoolVRef cancelThree) {
            QuasiParticle three = new QuasiParticle();
            call(state.threeParticle(three));

            BoolVfRef vf = tmp(new BoolVfRef());
            BoolFvRef fv = tmp(new BoolFvRef());

            // Cannot flip around a three-particle
            call(three.frontierV(cancelThree));

            // Identify the centers of the three-particles that will completely disappear
            BoolFRef disappearingParticleCenter = tmp(new BoolFRef());
            BoolFRef flipCenter = tmp(new BoolFRef());

            broadcast(three, vf);
            transfer(vf, fv);
            redAnd(fv, disappearingParticleCenter);

            broadcast(flip, vf);
            transfer(vf, fv);
            redAnd(fv, flipCenter);

            and(disappearingParticleCenter, flipCenter, disappearingParticleCenter);

            BoolVRef isMinV = tmp(new BoolVRef());
            call(tritex(disappearingParticleCenter, priority, prioRand, isMinV));

            or(isMinV, cancelThree, cancelThree);
        }
    }
}
