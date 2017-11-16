package at.greywind.cgui.test.components;


import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.test.Breakpoint;
import at.greywind.cgui.test.components.CircleComponent;

public class ShootComponent extends CircleComponent {

    public ShootComponent(int x, int y){
        super(x, y);
        setWidth(15);
        setShapeColor(CColor.BLACK);
        setAngle(90);
        setResizeable(false);
        setKeepInComponent(true);
    }

    @Override
    public void drawBreakpoints(CGraphics g) {
        super.drawBreakpoints(g);

        for(Breakpoint b : breakpoints){
            g.drawLine(b.getX() + b.getWidth() / 2, b.getY() + b.getHeight() / 2, b.getX() + b.getWidth() / 2 + (float) (200*Math.cos(Math.toRadians(b.getAngle()))), b.getY() + b.getHeight() / 2 + (float) (200*Math.sin(Math.toRadians(b.getAngle()))));
        }
    }

    @Override
    public void drawThisComponent(CGraphics g) {
        super.drawThisComponent(g);

        g.setColor(CColor.BLACK);

        g.drawLine(getWidth() / 2, getHeight() / 2, getWidth() / 2 + (float) (200*Math.cos(Math.toRadians(getAngle()))), getHeight() / 2 +(float) (200*Math.sin(Math.toRadians(getAngle()))));
    }
}
