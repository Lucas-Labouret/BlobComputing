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
            BoolVRef frontierV = new BoolVRef();
            BoolVRef notCells = new BoolVRef();

            add(BitOp.not(cells, notCells));
            add(BitOp.and(notCells, grow, frontierV));

            BoolERef frontierEGrow = new BoolERef();
            BoolERef frontierEGrowInv = new BoolERef();
            BoolVeRef ve = new BoolVeRef();
            BoolEvRef ev = new BoolEvRef();

            add(CommOp.broadcast(frontierV, ve));
            add(CommOp.transfer(ve, ev));
            add(CommOp.redAnd(ev, frontierEGrow));
            add(BitOp.not(frontierEGrow, frontierEGrowInv));

            BoolERef meetE = new BoolERef();

            add(CommOp.broadcast(frontierV, ve));
            add(CommOp.transfer(ve, ev));
            add(CommOp.redAnd(ev, meetE));
            add(BitOp.and(meetE, frontierEGrowInv, meetE));

            add(CommOp.broadcast(meetE, ev));
            add(CommOp.transfer(ev, ve));

            // Contains mergeV, i.e. the vertices that would cause cells to merge should they grow there simultaneously
            add(CommOp.redOr(ve, meet));

            add(CommOp.broadcast(cells, ve));
            add(CommOp.transfer(ve, ev));

            BoolERef frontierE = new BoolERef();

            add(new FrontierE(cells, frontierE));
            add(CommOp.broadcast(frontierE, ev));
            add(CommOp.transfer(ev, ve));

            BoolVfRef vf = new BoolVfRef();
            BoolVeRef cw = new BoolVeRef();
            BoolVeRef ccw = new BoolVeRef();

            add(CommOp.rotCW(ve, vf));
            add(CommOp.rotCW(vf, cw));
            add(CommOp.rotCCW(ve, vf));
            add(CommOp.rotCCW(vf, ccw));
            add(BitOp.xor(cw, ccw, ve));

            IntVRef connectedComponents = IntVRef.of(new IntV(4));
            BoolVRef meetV = new BoolVRef();

            add(IntField.redAdd(ve, connectedComponents));
            add(new Show("ve", ve));
            add(new Show("connectedComponents", connectedComponents));

            // Contains meetV, i.e. the vertices that would cause cells to overlap should they grow there
            add(IntField.gt(connectedComponents, IntVRef.of(IntV.of(3, 4)), meetV));

            add(new Show("meetV", meetV));

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
            add(BitOp.and(grow, meet, grow));
            add(BitOp.or(cells, grow, cells));
        }
    }

    public Procedure growCells() {
        return new Procedure() {
            @Override protected void setInstructions() {
                add(new Show("cells", cells));
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
