package ui.display.displayable.boolFieldDisplay;

import javafx.scene.paint.Color;
import language.field.boolField.BoolEf;
import language.fieldRef.boolField.BoolEfRef;
import medium.Medium;
import medium.locusT.Ef;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolEfDisplay implements Displayable {
    private final BoolEfRef ref;
    private final Styles.Style style;

    public BoolEfDisplay(BoolEfRef ref, Styles.Style style){
        this.ref = ref;
        this.style = style;
    }

    public BoolEfDisplay(BoolEfRef ref){
        this(ref, Styles.DEFAULT);
    }

    @Override public boolean updatesEf() { return true; }

    @Override
    public HashMap<Ef, Color> displayEf(Medium medium) {
        HashMap<Ef, Boolean> mem = BoolEf.decode(medium.efs, ref.get());
        HashMap<Ef, Color> colors = new HashMap<>();
        for (Ef ef : mem.keySet())
            if (mem.get(ef)) colors.put(ef, style.EF_TRUE());
            else colors.put(ef, style.EF_FALSE());
        return colors;
    }
}

