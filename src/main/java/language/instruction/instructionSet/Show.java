package language.instruction.instructionSet;

import language.field.Field;
import language.field.VoidF;
import language.instruction.BasicInstruction;
import ui.display.Styles;

import java.util.Optional;

/**
 * Show is used as a marker that can be detected by a UI to indicate that the value of inRef should be displayed.
 * It otherwise does not have any effect on the execution of the program.
 */
public class Show<F extends Field<?>> extends BasicInstruction<VoidF> {
    public final String name;
    private final F in;
    public final Field<?> out;
    public final Optional<Styles.Style> style;

    public Show(String name, F in, Optional<Styles.Style> style) {
        this.name = name;
        this.in = in;
        this.out = in.copy();
        this.style = style;

        returnValue(new VoidF());
    }

    public Show(String name, F in) {
        this(name, in, Optional.empty());
    }

    public Show(String name, F in, Styles.Style style) {
        this(name, in, Optional.of(style));
    }

    @Override
    public boolean execute() {
        out.copyFrom(in);
        return true;
    }
}