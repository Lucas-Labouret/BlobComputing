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

    private final int iterations = 100;
    int[] mins = new int[iterations];
    int[] maxs = new int[iterations];

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
        int diff = Math.abs(max - min);
        if (diff >= 6) diff -= 4;

        boolean result = diff > 2;

        return result;
    }
}