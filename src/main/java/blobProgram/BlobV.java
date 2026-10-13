package blobProgram;

import language.field.boolField.*;
import language.field.intField.IntEf;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.field.intField.IntVf;
import language.instruction.Procedure;
import medium.Medium;
import medium.locusS.Vertex;

/**
 * A BlobV is simply a BoolV augmented with several useful procedures for manipulating blobs in a medium.
 */
public class BlobV extends BoolV {
    public BlobV() {
        this(new BoolV().zeroes());
    }

    public BlobV(BoolV state) { super(state); }

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

    private static class SendB extends Procedure<BoolVe> {
        public SendB(BoolVe in) {
            returnValue(transfer(rotCW(rotCW(transfer(in)))));
        }
    }
    private static class SendI extends Procedure<IntVe> {
        public SendI(IntVe in) {
            returnValue(transfer(rotCW(rotCW(transfer(in)))));
        }
    }
    public static Procedure<BoolVe> send(BoolVe in) { return new SendB(in); }
    public static Procedure<IntVe>  send(IntVe in)  { return new SendI(in); }

    private static class ApexToEdgeB extends Procedure<BoolEf> {
        public ApexToEdgeB(BoolVf in) {
            returnValue(transfer(rotCW(rotCW(rotCW(transfer(in))))));
        }
    }
    private static class ApexToEdgeI extends Procedure<IntEf> {
        public ApexToEdgeI(IntVf in) {
            returnValue(transfer(rotCW(rotCW(rotCW(transfer(in))))));
        }
    }
    private static class EdgeToApexB extends Procedure<BoolVf> {
        public EdgeToApexB(BoolEf in) {
            returnValue(transfer(rotCW(rotCW(rotCW(transfer(in))))));
        }
    }
    private static class EdgeToApexI extends Procedure<IntVf> {
        public EdgeToApexI(IntEf in) {
            returnValue(transfer(rotCW(rotCW(rotCW(transfer(in))))));
        }
    }
    public static Procedure<BoolEf> apexToEdge(BoolVf in) { return new ApexToEdgeB(in); }
    public static Procedure<IntEf>  apexToEdge(IntVf in)  { return new ApexToEdgeI(in); }
    public static Procedure<BoolVf> edgeToApex(BoolEf in) { return new EdgeToApexB(in); }
    public static Procedure<IntVf>  edgeToApex(IntEf in)  { return new EdgeToApexI(in); }

    private static class Grow extends Procedure<BlobV> {
        public Grow(BlobV in) {
            BoolE middle = redOr(transfer(broadcastVe(in)));

            BlobV grow = tmp(new BlobV());
            set(redOr(transfer(broadcastEv(middle))), grow);

            returnValue(grow);
        }
    }
    public static Procedure<BlobV> grow(BlobV in) { return new Grow(in); }
    public Procedure<BlobV> grow() {return grow(this); }

    private static class GrowDebug extends Procedure<BoolV> {
        public GrowDebug(BoolV in) {
            BoolV start = new BoolV();
            set(in, start);
            show("BlobV", start);

            BoolVe veIn = broadcastVe(in);
            show("veIn", veIn);

            BoolEv evIn = transfer(veIn);
            show("evIn", evIn);

            BoolE middle = redOr(evIn);
            show("middle", middle);

            BoolEv evOut = broadcastEv(middle);
            show("evOut", evOut);

            BoolVe veOut = transfer(evOut);
            show("veOut", veOut);

            returnValue(redOr(veOut));
        }
    }
    public static Procedure<BoolV> growDebug(BoolV in) { return new GrowDebug(in); }

    private static class FrontierE extends Procedure<BoolE> {
        public FrontierE(BlobV in) {
            returnValue(redXor(transfer(broadcastVe(in))));
        }
    }
    public static Procedure<BoolE> frontierE(BlobV in) { return new FrontierE(in); }
    public Procedure<BoolE> frontierE() { return frontierE(this); }

    private static class FrontierV extends Procedure<BlobV> {
        public FrontierV(BlobV in) {
            BlobV res = tmp(new BlobV());
            set(and(call(in.grow()), not(in)), res);
            returnValue(res);
        }
    }
    public static Procedure<BlobV> frontierV(BlobV in) { return new FrontierV(in); }
    public Procedure<BlobV> frontierV() { return frontierV(this); }

    private static class OutVe extends Procedure<BoolVe> {
        public OutVe(BlobV in) {
            BoolVe frontierVe = transfer(broadcastEv(call(in.frontierE())));
            BoolVe ve = broadcastVe(in);
            returnValue(and(ve, frontierVe));
        }
    }
    public static Procedure<BoolVe> outVe(BlobV in) { return new OutVe(in); }
    public Procedure<BoolVe> outVe() { return outVe(this); }

    private static class BorderVe extends Procedure<BoolVe> {
        public BorderVe(BlobV in) {
            BoolV borderV = and(in, redOr(transfer(broadcastEv(call(in.frontierE())))));
            BoolVe borderVe = call(send(broadcastVe(borderV)));
            BoolVe inVe = broadcastVe(in);
            returnValue(and(inVe, borderVe));
        }
    }
    public static Procedure<BoolVe> borderVe(BlobV in) { return new BorderVe(in); }
    public Procedure<BoolVe> borderVe() { return borderVe(this); }

    private static class MeetE extends Procedure<BoolE> {
        public MeetE(BlobV in) {
            // Select edges that are between two frontier vertices
            BoolE candidates = redAnd(transfer(broadcastVe(call(in.frontierV()))));

            // Filter out edges between vertices belonging to the same frontier
            BoolF inFaces = redOr(transfer(broadcastVf(in)));
            BoolE inEdges = redOr(transfer(broadcastFe(inFaces)));

            set(and(candidates, not(inEdges)), candidates);

            // Filter out edges that have an apex outside the blob
            BoolE hasNonBlobApexE = redOr(call(apexToEdge(broadcastVf(not(in)))));

            returnValue(and(candidates, hasNonBlobApexE));
        }
    }
    public static Procedure<BoolE> meetE(BlobV in) { return new MeetE(in); }
    public Procedure<BoolE> meetE() { return meetE(this); }

    private static class ConnectedComponents extends Procedure<IntV> {
        public ConnectedComponents(BoolVe in, int nbits) {
            returnValue(rShift(redAdd(xor(rotCW(in), rotCCW(in)), nbits), 1));
        }
    }
    public static Procedure<IntV> connectedComponents(BoolVe in, int nbits) { return new ConnectedComponents(in, nbits); }

    private static class MeetV extends Procedure<BoolV> {
        public MeetV(BlobV in) {
            BoolVe frontierVe = transfer(broadcastEv(call(in.frontierE())));
            BoolV candidates = gt(call(connectedComponents(frontierVe, 4)), IntV.of(2, 4));
            returnValue(and(candidates, not(in)));
        }
    }
    public static Procedure<BoolV> meetV(BlobV in) { return new MeetV(in); }
    public Procedure<BoolV> meetV() { return meetV(this); }

    private static class Meet extends Procedure<BoolV> {
        public Meet(BlobV in) {
            BoolV meetV = call(in.meetV());
            BoolV meetE = redOr(transfer(broadcastEv(call(in.meetE()))));

            returnValue(or(meetV, meetE));
        }
    }
    public static Procedure<BoolV> meet(BlobV in) { return new Meet(in); }
    public Procedure<BoolV> meet() { return meet(this); }

    private static class Voronoi extends Procedure<BlobV> {
        public Voronoi(BlobV in) {
            BlobV res = tmp(new BlobV());

            BoolV newV = and(call(in.grow()), not(call(in.meet())));
            set(or(in, newV), res);

            returnValue(res);
        }
    }
    public static Procedure<BlobV> voronoi(BlobV in) { return new Voronoi(in); }
    public Procedure<BlobV> voronoi() { return voronoi(this); }
}
