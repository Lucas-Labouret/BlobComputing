package prog.obj;

import field.boolField.fieldS.BoolV;
import language.Obj;
import language.Procedure;
import language.basicInstruction.*;
import language.basicInstruction.commOp.*;
import language.basicInstruction.bitOp.*;
import language.fieldRef.*;
import prog.obj.intField.IntField;
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
        private final BoolVRef cells;
        private final BoolERef frontier;

        public FrontierE(BoolVRef cells, BoolERef frontier) {
            this.cells = cells;
            this.frontier = frontier;
        }

        @Override
        public void setInstructions() {
            BoolVeRef ve = new BoolVeRef();
            BoolEvRef ev = new BoolEvRef();

            add(CommOp.broadcast(cells, ve));
            add(CommOp.transfer(ve, ev));
            add(CommOp.redXor(ev, frontier));
        }
    }

    private static class Meet extends Procedure {
        private final BoolVRef cells;
        private final BoolVRef grow;
        private final BoolVRef meet;

        public Meet(BoolVRef cells, BoolVRef grow, BoolVRef meet) {
            this.cells = cells;
            this.grow = grow;
            this.meet = meet;
        }

        @Override
        protected void setInstructions() {
            BoolVfRef vf = new BoolVfRef();
            BoolFvRef fv = new BoolFvRef();
            BoolFRef f = new BoolFRef();
            BoolFeRef fe = new BoolFeRef();
            BoolEfRef ef = new BoolEfRef();

            BoolVRef notCells = new BoolVRef();
            BoolVRef frontierV = new BoolVRef();
            add(BitOp.not(cells, notCells));
            add(BitOp.and(grow, notCells, frontierV));

            BoolERef frontierInteriorE = new BoolERef();
            BoolERef frontierInteriorEInv = new BoolERef();

            add(CommOp.broadcast(cells, vf));
            add(CommOp.transfer(vf, fv));
            add(CommOp.redOr(fv, f));
            add(CommOp.broadcast(f, fe));
            add(CommOp.transfer(fe, ef));
            add(CommOp.redOr(ef, frontierInteriorE));
            add(BitOp.not(frontierInteriorE, frontierInteriorEInv));

            BoolERef meetE = new BoolERef();
            BoolVeRef ve = new BoolVeRef();
            BoolEvRef ev = new BoolEvRef();

            add(CommOp.broadcast(frontierV, ve));
            add(CommOp.transfer(ve, ev));
            add(CommOp.redAnd(ev, meetE));

            add(BitOp.and(meetE, frontierInteriorEInv, meetE));

            add(CommOp.broadcast(meetE, ev));
            add(CommOp.transfer(ev, ve));

            add(CommOp.redOr(ve, meet));

            BoolVRef meet2 = new BoolVRef();
            add(new SetRef<>(meet, meet2));
            add(new Show("meet d=2", meet2));

            //---------------------------------------------------------------------------------------------

            BoolERef frontierE = new BoolERef();

            add(new FrontierE(cells, frontierE));
            add(CommOp.broadcast(frontierE, ev));
            add(CommOp.transfer(ev, ve));

            BoolVfRef cw = new BoolVfRef();
            BoolVfRef ccw = new BoolVfRef();

            add(CommOp.rotCW(ve, cw));
            add(CommOp.rotCCW(ve, ccw));
            add(BitOp.xor(cw, ccw, vf));

            add(new Show("frontier", vf));

            IntVRef connectedComponents = IntVRef.of(new IntV(4));

            BoolVRef meetV = new BoolVRef();

            add(IntField.redAdd(vf, connectedComponents));

            add(new Show("connected components", connectedComponents));

            add(IntField.gt(connectedComponents, IntVRef.of(IntV.of(3, 4)), meetV));

            add(BitOp.and(meetV, notCells, meetV));

            add(new Show("meet d=1", meetV));

            add(BitOp.or(meet, meetV, meet));
        }
    }

    private static class GrowCells extends Procedure {
        private final BoolVRef cells;

        public GrowCells(BoolVRef cells) {
            this.cells = cells;
        }

        @Override
        protected void setInstructions() {
            BoolVRef meet = new BoolVRef();
            BoolVRef grow = new BoolVRef();

            add(new GrowV(cells).grow(grow));
            add(new Meet(cells, grow, meet));
            add(BitOp.not(meet, meet));
            add(BitOp.and(grow, meet, cells));
        }
    }

    public Procedure growCells() {
        return new Procedure() {
            @Override protected void setInstructions() {
                BoolVRef startCells = new BoolVRef();
                add(new SetRef<>(cells, startCells));
                add(new Show("cells", startCells));
                add(new GrowCells(cells));
                add(new Print("loop done"));
            }
        };
    }

    @Override
    public Voronoi copy() {
        return null;
    }
}
