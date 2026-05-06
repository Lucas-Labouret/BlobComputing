package language.basicInstruction;

import javafx.application.Platform;
import language.Ref;
import ui.display.Binder;

/** Represents a basic instruction that prints the value of a reference. */
public class Show implements BasicInstruction {
    private final Ref<?> fieldRef;
    private final String name;

    private boolean firstExec = true;

    /** Creates a new Show. */
    public Show(String name, Ref<?> fieldRef) {
        this.fieldRef = fieldRef;
        this.name = name;
    }
    /** @return true. */

    @Override
    public boolean exec() {
        Platform.runLater(() -> {
            if (firstExec) {
                Binder.bind(name, fieldRef);
                firstExec = false;
            } else Binder.update();
        });
        return true;
    }
}
