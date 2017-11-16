package at.greywind.cgui.test.components;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.test.Breakpoint;

public class RectangleComponent extends ShapeComponent {

    public static final int SHAPE_TYPE = 0;

    public static final int DEFAULT_WIDTH = 30;
    public static final int DEFAULT_HEIGHT = 30;
    private static final CColor DEFAULT_COLOR = CColor.GREEN;

    public RectangleComponent(RectangleComponent component, int x, int y) {
        super(x, y, component.getWidth(), component.getHeight(), component.getShapeColor(), SHAPE_TYPE);
    }

    public RectangleComponent(int x, int y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR, SHAPE_TYPE);
    }

    @Override
    public void drawThisComponent(CGraphics g) {
        super.drawThisComponent(g);
        g.setColor(getShapeColor());

        g.drawFilledRect(0,0, getWidth() / 2, getHeight() / 2, getWidth(), getHeight(), 1,1, getAngle());
    }

    @Override
    public void drawBreakpoints(CGraphics g) {
        for (Breakpoint b : breakpoints) {
            g.drawRect(b.getX(), b.getY(), b.getWidth() / 2, b.getHeight() / 2, b.getWidth(), b.getHeight(), 1, 1, b.getAngle());
        }
    }
}
