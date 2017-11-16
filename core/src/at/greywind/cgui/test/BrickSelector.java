package at.greywind.cgui.test;

import at.greywind.cgui.test.components.ShapeComponent;
import at.greywind.cgui.util.Selector;

import java.util.ArrayList;

public class BrickSelector extends Selector<ShapeComponent>{
    @Override
    public void executeAction(ShapeComponent selected, ArrayList<ShapeComponent> selectables) {
        selectables.forEach(s -> s.setSelected(false));
        selected.setSelected(true);
    }
}
