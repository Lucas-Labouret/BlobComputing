package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.obj.field.boolField.BoolV;
import language.ref.field.boolField.BoolVRef;
import medium.Medium;
import medium.locusS.Vertex;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolVDisplay implements Displayable {
    private final BoolVRef ref;
    private final Styles.Style style;

    public BoolVDisplay(BoolVRef ref, Styles.Style style){
        this.ref = ref;
        this.style = style;
    }

    public BoolVDisplay(BoolVRef ref){
        this(ref, Styles.DEFAULT);
    }

    @Override public boolean updatesV() { return true; }

    @Override
    public HashMap<Vertex, Color> displayV(Medium medium) {
        HashMap<Vertex, Boolean> mem = BoolV.decode(medium.vertices, ref.get());
        HashMap<Vertex, Color> colors = new HashMap<>();
        for (Vertex v : mem.keySet())
            if (mem.get(v)) colors.put(v, style.VERTEX_TRUE());
            else colors.put(v, style.VERTEX_FALSE());
        return colors;
    }
}
