package at.greywind.cgui.test.components;

import at.greywind.cgui.border.CBorderFactory;
import at.greywind.cgui.component.CPanel;
import at.greywind.cgui.component.button.CButtonGroup;
import at.greywind.cgui.component.button.CIconButton;
import at.greywind.cgui.component.button.CToggleIconButton;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.layout.Alignment;
import at.greywind.cgui.layout.CBox;
import at.greywind.cgui.test.ToolbarMode;
import at.greywind.cgui.test.components.ConfigPanel;
import at.greywind.cgui.test.components.EditorPanel;
import at.greywind.cgui.test.components.ShapeComponent;
import at.greywind.cgui.test.parser.LevelParser;
import at.greywind.cgui.util.Icon;

public class Toolbar extends CPanel {

    private final static CColorGradient BACKGROUND_COLOR = new CColorGradient(new CColor(35,35,35), new CColor(70,70,70), CColorGradient.GradientDirection.VERTICAL);
    private final static CColor BUTTON_PRESSED_COLOR = new CColor(35, 35, 35);
    private final static CColor BUTTON_MOUSE_OVER_COLOR = new CColor(110, 110, 110);
    private CBox toolBox;
    private CBox practicalBox;
    private CButtonGroup toolGroup;

    public Toolbar(int x, int y, int width, int height) {
        super(x, y, width, height);
        setBackground(BACKGROUND_COLOR);
        snapTo(Alignment.TOP);
        resizeTo(Alignment.RIGHT);
        setBorder(CBorderFactory.createLineBorder());


        toolGroup = new CButtonGroup();
        toolGroup.setNoButtonPressedAllowed(false);
        toolBox = new CBox(CBox.Orientation.HORIZONTAL);
        practicalBox = new CBox(CBox.Orientation.HORIZONTAL);
        practicalBox.snapTo(Alignment.RIGHT);

        addToolButtons();
        addPracticalButtons();

        practicalBox.setPosition(getWidth() - practicalBox.getWidth(), 0);
        add(toolBox);
        add(practicalBox);
    }

    private void addToolButtons(){
        CToggleIconButton selectButton = createToolButton("icons/arrow.png");
        selectButton.addClickListener(((x, y, e) -> EditorPanel.setMode(null)));

        CToggleIconButton circleButton = createToolButton("icons/circle.png");
        circleButton.addClickListener(((x, y, e) -> EditorPanel.setMode(ToolbarMode.ADD_CIRCLE)));

        CToggleIconButton rectangleButton = createToolButton("icons/rectangle.png");
        rectangleButton.addClickListener(((x, y, e) -> EditorPanel.setMode(ToolbarMode.ADD_RECTANGLE)));

        CToggleIconButton triangleButton = createToolButton("icons/triangle.png");
        triangleButton.addClickListener(((x, y, e) -> EditorPanel.setMode(ToolbarMode.ADD_TRIANGLE)));

        CToggleIconButton removeButton = createToolButton("icons/remove.png");
        removeButton.addClickListener(((x, y, e) -> EditorPanel.setMode(ToolbarMode.DELETE_BRICK)));
    }

    private CToggleIconButton createToolButton(String iconPath){
        Icon icon = new Icon(iconPath);
        icon.setWidth(20);
        icon.setHeight(20);

        CToggleIconButton button = new CToggleIconButton(icon);
        button.setDownColor(BUTTON_PRESSED_COLOR);
        button.setMouseOverColor(BUTTON_MOUSE_OVER_COLOR);
        button.setBorder(CBorderFactory.createLineBorder(1));

        toolGroup.addButton(button);

        toolBox.layout(button);

        return button;
    }

    private void addPracticalButtons(){
        CIconButton undoButton = createPracticalButton("icons/undo.png");
        undoButton.addClickListener(((x, y, e) -> EditorPanel.undo()));

        CIconButton redoButton = createPracticalButton("icons/redo.png");
        redoButton.addClickListener(((x, y, e) -> EditorPanel.redo()));

        CIconButton copyButton = createPracticalButton("icons/copy.png");
        copyButton.addClickListener(((x, y, e) -> EditorPanel.copy()));

        CIconButton pasteButton = createPracticalButton("icons/paste.png");
        pasteButton.addClickListener(((x, y, e) -> EditorPanel.paste()));

        CIconButton cutButton = createPracticalButton("icons/cut.png");
        cutButton.addClickListener(((x, y, e) -> EditorPanel.cut()));

        CIconButton breakpointButton = createPracticalButton("icons/cut.png");
        breakpointButton.addClickListener(((x, y, e) -> ShapeComponent.addBreakpoint()));

        CIconButton rotateLeft = createPracticalButton("icons/undo.png");
        rotateLeft.addClickListener(((x, y, e) -> ShapeComponent.rotate(10)));

        CIconButton rotateRight = createPracticalButton("icons/redo.png");
        rotateRight.addClickListener(((x, y, e) -> ShapeComponent.rotate(-10)));

        CIconButton saveLevel = createPracticalButton("icons/copy.png");
        saveLevel.addClickListener((x, y, e) -> EditorPanel.showSavePanel());
    }

    private CIconButton createPracticalButton(String iconPath){
        Icon icon = new Icon(iconPath);
        icon.setWidth(20);
        icon.setHeight(20);

        CIconButton button = new CIconButton(icon);
        button.setDownColor(BUTTON_PRESSED_COLOR);
        button.setMouseOverColor(BUTTON_MOUSE_OVER_COLOR);
        button.setBorder(CBorderFactory.createLineBorder(1));

        practicalBox.layout(button);

        return button;
    }
}
