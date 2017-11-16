package at.greywind.cgui.event;

public class ScrollEvent extends PositionEvent<ScrollListener> {

    private int amount;

    public ScrollEvent(int windowX, int windowY, int amount) {
        super(windowX, windowY);
        this.amount = amount;
    }

    @Override
    public void fire(ScrollListener listener) {
        listener.scrolled(amount);
    }
}
