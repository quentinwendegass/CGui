package at.greywind.cgui.test.actions;

import at.greywind.cgui.component.CComponent;

public class MoveAction implements Action {

    private CComponent component;
    private int oldX, oldY;
    private int newX, newY;

    public MoveAction(CComponent c, int oldX, int oldY){
        this.component = c;
        this.oldX = oldX;
        this.oldY = oldY;
        this.newX = newX;
        this.newY = newY;
    }

    public void setNewPosition(int newX, int newY){
        setNewX(newX);
        setNewY(newY);
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public int getNewX() {
        return newX;
    }

    public int getNewY() {
        return newY;
    }

    public void setNewX(int newX) {
        this.newX = newX;
    }

    public void setNewY(int newY) {
        this.newY = newY;
    }

    @Override
    public void undoAction() {
        component.setPosition(oldX, oldY);
    }

    @Override
    public void redoAction() {
        component.setPosition(newX, newY);
    }
}
