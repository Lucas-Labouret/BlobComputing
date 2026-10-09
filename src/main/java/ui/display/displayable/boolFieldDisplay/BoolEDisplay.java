package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.field.boolField.BoolE;
import medium.Medium;
import medium.locusS.Edge;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolEDisplay implements Displayable {
    private final BoolE field;
    private final Styles.Style style;

    public BoolEDisplay(BoolE field, Styles.Style style){
        this.field = field;
        this.style = style;
    }

    @Override public boolean updatesE() { return true; }

    @Override
    public HashMap<Edge, Color> displayColorE() {
        HashMap<Edge, Boolean> mem = field.decode();
        HashMap<Edge, Color> colors = new HashMap<>();
        for (Edge e : mem.keySet())
            if (mem.get(e)) colors.put(e, style.EDGE_TRUE());
            else colors.put(e, style.EDGE_FALSE());
        return colors;
    }

    @Override
    public HashMap<Edge, String> displayStringE() {
        HashMap<Edge, Boolean> mem = field.decode();
        HashMap<Edge, String> texts = new HashMap<>();
        for (Edge e : mem.keySet())
            texts.put(e, mem.get(e).toString());
        return texts;
    }
}

