package medium.locusS;

import medium.Locus;

public class Vertex extends Locus {
    public final int y, x;
    public final boolean top, left, right, bot;

    public Vertex(int y, int x){
        this(0, 0, y, x);
    }

    public Vertex(double h, double w, int y, int x){
        this(h, w, y, x, false, false, false, false);
    }

    public Vertex(double h, double w, int y, int x, boolean top, boolean left, boolean right, boolean bot){
        super(h, w);
        this.y = y;
        this.x = x;
        this.top = top;
        this.left = left;
        this.right = right;
        this.bot = bot;
    }
}
