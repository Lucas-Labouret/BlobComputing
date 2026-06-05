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

    public IntVeDisplay(IntVeRef ref) {
        this(ref, Styles.DEFAULT_INT);
    }

    @Override public boolean updatesVe() { return true; }

    @Override
    public HashMap<Ve, Color> displayVe(Medium medium) {
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
                shade = -.8d * (double) val / absMax;
            }
            Color targetColor = Color.BLACK;
            Color interpolated = baseColor.interpolate(targetColor, shade);
            colors.put(ve, interpolated);
        }

        return colors;
    }
}
