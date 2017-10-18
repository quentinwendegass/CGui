package at.greywind.cgui.test;

import at.greywind.cgui.CWindow;
import at.greywind.cgui.WindowLauncher;
import at.greywind.cgui.component.CSlider;
import at.greywind.cgui.component.DynamicComponent;
import at.greywind.cgui.component.button.*;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.layout.CTable;
import at.greywind.cgui.text.CFont;
import at.greywind.cgui.text.CFontFactory;

public class TestWindow extends CWindow{

    public TestWindow(WindowLauncher launcher) {
        super(launcher);
    }

    @Override
    public void initComponents() {

        CFont font = CFontFactory.createTrueTypeFont("atari.ttf", 12);

        CToggleTextButton b2 = new CToggleTextButton("2sdc", font);
        CToggleTextButton b1 = new CToggleTextButton("1 sdcsdc", font);
        CToggleTextButton b3 = new CToggleTextButton("3 sfgbgfbgfbgfb", font);
        CToggleTextButton b4 = new CToggleTextButton("4d", font);
        CToggleTextButton b5 = new CToggleTextButton("5csdcdsc", font);

        CSlider s = new CSlider();

        CButtonGroup group = new CButtonGroup();
        group.setNoButtonPressedAllowed(false);
        group.addButtons(b1, b2, b3, b4, b5);


        CTable table = new CTable(contentPanel.getWidth(), contentPanel.getHeight());
        table.setDebugMode(true);


        DynamicComponent component = new DynamicComponent();
        component.setBackground(CColor.BLUE);
        component.setSize(100,100);
        component.setPosition(200, 200);
        component.setMaxSize(100, 100);
        contentPanel.add(component);

        table.layout(b1).right();
        table.layout(b2).right();
        table.layout(b4).right().width(50);
        table.row();
        table.layout(b5).width(200).height(200);
        table.layout(b3);
        table.row();
        table.layout(s);
    }


}
