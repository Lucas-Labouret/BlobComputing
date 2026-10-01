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
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.intField.IntVRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Instruction;
import language.instruction.Procedure;
import language.utils.BoolFieldManager;
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
    public void stop() {
        ms.stop();
    }

    private void blobStaging(Stage stage) {
        stage.setTitle("Blob");
        Image icon = new Image("blob.png");
        stage.getIcons().add(icon);
    }

    static void main(String[] args) {
        launch(args);
    }
}

class MainInstructions {
    public final Lazy<Instruction> grow;
    public final Lazy<Instruction> staticVoronoi1;
    public final Lazy<Instruction> staticVoronoi2;
    public final Lazy<Instruction> random;
    public final Lazy<Instruction> distance;
    public final Lazy<Instruction> gabriel;
    public final Lazy<Instruction> flies;
    public final Lazy<Instruction> homogenize;
    
    public MainInstructions(Medium medium) {
        grow = new Lazy<>(() -> new Procedure() {{
            BlobV blob = BlobV.rand(6);
            show("Blob", blob);
            call(blob.grow());
        }});

        staticVoronoi1 = new Lazy<>(() -> new Procedure() {{
            BlobV blob = BlobV.rand(6);
            show("Blob", blob);
            call(blob.voronoi());
        }});

        staticVoronoi2 = new Lazy<>(() -> new BlobV().voronoi());

        random = new Lazy<>(() -> new Procedure() {{
            Rand.init();
            call(new Rand().showRand());
        }});

        distance = new Lazy<>(() -> new Procedure() {{
            Rand.init();
            QuasiParticle seed = new QuasiParticle(BoolV.zeroes());
            BoolV.setBit(seed.get(), 18, 5, true);
            BoolV.setBit(seed.get(), 18, 14, true);
            Flies flies = new Flies(seed);
            show("Flies", seed);
            call(flies.flip());

            IntVRef dist = new IntVRef(new IntV(3));
            IntVeRef gradient = new IntVeRef(new IntVe(3));
            DistField distField = new DistField(seed, 3);
            call(distField.getDist(dist));
            call(distField.getGradient(gradient));
            show("DistField", dist);
            show("Gradient", gradient);
            call(distField.update());
        }});

        gabriel = new Lazy<>(() -> new Procedure() {{
            Rand.init();
            QuasiParticle seed = new QuasiParticle(BoolV.zeroes());
            for (int i = 0; i < 5; i++){
                int rand = (int) (Math.random() * medium.vertices.size());
                Vertex v = (Vertex) medium.vertices.toArray()[rand];
                BoolV.setBit(seed.get(), v, true);
            }
//            BoolV.setBit(seed.get(), 0, 0, true);
//            BoolV.setBit(seed.get(), 21, 21, true);
//            BoolV.setBit(seed.get(), 18, 5, true);
//            BoolV.setBit(seed.get(), 18, 14, true);
            Flies flies = new Flies(seed);
            call(flies.flip());
            show("Sources", flies.state);

            GabrielCenter gabrielCenter = new GabrielCenter(flies.state);
            IntVRef dist = new IntVRef(new IntV(3));
            IntVeRef gradient = new IntVeRef(new IntVe(3));
            BoolVRef center = new BoolVRef();
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
            QuasiParticle seed = new QuasiParticle(BoolV.zeroes());
            for (int i = 0; i < 10; i++){
                int rand = (int) (Math.random() * medium.vertices.size());
                Vertex v = (Vertex) medium.vertices.toArray()[rand];
                BoolV.setBit(seed.get(), v, true);
            }
            Flies flies = new Flies(seed);
            show("Sources", flies.state, Styles.PARTICLE);
            call(flies.flip());
        }});

        homogenize = new Lazy<>(() -> new Procedure() {{
            Rand.init();
            QuasiParticle particle = new QuasiParticle(BoolV.zeroes());
//            for (int i = 0; i < 20; i++){
//                int rand = (int) (Math.random() * medium.vertices.size());
//                Vertex v = (Vertex) medium.vertices.toArray()[rand];
//                BoolV.setBit(particle.get(), v, true);
//            }
            for (int y = 0; y < 6; y++) for (int x = 0; x < 6; x++) {
                BoolV.setBit(particle.get(), 4*y+1, 4*x+1, true);
            }
//            BoolV.setBit(particle.get(), 3, 2, true);
//            BoolV.setBit(particle.get(), 3, 5, true);
//            BoolV.setBit(particle.get(), 5, 2, true);
            Homogenize homogenize = Homogenize.make(particle);

            show("Particles", particle, Styles.PARTICLE);
            call(homogenize.flip());
        }});
    }
}
