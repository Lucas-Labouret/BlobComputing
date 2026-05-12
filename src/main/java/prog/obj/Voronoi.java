package prog.obj;

import field.boolField.fieldS.BoolV;
import language.Obj;
import language.Procedure;
import language.fieldRef.*;
import prog.obj.intField.IntV;
import prog.ref.intField.IntVRef;

public class Voronoi extends Obj {
    private final BoolVRef cells;

    public Voronoi(BoolVRef cells) {
        this.cells = cells;
    }

    public static Voronoi rand(int sparsity) {
        BoolV cells = BoolV.rand();
        for (int i = 0; i < sparsity; i++) {
            cells = BoolV.and(cells, BoolV.rand());
        }
        return new Voronoi(BoolVRef.of(cells));
    }

    private static class FrontierE extends Procedure {
        public FrontierE(BoolVRef cells, BoolERef frontier) {
            BoolVeRef ve = new BoolVeRef();
            BoolEvRef ev = new BoolEvRef();

            broadcast(cells, ve);
            transfer(ve, ev);
            redXor(ev, frontier);
        }
    }

    private static class Meet extends Procedure {
        public Meet(BoolVRef cells, BoolVRef grow, BoolVRef meet) {
            BoolVfRef vf = new BoolVfRef();
            BoolFvRef fv = new BoolFvRef();
            BoolFRef f = new BoolFRef();
            BoolFeRef fe = new BoolFeRef();
            BoolEfRef ef = new BoolEfRef();

            BoolVRef notCells = new BoolVRef();
            BoolVRef frontierV = new BoolVRef();
            not(cells, notCells);
            and(grow, notCells, frontierV);

            BoolERef frontierInteriorE = new BoolERef();
            BoolERef frontierInteriorEInv = new BoolERef();

            broadcast(cells, vf);
            transfer(vf, fv);
            redOr(fv, f);
            broadcast(f, fe);
            transfer(fe, ef);
            redOr(ef, frontierInteriorE);
            not(frontierInteriorE, frontierInteriorEInv);

            BoolERef meetE = new BoolERef();
            BoolVeRef ve = new BoolVeRef();
            BoolEvRef ev = new BoolEvRef();

            broadcast(frontierV, ve);
            transfer(ve, ev);
            redAnd(ev, meetE);

            and(meetE, frontierInteriorEInv, meetE);

            broadcast(meetE, ev);
            transfer(ev, ve);
            redOr(ve, meet);

            BoolVRef meetd1 = new BoolVRef();
            set(meet, meetd1);
            show("meet d=2", meetd1);

            //---------------------------------------------------------------------------------------------

            BoolERef frontierE = new BoolERef();

            addInstr(new FrontierE(cells, frontierE));
            broadcast(frontierE, ev);
            transfer(ev, ve);

            BoolVfRef cw = new BoolVfRef();
            BoolVfRef ccw = new BoolVfRef();


            rotCW(ve, cw);
            rotCCW(ve, ccw);
            xor(cw, ccw, vf);
            show("cw xor ccw", vf);

            IntVRef connectedComponents = IntVRef.of(new IntV(4));
            show("connected components", connectedComponents);

            BoolVRef meetV = new BoolVRef();

            redAdd(vf, connectedComponents);
            gt(connectedComponents, IntVRef.of(IntV.of(3, 4)), meetV);
            BoolVRef meetVCopy = new BoolVRef();
            set(meetV, meetVCopy);
            and(meetV, notCells, meetV);
            show("meet d=1", meetV);

//            BoolVRef corners = new BoolVRef();
//            gt(connectedComponents, IntVRef.of(IntV.of(5, 4)), corners);
//            add(new Show("corners", corners));

            or(meet, meetV, meet);
            show("meet", meet);
        }
    }

    private static class GrowCells extends Procedure {
        public GrowCells(BoolVRef cells) {
            BoolVRef startCells = new BoolVRef();
            set(cells, startCells);
            show("cells", startCells);
            //snapshot();

            BoolVRef meet = new BoolVRef();
            BoolVRef grow = new BoolVRef();

            addInstr(new GrowV(cells).grow(grow));
            addInstr(new Meet(cells, grow, meet));
            not(meet, meet);
            and(grow, meet, cells);

            print("loop done");
        }
    }

    public Procedure growCells() {
        return new GrowCells(cells);
    }

    @Override
    public Voronoi copy() {
        return new Voronoi(cells.copy());
    }
}
