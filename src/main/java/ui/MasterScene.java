package ui;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import language.instruction.Instruction;
import language.obj.agents.Flies;
import language.utils.BoolFieldManager;
import medium.Medium;
import ui.display.MediumDrawer;
import ui.utils.CacheMenu;
import ui.utils.OrderableDisplayPanel;
import ui.utils.TBIntInput;
import ui.utils.ZoomableScrollPane;

public class MasterScene extends BorderPane {
    private final ToolBar toolBar;
    private final OrderableDisplayPanel displays;
    private final ScrollPane sidePanel;
    private final ZoomableScrollPane scrollPane;

    private final Button stepButton = new Button("Step");
    private final Button loopButton = new Button("Loop");
    private final Button backButton = new Button("Loop Back");
    private final Button playButton = new Button("Play");

    private final CacheMenu quicksaveMenu;
    private final Button quickloadButton = new Button("Quick Load");

    private final Button snapButton = new Button("Snapshot");

    private final TBIntInput speedInput = new TBIntInput("Speed");
    private final InstructionPlayer player;

    private final Medium medium;
    private final DisplayController displayController;

    public MasterScene() {
        try { medium = Medium.read("large"); }
        catch (Exception e) { throw new RuntimeException(e); }

        toolBar = new ToolBar();
        displays = new OrderableDisplayPanel();
        sidePanel = new ScrollPane(displays);

        MediumDrawer drawer = new MediumDrawer(medium);
        displayController = new DisplayController(drawer, displays);
        scrollPane = new ZoomableScrollPane(drawer);

        BoolFieldManager.setup(medium);
        //Instruction instruction = RotateV.rand().ccw();
        //Instruction instruction = BlobV.rand(medium).showGrow();
        //Instruction instruction = BlobV.rand(5).voronoi();
        //Instruction instruction = new BlobV().voronoi();
        //Instruction instruction = new Rand().showRand();
        Instruction instruction = Flies.rand(6).showFlies();

        player = new InstructionPlayer(instruction, displayController);

        setTop(toolBar);
        setLeft(sidePanel);
        setCenter(scrollPane);

        Region spacer1 = new Region();
        Region spacer2 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        HBox.setHgrow(spacer2, Priority.ALWAYS);

        toolBar.getItems().add(stepButton);
        toolBar.getItems().add(loopButton);
        toolBar.getItems().add(backButton);
        toolBar.getItems().add(playButton);
        toolBar.getItems().add(speedInput);

        toolBar.getItems().add(spacer1);

        quicksaveMenu = new CacheMenu(player);
        toolBar.getItems().add(quicksaveMenu);
        toolBar.getItems().add(quickloadButton);

        toolBar.getItems().add(spacer2);

        toolBar.getItems().add(snapButton);

        addHandlers();
    }

    private void addHandlers() {
        stepButton.setOnAction(_ -> player.step());
        loopButton.setOnAction(_ -> player.loop());
        backButton.setOnAction(_ -> player.loopBack());
        playButton.setOnAction(_ -> {
            if (player.isPlaying()) {
                player.stop();
                playButton.setText("Start");
            }
            else {
                player.start();
                playButton.setText("Stop");
            }
        });
        speedInput.setOnChange(player::setSpeed);

        quickloadButton.setOnAction(_ -> {
            player.restoreState(quicksaveMenu.getValue().entry);
        });

        snapButton.setOnAction(_ -> displayController.snapshot());
    }

    public void stop() {
        player.stop();
    }
}
