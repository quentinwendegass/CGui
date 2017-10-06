package at.greywind.cgui.border;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;

public final class LineBorder extends CBorder {

    protected LineBorder(float width, CColor c1, CColor c2){
        super(width, c1, c2);
    }

    @Override
    public void draw(CGraphics g, float width, float height) {
        g.setColor(color);
        g.drawLine(0 - super.lineWidth / 2, 0, width + super.lineWidth / 2, 0, super.lineWidth);
        g.drawLine(width, 0, width, height, super.lineWidth);
        g.drawLine(width + super.lineWidth / 2, height, 0 - super.lineWidth / 2, height, super.lineWidth);
        g.drawLine(0, height, 0, 0, super.lineWidth);
    }
}
