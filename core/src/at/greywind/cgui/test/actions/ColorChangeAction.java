package at.greywind.cgui.test.actions;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.test.components.ShapeComponent;

public class ColorChangeAction implements Action {

    private ShapeComponent component;
    private CColor oldColor;
    private CColor newColor;

    public ColorChangeAction(ShapeComponent component, CColor oldColor) {
        this.component = component;
        this.oldColor = oldColor;
    }

    public void setNewColor(CColor newColor){
        this.newColor = newColor;
    }

    @Override
    public void undoAction() {
        component.setShapeColor(oldColor);
    }

    @Override
    public void redoAction() {
        component.setShapeColor(newColor);
    }
}
