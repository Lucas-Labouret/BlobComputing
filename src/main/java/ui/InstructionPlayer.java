package ui;

import language.Instruction;
import language.Procedure;
import language.basicInstruction.BasicInstruction;
import language.basicInstruction.Show;
import ui.display.Binder;

public class InstructionPlayer {
    private final Instruction instruction;
    private final Binder binder;

    private volatile boolean playing = false;
    private boolean pauseAfterLoop = false;

    private volatile int speed = 0;

    private Thread playerThread;

    public InstructionPlayer(Instruction instruction, Binder binder) {
        this.instruction = instruction;
        this.binder = binder;
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

                switch (instruction) {
                    case Show show -> binder.bind(show);
                    case Procedure p when p.currentBasicInstruction() instanceof Show show -> binder.bind(show);
                    default -> {}
                }
                boolean done = instruction.exec();
                if (done && pauseAfterLoop) {
                    playing = false;
                    return;
                }
            }
        });
    }

    public void step() {
        if (playing) return;
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
