package language.obj.agent.flies;

import language.Ref;
import language.instruction.Procedure;
import language.obj.Rand;
import language.obj.agent.BlobV;
import language.obj.agent.QuasiParticle;
import language.obj.field.boolField.BoolV;
import language.obj.field.intField.*;
import language.ref.agent.BlobVRef;
import language.ref.agent.flies.FliesRef;
import language.ref.RandRef;
import language.ref.field.boolField.*;
import language.ref.field.intField.*;

public class Flies extends QuasiParticle {
    private static final RandRef rand = RandRef.of(new Rand());
    private final FliesRef thisRef = FliesRef.of(this);

     public Flies() { super(); }
     public Flies(BoolVRef cells) { super(cells); }

    public static Flies rand() { return new Flies(BoolVRef.of(BoolV.rand())); }
    public static Flies rand(int sparsity) {
         BoolVRef cells = BoolVRef.of(BoolV.rand());
         for (int i = 0; i < sparsity; i++) {
             cells = BoolVRef.of(BoolV.and(cells.get(), BoolV.rand()));
         }
         return new Flies(cells);
    }

    private static class Fly extends Procedure {
        public <I extends Flies, O extends Flies> Fly(Ref<I> in, Ref<O> out) {
            int pBits = 5;

            BoolVRef meet = tmp(new BoolVRef());
            call(in.get().meet(meet));

            BlobVRef grow = tmp(BlobVRef.of(new BlobV()));
            call(in.get().grow(grow));

            BoolVRef notState = tmp(new BoolVRef());
            BoolVRef growable = tmp(new BoolVRef());
            not(meet, meet);
            not(in.get().state, notState);
            and(meet, grow.get().state, growable);
            and(notState, growable, growable);


            IntVeRef randVe = IntVeRef.of(new IntVe(pBits));
            call(rand.get().next(randVe));

            IntVRef maxV = IntVRef.of(new IntV(pBits));
            redMax(randVe, maxV);

            IntVeRef maxVe = IntVeRef.of(new IntVe(pBits));
            broadcast(maxV, maxVe);

            BoolVeRef isMax = tmp(new BoolVeRef());
            eq(randVe, maxVe, isMax);

            IntVRef v = tmp(IntVRef.of(new IntV(4)));
            redAdd(isMax, v);

            BoolVRef oneMaxV = tmp(new BoolVRef());
            BoolVeRef oneMaxVe = tmp(new BoolVeRef());
            eq(v, IntVRef.of(IntV.of(1, 4)), oneMaxV);
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
            broadcast(in.get().state, outVe);
            and(outVe, ve, outVe);

            BoolVRef triggered = tmp(new BoolVRef());
            BoolVRef notTriggered = tmp(new BoolVRef());
            BoolVRef untriggered = tmp(new BoolVRef());
            redOr(outVe, triggered);
            not(triggered, notTriggered);
            and(notTriggered, in.get().state, untriggered);

            transfer(outVe, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, ve);
            redOr(ve, out.get().state);

            or(out.get().state, untriggered, out.get().state);
        }
    }
    public static <I extends Flies, O extends Flies> Procedure fly(Ref<I> in, Ref<O> out) { return new Fly(in, out); }
    public <O extends Flies> Procedure fly(Ref<O> out) { return fly(thisRef, out); }
    public Procedure fly() { return fly(thisRef, thisRef); }

    private class ShowFlies extends Procedure {
        public ShowFlies() {
            BoolVRef stateCopy = new BoolVRef();
            set(state, stateCopy);
            show("Flies", stateCopy);
            call(fly());
        }
    }
    public Procedure showFlies() { return new ShowFlies(); }

    @Override
    public Flies copy() { return new Flies(state.copy()); }
}
