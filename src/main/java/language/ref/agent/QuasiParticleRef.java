package language.ref.agent;

import language.Ref;

import language.obj.agent.QuasiParticle;

/** Represents a reference to a QuasiParticle value */
public class QuasiParticleRef extends Ref<QuasiParticle> {
    private QuasiParticle field;

    /** Sets the referenced value. */
    @Override
    public void set(QuasiParticle value) { field = value; }

    /** @return the referenced value. */
    @Override
    public QuasiParticle get() { return field; }

    /** @return a reference to the given QuasiParticle. */
    public static QuasiParticleRef of(QuasiParticle value) {
        QuasiParticleRef ref = new QuasiParticleRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public QuasiParticleRef copy() { 
        if (field != null) return QuasiParticleRef.of(field.copy());
        return new QuasiParticleRef();
    }
}
