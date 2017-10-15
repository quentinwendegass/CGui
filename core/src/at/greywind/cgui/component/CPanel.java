package at.greywind.cgui.component;

public class CPanel extends CComponent{

    private static final int DEFAULT_SIZE = 50;

    public CPanel(){
        this(0,0);
    }

    public CPanel(int x, int y){
        this(x,y, DEFAULT_SIZE,DEFAULT_SIZE);
    }

    public CPanel(int x, int y, int width, int height){
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }
}
