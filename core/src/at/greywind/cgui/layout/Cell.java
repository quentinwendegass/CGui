package at.greywind.cgui.layout;

import at.greywind.cgui.component.CComponent;

public class Cell {

    private CComponent component;

    private int width;
    private int height;
    private int x;
    private int y;

    private int paddingLeft, paddingRight, paddingTop, paddingBottom;
    private int marginLeft, marginRight, marginTop, marginBottom;


    public void setSize(int width, int height){
        this.width = width;
        this.height = height;
        adjust();
    }

    public void setComponent(CComponent component){
        this.component = component;
    }

    public CComponent getComponent(){
        return component;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
        adjust();
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
        adjust();
    }

    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
        adjust();
    }

    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
        adjust();
    }

    private void adjust(){
        component.setX(paddingLeft + x);
        component.setY(paddingBottom + y);
        component.setWidth(width - paddingLeft - paddingRight);
        component.setHeight(height - paddingTop - paddingBottom);
    }

    public void setX(int x) {
        this.x = x;
        adjust();
    }

    public void setY(int y) {
        this.y = y;
        adjust();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
