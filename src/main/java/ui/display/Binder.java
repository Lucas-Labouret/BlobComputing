package ui.display;

import language.fieldRef.*;
import prog.ref.intField.IntVRef;
import ui.MasterScene;
import ui.display.displayable.IntFIeldDisplay.*;
import ui.display.displayable.boolFieldDisplay.*;

public class Binder {
    public static void bind(String name, Object object) {
        switch (object) {
            case BoolVRef boolVRef   -> MasterScene.getInstance().addDisplay(name, new BoolVDisplay(boolVRef));
            case BoolVeRef boolVeRef -> MasterScene.getInstance().addDisplay(name, new BoolVeDisplay(boolVeRef));
            case BoolVfRef boolVfRef -> MasterScene.getInstance().addDisplay(name, new BoolVfDisplay(boolVfRef));
            case BoolERef boolERef   -> MasterScene.getInstance().addDisplay(name, new BoolEDisplay(boolERef));
            case BoolEvRef boolEvRef -> MasterScene.getInstance().addDisplay(name, new BoolEvDisplay(boolEvRef));
            case BoolEfRef boolEfRef -> MasterScene.getInstance().addDisplay(name, new BoolEfDisplay(boolEfRef));
            case BoolFRef boolFRef   -> MasterScene.getInstance().addDisplay(name, new BoolFDisplay(boolFRef));
            case BoolFvRef boolFvRef -> MasterScene.getInstance().addDisplay(name, new BoolFvDisplay(boolFvRef));
            case BoolFeRef boolFeRef -> MasterScene.getInstance().addDisplay(name, new BoolFeDisplay(boolFeRef));

            case IntVRef intVRef     -> MasterScene.getInstance().addDisplay(name, new IntVDisplay(intVRef));

            default -> throw new IllegalArgumentException("Unsupported type for display: " + object.getClass().getName());
        }

        update();
    }

    public static void update() { MasterScene.getInstance().display(); }
}
