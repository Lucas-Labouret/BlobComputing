package ui;

import language.Instruction;
import language.Procedure;
import language.basicInstruction.BasicInstruction;
import language.basicInstruction.Show;
import language.basicInstruction.Snapshot;

public class InstructionPlayer {
    private final Instruction instruction;
    private final DisplayController displayController;

    private volatile boolean playing = false;
    private boolean pauseAfterLoop = false;

    private volatile int speed = 0;

    private Thread playerThread;

    public InstructionPlayer(Instruction instruction, DisplayController displayController) {
        this.instruction = instruction;
        this.displayController = displayController;
        createPlayerThread();

        System.out.println(instruction.leafCount() + " leaves");
        System.out.println(switch (instruction) {
            case Procedure p -> p.instructionTree();
            case BasicInstruction i -> i.getClass().getSimpleName();
        });
    }

    @SuppressWarnings("BusyWait")
    private void createPlayerThread() {
        playerThread = new Thread(() -> {
            while (playing) {
                try { Thread.sleep(speed); }
                catch (InterruptedException _) { break; }

                tryDisplayUpdate();
                boolean done = instruction.exec();
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
        tryDisplayUpdate();
        instruction.exec();
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
}
