package language.obj;

import language.Obj;
import language.instruction.Procedure;
import language.obj.field.boolField.BoolVe;
import language.obj.field.boolField.BoolVf;
import language.ref.field.boolField.BoolVeRef;
import language.ref.field.boolField.BoolVfRef;

public class RotateV extends Obj {
    final BoolVeRef state;

    public RotateV(BoolVeRef init) {
        this.state = init;
    }

    public static RotateV rand() {
        BoolVeRef init = BoolVeRef.of(BoolVe.rand());
        return new RotateV(init);
    }

    public Procedure cw() {
        return new CW(state, state);
    }

    public Procedure ccw() {
        return new CCW(state, state);
    }

    private static class CW extends Procedure {
        public CW(BoolVeRef in, BoolVeRef out) {
            BoolVfRef temp = BoolVfRef.of(BoolVf.rand());

            show("Ve", in);
            rotCW(in, temp);

            show("Vf", temp);
            rotCW(temp, out);
        }
    }

    private static class CCW extends Procedure {
        public CCW(BoolVeRef in, BoolVeRef out) {
            BoolVfRef temp = BoolVfRef.of(BoolVf.rand());

            show("Ve", in);
            rotCCW(in, temp);

            show("Vf", temp);
            rotCCW(temp, out);
        }
    }

    @Override
    public RotateV copy() { return new RotateV(state.copy()); }
}
