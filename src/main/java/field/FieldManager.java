package field;

import field.boolField.BoolFieldLine;
import field.boolField.fieldT.*;
import field.boolField.fieldS.*;

import medium.Medium;
import medium.locusS.*;
import medium.locusT.*;

import java.util.HashMap;

/** Stores the global field-dimension configuration shared by field types. */
public final class FieldManager {
    // Block instantiation
    private FieldManager(){}

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

    /** Initializes the global field configuration from the given medium. */
    public static void setup(Medium m) {
        medium = m;

        width = height = breadthV = 0;
        for (Ve ve: medium.ves){
            if (ve.y > height) height = ve.y;
            if (ve.x > width) width = ve.x;
            if (ve.t > breadthV) breadthV = ve.t;
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

        setMasksVe();
        setMasksVf();
        setMasksEv();
        setMasksEf();
        setMasksFv();
        setMasksFe();
    }

    public static int getWidth(){ return width; }
    public static int getHeight(){ return height; }
    public static int getSpanE(){ return spanE; }
    public static int getSpanF(){ return spanF; }
    public static int getBreadthV(){ return breadthV; }
    public static int getBreadthE(){ return breadthE; }
    public static int getBreadthF(){ return breadthF; }

    private static int veFlat(int y, int t){ return y * breadthV + t; }
    private static int vfFlat(int y, int t){ return y * breadthV + t; }
    private static int evFlat(int y, int t, int s){ return (y * spanE + t) * breadthE + s; }
    private static int efFlat(int y, int t, int s){ return (y * spanE + t) * breadthE + s; }
    private static int fvFlat(int y, int t, int s){ return (y * spanF + t) * breadthF + s; }
    private static int feFlat(int y, int t, int s){ return (y * spanF + t) * breadthF + s; }

    private static void setMasksVe(){
        BoolVe pos = BoolVe.zeroes();
        HashMap<Integer, HashMap<Integer, BoolVe>> masks = new HashMap<>();

        for (Ve ve: medium.ves) {
            BoolVe.setBit(pos, ve.y, ve.x, ve.t, true);

            Ev ev = ve.getPair();

            int dy = evFlat(ev.y, ev.t, ev.s) - veFlat(ve.y, ve.t);
            int dx = ev.x - ve.x;

            if (!masks.containsKey(dy)) masks.put(dy, new HashMap<>());
            if (!masks.get(dy).containsKey(dx)) masks.get(dy).put(dx, BoolVe.zeroes());
            BoolVe.setBit(masks.get(dy).get(dx),  ve.y, ve.x, ve.t, true);
        }

        BoolVe.SET_MASKS(pos, masks);
    }

    private static void setMasksVf(){
        BoolVf pos = BoolVf.zeroes();
        HashMap<Integer, HashMap<Integer, BoolVf>> masks = new HashMap<>();

        for (Vf vf: medium.vfs) {
            BoolVf.setBit(pos, vf.y, vf.x, vf.t, true);

            Fv fv = vf.getPair();

            int dy = fvFlat(fv.y, fv.t, fv.s) - vfFlat(vf.y, vf.t);
            int dx = fv.x - vf.x;

            if (!masks.containsKey(dy)) masks.put(dy, new HashMap<>());
            if (!masks.get(dy).containsKey(dx)) masks.get(dy).put(dx, BoolVf.zeroes());
            BoolVf.setBit(masks.get(dy).get(dx), vf.y, vf.x, vf.t, true);
        }

        BoolVf.SET_MASKS(pos, masks);
    }

    private static void setMasksEv(){
        BoolEv pos = BoolEv.zeroes();
        HashMap<Integer, HashMap<Integer, BoolEv>> masks = new HashMap<>();

        for (Ev ev: medium.evs) {
            BoolEv.setBit(pos, ev.y, ev.x, ev.t, ev.s, true);

            Ve ve = ev.getPair();

            int dy = veFlat(ve.y, ve.t) - evFlat(ev.y, ev.t, ev.s);
            int dx = ve.x - ev.x;

            if (!masks.containsKey(dy)) masks.put(dy, new HashMap<>());
            if (!masks.get(dy).containsKey(dx)) masks.get(dy).put(dx, BoolEv.zeroes());
            BoolEv.setBit(masks.get(dy).get(dx), ev.y, ev.x, ev.t, ev.s, true);
        }

        BoolEv.SET_MASKS(pos, masks);
    }

    private static void setMasksEf(){
        BoolEf pos = BoolEf.zeroes();
        HashMap<Integer, HashMap<Integer, BoolEf>> masks = new HashMap<>();

        for (Ef ef: medium.efs) {
            BoolEf.setBit(pos, ef.y, ef.x, ef.t, ef.s, true);

            Fe fe = ef.getPair();

            int dy = feFlat(fe.y, fe.t, fe.s) - efFlat(ef.y, ef.t, ef.s);
            int dx = fe.x - ef.x;

            if (!masks.containsKey(dy)) masks.put(dy, new HashMap<>());
            if (!masks.get(dy).containsKey(dx)) masks.get(dy).put(dx, BoolEf.zeroes());
            BoolEf.setBit(masks.get(dy).get(dx), ef.y, ef.x, ef.t, ef.s, true);
        }

        BoolEf.SET_MASKS(pos, masks);
    }

    private static void setMasksFv(){
        BoolFv pos = BoolFv.zeroes();
        HashMap<Integer, HashMap<Integer, BoolFv>> masks = new HashMap<>();

        for (Fv fv: medium.fvs) {
            BoolFv.setBit(pos, fv.y, fv.x, fv.t, fv.s, true);

            Vf vf = fv.getPair();

            int dy = vfFlat(vf.y, vf.t) - fvFlat(fv.y, fv.t, fv.s);
            int dx = vf.x - fv.x;

            if (!masks.containsKey(dy)) masks.put(dy, new HashMap<>());
            if (!masks.get(dy).containsKey(dx)) masks.get(dy).put(dx, BoolFv.zeroes());
            BoolFv.setBit(masks.get(dy).get(dx), fv.y, fv.x, fv.t, fv.s, true);
        }

        BoolFv.SET_MASKS(pos, masks);
    }

    private static void setMasksFe(){
        BoolFe pos = BoolFe.zeroes();
        HashMap<Integer, HashMap<Integer, BoolFe>> masks = new HashMap<>();

        for (Fe fe: medium.fes) {
            BoolFe.setBit(pos, fe.y, fe.x, fe.t, fe.s, true);

            Ef ef = fe.getPair();

            int dy = efFlat(ef.y, ef.t, ef.s) - feFlat(fe.y, fe.t, fe.s);
            int dx = ef.x - fe.x;

            if (!masks.containsKey(dy)) masks.put(dy, new HashMap<>());
            if (!masks.get(dy).containsKey(dx)) masks.get(dy).put(dx, BoolFe.zeroes());
            BoolFe.setBit(masks.get(dy).get(dx), fe.y, fe.x, fe.t, fe.s, true);
        }

        BoolFe.SET_MASKS(pos, masks);
    }
}
