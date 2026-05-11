package ui;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

import language.Instruction;
import language.basicInstruction.BasicInstruction;
import medium.Medium;
import medium.locusS.*;
import medium.locusT.*;
import field.FieldManager;

import language.Procedure;
import prog.obj.GrowV;
import prog.obj.RotateV;

import prog.obj.Voronoi;
import ui.display.Binder;
import ui.display.MediumDrawer;
import ui.display.displayable.Displayable;
import ui.utils.DisplayBox;
import ui.utils.OrderableDisplayPanel;
import ui.utils.TBIntInput;
import ui.utils.ZoomableScrollPane;

import java.util.HashMap;

public class MasterScene extends BorderPane {
    private ToolBar toolBar;
    private OrderableDisplayPanel displays;
    private ScrollPane sidePanel;
    private ZoomableScrollPane scrollPane;

    private final Button stepButton = new Button("Step");
    private final Button loopButton = new Button("Loop");
    private final Button playButton = new Button("Play");

    private final TBIntInput speedInput = new TBIntInput("Speed");
    private final InstructionPlayer player;

    private final Medium medium;
    private DisplayController displayController;

    public MasterScene() {
        try { medium = Medium.read("large"); }
        catch (Exception e) { throw new RuntimeException(e); }
        buildUI();

        FieldManager.setup(medium);
        Instruction instruction = Voronoi.rand(5).growCells();
        //Instruction instruction = RotateV.rand().ccw();
        //Instruction instruction = GrowV.rand(medium).growDebug();

        player = new InstructionPlayer(instruction, new Binder(displayController));

        addHandlers();
    }

    private void buildUI() {
        toolBar = new ToolBar();
        displays = new OrderableDisplayPanel();
        sidePanel = new ScrollPane(displays);
        displayController = new DisplayController(medium, displays);
        scrollPane = new ZoomableScrollPane(displayController.getDrawer());

        setTop(toolBar);
        setLeft(sidePanel);
        setCenter(scrollPane);

        toolBar.getItems().add(stepButton);
        toolBar.getItems().add(loopButton);
        toolBar.getItems().add(playButton);
        toolBar.getItems().add(speedInput);
    }

    private void addHandlers() {
        stepButton.setOnAction(_ -> player.step());
        loopButton.setOnAction(_ -> player.loop());
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
    }

    public void stop() {
        player.stop();
    }
}
