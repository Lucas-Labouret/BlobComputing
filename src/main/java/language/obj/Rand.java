package language.obj;

import language.Obj;
import language.instruction.Procedure;
import language.obj.field.boolField.*;
import language.ref.field.boolField.*;
import language.ref.field.intField.*;

public class Rand extends Obj {
    private final static BoolVRef state = new BoolVRef();
    private final NextState nextState = new NextState();

    private static boolean initialized = false;
    public static void init(BoolV seed) {
        if (initialized) throw new IllegalStateException("Rand has already been initialized");
        initialized = true;
        state.set(seed);
    }
    public static void init() {
        init(BoolV.rand());
    }

    public Rand() {

    }

    private static class NextState extends Procedure {
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

        public Next(IntVRef res) { this(res, ""); }
        public Next(IntVRef res, String option) {
            BoolVRef[] bits = new BoolVRef[res.get().n + 1];
            for (int i = 1; i < bits.length; i++) {
                bits[i] = tmp(new BoolVRef());
                call(next(bits[i]));
            }

            bits[0] = tmp(new BoolVRef());
            if (option.equals("0+")) set(BoolVRef.of(BoolV.zeroes()), bits[0]);
            else call(next(bits[0]));
            join(bits, res);
        }

        public Next(IntVeRef res) { this(res, ""); }
        public Next(IntVeRef res, String option) {
            BoolVeRef[] bits = new BoolVeRef[res.get().n + 1];
            for (int i = 1; i < bits.length; i++) {
                bits[i] = tmp(new BoolVeRef());
                call(next(bits[i]));
            }

            bits[0] = tmp(new BoolVeRef());
            if (option.equals("0+")) set(BoolVeRef.of(BoolVe.zeroes()), bits[0]);
            else call(next(bits[0]));
            join(bits, res);
        }

        public Next(IntVfRef res) { this(res, ""); }
        public Next(IntVfRef res, String option) {
            BoolVfRef[] bits = new BoolVfRef[res.get().n + 1];
            for (int i = 1; i < bits.length; i++) {
                bits[i] = tmp(new BoolVfRef());
                call(next(bits[i]));
            }

            bits[0] = tmp(new BoolVfRef());
            if (option.equals("0+")) set(BoolVfRef.of(BoolVf.zeroes()), bits[0]);
            else call(next(bits[0]));
            join(bits, res);
        }

        public Next(IntERef res) { this(res, ""); }
        public Next(IntERef res, String option) {
            BoolERef[] bits = new BoolERef[res.get().n + 1];
            for (int i = 1; i < bits.length; i++) {
                bits[i] = tmp(new BoolERef());
                call(next(bits[i]));
            }

            bits[0] = tmp(new BoolERef());
            if (option.equals("0+")) set(BoolERef.of(BoolE.zeroes()), bits[0]);
            else call(next(bits[0]));
            join(bits, res);
        }

        public Next(IntEvRef res) { this(res, ""); }
        public Next(IntEvRef res, String option) {
            BoolEvRef[] bits = new BoolEvRef[res.get().n + 1];
            for (int i = 1; i < bits.length; i++) {
                bits[i] = tmp(new BoolEvRef());
                call(next(bits[i]));
            }

            bits[0] = tmp(new BoolEvRef());
            if (option.equals("0+")) set(BoolEvRef.of(BoolEv.zeroes()), bits[0]);
            else call(next(bits[0]));
            join(bits, res);
        }

        public Next(IntEfRef res) { this(res, ""); }
        public Next(IntEfRef res, String option) {
            BoolEfRef[] bits = new BoolEfRef[res.get().n + 1];
            for (int i = 1; i < bits.length; i++) {
                bits[i] = tmp(new BoolEfRef());
                call(next(bits[i]));
            }

            bits[0] = tmp(new BoolEfRef());
            if (option.equals("0+")) set(BoolEfRef.of(BoolEf.zeroes()), bits[0]);
            else call(next(bits[0]));
            join(bits, res);
        }

        public Next(IntFRef res) { this(res, ""); }
        public Next(IntFRef res, String option) {
            BoolFRef[] bits = new BoolFRef[res.get().n + 1];
            for (int i = 1; i < bits.length; i++) {
                bits[i] = tmp(new BoolFRef());
                call(next(bits[i]));
            }

            bits[0] = tmp(new BoolFRef());
            if (option.equals("0+")) set(BoolFRef.of(BoolF.zeroes()), bits[0]);
            else call(next(bits[0]));
            join(bits, res);
        }

        public Next(IntFvRef res) { this(res, ""); }
        public Next(IntFvRef res, String option) {
            BoolFvRef[] bits = new BoolFvRef[res.get().n + 1];
            for (int i = 1; i < bits.length; i++) {
                bits[i] = tmp(new BoolFvRef());
                call(next(bits[i]));
            }

            bits[0] = tmp(new BoolFvRef());
            if (option.equals("0+")) set(BoolFvRef.of(BoolFv.zeroes()), bits[0]);
            else call(next(bits[0]));
            join(bits, res);
        }

        public Next(IntFeRef res) { this(res, ""); }
        public Next(IntFeRef res, String option) {
            BoolFeRef[] bits = new BoolFeRef[res.get().n + 1];
            for (int i = 1; i < bits.length; i++) {
                bits[i] = tmp(new BoolFeRef());
                call(next(bits[i]));
            }

            bits[0] = tmp(new BoolFeRef());
            if (option.equals("0+")) set(BoolFeRef.of(BoolFe.zeroes()), bits[0]);
            else call(next(bits[0]));
            join(bits, res);
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

    public Procedure next(IntVRef res) { return new Next(res); }
    public Procedure next0p(IntVRef res) { return new Next(res, "0+"); }
    public Procedure next(IntVeRef res) { return new Next(res); }
    public Procedure next0p(IntVeRef res) { return new Next(res, "0+"); }
    public Procedure next(IntVfRef res) { return new Next(res); }
    public Procedure next0p(IntVfRef res) { return new Next(res, "0+"); }


    public Procedure next(IntERef res) { return new Next(res); }
    public Procedure next0p(IntERef res) { return new Next(res, "0+"); }
    public Procedure next(IntEvRef res) { return new Next(res); }
    public Procedure next0p(IntEvRef res) { return new Next(res, "0+"); }
    public Procedure next(IntEfRef res) { return new Next(res); }
    public Procedure next0p(IntEfRef res) { return new Next(res, "0+"); }

    public Procedure next(IntFRef res) { return new Next(res); }
    public Procedure next0p(IntFRef res) { return new Next(res, "0+"); }
    public Procedure next(IntFvRef res) { return new Next(res); }
    public Procedure next0p(IntFvRef res) { return new Next(res, "0+"); }
    public Procedure next(IntFeRef res) { return new Next(res); }
    public Procedure next0p(IntFeRef res) { return new Next(res, "0+"); }

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
    public Rand copy() {
        return new Rand(state.copy());
    }
}
