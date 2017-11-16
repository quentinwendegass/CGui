package at.greywind.cgui.test.actions;

import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.test.components.EditorPanel;

public class RemoveAction implements Action{

    private CComponent component;

    public RemoveAction(CComponent component) {
        this.component = component;
    }

    @Override
    public void undoAction() {
        EditorPanel.getLevel().add(component);
    }

    @Override
    public void redoAction() {
        EditorPanel.getLevel().getChildComponents().remove(component);
    }
}
