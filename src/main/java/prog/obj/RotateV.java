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
        private final BoolVeRef in;
        private final BoolVeRef out;

        public CW(BoolVeRef in, BoolVeRef out) {
            this.in = in;
            this.out = out;
        }

        @Override
        protected void setInstructions() {
            BoolVfRef temp = BoolVfRef.zeroes();

            add(new Show("Ve", in));
            add(CommOp.rotCW(in, temp));

            add(new Show("Vf", temp));
            add(CommOp.rotCW(temp, out));
        }
    }

    private static class CCW extends Procedure {
        private final BoolVeRef in;
        private final BoolVeRef out;

        public CCW(BoolVeRef in, BoolVeRef out) {
            this.in = in;
            this.out = out;
        }

        @Override
        protected void setInstructions() {
            BoolVfRef temp = BoolVfRef.zeroes();

            add(new Show("Ve", in));
            add(CommOp.rotCCW(in, temp));

            add(new Show("Vf", temp));
            add(CommOp.rotCCW(temp, out));
        }
    }

    @Override
    public RotateV copy() { return null; }
}
