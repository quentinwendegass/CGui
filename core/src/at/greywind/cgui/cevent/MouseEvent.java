package at.greywind.cgui.cevent;

public class MouseEvent extends AbstractEvent implements CEvent<MouseListener> {

    private MouseType type;

    public enum MouseType{
        EXIT, ENTER, MOVE
    }

    public MouseEvent(MouseType type){
        this(0,0, type);
    }

    public MouseEvent(int wX, int wY, MouseType type){
        super(wX, wY);
        this.type = type;
    }

    @Override
    public void fire(MouseListener listener) {
        switch (type){
            case EXIT:
                listener.exit();
                break;
            case ENTER:
                listener.enter();
                break;
            case MOVE:
                listener.move(x, y);
                break;
        }
    }
}
