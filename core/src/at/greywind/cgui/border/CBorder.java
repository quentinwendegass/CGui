package at.greywind.cgui.border;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.graphic.CGraphics;

public abstract class CBorder {

    protected float lineWidth = 0;
    protected CColorGradient color;

    protected CBorder(float lineWidth, CColor c1, CColor c2){
        this.lineWidth = lineWidth;
        color = new CColorGradient(c1, c2, c1, c2);
    }

    public abstract void draw(CGraphics g, float width, float height);

    protected  static float calcSpacing(float spacing, float dotSize, float lineLength){
        int counter = 0;
        int i = 0;
        while(i < lineLength - dotSize){
            i += dotSize;
            i += spacing;
            counter++;
        }
        i += dotSize;

        return spacing - (i - lineLength) / counter;
    }

    public float getWidth() {
        return lineWidth;
    }
}
