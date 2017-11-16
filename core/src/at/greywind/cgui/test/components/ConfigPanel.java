package at.greywind.cgui.test.components;

import at.greywind.cgui.border.CBorderFactory;
import at.greywind.cgui.component.*;
import at.greywind.cgui.component.button.CTextButton;
import at.greywind.cgui.event.KeyListener;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.layout.Alignment;
import at.greywind.cgui.layout.CTable;
import at.greywind.cgui.layout.NullComponent;
import at.greywind.cgui.test.Breakpoint;
import at.greywind.cgui.test.actions.ColorChangeAction;
import at.greywind.cgui.text.CFont;
import at.greywind.cgui.text.CFontFactory;

import java.util.ArrayList;


public class ConfigPanel extends CPanel {

    private final static CColorGradient BACKGROUND_COLOR = new CColorGradient(new CColor(70,70,70), new CColor(70,70,70), new CColor(70,70,70), new CColor(35,35,35));

    private CPanel shapeConfigPanel;
    private CPanel animationConfigPanel;
    private CPanel ballConfigPanel;

    private static CList ballList;

    private static CTextField colorRedTextField;
    private static CTextField colorGreenTextField;
    private static CTextField colorBlueTextField;

    private static CTextField widthTextField;
    private static CTextField heightTextField;
    private static CTextField posXTextField;
    private static CTextField posYTextField;
    private static CTextField angleTextField;
    private static CTextField alphaTextField;
    private static CTextField durationTextField;

    private static CLabel damageRedLabel;
    private static CLabel damageGreenLabel;
    private static CLabel damageBlueLabel;

    private static CLabel healthRedLabel;
    private static CLabel healthGreenLabel;
    private static CLabel healthBlueLabel;

    private static ShapeComponent selectedShape;
    private static Breakpoint selectedBreakpoint;

    private CFont labelFont;
    private CFont textFieldFont;


    public ConfigPanel(int x, int y, int width, int height) {
        super(x, y, width, height);

        setBackground(BACKGROUND_COLOR);
        resizeTo(Alignment.TOP);
        snapTo(Alignment.RIGHT);
        shapeConfigPanel = new CPanel(10, getHeight() - 50 - 10, getWidth() - 20, 50);
        shapeConfigPanel.snapTo(Alignment.TOP);
        shapeConfigPanel.setBackground(CColor.CLEAR);
        shapeConfigPanel.setBorder(CBorderFactory.createLineBorder(1));

        animationConfigPanel = new CPanel(0, 0, getWidth() - 20, getHeight() - shapeConfigPanel.getHeight() - 200);
        animationConfigPanel.setPosition(10, getHeight() - animationConfigPanel.getHeight() - shapeConfigPanel.getHeight() - 20);
        animationConfigPanel.resizeTo(Alignment.TOP);
        animationConfigPanel.setBackground(CColor.CLEAR);
        animationConfigPanel.setBorder(CBorderFactory.createLineBorder(1));

        ballConfigPanel = new CPanel(10, 10, getWidth() - 20, getHeight() - shapeConfigPanel.getHeight() - animationConfigPanel.getHeight() - 40);
        ballConfigPanel.setBackground(CColor.CLEAR);
        ballConfigPanel.setBorder(CBorderFactory.createLineBorder(1));

        labelFont = CFontFactory.createTrueTypeFont("arial.ttf", 16, new CColor(200,200,200), CColor.BLACK, 1);
        textFieldFont = CFontFactory.createTrueTypeFont("arial.ttf", 16);

        add(shapeConfigPanel);
        add(animationConfigPanel);
        add(ballConfigPanel);

        initAnimationConfigPanel();
        initShapeConfigPanel();
        initBallConfigPanel();

        BreakpointList list = BreakpointList.createInstance(0,0, animationConfigPanel.getWidth() - 40, 200);
        list.resizeTo(Alignment.TOP);
        list.setBorder(CBorderFactory.createLineBorder(2, new CColor(20,20,20)));
        list.setPosition(20,animationConfigPanel.getHeight() - 250 - list.getHeight());

        animationConfigPanel.add(list);

        setShapeConfig(null);
        setBreakpointConfig(null);
    }

    private void initBallConfigPanel(){
        CTextButton addBallButton = new CTextButton("Add Ball", textFieldFont);
        addBallButton.setWidth(ballConfigPanel.getWidth() / 2 - 20);
        addBallButton.setPosition(10, ballConfigPanel.getHeight() - addBallButton.getHeight() - 10);
        addBallButton.setDownBackgroundColor(new CColorGradient(new CColor(30,30,30)));
        addBallButton.setUpBackgroundColor(new CColorGradient(new CColor(100,100,100)));
        addBallButton.setMouseOverBackground(new CColorGradient(new CColor(100,100,100)));

        CTextButton removeBallButton = new CTextButton("Remove Ball", textFieldFont);
        removeBallButton.setWidth(ballConfigPanel.getWidth() / 2 - 20);
        removeBallButton.setPosition(addBallButton.getX() + addBallButton.getWidth() + 20, ballConfigPanel.getHeight() - addBallButton.getHeight() - 10);
        removeBallButton.setDownBackgroundColor(new CColorGradient(new CColor(30,30,30)));
        removeBallButton.setUpBackgroundColor(new CColorGradient(new CColor(100,100,100)));
        removeBallButton.setMouseOverBackground(new CColorGradient(new CColor(100,100,100)));

        ballList = new BallList(10,10, 50, ballConfigPanel.getHeight() - addBallButton.getHeight() - 30);
        ballList.setBorder(CBorderFactory.createLineBorder(2));

        CLabel totalHealthLabel = new CLabel("Total Health:", labelFont);
        totalHealthLabel.setPosition(10 + ballList.getWidth() + 30, ballConfigPanel.getHeight() - addBallButton.getHeight() - totalHealthLabel.getHeight() - 20);
        CLabel totalDamageLabel = new CLabel("Total Damage:", labelFont);
        totalDamageLabel.setPosition(totalHealthLabel.getX(), 45);

        CFont red = CFontFactory.createTrueTypeFont("arial.ttf", 17, CColor.RED, CColor.BLACK, 1);
        CFont green = CFontFactory.createTrueTypeFont("arial.ttf", 17, CColor.GREEN, CColor.BLACK, 1);
        CFont blue = CFontFactory.createTrueTypeFont("arial.ttf", 17, CColor.BLUE, CColor.BLACK, 1);

        CTable maxHealthColorTable = new CTable(ballConfigPanel.getWidth() - totalHealthLabel.getX(), 40);
        maxHealthColorTable.setBackground(CColor.CLEAR);
        maxHealthColorTable.setPosition(totalHealthLabel.getX(), totalHealthLabel.getY() - maxHealthColorTable.getHeight());

        healthRedLabel = new CLabel("0", red);
        healthGreenLabel = new CLabel("0", green);
        healthBlueLabel = new CLabel("0", blue);

        maxHealthColorTable.layout(healthRedLabel).height(40).width(45).left();
        maxHealthColorTable.layout(healthGreenLabel).width(45).left();
        maxHealthColorTable.layout(healthBlueLabel).width(45).left();


        CTable maxDamageColorTable = new CTable(ballConfigPanel.getWidth() - totalDamageLabel.getX(), 40);
        maxDamageColorTable.setPosition(totalDamageLabel.getX(), totalDamageLabel.getY() - maxDamageColorTable.getHeight());
        maxDamageColorTable.setBackground(CColor.CLEAR);

        damageRedLabel = new CLabel("0", red);
        damageGreenLabel = new CLabel("0", green);
        damageBlueLabel = new CLabel("0", blue);

        maxDamageColorTable.layout(damageRedLabel).height(40).width(45).left();
        maxDamageColorTable.layout(damageGreenLabel).width(45).left();
        maxDamageColorTable.layout(damageBlueLabel).width(45).left();


        addBallButton.addClickListener((x, y, e) -> {
            ballList.add(new BallComponent());
            setTotalDamageValue();
        });

        removeBallButton.addClickListener((x, y, e) -> {
            ballList.getChildComponents().remove(0);
            ballList.scrollToTop();
            setTotalDamageValue();
        });

        ballConfigPanel.add(addBallButton);
        ballConfigPanel.add(removeBallButton);
        ballConfigPanel.add(ballList);
        ballConfigPanel.add(totalHealthLabel);
        ballConfigPanel.add(totalDamageLabel);
        ballConfigPanel.add(maxHealthColorTable);
        ballConfigPanel.add(maxDamageColorTable);
    }

    private void initAnimationConfigPanel(){
        CTable animationTable = new CTable(animationConfigPanel.getWidth(), 250);
        animationTable.snapTo(Alignment.TOP);
        animationTable.setBackground(CColor.CLEAR);
        animationTable.setY(animationConfigPanel.getHeight() - animationTable.getHeight());

        CLabel widthLabel = new CLabel("Width: ", labelFont);
        CLabel heightLabel = new CLabel("Height: ", labelFont);
        CLabel posXLabel = new CLabel("Position X: ", labelFont);
        CLabel posYLabel = new CLabel("Position Y: ", labelFont);
        CLabel angleLabel = new CLabel("Angle: ", labelFont);
        CLabel alphaLabel = new CLabel("Alpha: ", labelFont);
        CLabel durationLabel = new CLabel("Duration: ", labelFont);

        KeyListener listener = (keycode, key, event) -> {
            if(key.equals("\n"))
                setNewBreakpoint();
            if(key.equals("\t"))
                setFocusOnNextTextField((CTextField) event.getComponent());
        };

        widthTextField = createAnimationTextField(listener);
        heightTextField = createAnimationTextField(listener);
        posXTextField = createAnimationTextField(listener);
        posYTextField = createAnimationTextField(listener);
        angleTextField = createAnimationTextField(listener);
        angleTextField.setMaxCharacters(3);
        alphaTextField = createAnimationTextField(listener);
        alphaTextField.setMaxCharacters(3);
        durationTextField = createAnimationTextField(listener);

        animationTable.layout(new NullComponent()).height(5);
        animationTable.row();
        animationTable.layout(widthLabel).left().height(30);
        animationTable.layout(widthTextField);
        animationTable.row();
        animationTable.layout(heightLabel).left().height(30);
        animationTable.layout(heightTextField);
        animationTable.row();
        animationTable.layout(posXLabel).left().height(30);
        animationTable.layout(posXTextField);
        animationTable.row();
        animationTable.layout(posYLabel).left().height(30);
        animationTable.layout(posYTextField);
        animationTable.row();
        animationTable.layout(angleLabel).left().height(30);
        animationTable.layout(angleTextField);
        animationTable.row();
        animationTable.layout(alphaLabel).left().height(30);
        animationTable.layout(alphaTextField);
        animationTable.row();
        animationTable.layout(durationLabel).left().height(30);
        animationTable.layout(durationTextField);

        animationTable.setWidth(animationTable.getColumnWidth(0,1));
        animationTable.setX(animationConfigPanel.getWidth() / 2 - animationTable.getWidth() / 2);
        animationConfigPanel.add(animationTable);
    }

    private CTextField createAnimationTextField(KeyListener listener){
        CTextField tmp = new CTextField(textFieldFont);
        tmp.allowOnlyNumericInput(true);
        tmp.setMaxCharacters(5);
        tmp.setText("0");
        tmp.addKeyListener(listener);

        return tmp;
    }

    private void initShapeConfigPanel(){
        CTable colorTable = new CTable(shapeConfigPanel.getWidth(), shapeConfigPanel.getHeight());
        colorTable.setBackground(CColor.CLEAR);

        CLabel colorLabel = new CLabel("Color:", labelFont);

        KeyListener listener = (keycode, key, event) -> {
            if(key.equals("\n"))
                setNewColor();
            if(key.equals("\t"))
                setFocusOnNextTextField((CTextField) event.getComponent());
        };

        colorRedTextField = createColorTextField(CColor.RED, listener);
        colorGreenTextField = createColorTextField(CColor.LIGHT_GREEN, listener);
        colorBlueTextField = createColorTextField(CColor.LIGHT_BLUE, listener);


        colorTable.layout(colorLabel).left().width(70).left().height(colorTable.getHeight());
        colorTable.layout(colorRedTextField);
        colorTable.layout(colorGreenTextField);
        colorTable.layout(colorBlueTextField);

        colorTable.setWidth(colorTable.getColumnWidth(0,3));
        colorTable.setX(animationConfigPanel.getWidth() / 2 - colorTable.getWidth() / 2);

        shapeConfigPanel.add(colorTable);
    }

    private void setFocusOnNextTextField(CTextField textField){
        if(textField == colorRedTextField) colorGreenTextField.setFocus(true);
        else if(textField == colorGreenTextField) colorBlueTextField.setFocus(true);
        else if(textField == widthTextField) heightTextField.setFocus(true);
        else if(textField == heightTextField) posXTextField.setFocus(true);
        else if(textField == posXTextField) posYTextField.setFocus(true);
        else if(textField == posYTextField) angleTextField.setFocus(true);
        else if(textField == angleTextField) alphaTextField.setFocus(true);
        else if(textField == alphaTextField) durationTextField.setFocus(true);
    }

    private CTextField createColorTextField(CColor color, KeyListener listener){
        CTextField tmp = new CTextField(textFieldFont);
        tmp.allowOnlyNumericInput(true);
        tmp.setMaxCharacters(3);
        tmp.setWidth(40);
        tmp.setText("0");
        tmp.setBackground(color);
        tmp.addKeyListener(listener);

        return tmp;
    }

    private void setNewColor(){
        if(selectedShape != null){
            setColorChannelBounds(colorRedTextField);
            setColorChannelBounds(colorGreenTextField);
            setColorChannelBounds(colorBlueTextField);
            ColorChangeAction action = new ColorChangeAction(selectedShape, selectedShape.getShapeColor());
            selectedShape.setShapeColor(new CColor(Integer.parseInt(colorRedTextField.getText()), Integer.parseInt(colorGreenTextField.getText()), Integer.parseInt(colorBlueTextField.getText())));
            action.setNewColor(selectedShape.getShapeColor());
            EditorPanel.addAction(action);
        }
    }

    private void setColorChannelBounds(CTextField textField){
        if(Integer.parseInt(textField.getText()) > 255) textField.setText("255");
    }

    private void setNewBreakpoint(){
        if(selectedBreakpoint != null){
            setColorChannelBounds(alphaTextField);

            selectedBreakpoint.setX(Integer.parseInt(posXTextField.getText()));
            selectedBreakpoint.setY(Integer.parseInt(posYTextField.getText()));
            selectedBreakpoint.setWidth(Integer.parseInt(widthTextField.getText()));
            selectedBreakpoint.setHeight(Integer.parseInt(heightTextField.getText()));
            selectedBreakpoint.setAngle(Integer.parseInt(angleTextField.getText()));
            selectedBreakpoint.setAlpha(Integer.parseInt(alphaTextField.getText()));
            selectedBreakpoint.setDuration(Integer.parseInt(durationTextField.getText()));
        }
    }

    public static void setShapeConfig(ShapeComponent shape){
        selectedShape = shape;
        if(shape != null && !(shape instanceof ShootComponent)) {
            enableShapeTextFields(true);
            colorBlueTextField.setText(Integer.toString((int) (shape.getShapeColor().getBlue() * 256)));
            colorGreenTextField.setText(Integer.toString((int) (shape.getShapeColor().getGreen() * 256)));
            colorRedTextField.setText(Integer.toString((int) (shape.getShapeColor().getRed() * 256)));
        }else{
           enableShapeTextFields(false);
        }
    }

    private static void enableShapeTextFields(boolean enable){
        colorRedTextField.setEnabled(enable);
        colorGreenTextField.setEnabled(enable);
        colorBlueTextField.setEnabled(enable);
    }

    public static void setBreakpointConfig(Breakpoint breakpoint){
        selectedBreakpoint = breakpoint;

        if(breakpoint != null) {
            enableBreakpointTextFields(true);
            widthTextField.setText(Integer.toString(breakpoint.getWidth()));
            heightTextField.setText(Integer.toString(breakpoint.getHeight()));
            posXTextField.setText(Integer.toString(breakpoint.getX()));
            posYTextField.setText(Integer.toString(breakpoint.getY()));
            angleTextField.setText(Integer.toString(breakpoint.getAngle()));
            alphaTextField.setText(Integer.toString(breakpoint.getAlpha()));
            durationTextField.setText(Integer.toString(breakpoint.getDuration()));
        }else{
            enableBreakpointTextFields(false);
        }
    }

    private static void enableBreakpointTextFields(boolean enable){
        widthTextField.setEnabled(enable);
        heightTextField.setEnabled(enable);
        posXTextField.setEnabled(enable);
        posYTextField.setEnabled(enable);
        angleTextField.setEnabled(enable);
        alphaTextField.setEnabled(enable);
        durationTextField.setEnabled(enable);
    }

    public static void setTotalHealthValue(){
        if(healthRedLabel != null && healthBlueLabel != null && healthGreenLabel != null){
            healthRedLabel.setText(Integer.toString(EditorPanel.getTotalRedValue()));
            healthGreenLabel.setText(Integer.toString(EditorPanel.getTotalGreenValue()));
            healthBlueLabel.setText(Integer.toString(EditorPanel.getTotalBlueValue()));
        }
    }

    public static void setTotalDamageValue() {
        damageRedLabel.setText(Integer.toString(getTotalDamageRed()));
        damageGreenLabel.setText(Integer.toString(getTotalDamageGreen()));
        damageBlueLabel.setText(Integer.toString(getTotalDamageBlue()));
    }

    private static int getTotalDamageRed(){
        int totalRedDamage = 0;
        for(CComponent c : ballList.getChildComponents()){
            totalRedDamage += ((ShapeComponent)c).getShapeColor().getRed() * 255;
        }
        return totalRedDamage;
    }

    private static int getTotalDamageGreen(){
        int totalGreenDamage = 0;
        for(CComponent c : ballList.getChildComponents()){
            totalGreenDamage += ((ShapeComponent)c).getShapeColor().getGreen() * 255;
        }
        return totalGreenDamage;
    }

    private static int getTotalDamageBlue(){
        int totalBlueDamage = 0;
        for(CComponent c : ballList.getChildComponents()){
            totalBlueDamage += ((ShapeComponent)c).getShapeColor().getBlue() * 255;
        }
        return totalBlueDamage;
    }

    public static ArrayList<BallComponent> getBallComponents(){
        ArrayList<BallComponent> tmp = new ArrayList<>();

        for(CComponent c : ballList.getChildComponents()){
            if(c instanceof BallComponent)
                tmp.add((BallComponent) c);
        }

        return tmp;
    }
}
