package ui.display.displayable.boolFieldDisplay;

import language.obj.field.boolField.fieldT.BoolFe;
import language.ref.field.boolField.fieldT.BoolFeRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusT.Fe;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolFeDisplay implements Displayable {
    private final BoolFeRef ref;
    private final Styles.Style style;

    public BoolFeDisplay(BoolFeRef ref, Styles.Style style){
        this.ref = ref;
        this.style = style;
    }

    public BoolFeDisplay(BoolFeRef ref){
        this(ref, Styles.DEFAULT);
    }

    @Override public boolean updatesFe() { return true; }

    @Override
    public HashMap<Fe, Color> displayFe(Medium medium) {
        HashMap<Fe, Boolean> mem = BoolFe.decode(medium.fes, ref.get());
        HashMap<Fe, Color> colors = new HashMap<>();
        for (Fe fe : mem.keySet())
            if (mem.get(fe)) colors.put(fe, style.FE_TRUE());
            else colors.put(fe, style.FE_FALSE());
        return colors;
    }
}

