package blobProgram.agent;

import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.instruction.Procedure;

import java.util.ArrayList;

/**
 * A flip determines where the state of an agent will be changed.
 * It is determined by set of forces and constraints.
 * The forces determine where the flip will be applied,
 * and the constraints determine whether the flip is allowed to be applied.
 */
public class Flip {
    private static final BoolV zero = new BoolV().zeroes();
    private static final BoolV one = new BoolV().ones();

    private static final IntV minPrio = IntV.minValue(Force.priorityBits);
    private static final IntV minRand = IntV.minValue(Force.prioRandBits);

    private final ArrayList<Force> forces = new ArrayList<>();
    private final ArrayList<Constraint> constraints = new ArrayList<>();

    /** Adds a force to the flip. */
    public Flip addForce(Force f) { forces.add(f); return this; }
    /** Adds a constraint to the flip. */
    public Flip addConstraint(Constraint c) { constraints.add(c); return this; }

    /**
     * Applies a force to the pre-constraint raw flip.
     * <p>
     * The force is applied if it has a higher priority than the current priority,
     * or if it has the same priority but a higher random priority.
     * The current priority and random priority are updated if a force is applied.
     * <p>
     * The resulting flip is stored in the where parameter.
     */
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

    /** Applies all forces to the flip and verifies all constraints. */
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
