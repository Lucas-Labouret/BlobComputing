package ui.display.displayable.intFieldDisplay;

import javafx.scene.paint.Color;
import language.field.intField.IntVf;
import medium.Medium;
import medium.locusT.Vf;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class IntVfDisplay implements Displayable {
    private final IntVf ref;
    private final Styles.Style style;

    public IntVfDisplay(IntVf ref, Styles.Style style) {
        this.ref = ref;
        this.style = style;
    }

    @Override public boolean updatesVf() { return true; }

    @Override
    public HashMap<Vf, Color> displayColorVf(Medium medium) {
        HashMap<Vf, Integer> mem = ref.decode(medium);
        HashMap<Vf, Color> colors = new HashMap<>();

        int absMax = 0;
        for (Vf vf : mem.keySet()) {
            int cand = mem.get(vf);
            cand = cand >= 0 ? cand : -cand;
            if (cand > absMax) absMax = cand;
        }

        for (Vf vf : mem.keySet()) {
            int val = mem.get(vf);
            if (val == 0) {
                colors.put(vf, style.DEFAULT());
                continue;
            }
            Color baseColor;
            double shade;
            if (val > 0) {
                baseColor = style.VF_TRUE();
                shade = .8d * val / absMax;
            } else {
                baseColor = style.VF_FALSE();
                shade = -.8d * val / absMax;
            }
            Color targetColor = Color.WHITE.interpolate(Color.BLACK, shade);
            colors.put(vf, baseColor.interpolate(targetColor, 0.5));
        }

        return colors;
    }

    @Override
    public HashMap<Vf, String> displayStringVf(Medium medium) {
        HashMap<Vf, Integer> mem = ref.decode(medium);
        HashMap<Vf, String> strings = new HashMap<>();
        for (Vf vf : mem.keySet()) strings.put(vf, Integer.toString(mem.get(vf)));
        return strings;
    }
}
