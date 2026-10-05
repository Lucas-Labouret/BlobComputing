package blobProgram.agent.common.constraints;

import blobProgram.BlobV;
import blobProgram.QuasiParticle;
import blobProgram.agent.Constraint;
import blobProgram.agent.Force;
import language.field.boolField.*;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.instruction.Procedure;
import ui.display.Styles;

/**
 * Constraint that maintains the integrity of a quasi-particle.
 * A quasi-particle can have 1, 2 or 3 particles.
 * <p>
 * If a quasi-particle has 2 particles, those are connected by a single edge,
 * and if it has 3 particles, those are connected by a single face.
 * <p>
 * A 1-particle can only evolve into a 2-particle,
 * a 2-particle can only evolve into a 1-, 2- or 3-particle,
 * a 3-particle can only evolve into a 2-particle.
 */
public class MaintainQuasiParticle extends Constraint {
    private final QuasiParticle state;

    public MaintainQuasiParticle(QuasiParticle qp) {
        this.state = qp;
    }

    private class Verify extends Procedure {
        public Verify(BoolV flip, IntV priority, IntV prioRand) {
            show("QP / flip before", flip, Styles.FLIP);

            BoolV cancelOne = tmp(new BoolV());
            BoolV cancelTwo = tmp(new BoolV());
            BoolV cancelThree = tmp(new BoolV());
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
    public Procedure verify(BoolV flip, IntV priority, IntV prioRand) {
        return new Verify(flip, priority, prioRand);
    }

    private class VerifyOne extends Procedure {
        public VerifyOne(BoolV flip, IntV priority, IntV prioRand, BoolV cancelOne) {
            QuasiParticle one = new QuasiParticle();
            call(state.oneParticle(one));

            // Mark the Ve that lead to the particle
            BoolVe particleVe  = tmp(new BoolVe());
            broadcast(one, particleVe);
            call(BlobV.send(particleVe, particleVe));

            // Mark the Ve around flip
            BoolVe flipVe = tmp(new BoolVe());
            broadcast(flip, flipVe);

            // Find the maximum priority among the neighbors that will flip
            IntVe priorityVe = tmp(new IntVe(Force.priorityBits));
            IntV maxPriority = tmp(new IntV(Force.priorityBits));
            IntVe maxPriorityVe = tmp(new IntVe(Force.priorityBits));
            BoolVe hasMaxPriorityVe = tmp(new BoolVe());

            broadcast(priority, priorityVe);
            fif(flipVe, priorityVe, IntVe.minValue(Force.priorityBits), priorityVe);
            call(BlobV.send(priorityVe, priorityVe));
            redMax(priorityVe, maxPriority);
            broadcast(maxPriority, maxPriorityVe);
            eq(priorityVe, maxPriorityVe, hasMaxPriorityVe);

            // Find the maximum prioRand among the neighbors that have the maximum priority
            IntVe prioRandVe = tmp(new IntVe(Force.prioRandBits));
            IntV maxPrioRand = tmp(new IntV(Force.prioRandBits));
            IntVe maxPrioRandVe = tmp(new IntVe(Force.prioRandBits));
            BoolVe hasMaxPrioRandVe = tmp(new BoolVe());

            broadcast(prioRand, prioRandVe);
            call(BlobV.send(prioRandVe, prioRandVe));
            fif(hasMaxPriorityVe, prioRandVe, IntVe.minValue(Force.prioRandBits), prioRandVe);
            redMax(prioRandVe, maxPrioRand);
            broadcast(maxPrioRand, maxPrioRandVe);
            eq(prioRandVe, maxPrioRandVe, hasMaxPrioRandVe);

            BoolVe isMaxVe = tmp(new BoolVe());
            and(hasMaxPriorityVe, hasMaxPrioRandVe, isMaxVe);

            // Only flip if there is exactly one neighbor with the maximum priority and prioRand
            IntV nbMax = tmp(new IntV(3));
            BoolV oneMax = tmp(new BoolV());
            redAdd(isMaxVe, nbMax);
            eq(nbMax, IntV.of(1, 3), oneMax);

            BoolVe oneMaxVe = tmp(new BoolVe());
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
        public VerifyTwo(BoolV flip, IntV priority, IntV prioRand, BoolV cancelTwo) {
            QuasiParticle two = new QuasiParticle();
            call(state.twoParticle(two));

            show("QP / two-particle", two, Styles.PARTICLE);

            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());
            BoolEf ef = tmp(new BoolEf());
            BoolFe fe = tmp(new BoolFe());
            BoolFv fv = tmp(new BoolFv());
            BoolVf vf = tmp(new BoolVf());

            // Identify the centers of the two-particles
            BoolE particleCenter = tmp(new BoolE());
            broadcast(two, ve);
            transfer(ve, ev);
            redAnd(ev, particleCenter);

            // Identify the apexes of the two-particles
            BoolV apexes = tmp(new BoolV());
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
            BoolE bothApexesFlip = tmp(new BoolE());
            broadcast(flip, vf);
            transfer(vf, fv);
            rotCW(fv, fe);
            rotCW(fe, fv);
            rotCW(fv, fe);
            transfer(fe, ef);
            redAnd(ef, bothApexesFlip);
            and(bothApexesFlip, particleCenter, bothApexesFlip);

            BoolV cancelApexes = tmp(new BoolV());
            call(mutApex(bothApexesFlip, priority, prioRand, cancelApexes));
            or(cancelApexes, cancelTwo, cancelTwo);

            // Identify the centers of the two-particles that will completely disappear
            BoolV emptying = tmp(new BoolV());

            and(two, flip, emptying);
            broadcast(emptying, ve);
            transfer(ve, ev);
            redAnd(ev, particleCenter);

            BoolV cancelMinVertex = tmp(new BoolV());
            call(mutex(particleCenter, priority, prioRand, cancelMinVertex));

            or(cancelMinVertex, cancelTwo, cancelTwo);
        }
    }

    private class VerifyThree extends Procedure {
        public VerifyThree(BoolV flip, IntV priority, IntV prioRand, BoolV cancelThree) {
            QuasiParticle three = new QuasiParticle();
            call(state.threeParticle(three));

            BoolVf vf = tmp(new BoolVf());
            BoolFv fv = tmp(new BoolFv());

            // Cannot flip around a three-particle
            call(three.frontierV(cancelThree));

            // Identify the centers of the three-particles that will completely disappear
            BoolF disappearingParticleCenter = tmp(new BoolF());
            BoolF flipCenter = tmp(new BoolF());

            broadcast(three, vf);
            transfer(vf, fv);
            redAnd(fv, disappearingParticleCenter);

            broadcast(flip, vf);
            transfer(vf, fv);
            redAnd(fv, flipCenter);

            and(disappearingParticleCenter, flipCenter, disappearingParticleCenter);

            BoolV isMinV = tmp(new BoolV());
            call(tritex(disappearingParticleCenter, priority, prioRand, isMinV));

            or(isMinV, cancelThree, cancelThree);
        }
    }
}
