package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.field.boolField.BoolEv;
import language.fieldRef.boolField.BoolEvRef;
import medium.Medium;
import medium.locusT.Ev;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolEvDisplay implements Displayable {
    private final BoolEvRef ref;
    private final Styles.Style style;

    public BoolEvDisplay(BoolEvRef ref, Styles.Style style){
        this.ref = ref;
        this.style = style;
    }

    public BoolEvDisplay(BoolEvRef ref){
        this(ref, Styles.DEFAULT);
    }

    @Override public boolean updatesEv() { return true; }

    @Override
    public HashMap<Ev, Color> displayColorEv(Medium medium) {
        HashMap<Ev, Boolean> mem = BoolEv.decode(medium.evs, ref.get());
        HashMap<Ev, Color> colors = new HashMap<>();
        for (Ev ev : mem.keySet())
            if (mem.get(ev)) colors.put(ev, style.EV_TRUE());
            else colors.put(ev, style.EV_FALSE());
        return colors;
    }

    @Override
    public HashMap<Ev, String> displayStringEv(Medium medium) {
        HashMap<Ev, Boolean> mem = BoolEv.decode(medium.evs, ref.get());
        HashMap<Ev, String> strings = new HashMap<>();
        for (Ev ev : mem.keySet())
            strings.put(ev, mem.get(ev).toString());
        return strings;
    }
}

