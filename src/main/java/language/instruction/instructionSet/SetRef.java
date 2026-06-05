package language.instruction.instructionSet;

import language.field.Field;
import language.fieldRef.Ref;
import language.instruction.BasicInstruction;

public class SetRef<T extends Field> implements BasicInstruction {
    private final Ref<T> in;
    private final Ref<T> out;

    public SetRef(Ref<T> in, Ref<T> out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public boolean exec() {
        out.set(in.copy().get());
        return true;
    }
}
