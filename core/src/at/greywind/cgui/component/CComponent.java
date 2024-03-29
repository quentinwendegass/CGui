package at.greywind.cgui.component;

import at.greywind.cgui.CWindow;
import at.greywind.cgui.border.CBorder;
import at.greywind.cgui.border.NullBorder;
import at.greywind.cgui.event.*;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.graphic.CGraphics;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Queue;

import java.util.ArrayList;

public abstract class CComponent implements Focusable, ClickListener{

    public enum ClickEventMode{WINDOW_CLICK_EVENT, COMPONENT_CLICK_EVENT}

    private ClickEventMode clickEventMode = ClickEventMode.WINDOW_CLICK_EVENT;

    private int id;

    private int snapAlign = 0;
    private int resizeAlign = 0;

    private int rightSpacing = -1;
    private int topSpacing = -1;

    protected ArrayList<CComponent> childComponents = new ArrayList<>();
    protected CComponent parentComponent = null;

    private int x;
    private int y;

    protected int oldX;
    protected int oldY;

    private int width;
    private int height;

    private int minWidth = 10;
    private int minHeight = 10;

    private int maxWidth = 4096;
    private int maxHeight = 4096;

    private CColorGradient background = new CColorGradient(CColor.WHITE);
    private CBorder border = new NullBorder();

    private ArrayList<ClickListener> clickListeners = new ArrayList<>();
    private ArrayList<MouseListener> mouseListeners = new ArrayList<>();
    private ArrayList<ChangeListener> changeListeners = new ArrayList<>();
    private ArrayList<KeyListener> keyListeners = new ArrayList<>();
    private ArrayList<FocusListener> focusListeners = new ArrayList<>();
    private ArrayList<ComponentListener> componentListeners = new ArrayList<>();
    private ArrayList<ScrollListener> scrollListeners = new ArrayList<>();



    private boolean hasFocus = false;

    private Queue<CEvent> eventQueue = new Queue<>();

    private int eventIndex;
    private boolean useEventIndex = false;

    private boolean visible = true;

    protected CWindow window;

    public CComponent(){
        id = this.hashCode();
    }

    public void processEvents(){
        for (int i = 0; i < eventQueue.size; i++) {
            CEvent event = eventQueue.removeLast();
            if(event instanceof MouseEvent)
                fireMouseEvent((MouseEvent) event);
            else if(event instanceof ClickEvent && clickEventMode == ClickEventMode.COMPONENT_CLICK_EVENT)
                fireComponentClickEvent((ClickEvent)event);
            else if(event instanceof ClickEvent && clickEventMode == ClickEventMode.WINDOW_CLICK_EVENT)
                fireWindowClickEvent((ClickEvent)event);
            else if(event instanceof ChangeEvent)
                fireChangeEvent((ChangeEvent) event);
            else if(event instanceof KeyEvent)
                fireKeyEvent((KeyEvent)event);
            else if(event instanceof ComponentEvent)
                fireComponentEvent((ComponentEvent) event);
            else if(event instanceof ScrollEvent)
                fireScrollEvent((ScrollEvent) event);
        }
    }

    private void fireComponentEvent(ComponentEvent event){
        componentListeners.forEach(listener -> event.fire(listener));
    }

    private void fireKeyEvent(KeyEvent event){
        if(isFocused()) keyListeners.forEach(listener -> event.fire(listener));
    }

    private void fireChangeEvent(ChangeEvent event){
        changeListeners.forEach((l)->event.fire(l));
    }

    private void fireMouseEvent(MouseEvent event){
        boolean flag = true;
        for(CComponent c : childComponents){
            if(event.isInComponent(c) && event.wasLastFrameInComponent(c) && c.isVisible()){
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
            }else if(!event.isInComponent(c) && event.wasLastFrameInComponent(c) && c.isVisible()){
                event.setComponentPosition(c.getWindowX(), c.getWindowY());
                c.addEventToQueue(new MouseEvent(event, MouseEvent.MouseType.EXIT));
            }else if(event.isInComponent(c) && !event.wasLastFrameInComponent(c) && c.isVisible()){
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

    private void fireScrollEvent(ScrollEvent event){
        boolean flag = false;
        for(CComponent c : childComponents){
            if(event.isInComponent(c) && useEventIndex && c.isVisible()){
                if(c.eventIndex > eventIndex) {
                    c.addEventToQueue(event);
                }else if(c.eventIndex < eventIndex)
                    scrollListeners.forEach((l) -> event.fire(l));
                else{
                    c.addEventToQueue(event);
                    scrollListeners.forEach((l) -> event.fire(l));
                }
                flag = true;
                break;
            }else if(event.isInComponent(c) && c.isVisible()){
                c.addEventToQueue(event);
                flag = true;
                break;
            }
        }
        if(!flag){
            scrollListeners.forEach((l) -> event.fire(l));
        }
    }

    //If click event should occur when the position of the child component is out of the parent component           Nice English m8
    private void fireWindowClickEvent(ClickEvent event){
        event.setComponent(this);
        event.setComponentPosition(this.getWindowX(), this.getWindowY());
        if(event.isInComponent(this)) clickListeners.forEach((l) -> event.fire(l));
        getChildComponents().forEach(c -> c.addEventToQueue(event));
    }

    private void fireComponentClickEvent(ClickEvent event){
        boolean flag = false;
        for(CComponent c : childComponents){
            if(event.isInComponent(c) && useEventIndex && c.isVisible()){
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
            }else if(event.isInComponent(c) && c.isVisible()){
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

    @Override
    public void setFocus(boolean focus) {
        if(focus != hasFocus) {
            hasFocus = focus;
            focusListeners.forEach((l) -> {
                if (focus) l.gainFocus(this);
                else l.lostFocus(this);
            });
        }
    }


    @Override
    public boolean isFocused() {
        return hasFocus;
    }

    @Override
    public void addFocusListener(FocusListener listener) {
        focusListeners.add(listener);
    }

    @Override
    public void addKeyListener(KeyListener listener) {
        keyListeners.add(listener);
    }

    @Override
    public void clicked(int x, int y, ClickEvent e) {
        setFocus(true);
    }

    public void snapTo(int align){
        this.snapAlign = align;
    }

    public void resizeTo(int align){
        this.resizeAlign = align;
    }

    public void resized(int newWidth, int newHeight, int oldWidth, int oldHeight){
        if(snapAlign == 4){
            setX(newWidth - (oldWidth - getX()));
        }else if(snapAlign == 8){
            setY(newHeight - (oldHeight - getY()));
        }else if(snapAlign == 12){
            setX(newWidth - (oldWidth - getX()));
            setY(newHeight - (oldHeight - getY()));
        }

        if(resizeAlign == 4){
            if(rightSpacing >= 0)
                setWidth(newWidth - getX() - rightSpacing);
            else
                setWidth(getWidth() + (newWidth - oldWidth));
        }else if(resizeAlign == 8){
            if(topSpacing >= 0)
                setHeight(newHeight - getY() - topSpacing);
            else
                setHeight(getHeight() + (newHeight - oldHeight));
        }else if(resizeAlign == 12){
            if(rightSpacing >= 0)
                setWidth(newWidth - getX() - rightSpacing);
            else
                setWidth(getWidth() + (newWidth - oldWidth));
            if(topSpacing >= 0)
                setHeight(newHeight - getY() - topSpacing);
            else
                setHeight(getHeight() + (newHeight - oldHeight));
        }
    }

    public void updateComponent(CGraphics g){
        if(visible){
            processEvents();
            drawComponent(g);
        }
    }

    public void drawComponent(CGraphics g){
        drawThisComponent(g);
        drawChildComponents(g);
    }

    public void drawChildComponents(CGraphics g){
        for(CComponent c : childComponents){
            c.updateComponent(g);
        }
    }

    public void drawThisComponent(CGraphics g){
        g.setComponent(this);

        g.setColor(background);
        g.drawFilledRect(0, 0, getWidth(), getHeight());

        if(border != null)  border.draw(g, getWidth(), getHeight());
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

    public boolean isVisible(){
        return visible;
    }

    public void setVisible(boolean visible){
        if(visible != this.visible){
            this.visible = visible;

            if(visible)
                addEventToQueue(new ComponentEvent(this, ComponentEvent.Type.SHOWN));
            else
                addEventToQueue(new ComponentEvent(this, ComponentEvent.Type.HIDDEN));
        }
    }

    public void setPosition(int x, int y){
        setX(x);
        setY(y);
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        addEventToQueue(new ComponentEvent(this, x, getY(), ComponentEvent.Type.MOVED));
        oldX = getX();

        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        addEventToQueue(new ComponentEvent(this, getX(), y, ComponentEvent.Type.MOVED));
        oldY = getY();

        this.y = y;
    }

    public int getWindowX(){
        if(parentComponent != null)
            return parentComponent.getWindowX() + x;
        else
            return x;
    }

    public void setSize(int width, int height){
        setWidth(width);
        setHeight(height);
    }

    public void setMinSize(int width, int height){
        setMinWidth(width);
        setMinHeight(height);
    }

    public void setMaxSize(int width, int height){
        setMaxWidth(width);
        setMaxHeight(height);
    }

    public int getMinWidth() {
        return minWidth;
    }

    public void setMinWidth(int minWidth) {
        this.minWidth = minWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public void setMinHeight(int minHeight) {
        this.minHeight = minHeight;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        int oldWidth = this.width;

        if(width > maxWidth)
            this.width = maxWidth;
        else if(width < minWidth)
            this.width = minWidth;
        else{
            this.width = width;
            if(parentComponent != null)
                rightSpacing = parentComponent.getWidth() - (getX() + getWidth());
        }

        childComponents.forEach((c)->c.resized(this.width, getHeight(), oldWidth, getHeight()));

        addEventToQueue(new ComponentEvent(this, getWidth(), getHeight(), ComponentEvent.Type.RESIZED));
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
        int oldHeight = this.height;

        if(height > maxHeight)
            this.height = maxHeight;
        else if(height < minHeight)
            this.height = minHeight;
        else {
            this.height = height;
            if(parentComponent != null)
                topSpacing = parentComponent.getHeight() - (getY() + getHeight());
        }

        childComponents.forEach((c)->c.resized(getWidth(), this.height, getWidth(), oldHeight));

        addEventToQueue(new ComponentEvent(this, getWidth(), getHeight(), ComponentEvent.Type.RESIZED));
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

    public void addScrollListener(ScrollListener scrollListener) {
        this.scrollListeners.add(scrollListener);
    }

    public void addComponentListener(ComponentListener componentListener) {this.componentListeners.add(componentListener); }
    public int getEventIndex() {
        return eventIndex;
    }

    public void setEventIndex(int eventIndex) {
        this.eventIndex = eventIndex;
    }

    public void addEventToQueue(CEvent event){
        if(event instanceof ClickEvent || event instanceof MouseEvent)
            ((AbstractEvent) event).setComponent(this);

        eventQueue.addFirst(event);
    }

    public void add(CComponent cComponent){
        if(window!=null) cComponent.registerToWindow(window);
        cComponent.parentComponent = this;
        cComponent.setEventIndex(getEventIndex());
        cComponent.addEventToQueue(new ComponentEvent(this, ComponentEvent.Type.ADDED));
        childComponents.add(cComponent);
    }

    public void registerToWindow(CWindow window){
        this.window = window;
        addClickListener(this);
        childComponents.forEach(component -> {
            if(component.window == null) component.registerToWindow(window);
        });
        window.registerFocusComponent(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void useEventIndex(boolean use){
        useEventIndex = use;
    }

    public boolean isEventIndexInUse(){
        return useEventIndex;
    }

    public void setClickEventMode(ClickEventMode mode){
        this.clickEventMode = mode;
    }
}
