package medium.locusT;

import medium.Locus;

public final class Vf extends Locus {
    // Canning coordinates of this Vf in the medium.
    public final int y, x, s;
    // The companion Fv of this Vf.
    private Fv pair;

    public Vf(int y, int x, int s){
        this(0, 0, y, x, s);
    }

    public Vf(double h, double w, int y, int x, int s){
        super(h, w);
        this.y = y;
        this.x = x;
        this.s = s;
    }

    public void pairWith(Fv pair) {
        if (this.pair != null) return;
        this.pair = pair;
        pair.pairWith(this);
    }

    public Fv getPair(){ return pair; }

    @Override
    public String toString() {
        return "(" + y + ", " + x + ", " + s + ")";
    }
}
