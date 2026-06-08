package blobProgram;

import language.field.boolField.BoolV;
import language.field.intField.IntEf;
import language.field.intField.IntEv;
import language.field.intField.IntV;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.IntEfRef;
import language.fieldRef.intField.IntEvRef;
import language.fieldRef.intField.IntVRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;
import medium.Medium;
import medium.locusS.Vertex;

public class BlobV extends BoolVRef {
    protected final BoolVRef init;

    public BlobV() {
        this(BoolV.zeroes());
    }

    public BlobV(BoolV state) {
        super(state);
        this.init = new BoolVRef(state.copy());
    }

    public static BlobV randOne(Medium medium) {
        Vertex randomVertex = medium.vertices.toArray(new Vertex[0])[(int)(Math.random() * medium.vertices.size())];
        BoolV randomBoolV = BoolV.zeroes();
        BoolV.setBit(randomBoolV, randomVertex, true);
        return new BlobV(randomBoolV);
    }

    public static BlobV rand() {return rand(0); }
    public static BlobV rand(int sparsity) {
        BoolV cells = BoolV.rand();
        for (int i = 0; i < sparsity; i++) {
            cells = BoolV.and(cells, BoolV.rand());
        }
        return new BlobV(cells);
    }

    private static class Send extends Procedure {
        public Send(BoolVeRef in, BoolVeRef out) {
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolEfRef ef = tmp(new BoolEfRef());

            transfer(in, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, out);
        }

        public Send(IntVeRef in, IntVeRef out) {
            IntEvRef ev = tmp(new IntEvRef(new IntEv(in.get().n)));
            IntEfRef ef = tmp(new IntEfRef(new IntEf(in.get().n)));

            transfer(in, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, out);
        }
    }
    public static Procedure send(BoolVeRef in, BoolVeRef out) { return new Send(in, out); }
    public static Procedure send(IntVeRef in, IntVeRef out) { return new Send(in, out); }

    private static class Grow extends Procedure {
        public Grow(BlobV in, BlobV out) {
            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef middle = tmp(new BoolERef());

            broadcast(in, ve);
            transfer(ve, ev);
            redOr(ev, middle);
            broadcast(middle, ev);
            transfer(ev, ve);
            redOr(ve, out);
        }
    }
    public static Procedure grow(BlobV in, BlobV out) { return new Grow(in, out); }
    public Procedure grow(BlobV out) {return grow(this, out); }
    public Procedure grow() { return grow(this, this); }

    private static class GrowDebug extends Procedure {
        public GrowDebug(BoolVRef in, BoolVRef out) {
            BoolVRef start = new BoolVRef();
            set(in, start);
            show("BlobV", start);

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
    public static Procedure growDebug(BoolVRef in, BoolVRef out) { return new GrowDebug(in, out); }

    private static class FrontierE extends Procedure {
        public FrontierE(BlobV in, BoolERef frontier) {
            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());

            broadcast(in, ve);
            transfer(ve, ev);
            redXor(ev, frontier);
        }
    }
    public static Procedure frontierE(BlobV in, BoolERef frontier) { return new FrontierE(in, frontier); }
    public Procedure frontierE(BoolERef frontier) { return frontierE(this, frontier); }

    private static class FrontierV extends Procedure {
        public FrontierV(BlobV in, BoolVRef frontier) {
            BoolVRef notIn = tmp(new BoolVRef());
            BlobV grow = tmp(new BlobV());

            not(in, notIn);
            call(in.grow(grow));
            and(grow, notIn, frontier);
        }
    }
    public static Procedure frontierV(BlobV in, BoolVRef frontier) { return new FrontierV(in, frontier); }
    public Procedure frontierV(BoolVRef frontier) { return frontierV(this, frontier); }

    private static class OutVe extends Procedure {
        public OutVe(BlobV in, BoolVeRef out) {
            BoolERef frontierE = tmp(new BoolERef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolVeRef frontierVe = tmp(new BoolVeRef());

            call(in.frontierE(frontierE));
            broadcast(frontierE, ev);
            transfer(ev, frontierVe);

            BoolVeRef ve = tmp(new BoolVeRef());
            broadcast(in, ve);

            and(ve, frontierVe, out);
        }
    }
    public static Procedure outVe(BlobV in, BoolVeRef out) { return new OutVe(in, out); }
    public Procedure outVe(BoolVeRef out) { return outVe(this, out); }

    private static class BorderVe extends Procedure {
        public BorderVe(BlobV in, BoolVeRef out) {
            BoolERef frontierE = tmp(new BoolERef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolVeRef ve = tmp(new BoolVeRef());

            call(in.frontierE(frontierE));
            broadcast(frontierE, ev);
            transfer(ev, ve);

            BoolVRef borderV = tmp(new BoolVRef());
            redOr(ve, borderV);
            and(borderV, in, borderV);

            BoolEfRef ef = tmp(new BoolEfRef());
            broadcast(borderV, ve);
            transfer(ve, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, ve);

            BoolVeRef inVe = tmp(new BoolVeRef());
            broadcast(in, inVe);
            and(inVe, ve, out);
        }
    }
    public static Procedure borderVe(BlobV in, BoolVeRef out) { return new BorderVe(in, out); }
    public Procedure borderVe(BoolVeRef out) { return borderVe(this, out); }

    private static class MeetE extends Procedure {
        public MeetE(BlobV in, BoolERef out) {
            BoolVfRef vf = tmp(new BoolVfRef());
            BoolFvRef fv = tmp(new BoolFvRef());
            BoolFRef f = tmp(new BoolFRef());
            BoolFeRef fe = tmp(new BoolFeRef());
            BoolEfRef ef = tmp(new BoolEfRef());

            BoolERef frontierInteriorE = tmp(new BoolERef());
            BoolERef frontierInteriorEInv = tmp(new BoolERef());

            broadcast(in, vf);
            transfer(vf, fv);
            redOr(fv, f);
            broadcast(f, fe);
            transfer(fe, ef);
            redOr(ef, frontierInteriorE);
            not(frontierInteriorE, frontierInteriorEInv);

            BoolVRef frontierV = tmp(new BoolVRef());
            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());

            call(in.frontierV(frontierV));
            broadcast(frontierV, ve);
            transfer(ve, ev);
            redAnd(ev, out);

            and(out, frontierInteriorEInv, out);
        }
    }
    public static Procedure meetE(BlobV in, BoolERef out) { return new MeetE(in, out); }
    public Procedure meetE(BoolERef out) { return meetE(this, out); }

    private static class ConnectedComponents extends Procedure {
        public ConnectedComponents(BoolVeRef in, IntVRef out) {
            BoolVfRef cw = tmp(new BoolVfRef());
            BoolVfRef ccw = tmp(new BoolVfRef());
            BoolVfRef vf = tmp(new BoolVfRef());

            rotCW(in, cw);
            rotCCW(in, ccw);
            xor(cw, ccw, vf);

            redAdd(vf, out);
            rShift(out, out, 1);
        }
    }
    public static Procedure connectedComponents(BoolVeRef in, IntVRef out) { return new ConnectedComponents(in, out); }

    private static class MeetV extends Procedure {
        public MeetV(BlobV in, BoolVRef out) {
            BoolERef frontierE = tmp(new BoolERef());
            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());

            call(in.frontierE(frontierE));
            broadcast(frontierE, ev);
            transfer(ev, ve);

            IntVRef connectedComponents = tmp(new IntVRef(new IntV(4)));
            call(connectedComponents(ve, connectedComponents));
            gt(connectedComponents, new IntVRef(IntV.of(2, 4)), out);

            BoolVRef notIn = tmp(new BoolVRef());
            not(in, notIn);
            and(out, notIn, out);
        }
    }
    public static Procedure meetV(BlobV in, BoolVRef out) { return new MeetV(in, out); }
    public Procedure meetV(BoolVRef out) { return meetV(this, out); }

    private static class Meet extends Procedure {
        public Meet(BlobV in, BoolVRef out) {
            BoolERef meetE = tmp(new BoolERef());
            BoolVRef meetV = tmp(new BoolVRef());

            call(in.meetE(meetE));
            call(in.meetV(meetV));

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());

            broadcast(meetE, ev);
            transfer(ev, ve);
            redOr(ve, out);

            or(out, meetV, out);
        }
    }
    public static Procedure meet(BlobV in, BoolVRef out) { return new Meet(in, out); }
    public Procedure meet(BoolVRef out) { return meet(this, out); }

    private static class Voronoi extends Procedure {
        public Voronoi(BlobV in, BlobV out) {
            BoolVRef meet = tmp(new BoolVRef());
            call(in.meet(meet));
            not(meet, meet);

            call(in.grow(out));
            and(out, meet, out);
            or(in, out, out);
        }
    }
    public static Procedure voronoi(BlobV in, BlobV out) { return new Voronoi(in, out); }
    public Procedure voronoi(BlobV out) { return voronoi(this, out); }
    public Procedure voronoi() { return voronoi(this, this); }
}
