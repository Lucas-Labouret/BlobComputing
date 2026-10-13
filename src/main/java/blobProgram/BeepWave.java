package blobProgram;

import language.field.VoidF;
import language.field.boolField.BoolV;
import language.instruction.Procedure;

public class BeepWave {
    private final BoolV state_t;
    private final BoolV state_tm1;

    public BeepWave(BoolV seed) {
        state_t = seed.copy();
        state_tm1 = new BoolV().zeroes();
    }

    private class Propagate extends Procedure<VoidF> {
        public Propagate() {
            show("BeepWave", state_t);

            BoolV notTm1 = not(state_tm1);
            BoolV notT = not(state_t);

            set(state_t, state_tm1);
            set(redOr(call(BlobV.send(broadcastVe(state_t)))), state_t);

            set(and(state_t, and(notT, notTm1)), state_t);

            returnValue(new VoidF());
        }
    }
    public Procedure<VoidF> propagate() { return new Propagate(); }
}
