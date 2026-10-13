package language.instruction;

import language.cache.Cache;
import language.field.Field;
import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.instructionSet.*;
import ui.display.Styles;

import java.util.ArrayList;
import java.util.function.BiFunction;

/** Represents an instruction composed of a sequence of sub-instructions. */
@SuppressWarnings("unused")
public abstract non-sealed class Procedure<T extends Field<?>> extends Instruction<T> {
    /** The index of the current instruction being executed. */
    private int instrPtr = 0;
    /** The list of instructions that make up this procedure. */
    private final ArrayList<Instruction<?>> instr;

    /** Returns the current instruction pointer. */
    public int getInstrPtr() { return instrPtr; }
    /** Sets the current instruction pointer. Used to restore an execution state. */
    public void setInstrPtr(int instrPtr) { this.instrPtr = instrPtr; }

    /** Creates a new Procedure and registers it in the cache. */
    public Procedure() {
        instr = new ArrayList<>();
        Cache.register(this);
    }

    @Override
    public int leafCount() {
        int count = 0;
        for (Instruction<?> i : instr) count += i.leafCount();
        return count;
    }

    /** Returns a string representation of the instruction tree of this procedure. */
    public String instructionTree() { return instructionTree(0);}
    public String instructionTree(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.repeat("|  ", depth).append("--- ").append(this.getClass().getSimpleName()).append("\n");
        for (Instruction<?> i : instr) {
            if (i instanceof Procedure<?> p) sb.append(p.instructionTree(depth + 1));
            else sb.repeat("|  ", depth + 1).append("--- ").append(i.getClass().getSimpleName()).append("\n");
        }
        return sb.toString();
    }

    /** Returns the current instruction being executed. */
    public BasicInstruction<?> currentBasicInstruction() {
        try {
            return switch (instr.get(instrPtr)) {
                case BasicInstruction<?> i -> i;
                case Procedure<?> p -> p.currentBasicInstruction();
            };
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /** @return false if there are more instructions to execute, true if the loop is finished. */
    @Override
    public final boolean execute() {
        if (instr.isEmpty()) return true;

        try {
            boolean done = instr.get(instrPtr).exec();
            if (done) instrPtr++; // If the current instruction is done, we move to the next
        } catch (Exception e) {
            System.err.println("Error executing instruction " + instr.get(instrPtr).getClass().getSimpleName() + " at index " + instrPtr);
            throw e;
        }
        // There are no more instruction to perform.
        if (instrPtr == instr.size()) {
            instrPtr = 0; // Reset the instruction pointer for the next execution
            return true;
        }

        // There are more instructions to perform.
        return false;
    }

    /** Adds the given instruction to this procedure. */
    protected <F extends Field<?>> F call(Instruction<F> i) {
        instr.add(i);
        return tmp(i.getReturn());
    }

    @Override
    protected void returnValue(T value) {
        if (__ret == null) __ret = value;
        else throw new RuntimeException(
                "Procedure " + this.getClass().getSimpleName() + " has already returned a value."
        );
        retain(__ret);
    }

    // Wrapper functions to make writing procedures easier. These functions simply add the corresponding instruction to this procedure.

    /** Executes an arbitrary Java function. */
    protected void run(Runnable r) { call(new ArbitrarySysInstr(r)); }

    /** Indicates that the given field should be displayed in the UI. */
    protected <F extends Field<?>> void show(String name, F field) { call(new Show<>(name, field)); }
    /** Indicates that the given field should be displayed in the UI with the given style. */
    protected <F extends Field<?>> void show(String name, F field, Styles.Style style) { call(new Show<>(name, field, style)); }
    /** Indicates that the UI should take a snapshot of the current state of the fields. */
    protected void snapshot() { call(new Snapshot()); }

    /** Sets the value of the field in to the value of the field out. */
    protected <F extends Field<?>> void set(F in, F out) { call(new SetField<>(in, out)); }

    /** @return NOT a. */
    protected BoolV  not(BoolV  a) { return call(BoolOp.not(a)); }
    /** @return NOT a. */
    protected BoolVe not(BoolVe a) { return call(BoolOp.not(a)); }
    /** @return NOT a. */
    protected BoolVf not(BoolVf a) { return call(BoolOp.not(a)); }
    /** @return NOT a. */
    protected BoolE  not(BoolE  a) { return call(BoolOp.not(a)); }
    /** @return NOT a. */
    protected BoolEv not(BoolEv a) { return call(BoolOp.not(a)); }
    /** @return NOT a. */
    protected BoolEf not(BoolEf a) { return call(BoolOp.not(a)); }
    /** @return NOT a. */
    protected BoolF  not(BoolF  a) { return call(BoolOp.not(a)); }
    /** @return NOT a. */
    protected BoolFv not(BoolFv a) { return call(BoolOp.not(a)); }
    /** @return NOT a. */
    protected BoolFe not(BoolFe a) { return call(BoolOp.not(a)); }

    /** @return NOT a. */
    protected IntV  not(IntV  a) { return call(IntOp.not(a)); }
    /** @return NOT a. */
    protected IntVe not(IntVe a) { return call(IntOp.not(a)); }
    /** @return NOT a. */
    protected IntVf not(IntVf a) { return call(IntOp.not(a)); }
    /** @return NOT a. */
    protected IntE  not(IntE  a) { return call(IntOp.not(a)); }
    /** @return NOT a. */
    protected IntEv not(IntEv a) { return call(IntOp.not(a)); }
    /** @return NOT a. */
    protected IntEf not(IntEf a) { return call(IntOp.not(a)); }
    /** @return NOT a. */
    protected IntF  not(IntF  a) { return call(IntOp.not(a)); }
    /** @return NOT a. */
    protected IntFv not(IntFv a) { return call(IntOp.not(a)); }
    /** @return NOT a. */
    protected IntFe not(IntFe a) { return call(IntOp.not(a)); }

    /** @return -a. */
    protected IntV  neg(IntV  a) { return call(IntOp.neg(a)); }
    /** @return -a. */
    protected IntVe neg(IntVe a) { return call(IntOp.neg(a)); }
    /** @return -a. */
    protected IntVf neg(IntVf a) { return call(IntOp.neg(a)); }
    /** @return -a. */
    protected IntE  neg(IntE  a) { return call(IntOp.neg(a)); }
    /** @return -a. */
    protected IntEv neg(IntEv a) { return call(IntOp.neg(a)); }
    /** @return -a. */
    protected IntEf neg(IntEf a) { return call(IntOp.neg(a)); }
    /** @return -a. */
    protected IntF  neg(IntF  a) { return call(IntOp.neg(a)); }
    /** @return -a. */
    protected IntFv neg(IntFv a) { return call(IntOp.neg(a)); }
    /** @return -a. */
    protected IntFe neg(IntFe a) { return call(IntOp.neg(a)); }

    /** @return a shifted left by k bits. */
    protected IntV  lShift(IntV  a, int k) { return call(IntOp.lShift(a, k)); }
    /** @return a shifted left by k bits. */
    protected IntVe lShift(IntVe a, int k) { return call(IntOp.lShift(a, k)); }
    /** @return a shifted left by k bits. */
    protected IntVf lShift(IntVf a, int k) { return call(IntOp.lShift(a, k)); }
    /** @return a shifted left by k bits. */
    protected IntE  lShift(IntE  a, int k) { return call(IntOp.lShift(a, k)); }
    /** @return a shifted left by k bits. */
    protected IntEv lShift(IntEv a, int k) { return call(IntOp.lShift(a, k)); }
    /** @return a shifted left by k bits. */
    protected IntEf lShift(IntEf a, int k) { return call(IntOp.lShift(a, k)); }
    /** @return a shifted left by k bits. */
    protected IntF  lShift(IntF  a, int k) { return call(IntOp.lShift(a, k)); }
    /** @return a shifted left by k bits. */
    protected IntFv lShift(IntFv a, int k) { return call(IntOp.lShift(a, k)); }
    /** @return a shifted left by k bits. */
    protected IntFe lShift(IntFe a, int k) { return call(IntOp.lShift(a, k)); }

    /** @return a shifted right by k bits. */
    protected IntV  rShift(IntV  a, int k) { return call(IntOp.rShift(a, k)); }
    /** @return a shifted right by k bits. */
    protected IntVe rShift(IntVe a, int k) { return call(IntOp.rShift(a, k)); }
    /** @return a shifted right by k bits. */
    protected IntVf rShift(IntVf a, int k) { return call(IntOp.rShift(a, k)); }
    /** @return a shifted right by k bits. */
    protected IntE  rShift(IntE  a, int k) { return call(IntOp.rShift(a, k)); }
    /** @return a shifted right by k bits. */
    protected IntEv rShift(IntEv a, int k) { return call(IntOp.rShift(a, k)); }
    /** @return a shifted right by k bits. */
    protected IntEf rShift(IntEf a, int k) { return call(IntOp.rShift(a, k)); }
    /** @return a shifted right by k bits. */
    protected IntF  rShift(IntF  a, int k) { return call(IntOp.rShift(a, k)); }
    /** @return a shifted right by k bits. */
    protected IntFv rShift(IntFv a, int k) { return call(IntOp.rShift(a, k)); }
    /** @return a shifted right by k bits. */
    protected IntFe rShift(IntFe a, int k) { return call(IntOp.rShift(a, k)); }

    /** Converts boolean a to n-bits integer. */
    protected IntV  fromBool(BoolV  a, int n) { return call(IntOp.fromBool(a, n)); }
    /** Converts boolean a to n-bits integer. */
    protected IntVe fromBool(BoolVe a, int n) { return call(IntOp.fromBool(a, n)); }
    /** Converts boolean a to n-bits integer. */
    protected IntVf fromBool(BoolVf a, int n) { return call(IntOp.fromBool(a, n)); }
    /** Converts boolean a to n-bits integer. */
    protected IntE  fromBool(BoolE  a, int n) { return call(IntOp.fromBool(a, n)); }
    /** Converts boolean a to n-bits integer. */
    protected IntEv fromBool(BoolEv a, int n) { return call(IntOp.fromBool(a, n)); }
    /** Converts boolean a to n-bits integer. */
    protected IntEf fromBool(BoolEf a, int n) { return call(IntOp.fromBool(a, n)); }
    /** Converts boolean a to n-bits integer. */
    protected IntF  fromBool(BoolF  a, int n) { return call(IntOp.fromBool(a, n)); }
    /** Converts boolean a to n-bits integer. */
    protected IntFv fromBool(BoolFv a, int n) { return call(IntOp.fromBool(a, n)); }
    /** Converts boolean a to n-bits integer. */
    protected IntFe fromBool(BoolFe a, int n) { return call(IntOp.fromBool(a, n)); }

    /** @return a AND b. */
    protected BoolV  and(BoolV  a, BoolV  b) { return call(BoolOp.and(a, b)); }
    /** @return a AND b. */
    protected BoolVe and(BoolVe a, BoolVe b) { return call(BoolOp.and(a, b)); }
    /** @return a AND b. */
    protected BoolVf and(BoolVf a, BoolVf b) { return call(BoolOp.and(a, b)); }
    /** @return a AND b. */
    protected BoolE  and(BoolE  a, BoolE  b) { return call(BoolOp.and(a, b)); }
    /** @return a AND b. */
    protected BoolEv and(BoolEv a, BoolEv b) { return call(BoolOp.and(a, b)); }
    /** @return a AND b. */
    protected BoolEf and(BoolEf a, BoolEf b) { return call(BoolOp.and(a, b)); }
    /** @return a AND b. */
    protected BoolF  and(BoolF  a, BoolF  b) { return call(BoolOp.and(a, b)); }
    /** @return a AND b. */
    protected BoolFv and(BoolFv a, BoolFv b) { return call(BoolOp.and(a, b)); }
    /** @return a AND b. */
    protected BoolFe and(BoolFe a, BoolFe b) { return call(BoolOp.and(a, b)); }

    /** @return a AND b. */
    protected IntV  and(IntV  a, IntV  b) { return call(IntOp.and(a, b)); }
    /** @return a AND b. */
    protected IntVe and(IntVe a, IntVe b) { return call(IntOp.and(a, b)); }
    /** @return a AND b. */
    protected IntVf and(IntVf a, IntVf b) { return call(IntOp.and(a, b)); }
    /** @return a AND b. */
    protected IntE  and(IntE  a, IntE  b) { return call(IntOp.and(a, b)); }
    /** @return a AND b. */
    protected IntEv and(IntEv a, IntEv b) { return call(IntOp.and(a, b)); }
    /** @return a AND b. */
    protected IntEf and(IntEf a, IntEf b) { return call(IntOp.and(a, b)); }
    /** @return a AND b. */
    protected IntF  and(IntF  a, IntF  b) { return call(IntOp.and(a, b)); }
    /** @return a AND b. */
    protected IntFv and(IntFv a, IntFv b) { return call(IntOp.and(a, b)); }
    /** @return a AND b. */
    protected IntFe and(IntFe a, IntFe b) { return call(IntOp.and(a, b)); }

    /** @return a OR b. */
    protected BoolV  or(BoolV  a, BoolV  b) { return call(BoolOp.or(a, b)); }
    /** @return a OR b. */
    protected BoolVe or(BoolVe a, BoolVe b) { return call(BoolOp.or(a, b)); }
    /** @return a OR b. */
    protected BoolVf or(BoolVf a, BoolVf b) { return call(BoolOp.or(a, b)); }
    /** @return a OR b. */
    protected BoolE  or(BoolE  a, BoolE  b) { return call(BoolOp.or(a, b)); }
    /** @return a OR b. */
    protected BoolEv or(BoolEv a, BoolEv b) { return call(BoolOp.or(a, b)); }
    /** @return a OR b. */
    protected BoolEf or(BoolEf a, BoolEf b) { return call(BoolOp.or(a, b)); }
    /** @return a OR b. */
    protected BoolF  or(BoolF  a, BoolF  b) { return call(BoolOp.or(a, b)); }
    /** @return a OR b. */
    protected BoolFv or(BoolFv a, BoolFv b) { return call(BoolOp.or(a, b)); }
    /** @return a OR b. */
    protected BoolFe or(BoolFe a, BoolFe b) { return call(BoolOp.or(a, b)); }

    /** @return a OR b. */
    protected IntV  or(IntV  a, IntV  b) { return call(IntOp.or(a, b)); }
    /** @return a OR b. */
    protected IntVe or(IntVe a, IntVe b) { return call(IntOp.or(a, b)); }
    /** @return a OR b. */
    protected IntVf or(IntVf a, IntVf b) { return call(IntOp.or(a, b)); }
    /** @return a OR b. */
    protected IntE  or(IntE  a, IntE  b) { return call(IntOp.or(a, b)); }
    /** @return a OR b. */
    protected IntEv or(IntEv a, IntEv b) { return call(IntOp.or(a, b)); }
    /** @return a OR b. */
    protected IntEf or(IntEf a, IntEf b) { return call(IntOp.or(a, b)); }
    /** @return a OR b. */
    protected IntF  or(IntF  a, IntF  b) { return call(IntOp.or(a, b)); }
    /** @return a OR b. */
    protected IntFv or(IntFv a, IntFv b) { return call(IntOp.or(a, b)); }
    /** @return a OR b. */
    protected IntFe or(IntFe a, IntFe b) { return call(IntOp.or(a, b)); }

    /** @return a XOR b. */
    protected BoolV  xor(BoolV  a, BoolV  b) { return call(BoolOp.xor(a, b)); }
    /** @return a XOR b. */
    protected BoolVe xor(BoolVe a, BoolVe b) { return call(BoolOp.xor(a, b)); }
    /** @return a XOR b. */
    protected BoolVf xor(BoolVf a, BoolVf b) { return call(BoolOp.xor(a, b)); }
    /** @return a XOR b. */
    protected BoolE  xor(BoolE  a, BoolE  b) { return call(BoolOp.xor(a, b)); }
    /** @return a XOR b. */
    protected BoolEv xor(BoolEv a, BoolEv b) { return call(BoolOp.xor(a, b)); }
    /** @return a XOR b. */
    protected BoolEf xor(BoolEf a, BoolEf b) { return call(BoolOp.xor(a, b)); }
    /** @return a XOR b. */
    protected BoolF  xor(BoolF  a, BoolF  b) { return call(BoolOp.xor(a, b)); }
    /** @return a XOR b. */
    protected BoolFv xor(BoolFv a, BoolFv b) { return call(BoolOp.xor(a, b)); }
    /** @return a XOR b. */
    protected BoolFe xor(BoolFe a, BoolFe b) { return call(BoolOp.xor(a, b)); }

    /** @return a XOR b. */
    protected IntV  xor(IntV  a, IntV  b) { return call(IntOp.xor(a, b)); }
    /** @return a XOR b. */
    protected IntVe xor(IntVe a, IntVe b) { return call(IntOp.xor(a, b)); }
    /** @return a XOR b. */
    protected IntVf xor(IntVf a, IntVf b) { return call(IntOp.xor(a, b)); }
    /** @return a XOR b. */
    protected IntE  xor(IntE  a, IntE  b) { return call(IntOp.xor(a, b)); }
    /** @return a XOR b. */
    protected IntEv xor(IntEv a, IntEv b) { return call(IntOp.xor(a, b)); }
    /** @return a XOR b. */
    protected IntEf xor(IntEf a, IntEf b) { return call(IntOp.xor(a, b)); }
    /** @return a XOR b. */
    protected IntF  xor(IntF  a, IntF  b) { return call(IntOp.xor(a, b)); }
    /** @return a XOR b. */
    protected IntFv xor(IntFv a, IntFv b) { return call(IntOp.xor(a, b)); }
    /** @return a XOR b. */
    protected IntFe xor(IntFe a, IntFe b) { return call(IntOp.xor(a, b)); }

    /** @return t where cond is true, and f where cond is false. */
    protected BoolV  fif(BoolV  cond, BoolV  t, BoolV  f) { return call(BoolOp.fif(cond, t, f)); }
    /** @return t where cond is true, and f where cond is false. */
    protected BoolVe fif(BoolVe cond, BoolVe t, BoolVe f) { return call(BoolOp.fif(cond, t, f)); }
    /** @return t where cond is true, and f where cond is false. */
    protected BoolVf fif(BoolVf cond, BoolVf t, BoolVf f) { return call(BoolOp.fif(cond, t, f)); }
    /** @return t where cond is true, and f where cond is false. */
    protected BoolE  fif(BoolE  cond, BoolE  t, BoolE  f) { return call(BoolOp.fif(cond, t, f)); }
    /** @return t where cond is true, and f where cond is false. */
    protected BoolEv fif(BoolEv cond, BoolEv t, BoolEv f) { return call(BoolOp.fif(cond, t, f)); }
    /** @return t where cond is true, and f where cond is false. */
    protected BoolEf fif(BoolEf cond, BoolEf t, BoolEf f) { return call(BoolOp.fif(cond, t, f)); }
    /** @return t where cond is true, and f where cond is false. */
    protected BoolF  fif(BoolF  cond, BoolF  t, BoolF  f) { return call(BoolOp.fif(cond, t, f)); }
    /** @return t where cond is true, and f where cond is false. */
    protected BoolFv fif(BoolFv cond, BoolFv t, BoolFv f) { return call(BoolOp.fif(cond, t, f)); }
    /** @return t where cond is true, and f where cond is false. */
    protected BoolFe fif(BoolFe cond, BoolFe t, BoolFe f) { return call(BoolOp.fif(cond, t, f)); }

    /** @return t in res where cond is true, and f where cond is false. */
    protected IntV  fif(BoolV  cond, IntV  t, IntV  f) { return call(IntOp.fif(cond, t, f)); }
    /** @return t in res where cond is true, and f where cond is false. */
    protected IntVe fif(BoolVe cond, IntVe t, IntVe f) { return call(IntOp.fif(cond, t, f)); }
    /** @return t in res where cond is true, and f where cond is false. */
    protected IntVf fif(BoolVf cond, IntVf t, IntVf f) { return call(IntOp.fif(cond, t, f)); }
    /** @return t in res where cond is true, and f where cond is false. */
    protected IntE  fif(BoolE  cond, IntE  t, IntE  f) { return call(IntOp.fif(cond, t, f)); }
    /** @return t in res where cond is true, and f where cond is false. */
    protected IntEv fif(BoolEv cond, IntEv t, IntEv f) { return call(IntOp.fif(cond, t, f)); }
    /** @return t in res where cond is true, and f where cond is false. */
    protected IntEf fif(BoolEf cond, IntEf t, IntEf f) { return call(IntOp.fif(cond, t, f)); }
    /** @return t in res where cond is true, and f where cond is false. */
    protected IntF  fif(BoolF  cond, IntF  t, IntF  f) { return call(IntOp.fif(cond, t, f)); }
    /** @return t in res where cond is true, and f where cond is false. */
    protected IntFv fif(BoolFv cond, IntFv t, IntFv f) { return call(IntOp.fif(cond, t, f)); }
    /** @return t in res where cond is true, and f where cond is false. */
    protected IntFe fif(BoolFe cond, IntFe t, IntFe f) { return call(IntOp.fif(cond, t, f)); }

    /** @return a + b. */
    protected IntV  add(IntV  a, IntV  b) { return call(IntOp.add(a, b)); }
    /** @return a + b. */
    protected IntVe add(IntVe a, IntVe b) { return call(IntOp.add(a, b)); }
    /** @return a + b. */
    protected IntVf add(IntVf a, IntVf b) { return call(IntOp.add(a, b)); }
    /** @return a + b. */
    protected IntE  add(IntE  a, IntE  b) { return call(IntOp.add(a, b)); }
    /** @return a + b. */
    protected IntEv add(IntEv a, IntEv b) { return call(IntOp.add(a, b)); }
    /** @return a + b. */
    protected IntEf add(IntEf a, IntEf b) { return call(IntOp.add(a, b)); }
    /** @return a + b. */
    protected IntF  add(IntF  a, IntF  b) { return call(IntOp.add(a, b)); }
    /** @return a + b. */
    protected IntFv add(IntFv a, IntFv b) { return call(IntOp.add(a, b)); }
    /** @return a + b. */
    protected IntFe add(IntFe a, IntFe b) { return call(IntOp.add(a, b)); }

    /** @return a - b. */
    protected IntV  sub(IntV  a, IntV  b) { return call(IntOp.sub(a, b)); }
    /** @return a - b. */
    protected IntVe sub(IntVe a, IntVe b) { return call(IntOp.sub(a, b)); }
    /** @return a - b. */
    protected IntVf sub(IntVf a, IntVf b) { return call(IntOp.sub(a, b)); }
    /** @return a - b. */
    protected IntE  sub(IntE  a, IntE  b) { return call(IntOp.sub(a, b)); }
    /** @return a - b. */
    protected IntEv sub(IntEv a, IntEv b) { return call(IntOp.sub(a, b)); }
    /** @return a - b. */
    protected IntEf sub(IntEf a, IntEf b) { return call(IntOp.sub(a, b)); }
    /** @return a - b. */
    protected IntF  sub(IntF  a, IntF  b) { return call(IntOp.sub(a, b)); }
    /** @return a - b. */
    protected IntFv sub(IntFv a, IntFv b) { return call(IntOp.sub(a, b)); }
    /** @return a - b. */
    protected IntFe sub(IntFe a, IntFe b) { return call(IntOp.sub(a, b)); }

    /** @return whether a equals b. */
    protected BoolV  eq(IntV  a, IntV  b) { return call(IntOp.eq(a, b)); }
    /** @return whether a equals b. */
    protected BoolVe eq(IntVe a, IntVe b) { return call(IntOp.eq(a, b)); }
    /** @return whether a equals b. */
    protected BoolVf eq(IntVf a, IntVf b) { return call(IntOp.eq(a, b)); }
    /** @return whether a equals b. */
    protected BoolE  eq(IntE  a, IntE  b) { return call(IntOp.eq(a, b)); }
    /** @return whether a equals b. */
    protected BoolEv eq(IntEv a, IntEv b) { return call(IntOp.eq(a, b)); }
    /** @return whether a equals b. */
    protected BoolEf eq(IntEf a, IntEf b) { return call(IntOp.eq(a, b)); }
    /** @return whether a equals b. */
    protected BoolF  eq(IntF  a, IntF  b) { return call(IntOp.eq(a, b)); }
    /** @return whether a equals b. */
    protected BoolFv eq(IntFv a, IntFv b) { return call(IntOp.eq(a, b)); }
    /** @return whether a equals b. */
    protected BoolFe eq(IntFe a, IntFe b) { return call(IntOp.eq(a, b)); }

    /** @return the absolute value of a. */
    protected IntV  abs(IntV  a) { return call(IntOp.abs(a)); }
    /** @return the absolute value of a. */
    protected IntVe abs(IntVe a) { return call(IntOp.abs(a)); }
    /** @return the absolute value of a. */
    protected IntVf abs(IntVf a) { return call(IntOp.abs(a)); }
    /** @return the absolute value of a. */
    protected IntE  abs(IntE  a) { return call(IntOp.abs(a)); }
    /** @return the absolute value of a. */
    protected IntEv abs(IntEv a) { return call(IntOp.abs(a)); }
    /** @return the absolute value of a. */
    protected IntEf abs(IntEf a) { return call(IntOp.abs(a)); }
    /** @return the absolute value of a. */
    protected IntF  abs(IntF  a) { return call(IntOp.abs(a)); }
    /** @return the absolute value of a. */
    protected IntFv abs(IntFv a) { return call(IntOp.abs(a)); }
    /** @return the absolute value of a. */
    protected IntFe abs(IntFe a) { return call(IntOp.abs(a)); }

    /** @return whether a is greater than or equal to b. */
    protected BoolV  gt(IntV  a, IntV  b) { return call(IntOp.gt(a, b)); }
    /** @return whether a is greater than or equal to b. */
    protected BoolVe gt(IntVe a, IntVe b) { return call(IntOp.gt(a, b)); }
    /** @return whether a is greater than or equal to b. */
    protected BoolVf gt(IntVf a, IntVf b) { return call(IntOp.gt(a, b)); }
    /** @return whether a is greater than or equal to b. */
    protected BoolE  gt(IntE  a, IntE  b) { return call(IntOp.gt(a, b)); }
    /** @return whether a is greater than or equal to b. */
    protected BoolEv gt(IntEv a, IntEv b) { return call(IntOp.gt(a, b)); }
    /** @return whether a is greater than or equal to b. */
    protected BoolEf gt(IntEf a, IntEf b) { return call(IntOp.gt(a, b)); }
    /** @return whether a is greater than or equal to b. */
    protected BoolF  gt(IntF  a, IntF  b) { return call(IntOp.gt(a, b)); }
    /** @return whether a is greater than or equal to b. */
    protected BoolFv gt(IntFv a, IntFv b) { return call(IntOp.gt(a, b)); }
    /** @return whether a is greater than or equal to b. */
    protected BoolFe gt(IntFe a, IntFe b) { return call(IntOp.gt(a, b)); }

    /** Broadcasts a. */
    protected BoolVe broadcastVe(BoolV a) { return call(BoolOp.broadcastVe(a)); }
    /** Broadcasts a. */
    protected BoolVf broadcastVf(BoolV a) { return call(BoolOp.broadcastVf(a)); }
    /** Broadcasts a. */
    protected BoolEv broadcastEv(BoolE a) { return call(BoolOp.broadcastEv(a)); }
    /** Broadcasts a. */
    protected BoolEf broadcastEf(BoolE a) { return call(BoolOp.broadcastEf(a)); }
    /** Broadcasts a. */
    protected BoolFv broadcastFv(BoolF a) { return call(BoolOp.broadcastFv(a)); }
    /** Broadcasts a. */
    protected BoolFe broadcastFe(BoolF a) { return call(BoolOp.broadcastFe(a)); }

    /** Broadcasts a. */
    protected IntVe broadcastVe(IntV orig) { return call(IntOp.broadcastVe(orig)); }
    /** Broadcasts a. */
    protected IntVf broadcastVf(IntV orig) { return call(IntOp.broadcastVf(orig)); }
    /** Broadcasts a. */
    protected IntEv broadcastEv(IntE orig) { return call(IntOp.broadcastEv(orig)); }
    /** Broadcasts a. */
    protected IntEf broadcastEf(IntE orig) { return call(IntOp.broadcastEf(orig)); }
    /** Broadcasts a. */
    protected IntFv broadcastFv(IntF orig) { return call(IntOp.broadcastFv(orig)); }
    /** Broadcasts a. */
    protected IntFe broadcastFe(IntF orig) { return call(IntOp.broadcastFe(orig)); }

    /** Transfers a. */
    protected BoolEv transfer(BoolVe a) { return call(BoolOp.transfer(a)); }
    /** Transfers a. */
    protected BoolFv transfer(BoolVf a) { return call(BoolOp.transfer(a)); }
    /** Transfers a. */
    protected BoolVe transfer(BoolEv a) { return call(BoolOp.transfer(a)); }
    /** Transfers a. */
    protected BoolFe transfer(BoolEf a) { return call(BoolOp.transfer(a)); }
    /** Transfers a. */
    protected BoolVf transfer(BoolFv a) { return call(BoolOp.transfer(a)); }
    /** Transfers a. */
    protected BoolEf transfer(BoolFe a) { return call(BoolOp.transfer(a)); }

    /** Transfers a. */
    protected IntEv transfer(IntVe a) { return call(IntOp.transfer(a)); }
    /** Transfers a. */
    protected IntFv transfer(IntVf a) { return call(IntOp.transfer(a)); }
    /** Transfers a. */
    protected IntVe transfer(IntEv a) { return call(IntOp.transfer(a)); }
    /** Transfers a. */
    protected IntFe transfer(IntEf a) { return call(IntOp.transfer(a)); }
    /** Transfers a. */
    protected IntVf transfer(IntFv a) { return call(IntOp.transfer(a)); }
    /** Transfers a. */
    protected IntEf transfer(IntFe a) { return call(IntOp.transfer(a)); }

    /** Reduce a with AND. */
    protected BoolV redAnd(BoolVe a) { return call(BoolOp.redAnd(a)); }
    /** Reduce a with AND. */
    protected BoolV redAnd(BoolVf a) { return call(BoolOp.redAnd(a)); }
    /** Reduce a with AND. */
    protected BoolE redAnd(BoolEv a) { return call(BoolOp.redAnd(a)); }
    /** Reduce a with AND. */
    protected BoolE redAnd(BoolEf a) { return call(BoolOp.redAnd(a)); }
    /** Reduce a with AND. */
    protected BoolF redAnd(BoolFv a) { return call(BoolOp.redAnd(a)); }
    /** Reduce a with AND. */
    protected BoolF redAnd(BoolFe a) { return call(BoolOp.redAnd(a)); }

    /** Reduce a with OR. */
    protected BoolV redOr(BoolVe a) { return call(BoolOp.redOr(a)); }
    /** Reduce a with OR. */
    protected BoolV redOr(BoolVf a) { return call(BoolOp.redOr(a)); }
    /** Reduce a with OR. */
    protected BoolE redOr(BoolEv a) { return call(BoolOp.redOr(a)); }
    /** Reduce a with OR. */
    protected BoolE redOr(BoolEf a) { return call(BoolOp.redOr(a)); }
    /** Reduce a with OR. */
    protected BoolF redOr(BoolFv a) { return call(BoolOp.redOr(a)); }
    /** Reduce a with OR. */
    protected BoolF redOr(BoolFe a) { return call(BoolOp.redOr(a)); }

    /** Reduce a with XOR. */
    protected BoolV redXor(BoolVe a) { return call(BoolOp.redXor(a)); }
    /** Reduce a with XOR. */
    protected BoolV redXor(BoolVf a) { return call(BoolOp.redXor(a)); }
    /** Reduce a with XOR. */
    protected BoolE redXor(BoolEv a) { return call(BoolOp.redXor(a)); }
    /** Reduce a with XOR. */
    protected BoolE redXor(BoolEf a) { return call(BoolOp.redXor(a)); }
    /** Reduce a with XOR. */
    protected BoolF redXor(BoolFv a) { return call(BoolOp.redXor(a)); }
    /** Reduce a with XOR. */
    protected BoolF redXor(BoolFe a) { return call(BoolOp.redXor(a)); }

    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    protected void redStack0(BoolVe a, BoolV[] res) { call(BoolOp.redStack0(a, res)); }
    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    protected void redStack0(BoolVf a, BoolV[] res) { call(BoolOp.redStack0(a, res)); }
    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    protected void redStack0(BoolEv a, BoolE[] res) { call(BoolOp.redStack0(a, res)); }
    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    protected void redStack0(BoolEf a, BoolE[] res) { call(BoolOp.redStack0(a, res)); }
    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    protected void redStack0(BoolFv a, BoolF[] res) { call(BoolOp.redStack0(a, res)); }
    /** Stacks a onto res, putting FALSE in place of non-existent values. */
    protected void redStack0(BoolFe a, BoolF[] res) { call(BoolOp.redStack0(a, res)); }

    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    protected void redStack1(BoolVe a, BoolV[] res) { call(BoolOp.redStack1(a, res)); }
    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    protected void redStack1(BoolVf a, BoolV[] res) { call(BoolOp.redStack1(a, res)); }
    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    protected void redStack1(BoolEv a, BoolE[] res) { call(BoolOp.redStack1(a, res)); }
    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    protected void redStack1(BoolEf a, BoolE[] res) { call(BoolOp.redStack1(a, res)); }
    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    protected void redStack1(BoolFv a, BoolF[] res) { call(BoolOp.redStack1(a, res)); }
    /** Stacks a onto res, putting TRUE in place of non-existent values. */
    protected void redStack1(BoolFe a, BoolF[] res) { call(BoolOp.redStack1(a, res)); }

    /** Stacks a onto res, putting 0 in place of non-existent values. */
    protected void redStack0(IntVe a, IntV[] res) { call(IntOp.redStack0  (a, res)); }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    protected void redStack0(IntVf a, IntV[] res) { call(IntOp.redStack0  (a, res)); }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    protected void redStack0(IntEv a, IntE[] res) { call(IntOp.redStack0  (a, res)); }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    protected void redStack0(IntEf a, IntE[] res) { call(IntOp.redStack0  (a, res)); }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    protected void redStack0(IntFv a, IntF[] res) { call(IntOp.redStack0  (a, res)); }
    /** Stacks a onto res, putting 0 in place of non-existent values. */
    protected void redStack0(IntFe a, IntF[] res) { call(IntOp.redStack0  (a, res)); }

    /** Stacks a onto res, putting 1 in place of non-existent values. */
    protected void redStack1(IntVe a, IntV[] res) { call(IntOp.redStack1  (a, res)); }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    protected void redStack1(IntVf a, IntV[] res) { call(IntOp.redStack1  (a, res)); }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    protected void redStack1(IntEv a, IntE[] res) { call(IntOp.redStack1  (a, res)); }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    protected void redStack1(IntEf a, IntE[] res) { call(IntOp.redStack1  (a, res)); }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    protected void redStack1(IntFv a, IntF[] res) { call(IntOp.redStack1  (a, res)); }
    /** Stacks a onto res, putting 1 in place of non-existent values. */
    protected void redStack1(IntFe a, IntF[] res) { call(IntOp.redStack1  (a, res)); }

    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    protected void redStackMin(IntVe a, IntV[] res) { call(IntOp.redStackMin(a, res)); }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    protected void redStackMin(IntVf a, IntV[] res) { call(IntOp.redStackMin(a, res)); }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    protected void redStackMin(IntEv a, IntE[] res) { call(IntOp.redStackMin(a, res)); }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    protected void redStackMin(IntEf a, IntE[] res) { call(IntOp.redStackMin(a, res)); }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    protected void redStackMin(IntFv a, IntF[] res) { call(IntOp.redStackMin(a, res)); }
    /** Stacks a onto res, putting the minimum possible value in place of non-existent values. */
    protected void redStackMin(IntFe a, IntF[] res) { call(IntOp.redStackMin(a, res)); }

    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    protected void redStackMax(IntVe a, IntV[] res) { call(IntOp.redStackMax(a, res)); }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    protected void redStackMax(IntVf a, IntV[] res) { call(IntOp.redStackMax(a, res)); }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    protected void redStackMax(IntEv a, IntE[] res) { call(IntOp.redStackMax(a, res)); }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    protected void redStackMax(IntEf a, IntE[] res) { call(IntOp.redStackMax(a, res)); }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    protected void redStackMax(IntFv a, IntF[] res) { call(IntOp.redStackMax(a, res)); }
    /** Stacks a onto res, putting the maximum possible value in place of non-existent values. */
    protected void redStackMax(IntFe a, IntF[] res) { call(IntOp.redStackMax(a, res)); }

    /** Reduces a by minimum. */
    protected IntV redMin(IntVe a) { return call(IntOp.redMin(a)); }
    /** Reduces a by minimum. */
    protected IntV redMin(IntVf a) { return call(IntOp.redMin(a)); }
    /** Reduces a by minimum. */
    protected IntE redMin(IntEv a) { return call(IntOp.redMin(a)); }
    /** Reduces a by minimum. */
    protected IntE redMin(IntEf a) { return call(IntOp.redMin(a)); }
    /** Reduces a by minimum. */
    protected IntF redMin(IntFv a) { return call(IntOp.redMin(a)); }
    /** Reduces a by minimum. */
    protected IntF redMin(IntFe a) { return call(IntOp.redMin(a)); }

    /** Reduces a by maximum. */
    protected IntV redMax(IntVe a) { return call(IntOp.redMax(a)); }
    /** Reduces a by maximum. */
    protected IntV redMax(IntVf a) { return call(IntOp.redMax(a)); }
    /** Reduces a by maximum. */
    protected IntE redMax(IntEv a) { return call(IntOp.redMax(a)); }
    /** Reduces a by maximum. */
    protected IntE redMax(IntEf a) { return call(IntOp.redMax(a)); }
    /** Reduces a by maximum. */
    protected IntF redMax(IntFv a) { return call(IntOp.redMax(a)); }
    /** Reduces a by maximum. */
    protected IntF redMax(IntFe a) { return call(IntOp.redMax(a)); }

    /** Reduces a by addition into an n-bits integer. */
    protected IntV redAdd(BoolVe a, int n) { return call(IntOp.redAdd(a, n)); }
    /** Reduces a by addition into an n-bits integer. */
    protected IntV redAdd(BoolVf a, int n) { return call(IntOp.redAdd(a, n)); }
    /** Reduces a by addition into an n-bits integer. */
    protected IntE redAdd(BoolEv a, int n) { return call(IntOp.redAdd(a, n)); }
    /** Reduces a by addition into an n-bits integer. */
    protected IntE redAdd(BoolEf a, int n) { return call(IntOp.redAdd(a, n)); }
    /** Reduces a by addition into an n-bits integer. */
    protected IntF redAdd(BoolFv a, int n) { return call(IntOp.redAdd(a, n)); }
    /** Reduces a by addition into an n-bits integer. */
    protected IntF redAdd(BoolFe a, int n) { return call(IntOp.redAdd(a, n)); }

    /** Reduces a by addition. */
    protected IntV redAdd(IntVe a) { return call(IntOp.redAdd(a)); }
    /** Reduces a by addition. */
    protected IntV redAdd(IntVf a) { return call(IntOp.redAdd(a)); }
    /** Reduces a by addition. */
    protected IntE redAdd(IntEv a) { return call(IntOp.redAdd(a)); }
    /** Reduces a by addition. */
    protected IntE redAdd(IntEf a) { return call(IntOp.redAdd(a)); }
    /** Reduces a by addition. */
    protected IntF redAdd(IntFv a) { return call(IntOp.redAdd(a)); }
    /** Reduces a by addition. */
    protected IntF redAdd(IntFe a) { return call(IntOp.redAdd(a)); }

    /** Rotates a clockwise. */
    protected BoolVf rotCW(BoolVe a) { return call(BoolOp.rotCw(a)); }
    /** Rotates a clockwise. */
    protected BoolVe rotCW(BoolVf a) { return call(BoolOp.rotCw(a)); }
    /** Rotates a clockwise. */
    protected BoolEf rotCW(BoolEv a) { return call(BoolOp.rotCw(a)); }
    /** Rotates a clockwise. */
    protected BoolEv rotCW(BoolEf a) { return call(BoolOp.rotCw(a)); }
    /** Rotates a clockwise. */
    protected BoolFe rotCW(BoolFv a) { return call(BoolOp.rotCw(a)); }
    /** Rotates a clockwise. */
    protected BoolFv rotCW(BoolFe a) { return call(BoolOp.rotCw(a)); }

    /** Rotates a clockwise. */
    protected IntVf rotCW(IntVe a) { return call(IntOp.rotCw(a)); }
    /** Rotates a clockwise. */
    protected IntVe rotCW(IntVf a) { return call(IntOp.rotCw(a)); }
    /** Rotates a clockwise. */
    protected IntEf rotCW(IntEv a) { return call(IntOp.rotCw(a)); }
    /** Rotates a clockwise. */
    protected IntEv rotCW(IntEf a) { return call(IntOp.rotCw(a)); }
    /** Rotates a clockwise. */
    protected IntFe rotCW(IntFv a) { return call(IntOp.rotCw(a)); }
    /** Rotates a clockwise. */
    protected IntFv rotCW(IntFe a) { return call(IntOp.rotCw(a)); }

    /** Rotates a counterclockwise. */
    protected BoolVf rotCCW(BoolVe a) { return call(BoolOp.rotCcw(a)); }
    /** Rotates a counterclockwise. */
    protected BoolVe rotCCW(BoolVf a) { return call(BoolOp.rotCcw(a)); }
    /** Rotates a counterclockwise. */
    protected BoolEf rotCCW(BoolEv a) { return call(BoolOp.rotCcw(a)); }
    /** Rotates a counterclockwise. */
    protected BoolEv rotCCW(BoolEf a) { return call(BoolOp.rotCcw(a)); }
    /** Rotates a counterclockwise. */
    protected BoolFe rotCCW(BoolFv a) { return call(BoolOp.rotCcw(a)); }
    /** Rotates a counterclockwise. */
    protected BoolFv rotCCW(BoolFe a) { return call(BoolOp.rotCcw(a)); }

    /** Rotates a counterclockwise. */
    protected IntVf rotCCW(IntVe a) { return call(IntOp.rotCcw(a)); }
    /** Rotates a counterclockwise. */
    protected IntVe rotCCW(IntVf a) { return call(IntOp.rotCcw(a)); }
    /** Rotates a counterclockwise. */
    protected IntEf rotCCW(IntEv a) { return call(IntOp.rotCcw(a)); }
    /** Rotates a counterclockwise. */
    protected IntEv rotCCW(IntEf a) { return call(IntOp.rotCcw(a)); }
    /** Rotates a counterclockwise. */
    protected IntFe rotCCW(IntFv a) { return call(IntOp.rotCcw(a)); }
    /** Rotates a counterclockwise. */
    protected IntFv rotCCW(IntFe a) { return call(IntOp.rotCcw(a)); }

    /** Scans a from left to right. */
    protected BoolV  scanLeft(IntV  a, BoolV  res, BiFunction<BoolV,  BoolV,  Instruction<BoolV >> operation) { return call(IntOp.scanLeft(a, operation)); }
    /** Scans a from left to right. */
    protected BoolVe scanLeft(IntVe a, BoolVe res, BiFunction<BoolVe, BoolVe, Instruction<BoolVe>> operation) { return call(IntOp.scanLeft(a, operation)); }
    /** Scans a from left to right. */
    protected BoolVf scanLeft(IntVf a, BoolVf res, BiFunction<BoolVf, BoolVf, Instruction<BoolVf>> operation) { return call(IntOp.scanLeft(a, operation)); }
    /** Scans a from left to right. */
    protected BoolE  scanLeft(IntE  a, BoolE  res, BiFunction<BoolE,  BoolE,  Instruction<BoolE >> operation) { return call(IntOp.scanLeft(a, operation)); }
    /** Scans a from left to right. */
    protected BoolEv scanLeft(IntEv a, BoolEv res, BiFunction<BoolEv, BoolEv, Instruction<BoolEv>> operation) { return call(IntOp.scanLeft(a, operation));  }
    /** Scans a from left to right. */
    protected BoolEf scanLeft(IntEf a, BoolEf res, BiFunction<BoolEf, BoolEf, Instruction<BoolEf>> operation) { return call(IntOp.scanLeft(a, operation));  }
    /** Scans a from left to right. */
    protected BoolF  scanLeft(IntF  a, BoolF  res, BiFunction<BoolF,  BoolF,  Instruction<BoolF >> operation) { return call(IntOp.scanLeft(a, operation));  }
    /** Scans a from left to right. */
    protected BoolFv scanLeft(IntFv a, BoolFv res, BiFunction<BoolFv, BoolFv, Instruction<BoolFv>> operation) { return call(IntOp.scanLeft(a, operation));  }
    /** Scans a from left to right. */
    protected BoolFe scanLeft(IntFe a, BoolFe res, BiFunction<BoolFe, BoolFe, Instruction<BoolFe>> operation) { return call(IntOp.scanLeft(a, operation));  }

    /** Scans a from right to left. */
    protected BoolV  scanRight(IntV  a, BoolV  res, BiFunction<BoolV,  BoolV,  Instruction<BoolV >> operation) { return call(IntOp.scanRight(a, operation)); }
    /** Scans a from right to left. */
    protected BoolVe scanRight(IntVe a, BoolVe res, BiFunction<BoolVe, BoolVe, Instruction<BoolVe>> operation) { return call(IntOp.scanRight(a, operation)); }
    /** Scans a from right to left. */
    protected BoolVf scanRight(IntVf a, BoolVf res, BiFunction<BoolVf, BoolVf, Instruction<BoolVf>> operation) { return call(IntOp.scanRight(a, operation)); }
    /** Scans a from right to left. */
    protected BoolE  scanRight(IntE  a, BoolE  res, BiFunction<BoolE,  BoolE,  Instruction<BoolE >> operation) { return call(IntOp.scanRight(a, operation)); }
    /** Scans a from right to left. */
    protected BoolEv scanRight(IntEv a, BoolEv res, BiFunction<BoolEv, BoolEv, Instruction<BoolEv>> operation) { return call(IntOp.scanRight(a, operation));  }
    /** Scans a from right to left. */
    protected BoolEf scanRight(IntEf a, BoolEf res, BiFunction<BoolEf, BoolEf, Instruction<BoolEf>> operation) { return call(IntOp.scanRight(a, operation));  }
    /** Scans a from right to left. */
    protected BoolF  scanRight(IntF  a, BoolF  res, BiFunction<BoolF,  BoolF,  Instruction<BoolF >> operation) { return call(IntOp.scanRight(a, operation));  }
    /** Scans a from right to left. */
    protected BoolFv scanRight(IntFv a, BoolFv res, BiFunction<BoolFv, BoolFv, Instruction<BoolFv>> operation) { return call(IntOp.scanRight(a, operation));  }
    /** Scans a from right to left. */
    protected BoolFe scanRight(IntFe a, BoolFe res, BiFunction<BoolFe, BoolFe, Instruction<BoolFe>> operation) { return call(IntOp.scanRight(a, operation));  }
}
