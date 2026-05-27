package medium.locusT;

import medium.Locus;

public class Ef extends Locus {
    public final int y, x, t, s;
    private Fe pair;

    public Ef(int y, int x, int t, int s) {
        this(0, 0, y, x, t, s);
    }

    public Ef(double h, double w, int y, int x, int t, int s){
        super(h, w);
        this.y = y;
        this.x = x;
        this.t = t;
        this.s = s;
    }

    public void pairWith(Fe pair) {
        if (this.pair != null) return;
        this.pair = pair;
        pair.pairWith(this);
    }

    public Fe getPair(){ return pair; }

    @Override
    public String toString() {
        return "(" + y + ", " + x + ", " + t + ", " + s + ")";
    }
}
