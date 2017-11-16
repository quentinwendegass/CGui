package at.greywind.cgui.test.components;

import at.greywind.cgui.component.CLabel;
import at.greywind.cgui.component.CList;
import at.greywind.cgui.component.CPanel;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.layout.Alignment;
import at.greywind.cgui.text.CFont;
import at.greywind.cgui.text.CFontFactory;

public class LevelList extends CList {

    private final static CColorGradient BACKGROUND_COLOR = new CColorGradient(new CColor(70,70,70), new CColor(70,70,70), new CColor(35,35,35), new CColor(70,70,70));

    public LevelList(int x, int y, int width, int height) {
        super(x, y, width, height);

        setBackground(BACKGROUND_COLOR);
        resizeTo(Alignment.TOP);
    }
}
