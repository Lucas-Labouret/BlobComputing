package ui.display.displayable.boolFieldDisplay;

import field.boolField.fieldS.BoolE;
import language.fieldRef.BoolERef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusS.Edge;
import ui.display.Globals;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolEDisplay implements Displayable {
    private final BoolERef ref;

    public BoolEDisplay(BoolERef ref){
        this.ref = ref;
    }

    @Override public boolean updatesE() { return true; }

    @Override
    public HashMap<Edge, Color> displayE(Medium medium) {
        HashMap<Edge, Boolean> mem = BoolE.decode(medium.edges, ref.get());
        HashMap<Edge, Color> colors = new HashMap<>();
        for (Edge e : mem.keySet())
            if (mem.get(e)) colors.put(e, Globals.EDGE_TRUE);
            else colors.put(e, Globals.EDGE_FALSE);
        return colors;
    }
}

