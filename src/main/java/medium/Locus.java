package medium;

import medium.locusS.*;
import medium.locusT.*;

/** A locus is an element of a medium. */
public sealed abstract class Locus permits Vertex, Ve, Vf, Edge, Ev, Ef, Face, Fv, Fe {
    // Physical position of the locus in the medium.
    public final double h, w;

    public Locus(double h, double w) {
        this.h = h;
        this.w = w;
    }
}
