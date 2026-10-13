package blobProgram;

import language.field.VoidF;
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

    private static class NextState extends Procedure<VoidF> {
        public NextState() {
            if (!initialized) throw new IllegalStateException("Rand has not been initialized");

            set(redXor(transfer(broadcastEv(redXor(transfer(broadcastVe(state)))))), state);

            returnValue(new VoidF());
        }
    }

    private class Next extends Procedure<VoidF> {
        public Next(BoolV res) {
            call(nextState);
            set(state, res);

            returnValue(new VoidF());
        }

        public Next(IntV res) { this(res, ""); }
        public Next(IntV res, String option) {
            BoolV[] bits = res.getBits();
            for (int i = 1; i < bits.length; i++) call(next(bits[i]));

            if (option.equals("0+")) set(new BoolV().zeroes(), bits[0]);
            else call(next(bits[0]));

            returnValue(new VoidF());
        }
    }

    public Procedure<VoidF> next(BoolV res) { return new Next(res); }

    public Procedure<VoidF> next(IntV res) { return new Next(res); }
    public Procedure<VoidF> nextNonNeg(IntV res) { return new Next(res, "0+"); }
}
