package language.instruction;

import language.cache.Cache;
import language.field.Field;
import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.instructionSet.*;
import ui.display.Styles;
import utils.TriFunction;

import java.util.ArrayList;
import java.util.HashSet;

/** Represents an instruction composed of a sequence of sub-instructions. */
@SuppressWarnings("unused")
public abstract non-sealed class Procedure implements Instruction {
    /** The index of the current instruction being executed. */
    private int instrPtr = 0;
    /** The list of instructions that make up this procedure. */
    private final ArrayList<Instruction> instr;

    /** Returns the current instruction pointer. */
    public int getInstrPtr() { return instrPtr; }
    /** Sets the current instruction pointer. Used to restore an execution state. */
    public void setInstrPtr(int instrPtr) { this.instrPtr = instrPtr; }

    /** Creates a new Procedure and registers it in the cache. */
    public Procedure() {
        instr = new ArrayList<>();
        Cache.register(this);
    }

    /** Adds the given instruction to this procedure. */
    protected void call(Instruction i) { instr.add(i); }

    @Override
    public int leafCount() {
        int count = 0;
        for (Instruction i : instr) count += i.leafCount();
        return count;
    }

    /** Returns a string representation of the instruction tree of this procedure. */
    public String instructionTree() { return instructionTree(0);}
    public String instructionTree(int depth) {
        StringBuilder sb = new StringBuilder();
        sb.repeat("|  ", depth).append("--- ").append(this.getClass().getSimpleName()).append("\n");
        for (Instruction i : instr) {
            if (i instanceof Procedure p) sb.append(p.instructionTree(depth + 1));
            else sb.repeat("|  ", depth + 1).append("--- ").append(i.getClass().getSimpleName()).append("\n");
        }
        return sb.toString();
    }

    /** Returns the current instruction being executed. */
    public BasicInstruction currentBasicInstruction() {
        return switch (instr.get(instrPtr)) {
            case BasicInstruction i -> i;
            case Procedure p -> p.currentBasicInstruction();
        };
    }

    /**
     * Creates a temporary variable that can be used within this procedure.
     * The variable will be automatically cleaned up (set to null) when this procedure finishes executing.
     * This avoids expensive long term storage of fields that are no longer useful
     */
    public <F extends Field<?>> F tmp(F field) { tmpVars.add(field); return field; }
    /** The set of tmp variables of this procedure. */
    private final HashSet<Field<?>> tmpVars = new HashSet<>();
    /** Cleans up the temporary variables of this procedure. */
    private void cleanup() { for (Field<?> field : tmpVars) field.clear(); }

    /** @return false if there are more instructions to execute, true if the loop is finished. */
    @Override
    public final boolean exec() {
        if (instr.isEmpty()) return exit();

        try {
            boolean done = instr.get(instrPtr).exec();
            if (done) instrPtr++; // If the current instruction is done, we move to the next
        } catch (Exception e) {
            System.err.println("Error executing instruction " + instr.get(instrPtr).getClass().getSimpleName() + " at index " + instrPtr);
            throw e;
        }
        // There are no more instruction to perform.
        if (instrPtr == instr.size()) return exit();

        // There are more instructions to perform.
        return false;
    }

    /** Prepares this procedure to be executed again. This is called when the procedure is finished executing. */
    private boolean exit() {
        instrPtr = 0;
        cleanup();
        return true;
    }

    // Wrapper functions to make writing procedures easier. These functions simply add the corresponding instruction to this procedure.

    /** Executes an arbitrary Java function. */
    protected void run(Runnable r) { call(new ArbitrarySysInstr(r)); }

    /** Indicates that the given field should be displayed in the UI. */
    protected <F extends Field<F>> void show(String name, F field) { call(new Show<>(name, field)); }
    /** Indicates that the given field should be displayed in the UI with the given style. */
    protected <F extends Field<F>> void show(String name, F field, Styles.Style style) { call(new Show<>(name, field, style)); }
    /** Indicates that the UI should take a snapshot of the current state of the fields. */
    protected void snapshot() { call(new Snapshot()); }

    /** Sets the value of the field in to the value of the field out. */
    protected <F extends Field<F>> void set(F in, F out) { call(new SetField<>(in, out)); }

    /** Stores NOT a in res. */
    protected void not(BoolV  a, BoolV  res) { call(BoolOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(BoolVe a, BoolVe res) { call(BoolOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(BoolVf a, BoolVf res) { call(BoolOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(BoolE  a, BoolE  res) { call(BoolOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(BoolEv a, BoolEv res) { call(BoolOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(BoolEf a, BoolEf res) { call(BoolOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(BoolF  a, BoolF  res) { call(BoolOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(BoolFv a, BoolFv res) { call(BoolOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(BoolFe a, BoolFe res) { call(BoolOp.not(a, res)); }

    /** Stores NOT a in res. */
    protected void not(IntV  a, IntV  res) { call(IntOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(IntVe a, IntVe res) { call(IntOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(IntVf a, IntVf res) { call(IntOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(IntE  a, IntE  res) { call(IntOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(IntEv a, IntEv res) { call(IntOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(IntEf a, IntEf res) { call(IntOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(IntF  a, IntF  res) { call(IntOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(IntFv a, IntFv res) { call(IntOp.not(a, res)); }
    /** Stores NOT a in res. */
    protected void not(IntFe a, IntFe res) { call(IntOp.not(a, res)); }

    /** Stores -a in res. */
    protected void neg(IntV  a, IntV  res) { call(IntOp.neg(a, res)); }
    /** Stores -a in res. */
    protected void neg(IntVe a, IntVe res) { call(IntOp.neg(a, res)); }
    /** Stores -a in res. */
    protected void neg(IntVf a, IntVf res) { call(IntOp.neg(a, res)); }
    /** Stores -a in res. */
    protected void neg(IntE  a, IntE  res) { call(IntOp.neg(a, res)); }
    /** Stores -a in res. */
    protected void neg(IntEv a, IntEv res) { call(IntOp.neg(a, res)); }
    /** Stores -a in res. */
    protected void neg(IntEf a, IntEf res) { call(IntOp.neg(a, res)); }
    /** Stores -a in res. */
    protected void neg(IntF  a, IntF  res) { call(IntOp.neg(a, res)); }
    /** Stores -a in res. */
    protected void neg(IntFv a, IntFv res) { call(IntOp.neg(a, res)); }
    /** Stores -a in res. */
    protected void neg(IntFe a, IntFe res) { call(IntOp.neg(a, res)); }

    /** Stores a shifted left by k bits in res. */
    protected void lShift(IntV  a, IntV  res, int k) { call(IntOp.lShift(a, res, k)); }
    /** Stores a shifted left by k bits in res. */
    protected void lShift(IntVe a, IntVe res, int k) { call(IntOp.lShift(a, res, k)); }
    /** Stores a shifted left by k bits in res. */
    protected void lShift(IntVf a, IntVf res, int k) { call(IntOp.lShift(a, res, k)); }
    /** Stores a shifted left by k bits in res. */
    protected void lShift(IntE  a, IntE  res, int k) { call(IntOp.lShift(a, res, k)); }
    /** Stores a shifted left by k bits in res. */
    protected void lShift(IntEv a, IntEv res, int k) { call(IntOp.lShift(a, res, k)); }
    /** Stores a shifted left by k bits in res. */
    protected void lShift(IntEf a, IntEf res, int k) { call(IntOp.lShift(a, res, k)); }
    /** Stores a shifted left by k bits in res. */
    protected void lShift(IntF  a, IntF  res, int k) { call(IntOp.lShift(a, res, k)); }
    /** Stores a shifted left by k bits in res. */
    protected void lShift(IntFv a, IntFv res, int k) { call(IntOp.lShift(a, res, k)); }
    /** Stores a shifted left by k bits in res. */
    protected void lShift(IntFe a, IntFe res, int k) { call(IntOp.lShift(a, res, k)); }

    /** Stores a shifted right by k bits in res. */
    protected void rShift(IntV  a, IntV  res, int k) { call(IntOp.rShift(a, res, k)); }
    /** Stores a shifted right by k bits in res. */
    protected void rShift(IntVe a, IntVe res, int k) { call(IntOp.rShift(a, res, k)); }
    /** Stores a shifted right by k bits in res. */
    protected void rShift(IntVf a, IntVf res, int k) { call(IntOp.rShift(a, res, k)); }
    /** Stores a shifted right by k bits in res. */
    protected void rShift(IntE  a, IntE  res, int k) { call(IntOp.rShift(a, res, k)); }
    /** Stores a shifted right by k bits in res. */
    protected void rShift(IntEv a, IntEv res, int k) { call(IntOp.rShift(a, res, k)); }
    /** Stores a shifted right by k bits in res. */
    protected void rShift(IntEf a, IntEf res, int k) { call(IntOp.rShift(a, res, k)); }
    /** Stores a shifted right by k bits in res. */
    protected void rShift(IntF  a, IntF  res, int k) { call(IntOp.rShift(a, res, k)); }
    /** Stores a shifted right by k bits in res. */
    protected void rShift(IntFv a, IntFv res, int k) { call(IntOp.rShift(a, res, k)); }
    /** Stores a shifted right by k bits in res. */
    protected void rShift(IntFe a, IntFe res, int k) { call(IntOp.rShift(a, res, k)); }

    /** Converts boolean a to integer res. */
    protected void fromBool(BoolV  a, IntV  res) { call(IntOp.fromBool(a, res)); }
    /** Converts boolean a to integer res. */
    protected void fromBool(BoolVe a, IntVe res) { call(IntOp.fromBool(a, res)); }
    /** Converts boolean a to integer res. */
    protected void fromBool(BoolVf a, IntVf res) { call(IntOp.fromBool(a, res)); }
    /** Converts boolean a to integer res. */
    protected void fromBool(BoolE  a, IntE  res) { call(IntOp.fromBool(a, res)); }
    /** Converts boolean a to integer res. */
    protected void fromBool(BoolEv a, IntEv res) { call(IntOp.fromBool(a, res)); }
    /** Converts boolean a to integer res. */
    protected void fromBool(BoolEf a, IntEf res) { call(IntOp.fromBool(a, res)); }
    /** Converts boolean a to integer res. */
    protected void fromBool(BoolF  a, IntF  res) { call(IntOp.fromBool(a, res)); }
    /** Converts boolean a to integer res. */
    protected void fromBool(BoolFv a, IntFv res) { call(IntOp.fromBool(a, res)); }
    /** Converts boolean a to integer res. */
    protected void fromBool(BoolFe a, IntFe res) { call(IntOp.fromBool(a, res)); }

    /** Stores a AND b in res. */
    protected void and(BoolV  a, BoolV  b, BoolV  res) { call(BoolOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(BoolVe a, BoolVe b, BoolVe res) { call(BoolOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(BoolVf a, BoolVf b, BoolVf res) { call(BoolOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(BoolE  a, BoolE  b, BoolE  res) { call(BoolOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(BoolEv a, BoolEv b, BoolEv res) { call(BoolOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(BoolEf a, BoolEf b, BoolEf res) { call(BoolOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(BoolF  a, BoolF  b, BoolF  res) { call(BoolOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(BoolFv a, BoolFv b, BoolFv res) { call(BoolOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(BoolFe a, BoolFe b, BoolFe res) { call(BoolOp.and(a, b, res)); }

    /** Stores a AND b in res. */
    protected void and(IntV  a, IntV  b, IntV  res) { call(IntOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(IntVe a, IntVe b, IntVe res) { call(IntOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(IntVf a, IntVf b, IntVf res) { call(IntOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(IntE  a, IntE  b, IntE  res) { call(IntOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(IntEv a, IntEv b, IntEv res) { call(IntOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(IntEf a, IntEf b, IntEf res) { call(IntOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(IntF  a, IntF  b, IntF  res) { call(IntOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(IntFv a, IntFv b, IntFv res) { call(IntOp.and(a, b, res)); }
    /** Stores a AND b in res. */
    protected void and(IntFe a, IntFe b, IntFe res) { call(IntOp.and(a, b, res)); }

    /** Stores a OR b in res. */
    protected void or(BoolV  a, BoolV  b, BoolV  res) { call(BoolOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(BoolVe a, BoolVe b, BoolVe res) { call(BoolOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(BoolVf a, BoolVf b, BoolVf res) { call(BoolOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(BoolE  a, BoolE  b, BoolE  res) { call(BoolOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(BoolEv a, BoolEv b, BoolEv res) { call(BoolOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(BoolEf a, BoolEf b, BoolEf res) { call(BoolOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(BoolF  a, BoolF  b, BoolF  res) { call(BoolOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(BoolFv a, BoolFv b, BoolFv res) { call(BoolOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(BoolFe a, BoolFe b, BoolFe res) { call(BoolOp.or(a, b, res)); }

    /** Stores a OR b in res. */
    protected void or(IntV  a, IntV  b, IntV  res) { call(IntOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(IntVe a, IntVe b, IntVe res) { call(IntOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(IntVf a, IntVf b, IntVf res) { call(IntOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(IntE  a, IntE  b, IntE  res) { call(IntOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(IntEv a, IntEv b, IntEv res) { call(IntOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(IntEf a, IntEf b, IntEf res) { call(IntOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(IntF  a, IntF  b, IntF  res) { call(IntOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(IntFv a, IntFv b, IntFv res) { call(IntOp.or(a, b, res)); }
    /** Stores a OR b in res. */
    protected void or(IntFe a, IntFe b, IntFe res) { call(IntOp.or(a, b, res)); }

    /** Stores a XOR b in res. */
    protected void xor(BoolV  a, BoolV  b, BoolV  res) { call(BoolOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(BoolVe a, BoolVe b, BoolVe res) { call(BoolOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(BoolVf a, BoolVf b, BoolVf res) { call(BoolOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(BoolE  a, BoolE  b, BoolE  res) { call(BoolOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(BoolEv a, BoolEv b, BoolEv res) { call(BoolOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(BoolEf a, BoolEf b, BoolEf res) { call(BoolOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(BoolF  a, BoolF  b, BoolF  res) { call(BoolOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(BoolFv a, BoolFv b, BoolFv res) { call(BoolOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(BoolFe a, BoolFe b, BoolFe res) { call(BoolOp.xor(a, b, res)); }

    /** Stores a XOR b in res. */
    protected void xor(IntV  a, IntV  b, IntV  res) { call(IntOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(IntVe a, IntVe b, IntVe res) { call(IntOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(IntVf a, IntVf b, IntVf res) { call(IntOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(IntE  a, IntE  b, IntE  res) { call(IntOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(IntEv a, IntEv b, IntEv res) { call(IntOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(IntEf a, IntEf b, IntEf res) { call(IntOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(IntF  a, IntF  b, IntF  res) { call(IntOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(IntFv a, IntFv b, IntFv res) { call(IntOp.xor(a, b, res)); }
    /** Stores a XOR b in res. */
    protected void xor(IntFe a, IntFe b, IntFe res) { call(IntOp.xor(a, b, res)); }

    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolV  cond, BoolV  t, BoolV  f, BoolV  res) { call(BoolOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolVe cond, BoolVe t, BoolVe f, BoolVe res) { call(BoolOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolVf cond, BoolVf t, BoolVf f, BoolVf res) { call(BoolOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolE  cond, BoolE  t, BoolE  f, BoolE  res) { call(BoolOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolEv cond, BoolEv t, BoolEv f, BoolEv res) { call(BoolOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolEf cond, BoolEf t, BoolEf f, BoolEf res) { call(BoolOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolF  cond, BoolF  t, BoolF  f, BoolF  res) { call(BoolOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolFv cond, BoolFv t, BoolFv f, BoolFv res) { call(BoolOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolFe cond, BoolFe t, BoolFe f, BoolFe res) { call(BoolOp.fif(cond, t, f, res)); }

    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolV  cond, IntV  t, IntV  f, IntV  res) { call(IntOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolVe cond, IntVe t, IntVe f, IntVe res) { call(IntOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolVf cond, IntVf t, IntVf f, IntVf res) { call(IntOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolE  cond, IntE  t, IntE  f, IntE  res) { call(IntOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolEv cond, IntEv t, IntEv f, IntEv res) { call(IntOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolEf cond, IntEf t, IntEf f, IntEf res) { call(IntOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolF  cond, IntF  t, IntF  f, IntF  res) { call(IntOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolFv cond, IntFv t, IntFv f, IntFv res) { call(IntOp.fif(cond, t, f, res)); }
    /** Stores t in res where cond is true, and f where cond is false. */
    protected void fif(BoolFe cond, IntFe t, IntFe f, IntFe res) { call(IntOp.fif(cond, t, f, res)); }

    /** Stores a + b in res. */
    protected void add(IntV  a, IntV  b, IntV  res) { call(IntOp.add(a, b, res)); }
    /** Stores a + b in res. */
    protected void add(IntVe a, IntVe b, IntVe res) { call(IntOp.add(a, b, res)); }
    /** Stores a + b in res. */
    protected void add(IntVf a, IntVf b, IntVf res) { call(IntOp.add(a, b, res)); }
    /** Stores a + b in res. */
    protected void add(IntE  a, IntE  b, IntE  res) { call(IntOp.add(a, b, res)); }
    /** Stores a + b in res. */
    protected void add(IntEv a, IntEv b, IntEv res) { call(IntOp.add(a, b, res)); }
    /** Stores a + b in res. */
    protected void add(IntEf a, IntEf b, IntEf res) { call(IntOp.add(a, b, res)); }
    /** Stores a + b in res. */
    protected void add(IntF  a, IntF  b, IntF  res) { call(IntOp.add(a, b, res)); }
    /** Stores a + b in res. */
    protected void add(IntFv a, IntFv b, IntFv res) { call(IntOp.add(a, b, res)); }
    /** Stores a + b in res. */
    protected void add(IntFe a, IntFe b, IntFe res) { call(IntOp.add(a, b, res)); }

    /** Stores a - b in res. */
    protected void sub(IntV  a, IntV  b, IntV  res) { call(IntOp.sub(a, b, res)); }
    /** Stores a - b in res. */
    protected void sub(IntVe a, IntVe b, IntVe res) { call(IntOp.sub(a, b, res)); }
    /** Stores a - b in res. */
    protected void sub(IntVf a, IntVf b, IntVf res) { call(IntOp.sub(a, b, res)); }
    /** Stores a - b in res. */
    protected void sub(IntE  a, IntE  b, IntE  res) { call(IntOp.sub(a, b, res)); }
    /** Stores a - b in res. */
    protected void sub(IntEv a, IntEv b, IntEv res) { call(IntOp.sub(a, b, res)); }
    /** Stores a - b in res. */
    protected void sub(IntEf a, IntEf b, IntEf res) { call(IntOp.sub(a, b, res)); }
    /** Stores a - b in res. */
    protected void sub(IntF  a, IntF  b, IntF  res) { call(IntOp.sub(a, b, res)); }
    /** Stores a - b in res. */
    protected void sub(IntFv a, IntFv b, IntFv res) { call(IntOp.sub(a, b, res)); }
    /** Stores a - b in res. */
    protected void sub(IntFe a, IntFe b, IntFe res) { call(IntOp.sub(a, b, res)); }

    /** Stores whether a equals b in res. */
    protected void eq(IntV  a, IntV  b, BoolV  res) { call(IntOp.eq(a, b, res)); }
    /** Stores whether a equals b in res. */
    protected void eq(IntVe a, IntVe b, BoolVe res) { call(IntOp.eq(a, b, res)); }
    /** Stores whether a equals b in res. */
    protected void eq(IntVf a, IntVf b, BoolVf res) { call(IntOp.eq(a, b, res)); }
    /** Stores whether a equals b in res. */
    protected void eq(IntE  a, IntE  b, BoolE  res) { call(IntOp.eq(a, b, res)); }
    /** Stores whether a equals b in res. */
    protected void eq(IntEv a, IntEv b, BoolEv res) { call(IntOp.eq(a, b, res)); }
    /** Stores whether a equals b in res. */
    protected void eq(IntEf a, IntEf b, BoolEf res) { call(IntOp.eq(a, b, res)); }
    /** Stores whether a equals b in res. */
    protected void eq(IntF  a, IntF  b, BoolF  res) { call(IntOp.eq(a, b, res)); }
    /** Stores whether a equals b in res. */
    protected void eq(IntFv a, IntFv b, BoolFv res) { call(IntOp.eq(a, b, res)); }
    /** Stores whether a equals b in res. */
    protected void eq(IntFe a, IntFe b, BoolFe res) { call(IntOp.eq(a, b, res)); }

    /** Stores the absolute value of a in res. */
    protected void abs(IntV  a, IntV  res) { call(IntOp.abs(a, res)); }
    /** Stores the absolute value of a in res. */
    protected void abs(IntVe a, IntVe res) { call(IntOp.abs(a, res)); }
    /** Stores the absolute value of a in res. */
    protected void abs(IntVf a, IntVf res) { call(IntOp.abs(a, res)); }
    /** Stores the absolute value of a in res. */
    protected void abs(IntE  a, IntE  res) { call(IntOp.abs(a, res)); }
    /** Stores the absolute value of a in res. */
    protected void abs(IntEv a, IntEv res) { call(IntOp.abs(a, res)); }
    /** Stores the absolute value of a in res. */
    protected void abs(IntEf a, IntEf res) { call(IntOp.abs(a, res)); }
    /** Stores the absolute value of a in res. */
    protected void abs(IntF  a, IntF  res) { call(IntOp.abs(a, res)); }
    /** Stores the absolute value of a in res. */
    protected void abs(IntFv a, IntFv res) { call(IntOp.abs(a, res)); }
    /** Stores the absolute value of a in res. */
    protected void abs(IntFe a, IntFe res) { call(IntOp.abs(a, res)); }

    /** Stores whether a is greater than or equal to b in res. */
    protected void gt(IntV  a, IntV  b, BoolV  res) { call(IntOp.gt(a, b, res)); }
    /** Stores whether a is greater than or equal to b in res. */
    protected void gt(IntVe a, IntVe b, BoolVe res) { call(IntOp.gt(a, b, res)); }
    /** Stores whether a is greater than or equal to b in res. */
    protected void gt(IntVf a, IntVf b, BoolVf res) { call(IntOp.gt(a, b, res)); }
    /** Stores whether a is greater than or equal to b in res. */
    protected void gt(IntE  a, IntE  b, BoolE  res) { call(IntOp.gt(a, b, res)); }
    /** Stores whether a is greater than or equal to b in res. */
    protected void gt(IntEv a, IntEv b, BoolEv res) { call(IntOp.gt(a, b, res)); }
    /** Stores whether a is greater than or equal to b in res. */
    protected void gt(IntEf a, IntEf b, BoolEf res) { call(IntOp.gt(a, b, res)); }
    /** Stores whether a is greater than or equal to b in res. */
    protected void gt(IntF  a, IntF  b, BoolF  res) { call(IntOp.gt(a, b, res)); }
    /** Stores whether a is greater than or equal to b in res. */
    protected void gt(IntFv a, IntFv b, BoolFv res) { call(IntOp.gt(a, b, res)); }
    /** Stores whether a is greater than or equal to b in res. */
    protected void gt(IntFe a, IntFe b, BoolFe res) { call(IntOp.gt(a, b, res)); }

    /** Broadcast a to res. */
    protected void broadcast(IntV orig, IntVe res) { call(IntOp.broadcast(orig, res)); }
    /** Broadcast a to res. */
    protected void broadcast(IntV orig, IntVf res) { call(IntOp.broadcast(orig, res)); }
    /** Broadcast a to res. */
    protected void broadcast(IntE orig, IntEv res) { call(IntOp.broadcast(orig, res)); }
    /** Broadcast a to res. */
    protected void broadcast(IntE orig, IntEf res) { call(IntOp.broadcast(orig, res)); }
    /** Broadcast a to res. */
    protected void broadcast(IntF orig, IntFv res) { call(IntOp.broadcast(orig, res)); }
    /** Broadcast a to res. */
    protected void broadcast(IntF orig, IntFe res) { call(IntOp.broadcast(orig, res)); }

    /** Broadcast a to res. */
    protected void broadcast(BoolV a, BoolVe res) { call(BoolOp.broadcast(a, res)); }
    /** Broadcast a to res. */
    protected void broadcast(BoolV a, BoolVf res) { call(BoolOp.broadcast(a, res)); }
    /** Broadcast a to res. */
    protected void broadcast(BoolE a, BoolEv res) { call(BoolOp.broadcast(a, res)); }
    /** Broadcast a to res. */
    protected void broadcast(BoolE a, BoolEf res) { call(BoolOp.broadcast(a, res)); }
    /** Broadcast a to res. */
    protected void broadcast(BoolF a, BoolFv res) { call(BoolOp.broadcast(a, res)); }
    /** Broadcast a to res. */
    protected void broadcast(BoolF a, BoolFe res) { call(BoolOp.broadcast(a, res)); }

    /** Transfer a to res. */
    protected void transfer(BoolVe a, BoolEv res) { call(BoolOp.transfer(a, res)); }
    /** Transfer a to res. */
    protected void transfer(BoolVf a, BoolFv res) { call(BoolOp.transfer(a, res)); }
    /** Transfer a to res. */
    protected void transfer(BoolEv a, BoolVe res) { call(BoolOp.transfer(a, res)); }
    /** Transfer a to res. */
    protected void transfer(BoolEf a, BoolFe res) { call(BoolOp.transfer(a, res)); }
    /** Transfer a to res. */
    protected void transfer(BoolFv a, BoolVf res) { call(BoolOp.transfer(a, res)); }
    /** Transfer a to res. */
    protected void transfer(BoolFe a, BoolEf res) { call(BoolOp.transfer(a, res)); }

    /** Transfer a to res. */
    protected void transfer(IntVe a, IntEv res) { call(IntOp.transfer(a, res)); }
    /** Transfer a to res. */
    protected void transfer(IntVf a, IntFv res) { call(IntOp.transfer(a, res)); }
    /** Transfer a to res. */
    protected void transfer(IntEv a, IntVe res) { call(IntOp.transfer(a, res)); }
    /** Transfer a to res. */
    protected void transfer(IntEf a, IntFe res) { call(IntOp.transfer(a, res)); }
    /** Transfer a to res. */
    protected void transfer(IntFv a, IntVf res) { call(IntOp.transfer(a, res)); }
    /** Transfer a to res. */
    protected void transfer(IntFe a, IntEf res) { call(IntOp.transfer(a, res)); }

    /** Reduce a with AND to res. */
    protected void redAnd(BoolVe a, BoolV res) { call(BoolOp.redAnd(a, res)); }
    /** Reduce a with AND to res. */
    protected void redAnd(BoolVf a, BoolV res) { call(BoolOp.redAnd(a, res)); }
    /** Reduce a with AND to res. */
    protected void redAnd(BoolEv a, BoolE res) { call(BoolOp.redAnd(a, res)); }
    /** Reduce a with AND to res. */
    protected void redAnd(BoolEf a, BoolE res) { call(BoolOp.redAnd(a, res)); }
    /** Reduce a with AND to res. */
    protected void redAnd(BoolFv a, BoolF res) { call(BoolOp.redAnd(a, res)); }
    /** Reduce a with AND to res. */
    protected void redAnd(BoolFe a, BoolF res) { call(BoolOp.redAnd(a, res)); }

    /** Reduce a with OR to res. */
    protected void redOr(BoolVe a, BoolV res) { call(BoolOp.redOr(a, res)); }
    /** Reduce a with OR to res. */
    protected void redOr(BoolVf a, BoolV res) { call(BoolOp.redOr(a, res)); }
    /** Reduce a with OR to res. */
    protected void redOr(BoolEv a, BoolE res) { call(BoolOp.redOr(a, res)); }
    /** Reduce a with OR to res. */
    protected void redOr(BoolEf a, BoolE res) { call(BoolOp.redOr(a, res)); }
    /** Reduce a with OR to res. */
    protected void redOr(BoolFv a, BoolF res) { call(BoolOp.redOr(a, res)); }
    /** Reduce a with OR to res. */
    protected void redOr(BoolFe a, BoolF res) { call(BoolOp.redOr(a, res)); }

    /** Reduce a with XOR to res. */
    protected void redXor(BoolVe a, BoolV res) { call(BoolOp.redXor(a, res)); }
    /** Reduce a with XOR to res. */
    protected void redXor(BoolVf a, BoolV res) { call(BoolOp.redXor(a, res)); }
    /** Reduce a with XOR to res. */
    protected void redXor(BoolEv a, BoolE res) { call(BoolOp.redXor(a, res)); }
    /** Reduce a with XOR to res. */
    protected void redXor(BoolEf a, BoolE res) { call(BoolOp.redXor(a, res)); }
    /** Reduce a with XOR to res. */
    protected void redXor(BoolFv a, BoolF res) { call(BoolOp.redXor(a, res)); }
    /** Reduce a with XOR to res. */
    protected void redXor(BoolFe a, BoolF res) { call(BoolOp.redXor(a, res)); }

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

    /** Reduces a by minimum into res. */
    protected void redMin(IntVe a, IntV res) { call(IntOp.redMin(a, res)); }
    /** Reduces a by minimum into res. */
    protected void redMin(IntVf a, IntV res) { call(IntOp.redMin(a, res)); }
    /** Reduces a by minimum into res. */
    protected void redMin(IntEv a, IntE res) { call(IntOp.redMin(a, res)); }
    /** Reduces a by minimum into res. */
    protected void redMin(IntEf a, IntE res) { call(IntOp.redMin(a, res)); }
    /** Reduces a by minimum into res. */
    protected void redMin(IntFv a, IntF res) { call(IntOp.redMin(a, res)); }
    /** Reduces a by minimum into res. */
    protected void redMin(IntFe a, IntF res) { call(IntOp.redMin(a, res)); }

    /** Reduces a by maximum into res. */
    protected void redMax(IntVe a, IntV res) { call(IntOp.redMax(a, res)); }
    /** Reduces a by maximum into res. */
    protected void redMax(IntVf a, IntV res) { call(IntOp.redMax(a, res)); }
    /** Reduces a by maximum into res. */
    protected void redMax(IntEv a, IntE res) { call(IntOp.redMax(a, res)); }
    /** Reduces a by maximum into res. */
    protected void redMax(IntEf a, IntE res) { call(IntOp.redMax(a, res)); }
    /** Reduces a by maximum into res. */
    protected void redMax(IntFv a, IntF res) { call(IntOp.redMax(a, res)); }
    /** Reduces a by maximum into res. */
    protected void redMax(IntFe a, IntF res) { call(IntOp.redMax(a, res)); }

    /** Reduces a by addition into res. */
    protected void redAdd(BoolVe a, IntV res) { call(IntOp.redAdd(a, res)); }
    /** Reduces a by addition into res. */
    protected void redAdd(BoolVf a, IntV res) { call(IntOp.redAdd(a, res)); }
    /** Reduces a by addition into res. */
    protected void redAdd(BoolEv a, IntE res) { call(IntOp.redAdd(a, res)); }
    /** Reduces a by addition into res. */
    protected void redAdd(BoolEf a, IntE res) { call(IntOp.redAdd(a, res)); }
    /** Reduces a by addition into res. */
    protected void redAdd(BoolFv a, IntF res) { call(IntOp.redAdd(a, res)); }
    /** Reduces a by addition into res. */
    protected void redAdd(BoolFe a, IntF res) { call(IntOp.redAdd(a, res)); }

    /** Reduces a by addition into res. */
    protected void redAdd(IntVe a, IntV res) { call(IntOp.redAdd(a, res)); }
    /** Reduces a by addition into res. */
    protected void redAdd(IntVf a, IntV res) { call(IntOp.redAdd(a, res)); }
    /** Reduces a by addition into res. */
    protected void redAdd(IntEv a, IntE res) { call(IntOp.redAdd(a, res)); }
    /** Reduces a by addition into res. */
    protected void redAdd(IntEf a, IntE res) { call(IntOp.redAdd(a, res)); }
    /** Reduces a by addition into res. */
    protected void redAdd(IntFv a, IntF res) { call(IntOp.redAdd(a, res)); }
    /** Reduces a by addition into res. */
    protected void redAdd(IntFe a, IntF res) { call(IntOp.redAdd(a, res)); }

    /** Rotate a clockwise to res. */
    protected void rotCW(BoolVe a, BoolVf res) { call(BoolOp.rotCw(a, res)); }
    /** Rotate a clockwise to res. */
    protected void rotCW(BoolVf a, BoolVe res) { call(BoolOp.rotCw(a, res)); }
    /** Rotate a clockwise to res. */
    protected void rotCW(BoolEv a, BoolEf res) { call(BoolOp.rotCw(a, res)); }
    /** Rotate a clockwise to res. */
    protected void rotCW(BoolEf a, BoolEv res) { call(BoolOp.rotCw(a, res)); }
    /** Rotate a clockwise to res. */
    protected void rotCW(BoolFv a, BoolFe res) { call(BoolOp.rotCw(a, res)); }
    /** Rotate a clockwise to res. */
    protected void rotCW(BoolFe a, BoolFv res) { call(BoolOp.rotCw(a, res)); }

    /** Rotate a clockwise to res. */
    protected void rotCW(IntVe a, IntVf res) { call(IntOp.rotCw(a, res)); }
    /** Rotate a clockwise to res. */
    protected void rotCW(IntVf a, IntVe res) { call(IntOp.rotCw(a, res)); }
    /** Rotate a clockwise to res. */
    protected void rotCW(IntEv a, IntEf res) { call(IntOp.rotCw(a, res)); }
    /** Rotate a clockwise to res. */
    protected void rotCW(IntEf a, IntEv res) { call(IntOp.rotCw(a, res)); }
    /** Rotate a clockwise to res. */
    protected void rotCW(IntFv a, IntFe res) { call(IntOp.rotCw(a, res)); }
    /** Rotate a clockwise to res. */
    protected void rotCW(IntFe a, IntFv res) { call(IntOp.rotCw(a, res)); }

    /** Rotate a counterclockwise to res. */
    protected void rotCCW(BoolVe a, BoolVf res) { call(BoolOp.rotCcw(a, res)); }
    /** Rotate a counterclockwise to res. */
    protected void rotCCW(BoolVf a, BoolVe res) { call(BoolOp.rotCcw(a, res)); }
    /** Rotate a counterclockwise to res. */
    protected void rotCCW(BoolEv a, BoolEf res) { call(BoolOp.rotCcw(a, res)); }
    /** Rotate a counterclockwise to res. */
    protected void rotCCW(BoolEf a, BoolEv res) { call(BoolOp.rotCcw(a, res)); }
    /** Rotate a counterclockwise to res. */
    protected void rotCCW(BoolFv a, BoolFe res) { call(BoolOp.rotCcw(a, res)); }
    /** Rotate a counterclockwise to res. */
    protected void rotCCW(BoolFe a, BoolFv res) { call(BoolOp.rotCcw(a, res)); }

    /** Rotate a counterclockwise to res. */
    protected void rotCCW(IntVe a, IntVf res) { call(IntOp.rotCcw(a, res)); }
    /** Rotate a counterclockwise to res. */
    protected void rotCCW(IntVf a, IntVe res) { call(IntOp.rotCcw(a, res)); }
    /** Rotate a counterclockwise to res. */
    protected void rotCCW(IntEv a, IntEf res) { call(IntOp.rotCcw(a, res)); }
    /** Rotate a counterclockwise to res. */
    protected void rotCCW(IntEf a, IntEv res) { call(IntOp.rotCcw(a, res)); }
    /** Rotate a counterclockwise to res. */
    protected void rotCCW(IntFv a, IntFe res) { call(IntOp.rotCcw(a, res)); }
    /** Rotate a counterclockwise to res. */
    protected void rotCCW(IntFe a, IntFv res) { call(IntOp.rotCcw(a, res)); }

    /** Scans a from left to right into boolean res. */
    protected void scanLeft(IntV  a, BoolV  res, TriFunction<BoolV,  BoolV,  BoolV,  Instruction> operation) { call(IntOp.scanLeft(a, res, operation)); }
    /** Scans a from left to right into boolean res. */
    protected void scanLeft(IntVe a, BoolVe res, TriFunction<BoolVe, BoolVe, BoolVe, Instruction> operation) { call(IntOp.scanLeft(a, res, operation)); }
    /** Scans a from left to right into boolean res. */
    protected void scanLeft(IntVf a, BoolVf res, TriFunction<BoolVf, BoolVf, BoolVf, Instruction> operation) { call(IntOp.scanLeft(a, res, operation)); }
    /** Scans a from left to right into boolean res. */
    protected void scanLeft(IntE  a, BoolE  res, TriFunction<BoolE,  BoolE,  BoolE,  Instruction> operation) { call(IntOp.scanLeft(a, res, operation)); }
    /** Scans a from left to right into boolean res. */
    protected void scanLeft(IntEv a, BoolEv res, TriFunction<BoolEv, BoolEv, BoolEv, Instruction> operation) { call(IntOp.scanLeft(a, res, operation));  }
    /** Scans a from left to right into boolean res. */
    protected void scanLeft(IntEf a, BoolEf res, TriFunction<BoolEf, BoolEf, BoolEf, Instruction> operation) { call(IntOp.scanLeft(a, res, operation));  }
    /** Scans a from left to right into boolean res. */
    protected void scanLeft(IntF  a, BoolF  res, TriFunction<BoolF,  BoolF,  BoolF,  Instruction> operation) { call(IntOp.scanLeft(a, res, operation));  }
    /** Scans a from left to right into boolean res. */
    protected void scanLeft(IntFv a, BoolFv res, TriFunction<BoolFv, BoolFv, BoolFv, Instruction> operation) { call(IntOp.scanLeft(a, res, operation));  }
    /** Scans a from left to right into boolean res. */
    protected void scanLeft(IntFe a, BoolFe res, TriFunction<BoolFe, BoolFe, BoolFe, Instruction> operation) { call(IntOp.scanLeft(a, res, operation));  }

    /** Scans a from right to left into boolean res. */
    protected void scanRight(IntV  a, BoolV  res, TriFunction<BoolV,  BoolV,  BoolV,  Instruction> operation) { call(IntOp.scanRight(a, res, operation)); }
    /** Scans a from right to left into boolean res. */
    protected void scanRight(IntVe a, BoolVe res, TriFunction<BoolVe, BoolVe, BoolVe, Instruction> operation) { call(IntOp.scanRight(a, res, operation)); }
    /** Scans a from right to left into boolean res. */
    protected void scanRight(IntVf a, BoolVf res, TriFunction<BoolVf, BoolVf, BoolVf, Instruction> operation) { call(IntOp.scanRight(a, res, operation)); }
    /** Scans a from right to left into boolean res. */
    protected void scanRight(IntE  a, BoolE  res, TriFunction<BoolE,  BoolE,  BoolE,  Instruction> operation) { call(IntOp.scanRight(a, res, operation)); }
    /** Scans a from right to left into boolean res. */
    protected void scanRight(IntEv a, BoolEv res, TriFunction<BoolEv, BoolEv, BoolEv, Instruction> operation) { call(IntOp.scanRight(a, res, operation));  }
    /** Scans a from right to left into boolean res. */
    protected void scanRight(IntEf a, BoolEf res, TriFunction<BoolEf, BoolEf, BoolEf, Instruction> operation) { call(IntOp.scanRight(a, res, operation));  }
    /** Scans a from right to left into boolean res. */
    protected void scanRight(IntF  a, BoolF  res, TriFunction<BoolF,  BoolF,  BoolF,  Instruction> operation) { call(IntOp.scanRight(a, res, operation));  }
    /** Scans a from right to left into boolean res. */
    protected void scanRight(IntFv a, BoolFv res, TriFunction<BoolFv, BoolFv, BoolFv, Instruction> operation) { call(IntOp.scanRight(a, res, operation));  }
    /** Scans a from right to left into boolean res. */
    protected void scanRight(IntFe a, BoolFe res, TriFunction<BoolFe, BoolFe, BoolFe, Instruction> operation) { call(IntOp.scanRight(a, res, operation));  }
}
