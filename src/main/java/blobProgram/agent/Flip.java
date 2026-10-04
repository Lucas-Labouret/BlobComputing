package blobProgram.agent;

import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.instruction.Procedure;

import java.util.ArrayList;

public class Flip {
    private static final BoolV zero = new BoolV().zeroes();
    private static final BoolV one = new BoolV().ones();

    private static final IntV minPrio = IntV.minValue(Force.priorityBits);
    private static final IntV minRand = IntV.minValue(Force.prioRandBits);

    private final ArrayList<Force> forces = new ArrayList<>();
    private final ArrayList<Constraint> constraints = new ArrayList<>();

    public Flip addForce(Force f) { forces.add(f); return this; }
    public Flip addConstraint(Constraint c) { constraints.add(c); return this; }

    private static class ApplyForce extends Procedure {
        public ApplyForce(Force force,
                          IntV currentPrio, IntV currentPrioRand,
                          BoolV where) {
            BoolV whereYes = tmp(new BoolV());
            BoolV whereNo = tmp(new BoolV());
            call(force.apply(whereYes, whereNo));

            BoolV priorityEq = tmp(new BoolV());
            eq(force.priority, currentPrio, priorityEq);

            BoolV priorityNotEq = tmp(new BoolV());
            not(priorityEq, priorityNotEq);

            BoolV priorityGt = tmp(new BoolV());
            BoolV prioRandGte = tmp(new BoolV());
            gt(force.priority, currentPrio, priorityGt);
            and(priorityGt, priorityNotEq, priorityGt);
            gt(force.prioRand, currentPrioRand, prioRandGte);

            BoolV apply = tmp(new BoolV());
            and(priorityEq, prioRandGte, apply);
            or(priorityGt, apply, apply);

            BoolV applyYes = tmp(new BoolV());
            and(apply, whereYes, applyYes);
            BoolV applyNo = tmp(new BoolV());
            and(apply, whereNo, applyNo);

            fif(applyYes, one, where, where);
            fif(applyNo, zero, where, where);

            BoolV affected = tmp(new BoolV());
            or(applyYes, applyNo, affected);
            fif(affected, force.priority, currentPrio, currentPrio);
            fif(affected, force.prioRand, currentPrioRand, currentPrioRand);
        }
    }
    private static Procedure applyForce(Force force,
                                        IntV currentPriority, IntV currentPrioRand,
                                        BoolV where) {
        return new ApplyForce(force, currentPriority, currentPrioRand, where);
    }

    private class Where extends Procedure {
        public Where(BoolV flip) {
            set(zero, flip);
            
            IntV currentPriority = new IntV(Force.priorityBits);
            IntV currentPrioRand = new IntV(Force.prioRandBits);
            set(minPrio, currentPriority);
            set(minRand, currentPrioRand);
            
            for (Force f : forces) { call(applyForce(f, currentPriority, currentPrioRand, flip)); }

            show("priority", currentPriority);
            show("prioRand", currentPrioRand);

            for (Constraint c : constraints) { call(c.verify(flip, currentPriority, currentPrioRand)); }
        }
    }
    public Procedure where(BoolV where) { return new Where(where); }
}
