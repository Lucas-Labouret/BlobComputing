package blobProgram;

import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.IntVRef;
import language.instruction.Procedure;

public class QuasiParticle extends BlobV {
    public QuasiParticle() { super(); }
    public QuasiParticle(BoolV state) { super(state); }

    public static QuasiParticle rand() {return rand(0); }
    public static QuasiParticle rand(int sparsity) {
        BoolV cells = BoolV.rand();
        for (int i = 0; i < sparsity; i++) {
            cells = BoolV.and(cells, BoolV.rand());
        }
        return new QuasiParticle(cells);
    }

    private static class OneParticle extends Procedure {
        public OneParticle(QuasiParticle in, QuasiParticle out) {
            BoolVeRef ve = tmp(new BoolVeRef());
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
            BoolVeRef ve = tmp(new BoolVeRef());
            broadcast(in, ve);
            call(send(ve, ve));

            IntVRef nbNeighbors = tmp(new IntVRef(new IntV(4)));
            redAdd(ve, nbNeighbors);

            BoolVRef oneNeighbor = tmp(new BoolVRef());
            eq(nbNeighbors, new IntVRef(IntV.of(1, 4)), oneNeighbor);

            and(in, oneNeighbor, out);
        }
    }
    public static Procedure twoParticle(QuasiParticle in, QuasiParticle out) { return new TwoParticle(in, out); }
    public Procedure twoParticle(QuasiParticle out) { return twoParticle(this, out); }

    public static class ThreeParticle extends Procedure {
        public <I extends QuasiParticle, O extends QuasiParticle> ThreeParticle(QuasiParticle in, QuasiParticle out) {
            BoolVfRef vf = tmp(new BoolVfRef());
            BoolFvRef fv = tmp(new BoolFvRef());
            BoolFRef f = tmp(new BoolFRef());

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
