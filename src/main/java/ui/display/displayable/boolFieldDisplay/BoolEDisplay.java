package ui.display.displayable.boolFieldDisplay;

import language.obj.field.boolField.fieldS.BoolE;
import language.ref.field.boolField.fieldS.BoolERef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusS.Edge;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolEDisplay implements Displayable {
    private final BoolERef ref;
    private final Styles.Style style;

    public BoolEDisplay(BoolERef ref, Styles.Style style){
        this.ref = ref;
        this.style = style;
    }

    public BoolEDisplay(BoolERef ref){
        this(ref, Styles.DEFAULT);
    }

    @Override public boolean updatesE() { return true; }

    @Override
    public HashMap<Edge, Color> displayE(Medium medium) {
        HashMap<Edge, Boolean> mem = BoolE.decode(medium.edges, ref.get());
        HashMap<Edge, Color> colors = new HashMap<>();
        for (Edge e : mem.keySet())
            if (mem.get(e)) colors.put(e, style.EDGE_TRUE());
            else colors.put(e, style.EDGE_FALSE());
        return colors;
    }
}

