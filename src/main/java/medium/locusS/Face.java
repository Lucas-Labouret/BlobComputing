package medium.locusS;

import medium.Locus;

public class Face extends Locus {
    public final int y, x, t;

    public Face(int y, int x, int t){
        this(0, 0, y, x, t);
    }

    public Face(double h, double w, int y, int x, int t){
        super(h, w);
        this.y = y;
        this.x = x;
        this.t = t;
    }
}
