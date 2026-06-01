package language.instruction.instructionSet;

import language.Ref;
import language.instruction.BasicInstruction;

/**
 * Show is used as a marker that can be detected by a UI to indicate that the value of ref should be displayed.
 * It otherwise does not have any effect on the execution of the program.
 */
public record Show(String name, Ref<?> ref) implements BasicInstruction {
    /** @return true. */
    @Override
    public boolean exec() {
        return true;
    }
}
