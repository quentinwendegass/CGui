package at.greywind.cgui.layout;

import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;

import java.util.ArrayList;

public abstract class CLayout {

    protected ArrayList<Cell> cells = new ArrayList<>();
    protected CComponent layoutComponent;

    public abstract void set(CComponent component);

    public abstract void width(float width);

    public abstract void height(float height);

    public abstract void paddingTop(float margin);

    public abstract void paddingBottom(float margin);

    public abstract void paddingLeft(float margin);

    public abstract void paddingRight(float margin);

    public abstract void marginTop(float margin);

    public abstract void marginBottom(float margin);

    public abstract void marginLeft(float margin);

    public abstract void marginRight(float margin);

    public void setLayoutable(Layoutable layoutable){
        layoutComponent = (CComponent) layoutable;
    }

    public void debugDraw(CGraphics g){
        g.setColor(CColor.RED);
        g.setComponent(layoutComponent);
        for(Cell c : cells){
            g.drawRect(c.getX(), c.getY(), c.getWidth(), c.getHeight(), 2);
        }
    }
}
