package ui.display.displayable.intFieldDisplay;

import javafx.scene.paint.Color;
import language.field.intField.IntFv;
import medium.Medium;
import medium.locusT.Fv;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class IntFvDisplay implements Displayable {
    private final IntFv ref;
    private final Styles.Style style;

    public IntFvDisplay(IntFv ref, Styles.Style style) {
        this.ref = ref;
        this.style = style;
    }

    @Override public boolean updatesFv() { return true; }

    @Override
    public HashMap<Fv, Color> displayColorFv() {
        HashMap<Fv, Integer> mem = ref.decode();
        HashMap<Fv, Color> colors = new HashMap<>();

        int absMax = 0;
        for (Fv fv : mem.keySet()) {
            int cand = mem.get(fv);
            cand = cand >= 0 ? cand : -cand;
            if (cand > absMax) absMax = cand;
        }

        for (Fv fv : mem.keySet()) {
            int val = mem.get(fv);
            if (val == 0) {
                colors.put(fv, style.DEFAULT());
                continue;
            }
            Color baseColor;
            double shade;
            if (val > 0) {
                baseColor = style.FV_TRUE();
                shade = .8d * val / absMax;
            } else {
                baseColor = style.FV_FALSE();
                shade = -.8d * val / absMax;
            }
            Color targetColor = Color.WHITE.interpolate(Color.BLACK, shade);
            colors.put(fv, baseColor.interpolate(targetColor, 0.5));
        }

        return colors;
    }

    @Override
    public HashMap<Fv, String> displayStringFv() {
        HashMap<Fv, Integer> mem = ref.decode();
        HashMap<Fv, String> strings = new HashMap<>();
        for (Fv fv : mem.keySet()) strings.put(fv, Integer.toString(mem.get(fv)));
        return strings;
    }
}
