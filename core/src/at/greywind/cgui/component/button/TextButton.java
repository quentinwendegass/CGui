package at.greywind.cgui.component.button;

import at.greywind.cgui.border.CBorder;
import at.greywind.cgui.border.CBorderFactory;
import at.greywind.cgui.component.CLabel;
import at.greywind.cgui.component.Enableable;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.text.CFont;
import at.greywind.cgui.util.Intersection;
import com.badlogic.gdx.Gdx;

public abstract class TextButton extends CLabel implements Enableable{

    private final CColorGradient DEFAULT_DOWN_BACKGROUND = new CColorGradient(CColor.BLUE);
    private final CColorGradient DEFAULT_UP_BACKGROUND = new CColorGradient(CColor.LIGHT_BLUE);
    private final CColor DISABLED_BACKGROUND_OVERLAY = new CColor(115,115,115, 155);

    protected CColorGradient downBackground = DEFAULT_DOWN_BACKGROUND;
    protected CColorGradient upBackground = DEFAULT_UP_BACKGROUND;
    protected CColorGradient mouseOverBackground = DEFAULT_UP_BACKGROUND;

    protected CBorder downBorder = CBorderFactory.createLineBorder();
    protected CBorder upBorder = CBorderFactory.createLineBorder();
    protected CBorder mouseOverBorder = CBorderFactory.createLineBorder();


    protected boolean pressed = false;
    protected boolean isEnabled = true;

    private boolean isMouseInComponentLastFrame = false;


    public TextButton(String text, CFont font, int x, int y, int width, int height) {
        super(text, font, x, y, width, height);

        if(width <= 0 || height <= 0){
            setWidth((int)(font.getWidth(text) + 10));
            setHeight((int)(font.getHeight(text) * 2));
        }
    }

    @Override
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);

        handleMouse();

        if(!isEnabled){
            g.setColor(DISABLED_BACKGROUND_OVERLAY);
            g.drawFilledRect(0,0, getWidth(), getHeight());
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
    public boolean isEnabled() {
        return isEnabled;
    }

    public CColorGradient getDownBackgroundColor() {
        return downBackground;
    }

    public void setDownBackgroundColor(CColorGradient downBackground) {
        this.downBackground = downBackground;
        if(pressed) setBackground(downBackground);

    }

    public CColorGradient getUpBackgroundColor() {
        return upBackground;
    }

    public void setUpBackgroundColor(CColorGradient upBackground) {
        this.upBackground = upBackground;
        if(!pressed) setBackground(upBackground);

    }

    public CBorder getDownBorder() {
        return downBorder;
    }

    public void setDownBorder(CBorder downBorder) {
        this.downBorder = downBorder;
        if(pressed) setBorder(downBorder);
    }

    public CBorder getUpBorder() {
        return upBorder;
    }

    public void setUpBorder(CBorder upBorder) {
        this.upBorder = upBorder;
        if(!pressed) setBorder(upBorder);

    }

    public CColorGradient getMouseOverBackground() {
        return mouseOverBackground;
    }

    public void setMouseOverBackground(CColorGradient mouseOverBackground) {
        this.mouseOverBackground = mouseOverBackground;
    }

    public CBorder getMouseOverBorder() {
        return mouseOverBorder;
    }

    public void setMouseOverBorder(CBorder mouseOverBorder) {
        this.mouseOverBorder = mouseOverBorder;
    }
}
