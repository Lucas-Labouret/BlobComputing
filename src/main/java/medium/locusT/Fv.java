package medium.locusT;

import medium.Locus;

public final class Fv extends Locus {
    // Canning coordinates of this Fv in the medium.
    public final int y, x, t, s;
    // The companion Vf of this Fv.
    private Vf pair;

    public Fv(int y, int x, int t, int s) {
        this(0, 0, y, x, t, s);
    }

    public Fv(double h, double w, int y, int x, int t, int s){
        super(h, w);
        this.y = y;
        this.x = x;
        this.t = t;
        this.s = s;
    }

    public void pairWith(Vf pair) {
        if (this.pair != null) return;
        this.pair = pair;
        pair.pairWith(this);
    }

    public Vf getPair(){ return pair; }

    @Override
    public String toString() {
        return "(" + y + ", " + x + ", " + t + ", " + s + ")";
    }
}
