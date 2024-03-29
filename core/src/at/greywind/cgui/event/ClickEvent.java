package at.greywind.cgui.event;


public class ClickEvent extends PositionEvent<ClickListener> {

    private ClickType type;

    public enum ClickType{
        CLICKED, PRESS, TOUCH_UP, TOUCH_DOWN
    }

    public ClickEvent(ClickType type){
        this(0,0,type);
    }

    public ClickEvent(int wX, int wY, ClickType type){
        super(wX, wY);
        this.type = type;
    }

    @Override
    public void fire(ClickListener listener) {
        switch (type){
            case PRESS:
                listener.pressed(x, y, this);
                break;
            case CLICKED:
                listener.clicked(x, y, this);
                break;
            case TOUCH_UP:
                listener.touchUp(x, y, this);
                break;
            case TOUCH_DOWN:
                listener.touchDown(x, y, this);
                break;
        }
    }
}
