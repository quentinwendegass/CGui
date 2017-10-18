package at.greywind.cgui.layout;


import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.component.Debugable;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;

import java.util.ArrayList;

public class CTable extends CComponent implements Debugable, CellListener{

    private ArrayList<Row> rows;
    private int columnCount = 0;
    private int rowCount = 0;

    private int indexRow = 0;
    private int indexColumn = 0;

    private boolean debug = true;


    public CTable(int width, int height) {
        this(width,height, 0, 0);
    }

    public CTable(int width, int height, int rows, int columns) {
        setSize(width, height);

        this.rows = new ArrayList<>();

        for(int i = 0; i < rows; i++)
            addRow();

        for(int i = 0; i < columns; i++)
            addColumn();
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);
        g.setComponent(this);
        g.setColor(CColor.RED);

        if(debug){
            g.drawRect(0,0, getWidth(), getHeight(), 3);

            if(getColumnCount() > 0 && getRowCount() > 0){
                int x = 0;
                for(int i = 0; i < getColumnCount(); i++){
                    x += getCell(0, i).getWidth();
                    g.drawLine(x, 0, x, getHeight());
                }

                int y = getHeight();
                for(int i = 0; i < getRowCount(); i++){
                    y -= getCell(i, 0).getHeight();
                    g.drawLine(0, y, getWidth(), y);
                }
            }
        }
    }

    public Cell layout(CComponent component){
        Cell cell = new Cell(component);
        cell.addCellListener(this);

        if(getColumnCount() - 1 < indexColumn) addColumn(indexColumn);
        if(rowCount == 0) addRow();

        setCell(cell, indexRow, indexColumn);
        add(cell);

        adjustCellPosition();
        indexColumn++;

        return cell;
    }

    public void row(){
        if(getRowCount() - 1 <= indexRow) addRow(getRowCount());
        indexRow++;
        indexColumn = 0;
    }

    private void adjustCellSize(){
        for(int i = 0; i < getRowCount(); i++){
            int maxHeight = 0;
            for(Cell c : rows.get(i).getCells())
                if(c.getHeight() > maxHeight) maxHeight = c.getHeight();
            setRowHeight(i, maxHeight);
        }

        for(int i = 0; i < getColumnCount(); i++){
            int maxWidth = 0;
            for(int j = 0; j < getRowCount(); j++)
                if(getCell(j, i).getWidth() > maxWidth) maxWidth = getCell(j, i).getWidth();
            setColumnWidth(i, maxWidth);
        }
    }

    private void adjustCellPosition(){
        int y = getHeight();

        for(int i = 0; i < getRowCount(); i++){

            y -= getRow(i).get(0).getHeight();

            int x = 0;
            for(Cell c : getRow(i)){
                c.setPosition(x, y);
                x += c.getWidth();
            }
        }
    }

    public void setCell(Cell cell, int row, int column){
        if(cell.getWidth() > rows.get(0).getCell(column).getWidth())
            setColumnWidth(column, cell.getWidth());
        else
            cell.setWidth(rows.get(0).getCell(column).getWidth());

        if(cell.getHeight() > rows.get(row).getCell(0).getHeight())
            setRowHeight(row, cell.getHeight());
        else
            cell.setHeight(rows.get(row).getCell(0).getHeight());

        rows.get(row).setCell(cell, column);
    }

    public Cell getCell(int row, int column){
        return rows.get(row).getCell(column);
    }

    public void addRow(){
        addRow(getRowCount());
    }

    public void addRow(int index){
        addRow(index, 10);
    }

    public void addRow(int index, int height){
        rowCount++;
        Row row = new Row();
        for(int i = 0; i < columnCount; i++)
            if(rows.size() != 0)
                row.addCell(new Cell(rows.get(0).getCell(i).getWidth(), 0));
            else
                row.addCell(new Cell());

        rows.add(index, row);
        setRowHeight(index, height);
    }

    public void addColumn(){
        addColumn(getColumnCount());
    }

    public void addColumn(int index){
        addColumn(index, 10);
    }

    public void addColumn(int index, int width){
        columnCount++;
        for(Row row : rows){
            if(row.getCells().size() != 0)
                row.addCell(index, new Cell(0, row.getCell(0).getHeight()));
            else
                row.addCell(index, new Cell());
        }
        setColumnWidth(index, width);
    }

    public void setColumnWidth(int index, int width){
        rows.forEach(row -> row.getCell(index).setWidth(width));
    }

    public void setRowHeight(int index, int height){
        rows.get(index).getCells().forEach(cell -> cell.setHeight(height));
    }

    public int getColumnCount() {
        return columnCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public ArrayList<Cell> getRow(int index){
        return rows.get(index).getCells();
    }

    public ArrayList<Cell> getColumn(int index){
        ArrayList<Cell> tmp = new ArrayList<>();
        rows.forEach(r -> tmp.add(r.getCell(index)));

        return tmp;
    }

    @Override
    public void setDebugMode(boolean debugMode) {
        debug = debugMode;
    }

    @Override
    public boolean getDebugMode() {
        return debug;
    }

    @Override
    public void resizeAction() {
        adjustCellSize();
        adjustCellPosition();
    }

    @Override
    public void resized(int newWidth, int newHeight, int oldWidth, int oldHeight) {
        super.resized(newWidth, newHeight, oldWidth, oldHeight);

        setSize(newWidth, newHeight);
        resizeAction();
    }
}
