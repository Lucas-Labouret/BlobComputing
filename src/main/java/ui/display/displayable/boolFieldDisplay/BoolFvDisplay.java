package ui.display.displayable.boolFieldDisplay;

import language.obj.field.boolField.fieldT.BoolFv;
import language.ref.field.boolField.fieldT.BoolFvRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusT.Fv;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolFvDisplay implements Displayable {
    private final BoolFvRef ref;
    private final Styles.Style style;

    public BoolFvDisplay(BoolFvRef ref, Styles.Style style){
        this.ref = ref;
        this.style = style;
    }

    public BoolFvDisplay(BoolFvRef ref){
        this(ref, Styles.DEFAULT);
    }

    @Override public boolean updatesFv() { return true; }

    @Override
    public HashMap<Fv, Color> displayFv(Medium medium) {
        HashMap<Fv, Boolean> mem = BoolFv.decode(medium.fvs, ref.get());
        HashMap<Fv, Color> colors = new HashMap<>();
        for (Fv fv : mem.keySet())
            if (mem.get(fv)) colors.put(fv, style.FV_TRUE());
            else colors.put(fv, style.FV_FALSE());
        return colors;
    }
}

