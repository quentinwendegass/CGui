package at.greywind.cgui.component;

import at.greywind.cgui.CWindow;
import at.greywind.cgui.event.FocusListener;
import at.greywind.cgui.event.KeyListener;

public interface Focusable{

    void setFocus(boolean focus);

    boolean isFocused();

    void addFocusListener(FocusListener listener);

    void addKeyListener(KeyListener listener);
}
