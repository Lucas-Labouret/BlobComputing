package blobProgram.agent.voronoi;

import blobProgram.BlobV;
import blobProgram.agent.Force;
import language.field.boolField.BoolV;
import language.fieldRef.boolField.BoolVRef;
import language.instruction.Procedure;

public class MaintainBlob extends Force {
    BlobV blob;

    public MaintainBlob(BlobV blob) {
        this.blob = blob;
    }

    private class _Compute extends Procedure {
        public _Compute(BoolVRef yes, BoolVRef no) {
            set(new BoolVRef(BoolV.zeroes()), yes);

            // Cannot add meeting points as to not merge a blob
            call(blob.meet(no));

            // Cannot remove meeting points of the inverse as to not split a blob
            BlobV inverseBlob = new BlobV();
            not(blob, inverseBlob);
            BlobV inverseMeet = new BlobV();
            call(inverseBlob.meet(inverseMeet));

            or(inverseMeet, no, no);
        }
    }

    @Override
    protected Procedure _compute(BoolVRef yes, BoolVRef no) {
        return new _Compute(yes, no);
    }
}
