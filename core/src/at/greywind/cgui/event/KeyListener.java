package at.greywind.cgui.event;

public interface KeyListener extends CEventListener {

    void keyDown(int keycode, String key, KeyEvent event);

    void keyUp(int keycode, String key, KeyEvent event);

    void keyTyped(int keycode, String key, KeyEvent event);
}
