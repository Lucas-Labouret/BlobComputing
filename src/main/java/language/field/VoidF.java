package language.field;

/** Represents the absence of a field. This is used for instructions that do not return a value. */
public final class VoidF extends Field<VoidF> {
    @Override public VoidF copy() { return new VoidF(); }
    @Override public void set(VoidF other) {}
    @Override public void clear() {}
    @Override public void copyFrom(Field<?> other) {}
}
