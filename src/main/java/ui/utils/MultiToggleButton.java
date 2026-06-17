package ui.utils;

import javafx.geometry.Insets;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;

/** A ToggleButton that can be part of multiple ToggleGroups, behaving like a standard ToggleButton in each group. */
public class MultiToggleButton extends ToggleButton {
    //Used to replace the graphics of the ToggleButton base class by the graphics of a RadioButton
    private final RadioButton graphic = new RadioButton();

    public void addToggleGroup(ToggleGroup group) {
        group.getToggles().add(this);
        group.selectedToggleProperty().addListener((_, _, newValue) ->{
            setSelected(newValue == this);
        });
    }

    public MultiToggleButton() {
        //Setups the graphics and links them to the actual MultiToggleButton
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        setPadding(Insets.EMPTY);
        setBackground(Background.EMPTY);
        setGraphic(graphic);
        graphic.setOnAction(_ -> setSelected(graphic.isSelected()));
        this.selectedProperty().addListener((_, _, isSelected) -> {
            graphic.setSelected(isSelected);
        });
    }
}
