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
    default HashMap<Vertex, Color> displayV (Medium medium) { return new HashMap<>(); }
    default HashMap<Ve,     Color> displayVe(Medium medium) { return new HashMap<>(); }
    default HashMap<Vf,     Color> displayVf(Medium medium) { return new HashMap<>(); }
    default HashMap<Edge,   Color> displayE (Medium medium) { return new HashMap<>(); }
    default HashMap<Ev,     Color> displayEv(Medium medium) { return new HashMap<>(); }
    default HashMap<Ef,     Color> displayEf(Medium medium) { return new HashMap<>(); }
    default HashMap<Face,   Color> displayF (Medium medium) { return new HashMap<>(); }
    default HashMap<Fv,     Color> displayFv(Medium medium) { return new HashMap<>(); }
    default HashMap<Fe,     Color> displayFe(Medium medium) { return new HashMap<>(); }
}
