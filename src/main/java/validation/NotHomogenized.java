package validation;

import language.field.boolField.BoolV;
import language.field.intField.IntV;
import medium.locusS.Vertex;

import java.util.HashMap;
import java.util.HashSet;

public class NotHomogenized extends GlobalValidator {
    private final BoolV particles;
    private  final IntV distance;

    public NotHomogenized(BoolV particles, IntV distance) {
        this.particles = particles;
        this.distance = distance;
        super(true);
    }

    private final int maxIter = 1;
    private int iter = 0;
    private int index = 0;
    private final int[] mins = new int[maxIter];
    private final int[] maxs = new int[maxIter];

    @Override
    protected boolean _validate() {
        HashMap<Vertex, Boolean> particlesMap = particles.decode();
        HashMap<Vertex, Integer> distanceMap = distance.decode();

        HashSet<Integer> distanceSet = new HashSet<>();
        for (Vertex v : particlesMap.keySet())
            if (particlesMap.get(v)) distanceSet.add(distanceMap.get(v));

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int d : distanceSet) {
            if (d < min) min = d;
            if (d > max) max = d;
        }

        iter++;
        mins[index] = min;
        maxs[index] = max;
        index = (index + 1) % maxIter;

        for (int i = 0; i < maxIter; i++) {
            if (mins[i] < min) min = mins[i];
            if (maxs[i] > max) max = maxs[i];
        }
        int diff = (max>>1) - (min>>1);
        System.out.println(diff);
        return diff > 1 || iter < maxIter || iter < 100;
    }
}