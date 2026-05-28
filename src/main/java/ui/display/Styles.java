package ui.display;

import javafx.scene.paint.Color;

/** Defines the styles used by Displayables */
public class Styles {
    // This class is just a container for the Style record, as well as some predefined styles.
    // It is not meant to be instantiated.
    private Styles() {}

    public record Style(
            Color VERTEX_FALSE, Color VERTEX_TRUE,
            Color EDGE_FALSE,   Color EDGE_TRUE,
            Color FACE_FALSE,   Color FACE_TRUE,

            Color VE_FALSE, Color VE_TRUE,
            Color VF_FALSE, Color VF_TRUE,

            Color EV_FALSE, Color EV_TRUE,
            Color EF_FALSE, Color EF_TRUE,

            Color FV_FALSE, Color FV_TRUE,
            Color FE_FALSE, Color FE_TRUE,

            Color DEFAULT
    ) {
        public Style (
                Color VERTEX_FALSE, Color VERTEX_TRUE,
                Color EDGE_FALSE,   Color EDGE_TRUE,
                Color FACE_FALSE,   Color FACE_TRUE,

                Color DEFAULT
        ) {
            this(
                    VERTEX_FALSE, VERTEX_TRUE,
                    EDGE_FALSE, EDGE_TRUE,
                    FACE_FALSE, FACE_TRUE,

                    VERTEX_FALSE.interpolate(EDGE_FALSE, 0.3), VERTEX_TRUE.interpolate(EDGE_TRUE, 0.3),
                    VERTEX_FALSE.interpolate(FACE_FALSE, 0.3), VERTEX_TRUE.interpolate(FACE_TRUE, 0.3),

                    EDGE_FALSE.interpolate(VERTEX_FALSE, 0.3), EDGE_TRUE.interpolate(VERTEX_TRUE, 0.3),
                    EDGE_FALSE.interpolate(FACE_FALSE, 0.3), EDGE_TRUE.interpolate(FACE_TRUE, 0.3),

                    FACE_FALSE.interpolate(VERTEX_FALSE, 0.3), FACE_TRUE.interpolate(VERTEX_TRUE, 0.3),
                    FACE_FALSE.interpolate(EDGE_FALSE, 0.3), FACE_TRUE.interpolate(EDGE_TRUE, 0.3),

                    DEFAULT
            );
        }

        public Style(
                Color VERTEX_FALSE, Color VERTEX_TRUE,
                Color EDGE_FALSE,   Color EDGE_TRUE,
                Color FACE_FALSE,   Color FACE_TRUE
        ) {
            this(
                    VERTEX_FALSE, VERTEX_TRUE,
                    EDGE_FALSE, EDGE_TRUE,
                    FACE_FALSE, FACE_TRUE,

                    Color.LIGHTGREY
            );
        }
    }

    public static final Style DEFAULT = new Style(
            Color.LIME, Color.DARKGREEN,
            Color.SALMON, Color.MAROON,
            Color.LIGHTBLUE, Color.DARKBLUE
    );

    public static final Style DEFAULT_INT = new Style(
            Color.rgb(0, 0, 255), Color.rgb(255, 0, 0),

            // Undefined for now as I haven't chosen colors for Edges and Faces yet
            Color.LIGHTGREY, Color.LIGHTGREY,
            Color.LIGHTGREY, Color.LIGHTGREY
    );
}
