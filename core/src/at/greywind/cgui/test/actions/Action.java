package at.greywind.cgui.test.actions;

import at.greywind.cgui.component.CComponent;

public interface Action {

    void undoAction();

    void redoAction();
}
