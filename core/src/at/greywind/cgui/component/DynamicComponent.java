package at.greywind.cgui.component;

import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.util.Intersection;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;

import java.util.ArrayList;

public class DynamicComponent extends CComponent{

    private boolean resizeable = true;
    private boolean movable = true;
    private boolean keepInComponent = true;

    private boolean snapToGrid = true;
    private int gridSize;

    private boolean touchDown = false;
    private int mouseOffsetX;
    private int mouseOffsetY;

    private int resizeOffset = 3;

    private boolean moving = false;
    private boolean resizing = false;

    private ArrayList<DynamicComponentListener> listeners;


    public DynamicComponent(){
        this(0,0);
    }

    public DynamicComponent(boolean snapToGrid){
        this(0,0, 10, 10, snapToGrid, 20);
    }

    public DynamicComponent(int x, int y){
        this(x, y, 10, 10, false, 20);
    }

    public DynamicComponent(int x, int y, int width, int height, boolean snapToGrid, int gridSize){
        super();

        this.gridSize = gridSize;

        setPosition(x, y);
        snapToGrid(snapToGrid);

        setSize(width, height);

        if(snapToGrid){
            snapPositionToGrid();
            setWidth(getWidth()  + (getWidth() % gridSize));
            setHeight(getHeight() + (getHeight() % gridSize));
        }

        listeners = new ArrayList<>();
    }

    @Override
    public void updateComponent(CGraphics g) {
        super.updateComponent(g);

        if(Intersection.isPointInRect(getWindowX(), getWindowY(), getWidth(), getHeight(),
                Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && !Gdx.input.isTouched()){
            setFocus(true);
        }

        if(!resizing && isFocused()) move();
        if(!moving && isFocused()) resize();
    }

    private void move(){
        if(touchDown){
            if(movable && Gdx.input.isTouched()){
                if(!moving){
                    moving = true;
                    listeners.forEach(l -> l.moveBegin());
                }
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Hand);
                setX(Gdx.input.getX() - getParentComponent().getWindowX() - mouseOffsetX);
                setY(Gdx.graphics.getHeight() - Gdx.input.getY() - getParentComponent().getWindowY() - mouseOffsetY);
                snapPositionToGrid();

                if(keepInComponent){
                    if(getX() < 0) setX(0);
                    else if(getX() > getParentComponent().getWidth() - getWidth()) setX(getParentComponent().getWidth() - getWidth());

                    if(getY() < 0) setY(0);
                    else if(getY() > getParentComponent().getHeight() - getHeight()) setY(getParentComponent().getHeight() - getHeight());
                }
            }else{
                touchDown = false;
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);

                if(moving){
                    moving = false;
                    listeners.forEach(l -> l.moveEnd());
                }
            }
        }
    }

    private void snapPositionToGrid(){
        if(snapToGrid) {
            int offsetX = getX() % gridSize;
            setX(getX() - offsetX);

            int offsetY = getY() % gridSize;
            setY(getY() - offsetY);
        }
    }

    private boolean resRight;
    private boolean resTop;
    private boolean resLeft;
    private boolean resBottom;

    private boolean lastInput = false;

    private void resize() {
        if (resizeable) {

            if (Intersection.isPointInRect(getWindowX() + getWidth() - resizeOffset, getWindowY(), resizeOffset + 1, getHeight(), Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && !lastInput) {
                resRight = true;
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.HorizontalResize);
            }else if (Intersection.isPointInRect(getWindowX(), getWindowY() + getHeight() - resizeOffset, getWidth(), resizeOffset + 1, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && !lastInput){
                resTop = true;
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.VerticalResize);
            }else if (Intersection.isPointInRect(getWindowX() - 1, getWindowY(), resizeOffset, getHeight(), Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && !lastInput){
                resLeft = true;
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.HorizontalResize);
            }else if(Intersection.isPointInRect(getWindowX(), getWindowY() - 1, getWidth(), resizeOffset, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY()) && !lastInput) {
                resBottom = true;
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.VerticalResize);
            }else{
                Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);
            }

            if(resRight && Gdx.input.isTouched()){
                setWidth(Gdx.input.getX() - getWindowX());

                if(snapToGrid){
                    int offset = gridSize - (getWidth() % gridSize);
                    setWidth(getWidth() + offset);
                }

                if(keepInComponent && Gdx.input.getX() > getParentComponent().getWindowX() + getParentComponent().getWidth())
                    setWidth(getParentComponent().getWindowX() + getParentComponent().getWidth() - getWindowX());
            }
            else
                resRight = false;

            if(resTop && Gdx.input.isTouched()) {
                setHeight(Gdx.graphics.getHeight() - Gdx.input.getY() - getWindowY());

                if(snapToGrid){
                    int offset = gridSize - (getHeight() % gridSize);
                    setHeight(getHeight() + offset);
                }

                if(keepInComponent && (Gdx.graphics.getHeight() - Gdx.input.getY()) > getParentComponent().getWindowY() + getParentComponent().getHeight())
                    setHeight(getParentComponent().getWindowY() + getParentComponent().getHeight() - getWindowY());
            }
            else
                resTop = false;

            if(resLeft && Gdx.input.isTouched()) {
                int oldWidth = getWidth();
                setWidth(getWindowX() - Gdx.input.getX() + getWidth());
                setX((getX() - (getWidth() - oldWidth)));

                if(snapToGrid){
                    int offset = (getX() % gridSize);
                    setX(getX() - offset);
                    setWidth(getWidth() + offset);
                }

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

                if(snapToGrid){
                    int offset = (getY() % gridSize);
                    setY(getY() - offset);
                    setHeight(getHeight() + offset);
                }

                if(keepInComponent && Gdx.graphics.getHeight() - Gdx.input.getY() < getParentComponent().getWindowY()){
                    setHeight(getHeight() - (getParentComponent().getWindowY() - getY()));
                    setY(0);
                }
            }else
                resBottom = false;


            if(resLeft || resRight || resBottom || resTop){
                if(!resizing)
                    listeners.forEach(l -> l.resizeBegin());
                resizing = true;
            }
            else{
                if(resizing)
                    listeners.forEach(l -> l.resizeEnd());
                resizing = false;
            }

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

    public boolean isSnapedToGrid() {
        return snapToGrid;
    }

    public void snapToGrid(boolean snapToGrid) {
        this.snapToGrid = snapToGrid;
    }

    public int getGridSize() {
        return gridSize;
    }

    public void setGridSize(int gridSize) {
        this.gridSize = gridSize;
    }

    public void addListener(DynamicComponentListener listener){
        listeners.add(listener);
    }
}
