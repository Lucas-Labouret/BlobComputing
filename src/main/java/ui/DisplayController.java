package ui;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import medium.Medium;
import medium.locusS.Edge;
import medium.locusS.Face;
import medium.locusS.Vertex;
import medium.locusT.*;
import ui.display.MediumDrawer;
import ui.display.displayable.Displayable;
import ui.utils.DisplayBox;
import ui.utils.OrderableDisplayPanel;

import java.util.HashMap;

public class DisplayController {
    private final Medium medium;
    private final OrderableDisplayPanel displays;

    private final MediumDrawer drawer;

    public DisplayController(Medium medium, OrderableDisplayPanel displays) {
        this.medium = medium;
        this.displays = displays;

        drawer = new MediumDrawer(medium);
    }

    public MediumDrawer getDrawer() {
        return drawer;
    }

    private VColorer  vColorer  = (_ -> new HashMap<>());
    private VeColorer veColorer = (_ -> new HashMap<>());
    private VfColorer vfColorer = (_ -> new HashMap<>());
    private EColorer  eColorer  = (_ -> new HashMap<>());
    private EvColorer evColorer = (_ -> new HashMap<>());
    private EfColorer efColorer = (_ -> new HashMap<>());
    private FColorer  fColorer  = (_ -> new HashMap<>());
    private FvColorer fvColorer = (_ -> new HashMap<>());
    private FeColorer feColorer = (_ -> new HashMap<>());

    public void display() {
        drawer.setVColors (vColorer .color(medium));
        drawer.setVeColors(veColorer.color(medium));
        drawer.setVfColors(vfColorer.color(medium));
        drawer.setEColors (eColorer .color(medium));
        drawer.setEvColors(evColorer.color(medium));
        drawer.setEfColors(efColorer.color(medium));
        drawer.setFColors (fColorer .color(medium));
        drawer.setFvColors(fvColorer.color(medium));
        drawer.setFeColors(feColorer.color(medium));
        drawer.draw();
    }

    public void addDisplay(String name, Displayable d) {
        DisplayBox box = new DisplayBox(name, d, this);
        displays.add(box);
        updateDisplayOrder();
    }

    public void updateDisplayOrder() {
        boolean vSet  = false;
        boolean veSet = false;
        boolean vfSet = false;
        boolean eSet  = false;
        boolean evSet = false;
        boolean efSet = false;
        boolean fSet  = false;
        boolean fvSet = false;
        boolean feSet = false;

        for (Node node: displays.getChildren()) {
            if (!(node instanceof DisplayBox box)) continue;
            if (!box.isShown()) continue;
            Displayable d = box.displayable;

            if (d.updatesV() && !vSet) {
                vSet = true;
                vColorer = d::displayV;
            }
            if (d.updatesVe() && !veSet) {
                veSet = true;
                veColorer = d::displayVe;
            }
            if (d.updatesVf() && !vfSet) {
                vfSet = true;
                vfColorer = d::displayVf;
            }
            if (d.updatesE() && !eSet) {
                eSet = true;
                eColorer = d::displayE;
            }
            if (d.updatesEv() && !evSet) {
                evSet = true;
                evColorer = d::displayEv;
            }
            if (d.updatesEf() && !efSet) {
                efSet = true;
                efColorer = d::displayEf;
            }
            if (d.updatesF() && !fSet) {
                fSet = true;
                fColorer = d::displayF;
            }
            if (d.updatesFv() && !fvSet) {
                fvSet = true;
                fvColorer = d::displayFv;
            }
            if (d.updatesFe() && !feSet) {
                feSet = true;
                feColorer = d::displayFe;
            }
        }

        drawer.setDrawV(vSet);
        drawer.setDrawVe(veSet);
        drawer.setDrawVf(vfSet);
        drawer.setDrawE(eSet);
        drawer.setDrawEv(evSet);
        drawer.setDrawEf(efSet);
        drawer.setDrawF(fSet);
        drawer.setDrawFv(fvSet);
        drawer.setDrawFe(feSet);

        display();
    }
}

@FunctionalInterface interface VColorer  { HashMap<Vertex, Color> color(Medium medium); }
@FunctionalInterface interface VeColorer { HashMap<Ve,     Color> color(Medium medium); }
@FunctionalInterface interface VfColorer { HashMap<Vf,     Color> color(Medium medium); }
@FunctionalInterface interface EColorer  { HashMap<Edge,   Color> color(Medium medium); }
@FunctionalInterface interface EvColorer { HashMap<Ev,     Color> color(Medium medium); }
@FunctionalInterface interface EfColorer { HashMap<Ef,     Color> color(Medium medium); }
@FunctionalInterface interface FColorer  { HashMap<Face,   Color> color(Medium medium); }
@FunctionalInterface interface FvColorer { HashMap<Fv,     Color> color(Medium medium); }
@FunctionalInterface interface FeColorer { HashMap<Fe,     Color> color(Medium medium); }
