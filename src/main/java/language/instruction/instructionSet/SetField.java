package language.instruction.instructionSet;

import language.field.Field;
import language.instruction.BasicInstruction;

public class SetField<F extends Field<F>> implements BasicInstruction {
    private final F in;
    private final F out;

    public SetField(F in, F out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public boolean exec() {
        if (in == null) out.clear();
        else out.set(in);
        return true;
    }
}
