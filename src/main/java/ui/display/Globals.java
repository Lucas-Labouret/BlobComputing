package ui.display;

import javafx.scene.paint.Color;

/** A class to hold global variables for the displayable package. */
public class Globals {
    public static Color VERTEX_FALSE = Color.LIME;
    public static Color VERTEX_TRUE = Color.DARKGREEN;

    public static Color EDGE_FALSE = Color.SALMON;
    public static Color EDGE_TRUE = Color.MAROON;

    public static Color FACE_FALSE = Color.LIGHTBLUE;
    public static Color FACE_TRUE = Color.DARKBLUE;

    public static Color VE_FALSE = VERTEX_FALSE.interpolate(EDGE_FALSE, 0.3);
    public static Color VE_TRUE = VERTEX_TRUE.interpolate(EDGE_TRUE, 0.3);

    public static Color VF_FALSE = VERTEX_FALSE.interpolate(FACE_FALSE, 0.3);
    public static Color VF_TRUE = VERTEX_TRUE.interpolate(FACE_TRUE, 0.3);

    public static Color EV_FALSE = EDGE_FALSE.interpolate(VERTEX_FALSE, 0.3);
    public static Color EV_TRUE = EDGE_TRUE.interpolate(VERTEX_TRUE, 0.3);

    public static Color EF_FALSE = EDGE_FALSE.interpolate(FACE_FALSE, 0.3);
    public static Color EF_TRUE = EDGE_TRUE.interpolate(FACE_TRUE, 0.3);

    public static Color FV_FALSE = FACE_FALSE.interpolate(VERTEX_FALSE, 0.3);
    public static Color FV_TRUE = FACE_TRUE.interpolate(VERTEX_TRUE, 0.3);

    public static Color FE_FALSE = FACE_FALSE.interpolate(EDGE_FALSE, 0.3);
    public static Color FE_TRUE = FACE_TRUE.interpolate(EDGE_TRUE, 0.3);

    public static Color DEFAULT = Color.LIGHTGREY;
}
