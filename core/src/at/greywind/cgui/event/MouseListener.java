package at.greywind.cgui.event;

public interface MouseListener extends CEventListener {

    void enter(MouseEvent e);

    void exit(MouseEvent e);

    void move(int x, int y, MouseEvent e);
}
