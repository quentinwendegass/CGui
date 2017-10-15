package at.greywind.cgui.layout;

import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.component.Debugable;
import at.greywind.cgui.event.ComponentListener;

import java.util.ArrayList;

public class CTable extends CComponent implements ComponentListener, Debugable{

    private ArrayList<Column> columns = new ArrayList<>();
    private ArrayList<Row> rows = new ArrayList<>();

    private int indexRow = 0;
    private int indexColumn = 0;

    private boolean debug = false;

    public CTable(){
        addComponentListener(this);

        rows.add(new Row(this));
    }

    public Cell layout(CComponent component){
        columns.add(new Column());
        Cell cell = new Cell(component);
        rows.get(indexRow).addCell(indexColumn, cell);
        columns.get(indexColumn).addCell(indexRow, cell);
        indexColumn++;

        adjustCellPositions();
        add(cell);

        return cell;
    }

    private void adjustCellPositions(){
        int y = getHeight();

        for(Row row : rows){
            y-=row.getHeight();
            int x = 0;

            for(Cell c : row.getCells()){
                c.setPosition(x, y);
                x+=c.getWidth();
            }
        }
    }

    public void row(){
        indexColumn = 0;
        indexRow++;
        rows.add(new Row(this));
    }


    @Override
    public void resized(int width, int height) {
        adjustCellPositions();
    }

    @Override
    public void added(CComponent parent) {
        setSize(parent.getWidth(), parent.getHeight());
        setPosition(0,0);
        adjustCellPositions();
    }

    @Override
    public void setDebugMode(boolean debugMode) {
        debug = debugMode;
    }

    @Override
    public boolean getDebugMode() {
        return debug;
    }
}
