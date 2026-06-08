package ui;

import blobProgram.Rand;
import blobProgram.agent.flies.Flies;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import language.fieldRef.boolField.BoolVRef;
import language.instruction.Instruction;
import language.instruction.Procedure;
import blobProgram.BlobV;
import language.fieldRef.boolField.BoolVeRef;
import language.utils.BoolFieldManager;
import medium.Medium;

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
        Instruction instruction = new Procedure() {{
            Rand.init();
            Flies flies = Flies.rand(6);
            BoolVRef start = new BoolVRef();
            set(flies.state, start);
            show("Flies", start);
            call(flies.flip());
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
