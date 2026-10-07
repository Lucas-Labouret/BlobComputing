package ui;

import language.cache.Cache;
import language.instruction.BasicInstruction;
import language.instruction.Instruction;
import language.instruction.Procedure;
import language.instruction.instructionSet.Show;
import language.instruction.instructionSet.Snapshot;

/**
 * The InstructionPlayer class is responsible for executing a given Instruction,
 * managing its execution state, and requesting updates to the display accordingly.
 * It provides methods to step through the instruction, start and stop execution,
 * loop through the instruction, and manage playback speed.
 * <p>
 * The class also maintains caches for automatic and user-defined states,
 * allowing for state saving and restoration.
 */
public class InstructionPlayer {
    private final Instruction instruction;
    private final int leafCount;
    private final Cache autoCache = new Cache();
    private final Cache userCache = new Cache();
    private final DisplayController displayController;

    private volatile boolean playing = false;
    private boolean pauseAfterLoop = false;

    private volatile int speed = 0;

    private Thread playerThread;

    private static final boolean printLeafCount = true;
    private static final boolean printInstructionTree = true;
    public InstructionPlayer(Instruction instruction, DisplayController displayController) {
        this.instruction = instruction;
        leafCount = instruction.leafCount();
        this.displayController = displayController;

        autoCache.push(0);

        if (printLeafCount) System.out.println(leafCount + " leaves");
        if (printInstructionTree) System.out.println(switch (instruction) {
            case Procedure p -> p.instructionTree();
            case BasicInstruction i -> i.getClass().getSimpleName();
        });
    }

    /** Quickly check if n = 2^k - 1 for some k >= 0. */
    boolean isPowerOf2minus1(long n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");

        //propagate the highest set bit to the right
        long value = n;
        value |= value >> 1;
        value |= value >> 2;
        value |= value >> 4;
        value |= value >> 8;
        value |= value >> 16;
        value |= value >> 32;

        return n == value;
    }

    long stepCounter = 0;
    long loopCounter = 0;
    /**
     * Executes the current instruction, requests display updates,
     * and automatically saves the state with logarithmic frequency.
     */
    private boolean exec() {
        BasicInstruction current = switch (instruction) {
            case Procedure p -> p.currentBasicInstruction();
            case BasicInstruction bi -> bi;
        };

        boolean done = instruction.exec();
        updateDisplay(current);

        stepCounter++;
        if (done && isPowerOf2minus1(++loopCounter)) autoCache.push(stepCounter);
        if (stepCounter%leafCount == 0) System.out.println("Loop : " + loopCounter);

        return done;
    }

    /**
     * Creates a new thread for executing the instruction in a loop.
     * The thread waits for the specified speed between executions and checks for interruptions.
     * If the instruction is done and pauseAfterLoop is true, it stops playing.
     */
    @SuppressWarnings("BusyWait")
    private void createPlayerThread() {
        playerThread = new Thread(() -> {
            while (playing) {
                try { Thread.sleep(speed); }
                catch (InterruptedException _) { break; }

                boolean done = exec();
                if (done && pauseAfterLoop) {
                    playing = false;
                    return;
                }
            }
        });
    }

    /** Request the display controller to update the display based on the current instruction. */
    private void updateDisplay(BasicInstruction instruction) {
        switch (instruction) {
            case Show<?> show -> displayController.bind(show);
            case Snapshot _ -> displayController.snapshot();
            default -> {}
        }
    }

    /** Executes a single step of the instruction. */
    public void step() {
        if (playing) return;
        exec();
    }
    /** Executes the instruction in a loop until it is interrupted. */
    public void start() {
        playing = true;
        pauseAfterLoop = false;
        createPlayerThread();
        playerThread.start();
    }
    /** Stops the execution of the instruction. */
    public void stop() {
        playing = false;
        playerThread.interrupt();
    }
    /** Executes the instruction until it completes one full loop, then pauses. */
    public void loop() {
        if (playing) return;
        playing = true;
        pauseAfterLoop = true;
        createPlayerThread();
        playerThread.start();
    }

    /** Returns true if the instruction is currently playing. */
    public boolean isPlaying() { return playing; }

    /** Sets the speed of execution in milliseconds (higher is slower). */
    public void setSpeed(int ms) { speed = ms; }

    /** Return the current state to the previous loop state, if possible. */
    public void loopBack() {
        if (playing) return;
        if (loopCounter == 0) return; // Can't loop back if we're at the beginning
        loopCounter--;
        autoCache.retrieve(loopCounter * leafCount, instruction);
        stepCounter = loopCounter * leafCount;
        displayController.refresh();
    }

    /** Saves the current state of the execution to the cache. */
    public Cache.CacheEntry saveState() {
        return userCache.push(stepCounter);
    }

    /** Restores the state of the execution from the cache. */
    public void restoreState(Cache.CacheEntry entry) {
        if (playing) return;
        stepCounter = userCache.retrieve(entry);
        loopCounter =  stepCounter/leafCount;
        displayController.refresh();
    }
}
