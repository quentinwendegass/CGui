package at.greywind.cgui.layout;

import at.greywind.cgui.component.CComponent;
import at.greywind.cgui.graphic.CColor;

public class NullComponent extends CComponent {

    public NullComponent() {
        setBackground(CColor.CLEAR);
        setBorder(null);
    }
}
