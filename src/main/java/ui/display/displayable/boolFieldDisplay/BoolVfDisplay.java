package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.field.boolField.BoolVf;
import medium.Medium;
import medium.locusT.Vf;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolVfDisplay implements Displayable {
    private final BoolVf field;
    private final Styles.Style style;

    public BoolVfDisplay(BoolVf field, Styles.Style style){
        this.field = field;
        this.style = style;
    }

    @Override public boolean updatesVf() { return true; }

    @Override
    public HashMap<Vf, Color> displayColorVf() {
        HashMap<Vf, Boolean> mem = field.decode();
        HashMap<Vf, Color> colors = new HashMap<>();
        for (Vf vf : mem.keySet())
            if (mem.get(vf)) colors.put(vf, style.VF_TRUE());
            else colors.put(vf, style.VF_FALSE());
        return colors;
    }

    @Override
    public HashMap<Vf, String> displayStringVf() {
        HashMap<Vf, Boolean> mem = field.decode();
        HashMap<Vf, String> strings = new HashMap<>();
        for (Vf vf : mem.keySet())
            strings.put(vf, mem.get(vf).toString());
        return strings;
    }
}
