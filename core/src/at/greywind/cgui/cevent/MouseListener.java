package at.greywind.cgui.cevent;

public interface MouseListener extends CEventListener {

    void enter();

    void exit();

    void move(int x, int y);
}
