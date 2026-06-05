package language.obj.agent.flies;

import language.instruction.Procedure;
import language.obj.agent.BlobV;
import language.obj.agent.Force;
import language.obj.field.boolField.BoolVf;
import language.obj.field.intField.IntV;
import language.obj.field.intField.IntVe;
import language.ref.agent.QuasiParticleRef;
import language.ref.field.boolField.*;
import language.ref.field.intField.IntVRef;
import language.ref.field.intField.IntVeRef;

public class Expand extends Force {
    private final QuasiParticleRef state;

    public Expand(QuasiParticleRef state) {
        super(IntVRef.of(IntV.of(0, Force.prioRandBits)));
        this.state = state;
    }

    private class _Compute extends Procedure {
        public _Compute(BoolVRef target) {
            QuasiParticleRef one = tmp(new QuasiParticleRef());
            QuasiParticleRef two = tmp(new QuasiParticleRef());

            call(state.get().oneParticle(one));
            call(state.get().twoParticle(two));

            BoolVRef meetV = tmp(new BoolVRef());
            BoolVeRef forbiddenVe = tmp(new BoolVeRef());
            call(state.get().meetV(meetV));
            call(BlobV.send(meetV, forbiddenVe));

            BoolVeRef oneOut = tmp(new BoolVeRef());
            broadcast(one.get().state, oneOut);

            IntVeRef min = IntVeRef.of(IntVe.minValue(5));
            IntVeRef randVe = IntVeRef.of(new IntVe(5));
            call(rand.next(randVe));



            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef e = tmp(new BoolERef());
            BoolEfRef ef = tmp(new BoolEfRef());
            BoolFeRef fe = tmp(new BoolFeRef());
            BoolFvRef fv = tmp(new BoolFvRef());
            BoolVfRef vf = tmp(new BoolVfRef());
            BoolVeRef twoOut = tmp(new BoolVeRef());

            broadcast(two.get().state, ve);
            transfer(ve, ev);
            redAnd(ev, e);
            broadcast(e, ef);
            transfer(ef, fe);
            rotCW(fe, fv);
            rotCW(fv, fe);
            rotCW(fe, fv);
            transfer(fv, vf);
            rotCW(vf, twoOut);
            rotCCW(vf, ve);
            or(twoOut, ve, ve);
            transfer(ve, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, twoOut);

            BoolVeRef out = tmp(new BoolVeRef());
            or(oneOut, twoOut, out);

            transfer(out, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, out);
            redOr(out, target);
            and(target, meetV, target);
        }
    }

    @Override
    public Procedure _compute(BoolVRef target) {
        return new _Compute(target);
    }

    @Override
    public Expand copy() {
        return new Expand(state.copy());
    }
}
