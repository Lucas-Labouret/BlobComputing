package language.obj;

import language.Ref;
import language.instruction.Procedure;
import language.obj.field.boolField.BoolV;
import language.obj.field.intField.*;
import language.ref.BlobVRef;
import language.ref.FliesRef;
import language.ref.RandRef;
import language.ref.field.boolField.*;
import language.ref.field.intField.*;

public class Flies extends BlobV {
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

            BoolVRef growable = tmp(new BoolVRef());
            not(meet, meet);
            and(meet, grow.get().state, growable);

            IntVeRef randVe = IntVeRef.of(new IntVe(pBits));
            call(rand.get().next(randVe));

            IntVeRef randVeCopy = new IntVeRef();
            set(randVe, randVeCopy);
            show("randVe", randVeCopy);

            IntVRef maxV = IntVRef.of(new IntV(pBits));
            redMax(randVe, maxV);

            IntVRef maxVCopy = new IntVRef();
            set(maxV, maxVCopy);
            show("maxV", maxVCopy);

            IntVeRef maxVe = IntVeRef.of(new IntVe(pBits));
            broadcast(maxV, maxVe);

            BoolVeRef isMax = tmp(new BoolVeRef());
            eq(randVe, maxVe, isMax);

            BoolVeRef isMaxCopy = tmp(new BoolVeRef());
            set(isMax, isMaxCopy);
            show("isMax", isMaxCopy);

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

            BoolVRef triggered = tmp(new BoolVRef());
            redOr(ve, triggered);

            transfer(ve, ev);
            rotCW(ev, ef);
            rotCW(ef, ev);
            transfer(ev, ve);
            redOr(ve, out.get().state);

            BoolVRef notTriggered = tmp(new BoolVRef());
            BoolVRef untriggered = tmp(new BoolVRef());
            not(triggered, notTriggered);
            and(notTriggered, in.get().state, untriggered);

            or(out.get().state, untriggered, out.get().state);
        }
    }
    public static <I extends Flies, O extends Flies> Procedure fly(Ref<I> in, Ref<O> out) { return new Fly(in, out); }
    public <O extends Flies> Procedure fly(Ref<O> out) { return fly(thisRef, out); }
    public Procedure fly() { return fly(thisRef, thisRef); }

    private class ShowFlies extends Procedure {
        public ShowFlies() {
            show("Flies", state);
            call(fly());
        }
    }
    public Procedure showFlies() { return new ShowFlies(); }

    @Override
    public Flies copy() { return new Flies(state.copy()); }
}
