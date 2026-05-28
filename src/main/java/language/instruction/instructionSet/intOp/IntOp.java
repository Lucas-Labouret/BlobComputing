package language.instruction.instructionSet.intOp;

import language.instruction.Procedure;
import language.instruction.instructionSet.BasicInstruction;
import language.instruction.instructionSet.boolOp.BoolOp;
import language.obj.field.intField.*;
import language.ref.field.boolField.fieldS.BoolERef;
import language.ref.field.boolField.fieldS.BoolFRef;
import language.ref.field.boolField.fieldS.BoolVRef;
import language.ref.field.boolField.fieldT.*;
import language.ref.field.intField.*;

public class IntOp {
    // NOT operations
    public static Procedure not(IntVRef a, IntVRef  res) { return new NotV(a, res); }
    public static Procedure not(IntVeRef a, IntVeRef res) { return new NotVe(a, res); }
    public static Procedure not(IntVfRef a, IntVfRef res) { return new NotVf(a, res); }
    public static Procedure not(IntERef a, IntERef  res) { return new NotE(a, res); }
    public static Procedure not(IntEvRef a, IntEvRef res) { return new NotEv(a, res); }
    public static Procedure not(IntEfRef a, IntEfRef res) { return new NotEf(a, res); }
    public static Procedure not(IntFRef  a, IntFRef  res) { return new NotF(a, res); }
    public static Procedure not(IntFvRef a, IntFvRef res) { return new NotFv(a, res); }
    public static Procedure not(IntFeRef a, IntFeRef res) { return new NotFe(a, res); }

    // NEG operations
    public static Procedure neg(IntVRef  a, IntVRef  res) { return new NegV(a, res); }
    public static Procedure neg(IntVeRef a, IntVeRef res) { return new NegVe(a, res); }
    public static Procedure neg(IntVfRef a, IntVfRef res) { return new NegVf(a, res); }
    public static Procedure neg(IntERef  a, IntERef  res) { return new NegE(a, res); }
    public static Procedure neg(IntEvRef a, IntEvRef res) { return new NegEv(a, res); }
    public static Procedure neg(IntEfRef a, IntEfRef res) { return new NegEf(a, res); }
    public static Procedure neg(IntFRef  a, IntFRef  res) { return new NegF(a, res); }
    public static Procedure neg(IntFvRef a, IntFvRef res) { return new NegFv(a, res); }
    public static Procedure neg(IntFeRef a, IntFeRef res) { return new NegFe(a, res); }

    // AND operations
    public static Procedure and(IntVRef  a, IntVRef  b, IntVRef  res) { return new AndV(a, b, res); }
    public static Procedure and(IntVeRef a, IntVeRef b, IntVeRef res) { return new AndVe(a, b, res); }
    public static Procedure and(IntVfRef a, IntVfRef b, IntVfRef res) { return new AndVf(a, b, res); }
    public static Procedure and(IntERef  a, IntERef  b, IntERef  res) { return new AndE(a, b, res); }
    public static Procedure and(IntEvRef a, IntEvRef b, IntEvRef res) { return new AndEv(a, b, res); }
    public static Procedure and(IntEfRef a, IntEfRef b, IntEfRef res) { return new AndEf(a, b, res); }
    public static Procedure and(IntFRef  a, IntFRef  b, IntFRef  res) { return new AndF(a, b, res); }
    public static Procedure and(IntFvRef a, IntFvRef b, IntFvRef res) { return new AndFv(a, b, res); }
    public static Procedure and(IntFeRef a, IntFeRef b, IntFeRef res) { return new AndFe(a, b, res); }

    // OR operations
    public static Procedure or(IntVRef  a, IntVRef  b, IntVRef  res) { return new OrV(a, b, res); }
    public static Procedure or(IntVeRef a, IntVeRef b, IntVeRef res) { return new OrVe(a, b, res); }
    public static Procedure or(IntVfRef a, IntVfRef b, IntVfRef res) { return new OrVf(a, b, res); }
    public static Procedure or(IntERef  a, IntERef  b, IntERef  res) { return new OrE(a, b, res); }
    public static Procedure or(IntEvRef a, IntEvRef b, IntEvRef res) { return new OrEv(a, b, res); }
    public static Procedure or(IntEfRef a, IntEfRef b, IntEfRef res) { return new OrEf(a, b, res); }
    public static Procedure or(IntFRef  a, IntFRef  b, IntFRef  res) { return new OrF(a, b, res); }
    public static Procedure or(IntFvRef a, IntFvRef b, IntFvRef res) { return new OrFv(a, b, res); }
    public static Procedure or(IntFeRef a, IntFeRef b, IntFeRef res) { return new OrFe(a, b, res); }

    // XOR operations
    public static Procedure xor(IntVRef  a, IntVRef  b, IntVRef  res) { return new XorV(a, b, res); }
    public static Procedure xor(IntVeRef a, IntVeRef b, IntVeRef res) { return new XorVe(a, b, res); }
    public static Procedure xor(IntVfRef a, IntVfRef b, IntVfRef res) { return new XorVf(a, b, res); }
    public static Procedure xor(IntERef  a, IntERef  b, IntERef  res) { return new XorE(a, b, res); }
    public static Procedure xor(IntEvRef a, IntEvRef b, IntEvRef res) { return new XorEv(a, b, res); }
    public static Procedure xor(IntEfRef a, IntEfRef b, IntEfRef res) { return new XorEf(a, b, res); }
    public static Procedure xor(IntFRef  a, IntFRef  b, IntFRef  res) { return new XorF(a, b, res); }
    public static Procedure xor(IntFvRef a, IntFvRef b, IntFvRef res) { return new XorFv(a, b, res); }
    public static Procedure xor(IntFeRef a, IntFeRef b, IntFeRef res) { return new XorFe(a, b, res); }

    // LEFT SHIFT operations
    public static BasicInstruction lShift(IntVRef  a, IntVRef  res, int k) { return new LShiftV (a, res, k); }
    public static BasicInstruction lShift(IntVeRef a, IntVeRef res, int k) { return new LShiftVe(a, res, k); }
    public static BasicInstruction lShift(IntVfRef a, IntVfRef res, int k) { return new LShiftVf(a, res, k); }
    public static BasicInstruction lShift(IntERef  a, IntERef  res, int k) { return new LShiftE (a, res, k); }
    public static BasicInstruction lShift(IntEvRef a, IntEvRef res, int k) { return new LShiftEv(a, res, k); }
    public static BasicInstruction lShift(IntEfRef a, IntEfRef res, int k) { return new LShiftEf(a, res, k); }
    public static BasicInstruction lShift(IntFRef  a, IntFRef  res, int k) { return new LShiftF (a, res, k); }
    public static BasicInstruction lShift(IntFvRef a, IntFvRef res, int k) { return new LShiftFv(a, res, k); }
    public static BasicInstruction lShift(IntFeRef a, IntFeRef res, int k) { return new LShiftFe(a, res, k); }

    // RIGHT SHIFT operations
    public static BasicInstruction rShift(IntVRef  a, IntVRef  res, int k) { return new RShiftV (a, res, k); }
    public static BasicInstruction rShift(IntVeRef a, IntVeRef res, int k) { return new RShiftVe(a, res, k); }
    public static BasicInstruction rShift(IntVfRef a, IntVfRef res, int k) { return new RShiftVf(a, res, k); }
    public static BasicInstruction rShift(IntERef  a, IntERef  res, int k) { return new RShiftE (a, res, k); }
    public static BasicInstruction rShift(IntEvRef a, IntEvRef res, int k) { return new RShiftEv(a, res, k); }
    public static BasicInstruction rShift(IntEfRef a, IntEfRef res, int k) { return new RShiftEf(a, res, k); }
    public static BasicInstruction rShift(IntFRef  a, IntFRef  res, int k) { return new RShiftF (a, res, k); }
    public static BasicInstruction rShift(IntFvRef a, IntFvRef res, int k) { return new RShiftFv(a, res, k); }
    public static BasicInstruction rShift(IntFeRef a, IntFeRef res, int k) { return new RShiftFe(a, res, k); }

    // ADD operations
    public static Procedure add(IntVRef  a, IntVRef  b, IntVRef  res) { return new AddV(a, b, res); }
    public static Procedure add(IntVeRef a, IntVeRef b, IntVeRef res) { return new AddVe(a, b, res); }
    public static Procedure add(IntVfRef a, IntVfRef b, IntVfRef res) { return new AddVf(a, b, res); }
    public static Procedure add(IntERef  a, IntERef  b, IntERef  res) { return new AddE(a, b, res); }
    public static Procedure add(IntEvRef a, IntEvRef b, IntEvRef res) { return new AddEv(a, b, res); }
    public static Procedure add(IntEfRef a, IntEfRef b, IntEfRef res) { return new AddEf(a, b, res); }
    public static Procedure add(IntFRef  a, IntFRef  b, IntFRef  res) { return new AddF(a, b, res); }
    public static Procedure add(IntFvRef a, IntFvRef b, IntFvRef res) { return new AddFv(a, b, res); }
    public static Procedure add(IntFeRef a, IntFeRef b, IntFeRef res) { return new AddFe(a, b, res); }

    // GT / greater-than operations (a > b)
    public static Procedure gt(IntVRef  a, IntVRef  b, BoolVRef res) { return new GTV (a, b, res); }
    public static Procedure gt(IntVeRef a, IntVeRef b, BoolVeRef res) { return new GTVe(a, b, res); }
    public static Procedure gt(IntVfRef a, IntVfRef b, BoolVfRef res) { return new GTVf(a, b, res); }
    public static Procedure gt(IntERef  a, IntERef  b, BoolERef res) { return new GTE (a, b, res); }
    public static Procedure gt(IntEvRef a, IntEvRef b, BoolEvRef res) { return new GTEv(a, b, res); }
    public static Procedure gt(IntEfRef a, IntEfRef b, BoolEfRef res) { return new GTEf(a, b, res); }
    public static Procedure gt(IntFRef  a, IntFRef  b, BoolFRef res) { return new GTF (a, b, res); }
    public static Procedure gt(IntFvRef a, IntFvRef b, BoolFvRef res) { return new GTFv(a, b, res); }
    public static Procedure gt(IntFeRef a, IntFeRef b, BoolFeRef res) { return new GTFe(a, b, res); }

    // SUB operations
    public static Procedure sub(IntVRef  a, IntVRef  b, IntVRef  res) { return new SubV(a, b, res); }
    public static Procedure sub(IntVeRef a, IntVeRef b, IntVeRef res) { return new SubVe(a, b, res); }
    public static Procedure sub(IntVfRef a, IntVfRef b, IntVfRef res) { return new SubVf(a, b, res); }
    public static Procedure sub(IntERef  a, IntERef  b, IntERef  res) { return new SubE(a, b, res); }
    public static Procedure sub(IntEvRef a, IntEvRef b, IntEvRef res) { return new SubEv(a, b, res); }
    public static Procedure sub(IntEfRef a, IntEfRef b, IntEfRef res) { return new SubEf(a, b, res); }
    public static Procedure sub(IntFRef  a, IntFRef  b, IntFRef  res) { return new SubF(a, b, res); }
    public static Procedure sub(IntFvRef a, IntFvRef b, IntFvRef res) { return new SubFv(a, b, res); }
    public static Procedure sub(IntFeRef a, IntFeRef b, IntFeRef res) { return new SubFe(a, b, res); }

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
}
