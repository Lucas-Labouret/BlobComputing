package language.field.boolField;

import language.field.BoolFieldLine;
import language.field.Border;
import language.field.Coord2D;

import java.util.HashMap;
import java.util.function.BiFunction;

/**
 * Represents the abstract base type for transfer boolean fields.
 * A transfer field is a field over the Ve, Vf, Ev, Ef, Fv, or Fe loci.
 */
public sealed abstract class BoolFieldT<F extends BoolFieldT<F>> extends BoolField<F> permits BoolVe, BoolVf, BoolEv, BoolEf, BoolFv, BoolFe {
    /**
     * Creates a new boolean transfer field base instance.
     * @param HEIGHT the height of the field
     * @param SPAN the span of the field
     * @param BREADTH the breadth of the field
     * @param border the border type of the field
     * @param register whether to register the field with the cache
     */
    protected BoolFieldT(int HEIGHT, int SPAN, int BREADTH, Border border, boolean register) {
        super(HEIGHT, SPAN, BREADTH, border, register);
    }

    /**
     * Creates a new boolean transfer field base instance. This constructor will register the field with the cache.
     * @param HEIGHT the height of the field
     * @param SPAN the span of the field
     * @param BREADTH the breadth of the field
     * @param border the border type of the field
     */
    protected BoolFieldT(int HEIGHT, int SPAN, int BREADTH, Border border) {
        this(HEIGHT, SPAN, BREADTH, border, true);
    }

    /**
     * Valid data (i.e. data that corresponds to an existing locus)
     * is stored in contiguous blocks of size BREADTH on the vertical axis of the data array.
     * <p>
     * This method computes a field that contains TRUE for the last valid data point in each block of each column,
     * and FALSE for all other points.
     */
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

    /**
     * Perform a broadcast from a simplicial field to a child transfer field.
     * This corresponds to copying the data at y line in the simplicial field
     * to every line of the y-th contiguous block of size BREADTH in the transfer field.
     */
    protected static <S extends BoolFieldS<S>, T extends BoolFieldT<T>>
    void broadcastGeneric(int HEIGHT, int SPAN, int BREADTH, S orig, T target) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) for (int k = 0; k < BREADTH; k++)
            target.lines[(i * SPAN + j) * BREADTH + k] = new BoolFieldLine(orig.lines[i * SPAN + j]);
    }

    /** Computes the reduction for the target simplicial field. */
    protected static <S extends BoolFieldS<S>, T extends BoolFieldT<T>>
    void redGeneric(int HEIGHT, int SPAN, int BREADTH, S target, T orig, BiFunction<BoolFieldLine, BoolFieldLine, BoolFieldLine> reduction) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int targetIndex = i * SPAN + j;
            for (int k = 0; k < BREADTH; k++) {
                target.lines[targetIndex] = reduction.apply(
                        target.lines[targetIndex],
                        orig.lines[targetIndex * BREADTH + k]
                );
            }
        }
    }

    /** Computes the stack reduction for the target simplicial field. */
    protected static <S extends BoolFieldS<S>, T extends BoolFieldT<T>>
    void redStackGeneric(int HEIGHT, int SPAN, int BREADTH, S[] target, T orig) {
        for (int i = 0; i < HEIGHT; i++) for (int j = 0; j < SPAN; j++) {
            int targetIndex = i * SPAN + j;
            for (int k = 0; k < BREADTH; k++) {
                target[k].lines[targetIndex] = orig.lines[targetIndex * BREADTH + k].copy();
            }
        }
    }

    /**
     * Transfers data from one transfer field to a companion transfer field.
     * Masks are used to which bits should be transferred to each integer in the target field, and how much they should be shifted.
     */
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
