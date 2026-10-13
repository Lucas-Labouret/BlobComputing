package language.instruction.instructionSet;

import language.field.BoolFieldManager;
import language.field.VoidF;
import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.BasicInstruction;
import language.instruction.Instruction;
import language.instruction.Procedure;
import utils.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/** A set of operations for integer values. */
public class IntOp {
    /** Broadcasts a. */
    public static Procedure<IntVe> broadcastVe(IntV a) { return new UnaryBitwiseOp<>(a, () -> new IntVe(a.n), BoolOp::broadcastVe); }
    /** Broadcasts a. */
    public static Procedure<IntVf> broadcastVf(IntV a) { return new UnaryBitwiseOp<>(a, () -> new IntVf(a.n), BoolOp::broadcastVf); }
    /** Broadcasts a. */
    public static Procedure<IntEv> broadcastEv(IntE a) { return new UnaryBitwiseOp<>(a, () -> new IntEv(a.n), BoolOp::broadcastEv); }
    /** Broadcasts a. */
    public static Procedure<IntEf> broadcastEf(IntE a) { return new UnaryBitwiseOp<>(a, () -> new IntEf(a.n), BoolOp::broadcastEf); }
    /** Broadcasts a. */
    public static Procedure<IntFv> broadcastFv(IntF a) { return new UnaryBitwiseOp<>(a, () -> new IntFv(a.n), BoolOp::broadcastFv); }
    /** Broadcasts a. */
    public static Procedure<IntFe> broadcastFe(IntF a) { return new UnaryBitwiseOp<>(a, () -> new IntFe(a.n), BoolOp::broadcastFe); }

    /** Transfers a. */
    public static Procedure<IntEv> transfer(IntVe a) { return new UnaryBitwiseOp<>(a, () -> new IntEv(a.n), BoolOp::transfer); }
    /** Transfers a. */
    public static Procedure<IntFv> transfer(IntVf a) { return new UnaryBitwiseOp<>(a, () -> new IntFv(a.n), BoolOp::transfer); }
    /** Transfers a. */
    public static Procedure<IntVe> transfer(IntEv a) { return new UnaryBitwiseOp<>(a, () -> new IntVe(a.n), BoolOp::transfer); }
    /** Transfers a. */
    public static Procedure<IntFe> transfer(IntEf a) { return new UnaryBitwiseOp<>(a, () -> new IntFe(a.n), BoolOp::transfer); }
    /** Transfers a. */
    public static Procedure<IntVf> transfer(IntFv a) { return new UnaryBitwiseOp<>(a, () -> new IntVf(a.n), BoolOp::transfer); }
    /** Transfers a. */
    public static Procedure<IntEf> transfer(IntFe a) { return new UnaryBitwiseOp<>(a, () -> new IntEf(a.n), BoolOp::transfer); }

    /** Rotates a clockwise. */
    public static Procedure<IntVf> rotCw(IntVe a) { return new UnaryBitwiseOp<>(a, () -> new IntVf(a.n), BoolOp::rotCw); }
    /** Rotates a clockwise. */
    public static Procedure<IntVe> rotCw(IntVf a) { return new UnaryBitwiseOp<>(a, () -> new IntVe(a.n), BoolOp::rotCw); }
    /** Rotates a clockwise. */
    public static Procedure<IntEf> rotCw(IntEv a) { return new UnaryBitwiseOp<>(a, () -> new IntEf(a.n), BoolOp::rotCw); }
    /** Rotates a clockwise. */
    public static Procedure<IntEv> rotCw(IntEf a) { return new UnaryBitwiseOp<>(a, () -> new IntEv(a.n), BoolOp::rotCw); }
    /** Rotates a clockwise. */
    public static Procedure<IntFe> rotCw(IntFv a) { return new UnaryBitwiseOp<>(a, () -> new IntFe(a.n), BoolOp::rotCw); }
    /** Rotates a clockwise. */
    public static Procedure<IntFv> rotCw(IntFe a) { return new UnaryBitwiseOp<>(a, () -> new IntFv(a.n), BoolOp::rotCw); }

    /** Rotates a counterclockwise. */
    public static Procedure<IntVf> rotCcw(IntVe a) { return new UnaryBitwiseOp<>(a, () -> new IntVf(a.n), BoolOp::rotCcw); }
    /** Rotates a counterclockwise. */
    public static Procedure<IntVe> rotCcw(IntVf a) { return new UnaryBitwiseOp<>(a, () -> new IntVe(a.n), BoolOp::rotCcw); }
    /** Rotates a counterclockwise. */
    public static Procedure<IntEf> rotCcw(IntEv a) { return new UnaryBitwiseOp<>(a, () -> new IntEf(a.n), BoolOp::rotCcw); }
    /** Rotates a counterclockwise. */
    public static Procedure<IntEv> rotCcw(IntEf a) { return new UnaryBitwiseOp<>(a, () -> new IntEv(a.n), BoolOp::rotCcw); }
    /** Rotates a counterclockwise. */
    public static Procedure<IntFe> rotCcw(IntFv a) { return new UnaryBitwiseOp<>(a, () -> new IntFe(a.n), BoolOp::rotCcw); }
    /** Rotates a counterclockwise. */
    public static Procedure<IntFv> rotCcw(IntFe a) { return new UnaryBitwiseOp<>(a, () -> new IntFv(a.n), BoolOp::rotCcw); }

    /** Computes NOT a. */
    public static Procedure<IntV > not(IntV  a) { return new UnaryBitwiseOp<>(a, () -> new IntV (a.n), BoolOp::not); }
    /** Computes NOT a. */
    public static Procedure<IntVe> not(IntVe a) { return new UnaryBitwiseOp<>(a, () -> new IntVe(a.n), BoolOp::not); }
    /** Computes NOT a. */
    public static Procedure<IntVf> not(IntVf a) { return new UnaryBitwiseOp<>(a, () -> new IntVf(a.n), BoolOp::not); }
    /** Computes NOT a. */
    public static Procedure<IntE > not(IntE  a) { return new UnaryBitwiseOp<>(a, () -> new IntE (a.n), BoolOp::not); }
    /** Computes NOT a. */
    public static Procedure<IntEv> not(IntEv a) { return new UnaryBitwiseOp<>(a, () -> new IntEv(a.n), BoolOp::not); }
    /** Computes NOT a. */
    public static Procedure<IntEf> not(IntEf a) { return new UnaryBitwiseOp<>(a, () -> new IntEf(a.n), BoolOp::not); }
    /** Computes NOT a. */
    public static Procedure<IntF > not(IntF  a) { return new UnaryBitwiseOp<>(a, () -> new IntF (a.n), BoolOp::not); }
    /** Computes NOT a. */
    public static Procedure<IntFv> not(IntFv a) { return new UnaryBitwiseOp<>(a, () -> new IntFv(a.n), BoolOp::not); }
    /** Computes NOT a. */
    public static Procedure<IntFe> not(IntFe a) { return new UnaryBitwiseOp<>(a, () -> new IntFe(a.n), BoolOp::not); }

    /** Computes -a. */
    public static Procedure<IntV > neg(IntV  a) { return new Neg<>(a, () -> IntV.of(1, a.n),  IntOp::not, IntOp::add); }
    /** Computes -a. */
    public static Procedure<IntVe> neg(IntVe a) { return new Neg<>(a, () -> IntVe.of(1, a.n), IntOp::not, IntOp::add); }
    /** Computes -a. */
    public static Procedure<IntVf> neg(IntVf a) { return new Neg<>(a, () -> IntVf.of(1, a.n), IntOp::not, IntOp::add); }
    /** Computes -a. */
    public static Procedure<IntE > neg(IntE  a) { return new Neg<>(a, () -> IntE.of(1, a.n),  IntOp::not, IntOp::add); }
    /** Computes -a. */
    public static Procedure<IntEv> neg(IntEv a) { return new Neg<>(a, () -> IntEv.of(1, a.n), IntOp::not, IntOp::add); }
    /** Computes -a. */
    public static Procedure<IntEf> neg(IntEf a) { return new Neg<>(a, () -> IntEf.of(1, a.n), IntOp::not, IntOp::add); }
    /** Computes -a. */
    public static Procedure<IntF > neg(IntF  a) { return new Neg<>(a, () -> IntF.of(1, a.n),  IntOp::not, IntOp::add); }
    /** Computes -a. */
    public static Procedure<IntFv> neg(IntFv a) { return new Neg<>(a, () -> IntFv.of(1, a.n), IntOp::not, IntOp::add); }
    /** Computes -a. */
    public static Procedure<IntFe> neg(IntFe a) { return new Neg<>(a, () -> IntFe.of(1, a.n), IntOp::not, IntOp::add); }

    /** Computes a AND b. */
    public static Procedure<IntV > and(IntV  a, IntV  b) { return new BinaryBitwiseOp<>(a, b, () -> new IntV (a.n), BoolOp::and); }
    /** Computes a AND b. */
    public static Procedure<IntVe> and(IntVe a, IntVe b) { return new BinaryBitwiseOp<>(a, b, () -> new IntVe(a.n), BoolOp::and); }
    /** Computes a AND b. */
    public static Procedure<IntVf> and(IntVf a, IntVf b) { return new BinaryBitwiseOp<>(a, b, () -> new IntVf(a.n), BoolOp::and); }
    /** Computes a AND b. */
    public static Procedure<IntE > and(IntE  a, IntE  b) { return new BinaryBitwiseOp<>(a, b, () -> new IntE (a.n), BoolOp::and); }
    /** Computes a AND b. */
    public static Procedure<IntEv> and(IntEv a, IntEv b) { return new BinaryBitwiseOp<>(a, b, () -> new IntEv(a.n), BoolOp::and); }
    /** Computes a AND b. */
    public static Procedure<IntEf> and(IntEf a, IntEf b) { return new BinaryBitwiseOp<>(a, b, () -> new IntEf(a.n), BoolOp::and); }
    /** Computes a AND b. */
    public static Procedure<IntF > and(IntF  a, IntF  b) { return new BinaryBitwiseOp<>(a, b, () -> new IntF (a.n), BoolOp::and); }
    /** Computes a AND b. */
    public static Procedure<IntFv> and(IntFv a, IntFv b) { return new BinaryBitwiseOp<>(a, b, () -> new IntFv(a.n), BoolOp::and); }
    /** Computes a AND b. */
    public static Procedure<IntFe> and(IntFe a, IntFe b) { return new BinaryBitwiseOp<>(a, b, () -> new IntFe(a.n), BoolOp::and); }

    /** Computes a OR b. */
    public static Procedure<IntV > or(IntV  a, IntV  b) { return new BinaryBitwiseOp<>(a, b, () -> new IntV (a.n), BoolOp::or); }
    /** Computes a OR b. */
    public static Procedure<IntVe> or(IntVe a, IntVe b) { return new BinaryBitwiseOp<>(a, b, () -> new IntVe(a.n), BoolOp::or); }
    /** Computes a OR b. */
    public static Procedure<IntVf> or(IntVf a, IntVf b) { return new BinaryBitwiseOp<>(a, b, () -> new IntVf(a.n), BoolOp::or); }
    /** Computes a OR b. */
    public static Procedure<IntE > or(IntE  a, IntE  b) { return new BinaryBitwiseOp<>(a, b, () -> new IntE (a.n), BoolOp::or); }
    /** Computes a OR b. */
    public static Procedure<IntEv> or(IntEv a, IntEv b) { return new BinaryBitwiseOp<>(a, b, () -> new IntEv(a.n), BoolOp::or); }
    /** Computes a OR b. */
    public static Procedure<IntEf> or(IntEf a, IntEf b) { return new BinaryBitwiseOp<>(a, b, () -> new IntEf(a.n), BoolOp::or); }
    /** Computes a OR b. */
    public static Procedure<IntF > or(IntF  a, IntF  b) { return new BinaryBitwiseOp<>(a, b, () -> new IntF (a.n), BoolOp::or); }
    /** Computes a OR b. */
    public static Procedure<IntFv> or(IntFv a, IntFv b) { return new BinaryBitwiseOp<>(a, b, () -> new IntFv(a.n), BoolOp::or); }
    /** Computes a OR b. */
    public static Procedure<IntFe> or(IntFe a, IntFe b) { return new BinaryBitwiseOp<>(a, b, () -> new IntFe(a.n), BoolOp::or); }

    /** Computes a XOR b. */
    public static Procedure<IntV > xor(IntV  a, IntV  b) { return new BinaryBitwiseOp<>(a, b, () -> new IntV (a.n), BoolOp::xor); }
    /** Computes a XOR b. */
    public static Procedure<IntVe> xor(IntVe a, IntVe b) { return new BinaryBitwiseOp<>(a, b, () -> new IntVe(a.n), BoolOp::xor); }
    /** Computes a XOR b. */
    public static Procedure<IntVf> xor(IntVf a, IntVf b) { return new BinaryBitwiseOp<>(a, b, () -> new IntVf(a.n), BoolOp::xor); }
    /** Computes a XOR b. */
    public static Procedure<IntE > xor(IntE  a, IntE  b) { return new BinaryBitwiseOp<>(a, b, () -> new IntE (a.n), BoolOp::xor); }
    /** Computes a XOR b. */
    public static Procedure<IntEv> xor(IntEv a, IntEv b) { return new BinaryBitwiseOp<>(a, b, () -> new IntEv(a.n), BoolOp::xor); }
    /** Computes a XOR b. */
    public static Procedure<IntEf> xor(IntEf a, IntEf b) { return new BinaryBitwiseOp<>(a, b, () -> new IntEf(a.n), BoolOp::xor); }
    /** Computes a XOR b. */
    public static Procedure<IntF > xor(IntF  a, IntF  b) { return new BinaryBitwiseOp<>(a, b, () -> new IntF (a.n), BoolOp::xor); }
    /** Computes a XOR b. */
    public static Procedure<IntFv> xor(IntFv a, IntFv b) { return new BinaryBitwiseOp<>(a, b, () -> new IntFv(a.n), BoolOp::xor); }
    /** Computes a XOR b. */
    public static Procedure<IntFe> xor(IntFe a, IntFe b) { return new BinaryBitwiseOp<>(a, b, () -> new IntFe(a.n), BoolOp::xor); }

    /** Left shifts a by k bits. */
    public static BasicInstruction<IntV > lShift(IntV  a, int k) { return new LShift<>(a, () -> new IntV (a.n), k, BoolV::zeroes ); }
    /** Left shifts a by k bits. */
    public static BasicInstruction<IntVe> lShift(IntVe a, int k) { return new LShift<>(a, () -> new IntVe(a.n), k, BoolVe::zeroes); }
    /** Left shifts a by k bits. */
    public static BasicInstruction<IntVf> lShift(IntVf a, int k) { return new LShift<>(a, () -> new IntVf(a.n), k, BoolVf::zeroes); }
    /** Left shifts a by k bits. */
    public static BasicInstruction<IntE > lShift(IntE  a, int k) { return new LShift<>(a, () -> new IntE (a.n), k, BoolE::zeroes ); }
    /** Left shifts a by k bits. */
    public static BasicInstruction<IntEv> lShift(IntEv a, int k) { return new LShift<>(a, () -> new IntEv(a.n), k, BoolEv::zeroes); }
    /** Left shifts a by k bits. */
    public static BasicInstruction<IntEf> lShift(IntEf a, int k) { return new LShift<>(a, () -> new IntEf(a.n), k, BoolEf::zeroes); }
    /** Left shifts a by k bits. */
    public static BasicInstruction<IntF > lShift(IntF  a, int k) { return new LShift<>(a, () -> new IntF (a.n), k, BoolF::zeroes ); }
    /** Left shifts a by k bits. */
    public static BasicInstruction<IntFv> lShift(IntFv a, int k) { return new LShift<>(a, () -> new IntFv(a.n), k, BoolFv::zeroes); }
    /** Left shifts a by k bits. */
    public static BasicInstruction<IntFe> lShift(IntFe a, int k) { return new LShift<>(a, () -> new IntFe(a.n), k, BoolFe::zeroes); }

    /** Right shifts a by k bits. */
    public static BasicInstruction<IntV > rShift(IntV  a, int k) { return new RShift<>(a, () -> new IntV (a.n), k, BoolV::zeroes ); }
    /** Right shifts a by k bits. */
    public static BasicInstruction<IntVe> rShift(IntVe a, int k) { return new RShift<>(a, () -> new IntVe(a.n), k, BoolVe::zeroes); }
    /** Right shifts a by k bits. */
    public static BasicInstruction<IntVf> rShift(IntVf a, int k) { return new RShift<>(a, () -> new IntVf(a.n), k, BoolVf::zeroes); }
    /** Right shifts a by k bits. */
    public static BasicInstruction<IntE > rShift(IntE  a, int k) { return new RShift<>(a, () -> new IntE (a.n), k, BoolE::zeroes ); }
    /** Right shifts a by k bits. */
    public static BasicInstruction<IntEv> rShift(IntEv a, int k) { return new RShift<>(a, () -> new IntEv(a.n), k, BoolEv::zeroes); }
    /** Right shifts a by k bits. */
    public static BasicInstruction<IntEf> rShift(IntEf a, int k) { return new RShift<>(a, () -> new IntEf(a.n), k, BoolEf::zeroes); }
    /** Right shifts a by k bits. */
    public static BasicInstruction<IntF > rShift(IntF  a, int k) { return new RShift<>(a, () -> new IntF (a.n), k, BoolF::zeroes ); }
    /** Right shifts a by k bits. */
    public static BasicInstruction<IntFv> rShift(IntFv a, int k) { return new RShift<>(a, () -> new IntFv(a.n), k, BoolFv::zeroes); }
    /** Right shifts a by k bits. */
    public static BasicInstruction<IntFe> rShift(IntFe a, int k) { return new RShift<>(a, () -> new IntFe(a.n), k, BoolFe::zeroes); }

    /** Computes a + b. */
    public static Procedure<IntV > add(IntV  a, IntV  b) { return new Add<>(a, b, () -> new IntV(a.n),  IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Computes a + b. */
    public static Procedure<IntVe> add(IntVe a, IntVe b) { return new Add<>(a, b, () -> new IntVe(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Computes a + b. */
    public static Procedure<IntVf> add(IntVf a, IntVf b) { return new Add<>(a, b, () -> new IntVf(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Computes a + b. */
    public static Procedure<IntE > add(IntE  a, IntE  b) { return new Add<>(a, b, () -> new IntE(a.n),  IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Computes a + b. */
    public static Procedure<IntEv> add(IntEv a, IntEv b) { return new Add<>(a, b, () -> new IntEv(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Computes a + b. */
    public static Procedure<IntEf> add(IntEf a, IntEf b) { return new Add<>(a, b, () -> new IntEf(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Computes a + b. */
    public static Procedure<IntF > add(IntF  a, IntF  b) { return new Add<>(a, b, () -> new IntF(a.n),  IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Computes a + b. */
    public static Procedure<IntFv> add(IntFv a, IntFv b) { return new Add<>(a, b, () -> new IntFv(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Computes a + b. */
    public static Procedure<IntFe> add(IntFe a, IntFe b) { return new Add<>(a, b, () -> new IntFe(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }

    /** Computes a - b. */
    public static Procedure<IntV > sub(IntV  a, IntV  b) { return new Sub<>(a, b, IntOp::add, IntOp::neg); }
    /** Computes a - b. */
    public static Procedure<IntVe> sub(IntVe a, IntVe b) { return new Sub<>(a, b, IntOp::add, IntOp::neg); }
    /** Computes a - b. */
    public static Procedure<IntVf> sub(IntVf a, IntVf b) { return new Sub<>(a, b, IntOp::add, IntOp::neg); }
    /** Computes a - b. */
    public static Procedure<IntE > sub(IntE  a, IntE  b) { return new Sub<>(a, b, IntOp::add, IntOp::neg); }
    /** Computes a - b. */
    public static Procedure<IntEv> sub(IntEv a, IntEv b) { return new Sub<>(a, b, IntOp::add, IntOp::neg); }
    /** Computes a - b. */
    public static Procedure<IntEf> sub(IntEf a, IntEf b) { return new Sub<>(a, b, IntOp::add, IntOp::neg); }
    /** Computes a - b. */
    public static Procedure<IntF > sub(IntF  a, IntF  b) { return new Sub<>(a, b, IntOp::add, IntOp::neg); }
    /** Computes a - b. */
    public static Procedure<IntFv> sub(IntFv a, IntFv b) { return new Sub<>(a, b, IntOp::add, IntOp::neg); }
    /** Computes a - b. */
    public static Procedure<IntFe> sub(IntFe a, IntFe b) { return new Sub<>(a, b, IntOp::add, IntOp::neg); }

    /** Computes whether a equals b. */
    public static Procedure<BoolV > eq(IntV  a, IntV  b) { return new Eq<>(a, b, IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Computes whether a equals b. */
    public static Procedure<BoolVe> eq(IntVe a, IntVe b) { return new Eq<>(a, b, IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Computes whether a equals b. */
    public static Procedure<BoolVf> eq(IntVf a, IntVf b) { return new Eq<>(a, b, IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Computes whether a equals b. */
    public static Procedure<BoolE > eq(IntE  a, IntE  b) { return new Eq<>(a, b, IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Computes whether a equals b. */
    public static Procedure<BoolEv> eq(IntEv a, IntEv b) { return new Eq<>(a, b, IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Computes whether a equals b. */
    public static Procedure<BoolEf> eq(IntEf a, IntEf b) { return new Eq<>(a, b, IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Computes whether a equals b. */
    public static Procedure<BoolF > eq(IntF  a, IntF  b) { return new Eq<>(a, b, IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Computes whether a equals b. */
    public static Procedure<BoolFv> eq(IntFv a, IntFv b) { return new Eq<>(a, b, IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Computes whether a equals b. */
    public static Procedure<BoolFe> eq(IntFe a, IntFe b) { return new Eq<>(a, b, IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }

    /** Computes whether a is greater than or equal to b. */
    public static Procedure<BoolV > gt(IntV  a, IntV  b) { return new GT<>(a, b, BoolV::new,  () -> new BoolV().ones(),  BoolOp::xor, BoolOp::fif); }
    /** Computes whether a is greater than or equal to b. */
    public static Procedure<BoolVe> gt(IntVe a, IntVe b) { return new GT<>(a, b, BoolVe::new, () -> new BoolVe().ones(), BoolOp::xor, BoolOp::fif); }
    /** Computes whether a is greater than or equal to b. */
    public static Procedure<BoolVf> gt(IntVf a, IntVf b) { return new GT<>(a, b, BoolVf::new, () -> new BoolVf().ones(), BoolOp::xor, BoolOp::fif); }
    /** Computes whether a is greater than or equal to b. */
    public static Procedure<BoolE > gt(IntE  a, IntE  b) { return new GT<>(a, b, BoolE::new,  () -> new BoolE().ones(),  BoolOp::xor, BoolOp::fif); }
    /** Computes whether a is greater than or equal to b. */
    public static Procedure<BoolEv> gt(IntEv a, IntEv b) { return new GT<>(a, b, BoolEv::new, () -> new BoolEv().ones(), BoolOp::xor, BoolOp::fif); }
    /** Computes whether a is greater than or equal to b. */
    public static Procedure<BoolEf> gt(IntEf a, IntEf b) { return new GT<>(a, b, BoolEf::new, () -> new BoolEf().ones(), BoolOp::xor, BoolOp::fif); }
    /** Computes whether a is greater than or equal to b. */
    public static Procedure<BoolF > gt(IntF  a, IntF  b) { return new GT<>(a, b, BoolF::new,  () -> new BoolF().ones(),  BoolOp::xor, BoolOp::fif); }
    /** Computes whether a is greater than or equal to b. */
    public static Procedure<BoolFv> gt(IntFv a, IntFv b) { return new GT<>(a, b, BoolFv::new, () -> new BoolFv().ones(), BoolOp::xor, BoolOp::fif); }
    /** Computes whether a is greater than or equal to b. */
    public static Procedure<BoolFe> gt(IntFe a, IntFe b) { return new GT<>(a, b, BoolFe::new, () -> new BoolFe().ones(), BoolOp::xor, BoolOp::fif); }

    /** Computes the absolute value of a. */
    public static Procedure<IntV > abs(IntV  a) { return new Abs<>(a, IntOp::fif, IntOp::neg); }
    /** Computes the absolute value of a. */
    public static Procedure<IntVe> abs(IntVe a) { return new Abs<>(a, IntOp::fif, IntOp::neg); }
    /** Computes the absolute value of a. */
    public static Procedure<IntVf> abs(IntVf a) { return new Abs<>(a, IntOp::fif, IntOp::neg); }
    /** Computes the absolute value of a. */
    public static Procedure<IntE > abs(IntE  a) { return new Abs<>(a, IntOp::fif, IntOp::neg); }
    /** Computes the absolute value of a. */
    public static Procedure<IntEv> abs(IntEv a) { return new Abs<>(a, IntOp::fif, IntOp::neg); }
    /** Computes the absolute value of a. */
    public static Procedure<IntEf> abs(IntEf a) { return new Abs<>(a, IntOp::fif, IntOp::neg); }
    /** Computes the absolute value of a. */
    public static Procedure<IntF > abs(IntF  a) { return new Abs<>(a, IntOp::fif, IntOp::neg); }
    /** Computes the absolute value of a. */
    public static Procedure<IntFv> abs(IntFv a) { return new Abs<>(a, IntOp::fif, IntOp::neg); }
    /** Computes the absolute value of a. */
    public static Procedure<IntFe> abs(IntFe a) { return new Abs<>(a, IntOp::fif, IntOp::neg); }

    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<IntV > fif(BoolV  cond, IntV  t, IntV  f) { return new iIf<>(cond, t, f, () -> new IntV (t.n), BoolOp::fif); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<IntVe> fif(BoolVe cond, IntVe t, IntVe f) { return new iIf<>(cond, t, f, () -> new IntVe(t.n), BoolOp::fif); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<IntVf> fif(BoolVf cond, IntVf t, IntVf f) { return new iIf<>(cond, t, f, () -> new IntVf(t.n), BoolOp::fif); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<IntE > fif(BoolE  cond, IntE  t, IntE  f) { return new iIf<>(cond, t, f, () -> new IntE (t.n), BoolOp::fif); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<IntEv> fif(BoolEv cond, IntEv t, IntEv f) { return new iIf<>(cond, t, f, () -> new IntEv(t.n), BoolOp::fif); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<IntEf> fif(BoolEf cond, IntEf t, IntEf f) { return new iIf<>(cond, t, f, () -> new IntEf(t.n), BoolOp::fif); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<IntF > fif(BoolF  cond, IntF  t, IntF  f) { return new iIf<>(cond, t, f, () -> new IntF (t.n), BoolOp::fif); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<IntFv> fif(BoolFv cond, IntFv t, IntFv f) { return new iIf<>(cond, t, f, () -> new IntFv(t.n), BoolOp::fif); }
    /** Computes t where cond is true, and f where cond is false. */
    public static Procedure<IntFe> fif(BoolFe cond, IntFe t, IntFe f) { return new iIf<>(cond, t, f, () -> new IntFe(t.n), BoolOp::fif); }

    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack0(IntVe a, IntV[] res) {
        BiFunction<BoolVe, BoolV[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack0(IntVf a, IntV[] res) {
        BiFunction<BoolVf, BoolV[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack0(IntEv a, IntE[] res) {
        BiFunction<BoolEv, BoolE[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack0(IntEf a, IntE[] res) {
        BiFunction<BoolEf, BoolE[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack0(IntFv a, IntF[] res) {
        BiFunction<BoolFv, BoolF[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack0(IntFe a, IntF[] res) {
        BiFunction<BoolFe, BoolF[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }

    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack1(IntVe a, IntV[] res) {
        BiFunction<BoolVe, BoolV[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack1(IntVf a, IntV[] res) {
        BiFunction<BoolVf, BoolV[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack1(IntEv a, IntE[] res) {
        BiFunction<BoolEv, BoolE[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack1(IntEf a, IntE[] res) {
        BiFunction<BoolEf, BoolE[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack1(IntFv a, IntF[] res) {
        BiFunction<BoolFv, BoolF[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStack1(IntFe a, IntF[] res) {
        BiFunction<BoolFe, BoolF[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }

    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMin(IntVe a, IntV[] res) {
        BiFunction<BoolVe, BoolV[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMin(IntVf a, IntV[] res) {
        BiFunction<BoolVf, BoolV[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMin(IntEv a, IntE[] res) {
        BiFunction<BoolEv, BoolE[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMin(IntEf a, IntE[] res) {
        BiFunction<BoolEf, BoolE[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMin(IntFv a, IntF[] res) {
        BiFunction<BoolFv, BoolF[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMin(IntFe a, IntF[] res) {
        BiFunction<BoolFe, BoolF[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }

    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMax(IntVe a, IntV[] res) {
        BiFunction<BoolVe, BoolV[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);

    }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMax(IntVf a, IntV[] res) {
        BiFunction<BoolVf, BoolV[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMax(IntEv a, IntE[] res) {
        BiFunction<BoolEv, BoolE[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMax(IntEf a, IntE[] res) {
        BiFunction<BoolEf, BoolE[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMax(IntFv a, IntF[] res) {
        BiFunction<BoolFv, BoolF[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure<VoidF> redStackMax(IntFe a, IntF[] res) {
        BiFunction<BoolFe, BoolF[], Instruction<VoidF>>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }

    /** Reduces a by minimum. */
    public static Procedure<IntV> redMin(IntVe a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), BoolV::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }
    /** Reduces a by minimum. */
    public static Procedure<IntV> redMin(IntVf a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), BoolV::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }
    /** Reduces a by minimum. */
    public static Procedure<IntE> redMin(IntEv a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), BoolE::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }
    /** Reduces a by minimum. */
    public static Procedure<IntE> redMin(IntEf a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), BoolE::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }
    /** Reduces a by minimum. */
    public static Procedure<IntF> redMin(IntFv a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), BoolF::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }
    /** Reduces a by minimum. */
    public static Procedure<IntF> redMin(IntFe a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), BoolF::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }

    /** Reduces a by maximum. */
    public static Procedure<IntV> redMax(IntVe a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), BoolV::new, IntOp::redStackMin, IntOp::gt, (cond, f, t) -> IntOp.fif(cond, t, f)); }
    /** Reduces a by maximum. */
    public static Procedure<IntV> redMax(IntVf a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), BoolV::new, IntOp::redStackMin, IntOp::gt, (cond, f, t) -> IntOp.fif(cond, t, f)); }
    /** Reduces a by maximum. */
    public static Procedure<IntE> redMax(IntEv a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), BoolE::new, IntOp::redStackMin, IntOp::gt, (cond, f, t) -> IntOp.fif(cond, t, f)); }
    /** Reduces a by maximum. */
    public static Procedure<IntE> redMax(IntEf a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), BoolE::new, IntOp::redStackMin, IntOp::gt, (cond, f, t) -> IntOp.fif(cond, t, f)); }
    /** Reduces a by maximum. */
    public static Procedure<IntF> redMax(IntFv a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), BoolF::new, IntOp::redStackMin, IntOp::gt, (cond, f, t) -> IntOp.fif(cond, t, f)); }
    /** Reduces a by maximum. */
    public static Procedure<IntF> redMax(IntFe a) { return new RedExtremum<>(a, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), BoolF::new, IntOp::redStackMin, IntOp::gt, (cond, f, t) -> IntOp.fif(cond, t, f)); }

    /** Reduces a by addition. */
    public static Procedure<IntV> redAdd(BoolVe a, int n) { return new RedAdd<>(a, BoolFieldManager.getBreadthV(), BoolV[]::new, IntV[]::new, BoolV::new, () -> new IntV(n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }
    /** Reduces a by addition. */
    public static Procedure<IntV> redAdd(BoolVf a, int n) { return new RedAdd<>(a, BoolFieldManager.getBreadthV(), BoolV[]::new, IntV[]::new, BoolV::new, () -> new IntV(n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }
    /** Reduces a by addition. */
    public static Procedure<IntE> redAdd(BoolEv a, int n) { return new RedAdd<>(a, BoolFieldManager.getBreadthE(), BoolE[]::new, IntE[]::new, BoolE::new, () -> new IntE(n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }
    /** Reduces a by addition. */
    public static Procedure<IntE> redAdd(BoolEf a, int n) { return new RedAdd<>(a, BoolFieldManager.getBreadthE(), BoolE[]::new, IntE[]::new, BoolE::new, () -> new IntE(n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }
    /** Reduces a by addition. */
    public static Procedure<IntF> redAdd(BoolFv a, int n) { return new RedAdd<>(a, BoolFieldManager.getBreadthF(), BoolF[]::new, IntF[]::new, BoolF::new, () -> new IntF(n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }
    /** Reduces a by addition. */
    public static Procedure<IntF> redAdd(BoolFe a, int n) { return new RedAdd<>(a, BoolFieldManager.getBreadthF(), BoolF[]::new, IntF[]::new, BoolF::new, () -> new IntF(n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }

    /** Reduces a by addition. */
    public static Procedure<IntV> redAdd(IntVe a) { return new RedAdd<>(a, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), IntOp::redStack0, IntOp::add); }
    /** Reduces a by addition. */
    public static Procedure<IntV> redAdd(IntVf a) { return new RedAdd<>(a, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), IntOp::redStack0, IntOp::add); }
    /** Reduces a by addition. */
    public static Procedure<IntE> redAdd(IntEv a) { return new RedAdd<>(a, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), IntOp::redStack0, IntOp::add); }
    /** Reduces a by addition. */
    public static Procedure<IntE> redAdd(IntEf a) { return new RedAdd<>(a, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), IntOp::redStack0, IntOp::add); }
    /** Reduces a by addition. */
    public static Procedure<IntF> redAdd(IntFv a) { return new RedAdd<>(a, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), IntOp::redStack0, IntOp::add); }
    /** Reduces a by addition. */
    public static Procedure<IntF> redAdd(IntFe a) { return new RedAdd<>(a, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), IntOp::redStack0, IntOp::add); }

    /** Converts boolean a to integer. */
    public static BasicInstruction<IntV > fromBool(BoolV  a, int n) { return new BoolToInt<>(a, () -> new IntV (n), BoolV::zeroes ); }
    /** Converts boolean a to integer. */
    public static BasicInstruction<IntVe> fromBool(BoolVe a, int n) { return new BoolToInt<>(a, () -> new IntVe(n), BoolVe::zeroes); }
    /** Converts boolean a to integer. */
    public static BasicInstruction<IntVf> fromBool(BoolVf a, int n) { return new BoolToInt<>(a, () -> new IntVf(n), BoolVf::zeroes); }
    /** Converts boolean a to integer. */
    public static BasicInstruction<IntE > fromBool(BoolE  a, int n) { return new BoolToInt<>(a, () -> new IntE (n), BoolE::zeroes ); }
    /** Converts boolean a to integer. */
    public static BasicInstruction<IntEv> fromBool(BoolEv a, int n) { return new BoolToInt<>(a, () -> new IntEv(n), BoolEv::zeroes); }
    /** Converts boolean a to integer. */
    public static BasicInstruction<IntEf> fromBool(BoolEf a, int n) { return new BoolToInt<>(a, () -> new IntEf(n), BoolEf::zeroes); }
    /** Converts boolean a to integer. */
    public static BasicInstruction<IntF > fromBool(BoolF  a, int n) { return new BoolToInt<>(a, () -> new IntF (n), BoolF::zeroes ); }
    /** Converts boolean a to integer. */
    public static BasicInstruction<IntFv> fromBool(BoolFv a, int n) { return new BoolToInt<>(a, () -> new IntFv(n), BoolFv::zeroes); }
    /** Converts boolean a to integer. */
    public static BasicInstruction<IntFe> fromBool(BoolFe a, int n) { return new BoolToInt<>(a, () -> new IntFe(n), BoolFe::zeroes); }

    /** Scans a from left to right. */
    public static Procedure<BoolV > scanLeft(IntV  a, BiFunction<BoolV,  BoolV,  Instruction<BoolV >> operation) { return new ScanLeft<>(a, BoolV ::new, operation); }
    /** Scans a from left to right. */
    public static Procedure<BoolVe> scanLeft(IntVe a, BiFunction<BoolVe, BoolVe, Instruction<BoolVe>> operation) { return new ScanLeft<>(a, BoolVe::new, operation); }
    /** Scans a from left to right. */
    public static Procedure<BoolVf> scanLeft(IntVf a, BiFunction<BoolVf, BoolVf, Instruction<BoolVf>> operation) { return new ScanLeft<>(a, BoolVf::new, operation); }
    /** Scans a from left to right. */
    public static Procedure<BoolE > scanLeft(IntE  a, BiFunction<BoolE,  BoolE,  Instruction<BoolE >> operation) { return new ScanLeft<>(a, BoolE ::new, operation); }
    /** Scans a from left to right. */
    public static Procedure<BoolEv> scanLeft(IntEv a, BiFunction<BoolEv, BoolEv, Instruction<BoolEv>> operation) { return new ScanLeft<>(a, BoolEv::new, operation); }
    /** Scans a from left to right. */
    public static Procedure<BoolEf> scanLeft(IntEf a, BiFunction<BoolEf, BoolEf, Instruction<BoolEf>> operation) { return new ScanLeft<>(a, BoolEf::new, operation); }
    /** Scans a from left to right. */
    public static Procedure<BoolF > scanLeft(IntF  a, BiFunction<BoolF,  BoolF,  Instruction<BoolF >> operation) { return new ScanLeft<>(a, BoolF ::new, operation); }
    /** Scans a from left to right. */
    public static Procedure<BoolFv> scanLeft(IntFv a, BiFunction<BoolFv, BoolFv, Instruction<BoolFv>> operation) { return new ScanLeft<>(a, BoolFv::new, operation); }
    /** Scans a from left to right. */
    public static Procedure<BoolFe> scanLeft(IntFe a, BiFunction<BoolFe, BoolFe, Instruction<BoolFe>> operation) { return new ScanLeft<>(a, BoolFe::new, operation); }

    /** Scans a from right to left. */
    public static Procedure<BoolV > scanRight(IntV  a, BiFunction<BoolV , BoolV , Instruction<BoolV >> operation) { return new ScanRight<>(a, BoolV ::new, operation); }
    /** Scans a from right to left. */
    public static Procedure<BoolVe> scanRight(IntVe a, BiFunction<BoolVe, BoolVe, Instruction<BoolVe>> operation) { return new ScanRight<>(a, BoolVe::new, operation); }
    /** Scans a from right to left. */
    public static Procedure<BoolVf> scanRight(IntVf a, BiFunction<BoolVf, BoolVf, Instruction<BoolVf>> operation) { return new ScanRight<>(a, BoolVf::new, operation); }
    /** Scans a from right to left. */
    public static Procedure<BoolE > scanRight(IntE  a, BiFunction<BoolE , BoolE , Instruction<BoolE >> operation) { return new ScanRight<>(a, BoolE ::new, operation); }
    /** Scans a from right to left. */
    public static Procedure<BoolEv> scanRight(IntEv a, BiFunction<BoolEv, BoolEv, Instruction<BoolEv>> operation) { return new ScanRight<>(a, BoolEv::new, operation); }
    /** Scans a from right to left. */
    public static Procedure<BoolEf> scanRight(IntEf a, BiFunction<BoolEf, BoolEf, Instruction<BoolEf>> operation) { return new ScanRight<>(a, BoolEf::new, operation); }
    /** Scans a from right to left. */
    public static Procedure<BoolF > scanRight(IntF  a, BiFunction<BoolF , BoolF , Instruction<BoolF >> operation) { return new ScanRight<>(a, BoolF ::new, operation); }
    /** Scans a from right to left. */
    public static Procedure<BoolFv> scanRight(IntFv a, BiFunction<BoolFv, BoolFv, Instruction<BoolFv>> operation) { return new ScanRight<>(a, BoolFv::new, operation); }
    /** Scans a from right to left. */
    public static Procedure<BoolFe> scanRight(IntFe a, BiFunction<BoolFe, BoolFe, Instruction<BoolFe>> operation) { return new ScanRight<>(a, BoolFe::new, operation); }
}

/** Applies a unary boolean operation to every bit of an integer field. */
class UnaryBitwiseOp<BI extends BoolField<BI>, I extends IntField<BI, I>,
                     BR extends BoolField<BR>, R extends IntField<BR, R>> extends Procedure<R> {
    /** Builds an instruction applying {@code op} from {@code a} to {@code res}. */
    public UnaryBitwiseOp(I a, Supplier<R> resSupplier, Function<BI, Instruction<BR>> op) {
        R res = tmp(resSupplier.get());
        for (int i = 0; i <= a.n; i++) set((call(op.apply(a.getBits()[i]))), res.getBits()[i]);
        returnValue(res);
    }
}

/** Applies a binary boolean operation to every bit of integer fields. */
class BinaryBitwiseOp<BI extends BoolField<BI>, I extends IntField<BI, I>,
                      BR extends BoolField<BR>, R extends IntField<BR, R>> extends Procedure<R> {
    /** Builds an instruction applying {@code operation} from {@code a} and {@code b} to {@code res}. */
    public BinaryBitwiseOp(I a, I b, Supplier<R> resSupplier,
                           BiFunction<BI, BI, Instruction<BR>> and) {
        if (a.n != b.n) throw new IllegalArgumentException("Cannot apply bitwise operation to IntVs of different sizes.");
        R res = tmp(resSupplier.get());
        for(int i = 0; i <= a.n; i++) set(call(and.apply(a.getBits()[i], b.getBits()[i])), res.getBits()[i]);
        returnValue(res);
    }
}

/** Builds the two's-complement negation of an integer field. */
class Neg<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure<I> {
    /** Builds a negation procedure using the supplied integer operations. */
    public Neg(I a,
               Supplier<I> one,
               Function<I, Instruction<I>> not,
               BiFunction<I, I, Instruction<I>> add) {
        returnValue(call(add.apply(call(not.apply(a)), one.get())));
    }
}

/** Shifts an integer field left and fills exposed bits with zeroes. */
class LShift<B extends BoolField<B>, I extends IntField<B, I>> extends BasicInstruction<I> {
    private final I a;
    private final int k;
    private final Consumer<B> zeroes;

    /** Builds a left-shift instruction. */
    public LShift(I a, Supplier<I> resSupplier, int k, Consumer<B> zeroes) {
        if (k > a.n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        if (k < 0) throw new IllegalArgumentException("Cannot left shift by a negative number of bits.");

        this.a = a;
        this.k = k;
        this.zeroes = zeroes;

        returnValue(resSupplier.get());
    }

    /** Executes the left shift. */
    @Override
    public boolean execute() {
        for (int i = 0; i <= a.n - k; i++) getReturn().getBits()[i].set(a.getBits()[i + k]);
        for (int i = a.n - k + 1; i <= a.n; i++) zeroes.accept(getReturn().getBits()[i]);
        return true;
    }
}

/** Shifts an integer field right and fills exposed bits with zeroes. */
class RShift<B extends BoolField<B>, I extends IntField<B, I>> extends BasicInstruction<I> {
    private final I a;
    private final int k;
    private final Consumer<B> zeroes;

    /** Builds a right-shift instruction. */
    public RShift(I a, Supplier<I> resSupplier, int k, Consumer<B> zeroes) {
        if (k > a.n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        if (k < 0) throw new IllegalArgumentException("Cannot right shift by a negative number of bits.");

        this.a = a;
        this.k = k;
        this.zeroes = zeroes;

        returnValue(resSupplier.get());
    }

    /** Executes the right shift. */
    @Override
    public boolean execute() {
        for (int i = a.n; i >= k ; i--) getReturn().getBits()[i].set(a.getBits()[i - k]);
        for (int i = 0; i < k; i++) zeroes.accept(getReturn().getBits()[i]);
        return true;
    }
}

/** Builds integer addition from bitwise operations. */
class Add<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure<I> {
    /** Builds an addition procedure. */
    public Add(I a, I b, Supplier<I> intSupplier,
               BiFunction<I, I, Instruction<I>> and,
               BiFunction<I, I, Instruction<I>> xor,
               BiFunction<I, Integer, Instruction<I>> lShift) {
        if (a.n != b.n) throw new IllegalArgumentException("Cannot add IntVs of different sizes.");

        I res = tmp(intSupplier.get());
        I carry = tmp(intSupplier.get());
        I temp = tmp(intSupplier.get());
        set(b, temp);
        set(a, res);

        for (int i = 0; i <= a.n; i++){
            set(call(and.apply(res, temp)), carry);
            set(call(xor.apply(res, temp)), res);
            set(call(lShift.apply(carry, 1)), temp);
        }

        returnValue(res);
    }
}

/** Builds integer subtraction from addition and negation. */
class Sub<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure<I> {
    /** Builds a subtraction procedure. */
    public Sub(I a, I b,
               BiFunction<I, I, Instruction<I>> add,
               Function<I, Instruction<I>> neg) {
        if (a.n != b.n) throw new IllegalArgumentException("Cannot subtract IntVs of different sizes.");
        returnValue(call(add.apply(a, call(neg.apply(b)))));
    }
}

/** Builds an integer equality comparison. */
class Eq<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure<B> {
    /** Builds an equality procedure. */
    public Eq(I a, I b,
              BiFunction<I, I, Instruction<I>> xor,
              Function<B, Instruction<B>> not,
              BiFunction<B, B, Instruction<B>> or,
              BiFunction<I, BiFunction<B, B, Instruction<B>>, Instruction<B>> scanLeft) {
        if (a.n != b.n) throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");
        returnValue(call(not.apply(call(scanLeft.apply(call(xor.apply(a, b)), or)))));
    }
}

/** Builds an integer greater-than-or-equal comparison. */
class GT<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure<B> {
    /** Builds a comparison procedure. */
    public GT(I a, I b,
              Supplier<B> boolSupplier, Supplier<B> trueSupplier,
              BiFunction<B, B, Instruction<B>> xor,
              TriFunction<B, B, B, Instruction<B>> fif) {
        if  (a.n != b.n) throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        B[] aBits = a.getBits();
        B[] bBits = b.getBits();

        B diffSign = tmp(boolSupplier.get());
        set(boolSupplier.get(), diffSign);
        set(call(xor.apply(aBits[0], bBits[0])), diffSign);

        B res = tmp(trueSupplier.get());;
        B diffBits = tmp(boolSupplier.get());

        set(trueSupplier.get(), res);
        set(boolSupplier.get(), diffBits);
        for (int i=a.n; i>=1; i--) {
            set(call(xor.apply(aBits[i], bBits[i])), diffBits);
            set(call(fif.apply(diffBits, aBits[i], res)), res);
        }

        returnValue(call(fif.apply(diffSign, bBits[0], res)));
    }
}

/** Builds the absolute value of an integer field. */
class Abs<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure<I> {
    /** Builds an absolute-value procedure. */
    public Abs(I a,
               TriFunction<B, I, I, Instruction<I>> fif,
               Function<I, Instruction<I>> neg
    ) {
        returnValue(call(fif.apply(a.getBits()[0], call(neg.apply(a)), a)));
    }
}

/** Selects between two integer fields using a boolean condition. */
class iIf<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure<I> {
    /** Builds a conditional integer selection procedure. */
    public iIf(B cond, I t, I f, Supplier<I> resSupplier,
              TriFunction<B, B, B, Instruction<B>> fif) {
        if (t.n != f.n) throw new IllegalArgumentException("Cannot IF IntVs of different sizes.");

        I res = tmp(resSupplier.get());
        set(resSupplier.get(), res);
        for(int i = 0; i <= t.n; i++) set(call(fif.apply(cond, t.getBits()[i], f.getBits()[i])), res.getBits()[i]);
        returnValue(res);
    }
}

/** Reduces each integer bit across a stack of field positions. */
class iRedStack<BI extends BoolField<BI>, I extends IntField<BI, I>,
                BR extends BoolField<BR>, R extends IntField<BR, R>> extends Procedure<VoidF> {
    /** Builds a stack-reduction procedure. */
    iRedStack(I a, R[] res, int breadth,
              BiFunction<BI, BR[], Instruction<VoidF>>[] redStacks,
              BiFunction<Integer, Integer, BR[][]> gen2DArray,
              Function<Integer, BR[]> genArray,
              Supplier<BR> boolSupplier) {
        if (res.length != breadth) throw new IllegalArgumentException("Result array length does not match breadth.");
        for (int i = 0; i < breadth; i++) if (res[i].n != a.n)
            throw new IllegalArgumentException("Cannot reduce IntVs of different sizes.");

        BR[][] veStacks = gen2DArray.apply(a.n + 1, breadth);
        for (int i = 0; i <= a.n; i++) {
            veStacks[i] = genArray.apply(breadth);
            for (int j = 0; j < breadth; j++) veStacks[i][j] = tmp(boolSupplier.get());
            call(redStacks[i].apply(a.getBits()[i], veStacks[i]));
        }

        BR[][] bitStacks = gen2DArray.apply(breadth, a.n + 1);
        IntField.transpose(veStacks, bitStacks, breadth - 1, a.n);
        
        for (int i = 0; i < breadth; i++) {
            BR[] resBits = res[i].getBits();
            for (int j = 0; j <= a.n; j++) set(bitStacks[i][j], resBits[j]);
        }

        returnValue(new VoidF());
    }
}

/** Builds a minimum or maximum reduction procedure. */
class RedExtremum<BI extends BoolField<BI>, I extends IntField<BI, I>,
                  BR extends BoolField<BR>, R extends IntField<BR, R>> extends Procedure<R> {
    /** Builds an extremum-reduction procedure. */
    public RedExtremum(I a, int breadth,
                       Function<Integer, R[]> genArray,
                       Supplier<R> intSupplier,
                       Supplier<BR> boolSupplier,
                       BiFunction<I, R[], Instruction<VoidF>> redStackNeutral,
                       BiFunction<R, R, Instruction<BR>> gt,
                       TriFunction<BR, R, R, Instruction<R>> fif) {
        R[] stack = genArray.apply(breadth);
        for (int i=0; i<stack.length; i++) stack[i] = tmp(intSupplier.get());
        call(redStackNeutral.apply(a, stack));

        R res = tmp(intSupplier.get());
        
        set(stack[0], res);
        BR greater = tmp(boolSupplier.get());
        for (int i=1; i<breadth; i++) {
            set(call(gt.apply(res, stack[i])), greater);
            set(call(fif.apply(greater, stack[i], res)), res);
        }

        returnValue(res);
    }
}

/** Builds an addition reduction procedure. */
class RedAdd<BI extends BoolField<BI>, I extends IntField<BI, I>,
             BR extends BoolField<BR>, R extends IntField<BR, R>> extends Procedure<R> {
    /** Builds a reduction from boolean fields into an integer field. */
    public RedAdd(BI a, int breadth,
                  Function<Integer, BR[]> genBoolArray,
                  Function<Integer, R[]> genIntArray,
                  Supplier<BR> boolSupplier,
                  Supplier<R> intSupplier,
                  BiFunction<BR, Integer, Instruction<R>> boolToInt,
                  BiFunction<BI, BR[], Instruction<VoidF>> redStack0,
                  BiFunction<R, R, Instruction<R>> add) {
        R res = tmp(intSupplier.get());

        BR[] stackB = genBoolArray.apply(breadth);
        for (int i = 0; i < breadth; i++) stackB[i] = tmp(boolSupplier.get());
        call(redStack0.apply(a, stackB));
        
        R[] stack = genIntArray.apply(breadth);
        for (int i = 0; i < breadth; i++) stack[i] = tmp(intSupplier.get());
        for (int i = 0; i < breadth; i++) set(call(boolToInt.apply(stackB[i], res.n)), stack[i]);

        set(stack[0], res);
        for (int i = 1; i < breadth; i++) set(call(add.apply(res, stack[i])), res);
        returnValue(res);
    }
    
    /** Builds a reduction from integer fields into an integer field. */
    public RedAdd(I a, int breadth,
                  Function<Integer, R[]> genArray,
                  Supplier<R> intSupplier,
                  BiFunction<I, R[], Instruction<VoidF>> redStack0,
                  BiFunction<R, R, Instruction<R>> add) {

        R[] stack = genArray.apply(breadth);
        for (int i = 0; i < breadth; i++) stack[i] = tmp(intSupplier.get());
        call(redStack0.apply(a, stack));

        R res = tmp(intSupplier.get());
        set(stack[0], res);
        for (int i = 1; i < breadth; i++) set(call(add.apply(res, stack[i])), res);
        returnValue(res);
    }
}

/** Converts a boolean field into the sign bit of an integer field. */
class BoolToInt<B extends BoolField<B>, I extends IntField<B, I>> extends BasicInstruction<I> {
    private final B a;
    private final Consumer<B> zeroes;

    /** Builds a boolean-to-integer conversion instruction. */
    public BoolToInt(B a, Supplier<I> resSupplier, Consumer<B> zeroes) {
        this.a = a;
        this.zeroes = zeroes;

        returnValue(resSupplier.get());
    }

    /** Executes the conversion. */
    @Override
    public boolean execute() {
        I res = getReturn();
        for (int i=0; i < res.n; i++) zeroes.accept(res.getBits()[i]);
        res.getBits()[res.n].set(a);
        return true;
    }
}

/** Scans integer bits from the least significant bit toward the most significant bit. */
class ScanLeft<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure<B> {
    /** Builds a left scan procedure. */
    public ScanLeft(I a, Supplier<B> resSupplier,
                    BiFunction<B, B, Instruction<B>> operation) {
        B res = tmp(resSupplier.get());
        set(a.getBits()[0], res);
        for (int i = 1; i <= a.n; i++)
            set(call(operation.apply(a.getBits()[i], res)), res);
        returnValue(res);
    }
}

/** Scans integer bits from the most significant bit toward the least significant bit. */
class ScanRight<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure<B> {
    /** Builds a right scan procedure. */
    public ScanRight(I a, Supplier<B> resSupplier,
                     BiFunction<B, B, Instruction<B>> operation) {
        B res = tmp(resSupplier.get());
        set(a.getBits()[a.n], res);
        for (int i = a.n - 1; i >= 0; i--)
            set(call(operation.apply(a.getBits()[i], res)), res);
        returnValue(res);
    }
}