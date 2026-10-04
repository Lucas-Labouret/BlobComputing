package blobProgram.agent;

import blobProgram.BlobV;
import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

public abstract class Constraint {
    protected final BlobV state;

    protected Constraint(BlobV state) {
        this.state = state;
    }

    public abstract Procedure verify(BoolV flip, IntV priority, IntV prioRand);

    private static class Mutex extends Procedure {
        public Mutex(BoolE mutex, IntV priority, IntV prioRand, BoolV cancel) {
            IntE minPriorityE = tmp(new IntE(priority.n));
            IntVe priorityVe = tmp(new IntVe(priority.n));
            IntEv priorityEv = tmp(new IntEv(priority.n));
            broadcast(priority, priorityVe);
            transfer(priorityVe, priorityEv);
            redMin(priorityEv, minPriorityE);

            BoolEv isMinPriorityEv = tmp(new BoolEv());
            IntEv minPriorityEv = tmp(new IntEv(priority.n));
            broadcast(minPriorityE, minPriorityEv);
            eq(priorityEv, minPriorityEv, isMinPriorityEv);

            IntE minPrioRandE = tmp(new IntE(prioRand.n));
            IntVe prioRandVe = tmp(new IntVe(prioRand.n));
            IntEv prioRandEv = tmp(new IntEv(prioRand.n));
            broadcast(prioRand, prioRandVe);
            transfer(prioRandVe, prioRandEv);
            fif(isMinPriorityEv, prioRandEv, IntEv.maxValue(prioRand.n), prioRandEv);
            redMin(prioRandEv, minPrioRandE);

            BoolEv isMinPrioRandEv = tmp(new BoolEv());
            IntEv minPrioRandEv = tmp(new IntEv(prioRand.n));
            broadcast(minPrioRandE, minPrioRandEv);
            eq(prioRandEv, minPrioRandEv, isMinPrioRandEv);

            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());
            and(isMinPriorityEv, isMinPrioRandEv, isMinPriorityEv);
            broadcast(mutex, ev);
            and(ev, isMinPriorityEv, isMinPriorityEv);
            transfer(isMinPriorityEv, ve);
            redOr(ve, cancel);
        }
    }
    protected Procedure mutex(BoolE mutexE, IntV priority, IntV prioRand, BoolV cancel) {
        return new Mutex(mutexE, priority, prioRand, cancel);
    }

    private static class MutApex extends Procedure {
        public MutApex(BoolE mutApex, IntV priority, IntV prioRand, BoolV cancel) {
            // Find the lowest priority apex
            IntVf priorityVf = tmp(new IntVf(Force.priorityBits));
            IntFv priorityFv = tmp(new IntFv(Force.priorityBits));
            IntFe priorityFe = tmp(new IntFe(Force.priorityBits));
            IntEf priorityEf = tmp(new IntEf(Force.priorityBits));
            IntE minPriority = tmp(new IntE(Force.priorityBits));
            IntEf minPriorityEf = tmp(new IntEf(Force.priorityBits));
            BoolEf hasMinPriorityEf = new BoolEf();

            broadcast(priority, priorityVf);
            transfer(priorityVf, priorityFv);
            rotCW(priorityFv, priorityFe);
            rotCW(priorityFe, priorityFv);
            rotCW(priorityFv, priorityFe);
            transfer(priorityFe, priorityEf);
            redMin(priorityEf, minPriority);
            broadcast(minPriority, minPriorityEf);
            eq(priorityEf, minPriorityEf, hasMinPriorityEf);

            // Find the lowest prioRand apex among the lowest priority apexes
            IntVf prioRandVf = tmp(new IntVf(Force.prioRandBits));
            IntFv prioRandFv = tmp(new IntFv(Force.prioRandBits));
            IntFe prioRandFe = tmp(new IntFe(Force.prioRandBits));
            IntEf prioRandEf = tmp(new IntEf(Force.prioRandBits));
            IntE minPrioRand = tmp(new IntE(Force.prioRandBits));
            IntEf minPrioRandEf = tmp(new IntEf(Force.prioRandBits));
            BoolEf hasMinPrioRandEf = new BoolEf();

            broadcast(prioRand, prioRandVf);
            transfer(prioRandVf, prioRandFv);
            rotCW(prioRandFv, prioRandFe);
            rotCW(prioRandFe, prioRandFv);
            rotCW(prioRandFv, prioRandFe);
            transfer(prioRandFe, prioRandEf);
            fif(hasMinPriorityEf, prioRandEf, IntEf.maxValue(Force.prioRandBits), prioRandEf);
            redMin(prioRandEf, minPrioRand);
            broadcast(minPrioRand, minPrioRandEf);
            eq(prioRandEf, minPrioRandEf, hasMinPrioRandEf);

            // Cancel the flip of the minimum priority and minimum prioRand apex
            BoolEf ef = tmp(new BoolEf());
            BoolFe fe = tmp(new BoolFe());
            BoolFv fv = tmp(new BoolFv());
            BoolVf vf = tmp(new BoolVf());

            and(hasMinPriorityEf, hasMinPrioRandEf, hasMinPriorityEf);
            broadcast(mutApex, ef);
            and(ef, hasMinPriorityEf, hasMinPriorityEf);
            transfer(hasMinPriorityEf, fe);
            rotCW(fe, fv);
            rotCW(fv, fe);
            rotCW(fe, fv);
            transfer(fv, vf);
            redOr(vf, cancel);
        }
    }
    protected Procedure mutApex(BoolE mutexE, IntV priority, IntV prioRand, BoolV cancel) {
        return new MutApex(mutexE, priority, prioRand, cancel);
    }

    private static class Tritex extends Procedure {
        public Tritex(BoolF tritex, IntV priority, IntV prioRand, BoolV cancel) {
            // Identify the minimum priority vertices of the three-particles
            IntVf priorityVf = tmp(new IntVf(Force.priorityBits));
            IntFv priorityFv = tmp(new IntFv(Force.priorityBits));
            IntF minPriority = tmp(new IntF(Force.priorityBits));
            IntFv minPriorityFv = tmp(new IntFv(Force.priorityBits));
            BoolFv hasMinPriorityFv = tmp(new BoolFv());

            broadcast(priority, priorityVf);
            transfer(priorityVf, priorityFv);
            redMin(priorityFv, minPriority);
            broadcast(minPriority, minPriorityFv);
            eq(priorityFv, minPriorityFv, hasMinPriorityFv);

            // Identify the minimum prioRand vertices among the three particles that have the minimum priority
            IntVf prioRandVf = tmp(new IntVf(Force.prioRandBits));
            IntFv prioRandFv = tmp(new IntFv(Force.prioRandBits));
            IntF minPrioRand = tmp(new IntF(Force.prioRandBits));
            IntFv minPrioRandFv = tmp(new IntFv(Force.prioRandBits));
            BoolFv hasMinPrioRandFv = tmp(new BoolFv());

            broadcast(prioRand, prioRandVf);
            transfer(prioRandVf, prioRandFv);
            fif(hasMinPriorityFv, prioRandFv, IntFv.maxValue(Force.prioRandBits), prioRandFv);
            redMin(prioRandFv, minPrioRand);
            broadcast(minPrioRand, minPrioRandFv);
            eq(prioRandFv, minPrioRandFv, hasMinPrioRandFv);

            // Identify the vertex that has the minimum priority and minimum prioRand and cancel its flip
            BoolFv tritexFv = tmp(new BoolFv());
            BoolVf vf = tmp(new BoolVf());
            and(hasMinPriorityFv, hasMinPrioRandFv, hasMinPriorityFv);
            broadcast(tritex, tritexFv);
            and(tritexFv, hasMinPriorityFv, hasMinPriorityFv);
            transfer(hasMinPrioRandFv, vf);
            redOr(vf, cancel);
        }
    }
    protected Procedure tritex(BoolF tritex, IntV priority, IntV prioRand, BoolV cancel) {
        return new Tritex(tritex, priority, prioRand, cancel);
    }
}
