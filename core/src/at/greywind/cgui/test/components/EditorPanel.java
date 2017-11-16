package at.greywind.cgui.test.components;

import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.component.CPanel;
import at.greywind.cgui.event.*;
import at.greywind.cgui.layout.Alignment;
import at.greywind.cgui.test.*;
import at.greywind.cgui.test.actions.Action;
import at.greywind.cgui.test.actions.AddAction;
import at.greywind.cgui.test.actions.RemoveAction;
import at.greywind.cgui.util.Intersection;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

public class EditorPanel extends CPanel implements ComponentListener {

    private static CPanel levelPanel;
    private static ToolbarMode mode;

    private static SavePanel savePanel;

    private static ArrayList<Action> actions;
    private static int actionIndex;
    private static int windowX;
    private static int windowY;
    private static int width;
    private static int height;

    private static CComponent clipboard = null;

    public static int gridSize = 15;

    public EditorPanel(int x, int y, int width, int height) {
        setPosition(x, y);
        setSize(width, height);
        resizeTo(Alignment.RIGHT | Alignment.TOP);
        addComponentListener(this);

        actionIndex = -1;
        actions = new ArrayList<>();

        levelPanel = new LevelPanel(gridSize);
        add(levelPanel);

        levelPanel.add(new ShootComponent(levelPanel.getWidth() / 2, 0));

        savePanel = new SavePanel(getWidth() / 2 - 100, getHeight() / 2 - 50, 200,100);
        savePanel.setVisible(true);
        add(savePanel);

        addClickListener((x1, y1, e) -> {
            if(mode == null) return;

            switch (mode){
               case ADD_CIRCLE:
                   addCircle(x1, y1);
                   break;
               case ADD_TRIANGLE:
                   addTriangle(x1, y1);
                   break;
               case ADD_RECTANGLE:
                   addRectangle(x1, y1);
                   break;
                case DELETE_BRICK:
                    deleteBrick(x1, y1);
                    break;
           }
        });
    }


    private void deleteBrick(int x, int y){
        for(CComponent c : levelPanel.getChildComponents()){
            if(c instanceof ShapeComponent){
                if(c.containsPoint(getWindowX() + x, getWindowY() + y)){
                    addAction(new RemoveAction(c));
                    if(((ShapeComponent) c).isSelected()) {
                        BreakpointList.setBreakpoints(null);
                        ConfigPanel.setBreakpointConfig(null);
                    }
                    levelPanel.getChildComponents().remove(c);
                    break;
                }
            }
        }
    }

    private void addCircle(int x, int y){
        CircleComponent circleComponent = new CircleComponent(x - levelPanel.getX() - CircleComponent.DEFAULT_SIZE / 2, y - levelPanel.getY() - CircleComponent.DEFAULT_SIZE / 2);
        addAction(new AddAction(circleComponent));
        levelPanel.add(circleComponent);
        ConfigPanel.setTotalHealthValue();
    }

    private void addRectangle(int x, int y){
        RectangleComponent rectangleComponent = new RectangleComponent(x - levelPanel.getX() - CircleComponent.DEFAULT_SIZE / 2, y - levelPanel.getY() - CircleComponent.DEFAULT_SIZE / 2);
        addAction(new AddAction(rectangleComponent));
        levelPanel.add(rectangleComponent);
        ConfigPanel.setTotalHealthValue();
    }

    private void addTriangle(int x, int y){
        TriangleComponent triangleComponent = new TriangleComponent(x - levelPanel.getX() - CircleComponent.DEFAULT_SIZE / 2, y - levelPanel.getY() - CircleComponent.DEFAULT_SIZE / 2);
        addAction(new AddAction(triangleComponent));
        levelPanel.add(triangleComponent);
        ConfigPanel.setTotalHealthValue();
    }

    @Override
    public void resized(int width, int height, ComponentEvent event) {
        levelPanel.setPosition(width / 2 - levelPanel.getWidth() / 2, height / 2 - levelPanel.getHeight() / 2);
        savePanel.setPosition(getWidth() / 2 - savePanel.getWidth() / 2, getHeight() / 2 - savePanel.getHeight() / 2);
    }

    public static void setMode(ToolbarMode _mode){
        mode = _mode;
    }

    public static void addAction(Action action){
        actions.removeIf(a -> actionIndex<actions.indexOf(a));
        actions.add(action);
        actionIndex++;
    }

    public static void undo(){
        if(actionIndex < actions.size() && actionIndex >= 0){
            actions.get(actionIndex).undoAction();
            actionIndex--;
        }
    }

    public static void redo(){
        if(actionIndex < actions.size() - 1 && actionIndex >= -1){
            actionIndex++;
            actions.get(actionIndex).redoAction();
        }
    }

    public static void copy(){
        clipboard = ShapeComponent.getSelected();
    }

    public static void paste(){
        ShapeComponent newClipboard = null;

        if(clipboard != null && Intersection.isPointInRect(windowX, windowY, width, height, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY())){
            if(clipboard instanceof CircleComponent) newClipboard = new CircleComponent((CircleComponent) clipboard, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            else if(clipboard instanceof RectangleComponent) newClipboard = new RectangleComponent((RectangleComponent) clipboard, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            else if(clipboard instanceof TriangleComponent) newClipboard = new TriangleComponent((TriangleComponent) clipboard, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
        }else if(clipboard != null){
            if(clipboard instanceof CircleComponent) newClipboard = new CircleComponent((CircleComponent) clipboard, clipboard.getX() + clipboard.getWidth()/2, clipboard.getY() + clipboard.getHeight() / 2);
            else if(clipboard instanceof RectangleComponent) newClipboard = new RectangleComponent((RectangleComponent) clipboard, clipboard.getX() + clipboard.getWidth()/2, clipboard.getY() + clipboard.getHeight() / 2);
            else if(clipboard instanceof TriangleComponent) newClipboard = new TriangleComponent((TriangleComponent) clipboard, clipboard.getX() + clipboard.getWidth()/2, clipboard.getY() + clipboard.getHeight() / 2);
        }
        if(newClipboard != null){
            levelPanel.add(newClipboard);
            addAction(new AddAction(newClipboard));
        }
        clipboard = newClipboard;

    }

    public static void cut(){
        clipboard = ShapeComponent.getSelected();

        if(clipboard != null) {
            levelPanel.getChildComponents().remove(clipboard);
            addAction(new RemoveAction(clipboard));
        }
    }

    public static int getTotalRedValue(){
        int totalRedValue = 0;
        for(CComponent c : levelPanel.getChildComponents()){
            if(!(c instanceof ShootComponent))
                totalRedValue += ((ShapeComponent)c).getShapeColor().getRed() * 256;
        }
        return totalRedValue;
    }

    public static int getTotalGreenValue(){
        int totalGreenValue = 0;
        for(CComponent c : levelPanel.getChildComponents()){
            if(!(c instanceof ShootComponent))
                totalGreenValue += ((ShapeComponent)c).getShapeColor().getGreen() * 256;
        }
        return totalGreenValue;
    }

    public static int getTotalBlueValue(){
        int totalBlueValue = 0;
        for(CComponent c : levelPanel.getChildComponents()){
            if(!(c instanceof ShootComponent))
                totalBlueValue += ((ShapeComponent)c).getShapeColor().getBlue() * 256;
        }
        return totalBlueValue;
    }


    public static CPanel getLevel(){
        return levelPanel;
    }

    @Override
    public void setX(int x) {
        super.setX(x);
        windowX = getWindowX();
    }

    @Override
    public void setY(int y) {
        super.setY(y);
        windowY = getWindowY();
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        this.width = getWidth();
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        this.height = getHeight();
    }

    public static ShootComponent getShootComponent(){
        for(CComponent c : levelPanel.getChildComponents()){
            if(c instanceof ShootComponent)
                return (ShootComponent) c;
        }
        return null;
    }

    public static ArrayList<ShapeComponent> getShapeComponents(){
        ArrayList<ShapeComponent> tmp = new ArrayList<>();

        for(CComponent c : levelPanel.getChildComponents()){
            if(!(c instanceof ShootComponent))
                tmp.add((ShapeComponent) c);
        }

        return tmp;
    }

    public static void showSavePanel(){
        savePanel.setVisible(true);
        enableLevelPanel(false);
    }

    public static void enableLevelPanel(boolean enable){
        levelPanel.setEnabled(enable);
    }
}
