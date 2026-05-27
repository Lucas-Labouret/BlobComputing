package ui.display.displayable.boolFieldDisplay;

import language.obj.field.boolField.fieldT.BoolVe;
import language.ref.field.boolField.fieldT.BoolVeRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusT.Ve;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolVeDisplay implements Displayable {
    private final BoolVeRef ref;
    private final Styles.Style style;

    public BoolVeDisplay(BoolVeRef ref, Styles.Style style){
        this.ref = ref;
        this.style = style;
    }

    public BoolVeDisplay(BoolVeRef ref){
        this(ref, Styles.DEFAULT);
    }

    @Override public boolean updatesVe() { return true; }

    @Override
    public HashMap<Ve, Color> displayVe(Medium medium) {
        HashMap<Ve, Boolean> mem = BoolVe.decode(medium.ves, ref.get());
        HashMap<Ve, Color> colors = new HashMap<>();
        for (Ve ve : mem.keySet())
            if (mem.get(ve)) colors.put(ve, style.VE_TRUE());
            else colors.put(ve, style.VE_FALSE());
        return colors;
    }
}

