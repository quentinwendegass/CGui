package at.greywind.cgui.component.button;

import at.greywind.cgui.util.Icon;
import at.greywind.cgui.component.CIcon;
import at.greywind.cgui.component.Enableable;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;

public abstract class IconButton extends CIcon implements Enableable{

    private final CColor DISABLED_BACKGROUND_OVERLAY = new CColor(115,115,115, 155);

    protected Icon upIcon;
    protected Icon downIcon;
    protected Icon mouseOverIcon;

    protected boolean pressed;
    protected boolean isEnabled;

    public IconButton(Icon upIcon, Icon downIcon, int x, int y, int width, int height) {
        super(upIcon, x, y, width, height);
        this.upIcon = upIcon;
        this.downIcon = downIcon;
        this.mouseOverIcon = upIcon;
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);

        if(!isEnabled){
            g.setColor(DISABLED_BACKGROUND_OVERLAY);
            g.drawFilledRect(0,0,getWidth(), getHeight());
        }
    }

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
}
