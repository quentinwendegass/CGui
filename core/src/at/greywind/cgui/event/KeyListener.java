package at.greywind.cgui.event;

public interface KeyListener extends CEventListener {

    default void keyDown(int keycode, String key, KeyEvent event){}

    default void keyUp(int keycode, String key, KeyEvent event){}

    void keyTyped(int keycode, String key, KeyEvent event);
}
