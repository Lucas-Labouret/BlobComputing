package ui.display.displayable;

import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusS.Edge;
import medium.locusS.Face;
import medium.locusS.Vertex;
import medium.locusT.*;

import java.util.HashMap;

/** Interface for objects that can be displayed on a MediumDrawer. */
public interface Displayable {
    // These methods should be overridden to return true by Displayables that want to display the corresponding loci.
    default boolean updatesV()  { return false; }
    default boolean updatesVe() { return false; }
    default boolean updatesVf() { return false; }
    default boolean updatesE()  { return false; }
    default boolean updatesEv() { return false; }
    default boolean updatesEf() { return false; }
    default boolean updatesF()  { return false; }
    default boolean updatesFv() { return false; }
    default boolean updatesFe() { return false; }

    // These methods should be overridden to compute the appropriate colors for the loci that this Displayable wants to display.
    // They should only be called if the corresponding updates* returns true
    default HashMap<Vertex, Color> displayColorV () { return new HashMap<>(); }
    default HashMap<Ve,     Color> displayColorVe() { return new HashMap<>(); }
    default HashMap<Vf,     Color> displayColorVf() { return new HashMap<>(); }
    default HashMap<Edge,   Color> displayColorE () { return new HashMap<>(); }
    default HashMap<Ev,     Color> displayColorEv() { return new HashMap<>(); }
    default HashMap<Ef,     Color> displayColorEf() { return new HashMap<>(); }
    default HashMap<Face,   Color> displayColorF () { return new HashMap<>(); }
    default HashMap<Fv,     Color> displayColorFv() { return new HashMap<>(); }
    default HashMap<Fe,     Color> displayColorFe() { return new HashMap<>(); }

    default HashMap<Vertex, String> displayStringV () { return new HashMap<>(); }
    default HashMap<Ve,     String> displayStringVe() { return new HashMap<>(); }
    default HashMap<Vf,     String> displayStringVf() { return new HashMap<>(); }
    default HashMap<Edge,   String> displayStringE () { return new HashMap<>(); }
    default HashMap<Ev,     String> displayStringEv() { return new HashMap<>(); }
    default HashMap<Ef,     String> displayStringEf() { return new HashMap<>(); }
    default HashMap<Face,   String> displayStringF () { return new HashMap<>(); }
    default HashMap<Fv,     String> displayStringFv() { return new HashMap<>(); }
    default HashMap<Fe,     String> displayStringFe() { return new HashMap<>(); }
}
