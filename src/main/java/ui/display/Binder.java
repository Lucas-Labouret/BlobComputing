package ui.display;

import javafx.application.Platform;
import language.Ref;
import language.basicInstruction.Show;
import language.fieldRef.*;
import prog.ref.intField.IntVRef;
import ui.DisplayController;
import ui.display.displayable.intFIeldDisplay.*;
import ui.display.displayable.boolFieldDisplay.*;

import java.util.HashSet;

public class Binder {
    private final DisplayController displayController;
    private final HashSet<Show> bound = new HashSet<>();

    public Binder(DisplayController displayController) {
        this.displayController = displayController;
    }

    public void bind(Show show) {
        Platform.runLater(() -> _bind(show));
    }

    private void _bind(Show show) {
        String name = show.name();
        Ref<?> ref = show.fieldRef();

        if (!bound.contains(show)) switch (ref) {
            case BoolVRef boolVRef   -> displayController.addDisplay(name, new BoolVDisplay(boolVRef));
            case BoolVeRef boolVeRef -> displayController.addDisplay(name, new BoolVeDisplay(boolVeRef));
            case BoolVfRef boolVfRef -> displayController.addDisplay(name, new BoolVfDisplay(boolVfRef));
            case BoolERef boolERef   -> displayController.addDisplay(name, new BoolEDisplay(boolERef));
            case BoolEvRef boolEvRef -> displayController.addDisplay(name, new BoolEvDisplay(boolEvRef));
            case BoolEfRef boolEfRef -> displayController.addDisplay(name, new BoolEfDisplay(boolEfRef));
            case BoolFRef boolFRef   -> displayController.addDisplay(name, new BoolFDisplay(boolFRef));
            case BoolFvRef boolFvRef -> displayController.addDisplay(name, new BoolFvDisplay(boolFvRef));
            case BoolFeRef boolFeRef -> displayController.addDisplay(name, new BoolFeDisplay(boolFeRef));

            case IntVRef intVRef     -> displayController.addDisplay(name, new IntVDisplay(intVRef));

            default -> throw new IllegalArgumentException("Unsupported type for display: " + ref.getClass().getName());
        }

        bound.add(show);
        displayController.display();
    }
}
