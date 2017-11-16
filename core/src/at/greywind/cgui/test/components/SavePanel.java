package at.greywind.cgui.test.components;

import at.greywind.cgui.border.CBorderFactory;
import at.greywind.cgui.component.CLabel;
import at.greywind.cgui.component.CPanel;
import at.greywind.cgui.component.CTextField;
import at.greywind.cgui.component.button.CTextButton;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.test.parser.LevelParser;
import at.greywind.cgui.text.CFont;
import at.greywind.cgui.text.CFontFactory;

public class SavePanel extends CPanel{

    private final static CColorGradient BACKGROUND_COLOR = new CColorGradient(new CColor(70,70,70), new CColor(70,70,70), new CColor(70,70,70), new CColor(35,35,35));

    private CTextButton saveButton;
    private CTextButton cancelButton;

    public SavePanel(int x, int y, int width, int height) {
        super(x, y, width, height);
        setBackground(BACKGROUND_COLOR);
        setBorder(CBorderFactory.createLineBorder());

        CFont font = CFontFactory.createTrueTypeFont("arial.ttf", 17);

        saveButton = new CTextButton("Save", font, 10, 10);
        saveButton.setWidth(getWidth() / 2 - 20);
        saveButton.setDownBackgroundColor(new CColorGradient(new CColor(30,30,30)));
        saveButton.setUpBackgroundColor(new CColorGradient(new CColor(100,100,100)));
        saveButton.setMouseOverBackground(new CColorGradient(new CColor(100,100,100)));

        cancelButton = new CTextButton("Cancel", font, saveButton.getWidth() + 30, 10);
        cancelButton.setWidth(saveButton.getWidth());
        cancelButton.setDownBackgroundColor(new CColorGradient(new CColor(30,30,30)));
        cancelButton.setUpBackgroundColor(new CColorGradient(new CColor(100,100,100)));
        cancelButton.setMouseOverBackground(new CColorGradient(new CColor(100,100,100)));

        CLabel nameLabel = new CLabel("Level name:", font);
        nameLabel.setY(cancelButton.getY() + cancelButton.getHeight() + (((getHeight() - cancelButton.getHeight() - cancelButton.getY()) / 2) - nameLabel.getHeight() / 2));
        nameLabel.setX(10);

        CTextField nameTextField = new CTextField(font);
        nameTextField.setY(nameLabel.getY() - ((nameTextField.getHeight() - nameLabel.getHeight()) / 2));
        nameTextField.setX(10 + nameLabel.getWidth() + 10);
        nameTextField.setWidth(getWidth() - (nameLabel.getWidth() + 20) - 10);

        saveButton.addClickListener((x1, y1, e) -> {
            if(nameTextField.getText().equals(""))
                LevelParser.setPath("newLevel.lvl");
            else
                LevelParser.setPath(nameTextField.getText() + ".lvl");

            LevelParser.writeToJson(EditorPanel.getShapeComponents(), ConfigPanel.getBallComponents(), EditorPanel.getShootComponent());
            setVisible(false);
            EditorPanel.enableLevelPanel(true);
        });

        cancelButton.addClickListener((x1, y1, e) -> {
            setVisible(false);
            EditorPanel.enableLevelPanel(true);
        });

        add(saveButton);
        add(cancelButton);
        add(nameLabel);
        add(nameTextField);
    }
}
