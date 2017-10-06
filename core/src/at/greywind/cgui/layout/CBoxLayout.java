package at.greywind.cgui.layout;

import at.greywind.cgui.component.CComponent;

public class CBoxLayout extends CLayout{

    private int DEFAULT_SIZE = 50;

    private int totalSize = 0;

    private Axis axis = Axis.X_AXIS;
    private Layoutable.Alignment alignment = Layoutable.Alignment.CENTER;

    public enum Axis{
        X_AXIS, Y_AXIS
    }

    public CBoxLayout(Axis axis){
        this.axis = axis;
    }

    public void setDefaultSize(int size){
        DEFAULT_SIZE = size;
    }

    public void setAlignment(Layoutable.Alignment alignment){
        this.alignment = alignment;
    }

    @Override
    public void set(CComponent component) {
        Cell cell = new Cell();
        cell.setComponent(component);
        cells.add(cell);
        width(DEFAULT_SIZE);
        height(DEFAULT_SIZE);
    }

    private void setPosition(){
        calcTotalSize();

        if(axis == Axis.Y_AXIS){
            int y = layoutComponent.getHeight() / 2 - totalSize / 2;
            switch (alignment){
                case BOTTOM:
                    y = 0;
                    break;
                case TOP:
                    y = layoutComponent.getHeight() - totalSize;
                    break;
            }
            for(Cell c : cells){
                y+=c.getMarginBottom();
                c.setY(y);
                y+=c.getHeight();
                y+=c.getMarginTop();
            }
        }else if(axis == Axis.X_AXIS){
            int x = layoutComponent.getWidth() / 2 - totalSize / 2;
            switch (alignment){
                case LEFT:
                    x = 0;
                    break;
                case RIGHT:
                    x = layoutComponent.getWidth() - totalSize;
                    break;
            }
            for(Cell c : cells){
                x+=c.getMarginLeft();
                c.setX(x);
                x+=c.getWidth();
                x+=c.getMarginRight();
            }
        }
    }

    private void calcTotalSize(){
        totalSize = 0;
        for (Cell c : cells){
            if(axis == Axis.Y_AXIS)
                totalSize += (c.getHeight() + c.getMarginTop() + c.getMarginBottom());
            else if(axis == Axis.X_AXIS)
                totalSize += (c.getWidth() + c.getMarginLeft() + c.getMarginRight());
        }
    }


    @Override
    public void width(float width) {
        if(axis == Axis.X_AXIS){
            cells.get(cells.size() - 1).setSize((int)width, layoutComponent.getHeight());
            setPosition();
        }
    }

    @Override
    public void height(float height) {
        if(axis == Axis.Y_AXIS){
            cells.get(cells.size() - 1).setSize(layoutComponent.getWidth(), (int)height);
            setPosition();
        }
    }

    @Override
    public void paddingTop(float padding) {
        cells.get(cells.size() - 1).setPaddingTop((int)padding);
    }

    @Override
    public void paddingBottom(float padding) {
        cells.get(cells.size() - 1).setPaddingBottom((int)padding);
    }

    @Override
    public void paddingLeft(float padding) {
        cells.get(cells.size() - 1).setPaddingLeft((int)padding);
    }

    @Override
    public void paddingRight(float padding) {
        cells.get(cells.size() - 1).setPaddingRight((int)padding);
    }

    @Override
    public void marginTop(float margin) {
        cells.get(cells.size() - 1).setMarginTop((int)margin);
    }

    @Override
    public void marginBottom(float margin) {
        cells.get(cells.size() - 1).setMarginBottom((int)margin);
    }

    @Override
    public void marginLeft(float margin) {
        cells.get(cells.size() - 1).setMarginLeft((int)margin);
    }

    @Override
    public void marginRight(float margin) {
        cells.get(cells.size() - 1).setMarginRight((int)margin);
    }
}
