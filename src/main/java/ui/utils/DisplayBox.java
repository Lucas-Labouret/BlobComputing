package ui.utils;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import ui.DisplayController;
import ui.display.displayable.Displayable;


public class DisplayBox extends HBox {
    private static final double SPACING = 10;

    public final String name;
    public final Displayable displayable;

    private final CheckBox showCheckBox = new CheckBox();

    private static boolean first = true;
    public DisplayBox(String name, Displayable displayable, DisplayController displayController) {
        super(SPACING);

        this.name = name;
        this.displayable = displayable;

        Label nameLabel = new Label();
        nameLabel.setText(name);
        showCheckBox.setSelected(first);
        showCheckBox.setAllowIndeterminate(false);
        showCheckBox.setOnAction(_ -> {
            if (showCheckBox.isSelected()) displayController.addDisplay(displayable);
            else displayController.removeDisplay(displayable);
        });

        if (first) {
            displayController.addDisplay(displayable);
            first = false;
        }

        getChildren().addAll(showCheckBox, nameLabel);
    }
}
