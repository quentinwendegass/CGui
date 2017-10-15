package at.greywind.cgui.util;

import at.greywind.cgui.component.CComponent;

import java.util.ArrayList;

public class VisibleSelector extends Selector<CComponent> {

    @Override
    public void executeAction(CComponent selected, ArrayList<CComponent> selectables) {
        selectables.forEach(s -> s.setVisible(false));
        selected.setVisible(true);
    }
}
