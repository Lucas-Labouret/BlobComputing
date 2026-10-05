package blobProgram;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.Procedure;

/**
 * A pseudorandom number generator using successive XOR reduction to generate a new state from the previous state.
 * It uses an initial seed to start the random number generation, then generates new pseudorandom states deterministically.
 */
public class Rand {
    private final static BoolV state = new BoolV();
    private final NextState nextState = new NextState();

    private static boolean initialized = false;
    public static void init(BoolV seed) {
        if (initialized) throw new IllegalStateException("Rand has already been initialized");
        initialized = true;
        state.set(seed);
    }
    public static void init() {
        init(new BoolV().rand());
    }

    public Rand() {}

    private static class NextState extends Procedure {
        public NextState() {
            if (!initialized) throw new IllegalStateException("Rand has not been initialized");

            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());
            BoolE e = tmp(new BoolE());

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, e);
            broadcast(e, ev);
            transfer(ev, ve);
            redXor(ve, state);
        }
    }

    private class Next extends Procedure {
        public Next(BoolV res) {
            call(nextState);
            set(state, res);
        }
        public Next(BoolVe res) {
            call(nextState);

            BoolVf vf = tmp(new BoolVf());
            BoolFv fv = tmp(new BoolFv());
            BoolF f = tmp(new BoolF());
            BoolFe fe = tmp(new BoolFe());
            BoolEf ef = tmp(new BoolEf());
            BoolEv ev = tmp(new BoolEv());

            broadcast(state, vf);
            transfer(vf, fv);
            redXor(fv, f);
            broadcast(f, fe);
            transfer(fe, ef);
            rotCW(ef, ev);
            transfer(ev, res);
        }
        public Next(BoolVf res) {
            call(nextState);

            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());
            BoolE e = tmp(new BoolE());
            BoolEf ef = tmp(new BoolEf());
            BoolFe fe = tmp(new BoolFe());
            BoolFv fv = tmp(new BoolFv());

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, e);
            broadcast(e, ef);
            transfer(ef, fe);
            rotCW(fe, fv);
            transfer(fv, res);
        }

        public Next(BoolE res) {
            call(nextState);

            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, res);
        }
        public Next(BoolEv res) {
            call(nextState);

            BoolVe ve = tmp(new BoolVe());
            BoolEv ev1 = tmp(new BoolEv());
            BoolEv ev2 = tmp(new BoolEv());
            BoolE e = tmp(new BoolE());

            broadcast(state, ve);
            transfer(ve, ev1);

            call(nextState);

            broadcast(state, ve);
            transfer(ve, ev2);
            redXor(ev2, e);
            broadcast(e, ev2);

            xor(ev1, ev2, res);
        }
        public Next(BoolEf res) {
            call(nextState);

            BoolVf vf  = tmp(new BoolVf());
            BoolFv fv  = tmp(new BoolFv());
            BoolF f = tmp(new BoolF());
            BoolFe fe = tmp(new BoolFe());

            broadcast(state, vf);
            transfer(vf, fv);
            redXor(fv, f);
            broadcast(f, fe);
            transfer(fe, res);
        }

        public Next(BoolF res) {
            call(nextState);

            BoolVf vf = tmp(new BoolVf());
            BoolFv fv = tmp(new BoolFv());

            broadcast(state, vf);
            transfer(vf, fv);
            redXor(fv, res);
        }
        public Next(BoolFv res) {
            call(nextState);

            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());
            BoolE e = tmp(new BoolE());
            BoolEf ef = tmp(new BoolEf());
            BoolFe fe = tmp(new BoolFe());

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, e);
            broadcast(e, ef);
            transfer(ef, fe);
            rotCW(fe, res);
        }
        public Next(BoolFe res) {
            call(nextState);

            BoolVe ve = tmp(new BoolVe());
            BoolEv ev = tmp(new BoolEv());
            BoolE e = tmp(new BoolE());
            BoolEf ef = tmp(new BoolEf());

            broadcast(state, ve);
            transfer(ve, ev);
            redXor(ev, e);
            broadcast(e, ef);
            transfer(ef, res);
        }

        public Next(IntV res) { this(res, ""); }
        public Next(IntV res, String option) {
            BoolV[] bits = res.getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolV().zeroes(), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntVe res) { this(res, ""); }
        public Next(IntVe res, String option) {
            BoolVe[] bits = res.getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolVe().zeroes(), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntVf res) { this(res, ""); }
        public Next(IntVf res, String option) {
            BoolVf[] bits = res.getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolVf().zeroes(), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntE res) { this(res, ""); }
        public Next(IntE res, String option) {
            BoolE[] bits = res.getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolE().zeroes(), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntEv res) { this(res, ""); }
        public Next(IntEv res, String option) {
            BoolEv[] bits = res.getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolEv().zeroes(), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntEf res) { this(res, ""); }
        public Next(IntEf res, String option) {
            BoolEf[] bits = res.getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolEf().zeroes(), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntF res) { this(res, ""); }
        public Next(IntF res, String option) {
            BoolF[] bits = res.getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolF().zeroes(), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntFv res) { this(res, ""); }
        public Next(IntFv res, String option) {
            BoolFv[] bits = res.getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolFv().zeroes(), bits[0]);
            else call(next(bits[0]));
        }

        public Next(IntFe res) { this(res, ""); }
        public Next(IntFe res, String option) {
            BoolFe[] bits = res.getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolFe().zeroes(), bits[0]);
            else call(next(bits[0]));
        }
    }

    public Procedure next(BoolV res) { return new Next(res); }
    public Procedure next(BoolVe res) { return new Next(res); }
    public Procedure next(BoolVf res) { return new Next(res); }

    public Procedure next(BoolE res) { return new Next(res); }
    public Procedure next(BoolEv res) { return new Next(res); }
    public Procedure next(BoolEf res) { return new Next(res); }

    public Procedure next(BoolF res) { return new Next(res); }
    public Procedure next(BoolFv res) { return new Next(res); }
    public Procedure next(BoolFe res) { return new Next(res); }

    public Procedure next(IntV res) { return new Next(res); }
    public Procedure next0p(IntV res) { return new Next(res, "0+"); }
    public Procedure next(IntVe res) { return new Next(res); }
    public Procedure next0p(IntVe res) { return new Next(res, "0+"); }
    public Procedure next(IntVf res) { return new Next(res); }
    public Procedure next0p(IntVf res) { return new Next(res, "0+"); }


    public Procedure next(IntE res) { return new Next(res); }
    public Procedure next0p(IntE res) { return new Next(res, "0+"); }
    public Procedure next(IntEv res) { return new Next(res); }
    public Procedure next0p(IntEv res) { return new Next(res, "0+"); }
    public Procedure next(IntEf res) { return new Next(res); }
    public Procedure next0p(IntEf res) { return new Next(res, "0+"); }

    public Procedure next(IntF res) { return new Next(res); }
    public Procedure next0p(IntF res) { return new Next(res, "0+"); }
    public Procedure next(IntFv res) { return new Next(res); }
    public Procedure next0p(IntFv res) { return new Next(res, "0+"); }
    public Procedure next(IntFe res) { return new Next(res); }
    public Procedure next0p(IntFe res) { return new Next(res, "0+"); }

    private class ShowRand extends Procedure {
        public ShowRand() {
            BoolV v =  new BoolV();
            BoolVe ve = new BoolVe();
            BoolVf vf = new BoolVf();

            BoolE e = new BoolE();
            BoolEv ev = new BoolEv();
            BoolEf ef = new BoolEf();

            BoolF f = new BoolF();
            BoolFv fv = new BoolFv();
            BoolFe fe = new BoolFe();

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
