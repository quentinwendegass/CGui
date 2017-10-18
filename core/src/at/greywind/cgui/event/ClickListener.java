package at.greywind.cgui.event;


public interface ClickListener extends CEventListener {

    void clicked(int x, int y, ClickEvent e);

    default void pressed(int x, int y, ClickEvent e){}

    default void touchUp(int x, int y, ClickEvent e){}

    default void touchDown(int x, int y, ClickEvent e){}
}
