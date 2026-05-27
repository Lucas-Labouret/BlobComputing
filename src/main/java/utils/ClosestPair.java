package utils;

import medium.Locus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
public class ClosestPair {

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

