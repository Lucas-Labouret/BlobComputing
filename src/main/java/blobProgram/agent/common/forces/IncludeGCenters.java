package blobProgram.agent.common.forces;

import blobProgram.BlobV;
import blobProgram.GabrielCenter;
import blobProgram.agent.Force;
import javafx.scene.paint.Color;
import language.field.VoidF;
import language.field.boolField.BoolV;
import language.instruction.Procedure;
import ui.display.Styles;

/** Includes adjacent Gabriel Centers into the blob, and blocks removal of Gabriel Centers that are already in the blob. */
public class IncludeGCenters extends Force {
    private final BlobV state;
    private final GabrielCenter gCenters;

    public IncludeGCenters(BlobV state, GabrielCenter gCenters) {
        this.state = state;
        this.gCenters = gCenters;
    }

    private class Compute extends Procedure<VoidF> {
        public Compute(BoolV yes, BoolV no) {
            BlobV frontier = call(state.frontierV());
            BoolV centers = call(gCenters.saddle());

            show("Gabriel Centers", centers, new Styles.Factory().vertexTrue(Color.HOTPINK).make());

            set(and(frontier, centers), yes); // We want to include gCenters that are adjacent to the blob
            set(and(state, centers), no); // We don't want to remove gCenters that are already in the blob

            returnValue(new VoidF());
        }
    }

    @Override
    protected Procedure<VoidF> compute(BoolV yes, BoolV no) {
        return new Compute(yes, no);
    }
}
