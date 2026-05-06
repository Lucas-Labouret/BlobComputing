package ui.utils;

import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class OrderableDisplayPanel extends VBox {
    private static final double SPACING = 5;
    private static final double PADDING = 10;

    public OrderableDisplayPanel(){
        super(SPACING);
        setPadding(new javafx.geometry.Insets(PADDING));

        // in case user drops node in blank space in root:
        setOnMouseDragReleased(event -> {
            int indexOfDraggingNode = getChildren().indexOf(event.getGestureSource());
            rotateNodes(indexOfDraggingNode, getChildren().size() - 1);
        });
    }

    private void rotateNodes(int indexOfDraggingNode, int indexOfDropTarget) {
        if (indexOfDraggingNode >= 0 && indexOfDropTarget >= 0) {
            Node node = getChildren().remove(indexOfDraggingNode);
            getChildren().add(indexOfDropTarget, node);
        }
    }

    public void add(DisplayBox db) {
        db.setOnDragDetected(_ -> db.startFullDrag());

        // next two handlers show the drop target visually:
        db.setOnMouseDragEntered(_ -> db.setStyle("-fx-background-color: #ffffa0;"));
        db.setOnMouseDragExited(_ -> db.setStyle(""));

        db.setOnMouseDragReleased(event -> {
            db.setStyle("");
            int indexOfDraggingNode = getChildren().indexOf(event.getGestureSource());
            int indexOfDropTarget = getChildren().indexOf(db);
            rotateNodes(indexOfDraggingNode, indexOfDropTarget);
            event.consume();
        });
        getChildren().add(db);
    }
}
