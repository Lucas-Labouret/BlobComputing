package ui;

import blobProgram.*;
import blobProgram.agent.flies.Flies;
import blobProgram.agent.homogeneize.Homogenize;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.instruction.Instruction;
import language.instruction.Procedure;
import language.field.BoolFieldManager;
import medium.Medium;
import medium.locusS.Vertex;
import ui.display.Styles;
import utils.Lazy;

/**
 * Main class for the Blob application.
 * This class initializes the JavaFX application, and serves as the entry point.
 */
public class Main extends Application {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 450;

    private MasterScene ms;

    @Override
    public void start(Stage stage) {
        blobStaging(stage);
    
        Medium medium;
        try { medium = Medium.read("large"); }
        catch (Exception e) { throw new RuntimeException(e); }
        BoolFieldManager.setup(medium);

        ms = new MasterScene(medium, new MainInstructions(medium).homogenize.get());

        Scene scene = new Scene(ms, Main.WIDTH, Main.HEIGHT);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() { ms.stop(); }

    private void blobStaging(Stage stage) {
        stage.setTitle("Blob");
        Image icon = new Image("blob.png");
        stage.getIcons().add(icon);
    }

    static void main(String[] args) {
        launch(args);
    }
}

/**
 * MainInstructions class holds the main instructions for the Blob application.
 * It initializes various procedures that can be executed in the application.
 * <p>
 * The procedures are held in Lazy objects to ensure that only the necessary procedure is created.
 * This allows all procedures to call Rand.init() as needed without conflicting with each other.
 * This also reduces the memory footprint of the application, as only the necessary procedures are created.
 */
class MainInstructions {
    public final Lazy<Instruction> grow;
    public final Lazy<Instruction> staticVoronoi;
    public final Lazy<Instruction> random;
    public final Lazy<Instruction> distance;
    public final Lazy<Instruction> gabriel;
    public final Lazy<Instruction> flies;
    public final Lazy<Instruction> homogenize;
    
    public MainInstructions(Medium medium) {
        grow = new Lazy<>(() -> new Procedure() {{
            BlobV blob = BlobV.random(6);
            show("Blob", blob);
            call(blob.grow());
        }});

        staticVoronoi = new Lazy<>(() -> new Procedure() {{
            BlobV blob = BlobV.random(6);
            show("Blob", blob);
            call(blob.voronoi());
        }});

        random = new Lazy<>(() -> new Procedure() {{
            Rand.init();
            call(new Rand().showRand());
        }});

        distance = new Lazy<>(() -> new Procedure() {{
            Rand.init();
            QuasiParticle seed = new QuasiParticle(new BoolV().zeroes());
            seed.setBit(18, 5, true);
            seed.setBit(18, 14, true);
            Flies flies = new Flies(seed);
            show("Flies", seed);
            call(flies.flip());

            IntV dist = new IntV(3);
            IntVe gradient = new IntVe(3);
            DistanceField distField = new DistanceField(seed, 3);
            call(distField.getDist(dist));
            call(distField.getGradient(gradient));
            show("DistField", dist);
            show("Gradient", gradient);
            call(distField.update());
        }});

        gabriel = new Lazy<>(() -> new Procedure() {{
            Rand.init();
            QuasiParticle seed = new QuasiParticle(new BoolV().zeroes());
            for (int i = 0; i < 5; i++){
                int rand = (int) (Math.random() * medium.vertices.size());
                Vertex v = (Vertex) medium.vertices.toArray()[rand];
                seed.setBit(v, true);
            }
//            seed.setBit(0, 0, true);
//            seed.setBit(21, 21, true);
//            seed.setBit(18, 5, true);
//            seed.setBit(18, 14, true);
            Flies flies = new Flies(seed);
            call(flies.flip());
            show("Sources", flies.state);

            GabrielCenter gabrielCenter = new GabrielCenter(flies.state);
            IntV dist = new IntV(3);
            IntVe gradient = new IntVe(3);
            BoolV center = new BoolV();
            call(gabrielCenter.update());
            call(gabrielCenter.distField.getDist(dist));
            call(gabrielCenter.distField.getGradient(gradient));
            call(gabrielCenter.saddle(center));
            show("DistField", dist);
            show("Gradient", gradient);
            show("Saddle", center);
        }});

        flies = new Lazy<>(() -> new Procedure() {{
            Rand.init();
            QuasiParticle seed = new QuasiParticle(new BoolV().zeroes());
            for (int i = 0; i < 10; i++){
                int rand = (int) (Math.random() * medium.vertices.size());
                Vertex v = (Vertex) medium.vertices.toArray()[rand];
                seed.setBit(v, true);
            }
            Flies flies = new Flies(seed);
            show("Sources", flies.state, Styles.PARTICLE);
            call(flies.flip());
        }});

        homogenize = new Lazy<>(() -> new Procedure() {{
            Rand.init();
            QuasiParticle particle = new QuasiParticle(new BoolV().zeroes());
//            for (int i = 0; i < 20; i++){
//                int rand = (int) (Math.random() * medium.vertices.size());
//                Vertex v = (Vertex) medium.vertices.toArray()[rand];
//                particle.setBit(v, true);
//            }
            for (int y = 0; y < 6; y++) for (int x = 0; x < 6; x++) {
                particle.setBit(3*y+1, 3*x+1, true);
            }
//            particle.setBit(3, 2, true);
//            particle.setBit(3, 5, true);
//            particle.setBit(5, 2, true);
            Homogenize homogenize = Homogenize.make(particle);

            show("Particles", particle, Styles.PARTICLE);
            call(homogenize.flip());
        }});
    }
}
