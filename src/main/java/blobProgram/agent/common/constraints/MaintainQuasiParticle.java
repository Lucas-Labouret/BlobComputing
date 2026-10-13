package blobProgram.agent.common.constraints;

import blobProgram.BlobV;
import blobProgram.QuasiParticle;
import blobProgram.agent.Constraint;
import blobProgram.agent.Force;
import language.field.VoidF;
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

    private class Verify extends Procedure<VoidF> {
        public Verify(BoolV flip, IntV priority, IntV prioRand) {
            show("QP / flip before", flip, Styles.FLIP);

            BoolV cancelOne = call(new VerifyOne(flip, priority, prioRand));
            BoolV cancelTwo = call(new VerifyTwo(flip, priority, prioRand));
            BoolV cancelThree = call(new VerifyThree(flip, priority, prioRand));
            BoolV cancel = or(cancelOne, or(cancelTwo, cancelThree));
            set(and(flip, not(cancel)), flip);

            show("QP / flip after", flip, Styles.FLIP);

            returnValue(new VoidF());
        }
    }

    @Override
    public Procedure<VoidF> verify(BoolV flip, IntV priority, IntV prioRand) {
        return new Verify(flip, priority, prioRand);
    }

    private class VerifyOne extends Procedure<BoolV> {
        public VerifyOne(BoolV flip, IntV priority, IntV prioRand) {
            QuasiParticle one = call(state.oneParticle());

            show("QP / one", one, Styles.PARTICLE);

            // Mark the Ve that lead to the particle
            BoolVe particleVe = call(BlobV.send(broadcastVe(one)));

            // Mark the Ve pointing toward a flip
            BoolVe flipVe = broadcastVe(flip);

            // Find the maximum priority among the neighbors that will flip
            show("QP / priority", priority);
            IntVe priorityVe = call(BlobV.send(fif(flipVe, broadcastVe(priority), IntVe.minValue(Force.priorityBits))));
            BoolVe hasMaxPriorityVe = eq(priorityVe, broadcastVe(redMax(priorityVe)));
            show("QP / hasMaxPriorityVe", hasMaxPriorityVe);

            // Find the maximum prioRand among the neighbors that have the maximum priority
            IntVe prioRandVe = fif(hasMaxPriorityVe, call(BlobV.send(broadcastVe(prioRand))), IntVe.minValue(Force.prioRandBits));
            BoolVe hasMaxPrioRandVe = eq(prioRandVe, broadcastVe(redMax(prioRandVe)));

            BoolVe isMaxVe = and(hasMaxPriorityVe, hasMaxPrioRandVe);

            // Only flip if there is exactly one neighbor with the maximum priority and prioRand
            BoolVe oneMaxVe = and(isMaxVe, broadcastVe(eq(redAdd(isMaxVe, 4), IntV.of(1, 4))));
            BoolV selected = redOr(and(call(BlobV.send(oneMaxVe)), particleVe));

            // Cancel the unselected flips and cancel the flips on the one-particle itself
            BoolV cancel = and(not(selected), and(call(one.frontierV()), flip));
            returnValue(or(one, cancel));
        }
    }

    private class VerifyTwo extends Procedure<BoolV> {
        public VerifyTwo(BoolV flip, IntV priority, IntV prioRand) {
            QuasiParticle two = call(state.twoParticle());

            // Identify the centers of the two-particles
            BoolE particleCenter = redAnd(transfer(broadcastVe(two)));

            // Cancel the flips around the two-particles, except for the apexes
            BoolV apexes = redOr(call(BlobV.edgeToApex(broadcastEf(particleCenter))));
            BoolV cancelFrontier = and(call(two.frontierV()), not(apexes));

            // Identify the centers where both apexes will flip
            BoolE bothApexesFlip = and(particleCenter, redAnd(call(BlobV.apexToEdge(broadcastVf(flip)))));
            BoolV cancelApexes = call(mutApex(bothApexesFlip, priority, prioRand));

            // Identify the centers of the two-particles that will completely disappear
            BoolV emptying = and(two, flip);
            BoolE disappearing = redAnd(transfer(broadcastVe(emptying)));
            BoolV cancelDisappearing = call(mutex(disappearing, priority, prioRand));

            returnValue(or(cancelFrontier, or(cancelApexes, cancelDisappearing)));
        }
    }

    private class VerifyThree extends Procedure<BoolV> {
        public VerifyThree(BoolV flip, IntV priority, IntV prioRand) {
            QuasiParticle three = call(state.threeParticle());

            // Cannot flip around a three-particle
            BoolV cancelFrontier = call(three.frontierV());

            // Identify the centers of the three-particles that will completely disappear
            BoolF particleCenter = redAnd(transfer(broadcastVf(three)));
            BoolF flipCenter = redAnd(transfer(broadcastVf(flip)));
            BoolF disappearingParticleCenter = and(particleCenter, flipCenter);

            BoolV cancelDisappearing = call(tritex(disappearingParticleCenter, priority, prioRand));

            returnValue(or(cancelFrontier, cancelDisappearing));
        }
    }
}
