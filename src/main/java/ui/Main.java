package ui;

import blobProgram.*;
import blobProgram.agent.flies.Flies;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import language.field.boolField.BoolV;
import language.field.intField.IntV;
import language.field.intField.IntVe;
import language.fieldRef.boolField.BoolEfRef;
import language.fieldRef.boolField.BoolVRef;
import language.fieldRef.intField.IntVRef;
import language.fieldRef.intField.IntVeRef;
import language.instruction.Instruction;
import language.instruction.Procedure;
import language.utils.BoolFieldManager;
import medium.Medium;
import medium.locusS.Vertex;

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

//        Instruction instruction = BlobV.rand(medium).showGrow();
//        Instruction instruction = new Procedure() {{
//            BlobV blob = BlobV.rand(5);
//            show("Blob", blob);
//            call(blob.voronoi());
//        }};
//        Instruction instruction = new BlobV().voronoi();
//        Instruction instruction = new Rand().showRand();
//        Instruction instruction = new Procedure() {{
//            Rand.init();
//            Flies flies = Flies.rand(6);
//            BoolVRef start = new BoolVRef();
//            set(flies.state, start);
//            show("Flies", start);
//            call(flies.flip());
//        }};
        Instruction instruction = new Procedure() {{
            Rand.init();

            QuasiParticle seed = new QuasiParticle(BoolV.zeroes());
            for (int i = 0; i < 2; i++){
                int rand = (int) (Math.random() * medium.vertices.size());
                Vertex v = (Vertex) medium.vertices.toArray()[rand];
                BoolV.setBit(seed.get(), v, true);
            }
//            BoolV.setBit(seed.get(), 0, 0, true);
//            BoolV.setBit(seed.get(), 21, 21, true);
//            BoolV.setBit(seed.get(), 18, 4, true);
//            BoolV.setBit(seed.get(), 18, 6, true);
            Flies flies = new Flies(seed);
            //call(flies.flip());
            show("Sources", flies.state);

            GabrielCenter gabrielCenter = new GabrielCenter(flies.state);
            IntVRef dist = new IntVRef(new IntV(3));
            IntVeRef gradient = new IntVeRef(new IntVe(3));
            BoolVRef floodCenter = new BoolVRef();
            BoolVRef saddleCenter = new BoolVRef();
            call(gabrielCenter.update());
            call(gabrielCenter.distField.getDist(dist));
            call(gabrielCenter.distField.getGradient(gradient));
            call(gabrielCenter.flood());
            call(gabrielCenter.getCenter(floodCenter));
            call(gabrielCenter.saddle(saddleCenter));
            show("DistField", dist);
            show("Gradient", gradient);
            show("Flood", floodCenter);
            show("Saddle", saddleCenter);
        }};

        ms = new MasterScene(medium, instruction);

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
