package language.instruction.instructionSet;

import language.field.Field;
import language.field.VoidF;
import language.instruction.BasicInstruction;

/**
 * Set the value of one field to another.
 * @param <F> The type of the field.
 */
public class SetField<F extends Field<?>> extends BasicInstruction<VoidF> {
    private final F in;
    private final F out;

    public SetField(F in, F out) {
        this.in = in;
        this.out = out;

        returnValue(new VoidF());
    }

    @Override
    public boolean execute() {
        if (in == null) out.clear();
        else out.copyFrom(in);
        return true;
    }
}
