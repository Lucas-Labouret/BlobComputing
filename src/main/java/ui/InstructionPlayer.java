package ui;

import language.cache.Cache;
import language.instruction.Instruction;
import language.instruction.Procedure;
import language.instruction.BasicInstruction;
import language.instruction.instructionSet.Show;
import language.instruction.instructionSet.Snapshot;

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
    private boolean exec() {
        boolean done = instruction.exec();
        stepCounter++;
        if (done && isPowerOf2minus1(++loopCounter)) autoCache.push(stepCounter);
        return done;
    }

    @SuppressWarnings("BusyWait")
    private void createPlayerThread() {
        playerThread = new Thread(() -> {
            while (playing) {
                try { Thread.sleep(speed); }
                catch (InterruptedException _) { break; }

                boolean done = exec();
                tryDisplayUpdate();
                if (done && pauseAfterLoop) {
                    playing = false;
                    return;
                }
            }
        });
    }

    private void tryDisplayUpdate() {
        switch (instruction) {
            case Show show -> displayController.bind(show);
            case Procedure p when p.currentBasicInstruction() instanceof Show show -> displayController.bind(show);
            case Snapshot _ -> displayController.snapshot();
            case Procedure p when p.currentBasicInstruction() instanceof Snapshot -> displayController.snapshot();
            default -> {}
        }
    }

    public void step() {
        if (playing) return;
        exec();
        tryDisplayUpdate();
    }
    public void start() {
        playing = true;
        pauseAfterLoop = false;
        createPlayerThread();
        playerThread.start();
    }
    public void stop() {
        playing = false;
        playerThread.interrupt();
    }
    public void loop() {
        if (playing) return;
        playing = true;
        pauseAfterLoop = true;
        createPlayerThread();
        playerThread.start();
    }

    public boolean isPlaying() { return playing; }

    public void setSpeed(int ms) { speed = ms; }

    public void loopBack() {
        if (playing) return;
        if (loopCounter == 0) return; // Can't loop back if we're at the beginning
        loopCounter--;
        autoCache.retrieve(loopCounter * leafCount, instruction);
        stepCounter = loopCounter * leafCount;
        displayController.refresh();
    }

    public Cache.CacheEntry saveState() {
        return userCache.push(stepCounter);
    }

    public void restoreState(Cache.CacheEntry entry) {
        if (playing) return;
        stepCounter = userCache.retrieve(entry);
        loopCounter =  stepCounter/leafCount;
        displayController.refresh();
    }
}
