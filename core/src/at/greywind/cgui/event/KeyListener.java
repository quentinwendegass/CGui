package at.greywind.cgui.event;

public interface KeyListener extends CEventListener {

    void keyDown(int keycode, String key);

    void keyUp(int keycode, String key);

    void keyTyped(int keycode, String key);
}
