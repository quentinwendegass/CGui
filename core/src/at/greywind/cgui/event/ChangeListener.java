package at.greywind.cgui.event;

public interface ChangeListener extends CEventListener {

    void changed(boolean value, ChangeEvent e);
}
