package language.field.boolField;

import language.utils.BoolFieldLine;
import language.utils.Border;
import language.utils.Coord2D;

import java.util.HashMap;

/** Represents the abstract base type for transfer boolean fields. */
public sealed abstract class BoolFieldT<F extends BoolFieldT<F>> extends BoolField<F> permits BoolVe, BoolVf, BoolEv, BoolEf, BoolFv, BoolFe {
    /** Creates a new transfer boolean language.field base instance. */
    protected BoolFieldT(int HEIGHT, int SPAN, int BREADTH, Border border, boolean register) {
        super(HEIGHT, SPAN, BREADTH, border, register);
    }

    protected BoolFieldT(int HEIGHT, int SPAN, int BREADTH, Border border) {
        this(HEIGHT, SPAN, BREADTH, border, true);
    }

    /** Computes the end index of the data region. */
    protected static <T extends BoolFieldT<T>>
    void computeDataEnd(int HEIGHT, int SPAN, int BREADTH, T pos, T target){
        for (int i = 0; i < HEIGHT * SPAN; i++) {
            for (int j = 0; j < BREADTH-1; j++) {
                int index = i * BREADTH + j;
                target.lines[index] = BoolFieldLine.xor(pos.lines[index], pos.lines[index + 1]);
            }
            target.lines[i * BREADTH + BREADTH-1] = new BoolFieldLine(pos.lines[i * BREADTH + BREADTH - 1]);
        }
    }

    /** Copies broadcast data into the corresponding transfer language.field. */
    protected static <S extends BoolFieldS<S>, T extends BoolFieldT<T>>
    void broadcastGeneric(int HEIGHT, int SPAN, int BREADTH, S orig, T target) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            target.lines[(i * SPAN + j) * BREADTH + k] = new BoolFieldLine(orig.lines[i * SPAN + j]);
    }

    /** Computes the OR reduction for the target simplicial language.field. */
    protected static <S extends BoolFieldS<S>, T extends BoolFieldT<T>>
    void redOrGeneric(int HEIGHT, int SPAN, int BREADTH, S target, T orig) {
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
    protected static <S extends BoolFieldS<S>, T extends BoolFieldT<T>>
    void redAndGeneric(int HEIGHT, int SPAN, int BREADTH, S target, T orig) {
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
    protected static <S extends BoolFieldS<S>, T extends BoolFieldT<T>>
    void redXorGeneric(int HEIGHT, int SPAN, int BREADTH, S target, T orig) {
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

    protected static <S extends BoolFieldS<S>, T extends BoolFieldT<T>>
    void redStackGeneric(int HEIGHT, int SPAN, int BREADTH, S[] target, T orig) {
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

    protected static <T1 extends BoolFieldT<T1>, T2 extends BoolFieldT<T2>>
    void transferGeneric(T1 orig, T2 target, HashMap<Coord2D, HashMap<Coord2D, HashMap<Integer, Integer>>> masks) {
        for (Coord2D start: masks.keySet()) for (Coord2D end: masks.get(start).keySet()) for (Integer shift: masks.get(start).get(end).keySet()) {
            int startInt = orig.getInt(start);
            int mask = masks.get(start).get(end).get(shift);
            int masked = startInt & mask;

            masked = shift > 0 ? masked >>> shift : masked << -shift;

            int endInt = target.getInt(end) | masked;
            target.setInt(endInt, end);
        }
    }

    public abstract F copy();
}
