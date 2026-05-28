package language.obj;

import language.Obj;
import language.instruction.Procedure;
import language.obj.field.boolField.BoolE;
import language.obj.field.boolField.BoolEv;
import language.obj.field.boolField.BoolV;
import language.obj.field.boolField.BoolVe;
import language.ref.field.boolField.BoolERef;
import language.ref.field.boolField.BoolEvRef;
import language.ref.field.boolField.BoolVRef;
import language.ref.field.boolField.BoolVeRef;
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
        BoolV.setBit(randomBoolV, randomVertex, true);
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
        public ShowGrow(BoolVRef in, BoolVRef out) {
            show("grow", in);
            call(new GrowThroughE(in, out));
        }
    }

    private static class GrowThroughE extends Procedure {
        public GrowThroughE(BoolVRef in, BoolVRef out) {
            BoolERef middle = BoolERef.of(BoolE.rand());
            call(new GrowVtoE(in, middle));
            call(new GrowEtoV(middle, out));
        }
    }

    private static class GrowVtoE extends Procedure {
        public GrowVtoE(BoolVRef in, BoolERef out) {
            BoolVeRef ve = BoolVeRef.of(BoolVe.rand());
            BoolEvRef ev = BoolEvRef.of(BoolEv.rand());
            broadcast(in, ve);
            transfer(ve, ev);
            redOr(ev, out);
        }
    }

    private static class GrowEtoV extends Procedure {
        public GrowEtoV(BoolERef in, BoolVRef out) {
            BoolEvRef ev = BoolEvRef.of(BoolEv.rand());
            BoolVeRef ve = BoolVeRef.of(BoolVe.rand());
            broadcast(in, ev);
            transfer(ev, ve);
            redOr(ve, out);
        }
    }

    private static class GrowDebug extends Procedure {
        public GrowDebug(BoolVRef in, BoolVRef out) {
            show("GrowV", in);

            BoolVeRef veIn = new BoolVeRef();
            BoolEvRef evIn = new BoolEvRef();
            BoolERef middle = new BoolERef();
            BoolEvRef evOut = new BoolEvRef();
            BoolVeRef veOut = new BoolVeRef();

            broadcast(in, veIn);
            show("veIn", veIn);

            transfer(veIn, evIn);
            show("evIn", evIn);

            redOr(evIn, middle);
            show("middle", middle);

            broadcast(middle, evOut);
            show("evOut", evOut);

            transfer(evOut, veOut);
            show("veOut", veOut);

            redOr(veOut, out);
        }
    }

    @Override
    public GrowV copy() {
        return new GrowV(state.copy());
    }
}
