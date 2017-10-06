package at.greywind.cgui.component;

import at.greywind.cgui.border.CBorder;
import at.greywind.cgui.event.*;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.graphic.CGraphics;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Queue;

import java.util.ArrayList;

public abstract class CComponent {

    private ArrayList<CComponent> childComponents = new ArrayList<>();
    private CComponent parentComponent = null;

    private int x;
    private int y;

    private int width;
    private int height;

    private CColorGradient background = new CColorGradient(CColor.WHITE);
    private CBorder border = null;

    private ArrayList<ClickListener> clickListeners = new ArrayList<>();
    private ArrayList<MouseListener> mouseListeners = new ArrayList<>();
    private ArrayList<ChangeListener> changeListeners = new ArrayList<>();

    private Queue<AbstractEvent> eventQueue = new Queue<>();

    private int eventIndex;
    private boolean useEventIndex = false;


    public CComponent(){

    }

    private void processEvents(){
        for (int i = 0; i < eventQueue.size; i++) {
            AbstractEvent event = eventQueue.removeLast();
            if(event instanceof MouseEvent)
                fireMouseEvent((MouseEvent) event);
            else if(event instanceof ClickEvent)
                fireClickEvent((ClickEvent)event);
            else if(event instanceof ChangeEvent)
                fireChangeEvent((ChangeEvent) event);
        }
    }

    private void fireChangeEvent(ChangeEvent event){
        changeListeners.forEach((l)->event.fire(l));
    }

    private void fireMouseEvent(MouseEvent event){
        boolean flag = true;
        for(CComponent c : childComponents){
            if(event.isInComponent(c) && event.wasLastFrameInComponent(c)){
                if(useEventIndex){
                    if(eventIndex < c.eventIndex){
                        event.setComponentPosition(c.getWindowX(), c.getWindowY());
                        c.addEventToQueue(event);
                        flag = false;
                    }else if(eventIndex == c.eventIndex){
                        event.setComponentPosition(c.getWindowX(), c.getWindowY());
                        c.addEventToQueue(event);
                    }
                    break;
                }else{
                    event.setComponentPosition(c.getWindowX(), c.getWindowY());
                    c.addEventToQueue(event);
                    flag = false;
                    break;
                }
            }else if(!event.isInComponent(c) && event.wasLastFrameInComponent(c)){
                event.setComponentPosition(c.getWindowX(), c.getWindowY());
                c.addEventToQueue(new MouseEvent(event, MouseEvent.MouseType.EXIT));
            }else if(event.isInComponent(c) && !event.wasLastFrameInComponent(c)){
                event.setComponentPosition(c.getWindowX(), c.getWindowY());
                c.addEventToQueue(new MouseEvent(event, MouseEvent.MouseType.ENTER));
            }
        }

        if(event.isInComponent(this) && event.wasLastFrameInComponent(this) && flag){
            mouseListeners.forEach((l) -> event.fire(l));
        }else if(!event.isInComponent(this) && event.wasLastFrameInComponent(this)){
            mouseListeners.forEach((l) -> new MouseEvent(event, MouseEvent.MouseType.EXIT).fire(l));
        }else if(event.isInComponent(this) && !event.wasLastFrameInComponent(this)){
            mouseListeners.forEach((l) -> new MouseEvent(event, MouseEvent.MouseType.ENTER).fire(l));
        }
    }

    private void fireClickEvent(ClickEvent event){
        boolean flag = false;
        for(CComponent c : childComponents){
            if(event.isInComponent(c) && useEventIndex){
                if(c.eventIndex > eventIndex) {
                    event.setComponentPosition(c.getWindowX(), c.getWindowY());
                    c.addEventToQueue(event);
                }else if(c.eventIndex < eventIndex)
                    clickListeners.forEach((l) -> event.fire(l));
                else{
                    event.setComponentPosition(c.getWindowX(), c.getWindowY());
                    c.addEventToQueue(event);
                    clickListeners.forEach((l) -> event.fire(l));
                }
                flag = true;
                break;
            }else if(event.isInComponent(c)){
                event.setComponentPosition(c.getWindowX(), c.getWindowY());
                c.addEventToQueue(event);
                flag = true;
                break;
            }
        }
        if(!flag){
            clickListeners.forEach((l) -> event.fire(l));
        }
    }

    public void updateComponent(CGraphics g){
        processEvents();

        drawComponent(g);
    }

    private void drawComponent(CGraphics g){
        g.setComponent(this);

        g.setColor(background);
        g.drawFilledRect(0, 0, getWidth(), getHeight());

        if(border != null)  border.draw(g, getWidth(), getHeight());

        for(CComponent c : childComponents){
            c.updateComponent(g);
        }
    }

    public boolean containsPoint(int windowX, int windowY){
        Rectangle comp = new Rectangle(getWindowX(), getWindowY(), width, height);
        if(comp.contains(windowX, windowY))
            return true;
        return false;
    }

    public ArrayList<CComponent> getChildComponents() {
        return childComponents;
    }

    public CComponent getParentComponent() {
        return parentComponent;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWindowX(){
        if(parentComponent != null)
            return parentComponent.getWindowX() + x;
        else
            return x;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getWindowY(){
        if(parentComponent != null)
            return parentComponent.getWindowY() + y;
        else
            return y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public CColorGradient getBackground() {
        return background;
    }

    public void setBackground(CColorGradient background) {
        this.background = background;
    }

    public void setBackground(CColor background) {
        setBackground(new CColorGradient(background));
    }

    public CBorder getBorder() {
        return border;
    }

    public void setBorder(CBorder border) {
        this.border = border;
    }

    public void addClickListener(ClickListener clickListener) {
        this.clickListeners.add(clickListener);
    }

    public void addMouseListener(MouseListener mouseListener) {
        this.mouseListeners.add(mouseListener);
    }

    public void addChangeListener(ChangeListener changeListener) {
        this.changeListeners.add(changeListener);
    }

    public int getEventIndex() {
        return eventIndex;
    }

    public void setEventIndex(int eventIndex) {
        this.eventIndex = eventIndex;
    }

    public void addEventToQueue(AbstractEvent event){
        eventQueue.addFirst(event);
    }

    public void add(CComponent cComponent){
        cComponent.parentComponent = this;
        cComponent.setEventIndex(getEventIndex());
        childComponents.add(cComponent);
    }

    public void useEventIndex(boolean use){
        useEventIndex = use;
    }

    public boolean isEventIndexInUse(){
        return useEventIndex;
    }
}
