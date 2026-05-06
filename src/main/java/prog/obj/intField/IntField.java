package prog.obj.intField;

import field.boolField.BoolField;
import language.Obj;
import language.Procedure;
import language.Ref;
import language.basicInstruction.*;
import language.basicInstruction.bitOp.BitOp;
import language.fieldRef.*;
import prog.ref.intField.*;

public abstract class IntField<F extends BoolField> extends Obj {
    public final int n;
    protected final Ref<F>[] bits;

    /** @param n number of bits of this IntV, excluding the sign bit */
    protected IntField(int n, Ref<F>[] bits) {
        if (n < 1) throw new IllegalArgumentException("IntV must have at least 1 bit.");
        this.n = n;
        this.bits = bits;
    }

    public abstract Ref<F>[] getBits();
    
    // NOT operations
    public static Procedure not(IntVRef  a, IntVRef  res) { return new IntNotV (a, res); }
    public static Procedure not(IntVeRef a, IntVeRef res) { return new IntNotVe(a, res); }
    public static Procedure not(IntVfRef a, IntVfRef res) { return new IntNotVf(a, res); }
    public static Procedure not(IntERef  a, IntERef  res) { return new IntNotE (a, res); }
    public static Procedure not(IntEvRef a, IntEvRef res) { return new IntNotEv(a, res); }
    public static Procedure not(IntEfRef a, IntEfRef res) { return new IntNotEf(a, res); }
    public static Procedure not(IntFRef  a, IntFRef  res) { return new IntNotF (a, res); }
    public static Procedure not(IntFvRef a, IntFvRef res) { return new IntNotFv(a, res); }
    public static Procedure not(IntFeRef a, IntFeRef res) { return new IntNotFe(a, res); }

    // NEG operations
    public static Procedure neg(IntVRef  a, IntVRef  res) { return new IntNegV (a, res); }
    public static Procedure neg(IntVeRef a, IntVeRef res) { return new IntNegVe(a, res); }
    public static Procedure neg(IntVfRef a, IntVfRef res) { return new IntNegVf(a, res); }
    public static Procedure neg(IntERef  a, IntERef  res) { return new IntNegE (a, res); }
    public static Procedure neg(IntEvRef a, IntEvRef res) { return new IntNegEv(a, res); }
    public static Procedure neg(IntEfRef a, IntEfRef res) { return new IntNegEf(a, res); }
    public static Procedure neg(IntFRef  a, IntFRef  res) { return new IntNegF (a, res); }
    public static Procedure neg(IntFvRef a, IntFvRef res) { return new IntNegFv(a, res); }
    public static Procedure neg(IntFeRef a, IntFeRef res) { return new IntNegFe(a, res); }

    // AND operations
    public static Procedure and(IntVRef  a, IntVRef  b, IntVRef  res) { return new IntAndV (a, b, res); }
    public static Procedure and(IntVeRef a, IntVeRef b, IntVeRef res) { return new IntAndVe(a, b, res); }
    public static Procedure and(IntVfRef a, IntVfRef b, IntVfRef res) { return new IntAndVf(a, b, res); }
    public static Procedure and(IntERef  a, IntERef  b, IntERef  res) { return new IntAndE (a, b, res); }
    public static Procedure and(IntEvRef a, IntEvRef b, IntEvRef res) { return new IntAndEv(a, b, res); }
    public static Procedure and(IntEfRef a, IntEfRef b, IntEfRef res) { return new IntAndEf(a, b, res); }
    public static Procedure and(IntFRef  a, IntFRef  b, IntFRef  res) { return new IntAndF (a, b, res); }
    public static Procedure and(IntFvRef a, IntFvRef b, IntFvRef res) { return new IntAndFv(a, b, res); }
    public static Procedure and(IntFeRef a, IntFeRef b, IntFeRef res) { return new IntAndFe(a, b, res); }

    // OR operations
    public static Procedure or(IntVRef  a, IntVRef  b, IntVRef  res) { return new IntOrV (a, b, res); }
    public static Procedure or(IntVeRef a, IntVeRef b, IntVeRef res) { return new IntOrVe(a, b, res); }
    public static Procedure or(IntVfRef a, IntVfRef b, IntVfRef res) { return new IntOrVf(a, b, res); }
    public static Procedure or(IntERef  a, IntERef  b, IntERef  res) { return new IntOrE (a, b, res); }
    public static Procedure or(IntEvRef a, IntEvRef b, IntEvRef res) { return new IntOrEv(a, b, res); }
    public static Procedure or(IntEfRef a, IntEfRef b, IntEfRef res) { return new IntOrEf(a, b, res); }
    public static Procedure or(IntFRef  a, IntFRef  b, IntFRef  res) { return new IntOrF (a, b, res); }
    public static Procedure or(IntFvRef a, IntFvRef b, IntFvRef res) { return new IntOrFv(a, b, res); }
    public static Procedure or(IntFeRef a, IntFeRef b, IntFeRef res) { return new IntOrFe(a, b, res); }

    // XOR operations
    public static Procedure xor(IntVRef  a, IntVRef  b, IntVRef  res) { return new IntXorV (a, b, res); }
    public static Procedure xor(IntVeRef a, IntVeRef b, IntVeRef res) { return new IntXorVe(a, b, res); }
    public static Procedure xor(IntVfRef a, IntVfRef b, IntVfRef res) { return new IntXorVf(a, b, res); }
    public static Procedure xor(IntERef  a, IntERef  b, IntERef  res) { return new IntXorE (a, b, res); }
    public static Procedure xor(IntEvRef a, IntEvRef b, IntEvRef res) { return new IntXorEv(a, b, res); }
    public static Procedure xor(IntEfRef a, IntEfRef b, IntEfRef res) { return new IntXorEf(a, b, res); }
    public static Procedure xor(IntFRef  a, IntFRef  b, IntFRef  res) { return new IntXorF (a, b, res); }
    public static Procedure xor(IntFvRef a, IntFvRef b, IntFvRef res) { return new IntXorFv(a, b, res); }
    public static Procedure xor(IntFeRef a, IntFeRef b, IntFeRef res) { return new IntXorFe(a, b, res); }

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
    public static Procedure add(IntVRef  a, IntVRef  b, IntVRef  res) { return new IntAddV (a, b, res); }
    public static Procedure add(IntVeRef a, IntVeRef b, IntVeRef res) { return new IntAddVe(a, b, res); }
    public static Procedure add(IntVfRef a, IntVfRef b, IntVfRef res) { return new IntAddVf(a, b, res); }
    public static Procedure add(IntERef  a, IntERef  b, IntERef  res) { return new IntAddE (a, b, res); }
    public static Procedure add(IntEvRef a, IntEvRef b, IntEvRef res) { return new IntAddEv(a, b, res); }
    public static Procedure add(IntEfRef a, IntEfRef b, IntEfRef res) { return new IntAddEf(a, b, res); }
    public static Procedure add(IntFRef  a, IntFRef  b, IntFRef  res) { return new IntAddF (a, b, res); }
    public static Procedure add(IntFvRef a, IntFvRef b, IntFvRef res) { return new IntAddFv(a, b, res); }
    public static Procedure add(IntFeRef a, IntFeRef b, IntFeRef res) { return new IntAddFe(a, b, res); }

    // GT / greater-than operations (a > b)
    public static Procedure gt(IntVRef  a, IntVRef  b, BoolVRef  res) { return new GTV (a, b, res); }
    public static Procedure gt(IntVeRef a, IntVeRef b, BoolVeRef res) { return new GTVe(a, b, res); }
    public static Procedure gt(IntVfRef a, IntVfRef b, BoolVfRef res) { return new GTVf(a, b, res); }
    public static Procedure gt(IntERef  a, IntERef  b, BoolERef  res) { return new GTE (a, b, res); }
    public static Procedure gt(IntEvRef a, IntEvRef b, BoolEvRef res) { return new GTEv(a, b, res); }
    public static Procedure gt(IntEfRef a, IntEfRef b, BoolEfRef res) { return new GTEf(a, b, res); }
    public static Procedure gt(IntFRef  a, IntFRef  b, BoolFRef  res) { return new GTF (a, b, res); }
    public static Procedure gt(IntFvRef a, IntFvRef b, BoolFvRef res) { return new GTFv(a, b, res); }
    public static Procedure gt(IntFeRef a, IntFeRef b, BoolFeRef res) { return new GTFe(a, b, res); }

    // SUB operations
    public static Procedure sub(IntVRef  a, IntVRef  b, IntVRef  res) { return new IntSubV (a, b, res); }
    public static Procedure sub(IntVeRef a, IntVeRef b, IntVeRef res) { return new IntSubVe(a, b, res); }
    public static Procedure sub(IntVfRef a, IntVfRef b, IntVfRef res) { return new IntSubVf(a, b, res); }
    public static Procedure sub(IntERef  a, IntERef  b, IntERef  res) { return new IntSubE (a, b, res); }
    public static Procedure sub(IntEvRef a, IntEvRef b, IntEvRef res) { return new IntSubEv(a, b, res); }
    public static Procedure sub(IntEfRef a, IntEfRef b, IntEfRef res) { return new IntSubEf(a, b, res); }
    public static Procedure sub(IntFRef  a, IntFRef  b, IntFRef  res) { return new IntSubF (a, b, res); }
    public static Procedure sub(IntFvRef a, IntFvRef b, IntFvRef res) { return new IntSubFv(a, b, res); }
    public static Procedure sub(IntFeRef a, IntFeRef b, IntFeRef res) { return new IntSubFe(a, b, res); }

    // REDUCE ADD operations
    public static Procedure redAdd(BoolVeRef boolVe, IntVRef intV) { return new IntRedAddVe(boolVe, intV); }
    public static Procedure redAdd(BoolVfRef boolVf, IntVRef intV) { return new IntRedAddVf(boolVf, intV); }
    public static Procedure redAdd(BoolEvRef boolEv, IntERef intE) { return new IntRedAddEv(boolEv, intE); }
    public static Procedure redAdd(BoolEfRef boolEf, IntERef intE) { return new IntRedAddEf(boolEf, intE); }
    public static Procedure redAdd(BoolFvRef boolFv, IntFRef intF) { return new IntRedAddFv(boolFv, intF); }
    public static Procedure redAdd(BoolFeRef boolFe, IntFRef intF) { return new IntRedAddFe(boolFe, intF); }

    // FROM BOOL operations
    public static BasicInstruction fromBool(BoolVRef  boolV,  IntVRef  intV ) { return IntV .of(boolV,  intV ); }
    public static BasicInstruction fromBool(BoolVeRef boolVe, IntVeRef intVe) { return IntVe.of(boolVe, intVe); }
    public static BasicInstruction fromBool(BoolVfRef boolVf, IntVfRef intVf) { return IntVf.of(boolVf, intVf); }
    public static BasicInstruction fromBool(BoolERef  boolE,  IntERef  intE ) { return IntE .of(boolE,  intE ); }
    public static BasicInstruction fromBool(BoolEvRef boolEv, IntEvRef intEv) { return IntEv.of(boolEv, intEv); }
    public static BasicInstruction fromBool(BoolEfRef boolEf, IntEfRef intEf) { return IntEf.of(boolEf, intEf); }
    public static BasicInstruction fromBool(BoolFRef  boolF,  IntFRef  intF ) { return IntF .of(boolF,  intF ); }
    public static BasicInstruction fromBool(BoolFvRef boolFv, IntFvRef intFv) { return IntFv.of(boolFv, intFv); }
    public static BasicInstruction fromBool(BoolFeRef boolFe, IntFeRef intFe) { return IntFe.of(boolFe, intFe); }

    // SPLIT operations
    public static Procedure split(IntVRef  intV,  BoolVRef[]  boolV) { return new SplitV (intV,  boolV); }

    @Override
    public abstract IntField<F> copy();
}
