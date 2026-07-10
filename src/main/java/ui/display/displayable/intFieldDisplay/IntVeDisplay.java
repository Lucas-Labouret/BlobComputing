package ui.display.displayable.intFieldDisplay;

import javafx.scene.paint.Color;
import language.fieldRef.intField.IntVeRef;
import medium.Medium;
import medium.locusT.Ve;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class IntVeDisplay implements Displayable {
    private final IntVeRef ref;
    private final Styles.Style style;

    public IntVeDisplay(IntVeRef ref, Styles.Style style) {
        this.ref = ref;
        this.style = style;
    }

    @Override public boolean updatesVe() { return true; }

    @Override
    public HashMap<Ve, Color> displayColorVe(Medium medium) {
        HashMap<Ve, Integer> mem = ref.get().decode(medium);
        HashMap<Ve, Color> colors = new HashMap<>();

        int absMax = 0;
        for (Ve ve : mem.keySet()) {
            int cand = mem.get(ve);
            cand = cand >= 0 ? cand : -cand;
            if (cand > absMax) absMax = cand;
        }

        for (Ve ve: mem.keySet()) {
            int val = mem.get(ve);
            double shade;
            if (val == 0) {
                colors.put(ve, style.DEFAULT());
                continue;
            }
            Color baseColor;
            if (val > 0) {
                baseColor = style.VE_TRUE();
                shade =  .8d * val / absMax;
            } else {
                baseColor = style.VE_FALSE();
                shade = -.8d * val / absMax;
            }
            Color targetColor = Color.WHITE.interpolate(Color.BLACK, shade);
            Color interpolated = baseColor.interpolate(targetColor, 0.5);
            colors.put(ve, interpolated);
        }

        return colors;
    }

    @Override
    public HashMap<Ve, String> displayStringVe(Medium medium) {
        HashMap<Ve, Integer> mem = ref.get().decode(medium);
        HashMap<Ve, String> strings = new HashMap<>();
        for (Ve ve: mem.keySet()) {
            strings.put(ve, Integer.toString(mem.get(ve)));
        }
        return strings;
    }
}
