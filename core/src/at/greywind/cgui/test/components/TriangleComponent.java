package at.greywind.cgui.test.components;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.test.Breakpoint;

public class TriangleComponent extends ShapeComponent {

    public static final int SHAPE_TYPE = 1;
    public static final int DEFAULT_WIDTH = 30;
    public static final int DEFAULT_HEIGHT = 30;
    private static final CColor DEFAULT_COLOR = CColor.RED;
    public TriangleComponent(TriangleComponent component, int x, int y) {
        super(x, y, component.getWidth(), component.getHeight(), component.getShapeColor(), SHAPE_TYPE);
    }

    public TriangleComponent(int x, int y) {
        super(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_COLOR, SHAPE_TYPE);
    }

    @Override
    public void drawThisComponent(CGraphics g) {
        super.drawThisComponent(g);
        g.setColor(getShapeColor());

        g.drawFilledTriangle(0,0, getWidth(), 0, getWidth() / 2, getHeight(), getAngle());
    }

    @Override
    public void drawBreakpoints(CGraphics g) {
        for (Breakpoint b : breakpoints) {
            g.drawTriangle(b.getX(), b.getY(), b.getX() + b.getWidth(), b.getY(), b.getX() + b.getWidth() / 2, b.getY() + b.getHeight(), b.getAngle());
        }
    }
}
