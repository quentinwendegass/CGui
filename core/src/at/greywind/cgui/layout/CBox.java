package at.greywind.cgui.layout;


import at.greywind.cgui.border.CBorderFactory;
import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.component.Debugable;
import at.greywind.cgui.event.ComponentListener;
import at.greywind.cgui.graphic.CColor;


public class CBox extends CComponent implements ComponentListener, Debugable{

    private Orientation orientation = Orientation.VERTICAL;

    private boolean debugMode = false;


    public enum Orientation{
        VERTICAL, HORIZONTAL
    }

    public CBox() {
        this(Orientation.VERTICAL);
    }

    public CBox(Orientation orientation) {
        setBackground(CColor.CLEAR);
        this.orientation = orientation;
    }

    public Cell layout(CComponent component) {
        Cell cell = new Cell(component);
        cell.addComponentListener(this);

        add(cell);
        return cell;
    }

    @Override
    public void resized(int width, int height) {
        if(orientation == Orientation.VERTICAL){
            int iHeight = getHeight();
            int maxHeight = 0;

            for(CComponent cell : getChildComponents()){
                iHeight -= cell.getHeight();
                cell.setY(iHeight);

                if(cell.getWidth() > getWidth()){
                    setWidth(cell.getWidth());
                    getChildComponents().forEach(c -> c.setWidth(cell.getWidth()));
                }

                maxHeight += cell.getHeight();
            }
            setHeight(maxHeight);
        }else if(orientation == Orientation.HORIZONTAL){
            int iWidth = 0;
            int maxWidth = 0;

            for(CComponent cell : getChildComponents()){
                cell.setX(iWidth);
                iWidth += cell.getWidth();


                if(cell.getHeight() > getHeight()){
                    setHeight(cell.getHeight());
                    getChildComponents().forEach(c -> c.setHeight(cell.getHeight()));
                }

                maxWidth += cell.getWidth();
            }
            setWidth(maxWidth);
        }
    }

    @Override
    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;

        if(debugMode)
            getChildComponents().forEach(c -> c.setBorder(CBorderFactory.createLineBorder(2, CColor.RED)));
        else
            getChildComponents().forEach(c -> c.setBorder(null));
    }

    @Override
    public boolean getDebugMode() {
        return debugMode;
    }

    @Override
    public void moved(int x, int y) {

    }
}
