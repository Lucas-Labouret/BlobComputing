package ui.display.displayable.boolFieldDisplay;

import field.boolField.fieldT.BoolEv;
import language.fieldRef.BoolEvRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusT.Ev;
import ui.display.Globals;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolEvDisplay implements Displayable {
    private final BoolEvRef ref;

    public BoolEvDisplay(BoolEvRef ref){
        this.ref = ref;
    }

    @Override public boolean updatesEv() { return true; }

    @Override
    public HashMap<Ev, Color> displayEv(Medium medium) {
        HashMap<Ev, Boolean> mem = BoolEv.decode(medium.evs, ref.get());
        HashMap<Ev, Color> colors = new HashMap<>();
        for (Ev ev : mem.keySet())
            if (mem.get(ev)) colors.put(ev, Globals.EV_TRUE);
            else colors.put(ev, Globals.EV_FALSE);
        return colors;
    }
}

