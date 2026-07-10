package blobProgram;

import language.field.boolField.BoolV;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.boolField.BoolVeRef;
import language.instruction.Procedure;

public class BeepWave {
    private final BoolVRef state_t;
    private final BoolVRef state_tm1;

    public BeepWave(BoolVRef seed) {
        state_t = seed.copy();
        state_tm1 = new BoolVRef(BoolV.zeroes());
    }

    private class Propagate extends Procedure {
        public Propagate() {
            show("BeepWave", state_t);

            BoolVRef notTm1 = tmp(new BoolVRef());
            not(state_tm1, notTm1);
            set(state_t, state_tm1);

            BoolVRef notT = tmp(new BoolVRef());
            not(state_t, notT);

            BoolVeRef ve = new BoolVeRef();
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
