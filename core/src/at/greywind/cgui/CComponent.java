package at.greywind.cgui;

import at.greywind.cgui.cevent.ClickEvent;
import at.greywind.cgui.cevent.ClickListener;
import at.greywind.cgui.cevent.MouseListener;
import at.greywind.cgui.cgraphic.CColor;
import at.greywind.cgui.cgraphic.CColorGradient;
import at.greywind.cgui.cgraphic.CGraphics;
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
    private CColorGradient foreground = new CColorGradient(CColor.BLACK);
    private CBorder border;

    private ArrayList<ClickListener> clickListeners = new ArrayList<>();
    private ArrayList<MouseListener> mouseListeners = new ArrayList<>();

    private Queue<ClickEvent> clickEventQueue = new Queue<>();

    private int eventIndex;
    private static boolean useEventIndex = false;


    public CComponent(){

    }

    private void fireMouseEvents(){

    }

    private void fireClickEvents(){
        for (int i = 0; i < clickEventQueue.size; i++){
            ClickEvent event = clickEventQueue.removeLast();
            boolean flag = false;
            for(CComponent c : childComponents){
                if(event.isInComponent(c) && useEventIndex){
                    if(c.eventIndex > eventIndex) {
                        event.setComponentPosition(c.getWindowX(), c.getWindowY());
                        c.addClickEventToQueue(event);
                    }else if(c.eventIndex < eventIndex)
                        clickListeners.forEach((l) -> event.fire(l));
                    else{
                        event.setComponentPosition(c.getWindowX(), c.getWindowY());
                        c.addClickEventToQueue(event);
                        clickListeners.forEach((l) -> event.fire(l));
                    }
                    flag = true;
                    break;
                }else if(event.isInComponent(c)){
                    event.setComponentPosition(c.getWindowX(), c.getWindowY());
                    c.addClickEventToQueue(event);
                    flag = true;
                    break;
                }
            }
            if(!flag){
                clickListeners.forEach((l) -> event.fire(l));
            }
        }
    }

    public void drawComponent(CGraphics g){
        fireClickEvents();

        g.setColor(background);
        g.drawFilledRect(getWindowX(), getWindowY(), getWidth(), getHeight());

        for(CComponent c : childComponents){
            c.drawComponent(g);
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

    public CColorGradient getForeground() {
        return foreground;
    }

    public void setForeground(CColorGradient foreground) {
        this.foreground = foreground;
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


    public int getEventIndex() {
        return eventIndex;
    }

    public void setEventIndex(int eventIndex) {
        this.eventIndex = eventIndex;
    }

    public void addClickEventToQueue(ClickEvent event){
        clickEventQueue.addFirst(event);
    }

    public void add(CComponent cComponent){
        cComponent.parentComponent = this;
        cComponent.setEventIndex(getEventIndex() + 10);
        childComponents.add(cComponent);
    }

    public void useEventIndex(boolean use){
        useEventIndex = use;
    }
}
