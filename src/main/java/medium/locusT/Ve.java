package medium.locusT;

import medium.Locus;

public class Ve extends Locus {
    public final int y, x, s;
    private Ev pair;

    public Ve(int y, int x, int s) {
        this(0, 0, y, x, s);
    }

    public Ve(double h, double w, int y, int x, int s){
        super(h, w);
        this.y = y;
        this.x = x;
        this.s = s;
    }

    public void pairWith(Ev pair) {
        if (this.pair != null) return;
        this.pair = pair;
        pair.pairWith(this);
    }

    public Ev getPair(){ return pair; }

    @Override
    public String toString() {
        return "(" + y + ", " + x + ", " + s + ")";
    }
}
