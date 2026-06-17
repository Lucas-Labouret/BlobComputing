package ui.display.displayable;

import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusS.Edge;
import medium.locusS.Face;
import medium.locusS.Vertex;
import medium.locusT.*;

import java.util.HashMap;

/**
 * Implemented by classes that contain loci and can be drawn on a MediumDrawer.
 */
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
    default HashMap<Vertex, Color> displayColorV (Medium medium) { return new HashMap<>(); }
    default HashMap<Ve,     Color> displayColorVe(Medium medium) { return new HashMap<>(); }
    default HashMap<Vf,     Color> displayColorVf(Medium medium) { return new HashMap<>(); }
    default HashMap<Edge,   Color> displayColorE (Medium medium) { return new HashMap<>(); }
    default HashMap<Ev,     Color> displayColorEv(Medium medium) { return new HashMap<>(); }
    default HashMap<Ef,     Color> displayColorEf(Medium medium) { return new HashMap<>(); }
    default HashMap<Face,   Color> displayColorF (Medium medium) { return new HashMap<>(); }
    default HashMap<Fv,     Color> displayColorFv(Medium medium) { return new HashMap<>(); }
    default HashMap<Fe,     Color> displayColorFe(Medium medium) { return new HashMap<>(); }

    default HashMap<Vertex, String> displayStringV (Medium medium) { return new HashMap<>(); }
    default HashMap<Ve,     String> displayStringVe(Medium medium) { return new HashMap<>(); }
    default HashMap<Vf,     String> displayStringVf(Medium medium) { return new HashMap<>(); }
    default HashMap<Edge,   String> displayStringE (Medium medium) { return new HashMap<>(); }
    default HashMap<Ev,     String> displayStringEv(Medium medium) { return new HashMap<>(); }
    default HashMap<Ef,     String> displayStringEf(Medium medium) { return new HashMap<>(); }
    default HashMap<Face,   String> displayStringF (Medium medium) { return new HashMap<>(); }
    default HashMap<Fv,     String> displayStringFv(Medium medium) { return new HashMap<>(); }
    default HashMap<Fe,     String> displayStringFe(Medium medium) { return new HashMap<>(); }
}
