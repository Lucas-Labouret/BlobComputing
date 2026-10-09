package ui.display;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import medium.Locus;
import medium.Medium;
import medium.locusS.Edge;
import medium.locusS.Face;
import medium.locusS.Vertex;
import medium.locusT.*;
import ui.display.displayable.Displayable;
import utils.ClosestPair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Draws a medium on a canvas.
 * Uses the displayables to determine which loci to draw and what color/label to use.
 */
public class MediumDrawer extends Canvas {
    private final GraphicsContext gc = getGraphicsContext2D();

    // Scale of the drawing relative to the medium
    private static final double SCALE_TARGET = 10000;
    private final double scale;
    private final double offSet;

    // The medium to be drawn
    private final Medium medium;

    // Which loci should be shown
    private boolean v  = false;
    private boolean ve = false;
    private boolean vf = false;
    private boolean e  = false;
    private boolean ev = false;
    private boolean ef = false;
    private boolean f  = false;
    private boolean fv = false;
    private boolean fe = false;

    // Used to only recompute circle size if the loci shown changed
    private boolean lastv  = false;
    private boolean lastve = false;
    private boolean lastvf = false;
    private boolean laste  = false;
    private boolean lastev = false;
    private boolean lastef = false;
    private boolean lastf  = false;
    private boolean lastfv = false;
    private boolean lastfe = false;

    // Contains the displaybles currently displayed
    private final HashSet<Displayable> colorDisplayables = new HashSet<>();
    private final HashSet<Displayable> stringDisplayables = new HashSet<>();

    // Maps each locus to its color
    private final HashMap<Vertex, Color> vColors  = new HashMap<>();
    private final HashMap<Ve,     Color> veColors = new HashMap<>();
    private final HashMap<Vf,     Color> vfColors = new HashMap<>();
    private final HashMap<Edge,   Color> eColors  = new HashMap<>();
    private final HashMap<Ev,     Color> evColors = new HashMap<>();
    private final HashMap<Ef,     Color> efColors = new HashMap<>();
    private final HashMap<Face,   Color> fColors  = new HashMap<>();
    private final HashMap<Fv,     Color> fvColors = new HashMap<>();
    private final HashMap<Fe,     Color> feColors = new HashMap<>();

    // Maps each locus to its string representation
    private HashMap<Locus, String> strings;

    // The size of circle representing a locus
    private double circleSize;

    public MediumDrawer(Medium medium) {
        this.medium = medium;

        double height = medium.height;
        double width = medium.width;
        double dim = Math.max(height, width);
        offSet = 0.025 * dim;
        scale = SCALE_TARGET/dim;

        // Set canvas size
        setHeight((height + 2*offSet) * scale);
        setWidth((width + 2*offSet) * scale);

        // Draw grid
        draw();
    }

    /** Draws the medium on the canvas. */
    public void draw() {
        // Clear the canvas
        gc.clearRect(0, 0, getWidth(), getHeight());

        // Compute colors and strings for loci
        computeColors();
        computeStrings();

        // Update circle size if the loci shown changed
        if (lastv != v || lastve != ve || lastvf != vf || laste != e || lastev != ev || lastef != ef || lastf != f || lastfv != fv || lastfe != fe) {
            updateCircleSize();
            lastv = v; lastve = ve; lastvf = vf; laste = e; lastev = ev; lastef = ef; lastf = f; lastfv = fv; lastfe = fe;
        }

        // Draw loci
        if (v ) for (Vertex l : medium.vertices) drawV(l);
        if (ve) for (Ve     l : medium.ves)      drawTransfer(l);
        if (vf) for (Vf     l : medium.vfs)      drawTransfer(l);
        if (e ) for (Edge   l : medium.edges)    drawE(l);
        if (ev) for (Ev     l : medium.evs)      drawTransfer(l);
        if (ef) for (Ef     l : medium.efs)      drawTransfer(l);
        if (f ) for (Face   l : medium.faces)    drawF(l);
        if (fv) for (Fv     l : medium.fvs)      drawTransfer(l);
        if (fe) for (Fe     l : medium.fes)      drawTransfer(l);

        // Draw strings
        for (Locus l: strings.keySet()) {
            if (l instanceof Vertex && v ||
                l instanceof Ve && ve    ||
                l instanceof Vf && vf    ||
                l instanceof Edge && e   ||
                l instanceof Ev && ev    ||
                l instanceof Ef && ef    ||
                l instanceof Face && f   ||
                l instanceof Fv && fv    ||
                l instanceof Fe && fe
            ) {
                String s = strings.get(l);
                gc.setFont(Font.font(circleSize));
                gc.setFill(Color.BLACK);
                gc.fillText(s, (l.w+offSet)*scale - circleSize/2, (l.h+offSet)*scale + circleSize/2);
            }
        }
    }

    /** Draws a vertex on the canvas. */
    private void drawV(Vertex l){
        gc.setFill(getColor(l));
        gc.fillOval((l.w+offSet)*scale - circleSize/2, (l.h+offSet)*scale - circleSize/2, circleSize, circleSize);
    }

    /** Draws an edge on the canvas. */
    private void drawE(Edge l){
        gc.setFill(getColor(l));
        gc.fillRect((l.w+offSet)*scale - circleSize/2, (l.h+offSet)*scale - circleSize/2, circleSize, circleSize);
    }

    // equilateral triangle inscribed in unit circle
    /** Moves a point in the triangle to the correct position on the canvas. */
    private double moveTriangle(double ct, double cl) { return ct * circleSize/2 + (cl + offSet) * scale; }
    private static final double ax = -0.866;
    private static final double ay = -0.5;
    private static final double bx = 0.866;
    private static final double by = -0.5;
    private static final double cx = 0.0;
    private static final double cy = 1.0;

    /** Draws a face on the canvas. */
    private void drawF(Face l){
        gc.setFill(getColor(l));
        gc.fillPolygon(new double[]{moveTriangle(ax, l.w), moveTriangle(bx, l.w), moveTriangle(cx, l.w)},
                       new double[]{moveTriangle(ay, l.h), moveTriangle(by, l.h), moveTriangle(cy, l.h)}, 3);
    }

    /** Draws a transfer locus on the canvas. */
    private void drawTransfer(Locus l){
        gc.setFill(getColor(l));
        gc.fillRect((l.w+offSet)*scale - circleSize/4, (l.h+offSet)*scale - circleSize/4, circleSize/2, circleSize/2);
    }

    /** Returns the color of a locus. */
    private Color getColor(Locus l) {
        switch (l) {
            case Vertex vl -> { return vColors .getOrDefault(vl,  Styles.DEFAULT.DEFAULT()); }
            case Ve vel ->    { return veColors.getOrDefault(vel, Styles.DEFAULT.DEFAULT()); }
            case Vf vfl ->    { return vfColors.getOrDefault(vfl, Styles.DEFAULT.DEFAULT()); }
            case Edge el ->   { return eColors .getOrDefault(el,  Styles.DEFAULT.DEFAULT()); }
            case Ev evl ->    { return evColors.getOrDefault(evl, Styles.DEFAULT.DEFAULT()); }
            case Ef efl ->    { return efColors.getOrDefault(efl, Styles.DEFAULT.DEFAULT()); }
            case Face fl ->   { return fColors .getOrDefault(fl,  Styles.DEFAULT.DEFAULT()); }
            case Fv fvl ->    { return fvColors.getOrDefault(fvl, Styles.DEFAULT.DEFAULT()); }
            case Fe fel ->    { return feColors.getOrDefault(fel, Styles.DEFAULT.DEFAULT()); }
            default -> throw new IllegalStateException("Unexpected value: " + l);
        }
    }

    // Methods to add and remove displayables
    public void addColorDisplay(Displayable d) { colorDisplayables.add(d); }
    public void removeColorDisplay(Displayable d) { colorDisplayables.remove(d); }
    public void addStringDisplay(Displayable d) { stringDisplayables.add(d); }
    public void removeStringDisplay(Displayable d) { stringDisplayables.remove(d); }

    /** Computes the colors of all loci based on the displayables. */
    private void computeColors() {
        HashSet<HashMap<Vertex, Color>> vColorsPrimary  = new HashSet<>();
        HashSet<HashMap<Ve,     Color>> veColorsPrimary = new HashSet<>();
        HashSet<HashMap<Vf,     Color>> vfColorsPrimary = new HashSet<>();
        HashSet<HashMap<Edge,   Color>> eColorsPrimary  = new HashSet<>();
        HashSet<HashMap<Ev,     Color>> evColorsPrimary = new HashSet<>();
        HashSet<HashMap<Ef,     Color>> efColorsPrimary = new HashSet<>();
        HashSet<HashMap<Face,   Color>> fColorsPrimary  = new HashSet<>();
        HashSet<HashMap<Fv,     Color>> fvColorsPrimary = new HashSet<>();
        HashSet<HashMap<Fe,     Color>> feColorsPrimary = new HashSet<>();

        v = ve = vf = e = ev = ef = f = fv = fe = false;
        for (Displayable d: colorDisplayables) {
            if (d.updatesV ()) { v  = true; vColorsPrimary .add(d.displayColorV()); }
            if (d.updatesVe()) { ve = true; veColorsPrimary.add(d.displayColorVe()); }
            if (d.updatesVf()) { vf = true; vfColorsPrimary.add(d.displayColorVf()); }
            if (d.updatesE ()) { e  = true; eColorsPrimary .add(d.displayColorE()); }
            if (d.updatesEv()) { ev = true; evColorsPrimary.add(d.displayColorEv()); }
            if (d.updatesEf()) { ef = true; efColorsPrimary.add(d.displayColorEf()); }
            if (d.updatesF ()) { f  = true; fColorsPrimary .add(d.displayColorF()); }
            if (d.updatesFv()) { fv = true; fvColorsPrimary.add(d.displayColorFv()); }
            if (d.updatesFe()) { fe = true; feColorsPrimary.add(d.displayColorFe()); }
        }

        if (v)  computeColor(medium.vertices, vColorsPrimary,  vColors );
        if (ve) computeColor(medium.ves,      veColorsPrimary, veColors);
        if (vf) computeColor(medium.vfs,      vfColorsPrimary, vfColors);
        if (e)  computeColor(medium.edges,    eColorsPrimary,  eColors );
        if (ev) computeColor(medium.evs,      evColorsPrimary, evColors);
        if (ef) computeColor(medium.efs,      efColorsPrimary, efColors);
        if (f)  computeColor(medium.faces,    fColorsPrimary,  fColors );
        if (fv) computeColor(medium.fvs,      fvColorsPrimary, fvColors);
        if (fe) computeColor(medium.fes,      feColorsPrimary, feColors);
    }

    /** Computes the color of a locus by averaging the colors of all displayables that update it. */
    private <L extends Locus> void computeColor(HashSet<L> loci, HashSet<HashMap<L, Color>> colorsPrimary, HashMap<L, Color> colors) {
        for (L l: loci) {
            int size = 0;
            double r, g, b;
            r = g = b = 0;
            for (HashMap<L, Color> c: colorsPrimary) {
                Color col = c.get(l);
                if (col == null) continue;

                size++;
                r += col.getRed();
                g += col.getGreen();
                b += col.getBlue();
            }
            if (size == 0) colors.put(l, Styles.DEFAULT.DEFAULT());
            else colors.put(l, new Color(r/size, g/size, b/size, 1));
        }
    }

    /** Computes the strings of all loci based on the displayables. */
    private void computeStrings() {
        strings = new HashMap<>();
        for (Displayable d: stringDisplayables) {
            if (d.updatesV ()) { strings.putAll(d.displayStringV ()); }
            if (d.updatesVe()) { strings.putAll(d.displayStringVe()); }
            if (d.updatesVf()) { strings.putAll(d.displayStringVf()); }
            if (d.updatesE ()) { strings.putAll(d.displayStringE ()); }
            if (d.updatesEv()) { strings.putAll(d.displayStringEv()); }
            if (d.updatesEf()) { strings.putAll(d.displayStringEf()); }
            if (d.updatesF ()) { strings.putAll(d.displayStringF ()); }
            if (d.updatesFv()) { strings.putAll(d.displayStringFv()); }
            if (d.updatesFe()) { strings.putAll(d.displayStringFe()); }
        }
    }

    /** Updates the size of the circle representing a locus based on the closest pair of loci currently drawn. */
    private void updateCircleSize(){
        ArrayList<Locus> drawnLoci = new ArrayList<>();
        if (v)  drawnLoci.addAll(medium.vertices);
        if (ve) drawnLoci.addAll(medium.ves);
        if (vf) drawnLoci.addAll(medium.vfs);
        if (e)  drawnLoci.addAll(medium.edges);
        if (ev) drawnLoci.addAll(medium.evs);
        if (ef) drawnLoci.addAll(medium.efs);
        if (f)  drawnLoci.addAll(medium.faces);
        if (fv) drawnLoci.addAll(medium.fvs);
        if (fe) drawnLoci.addAll(medium.fes);

        circleSize = (new ClosestPair(drawnLoci)).distance() * scale * 0.95;
        System.out.println("Circle size: " + circleSize);
    }
}
