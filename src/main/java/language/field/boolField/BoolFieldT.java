package language.field.boolField;

import language.utils.BoolFieldLine;
import language.utils.Border;

/** Represents the abstract base type for transfer boolean fields. */
public sealed abstract class BoolFieldT extends BoolField permits BoolVe, BoolVf, BoolEv, BoolEf, BoolFv, BoolFe {
    /** Creates a new transfer boolean language.field base instance. */
    protected BoolFieldT(int HEIGHT, int SPAN, int BREADTH, Border border) {
        super(HEIGHT, SPAN, BREADTH, border);
    }

    /** Computes the end index of the data region. */
    protected static void computeDataEnd(int HEIGHT, int SPAN, int BREADTH,
                                         BoolFieldT pos, BoolFieldT target){
        for (int i = 0; i < HEIGHT * SPAN; i++) {
            for (int j = 0; j < BREADTH-1; j++) {
                int index = i * BREADTH + j;
                target.lines[index] = BoolFieldLine.xor(pos.lines[index], pos.lines[index + 1]);
            }
            target.lines[i * BREADTH + BREADTH-1] = new BoolFieldLine(pos.lines[i * BREADTH + BREADTH - 1]);
        }
    }

    /** Copies broadcast data into the corresponding transfer language.field. */
    protected static void fromBroadcastGeneric(int HEIGHT, int SPAN, int BREADTH,
                                               BoolFieldS orig, BoolFieldT target) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            target.lines[(i * SPAN + j) * BREADTH + k] = new BoolFieldLine(orig.lines[i * SPAN + j]);
    }

    /** Computes the OR reduction for the target simplicial language.field. */
    protected static void redOrGeneric(int HEIGHT, int SPAN, int BREADTH,
                                       BoolFieldS target, BoolFieldT orig) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int targetIndex = i * SPAN + j;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[targetIndex] = BoolFieldLine.or(
                        target.lines[targetIndex],
                        orig.lines[targetIndex * BREADTH + k]
                );
            }
        }
    }

    /** Computes the AND reduction for the target simplicial language.field. */
    protected static void redAndGeneric(int HEIGHT, int SPAN, int BREADTH,
                                       BoolFieldS target, BoolFieldT orig) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int targetIndex = i * SPAN + j;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[targetIndex] = BoolFieldLine.and(
                        target.lines[targetIndex],
                        orig.lines[targetIndex * BREADTH + k]
                );
            }
        }
    }

    /** Computes the XOR reduction for the target simplicial language.field. */
    protected static void redXorGeneric(int HEIGHT, int SPAN, int BREADTH,
                                        BoolFieldS target, BoolFieldT orig) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int targetIndex = i * SPAN + j;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[targetIndex] = BoolFieldLine.xor(
                        target.lines[targetIndex],
                        orig.lines[targetIndex * BREADTH + k]
                );
            }
        }
    }

    protected static void redStackGeneric(int HEIGHT, int SPAN, int BREADTH,
                                          BoolFieldS[] target, BoolFieldT orig) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int targetIndex = i * SPAN + j;
            for (int k = 0; k < BREADTH; k++) {
                target[k].lines[targetIndex] = BoolFieldLine.or(
                        target[k].lines[targetIndex],
                        orig.lines[targetIndex * BREADTH + k]
                );
            }
        }
    }

    /** Applies the transfer mask to the target language.field. */
    protected static void applyTransferMask(int origLen, int targetLen,
                                            int dy, int dx, BoolFieldT mask,
                                            BoolFieldT orig, BoolFieldT res){
        for (int srcIndex = 0; srcIndex < origLen; srcIndex++) {
            int dstIndex = srcIndex + dy;
            if (dstIndex < 0 || dstIndex >= targetLen) continue;

            BoolFieldLine masked = BoolFieldLine.and(orig.lines[srcIndex], mask.lines[srcIndex]);
            BoolFieldLine shifted = BoolFieldLine.shiftLine(masked, dx);
            res.lines[dstIndex] = BoolFieldLine.or(res.lines[dstIndex], shifted);
        }
    }

    public abstract BoolFieldT copy();
}
