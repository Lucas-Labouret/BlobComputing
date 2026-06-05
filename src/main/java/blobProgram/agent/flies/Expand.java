package blobProgram.agent.flies;

import blobProgram.QuasiParticle;
import language.instruction.Procedure;
import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.IntVRef;
import language.fieldRef.intField.IntVeRef;

public class Expand extends Force {
    private final QuasiParticle state;

    public Expand(QuasiParticle state) {
        super(new IntVRef(IntV.of(0, Force.prioRandBits)));
        this.state = state;
    }

    private class _Compute extends Procedure {
        public _Compute(BoolVRef target) {
            QuasiParticle one = tmp(new QuasiParticle());
            QuasiParticle two = tmp(new QuasiParticle());

            call(state.oneParticle(one));
            call(state.twoParticle(two));

            BoolVRef meetV = tmp(new BoolVRef());
            BoolVeRef forbiddenVe = tmp(new BoolVeRef());
            call(state.meetV(meetV));
            call(BlobV.send(meetV, forbiddenVe));

            BoolVeRef oneOut = tmp(new BoolVeRef());
            broadcast(one, oneOut);

            IntVeRef min = new IntVeRef(IntVe.minValue(5));
            IntVeRef randVe = new IntVeRef(new IntVe(5));
            call(rand.next(randVe));



            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef e = tmp(new BoolERef());
            BoolEfRef ef = tmp(new BoolEfRef());
            BoolFeRef fe = tmp(new BoolFeRef());
            BoolFvRef fv = tmp(new BoolFvRef());
            BoolVfRef vf = tmp(new BoolVfRef());
            BoolVeRef twoOut = tmp(new BoolVeRef());

            broadcast(two, ve);
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
}
