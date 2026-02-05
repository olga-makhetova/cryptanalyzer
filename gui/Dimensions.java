package gui;

import java.awt.*;

public enum Dimensions {
    MAIN_WINDOW(new Dimension(400, 300)),
    BUTTON(new Dimension(150, 30)),
    SMALL_BUTTON(new Dimension(20, 30)),
    TEXT_FIELD(new Dimension(500, 30)),
    SMALL_TEXT_FIELD(new Dimension(100, 30));


    private final Dimension dimension;

    Dimensions(Dimension dimension) {
        this.dimension = dimension;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
