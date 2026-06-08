package language.fieldRef;

import language.cache.Cache;
import language.field.Field;

/** Represents a mutable reference used to pass and store values in interpreter programs. */
public abstract class Ref<T extends Field> {
    /** Creates a new reference base. */
    protected Ref(){
        Cache.register(this);
        try { throw new RuntimeException("New ref created"); }
        catch (RuntimeException e) { e.printStackTrace(); }
    }

    /** Sets the referenced value. */
    public abstract void set(T obj);

    /** @return the referenced value. */
    public abstract T get();

    /** @return a reference to a copy of the referenced value. */
    public abstract Ref<T> copy();
}

