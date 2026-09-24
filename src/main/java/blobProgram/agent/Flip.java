package blobProgram.agent;

import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.intField.IntVRef;
import language.instruction.Procedure;

import java.util.ArrayList;

public class Flip {
    private static final BoolVRef zero = new BoolVRef(BoolV.zeroes());
    private static final BoolVRef one = new BoolVRef(BoolV.ones());

    private static final IntVRef minPrio = new IntVRef(IntV.minValue(Force.priorityBits));
    private static final IntVRef minRand = new IntVRef(IntV.minValue(Force.prioRandBits));

    private final ArrayList<Force> forces = new ArrayList<>();

    public Flip addForce(Force f) { forces.add(f); return this; }

    private static class ApplyForce extends Procedure {
        public ApplyForce(Force force,
                          IntVRef currentPrio, IntVRef currentPrioRand,
                          BoolVRef where) {
            BoolVRef whereYes = tmp(new BoolVRef());
            BoolVRef whereNo = tmp(new BoolVRef());
            call(force.compute(whereYes, whereNo));

            BoolVRef priorityEq = tmp(new BoolVRef());
            eq(force.priority, currentPrio, priorityEq);

            BoolVRef priorityNotEq = tmp(new BoolVRef());
            not(priorityEq, priorityNotEq);

            BoolVRef priorityGt = tmp(new BoolVRef());
            BoolVRef prioRandGte = tmp(new BoolVRef());
            gt(force.priority, currentPrio, priorityGt);
            and(priorityGt, priorityNotEq, priorityGt);
            gt(force.prioRand, currentPrioRand, prioRandGte);

            BoolVRef apply = tmp(new BoolVRef());
            and(priorityEq, prioRandGte, apply);
            or(priorityGt, apply, apply);

            BoolVRef applyYes = tmp(new BoolVRef());
            and(apply, whereYes, applyYes);
            BoolVRef applyNo = tmp(new BoolVRef());
            and(apply, whereNo, applyNo);

            fif(applyYes, one, where, where);
            fif(applyNo, zero, where, where);

            BoolVRef affected = tmp(new BoolVRef());
            or(applyYes, applyNo, affected);
            fif(affected, force.priority, currentPrio, currentPrio);
            fif(affected, force.prioRand, currentPrioRand, currentPrioRand);
        }
    }
    private static Procedure applyForce(Force force,
                                        IntVRef currentPriority, IntVRef currentPrioRand,
                                        BoolVRef where) {
        return new ApplyForce(force, currentPriority, currentPrioRand, where);
    }

    private class Where extends Procedure {
        public Where(BoolVRef where) {
            set(zero, where);
            
            IntVRef currentPriority = new IntVRef(new IntV(Force.priorityBits));
            IntVRef currentPrioRand = tmp(new IntVRef(new IntV(Force.prioRandBits)));
            set(minPrio, currentPriority);
            set(minRand, currentPrioRand);
            
            for (Force f : forces) { call(applyForce(f, currentPriority, currentPrioRand, where)); }

            //show("Prio", currentPriority);
        }
    }
    public Procedure where(BoolVRef where) { return new Where(where); }
}
