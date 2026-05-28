package language.instruction.instructionSet;

/**
 * Snapshot is used as a marker that can be detected by a UI to indicate that the current display should be saved to disk.
 * It otherwise does not have any effect on the execution of the program.
 */
public class Snapshot implements BasicInstruction {
    /** @return true. */
    @Override
    public boolean exec() {
        return true;
    }
}
