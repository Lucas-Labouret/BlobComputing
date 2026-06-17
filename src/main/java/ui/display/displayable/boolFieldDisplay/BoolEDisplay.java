package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.field.boolField.BoolE;
import language.fieldRef.boolField.BoolERef;
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
    public HashMap<Edge, Color> displayColorE(Medium medium) {
        HashMap<Edge, Boolean> mem = BoolE.decode(medium.edges, ref.get());
        HashMap<Edge, Color> colors = new HashMap<>();
        for (Edge e : mem.keySet())
            if (mem.get(e)) colors.put(e, style.EDGE_TRUE());
            else colors.put(e, style.EDGE_FALSE());
        return colors;
    }

    @Override
    public HashMap<Edge, String> displayStringE(Medium medium) {
        HashMap<Edge, Boolean> mem = BoolE.decode(medium.edges, ref.get());
        HashMap<Edge, String> texts = new HashMap<>();
        for (Edge e : mem.keySet())
            texts.put(e, mem.get(e).toString());
        return texts;
    }
}

