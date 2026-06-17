package ui.utils;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import ui.DisplayController;
import ui.display.displayable.Displayable;


public class DisplayBox extends HBox {
    private static final double SPACING = 10;

    public final String name;
    public final Displayable displayable;

    private final CheckBox colorCheckBox = new CheckBox();
    private final MultiToggleButton stringButton = new MultiToggleButton();

    private static final ToggleGroup vToggleGroup  = new ToggleGroup();
    private static final ToggleGroup veToggleGroup = new ToggleGroup();
    private static final ToggleGroup vfToggleGroup = new ToggleGroup();
    private static final ToggleGroup eToggleGroup  = new ToggleGroup();
    private static final ToggleGroup evToggleGroup = new ToggleGroup();
    private static final ToggleGroup efToggleGroup = new ToggleGroup();
    private static final ToggleGroup fToggleGroup  = new ToggleGroup();
    private static final ToggleGroup fvToggleGroup = new ToggleGroup();
    private static final ToggleGroup feToggleGroup = new ToggleGroup();

    private static boolean first = true;
    public DisplayBox(String name, Displayable displayable, DisplayController displayController) {
        super(SPACING);

        this.name = name;
        this.displayable = displayable;

        Label nameLabel = new Label();
        nameLabel.setText(name);
        colorCheckBox.setSelected(first);
        colorCheckBox.setAllowIndeterminate(false);
        colorCheckBox.setOnAction(_ -> {
            if (colorCheckBox.isSelected()) displayController.addColorDisplay(displayable);
            else displayController.removeColorDisplay(displayable);
        });

        if (displayable.updatesV())  stringButton.addToggleGroup(vToggleGroup);
        if (displayable.updatesVe()) stringButton.addToggleGroup(veToggleGroup);
        if (displayable.updatesVf()) stringButton.addToggleGroup(vfToggleGroup);
        if (displayable.updatesE())  stringButton.addToggleGroup(eToggleGroup);
        if (displayable.updatesEv()) stringButton.addToggleGroup(evToggleGroup);
        if (displayable.updatesEf()) stringButton.addToggleGroup(efToggleGroup);
        if (displayable.updatesF())  stringButton.addToggleGroup(fToggleGroup);
        if (displayable.updatesFv()) stringButton.addToggleGroup(fvToggleGroup);
        if (displayable.updatesFe()) stringButton.addToggleGroup(feToggleGroup);

        stringButton.selectedProperty().addListener((_, _, newValue) -> {
            if (newValue) displayController.addStringDisplay(displayable);
            else displayController.removeStringDisplay(displayable);
        });

        if (first) {
            displayController.addColorDisplay(displayable);
            first = false;
        }

        getChildren().addAll(colorCheckBox, stringButton, nameLabel);
    }
}
