package language;

import language.cache.Cache;

/** Represents a mutable reference used to pass and store values in interpreter programs. */
public abstract class Ref<T extends Obj> {
    /** Creates a new empty reference */
    public Ref(){
        //if (!Cache.isCaching()) Cache.register(this);
    }

    /** Sets the referenced value. */
    public abstract void set(T obj);

    /** @return the referenced value. */
    public abstract T get();

    /** @return a reference to a copy of the referenced value. */
    public abstract Ref<T> copy();
}