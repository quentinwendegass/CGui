package at.greywind.cgui.test;

import at.greywind.cgui.CWindow;
import at.greywind.cgui.Icon;
import at.greywind.cgui.WindowLauncher;
import at.greywind.cgui.border.CBorderFactory;
import at.greywind.cgui.component.CPanel;
import at.greywind.cgui.component.button.*;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.layout.CBoxLayout;
import at.greywind.cgui.layout.Layoutable;
import at.greywind.cgui.text.CFont;
import at.greywind.cgui.text.CFontFactory;

public class TestWindow extends CWindow{

    CButtonGroup buttonGroup;

    public TestWindow(WindowLauncher launcher) {
        super(launcher);
    }

    @Override
    public void initComponents() {

        CPanel boxPanel = new CPanel(0,0, 100, 500);
        //boxPanel.setBorder(CBorderFactory.createLineBorder(CColor.RED));
        CBoxLayout layout = new CBoxLayout(CBoxLayout.Axis.Y_AXIS);
        layout.setDefaultSize(100);
        layout.setAlignment(Layoutable.Alignment.CENTER);
        boxPanel.setLayout(layout);


        CFont font = CFontFactory.createTrueTypeFont("atari.ttf", 10);

        CToggleTextButton b1 = new CToggleTextButton("Button 1", font, 0, 0);
        CToggleTextButton b2 = new CToggleTextButton("Button 2", font, 10, 60);
        CToggleTextButton b3 = new CToggleTextButton("Button 3", font, 10, 110);
        CToggleTextButton b4 = new CToggleTextButton("Button 4", font, 10, 160);
        CToggleTextButton b5 = new CToggleTextButton("Button 5", font, 10, 210);


        boxPanel.layout(b1);
        boxPanel.layout(b2).margin(10);
        boxPanel.layout(b3).padding(5);
        boxPanel.layout(b4);
        //boxPanel.layout(b5).margin(5);

        contentPanel.add(boxPanel);



    }

    @Override
    public void updateComponent() {
        super.updateComponent();
       // if(buttonGroup.getPressedButton() instanceof TextButton)
       // System.out.println((((TextButton) buttonGroup.getPressedButton()).getText()));
    }
}
