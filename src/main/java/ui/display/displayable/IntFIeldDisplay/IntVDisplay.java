package ui.display.displayable.IntFIeldDisplay;

import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusS.Vertex;
import prog.ref.intField.IntVRef;
import ui.display.displayable.Displayable;

import java.util.HashMap;

public class IntVDisplay implements Displayable {
    private final IntVRef ref;

    public IntVDisplay(IntVRef ref){
        this.ref = ref;
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
            if (val == 0) colors.put(v, Color.LIGHTGREY);
            else if (val > 0) colors.put(v, Color.rgb((int) (255 * (1 - .5 * val / absMax)), 0, 0));
            else colors.put(v, Color.rgb(0, 0, (int) (255 * (1 + .5 * val / absMax))));
        }

        return colors;
    }
}
