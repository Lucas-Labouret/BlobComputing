package language.instruction.instructionSet.boolOp;

import language.field.boolField.*;
import language.instruction.BasicInstruction;
import language.instruction.Procedure;

public class BoolOp {
    public static BasicInstruction not(BoolV  a, BoolV  res) { return new NotV (a, res); }
    public static BasicInstruction not(BoolVe a, BoolVe res) { return new NotVe(a, res); }
    public static BasicInstruction not(BoolVf a, BoolVf res) { return new NotVf(a, res); }
    public static BasicInstruction not(BoolE  a, BoolE  res) { return new NotE (a, res); }
    public static BasicInstruction not(BoolEv a, BoolEv res) { return new NotEv(a, res); }
    public static BasicInstruction not(BoolEf a, BoolEf res) { return new NotEf(a, res); }
    public static BasicInstruction not(BoolF  a, BoolF  res) { return new NotF (a, res); }
    public static BasicInstruction not(BoolFv a, BoolFv res) { return new NotFv(a, res); }
    public static BasicInstruction not(BoolFe a, BoolFe res) { return new NotFe(a, res); }

    public static BasicInstruction and(BoolV  a, BoolV  b, BoolV  res) { return new AndV (a, b, res); }
    public static BasicInstruction and(BoolVe a, BoolVe b, BoolVe res) { return new AndVe(a, b, res); }
    public static BasicInstruction and(BoolVf a, BoolVf b, BoolVf res) { return new AndVf(a, b, res); }
    public static BasicInstruction and(BoolE  a, BoolE  b, BoolE  res) { return new AndE (a, b, res); }
    public static BasicInstruction and(BoolEv a, BoolEv b, BoolEv res) { return new AndEv(a, b, res); }
    public static BasicInstruction and(BoolEf a, BoolEf b, BoolEf res) { return new AndEf(a, b, res); }
    public static BasicInstruction and(BoolF  a, BoolF  b, BoolF  res) { return new AndF (a, b, res); }
    public static BasicInstruction and(BoolFv a, BoolFv b, BoolFv res) { return new AndFv(a, b, res); }
    public static BasicInstruction and(BoolFe a, BoolFe b, BoolFe res) { return new AndFe(a, b, res); }

    public static BasicInstruction or(BoolV  a, BoolV  b, BoolV  res) { return new OrV (a, b, res); }
    public static BasicInstruction or(BoolVe a, BoolVe b, BoolVe res) { return new OrVe(a, b, res); }
    public static BasicInstruction or(BoolVf a, BoolVf b, BoolVf res) { return new OrVf(a, b, res); }
    public static BasicInstruction or(BoolE  a, BoolE  b, BoolE  res) { return new OrE (a, b, res); }
    public static BasicInstruction or(BoolEv a, BoolEv b, BoolEv res) { return new OrEv(a, b, res); }
    public static BasicInstruction or(BoolEf a, BoolEf b, BoolEf res) { return new OrEf(a, b, res); }
    public static BasicInstruction or(BoolF  a, BoolF  b, BoolF  res) { return new OrF (a, b, res); }
    public static BasicInstruction or(BoolFv a, BoolFv b, BoolFv res) { return new OrFv(a, b, res); }
    public static BasicInstruction or(BoolFe a, BoolFe b, BoolFe res) { return new OrFe(a, b, res); }

    public static BasicInstruction xor(BoolV  a, BoolV  b, BoolV  res) { return new XorV (a, b, res); }
    public static BasicInstruction xor(BoolVe a, BoolVe b, BoolVe res) { return new XorVe(a, b, res); }
    public static BasicInstruction xor(BoolVf a, BoolVf b, BoolVf res) { return new XorVf(a, b, res); }
    public static BasicInstruction xor(BoolE  a, BoolE  b, BoolE  res) { return new XorE (a, b, res); }
    public static BasicInstruction xor(BoolEv a, BoolEv b, BoolEv res) { return new XorEv(a, b, res); }
    public static BasicInstruction xor(BoolEf a, BoolEf b, BoolEf res) { return new XorEf(a, b, res); }
    public static BasicInstruction xor(BoolF  a, BoolF  b, BoolF  res) { return new XorF (a, b, res); }
    public static BasicInstruction xor(BoolFv a, BoolFv b, BoolFv res) { return new XorFv(a, b, res); }
    public static BasicInstruction xor(BoolFe a, BoolFe b, BoolFe res) { return new XorFe(a, b, res); }

    public static Procedure fif(BoolV  cond, BoolV  t, BoolV  f, BoolV  res) { return new IfV (cond, t, f, res); }
    public static Procedure fif(BoolVe cond, BoolVe t, BoolVe f, BoolVe res) { return new IfVe(cond, t, f, res); }
    public static Procedure fif(BoolVf cond, BoolVf t, BoolVf f, BoolVf res) { return new IfVf(cond, t, f, res); }
    public static Procedure fif(BoolE  cond, BoolE  t, BoolE  f, BoolE  res) { return new IfE (cond, t, f, res); }
    public static Procedure fif(BoolEv cond, BoolEv t, BoolEv f, BoolEv res) { return new IfEv(cond, t, f, res); }
    public static Procedure fif(BoolEf cond, BoolEf t, BoolEf f, BoolEf res) { return new IfEf(cond, t, f, res); }
    public static Procedure fif(BoolF  cond, BoolF  t, BoolF  f, BoolF  res) { return new IfF (cond, t, f, res); }
    public static Procedure fif(BoolFv cond, BoolFv t, BoolFv f, BoolFv res) { return new IfFv(cond, t, f, res); }
    public static Procedure fif(BoolFe cond, BoolFe t, BoolFe f, BoolFe res) { return new IfFe(cond, t, f, res); }

    public static BasicInstruction broadcast(BoolV boolV, BoolVe boolVe) { return new BroadcastVe(boolV, boolVe); }
    public static BasicInstruction broadcast(BoolV boolV, BoolVf boolVf) { return new BroadcastVf(boolV, boolVf); }
    public static BasicInstruction broadcast(BoolE boolE, BoolEv boolEv) { return new BroadcastEv(boolE, boolEv); }
    public static BasicInstruction broadcast(BoolE boolE, BoolEf boolEf) { return new BroadcastEf(boolE, boolEf); }
    public static BasicInstruction broadcast(BoolF boolF, BoolFv boolFv) { return new BroadcastFv(boolF, boolFv); }
    public static BasicInstruction broadcast(BoolF boolF, BoolFe boolFe) { return new BroadcastFe(boolF, boolFe); }

    public static BasicInstruction transfer(BoolVe boolVe, BoolEv boolEv) { return new TransferVe(boolVe, boolEv); }
    public static BasicInstruction transfer(BoolVf boolVf, BoolFv boolFv) { return new TransferVf(boolVf, boolFv); }
    public static BasicInstruction transfer(BoolEv boolEv, BoolVe boolVe) { return new TransferEv(boolEv, boolVe); }
    public static BasicInstruction transfer(BoolEf boolEf, BoolFe boolFe) { return new TransferEf(boolEf, boolFe); }
    public static BasicInstruction transfer(BoolFv boolFv, BoolVf boolVf) { return new TransferFv(boolFv, boolVf); }
    public static BasicInstruction transfer(BoolFe boolFe, BoolEf boolEf) { return new TransferFe(boolFe, boolEf); }

    public static BasicInstruction redAnd(BoolVe boolVe, BoolV boolV) { return new RedAndVe(boolVe, boolV); }
    public static BasicInstruction redAnd(BoolVf boolVf, BoolV boolV) { return new RedAndVf(boolVf, boolV); }
    public static BasicInstruction redAnd(BoolEv boolEv, BoolE boolE) { return new RedAndEv(boolEv, boolE); }
    public static BasicInstruction redAnd(BoolEf boolEf, BoolE boolE) { return new RedAndEf(boolEf, boolE); }
    public static BasicInstruction redAnd(BoolFv boolFv, BoolF boolF) { return new RedAndFv(boolFv, boolF); }
    public static BasicInstruction redAnd(BoolFe boolFe, BoolF boolF) { return new RedAndFe(boolFe, boolF); }

    public static BasicInstruction redOr(BoolVe boolVe, BoolV boolV) { return new RedOrVe(boolVe, boolV); }
    public static BasicInstruction redOr(BoolVf boolVf, BoolV boolV) { return new RedOrVf(boolVf, boolV); }
    public static BasicInstruction redOr(BoolEv boolEv, BoolE boolE) { return new RedOrEv(boolEv, boolE); }
    public static BasicInstruction redOr(BoolEf boolEf, BoolE boolE) { return new RedOrEf(boolEf, boolE); }
    public static BasicInstruction redOr(BoolFv boolFv, BoolF boolF) { return new RedOrFv(boolFv, boolF); }
    public static BasicInstruction redOr(BoolFe boolFe, BoolF boolF) { return new RedOrFe(boolFe, boolF); }

    public static BasicInstruction redXor(BoolVe boolVe, BoolV boolV) { return new RedXorVe(boolVe, boolV); }
    public static BasicInstruction redXor(BoolVf boolVf, BoolV boolV) { return new RedXorVf(boolVf, boolV); }
    public static BasicInstruction redXor(BoolEv boolEv, BoolE boolE) { return new RedXorEv(boolEv, boolE); }
    public static BasicInstruction redXor(BoolEf boolEf, BoolE boolE) { return new RedXorEf(boolEf, boolE); }
    public static BasicInstruction redXor(BoolFv boolFv, BoolF boolF) { return new RedXorFv(boolFv, boolF); }
    public static BasicInstruction redXor(BoolFe boolFe, BoolF boolF) { return new RedXorFe(boolFe, boolF); }

    public static BasicInstruction redStack0(BoolVe boolVe, BoolV[] boolV) { return new RedStackVe0(boolVe, boolV); }
    public static BasicInstruction redStack1(BoolVe boolVe, BoolV[] boolV) { return new RedStackVe1(boolVe, boolV); }
    public static BasicInstruction redStack0(BoolVf boolVf, BoolV[] boolV) { return new RedStackVf0(boolVf, boolV); }
    public static BasicInstruction redStack1(BoolVf boolVf, BoolV[] boolV) { return new RedStackVf1(boolVf, boolV); }
    public static BasicInstruction redStack0(BoolEv boolEv, BoolE[] boolE) { return new RedStackEv0(boolEv, boolE); }
    public static BasicInstruction redStack1(BoolEv boolEv, BoolE[] boolE) { return new RedStackEv1(boolEv, boolE); }
    public static BasicInstruction redStack0(BoolEf boolEf, BoolE[] boolE) { return new RedStackEf0(boolEf, boolE); }
    public static BasicInstruction redStack1(BoolEf boolEf, BoolE[] boolE) { return new RedStackEf1(boolEf, boolE); }
    public static BasicInstruction redStack0(BoolFv boolFv, BoolF[] boolF) { return new RedStackFv0(boolFv, boolF); }
    public static BasicInstruction redStack1(BoolFv boolFv, BoolF[] boolF) { return new RedStackFv1(boolFv, boolF); }
    public static BasicInstruction redStack0(BoolFe boolFe, BoolF[] boolF) { return new RedStackFe0(boolFe, boolF); }
    public static BasicInstruction redStack1(BoolFe boolFe, BoolF[] boolF) { return new RedStackFe1(boolFe, boolF); }

    public static BasicInstruction rotCW(BoolVe boolVe, BoolVf boolVf) { return new RotVeCW(boolVe, boolVf); }
    public static BasicInstruction rotCW(BoolVf boolVf, BoolVe boolVe) { return new RotVfCW(boolVf, boolVe); }
    public static BasicInstruction rotCW(BoolEv boolEv, BoolEf boolEf) { return new RotEvCW(boolEv, boolEf); }
    public static BasicInstruction rotCW(BoolEf boolEf, BoolEv boolEv) { return new RotEfCW(boolEf, boolEv); }
    public static BasicInstruction rotCW(BoolFv boolFv, BoolFe boolFe) { return new RotFvCW(boolFv, boolFe); }
    public static BasicInstruction rotCW(BoolFe boolFe, BoolFv boolFv) { return new RotFeCW(boolFe, boolFv); }

    public static BasicInstruction rotCCW(BoolVe boolVe, BoolVf boolVf) { return new RotVeCCW(boolVe, boolVf); }
    public static BasicInstruction rotCCW(BoolVf boolVf, BoolVe boolVe) { return new RotVfCCW(boolVf, boolVe); }
    public static BasicInstruction rotCCW(BoolEv boolEv, BoolEf boolEf) { return new RotEvCCW(boolEv, boolEf); }
    public static BasicInstruction rotCCW(BoolEf boolEf, BoolEv boolEv) { return new RotEfCCW(boolEf, boolEv); }
    public static BasicInstruction rotCCW(BoolFv boolFv, BoolFe boolFe) { return new RotFvCCW(boolFv, boolFe); }
    public static BasicInstruction rotCCW(BoolFe boolFe, BoolFv boolFv) { return new RotFeCCW(boolFe, boolFv); }
}
