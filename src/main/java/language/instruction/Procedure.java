package language.instruction;

import language.Obj;
import language.Ref;
import language.cache.Cache;
import language.instruction.basicInstruction.*;
import language.instruction.basicInstruction.bitOp.BitOp;
import language.instruction.basicInstruction.commOp.CommOp;
import language.ref.field.intField.*;
import language.ref.field.boolField.fieldS.*;
import language.ref.field.boolField.fieldT.*;
import language.obj.field.intField.IntField;

import java.util.ArrayList;

/** Represents an instruction composed of a sequence of sub-instructions. */
@SuppressWarnings("unused")
public abstract non-sealed class Procedure implements Instruction {
    private int instrPtr = 0;
    private final ArrayList<Instruction> instr;

    public int getInstrPtr() { return instrPtr; }
    public void setInstrPtr(int instrPtr) { this.instrPtr = instrPtr; }

    /** Creates a new Procedure. */
    public Procedure() {
        instr = new ArrayList<>();
        Cache.register(this);
    }

    /** Adds the given instruction to this procedure. */
    protected void call(Instruction i) {
        instr.add(i);
    }

    @Override
    public int leafCount() {
        int count = 0;
        for (Instruction i : instr) count += i.leafCount();
        return count;
    }

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

    public BasicInstruction currentBasicInstruction() {
        return switch (instr.get(instrPtr)) {
            case BasicInstruction i -> i;
            case Procedure p -> p.currentBasicInstruction();
        };
    }

    /** @return false if there are more instructions to execute, true if the loop is finished. */
    @Override
    public final boolean exec() {
        //if (instr.get(instrCounter) instanceof BasicInstruction) System.out.println("Executing " + instr.get(instrCounter).getClass().getSimpleName());

        boolean done = instr.get(instrPtr).exec();
        if (done) instrPtr++; // If the current instruction is done, we move to the next

        if (instrPtr == instr.size()) {
            // There are no more instruction to perform.
            // We reset the counter so that the loop can be executed again, and return true to indicate that the loop is finished
            instrPtr = 0;
            return true;
        }
        return false;
    }

    // Wrapper functions to make writing procedures easier. These functions simply add the corresponding instruction to this procedure.

    protected void print(@SuppressWarnings("SameParameterValue") String message) { call(new Print(message)); }
    protected <T extends Obj> void show(String name, Ref<T> fieldRef) { call(new Show(name, fieldRef)); }
    protected void snapshot() { call(new Snapshot()); }
    protected <T extends Obj> void set(Ref<T> in, Ref<T> out) { call(new SetRef<>(in, out)); }

    protected void not(BoolVRef  a, BoolVRef  res) { call(BitOp.not(a, res)); }
    protected void not(BoolVeRef a, BoolVeRef res) { call(BitOp.not(a, res)); }
    protected void not(BoolVfRef a, BoolVfRef res) { call(BitOp.not(a, res)); }
    protected void not(BoolERef  a, BoolERef  res) { call(BitOp.not(a, res)); }
    protected void not(BoolEvRef a, BoolEvRef res) { call(BitOp.not(a, res)); }
    protected void not(BoolEfRef a, BoolEfRef res) { call(BitOp.not(a, res)); }
    protected void not(BoolFRef  a, BoolFRef  res) { call(BitOp.not(a, res)); }
    protected void not(BoolFvRef a, BoolFvRef res) { call(BitOp.not(a, res)); }
    protected void not(BoolFeRef a, BoolFeRef res) { call(BitOp.not(a, res)); }

    protected void not(IntVRef  a, IntVRef  res) { call(IntField.not(a, res)); }
    protected void not(IntVeRef a, IntVeRef res) { call(IntField.not(a, res)); }
    protected void not(IntVfRef a, IntVfRef res) { call(IntField.not(a, res)); }
    protected void not(IntERef  a, IntERef  res) { call(IntField.not(a, res)); }
    protected void not(IntEvRef a, IntEvRef res) { call(IntField.not(a, res)); }
    protected void not(IntEfRef a, IntEfRef res) { call(IntField.not(a, res)); }
    protected void not(IntFRef  a, IntFRef  res) { call(IntField.not(a, res)); }
    protected void not(IntFvRef a, IntFvRef res) { call(IntField.not(a, res)); }
    protected void not(IntFeRef a, IntFeRef res) { call(IntField.not(a, res)); }

    protected void neg(IntVRef  a, IntVRef  res) { call(IntField.neg(a, res)); }
    protected void neg(IntVeRef a, IntVeRef res) { call(IntField.neg(a, res)); }
    protected void neg(IntVfRef a, IntVfRef res) { call(IntField.neg(a, res)); }
    protected void neg(IntERef  a, IntERef  res) { call(IntField.neg(a, res)); }
    protected void neg(IntEvRef a, IntEvRef res) { call(IntField.neg(a, res)); }
    protected void neg(IntEfRef a, IntEfRef res) { call(IntField.neg(a, res)); }
    protected void neg(IntFRef  a, IntFRef  res) { call(IntField.neg(a, res)); }
    protected void neg(IntFvRef a, IntFvRef res) { call(IntField.neg(a, res)); }
    protected void neg(IntFeRef a, IntFeRef res) { call(IntField.neg(a, res)); }

    protected void lShift(IntVRef  a, IntVRef  res, int k) { call(IntField.lShift(a, res, k)); }
    protected void lShift(IntVeRef a, IntVeRef res, int k) { call(IntField.lShift(a, res, k)); }
    protected void lShift(IntVfRef a, IntVfRef res, int k) { call(IntField.lShift(a, res, k)); }
    protected void lShift(IntERef  a, IntERef  res, int k) { call(IntField.lShift(a, res, k)); }
    protected void lShift(IntEvRef a, IntEvRef res, int k) { call(IntField.lShift(a, res, k)); }
    protected void lShift(IntEfRef a, IntEfRef res, int k) { call(IntField.lShift(a, res, k)); }
    protected void lShift(IntFRef  a, IntFRef  res, int k) { call(IntField.lShift(a, res, k)); }
    protected void lShift(IntFvRef a, IntFvRef res, int k) { call(IntField.lShift(a, res, k)); }
    protected void lShift(IntFeRef a, IntFeRef res, int k) { call(IntField.lShift(a, res, k)); }

    protected void rShift(IntVRef  a, IntVRef  res, int k) { call(IntField.rShift(a, res, k)); }
    protected void rShift(IntVeRef a, IntVeRef res, int k) { call(IntField.rShift(a, res, k)); }
    protected void rShift(IntVfRef a, IntVfRef res, int k) { call(IntField.rShift(a, res, k)); }
    protected void rShift(IntERef  a, IntERef  res, int k) { call(IntField.rShift(a, res, k)); }
    protected void rShift(IntEvRef a, IntEvRef res, int k) { call(IntField.rShift(a, res, k)); }
    protected void rShift(IntEfRef a, IntEfRef res, int k) { call(IntField.rShift(a, res, k)); }
    protected void rShift(IntFRef  a, IntFRef  res, int k) { call(IntField.rShift(a, res, k)); }
    protected void rShift(IntFvRef a, IntFvRef res, int k) { call(IntField.rShift(a, res, k)); }
    protected void rShift(IntFeRef a, IntFeRef res, int k) { call(IntField.rShift(a, res, k)); }

    protected void fromBool(BoolVRef  a, IntVRef  res) { call(IntField.fromBool(a, res)); }
    protected void fromBool(BoolVeRef a, IntVeRef res) { call(IntField.fromBool(a, res)); }
    protected void fromBool(BoolVfRef a, IntVfRef res) { call(IntField.fromBool(a, res)); }
    protected void fromBool(BoolERef  a, IntERef  res) { call(IntField.fromBool(a, res)); }
    protected void fromBool(BoolEvRef a, IntEvRef res) { call(IntField.fromBool(a, res)); }
    protected void fromBool(BoolEfRef a, IntEfRef res) { call(IntField.fromBool(a, res)); }
    protected void fromBool(BoolFRef  a, IntFRef  res) { call(IntField.fromBool(a, res)); }
    protected void fromBool(BoolFvRef a, IntFvRef res) { call(IntField.fromBool(a, res)); }
    protected void fromBool(BoolFeRef a, IntFeRef res) { call(IntField.fromBool(a, res)); }

    protected void split(IntVRef  a, BoolVRef[]  res) { call(IntField.split(a, res)); }
    protected void split(IntVeRef a, BoolVeRef[] res) { call(IntField.split(a, res)); }
    protected void split(IntVfRef a, BoolVfRef[] res) { call(IntField.split(a, res)); }
    protected void split(IntERef  a, BoolERef[]  res) { call(IntField.split(a, res)); }
    protected void split(IntEvRef a, BoolEvRef[] res) { call(IntField.split(a, res)); }
    protected void split(IntEfRef a, BoolEfRef[] res) { call(IntField.split(a, res)); }
    protected void split(IntFRef  a, BoolFRef[]  res) { call(IntField.split(a, res)); }
    protected void split(IntFvRef a, BoolFvRef[] res) { call(IntField.split(a, res)); }
    protected void split(IntFeRef a, BoolFeRef[] res) { call(IntField.split(a, res)); }

    protected void join(BoolVRef[]  a, IntVRef  res) { call(IntField.join(a, res)); }
    protected void join(BoolVeRef[] a, IntVeRef res) { call(IntField.join(a, res)); }
    protected void join(BoolVfRef[] a, IntVfRef res) { call(IntField.join(a, res)); }
    protected void join(BoolERef[]  a, IntERef  res) { call(IntField.join(a, res)); }
    protected void join(BoolEvRef[] a, IntEvRef res) { call(IntField.join(a, res)); }
    protected void join(BoolEfRef[] a, IntEfRef res) { call(IntField.join(a, res)); }
    protected void join(BoolFRef[]  a, IntFRef  res) { call(IntField.join(a, res)); }
    protected void join(BoolFvRef[] a, IntFvRef res) { call(IntField.join(a, res)); }
    protected void join(BoolFeRef[] a, IntFeRef res) { call(IntField.join(a, res)); }

    protected void and(BoolVRef  a, BoolVRef  b, BoolVRef  res) { call(BitOp.and(a, b, res)); }
    protected void and(BoolVeRef a, BoolVeRef b, BoolVeRef res) { call(BitOp.and(a, b, res)); }
    protected void and(BoolVfRef a, BoolVfRef b, BoolVfRef res) { call(BitOp.and(a, b, res)); }
    protected void and(BoolERef  a, BoolERef  b, BoolERef  res) { call(BitOp.and(a, b, res)); }
    protected void and(BoolEvRef a, BoolEvRef b, BoolEvRef res) { call(BitOp.and(a, b, res)); }
    protected void and(BoolEfRef a, BoolEfRef b, BoolEfRef res) { call(BitOp.and(a, b, res)); }
    protected void and(BoolFRef  a, BoolFRef  b, BoolFRef  res) { call(BitOp.and(a, b, res)); }
    protected void and(BoolFvRef a, BoolFvRef b, BoolFvRef res) { call(BitOp.and(a, b, res)); }
    protected void and(BoolFeRef a, BoolFeRef b, BoolFeRef res) { call(BitOp.and(a, b, res)); }

    protected void and(IntVRef  a, IntVRef  b, IntVRef  res) { call(IntField.and(a, b, res)); }
    protected void and(IntVeRef a, IntVeRef b, IntVeRef res) { call(IntField.and(a, b, res)); }
    protected void and(IntVfRef a, IntVfRef b, IntVfRef res) { call(IntField.and(a, b, res)); }
    protected void and(IntERef  a, IntERef  b, IntERef  res) { call(IntField.and(a, b, res)); }
    protected void and(IntEvRef a, IntEvRef b, IntEvRef res) { call(IntField.and(a, b, res)); }
    protected void and(IntEfRef a, IntEfRef b, IntEfRef res) { call(IntField.and(a, b, res)); }
    protected void and(IntFRef  a, IntFRef  b, IntFRef  res) { call(IntField.and(a, b, res)); }
    protected void and(IntFvRef a, IntFvRef b, IntFvRef res) { call(IntField.and(a, b, res)); }
    protected void and(IntFeRef a, IntFeRef b, IntFeRef res) { call(IntField.and(a, b, res)); }

    protected void or(BoolVRef  a, BoolVRef  b, BoolVRef  res) { call(BitOp.or(a, b, res)); }
    protected void or(BoolVeRef a, BoolVeRef b, BoolVeRef res) { call(BitOp.or(a, b, res)); }
    protected void or(BoolVfRef a, BoolVfRef b, BoolVfRef res) { call(BitOp.or(a, b, res)); }
    protected void or(BoolERef  a, BoolERef  b, BoolERef  res) { call(BitOp.or(a, b, res)); }
    protected void or(BoolEvRef a, BoolEvRef b, BoolEvRef res) { call(BitOp.or(a, b, res)); }
    protected void or(BoolEfRef a, BoolEfRef b, BoolEfRef res) { call(BitOp.or(a, b, res)); }
    protected void or(BoolFRef  a, BoolFRef  b, BoolFRef  res) { call(BitOp.or(a, b, res)); }
    protected void or(BoolFvRef a, BoolFvRef b, BoolFvRef res) { call(BitOp.or(a, b, res)); }
    protected void or(BoolFeRef a, BoolFeRef b, BoolFeRef res) { call(BitOp.or(a, b, res)); }

    protected void or(IntVRef  a, IntVRef  b, IntVRef  res) { call(IntField.or(a, b, res)); }
    protected void or(IntVeRef a, IntVeRef b, IntVeRef res) { call(IntField.or(a, b, res)); }
    protected void or(IntVfRef a, IntVfRef b, IntVfRef res) { call(IntField.or(a, b, res)); }
    protected void or(IntERef  a, IntERef  b, IntERef  res) { call(IntField.or(a, b, res)); }
    protected void or(IntEvRef a, IntEvRef b, IntEvRef res) { call(IntField.or(a, b, res)); }
    protected void or(IntEfRef a, IntEfRef b, IntEfRef res) { call(IntField.or(a, b, res)); }
    protected void or(IntFRef  a, IntFRef  b, IntFRef  res) { call(IntField.or(a, b, res)); }
    protected void or(IntFvRef a, IntFvRef b, IntFvRef res) { call(IntField.or(a, b, res)); }
    protected void or(IntFeRef a, IntFeRef b, IntFeRef res) { call(IntField.or(a, b, res)); }

    protected void xor(BoolVRef  a, BoolVRef  b, BoolVRef  res) { call(BitOp.xor(a, b, res)); }
    protected void xor(BoolVeRef a, BoolVeRef b, BoolVeRef res) { call(BitOp.xor(a, b, res)); }
    protected void xor(BoolVfRef a, BoolVfRef b, BoolVfRef res) { call(BitOp.xor(a, b, res)); }
    protected void xor(BoolERef  a, BoolERef  b, BoolERef  res) { call(BitOp.xor(a, b, res)); }
    protected void xor(BoolEvRef a, BoolEvRef b, BoolEvRef res) { call(BitOp.xor(a, b, res)); }
    protected void xor(BoolEfRef a, BoolEfRef b, BoolEfRef res) { call(BitOp.xor(a, b, res)); }
    protected void xor(BoolFRef  a, BoolFRef  b, BoolFRef  res) { call(BitOp.xor(a, b, res)); }
    protected void xor(BoolFvRef a, BoolFvRef b, BoolFvRef res) { call(BitOp.xor(a, b, res)); }
    protected void xor(BoolFeRef a, BoolFeRef b, BoolFeRef res) { call(BitOp.xor(a, b, res)); }

    protected void xor(IntVRef  a, IntVRef  b, IntVRef  res) { call(IntField.xor(a, b, res)); }
    protected void xor(IntVeRef a, IntVeRef b, IntVeRef res) { call(IntField.xor(a, b, res)); }
    protected void xor(IntVfRef a, IntVfRef b, IntVfRef res) { call(IntField.xor(a, b, res)); }
    protected void xor(IntERef  a, IntERef  b, IntERef  res) { call(IntField.xor(a, b, res)); }
    protected void xor(IntEvRef a, IntEvRef b, IntEvRef res) { call(IntField.xor(a, b, res)); }
    protected void xor(IntEfRef a, IntEfRef b, IntEfRef res) { call(IntField.xor(a, b, res)); }
    protected void xor(IntFRef  a, IntFRef  b, IntFRef  res) { call(IntField.xor(a, b, res)); }
    protected void xor(IntFvRef a, IntFvRef b, IntFvRef res) { call(IntField.xor(a, b, res)); }
    protected void xor(IntFeRef a, IntFeRef b, IntFeRef res) { call(IntField.xor(a, b, res)); }

    protected void add(IntVRef  a, IntVRef  b, IntVRef  res) { call(IntField.add(a, b, res)); }
    protected void add(IntVeRef a, IntVeRef b, IntVeRef res) { call(IntField.add(a, b, res)); }
    protected void add(IntVfRef a, IntVfRef b, IntVfRef res) { call(IntField.add(a, b, res)); }
    protected void add(IntERef  a, IntERef  b, IntERef  res) { call(IntField.add(a, b, res)); }
    protected void add(IntEvRef a, IntEvRef b, IntEvRef res) { call(IntField.add(a, b, res)); }
    protected void add(IntEfRef a, IntEfRef b, IntEfRef res) { call(IntField.add(a, b, res)); }
    protected void add(IntFRef  a, IntFRef  b, IntFRef  res) { call(IntField.add(a, b, res)); }
    protected void add(IntFvRef a, IntFvRef b, IntFvRef res) { call(IntField.add(a, b, res)); }
    protected void add(IntFeRef a, IntFeRef b, IntFeRef res) { call(IntField.add(a, b, res)); }

    protected void sub(IntVRef  a, IntVRef  b, IntVRef  res) { call(IntField.sub(a, b, res)); }
    protected void sub(IntVeRef a, IntVeRef b, IntVeRef res) { call(IntField.sub(a, b, res)); }
    protected void sub(IntVfRef a, IntVfRef b, IntVfRef res) { call(IntField.sub(a, b, res)); }
    protected void sub(IntERef  a, IntERef  b, IntERef  res) { call(IntField.sub(a, b, res)); }
    protected void sub(IntEvRef a, IntEvRef b, IntEvRef res) { call(IntField.sub(a, b, res)); }
    protected void sub(IntEfRef a, IntEfRef b, IntEfRef res) { call(IntField.sub(a, b, res)); }
    protected void sub(IntFRef  a, IntFRef  b, IntFRef  res) { call(IntField.sub(a, b, res)); }
    protected void sub(IntFvRef a, IntFvRef b, IntFvRef res) { call(IntField.sub(a, b, res)); }
    protected void sub(IntFeRef a, IntFeRef b, IntFeRef res) { call(IntField.sub(a, b, res)); }

    protected void gt(IntVRef  a, IntVRef  b, BoolVRef  res) { call(IntField.gt(a, b, res)); }
    protected void gt(IntVeRef a, IntVeRef b, BoolVeRef res) { call(IntField.gt(a, b, res)); }
    protected void gt(IntVfRef a, IntVfRef b, BoolVfRef res) { call(IntField.gt(a, b, res)); }
    protected void gt(IntERef  a, IntERef  b, BoolERef  res) { call(IntField.gt(a, b, res)); }
    protected void gt(IntEvRef a, IntEvRef b, BoolEvRef res) { call(IntField.gt(a, b, res)); }
    protected void gt(IntEfRef a, IntEfRef b, BoolEfRef res) { call(IntField.gt(a, b, res)); }
    protected void gt(IntFRef  a, IntFRef  b, BoolFRef  res) { call(IntField.gt(a, b, res)); }
    protected void gt(IntFvRef a, IntFvRef b, BoolFvRef res) { call(IntField.gt(a, b, res)); }
    protected void gt(IntFeRef a, IntFeRef b, BoolFeRef res) { call(IntField.gt(a, b, res)); }

    protected void broadcast(BoolVRef a, BoolVeRef res) { call(CommOp.broadcast(a, res)); }
    protected void broadcast(BoolVRef a, BoolVfRef res) { call(CommOp.broadcast(a, res)); }
    protected void broadcast(BoolERef a, BoolEvRef res) { call(CommOp.broadcast(a, res)); }
    protected void broadcast(BoolERef a, BoolEfRef res) { call(CommOp.broadcast(a, res)); }
    protected void broadcast(BoolFRef a, BoolFvRef res) { call(CommOp.broadcast(a, res)); }
    protected void broadcast(BoolFRef a, BoolFeRef res) { call(CommOp.broadcast(a, res)); }

    protected void transfer(BoolVeRef a, BoolEvRef res) { call(CommOp.transfer(a, res)); }
    protected void transfer(BoolVfRef a, BoolFvRef res) { call(CommOp.transfer(a, res)); }
    protected void transfer(BoolEvRef a, BoolVeRef res) { call(CommOp.transfer(a, res)); }
    protected void transfer(BoolEfRef a, BoolFeRef res) { call(CommOp.transfer(a, res)); }
    protected void transfer(BoolFvRef a, BoolVfRef res) { call(CommOp.transfer(a, res)); }
    protected void transfer(BoolFeRef a, BoolEfRef res) { call(CommOp.transfer(a, res)); }

    protected void redAnd(BoolVeRef a, BoolVRef res) { call(CommOp.redAnd(a, res)); }
    protected void redAnd(BoolVfRef a, BoolVRef res) { call(CommOp.redAnd(a, res)); }
    protected void redAnd(BoolEvRef a, BoolERef res) { call(CommOp.redAnd(a, res)); }
    protected void redAnd(BoolEfRef a, BoolERef res) { call(CommOp.redAnd(a, res)); }
    protected void redAnd(BoolFvRef a, BoolFRef res) { call(CommOp.redAnd(a, res)); }
    protected void redAnd(BoolFeRef a, BoolFRef res) { call(CommOp.redAnd(a, res)); }

    protected void redOr(BoolVeRef a, BoolVRef res) { call(CommOp.redOr(a, res)); }
    protected void redOr(BoolVfRef a, BoolVRef res) { call(CommOp.redOr(a, res)); }
    protected void redOr(BoolEvRef a, BoolERef res) { call(CommOp.redOr(a, res)); }
    protected void redOr(BoolEfRef a, BoolERef res) { call(CommOp.redOr(a, res)); }
    protected void redOr(BoolFvRef a, BoolFRef res) { call(CommOp.redOr(a, res)); }
    protected void redOr(BoolFeRef a, BoolFRef res) { call(CommOp.redOr(a, res)); }

    protected void redXor(BoolVeRef a, BoolVRef res) { call(CommOp.redXor(a, res)); }
    protected void redXor(BoolVfRef a, BoolVRef res) { call(CommOp.redXor(a, res)); }
    protected void redXor(BoolEvRef a, BoolERef res) { call(CommOp.redXor(a, res)); }
    protected void redXor(BoolEfRef a, BoolERef res) { call(CommOp.redXor(a, res)); }
    protected void redXor(BoolFvRef a, BoolFRef res) { call(CommOp.redXor(a, res)); }
    protected void redXor(BoolFeRef a, BoolFRef res) { call(CommOp.redXor(a, res)); }

    protected void redStack0(BoolVeRef a, BoolVRef[] res) { call(CommOp.redStack0(a, res)); }
    protected void redStack1(BoolVeRef a, BoolVRef[] res) { call(CommOp.redStack1(a, res)); }
    protected void redStack0(BoolVfRef a, BoolVRef[] res) { call(CommOp.redStack0(a, res)); }
    protected void redStack1(BoolVfRef a, BoolVRef[] res) { call(CommOp.redStack1(a, res)); }
    protected void redStack0(BoolEvRef a, BoolERef[] res) { call(CommOp.redStack0(a, res)); }
    protected void redStack1(BoolEvRef a, BoolERef[] res) { call(CommOp.redStack1(a, res)); }
    protected void redStack0(BoolEfRef a, BoolERef[] res) { call(CommOp.redStack0(a, res)); }
    protected void redStack1(BoolEfRef a, BoolERef[] res) { call(CommOp.redStack1(a, res)); }
    protected void redStack0(BoolFvRef a, BoolFRef[] res) { call(CommOp.redStack0(a, res)); }
    protected void redStack1(BoolFvRef a, BoolFRef[] res) { call(CommOp.redStack1(a, res)); }
    protected void redStack0(BoolFeRef a, BoolFRef[] res) { call(CommOp.redStack0(a, res)); }
    protected void redStack1(BoolFeRef a, BoolFRef[] res) { call(CommOp.redStack1(a, res)); }

    protected void redAdd(BoolVeRef a, IntVRef res) { call(IntField.redAdd(a, res)); }
    protected void redAdd(BoolVfRef a, IntVRef res) { call(IntField.redAdd(a, res)); }
    protected void redAdd(BoolEvRef a, IntERef res) { call(IntField.redAdd(a, res)); }
    protected void redAdd(BoolEfRef a, IntERef res) { call(IntField.redAdd(a, res)); }
    protected void redAdd(BoolFvRef a, IntFRef res) { call(IntField.redAdd(a, res)); }
    protected void redAdd(BoolFeRef a, IntFRef res) { call(IntField.redAdd(a, res)); }

    protected void redAdd(IntVeRef a, IntVRef res) { call(IntField.redAdd(a, res)); }
    protected void redAdd(IntVfRef a, IntVRef res) { call(IntField.redAdd(a, res)); }

    protected void rotCW(BoolVeRef a, BoolVfRef res) { call(CommOp.rotCW(a, res)); }
    protected void rotCW(BoolVfRef a, BoolVeRef res) { call(CommOp.rotCW(a, res)); }
    protected void rotCW(BoolEvRef a, BoolEfRef res) { call(CommOp.rotCW(a, res)); }
    protected void rotCW(BoolEfRef a, BoolEvRef res) { call(CommOp.rotCW(a, res)); }
    protected void rotCW(BoolFvRef a, BoolFeRef res) { call(CommOp.rotCW(a, res)); }
    protected void rotCW(BoolFeRef a, BoolFvRef res) { call(CommOp.rotCW(a, res)); }

    protected void rotCCW(BoolVeRef a, BoolVfRef res) { call(CommOp.rotCCW(a, res)); }
    protected void rotCCW(BoolVfRef a, BoolVeRef res) { call(CommOp.rotCCW(a, res)); }
    protected void rotCCW(BoolEvRef a, BoolEfRef res) { call(CommOp.rotCCW(a, res)); }
    protected void rotCCW(BoolEfRef a, BoolEvRef res) { call(CommOp.rotCCW(a, res)); }
    protected void rotCCW(BoolFvRef a, BoolFeRef res) { call(CommOp.rotCCW(a, res)); }
    protected void rotCCW(BoolFeRef a, BoolFvRef res) { call(CommOp.rotCCW(a, res)); }
}
