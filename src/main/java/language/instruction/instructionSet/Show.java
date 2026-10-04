package language.instruction.instructionSet;

import language.field.Field;
import language.instruction.BasicInstruction;
import ui.display.Styles;

import java.util.Optional;

/**
 * Show is used as a marker that can be detected by a UI to indicate that the value of inRef should be displayed.
 * It otherwise does not have any effect on the execution of the program.
 */
public class Show<F extends Field<F>> implements BasicInstruction {
    public final String name;
    private final F in;
    public final F field;
    public final Optional<Styles.Style> style;

    public Show(String name, F in, Optional<Styles.Style> style) {
        this.name = name;
        this.in = in;
        this.field = in.copy();
        this.style = style;
    }

    public Show(String name, F in) {
        this(name, in, Optional.empty());
    }

    public Show(String name, F in, Styles.Style style) {
        this(name, in, Optional.of(style));
    }

    /**
     * @return true.
     */
    @Override
    public boolean exec() {
        field.set(in);
        return true;
    }
}