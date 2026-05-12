package language;

/** Represents a mutable reference used to pass and store values in interpreter programs. */
public abstract class Ref<T extends Obj> {
    /** Creates a new empty reference */
    public Ref(){}

    /** Sets the referenced value. */
    public abstract void set(T obj);

    /** @return the referenced value. */
    public abstract T get();

    /** @return a reference to a copy of the referenced value. */
    public abstract Ref<T> copy();

    public static String typeName(Ref<?> ref) {
        return ref == null || ref.get() == null ? "null" : ref.get().getClass().getSimpleName();
    }
}
