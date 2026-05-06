package medium.locusT;

import medium.Locus;

public class Vf extends Locus {
    public final int y, x, t;
    private Fv pair;

    public Vf(int y, int x, int t){
        this(0, 0, y, x, t);
    }

    public Vf(double h, double w, int y, int x, int t){
        super(h, w);
        this.y = y;
        this.x = x;
        this.t = t;
    }

    public void pairWith(Fv pair) {
        if (this.pair != null) return;
        this.pair = pair;
        pair.pairWith(this);
    }

    public Fv getPair(){ return pair; }

}

