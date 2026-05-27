package language.obj;

import language.Obj;
import language.instruction.Procedure;
import language.obj.field.boolField.fieldS.BoolV;
import language.ref.field.boolField.fieldS.*;
import language.ref.field.boolField.fieldT.*;

public class Rand extends Obj {
    private final BoolVRef state;
    private final NextState nextState;

    public Rand(BoolVRef seed){
        this.state = seed;
        this.nextState = new NextState();
    }

    public Rand() {
        this(BoolVRef.of(BoolV.rand()));
    }

    private class NextState extends Procedure {
        public NextState() {
            BoolVeRef ve = new BoolVeRef();
            BoolEvRef ev = new BoolEvRef();
            BoolERef e = new BoolERef();

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, e);
            broadcast(e, ev);
            transfer(ev, ve);
            redXor(ve, state);
        }
    }

    private class Next extends Procedure {
        public Next(BoolVRef res) {
            call(nextState);
            set(state, res);
        }
        public Next(BoolVeRef res) {
            call(nextState);

            BoolVfRef vf = new BoolVfRef();
            BoolFvRef fv = new BoolFvRef();
            BoolFRef f = new BoolFRef();
            BoolFeRef fe = new BoolFeRef();
            BoolEfRef ef = new BoolEfRef();
            BoolEvRef ev = new BoolEvRef();

            broadcast(state, vf);
            transfer(vf, fv);
            redXor(fv, f);
            broadcast(f, fe);
            transfer(fe, ef);
            rotCW(ef, ev);
            transfer(ev, res);
        }
        public Next(BoolVfRef res) {
            call(nextState);

            BoolVeRef ve = new BoolVeRef();
            BoolEvRef ev = new BoolEvRef();
            BoolERef e = new BoolERef();
            BoolEfRef ef = new BoolEfRef();
            BoolFeRef fe = new BoolFeRef();
            BoolFvRef fv = new BoolFvRef();

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, e);
            broadcast(e, ef);
            transfer(ef, fe);
            rotCW(fe, fv);
            transfer(fv, res);
        }

        public Next(BoolERef res) {
            call(nextState);

            BoolVeRef ve = new BoolVeRef();
            BoolEvRef ev = new BoolEvRef();

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, res);
        }
        public Next(BoolEvRef res) {
            call(nextState);

            BoolVeRef ve = new BoolVeRef();
            BoolEvRef ev1 = new BoolEvRef();
            BoolEvRef ev2 = new BoolEvRef();
            BoolERef e = new BoolERef();

            broadcast(state, ve);
            transfer(ve, ev1);

            call(nextState);

            broadcast(state, ve);
            transfer(ve, ev2);
            redXor(ev2, e);
            broadcast(e, ev2);

            xor(ev1, ev2, res);
        }
        public Next(BoolEfRef res) {
            call(nextState);

            BoolVfRef vf  = new BoolVfRef();
            BoolFvRef fv  = new BoolFvRef();
            BoolFRef f = new BoolFRef();
            BoolFeRef fe = new BoolFeRef();

            broadcast(state, vf);
            transfer(vf, fv);
            redXor(fv, f);
            broadcast(f, fe);
            transfer(fe, res);
        }

        public Next(BoolFRef res) {
            call(nextState);

            BoolVfRef vf = new BoolVfRef();
            BoolFvRef fv = new BoolFvRef();

            broadcast(state, vf);
            transfer(vf, fv);
            redXor(fv, res);
        }
        public Next(BoolFvRef res) {
            call(nextState);

            BoolVeRef ve = new BoolVeRef();
            BoolEvRef ev = new BoolEvRef();
            BoolERef e = new BoolERef();
            BoolEfRef ef = new BoolEfRef();
            BoolFeRef fe = new BoolFeRef();

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, e);
            broadcast(e, ef);
            transfer(ef, fe);
            rotCW(fe, res);
        }
        public Next(BoolFeRef res) {
            call(nextState);

            BoolVeRef ve = new BoolVeRef();
            BoolEvRef ev = new BoolEvRef();
            BoolERef e = new BoolERef();
            BoolEfRef ef = new BoolEfRef();

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, e);
            broadcast(e, ef);
            transfer(ef, res);
        }
    }

    public Procedure next(BoolVRef res) { return new Next(res); }
    public Procedure next(BoolVeRef res) { return new Next(res); }
    public Procedure next(BoolVfRef res) { return new Next(res); }

    public Procedure next(BoolERef res) { return new Next(res); }
    public Procedure next(BoolEvRef res) { return new Next(res); }
    public Procedure next(BoolEfRef res) { return new Next(res); }

    public Procedure next(BoolFRef res) { return new Next(res); }
    public Procedure next(BoolFvRef res) { return new Next(res); }
    public Procedure next(BoolFeRef res) { return new Next(res); }

    private class ShowRand extends Procedure {
        public ShowRand() {
            BoolVRef v =  new BoolVRef();
            BoolVeRef ve = new BoolVeRef();
            BoolVfRef vf = new BoolVfRef();

            BoolERef e = new BoolERef();
            BoolEvRef ev = new BoolEvRef();
            BoolEfRef ef = new BoolEfRef();

            BoolFRef f = new BoolFRef();
            BoolFvRef fv = new BoolFvRef();
            BoolFeRef fe = new BoolFeRef();

            call(next(v));
            call(next(ve));
            call(next(vf));

            call(next(e));
            call(next(ev));
            call(next(ef));

            call(next(f));
            call(next(fv));
            call(next(fe));

            show("v", v);
            show("ve", ve);
            show("vf", vf);

            show("e", e);
            show("ev", ev);
            show("ef", ef);

            show("f", f);
            show("fv", fv);
            show("fe", fe);
        }
    }

    public Procedure showRand() {
        return new ShowRand();
    }

    @Override
    public Obj copy() {
        return new Rand(state.copy());
    }
}
