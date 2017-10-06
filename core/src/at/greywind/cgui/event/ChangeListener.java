package at.greywind.cgui.event;

import at.greywind.cgui.component.CComponent;

public interface ChangeListener extends CEventListener {

    void changed(boolean value, CComponent changedComponent);
}
