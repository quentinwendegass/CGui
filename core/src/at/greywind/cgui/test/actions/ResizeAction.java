package at.greywind.cgui.test.actions;

import at.greywind.cgui.component.CComponent;

public class ResizeAction implements Action{

    private CComponent component;
    private int oldWidth, oldHeight;
    private int newWidth, newHeight;
    private int oldX, oldY;
    private int newX, newY;

    public ResizeAction(CComponent component, int oldX, int oldY, int oldWidth, int oldHeight) {
        this.component = component;
        this.oldWidth = oldWidth;
        this.oldHeight = oldHeight;
        this.oldX = oldX;
        this.oldY = oldY;
    }

    @Override
    public void undoAction() {
        component.setPosition(oldX, oldY);
        component.setSize(oldWidth, oldHeight);
    }

    @Override
    public void redoAction() {
        component.setPosition(newX, newY);
        component.setSize(newWidth, newHeight);
    }

    public void setNewSize(int newWidth, int newHeight){
        setNewWidth(newWidth);
        setNewHeight(newHeight);
    }

    public void setNewWidth(int newWidth) {
        this.newWidth = newWidth;
    }

    public void setNewHeight(int newHeight) {
        this.newHeight = newHeight;
    }

    public int getOldWidth() {
        return oldWidth;
    }

    public int getOldHeight() {
        return oldHeight;
    }

    public int getNewWidth() {
        return newWidth;
    }

    public int getNewHeight() {
        return newHeight;
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

}
