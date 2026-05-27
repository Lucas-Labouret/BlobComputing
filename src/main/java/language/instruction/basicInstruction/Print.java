package language.instruction.basicInstruction;

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
