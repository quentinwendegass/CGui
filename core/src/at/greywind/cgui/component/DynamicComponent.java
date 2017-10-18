package at.greywind.cgui.component;

import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.graphic.CGraphics;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;


public class DynamicComponent extends CComponent{

    private boolean resizeable = true;
    private boolean movable = true;
    private boolean keepInComponent = true;

    private boolean touchDown = false;
    private int mouseOffsetX;
    private int mouseOffsetY;

    private int resizeOffset = 6;

    private boolean moving = false;
    private boolean resizing = false;


    @Override
    public void updateComponent(CGraphics g) {
        super.updateComponent(g);

        if(!resizing) move();
        if(!moving) resize();
    }

    private void move(){
        if(touchDown){
            if(movable && Gdx.input.isTouched()){
                moving = true;
                setX(Gdx.input.getX() - getParentComponent().getWindowX() - mouseOffsetX);
                setY(Gdx.graphics.getHeight() - Gdx.input.getY() - getParentComponent().getWindowY() - mouseOffsetY);

                if(keepInComponent){
                    if(getX() < 0) setX(0);
                    else if(getX() > getParentComponent().getWidth() - getWidth()) setX(getParentComponent().getWidth() - getWidth());

                    if(getY() < 0) setY(0);
                    else if(getY() > getParentComponent().getHeight() - getHeight()) setY(getParentComponent().getHeight() - getHeight());
                }
            }else{
                touchDown = false;
                moving = false;
            }

            if(moving)
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
            else
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
        }
    }

    private boolean resRight;
    private boolean resTop;
    private boolean resLeft;
    private boolean resBottom;

    private boolean lastInput = false;

    private void resize() {
        if (resizeable) {

            if (isInBounds(getWindowX() + getWidth() - resizeOffset / 2, getWindowY(), resizeOffset, getHeight(), Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && !lastInput) {
                resRight = true;
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.HorizontalResize);
            }else if (isInBounds(getWindowX(), getWindowY() + getHeight() - resizeOffset / 2, getWidth(), resizeOffset, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && !lastInput){
                resTop = true;
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.VerticalResize);
            }else if (isInBounds(getWindowX() - resizeOffset / 2, getWindowY(), resizeOffset, getHeight(), Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && !lastInput){
                resLeft = true;
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.HorizontalResize);
            }else if(isInBounds(getWindowX(), getWindowY() - resizeOffset/2, getWidth(), resizeOffset, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && !lastInput) {
                resBottom = true;
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.VerticalResize);
            }else{
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }

            if(resRight && Gdx.input.isTouched()){
                setWidth(Gdx.input.getX() - getWindowX());
                if(keepInComponent && Gdx.input.getX() > getParentComponent().getWindowX() + getParentComponent().getWidth())
                    setWidth(getParentComponent().getWindowX() + getParentComponent().getWidth() - getWindowX());
            }
            else
                resRight = false;

            if(resTop && Gdx.input.isTouched()) {
                setHeight(Gdx.graphics.getHeight() - Gdx.input.getY() - getWindowY());
                if(keepInComponent && (Gdx.graphics.getHeight() - Gdx.input.getY()) > getParentComponent().getWindowY() + getParentComponent().getHeight())
                    setHeight(getParentComponent().getWindowY() + getParentComponent().getHeight() - getWindowY());
            }
            else
                resTop = false;

            if(resLeft && Gdx.input.isTouched()) {
                int oldWidth = getWidth();
                setWidth(getWindowX() - Gdx.input.getX() + getWidth());
                setX(getX() - (getWidth() - oldWidth));

                if(keepInComponent && Gdx.input.getX() < getParentComponent().getWindowX()){
                    setWidth(getWidth() - (getParentComponent().getWindowX() - getX()));
                    setX(0);
                }
            }else
                resLeft = false;

            if(resBottom && Gdx.input.isTouched()){
                int oldHeight = getHeight();
                setHeight(getWindowY() - (Gdx.graphics.getHeight() - Gdx.input.getY()) + getHeight());
                setY(getY() - (getHeight() - oldHeight));

                if(keepInComponent && Gdx.graphics.getHeight() - Gdx.input.getY() < getParentComponent().getWindowY()){
                    setHeight(getHeight() - (getParentComponent().getWindowY() - getY()));
                    setY(0);
                }
            }else
                resBottom = false;


            if(resLeft || resRight || resBottom || resTop)
                resizing = true;
            else
                resizing = false;

            if(resBottom || resTop)
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.VerticalResize);
            else if(resLeft || resRight)
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.HorizontalResize);
        }

        lastInput = Gdx.input.isTouched();
    }

    @Override
    public void touchDown(int x, int y, ClickEvent e) {
        touchDown = true;
        mouseOffsetX = x;
        mouseOffsetY = y;
    }

    private boolean isInBounds(int x, int y, int width, int height, int px, int py){
        if(px > x && px < x + width && py > y && py < y + height)
            return true;
        else return false;
    }

    public boolean isResizeable() {
        return resizeable;
    }

    public void setResizeable(boolean resizeable) {
        this.resizeable = resizeable;
    }

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }

    public boolean isKeepInComponent() {
        return keepInComponent;
    }

    public void setKeepInComponent(boolean keepInComponent) {
        this.keepInComponent = keepInComponent;
    }
}
