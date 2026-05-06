package medium.locusS;

import medium.Locus;

public class Vertex extends Locus {
    public final int y, x;

        public Vertex(int y, int x){
            this(0, 0, y, x);
        }

    public Vertex(double h, double w, int y, int x){
        super(h, w);
        this.y = y;
        this.x = x;
    }
}
