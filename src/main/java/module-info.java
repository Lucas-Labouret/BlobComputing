module com.github.lucaslabouret.blobcomputing {
    requires javafx.controls;
    requires javafx.base;
    requires java.desktop;
    requires javafx.swing;

    opens ui to javafx.fxml;
    opens ui.display to javafx.fxml;
    opens ui.display.displayable to javafx.fxml;
    opens ui.display.displayable.boolFieldDisplay to javafx.fxml;

    exports utils;

    exports ui;
    exports ui.display;
    exports ui.display.displayable;
    exports ui.display.displayable.boolFieldDisplay;
    exports ui.display.displayable.intFieldDisplay;
    exports ui.utils;

    exports medium;
    exports medium.locusS;
    exports medium.locusT;

    exports language.cache;
    exports language.instruction.instructionSet;
    exports blobProgram;
    exports language.field;
    exports language.field.boolField;
    exports language.field.intField;
    exports language.utils;
    exports language.instruction;
    exports blobProgram.agent;
    exports blobProgram.agent.flies;
}