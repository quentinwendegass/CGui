package at.greywind.cgui.layout;

import at.greywind.cgui.event.ComponentListener;

import java.util.ArrayList;

public class Row implements ComponentListener {

    private ArrayList<Cell> cells;

    private int height = 0;

    private ComponentListener listener;

    public Row(ComponentListener listener){
        this.listener = listener;
        cells = new ArrayList<>();
    }

    public void addCell(Cell c){
        adjustHeight(c.getHeight());
        c.addComponentListener(this);
        cells.add(c);
    }

    public void addCell(int index, Cell c){
        adjustHeight(c.getHeight());
        c.addComponentListener(this);
        cells.add(index, c);
    }

    public Cell getCell(int index){
        return cells.get(index);
    }

    public ArrayList<Cell> getCells(){
        return cells;
    }

    @Override
    public void resized(int width, int height) {
        adjustHeight(height);
        listener.resized(width, height);

    }

    private void adjustHeight(int newHeight){
        if(newHeight > height){
            height = newHeight;
            cells.forEach(c -> c.setHeight(height));
        }
    }

    public void setHeight(int height){
        this.height = height;
    }

    public int getHeight(){
        return height;
    }
}