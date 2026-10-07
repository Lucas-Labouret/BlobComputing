package medium.locusT;

import medium.Locus;

public final class Ev extends Locus {
    // Canning coordinates of this Ev in the medium.
    public final int y, x, t, s;
    // The companion Ve of this Ev.
    private Ve pair;

    public Ev(int y, int x, int t, int s){
        this(0, 0, y, x, t, s);
    }

    public Ev(double h, double w, int y, int x, int t, int s){
        super(h, w);
        this.y = y;
        this.x = x;
        this.t = t;
        this.s = s;
    }

    public void pairWith(Ve pair) {
        if (this.pair != null) return;
        this.pair = pair;
        pair.pairWith(this);
    }

    public Ve getPair(){ return pair; }

    @Override
    public String toString() {
        return "(" + y + ", " + x + ", " + t + ", " + s + ")";
    }
}
