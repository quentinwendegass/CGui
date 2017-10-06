package at.greywind.cgui.border;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;

public final class DottedBorder extends CBorder {

    private float space;

    protected DottedBorder(float width, float space, CColor c1, CColor c2){
        super(width, c1, c2);
        this.space = space;
    }

    @Override
    public void draw(CGraphics g, float width, float height) {

        float widthSpacing = CBorder.calcSpacing(space, lineWidth, width + lineWidth);
        float heightSpacing = CBorder.calcSpacing(space, lineWidth, height + lineWidth);

        g.setColor(color);

        for(int j = 0; j <= width; j += (lineWidth + widthSpacing)){
            g.drawFilledCircle(0 + j, 0, super.lineWidth / 2);
            g.drawFilledCircle(width - j, height, super.lineWidth / 2);
        }

        for(int j = 0; j <= height; j += lineWidth + heightSpacing){
            g.drawFilledCircle(0, 0 + j, super.lineWidth / 2);
            g.drawFilledCircle(width, height - j, super.lineWidth / 2);
        }
    }
}
