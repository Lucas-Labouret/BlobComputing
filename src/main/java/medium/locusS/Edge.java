package medium.locusS;

import medium.Locus;

public class Edge extends Locus {
    public final int y, x, t;

    public Edge(int y, int x, int t){
        this(0, 0, y, x, t);
    }

    public Edge(double h, double w, int y, int x, int t){
        super(h, w);
        this.y = y;
        this.x = x;
        this.t = t;
    }
}