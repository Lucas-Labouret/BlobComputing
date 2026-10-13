package language.instruction;

import language.field.Field;

import java.util.HashSet;

/** Instruction are the basic building blocks of blob programs. <br>
 * They are executed one at a time, and can be either <br>
 * - BasicInstructions (which perform a single action) or <br>
 * - Procedures (which execute a sequence of instructions). */
public sealed abstract class Instruction<T extends Field<?>> permits BasicInstruction, Procedure {
    /** @return the number of basic instruction calls of the instruction */
    public abstract int leafCount();
    /**
     * Should be overridden by subclasses to implement the execution logic.
     * @return false if there are more steps to execute, true if the instruction is finished.
     */
    protected abstract boolean execute();
    /**
     * Executes the instruction and cleans up temporary variables if finished.
     * @return true if the instruction is finished, false otherwise.
     */
    public final boolean exec() {
        boolean finished = execute();
        if (finished) cleanup();
        return finished;
    }

    /** Return value of the instruction */
    protected T __ret;
    /** Sets the return value of the instruction. */
    protected abstract void returnValue(T value);
    /** @return the return value of the instruction. */
    protected T getReturn(){
        if (__ret == null) throw new IllegalStateException("Return value is not set yet.");
        return __ret;
    }

    /**
     * Creates a temporary variable that can be used within this procedure.
     * The variable will be automatically cleaned up (set to null) when this procedure finishes executing.
     * This avoids expensive long term storage of fields that are no longer useful
     */
    public <F extends Field<?>> F tmp(F field) { tmpVars.add(field); return field; }
    protected <F extends Field<?>> void retain(F field) { tmpVars.remove(field); }
    /** The set of tmp variables of this procedure. */
    private final HashSet<Field<?>> tmpVars = new HashSet<>();
    /** Cleans up the temporary variables of this procedure. */
    private void cleanup() { for (Field<?> field : tmpVars) field.clear(); }
}
