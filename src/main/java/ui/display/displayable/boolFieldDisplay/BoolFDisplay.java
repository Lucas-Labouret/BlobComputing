package ui.display.displayable.boolFieldDisplay;

import language.obj.field.boolField.fieldS.BoolF;
import language.ref.field.boolField.fieldS.BoolFRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusS.Face;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolFDisplay implements Displayable {
    private final BoolFRef ref;
    private final Styles.Style style;

    public BoolFDisplay(BoolFRef ref, Styles.Style style){
        this.ref = ref;
        this.style = style;
    }

    public BoolFDisplay(BoolFRef ref){
        this(ref, Styles.DEFAULT);
    }

    @Override public boolean updatesF() { return true; }

    @Override
    public HashMap<Face, Color> displayF(Medium medium) {
        HashMap<Face, Boolean> mem = BoolF.decode(medium.faces, ref.get());
        HashMap<Face, Color> colors = new HashMap<>();
        for (Face f : mem.keySet())
            if (mem.get(f)) colors.put(f, style.FACE_TRUE());
            else colors.put(f, style.FACE_FALSE());
        return colors;
    }
}

