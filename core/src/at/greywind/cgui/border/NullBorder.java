package at.greywind.cgui.border;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;

public class NullBorder extends CBorder{

    public NullBorder() {
        super(0, CColor.BLACK, CColor.BLACK);
    }

    @Override
    public void draw(CGraphics g, float width, float height) {

    }
}
