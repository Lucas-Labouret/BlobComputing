package language.instruction.instructionSet;

import language.field.VoidF;
import language.field.boolField.*;
import language.instruction.BasicInstruction;
import language.instruction.Instruction;
import language.instruction.Procedure;
import utils.TriConsumer;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/** A set of operations for boolean values. */
public class BoolOp {
    /** Computes NOT a. */
    public static BasicInstruction<BoolV>  not(BoolV  a) { return new UnOp<>(a, BoolV::new , (aa, rr) -> rr.not(aa)); }
    /** Computes NOT a. */
    public static BasicInstruction<BoolVe> not(BoolVe a) { return new UnOp<>(a, BoolVe::new, (aa, rr) -> rr.not(aa)); }
    /** Computes NOT a. */
    public static BasicInstruction<BoolVf> not(BoolVf a) { return new UnOp<>(a, BoolVf::new, (aa, rr) -> rr.not(aa)); }
    /** Computes NOT a. */
    public static BasicInstruction<BoolE>  not(BoolE  a) { return new UnOp<>(a, BoolE::new , (aa, rr) -> rr.not(aa)); }
    /** Computes NOT a. */
    public static BasicInstruction<BoolEv> not(BoolEv a) { return new UnOp<>(a, BoolEv::new, (aa, rr) -> rr.not(aa)); }
    /** Computes NOT a. */
    public static BasicInstruction<BoolEf> not(BoolEf a) { return new UnOp<>(a, BoolEf::new, (aa, rr) -> rr.not(aa)); }
    /** Computes NOT a. */
    public static BasicInstruction<BoolF>  not(BoolF  a) { return new UnOp<>(a, BoolF::new , (aa, rr) -> rr.not(aa)); }
    /** Computes NOT a. */
    public static BasicInstruction<BoolFv> not(BoolFv a) { return new UnOp<>(a, BoolFv::new, (aa, rr) -> rr.not(aa)); }
    /** Computes NOT a. */
    public static BasicInstruction<BoolFe> not(BoolFe a) { return new UnOp<>(a, BoolFe::new, (aa, rr) -> rr.not(aa)); }

    /** Computes a AND b. */
    public static BasicInstruction<BoolV>  and(BoolV  a, BoolV  b) { return new BiOp<>(a, b, BoolV::new , (aa, bb, rr) -> rr.and(aa, bb)); }
    /** Computes a AND b. */
    public static BasicInstruction<BoolVe> and(BoolVe a, BoolVe b) { return new BiOp<>(a, b, BoolVe::new, (aa, bb, rr) -> rr.and(aa, bb)); }
    /** Computes a AND b. */
    public static BasicInstruction<BoolVf> and(BoolVf a, BoolVf b) { return new BiOp<>(a, b, BoolVf::new, (aa, bb, rr) -> rr.and(aa, bb)); }
    /** Computes a AND b. */
    public static BasicInstruction<BoolE>  and(BoolE  a, BoolE  b) { return new BiOp<>(a, b, BoolE::new , (aa, bb, rr) -> rr.and(aa, bb)); }
    /** Computes a AND b. */
    public static BasicInstruction<BoolEv> and(BoolEv a, BoolEv b) { return new BiOp<>(a, b, BoolEv::new, (aa, bb, rr) -> rr.and(aa, bb)); }
    /** Computes a AND b. */
    public static BasicInstruction<BoolEf> and(BoolEf a, BoolEf b) { return new BiOp<>(a, b, BoolEf::new, (aa, bb, rr) -> rr.and(aa, bb)); }
    /** Computes a AND b. */
    public static BasicInstruction<BoolF>  and(BoolF  a, BoolF  b) { return new BiOp<>(a, b, BoolF::new , (aa, bb, rr) -> rr.and(aa, bb)); }
    /** Computes a AND b. */
    public static BasicInstruction<BoolFv> and(BoolFv a, BoolFv b) { return new BiOp<>(a, b, BoolFv::new, (aa, bb, rr) -> rr.and(aa, bb)); }
    /** Computes a AND b. */
    public static BasicInstruction<BoolFe> and(BoolFe a, BoolFe b) { return new BiOp<>(a, b, BoolFe::new, (aa, bb, rr) -> rr.and(aa, bb)); }

    /** Computes a OR b. */
    public static BasicInstruction<BoolV>  or(BoolV  a, BoolV  b) { return new BiOp<>(a, b, BoolV::new , (aa, bb, rr) -> rr.or(aa, bb)); }
    /** Computes a OR b. */
    public static BasicInstruction<BoolVe> or(BoolVe a, BoolVe b) { return new BiOp<>(a, b, BoolVe::new, (aa, bb, rr) -> rr.or(aa, bb)); }
    /** Computes a OR b. */
    public static BasicInstruction<BoolVf> or(BoolVf a, BoolVf b) { return new BiOp<>(a, b, BoolVf::new, (aa, bb, rr) -> rr.or(aa, bb)); }
    /** Computes a OR b. */
    public static BasicInstruction<BoolE>  or(BoolE  a, BoolE  b) { return new BiOp<>(a, b, BoolE::new , (aa, bb, rr) -> rr.or(aa, bb)); }
    /** Computes a OR b. */
    public static BasicInstruction<BoolEv> or(BoolEv a, BoolEv b) { return new BiOp<>(a, b, BoolEv::new, (aa, bb, rr) -> rr.or(aa, bb)); }
    /** Computes a OR b. */
    public static BasicInstruction<BoolEf> or(BoolEf a, BoolEf b) { return new BiOp<>(a, b, BoolEf::new, (aa, bb, rr) -> rr.or(aa, bb)); }
    /** Computes a OR b. */
    public static BasicInstruction<BoolF>  or(BoolF  a, BoolF  b) { return new BiOp<>(a, b, BoolF::new , (aa, bb, rr) -> rr.or(aa, bb)); }
    /** Computes a OR b. */
    public static BasicInstruction<BoolFv> or(BoolFv a, BoolFv b) { return new BiOp<>(a, b, BoolFv::new, (aa, bb, rr) -> rr.or(aa, bb)); }
    /** Computes a OR b. */
    public static BasicInstruction<BoolFe> or(BoolFe a, BoolFe b) { return new BiOp<>(a, b, BoolFe::new, (aa, bb, rr) -> rr.or(aa, bb)); }

    /** Computes a XOR b. */
    public static BasicInstruction<BoolV>  xor(BoolV  a, BoolV  b) { return new BiOp<>(a, b, BoolV::new , (aa, bb, rr) -> rr.xor(aa, bb)); }
    /** Computes a XOR b. */
    public static BasicInstruction<BoolVe> xor(BoolVe a, BoolVe b) { return new BiOp<>(a, b, BoolVe::new, (aa, bb, rr) -> rr.xor(aa, bb)); }
    /** Computes a XOR b. */
    public static BasicInstruction<BoolVf> xor(BoolVf a, BoolVf b) { return new BiOp<>(a, b, BoolVf::new, (aa, bb, rr) -> rr.xor(aa, bb)); }
    /** Computes a XOR b. */
    public static BasicInstruction<BoolE>  xor(BoolE  a, BoolE  b) { return new BiOp<>(a, b, BoolE::new , (aa, bb, rr) -> rr.xor(aa, bb)); }
    /** Computes a XOR b. */
    public static BasicInstruction<BoolEv> xor(BoolEv a, BoolEv b) { return new BiOp<>(a, b, BoolEv::new, (aa, bb, rr) -> rr.xor(aa, bb)); }
    /** Computes a XOR b. */
    public static BasicInstruction<BoolEf> xor(BoolEf a, BoolEf b) { return new BiOp<>(a, b, BoolEf::new, (aa, bb, rr) -> rr.xor(aa, bb)); }
    /** Computes a XOR b. */
    public static BasicInstruction<BoolF>  xor(BoolF  a, BoolF  b) { return new BiOp<>(a, b, BoolF::new , (aa, bb, rr) -> rr.xor(aa, bb)); }
    /** Computes a XOR b. */
    public static BasicInstruction<BoolFv> xor(BoolFv a, BoolFv b) { return new BiOp<>(a, b, BoolFv::new, (aa, bb, rr) -> rr.xor(aa, bb)); }
    /** Computes a XOR b. */
    public static BasicInstruction<BoolFe> xor(BoolFe a, BoolFe b) { return new BiOp<>(a, b, BoolFe::new, (aa, bb, rr) -> rr.xor(aa, bb)); }

    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<BoolV>  fif(BoolV  cond, BoolV  t, BoolV  f) { return new bIf<>(cond, t, f, BoolOp::and, BoolOp::or, BoolOp::not); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<BoolVe> fif(BoolVe cond, BoolVe t, BoolVe f) { return new bIf<>(cond, t, f, BoolOp::and, BoolOp::or, BoolOp::not); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<BoolVf> fif(BoolVf cond, BoolVf t, BoolVf f) { return new bIf<>(cond, t, f, BoolOp::and, BoolOp::or, BoolOp::not); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<BoolE>  fif(BoolE  cond, BoolE  t, BoolE  f) { return new bIf<>(cond, t, f, BoolOp::and, BoolOp::or, BoolOp::not); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<BoolEv> fif(BoolEv cond, BoolEv t, BoolEv f) { return new bIf<>(cond, t, f, BoolOp::and, BoolOp::or, BoolOp::not); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<BoolEf> fif(BoolEf cond, BoolEf t, BoolEf f) { return new bIf<>(cond, t, f, BoolOp::and, BoolOp::or, BoolOp::not); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<BoolF>  fif(BoolF  cond, BoolF  t, BoolF  f) { return new bIf<>(cond, t, f, BoolOp::and, BoolOp::or, BoolOp::not); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<BoolFv> fif(BoolFv cond, BoolFv t, BoolFv f) { return new bIf<>(cond, t, f, BoolOp::and, BoolOp::or, BoolOp::not); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<BoolFe> fif(BoolFe cond, BoolFe t, BoolFe f) { return new bIf<>(cond, t, f, BoolOp::and, BoolOp::or, BoolOp::not); }

    /** Broadcasts a. */
    public static BasicInstruction<BoolVe> broadcastVe(BoolV a) { return new UnOp<>(a, BoolVe::new, (aa, rr) -> rr.broadcast(aa)); }
    /** Broadcasts a. */
    public static BasicInstruction<BoolVf> broadcastVf(BoolV a) { return new UnOp<>(a, BoolVf::new, (aa, rr) -> rr.broadcast(aa)); }
    /** Broadcasts a. */
    public static BasicInstruction<BoolEv> broadcastEv(BoolE a) { return new UnOp<>(a, BoolEv::new, (aa, rr) -> rr.broadcast(aa)); }
    /** Broadcasts a. */
    public static BasicInstruction<BoolEf> broadcastEf(BoolE a) { return new UnOp<>(a, BoolEf::new, (aa, rr) -> rr.broadcast(aa)); }
    /** Broadcasts a. */
    public static BasicInstruction<BoolFv> broadcastFv(BoolF a) { return new UnOp<>(a, BoolFv::new, (aa, rr) -> rr.broadcast(aa)); }
    /** Broadcasts a. */
    public static BasicInstruction<BoolFe> broadcastFe(BoolF a) { return new UnOp<>(a, BoolFe::new, (aa, rr) -> rr.broadcast(aa)); }

    /** Transfers a. */
    public static BasicInstruction<BoolEv> transfer(BoolVe a) { return new UnOp<>(a, BoolEv::new, (aa, rr) -> rr.transfer(aa)); }
    /** Transfers a. */
    public static BasicInstruction<BoolFv> transfer(BoolVf a) { return new UnOp<>(a, BoolFv::new, (aa, rr) -> rr.transfer(aa)); }
    /** Transfers a. */
    public static BasicInstruction<BoolVe> transfer(BoolEv a) { return new UnOp<>(a, BoolVe::new, (aa, rr) -> rr.transfer(aa)); }
    /** Transfers a. */
    public static BasicInstruction<BoolFe> transfer(BoolEf a) { return new UnOp<>(a, BoolFe::new, (aa, rr) -> rr.transfer(aa)); }
    /** Transfers a. */
    public static BasicInstruction<BoolVf> transfer(BoolFv a) { return new UnOp<>(a, BoolVf::new, (aa, rr) -> rr.transfer(aa)); }
    /** Transfers a. */
    public static BasicInstruction<BoolEf> transfer(BoolFe a) { return new UnOp<>(a, BoolEf::new, (aa, rr) -> rr.transfer(aa)); }

    /** Reduces a with AND. */
    public static BasicInstruction<BoolV> redAnd(BoolVe a) { return new UnOp<>(a, BoolV::new, (aa, rr) -> rr.redAnd(aa)); }
    /** Reduces a with AND. */
    public static BasicInstruction<BoolV> redAnd(BoolVf a) { return new UnOp<>(a, BoolV::new, (aa, rr) -> rr.redAnd(aa)); }
    /** Reduces a with AND. */
    public static BasicInstruction<BoolE> redAnd(BoolEv a) { return new UnOp<>(a, BoolE::new, (aa, rr) -> rr.redAnd(aa)); }
    /** Reduces a with AND. */
    public static BasicInstruction<BoolE> redAnd(BoolEf a) { return new UnOp<>(a, BoolE::new, (aa, rr) -> rr.redAnd(aa)); }
    /** Reduces a with AND. */
    public static BasicInstruction<BoolF> redAnd(BoolFv a) { return new UnOp<>(a, BoolF::new, (aa, rr) -> rr.redAnd(aa)); }
    /** Reduces a with AND. */
    public static BasicInstruction<BoolF> redAnd(BoolFe a) { return new UnOp<>(a, BoolF::new, (aa, rr) -> rr.redAnd(aa)); }

    /** Reduces a with OR. */
    public static BasicInstruction<BoolV> redOr(BoolVe a) { return new UnOp<>(a, BoolV::new, (aa, rr) -> rr.redOr(aa)); }
    /** Reduces a with OR. */
    public static BasicInstruction<BoolV> redOr(BoolVf a) { return new UnOp<>(a, BoolV::new, (aa, rr) -> rr.redOr(aa)); }
    /** Reduces a with OR. */
    public static BasicInstruction<BoolE> redOr(BoolEv a) { return new UnOp<>(a, BoolE::new, (aa, rr) -> rr.redOr(aa)); }
    /** Reduces a with OR. */
    public static BasicInstruction<BoolE> redOr(BoolEf a) { return new UnOp<>(a, BoolE::new, (aa, rr) -> rr.redOr(aa)); }
    /** Reduces a with OR. */
    public static BasicInstruction<BoolF> redOr(BoolFv a) { return new UnOp<>(a, BoolF::new, (aa, rr) -> rr.redOr(aa)); }
    /** Reduces a with OR. */
    public static BasicInstruction<BoolF> redOr(BoolFe a) { return new UnOp<>(a, BoolF::new, (aa, rr) -> rr.redOr(aa)); }

    /** Reduces a with XOR. */
    public static BasicInstruction<BoolV> redXor(BoolVe a) { return new UnOp<>(a, BoolV::new, (aa, rr) -> rr.redXor(aa)); }
    /** Reduces a with XOR. */
    public static BasicInstruction<BoolV> redXor(BoolVf a) { return new UnOp<>(a, BoolV::new, (aa, rr) -> rr.redXor(aa)); }
    /** Reduces a with XOR. */
    public static BasicInstruction<BoolE> redXor(BoolEv a) { return new UnOp<>(a, BoolE::new, (aa, rr) -> rr.redXor(aa)); }
    /** Reduces a with XOR. */
    public static BasicInstruction<BoolE> redXor(BoolEf a) { return new UnOp<>(a, BoolE::new, (aa, rr) -> rr.redXor(aa)); }
    /** Reduces a with XOR. */
    public static BasicInstruction<BoolF> redXor(BoolFv a) { return new UnOp<>(a, BoolF::new, (aa, rr) -> rr.redXor(aa)); }
    /** Reduces a with XOR. */
    public static BasicInstruction<BoolF> redXor(BoolFe a) { return new UnOp<>(a, BoolF::new, (aa, rr) -> rr.redXor(aa)); }

    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack0(BoolVe a, BoolV[] res) { return new bRedStack<>(a, res, BoolVe::redStack0); }
    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack0(BoolVf a, BoolV[] res) { return new bRedStack<>(a, res, BoolVf::redStack0); }
    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack0(BoolEv a, BoolE[] res) { return new bRedStack<>(a, res, BoolEv::redStack0); }
    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack0(BoolEf a, BoolE[] res) { return new bRedStack<>(a, res, BoolEf::redStack0); }
    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack0(BoolFv a, BoolF[] res) { return new bRedStack<>(a, res, BoolFv::redStack0); }
    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack0(BoolFe a, BoolF[] res) { return new bRedStack<>(a, res, BoolFe::redStack0); }

    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack1(BoolVe a, BoolV[] res) { return new bRedStack<>(a, res, BoolVe::redStack1); }
    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack1(BoolVf a, BoolV[] res) { return new bRedStack<>(a, res, BoolVf::redStack1); }
    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack1(BoolEv a, BoolE[] res) { return new bRedStack<>(a, res, BoolEv::redStack1); }
    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack1(BoolEf a, BoolE[] res) { return new bRedStack<>(a, res, BoolEf::redStack1); }
    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack1(BoolFv a, BoolF[] res) { return new bRedStack<>(a, res, BoolFv::redStack1); }
    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    public static BasicInstruction<VoidF> redStack1(BoolFe a, BoolF[] res) { return new bRedStack<>(a, res, BoolFe::redStack1); }

    /** Rotates a clockwise. */
    public static BasicInstruction<BoolVf> rotCw(BoolVe a) { return new UnOp<>(a, BoolVf::new, (aa, rr) -> rr.rotateCW(aa)); }
    /** Rotates a clockwise. */
    public static BasicInstruction<BoolVe> rotCw(BoolVf a) { return new UnOp<>(a, BoolVe::new, (aa, rr) -> rr.rotateCW(aa)); }
    /** Rotates a clockwise. */
    public static BasicInstruction<BoolEf> rotCw(BoolEv a) { return new UnOp<>(a, BoolEf::new, (aa, rr) -> rr.rotateCW(aa)); }
    /** Rotates a clockwise. */
    public static BasicInstruction<BoolEv> rotCw(BoolEf a) { return new UnOp<>(a, BoolEv::new, (aa, rr) -> rr.rotateCW(aa)); }
    /** Rotates a clockwise. */
    public static BasicInstruction<BoolFe> rotCw(BoolFv a) { return new UnOp<>(a, BoolFe::new, (aa, rr) -> rr.rotateCW(aa)); }
    /** Rotates a clockwise. */
    public static BasicInstruction<BoolFv> rotCw(BoolFe a) { return new UnOp<>(a, BoolFv::new, (aa, rr) -> rr.rotateCW(aa)); }

    /** Rotates a counterclockwise. */
    public static BasicInstruction<BoolVf> rotCcw(BoolVe a) { return new UnOp<>(a, BoolVf::new, (aa, rr) -> rr.rotateCCW(aa)); }
    /** Rotates a counterclockwise. */
    public static BasicInstruction<BoolVe> rotCcw(BoolVf a) { return new UnOp<>(a, BoolVe::new, (aa, rr) -> rr.rotateCCW(aa)); }
    /** Rotates a counterclockwise. */
    public static BasicInstruction<BoolEf> rotCcw(BoolEv a) { return new UnOp<>(a, BoolEf::new, (aa, rr) -> rr.rotateCCW(aa)); }
    /** Rotates a counterclockwise. */
    public static BasicInstruction<BoolEv> rotCcw(BoolEf a) { return new UnOp<>(a, BoolEv::new, (aa, rr) -> rr.rotateCCW(aa)); }
    /** Rotates a counterclockwise. */
    public static BasicInstruction<BoolFe> rotCcw(BoolFv a) { return new UnOp<>(a, BoolFe::new, (aa, rr) -> rr.rotateCCW(aa)); }
    /** Rotates a counterclockwise. */
    public static BasicInstruction<BoolFv> rotCcw(BoolFe a) { return new UnOp<>(a, BoolFv::new, (aa, rr) -> rr.rotateCCW(aa)); }
}

/** Performs a unary operation on a BoolField. */
class UnOp<T extends BoolField<T>, R extends BoolField<R>> extends BasicInstruction<R> {
    private final T orig;
    private final BiConsumer<T, R> op;

    /**
     * Creates a new UnOp
     * @param orig The original BoolField to operate on.
     * @param resSupplier A supplier for the result BoolField to store the result of the operation.
     * @param op The operation to perform.
     */
    public UnOp(T orig, Supplier<R> resSupplier, BiConsumer<T, R> op) {
        this.orig = orig;
        this.op = op;

        returnValue(resSupplier.get());
    }

    @Override
    public boolean execute() {
        op.accept(orig, getReturn());
        return true;
    }
}

/** Performs a binary operation on two BoolField. */
class BiOp<T extends BoolField<T>, U extends BoolField<U>, R extends BoolField<R>> extends BasicInstruction<R> {
    private final T a;
    private final U b;
    private final TriConsumer<T, U, R> op;

    /**
     * Creates a new BiOp
     * @param a The first BoolField to operate on.
     * @param b The second BoolField to operate on.
     * @param resSupplier A supplier for the result BoolField to store the result of the operation.
     * @param op The operation to perform.
     */
    public BiOp(T a, U b, Supplier<R> resSupplier, TriConsumer<T, U, R> op) {
        this.a = a;
        this.b = b;
        this.op = op;

        returnValue(resSupplier.get());
    }

    @Override
    public boolean execute() {
        op.accept(a, b, getReturn());
        return true;
    }
}

/** Generic BoolField if-then-else. */
class bIf<T extends BoolField<T>> extends Procedure<T> {
    /**
     * Creates a new bIf procedure.
     * @param cond The condition.
     * @param t The value to return if the condition is true.
     * @param f The value to return if the condition is false.
     * @param and Logical AND operation on two fields.
     * @param or  Logical OR operation on two fields.
     * @param not Logical NOT operation on a field.
     */
    public bIf(T cond, T t, T f,
               BiFunction<T, T, Instruction<T>> and,
               BiFunction<T, T, Instruction<T>> or,
               Function<T, Instruction<T>> not
    ) {
        returnValue(call(or.apply(
                call(and.apply(cond, t)),
                call(and.apply(call(not.apply(cond)), f))
        )));
    }
}

/** Generic BoolField stack reduction. */
class bRedStack<T extends BoolFieldT<T>, R extends BoolFieldS<R>> extends BasicInstruction<VoidF> {
    private final T orig;
    private final R[] dest;
    private final Function<T, R[]> stacker;

    /**
     * Creates a new bRedStack.
     * @param orig The original BoolField to reduce.
     * @param dest The destination array of BoolFields to store the reduced values.
     * @param stacker A function that takes a T and returns an array of BoolFieldS.
     */
    public bRedStack(T orig, R[] dest, Function<T, R[]> stacker) {
        this.orig = orig;
        this.dest = dest;
        this.stacker = stacker;

        returnValue(new VoidF());
    }

    @Override
    public boolean execute() {
        R[] stack = stacker.apply(orig);

        if (stack.length != dest.length) throw new IllegalStateException(
                "Invalid reduction: expected " + dest.length + " levels, but got " + stack.length
        );

        for (int i = 0; i < dest.length; i++) dest[i].set(stack[i]);
        return true;
    }
}
