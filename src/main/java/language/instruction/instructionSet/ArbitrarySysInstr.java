package language.instruction.instructionSet;

import language.instruction.BasicInstruction;

/**
 * Executes an arbitrary system instruction.
 * For example, print something to console.
 */
public class ArbitrarySysInstr implements BasicInstruction {
    Runnable action;

    public ArbitrarySysInstr(Runnable action) {
        this.action = action;
    }

    @Override
    public boolean exec() {
        action.run();
        return true;
    }
}
