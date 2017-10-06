package at.greywind.cgui.border;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;

public final class DashedBorder extends CBorder{

    private float space;
    private float dashLength;

    protected DashedBorder(float lineWidth, float dashLength, float space, CColor c1, CColor c2) {
        super(lineWidth, c1, c2);
        this.space = space;
        this.dashLength = dashLength;
    }

    @Override
    public void draw(CGraphics g, float width, float height) {
        float widthSpacing = CBorder.calcSpacing(space, dashLength, width + lineWidth);
        float heightSpacing = CBorder.calcSpacing(space, dashLength, height + lineWidth);

        g.setColor(color);
        System.out.println(widthSpacing);

        for(int j = 0 - (int)lineWidth / 2; j <= width; j += (dashLength + widthSpacing)){
            g.drawLine(0 + j, 0, 0 + j + dashLength, 0, lineWidth);
            g.drawLine(width - j, height, width - j - dashLength, height, lineWidth);
        }

        for(int j = 0 - (int) lineWidth / 2; j <= height; j += dashLength + heightSpacing){
            g.drawLine(0, 0 + j, 0, 0 + j + dashLength, lineWidth);
            g.drawLine(width, height - j,  width, height - j - dashLength, lineWidth);
        }
    }
}
