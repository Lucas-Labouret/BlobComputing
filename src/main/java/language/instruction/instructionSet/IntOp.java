package language.instruction.instructionSet;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.BasicInstruction;
import language.instruction.Instruction;
import language.instruction.Procedure;
import language.field.BoolFieldManager;
import utils.QuadFunction;
import utils.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/** A set of operations for integer values. */
public class IntOp {
    /** Broadcast a to res. */
    public static Procedure broadcast(IntV a, IntVe res) { return new UnaryBitwiseOp<>(a, res, BoolOp::broadcast); }
    /** Broadcast a to res. */
    public static Procedure broadcast(IntV a, IntVf res) { return new UnaryBitwiseOp<>(a, res, BoolOp::broadcast); }
    /** Broadcast a to res. */
    public static Procedure broadcast(IntE a, IntEv res) { return new UnaryBitwiseOp<>(a, res, BoolOp::broadcast); }
    /** Broadcast a to res. */
    public static Procedure broadcast(IntE a, IntEf res) { return new UnaryBitwiseOp<>(a, res, BoolOp::broadcast); }
    /** Broadcast a to res. */
    public static Procedure broadcast(IntF a, IntFv res) { return new UnaryBitwiseOp<>(a, res, BoolOp::broadcast); }
    /** Broadcast a to res. */
    public static Procedure broadcast(IntF a, IntFe res) { return new UnaryBitwiseOp<>(a, res, BoolOp::broadcast); }

    /** Transfer a to res. */
    public static Procedure transfer(IntVe a, IntEv res) { return new UnaryBitwiseOp<>(a, res, BoolOp::transfer); }
    /** Transfer a to res. */
    public static Procedure transfer(IntVf a, IntFv res) { return new UnaryBitwiseOp<>(a, res, BoolOp::transfer); }
    /** Transfer a to res. */
    public static Procedure transfer(IntEv a, IntVe res) { return new UnaryBitwiseOp<>(a, res, BoolOp::transfer); }
    /** Transfer a to res. */
    public static Procedure transfer(IntEf a, IntFe res) { return new UnaryBitwiseOp<>(a, res, BoolOp::transfer); }
    /** Transfer a to res. */
    public static Procedure transfer(IntFv a, IntVf res) { return new UnaryBitwiseOp<>(a, res, BoolOp::transfer); }
    /** Transfer a to res. */
    public static Procedure transfer(IntFe a, IntEf res) { return new UnaryBitwiseOp<>(a, res, BoolOp::transfer); }

    /** Rotate a clockwise to res. */
    public static Procedure rotCw(IntVe a, IntVf res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCw); }
    /** Rotate a clockwise to res. */
    public static Procedure rotCw(IntVf a, IntVe res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCw); }
    /** Rotate a clockwise to res. */
    public static Procedure rotCw(IntEv a, IntEf res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCw); }
    /** Rotate a clockwise to res. */
    public static Procedure rotCw(IntEf a, IntEv res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCw); }
    /** Rotate a clockwise to res. */
    public static Procedure rotCw(IntFv a, IntFe res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCw); }
    /** Rotate a clockwise to res. */
    public static Procedure rotCw(IntFe a, IntFv res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCw); }

    /** Rotate a counterclockwise to res. */
    public static Procedure rotCcw(IntVe a, IntVf res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCcw); }
    /** Rotate a counterclockwise to res. */
    public static Procedure rotCcw(IntVf a, IntVe res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCcw); }
    /** Rotate a counterclockwise to res. */
    public static Procedure rotCcw(IntEv a, IntEf res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCcw); }
    /** Rotate a counterclockwise to res. */
    public static Procedure rotCcw(IntEf a, IntEv res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCcw); }
    /** Rotate a counterclockwise to res. */
    public static Procedure rotCcw(IntFv a, IntFe res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCcw); }
    /** Rotate a counterclockwise to res. */
    public static Procedure rotCcw(IntFe a, IntFv res) { return new UnaryBitwiseOp<>(a, res, BoolOp::rotCcw); }

    /** Stores NOT a in res. */
    public static Procedure not(IntV  a, IntV  res) { return new UnaryBitwiseOp<>(a, res, BoolOp::not); }
    /** Stores NOT a in res. */
    public static Procedure not(IntVe a, IntVe res) { return new UnaryBitwiseOp<>(a, res, BoolOp::not); }
    /** Stores NOT a in res. */
    public static Procedure not(IntVf a, IntVf res) { return new UnaryBitwiseOp<>(a, res, BoolOp::not); }
    /** Stores NOT a in res. */
    public static Procedure not(IntE  a, IntE  res) { return new UnaryBitwiseOp<>(a, res, BoolOp::not); }
    /** Stores NOT a in res. */
    public static Procedure not(IntEv a, IntEv res) { return new UnaryBitwiseOp<>(a, res, BoolOp::not); }
    /** Stores NOT a in res. */
    public static Procedure not(IntEf a, IntEf res) { return new UnaryBitwiseOp<>(a, res, BoolOp::not); }
    /** Stores NOT a in res. */
    public static Procedure not(IntF  a, IntF  res) { return new UnaryBitwiseOp<>(a, res, BoolOp::not); }
    /** Stores NOT a in res. */
    public static Procedure not(IntFv a, IntFv res) { return new UnaryBitwiseOp<>(a, res, BoolOp::not); }
    /** Stores NOT a in res. */
    public static Procedure not(IntFe a, IntFe res) { return new UnaryBitwiseOp<>(a, res, BoolOp::not); }

    /** Stores -a in res. */
    public static Procedure neg(IntV  a, IntV  res) { return new Neg<>(a, res, () -> IntV.of(1, a.n),  IntOp::not, IntOp::add); }
    /** Stores -a in res. */
    public static Procedure neg(IntVe a, IntVe res) { return new Neg<>(a, res, () -> IntVe.of(1, a.n), IntOp::not, IntOp::add); }
    /** Stores -a in res. */
    public static Procedure neg(IntVf a, IntVf res) { return new Neg<>(a, res, () -> IntVf.of(1, a.n), IntOp::not, IntOp::add); }
    /** Stores -a in res. */
    public static Procedure neg(IntE  a, IntE  res) { return new Neg<>(a, res, () -> IntE.of(1, a.n),  IntOp::not, IntOp::add); }
    /** Stores -a in res. */
    public static Procedure neg(IntEv a, IntEv res) { return new Neg<>(a, res, () -> IntEv.of(1, a.n), IntOp::not, IntOp::add); }
    /** Stores -a in res. */
    public static Procedure neg(IntEf a, IntEf res) { return new Neg<>(a, res, () -> IntEf.of(1, a.n), IntOp::not, IntOp::add); }
    /** Stores -a in res. */
    public static Procedure neg(IntF  a, IntF  res) { return new Neg<>(a, res, () -> IntF.of(1, a.n),  IntOp::not, IntOp::add); }
    /** Stores -a in res. */
    public static Procedure neg(IntFv a, IntFv res) { return new Neg<>(a, res, () -> IntFv.of(1, a.n), IntOp::not, IntOp::add); }
    /** Stores -a in res. */
    public static Procedure neg(IntFe a, IntFe res) { return new Neg<>(a, res, () -> IntFe.of(1, a.n), IntOp::not, IntOp::add); }

    /** Stores a AND b in res. */
    public static Procedure and(IntV  a, IntV  b, IntV  res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::and); }
    /** Stores a AND b in res. */
    public static Procedure and(IntVe a, IntVe b, IntVe res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::and); }
    /** Stores a AND b in res. */
    public static Procedure and(IntVf a, IntVf b, IntVf res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::and); }
    /** Stores a AND b in res. */
    public static Procedure and(IntE  a, IntE  b, IntE  res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::and); }
    /** Stores a AND b in res. */
    public static Procedure and(IntEv a, IntEv b, IntEv res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::and); }
    /** Stores a AND b in res. */
    public static Procedure and(IntEf a, IntEf b, IntEf res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::and); }
    /** Stores a AND b in res. */
    public static Procedure and(IntF  a, IntF  b, IntF  res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::and); }
    /** Stores a AND b in res. */
    public static Procedure and(IntFv a, IntFv b, IntFv res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::and); }
    /** Stores a AND b in res. */
    public static Procedure and(IntFe a, IntFe b, IntFe res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::and); }

    /** Stores a OR b in res. */
    public static Procedure or(IntV  a, IntV  b, IntV  res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::or); }
    /** Stores a OR b in res. */
    public static Procedure or(IntVe a, IntVe b, IntVe res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::or); }
    /** Stores a OR b in res. */
    public static Procedure or(IntVf a, IntVf b, IntVf res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::or); }
    /** Stores a OR b in res. */
    public static Procedure or(IntE  a, IntE  b, IntE  res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::or); }
    /** Stores a OR b in res. */
    public static Procedure or(IntEv a, IntEv b, IntEv res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::or); }
    /** Stores a OR b in res. */
    public static Procedure or(IntEf a, IntEf b, IntEf res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::or); }
    /** Stores a OR b in res. */
    public static Procedure or(IntF  a, IntF  b, IntF  res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::or); }
    /** Stores a OR b in res. */
    public static Procedure or(IntFv a, IntFv b, IntFv res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::or); }
    /** Stores a OR b in res. */
    public static Procedure or(IntFe a, IntFe b, IntFe res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::or); }

    /** Stores a XOR b in res. */
    public static Procedure xor(IntV  a, IntV  b, IntV  res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::xor); }
    /** Stores a XOR b in res. */
    public static Procedure xor(IntVe a, IntVe b, IntVe res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::xor); }
    /** Stores a XOR b in res. */
    public static Procedure xor(IntVf a, IntVf b, IntVf res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::xor); }
    /** Stores a XOR b in res. */
    public static Procedure xor(IntE  a, IntE  b, IntE  res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::xor); }
    /** Stores a XOR b in res. */
    public static Procedure xor(IntEv a, IntEv b, IntEv res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::xor); }
    /** Stores a XOR b in res. */
    public static Procedure xor(IntEf a, IntEf b, IntEf res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::xor); }
    /** Stores a XOR b in res. */
    public static Procedure xor(IntF  a, IntF  b, IntF  res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::xor); }
    /** Stores a XOR b in res. */
    public static Procedure xor(IntFv a, IntFv b, IntFv res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::xor); }
    /** Stores a XOR b in res. */
    public static Procedure xor(IntFe a, IntFe b, IntFe res) { return new BinaryBitwiseOp<>(a, b, res, BoolOp::xor); }

    /** Stores a shifted left by k bits in res. */
    public static BasicInstruction lShift(IntV  a, IntV  res, int k) { return new LShift<>(a, res, k, BoolV::zeroes ); }
    /** Stores a shifted left by k bits in res. */
    public static BasicInstruction lShift(IntVe a, IntVe res, int k) { return new LShift<>(a, res, k, BoolVe::zeroes); }
    /** Stores a shifted left by k bits in res. */
    public static BasicInstruction lShift(IntVf a, IntVf res, int k) { return new LShift<>(a, res, k, BoolVf::zeroes); }
    /** Stores a shifted left by k bits in res. */
    public static BasicInstruction lShift(IntE  a, IntE  res, int k) { return new LShift<>(a, res, k, BoolE::zeroes ); }
    /** Stores a shifted left by k bits in res. */
    public static BasicInstruction lShift(IntEv a, IntEv res, int k) { return new LShift<>(a, res, k, BoolEv::zeroes); }
    /** Stores a shifted left by k bits in res. */
    public static BasicInstruction lShift(IntEf a, IntEf res, int k) { return new LShift<>(a, res, k, BoolEf::zeroes); }
    /** Stores a shifted left by k bits in res. */
    public static BasicInstruction lShift(IntF  a, IntF  res, int k) { return new LShift<>(a, res, k, BoolF::zeroes ); }
    /** Stores a shifted left by k bits in res. */
    public static BasicInstruction lShift(IntFv a, IntFv res, int k) { return new LShift<>(a, res, k, BoolFv::zeroes); }
    /** Stores a shifted left by k bits in res. */
    public static BasicInstruction lShift(IntFe a, IntFe res, int k) { return new LShift<>(a, res, k, BoolFe::zeroes); }

    /** Stores a shifted right by k bits in res. */
    public static BasicInstruction rShift(IntV  a, IntV  res, int k) { return new RShift<>(a, res, k, BoolV::zeroes ); }
    /** Stores a shifted right by k bits in res. */
    public static BasicInstruction rShift(IntVe a, IntVe res, int k) { return new RShift<>(a, res, k, BoolVe::zeroes); }
    /** Stores a shifted right by k bits in res. */
    public static BasicInstruction rShift(IntVf a, IntVf res, int k) { return new RShift<>(a, res, k, BoolVf::zeroes); }
    /** Stores a shifted right by k bits in res. */
    public static BasicInstruction rShift(IntE  a, IntE  res, int k) { return new RShift<>(a, res, k, BoolE::zeroes ); }
    /** Stores a shifted right by k bits in res. */
    public static BasicInstruction rShift(IntEv a, IntEv res, int k) { return new RShift<>(a, res, k, BoolEv::zeroes); }
    /** Stores a shifted right by k bits in res. */
    public static BasicInstruction rShift(IntEf a, IntEf res, int k) { return new RShift<>(a, res, k, BoolEf::zeroes); }
    /** Stores a shifted right by k bits in res. */
    public static BasicInstruction rShift(IntF  a, IntF  res, int k) { return new RShift<>(a, res, k, BoolF::zeroes ); }
    /** Stores a shifted right by k bits in res. */
    public static BasicInstruction rShift(IntFv a, IntFv res, int k) { return new RShift<>(a, res, k, BoolFv::zeroes); }
    /** Stores a shifted right by k bits in res. */
    public static BasicInstruction rShift(IntFe a, IntFe res, int k) { return new RShift<>(a, res, k, BoolFe::zeroes); }

    /** Stores a + b in res. */
    public static Procedure add(IntV  a, IntV  b, IntV  res) { return new Add<>(a, b, res, () -> new IntV(a.n),  IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Stores a + b in res. */
    public static Procedure add(IntVe a, IntVe b, IntVe res) { return new Add<>(a, b, res, () -> new IntVe(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Stores a + b in res. */
    public static Procedure add(IntVf a, IntVf b, IntVf res) { return new Add<>(a, b, res, () -> new IntVf(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Stores a + b in res. */
    public static Procedure add(IntE  a, IntE  b, IntE  res) { return new Add<>(a, b, res, () -> new IntE(a.n),  IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Stores a + b in res. */
    public static Procedure add(IntEv a, IntEv b, IntEv res) { return new Add<>(a, b, res, () -> new IntEv(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Stores a + b in res. */
    public static Procedure add(IntEf a, IntEf b, IntEf res) { return new Add<>(a, b, res, () -> new IntEf(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Stores a + b in res. */
    public static Procedure add(IntF  a, IntF  b, IntF  res) { return new Add<>(a, b, res, () -> new IntF(a.n),  IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Stores a + b in res. */
    public static Procedure add(IntFv a, IntFv b, IntFv res) { return new Add<>(a, b, res, () -> new IntFv(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }
    /** Stores a + b in res. */
    public static Procedure add(IntFe a, IntFe b, IntFe res) { return new Add<>(a, b, res, () -> new IntFe(a.n), IntOp::and, IntOp::xor, IntOp::lShift); }

    /** Stores a - b in res. */
    public static Procedure sub(IntV  a, IntV  b, IntV  res) { return new Sub<>(a, b, res, () -> new IntV(a.n), IntOp::add, IntOp::neg); }
    /** Stores a - b in res. */
    public static Procedure sub(IntVe a, IntVe b, IntVe res) { return new Sub<>(a, b, res, () -> new IntVe(a.n), IntOp::add, IntOp::neg); }
    /** Stores a - b in res. */
    public static Procedure sub(IntVf a, IntVf b, IntVf res) { return new Sub<>(a, b, res, () -> new IntVf(a.n), IntOp::add, IntOp::neg); }
    /** Stores a - b in res. */
    public static Procedure sub(IntE  a, IntE  b, IntE  res) { return new Sub<>(a, b, res, () -> new IntE(a.n), IntOp::add, IntOp::neg); }
    /** Stores a - b in res. */
    public static Procedure sub(IntEv a, IntEv b, IntEv res) { return new Sub<>(a, b, res, () -> new IntEv(a.n), IntOp::add, IntOp::neg); }
    /** Stores a - b in res. */
    public static Procedure sub(IntEf a, IntEf b, IntEf res) { return new Sub<>(a, b, res, () -> new IntEf(a.n), IntOp::add, IntOp::neg); }
    /** Stores a - b in res. */
    public static Procedure sub(IntF  a, IntF  b, IntF  res) { return new Sub<>(a, b, res, () -> new IntF(a.n), IntOp::add, IntOp::neg); }
    /** Stores a - b in res. */
    public static Procedure sub(IntFv a, IntFv b, IntFv res) { return new Sub<>(a, b, res, () -> new IntFv(a.n), IntOp::add, IntOp::neg); }
    /** Stores a - b in res. */
    public static Procedure sub(IntFe a, IntFe b, IntFe res) { return new Sub<>(a, b, res, () -> new IntFe(a.n), IntOp::add, IntOp::neg); }

    /** Stores whether a equals b in res. */
    public static Procedure eq(IntV  a, IntV  b, BoolV  res) { return new Eq<>(a, b, res, () -> new IntV(a.n),  IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Stores whether a equals b in res. */
    public static Procedure eq(IntVe a, IntVe b, BoolVe res) { return new Eq<>(a, b, res, () -> new IntVe(a.n), IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Stores whether a equals b in res. */
    public static Procedure eq(IntVf a, IntVf b, BoolVf res) { return new Eq<>(a, b, res, () -> new IntVf(a.n), IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Stores whether a equals b in res. */
    public static Procedure eq(IntE  a, IntE  b, BoolE  res) { return new Eq<>(a, b, res, () -> new IntE(a.n),  IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Stores whether a equals b in res. */
    public static Procedure eq(IntEv a, IntEv b, BoolEv res) { return new Eq<>(a, b, res, () -> new IntEv(a.n), IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Stores whether a equals b in res. */
    public static Procedure eq(IntEf a, IntEf b, BoolEf res) { return new Eq<>(a, b, res, () -> new IntEf(a.n), IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Stores whether a equals b in res. */
    public static Procedure eq(IntF  a, IntF  b, BoolF  res) { return new Eq<>(a, b, res, () -> new IntF(a.n),  IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Stores whether a equals b in res. */
    public static Procedure eq(IntFv a, IntFv b, BoolFv res) { return new Eq<>(a, b, res, () -> new IntFv(a.n), IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }
    /** Stores whether a equals b in res. */
    public static Procedure eq(IntFe a, IntFe b, BoolFe res) { return new Eq<>(a, b, res, () -> new IntFe(a.n), IntOp::xor, BoolOp::not, BoolOp::or, IntOp::scanLeft); }

    /** Stores whether a is greater than or equal to b in res. */
    public static Procedure gt(IntV  a, IntV  b, BoolV  res) { return new GT<>(a, b, res, BoolV::new,  () -> new BoolV().ones(),  BoolOp::xor, BoolOp::fif); }
    /** Stores whether a is greater than or equal to b in res. */
    public static Procedure gt(IntVe a, IntVe b, BoolVe res) { return new GT<>(a, b, res, BoolVe::new, () -> new BoolVe().ones(), BoolOp::xor, BoolOp::fif); }
    /** Stores whether a is greater than or equal to b in res. */
    public static Procedure gt(IntVf a, IntVf b, BoolVf res) { return new GT<>(a, b, res, BoolVf::new, () -> new BoolVf().ones(), BoolOp::xor, BoolOp::fif); }
    /** Stores whether a is greater than or equal to b in res. */
    public static Procedure gt(IntE  a, IntE  b, BoolE  res) { return new GT<>(a, b, res, BoolE::new,  () -> new BoolE().ones(),  BoolOp::xor, BoolOp::fif); }
    /** Stores whether a is greater than or equal to b in res. */
    public static Procedure gt(IntEv a, IntEv b, BoolEv res) { return new GT<>(a, b, res, BoolEv::new, () -> new BoolEv().ones(), BoolOp::xor, BoolOp::fif); }
    /** Stores whether a is greater than or equal to b in res. */
    public static Procedure gt(IntEf a, IntEf b, BoolEf res) { return new GT<>(a, b, res, BoolEf::new, () -> new BoolEf().ones(), BoolOp::xor, BoolOp::fif); }
    /** Stores whether a is greater than or equal to b in res. */
    public static Procedure gt(IntF  a, IntF  b, BoolF  res) { return new GT<>(a, b, res, BoolF::new,  () -> new BoolF().ones(),  BoolOp::xor, BoolOp::fif); }
    /** Stores whether a is greater than or equal to b in res. */
    public static Procedure gt(IntFv a, IntFv b, BoolFv res) { return new GT<>(a, b, res, BoolFv::new, () -> new BoolFv().ones(), BoolOp::xor, BoolOp::fif); }
    /** Stores whether a is greater than or equal to b in res. */
    public static Procedure gt(IntFe a, IntFe b, BoolFe res) { return new GT<>(a, b, res, BoolFe::new, () -> new BoolFe().ones(), BoolOp::xor, BoolOp::fif); }

    /** Stores the absolute value of a in res. */
    public static Procedure abs(IntV  a, IntV  res) { return new Abs<>(a, res, () -> new IntV(a.n),  IntOp::fif, IntOp::neg); }
    /** Stores the absolute value of a in res. */
    public static Procedure abs(IntVe a, IntVe res) { return new Abs<>(a, res, () -> new IntVe(a.n), IntOp::fif, IntOp::neg); }
    /** Stores the absolute value of a in res. */
    public static Procedure abs(IntVf a, IntVf res) { return new Abs<>(a, res, () -> new IntVf(a.n), IntOp::fif, IntOp::neg); }
    /** Stores the absolute value of a in res. */
    public static Procedure abs(IntE  a, IntE  res) { return new Abs<>(a, res, () -> new IntE(a.n),  IntOp::fif, IntOp::neg); }
    /** Stores the absolute value of a in res. */
    public static Procedure abs(IntEv a, IntEv res) { return new Abs<>(a, res, () -> new IntEv(a.n), IntOp::fif, IntOp::neg); }
    /** Stores the absolute value of a in res. */
    public static Procedure abs(IntEf a, IntEf res) { return new Abs<>(a, res, () -> new IntEf(a.n), IntOp::fif, IntOp::neg); }
    /** Stores the absolute value of a in res. */
    public static Procedure abs(IntF  a, IntF  res) { return new Abs<>(a, res, () -> new IntF(a.n),  IntOp::fif, IntOp::neg); }
    /** Stores the absolute value of a in res. */
    public static Procedure abs(IntFv a, IntFv res) { return new Abs<>(a, res, () -> new IntFv(a.n), IntOp::fif, IntOp::neg); }
    /** Stores the absolute value of a in res. */
    public static Procedure abs(IntFe a, IntFe res) { return new Abs<>(a, res, () -> new IntFe(a.n), IntOp::fif, IntOp::neg); }

    /** Stores t in res where cond is true, and f where cond is false. */
    public static Procedure fif(BoolV  cond, IntV  t, IntV  f, IntV  res) { return new iIf<>(cond, t, f, res, BoolOp::fif); }
    /** Stores t in res where cond is true, and f where cond is false. */
    public static Procedure fif(BoolVe cond, IntVe t, IntVe f, IntVe res) { return new iIf<>(cond, t, f, res, BoolOp::fif); }
    /** Stores t in res where cond is true, and f where cond is false. */
    public static Procedure fif(BoolVf cond, IntVf t, IntVf f, IntVf res) { return new iIf<>(cond, t, f, res, BoolOp::fif); }
    /** Stores t in res where cond is true, and f where cond is false. */
    public static Procedure fif(BoolE  cond, IntE  t, IntE  f, IntE  res) { return new iIf<>(cond, t, f, res, BoolOp::fif); }
    /** Stores t in res where cond is true, and f where cond is false. */
    public static Procedure fif(BoolEv cond, IntEv t, IntEv f, IntEv res) { return new iIf<>(cond, t, f, res, BoolOp::fif); }
    /** Stores t in res where cond is true, and f where cond is false. */
    public static Procedure fif(BoolEf cond, IntEf t, IntEf f, IntEf res) { return new iIf<>(cond, t, f, res, BoolOp::fif); }
    /** Stores t in res where cond is true, and f where cond is false. */
    public static Procedure fif(BoolF  cond, IntF  t, IntF  f, IntF  res) { return new iIf<>(cond, t, f, res, BoolOp::fif); }
    /** Stores t in res where cond is true, and f where cond is false. */
    public static Procedure fif(BoolFv cond, IntFv t, IntFv f, IntFv res) { return new iIf<>(cond, t, f, res, BoolOp::fif); }
    /** Stores t in res where cond is true, and f where cond is false. */
    public static Procedure fif(BoolFe cond, IntFe t, IntFe f, IntFe res) { return new iIf<>(cond, t, f, res, BoolOp::fif); }

    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack0(IntVe a, IntV[] res) {
        BiFunction<BoolVe, BoolV[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack0(IntVf a, IntV[] res) {
        BiFunction<BoolVf, BoolV[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack0(IntEv a, IntE[] res) {
        BiFunction<BoolEv, BoolE[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack0(IntEf a, IntE[] res) {
        BiFunction<BoolEf, BoolE[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack0(IntFv a, IntF[] res) {
        BiFunction<BoolFv, BoolF[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack0(IntFe a, IntF[] res) {
        BiFunction<BoolFe, BoolF[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }

    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack1(IntVe a, IntV[] res) {
        BiFunction<BoolVe, BoolV[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack1(IntVf a, IntV[] res) {
        BiFunction<BoolVf, BoolV[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack1(IntEv a, IntE[] res) {
        BiFunction<BoolEv, BoolE[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack1(IntEf a, IntE[] res) {
        BiFunction<BoolEf, BoolE[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack1(IntFv a, IntF[] res) {
        BiFunction<BoolFv, BoolF[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStack1(IntFe a, IntF[] res) {
        BiFunction<BoolFe, BoolF[], Instruction>[] stacks = new BiFunction[a.n+1];
        for (int i = 0; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }

    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMin(IntVe a, IntV[] res) {
        BiFunction<BoolVe, BoolV[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMin(IntVf a, IntV[] res) {
        BiFunction<BoolVf, BoolV[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMin(IntEv a, IntE[] res) {
        BiFunction<BoolEv, BoolE[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMin(IntEf a, IntE[] res) {
        BiFunction<BoolEf, BoolE[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMin(IntFv a, IntF[] res) {
        BiFunction<BoolFv, BoolF[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMin(IntFe a, IntF[] res) {
        BiFunction<BoolFe, BoolF[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack0;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }

    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMax(IntVe a, IntV[] res) {
        BiFunction<BoolVe, BoolV[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);

    }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMax(IntVf a, IntV[] res) {
        BiFunction<BoolVf, BoolV[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthV(), stacks, (i, j) -> new BoolV[i][j], BoolV[]::new, BoolV::new);
    }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMax(IntEv a, IntE[] res) {
        BiFunction<BoolEv, BoolE[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMax(IntEf a, IntE[] res) {
        BiFunction<BoolEf, BoolE[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthE(), stacks, (i, j) -> new BoolE[i][j], BoolE[]::new, BoolE::new);
    }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMax(IntFv a, IntF[] res) {
        BiFunction<BoolFv, BoolF[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    public static Procedure redStackMax(IntFe a, IntF[] res) {
        BiFunction<BoolFe, BoolF[], Instruction>[] stacks = new BiFunction[a.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= a.n; i++) stacks[i] = BoolOp::redStack1;
        return new iRedStack<>(a, res, BoolFieldManager.getBreadthF(), stacks, (i, j) -> new BoolF[i][j], BoolF[]::new, BoolF::new);
    }

    /** Reduces a by minimum into res. */
    public static Procedure redMin(IntVe a, IntV res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), BoolV::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }
    /** Reduces a by minimum into res. */
    public static Procedure redMin(IntVf a, IntV res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), BoolV::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }
    /** Reduces a by minimum into res. */
    public static Procedure redMin(IntEv a, IntE res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), BoolE::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }
    /** Reduces a by minimum into res. */
    public static Procedure redMin(IntEf a, IntE res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), BoolE::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }
    /** Reduces a by minimum into res. */
    public static Procedure redMin(IntFv a, IntF res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), BoolF::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }
    /** Reduces a by minimum into res. */
    public static Procedure redMin(IntFe a, IntF res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), BoolF::new, IntOp::redStackMax, IntOp::gt, IntOp::fif); }

    /** Reduces a by maximum into res. */
    public static Procedure redMax(IntVe a, IntV res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), BoolV::new, IntOp::redStackMin, IntOp::gt, (cond, f, t, r) -> IntOp.fif(cond, t, f, r)); }
    /** Reduces a by maximum into res. */
    public static Procedure redMax(IntVf a, IntV res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), BoolV::new, IntOp::redStackMin, IntOp::gt, (cond, f, t, r) -> IntOp.fif(cond, t, f, r)); }
    /** Reduces a by maximum into res. */
    public static Procedure redMax(IntEv a, IntE res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), BoolE::new, IntOp::redStackMin, IntOp::gt, (cond, f, t, r) -> IntOp.fif(cond, t, f, r)); }
    /** Reduces a by maximum into res. */
    public static Procedure redMax(IntEf a, IntE res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), BoolE::new, IntOp::redStackMin, IntOp::gt, (cond, f, t, r) -> IntOp.fif(cond, t, f, r)); }
    /** Reduces a by maximum into res. */
    public static Procedure redMax(IntFv a, IntF res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), BoolF::new, IntOp::redStackMin, IntOp::gt, (cond, f, t, r) -> IntOp.fif(cond, t, f, r)); }
    /** Reduces a by maximum into res. */
    public static Procedure redMax(IntFe a, IntF res) { return new RedExtremum<>(a, res, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), BoolF::new, IntOp::redStackMin, IntOp::gt, (cond, f, t, r) -> IntOp.fif(cond, t, f, r)); }

    /** Reduces a by addition into res. */
    public static Procedure redAdd(BoolVe a, IntV res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthV(), BoolV[]::new, IntV[]::new, BoolV::new, () -> new IntV(res.n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }
    /** Reduces a by addition into res. */
    public static Procedure redAdd(BoolVf a, IntV res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthV(), BoolV[]::new, IntV[]::new, BoolV::new, () -> new IntV(res.n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }
    /** Reduces a by addition into res. */
    public static Procedure redAdd(BoolEv a, IntE res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthE(), BoolE[]::new, IntE[]::new, BoolE::new, () -> new IntE(res.n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }
    /** Reduces a by addition into res. */
    public static Procedure redAdd(BoolEf a, IntE res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthE(), BoolE[]::new, IntE[]::new, BoolE::new, () -> new IntE(res.n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }
    /** Reduces a by addition into res. */
    public static Procedure redAdd(BoolFv a, IntF res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthF(), BoolF[]::new, IntF[]::new, BoolF::new, () -> new IntF(res.n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }
    /** Reduces a by addition into res. */
    public static Procedure redAdd(BoolFe a, IntF res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthF(), BoolF[]::new, IntF[]::new, BoolF::new, () -> new IntF(res.n), IntOp::fromBool, BoolOp::redStack0, IntOp::add); }

    /** Reduces a by addition into res. */
    public static Procedure redAdd(IntVe a, IntV res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), IntOp::redStack0, IntOp::add); }
    /** Reduces a by addition into res. */
    public static Procedure redAdd(IntVf a, IntV res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthV(), IntV[]::new, () -> new IntV(a.n), IntOp::redStack0, IntOp::add); }
    /** Reduces a by addition into res. */
    public static Procedure redAdd(IntEv a, IntE res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), IntOp::redStack0, IntOp::add); }
    /** Reduces a by addition into res. */
    public static Procedure redAdd(IntEf a, IntE res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthE(), IntE[]::new, () -> new IntE(a.n), IntOp::redStack0, IntOp::add); }
    /** Reduces a by addition into res. */
    public static Procedure redAdd(IntFv a, IntF res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), IntOp::redStack0, IntOp::add); }
    /** Reduces a by addition into res. */
    public static Procedure redAdd(IntFe a, IntF res) { return new RedAdd<>(a, res, BoolFieldManager.getBreadthF(), IntF[]::new, () -> new IntF(a.n), IntOp::redStack0, IntOp::add); }

    /** Converts boolean a to integer res. */
    public static BasicInstruction fromBool(BoolV  a, IntV  res) { return new BoolToInt<>(a, res, BoolV::zeroes ); }
    /** Converts boolean a to integer res. */
    public static BasicInstruction fromBool(BoolVe a, IntVe res) { return new BoolToInt<>(a, res, BoolVe::zeroes); }
    /** Converts boolean a to integer res. */
    public static BasicInstruction fromBool(BoolVf a, IntVf res) { return new BoolToInt<>(a, res, BoolVf::zeroes); }
    /** Converts boolean a to integer res. */
    public static BasicInstruction fromBool(BoolE  a, IntE  res) { return new BoolToInt<>(a, res, BoolE::zeroes ); }
    /** Converts boolean a to integer res. */
    public static BasicInstruction fromBool(BoolEv a, IntEv res) { return new BoolToInt<>(a, res, BoolEv::zeroes); }
    /** Converts boolean a to integer res. */
    public static BasicInstruction fromBool(BoolEf a, IntEf res) { return new BoolToInt<>(a, res, BoolEf::zeroes); }
    /** Converts boolean a to integer res. */
    public static BasicInstruction fromBool(BoolF  a, IntF  res) { return new BoolToInt<>(a, res, BoolF::zeroes ); }
    /** Converts boolean a to integer res. */
    public static BasicInstruction fromBool(BoolFv a, IntFv res) { return new BoolToInt<>(a, res, BoolFv::zeroes); }
    /** Converts boolean a to integer res. */
    public static BasicInstruction fromBool(BoolFe a, IntFe res) { return new BoolToInt<>(a, res, BoolFe::zeroes); }

    /** Scans a from left to right into boolean res. */
    public static Procedure scanLeft(IntV  a, BoolV  res, TriFunction<BoolV,  BoolV,  BoolV,  Instruction> operation) { return new ScanLeft<>(a, res, operation); }
    /** Scans a from left to right into boolean res. */
    public static Procedure scanLeft(IntVe a, BoolVe res, TriFunction<BoolVe, BoolVe, BoolVe, Instruction> operation) { return new ScanLeft<>(a, res, operation); }
    /** Scans a from left to right into boolean res. */
    public static Procedure scanLeft(IntVf a, BoolVf res, TriFunction<BoolVf, BoolVf, BoolVf, Instruction> operation) { return new ScanLeft<>(a, res, operation); }
    /** Scans a from left to right into boolean res. */
    public static Procedure scanLeft(IntE  a, BoolE  res, TriFunction<BoolE,  BoolE,  BoolE,  Instruction> operation) { return new ScanLeft<>(a, res, operation); }
    /** Scans a from left to right into boolean res. */
    public static Procedure scanLeft(IntEv a, BoolEv res, TriFunction<BoolEv, BoolEv, BoolEv, Instruction> operation) { return new ScanLeft<>(a, res, operation); }
    /** Scans a from left to right into boolean res. */
    public static Procedure scanLeft(IntEf a, BoolEf res, TriFunction<BoolEf, BoolEf, BoolEf, Instruction> operation) { return new ScanLeft<>(a, res, operation); }
    /** Scans a from left to right into boolean res. */
    public static Procedure scanLeft(IntF  a, BoolF  res, TriFunction<BoolF,  BoolF,  BoolF,  Instruction> operation) { return new ScanLeft<>(a, res, operation); }
    /** Scans a from left to right into boolean res. */
    public static Procedure scanLeft(IntFv a, BoolFv res, TriFunction<BoolFv, BoolFv, BoolFv, Instruction> operation) { return new ScanLeft<>(a, res, operation); }
    /** Scans a from left to right into boolean res. */
    public static Procedure scanLeft(IntFe a, BoolFe res, TriFunction<BoolFe, BoolFe, BoolFe, Instruction> operation) { return new ScanLeft<>(a, res, operation); }

    /** Scans a from right to left into boolean res. */
    public static Procedure scanRight(IntV  a, BoolV  res, TriFunction<BoolV,  BoolV,  BoolV , Instruction> operation) { return new ScanRight<>(a, res, operation); }
    /** Scans a from right to left into boolean res. */
    public static Procedure scanRight(IntVe a, BoolVe res, TriFunction<BoolVe, BoolVe, BoolVe, Instruction> operation) { return new ScanRight<>(a, res, operation); }
    /** Scans a from right to left into boolean res. */
    public static Procedure scanRight(IntVf a, BoolVf res, TriFunction<BoolVf, BoolVf, BoolVf, Instruction> operation) { return new ScanRight<>(a, res, operation); }
    /** Scans a from right to left into boolean res. */
    public static Procedure scanRight(IntE  a, BoolE  res, TriFunction<BoolE,  BoolE,  BoolE , Instruction> operation) { return new ScanRight<>(a, res, operation); }
    /** Scans a from right to left into boolean res. */
    public static Procedure scanRight(IntEv a, BoolEv res, TriFunction<BoolEv, BoolEv, BoolEv, Instruction> operation) { return new ScanRight<>(a, res, operation); }
    /** Scans a from right to left into boolean res. */
    public static Procedure scanRight(IntEf a, BoolEf res, TriFunction<BoolEf, BoolEf, BoolEf, Instruction> operation) { return new ScanRight<>(a, res, operation); }
    /** Scans a from right to left into boolean res. */
    public static Procedure scanRight(IntF  a, BoolF  res, TriFunction<BoolF,  BoolF,  BoolF , Instruction> operation) { return new ScanRight<>(a, res, operation); }
    /** Scans a from right to left into boolean res. */
    public static Procedure scanRight(IntFv a, BoolFv res, TriFunction<BoolFv, BoolFv, BoolFv, Instruction> operation) { return new ScanRight<>(a, res, operation); }
    /** Scans a from right to left into boolean res. */
    public static Procedure scanRight(IntFe a, BoolFe res, TriFunction<BoolFe, BoolFe, BoolFe, Instruction> operation) { return new ScanRight<>(a, res, operation); }
}

/** Applies a unary boolean operation to every bit of an integer field. */
class UnaryBitwiseOp<B1 extends BoolField<B1>, I1 extends IntField<B1, I1>,
                     B2 extends BoolField<B2>, I2 extends IntField<B2, I2>> extends Procedure {
    /** Builds an instruction applying {@code op} from {@code a} to {@code res}. */
    public UnaryBitwiseOp(I1 a, I2 res,
                          BiFunction<B1, B2, Instruction> op) {
        if (a.n != res.n) throw new IllegalArgumentException("Cannot apply bitwise operation to IntVs of different sizes.");
        for (int i = 0; i <= res.n; i++) call(op.apply(a.getBits()[i], res.getBits()[i]));
    }
}

/** Applies a binary boolean operation to every bit of integer fields. */
class BinaryBitwiseOp<B1 extends BoolField<B1>, I1 extends IntField<B1, I1>,
                      B2 extends BoolField<B2>, I2 extends IntField<B2, I2>> extends Procedure {
    /** Builds an instruction applying {@code operation} from {@code a} and {@code b} to {@code res}. */
    public BinaryBitwiseOp(I1 a, I1 b, I2 res,
                           TriFunction<B1, B1, B2, Instruction> and) {
        if (a.n != b.n || a.n != res.n) throw new IllegalArgumentException("Cannot apply bitwise operation to IntVs of different sizes.");
        for(int i = 0; i <= a.n; i++) call(and.apply(a.getBits()[i], b.getBits()[i], res.getBits()[i]));
    }
}

/** Builds the two's-complement negation of an integer field. */
class Neg<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure {
    /** Builds a negation procedure using the supplied integer operations. */
    public Neg(I a, I res, 
               Supplier<I> one,
               BiFunction<I, I, Instruction> not,
               TriFunction<I, I, I, Instruction> add) {
        if (a.n != res.n) throw new IllegalArgumentException("Cannot negate IntVs of different sizes.");
        call(not.apply(a, res));
        call(add.apply(res, one.get(), res));
    }
}

/** Shifts an integer field left and fills exposed bits with zeroes. */
class LShift<B extends BoolField<B>, I extends IntField<B, I>> implements BasicInstruction {
    private final I a;
    private final I res;
    private final int k;
    private final Consumer<B> zeroes;

    /** Builds a left-shift instruction. */
    public LShift(I a, I res, int k, Consumer<B> zeroes) {
        if (k > a.n) throw new IllegalArgumentException("Cannot left shift by more than n bits.");
        if (k < 0) throw new IllegalArgumentException("Cannot left shift by a negative number of bits.");
        if (a.n != res.n) throw new IllegalArgumentException("Cannot left shift IntVs of different sizes.");

        this.a = a;
        this.res = res;
        this.k = k;
        this.zeroes = zeroes;
    }

    /** Executes the left shift. */
    @Override
    public boolean exec() {
        for (int i = 0; i <= a.n - k; i++) res.getBits()[i].set(a.getBits()[i + k]);
        for (int i = a.n - k + 1; i <= a.n; i++) zeroes.accept(res.getBits()[i]);
        return true;
    }
}

/** Shifts an integer field right and fills exposed bits with zeroes. */
class RShift<B extends BoolField<B>, I extends IntField<B, I>> implements BasicInstruction {
    private final I a;
    private final I res;
    private final int k;
    private final Consumer<B> zeroes;

    /** Builds a right-shift instruction. */
    public RShift(I a, I res, int k, Consumer<B> zeroes) {
        if (k > a.n) throw new IllegalArgumentException("Cannot right shift by more than n bits.");
        if (k < 0) throw new IllegalArgumentException("Cannot right shift by a negative number of bits.");
        if (a.n != res.n) throw new IllegalArgumentException("Cannot right shift IntVs of different sizes.");

        this.a = a;
        this.res = res;
        this.k = k;
        this.zeroes = zeroes;
    }

    /** Executes the right shift. */
    @Override
    public boolean exec() {
        for (int i = a.n; i >= k ; i--) res.getBits()[i].set(a.getBits()[i - k]);
        for (int i = 0; i < k; i++) zeroes.accept(res.getBits()[i]);
        return true;
    }
}

/** Builds integer addition from bitwise operations. */
class Add<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure {
    /** Builds an addition procedure. */
    public Add(I a, I b, I res, Supplier<I> intSupplier,
               TriFunction<I, I, I, Instruction> and,
               TriFunction<I, I, I, Instruction> xor,
               TriFunction<I, I, Integer, Instruction> lShift) {
        if (a.n != b.n || a.n != res.n) throw new IllegalArgumentException("Cannot add IntVs of different sizes.");

        I carry = tmp(intSupplier.get());
        I tmp = tmp(intSupplier.get());
        set(b, tmp);
        set(a, res);

        for (int i = 0; i <= a.n; i++){
            call(and.apply(res, tmp, carry));
            call(xor.apply(res, tmp, res));
            call(lShift.apply(carry, tmp, 1));
        }
    }
}

/** Builds integer subtraction from addition and negation. */
class Sub<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure {
    /** Builds a subtraction procedure. */
    public Sub(I a, I b, I res, Supplier<I> intSupplier,
               TriFunction<I, I, I, Instruction> add,
               BiFunction<I, I, Instruction> neg) {
        if (a.n != b.n || a.n != res.n) throw new IllegalArgumentException("Cannot subtract IntVs of different sizes.");
        I negB = tmp(intSupplier.get());
        call(neg.apply(b, negB));
        call(add.apply(a, negB, res));
    }
}

/** Builds an integer equality comparison. */
class Eq<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure {
    /** Builds an equality procedure. */
    public Eq(I a, I b, B res,
              Supplier<I> intSupplier,
              TriFunction<I, I, I, Instruction> xor,
              BiFunction<B, B, Instruction> not,
              TriFunction<B, B, B, Instruction> or,
              TriFunction<I, B, TriFunction<B, B, B, Instruction>, Instruction> scanLeft) {
        if (a.n != b.n) throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");
        I xored = tmp(intSupplier.get());
        call(xor.apply(a, b, xored));
        call(scanLeft.apply(xored, res, or));
        call(not.apply(res, res));
    }
}

/** Builds an integer greater-than-or-equal comparison. */
class GT<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure {
    /** Builds a comparison procedure. */
    public GT(I a, I b, B res,
              Supplier<B> boolSupplier, Supplier<B> trueSupplier,
              TriFunction<B, B, B, Instruction> xor,
              QuadFunction<B, B, B, B, Instruction> fif) {
        if  (a.n != b.n) throw new IllegalArgumentException("Cannot compare IntVs of different sizes.");

        B[] aBits = a.getBits();
        B[] bBits = b.getBits();

        B diffSign = tmp(boolSupplier.get());
        call(xor.apply(aBits[0], bBits[0], diffSign));

        set(trueSupplier.get(), res);
        B diffBits = tmp(boolSupplier.get());
        for (int i=a.n; i>=1; i--) {
            call(xor.apply(aBits[i], bBits[i], diffBits));
            call(fif.apply(diffBits, aBits[i], res, res));
        }

        call(fif.apply(diffSign, bBits[0], res, res));
    }
}

/** Builds the absolute value of an integer field. */
class Abs<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure {
    /** Builds an absolute-value procedure. */
    public Abs(I a, I res, Supplier<I> intSupplier,
               QuadFunction<B, I, I, I, Instruction> fif,
               BiFunction<I, I, Instruction> neg
    ) {
        if (a.n != res.n) throw new IllegalArgumentException("Cannot take absolute value of IntVs of different sizes.");
        I negA = tmp(intSupplier.get());
        call(neg.apply(a, negA));
        call(fif.apply(a.getBits()[0], negA, a, res));
    }
}

/** Selects between two integer fields using a boolean condition. */
class iIf<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure {
    /** Builds a conditional integer selection procedure. */
    public iIf(B cond, I t, I f, I res,
              QuadFunction<B, B, B, B, Instruction> fif) {
        if (t.n != f.n || t.n != res.n) throw new IllegalArgumentException("Cannot IF IntVs of different sizes.");
        for(int i = 0; i <= t.n; i++) call(fif.apply(cond, t.getBits()[i], f.getBits()[i], res.getBits()[i]));
    }
}

/** Reduces each integer bit across a stack of field positions. */
class iRedStack<B1 extends BoolField<B1>, I1 extends IntField<B1, I1>,
               B2 extends BoolField<B2>, I2 extends IntField<B2, I2>> extends Procedure {
    /** Builds a stack-reduction procedure. */
    iRedStack(I1 a, I2[] res, int breadth,
             BiFunction<B1, B2[], Instruction>[] redStacks,
             BiFunction<Integer, Integer, B2[][]> gen2DArray,
             Function<Integer, B2[]> genArray,
             Supplier<B2> boolSupplier) {
        if (res.length != breadth) throw new IllegalArgumentException("Result array length does not match breadth.");
        for (int i = 0; i < breadth; i++) if (res[i].n != a.n)
            throw new IllegalArgumentException("Cannot reduce IntVs of different sizes.");

        B2[][] veStacks = gen2DArray.apply(a.n + 1, breadth);
        for (int i = 0; i <= a.n; i++) {
            veStacks[i] = genArray.apply(breadth);
            for (int j = 0; j < breadth; j++) veStacks[i][j] = tmp(boolSupplier.get());
            call(redStacks[i].apply(a.getBits()[i], veStacks[i]));
        }

        B2[][] bitStacks = gen2DArray.apply(breadth, a.n + 1);
        IntField.transpose(veStacks, bitStacks, breadth - 1, a.n);
        
        for (int i = 0; i < breadth; i++) {
            B2[] resBits = res[i].getBits();
            for (int j = 0; j <= a.n; j++) set(bitStacks[i][j], resBits[j]);
        }
    }
}

/** Builds a minimum or maximum reduction procedure. */
class RedExtremum<B1 extends BoolField<B1>, I1 extends IntField<B1, I1>,
                  B2 extends BoolField<B2>, I2 extends IntField<B2, I2>> extends Procedure {
    /** Builds an extremum-reduction procedure. */
    public RedExtremum(I1 a, I2 res, int breadth,
                       Function<Integer, I2[]> genArray,
                       Supplier<I2> intSupplier,
                       Supplier<B2> boolSupplier,
                       BiFunction<I1, I2[], Instruction> redStackNeutral,
                       TriFunction<I2, I2, B2, Instruction> gt,
                       QuadFunction<B2, I2, I2, I2, Instruction> fif) {
        if (a.n != res.n) throw new IllegalArgumentException("Cannot reduce IntVs of different sizes.");

        I2[] stack = genArray.apply(breadth);
        for (int i=0; i<stack.length; i++) stack[i] = tmp(intSupplier.get());
        call(redStackNeutral.apply(a, stack));
        
        set(stack[0], res);
        B2 greater = tmp(boolSupplier.get());
        for (int i=1; i<breadth; i++) {
            call(gt.apply(res, stack[i], greater));
            call(fif.apply(greater, stack[i], res, res));
        }
    }
}

/** Builds an addition reduction procedure. */
class RedAdd<B1 extends BoolField<B1>, I1 extends IntField<B1, I1>,
             B2 extends BoolField<B2>, I2 extends IntField<B2, I2>> extends Procedure {
    /** Builds a reduction from boolean fields into an integer field. */
    public RedAdd(B1 a, I2 res, int breadth,
                  Function<Integer, B2[]> genBoolArray,
                  Function<Integer, I2[]> genIntArray,
                  Supplier<B2> boolSupplier,
                  Supplier<I2> intSupplier,
                  BiFunction<B2, I2, Instruction> boolToInt,
                  BiFunction<B1, B2[], Instruction> redStack0,
                  TriFunction<I2, I2, I2, Instruction> add) {
        B2[] stackB = genBoolArray.apply(breadth);
        for (int i = 0; i < breadth; i++) stackB[i] = tmp(boolSupplier.get());
        call(redStack0.apply(a, stackB));
        
        I2[] stack = genIntArray.apply(breadth);
        for (int i = 0; i < breadth; i++) stack[i] = tmp(intSupplier.get());
        for (int i = 0; i < breadth; i++) call(boolToInt.apply(stackB[i], stack[i]));
        
        set(stack[0], res);
        for (int i = 1; i < breadth; i++) call(add.apply(res, stack[i], res));
    }
    
    /** Builds a reduction from integer fields into an integer field. */
    public RedAdd(I1 a, I2 res, int breadth,
                  Function<Integer, I2[]> genArray,
                  Supplier<I2> intSupplier,
                  BiFunction<I1, I2[], Instruction> redStack0,
                  TriFunction<I2, I2, I2, Instruction> add) {
        if (a.n != res.n) throw new IllegalArgumentException("Cannot reduce IntVs of different sizes.");
        
        I2[] stack = genArray.apply(breadth);
        for (int i = 0; i < breadth; i++) stack[i] = tmp(intSupplier.get());
        call(redStack0.apply(a, stack));
        
        set(stack[0], res);
        for (int i = 1; i < breadth; i++) call(add.apply(res, stack[i], res));
    }
}

/** Converts a boolean field into the sign bit of an integer field. */
class BoolToInt<B extends BoolField<B>, I extends IntField<B, I>> implements BasicInstruction {
    private final B a;
    private final I res;
    private final Consumer<B> zeroes;

    /** Builds a boolean-to-integer conversion instruction. */
    public BoolToInt(B a, I res, Consumer<B> zeroes) {
        this.a = a;
        this.res = res;
        this.zeroes = zeroes;
    }

    /** Executes the conversion. */
    @Override
    public boolean exec() {
        for (int i=0; i < res.n; i++) zeroes.accept(res.getBits()[i]);
        res.getBits()[res.n].set(a);
        return true;
    }
}

/** Scans integer bits from the least significant bit toward the most significant bit. */
class ScanLeft<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure {
    /** Builds a left scan procedure. */
    public ScanLeft(I a, B res,
                    TriFunction<B, B, B, Instruction> operation) {
        set(a.getBits()[0], res);
        for (int i = 1; i <= a.n; i++)
            call(operation.apply(a.getBits()[i], res, res));
    }
}

/** Scans integer bits from the most significant bit toward the least significant bit. */
class ScanRight<B extends BoolField<B>, I extends IntField<B, I>> extends Procedure {
    /** Builds a right scan procedure. */
    public ScanRight(I a, B res,
                     TriFunction<B, B, B, Instruction> operation) {
        set(a.getBits()[a.n], res);
        for (int i = a.n - 1; i >= 0; i--)
            call(operation.apply(a.getBits()[i], res, res));
    }
}