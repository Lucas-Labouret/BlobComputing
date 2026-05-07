package prog.obj;

import field.boolField.fieldT.BoolVe;
import language.Obj;
import language.Procedure;
import language.basicInstruction.Show;
import language.basicInstruction.commOp.CommOp;
import language.fieldRef.BoolVeRef;
import language.fieldRef.BoolVfRef;

public class RotateV extends Obj {
    BoolVeRef state;

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
            BoolVfRef temp = BoolVfRef.zeroes();

            show("Ve", in);
            rotCW(in, temp);

            show("Vf", temp);
            rotCW(temp, out);
        }
    }

    private static class CCW extends Procedure {
        public CCW(BoolVeRef in, BoolVeRef out) {
            BoolVfRef temp = BoolVfRef.zeroes();

            show("Ve", in);
            rotCCW(in, temp);

            show("Vf", temp);
            rotCCW(temp, out);
        }
    }

    @Override
    public RotateV copy() { return new RotateV(state.copy()); }
}
