package at.greywind.cgui.test.components;

import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.component.DynamicComponent;
import at.greywind.cgui.component.DynamicComponentListener;
import at.greywind.cgui.event.ClickEvent;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.test.Breakpoint;
import at.greywind.cgui.test.BrickSelector;
import at.greywind.cgui.test.actions.MoveAction;
import at.greywind.cgui.test.actions.ResizeAction;

import java.util.ArrayList;
import java.util.Collections;

public abstract class ShapeComponent extends DynamicComponent implements DynamicComponentListener{

    public static final int DEFAULT_ANGLE = 0;

    private boolean selected;
    private static BrickSelector selector = new BrickSelector();

    private MoveAction currentMoveAction;
    private ResizeAction currentResizeAction;

    private CColor shapeColor;

    protected ArrayList<Breakpoint> breakpoints;
    protected boolean swap = true;

    private int angle = DEFAULT_ANGLE;
    private int alpha = 255;

    private final int shapeType;

    public ShapeComponent(int x, int y, int width, int height, CColor shapeColor, int shapeType) {
        super(x, y, width, height, true, EditorPanel.gridSize);
        this.shapeColor = shapeColor;
        setGridSize(EditorPanel.gridSize);
        setKeepInComponent(false);
        selector.add(this);
        setBackground(CColor.CLEAR);
        addListener(this);
        breakpoints = new ArrayList<>();
        this.shapeType = shapeType;
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);

        if(isSelected()) {
            g.setComponent(getParentComponent());
            g.setColor(CColor.RED);

            drawBreakpoints(g);

            for(int i = 0; i < breakpoints.size() - 1; i++){
                Breakpoint b1 = breakpoints.get(i);
                Breakpoint b2 = breakpoints.get(i+1);
                g.drawLine(b1.getX() + b1.getWidth() / 2, b1.getY() + b1.getHeight() / 2,b2.getX() + b2.getWidth() / 2, b2.getY() + b2.getHeight() / 2 );
            }
        }
    }

    public abstract void drawBreakpoints(CGraphics g);

    public CColor getShapeColor() {
        return shapeColor;
    }

    public void setShapeColor(CColor shapeColor) {
        this.shapeColor = shapeColor;
        if(this instanceof BallComponent){
            ConfigPanel.setTotalDamageValue();
        }else{
            ConfigPanel.setTotalHealthValue();
        }
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public void clicked(int x, int y, ClickEvent e) {
        super.clicked(x, y, e);

        selector.set(this);
        ConfigPanel.setShapeConfig((ShapeComponent) e.getComponent());
        BreakpointList.setBreakpoints(breakpoints);
        if(getParentComponent().getChildComponents().size() > 1 && swap) {
            int index = getParentComponent().getChildComponents().indexOf(this);
            Collections.swap(getParentComponent().getChildComponents(), index, getParentComponent().getChildComponents().size() - 1);
        }
    }

    @Override
    public void moveBegin() {
        currentMoveAction = new MoveAction(this, getX(), getY());
    }

    @Override
    public void moveEnd() {
        if(currentMoveAction != null && (currentMoveAction.getOldY() != getY() || currentMoveAction.getOldX() != getX())){
            currentMoveAction.setNewPosition(getX(), getY());
            EditorPanel.addAction(currentMoveAction);
        }
    }

    @Override
    public void resizeBegin() {
        currentResizeAction = new ResizeAction(this, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void resizeEnd() {
       if(currentResizeAction != null && (currentResizeAction.getOldWidth() != getWidth() || currentResizeAction.getOldHeight() != getHeight())){
           currentResizeAction.setNewSize(getWidth(), getHeight());
           currentResizeAction.setNewPosition(getX(), getY());
           EditorPanel.addAction(currentResizeAction);
       }
    }

    public static void addBreakpoint() {
        ShapeComponent selected = (ShapeComponent) ShapeComponent.getSelected();

        if (selected != null) {
            selected.breakpoints.add(new Breakpoint(selected.getX(), selected.getY(), selected.getWidth(), selected.getHeight(), selected.getAngle(), selected.getAlpha(), 0));
            BreakpointList.setBreakpoints(selected.breakpoints);
        }
    }

    public static void rotate(int degrees){
        ShapeComponent selected = (ShapeComponent) ShapeComponent.getSelected();

        if (selected != null)
            selected.setAngle(selected.getAngle() + degrees);
    }

    public void deleteBreakpoint(Breakpoint breakpoint){
        if(breakpoints.contains(breakpoint))
            breakpoints.remove(breakpoint);
    }

    public ArrayList<Breakpoint> getBreakpoints(){
        return breakpoints;
    }

    public static CComponent getSelected(){
        return selector.getSelected();
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getAlpha() {
        return alpha;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }

    public int getShapeType() {
        return shapeType;
    }
}
