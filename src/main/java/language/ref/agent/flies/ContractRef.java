package language.ref.agent.flies;

import language.Ref;

import language.obj.agent.flies.Contract;

/** Represents a reference to a Contract value */
public class ContractRef extends Ref<Contract> {
    private Contract field;

    /** Sets the referenced value. */
    @Override
    public void set(Contract value) { field = value; }

    /** @return the referenced value. */
    @Override
    public Contract get() { return field; }

    /** @return a reference to the given Contract. */
    public static ContractRef of(Contract value) {
        ContractRef ref = new ContractRef();
        ref.field = value;
        return ref;
    }

/** @return a reference to a copy of the referenced value. */
    @Override
    public ContractRef copy() { 
        if (field != null) return ContractRef.of(field.copy());
        return new ContractRef();
    }
}
