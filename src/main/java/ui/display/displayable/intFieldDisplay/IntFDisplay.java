package ui.display.displayable.intFieldDisplay;

import javafx.scene.paint.Color;
import language.fieldRef.intField.IntFRef;
import medium.Medium;
import medium.locusS.Face;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class IntFDisplay implements Displayable {
    private final IntFRef ref;
    private final Styles.Style style;

    public IntFDisplay(IntFRef ref, Styles.Style style) {
        this.ref = ref;
        this.style = style;
    }

    @Override public boolean updatesF() { return true; }

    @Override
    public HashMap<Face, Color> displayColorF(Medium medium) {
        HashMap<Face, Integer> mem = ref.get().decode(medium);
        HashMap<Face, Color> colors = new HashMap<>();

        int absMax = 0;
        for (Face face : mem.keySet()) {
            int cand = mem.get(face);
            cand = cand >= 0 ? cand : -cand;
            if (cand > absMax) absMax = cand;
        }

        for (Face face : mem.keySet()) {
            int val = mem.get(face);
            if (val == 0) {
                colors.put(face, style.DEFAULT());
                continue;
            }
            Color baseColor;
            double shade;
            if (val > 0) {
                baseColor = style.FACE_TRUE();
                shade = .8d * val / absMax;
            } else {
                baseColor = style.FACE_FALSE();
                shade = -.8d * val / absMax;
            }
            Color targetColor = Color.WHITE.interpolate(Color.BLACK, shade);
            colors.put(face, baseColor.interpolate(targetColor, 0.5));
        }

        return colors;
    }

    @Override
    public HashMap<Face, String> displayStringF(Medium medium) {
        HashMap<Face, Integer> mem = ref.get().decode(medium);
        HashMap<Face, String> strings = new HashMap<>();
        for (Face face : mem.keySet()) strings.put(face, Integer.toString(mem.get(face)));
        return strings;
    }
}
