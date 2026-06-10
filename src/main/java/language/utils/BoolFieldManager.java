package language.utils;

import language.field.boolField.*;
import medium.Medium;
import medium.locusS.Edge;
import medium.locusS.Face;
import medium.locusS.Vertex;
import medium.locusT.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;

/** Stores the global language.field-dimension configuration shared by language.field types. */
public final class BoolFieldManager {
    // Block instantiation
    private BoolFieldManager(){}

    // Medium
    private static Medium medium;

    // Max nb of vertices in a column
    private static int height;
    // Max nb of vertices in a line
    private static int width;
    // Max number of Edges belonging to a single vertex
    private static int spanE;
    // Max number of Faces belonging to a single vertex
    private static int spanF;
    // Max nb of Ve/Vf around a vertex
    private static int breadthV;
    // Max nb of Ev/Ef around an edge
    private static final int breadthE = 2;
    // Max nb of Fv/Fe around a face
    private static final int breadthF = 3;

    private static Boolean SETUP_DONE = false;
    /** Initializes the global language.field configuration from the given medium. */
    public static void setup(Medium m) {
        if (SETUP_DONE) throw new IllegalStateException("Setup has already been done.");
        SETUP_DONE = true;

        medium = m;

        width = height = breadthV = 0;
        for (Ve ve: medium.ves){
            if (ve.y > height) height = ve.y;
            if (ve.x > width) width = ve.x;
            if (ve.s > breadthV) breadthV = ve.s;
        }
        width++; height++; breadthV++;

        spanE = 0;
        for (Edge e : medium.edges){
            if (e.t > spanE) spanE = e.t;
        }
        spanE++;

        spanF = 0;
        for (Face f : medium.faces){
            if (f.t > spanF) spanF = f.t;
        }
        spanF++;

        BoolFieldLine.SET_PARAMS(width);
        BoolV.SET_PARAMS(height);
        BoolE.SET_PARAMS(height, spanE);
        BoolF.SET_PARAMS(height, spanF);
        BoolVe.SET_PARAMS(height, breadthV);
        BoolVf.SET_PARAMS(height, breadthV);
        BoolEv.SET_PARAMS(height, spanE);
        BoolEf.SET_PARAMS(height, spanE);
        BoolFv.SET_PARAMS(height, spanF);
        BoolFe.SET_PARAMS(height, spanF);

        setDataPosV();
        setDataPosE();
        setDataPosF();

        setMasksVe();
        setMasksVf();
        setMasksEv();
        setMasksEf();
        setMasksFv();
        setMasksFe();

        setBorders();
    }

    public static int getWidth(){ return width; }
    public static int getHeight(){ return height; }
    public static int getSpanE(){ return spanE; }
    public static int getSpanF(){ return spanF; }
    public static int getBreadthV(){ return breadthV; }
    public static int getBreadthE(){ return breadthE; }
    public static int getBreadthF(){ return breadthF; }

    private static void setDataPosV() {
        BoolV dataPos = BoolV.zeroes(Border.MIRROR);
        for (Vertex v: medium.vertices)
            BoolV.setBit(dataPos, v, true);
        BoolV.setDataPos(dataPos);
    }

    private static void setDataPosE() {
        BoolE dataPos = BoolE.zeroes(Border.MIRROR);
        for (Edge e: medium.edges)
            BoolE.setBit(dataPos, e, true);
        BoolE.setDataPos(dataPos);
    }

    private static void setDataPosF() {
        BoolF dataPos = BoolF.zeroes(Border.MIRROR);
        for (Face f: medium.faces)
            BoolF.setBit(dataPos, f, true);
        BoolF.setDataPos(dataPos);
    }

    private static int veFlat(int y, int s){ return y * breadthV + s; }
    private static int vfFlat(int y, int s){ return y * breadthV + s; }
    private static int evFlat(int y, int t, int s){ return (y * spanE + t) * breadthE + s; }
    private static int efFlat(int y, int t, int s){ return (y * spanE + t) * breadthE + s; }
    private static int fvFlat(int y, int t, int s){ return (y * spanF + t) * breadthF + s; }
    private static int feFlat(int y, int t, int s){ return (y * spanF + t) * breadthF + s; }

    private static void setMasksVe(){
        BoolVe pos = BoolVe.zeroes(Border.MIRROR);
        HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks = new HashMap<>();

        for (Ve ve: medium.ves) {
            BoolVe.setBit(pos, ve, true);

            Ev ev = ve.getPair();

            Coord2D startCoord =  new Coord2D(veFlat(ve.y, ve.s), ve.x/32);
            Coord2D endCoord = new Coord2D(evFlat(ev.y, ev.t, ev.s), ev.x/32);
            int shift = ev.x%32 - ve.x%32;

            masks.computeIfAbsent(startCoord, _ -> new HashMap<>())
                 .computeIfAbsent(endCoord, _ -> new HashMap<>())
                 .putIfAbsent(shift, 0);

            masks.get(startCoord).get(endCoord).put(shift, masks.get(startCoord).get(endCoord).get(shift) |
                    (1 << (31 - ve.x%32))
            );
        }

        BoolVe.SET_MASKS(pos, masks);
    }

    private static void setMasksVf(){
        BoolVf pos = BoolVf.zeroes(Border.MIRROR);
        HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks = new HashMap<>();

        for (Vf vf: medium.vfs) {
            BoolVf.setBit(pos, vf, true);

            Fv fv = vf.getPair();

            Coord2D startCoord =  new Coord2D(vfFlat(vf.y, vf.s), vf.x/32);
            Coord2D endCoord = new Coord2D(fvFlat(fv.y, fv.t, fv.s), fv.x/32);
            int shift = fv.x%32 - vf.x%32;

            masks.computeIfAbsent(startCoord, _ -> new HashMap<>())
                 .computeIfAbsent(endCoord, _ -> new HashMap<>())
                 .putIfAbsent(shift, 0);

            masks.get(startCoord).get(endCoord).put(shift, masks.get(startCoord).get(endCoord).get(shift) |
                    (1 << (31 - vf.x%32))
            );
        }

        BoolVf.SET_MASKS(pos, masks);
    }

    private static void setMasksEv(){
        BoolEv pos = BoolEv.zeroes(Border.MIRROR);
        HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks = new HashMap<>();

        for (Ev ev: medium.evs) {
            BoolEv.setBit(pos, ev, true);

            Ve ve = ev.getPair();

            Coord2D startCoord = new Coord2D(evFlat(ev.y, ev.t, ev.s), ev.x/32);
            Coord2D endCoord =  new Coord2D(veFlat(ve.y, ve.s), ve.x/32);
            int shift = ve.x%32 - ev.x%32;

            masks.computeIfAbsent(startCoord, _ -> new HashMap<>())
                 .computeIfAbsent(endCoord, _ -> new HashMap<>())
                 .putIfAbsent(shift, 0);

            masks.get(startCoord).get(endCoord).put(shift, masks.get(startCoord).get(endCoord).get(shift) |
                    (1 << (31 - ev.x%32))
            );
        }

        BoolEv.SET_MASKS(pos, masks);
    }

    private static void setMasksEf(){
        BoolEf pos = BoolEf.zeroes(Border.MIRROR);
        HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks = new HashMap<>();

        for (Ef ef: medium.efs) {
            BoolEf.setBit(pos, ef, true);

            Fe fe = ef.getPair();

            Coord2D startCoord =  new Coord2D(efFlat(ef.y, ef.t, ef.s), ef.x/32);
            Coord2D endCoord = new Coord2D(feFlat(fe.y, fe.t, fe.s), fe.x/32);
            int shift = fe.x%32 - ef.x%32;

            masks.computeIfAbsent(startCoord, _ -> new HashMap<>())
                 .computeIfAbsent(endCoord, _ -> new HashMap<>())
                 .putIfAbsent(shift, 0);

            masks.get(startCoord).get(endCoord).put(shift, masks.get(startCoord).get(endCoord).get(shift) |
                    (1 << (31 - ef.x%32))
            );
        }

        BoolEf.SET_MASKS(pos, masks);
    }

    private static void setMasksFv(){
        BoolFv pos = BoolFv.zeroes(Border.MIRROR);
        HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks = new HashMap<>();

        for (Fv fv: medium.fvs) {
            BoolFv.setBit(pos, fv, true);

            Vf vf = fv.getPair();

            Coord2D startCoord =  new Coord2D(fvFlat(fv.y, fv.t, fv.s), fv.x/32);
            Coord2D endCoord = new Coord2D(vfFlat(vf.y, vf.s), vf.x/32);
            int shift = vf.x%32 - fv.x%32;

            masks.computeIfAbsent(startCoord, _ -> new HashMap<>())
                 .computeIfAbsent(endCoord, _ -> new HashMap<>())
                 .putIfAbsent(shift, 0);

            masks.get(startCoord).get(endCoord).put(shift, masks.get(startCoord).get(endCoord).get(shift) |
                    (1 << (31 - fv.x%32))
            );
        }

        BoolFv.SET_MASKS(pos, masks);
    }

    private static void setMasksFe(){
        BoolFe pos = BoolFe.zeroes(Border.MIRROR);
        HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks = new HashMap<>();

        for (Fe fe: medium.fes) {
            BoolFe.setBit(pos, fe, true);

            Ef ef = fe.getPair();

            Coord2D startCoord =  new Coord2D(feFlat(fe.y, fe.t, fe.s), fe.x/32);
            Coord2D endCoord = new Coord2D(efFlat(ef.y, ef.t, ef.s), ef.x/32);
            int shift = ef.x%32 - fe.x%32;

            masks.computeIfAbsent(startCoord, _ -> new HashMap<>())
                 .computeIfAbsent(endCoord, _ -> new HashMap<>())
                 .putIfAbsent(shift, 0);

            masks.get(startCoord).get(endCoord).put(shift, masks.get(startCoord).get(endCoord).get(shift) |
                    (1 << (31 - fe.x%32))
            );
        }

        BoolFe.SET_MASKS(pos, masks);
    }

    private static Border DEFAULT_BORDER = Border.MIRROR;
    public static Border DEFAULT_BORDER() { return DEFAULT_BORDER; }
    public static void setDefaultBorder(Border b) {
        if (SETUP_DONE) throw new IllegalStateException("Cannot change default border after setup.");
        DEFAULT_BORDER = b;
    }

    private static void setBorders() {
        // Initialize data structures for borders
        HashSet<Vertex> corners = new HashSet<>();
        ArrayList<Vertex> topBorderV = new ArrayList<>();
        ArrayList<Vertex> bottomBorderV = new ArrayList<>();
        ArrayList<Vertex> leftBorderV = new ArrayList<>();
        ArrayList<Vertex> rightBorderV = new ArrayList<>();
        HashMap<Integer, HashMap<Integer, Vertex>> vertexByCoord = new HashMap<>();

        ArrayList<Ve> topBorderVe = new ArrayList<>();
        ArrayList<Ve> bottomBorderVe = new ArrayList<>();
        ArrayList<Ve> leftBorderVe = new ArrayList<>();
        ArrayList<Ve> rightBorderVe = new ArrayList<>();

        ArrayList<Edge> topBorderE = new ArrayList<>();
        ArrayList<Edge> bottomBorderE = new ArrayList<>();
        ArrayList<Edge> leftBorderE = new ArrayList<>();
        ArrayList<Edge> rightBorderE = new ArrayList<>();
        HashMap<Integer, HashMap<Integer, HashMap<Integer, Edge>>> edgeByCoord = new HashMap<>();

        ArrayList<Ev> topBorderEv = new ArrayList<>();
        ArrayList<Ev> bottomBorderEv = new ArrayList<>();
        ArrayList<Ev> leftBorderEv = new ArrayList<>();
        ArrayList<Ev> rightBorderEv = new ArrayList<>();

        processBorderVertices(corners, topBorderV, bottomBorderV, leftBorderV, rightBorderV, vertexByCoord);
        processBorderEdges(topBorderE, bottomBorderE, leftBorderE, rightBorderE, edgeByCoord);

        // Build relationship maps between border vertices and their t-loci
        HashMap<Vertex, HashSet<Vf>> vfByVertex = new HashMap<>();
        HashMap<Vertex, HashSet<Ve>> veByVertex = new HashMap<>();
        buildVertexTNeighborhood(vertexByCoord, vfByVertex, veByVertex);

        processBorderVeEvs(topBorderVe, bottomBorderVe, leftBorderVe, rightBorderVe,
                           topBorderEv, bottomBorderEv, leftBorderEv, rightBorderEv,
                           vertexByCoord, edgeByCoord, veByVertex);

        HashMap<Ve, Integer> interiorVe = new HashMap<>();
        HashMap<Vf, Integer> interiorVf = new HashMap<>();
        HashMap<Vf, Ve> cw = new HashMap<>();
        HashMap<Vf, Ve> ccw = new HashMap<>();
        processVertexEdgeRelationships(veByVertex, vfByVertex, edgeByCoord, interiorVe, interiorVf, cw, ccw);

        // Identify Ef loci belonging to a border edge
        HashSet<Ef> interiorEf = new HashSet<>();
        processInteriorFaceEf(edgeByCoord, interiorEf);

        // Create torus mappings for wrapping
        HashMap<Vertex, Vertex> torusV = new HashMap<>();
        HashMap<Ve, Ve> torusVe = new HashMap<>();
        HashMap<Edge, Edge> torusE = new HashMap<>();
        HashMap<Ev, Ev> torusEv = new HashMap<>();
        createTorusMappings(topBorderV, bottomBorderV, leftBorderV, rightBorderV,
                            topBorderVe, bottomBorderVe, leftBorderVe, rightBorderVe,
                            topBorderE, bottomBorderE, leftBorderE, rightBorderE,
                            topBorderEv, bottomBorderEv, leftBorderEv, rightBorderEv,
                            torusV, torusVe, torusE, torusEv);

        // Apply border settings to boolean fields
        applyBorderSettings(corners, torusV, torusVe, torusE, torusEv, interiorVe, interiorVf, cw, ccw, interiorEf);
    }

    private static void processBorderVertices(
            HashSet<Vertex> corners,
            ArrayList<Vertex> topBorderV,
            ArrayList<Vertex> bottomBorderV,
            ArrayList<Vertex> leftBorderV,
            ArrayList<Vertex> rightBorderV,
            HashMap<Integer, HashMap<Integer, Vertex>> vertexByCoord
    ) {
        for (Vertex v: medium.vertices) {
            if (!v.top && !v.bot && !v.left && !v.right) continue;

            // Categorize vertex into appropriate border list
            if (v.top) {
                if (v.left || v.right) corners.add(v);
                else topBorderV.add(v);
            }
            else if (v.bot) {
                if (v.left || v.right) corners.add(v);
                else bottomBorderV.add(v);
            }
            else if (v.left) leftBorderV.add(v);
            else rightBorderV.add(v);

            // Store vertex by coordinates for quick lookup
            vertexByCoord.putIfAbsent(v.y, new HashMap<>());
            vertexByCoord.get(v.y).put(v.x, v);
        }

        // Sort border vertices by their position
        topBorderV.sort(Comparator.comparingDouble(v -> v.w));
        bottomBorderV.sort(Comparator.comparingDouble(v -> v.w));
        leftBorderV.sort(Comparator.comparingDouble(v -> v.h));
        rightBorderV.sort(Comparator.comparingDouble(v -> v.h));
    }

    private static void processBorderEdges(
            ArrayList<Edge> topBorderE,
            ArrayList<Edge> bottomBorderE,
            ArrayList<Edge> leftBorderE,
            ArrayList<Edge> rightBorderE,
            HashMap<Integer, HashMap<Integer, HashMap<Integer, Edge>>> edgeByCoord
    ) {
        for (Edge e: medium.edges) {
            if (!e.top && !e.bot && !e.left && !e.right) continue;

            // Categorize edge into appropriate border list
            if (e.top) topBorderE.add(e);
            else if (e.bot) bottomBorderE.add(e);
            else if (e.left) leftBorderE.add(e);
            else rightBorderE.add(e);

            // Store edge by coordinates for quick lookup
            edgeByCoord.putIfAbsent(e.y, new HashMap<>());
            edgeByCoord.get(e.y).putIfAbsent(e.x, new HashMap<>());
            edgeByCoord.get(e.y).get(e.x).put(e.t, e);
        }

        // Sort border edges by their position
        topBorderE.sort(Comparator.comparingDouble(e -> e.w));
        bottomBorderE.sort(Comparator.comparingDouble(e -> e.w));
        leftBorderE.sort(Comparator.comparingDouble(e -> e.h));
        rightBorderE.sort(Comparator.comparingDouble(e -> e.h));
    }

    private static void processBorderVeEvs(
            ArrayList<Ve> topBorderVe,
            ArrayList<Ve> bottomBorderVe,
            ArrayList<Ve> leftBorderVe,
            ArrayList<Ve> rightBorderVe,

            ArrayList<Ev> topBorderEv,
            ArrayList<Ev> bottomBorderEv,
            ArrayList<Ev> leftBorderEv,
            ArrayList<Ev> rightBorderEv,

            HashMap<Integer, HashMap<Integer, Vertex>> vertexByCoord,
            HashMap<Integer, HashMap<Integer, HashMap<Integer, Edge>>> edgeByCoord,
            HashMap<Vertex, HashSet<Ve>> veByVertex
    ) {
        for (int i: vertexByCoord.keySet()) for (Vertex v: vertexByCoord.get(i).values()) for (Ve ve: veByVertex.get(v)){
            Ev ev = ve.getPair();
            Edge e = lookupEdge(edgeByCoord, ev.y, ev.x, ev.t);
            if (e == null) continue;

            // Categorize Ve and Ev into appropriate border lists based on their vertex and edge
            if (v.top && e.top) {
                topBorderVe.add(ve);
                topBorderEv.add(ev);
            }
            else if (v.bot && e.bot) {
                bottomBorderVe.add(ve);
                bottomBorderEv.add(ev);
            }
            else if (v.left && e.left) {
                leftBorderVe.add(ve);
                leftBorderEv.add(ev);
            }
            else if (v.right && e.right) {
                rightBorderVe.add(ve);
                rightBorderEv.add(ev);
            }
        }

        topBorderVe.sort(Comparator.comparingDouble(ve -> ve.w));
        bottomBorderVe.sort(Comparator.comparingDouble(ve -> ve.w));
        leftBorderVe.sort(Comparator.comparingDouble(ve -> ve.h));
        rightBorderVe.sort(Comparator.comparingDouble(ve -> ve.h));

        topBorderEv.sort(Comparator.comparingDouble(ev -> ev.w));
        bottomBorderEv.sort(Comparator.comparingDouble(ev -> ev.w));
        leftBorderEv.sort(Comparator.comparingDouble(ev -> ev.h));
        rightBorderEv.sort(Comparator.comparingDouble(ev -> ev.h));
    }

    private static void buildVertexTNeighborhood(
            HashMap<Integer, HashMap<Integer, Vertex>> vertexByCoord,
            HashMap<Vertex, HashSet<Vf>> vfByVertex,
            HashMap<Vertex, HashSet<Ve>> veByVertex
    ) {
        // Map each Vf to its corresponding vertex
        for (Vf vf: medium.vfs) {
            if (vertexByCoord.containsKey(vf.y) && vertexByCoord.get(vf.y).containsKey(vf.x)) {
                Vertex v = vertexByCoord.get(vf.y).get(vf.x);
                vfByVertex.putIfAbsent(v, new HashSet<>());
                vfByVertex.get(v).add(vf);
            }
        }

        // Map each Ve to its corresponding vertex
        for (Ve ve: medium.ves) {
            if (vertexByCoord.containsKey(ve.y) && vertexByCoord.get(ve.y).containsKey(ve.x)) {
                Vertex v = vertexByCoord.get(ve.y).get(ve.x);
                veByVertex.putIfAbsent(v, new HashSet<>());
                veByVertex.get(v).add(ve);
            }
        }
    }

    private static void processVertexEdgeRelationships(
            HashMap<Vertex, HashSet<Ve>> veByVertex,
            HashMap<Vertex, HashSet<Vf>> vfByVertex,
            HashMap<Integer, HashMap<Integer, HashMap<Integer, Edge>>> edgeByCoord,
            HashMap<Ve, Integer> interiorVe,
            HashMap<Vf, Integer> interiorVf,
            HashMap<Vf, Ve> cw,
            HashMap<Vf, Ve> ccw
    ) {
        for (Vertex v: veByVertex.keySet()) {
            HashSet<Ve> ves = veByVertex.get(v);
            HashSet<Vf> vfs = vfByVertex.get(v);

            // Find the two border Ve adjacent to this vertex
            Ve border1 = null;
            Ve border2 = null;

            for (Ve ve: ves) {
                Ev pair = ve.getPair();
                Edge pairE = lookupEdge(edgeByCoord, pair.y, pair.x, pair.t);
                if (pairE == null) continue;

                if (border1 == null) border1 = ve;
                else if (border2 == null) border2 = ve;
                else throw new IllegalStateException("A border vertex cannot be adjacent to more than 2 border edges.");
            }

            if (border1 == null || border2 == null)
                throw new IllegalStateException("A border vertex adjacent to exactly 2 border edges.");

            validateBorderEdgeSeparation(border1, border2, ves);

            // Mark non-border transfers of this vertex as interior
            int breadth = ves.size();
            HashSet<Integer> takenVe = new HashSet<>();
            for (Ve ve: ves) {
                takenVe.add(ve.s);
                if (ve != border1 && ve != border2) interiorVe.put(ve, 0);
            }
            HashSet<Integer> takenVf = new HashSet<>();
            for (Vf vf: vfs) {
                takenVf.add(vf.s);
                interiorVf.put(vf, breadth + vf.s);
            }

            int i = -1;
            for (Ve ve: ves) if (interiorVe.containsKey(ve)) {
                boolean done = false;
                while (!done) {
                    i++;
                    if (i >= getBreadthV())
                        throw new IllegalStateException("Border vertex would have more Ve than breadth due to mirror.");
                    if (takenVe.contains(i)) continue;
                    takenVe.add(i);
                    interiorVe.put(ve, i);
                    done = true;
                }
            }

            i = -1;
            for (Vf vf: vfs) {
                boolean done = false;
                while (!done) {
                    i++;
                    if (i >= getBreadthV())
                        throw new IllegalStateException("Border vertex would have more Vf than breadth due to mirror.");
                    if (takenVf.contains(i)) {
                        continue;
                    }
                    takenVe.add(i);
                    interiorVf.put(vf, i);
                    done = true;
                }
            }
            // Determine directional mappings for vertex faces
            determineDirectionalMappings(border1, border2, vfs, breadth, cw, ccw);
        }
    }

    private static Edge lookupEdge(
            HashMap<Integer, HashMap<Integer, HashMap<Integer, Edge>>> edgeByCoord,
            int y, int x, int t
    ) {
        if (edgeByCoord.containsKey(y) &&
                edgeByCoord.get(y).containsKey(x) &&
                edgeByCoord.get(y).get(x).containsKey(t)) {
            return edgeByCoord.get(y).get(x).get(t);
        }
        return null;
    }

    private static void validateBorderEdgeSeparation(Ve border1, Ve border2, HashSet<Ve> ves) {
        int breadth = ves.size();
        int sep = Math.abs(border1.s - border2.s);
        if (sep != 1 && sep != breadth-1)
            throw new IllegalStateException(
                    "The 2 border edges adjacent to a border vertex must be consecutive around it.\n" +
                    border1 + " " + border2 + "\n" +
                    ves
            );
    }

    private static void determineDirectionalMappings(
            Ve border1, Ve border2, HashSet<Vf> vfs, int breadth,
            HashMap<Vf, Ve> cw, HashMap<Vf, Ve> ccw
    ) {
        Ve veStart = null;
        Ve veEnd = null;
        Vf vfStart = null;
        Vf vfEnd = null;

        for (Vf vf: vfs) {
            if (vf.s == border1.s) {
                veStart = border1;
                vfStart = vf;
            }
            if (vf.s == border2.s) {
                veStart = border2;
                vfStart = vf;
            }

            if (vf.s == ((border1.s-1)%breadth + breadth) % breadth) {
                veEnd = border1;
                vfEnd = vf;
            }
            if (vf.s == ((border2.s-1)%breadth + breadth) % breadth) {
                veEnd = border2;
                vfEnd = vf;
            }
        }

        if (veStart == null) throw new IllegalStateException(
                "One of the two ves pointing to a border edge must have " +
                "one vf belonging to the same vertex with the same s coord\n" +
                border1 + " " + border2 + "\n" + vfs

        );
        if (veEnd == null) throw new IllegalStateException(
                "One of the two ves pointing to a border edge must have " +
                "one vf belonging to the same vertex with s coord one less than the ve's\n" +
                border1 + " " + border2 + "\n" + vfs
        );

        cw.put(vfEnd, veStart);
        ccw.put(vfStart, veEnd);
    }

    private static void processInteriorFaceEf(
            HashMap<Integer, HashMap<Integer, HashMap<Integer, Edge>>> edgeByCoord,
            HashSet<Ef> interiorEf
    ) {
        for (Ef ef: medium.efs) {
            if (lookupEdge(edgeByCoord, ef.y, ef.x, ef.t) != null) {
                interiorEf.add(ef);
            }
        }
    }

    private static <T> void map(ArrayList<T> in, ArrayList<T> out, HashMap<T, T> map) {
        for (int i=0; i<in.size(); i++) map.put(in.get(i), out.get(i));
    }
    private static void createTorusMappings(
            ArrayList<Vertex> topBorderV,
            ArrayList<Vertex> bottomBorderV,
            ArrayList<Vertex> leftBorderV,
            ArrayList<Vertex> rightBorderV,

            ArrayList<Ve> topBorderVe,
            ArrayList<Ve> bottomBorderVe,
            ArrayList<Ve> leftBorderVe,
            ArrayList<Ve> rightBorderVe,

            ArrayList<Edge> topBorderE,
            ArrayList<Edge> bottomBorderE,
            ArrayList<Edge> leftBorderE,
            ArrayList<Edge> rightBorderE,

            ArrayList<Ev> topBorderEv,
            ArrayList<Ev> bottomBorderEv,
            ArrayList<Ev> leftBorderEv,
            ArrayList<Ev> rightBorderEv,

            HashMap<Vertex, Vertex> torusV,
            HashMap<Ve, Ve> torusVe,
            HashMap<Edge, Edge> torusE,
            HashMap<Ev, Ev> torusEv
    ) {
        map(topBorderV, bottomBorderV, torusV);
        map(leftBorderV, rightBorderV, torusV);

        map(topBorderVe, bottomBorderVe, torusVe);
        map(leftBorderVe, rightBorderVe, torusVe);

        map(topBorderE, bottomBorderE, torusE);
        map(leftBorderE, rightBorderE, torusE);

        map(topBorderEv, bottomBorderEv, torusEv);
        map(leftBorderEv, rightBorderEv, torusEv);
    }

    private static void applyBorderSettings(
            HashSet<Vertex> corners,
            HashMap<Vertex, Vertex> torusV,
            HashMap<Ve, Ve> torusVe,
            HashMap<Edge, Edge> torusE,
            HashMap<Ev, Ev> torusEv,

            HashMap<Ve, Integer> interiorVe,
            HashMap<Vf, Integer> interiorVf,
            HashMap<Vf, Ve> cw,
            HashMap<Vf, Ve> ccw,
            HashSet<Ef> interiorEf
    ) {
        BoolV.setBorder(torusV, corners);
        BoolE.setBorder(torusE);

        BoolVe.setBorder(torusVe, null, interiorVe);
        BoolVf.setBorder(interiorVf, cw, ccw);
        BoolEv.setBorder(torusEv);

        BoolEf.setBorder(null, interiorEf);
    }
}