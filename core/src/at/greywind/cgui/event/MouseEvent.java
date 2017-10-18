package at.greywind.cgui.event;

import at.greywind.cgui.component.CComponent;

public class MouseEvent extends PositionEvent<MouseListener> {

    private MouseType type;

    private int lastFrameWindowX;
    private int lastFrameWindowY;

    public enum MouseType{
        EXIT, ENTER, MOVE
    }

    public MouseEvent(MouseEvent event, MouseType type){
        this(event.getWindowX(),event.getWindowY(), event.lastFrameWindowX, event.lastFrameWindowY, type);
    }

    public MouseEvent(MouseType type){
        this(0,0, 0,0, type);
    }

    public MouseEvent(int wX, int wY, int lwX, int lwY, MouseType type){
        super(wX, wY);
        this.lastFrameWindowX = lwX;
        this.lastFrameWindowY = lwY;
        this.type = type;
    }

    @Override
    public void fire(MouseListener listener) {
        switch (type){
            case EXIT:
                listener.exit(this);
                break;
            case ENTER:
                listener.enter(this);
                break;
            case MOVE:
                listener.move(x, y, this);
                break;
        }
    }

    public void setLastFramePosition(int x, int y){
        this.lastFrameWindowX = x;
        this.lastFrameWindowY = y;
    }

    public boolean wasLastFrameInComponent(CComponent c){
        if(c.containsPoint(lastFrameWindowX, lastFrameWindowY)) return true;
        else return false;
    }
}
