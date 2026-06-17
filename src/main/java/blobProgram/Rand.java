package blobProgram;

import language.instruction.Procedure;
import language.field.boolField.*;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.*;

public class Rand {
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
            if (!initialized) throw new IllegalStateException("Rand has not been initialized");

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef e = tmp(new BoolERef());

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

            BoolVfRef vf = tmp(new BoolVfRef());
            BoolFvRef fv = tmp(new BoolFvRef());
            BoolFRef f = tmp(new BoolFRef());
            BoolFeRef fe = tmp(new BoolFeRef());
            BoolEfRef ef = tmp(new BoolEfRef());
            BoolEvRef ev = tmp(new BoolEvRef());

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

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef e = tmp(new BoolERef());
            BoolEfRef ef = tmp(new BoolEfRef());
            BoolFeRef fe = tmp(new BoolFeRef());
            BoolFvRef fv = tmp(new BoolFvRef());

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

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, res);
        }
        public Next(BoolEvRef res) {
            call(nextState);

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev1 = tmp(new BoolEvRef());
            BoolEvRef ev2 = tmp(new BoolEvRef());
            BoolERef e = tmp(new BoolERef());

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

            BoolVfRef vf  = tmp(new BoolVfRef());
            BoolFvRef fv  = tmp(new BoolFvRef());
            BoolFRef f = tmp(new BoolFRef());
            BoolFeRef fe = tmp(new BoolFeRef());

            broadcast(state, vf);
            transfer(vf, fv);
            redXor(fv, f);
            broadcast(f, fe);
            transfer(fe, res);
        }

        public Next(BoolFRef res) {
            call(nextState);

            BoolVfRef vf = tmp(new BoolVfRef());
            BoolFvRef fv = tmp(new BoolFvRef());

            broadcast(state, vf);
            transfer(vf, fv);
            redXor(fv, res);
        }
        public Next(BoolFvRef res) {
            call(nextState);

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef e = tmp(new BoolERef());
            BoolEfRef ef = tmp(new BoolEfRef());
            BoolFeRef fe = tmp(new BoolFeRef());

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, e);
            broadcast(e, ef);
            transfer(ef, fe);
            rotCW(fe, res);
        }
        public Next(BoolFeRef res) {
            call(nextState);

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolERef e = tmp(new BoolERef());
            BoolEfRef ef = tmp(new BoolEfRef());

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, e);
            broadcast(e, ef);
            transfer(ef, res);
        }

        public Next(IntVRef res) { this(res, ""); }
        public Next(IntVRef res, String option) {
            BoolVRef[] bits = res.get().getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolVRef(BoolV.zeroes()), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntVeRef res) { this(res, ""); }
        public Next(IntVeRef res, String option) {
            BoolVeRef[] bits = res.get().getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolVeRef(BoolVe.zeroes()), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntVfRef res) { this(res, ""); }
        public Next(IntVfRef res, String option) {
            BoolVfRef[] bits = res.get().getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolVfRef(BoolVf.zeroes()), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntERef res) { this(res, ""); }
        public Next(IntERef res, String option) {
            BoolERef[] bits = res.get().getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolERef(BoolE.zeroes()), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntEvRef res) { this(res, ""); }
        public Next(IntEvRef res, String option) {
            BoolEvRef[] bits = res.get().getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolEvRef(BoolEv.zeroes()), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntEfRef res) { this(res, ""); }
        public Next(IntEfRef res, String option) {
            BoolEfRef[] bits = res.get().getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolEfRef(BoolEf.zeroes()), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntFRef res) { this(res, ""); }
        public Next(IntFRef res, String option) {
            BoolFRef[] bits = res.get().getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolFRef(BoolF.zeroes()), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntFvRef res) { this(res, ""); }
        public Next(IntFvRef res, String option) {
            BoolFvRef[] bits = res.get().getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolFvRef(BoolFv.zeroes()), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntFeRef res) { this(res, ""); }
        public Next(IntFeRef res, String option) {
            BoolFeRef[] bits = res.get().getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolFeRef(BoolFe.zeroes()), bits[0]);
            else call(next(bits[0]));
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
}
