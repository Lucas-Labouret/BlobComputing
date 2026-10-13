package blobProgram.agent;

import blobProgram.BlobV;
import language.field.VoidF;
import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

/**
 * A constraint verify whether a flip is allowed to happen or not.
 * It cancels the flip where it is not allowed to happen,
 * and resolves conflicts where only a limited number of flips are allowed to happen at the same time,
 * according to the specified priorities.
 */
public abstract class Constraint {
    /**
     * Cancel the flip to resolve conflicts and maintain properties.
     * @param flip the flip to verify. In/Out parameter.
     * @param priority The priority of the flip. Higher priority flips are more likely to be allowed to happen.
     * @param prioRand A random number to break ties between flips with the same priority. Higher prioRand flips are more likely to be allowed to happen.
     */
    public abstract Procedure<VoidF> verify(BoolV flip, IntV priority, IntV prioRand);

    /** A mutex resolves conflicts between flips situated on the two ends of an edge. */
    private static class Mutex extends Procedure<BoolV> {
        public Mutex(BoolE mutex, IntV priority, IntV prioRand) {
            // Identify the minimum priority vertices of the two-particles
            IntEv priorityEv = transfer(broadcastVe(priority));
            BoolEv isMinPriorityEv = eq(priorityEv, broadcastEv(redMin(priorityEv)));

            // Identify the minimum prioRand vertices among the two particles that have the minimum priority
            IntEv prioRandEv = fif(isMinPriorityEv, transfer(broadcastVe(prioRand)), IntEv.maxValue(prioRand.n));
            BoolEv isMinPrioRandEv = eq(prioRandEv, broadcastEv(redMin(prioRandEv)));

            // Identify the vertex that has the minimum priority and minimum prioRand and cancel its flip
            BoolEv towardCancel = and(broadcastEv(mutex), and(isMinPriorityEv, isMinPrioRandEv));
            returnValue(redOr(transfer(towardCancel)));
        }
    }
    protected Procedure<BoolV> mutex(BoolE mutexE, IntV priority, IntV prioRand) {
        return new Mutex(mutexE, priority, prioRand);
    }

    /** A mutApex resolves conflicts between flips situated on the two apexes of an edge. */
    private static class MutApex extends Procedure<BoolV> {
        public MutApex(BoolE mutApex, IntV priority, IntV prioRand) {
            // Identify the minimum priority apexes of the two-particles
            IntEf priorityEf = call(BlobV.apexToEdge(broadcastVf(priority)));
            BoolEf hasMinPriorityEf = eq(priorityEf, broadcastEf(redMin(priorityEf)));

            // Identify the minimum prioRand apexes among the two particles that have the minimum priority
            IntEf prioRandEf = fif(hasMinPriorityEf, call(BlobV.apexToEdge(broadcastVf(prioRand))), IntEf.maxValue(Force.prioRandBits));
            BoolEf hasMinPrioRandEf = eq(prioRandEf, broadcastEf(redMin(prioRandEf)));

            // Identify the apex that has the minimum priority and minimum prioRand and cancel its flip
            BoolEf towardCancel = and(broadcastEf(mutApex), and(hasMinPriorityEf, hasMinPrioRandEf));
            returnValue(redOr(call(BlobV.edgeToApex(towardCancel))));
        }
    }
    protected Procedure<BoolV> mutApex(BoolE mutexE, IntV priority, IntV prioRand) {
        return new MutApex(mutexE, priority, prioRand);
    }

    /** A tritex resolves conflicts between flips situated on the three ends of a face. */
    private static class Tritex extends Procedure<BoolV> {
        public Tritex(BoolF tritex, IntV priority, IntV prioRand) {
            // Identify the minimum priority vertices of the three-particles
            IntFv priorityFv = transfer(broadcastVf(priority));
            BoolFv hasMinPriorityFv = eq(priorityFv, broadcastFv(redMin(priorityFv)));

            // Identify the minimum prioRand vertices among the three particles that have the minimum priority
            IntFv prioRandFv = fif(hasMinPriorityFv, transfer(broadcastVf(prioRand)), IntFv.maxValue(Force.prioRandBits));
            BoolFv hasMinPrioRandFv = eq(prioRandFv, broadcastFv(redMin(prioRandFv)));

            // Identify the vertex that has the minimum priority and minimum prioRand and cancel its flip
            BoolFv towardCancel = and(broadcastFv(tritex), and(hasMinPriorityFv, hasMinPrioRandFv));
            returnValue(redOr(transfer(towardCancel)));
        }
    }
    protected Procedure<BoolV> tritex(BoolF tritex, IntV priority, IntV prioRand) {
        return new Tritex(tritex, priority, prioRand);
    }
}
