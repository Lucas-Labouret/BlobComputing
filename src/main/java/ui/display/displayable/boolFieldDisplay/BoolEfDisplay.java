package ui.display.displayable.boolFieldDisplay;

import field.boolField.fieldT.BoolEf;
import language.fieldRef.BoolEfRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusT.Ef;
import ui.display.Globals;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolEfDisplay implements Displayable {
    private final BoolEfRef ref;

    public BoolEfDisplay(BoolEfRef ref){
        this.ref = ref;
    }

    @Override public boolean updatesEf() { return true; }

    @Override
    public HashMap<Ef, Color> displayEf(Medium medium) {
        HashMap<Ef, Boolean> mem = BoolEf.decode(medium.efs, ref.get());
        HashMap<Ef, Color> colors = new HashMap<>();
        for (Ef ef : mem.keySet())
            if (mem.get(ef)) colors.put(ef, Globals.EF_TRUE);
            else colors.put(ef, Globals.EF_FALSE);
        return colors;
    }
}

