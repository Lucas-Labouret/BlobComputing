package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.field.boolField.BoolF;
import medium.Medium;
import medium.locusS.Face;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolFDisplay implements Displayable {
    private final BoolF field;
    private final Styles.Style style;

    public BoolFDisplay(BoolF field, Styles.Style style){
        this.field = field;
        this.style = style;
    }

    @Override public boolean updatesF() { return true; }

    @Override
    public HashMap<Face, Color> displayColorF() {
        HashMap<Face, Boolean> mem = field.decode();
        HashMap<Face, Color> colors = new HashMap<>();
        for (Face f : mem.keySet())
            if (mem.get(f)) colors.put(f, style.FACE_TRUE());
            else colors.put(f, style.FACE_FALSE());
        return colors;
    }

    @Override
    public HashMap<Face, String> displayStringF() {
        HashMap<Face, Boolean> mem = field.decode();
        HashMap<Face, String> strings = new HashMap<>();
        for (Face f : mem.keySet())
            strings.put(f, mem.get(f).toString());
        return strings;
    }
}

