package language.instruction.instructionSet;

import language.field.Field;
import language.fieldRef.Ref;
import language.instruction.BasicInstruction;
import ui.display.Styles;

import java.util.Optional;

/**
 * Show is used as a marker that can be detected by a UI to indicate that the value of inRef should be displayed.
 * It otherwise does not have any effect on the execution of the program.
 */
public class Show<T extends Field>implements BasicInstruction {
    public final String name;
    private final Ref<T> inRef;
    public final Ref<T> ref;
    public final Optional<Styles.Style> style;

    public Show (String name, Ref<T> inRef, Optional<Styles.Style> style) {
        this.name = name;
        this.inRef = inRef;
        this.ref = inRef.copy();
        this.style = style;
    }

    public Show(String name, Ref<T> inRef) {
        this(name, inRef, Optional.empty());
    }

    public Show(String name, Ref<T> inRef, Styles.Style style) {
        this(name, inRef, Optional.of(style));
    }

    /** @return true. */
    @Override
    public boolean exec() {
        ref.set(inRef.get());
        return true;
    }
}
