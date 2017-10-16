package at.greywind.cgui.layout;

import java.util.ArrayList;

public class Row{

    private ArrayList<Cell> cells;


    public Row(){
        cells = new ArrayList<>();
    }

    public void addCell(Cell c){
        cells.add(c);
    }

    public void addCell(int index, Cell c){
        cells.add(index, c);
    }

    public void setCell(Cell cell, int index){
        cells.set(index, cell);
    }

    public Cell getCell(int index){
        return cells.get(index);
    }

    public ArrayList<Cell> getCells(){
        return cells;
    }

}