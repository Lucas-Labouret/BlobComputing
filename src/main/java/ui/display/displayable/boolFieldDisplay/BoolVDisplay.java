package ui.display.displayable.boolFieldDisplay;

import field.boolField.fieldS.BoolV;
import language.fieldRef.BoolVRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusS.Vertex;
import ui.display.Globals;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolVDisplay implements Displayable {
    private final BoolVRef ref;

    public BoolVDisplay(BoolVRef ref){
        this.ref = ref;
    }

    @Override public boolean updatesV() { return true; }

    @Override
    public HashMap<Vertex, Color> displayV(Medium medium) {
        HashMap<Vertex, Boolean> mem = BoolV.decode(medium.vertices, ref.get());
        HashMap<Vertex, Color> colors = new HashMap<>();
        for (Vertex v : mem.keySet())
            if (mem.get(v)) colors.put(v, Globals.VERTEX_TRUE);
            else colors.put(v, Globals.VERTEX_FALSE);
        return colors;
    }
}
