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

    public DisplayBox(String name, Displayable displayable, DisplayController displayController) {
        super(SPACING);

        this.name = name;
        this.displayable = displayable;

        Label nameLabel = new Label();
        nameLabel.setText(name);
        showCheckBox.setSelected(true);
        showCheckBox.setAllowIndeterminate(false);
        showCheckBox.setOnAction(_ -> displayController.updateDisplay());

        getChildren().addAll(showCheckBox, nameLabel);
    }

    public boolean isShown() {
        return showCheckBox.isSelected();
    }
}
