package tests;

import medium.Medium;
import medium.locusS.Edge;
import medium.locusS.Face;
import medium.locusS.Vertex;
import medium.locusT.*;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/** Validates that transfer mappings are injective on raw and packed target coordinates. */
public final class MediumValidator {
    private static final int BREADTH_E = 2;
    private static final int BREADTH_F = 3;

    private MediumValidator() {}

    static void main(String[] args) {
        String mediumName = args.length > 0 ? args[0] : "small";
        Medium medium;
        try {
            medium = Medium.read(mediumName);
        } catch (IOException e) {
            System.err.println("Could not read medium '" + mediumName + "': " + e.getMessage());
            System.exit(2);
            return;
        }

        validateDuplicates(medium, mediumName);
        System.out.flush();
        System.out.println("\n--------------\n");
        validateTransfers(medium, mediumName);
    }

    private static void validateDuplicates(Medium medium, String mediumName) {
        boolean errorV = false;
        HashMap<Integer, HashSet<Integer>> validateV = new HashMap<>();
        for (Vertex v: medium.vertices) {
            int y = v.y;
            int x = v.x;

            validateV.putIfAbsent(y, new HashSet<>());
            if (validateV.get(y).contains(x)) {
                System.err.println("FAIL: duplicate vertex at (y=" + y + ", x=" + x + ") in medium '" + mediumName + "'.");
                errorV = true;
            } else {
                validateV.get(y).add(x);
            }
        }
        if (!errorV) System.out.println("PASS: no duplicate vertices found in medium '" + mediumName + "'.");

        boolean errorE = false;
        HashMap<Integer, HashMap<Integer, HashSet<Integer>>> validateE = new HashMap<>();
        for (Edge e: medium.edges) {
            int y = e.y;
            int x = e.x;
            int t = e.t;

            validateE.putIfAbsent(y, new HashMap<>());
            validateE.get(y).putIfAbsent(x, new HashSet<>());
            if (validateE.get(y).get(x).contains(t)) {
                System.err.println("FAIL: duplicate edge at (y=" + y + ", x=" + x + ", t=" + t + ") in medium '" + mediumName + "'.");
                errorE = true;
            } else {
                validateE.get(y).get(x).add(t);
            }
        }
        if (!errorE) System.out.println("PASS: no duplicate edges found in medium '" + mediumName + "'.");

        boolean errorF = false;
        HashMap<Integer, HashMap<Integer, HashSet<Integer>>> validateF = new HashMap<>();
        for (Face f: medium.faces) {
            int y = f.y;
            int x = f.x;
            int t = f.t;

            validateF.putIfAbsent(y, new HashMap<>());
            validateF.get(y).putIfAbsent(x, new HashSet<>());
            if (validateF.get(y).get(x).contains(t)) {
                System.err.println("FAIL: duplicate edge at (y=" + y + ", x=" + x + ", t=" + t + ") in medium '" + mediumName + "'.");
                errorF = true;
            } else {
                validateF.get(y).get(x).add(t);
            }
        }
        if (!errorF) System.out.println("PASS: no duplicate faces found in medium '" + mediumName + "'.");

        boolean errorVe = false;
        HashMap<Integer, HashMap<Integer, HashSet<Integer>>> validateVe = new HashMap<>();
        for (Ve ve: medium.ves) {
            int y = ve.y;
            int x = ve.x;
            int t = ve.s;

            validateVe.putIfAbsent(y, new HashMap<>());
            validateVe.get(y).putIfAbsent(x, new HashSet<>());
            if (validateVe.get(y).get(x).contains(t)) {
                System.err.println("FAIL: duplicate Ve at (y=" + y + ", x=" + x + ", t=" + t + ") in medium '" + mediumName + "'.");
                errorVe = true;
            } else {
                validateVe.get(y).get(x).add(t);
            }
        }
        if (!errorVe) System.out.println("PASS: no duplicate Ves found in medium '" + mediumName + "'.");

        boolean errorVf = false;
        HashMap<Integer, HashMap<Integer, HashSet<Integer>>> validateVf = new HashMap<>();
        for (Vf vf: medium.vfs) {
            int y = vf.y;
            int x = vf.x;
            int t = vf.s;

            validateVf.putIfAbsent(y, new HashMap<>());
            validateVf.get(y).putIfAbsent(x, new HashSet<>());
            if (validateVf.get(y).get(x).contains(t)) {
                System.err.println("FAIL: duplicate Vf at (y=" + y + ", x=" + x + ", t=" + t + ") in medium '" + mediumName + "'.");
                errorVf = true;
            } else {
                validateVf.get(y).get(x).add(t);
            }
        }
        if (!errorVf) System.out.println("PASS: no duplicate Vfs found in medium '" + mediumName + "'.");

        boolean errorEv = false;
        HashMap<Integer, HashMap<Integer, HashMap<Integer, HashSet<Integer>>>> validateEv = new HashMap<>();
        for (Ev ev: medium.evs) {
            int y = ev.y;
            int x = ev.x;
            int t = ev.t;
            int s = ev.s;

            validateEv.putIfAbsent(y, new HashMap<>());
            validateEv.get(y).putIfAbsent(x, new HashMap<>());
            validateEv.get(y).get(x).putIfAbsent(t, new HashSet<>());
            if (validateEv.get(y).get(x).get(t).contains(s)) {
                System.err.println("FAIL: duplicate Ev at (y=" + y + ", x=" + x + ", t=" + t + ", s=" + s + ") in medium '" + mediumName + "'.");
                errorEv = true;
            } else {
                validateEv.get(y).get(x).get(t).add(s);
            }
        }
        if (!errorEv) System.out.println("PASS: no duplicate Evs found in medium '" + mediumName + "'.");

        boolean errorEf = false;
        HashMap<Integer, HashMap<Integer, HashMap<Integer, HashSet<Integer>>>> validateEf = new HashMap<>();
        for (Ef ef: medium.efs) {
            int y = ef.y;
            int x = ef.x;
            int t = ef.t;
            int s = ef.s;

            validateEf.putIfAbsent(y, new HashMap<>());
            validateEf.get(y).putIfAbsent(x, new HashMap<>());
            validateEf.get(y).get(x).putIfAbsent(t, new HashSet<>());
            if (validateEf.get(y).get(x).get(t).contains(s)) {
                System.err.println("FAIL: duplicate Ef at (y=" + y + ", x=" + x + ", t=" + t + ", s=" + s + ") in medium '" + mediumName + "'.");
                errorEf = true;
            } else {
                validateEf.get(y).get(x).get(t).add(s);
            }
        }
        if (!errorEf) System.out.println("PASS: no duplicate Efs found in medium '" + mediumName + "'.");

        boolean errorFv = false;
        HashMap<Integer, HashMap<Integer, HashMap<Integer, HashSet<Integer>>>> validateFv = new HashMap<>();
        HashSet<Fv> fvDuplicates = new HashSet<>();
        for (Fv fv: medium.fvs) {
            int y = fv.y;
            int x = fv.x;
            int t = fv.t;
            int s = fv.s;

            validateFv.putIfAbsent(y, new HashMap<>());
            validateFv.get(y).putIfAbsent(x, new HashMap<>());
            validateFv.get(y).get(x).putIfAbsent(t, new HashSet<>());
            if (validateFv.get(y).get(x).get(t).contains(s)) {
                System.err.println("FAIL: duplicate Fv at (y=" + y + ", x=" + x + ", t=" + t + ", s=" + s + ") in medium '" + mediumName + "'.");
                fvDuplicates.add(fv);
                errorFv = true;
            } else {
                validateFv.get(y).get(x).get(t).add(s);
            }
        }
        if (!errorFv) System.out.println("PASS: no duplicate Fvs found in medium '" + mediumName + "'.");
        else for (Fv dup : fvDuplicates) for (Fv fv : medium.fvs) {
            if (fv.y == dup.y && fv.x == dup.x && fv.t == dup.t) {
                System.err.println("  Face(y=" + fv.y + ", x=" + fv.x + ", t=" + fv.t + ") has side " + fv.s);
            }
        }

        boolean errorFe = false;
        HashMap<Integer, HashMap<Integer, HashMap<Integer, HashSet<Integer>>>> validateFe = new HashMap<>();
        HashSet<Fe> feDuplicates = new HashSet<>();
        for (Fe fe: medium.fes) {
            int y = fe.y;
            int x = fe.x;
            int t = fe.t;
            int s = fe.s;

            validateFe.putIfAbsent(y, new HashMap<>());
            validateFe.get(y).putIfAbsent(x, new HashMap<>());
            validateFe.get(y).get(x).putIfAbsent(t, new HashSet<>());
            if (validateFe.get(y).get(x).get(t).contains(s)) {
                System.err.println("FAIL: duplicate Fe at (y=" + y + ", x=" + x + ", t=" + t + ", s=" + s + ") in medium '" + mediumName + "'.");
                feDuplicates.add(fe);
                errorFe = true;
            } else {
                validateFe.get(y).get(x).get(t).add(s);
            }
        }
        if (!errorFe) System.out.println("PASS: no duplicate Fes found in medium '" + mediumName + "'.");
        else for (Fe dup : feDuplicates) for (Fe fe : medium.fes) {
            if (fe.y == dup.y && fe.x == dup.x && fe.t == dup.t) {
                System.err.println("  Face(y=" + fe.y + ", x=" + fe.x + ", t=" + fe.t + ") has side " + fe.s);
            }
        }
    }

    private static void validateTransfers(Medium medium, String mediumName) {
        Dimensions d = computeDimensions(medium);
        int total = 0;

        total += validateVeToEv(medium, d);
        total += validateVfToFv(medium, d);
        total += validateEfToFe(medium, d);

        if (total == 0) {
            System.out.println("PASS: no transfer collisions found in medium '" + mediumName + "'.");
        } else {
            System.err.println("FAIL: " + total + " transfer collision(s) found in medium '" + mediumName + "'.");
            System.exit(1);
        }
    }

    private static int validateVeToEv(Medium medium, Dimensions d) {
        HashMap<String, String> seenRaw = new HashMap<>();
        HashMap<String, String> seenFlat = new HashMap<>();
        int collisions = 0;

        List<Ve> sorted = medium.ves.stream()
                .sorted(Comparator.comparingInt((Ve v) -> v.y)
                        .thenComparingInt(v -> v.s)
                        .thenComparingInt(v -> v.x))
                .toList();

        for (Ve ve : sorted) {
            Ev ev = ve.getPair();
            if (ev == null) {
                System.err.println("Ve without pair: " + veDesc(ve));
                collisions++;
                continue;
            }
            String src = veDesc(ve) + " -> " + evDesc(ev);

            String rawKey = ev.y + ":" + ev.t + ":" + ev.s + ":" + ev.x;
            collisions += register(seenRaw, rawKey, src, "Ve->Ev/raw");

            String flatKey = evFlat(ev, d) + ":" + ev.x;
            collisions += register(seenFlat, flatKey, src, "Ve->Ev/flat");
        }

        printSummary("Ve->Ev", collisions);
        return collisions;
    }

    private static int validateVfToFv(Medium medium, Dimensions d) {
        HashMap<String, String> seenRaw = new HashMap<>();
        HashMap<String, String> seenFlat = new HashMap<>();
        int collisions = 0;

        List<Vf> sorted = medium.vfs.stream()
                .sorted(Comparator.comparingInt((Vf v) -> v.y)
                        .thenComparingInt(v -> v.s)
                        .thenComparingInt(v -> v.x))
                .toList();

        for (Vf vf : sorted) {
            Fv fv = vf.getPair();
            if (fv == null) {
                System.err.println("Vf without pair: " + vfDesc(vf));
                collisions++;
                continue;
            }
            String src = vfDesc(vf) + " -> " + fvDesc(fv);

            String rawKey = fv.y + ":" + fv.t + ":" + fv.s + ":" + fv.x;
            collisions += register(seenRaw, rawKey, src, "Vf->Fv/raw");

            String flatKey = fvFlat(fv, d) + ":" + fv.x;
            collisions += register(seenFlat, flatKey, src, "Vf->Fv/flat");
        }

        printSummary("Vf->Fv", collisions);
        return collisions;
    }

    private static int validateEfToFe(Medium medium, Dimensions d) {
        HashMap<String, String> seenRaw = new HashMap<>();
        HashMap<String, String> seenFlat = new HashMap<>();
        int collisions = 0;

        List<Ef> sorted = medium.efs.stream()
                .sorted(Comparator.comparingInt((Ef e) -> e.y)
                        .thenComparingInt(e -> e.t)
                        .thenComparingInt(e -> e.s)
                        .thenComparingInt(e -> e.x))
                .toList();

        for (Ef ef : sorted) {
            Fe fe = ef.getPair();
            if (fe == null) {
                System.err.println("Ef without pair: " + efDesc(ef));
                collisions++;
                continue;
            }
            String src = efDesc(ef) + " -> " + feDesc(fe);

            String rawKey = fe.y + ":" + fe.t + ":" + fe.s + ":" + fe.x;
            collisions += register(seenRaw, rawKey, src, "Ef->Fe/raw");

            String flatKey = feFlat(fe, d) + ":" + fe.x;
            collisions += register(seenFlat, flatKey, src, "Ef->Fe/flat");
        }

        printSummary("Ef->Fe", collisions);
        return collisions;
    }

    private static int register(HashMap<String, String> seen, String key, String src, String label) {
        String prev = seen.putIfAbsent(key, src);
        if (prev == null || prev.equals(src)) {
            return 0;
        }

        System.err.println("Collision [" + label + "] target=" + key);
        System.err.println("  previous: " + prev);
        System.err.println("  new:      " + src);
        return 1;
    }

    private static void printSummary(String label, int collisions) {
        if (collisions == 0) {
            System.out.println(label + ": OK");
        } else {
            System.err.println(label + ": " + collisions + " collision(s)");
        }
    }

    private static int evFlat(Ev ev, Dimensions d) {
        return (ev.y * d.spanE + ev.t) * BREADTH_E + ev.s;
    }

    private static int fvFlat(Fv fv, Dimensions d) {
        return (fv.y * d.spanF + fv.t) * BREADTH_F + fv.s;
    }

    private static int feFlat(Fe fe, Dimensions d) {
        return (fe.y * d.spanF + fe.t) * BREADTH_F + fe.s;
    }

    private static Dimensions computeDimensions(Medium medium) {
        int spanE = medium.edges.stream().mapToInt(e -> e.t).max().orElse(0) + 1;
        int spanF = medium.faces.stream().mapToInt(f -> f.t).max().orElse(0) + 1;
        return new Dimensions(spanE, spanF);
    }

    private static String veDesc(Ve ve) {
        return "Ve(y=" + ve.y + ",t=" + ve.s + ",x=" + ve.x + ")";
    }

    private static String evDesc(Ev ev) {
        return "Ev(y=" + ev.y + ",t=" + ev.t + ",s=" + ev.s + ",x=" + ev.x + ")";
    }

    private static String vfDesc(Vf vf) {
        return "Vf(y=" + vf.y + ",t=" + vf.s + ",x=" + vf.x + ")";
    }

    private static String fvDesc(Fv fv) {
        return "Fv(y=" + fv.y + ",t=" + fv.t + ",s=" + fv.s + ",x=" + fv.x + ")";
    }

    private static String efDesc(Ef ef) {
        return "Ef(y=" + ef.y + ",t=" + ef.t + ",s=" + ef.s + ",x=" + ef.x + ")";
    }

    private static String feDesc(Fe fe) {
        return "Fe(y=" + fe.y + ",t=" + fe.t + ",s=" + fe.s + ",x=" + fe.x + ")";
    }

    private record Dimensions(int spanE, int spanF) {}
}


