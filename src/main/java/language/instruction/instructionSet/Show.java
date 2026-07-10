package language.instruction.instructionSet;

import language.fieldRef.Ref;
import language.instruction.BasicInstruction;
import ui.display.Styles;

import java.util.Optional;

/**
 * Show is used as a marker that can be detected by a UI to indicate that the value of ref should be displayed.
 * It otherwise does not have any effect on the execution of the program.
 */
public record Show(String name, Ref<?> ref, Optional<Styles.Style> style) implements BasicInstruction {
    public Show(String name, Ref<?> ref) {
        this(name, ref, Optional.empty());
    }

    public Show(String name, Ref<?> ref, Styles.Style style) {
        this(name, ref, Optional.of(style));
    }

    /** @return true. */
    @Override
    public boolean exec() {
        return true;
    }
}
