package at.greywind.cgui.event;


public interface ClickListener extends CEventListener {

    void clicked(int x, int y);

    void pressed(int x, int y);

    void touchUp(int x, int y);

    void touchDown(int x, int y);
}
