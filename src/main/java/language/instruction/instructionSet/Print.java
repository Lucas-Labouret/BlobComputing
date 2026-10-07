package language.instruction.instructionSet;

import language.instruction.BasicInstruction;

/** Print a message to the console. */
public class Print implements BasicInstruction {
    private final String message;

    public Print(String message) {
        this.message = message;
    }

    @Override
    public boolean exec() {
        System.out.println(message);
        return true;
    }
}
