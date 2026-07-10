package ui;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;

import language.fieldRef.Ref;
import language.fieldRef.intField.IntERef;
import language.fieldRef.intField.IntEvRef;
import language.instruction.instructionSet.Show;
import language.fieldRef.boolField.*;
import language.fieldRef.intField.IntVRef;
import language.fieldRef.intField.IntVeRef;
import ui.display.MediumDrawer;
import ui.display.Styles;
import ui.display.displayable.Displayable;
import ui.display.displayable.boolFieldDisplay.*;
import ui.display.displayable.intFieldDisplay.IntEDisplay;
import ui.display.displayable.intFieldDisplay.IntEvDisplay;
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
import java.util.Optional;


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
        Optional<Styles.Style> style = show.style();

        if (!bound.contains(show)) switch (ref) {
            case BoolVRef boolVRef   -> createDisplay(name, new BoolVDisplay(boolVRef, style.orElse(Styles.DEFAULT)));
            case BoolVeRef boolVeRef -> createDisplay(name, new BoolVeDisplay(boolVeRef, style.orElse(Styles.DEFAULT)));
            case BoolVfRef boolVfRef -> createDisplay(name, new BoolVfDisplay(boolVfRef, style.orElse(Styles.DEFAULT)));
            case BoolERef boolERef   -> createDisplay(name, new BoolEDisplay(boolERef, style.orElse(Styles.DEFAULT)));
            case BoolEvRef boolEvRef -> createDisplay(name, new BoolEvDisplay(boolEvRef, style.orElse(Styles.DEFAULT)));
            case BoolEfRef boolEfRef -> createDisplay(name, new BoolEfDisplay(boolEfRef, style.orElse(Styles.DEFAULT)));
            case BoolFRef boolFRef   -> createDisplay(name, new BoolFDisplay(boolFRef, style.orElse(Styles.DEFAULT)));
            case BoolFvRef boolFvRef -> createDisplay(name, new BoolFvDisplay(boolFvRef, style.orElse(Styles.DEFAULT)));
            case BoolFeRef boolFeRef -> createDisplay(name, new BoolFeDisplay(boolFeRef, style.orElse(Styles.DEFAULT)));

            case IntVRef intVRef     -> createDisplay(name, new IntVDisplay(intVRef, style.orElse(Styles.DEFAULT_INT)));
            case IntVeRef intVeRef   -> createDisplay(name, new IntVeDisplay(intVeRef, style.orElse(Styles.DEFAULT_INT)));
            case IntERef intERef     -> createDisplay(name, new IntEDisplay(intERef, style.orElse(Styles.DEFAULT_INT)));
            case IntEvRef intEvRef   -> createDisplay(name, new IntEvDisplay(intEvRef, style.orElse(Styles.DEFAULT_INT)));

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

    public void addColorDisplay(Displayable d) {
        drawer.addColorDisplay(d);
        drawer.draw();
    }

    public void removeColorDisplay(Displayable d) {
        drawer.removeColorDisplay(d);
        drawer.draw();
    }

    public void addStringDisplay(Displayable d) {
        drawer.addStringDisplay(d);
        drawer.draw();
    }

    public void removeStringDisplay(Displayable d) {
        drawer.removeStringDisplay(d);
        drawer.draw();
    }
}
