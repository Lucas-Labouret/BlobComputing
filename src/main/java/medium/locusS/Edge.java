package medium.locusS;

import medium.Locus;

public class Edge extends Locus {
    public final int y, x, t;
    public final boolean top, left, right, bot;

    public Edge(int y, int x, int t){
        this(0, 0, y, x, t);
    }

    public Edge(double h, double w, int y, int x, int t){
        this(h, w, y, x, t, false, false, false, false);
    }

    public Edge(double h, double w, int y, int x, int t, boolean top, boolean left, boolean right, boolean bot){
        super(h, w);
        this.y = y;
        this.x = x;
        this.t = t;
        this.top = top;
        this.left = left;
        this.right = right;
        this.bot = bot;
    }
}