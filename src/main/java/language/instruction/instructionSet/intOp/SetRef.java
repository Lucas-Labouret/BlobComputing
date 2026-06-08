package language.instruction.instructionSet.intOp;

import language.field.boolField.BoolField;
import language.field.intField.IntField;
import language.fieldRef.Ref;
import language.instruction.BasicInstruction;

class SetRef<B extends BoolField, I extends IntField<B>> implements BasicInstruction {
    private final Ref<I> in;
    private final Ref<I> out;

    public SetRef(Ref<I> in, Ref<I> out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public boolean exec() {
        if (in.get() == null || out.get() ==  null)
            throw new IllegalArgumentException("Cannot set null refs for IntFields");
        if (in.get().n != out.get().n)
            throw new IllegalArgumentException("Cannot set refs for different number of bits");

        for (int i = 0; i <= in.get().n; i++) out.get().getBits()[i].set(in.get().getBits()[i].get());

        return true;
    }
}
