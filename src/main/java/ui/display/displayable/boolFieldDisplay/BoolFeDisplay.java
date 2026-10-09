package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.field.boolField.BoolFe;
import medium.Medium;
import medium.locusT.Fe;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolFeDisplay implements Displayable {
    private final BoolFe field;
    private final Styles.Style style;

    public BoolFeDisplay(BoolFe field, Styles.Style style){
        this.field = field;
        this.style = style;
    }

    @Override public boolean updatesFe() { return true; }

    @Override
    public HashMap<Fe, Color> displayColorFe() {
        HashMap<Fe, Boolean> mem = field.decode();
        HashMap<Fe, Color> colors = new HashMap<>();
        for (Fe fe : mem.keySet())
            if (mem.get(fe)) colors.put(fe, style.FE_TRUE());
            else colors.put(fe, style.FE_FALSE());
        return colors;
    }

    @Override
    public HashMap<Fe, String> displayStringFe() {
        HashMap<Fe, Boolean> mem = field.decode();
        HashMap<Fe, String> strings = new HashMap<>();
        for (Fe fe : mem.keySet())
            strings.put(fe, mem.get(fe).toString());
        return strings;
    }
}

