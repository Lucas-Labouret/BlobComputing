package blobProgram;

import language.field.boolField.*;
import language.field.intField.IntEf;
import language.field.intField.IntEv;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.instruction.Procedure;
import medium.Medium;
import medium.locusS.Vertex;

/**
 * A BlobV is simply a BoolV augmented with several useful procedures for manipulating blobs in a medium.
 */
public class BlobV extends BoolV {
    protected final BoolV init;

    public BlobV() {
        this(new BoolV().zeroes());
    }

    public BlobV(BoolV state) {
        super(state);
        this.init = new BoolV(state.copy());
    }

    public static BlobV randOne(Medium medium) {
        Vertex randomVertex = medium.vertices.toArray(new Vertex[0])[(int)(Math.random() * medium.vertices.size())];
        BoolV randomBoolV = new BoolV().zeroes();
        randomBoolV.setBit(randomVertex, true);
        return new BlobV(randomBoolV);
    }

    public static BlobV random() {return random(0); }
    public static BlobV random(int sparsity) {
        BoolV cells = new BoolV().rand();
        for (int i = 0; i < sparsity; i++) {
            cells.and(cells, new BoolV().rand());
        }
        return new BlobV(cells);
    }

    private static class Send extends Procedure {
        public Send(BoolVe in, BoolVe out) {
            BoolEv ev = tmp(new BoolEv());
            BoolEf ef = tmp(new BoolEf());

            transfer(in, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, out);
        }

        public Send(IntVe in, IntVe out) {
            IntEv ev = tmp(new IntEv(in.n));
            IntEf ef = tmp(new IntEf(in.n));

            transfer(in, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, out);
        }
    }
    public static Procedure send(BoolVe in, BoolVe out) { return new Send(in, out); }
    public static Procedure send(IntVe in, IntVe out) { return new Send(in, out); }

    private static class Grow extends Procedure {
        public Grow(BlobV in, BlobV out) {
            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());
            BoolE middle = tmp(new BoolE());

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
        public GrowDebug(BoolV in, BoolV out) {
            BoolV start = new BoolV();
            set(in, start);
            show("BlobV", start);

            BoolVe veIn = new BoolVe();
            BoolEv evIn = new BoolEv();
            BoolE middle = new BoolE();
            BoolEv evOut = new BoolEv();
            BoolVe veOut = new BoolVe();

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
    public static Procedure growDebug(BoolV in, BoolV out) { return new GrowDebug(in, out); }

    private static class FrontierE extends Procedure {
        public FrontierE(BlobV in, BoolE frontier) {
            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());

            broadcast(in, ve);
            transfer(ve, ev);
            redXor(ev, frontier);
        }
    }
    public static Procedure frontierE(BlobV in, BoolE frontier) { return new FrontierE(in, frontier); }
    public Procedure frontierE(BoolE frontier) { return frontierE(this, frontier); }

    private static class FrontierV extends Procedure {
        public FrontierV(BlobV in, BoolV frontier) {
            BoolV notIn = tmp(new BoolV());
            BlobV grow = tmp(new BlobV());

            not(in, notIn);
            call(in.grow(grow));
            and(grow, notIn, frontier);
        }
    }
    public static Procedure frontierV(BlobV in, BoolV frontier) { return new FrontierV(in, frontier); }
    public Procedure frontierV(BoolV frontier) { return frontierV(this, frontier); }

    private static class OutVe extends Procedure {
        public OutVe(BlobV in, BoolVe out) {
            BoolE frontierE = tmp(new BoolE());
            BoolEv ev = tmp(new BoolEv());
            BoolVe frontierVe = tmp(new BoolVe());

            call(in.frontierE(frontierE));
            broadcast(frontierE, ev);
            transfer(ev, frontierVe);

            BoolVe ve = tmp(new BoolVe());
            broadcast(in, ve);

            and(ve, frontierVe, out);
        }
    }
    public static Procedure outVe(BlobV in, BoolVe out) { return new OutVe(in, out); }
    public Procedure outVe(BoolVe out) { return outVe(this, out); }

    private static class BorderVe extends Procedure {
        public BorderVe(BlobV in, BoolVe out) {
            BoolE frontierE = tmp(new BoolE());
            BoolEv ev = tmp(new BoolEv());
            BoolVe ve = tmp(new BoolVe());

            call(in.frontierE(frontierE));
            broadcast(frontierE, ev);
            transfer(ev, ve);

            BoolV borderV = tmp(new BoolV());
            redOr(ve, borderV);
            and(borderV, in, borderV);

            BoolEf ef = tmp(new BoolEf());
            broadcast(borderV, ve);
            transfer(ve, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, ve);

            BoolVe inVe = tmp(new BoolVe());
            broadcast(in, inVe);
            and(inVe, ve, out);
        }
    }
    public static Procedure borderVe(BlobV in, BoolVe out) { return new BorderVe(in, out); }
    public Procedure borderVe(BoolVe out) { return borderVe(this, out); }

    private static class MeetE extends Procedure {
        public MeetE(BlobV in, BoolE out) {
            // Select edges that are between two frontier vertices
            BoolV frontierV = tmp(new BoolV());
            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());

            call(in.frontierV(frontierV));
            broadcast(frontierV, ve);
            transfer(ve, ev);
            redAnd(ev, out);

            // Filter out edges between vertices belonging to the same frontier
            BoolVf vf = tmp(new BoolVf());
            BoolFv fv = tmp(new BoolFv());
            BoolF f = tmp(new BoolF());
            BoolFe fe = tmp(new BoolFe());
            BoolEf ef = tmp(new BoolEf());

            BoolE frontierInteriorE = tmp(new BoolE());
            BoolE frontierInteriorEInv = tmp(new BoolE());

            broadcast(in, vf);
            transfer(vf, fv);
            redOr(fv, f);
            broadcast(f, fe);
            transfer(fe, ef);
            redOr(ef, frontierInteriorE);
            not(frontierInteriorE, frontierInteriorEInv);

            and(out, frontierInteriorEInv, out);

            // Filter out edges that have an apex outside the blob
            BlobV notIn = tmp(new BlobV());
            BoolE hasNonBlobApexE = tmp(new BoolE());

            not(in, notIn);
            broadcast(notIn, vf);
            transfer(vf, fv);
            rotCW(fv, fe);
            rotCW(fe, fv);
            rotCW(fv, fe);
            transfer(fe, ef);
            redOr(ef, hasNonBlobApexE);

            and(out, hasNonBlobApexE, out);
        }
    }
    public static Procedure meetE(BlobV in, BoolE out) { return new MeetE(in, out); }
    public Procedure meetE(BoolE out) { return meetE(this, out); }

    private static class ConnectedComponents extends Procedure {
        public ConnectedComponents(BoolVe in, IntV out) {
            BoolVf cw = tmp(new BoolVf());
            BoolVf ccw = tmp(new BoolVf());
            BoolVf vf = tmp(new BoolVf());

            rotCW(in, cw);
            rotCCW(in, ccw);
            xor(cw, ccw, vf);

            redAdd(vf, out);
            rShift(out, out, 1);
        }
    }
    public static Procedure connectedComponents(BoolVe in, IntV out) { return new ConnectedComponents(in, out); }

    private static class MeetV extends Procedure {
        public MeetV(BlobV in, BoolV out) {
            BoolE frontierE = tmp(new BoolE());
            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());

            call(in.frontierE(frontierE));
            broadcast(frontierE, ev);
            transfer(ev, ve);

            IntV connectedComponents = tmp(new IntV(4));
            call(connectedComponents(ve, connectedComponents));
            gt(connectedComponents, IntV.of(2, 4), out);

            BoolV notIn = tmp(new BoolV());
            not(in, notIn);
            and(out, notIn, out);
        }
    }
    public static Procedure meetV(BlobV in, BoolV out) { return new MeetV(in, out); }
    public Procedure meetV(BoolV out) { return meetV(this, out); }

    private static class Meet extends Procedure {
        public Meet(BlobV in, BoolV out) {
            BoolE meetE = tmp(new BoolE());
            BoolV meetV = tmp(new BoolV());

            call(in.meetE(meetE));
            call(in.meetV(meetV));

            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());

            broadcast(meetE, ev);
            transfer(ev, ve);
            redOr(ve, out);

            or(out, meetV, out);
        }
    }
    public static Procedure meet(BlobV in, BoolV out) { return new Meet(in, out); }
    public Procedure meet(BoolV out) { return meet(this, out); }

    private static class Voronoi extends Procedure {
        public Voronoi(BlobV in, BlobV out) {
            BoolV meet = tmp(new BoolV());
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
