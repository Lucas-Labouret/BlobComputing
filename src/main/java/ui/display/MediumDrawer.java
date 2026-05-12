package ui.display;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import medium.Locus;
import medium.Medium;
import medium.locusS.*;
import medium.locusT.*;

import java.util.*;

public class MediumDrawer extends Canvas {
    // Scale of the drawing relative to the medium
    private static final double SCALE_TARGET = 10000;
    private final double scale;
    private final double offSet;

    // The medium to be drawn
    private final Medium medium;

    // Which loci should be shown
    private boolean v  = true;
    private boolean ve = false;
    private boolean vf = false;
    private boolean e  = false;
    private boolean ev = false;
    private boolean ef = false;
    private boolean f  = false;
    private boolean fv = false;
    private boolean fe = false;

    private boolean lastv  = false;
    private boolean lastve = false;
    private boolean lastvf = false;
    private boolean laste  = false;
    private boolean lastev = false;
    private boolean lastef = false;
    private boolean lastf  = false;
    private boolean lastfv = false;
    private boolean lastfe = false;

    private HashMap<Vertex, Color> vColors  = new HashMap<>();
    private HashMap<Ve, Color>     veColors = new HashMap<>();
    private HashMap<Vf, Color>     vfColors = new HashMap<>();
    private HashMap<Edge, Color>   eColors  = new HashMap<>();
    private HashMap<Ev, Color>     evColors = new HashMap<>();
    private HashMap<Ef, Color>     efColors = new HashMap<>();
    private HashMap<Face, Color>   fColors  = new HashMap<>();
    private HashMap<Fv, Color>     fvColors = new HashMap<>();
    private HashMap<Fe, Color>     feColors = new HashMap<>();

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
        setHeight(height * scale + 2*offSet);
        setWidth(width * scale + 2*offSet);

        // Draw grid
        draw();
    }

    public void draw() {
        gc.clearRect(0, 0, getWidth(), getHeight());

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
    private double moveTriangle(double ct, double cl) { return ct * circleSize + (cl + offSet) * scale; }
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
            case Vertex vl -> { return vColors.getOrDefault (vl,  Globals.DEFAULT); }
            case Ve vel ->    { return veColors.getOrDefault(vel, Globals.DEFAULT); }
            case Vf vfl ->    { return vfColors.getOrDefault(vfl, Globals.DEFAULT); }
            case Edge el ->   { return eColors.getOrDefault (el,  Globals.DEFAULT); }
            case Ev evl ->    { return evColors.getOrDefault(evl, Globals.DEFAULT); }
            case Ef efl ->    { return efColors.getOrDefault(efl, Globals.DEFAULT); }
            case Face fl ->   { return fColors.getOrDefault (fl,  Globals.DEFAULT); }
            case Fv fvl ->    { return fvColors.getOrDefault(fvl, Globals.DEFAULT); }
            case Fe fel ->    { return feColors.getOrDefault(fel, Globals.DEFAULT); }
            default -> throw new IllegalStateException("Unexpected value: " + l);
        }
    }

    public void setDrawV (boolean b) { v  = b; }
    public void setDrawVe(boolean b) { ve = b; }
    public void setDrawVf(boolean b) { vf = b; }
    public void setDrawE (boolean b) { e  = b; }
    public void setDrawEv(boolean b) { ev = b; }
    public void setDrawEf(boolean b) { ef = b; }
    public void setDrawF (boolean b) { f  = b; }
    public void setDrawFv(boolean b) { fv = b; }
    public void setDrawFe(boolean b) { fe = b; }

    public void setVColors (HashMap<Vertex, Color> vColors)  { this.vColors  = vColors;  }
    public void setVeColors(HashMap<Ve,     Color> veColors) { this.veColors = veColors; }
    public void setVfColors(HashMap<Vf,     Color> vfColors) { this.vfColors = vfColors; }
    public void setEColors (HashMap<Edge,   Color> eColors)  { this.eColors  = eColors;  }
    public void setEvColors(HashMap<Ev,     Color> evColors) { this.evColors = evColors; }
    public void setEfColors(HashMap<Ef,     Color> efColors) { this.efColors = efColors; }
    public void setFColors (HashMap<Face,   Color> fColors)  { this.fColors  = fColors;  }
    public void setFvColors(HashMap<Fv,     Color> fvColors) { this.fvColors = fvColors; }
    public void setFeColors(HashMap<Fe,     Color> feColors) { this.feColors = feColors; }



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

/**
 *  The {@code ClosestPair} data type computes the closest pair of points
 *  in a set of <em>n</em> points in the plane and provides accessor methods
 *  for getting the closest pair of points and the distance between them.
 *  The distance between two points is their Euclidean distance.
 *  <p>
 *  This implementation uses a divide-and-conquer algorithm.
 *  It runs in O(<em>n</em> log <em>n</em>) time in the worst case and uses
 *  O(<em>n</em>) extra space.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/99hull">Section 9.9</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
class ClosestPair {

    // closest pair of points and their Euclidean distance
    private Locus best1, best2;
    private double bestDistance = Double.POSITIVE_INFINITY;

    /**
     * Computes the closest pair of points in the specified array of points.
     *
     * @param  points the array of points
     * @throws IllegalArgumentException if {@code points} is {@code null} or if any
     *         entry in {@code points[]} is {@code null}
     */
    public ClosestPair(ArrayList<Locus> points) {
        if (points == null) throw new IllegalArgumentException("constructor argument is null");
        for (int i = 0; i < points.size(); i++) {
            if (points.get(i) == null) throw new IllegalArgumentException("array element " + i + " is null");
        }

        int n = points.size();
        if (n <= 1) return;

        // sort by x-coordinate (breaking ties by y-coordinate via stability)
        ArrayList<Locus> pointsByX = new ArrayList<>(points);
        pointsByX.sort(Comparator.comparingDouble(a -> a.h));
        pointsByX.sort(Comparator.comparingDouble(a -> a.w));

        // check for coincident points
        for (int i = 0; i < n-1; i++) {
            if (pointsByX.get(i).equals(pointsByX.get(i+1))) {
                bestDistance = 0.0;
                best1 = pointsByX.get(i);
                best2 = pointsByX.get(i+1);
                return;
            }
        }

        // sort by y-coordinate (but not yet sorted)
        ArrayList<Locus> pointsByY = new ArrayList<>(points);
        pointsByY.sort(Comparator.comparingDouble(a -> a.h));

        // auxiliary array
        ArrayList<Locus> aux = new ArrayList<>(Collections.nCopies(n, null));

        closest(pointsByX, pointsByY, aux, 0, n-1);

        bestDistance = Math.sqrt(bestDistance);
    }

    // find the closest pair of points in pointsByX[lo..hi]
    // precondition:  pointsByX[lo..hi] and pointsByY[lo..hi] are the same sequence of points
    // precondition:  pointsByX[lo..hi] sorted by x-coordinate
    // postcondition: pointsByY[lo..hi] sorted by y-coordinate
    private double closest(ArrayList<Locus> pointsByX, ArrayList<Locus> pointsByY, ArrayList<Locus> aux, int lo, int hi) {
        if (hi <= lo) return Double.POSITIVE_INFINITY;

        int mid = lo + (hi - lo) / 2;
        Locus median = pointsByX.get(mid);

        // compute the closest pair with both endpoints in left subarray or both in right subarray
        double delta1 = closest(pointsByX, pointsByY, aux, lo, mid);
        double delta2 = closest(pointsByX, pointsByY, aux, mid+1, hi);
        double delta = Math.min(delta1, delta2);

        // merge back so that pointsByY[lo..hi] are sorted by y-coordinate
        merge(pointsByY, aux, lo, mid, hi);

        // aux[0..m-1] = sequence of points closer than delta, sorted by y-coordinate
        int m = 0;
        for (int i = lo; i <= hi; i++) {
            double dw = Math.abs(pointsByY.get(i).w - median.w);
            if (dw * dw < delta)
                aux.set(m++, pointsByY.get(i));
        }

        // compare each point to its neighbors with y-coordinate closer than delta
        for (int i = 0; i < m; i++) {
            // a geometric packing argument shows that this loop iterates at most 7 times
            for (int j = i + 1; j < m; j++) {
                double dy = aux.get(j).h - aux.get(i).h;
                if (dy * dy >= delta) break;
                Locus auxi = aux.get(i);
                Locus auxj = aux.get(j);
                double distance = (auxi.w - auxj.w) * (auxi.w - auxj.w) + (auxi.h - auxj.h) * (auxi.h - auxj.h);
                if (distance < delta) {
                    delta = distance;
                    if (distance < bestDistance) {
                        bestDistance = delta;
                        best1 = auxi;
                        best2 = auxj;
                    }
                }
            }
        }
        return delta;
    }

    /**
     * Returns one of the points in the closest pair of points.
     *
     * @return one of the two points in the closest pair of points;
     *         {@code null} if no such point (because there are fewer than 2 points)
     */
    public Locus either() {
        return best1;
    }

    /**
     * Returns the other point in the closest pair of points.
     *
     * @return the other point in the closest pair of points
     *         {@code null} if no such point (because there are fewer than 2 points)
     */
    public Locus other() {
        return best2;
    }

    /**
     * Returns the Euclidean distance between the closest pair of points.
     *
     * @return the Euclidean distance between the closest pair of points
     *         {@code Double.POSITIVE_INFINITY} if no such pair of points
     *         exist (because there are fewer than 2 points)
     */
    public double distance() {
        return bestDistance;
    }

    // stably merge a[lo .. mid] with a[mid+1 ..hi] using aux[lo .. hi]
    // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
    private static void merge(ArrayList<Locus> a, ArrayList<Locus> aux, int lo, int mid, int hi) {
        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux.set(k, a.get(k));
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)                      a.set(k, aux.get(j++));
            else if (j > hi)                       a.set(k, aux.get(i++));
            else if (aux.get(j).h  < aux.get(i).h) a.set(k, aux.get(j++));
            else                                   a.set(k, aux.get(i++));
        }
    }
}
