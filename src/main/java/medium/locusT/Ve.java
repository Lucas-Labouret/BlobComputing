package medium.locusT;

import medium.Locus;

public class Ve extends Locus {
    public final int y, x, t;
    private Ev pair;

    public Ve(int y, int x, int t) {
        this(0, 0, y, x, t);
    }

    public Ve(double h, double w, int y, int x, int t){
        super(h, w);
        this.y = y;
        this.x = x;
        this.t = t;
    }

    public void pairWith(Ev pair) {
        if (this.pair != null) return;
        this.pair = pair;
        pair.pairWith(this);
    }

    public Ev getPair(){ return pair; }

}
