package ui.display;

import javafx.scene.paint.Color;

/** Defines the styles used by Displayables */
public class Styles {
    // This class is just a container for the Style record, as well as some predefined styles.
    // It is not meant to be instantiated.
    private Styles() {}

    /** Factory for creating Style objects. */
    public static class Factory {
        private Color VERTEX_FALSE = null;
        private Color VERTEX_TRUE = null;

        private Color EDGE_FALSE = null;
        private Color EDGE_TRUE = null;

        private Color FACE_FALSE = null;
        private Color FACE_TRUE = null;

        private Color DEFAULT = Color.LIGHTGREY;

        public Style make() {
            return new Style(
                    VERTEX_FALSE, VERTEX_TRUE,
                    EDGE_FALSE, EDGE_TRUE,
                    FACE_FALSE, FACE_TRUE,
                    DEFAULT
            );
        }

        public Factory vertexFalse(Color color) { this.VERTEX_FALSE = color; return this; }
        public Factory vertexTrue(Color color) { this.VERTEX_TRUE = color; return this; }

        public Factory edgeFalse(Color color) { this.EDGE_FALSE = color; return this; }
        public Factory edgeTrue(Color color) { this.EDGE_TRUE = color; return this; }

        public Factory faceFalse(Color color) { this.FACE_FALSE = color; return this; }
        public Factory faceTrue(Color color) { this.FACE_TRUE = color; return this; }

        public Factory setDefault(Color color) { this.DEFAULT = color; return this; }
    }

    /** A Style defines the colors used to display a Displayable. */
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
                    EDGE_FALSE  , EDGE_TRUE  ,
                    FACE_FALSE  , FACE_TRUE  ,

                    (VERTEX_FALSE == null || EDGE_FALSE == null) ? null : VERTEX_FALSE.interpolate(EDGE_FALSE  , 0.3),
                    (VERTEX_TRUE == null || EDGE_TRUE == null)   ? null : VERTEX_TRUE .interpolate(EDGE_TRUE   , 0.3),

                    (VERTEX_FALSE == null || FACE_FALSE == null) ? null : VERTEX_FALSE.interpolate(FACE_FALSE  , 0.3),
                    (VERTEX_TRUE == null || FACE_TRUE == null)   ? null : VERTEX_TRUE .interpolate(FACE_TRUE   , 0.3),

                    (EDGE_FALSE == null || VERTEX_FALSE == null) ? null : EDGE_FALSE  .interpolate(VERTEX_FALSE, 0.3),
                    (EDGE_TRUE == null || VERTEX_TRUE == null)   ? null : EDGE_TRUE   .interpolate(VERTEX_TRUE , 0.3),

                    (EDGE_FALSE == null || FACE_FALSE == null)   ? null : EDGE_FALSE  .interpolate(FACE_FALSE  , 0.3),
                    (EDGE_TRUE == null || FACE_TRUE == null)     ? null : EDGE_TRUE   .interpolate(FACE_TRUE   , 0.3),

                    (FACE_FALSE == null || VERTEX_FALSE == null) ? null : FACE_FALSE  .interpolate(VERTEX_FALSE, 0.3),
                    (FACE_TRUE == null || VERTEX_TRUE == null)   ? null : FACE_TRUE   .interpolate(VERTEX_TRUE , 0.3),

                    (FACE_FALSE == null || EDGE_FALSE == null)   ? null : FACE_FALSE  .interpolate(EDGE_FALSE  , 0.3),
                    (FACE_TRUE == null || EDGE_TRUE == null)     ? null : FACE_TRUE   .interpolate(EDGE_TRUE   , 0.3),

                    DEFAULT
            );
        }
    }

    public static final Style DEFAULT = new Style(
            Color.LIME, Color.DARKGREEN,
            Color.SALMON, Color.MAROON,
            Color.LIGHTBLUE, Color.DARKBLUE,
            Color.LIGHTGREY
    );

    public static final Style DEFAULT_INT = new Style(
            Color.rgb(0, 0, 255), Color.rgb(255, 0, 0),
            Color.rgb(0, 0, 255), Color.rgb(255, 0, 0),
            Color.rgb(0, 0, 255), Color.rgb(255, 0, 0),
            Color.LIGHTGREY
    );

    public static final Style PARTICLE = new Factory().vertexTrue(Color.RED).make();
    public static final Style VORONOI = new Factory().vertexTrue(Color.GREEN).make();
    public static final Style FLIP = new Factory().vertexTrue(Color.BLUE).make();
}
