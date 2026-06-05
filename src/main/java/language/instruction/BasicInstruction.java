package language.instruction;

/** Represents an instruction that performs one boolean-language.field operation in a single step. */
public non-sealed interface BasicInstruction extends Instruction {
    default int leafCount() { return 1; }
    /** @return true. */
    @Override
    boolean exec();
}
