package at.greywind.cgui.layout;

import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.event.ComponentListener;
import at.greywind.cgui.graphic.CGraphics;

public class Cell extends CComponent implements CLayout, ComponentListener{

    private CComponent content;

    private int leftPadding = 0;
    private int bottomPadding = 0;
    private int rightPadding = 0;
    private int topPadding = 0;

    private int alignment = Alignment.CENTER;

    public Cell(int width, int height){
        setSize(width, height);
    }

    public Cell(CComponent content) {
        this.content = content;

        add(content);
        setWidth(content.getWidth());
        setHeight(content.getHeight());
        addComponentListener(this);
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);
        content.drawComponent(g);
    }

    public void refresh(){
        align(alignment);
    }


    @Override
    public void resized(int width, int height) {
        refresh();
    }


    @Override
    public Cell align(int alignment) {
       this.alignment = alignment;

        content.setPosition(getWidth() / 2 - content.getWidth() / 2, getHeight() / 2 - content.getHeight() / 2);

        if(alignment >= Alignment.BOTTOM){
            content.setY(bottomPadding);
            alignment -= Alignment.BOTTOM;
        }if(alignment >= Alignment.TOP){
            content.setY(getHeight() - content.getHeight() - topPadding);
            alignment -= Alignment.TOP;
        }if(alignment >= Alignment.RIGHT){
            content.setX(getWidth() - content.getWidth() - rightPadding);
            alignment -= Alignment.RIGHT;
        }if(alignment >= Alignment.LEFT){
            content.setX(leftPadding);
        }

        return this;
    }

    @Override
    public Cell center() {
        align(Alignment.CENTER);
        return this;
    }

    @Override
    public Cell top() {
        align(Alignment.TOP);
        return this;
    }

    @Override
    public Cell right() {
        align(Alignment.RIGHT);
        return this;
    }

    @Override
    public Cell bottom() {
        align(Alignment.BOTTOM);
        return this;
    }

    @Override
    public Cell left() {
        align(Alignment.LEFT);
        return this;
    }

    @Override
    public Cell width(int width) {
        setWidth(width);
        return this;
    }

    @Override
    public Cell height(int height) {
        setHeight(height);
        return this;
    }

    @Override
    public Cell pad(int paddingLeft, int paddingRight, int paddingBottom, int paddingTop) {
        topPadding = paddingTop;
        bottomPadding = paddingBottom;
        rightPadding = paddingRight;
        leftPadding = paddingLeft;

        return this;
    }

    @Override
    public Cell pad(int padding) {
        pad(padding, padding, padding, padding);
        return this;
    }

    @Override
    public Cell padTop(int padding) {
        topPadding = padding;
        return this;
    }

    @Override
    public Cell padBottom(int padding) {
        bottomPadding = padding;
        return this;
    }

    @Override
    public Cell padLeft(int padding) {
        leftPadding = padding;
        return this;
    }

    @Override
    public Cell padRight(int padding) {
        rightPadding = padding;
        return this;
    }

    public int getTopPadding() {
        return topPadding;
    }

    public int getBottomPadding() {
        return bottomPadding;
    }

    public int getLeftPadding() {
        return leftPadding;
    }

    public int getRightPadding() {
        return rightPadding;
    }

    public CComponent getContent() {
        return content;
    }

    @Override
    public void moved(int x, int y) {

    }
}
