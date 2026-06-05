package language.obj.agent;

import language.Obj;
import language.instruction.Procedure;
import language.obj.field.boolField.BoolV;
import language.obj.field.intField.IntV;
import language.Ref;
import language.ref.field.boolField.BoolVRef;
import language.ref.field.intField.IntVRef;

import java.util.ArrayList;

public class Flip extends Obj {
    private final ArrayList<Ref<? extends Force>> yes = new ArrayList<>();
    private final ArrayList<Ref<? extends Force>> no = new ArrayList<>();

    public void addYes(Ref<? extends Force> f) { yes.add(f); }
    public void addNo(Ref<? extends Force> f) { no.add(f); }
    
    private static class ApplyForce extends Procedure {
        public ApplyForce(Ref<? extends Force> force, BoolVRef action,
                          IntVRef currentPriority, IntVRef currentPrioRand,
                          BoolVRef where) {
            BoolVRef fWhere = tmp(new BoolVRef());
            call(force.get().compute(fWhere));

            BoolVRef priorityEq = tmp(new BoolVRef());
            eq(force.get().priority, currentPriority, priorityEq);

            BoolVRef priorityNotEq = tmp(new BoolVRef());
            not(priorityEq, priorityNotEq);

            BoolVRef priorityGt = tmp(new BoolVRef());
            BoolVRef prioRandGte = tmp(new BoolVRef());
            gt(force.get().priority, currentPriority, priorityGt);
            and(priorityGt, priorityNotEq, priorityGt);
            gt(force.get().prioRand, currentPrioRand, prioRandGte);

            BoolVRef apply = tmp(new BoolVRef());
            and(priorityEq, prioRandGte, apply);
            or(priorityGt, apply, apply);
            and(apply, fWhere, apply);

            fif(apply, action, where, where);
            fif(apply, force.get().priority, currentPriority, currentPriority);
            fif(apply, force.get().prioRand, currentPrioRand, currentPrioRand);
        }
    }
    private static Procedure applyForce(Ref<? extends Force> force, BoolVRef action,
                                        IntVRef currentPriority, IntVRef currentPrioRand,
                                        BoolVRef where) {
        return new ApplyForce(force, action, currentPriority, currentPrioRand, where);
    }


    private final BoolVRef zero = BoolVRef.of(BoolV.zeroes());
    private final BoolVRef one = BoolVRef.of(BoolV.ones());

    private final IntVRef min = IntVRef.of(IntV.minValue(Force.priorityBits));

    private class Where extends Procedure {
        public Where(BoolVRef where) {
            set(zero, where);
            
            IntVRef currentPriority = new IntVRef();
            IntVRef currentPrioRand = new IntVRef();
            set(min, currentPriority);
            set(min, currentPrioRand);
            
            for (Ref<? extends Force> f : yes) { call(applyForce(f, one , currentPriority, currentPrioRand, where)); }
            for (Ref<? extends Force> f : no ) { call(applyForce(f, zero, currentPriority, currentPrioRand, where)); }
        }
    }
    public Procedure where(BoolVRef where) { return new Where(where); }

    @Override
    public Obj copy() {
        Flip f = new Flip();
        for (Ref<? extends Force> fs : yes) { f.addYes(fs.copy()); }
        for (Ref<? extends Force> fs : no ) { f.addNo (fs.copy()); }
        return f;
    }
}
