package at.greywind.cgui.component.button;

import at.greywind.cgui.border.CBorder;
import at.greywind.cgui.border.CBorderFactory;
import at.greywind.cgui.component.CLabel;
import at.greywind.cgui.component.Enableable;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.text.CFont;

public abstract class TextButton extends CLabel implements Enableable{

    private final CColorGradient DEFAULT_DOWN_BACKGROUND = new CColorGradient(CColor.BLUE);
    private final CColorGradient DEFAULT_UP_BACKGROUND = new CColorGradient(CColor.LIGHT_BLUE);
    private final CColor DISABLED_BACKGROUND_OVERLAY = new CColor(115,115,115, 155);

    protected CColorGradient downBackground = DEFAULT_DOWN_BACKGROUND;
    protected CColorGradient upBackground = DEFAULT_UP_BACKGROUND;
    protected CBorder downBorder = CBorderFactory.createLineBorder();
    protected CBorder upBorder = CBorderFactory.createLineBorder();

    protected boolean pressed = false;
    protected boolean isEnabled = true;

    public TextButton(String text, CFont font, int x, int y, int width, int height) {
        super(text, font, x, y, width, height);
    }

    @Override
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);

        if(!isEnabled){
            g.setColor(DISABLED_BACKGROUND_OVERLAY);
            g.drawFilledRect(0,0, getWidth(), getHeight());
        }
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public CColorGradient getDownBackgroundColor() {
        return downBackground;
    }

    public void setDownBackgroundColor(CColorGradient downBackground) {
        this.downBackground = downBackground;
    }

    public CColorGradient getUpBackgroundColor() {
        return upBackground;
    }

    public void setUpBackgroundColor(CColorGradient upBackground) {
        this.upBackground = upBackground;
    }

    public CBorder getDownBorder() {
        return downBorder;
    }

    public void setDownBorder(CBorder downBorder) {
        this.downBorder = downBorder;
    }

    public CBorder getUpBorder() {
        return upBorder;
    }

    public void setUpBorder(CBorder upBorder) {
        this.upBorder = upBorder;
    }
}
