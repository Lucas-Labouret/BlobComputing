package ui.display.displayable.intFieldDisplay;

import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusS.Vertex;
import language.ref.field.intField.IntVRef;
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

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (Vertex v : mem.keySet()) {
            int cand = mem.get(v);
            if (cand > max) max = cand;
            if (cand < min) min = cand;
        }
        int absMax = Math.max(Math.abs(max), Math.abs(min));

        for (Vertex v: mem.keySet()) {
            int val = mem.get(v);
            double shade = 0;
            if (val == 0) {
                colors.put(v, style.DEFAULT());
                continue;
            }
            if (val > 0) shade = 1 - .5 * val / absMax;
            else         shade = 1 + .5 * val / absMax;
            double r = style.VERTEX_TRUE().getRed()   * shade;
            double g = style.VERTEX_TRUE().getGreen() * shade;
            double b = style.VERTEX_TRUE().getBlue()  * shade;
            colors.put(v, new Color(r, g, b, 1));
        }

        return colors;
    }
}
