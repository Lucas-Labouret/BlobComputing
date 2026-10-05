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

    private static class OneParticle extends Procedure {
        public OneParticle(QuasiParticle in, QuasiParticle out) {
            BoolVe ve = tmp(new BoolVe());
            broadcast(in, ve);
            call(send(ve, ve));

            redOr(ve, out);
            not(out, out);
            and(in, out, out);
        }
    }
    public static Procedure oneParticle(QuasiParticle in, QuasiParticle out) { return new OneParticle(in, out); }
    public Procedure oneParticle(QuasiParticle out) { return oneParticle(this, out); }

    private static class TwoParticle extends Procedure {
        public TwoParticle(QuasiParticle in, QuasiParticle out) {
            BoolVe ve = tmp(new BoolVe());
            broadcast(in, ve);
            call(send(ve, ve));

            IntV nbNeighbors = tmp(new IntV(2));
            redAdd(ve, nbNeighbors);
            eq(nbNeighbors, IntV.of(1, 2), out);
            and(in, out, out);
        }
    }
    public static Procedure twoParticle(QuasiParticle in, QuasiParticle out) { return new TwoParticle(in, out); }
    public Procedure twoParticle(QuasiParticle out) { return twoParticle(this, out); }

    public static class ThreeParticle extends Procedure {
        public <I extends QuasiParticle, O extends QuasiParticle> ThreeParticle(QuasiParticle in, QuasiParticle out) {
            BoolVf vf = tmp(new BoolVf());
            BoolFv fv = tmp(new BoolFv());
            BoolF f = tmp(new BoolF());

            broadcast(in, vf);
            transfer(vf, fv);
            redAnd(fv, f);
            broadcast(f, fv);
            transfer(fv, vf);
            redOr(vf, out);
        }
    }
    public static Procedure threeParticle(QuasiParticle in, QuasiParticle out) { return new ThreeParticle(in, out); }
    public Procedure threeParticle(QuasiParticle out) { return threeParticle(this, out); }
}
