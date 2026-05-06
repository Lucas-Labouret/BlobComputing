package ui.display.displayable.boolFieldDisplay;

import field.boolField.fieldT.BoolFv;
import language.fieldRef.BoolFvRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusT.Fv;
import ui.display.Globals;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolFvDisplay implements Displayable {
    private final BoolFvRef ref;

    public BoolFvDisplay(BoolFvRef ref){
        this.ref = ref;
    }

    @Override public boolean updatesFv() { return true; }

    @Override
    public HashMap<Fv, Color> displayFv(Medium medium) {
        HashMap<Fv, Boolean> mem = BoolFv.decode(medium.fvs, ref.get());
        HashMap<Fv, Color> colors = new HashMap<>();
        for (Fv fv : mem.keySet())
            if (mem.get(fv)) colors.put(fv, Globals.FV_TRUE);
            else colors.put(fv, Globals.FV_FALSE);
        return colors;
    }
}

