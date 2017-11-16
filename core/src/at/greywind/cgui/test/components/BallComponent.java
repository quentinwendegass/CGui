package at.greywind.cgui.test.components;

import at.greywind.cgui.graphic.CColor;

public class BallComponent extends CircleComponent {

    private static final CColor DEFAULT_COLOR = new CColor(100,0,0);
    public BallComponent() {
        super(0,0);
        setSize(30, 30);
        setResizeable(false);
        setMovable(false);
        setShapeColor(DEFAULT_COLOR);
        swap = false;
    }
}
