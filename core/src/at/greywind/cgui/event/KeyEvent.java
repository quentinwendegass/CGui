package at.greywind.cgui.event;

import at.greywind.cgui.component.CComponent;

public class KeyEvent extends AbstractEvent<KeyListener> {

    private int keycode;
    private String key;
    private KeyType type;

    public enum KeyType{
        KEY_UP, KEY_DOWN, KEY_TYPED
    }

    public KeyEvent(CComponent component, int keycode, String key, KeyType type){
        super(component);
        this.keycode = keycode;
        this.key = key;
        this.type = type;
    }

    @Override
    public void fire(KeyListener listener) {
        switch (type){
            case KEY_UP:
                listener.keyUp(keycode, key, this);
                break;
            case KEY_DOWN:
                listener.keyDown(keycode, key, this);
                break;
            case KEY_TYPED:
                listener.keyTyped(keycode, key, this);
                break;
        }
    }

    public int getKeycode() {
        return keycode;
    }

    public String getKey() {
        return key;
    }
}
