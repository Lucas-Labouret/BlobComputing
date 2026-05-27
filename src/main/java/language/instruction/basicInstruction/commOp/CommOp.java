package language.instruction.basicInstruction.commOp;

import language.instruction.basicInstruction.BasicInstruction;
import language.ref.field.boolField.fieldT.*;
import language.ref.field.boolField.fieldS.*;

public class CommOp {
    public static BasicInstruction broadcast(BoolVRef boolV, BoolVeRef boolVe) { return new BroadcastVe(boolV, boolVe); }
    public static BasicInstruction broadcast(BoolVRef boolV, BoolVfRef boolVf) { return new BroadcastVf(boolV, boolVf); }
    public static BasicInstruction broadcast(BoolERef boolE, BoolEvRef boolEv) { return new BroadcastEv(boolE, boolEv); }
    public static BasicInstruction broadcast(BoolERef boolE, BoolEfRef boolEf) { return new BroadcastEf(boolE, boolEf); }
    public static BasicInstruction broadcast(BoolFRef boolF, BoolFvRef boolFv) { return new BroadcastFv(boolF, boolFv); }
    public static BasicInstruction broadcast(BoolFRef boolF, BoolFeRef boolFe) { return new BroadcastFe(boolF, boolFe); }

    public static BasicInstruction transfer(BoolVeRef boolVe, BoolEvRef boolEv) { return new TransferVe(boolVe, boolEv); }
    public static BasicInstruction transfer(BoolVfRef boolVf, BoolFvRef boolFv) { return new TransferVf(boolVf, boolFv); }
    public static BasicInstruction transfer(BoolEvRef boolEv, BoolVeRef boolVe) { return new TransferEv(boolEv, boolVe); }
    public static BasicInstruction transfer(BoolEfRef boolEf, BoolFeRef boolFe) { return new TransferEf(boolEf, boolFe); }
    public static BasicInstruction transfer(BoolFvRef boolFv, BoolVfRef boolVf) { return new TransferFv(boolFv, boolVf); }
    public static BasicInstruction transfer(BoolFeRef boolFe, BoolEfRef boolEf) { return new TransferFe(boolFe, boolEf); }

    public static BasicInstruction redAnd(BoolVeRef boolVe, BoolVRef boolV) { return new RedAndVe(boolVe, boolV); }
    public static BasicInstruction redAnd(BoolVfRef boolVf, BoolVRef boolV) { return new RedAndVf(boolVf, boolV); }
    public static BasicInstruction redAnd(BoolEvRef boolEv, BoolERef boolE) { return new RedAndEv(boolEv, boolE); }
    public static BasicInstruction redAnd(BoolEfRef boolEf, BoolERef boolE) { return new RedAndEf(boolEf, boolE); }
    public static BasicInstruction redAnd(BoolFvRef boolFv, BoolFRef boolF) { return new RedAndFv(boolFv, boolF); }
    public static BasicInstruction redAnd(BoolFeRef boolFe, BoolFRef boolF) { return new RedAndFe(boolFe, boolF); }

    public static BasicInstruction redOr(BoolVeRef boolVe, BoolVRef boolV) { return new RedOrVe(boolVe, boolV); }
    public static BasicInstruction redOr(BoolVfRef boolVf, BoolVRef boolV) { return new RedOrVf(boolVf, boolV); }
    public static BasicInstruction redOr(BoolEvRef boolEv, BoolERef boolE) { return new RedOrEv(boolEv, boolE); }
    public static BasicInstruction redOr(BoolEfRef boolEf, BoolERef boolE) { return new RedOrEf(boolEf, boolE); }
    public static BasicInstruction redOr(BoolFvRef boolFv, BoolFRef boolF) { return new RedOrFv(boolFv, boolF); }
    public static BasicInstruction redOr(BoolFeRef boolFe, BoolFRef boolF) { return new RedOrFe(boolFe, boolF); }

    public static BasicInstruction redXor(BoolVeRef boolVe, BoolVRef boolV) { return new RedXorVe(boolVe, boolV); }
    public static BasicInstruction redXor(BoolVfRef boolVf, BoolVRef boolV) { return new RedXorVf(boolVf, boolV); }
    public static BasicInstruction redXor(BoolEvRef boolEv, BoolERef boolE) { return new RedXorEv(boolEv, boolE); }
    public static BasicInstruction redXor(BoolEfRef boolEf, BoolERef boolE) { return new RedXorEf(boolEf, boolE); }
    public static BasicInstruction redXor(BoolFvRef boolFv, BoolFRef boolF) { return new RedXorFv(boolFv, boolF); }
    public static BasicInstruction redXor(BoolFeRef boolFe, BoolFRef boolF) { return new RedXorFe(boolFe, boolF); }

    public static BasicInstruction redStack0(BoolVeRef boolVe, BoolVRef[] boolV) { return new RedStackVe0(boolVe, boolV); }
    public static BasicInstruction redStack1(BoolVeRef boolVe, BoolVRef[] boolV) { return new RedStackVe1(boolVe, boolV); }
    public static BasicInstruction redStack0(BoolVfRef boolVf, BoolVRef[] boolV) { return new RedStackVf0(boolVf, boolV); }
    public static BasicInstruction redStack1(BoolVfRef boolVf, BoolVRef[] boolV) { return new RedStackVf1(boolVf, boolV); }
    public static BasicInstruction redStack0(BoolEvRef boolEv, BoolERef[] boolE) { return new RedStackEv0(boolEv, boolE); }
    public static BasicInstruction redStack1(BoolEvRef boolEv, BoolERef[] boolE) { return new RedStackEv1(boolEv, boolE); }
    public static BasicInstruction redStack0(BoolEfRef boolEf, BoolERef[] boolE) { return new RedStackEf0(boolEf, boolE); }
    public static BasicInstruction redStack1(BoolEfRef boolEf, BoolERef[] boolE) { return new RedStackEf1(boolEf, boolE); }
    public static BasicInstruction redStack0(BoolFvRef boolFv, BoolFRef[] boolF) { return new RedStackFv0(boolFv, boolF); }
    public static BasicInstruction redStack1(BoolFvRef boolFv, BoolFRef[] boolF) { return new RedStackFv1(boolFv, boolF); }
    public static BasicInstruction redStack0(BoolFeRef boolFe, BoolFRef[] boolF) { return new RedStackFe0(boolFe, boolF); }
    public static BasicInstruction redStack1(BoolFeRef boolFe, BoolFRef[] boolF) { return new RedStackFe1(boolFe, boolF); }

    public static BasicInstruction rotCW(BoolVeRef boolVe, BoolVfRef boolVf) { return new RotVeCW(boolVe, boolVf); }
    public static BasicInstruction rotCW(BoolVfRef boolVf, BoolVeRef boolVe) { return new RotVfCW(boolVf, boolVe); }
    public static BasicInstruction rotCW(BoolEvRef boolEv, BoolEfRef boolEf) { return new RotEvCW(boolEv, boolEf); }
    public static BasicInstruction rotCW(BoolEfRef boolEf, BoolEvRef boolEv) { return new RotEfCW(boolEf, boolEv); }
    public static BasicInstruction rotCW(BoolFvRef boolFv, BoolFeRef boolFe) { return new RotFvCW(boolFv, boolFe); }
    public static BasicInstruction rotCW(BoolFeRef boolFe, BoolFvRef boolFv) { return new RotFeCW(boolFe, boolFv); }

    public static BasicInstruction rotCCW(BoolVeRef boolVe, BoolVfRef boolVf) { return new RotVeCCW(boolVe, boolVf); }
    public static BasicInstruction rotCCW(BoolVfRef boolVf, BoolVeRef boolVe) { return new RotVfCCW(boolVf, boolVe); }
    public static BasicInstruction rotCCW(BoolEvRef boolEv, BoolEfRef boolEf) { return new RotEvCCW(boolEv, boolEf); }
    public static BasicInstruction rotCCW(BoolEfRef boolEf, BoolEvRef boolEv) { return new RotEfCCW(boolEf, boolEv); }
    public static BasicInstruction rotCCW(BoolFvRef boolFv, BoolFeRef boolFe) { return new RotFvCCW(boolFv, boolFe); }
    public static BasicInstruction rotCCW(BoolFeRef boolFe, BoolFvRef boolFv) { return new RotFeCCW(boolFe, boolFv); }
}
