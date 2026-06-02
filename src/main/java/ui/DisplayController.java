package ui;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import language.Ref;
import language.instruction.instructionSet.Show;
import language.ref.field.boolField.*;
import language.ref.field.intField.IntVRef;
import language.ref.field.intField.IntVeRef;
import ui.display.MediumDrawer;
import ui.display.displayable.Displayable;
import ui.display.displayable.boolFieldDisplay.*;
import ui.display.displayable.intFieldDisplay.IntVDisplay;
import ui.display.displayable.intFieldDisplay.IntVeDisplay;
import ui.utils.DisplayBox;
import ui.utils.OrderableDisplayPanel;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;


public class DisplayController {
    private final OrderableDisplayPanel displays;

    private final MediumDrawer drawer;

    public DisplayController(MediumDrawer drawer, OrderableDisplayPanel displays) {
        this.displays = displays;
        this.drawer = drawer;
    }

    public void refresh() {
        Platform.runLater(drawer::draw);
    }

    public void bind(Show show) {
        Platform.runLater(() -> _bind(show));
    }

    private final HashSet<Show> bound = new HashSet<>();
    private void _bind(Show show) {
        String name = show.name();
        Ref<?> ref = show.ref();

        if (!bound.contains(show)) switch (ref) {
            case BoolVRef boolVRef   -> createDisplay(name, new BoolVDisplay(boolVRef));
            case BoolVeRef boolVeRef -> createDisplay(name, new BoolVeDisplay(boolVeRef));
            case BoolVfRef boolVfRef -> createDisplay(name, new BoolVfDisplay(boolVfRef));
            case BoolERef boolERef   -> createDisplay(name, new BoolEDisplay(boolERef));
            case BoolEvRef boolEvRef -> createDisplay(name, new BoolEvDisplay(boolEvRef));
            case BoolEfRef boolEfRef -> createDisplay(name, new BoolEfDisplay(boolEfRef));
            case BoolFRef boolFRef   -> createDisplay(name, new BoolFDisplay(boolFRef));
            case BoolFvRef boolFvRef -> createDisplay(name, new BoolFvDisplay(boolFvRef));
            case BoolFeRef boolFeRef -> createDisplay(name, new BoolFeDisplay(boolFeRef));

            case IntVRef intVRef     -> createDisplay(name, new IntVDisplay(intVRef));
            case IntVeRef intVeRef   -> createDisplay(name, new IntVeDisplay(intVeRef));

            default -> throw new IllegalArgumentException("Unsupported type for display: " + ref.getClass().getName());
        }

        bound.add(show);
        drawer.draw();
    }

    public void snapshot() {
        Platform.runLater(this::_snapshot);
    }

    final String path = "snapshots/";
    final String rootName = (new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss")).format(new Date());
    int snapshotCount = 0;
    public void _snapshot() {
        WritableImage image = drawer.snapshot(null, null);
        String filename = rootName + "_" + snapshotCount + ".png";
        try {
            File file = new File(path + filename);
            RenderedImage renderedImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(renderedImage, "png", file);
            snapshotCount++;
        } catch (java.io.IOException e) {
            System.out.println("Failed to save snapshot: " + e.getMessage());
        }
    }

    private void createDisplay(String name, Displayable d) {
        DisplayBox box = new DisplayBox(name, d, this);
        displays.add(box);
    }

    public void addDisplay(Displayable d) {
        drawer.addDisplay(d);
        drawer.draw();
    }

    public void removeDisplay(Displayable d) {
        drawer.removeDisplay(d);
        drawer.draw();
    }
}
