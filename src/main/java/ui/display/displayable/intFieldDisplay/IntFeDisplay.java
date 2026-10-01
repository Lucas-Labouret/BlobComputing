package ui.display.displayable.intFieldDisplay;

import javafx.scene.paint.Color;
import language.fieldRef.intField.IntFeRef;
import medium.Medium;
import medium.locusT.Fe;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class IntFeDisplay implements Displayable {
    private final IntFeRef ref;
    private final Styles.Style style;

    public IntFeDisplay(IntFeRef ref, Styles.Style style) {
        this.ref = ref;
        this.style = style;
    }

    @Override public boolean updatesFe() { return true; }

    @Override
    public HashMap<Fe, Color> displayColorFe(Medium medium) {
        HashMap<Fe, Integer> mem = ref.get().decode(medium);
        HashMap<Fe, Color> colors = new HashMap<>();

        int absMax = 0;
        for (Fe fe : mem.keySet()) {
            int cand = mem.get(fe);
            cand = cand >= 0 ? cand : -cand;
            if (cand > absMax) absMax = cand;
        }

        for (Fe fe : mem.keySet()) {
            int val = mem.get(fe);
            if (val == 0) {
                colors.put(fe, style.DEFAULT());
                continue;
            }
            Color baseColor;
            double shade;
            if (val > 0) {
                baseColor = style.FE_TRUE();
                shade = .8d * val / absMax;
            } else {
                baseColor = style.FE_FALSE();
                shade = -.8d * val / absMax;
            }
            Color targetColor = Color.WHITE.interpolate(Color.BLACK, shade);
            colors.put(fe, baseColor.interpolate(targetColor, 0.5));
        }

        return colors;
    }

    @Override
    public HashMap<Fe, String> displayStringFe(Medium medium) {
        HashMap<Fe, Integer> mem = ref.get().decode(medium);
        HashMap<Fe, String> strings = new HashMap<>();
        for (Fe fe : mem.keySet()) strings.put(fe, Integer.toString(mem.get(fe)));
        return strings;
    }
}
