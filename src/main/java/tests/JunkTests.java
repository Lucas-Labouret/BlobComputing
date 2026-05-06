package tests;

import field.FieldManager;
import field.boolField.fieldT.*;

import medium.Medium;
import medium.locusS.*;
import medium.locusT.*;

import java.util.List;

public class JunkTests {
    static void main(String[] args) {
        FieldManager.setup(largeMedium());

        long startTime = System.currentTimeMillis();
        spatialTestsTransfer();
        System.out.println("Total time: " + (System.currentTimeMillis() - startTime) + " ms");
    }


    private static void spatialTestsTransfer() {
        for (int i=0; i<50; i++) transferRoundTripVeEv();
        sep();
        for (int i=0; i<50; i++) transferRoundTripVfFv();
        sep();
        for (int i=0; i<50; i++) transferRoundTripEfFe();
    }

    private static void transferRoundTripVeEv() {
        BoolVe orig = BoolVe.rand();

        BoolVe roundTrip = BoolEv.transfer(BoolVe.transfer(orig));
        printRoundTripResult("Ve <-> Ev", orig, roundTrip);
    }

    private static void transferRoundTripVfFv() {
        BoolVf orig = BoolVf.rand();

        BoolVf roundTrip = BoolFv.transfer(BoolVf.transfer(orig));
        printRoundTripResult("Vf <-> Fv", orig, roundTrip);
    }

    private static void transferRoundTripEfFe() {
        BoolEf orig = BoolEf.rand();

        BoolEf roundTrip = BoolFe.transfer(BoolEf.transfer(orig));
        printRoundTripResult("Ef <-> Fe", orig, roundTrip);
    }

    private static void printRoundTripResult(String label, BoolFieldT orig, BoolFieldT roundTrip) {
        boolean ok = orig.equals(roundTrip);
        System.out.println(label + " round-trip: " + (ok ? "PASS" : "FAIL"));
    }

    private static void sep() {
        System.out.println();
        System.out.println("-------------");
        System.out.println();
    }

    private static Medium tinyMedium() {
        Vertex v0, v1, v2, v3, v4;
        v0 = new Vertex(0, 0);
        v1 = new Vertex(0, 1);
        v2 = new Vertex(1, 0);
        v3 = new Vertex(1, 1);
        v4 = new Vertex(2, 0);

        Edge e0, e1, e2, e3, e4, e5, e6;
        e0 = new Edge(0, 0, 0);
        e1 = new Edge(0, 0, 1);
        e2 = new Edge(0, 0, 2);
        e3 = new Edge(0, 1, 0);
        e4 = new Edge(1, 0, 0);
        e5 = new Edge(1, 0, 1);
        e6 = new Edge(1, 1, 0);

        Face f0, f1, f2;
        f0 = new Face(0, 0, 0);
        f1 = new Face(0, 0, 1);
        f2 = new Face(1, 0, 0);

        Ve ve0, ve1, ve2, ve3, ve4, ve5, ve6, ve7, ve8, ve9, ve10, ve11, ve12, ve13;
        ve0 = new Ve(0, 0, 0);
        ve1 = new Ve(0, 0, 1);
        ve2 = new Ve(0, 0, 2);
        ve3 = new Ve(0, 1, 0);
        ve4 = new Ve(0, 1, 1);
        ve5 = new Ve(1, 0, 0);
        ve6 = new Ve(1, 0, 1);
        ve7 = new Ve(1, 0, 2);
        ve8 = new Ve(1, 1, 0);
        ve9 = new Ve(1, 1, 1);
        ve10 = new Ve(1, 1, 2);
        ve11 = new Ve(1, 1, 3);
        ve12 = new Ve(2, 0, 0);
        ve13 = new Ve(2, 0, 1);

        Ev ev0, ev1, ev2, ev3, ev4, ev5, ev6, ev7, ev8, ev9, ev10, ev11, ev12, ev13;
        ev0 = new Ev(0, 0, 0, 0);
        ev1 = new Ev(0, 0, 0, 1);
        ev2 = new Ev(0, 0, 1, 0);
        ev3 = new Ev(0, 0, 1, 1);
        ev4 = new Ev(0, 0, 2, 0);
        ev5 = new Ev(0, 0, 2, 1);
        ev6 = new Ev(0, 1, 0, 0);
        ev7 = new Ev(0, 1, 0, 1);
        ev8 = new Ev(1, 0, 0, 0);
        ev9 = new Ev(1, 0, 0, 1);
        ev10 = new Ev(1, 0, 1, 0);
        ev11 = new Ev(1, 0, 1, 1);
        ev12 = new Ev(1, 1, 0, 0);
        ev13 = new Ev(1, 1, 0, 1);

        ve0.pairWith(ev0);
        ve1.pairWith(ev2);
        ve2.pairWith(ev4);
        ve3.pairWith(ev6);
        ve4.pairWith(ev1);
        ve5.pairWith(ev8);
        ve6.pairWith(ev10);
        ve7.pairWith(ev5);
        ve8.pairWith(ev12);
        ve9.pairWith(ev9);
        ve10.pairWith(ev3);
        ve11.pairWith(ev7);
        ve12.pairWith(ev11);
        ve13.pairWith(ev13);

        Vf vf0, vf1, vf2, vf3, vf4, vf5, vf6, vf7, vf8;
        vf0 = new Vf(0, 0, 0);
        vf1 = new Vf(0, 0, 1);
        vf2 = new Vf(0, 1, 0);
        vf3 = new Vf(1, 0, 0);
        vf4 = new Vf(1, 0, 1);
        vf5 = new Vf(1, 1, 0);
        vf6 = new Vf(1, 1, 1);
        vf7 = new Vf(1, 1, 2);
        vf8 = new Vf(2, 0, 0);

        Fv fv0, fv1, fv2, fv3, fv4, fv5, fv6, fv7, fv8;
        fv0 = new Fv(0, 0, 0, 0);
        fv1 = new Fv(0, 0, 0, 1);
        fv2 = new Fv(0, 0, 0, 2);
        fv3 = new Fv(0, 0, 1, 0);
        fv4 = new Fv(0, 0, 1, 1);
        fv5 = new Fv(0, 0, 1, 2);
        fv6 = new Fv(1, 0, 0, 0);
        fv7 = new Fv(1, 0, 0, 1);
        fv8 = new Fv(1, 0, 0, 2);

        vf0.pairWith(fv0);
        vf1.pairWith(fv3);
        vf2.pairWith(fv1);
        vf3.pairWith(fv6);
        vf4.pairWith(fv5);
        vf5.pairWith(fv7);
        vf6.pairWith(fv4);
        vf7.pairWith(fv2);
        vf8.pairWith(fv8);

        Ef ef0, ef1, ef2, ef3, ef4, ef5, ef6, ef7, ef8;
        ef0 = new Ef(0, 0, 0, 0);
        ef1 = new Ef(0, 0, 1, 0);
        ef2 = new Ef(0, 0, 1, 1);
        ef3 = new Ef(0, 0, 2, 0);
        ef4 = new Ef(0, 1, 0, 0);
        ef5 = new Ef(1, 0, 0, 0);
        ef6 = new Ef(1, 0, 0, 1);
        ef7 = new Ef(1, 0, 1, 0);
        ef8 = new Ef(1, 1, 0, 0);

        Fe fe0, fe1, fe2, fe3, fe4, fe5, fe6, fe7, fe8;
        fe0 = new Fe(0, 0, 0, 0);
        fe1 = new Fe(0, 0, 0, 1);
        fe2 = new Fe(0, 0, 0, 2);
        fe3 = new Fe(0, 0, 1, 0);
        fe4 = new Fe(0, 0, 1, 1);
        fe5 = new Fe(0, 0, 1, 2);
        fe6 = new Fe(1, 0, 0, 0);
        fe7 = new Fe(1, 0, 0, 1);
        fe8 = new Fe(1, 0, 0, 2);

        ef0.pairWith(fe0);
        ef1.pairWith(fe2);
        ef2.pairWith(fe3);
        ef3.pairWith(fe5);
        ef4.pairWith(fe1);
        ef5.pairWith(fe4);
        ef6.pairWith(fe6);
        ef7.pairWith(fe7);
        ef8.pairWith(fe8);

        return new Medium(
                List.of(v0, v1, v2, v3, v4),
                List.of(e0, e1, e2, e3, e4, e5, e6),
                List.of(f0, f1, f2),
                List.of(ve0, ve1, ve2, ve3, ve4, ve5, ve6, ve7, ve8, ve9, ve10, ve11, ve12, ve13),
                List.of(vf0, vf1, vf2, vf3, vf4, vf5, vf6, vf7, vf8),
                List.of(ev0, ev1, ev2, ev3, ev4, ev5, ev6, ev7, ev8, ev9, ev10, ev11, ev12, ev13),
                List.of(ef0, ef1, ef2, ef3, ef4, ef5, ef6, ef7, ef8),
                List.of(fv0, fv1, fv2, fv3, fv4, fv5, fv6, fv7, fv8),
                List.of(fe0, fe1, fe2, fe3, fe4, fe5, fe6, fe7, fe8)
        );
    }
    private static Medium smallMedium() {
        try {
            return Medium.read("small");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Medium mediumMedium() {
        try {
            return Medium.read("medium");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    private static Medium largeMedium() {
        try {
            return Medium.read("large");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
