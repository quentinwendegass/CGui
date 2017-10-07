package at.greywind.cgui.test;

import at.greywind.cgui.CWindow;
import at.greywind.cgui.WindowLauncher;
import at.greywind.cgui.component.*;
import at.greywind.cgui.component.button.*;
import at.greywind.cgui.event.ChangeListener;
import at.greywind.cgui.event.FocusListener;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.layout.CBoxLayout;
import at.greywind.cgui.layout.Layoutable;
import at.greywind.cgui.text.CFont;
import at.greywind.cgui.text.CFontFactory;

public class TestWindow extends CWindow{

    public TestWindow(WindowLauncher launcher) {
        super(launcher);
    }

    @Override
    public void initComponents() {


        CPanel boxPanel = new CPanel(0,launcher.getHeight() - 30, launcher.getWidth(), 30);
        //boxPanel.setBorder(CBorderFactory.createLineBorder(CColor.RED));
        CBoxLayout layout = new CBoxLayout(CBoxLayout.Axis.X_AXIS);
        layout.setDefaultSize(50);
        contentPanel.setDebugMode(true);
        layout.setAlignment(Layoutable.Alignment.RIGHT);
        boxPanel.setLayout(layout);



        CFont font = CFontFactory.createTrueTypeFont("atari.ttf", 10);
        CLabel label = new CLabel("Hallo", font, 100,100 );
        label.setBackground(CColor.BLUE);
        label.snapTo(CComponent.RIGHT | CComponent.TOP);
        label.addFocusListener(new FocusListener() {
            @Override
            public void lostFocus(CComponent component) {
                System.out.println("lost");
            }

            @Override
            public void gainFocus(CComponent component) {
                System.out.println("gained");
            }
        });

        CFont font1 = CFontFactory.createTrueTypeFont("atari.ttf", 12);
        CTextField component = new CTextField(font1, 10, 10);

        CTextArea area = new CTextArea(font, 100,100,200,300);
        area.resizeTo(CComponent.RIGHT);
        area.setText("Hallo das ist ein Text. Hallo das ist ein Text. Hallo das ist ein Text. Hallo das ist ein Text. Hallo das ist ein Text. Hallo das ist ein Text. Hallo das ist ein Text. Hallo das ist ein Text. Hallo das ist ein Text. Hallo das ist ein Text.");
        area.setEnabled(false);
        area.setMinSize(100,100);

        contentPanel.add(area);
        CPanel p = new CPanel(10,10, 200,200);
        p.setBackground(CColor.GREEN);
        p.resizeTo(CComponent.RIGHT);
        p.add(label);
        p.add(component);

        contentPanel.add(p);

        CToggleTextButton b2 = new CToggleTextButton("2", font, 10, 60);
        b2.setEnabled(false);
        CToggleTextButton b1 = new CToggleTextButton("1", font, 0, 0);
        CToggleTextButton b3 = new CToggleTextButton("3", font, 10, 110);
        CToggleTextButton b4 = new CToggleTextButton("4", font, 10, 160);
        CToggleTextButton b5 = new CToggleTextButton("5", font, 10, 210);

        CButtonGroup group = new CButtonGroup();
        group.setNoButtonPressedAllowed(false);
        group.addButtons(b1, b2, b3, b4, b5);
        group.addChangeListener(new ChangeListener() {
            @Override
            public void changed(boolean value, CComponent changedComponent) {
                    label.setText(component.getText());
            }
        });
        boxPanel.snapTo(CComponent.RIGHT | CComponent.TOP);

        boxPanel.layout(b1);
        boxPanel.layout(b2);
        boxPanel.layout(b3);
        boxPanel.layout(b4);
        boxPanel.layout(b5);

        contentPanel.add(boxPanel);
    }
}
