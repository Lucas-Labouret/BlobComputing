package blobProgram.agent;

import blobProgram.BlobV;
import language.field.intField.*;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;
import language.instruction.Procedure;

public abstract class Constraint {
    protected final BlobV state;

    protected Constraint(BlobV state) {
        this.state = state;
    }

    public abstract Procedure verify(BoolVRef flip, IntVRef priority, IntVRef prioRand);

    private static class Mutex extends Procedure {
        public Mutex(BoolERef mutex, IntVRef priority, IntVRef prioRand, BoolVRef cancel) {
            IntERef minPriorityE = tmp(new IntERef(new IntE(priority.get().n)));
            IntVeRef priorityVe = tmp(new IntVeRef(new IntVe(priority.get().n)));
            IntEvRef priorityEv = tmp(new IntEvRef(new IntEv(priority.get().n)));
            broadcast(priority, priorityVe);
            transfer(priorityVe, priorityEv);
            redMin(priorityEv, minPriorityE);

            BoolEvRef isMinPriorityEv = tmp(new BoolEvRef());
            IntEvRef minPriorityEv = tmp(new IntEvRef(new IntEv(priority.get().n)));
            broadcast(minPriorityE, minPriorityEv);
            eq(priorityEv, minPriorityEv, isMinPriorityEv);

            IntERef minPrioRandE = tmp(new IntERef(new IntE(prioRand.get().n)));
            IntVeRef prioRandVe = tmp(new IntVeRef(new IntVe(prioRand.get().n)));
            IntEvRef prioRandEv = tmp(new IntEvRef(new IntEv(prioRand.get().n)));
            broadcast(prioRand, prioRandVe);
            transfer(prioRandVe, prioRandEv);
            fif(isMinPriorityEv, prioRandEv, new IntEvRef(IntEv.maxValue(prioRand.get().n)), prioRandEv);
            redMin(prioRandEv, minPrioRandE);

            BoolEvRef isMinPrioRandEv = tmp(new BoolEvRef());
            IntEvRef minPrioRandEv = tmp(new IntEvRef(new IntEv(prioRand.get().n)));
            broadcast(minPrioRandE, minPrioRandEv);
            eq(prioRandEv, minPrioRandEv, isMinPrioRandEv);

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            and(isMinPriorityEv, isMinPrioRandEv, isMinPriorityEv);
            broadcast(mutex, ev);
            and(ev, isMinPriorityEv, isMinPriorityEv);
            transfer(isMinPriorityEv, ve);
            redOr(ve, cancel);
        }
    }
    protected Procedure mutex(BoolERef mutexE, IntVRef priority, IntVRef prioRand, BoolVRef cancel) {
        return new Mutex(mutexE, priority, prioRand, cancel);
    }

    private static class MutApex extends Procedure {
        public MutApex(BoolERef mutApex, IntVRef priority, IntVRef prioRand, BoolVRef cancel) {
            // Find the lowest priority apex
            IntVfRef priorityVf = tmp(new IntVfRef(new IntVf(Force.priorityBits)));
            IntFvRef priorityFv = tmp(new IntFvRef(new IntFv(Force.priorityBits)));
            IntFeRef priorityFe = tmp(new IntFeRef(new IntFe(Force.priorityBits)));
            IntEfRef priorityEf = tmp(new IntEfRef(new IntEf(Force.priorityBits)));
            IntERef minPriority = tmp(new IntERef(new IntE(Force.priorityBits)));
            IntEfRef minPriorityEf = tmp(new IntEfRef(new IntEf(Force.priorityBits)));
            BoolEfRef hasMinPriorityEf = new BoolEfRef();

            broadcast(priority, priorityVf);
            transfer(priorityVf, priorityFv);
            rotCW(priorityFv, priorityFe);
            rotCW(priorityFe, priorityFv);
            rotCW(priorityFv, priorityFe);
            transfer(priorityFe, priorityEf);
            redMin(priorityEf, minPriority);
            broadcast(minPriority, minPriorityEf);
            eq(priorityEf, minPriorityEf, hasMinPriorityEf);
            show("hasMinPriority", hasMinPriorityEf);

            // Find the lowest prioRand apex among the lowest priority apexes
            IntVfRef prioRandVf = tmp(new IntVfRef(new IntVf(Force.prioRandBits)));
            IntFvRef prioRandFv = tmp(new IntFvRef(new IntFv(Force.prioRandBits)));
            IntFeRef prioRandFe = tmp(new IntFeRef(new IntFe(Force.prioRandBits)));
            IntEfRef prioRandEf = tmp(new IntEfRef(new IntEf(Force.prioRandBits)));
            IntERef minPrioRand = tmp(new IntERef(new IntE(Force.prioRandBits)));
            IntEfRef minPrioRandEf = tmp(new IntEfRef(new IntEf(Force.prioRandBits)));
            BoolEfRef hasMinPrioRandEf = new BoolEfRef();

            broadcast(prioRand, prioRandVf);
            transfer(prioRandVf, prioRandFv);
            rotCW(prioRandFv, prioRandFe);
            rotCW(prioRandFe, prioRandFv);
            rotCW(prioRandFv, prioRandFe);
            transfer(prioRandFe, prioRandEf);
            show("prioRandEf", prioRandEf);
            fif(hasMinPriorityEf, prioRandEf, new IntEfRef(IntEf.maxValue(Force.prioRandBits)), prioRandEf);
            show("maxvalue Ef", new IntEfRef(IntEf.maxValue(Force.prioRandBits)));
            show("prioRandEf after fif", prioRandEf);
            redMin(prioRandEf, minPrioRand);
            broadcast(minPrioRand, minPrioRandEf);
            eq(prioRandEf, minPrioRandEf, hasMinPrioRandEf);
            show("hasMinPrioRand", hasMinPrioRandEf);

            // Cancel the flip of the minimum priority and minimum prioRand apex
            BoolEfRef ef = tmp(new BoolEfRef());
            BoolFeRef fe = tmp(new BoolFeRef());
            BoolFvRef fv = tmp(new BoolFvRef());
            BoolVfRef vf = tmp(new BoolVfRef());

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
    protected Procedure mutApex(BoolERef mutexE, IntVRef priority, IntVRef prioRand, BoolVRef cancel) {
        return new MutApex(mutexE, priority, prioRand, cancel);
    }

    private static class Tritex extends Procedure {
        public Tritex(BoolFRef tritex, IntVRef priority, IntVRef prioRand, BoolVRef cancel) {
            // Identify the minimum priority vertices of the three-particles
            IntVfRef priorityVf = tmp(new IntVfRef(new IntVf(Force.priorityBits)));
            IntFvRef priorityFv = tmp(new IntFvRef(new IntFv(Force.priorityBits)));
            IntFRef minPriority = tmp(new IntFRef(new IntF(Force.priorityBits)));
            IntFvRef minPriorityFv = tmp(new IntFvRef(new IntFv(Force.priorityBits)));
            BoolFvRef hasMinPriorityFv = tmp(new BoolFvRef());

            broadcast(priority, priorityVf);
            transfer(priorityVf, priorityFv);
            redMin(priorityFv, minPriority);
            broadcast(minPriority, minPriorityFv);
            eq(priorityFv, minPriorityFv, hasMinPriorityFv);

            // Identify the minimum prioRand vertices among the three particles that have the minimum priority
            IntVfRef prioRandVf = tmp(new IntVfRef(new IntVf(Force.prioRandBits)));
            IntFvRef prioRandFv = tmp(new IntFvRef(new IntFv(Force.prioRandBits)));
            IntFRef minPrioRand = tmp(new IntFRef(new IntF(Force.prioRandBits)));
            IntFvRef minPrioRandFv = tmp(new IntFvRef(new IntFv(Force.prioRandBits)));
            BoolFvRef hasMinPrioRandFv = tmp(new BoolFvRef());

            broadcast(prioRand, prioRandVf);
            transfer(prioRandVf, prioRandFv);
            fif(hasMinPriorityFv, prioRandFv, new IntFvRef(IntFv.maxValue(Force.prioRandBits)), prioRandFv);
            redMin(prioRandFv, minPrioRand);
            broadcast(minPrioRand, minPrioRandFv);
            eq(prioRandFv, minPrioRandFv, hasMinPrioRandFv);

            // Identify the vertex that has the minimum priority and minimum prioRand and cancel its flip
            BoolFvRef tritexFv = tmp(new BoolFvRef());
            BoolVfRef vf = tmp(new BoolVfRef());
            and(hasMinPriorityFv, hasMinPrioRandFv, hasMinPriorityFv);
            broadcast(tritex, tritexFv);
            and(tritexFv, hasMinPriorityFv, hasMinPriorityFv);
            transfer(hasMinPrioRandFv, vf);
            redOr(vf, cancel);
        }
    }
    protected Procedure tritex(BoolFRef tritex, IntVRef priority, IntVRef prioRand, BoolVRef cancel) {
        return new Tritex(tritex, priority, prioRand, cancel);
    }
}
