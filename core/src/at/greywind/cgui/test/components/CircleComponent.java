package at.greywind.cgui.test.components;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.test.Breakpoint;

public class CircleComponent extends ShapeComponent {

    public static final int SHAPE_TYPE = 2;

    public static final int DEFAULT_SIZE = 30;
    private static final CColor DEFAULT_COLOR = CColor.BLUE;

    public CircleComponent(CircleComponent component, int x, int y) {
        super(x, y, component.getWidth(), component.getHeight(), component.getShapeColor(), SHAPE_TYPE);
    }

    public CircleComponent(int x, int y) {
        super(x, y, DEFAULT_SIZE, DEFAULT_SIZE, DEFAULT_COLOR, SHAPE_TYPE);
    }

    @Override
    public void drawThisComponent(CGraphics g) {
        super.drawThisComponent(g);
        g.setColor(getShapeColor());

        g.drawFilledCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2);
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }

    @Override
    public void drawBreakpoints(CGraphics g) {
        for (Breakpoint b : breakpoints) {
            g.drawCircle(b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2, b.getWidth() / 2);
        }
    }
}
