package at.greywind.cgui.event;

import at.greywind.cgui.component.CComponent;

public interface FocusListener {

    void lostFocus(CComponent component);

    void gainFocus(CComponent component);
}
