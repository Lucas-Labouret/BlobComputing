package ui.display.displayable.boolFieldDisplay;

import field.boolField.fieldT.BoolVf;
import language.fieldRef.BoolVfRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusT.Vf;
import ui.display.Globals;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolVfDisplay implements Displayable {
    private final BoolVfRef ref;

    public BoolVfDisplay(BoolVfRef ref){
        this.ref = ref;
    }

    @Override public boolean updatesVf() { return true; }

    @Override
    public HashMap<Vf, Color> displayVf(Medium medium) {
        HashMap<Vf, Boolean> mem = BoolVf.decode(medium.vfs, ref.get());
        HashMap<Vf, Color> colors = new HashMap<>();
        for (Vf vf : mem.keySet())
            if (mem.get(vf)) colors.put(vf, Globals.VF_TRUE);
            else colors.put(vf, Globals.VF_FALSE);
        return colors;
    }
}
