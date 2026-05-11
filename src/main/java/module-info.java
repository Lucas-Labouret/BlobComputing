module com.github.lucaslabouret.blobcomputing {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.desktop;

    opens ui to javafx.fxml;
    opens ui.display to javafx.fxml;
    opens ui.display.displayable to javafx.fxml;

    exports ui;
    exports ui.display;
    exports ui.display.displayable;
    exports ui.utils;

    exports prog;
    exports prog.ref.intField;

    exports language;
    exports language.fieldRef;
    exports language.basicInstruction;

    exports medium;
    exports medium.locusS;
    exports medium.locusT;

    exports field.boolField.fieldS;
    exports field.boolField.fieldT;
    exports ui.display.displayable.boolFieldDisplay;
    opens ui.display.displayable.boolFieldDisplay to javafx.fxml;
}