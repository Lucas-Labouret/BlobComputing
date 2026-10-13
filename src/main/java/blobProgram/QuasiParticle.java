package blobProgram;

import language.field.boolField.*;
import language.field.intField.IntV;
import language.instruction.Procedure;

/**
 * A QuasiParticle is a BlobV in which blobs have size 1, 2, or 3.
 * 3-particles share a common face, 2-particles share a common edge, and 1-particles are isolated.
 */
public class QuasiParticle extends BlobV {
    public QuasiParticle() { super(); }
    public QuasiParticle(BoolV state) { super(state); }

    public static QuasiParticle random() {return random(0); }
    public static QuasiParticle random(int sparsity) {
        BoolV cells = new BoolV().rand();
        for (int i = 0; i < sparsity; i++) {
            cells.and(cells, new BoolV().rand());
        }
        return new QuasiParticle(cells);
    }

    private static class OneParticle extends Procedure<QuasiParticle> {
        public OneParticle(QuasiParticle in) {
            QuasiParticle res = tmp(new QuasiParticle());

            BoolV noNeighbor = not(redOr(call(send(broadcastVe(in)))));
            set(and(in, noNeighbor), res);

            returnValue(res);
        }
    }
    public static Procedure<QuasiParticle> oneParticle(QuasiParticle in) { return new OneParticle(in); }
    public Procedure<QuasiParticle> oneParticle() { return oneParticle(this); }

    private static class TwoParticle extends Procedure<QuasiParticle> {
        public TwoParticle(QuasiParticle in) {
            QuasiParticle res = tmp(new QuasiParticle());

            IntV nbNeighbors = redAdd(call(send(broadcastVe(in))), 4);
            set(and(in, eq(nbNeighbors, IntV.of(1, 4))), res);

            returnValue(res);
        }
    }
    public static Procedure<QuasiParticle> twoParticle(QuasiParticle in) { return new TwoParticle(in); }
    public Procedure<QuasiParticle> twoParticle() { return twoParticle(this); }

    public static class ThreeParticle extends Procedure<QuasiParticle> {
        public ThreeParticle(QuasiParticle in) {
            QuasiParticle res = tmp(new QuasiParticle());

            BoolF centers = redAnd(transfer(broadcastVf(in)));
            set(redOr(transfer(broadcastFv(centers))), res);

            returnValue(res);
        }
    }
    public static Procedure<QuasiParticle> threeParticle(QuasiParticle in) { return new ThreeParticle(in); }
    public Procedure<QuasiParticle> threeParticle() { return threeParticle(this); }
}
