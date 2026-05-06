package language;

/**
 * Represents an object in the interpreter.
 * All user defined objects must descend from this class.
 */
public abstract class Obj {
    /** @return a deep copy of this. */
    public abstract Obj copy();
}
