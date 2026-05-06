package ui.display.displayable.boolFieldDisplay;

import field.boolField.fieldT.BoolVe;
import language.fieldRef.BoolVeRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusT.Ve;
import ui.display.Globals;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolVeDisplay implements Displayable {
    private final BoolVeRef ref;

    public BoolVeDisplay(BoolVeRef ref){
        this.ref = ref;
    }

    @Override public boolean updatesVe() { return true; }

    @Override
    public HashMap<Ve, Color> displayVe(Medium medium) {
        HashMap<Ve, Boolean> mem = BoolVe.decode(medium.ves, ref.get());
        HashMap<Ve, Color> colors = new HashMap<>();
        for (Ve ve : mem.keySet())
            if (mem.get(ve)) colors.put(ve, Globals.VE_TRUE);
            else colors.put(ve, Globals.VE_FALSE);
        return colors;
    }
}

