package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import language.instruction.Instruction;
import language.instruction.Procedure;
import language.obj.agent.BlobV;
import language.ref.agent.BlobVRef;
import language.ref.field.boolField.BoolVeRef;
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

        //Instruction instruction = RotateV.rand().ccw();
        //Instruction instruction = BlobV.rand(medium).showGrow();
        //Instruction instruction = BlobV.rand(5).voronoi();
        //Instruction instruction = new BlobV().voronoi();
        //Instruction instruction = new Rand().showRand();
        //Instruction instruction = Flies.rand(6).showFlies();
        Instruction instruction = new Procedure() {{
            BlobVRef blob = BlobVRef.of(BlobV.rand(6));
            call(blob.get().grow());

            BoolVeRef borderVe = new BoolVeRef();
            call(blob.get().outVe(borderVe));

            show("Blob", blob.get().state);
            show("BorderVe", borderVe);
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
