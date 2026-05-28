package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.instruction.instructionSet.BasicInstruction;
import language.instruction.instructionSet.boolOp.BoolOp;
import language.ref.field.boolField.*;
import language.ref.field.intField.*;

public class IntOp {
    // NOT operations
    public static Procedure not(IntVRef  orig, IntVRef  res) { return new NotV (orig, res); }
    public static Procedure not(IntVeRef orig, IntVeRef res) { return new NotVe(orig, res); }
    public static Procedure not(IntVfRef orig, IntVfRef res) { return new NotVf(orig, res); }
    public static Procedure not(IntERef  orig, IntERef  res) { return new NotE (orig, res); }
    public static Procedure not(IntEvRef orig, IntEvRef res) { return new NotEv(orig, res); }
    public static Procedure not(IntEfRef orig, IntEfRef res) { return new NotEf(orig, res); }
    public static Procedure not(IntFRef  orig, IntFRef  res) { return new NotF (orig, res); }
    public static Procedure not(IntFvRef orig, IntFvRef res) { return new NotFv(orig, res); }
    public static Procedure not(IntFeRef orig, IntFeRef res) { return new NotFe(orig, res); }

    // NEG operations
    public static Procedure neg(IntVRef  orig, IntVRef  res) { return new NegV (orig, res); }
    public static Procedure neg(IntVeRef orig, IntVeRef res) { return new NegVe(orig, res); }
    public static Procedure neg(IntVfRef orig, IntVfRef res) { return new NegVf(orig, res); }
    public static Procedure neg(IntERef  orig, IntERef  res) { return new NegE (orig, res); }
    public static Procedure neg(IntEvRef orig, IntEvRef res) { return new NegEv(orig, res); }
    public static Procedure neg(IntEfRef orig, IntEfRef res) { return new NegEf(orig, res); }
    public static Procedure neg(IntFRef  orig, IntFRef  res) { return new NegF (orig, res); }
    public static Procedure neg(IntFvRef orig, IntFvRef res) { return new NegFv(orig, res); }
    public static Procedure neg(IntFeRef orig, IntFeRef res) { return new NegFe(orig, res); }

    // AND operations
    public static Procedure and(IntVRef  a, IntVRef  b, IntVRef  res) { return new AndV (a, b, res); }
    public static Procedure and(IntVeRef a, IntVeRef b, IntVeRef res) { return new AndVe(a, b, res); }
    public static Procedure and(IntVfRef a, IntVfRef b, IntVfRef res) { return new AndVf(a, b, res); }
    public static Procedure and(IntERef  a, IntERef  b, IntERef  res) { return new AndE (a, b, res); }
    public static Procedure and(IntEvRef a, IntEvRef b, IntEvRef res) { return new AndEv(a, b, res); }
    public static Procedure and(IntEfRef a, IntEfRef b, IntEfRef res) { return new AndEf(a, b, res); }
    public static Procedure and(IntFRef  a, IntFRef  b, IntFRef  res) { return new AndF (a, b, res); }
    public static Procedure and(IntFvRef a, IntFvRef b, IntFvRef res) { return new AndFv(a, b, res); }
    public static Procedure and(IntFeRef a, IntFeRef b, IntFeRef res) { return new AndFe(a, b, res); }

    // OR operations
    public static Procedure or(IntVRef  a, IntVRef  b, IntVRef  res) { return new OrV (a, b, res); }
    public static Procedure or(IntVeRef a, IntVeRef b, IntVeRef res) { return new OrVe(a, b, res); }
    public static Procedure or(IntVfRef a, IntVfRef b, IntVfRef res) { return new OrVf(a, b, res); }
    public static Procedure or(IntERef  a, IntERef  b, IntERef  res) { return new OrE (a, b, res); }
    public static Procedure or(IntEvRef a, IntEvRef b, IntEvRef res) { return new OrEv(a, b, res); }
    public static Procedure or(IntEfRef a, IntEfRef b, IntEfRef res) { return new OrEf(a, b, res); }
    public static Procedure or(IntFRef  a, IntFRef  b, IntFRef  res) { return new OrF (a, b, res); }
    public static Procedure or(IntFvRef a, IntFvRef b, IntFvRef res) { return new OrFv(a, b, res); }
    public static Procedure or(IntFeRef a, IntFeRef b, IntFeRef res) { return new OrFe(a, b, res); }

    // XOR operations
    public static Procedure xor(IntVRef  a, IntVRef  b, IntVRef  res) { return new XorV (a, b, res); }
    public static Procedure xor(IntVeRef a, IntVeRef b, IntVeRef res) { return new XorVe(a, b, res); }
    public static Procedure xor(IntVfRef a, IntVfRef b, IntVfRef res) { return new XorVf(a, b, res); }
    public static Procedure xor(IntERef  a, IntERef  b, IntERef  res) { return new XorE (a, b, res); }
    public static Procedure xor(IntEvRef a, IntEvRef b, IntEvRef res) { return new XorEv(a, b, res); }
    public static Procedure xor(IntEfRef a, IntEfRef b, IntEfRef res) { return new XorEf(a, b, res); }
    public static Procedure xor(IntFRef  a, IntFRef  b, IntFRef  res) { return new XorF (a, b, res); }
    public static Procedure xor(IntFvRef a, IntFvRef b, IntFvRef res) { return new XorFv(a, b, res); }
    public static Procedure xor(IntFeRef a, IntFeRef b, IntFeRef res) { return new XorFe(a, b, res); }

    // LEFT SHIFT operations
    public static BasicInstruction lShift(IntVRef  orig, IntVRef  res, int k) { return new LShiftV (orig, res, k); }
    public static BasicInstruction lShift(IntVeRef orig, IntVeRef res, int k) { return new LShiftVe(orig, res, k); }
    public static BasicInstruction lShift(IntVfRef orig, IntVfRef res, int k) { return new LShiftVf(orig, res, k); }
    public static BasicInstruction lShift(IntERef  orig, IntERef  res, int k) { return new LShiftE (orig, res, k); }
    public static BasicInstruction lShift(IntEvRef orig, IntEvRef res, int k) { return new LShiftEv(orig, res, k); }
    public static BasicInstruction lShift(IntEfRef orig, IntEfRef res, int k) { return new LShiftEf(orig, res, k); }
    public static BasicInstruction lShift(IntFRef  orig, IntFRef  res, int k) { return new LShiftF (orig, res, k); }
    public static BasicInstruction lShift(IntFvRef orig, IntFvRef res, int k) { return new LShiftFv(orig, res, k); }
    public static BasicInstruction lShift(IntFeRef orig, IntFeRef res, int k) { return new LShiftFe(orig, res, k); }

    // RIGHT SHIFT operations
    public static BasicInstruction rShift(IntVRef  orig, IntVRef  res, int k) { return new RShiftV (orig, res, k); }
    public static BasicInstruction rShift(IntVeRef orig, IntVeRef res, int k) { return new RShiftVe(orig, res, k); }
    public static BasicInstruction rShift(IntVfRef orig, IntVfRef res, int k) { return new RShiftVf(orig, res, k); }
    public static BasicInstruction rShift(IntERef  orig, IntERef  res, int k) { return new RShiftE (orig, res, k); }
    public static BasicInstruction rShift(IntEvRef orig, IntEvRef res, int k) { return new RShiftEv(orig, res, k); }
    public static BasicInstruction rShift(IntEfRef orig, IntEfRef res, int k) { return new RShiftEf(orig, res, k); }
    public static BasicInstruction rShift(IntFRef  orig, IntFRef  res, int k) { return new RShiftF (orig, res, k); }
    public static BasicInstruction rShift(IntFvRef orig, IntFvRef res, int k) { return new RShiftFv(orig, res, k); }
    public static BasicInstruction rShift(IntFeRef orig, IntFeRef res, int k) { return new RShiftFe(orig, res, k); }

    // ADD operations
    public static Procedure add(IntVRef  a, IntVRef  b, IntVRef  res) { return new AddV (a, b, res); }
    public static Procedure add(IntVeRef a, IntVeRef b, IntVeRef res) { return new AddVe(a, b, res); }
    public static Procedure add(IntVfRef a, IntVfRef b, IntVfRef res) { return new AddVf(a, b, res); }
    public static Procedure add(IntERef  a, IntERef  b, IntERef  res) { return new AddE (a, b, res); }
    public static Procedure add(IntEvRef a, IntEvRef b, IntEvRef res) { return new AddEv(a, b, res); }
    public static Procedure add(IntEfRef a, IntEfRef b, IntEfRef res) { return new AddEf(a, b, res); }
    public static Procedure add(IntFRef  a, IntFRef  b, IntFRef  res) { return new AddF (a, b, res); }
    public static Procedure add(IntFvRef a, IntFvRef b, IntFvRef res) { return new AddFv(a, b, res); }
    public static Procedure add(IntFeRef a, IntFeRef b, IntFeRef res) { return new AddFe(a, b, res); }

    // SUB operations
    public static Procedure sub(IntVRef  a, IntVRef  b, IntVRef  res) { return new SubV (a, b, res); }
    public static Procedure sub(IntVeRef a, IntVeRef b, IntVeRef res) { return new SubVe(a, b, res); }
    public static Procedure sub(IntVfRef a, IntVfRef b, IntVfRef res) { return new SubVf(a, b, res); }
    public static Procedure sub(IntERef  a, IntERef  b, IntERef  res) { return new SubE (a, b, res); }
    public static Procedure sub(IntEvRef a, IntEvRef b, IntEvRef res) { return new SubEv(a, b, res); }
    public static Procedure sub(IntEfRef a, IntEfRef b, IntEfRef res) { return new SubEf(a, b, res); }
    public static Procedure sub(IntFRef  a, IntFRef  b, IntFRef  res) { return new SubF (a, b, res); }
    public static Procedure sub(IntFvRef a, IntFvRef b, IntFvRef res) { return new SubFv(a, b, res); }
    public static Procedure sub(IntFeRef a, IntFeRef b, IntFeRef res) { return new SubFe(a, b, res); }

    //EQ operations
    public static Procedure eq(IntVRef  a, IntVRef  b, BoolVRef  res) { return new EqV (a, b, res); }
    public static Procedure eq(IntVeRef a, IntVeRef b, BoolVeRef res) { return new EqVe(a, b, res); }
    public static Procedure eq(IntVfRef a, IntVfRef b, BoolVfRef res) { return new EqVf(a, b, res); }
    public static Procedure eq(IntERef  a, IntERef  b, BoolERef  res) { return new EqE (a, b, res); }
    public static Procedure eq(IntEvRef a, IntEvRef b, BoolEvRef res) { return new EqEv(a, b, res); }
    public static Procedure eq(IntEfRef a, IntEfRef b, BoolEfRef res) { return new EqEf(a, b, res); }
    public static Procedure eq(IntFRef  a, IntFRef  b, BoolFRef  res) { return new EqF (a, b, res); }
    public static Procedure eq(IntFvRef a, IntFvRef b, BoolFvRef res) { return new EqFv(a, b, res); }
    public static Procedure eq(IntFeRef a, IntFeRef b, BoolFeRef res) { return new EqFe(a, b, res); }

    // GT / greater-than operations (a >= b)
    public static Procedure gt(IntVRef  a, IntVRef  b, BoolVRef  res) { return new GTV (a, b, res); }
    public static Procedure gt(IntVeRef a, IntVeRef b, BoolVeRef res) { return new GTVe(a, b, res); }
    public static Procedure gt(IntVfRef a, IntVfRef b, BoolVfRef res) { return new GTVf(a, b, res); }
    public static Procedure gt(IntERef  a, IntERef  b, BoolERef  res) { return new GTE (a, b, res); }
    public static Procedure gt(IntEvRef a, IntEvRef b, BoolEvRef res) { return new GTEv(a, b, res); }
    public static Procedure gt(IntEfRef a, IntEfRef b, BoolEfRef res) { return new GTEf(a, b, res); }
    public static Procedure gt(IntFRef  a, IntFRef  b, BoolFRef  res) { return new GTF (a, b, res); }
    public static Procedure gt(IntFvRef a, IntFvRef b, BoolFvRef res) { return new GTFv(a, b, res); }
    public static Procedure gt(IntFeRef a, IntFeRef b, BoolFeRef res) { return new GTFe(a, b, res); }

    // BROADCAST operations
    public static Procedure broadcast(IntVRef orig, IntVeRef res) { return new BroadcastVe(orig, res); }
    public static Procedure broadcast(IntVRef orig, IntVfRef res) { return new BroadcastVf(orig, res); }
    public static Procedure broadcast(IntERef orig, IntEvRef res) { return new BroadcastEv(orig, res); }
    public static Procedure broadcast(IntERef orig, IntEfRef res) { return new BroadcastEf(orig, res); }
    public static Procedure broadcast(IntFRef orig, IntFvRef res) { return new BroadcastFv(orig, res); }
    public static Procedure broadcast(IntFRef orig, IntFeRef res) { return new BroadcastFe(orig, res); }

    // REDUCE STACK operation
    public static Procedure redStack0(IntVeRef orig, IntVRef[] res) {
        RedStack_Ve.RedStack[] stacks = new RedStack_Ve.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ve(orig, res, stacks);
    }
    public static Procedure redStack1(IntVeRef orig, IntVRef[] res) {
        RedStack_Ve.RedStack[] stacks = new RedStack_Ve.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ve(orig, res, stacks);
    }
    public static Procedure redStackMin(IntVeRef orig, IntVRef[] res) {
        RedStack_Ve.RedStack[] stacks = new RedStack_Ve.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ve(orig, res, stacks);
    }
    public static Procedure redStackMax(IntVeRef orig, IntVRef[] res) {
        RedStack_Ve.RedStack[] stacks = new RedStack_Ve.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ve(orig, res, stacks);
    }
    public static Procedure redStack0(IntVfRef orig, IntVRef[] res) {
        RedStack_Vf.RedStack[] stacks = new RedStack_Vf.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Vf(orig, res, stacks);
    }
    public static Procedure redStack1(IntVfRef orig, IntVRef[] res) {
        RedStack_Vf.RedStack[] stacks = new RedStack_Vf.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Vf(orig, res, stacks);
    }
    public static Procedure redStackMin(IntVfRef orig, IntVRef[] res) {
        RedStack_Vf.RedStack[] stacks = new RedStack_Vf.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Vf(orig, res, stacks);
    }
    public static Procedure redStackMax(IntVfRef orig, IntVRef[] res) {
        RedStack_Vf.RedStack[] stacks = new RedStack_Vf.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Vf(orig, res, stacks);
    }
    public static Procedure redStack0(IntEvRef orig, IntERef[] res) {
        RedStack_Ev.RedStack[] stacks = new RedStack_Ev.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ev(orig, res, stacks);
    }
    public static Procedure redStack1(IntEvRef orig, IntERef[] res) {
        RedStack_Ev.RedStack[] stacks = new RedStack_Ev.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ev(orig, res, stacks);
    }
    public static Procedure redStackMin(IntEvRef orig, IntERef[] res) {
        RedStack_Ev.RedStack[] stacks = new RedStack_Ev.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ev(orig, res, stacks);
    }
    public static Procedure redStackMax(IntEvRef orig, IntERef[] res) {
        RedStack_Ev.RedStack[] stacks = new RedStack_Ev.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ev(orig, res, stacks);
    }
    public static Procedure redStack0(IntEfRef orig, IntERef[] res) {
        RedStack_Ef.RedStack[] stacks = new RedStack_Ef.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ef(orig, res, stacks);
    }
    public static Procedure redStack1(IntEfRef orig, IntERef[] res) {
        RedStack_Ef.RedStack[] stacks = new RedStack_Ef.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ef(orig, res, stacks);
    }
    public static Procedure redStackMin(IntEfRef orig, IntERef[] res) {
        RedStack_Ef.RedStack[] stacks = new RedStack_Ef.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ef(orig, res, stacks);
    }
    public static Procedure redStackMax(IntEfRef orig, IntERef[] res) {
        RedStack_Ef.RedStack[] stacks = new RedStack_Ef.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ef(orig, res, stacks);
    }
    public static Procedure redStack0(IntFvRef orig, IntFRef[] res) {
        RedStack_Fv.RedStack[] stacks = new RedStack_Fv.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Fv(orig, res, stacks);
    }
    public static Procedure redStack1(IntFvRef orig, IntFRef[] res) {
        RedStack_Fv.RedStack[] stacks = new RedStack_Fv.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Fv(orig, res, stacks);
    }
    public static Procedure redStackMin(IntFvRef orig, IntFRef[] res) {
        RedStack_Fv.RedStack[] stacks = new RedStack_Fv.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Fv(orig, res, stacks);
    }
    public static Procedure redStackMax(IntFvRef orig, IntFRef[] res) {
        RedStack_Fv.RedStack[] stacks = new RedStack_Fv.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Fv(orig, res, stacks);
    }
    public static Procedure redStack0(IntFeRef orig, IntFRef[] res) {
        RedStack_Fe.RedStack[] stacks = new RedStack_Fe.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Fe(orig, res, stacks);
    }
    public static Procedure redStack1(IntFeRef orig, IntFRef[] res) {
        RedStack_Fe.RedStack[] stacks = new RedStack_Fe.RedStack[orig.get().n+1];
        for (int i = 0; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Fe(orig, res, stacks);
    }
    public static Procedure redStackMin(IntFeRef orig, IntFRef[] res) {
        RedStack_Fe.RedStack[] stacks = new RedStack_Fe.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Fe(orig, res, stacks);
    }
    public static Procedure redStackMax(IntFeRef orig, IntFRef[] res) {
        RedStack_Fe.RedStack[] stacks = new RedStack_Fe.RedStack[orig.get().n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.get().n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Fe(orig, res, stacks);
    }

    // REDUCE ADD operations
    public static Procedure redAdd(BoolVeRef boolVe, IntVRef intV) { return new RedAddVe(boolVe, intV); }
    public static Procedure redAdd(BoolVfRef boolVf, IntVRef intV) { return new RedAddVf(boolVf, intV); }
    public static Procedure redAdd(BoolEvRef boolEv, IntERef intE) { return new RedAddEv(boolEv, intE); }
    public static Procedure redAdd(BoolEfRef boolEf, IntERef intE) { return new RedAddEf(boolEf, intE); }
    public static Procedure redAdd(BoolFvRef boolFv, IntFRef intF) { return new RedAddFv(boolFv, intF); }
    public static Procedure redAdd(BoolFeRef boolFe, IntFRef intF) { return new RedAddFe(boolFe, intF); }

    public static Procedure redAdd(IntVeRef intVe, IntVRef intV) { return new RedAddVe(intVe, intV); }
    public static Procedure redAdd(IntVfRef intVf, IntVRef intV) { return new RedAddVf(intVf, intV); }
    public static Procedure redAdd(IntEvRef intEv, IntERef intE) { return new RedAddEv(intEv, intE); }
    public static Procedure redAdd(IntEfRef intEf, IntERef intE) { return new RedAddEf(intEf, intE); }
    public static Procedure redAdd(IntFvRef intFv, IntFRef intF) { return new RedAddFv(intFv, intF); }
    public static Procedure redAdd(IntFeRef intFe, IntFRef intF) { return new RedAddFe(intFe, intF); }

    // FROM BOOL operations
    public static BasicInstruction fromBool(BoolVRef  boolV,  IntVRef  intV ) { return new BoolToIntV (boolV,  intV ); }
    public static BasicInstruction fromBool(BoolVeRef boolVe, IntVeRef intVe) { return new BoolToIntVe(boolVe, intVe); }
    public static BasicInstruction fromBool(BoolVfRef boolVf, IntVfRef intVf) { return new BoolToIntVf(boolVf, intVf); }
    public static BasicInstruction fromBool(BoolERef  boolE,  IntERef  intE ) { return new BoolToIntE (boolE,  intE ); }
    public static BasicInstruction fromBool(BoolEvRef boolEv, IntEvRef intEv) { return new BoolToIntEv(boolEv, intEv); }
    public static BasicInstruction fromBool(BoolEfRef boolEf, IntEfRef intEf) { return new BoolToIntEf(boolEf, intEf); }
    public static BasicInstruction fromBool(BoolFRef  boolF,  IntFRef  intF ) { return new BoolToIntF (boolF,  intF ); }
    public static BasicInstruction fromBool(BoolFvRef boolFv, IntFvRef intFv) { return new BoolToIntFv(boolFv, intFv); }
    public static BasicInstruction fromBool(BoolFeRef boolFe, IntFeRef intFe) { return new BoolToIntFe(boolFe, intFe); }

    // SPLIT operations
    public static BasicInstruction split(IntVRef  intV,  BoolVRef[]  boolV ) { return new SplitV (intV,  boolV ); }
    public static BasicInstruction split(IntVeRef intVe, BoolVeRef[] boolVe) { return new SplitVe(intVe, boolVe); }
    public static BasicInstruction split(IntVfRef intVf, BoolVfRef[] boolVf) { return new SplitVf(intVf, boolVf); }
    public static BasicInstruction split(IntERef  intE,  BoolERef[]  boolE ) { return new SplitE (intE,  boolE ); }
    public static BasicInstruction split(IntEvRef intEv, BoolEvRef[] boolEv) { return new SplitEv(intEv, boolEv); }
    public static BasicInstruction split(IntEfRef intEf, BoolEfRef[] boolEf) { return new SplitEf(intEf, boolEf); }
    public static BasicInstruction split(IntFRef  intF,  BoolFRef[]  boolF ) { return new SplitF (intF,  boolF ); }
    public static BasicInstruction split(IntFvRef intFv, BoolFvRef[] boolFv) { return new SplitFv(intFv, boolFv); }
    public static BasicInstruction split(IntFeRef intFe, BoolFeRef[] boolFe) { return new SplitFe(intFe, boolFe); }

    // JOIN operations
    public static BasicInstruction join(BoolVRef[]  boolV,  IntVRef  intV ) { return new JoinV (boolV,  intV ); }
    public static BasicInstruction join(BoolVeRef[] boolVe, IntVeRef intVe) { return new JoinVe(boolVe, intVe); }
    public static BasicInstruction join(BoolVfRef[] boolVf, IntVfRef intVf) { return new JoinVf(boolVf, intVf); }
    public static BasicInstruction join(BoolERef[]  boolE,  IntERef  intE ) { return new JoinE (boolE,  intE ); }
    public static BasicInstruction join(BoolEvRef[] boolEv, IntEvRef intEv) { return new JoinEv(boolEv, intEv); }
    public static BasicInstruction join(BoolEfRef[] boolEf, IntEfRef intEf) { return new JoinEf(boolEf, intEf); }
    public static BasicInstruction join(BoolFRef[]  boolF,  IntFRef  intF ) { return new JoinF (boolF,  intF ); }
    public static BasicInstruction join(BoolFvRef[] boolFv, IntFvRef intFv) { return new JoinFv(boolFv, intFv); }
    public static BasicInstruction join(BoolFeRef[] boolFe, IntFeRef intFe) { return new JoinFe(boolFe, intFe); }

    // SCAN operations
    public static Procedure scanLeft (IntVRef  orig,  BoolVRef  res, ScanLeftV.Scan   scan) { return new ScanLeftV  (orig, res, scan); }
    public static Procedure scanRight(IntVRef  orig,  BoolVRef  res, ScanRightV.Scan  scan) { return new ScanRightV (orig, res, scan); }
    public static Procedure scanLeft (IntVeRef orig,  BoolVeRef res, ScanLeftVe.Scan  scan) { return new ScanLeftVe (orig, res, scan); }
    public static Procedure scanRight(IntVeRef orig,  BoolVeRef res, ScanRightVe.Scan scan) { return new ScanRightVe(orig, res, scan); }
    public static Procedure scanLeft (IntVfRef orig,  BoolVfRef res, ScanLeftVf.Scan  scan) { return new ScanLeftVf (orig, res, scan); }
    public static Procedure scanRight(IntVfRef orig,  BoolVfRef res, ScanRightVf.Scan scan) { return new ScanRightVf(orig, res, scan); }
    public static Procedure scanLeft (IntERef  orig,  BoolERef  res, ScanLeftE.Scan   scan) { return new ScanLeftE  (orig, res, scan); }
    public static Procedure scanRight(IntERef  orig,  BoolERef  res, ScanRightE.Scan  scan) { return new ScanRightE (orig, res, scan); }
    public static Procedure scanLeft (IntEvRef orig,  BoolEvRef res, ScanLeftEv.Scan  scan) { return new ScanLeftEv (orig, res, scan); }
    public static Procedure scanRight(IntEvRef orig,  BoolEvRef res, ScanRightEv.Scan scan) { return new ScanRightEv(orig, res, scan); }
    public static Procedure scanLeft (IntEfRef orig,  BoolEfRef res, ScanLeftEf.Scan  scan) { return new ScanLeftEf (orig, res, scan); }
    public static Procedure scanRight(IntEfRef orig,  BoolEfRef res, ScanRightEf.Scan scan) { return new ScanRightEf(orig, res, scan); }
    public static Procedure scanLeft (IntFRef  orig,  BoolFRef  res, ScanLeftF.Scan   scan) { return new ScanLeftF  (orig, res, scan); }
    public static Procedure scanRight(IntFRef  orig,  BoolFRef  res, ScanRightF.Scan  scan) { return new ScanRightF (orig, res, scan); }
    public static Procedure scanLeft (IntFvRef orig,  BoolFvRef res, ScanLeftFv.Scan  scan) { return new ScanLeftFv (orig, res, scan); }
    public static Procedure scanRight(IntFvRef orig,  BoolFvRef res, ScanRightFv.Scan scan) { return new ScanRightFv(orig, res, scan); }
    public static Procedure scanLeft (IntFeRef orig,  BoolFeRef res, ScanLeftFe.Scan  scan) { return new ScanLeftFe (orig, res, scan); }
    public static Procedure scanRight(IntFeRef orig,  BoolFeRef res, ScanRightFe.Scan scan) { return new ScanRightFe(orig, res, scan); }

}
