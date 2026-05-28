package ui.display;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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

public class MediumDrawer extends Canvas {
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
    private final HashSet<Displayable> displayables = new HashSet<>();

    private final HashMap<Vertex, Color> vColors  = new HashMap<>();
    private final HashMap<Ve, Color>     veColors = new HashMap<>();
    private final HashMap<Vf, Color>     vfColors = new HashMap<>();
    private final HashMap<Edge, Color>   eColors  = new HashMap<>();
    private final HashMap<Ev, Color>     evColors = new HashMap<>();
    private final HashMap<Ef, Color>     efColors = new HashMap<>();
    private final HashMap<Face, Color>   fColors  = new HashMap<>();
    private final HashMap<Fv, Color>     fvColors = new HashMap<>();
    private final HashMap<Fe, Color>     feColors = new HashMap<>();

    // The size of circle representing a locus
    private double circleSize;

    private final GraphicsContext gc = getGraphicsContext2D();

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

    public void draw() {
        gc.clearRect(0, 0, getWidth(), getHeight());

        computeColors();

        if (lastv != v || lastve != ve || lastvf != vf || laste != e || lastev != ev || lastef != ef || lastf != f || lastfv != fv || lastfe != fe) {
            updateCircleSize();
            lastv = v; lastve = ve; lastvf = vf; laste = e; lastev = ev; lastef = ef; lastf = f; lastfv = fv; lastfe = fe;
        }

        if (v ) for (Vertex l : medium.vertices) drawV(l);
        if (ve) for (Ve     l : medium.ves)      drawTransfer(l);
        if (vf) for (Vf     l : medium.vfs)      drawTransfer(l);
        if (e ) for (Edge   l : medium.edges)    drawE(l);
        if (ev) for (Ev     l : medium.evs)      drawTransfer(l);
        if (ef) for (Ef     l : medium.efs)      drawTransfer(l);
        if (f ) for (Face   l : medium.faces)    drawF(l);
        if (fv) for (Fv     l : medium.fvs)      drawTransfer(l);
        if (fe) for (Fe     l : medium.fes)      drawTransfer(l);
    }

    private void drawV(Vertex l){
        gc.setFill(getColor(l));
        gc.fillOval((l.w+offSet)*scale - circleSize/2, (l.h+offSet)*scale - circleSize/2, circleSize, circleSize);
    }

    private void drawE(Edge l){
        gc.setFill(getColor(l));
        gc.fillRect((l.w+offSet)*scale - circleSize/2, (l.h+offSet)*scale - circleSize/2, circleSize, circleSize);
    }

    // equilateral triangle inscribed in unit circle
    private static final double ax = -0.866;
    private static final double ay = -0.5;
    private static final double bx = 0.866;
    private static final double by = -0.5;
    private static final double cx = 0.0;
    private static final double cy = 1.0;
    private double moveTriangle(double ct, double cl) { return ct * circleSize/2 + (cl + offSet) * scale; }
    private void drawF(Face l){
        gc.setFill(getColor(l));
        gc.fillPolygon(new double[]{moveTriangle(ax, l.w), moveTriangle(bx, l.w), moveTriangle(cx, l.w)},
                       new double[]{moveTriangle(ay, l.h), moveTriangle(by, l.h), moveTriangle(cy, l.h)}, 3);
    }

    private void drawTransfer(Locus l){
        gc.setFill(getColor(l));
        gc.fillRect((l.w+offSet)*scale - circleSize/4, (l.h+offSet)*scale - circleSize/4, circleSize/2, circleSize/2);
    }

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

    public void addDisplay(Displayable d) { displayables.add(d); }
    public void removeDisplay(Displayable d) { displayables.remove(d); }

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
        for (Displayable d: displayables) {
            if (d.updatesV ()) { v  = true; vColorsPrimary .add(d.displayV (medium)); }
            if (d.updatesVe()) { ve = true; veColorsPrimary.add(d.displayVe(medium)); }
            if (d.updatesVf()) { vf = true; vfColorsPrimary.add(d.displayVf(medium)); }
            if (d.updatesE ()) { e  = true; eColorsPrimary .add(d.displayE (medium)); }
            if (d.updatesEv()) { ev = true; evColorsPrimary.add(d.displayEv(medium)); }
            if (d.updatesEf()) { ef = true; efColorsPrimary.add(d.displayEf(medium)); }
            if (d.updatesF ()) { f  = true; fColorsPrimary .add(d.displayF (medium)); }
            if (d.updatesFv()) { fv = true; fvColorsPrimary.add(d.displayFv(medium)); }
            if (d.updatesFe()) { fe = true; feColorsPrimary.add(d.displayFe(medium)); }
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

    private <L extends Locus> void computeColor(HashSet<L> loci, HashSet<HashMap<L, Color>> colorsPrimary, HashMap<L, Color> colors) {
        int size = colorsPrimary.size();
        for (L l: loci) {
            double r, g, b;
            r = g = b = 0;
            for (HashMap<L, Color> c: colorsPrimary) {
                Color col = c.get(l);
                r += col.getRed();
                g += col.getGreen();
                b += col.getBlue();
            }
            colors.put(l, new Color(r/size, g/size, b/size, 1));
        }
    }

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
