package language;

import java.util.ArrayList;

/** Represents an instruction composed of a sequence of sub-instructions. */
public abstract class Procedure implements Instruction {
    private int instrCounter = 0;
    private final ArrayList<Instruction> instr;
    private boolean initialized = false;

    /** Creates a new Procedure. */
    public Procedure() {
        instr = new ArrayList<>();
    }

    /** Adds the given instruction to this procedure. */
    protected void add(Instruction i) {
        instr.add(i);
    }

    /** Populates this procedure with its instructions. */
    protected abstract void setInstructions();

    public int leafCount() {
        instr.clear();
        setInstructions();
        int count = 0;
        for (Instruction i : instr) {
            if (i instanceof Procedure p) count += p.leafCount();
            else count++;
        }
        return count;
    }
    public String instructionTree() { return instructionTree(0);}
    public String instructionTree(int depth) {
        instr.clear();
        setInstructions();
        StringBuilder sb = new StringBuilder();
        sb.repeat("|  ", depth).append("--- ").append(this.getClass().getSimpleName()).append("\n");
        for (Instruction i : instr) {
            if (i instanceof Procedure p) sb.append(p.instructionTree(depth + 1));
            else sb.append("|  ".repeat(depth + 1)).append("--- ").append(i.getClass().getSimpleName()).append("\n");
        }
        return sb.toString();
    }

    /** @return false if there are more instructions to execute, true if the loop is finished. */
    @Override
    public final boolean exec() {
        // Initialize instructions on first execution if not already done
        if (!initialized) {
            instr.clear();
            setInstructions();
            initialized = true;
        }

        boolean done = instr.get(instrCounter).exec();
        if (done) instrCounter++; // If the current instruction is done, we move to the next

        if (instrCounter == instr.size()) {
            // There are no more instruction to perform.
            // We reset the counter so that the loop can be executed again,
            // and return true to indicate that the loop is finished
            instrCounter = 0;
            return true;
        }
         else return false;
    }
}
