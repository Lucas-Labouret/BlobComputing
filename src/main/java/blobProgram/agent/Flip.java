package blobProgram.agent;

import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.intField.IntVRef;
import language.instruction.Procedure;

import java.util.ArrayList;

public class Flip {
    private final ArrayList<Force> yes = new ArrayList<>();
    private final ArrayList<Force> no = new ArrayList<>();

    public Flip addYes(Force f) { yes.add(f); return this; }
    public Flip addNo(Force f) { no.add(f); return this; }
    
    private static class ApplyForce extends Procedure {
        public ApplyForce(Force force, BoolVRef action,
                          IntVRef currentPriority, IntVRef currentPrioRand,
                          BoolVRef where) {
            BoolVRef fWhere = tmp(new BoolVRef());
            call(force.compute(fWhere));

            BoolVRef priorityEq = tmp(new BoolVRef());
            eq(force.priority, currentPriority, priorityEq);

            BoolVRef priorityNotEq = tmp(new BoolVRef());
            not(priorityEq, priorityNotEq);

            BoolVRef priorityGt = tmp(new BoolVRef());
            BoolVRef prioRandGte = tmp(new BoolVRef());
            gt(force.priority, currentPriority, priorityGt);
            and(priorityGt, priorityNotEq, priorityGt);
            gt(force.prioRand, currentPrioRand, prioRandGte);

            BoolVRef apply = tmp(new BoolVRef());
            and(priorityEq, prioRandGte, apply);
            or(priorityGt, apply, apply);
            and(apply, fWhere, apply);

            fif(apply, action, where, where);
            fif(apply, force.priority, currentPriority, currentPriority);
            fif(apply, force.prioRand, currentPrioRand, currentPrioRand);
        }
    }
    private static Procedure applyForce(Force force, BoolVRef action,
                                        IntVRef currentPriority, IntVRef currentPrioRand,
                                        BoolVRef where) {
        return new ApplyForce(force, action, currentPriority, currentPrioRand, where);
    }


    private final BoolVRef zero = new BoolVRef(BoolV.zeroes());
    private final BoolVRef one = new BoolVRef(BoolV.ones());

    private final IntVRef minPrio = new IntVRef(IntV.minValue(Force.priorityBits));
    private final IntVRef minRand = new IntVRef(IntV.minValue(Force.prioRandBits));

    private class Where extends Procedure {
        public Where(BoolVRef where) {
            set(zero, where);
            
            IntVRef currentPriority = tmp(new IntVRef(new IntV(Force.priorityBits)));
            IntVRef currentPrioRand = tmp(new IntVRef(new IntV(Force.prioRandBits)));
            set(minPrio, currentPriority);
            set(minRand, currentPrioRand);
            
            for (Force f : yes) { call(applyForce(f, one , currentPriority, currentPrioRand, where)); }
            for (Force f : no ) { call(applyForce(f, zero, currentPriority, currentPrioRand, where)); }
        }
    }
    public Procedure where(BoolVRef where) { return new Where(where); }
}
