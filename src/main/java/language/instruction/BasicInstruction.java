package language.instruction;

import language.field.Field;

/** Represents an instruction that performs one field operation in a single step. */
public non-sealed abstract class BasicInstruction<T extends Field<T>> extends Instruction<T> {
    public int leafCount() { return 1; }
    /** @return true, always. */
    public abstract boolean execute();

    @Override
    protected void returnValue(T value) {
        if (__ret == null) __ret = value;
        else value.set(__ret);
    }
}
