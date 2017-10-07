package at.greywind.cgui.event;

public class KeyEvent implements CEvent<KeyListener> {

    private int keycode;
    private String key;
    private KeyType type;

    public enum KeyType{
        KEY_UP, KEY_DOWN, KEY_TYPED
    }

    public KeyEvent(int keycode, String key, KeyType type){
        this.keycode = keycode;
        this.key = key;
        this.type = type;
    }

    @Override
    public void fire(KeyListener listener) {
        switch (type){
            case KEY_UP:
                listener.keyUp(keycode, key);
                break;
            case KEY_DOWN:
                listener.keyDown(keycode, key);
                break;
            case KEY_TYPED:
                listener.keyTyped(keycode, key);
                break;
        }
    }
}
