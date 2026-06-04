package language.obj.agents;

import language.Obj;
import language.instruction.Procedure;
import language.obj.field.boolField.BoolV;
import language.ref.field.boolField.BoolVRef;
import language.ref.field.intField.IntVRef;

import java.util.ArrayList;

public class Flip extends Obj {
    private final ArrayList<Force> yes = new ArrayList<>();
    private final ArrayList<Force> no = new ArrayList<>();
    private final ArrayList<Constraint> constraints = new ArrayList<>();

    public void addYes(Force f) {
        yes.add(f);
    }

    public void addNo(Force f) {
        no.add(f);
    }

    public void addConstraint(Constraint c) {
        constraints.add(c);
    }

    private final BoolVRef zero = BoolVRef.of(BoolV.zeroes());
    private final BoolVRef one = BoolVRef.of(BoolV.ones());

    private class Where extends Procedure {
        public Where(BoolVRef where) {

        }
    }

    @Override
    public Obj copy() {
        Flip f = new Flip();
        for (Force fs : yes) { f.addYes(fs.copy()); }
        for (Force fs : no) { f.addNo(fs.copy()); }
        for (Constraint c : constraints) { f.addConstraint(c.copy()); }
        return f;
    }
}
