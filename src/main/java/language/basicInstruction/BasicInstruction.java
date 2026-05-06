package language.basicInstruction;

import language.Instruction;

/** Represents an instruction that performs one boolean-field operation in a single step. */
public interface BasicInstruction extends Instruction {
    /** @return true. */
    @Override
    boolean exec();
}
