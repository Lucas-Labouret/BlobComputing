package ui.display.displayable.boolFieldDisplay;

import field.boolField.fieldS.BoolF;
import language.fieldRef.BoolFRef;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusS.Face;
import ui.display.Globals;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class BoolFDisplay implements Displayable {
    private final BoolFRef ref;

    public BoolFDisplay(BoolFRef ref){
        this.ref = ref;
    }

    @Override public boolean updatesF() { return true; }

    @Override
    public HashMap<Face, Color> displayF(Medium medium) {
        HashMap<Face, Boolean> mem = BoolF.decode(medium.faces, ref.get());
        HashMap<Face, Color> colors = new HashMap<>();
        for (Face f : mem.keySet())
            if (mem.get(f)) colors.put(f, Globals.FACE_TRUE);
            else colors.put(f, Globals.FACE_FALSE);
        return colors;
    }
}

