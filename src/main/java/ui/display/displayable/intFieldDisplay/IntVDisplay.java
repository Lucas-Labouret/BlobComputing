package ui.display.displayable.intFieldDisplay;

import javafx.scene.paint.Color;
import language.fieldRef.intField.IntVRef;
import medium.Medium;
import medium.locusS.Vertex;
import ui.display.Styles;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class IntVDisplay implements Displayable {
    private final IntVRef ref;
    private final Styles.Style style;

    public IntVDisplay(IntVRef ref, Styles.Style style) {
        this.ref = ref;
        this.style = style;
    }

    public IntVDisplay(IntVRef ref) {
        this(ref, Styles.DEFAULT_INT);
    }

    @Override public boolean updatesV() { return true; }

    @Override
    public HashMap<Vertex, Color> displayV(Medium medium) {
        HashMap<Vertex, Integer> mem = ref.get().decode(medium);
        HashMap<Vertex, Color> colors = new HashMap<>();

        int absMax = 0;
        for (Vertex v : mem.keySet()) {
            int cand = mem.get(v);
            cand = cand >= 0 ? cand : -cand;
            if (cand > absMax) absMax = cand;
        }

        for (Vertex v: mem.keySet()) {
            int val = mem.get(v);
            double shade;
            if (val == 0) {
                colors.put(v, style.DEFAULT());
                continue;
            }
            Color baseColor;
            if (val > 0) {
                baseColor = style.VERTEX_TRUE();
                shade = .8d * val / absMax;
            } else {
                baseColor = style.VERTEX_FALSE();
                shade = - .8d * val / absMax;
            }
            Color targetColor = Color.BLACK;
            Color interpolated = baseColor.interpolate(targetColor, shade);
            colors.put(v, interpolated);
        }

        return colors;
    }
}
