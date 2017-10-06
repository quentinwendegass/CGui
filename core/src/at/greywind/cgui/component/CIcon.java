package at.greywind.cgui.component;

import at.greywind.cgui.Icon;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;

public class CIcon extends CComponent {

    private static final int STANDART_PADDING = 5;

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
            setWidth((int)icon.getWidth() + 2*STANDART_PADDING);
            setHeight((int)icon.getHeight() + 2*STANDART_PADDING);
        }else{
            setWidth(width);
            setHeight(height);
        }
    }

    @Override
    public void updateComponent(CGraphics g) {
        super.updateComponent(g);

        g.drawTexture(icon.getTexture(), horizontalPadding, verticalPadding, icon.getWidth(), icon.getHeight(), icon.getAngle(), CColor.WHITE);

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

    public void setVerticalPadding(float padding){
        verticalPadding = padding;
        adjustHeight();
    }

    public void setHorizontalPadding(float padding){
        horizontalPadding = padding;
        adjustWidth();
    }



}
