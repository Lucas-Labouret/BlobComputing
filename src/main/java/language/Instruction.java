package language;

/** Instruction are the basic building blocks of programs. <br>
 * They are executed one at a time, and can be either <br>
 * - BasicInstructions (which perform a single action) or <br>
 * - Procedures (which execute a sequence of instructions). */
public interface Instruction {
    /** @return false if there are more steps to execute, true if the instruction is finished. */
    boolean exec();
}
