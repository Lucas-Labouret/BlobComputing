package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.field.boolField.BoolEf;
import medium.Medium;
import medium.locusT.Ef;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolEfDisplay implements Displayable {
    private final BoolEf field;
    private final Styles.Style style;

    public BoolEfDisplay(BoolEf field, Styles.Style style){
        this.field = field;
        this.style = style;
    }

    @Override public boolean updatesEf() { return true; }

    @Override
    public HashMap<Ef, Color> displayColorEf() {
        HashMap<Ef, Boolean> mem = field.decode();
        HashMap<Ef, Color> colors = new HashMap<>();
        for (Ef ef : mem.keySet())
            if (mem.get(ef)) colors.put(ef, style.EF_TRUE());
            else colors.put(ef, style.EF_FALSE());
        return colors;
    }

    @Override
    public HashMap<Ef, String> displayStringEf() {
        HashMap<Ef, Boolean> mem = field.decode();
        HashMap<Ef, String> strings = new HashMap<>();
        for (Ef ef : mem.keySet())
            strings.put(ef, mem.get(ef).toString());
        return strings;
    }
}

