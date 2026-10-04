package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.field.boolField.BoolV;
import medium.Medium;
import medium.locusS.Vertex;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolVDisplay implements Displayable {
    private final BoolV field;
    private final Styles.Style style;

    public BoolVDisplay(BoolV field, Styles.Style style){
        this.field = field;
        this.style = style;
    }

    @Override public boolean updatesV() { return true; }

    @Override
    public HashMap<Vertex, Color> displayColorV(Medium medium) {
        HashMap<Vertex, Boolean> mem = field.decode(medium.vertices);
        HashMap<Vertex, Color> colors = new HashMap<>();
        for (Vertex v : mem.keySet())
            if (mem.get(v)) colors.put(v, style.VERTEX_TRUE());
            else colors.put(v, style.VERTEX_FALSE());
        return colors;
    }

    @Override
    public HashMap<Vertex, String> displayStringV(Medium medium) {
        HashMap<Vertex, Boolean> mem = field.decode(medium.vertices);
        HashMap<Vertex, String> strings = new HashMap<>();
        for (Vertex v : mem.keySet())
            strings.put(v, mem.get(v).toString());
        return strings;
    }
}
