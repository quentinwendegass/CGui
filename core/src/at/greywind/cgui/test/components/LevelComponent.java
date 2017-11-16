package at.greywind.cgui.test.components;

import at.greywind.cgui.component.CPanel;
import at.greywind.cgui.component.CTextField;
import at.greywind.cgui.component.button.CTextButton;
import at.greywind.cgui.text.CFont;
import at.greywind.cgui.text.CFontFactory;

public class LevelComponent extends CPanel{

    public LevelComponent(int width, int height) {
        super(0,0, width, height);

        CFont font = CFontFactory.createTrueTypeFont("arial.ttf", 18);

        CTextField levelNameTextField = new CTextField(font);
        levelNameTextField.setWidth(getWidth());

        CTextButton loadButton = new CTextButton("Load Level", font);
        loadButton.setSize(getWidth(), getHeight() - levelNameTextField.getHeight());
        loadButton.setY(levelNameTextField.getHeight());

        add(levelNameTextField);
        add(loadButton);
    }


}
