package at.greywind.cgui.cevent;

import at.greywind.cgui.CComponent;

public abstract class AbstractEvent {

    protected int windowX;
    protected int windowY;

    protected int x;
    protected int y;

    public AbstractEvent(int windowX, int windowY){
        this.windowX = windowX;
        this.windowY = windowY;
    }

    public boolean isInComponent(CComponent comp){
        if(comp.containsPoint(windowX, windowY))
            return true;
        else return false;
    }

    public void setComponentPosition(int x, int y){
        this.x = windowX - x;
        this.y = windowY - y;
    }

    public void setWindowPosition(int x, int y){
        this.windowX = x;
        this.windowY = y;
    }

    public int getWindowX() {
        return windowX;
    }

    public int getWindowY() {
        return windowY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
