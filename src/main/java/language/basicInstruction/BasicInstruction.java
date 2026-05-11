package language.basicInstruction;

import language.Instruction;

/** Represents an instruction that performs one boolean-field operation in a single step. */
public non-sealed interface BasicInstruction extends Instruction {
    default int leafCount() { return 1; }
    /** @return true. */
    @Override
    boolean exec();
}
