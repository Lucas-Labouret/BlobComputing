package language.obj.agent;

import language.Ref;
import language.instruction.Procedure;
import language.obj.field.boolField.BoolV;
import language.obj.field.intField.IntV;
import language.ref.agent.BlobVRef;
import language.ref.field.boolField.*;
import language.ref.field.intField.IntVRef;
import medium.Medium;
import medium.locusS.Vertex;

public class BlobV extends Agent {
    protected final BoolVRef init;
    private final BlobVRef thisRef = BlobVRef.of(this);

    public BlobV() {
        init = this.state.copy();
    }

    public BlobV(BoolVRef cells) {
        super(cells);
        init = this.state.copy();
    }

    public static BlobV randOne(Medium medium) {
        Vertex randomVertex = medium.vertices.toArray(new Vertex[0])[(int)(Math.random() * medium.vertices.size())];
        BoolV randomBoolV = BoolV.zeroes();
        BoolV.setBit(randomBoolV, randomVertex, true);
        return new BlobV(BoolVRef.of(randomBoolV));
    }

    public static BlobV rand() {return rand(0); }
    public static BlobV rand(int sparsity) {
        BoolV cells = BoolV.rand();
        for (int i = 0; i < sparsity; i++) {
            cells = BoolV.and(cells, BoolV.rand());
        }
        return new BlobV(BoolVRef.of(cells));
    }

    private static class Send extends Procedure {
        public Send(BoolVRef in, BoolVeRef out) {
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolEfRef ef = tmp(new BoolEfRef());

            broadcast(in, out);
            transfer(out, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, out);
        }
    }
    public static Procedure send(BoolVRef in, BoolVeRef out) { return new Send(in, out); }

    private static class Grow extends Procedure {
        public <I extends BlobV, O extends BlobV> Grow(Ref<I> in, Ref<O> out) {
            BoolVRef inState = tmp(new BoolVRef());
            call(getState(in, inState));

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef middle = tmp(new BoolERef());

            BoolVRef outState = tmp(new BoolVRef());

            broadcast(inState, ve);
            transfer(ve, ev);
            redOr(ev, middle);
            broadcast(middle, ev);
            transfer(ev, ve);
            redOr(ve, outState);

            call(setState(outState, out));
        }
    }
    public static <I extends BlobV, O extends BlobV> Procedure grow(Ref<I> in, Ref<O> out) { return new Grow(in, out); }
    public <O extends BlobV> Procedure grow(Ref<O> out) { return grow(thisRef, out); }
    public Procedure grow() { return grow(thisRef, thisRef); }

    private static class GrowDebug extends Procedure {
        public GrowDebug(BoolVRef in, BoolVRef out) {
            BoolVRef sart = new BoolVRef();
            show("BlobV", in);

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

    private static class FrontierE extends Procedure {
        public <I extends BlobV> FrontierE(Ref<I> in, BoolERef frontier) {
            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());

            BoolVRef inState = tmp(new BoolVRef());
            call(getState(in, inState));

            broadcast(inState, ve);
            transfer(ve, ev);
            redXor(ev, frontier);
        }
    }
    public Procedure frontierE(BoolERef frontier) { return new FrontierE(thisRef, frontier); }

    private static class FrontierV extends Procedure {
        public <I extends BlobV> FrontierV(Ref<I> in, BoolVRef frontier) {
            BoolVRef notIn = tmp(new BoolVRef());
            BlobVRef grow = tmp(BlobVRef.of(new BlobV()));

            BoolVRef inState = tmp(new BoolVRef());
            call(getState(in, inState));

            not(inState, notIn);
            call(in.get().grow(grow));
            and(inState, notIn, frontier);
        }
    }
    public Procedure frontierV(BoolVRef frontier) { return new FrontierV(thisRef, frontier); }

    private static class OutVe extends Procedure {
        public <I extends BlobV> OutVe(Ref<I> in, BoolVeRef out) {
            BoolERef frontierE = tmp(new BoolERef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolVeRef frontierVe = tmp(new BoolVeRef());

            call(in.get().frontierE(frontierE));
            broadcast(frontierE, ev);
            transfer(ev, frontierVe);

            BoolVRef inState = tmp(new BoolVRef());
            BoolVeRef ve = tmp(new BoolVeRef());
            call(getState(in, inState));
            broadcast(inState, ve);

            and(ve, frontierVe, out);
        }
    }
    public Procedure outVe(BoolVeRef out) { return new OutVe(thisRef, out); }

    private static class BorderVe extends Procedure {
        public <I extends BlobV> BorderVe(Ref<I> in, BoolVeRef out) {
            BoolERef frontierE = tmp(new BoolERef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolVeRef ve = tmp(new BoolVeRef());

            call(in.get().frontierE(frontierE));
            broadcast(frontierE, ev);
            transfer(ev, ve);

            BoolVRef inState = tmp(new BoolVRef());
            BoolVRef borderV = tmp(new BoolVRef());
            call(getState(in, inState));
            redOr(ve, borderV);
            and(borderV, inState, borderV);

            BoolEfRef ef = tmp(new BoolEfRef());
            broadcast(borderV, ve);
            transfer(ve, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, ve);

            BoolVeRef inVe = tmp(new BoolVeRef());
            broadcast(inState, inVe);
            and(inVe, ve, out);
        }
    }
    public Procedure borderVe(BoolVeRef out) { return new BorderVe(thisRef, out); }


    private static class MeetE extends Procedure {
        public <I extends BlobV> MeetE(Ref<I> in, BoolERef out) {
            BoolVfRef vf = tmp(new BoolVfRef());
            BoolFvRef fv = tmp(new BoolFvRef());
            BoolFRef f = tmp(new BoolFRef());
            BoolFeRef fe = tmp(new BoolFeRef());
            BoolEfRef ef = tmp(new BoolEfRef());

            BoolVRef inState = tmp(new BoolVRef());
            call(getState(in, inState));

            BoolERef frontierInteriorE = tmp(new BoolERef());
            BoolERef frontierInteriorEInv = tmp(new BoolERef());

            broadcast(inState, vf);
            transfer(vf, fv);
            redOr(fv, f);
            broadcast(f, fe);
            transfer(fe, ef);
            redOr(ef, frontierInteriorE);
            not(frontierInteriorE, frontierInteriorEInv);

            BoolVRef frontierV = tmp(new BoolVRef());
            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());

            call(in.get().frontierV(frontierV));
            broadcast(frontierV, ve);
            transfer(ve, ev);
            redAnd(ev, out);

            and(out, frontierInteriorEInv, out);
        }
    }
    public Procedure meetE(BoolERef out) { return new MeetE(thisRef, out); }

    private static class MeetV extends Procedure {
        public <I extends BlobV> MeetV(Ref<I> in, BoolVRef out) {
            BoolERef frontierE = tmp(new BoolERef());
            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());

            call(in.get().frontierE(frontierE));
            broadcast(frontierE, ev);
            transfer(ev, ve);

            BoolVfRef cw = tmp(new BoolVfRef());
            BoolVfRef ccw = tmp(new BoolVfRef());
            BoolVfRef vf = tmp(new BoolVfRef());

            rotCW(ve, cw);
            rotCCW(ve, ccw);
            xor(cw, ccw, vf);

            IntVRef connectedComponents = tmp(IntVRef.of(new IntV(4)));
            redAdd(vf, connectedComponents);
            gt(connectedComponents, IntVRef.of(IntV.of(3, 4)), out);

            BoolVRef inState = tmp(new BoolVRef());
            call(getState(in, inState));

            BoolVRef notIn = tmp(new BoolVRef());
            not(inState, notIn);
            and(out, notIn, out);
        }
    }
    public Procedure meetV(BoolVRef out) { return new MeetV(thisRef, out); }

    private static class Meet extends Procedure {
        public <I extends BlobV> Meet(Ref<I> in, BoolVRef out) {
            BoolERef meetE = tmp(new BoolERef());
            BoolVRef meetV = tmp(new BoolVRef());

            call(in.get().meetE(meetE));
            call(in.get().meetV(meetV));

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());

            broadcast(meetE, ev);
            transfer(ev, ve);
            redOr(ve, out);

            or(out, meetV, out);
        }
    }
    public Procedure meet(BoolVRef out) { return new Meet(thisRef, out); }

    private static class Voronoi extends Procedure {
        public <I extends BlobV, O extends BlobV> Voronoi(Ref<I> in, Ref<O> out) {
            show("Seeds", in.get().init);

            BoolVRef inState = tmp(new BoolVRef());
            call(getState(in, inState));

            BoolVRef start = new BoolVRef();
            set(inState, start);
            show("BlobV", start);

            BoolVRef meet = tmp(new BoolVRef());
            call(in.get().meet(meet));
            not(meet, meet);

            BoolVRef outState = tmp(new BoolVRef());

            set(inState, outState);
            call(out.get().grow());

            BoolVRef growCopy = new BoolVRef();
            set(outState, growCopy);
            show("grow", growCopy);

            and(outState, meet, outState);
            or(inState, outState, outState);

            call(setState(outState, out));
        }
    }
    public Procedure voronoi() { return new Voronoi(thisRef, thisRef); }


    @Override
    public BlobV copy() {
        return new BlobV(state.copy());
    }
}
