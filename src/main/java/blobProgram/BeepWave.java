package blobProgram;

import language.field.boolField.BoolV;
import language.field.boolField.BoolVe;
import language.instruction.Procedure;

public class BeepWave {
    private final BoolV state_t;
    private final BoolV state_tm1;

    public BeepWave(BoolV seed) {
        state_t = seed.copy();
        state_tm1 = new BoolV().zeroes();
    }

    private class Propagate extends Procedure {
        public Propagate() {
            show("BeepWave", state_t);

            BoolV notTm1 = tmp(new BoolV());
            not(state_tm1, notTm1);
            set(state_t, state_tm1);

            BoolV notT = tmp(new BoolV());
            not(state_t, notT);

            BoolVe ve = new BoolVe();
            broadcast(state_t, ve);
            call(BlobV.send(ve, ve));
            show("ve", ve);
            redOr(ve, state_t);
            and(state_t, notTm1, state_t);
            and(state_t, notT, state_t);
        }
    }
    public Procedure propagate() { return new Propagate(); }
}
