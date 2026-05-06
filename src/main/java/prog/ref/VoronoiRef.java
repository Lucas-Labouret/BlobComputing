package prog.ref;

import language.Ref;

import prog.obj.Voronoi;

/** Represents a reference to a Voronoi value */
public class VoronoiRef extends Ref<Voronoi> {
    private Voronoi field;

    /** Sets the referenced value. */
    @Override
    public void set(Voronoi value) { field = value; }

    /** @return the referenced value. */
    @Override
    public Voronoi get() { return field; }

    /** @return a reference to the given Voronoi. */
    public static VoronoiRef of(Voronoi value) {
        VoronoiRef ref = new VoronoiRef();
        ref.field = value;
        return ref;
    }

    /** @return a reference to a copy of the referenced value. */
    @Override
    public VoronoiRef copy() { return VoronoiRef.of(field.copy()); }
}
