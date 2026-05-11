package language.basicInstruction;

import language.Ref;

/**
 * Represents a basic instruction that prints the value of a reference.
 */
public record Show(String name, Ref<?> fieldRef) implements BasicInstruction {
    /** @return true. */
    @Override
    public boolean exec() {
        return true;
    }
}
