package blobProgram.agent;

import blobProgram.Rand;
import language.field.VoidF;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.instruction.Procedure;

/**
 * A Force determines the behavior of an agent by indicating where its state should be changed.
 * <p>
 * A force has two components :<br>
 * - A "yes" component, which indicates where the state should be changed.<br>
 * - A "no" component, which indicates where the state should not be changed.<br>
 * <p>
 * A force has a priority used to resolve conflict between different forces,
 * and a random priority to break ties between forces with the same priority.
 */
public abstract class Force {
    public static final int priorityBits = 2;
    public static final int prioRandBits = 4;

    public final IntV priority;
    public final IntV prioRand;

    protected final Rand rand = new Rand();

    protected Force() {
        this.priority = IntV.of(0, priorityBits);
        this.prioRand = new IntV(prioRandBits);
    }

    protected Force(IntV priority) {
        this.priority = priority;
        this.prioRand = new IntV(prioRandBits);
    }

    public Force setPriority(int p) {
        priority.set(IntV.of(p, priorityBits));
        return this;
    }

    /** Updates the random priority, and computes the yes and no components of the force. */
    private class Apply extends Procedure<VoidF> {
        public Apply(BoolV yes, BoolV no) {
            // Update the random priority of the force.
            call(rand.next(prioRand));

            // Compute the yes and no components of the force.
            call(compute(yes, no));

            // Randomly cancel the force with a probability of 1/2.
            Rand rand = new Rand();
            BoolV randV = tmp(new BoolV());
            call(rand.next(randV));
            set(and(yes, randV), yes);
            call(rand.next(randV));
            set(and(no, randV), no);

            returnValue(new VoidF());
        }
    }
    public Procedure<VoidF> apply(BoolV yes, BoolV no) {
        return new Apply(yes, no);
    }

    /** Computes the yes and no components of the force. */
    protected abstract Procedure<VoidF> compute(BoolV yes, BoolV no);
}
