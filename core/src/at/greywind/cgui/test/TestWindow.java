package at.greywind.cgui.test;

import at.greywind.cgui.CWindow;
import at.greywind.cgui.WindowLauncher;
import at.greywind.cgui.component.button.*;
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

        CToggleTextButton b2 = new CToggleTextButton("2", font);
        CToggleTextButton b1 = new CToggleTextButton("1", font);
        CToggleTextButton b3 = new CToggleTextButton("3", font);
        CToggleTextButton b4 = new CToggleTextButton("4", font);
        CToggleTextButton b5 = new CToggleTextButton("5", font);

        CButtonGroup group = new CButtonGroup();
        group.setNoButtonPressedAllowed(false);
        group.addButtons(b1, b2, b3, b4, b5);


        CTable table = new CTable();
        table.setDebugMode(true);

        contentPanel.add(table);

        table.layout(b1);
        table.layout(b2);
        table.row();
        table.layout(b5);
        //table.bottom();
        table.layout(b3);
        //table.layout(b4);




    }
}
