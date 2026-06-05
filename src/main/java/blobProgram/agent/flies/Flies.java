package blobProgram.agent.flies;

import blobProgram.BlobV;
import blobProgram.QuasiParticle;
import blobProgram.Rand;
import blobProgram.agent.Agent;
import blobProgram.agent.Flip;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.fieldRef.boolField.BoolEfRef;
import language.fieldRef.boolField.BoolEvRef;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.boolField.BoolVeRef;
import language.fieldRef.intField.IntVRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Procedure;

public class Flies extends Agent {
    private static final Rand rand = new Rand();

    public Flies(QuasiParticle state) {
        super(
            state,
            new Flip()
                .addYes(new Expand(state))
                .addYes(new Contract(state))
        );
    }

    public static Flies rand() { return rand(0); }
    public static Flies rand(int sparsity) { return new Flies(QuasiParticle.rand(sparsity)); }

    private static class Fly extends Procedure {
        public Fly(QuasiParticle in, QuasiParticle out) {
            int pBits = 5;

            BoolVRef meet = tmp(new BoolVRef());
            call(in.meet(meet));

            BlobV grow = tmp(new BlobV());
            call(in.grow(grow));

            BoolVRef notIn = tmp(new BoolVRef());
            BoolVRef growable = tmp(new BoolVRef());
            not(meet, meet);
            not(in, notIn);
            and(meet, grow, growable);
            and(notIn, growable, growable);

            IntVeRef randVe = new IntVeRef(new IntVe(pBits));
            call(rand.next(randVe));

            IntVRef maxV = new IntVRef(new IntV(pBits));
            redMax(randVe, maxV);

            IntVeRef maxVe = new IntVeRef(new IntVe(pBits));
            broadcast(maxV, maxVe);

            BoolVeRef isMax = tmp(new BoolVeRef());
            eq(randVe, maxVe, isMax);

            IntVRef v = tmp(new IntVRef(new IntV(4)));
            redAdd(isMax, v);

            BoolVRef oneMaxV = tmp(new BoolVRef());
            BoolVeRef oneMaxVe = tmp(new BoolVeRef());
            eq(v, new IntVRef(IntV.of(1, 4)), oneMaxV);
            broadcast(oneMaxV, oneMaxVe);
            and(isMax, oneMaxVe, isMax);

            BoolVeRef ve = tmp(new BoolVeRef());
            BoolEvRef ev = tmp(new BoolEvRef());
            BoolEfRef ef = tmp(new BoolEfRef());
            broadcast(growable, ve);
            transfer(ve, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, ve);
            and(isMax, ve, ve);

            BoolVeRef outVe = tmp(new BoolVeRef());
            broadcast(in, outVe);
            and(outVe, ve, outVe);

            BoolVRef triggered = tmp(new BoolVRef());
            BoolVRef notTriggered = tmp(new BoolVRef());
            BoolVRef untriggered = tmp(new BoolVRef());
            redOr(outVe, triggered);
            not(triggered, notTriggered);
            and(notTriggered, in, untriggered);

            transfer(outVe, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, ve);
            redOr(ve, out);

            or(out, untriggered, out);
        }
    }
    public static Procedure fly(QuasiParticle in, QuasiParticle out) { return new Fly(in, out); }
    public Procedure fly(QuasiParticle out) { return fly((QuasiParticle) this.state, out); }
    public Procedure fly() { return fly((QuasiParticle) this.state, (QuasiParticle) this.state); }

    private class ShowFlies extends Procedure {
        public ShowFlies() {
            BoolVRef stateCopy = new BoolVRef();
            set(state, stateCopy);
            show("Flies", stateCopy);
            call(fly());
        }
    }
    public Procedure showFlies() { return new ShowFlies(); }
}
