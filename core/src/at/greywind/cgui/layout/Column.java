package at.greywind.cgui.layout;

import at.greywind.cgui.event.ComponentListener;

import java.util.ArrayList;

public class Column implements ComponentListener {

    private ArrayList<Cell> cells;

    private int width = 0;

    public Column(){
        cells = new ArrayList<>();
    }


    public Cell getCell(int index){
        return cells.get(index);
    }

    @Override
    public void resized(int width, int height) {
        adjustWidth(width);
    }

    private void adjustWidth(int newWidth){
        if(newWidth > width){
            width = newWidth;
            cells.forEach(c -> c.setWidth(width));
        }
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void addCell(int index, Cell cell) {
        adjustWidth(cell.getWidth());
        cell.addComponentListener(this);
        cells.add(index, cell);
    }

    public void addCell(Cell cell) {
        adjustWidth(cell.getWidth());
        cell.addComponentListener(this);
        cells.add(cell);
    }
}

