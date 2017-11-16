package at.greywind.cgui.component.button;

import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.util.Icon;
import at.greywind.cgui.component.CIcon;
import at.greywind.cgui.component.Enableable;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.util.Intersection;
import com.badlogic.gdx.Gdx;

public abstract class IconButton extends CIcon implements Enableable{

    private final CColor DISABLED_BACKGROUND_OVERLAY = new CColor(115,115,115, 155);
    private final CColor DEFAULT_ICON_COLOR = CColor.CLEAR;

    protected Icon upIcon;
    protected Icon downIcon;
    protected Icon mouseOverIcon;
    protected CColor upColor;
    protected CColor downColor;
    protected CColor mouseOverColor;


    protected boolean pressed;
    protected boolean isEnabled = true;

    private boolean isMouseInComponentLastFrame = false;

    public IconButton(Icon upIcon, Icon downIcon, int x, int y, int width, int height) {
        super(upIcon, x, y, width, height);
        this.upIcon = upIcon;
        this.downIcon = downIcon;
        this.mouseOverIcon = upIcon;
        this.upColor = DEFAULT_ICON_COLOR;
        this.downColor = DEFAULT_ICON_COLOR;
        this.mouseOverColor = DEFAULT_ICON_COLOR;
        setBackground(upColor);
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);

        handleMouse();

        if(!isEnabled){
            g.setColor(DISABLED_BACKGROUND_OVERLAY);
            g.drawFilledRect(0,0,getWidth(), getHeight());
        }
    }

    private void handleMouse(){
        boolean isMouseInComponent = Intersection.isPointInRect(getWindowX(), getWindowY(), getWidth(), getHeight(), Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

        if(isMouseInComponent && !isMouseInComponentLastFrame)
            mouseEnter();
        else if(!isMouseInComponent && isMouseInComponentLastFrame)
            mouseExit();

        isMouseInComponentLastFrame = isMouseInComponent;
    }

    public abstract void mouseExit();

    public abstract void mouseEnter();

    @Override
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public Icon getUpIcon() {
        return upIcon;
    }

    public void setUpIcon(Icon upIcon) {
        this.upIcon = upIcon;
    }

    public Icon getDownIcon() {
        return downIcon;
    }

    public void setDownIcon(Icon downIcon) {
        this.downIcon = downIcon;
    }

    public Icon getMouseOverIcon() {
        return mouseOverIcon;
    }

    public void setMouseOverIcon(Icon mouseOverIcon) {
        this.mouseOverIcon = mouseOverIcon;
    }

    public CColor getUpColor() {
        return upColor;
    }

    public void setUpColor(CColor upColor) {
        this.upColor = upColor;
    }

    public CColor getDownColor() {
        return downColor;
    }

    public void setDownColor(CColor downColor) {
        this.downColor = downColor;
    }

    public CColor getMouseOverColor() {
        return mouseOverColor;
    }

    public void setMouseOverColor(CColor mouseOverColor) {
        this.mouseOverColor = mouseOverColor;
    }
}
