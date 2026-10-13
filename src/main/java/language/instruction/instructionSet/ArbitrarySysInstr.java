package language.instruction.instructionSet;

import language.field.VoidF;
import language.instruction.BasicInstruction;

/**
 * Executes an arbitrary system instruction.
 * For example, print something to console.
 */
public class ArbitrarySysInstr extends BasicInstruction<VoidF> {
    private final Runnable action;

    public ArbitrarySysInstr(Runnable action) {
        this.action = action;

        returnValue(new VoidF());
    }

    @Override
    public boolean execute() {
        action.run();
        return true;
    }
}
