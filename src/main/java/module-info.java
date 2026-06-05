module com.github.lucaslabouret.blobcomputing {
    requires javafx.controls;
    requires javafx.base;
    requires java.desktop;
    requires javafx.swing;

    opens ui to javafx.fxml;
    opens ui.display to javafx.fxml;
    opens ui.display.displayable to javafx.fxml;
    opens ui.display.displayable.boolFieldDisplay to javafx.fxml;

    exports ui;
    exports ui.display;
    exports ui.display.displayable;
    exports ui.display.displayable.boolFieldDisplay;
    exports ui.display.displayable.intFieldDisplay;
    exports ui.utils;

    exports medium;
    exports medium.locusS;
    exports medium.locusT;

    exports language;
    exports language.cache;
    exports language.instruction.instructionSet;
    exports language.obj;
    exports language.ref;
    exports language.ref.agent;
    exports language.ref.agent.flies;
    exports language.ref.field.boolField;
    exports language.ref.field.intField;
    exports language.instruction;
    exports language.obj.agent;
    exports language.obj.agent.flies;
}