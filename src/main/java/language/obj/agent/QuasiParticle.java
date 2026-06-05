package language.obj.agent;

import language.Ref;
import language.instruction.Procedure;
import language.obj.field.intField.IntV;
import language.ref.agent.QuasiParticleRef;
import language.ref.field.boolField.*;
import language.ref.field.intField.IntVRef;

public class QuasiParticle extends BlobV {
    private final QuasiParticleRef thisRef = QuasiParticleRef.of(this);

    public QuasiParticle() { super(); }
    public QuasiParticle(BoolVRef state) { super(state); }

    private static class OneParticle extends Procedure {
        public <I extends QuasiParticle, O extends QuasiParticle> OneParticle(Ref<I> in, Ref<O> out) {
            BoolVeRef ve = tmp(new BoolVeRef());
            call(send(in.get().state, ve));

            redOr(ve, out.get().state);
            not(out.get().state, out.get().state);
            and(in.get().state, out.get().state, out.get().state);
        }
    }
    public static <I extends QuasiParticle, O extends QuasiParticle>
        Procedure oneParticle(Ref<I> in, Ref<O> out) { return new OneParticle(in, out); }
    public <O extends QuasiParticle> Procedure oneParticle(Ref<O> out) { return oneParticle(thisRef, out); }

    private static class TwoParticle extends Procedure {
        public <I extends QuasiParticle, O extends QuasiParticle> TwoParticle(Ref<I> in, Ref<O> out) {
            BoolVeRef ve = tmp(new BoolVeRef());
            call(send(in.get().state, ve));

            IntVRef count = IntVRef.of(new IntV(4));
            redAdd(ve, count);
            eq(count, IntVRef.of(IntV.of(1, 4)), out.get().state);
        }
    }
    public static <I extends QuasiParticle, O extends QuasiParticle>
        Procedure twoParticle(Ref<I> in, Ref<O> out) { return new TwoParticle(in, out); }
    public <O extends QuasiParticle> Procedure twoParticle(Ref<O> out) { return twoParticle(thisRef, out); }

    public static class ThreeParticle extends Procedure {
        public <I extends QuasiParticle, O extends QuasiParticle> ThreeParticle(Ref<I> in, Ref<O> out) {
            BoolVfRef vf = tmp(new BoolVfRef());
            BoolFvRef fv = tmp(new BoolFvRef());
            BoolFRef f = tmp(new BoolFRef());

            broadcast(in.get().state, vf);
            transfer(vf, fv);
            redAnd(fv, f);
            broadcast(f, fv);
            transfer(fv, vf);
            redOr(vf, out.get().state);
        }
    }
    public static <I extends QuasiParticle, O extends QuasiParticle>
        Procedure threeParticle(Ref<I> in, Ref<O> out) { return new ThreeParticle(in, out); }
    public <O extends QuasiParticle> Procedure threeParticle(Ref<O> out) { return threeParticle(thisRef, out); }
}
