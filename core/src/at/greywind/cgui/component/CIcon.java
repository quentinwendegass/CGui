package at.greywind.cgui.component;

import at.greywind.cgui.util.Icon;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;

public class CIcon extends CComponent {

    private static final int STANDARD_PADDING = 5;

    private Icon icon;

    private float horizontalPadding;
    private float verticalPadding;

    public CIcon(Icon icon) {
        this(icon, 0, 0, 0,0);
    }

    public CIcon(Icon icon, int x, int y) {
        this(icon, x, y, 0,0);
    }

    public CIcon(String internalPath, int x, int y) {
        this(new Icon(internalPath), x, y, 0,0);
    }


    public CIcon(Icon icon, int x, int y, int width, int height){
        this.icon = icon;
        setBackground(CColor.CLEAR);
        setX(x);
        setY(y);
        if(width == 0 || height == 0){
            setWidth((int)icon.getWidth() + 2* STANDARD_PADDING);
            setHeight((int)icon.getHeight() + 2* STANDARD_PADDING);
        }else{
            setWidth(width);
            setHeight(height);
        }
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);
        g.setColor(CColor.WHITE);

        g.drawTexture(icon.getTexture(), horizontalPadding, verticalPadding, icon.getWidth(), icon.getHeight(), icon.getAngle());

    }

    public void setIcon(Icon icon){
        this.icon = icon;
        adjustHorizontalPadding();
        adjustVerticalPadding();
    }

    public Icon getIcon(){
        return icon;
    }

    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        adjustHorizontalPadding();
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        adjustVerticalPadding();
    }

    private void adjustHorizontalPadding(){
        horizontalPadding = getWidth() / 2 - icon.getWidth() / 2;
    }

    private void adjustVerticalPadding(){
        verticalPadding = getHeight() / 2 - icon.getHeight() / 2;
    }

    private void adjustWidth(){
        setWidth((int) (icon.getWidth() + 2*horizontalPadding));
    }

    private void adjustHeight(){
        setHeight((int) (icon.getHeight() + 2*verticalPadding));
    }


}
