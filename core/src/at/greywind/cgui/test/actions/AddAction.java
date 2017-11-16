package at.greywind.cgui.test.actions;

import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.test.components.EditorPanel;

public class AddAction implements Action{

    private CComponent component;

    public AddAction(CComponent component) {
        this.component = component;
    }

    @Override
    public void undoAction() {
        EditorPanel.getLevel().getChildComponents().remove(component);
    }

    @Override
    public void redoAction() {
        EditorPanel.getLevel().add(component);
    }
}
