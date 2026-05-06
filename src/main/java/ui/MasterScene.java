package ui;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Color;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

import medium.Medium;
import medium.locusS.*;
import medium.locusT.*;
import field.FieldManager;

import language.Procedure;
import prog.obj.GrowV;
import prog.obj.RotateV;

import prog.obj.Voronoi;
import ui.display.MediumDrawer;
import ui.display.displayable.Displayable;
import ui.utils.DisplayBox;
import ui.utils.OrderableDisplayPanel;
import ui.utils.TBIntInput;
import ui.utils.ZoomableScrollPane;

import java.util.HashMap;

public class MasterScene extends BorderPane {
    static MasterScene instance;
    public static MasterScene getInstance() {
        if (instance == null)
            instance = new MasterScene();
        return instance;
    }

    private final ToolBar toolBar;
    private final OrderableDisplayPanel displays;
    private final ScrollPane sidePanel;
    private final ZoomableScrollPane scrollPane;

    private final Button stepButton = new Button("Step");
    private final Button playButton = new Button("Play");

    private volatile boolean playing = false;
    private final Thread player = new Thread() {
        @Override @SuppressWarnings("BusyWait")
        public void run() {
            while (!isInterrupted()) {
                int speed = speedInput.getValue();
                if (speed < 1) speed = 1;
                try { Thread.sleep(speed); }
                catch (InterruptedException _) { break; }
                if (playing) {
                    if (procedure.exec()) playing = false;
                }
            }
        }
    };
    private final TBIntInput speedInput = new TBIntInput("Speed");

    private final Medium medium;
    private final MediumDrawer drawer;

    private final Procedure procedure;

    private MasterScene() {
        try { medium = Medium.read("large"); }
        catch (Exception e) { throw new RuntimeException(e); }
        FieldManager.setup(medium);
        drawer = new MediumDrawer(medium);
        //Procedure.DEBUG = true;
        procedure = Voronoi.rand(5).growCells();
        //procedure = RotateV.rand().ccw();
        //procedure = GrowV.rand(medium).growDebug();

        System.out.println("Procedure has " + procedure.leafCount() + " leaves");
        System.out.println(procedure.instructionTree());

        toolBar = new ToolBar();
        displays = new OrderableDisplayPanel();
        sidePanel = new ScrollPane(displays);
        scrollPane = new ZoomableScrollPane(drawer);

        setTop(toolBar);
        setLeft(sidePanel);
        setCenter(scrollPane);

        toolBar.getItems().add(stepButton);
        stepButton.setOnAction(_ -> {
            for (int i = 0; i < 1; i++) procedure.exec();
        });
        toolBar.getItems().add(playButton);
        playButton.setOnAction(_ -> {
            playing = !playing;
            if (playing) playButton.setText("Stop");
            else playButton.setText("Play");
        });
        toolBar.getItems().add(speedInput);

        player.start();
    }

    public void display() {
        drawer.setVColors (vColorer .color(medium));
        drawer.setVeColors(veColorer.color(medium));
        drawer.setVfColors(vfColorer.color(medium));
        drawer.setEColors (eColorer .color(medium));
        drawer.setEvColors(evColorer.color(medium));
        drawer.setEfColors(efColorer.color(medium));
        drawer.setFColors (fColorer .color(medium));
        drawer.setFvColors(fvColorer.color(medium));
        drawer.setFeColors(feColorer.color(medium));
        drawer.draw();
    }

    public void addDisplay(String name, Displayable d) {
        DisplayBox box = new DisplayBox(name, d);
        displays.add(box);
        updateDisplayOrder();
    }

    @FunctionalInterface private interface VColorer  { HashMap<Vertex, Color> color(Medium medium); }
    @FunctionalInterface private interface VeColorer { HashMap<Ve,     Color> color(Medium medium); }
    @FunctionalInterface private interface VfColorer { HashMap<Vf,     Color> color(Medium medium); }
    @FunctionalInterface private interface EColorer  { HashMap<Edge,   Color> color(Medium medium); }
    @FunctionalInterface private interface EvColorer { HashMap<Ev,     Color> color(Medium medium); }
    @FunctionalInterface private interface EfColorer { HashMap<Ef,     Color> color(Medium medium); }
    @FunctionalInterface private interface FColorer  { HashMap<Face,   Color> color(Medium medium); }
    @FunctionalInterface private interface FvColorer { HashMap<Fv,     Color> color(Medium medium); }
    @FunctionalInterface private interface FeColorer { HashMap<Fe,     Color> color(Medium medium); }

    private VColorer  vColorer  = (_ -> new HashMap<>());
    private VeColorer veColorer = (_ -> new HashMap<>());
    private VfColorer vfColorer = (_ -> new HashMap<>());
    private EColorer  eColorer  = (_ -> new HashMap<>());
    private EvColorer evColorer = (_ -> new HashMap<>());
    private EfColorer efColorer = (_ -> new HashMap<>());
    private FColorer  fColorer  = (_ -> new HashMap<>());
    private FvColorer fvColorer = (_ -> new HashMap<>());
    private FeColorer feColorer = (_ -> new HashMap<>());

    public void updateDisplayOrder() {
        boolean vSet  = false;
        boolean veSet = false;
        boolean vfSet = false;
        boolean eSet  = false;
        boolean evSet = false;
        boolean efSet = false;
        boolean fSet  = false;
        boolean fvSet = false;
        boolean feSet = false;

        for (Node node: displays.getChildren()) {
            if (!(node instanceof DisplayBox box)) continue;
            if (!box.isShown()) continue;
            Displayable d = box.displayable;

            if (d.updatesV() && !vSet) {
                vSet = true;
                vColorer = d::displayV;
            }
            if (d.updatesVe() && !veSet) {
                veSet = true;
                veColorer = d::displayVe;
            }
            if (d.updatesVf() && !vfSet) {
                vfSet = true;
                vfColorer = d::displayVf;
            }
            if (d.updatesE() && !eSet) {
                eSet = true;
                eColorer = d::displayE;
            }
            if (d.updatesEv() && !evSet) {
                evSet = true;
                evColorer = d::displayEv;
            }
            if (d.updatesEf() && !efSet) {
                efSet = true;
                efColorer = d::displayEf;
            }
            if (d.updatesF() && !fSet) {
                fSet = true;
                fColorer = d::displayF;
            }
            if (d.updatesFv() && !fvSet) {
                fvSet = true;
                fvColorer = d::displayFv;
            }
            if (d.updatesFe() && !feSet) {
                feSet = true;
                feColorer = d::displayFe;
            }
        }

        drawer.setDrawV(vSet);
        drawer.setDrawVe(veSet);
        drawer.setDrawVf(vfSet);
        drawer.setDrawE(eSet);
        drawer.setDrawEv(evSet);
        drawer.setDrawEf(efSet);
        drawer.setDrawF(fSet);
        drawer.setDrawFv(fvSet);
        drawer.setDrawFe(feSet);

        display();
    }

    public void stop() {
        player.interrupt();
    }
}
