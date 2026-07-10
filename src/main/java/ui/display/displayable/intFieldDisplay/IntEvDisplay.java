package ui.display.displayable.intFieldDisplay;

import javafx.scene.paint.Color;
import language.fieldRef.intField.IntEvRef;
import medium.Medium;
import medium.locusT.Ev;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class IntEvDisplay implements Displayable {
    private final IntEvRef ref;
    private final Styles.Style style;

    public IntEvDisplay(IntEvRef ref, Styles.Style style) {
        this.ref = ref;
        this.style = style;
    }

    @Override public boolean updatesEv() { return true; }

    @Override
    public HashMap<Ev, Color> displayColorEv(Medium medium) {
        HashMap<Ev, Integer> mem = ref.get().decode(medium);
        HashMap<Ev, Color> colors = new HashMap<>();

        int absMax = 0;
        for (Ev v : mem.keySet()) {
            int cand = mem.get(v);
            cand = cand >= 0 ? cand : -cand;
            if (cand > absMax) absMax = cand;
        }

        for (Ev v: mem.keySet()) {
            int val = mem.get(v);
            double shade;
            if (val == 0) {
                colors.put(v, style.DEFAULT());
                continue;
            }
            Color baseColor;
            if (val > 0) {
                baseColor = style.VERTEX_TRUE();
                shade =  .8d * val / absMax;
            } else {
                baseColor = style.VERTEX_FALSE();
                shade = -.8d * val / absMax;
            }
            Color targetColor = Color.WHITE.interpolate(Color.BLACK, shade);
            Color interpolated = baseColor.interpolate(targetColor, 0.5);
            colors.put(v, interpolated);
        }

        return colors;
    }

    @Override
    public HashMap<Ev, String> displayStringEv(Medium medium) {
        HashMap<Ev, Integer> mem = ref.get().decode(medium);
        HashMap<Ev, String> strings = new HashMap<>();
        for (Ev v: mem.keySet()) {
            strings.put(v, Integer.toString(mem.get(v)));
        }
        return strings;
    }
}
