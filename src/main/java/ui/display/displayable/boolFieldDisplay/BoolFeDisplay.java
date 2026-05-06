package ui.display.displayable.boolFieldDisplay;

import field.boolField.fieldT.BoolFe;
import language.fieldRef.BoolFeRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusT.Fe;
import ui.display.Globals;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolFeDisplay implements Displayable {
    private final BoolFeRef ref;

    public BoolFeDisplay(BoolFeRef ref){
        this.ref = ref;
    }

    @Override public boolean updatesFe() { return true; }

    @Override
    public HashMap<Fe, Color> displayFe(Medium medium) {
        HashMap<Fe, Boolean> mem = BoolFe.decode(medium.fes, ref.get());
        HashMap<Fe, Color> colors = new HashMap<>();
        for (Fe fe : mem.keySet())
            if (mem.get(fe)) colors.put(fe, Globals.FE_TRUE);
            else colors.put(fe, Globals.FE_FALSE);
        return colors;
    }
}

