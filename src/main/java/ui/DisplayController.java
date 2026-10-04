package ui;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import language.field.Field;
import language.field.boolField.*;
import language.field.intField.*;
import language.instruction.instructionSet.Show;
import ui.display.MediumDrawer;
import ui.display.Styles;
import ui.display.displayable.Displayable;
import ui.display.displayable.boolFieldDisplay.*;
import ui.display.displayable.intFieldDisplay.*;
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

    public void bind(Show<?> show) {
        Platform.runLater(() -> _bind(show));
    }

    private final HashSet<Show<?>> bound = new HashSet<>();
    private void _bind(Show<?> show) {
        String name = show.name;
        Field<?> field = show.field;
        Optional<Styles.Style> style = show.style;

        if (!bound.contains(show)) switch (field) {
            case BoolV boolV   -> createDisplay(name, new BoolVDisplay(boolV, style.orElse(Styles.DEFAULT)));
            case BoolVe boolVe -> createDisplay(name, new BoolVeDisplay(boolVe, style.orElse(Styles.DEFAULT)));
            case BoolVf boolVf -> createDisplay(name, new BoolVfDisplay(boolVf, style.orElse(Styles.DEFAULT)));
            case BoolE boolE   -> createDisplay(name, new BoolEDisplay(boolE, style.orElse(Styles.DEFAULT)));
            case BoolEv boolEv -> createDisplay(name, new BoolEvDisplay(boolEv, style.orElse(Styles.DEFAULT)));
            case BoolEf boolEf -> createDisplay(name, new BoolEfDisplay(boolEf, style.orElse(Styles.DEFAULT)));
            case BoolF boolF   -> createDisplay(name, new BoolFDisplay(boolF, style.orElse(Styles.DEFAULT)));
            case BoolFv boolFv -> createDisplay(name, new BoolFvDisplay(boolFv, style.orElse(Styles.DEFAULT)));
            case BoolFe boolFe -> createDisplay(name, new BoolFeDisplay(boolFe, style.orElse(Styles.DEFAULT)));

            case IntV intV     -> createDisplay(name, new IntVDisplay(intV, style.orElse(Styles.DEFAULT_INT)));
            case IntVe intVe   -> createDisplay(name, new IntVeDisplay(intVe, style.orElse(Styles.DEFAULT_INT)));
            case IntVf intVf   -> createDisplay(name, new IntVfDisplay(intVf, style.orElse(Styles.DEFAULT_INT)));
            case IntE intE     -> createDisplay(name, new IntEDisplay(intE, style.orElse(Styles.DEFAULT_INT)));
            case IntEv intEv   -> createDisplay(name, new IntEvDisplay(intEv, style.orElse(Styles.DEFAULT_INT)));
            case IntEf intEf   -> createDisplay(name, new IntEfDisplay(intEf, style.orElse(Styles.DEFAULT_INT)));
            case IntF intF     -> createDisplay(name, new IntFDisplay(intF, style.orElse(Styles.DEFAULT_INT)));
            case IntFv intFv   -> createDisplay(name, new IntFvDisplay(intFv, style.orElse(Styles.DEFAULT_INT)));
            case IntFe intFe   -> createDisplay(name, new IntFeDisplay(intFe, style.orElse(Styles.DEFAULT_INT)));

            default -> throw new IllegalArgumentException("Unsupported type for display: " + field.getClass().getName());
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
