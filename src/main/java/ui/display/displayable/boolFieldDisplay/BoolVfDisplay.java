package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.field.boolField.BoolVf;
import language.fieldRef.boolField.BoolVfRef;
import medium.Medium;
import medium.locusT.Vf;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolVfDisplay implements Displayable {
    private final BoolVfRef ref;
    private final Styles.Style style;

    public BoolVfDisplay(BoolVfRef ref, Styles.Style style){
        this.ref = ref;
        this.style = style;
    }

    public BoolVfDisplay(BoolVfRef ref){
        this(ref, Styles.DEFAULT);
    }

    @Override public boolean updatesVf() { return true; }

    @Override
    public HashMap<Vf, Color> displayVf(Medium medium) {
        HashMap<Vf, Boolean> mem = BoolVf.decode(medium.vfs, ref.get());
        HashMap<Vf, Color> colors = new HashMap<>();
        for (Vf vf : mem.keySet())
            if (mem.get(vf)) colors.put(vf, style.VF_TRUE());
            else colors.put(vf, style.VF_FALSE());
        return colors;
    }
}
