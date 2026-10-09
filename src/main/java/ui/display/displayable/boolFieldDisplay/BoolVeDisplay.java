package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.field.boolField.BoolVe;
import medium.Medium;
import medium.locusT.Ve;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolVeDisplay implements Displayable {
    private final BoolVe field;
    private final Styles.Style style;

    public BoolVeDisplay(BoolVe field, Styles.Style style){
        this.field = field;
        this.style = style;
    }

    @Override public boolean updatesVe() { return true; }

    @Override
    public HashMap<Ve, Color> displayColorVe() {
        HashMap<Ve, Boolean> mem = field.decode();
        HashMap<Ve, Color> colors = new HashMap<>();
        for (Ve ve : mem.keySet())
            if (mem.get(ve)) colors.put(ve, style.VE_TRUE());
            else colors.put(ve, style.VE_FALSE());
        return colors;
    }

    @Override
    public HashMap<Ve, String> displayStringVe() {
        HashMap<Ve, Boolean> mem = field.decode();
        HashMap<Ve, String> strings = new HashMap<>();
        for (Ve ve : mem.keySet())
            strings.put(ve, mem.get(ve).toString());
        return strings;
    }
}

