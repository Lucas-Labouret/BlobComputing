package prog.obj;

import field.boolField.fieldS.BoolV;
import language.Obj;
import language.Procedure;
import language.basicInstruction.*;
import language.basicInstruction.commOp.CommOp;
import language.fieldRef.BoolERef;
import language.fieldRef.BoolEvRef;
import language.fieldRef.BoolVRef;
import language.fieldRef.BoolVeRef;
import medium.Medium;
import medium.locusS.Vertex;

public class GrowV extends Obj {
    private final BoolVRef state;

    public GrowV(BoolVRef cells) {
        this.state = cells;
    }

    public static GrowV rand(Medium medium) {
        Vertex randomVertex = medium.vertices.toArray(new Vertex[0])[(int)(Math.random() * medium.vertices.size())];
        BoolV randomBoolV = BoolV.zeroes();
        BoolV.setBit(randomBoolV, randomVertex.y, randomVertex.x, true);
        return new GrowV(BoolVRef.of(randomBoolV));
    }

    public Procedure showGrow() {
        return new ShowGrow(state, state);
    }

    public Procedure grow() {
        return new GrowThroughE(state, state);
    }

    public Procedure grow(BoolVRef out) {
        return new GrowThroughE(state, out);
    }

    public Procedure growDebug() {
        return new GrowDebug(state, state);
    }

    private static class ShowGrow extends Procedure {
        private final BoolVRef in;
        private final BoolVRef out;

        public ShowGrow(BoolVRef in, BoolVRef out) {
            this.in = in;
            this.out = out;
        }

        @Override
        protected void setInstructions() {
            add(new Show("grow", in));
            add(new GrowThroughE(in, out));
        }
    }

    private static class GrowThroughE extends Procedure {
        private final BoolVRef in;
        private final BoolVRef out;

        public GrowThroughE(BoolVRef in, BoolVRef out) {
            this.in = in;
            this.out = out;
        }

        @Override
        protected void setInstructions() {
            BoolERef middle = BoolERef.zeroes();
            add(new GrowVtoE(in, middle));
            add(new GrowEtoV(middle, out));
        }
    }

    private static class GrowVtoE extends Procedure {
        private final BoolVRef in;
        private final BoolERef out;

        public GrowVtoE(BoolVRef in, BoolERef out) {
            this.in = in;
            this.out = out;
        }

        @Override
        protected void setInstructions(){
            BoolVeRef ve = BoolVeRef.zeroes();
            BoolEvRef ev = BoolEvRef.zeroes();
            add(CommOp.broadcast(in, ve));
            add(CommOp.transfer(ve, ev));
            add(CommOp.redOr(ev, out));
        }

    }

    private static class GrowEtoV extends Procedure {
        private final BoolERef in;
        private final BoolVRef out;

        public GrowEtoV(BoolERef in, BoolVRef out) {
            this.in = in;
            this.out = out;
        }

        @Override
        protected void setInstructions() {
            BoolEvRef ev = BoolEvRef.zeroes();
            BoolVeRef ve = BoolVeRef.zeroes();
            add(CommOp.broadcast(in, ev));
            add(CommOp.transfer(ev, ve));
            add(CommOp.redOr(ve, out));
        }
    }

    private static class GrowDebug extends Procedure {
        private final BoolVRef in;
        private final BoolVRef out;

        public GrowDebug(BoolVRef in, BoolVRef out) {
            this.in = in;
            this.out = out;
        }

        @Override
        protected void setInstructions() {
            add(new Show("GrowV", in));

            BoolVeRef veIn = new BoolVeRef();
            BoolEvRef evIn = new BoolEvRef();
            BoolERef middle = new BoolERef();
            BoolEvRef evOut = new BoolEvRef();
            BoolVeRef veOut = new BoolVeRef();

            add(CommOp.broadcast(in, veIn));
            add(new Show("veIn", veIn));

            add(CommOp.transfer(veIn, evIn));
            add(new Show("evIn", evIn));

            add(CommOp.redOr(evIn, middle));
            add(new Show("middle", middle));

            add(CommOp.broadcast(middle, evOut));
            add(new Show("evOut", evOut));

            add(CommOp.transfer(evOut, veOut));
            add(new Show("veOut", veOut));

            add(CommOp.redOr(veOut, out));
        }
    };

    @Override
    public GrowV copy() {
        return new GrowV(state.copy());
    }
}
