package ui.display.displayable.intFieldDisplay;

import javafx.scene.paint.Color;
import language.field.intField.IntEf;
import medium.Medium;
import medium.locusT.Ef;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class IntEfDisplay implements Displayable {
    private final IntEf ref;
    private final Styles.Style style;

    public IntEfDisplay(IntEf ref, Styles.Style style) {
        this.ref = ref;
        this.style = style;
    }

    @Override public boolean updatesEf() { return true; }

    @Override
    public HashMap<Ef, Color> displayColorEf(Medium medium) {
        HashMap<Ef, Integer> mem = ref.decode(medium);
        HashMap<Ef, Color> colors = new HashMap<>();

        int absMax = 0;
        for (Ef ef : mem.keySet()) {
            int cand = mem.get(ef);
            cand = cand >= 0 ? cand : -cand;
            if (cand > absMax) absMax = cand;
        }

        for (Ef ef : mem.keySet()) {
            int val = mem.get(ef);
            if (val == 0) {
                colors.put(ef, style.DEFAULT());
                continue;
            }
            Color baseColor;
            double shade;
            if (val > 0) {
                baseColor = style.EF_TRUE();
                shade = .8d * val / absMax;
            } else {
                baseColor = style.EF_FALSE();
                shade = -.8d * val / absMax;
            }
            Color targetColor = Color.WHITE.interpolate(Color.BLACK, shade);
            colors.put(ef, baseColor.interpolate(targetColor, 0.5));
        }

        return colors;
    }

    @Override
    public HashMap<Ef, String> displayStringEf(Medium medium) {
        HashMap<Ef, Integer> mem = ref.decode(medium);
        HashMap<Ef, String> strings = new HashMap<>();
        for (Ef ef : mem.keySet()) strings.put(ef, Integer.toString(mem.get(ef)));
        return strings;
    }
}
