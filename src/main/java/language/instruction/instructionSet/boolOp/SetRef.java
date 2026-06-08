package language.instruction.instructionSet.boolOp;

import language.field.boolField.BoolField;
import language.fieldRef.Ref;
import language.instruction.BasicInstruction;

class SetRef<B extends BoolField> implements BasicInstruction {
    private final Ref<B> in;
    private final Ref<B> out;

    public SetRef(Ref<B> in, Ref<B> out) {
        this.in = in;
        this.out = out;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean exec() {
        if (in.get() == null) out.set(null);
        else out.set((B) in.get().copy());
        return true;
    }
}
