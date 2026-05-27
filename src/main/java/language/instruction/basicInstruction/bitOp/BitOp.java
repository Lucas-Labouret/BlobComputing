package language.instruction.basicInstruction.bitOp;


import language.instruction.basicInstruction.BasicInstruction;
import language.ref.field.boolField.fieldS.*;
import language.ref.field.boolField.fieldT.*;

public class BitOp {
    public static BasicInstruction not(BoolVRef  a, BoolVRef  res) { return new NotV (a, res); }
    public static BasicInstruction not(BoolVeRef a, BoolVeRef res) { return new NotVe(a, res); }
    public static BasicInstruction not(BoolVfRef a, BoolVfRef res) { return new NotVf(a, res); }
    public static BasicInstruction not(BoolERef  a, BoolERef  res) { return new NotE (a, res); }
    public static BasicInstruction not(BoolEvRef a, BoolEvRef res) { return new NotEv(a, res); }
    public static BasicInstruction not(BoolEfRef a, BoolEfRef res) { return new NotEf(a, res); }
    public static BasicInstruction not(BoolFRef  a, BoolFRef  res) { return new NotF (a, res); }
    public static BasicInstruction not(BoolFvRef a, BoolFvRef res) { return new NotFv(a, res); }
    public static BasicInstruction not(BoolFeRef a, BoolFeRef res) { return new NotFe(a, res); }

    public static BasicInstruction and(BoolVRef  a, BoolVRef  b, BoolVRef  res) { return new AndV (a, b, res); }
    public static BasicInstruction and(BoolVeRef a, BoolVeRef b, BoolVeRef res) { return new AndVe(a, b, res); }
    public static BasicInstruction and(BoolVfRef a, BoolVfRef b, BoolVfRef res) { return new AndVf(a, b, res); }
    public static BasicInstruction and(BoolERef  a, BoolERef  b, BoolERef  res) { return new AndE (a, b, res); }
    public static BasicInstruction and(BoolEvRef a, BoolEvRef b, BoolEvRef res) { return new AndEv(a, b, res); }
    public static BasicInstruction and(BoolEfRef a, BoolEfRef b, BoolEfRef res) { return new AndEf(a, b, res); }
    public static BasicInstruction and(BoolFRef  a, BoolFRef  b, BoolFRef  res) { return new AndF (a, b, res); }
    public static BasicInstruction and(BoolFvRef a, BoolFvRef b, BoolFvRef res) { return new AndFv(a, b, res); }
    public static BasicInstruction and(BoolFeRef a, BoolFeRef b, BoolFeRef res) { return new AndFe(a, b, res); }

    public static BasicInstruction or(BoolVRef  a, BoolVRef  b, BoolVRef  res) { return new OrV (a, b, res); }
    public static BasicInstruction or(BoolVeRef a, BoolVeRef b, BoolVeRef res) { return new OrVe(a, b, res); }
    public static BasicInstruction or(BoolVfRef a, BoolVfRef b, BoolVfRef res) { return new OrVf(a, b, res); }
    public static BasicInstruction or(BoolERef  a, BoolERef  b, BoolERef  res) { return new OrE (a, b, res); }
    public static BasicInstruction or(BoolEvRef a, BoolEvRef b, BoolEvRef res) { return new OrEv(a, b, res); }
    public static BasicInstruction or(BoolEfRef a, BoolEfRef b, BoolEfRef res) { return new OrEf(a, b, res); }
    public static BasicInstruction or(BoolFRef  a, BoolFRef  b, BoolFRef  res) { return new OrF (a, b, res); }
    public static BasicInstruction or(BoolFvRef a, BoolFvRef b, BoolFvRef res) { return new OrFv(a, b, res); }
    public static BasicInstruction or(BoolFeRef a, BoolFeRef b, BoolFeRef res) { return new OrFe(a, b, res); }

    public static BasicInstruction xor(BoolVRef  a, BoolVRef  b, BoolVRef  res) { return new XorV (a, b, res); }
    public static BasicInstruction xor(BoolVeRef a, BoolVeRef b, BoolVeRef res) { return new XorVe(a, b, res); }
    public static BasicInstruction xor(BoolVfRef a, BoolVfRef b, BoolVfRef res) { return new XorVf(a, b, res); }
    public static BasicInstruction xor(BoolERef  a, BoolERef  b, BoolERef  res) { return new XorE (a, b, res); }
    public static BasicInstruction xor(BoolEvRef a, BoolEvRef b, BoolEvRef res) { return new XorEv(a, b, res); }
    public static BasicInstruction xor(BoolEfRef a, BoolEfRef b, BoolEfRef res) { return new XorEf(a, b, res); }
    public static BasicInstruction xor(BoolFRef  a, BoolFRef  b, BoolFRef  res) { return new XorF (a, b, res); }
    public static BasicInstruction xor(BoolFvRef a, BoolFvRef b, BoolFvRef res) { return new XorFv(a, b, res); }
    public static BasicInstruction xor(BoolFeRef a, BoolFeRef b, BoolFeRef res) { return new XorFe(a, b, res); }
}
