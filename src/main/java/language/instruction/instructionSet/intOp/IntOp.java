package language.instruction.instructionSet.intOp;

import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.BasicInstruction;
import language.instruction.Procedure;
import language.instruction.instructionSet.boolOp.BoolOp;

public class IntOp {
    public static Procedure transfer(IntVe orig, IntEv  res) { return new TransferVe(orig, res); }
    public static Procedure transfer(IntVf orig, IntFv  res) { return new TransferVf(orig, res); }
    public static Procedure transfer(IntEv orig, IntVe  res) { return new TransferEv(orig, res); }
    public static Procedure transfer(IntEf orig, IntFe  res) { return new TransferEf(orig, res); }
    public static Procedure transfer(IntFv orig, IntVf  res) { return new TransferFv(orig, res); }
    public static Procedure transfer(IntFe orig, IntEf  res) { return new TransferFe(orig, res); }

    public static Procedure rotCW(IntVe orig, IntVf res) { return new RotVeCW(orig, res); }
    public static Procedure rotCW(IntVf orig, IntVe res) { return new RotVfCW(orig, res); }
    public static Procedure rotCW(IntEv orig, IntEf res) { return new RotEvCW(orig, res); }
    public static Procedure rotCW(IntEf orig, IntEv res) { return new RotEfCW(orig, res); }
    public static Procedure rotCW(IntFv orig, IntFe res) { return new RotFvCW(orig, res); }
    public static Procedure rotCW(IntFe orig, IntFv res) { return new RotFeCW(orig, res); }

    public static Procedure rotCCW(IntVe orig, IntVf res) { return new RotVeCCW(orig, res); }
    public static Procedure rotCCW(IntVf orig, IntVe res) { return new RotVfCCW(orig, res); }
    public static Procedure rotCCW(IntEv orig, IntEf res) { return new RotEvCCW(orig, res); }
    public static Procedure rotCCW(IntEf orig, IntEv res) { return new RotEfCCW(orig, res); }
    public static Procedure rotCCW(IntFv orig, IntFe res) { return new RotFvCCW(orig, res); }
    public static Procedure rotCCW(IntFe orig, IntFv res) { return new RotFeCCW(orig, res); }

    // NOT operations
    public static Procedure not(IntV  orig, IntV  res) { return new NotV (orig, res); }
    public static Procedure not(IntVe orig, IntVe res) { return new NotVe(orig, res); }
    public static Procedure not(IntVf orig, IntVf res) { return new NotVf(orig, res); }
    public static Procedure not(IntE  orig, IntE  res) { return new NotE (orig, res); }
    public static Procedure not(IntEv orig, IntEv res) { return new NotEv(orig, res); }
    public static Procedure not(IntEf orig, IntEf res) { return new NotEf(orig, res); }
    public static Procedure not(IntF  orig, IntF  res) { return new NotF (orig, res); }
    public static Procedure not(IntFv orig, IntFv res) { return new NotFv(orig, res); }
    public static Procedure not(IntFe orig, IntFe res) { return new NotFe(orig, res); }

    // NEG operations
    public static Procedure neg(IntV  orig, IntV  res) { return new NegV (orig, res); }
    public static Procedure neg(IntVe orig, IntVe res) { return new NegVe(orig, res); }
    public static Procedure neg(IntVf orig, IntVf res) { return new NegVf(orig, res); }
    public static Procedure neg(IntE  orig, IntE  res) { return new NegE (orig, res); }
    public static Procedure neg(IntEv orig, IntEv res) { return new NegEv(orig, res); }
    public static Procedure neg(IntEf orig, IntEf res) { return new NegEf(orig, res); }
    public static Procedure neg(IntF  orig, IntF  res) { return new NegF (orig, res); }
    public static Procedure neg(IntFv orig, IntFv res) { return new NegFv(orig, res); }
    public static Procedure neg(IntFe orig, IntFe res) { return new NegFe(orig, res); }

    // AND operations
    public static Procedure and(IntV  a, IntV  b, IntV  res) { return new AndV (a, b, res); }
    public static Procedure and(IntVe a, IntVe b, IntVe res) { return new AndVe(a, b, res); }
    public static Procedure and(IntVf a, IntVf b, IntVf res) { return new AndVf(a, b, res); }
    public static Procedure and(IntE  a, IntE  b, IntE  res) { return new AndE (a, b, res); }
    public static Procedure and(IntEv a, IntEv b, IntEv res) { return new AndEv(a, b, res); }
    public static Procedure and(IntEf a, IntEf b, IntEf res) { return new AndEf(a, b, res); }
    public static Procedure and(IntF  a, IntF  b, IntF  res) { return new AndF (a, b, res); }
    public static Procedure and(IntFv a, IntFv b, IntFv res) { return new AndFv(a, b, res); }
    public static Procedure and(IntFe a, IntFe b, IntFe res) { return new AndFe(a, b, res); }

    // OR operations
    public static Procedure or(IntV  a, IntV  b, IntV  res) { return new OrV (a, b, res); }
    public static Procedure or(IntVe a, IntVe b, IntVe res) { return new OrVe(a, b, res); }
    public static Procedure or(IntVf a, IntVf b, IntVf res) { return new OrVf(a, b, res); }
    public static Procedure or(IntE  a, IntE  b, IntE  res) { return new OrE (a, b, res); }
    public static Procedure or(IntEv a, IntEv b, IntEv res) { return new OrEv(a, b, res); }
    public static Procedure or(IntEf a, IntEf b, IntEf res) { return new OrEf(a, b, res); }
    public static Procedure or(IntF  a, IntF  b, IntF  res) { return new OrF (a, b, res); }
    public static Procedure or(IntFv a, IntFv b, IntFv res) { return new OrFv(a, b, res); }
    public static Procedure or(IntFe a, IntFe b, IntFe res) { return new OrFe(a, b, res); }

    // XOR operations
    public static Procedure xor(IntV  a, IntV  b, IntV  res) { return new XorV (a, b, res); }
    public static Procedure xor(IntVe a, IntVe b, IntVe res) { return new XorVe(a, b, res); }
    public static Procedure xor(IntVf a, IntVf b, IntVf res) { return new XorVf(a, b, res); }
    public static Procedure xor(IntE  a, IntE  b, IntE  res) { return new XorE (a, b, res); }
    public static Procedure xor(IntEv a, IntEv b, IntEv res) { return new XorEv(a, b, res); }
    public static Procedure xor(IntEf a, IntEf b, IntEf res) { return new XorEf(a, b, res); }
    public static Procedure xor(IntF  a, IntF  b, IntF  res) { return new XorF (a, b, res); }
    public static Procedure xor(IntFv a, IntFv b, IntFv res) { return new XorFv(a, b, res); }
    public static Procedure xor(IntFe a, IntFe b, IntFe res) { return new XorFe(a, b, res); }

    // LEFT SHIFT operations
    public static BasicInstruction lShift(IntV  orig, IntV  res, int k) { return new LShiftV (orig, res, k); }
    public static BasicInstruction lShift(IntVe orig, IntVe res, int k) { return new LShiftVe(orig, res, k); }
    public static BasicInstruction lShift(IntVf orig, IntVf res, int k) { return new LShiftVf(orig, res, k); }
    public static BasicInstruction lShift(IntE  orig, IntE  res, int k) { return new LShiftE (orig, res, k); }
    public static BasicInstruction lShift(IntEv orig, IntEv res, int k) { return new LShiftEv(orig, res, k); }
    public static BasicInstruction lShift(IntEf orig, IntEf res, int k) { return new LShiftEf(orig, res, k); }
    public static BasicInstruction lShift(IntF  orig, IntF  res, int k) { return new LShiftF (orig, res, k); }
    public static BasicInstruction lShift(IntFv orig, IntFv res, int k) { return new LShiftFv(orig, res, k); }
    public static BasicInstruction lShift(IntFe orig, IntFe res, int k) { return new LShiftFe(orig, res, k); }

    // RIGHT SHIFT operations
    public static BasicInstruction rShift(IntV  orig, IntV  res, int k) { return new RShiftV (orig, res, k); }
    public static BasicInstruction rShift(IntVe orig, IntVe res, int k) { return new RShiftVe(orig, res, k); }
    public static BasicInstruction rShift(IntVf orig, IntVf res, int k) { return new RShiftVf(orig, res, k); }
    public static BasicInstruction rShift(IntE  orig, IntE  res, int k) { return new RShiftE (orig, res, k); }
    public static BasicInstruction rShift(IntEv orig, IntEv res, int k) { return new RShiftEv(orig, res, k); }
    public static BasicInstruction rShift(IntEf orig, IntEf res, int k) { return new RShiftEf(orig, res, k); }
    public static BasicInstruction rShift(IntF  orig, IntF  res, int k) { return new RShiftF (orig, res, k); }
    public static BasicInstruction rShift(IntFv orig, IntFv res, int k) { return new RShiftFv(orig, res, k); }
    public static BasicInstruction rShift(IntFe orig, IntFe res, int k) { return new RShiftFe(orig, res, k); }

    // ADD operations
    public static Procedure add(IntV  a, IntV  b, IntV  res) { return new AddV (a, b, res); }
    public static Procedure add(IntVe a, IntVe b, IntVe res) { return new AddVe(a, b, res); }
    public static Procedure add(IntVf a, IntVf b, IntVf res) { return new AddVf(a, b, res); }
    public static Procedure add(IntE  a, IntE  b, IntE  res) { return new AddE (a, b, res); }
    public static Procedure add(IntEv a, IntEv b, IntEv res) { return new AddEv(a, b, res); }
    public static Procedure add(IntEf a, IntEf b, IntEf res) { return new AddEf(a, b, res); }
    public static Procedure add(IntF  a, IntF  b, IntF  res) { return new AddF (a, b, res); }
    public static Procedure add(IntFv a, IntFv b, IntFv res) { return new AddFv(a, b, res); }
    public static Procedure add(IntFe a, IntFe b, IntFe res) { return new AddFe(a, b, res); }

    // SUB operations
    public static Procedure sub(IntV  a, IntV  b, IntV  res) { return new SubV (a, b, res); }
    public static Procedure sub(IntVe a, IntVe b, IntVe res) { return new SubVe(a, b, res); }
    public static Procedure sub(IntVf a, IntVf b, IntVf res) { return new SubVf(a, b, res); }
    public static Procedure sub(IntE  a, IntE  b, IntE  res) { return new SubE (a, b, res); }
    public static Procedure sub(IntEv a, IntEv b, IntEv res) { return new SubEv(a, b, res); }
    public static Procedure sub(IntEf a, IntEf b, IntEf res) { return new SubEf(a, b, res); }
    public static Procedure sub(IntF  a, IntF  b, IntF  res) { return new SubF (a, b, res); }
    public static Procedure sub(IntFv a, IntFv b, IntFv res) { return new SubFv(a, b, res); }
    public static Procedure sub(IntFe a, IntFe b, IntFe res) { return new SubFe(a, b, res); }

    //EQ operations
    public static Procedure eq(IntV  a, IntV  b, BoolV  res) { return new EqV (a, b, res); }
    public static Procedure eq(IntVe a, IntVe b, BoolVe res) { return new EqVe(a, b, res); }
    public static Procedure eq(IntVf a, IntVf b, BoolVf res) { return new EqVf(a, b, res); }
    public static Procedure eq(IntE  a, IntE  b, BoolE  res) { return new EqE (a, b, res); }
    public static Procedure eq(IntEv a, IntEv b, BoolEv res) { return new EqEv(a, b, res); }
    public static Procedure eq(IntEf a, IntEf b, BoolEf res) { return new EqEf(a, b, res); }
    public static Procedure eq(IntF  a, IntF  b, BoolF  res) { return new EqF (a, b, res); }
    public static Procedure eq(IntFv a, IntFv b, BoolFv res) { return new EqFv(a, b, res); }
    public static Procedure eq(IntFe a, IntFe b, BoolFe res) { return new EqFe(a, b, res); }

    // GT / greater-than operations (a >= b)
    public static Procedure gt(IntV  a, IntV  b, BoolV  res) { return new GTV (a, b, res); }
    public static Procedure gt(IntVe a, IntVe b, BoolVe res) { return new GTVe(a, b, res); }
    public static Procedure gt(IntVf a, IntVf b, BoolVf res) { return new GTVf(a, b, res); }
    public static Procedure gt(IntE  a, IntE  b, BoolE  res) { return new GTE (a, b, res); }
    public static Procedure gt(IntEv a, IntEv b, BoolEv res) { return new GTEv(a, b, res); }
    public static Procedure gt(IntEf a, IntEf b, BoolEf res) { return new GTEf(a, b, res); }
    public static Procedure gt(IntF  a, IntF  b, BoolF  res) { return new GTF (a, b, res); }
    public static Procedure gt(IntFv a, IntFv b, BoolFv res) { return new GTFv(a, b, res); }
    public static Procedure gt(IntFe a, IntFe b, BoolFe res) { return new GTFe(a, b, res); }

    //ABS operations
    public static Procedure abs(IntV  a, IntV  res) { return new AbsV (a, res); }
    public static Procedure abs(IntVe a, IntVe res) { return new AbsVe(a, res); }
    public static Procedure abs(IntVf a, IntVf res) { return new AbsVf(a, res); }
    public static Procedure abs(IntE  a, IntE  res) { return new AbsE (a, res); }
    public static Procedure abs(IntEv a, IntEv res) { return new AbsEv(a, res); }
    public static Procedure abs(IntEf a, IntEf res) { return new AbsEf(a, res); }
    public static Procedure abs(IntF  a, IntF  res) { return new AbsF (a, res); }
    public static Procedure abs(IntFv a, IntFv res) { return new AbsFv(a, res); }
    public static Procedure abs(IntFe a, IntFe res) { return new AbsFe(a, res); }

    // IF operations
    public static Procedure fif(BoolV  cond, IntV  t, IntV  f, IntV  res) { return new IfV (cond, t, f, res); }
    public static Procedure fif(BoolVe cond, IntVe t, IntVe f, IntVe res) { return new IfVe(cond, t, f, res); }
    public static Procedure fif(BoolVf cond, IntVf t, IntVf f, IntVf res) { return new IfVf(cond, t, f, res); }
    public static Procedure fif(BoolE  cond, IntE  t, IntE  f, IntE  res) { return new IfE (cond, t, f, res); }
    public static Procedure fif(BoolEv cond, IntEv t, IntEv f, IntEv res) { return new IfEv(cond, t, f, res); }
    public static Procedure fif(BoolEf cond, IntEf t, IntEf f, IntEf res) { return new IfEf(cond, t, f, res); }
    public static Procedure fif(BoolF  cond, IntF  t, IntF  f, IntF  res) { return new IfF (cond, t, f, res); }
    public static Procedure fif(BoolFv cond, IntFv t, IntFv f, IntFv res) { return new IfFv(cond, t, f, res); }
    public static Procedure fif(BoolFe cond, IntFe t, IntFe f, IntFe res) { return new IfFe(cond, t, f, res); }

    // BROADCAST operations
    public static Procedure broadcast(IntV orig, IntVe res) { return new BroadcastVe(orig, res); }
    public static Procedure broadcast(IntV orig, IntVf res) { return new BroadcastVf(orig, res); }
    public static Procedure broadcast(IntE orig, IntEv res) { return new BroadcastEv(orig, res); }
    public static Procedure broadcast(IntE orig, IntEf res) { return new BroadcastEf(orig, res); }
    public static Procedure broadcast(IntF orig, IntFv res) { return new BroadcastFv(orig, res); }
    public static Procedure broadcast(IntF orig, IntFe res) { return new BroadcastFe(orig, res); }

    // REDUCE STACK operation
    public static Procedure redStack0(IntVe orig, IntV[] res) {
        RedStack_Ve.RedStack[] stacks = new RedStack_Ve.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ve(orig, res, stacks);
    }
    public static Procedure redStack1(IntVe orig, IntV[] res) {
        RedStack_Ve.RedStack[] stacks = new RedStack_Ve.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ve(orig, res, stacks);
    }
    public static Procedure redStackMin(IntVe orig, IntV[] res) {
        RedStack_Ve.RedStack[] stacks = new RedStack_Ve.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ve(orig, res, stacks);
    }
    public static Procedure redStackMax(IntVe orig, IntV[] res) {
        RedStack_Ve.RedStack[] stacks = new RedStack_Ve.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ve(orig, res, stacks);
    }
    public static Procedure redStack0(IntVf orig, IntV[] res) {
        RedStack_Vf.RedStack[] stacks = new RedStack_Vf.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Vf(orig, res, stacks);
    }
    public static Procedure redStack1(IntVf orig, IntV[] res) {
        RedStack_Vf.RedStack[] stacks = new RedStack_Vf.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Vf(orig, res, stacks);
    }
    public static Procedure redStackMin(IntVf orig, IntV[] res) {
        RedStack_Vf.RedStack[] stacks = new RedStack_Vf.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Vf(orig, res, stacks);
    }
    public static Procedure redStackMax(IntVf orig, IntV[] res) {
        RedStack_Vf.RedStack[] stacks = new RedStack_Vf.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Vf(orig, res, stacks);
    }
    public static Procedure redStack0(IntEv orig, IntE[] res) {
        RedStack_Ev.RedStack[] stacks = new RedStack_Ev.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ev(orig, res, stacks);
    }
    public static Procedure redStack1(IntEv orig, IntE[] res) {
        RedStack_Ev.RedStack[] stacks = new RedStack_Ev.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ev(orig, res, stacks);
    }
    public static Procedure redStackMin(IntEv orig, IntE[] res) {
        RedStack_Ev.RedStack[] stacks = new RedStack_Ev.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ev(orig, res, stacks);
    }
    public static Procedure redStackMax(IntEv orig, IntE[] res) {
        RedStack_Ev.RedStack[] stacks = new RedStack_Ev.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ev(orig, res, stacks);
    }
    public static Procedure redStack0(IntEf orig, IntE[] res) {
        RedStack_Ef.RedStack[] stacks = new RedStack_Ef.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ef(orig, res, stacks);
    }
    public static Procedure redStack1(IntEf orig, IntE[] res) {
        RedStack_Ef.RedStack[] stacks = new RedStack_Ef.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ef(orig, res, stacks);
    }
    public static Procedure redStackMin(IntEf orig, IntE[] res) {
        RedStack_Ef.RedStack[] stacks = new RedStack_Ef.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Ef(orig, res, stacks);
    }
    public static Procedure redStackMax(IntEf orig, IntE[] res) {
        RedStack_Ef.RedStack[] stacks = new RedStack_Ef.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Ef(orig, res, stacks);
    }
    public static Procedure redStack0(IntFv orig, IntF[] res) {
        RedStack_Fv.RedStack[] stacks = new RedStack_Fv.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Fv(orig, res, stacks);
    }
    public static Procedure redStack1(IntFv orig, IntF[] res) {
        RedStack_Fv.RedStack[] stacks = new RedStack_Fv.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Fv(orig, res, stacks);
    }
    public static Procedure redStackMin(IntFv orig, IntF[] res) {
        RedStack_Fv.RedStack[] stacks = new RedStack_Fv.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Fv(orig, res, stacks);
    }
    public static Procedure redStackMax(IntFv orig, IntF[] res) {
        RedStack_Fv.RedStack[] stacks = new RedStack_Fv.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Fv(orig, res, stacks);
    }
    public static Procedure redStack0(IntFe orig, IntF[] res) {
        RedStack_Fe.RedStack[] stacks = new RedStack_Fe.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Fe(orig, res, stacks);
    }
    public static Procedure redStack1(IntFe orig, IntF[] res) {
        RedStack_Fe.RedStack[] stacks = new RedStack_Fe.RedStack[orig.n+1];
        for (int i = 0; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Fe(orig, res, stacks);
    }
    public static Procedure redStackMin(IntFe orig, IntF[] res) {
        RedStack_Fe.RedStack[] stacks = new RedStack_Fe.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack1;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack0;
        return new RedStack_Fe(orig, res, stacks);
    }
    public static Procedure redStackMax(IntFe orig, IntF[] res) {
        RedStack_Fe.RedStack[] stacks = new RedStack_Fe.RedStack[orig.n+1];
        stacks[0] = BoolOp::redStack0;
        for (int i = 1; i <= orig.n; i++) stacks[i] = BoolOp::redStack1;
        return new RedStack_Fe(orig, res, stacks);
    }

    // REDUCE MIN/MAX operations
    public static Procedure redMin(IntVe orig, IntV res) { return new RedMinVe(orig, res); }
    public static Procedure redMax(IntVe orig, IntV res) { return new RedMaxVe(orig, res); }
    public static Procedure redMin(IntVf orig, IntV res) { return new RedMinVf(orig, res); }
    public static Procedure redMax(IntVf orig, IntV res) { return new RedMaxVf(orig, res); }
    public static Procedure redMin(IntEv orig, IntE res) { return new RedMinEv(orig, res); }
    public static Procedure redMax(IntEv orig, IntE res) { return new RedMaxEv(orig, res); }
    public static Procedure redMin(IntEf orig, IntE res) { return new RedMinEf(orig, res); }
    public static Procedure redMax(IntEf orig, IntE res) { return new RedMaxEf(orig, res); }
    public static Procedure redMin(IntFv orig, IntF res) { return new RedMinFv(orig, res); }
    public static Procedure redMax(IntFv orig, IntF res) { return new RedMaxFv(orig, res); }
    public static Procedure redMin(IntFe orig, IntF res) { return new RedMinFe(orig, res); }
    public static Procedure redMax(IntFe orig, IntF res) { return new RedMaxFe(orig, res); }

    // REDUCE ADD operations
    public static Procedure redAdd(BoolVe boolVe, IntV intV) { return new RedAddVe(boolVe, intV); }
    public static Procedure redAdd(BoolVf boolVf, IntV intV) { return new RedAddVf(boolVf, intV); }
    public static Procedure redAdd(BoolEv boolEv, IntE intE) { return new RedAddEv(boolEv, intE); }
    public static Procedure redAdd(BoolEf boolEf, IntE intE) { return new RedAddEf(boolEf, intE); }
    public static Procedure redAdd(BoolFv boolFv, IntF intF) { return new RedAddFv(boolFv, intF); }
    public static Procedure redAdd(BoolFe boolFe, IntF intF) { return new RedAddFe(boolFe, intF); }

    public static Procedure redAdd(IntVe intVe, IntV intV) { return new RedAddVe(intVe, intV); }
    public static Procedure redAdd(IntVf intVf, IntV intV) { return new RedAddVf(intVf, intV); }
    public static Procedure redAdd(IntEv intEv, IntE intE) { return new RedAddEv(intEv, intE); }
    public static Procedure redAdd(IntEf intEf, IntE intE) { return new RedAddEf(intEf, intE); }
    public static Procedure redAdd(IntFv intFv, IntF intF) { return new RedAddFv(intFv, intF); }
    public static Procedure redAdd(IntFe intFe, IntF intF) { return new RedAddFe(intFe, intF); }

    // FROM BOOL operations
    public static BasicInstruction fromBool(BoolV  boolV,  IntV  intV ) { return new BoolToIntV (boolV,  intV ); }
    public static BasicInstruction fromBool(BoolVe boolVe, IntVe intVe) { return new BoolToIntVe(boolVe, intVe); }
    public static BasicInstruction fromBool(BoolVf boolVf, IntVf intVf) { return new BoolToIntVf(boolVf, intVf); }
    public static BasicInstruction fromBool(BoolE  boolE,  IntE  intE ) { return new BoolToIntE (boolE,  intE ); }
    public static BasicInstruction fromBool(BoolEv boolEv, IntEv intEv) { return new BoolToIntEv(boolEv, intEv); }
    public static BasicInstruction fromBool(BoolEf boolEf, IntEf intEf) { return new BoolToIntEf(boolEf, intEf); }
    public static BasicInstruction fromBool(BoolF  boolF,  IntF  intF ) { return new BoolToIntF (boolF,  intF ); }
    public static BasicInstruction fromBool(BoolFv boolFv, IntFv intFv) { return new BoolToIntFv(boolFv, intFv); }
    public static BasicInstruction fromBool(BoolFe boolFe, IntFe intFe) { return new BoolToIntFe(boolFe, intFe); }

    // SCAN operations
    public static Procedure scanLeft (IntV  orig,  BoolV  res, ScanLeftV.Scan   scan) { return new ScanLeftV  (orig, res, scan); }
    public static Procedure scanRight(IntV  orig,  BoolV  res, ScanRightV.Scan  scan) { return new ScanRightV (orig, res, scan); }
    public static Procedure scanLeft (IntVe orig,  BoolVe res, ScanLeftVe.Scan  scan) { return new ScanLeftVe (orig, res, scan); }
    public static Procedure scanRight(IntVe orig,  BoolVe res, ScanRightVe.Scan scan) { return new ScanRightVe(orig, res, scan); }
    public static Procedure scanLeft (IntVf orig,  BoolVf res, ScanLeftVf.Scan  scan) { return new ScanLeftVf (orig, res, scan); }
    public static Procedure scanRight(IntVf orig,  BoolVf res, ScanRightVf.Scan scan) { return new ScanRightVf(orig, res, scan); }
    public static Procedure scanLeft (IntE  orig,  BoolE  res, ScanLeftE.Scan   scan) { return new ScanLeftE  (orig, res, scan); }
    public static Procedure scanRight(IntE  orig,  BoolE  res, ScanRightE.Scan  scan) { return new ScanRightE (orig, res, scan); }
    public static Procedure scanLeft (IntEv orig,  BoolEv res, ScanLeftEv.Scan  scan) { return new ScanLeftEv (orig, res, scan); }
    public static Procedure scanRight(IntEv orig,  BoolEv res, ScanRightEv.Scan scan) { return new ScanRightEv(orig, res, scan); }
    public static Procedure scanLeft (IntEf orig,  BoolEf res, ScanLeftEf.Scan  scan) { return new ScanLeftEf (orig, res, scan); }
    public static Procedure scanRight(IntEf orig,  BoolEf res, ScanRightEf.Scan scan) { return new ScanRightEf(orig, res, scan); }
    public static Procedure scanLeft (IntF  orig,  BoolF  res, ScanLeftF.Scan   scan) { return new ScanLeftF  (orig, res, scan); }
    public static Procedure scanRight(IntF  orig,  BoolF  res, ScanRightF.Scan  scan) { return new ScanRightF (orig, res, scan); }
    public static Procedure scanLeft (IntFv orig,  BoolFv res, ScanLeftFv.Scan  scan) { return new ScanLeftFv (orig, res, scan); }
    public static Procedure scanRight(IntFv orig,  BoolFv res, ScanRightFv.Scan scan) { return new ScanRightFv(orig, res, scan); }
    public static Procedure scanLeft (IntFe orig,  BoolFe res, ScanLeftFe.Scan  scan) { return new ScanLeftFe (orig, res, scan); }
    public static Procedure scanRight(IntFe orig,  BoolFe res, ScanRightFe.Scan scan) { return new ScanRightFe(orig, res, scan); }

}
