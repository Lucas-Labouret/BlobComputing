package language.instruction.instructionSet;

import language.field.VoidF;
import language.instruction.BasicInstruction;

/**
 * Snapshot is used as a marker that can be detected by a UI to indicate that the current display should be saved to disk.
 * It otherwise does not have any effect on the execution of the program.
 */
public class Snapshot extends BasicInstruction<VoidF> {
    public Snapshot() {
        returnValue(new VoidF());
    }

    /** @return true. */
    @Override
    public boolean execute() {
        return true;
    }
}
