package language.instruction.instructionSet;

import language.field.boolField.*;
import language.instruction.BasicInstruction;
import language.instruction.Instruction;
import language.instruction.Procedure;
import utils.TriConsumer;
import utils.TriFunction;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class BoolOp {
    public static BasicInstruction not(BoolV  a, BoolV  res) { return new UnOp<>(a, res, (aa, rr) -> rr.not(aa)); }
    public static BasicInstruction not(BoolVe a, BoolVe res) { return new UnOp<>(a, res, (aa, rr) -> rr.not(aa)); }
    public static BasicInstruction not(BoolVf a, BoolVf res) { return new UnOp<>(a, res, (aa, rr) -> rr.not(aa)); }
    public static BasicInstruction not(BoolE  a, BoolE  res) { return new UnOp<>(a, res, (aa, rr) -> rr.not(aa)); }
    public static BasicInstruction not(BoolEv a, BoolEv res) { return new UnOp<>(a, res, (aa, rr) -> rr.not(aa)); }
    public static BasicInstruction not(BoolEf a, BoolEf res) { return new UnOp<>(a, res, (aa, rr) -> rr.not(aa)); }
    public static BasicInstruction not(BoolF  a, BoolF  res) { return new UnOp<>(a, res, (aa, rr) -> rr.not(aa)); }
    public static BasicInstruction not(BoolFv a, BoolFv res) { return new UnOp<>(a, res, (aa, rr) -> rr.not(aa)); }
    public static BasicInstruction not(BoolFe a, BoolFe res) { return new UnOp<>(a, res, (aa, rr) -> rr.not(aa)); }

    public static BasicInstruction and(BoolV  a, BoolV  b, BoolV  res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.and(aa, bb)); }
    public static BasicInstruction and(BoolVe a, BoolVe b, BoolVe res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.and(aa, bb)); }
    public static BasicInstruction and(BoolVf a, BoolVf b, BoolVf res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.and(aa, bb)); }
    public static BasicInstruction and(BoolE  a, BoolE  b, BoolE  res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.and(aa, bb)); }
    public static BasicInstruction and(BoolEv a, BoolEv b, BoolEv res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.and(aa, bb)); }
    public static BasicInstruction and(BoolEf a, BoolEf b, BoolEf res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.and(aa, bb)); }
    public static BasicInstruction and(BoolF  a, BoolF  b, BoolF  res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.and(aa, bb)); }
    public static BasicInstruction and(BoolFv a, BoolFv b, BoolFv res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.and(aa, bb)); }
    public static BasicInstruction and(BoolFe a, BoolFe b, BoolFe res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.and(aa, bb)); }

    public static BasicInstruction or(BoolV  a, BoolV  b, BoolV  res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.or(aa, bb)); }
    public static BasicInstruction or(BoolVe a, BoolVe b, BoolVe res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.or(aa, bb)); }
    public static BasicInstruction or(BoolVf a, BoolVf b, BoolVf res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.or(aa, bb)); }
    public static BasicInstruction or(BoolE  a, BoolE  b, BoolE  res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.or(aa, bb)); }
    public static BasicInstruction or(BoolEv a, BoolEv b, BoolEv res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.or(aa, bb)); }
    public static BasicInstruction or(BoolEf a, BoolEf b, BoolEf res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.or(aa, bb)); }
    public static BasicInstruction or(BoolF  a, BoolF  b, BoolF  res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.or(aa, bb)); }
    public static BasicInstruction or(BoolFv a, BoolFv b, BoolFv res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.or(aa, bb)); }
    public static BasicInstruction or(BoolFe a, BoolFe b, BoolFe res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.or(aa, bb)); }

    public static BasicInstruction xor(BoolV  a, BoolV  b, BoolV  res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.xor(aa, bb)); }
    public static BasicInstruction xor(BoolVe a, BoolVe b, BoolVe res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.xor(aa, bb)); }
    public static BasicInstruction xor(BoolVf a, BoolVf b, BoolVf res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.xor(aa, bb)); }
    public static BasicInstruction xor(BoolE  a, BoolE  b, BoolE  res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.xor(aa, bb)); }
    public static BasicInstruction xor(BoolEv a, BoolEv b, BoolEv res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.xor(aa, bb)); }
    public static BasicInstruction xor(BoolEf a, BoolEf b, BoolEf res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.xor(aa, bb)); }
    public static BasicInstruction xor(BoolF  a, BoolF  b, BoolF  res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.xor(aa, bb)); }
    public static BasicInstruction xor(BoolFv a, BoolFv b, BoolFv res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.xor(aa, bb)); }
    public static BasicInstruction xor(BoolFe a, BoolFe b, BoolFe res) { return new BiOp<>(a, b, res, (aa, bb, rr) -> rr.xor(aa, bb)); }

    public static Procedure fif(BoolV  cond, BoolV  t, BoolV  f, BoolV  res) { return new bIf<>(cond, t, f, res, BoolOp::and, BoolOp::or, BoolOp::not, BoolV::new); }
    public static Procedure fif(BoolVe cond, BoolVe t, BoolVe f, BoolVe res) { return new bIf<>(cond, t, f, res, BoolOp::and, BoolOp::or, BoolOp::not, BoolVe::new); }
    public static Procedure fif(BoolVf cond, BoolVf t, BoolVf f, BoolVf res) { return new bIf<>(cond, t, f, res, BoolOp::and, BoolOp::or, BoolOp::not, BoolVf::new); }
    public static Procedure fif(BoolE  cond, BoolE  t, BoolE  f, BoolE  res) { return new bIf<>(cond, t, f, res, BoolOp::and, BoolOp::or, BoolOp::not, BoolE::new); }
    public static Procedure fif(BoolEv cond, BoolEv t, BoolEv f, BoolEv res) { return new bIf<>(cond, t, f, res, BoolOp::and, BoolOp::or, BoolOp::not, BoolEv::new); }
    public static Procedure fif(BoolEf cond, BoolEf t, BoolEf f, BoolEf res) { return new bIf<>(cond, t, f, res, BoolOp::and, BoolOp::or, BoolOp::not, BoolEf::new); }
    public static Procedure fif(BoolF  cond, BoolF  t, BoolF  f, BoolF  res) { return new bIf<>(cond, t, f, res, BoolOp::and, BoolOp::or, BoolOp::not, BoolF::new); }
    public static Procedure fif(BoolFv cond, BoolFv t, BoolFv f, BoolFv res) { return new bIf<>(cond, t, f, res, BoolOp::and, BoolOp::or, BoolOp::not, BoolFv::new); }
    public static Procedure fif(BoolFe cond, BoolFe t, BoolFe f, BoolFe res) { return new bIf<>(cond, t, f, res, BoolOp::and, BoolOp::or, BoolOp::not, BoolFe::new); }

    public static BasicInstruction broadcast(BoolV a, BoolVe res) { return new UnOp<>(a, res, (aa, rr) -> rr.broadcast(aa)); }
    public static BasicInstruction broadcast(BoolV a, BoolVf res) { return new UnOp<>(a, res, (aa, rr) -> rr.broadcast(aa)); }
    public static BasicInstruction broadcast(BoolE a, BoolEv res) { return new UnOp<>(a, res, (aa, rr) -> rr.broadcast(aa)); }
    public static BasicInstruction broadcast(BoolE a, BoolEf res) { return new UnOp<>(a, res, (aa, rr) -> rr.broadcast(aa)); }
    public static BasicInstruction broadcast(BoolF a, BoolFv res) { return new UnOp<>(a, res, (aa, rr) -> rr.broadcast(aa)); }
    public static BasicInstruction broadcast(BoolF a, BoolFe res) { return new UnOp<>(a, res, (aa, rr) -> rr.broadcast(aa)); }

    public static BasicInstruction transfer(BoolVe a, BoolEv res) { return new UnOp<>(a, res, (aa, rr) -> rr.transfer(aa)); }
    public static BasicInstruction transfer(BoolVf a, BoolFv res) { return new UnOp<>(a, res, (aa, rr) -> rr.transfer(aa)); }
    public static BasicInstruction transfer(BoolEv a, BoolVe res) { return new UnOp<>(a, res, (aa, rr) -> rr.transfer(aa)); }
    public static BasicInstruction transfer(BoolEf a, BoolFe res) { return new UnOp<>(a, res, (aa, rr) -> rr.transfer(aa)); }
    public static BasicInstruction transfer(BoolFv a, BoolVf res) { return new UnOp<>(a, res, (aa, rr) -> rr.transfer(aa)); }
    public static BasicInstruction transfer(BoolFe a, BoolEf res) { return new UnOp<>(a, res, (aa, rr) -> rr.transfer(aa)); }

    public static BasicInstruction redAnd(BoolVe a, BoolV res) { return new UnOp<>(a, res, (aa, rr) -> rr.redAnd(aa)); }
    public static BasicInstruction redAnd(BoolVf a, BoolV res) { return new UnOp<>(a, res, (aa, rr) -> rr.redAnd(aa)); }
    public static BasicInstruction redAnd(BoolEv a, BoolE res) { return new UnOp<>(a, res, (aa, rr) -> rr.redAnd(aa)); }
    public static BasicInstruction redAnd(BoolEf a, BoolE res) { return new UnOp<>(a, res, (aa, rr) -> rr.redAnd(aa)); }
    public static BasicInstruction redAnd(BoolFv a, BoolF res) { return new UnOp<>(a, res, (aa, rr) -> rr.redAnd(aa)); }
    public static BasicInstruction redAnd(BoolFe a, BoolF res) { return new UnOp<>(a, res, (aa, rr) -> rr.redAnd(aa)); }

    public static BasicInstruction redOr(BoolVe a, BoolV res) { return new UnOp<>(a, res, (aa, rr) -> rr.redOr(aa)); }
    public static BasicInstruction redOr(BoolVf a, BoolV res) { return new UnOp<>(a, res, (aa, rr) -> rr.redOr(aa)); }
    public static BasicInstruction redOr(BoolEv a, BoolE res) { return new UnOp<>(a, res, (aa, rr) -> rr.redOr(aa)); }
    public static BasicInstruction redOr(BoolEf a, BoolE res) { return new UnOp<>(a, res, (aa, rr) -> rr.redOr(aa)); }
    public static BasicInstruction redOr(BoolFv a, BoolF res) { return new UnOp<>(a, res, (aa, rr) -> rr.redOr(aa)); }
    public static BasicInstruction redOr(BoolFe a, BoolF res) { return new UnOp<>(a, res, (aa, rr) -> rr.redOr(aa)); }

    public static BasicInstruction redXor(BoolVe a, BoolV res) { return new UnOp<>(a, res, (aa, rr) -> rr.redXor(aa)); }
    public static BasicInstruction redXor(BoolVf a, BoolV res) { return new UnOp<>(a, res, (aa, rr) -> rr.redXor(aa)); }
    public static BasicInstruction redXor(BoolEv a, BoolE res) { return new UnOp<>(a, res, (aa, rr) -> rr.redXor(aa)); }
    public static BasicInstruction redXor(BoolEf a, BoolE res) { return new UnOp<>(a, res, (aa, rr) -> rr.redXor(aa)); }
    public static BasicInstruction redXor(BoolFv a, BoolF res) { return new UnOp<>(a, res, (aa, rr) -> rr.redXor(aa)); }
    public static BasicInstruction redXor(BoolFe a, BoolF res) { return new UnOp<>(a, res, (aa, rr) -> rr.redXor(aa)); }

    public static BasicInstruction redStack0(BoolVe a, BoolV[] res) { return new bRedStack<>(a, res, BoolVe::redStack0); }
    public static BasicInstruction redStack0(BoolVf a, BoolV[] res) { return new bRedStack<>(a, res, BoolVf::redStack0); }
    public static BasicInstruction redStack0(BoolEv a, BoolE[] res) { return new bRedStack<>(a, res, BoolEv::redStack0); }
    public static BasicInstruction redStack0(BoolEf a, BoolE[] res) { return new bRedStack<>(a, res, BoolEf::redStack0); }
    public static BasicInstruction redStack0(BoolFv a, BoolF[] res) { return new bRedStack<>(a, res, BoolFv::redStack0); }
    public static BasicInstruction redStack0(BoolFe a, BoolF[] res) { return new bRedStack<>(a, res, BoolFe::redStack0); }

    public static BasicInstruction redStack1(BoolVe a, BoolV[] res) { return new bRedStack<>(a, res, BoolVe::redStack1); }
    public static BasicInstruction redStack1(BoolVf a, BoolV[] res) { return new bRedStack<>(a, res, BoolVf::redStack1); }
    public static BasicInstruction redStack1(BoolEv a, BoolE[] res) { return new bRedStack<>(a, res, BoolEv::redStack1); }
    public static BasicInstruction redStack1(BoolEf a, BoolE[] res) { return new bRedStack<>(a, res, BoolEf::redStack1); }
    public static BasicInstruction redStack1(BoolFv a, BoolF[] res) { return new bRedStack<>(a, res, BoolFv::redStack1); }
    public static BasicInstruction redStack1(BoolFe a, BoolF[] res) { return new bRedStack<>(a, res, BoolFe::redStack1); }

    public static BasicInstruction rotCw(BoolVe a, BoolVf res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCW(aa)); }
    public static BasicInstruction rotCw(BoolVf a, BoolVe res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCW(aa)); }
    public static BasicInstruction rotCw(BoolEv a, BoolEf res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCW(aa)); }
    public static BasicInstruction rotCw(BoolEf a, BoolEv res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCW(aa)); }
    public static BasicInstruction rotCw(BoolFv a, BoolFe res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCW(aa)); }
    public static BasicInstruction rotCw(BoolFe a, BoolFv res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCW(aa)); }

    public static BasicInstruction rotCcw(BoolVe a, BoolVf res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCCW(aa)); }
    public static BasicInstruction rotCcw(BoolVf a, BoolVe res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCCW(aa)); }
    public static BasicInstruction rotCcw(BoolEv a, BoolEf res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCCW(aa)); }
    public static BasicInstruction rotCcw(BoolEf a, BoolEv res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCCW(aa)); }
    public static BasicInstruction rotCcw(BoolFv a, BoolFe res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCCW(aa)); }
    public static BasicInstruction rotCcw(BoolFe a, BoolFv res) { return new UnOp<>(a, res, (aa, rr) -> rr.rotateCCW(aa)); }
}

/** Performs a unary operation on a BoolField. */
class UnOp<T extends BoolField<T>, R extends BoolField<R>> implements BasicInstruction {
    private final T orig;
    private final R res;
    private final BiConsumer<T, R> op;

    public UnOp(T orig, R res, BiConsumer<T, R> op) {
        this.orig = orig;
        this.res = res;
        this.op = op;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        op.accept(orig, res);
        return true;
    }
}

/** Performs a binary operation on two BoolField. */
class BiOp<T extends BoolField<T>, U extends BoolField<U>, R extends BoolField<R>> implements BasicInstruction {
    private final T a;
    private final U b;
    private final R res;
    private final TriConsumer<T, U, R> op;

    /** Creates a new AndV. */
    public BiOp(T a, U b, R res, TriConsumer<T, U, R> op) {
        this.a = a;
        this.b = b;
        this.res = res;
        this.op = op;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        op.accept(a, b, res);
        return true;
    }
}

/** Generic BoolField if-then-else. */
class bIf<T extends BoolField<T>> extends Procedure {
    public bIf(T cond, T t, T f, T res,
               TriFunction<T, T, T, Instruction> and,
               TriFunction<T, T, T, Instruction> or,
               BiFunction<T, T, Instruction> not,
               Supplier<T> fieldSupplier
    ) {
        T notCond = tmp(fieldSupplier.get());
        T tt = tmp(fieldSupplier.get());
        T ff = tmp(fieldSupplier.get());

        call(not.apply(cond, notCond));
        call(and.apply(cond, t, tt));
        call(and.apply(notCond, f, ff));
        call(or.apply(tt, ff, res));
    }
}

/** Generic BoolField stack reduction. */
class bRedStack<T extends BoolField<T>, R extends BoolField<R>> implements BasicInstruction {
    private final T orig;
    private final R[] dest;
    private final Function<T, R[]> stacker;

    public bRedStack(T orig, R[] dest, Function<T, R[]> stacker) {
        this.orig = orig;
        this.dest = dest;
        this.stacker = stacker;
    }

    /** @return true. */
    @Override
    public boolean exec() {
        R[] stack = stacker.apply(orig);

        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid reduction: expected " + dest.length + " levels, but got " + stack.length
        );

        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}
