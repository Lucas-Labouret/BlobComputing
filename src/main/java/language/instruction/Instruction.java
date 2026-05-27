package language.instruction;

import language.instruction.basicInstruction.BasicInstruction;

/** Instruction are the basic building blocks of programs. <br>
 * They are executed one at a time, and can be either <br>
 * - BasicInstructions (which perform a single action) or <br>
 * - Procedures (which execute a sequence of instructions). */
public sealed interface Instruction permits BasicInstruction, Procedure {
    /** @return the number of basic instruction calls of the instruction */
    int leafCount();
    /** @return false if there are more steps to execute, true if the instruction is finished. */
    boolean exec();
}
