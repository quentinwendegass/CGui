package at.greywind.cgui.component;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.text.CFont;

public class CLabel extends CComponent implements Padable{

    private static final int STANDART_PADDING = 10;

    private float verticalPadding;
    private float horizontalPadding;
    private String text;
    private CFont font;

    public CLabel(CFont font){
        this("", font, 0,0);
    }

    public CLabel(String text, CFont font, int x, int y){
        this(text, font, x, y, 0,0);
    }

    public CLabel(String text, CFont font, int x, int y, int width, int height){
        this.text = text;
        this.font = font;
        setX(x);
        setY(y);
        setBackground(new CColorGradient(new CColor(0,0,0,0.0f)));

        if(width == 0|| height == 0){
            setWidth((int) (font.getWidth(text) + 2*STANDART_PADDING));
            setHeight((int) (font.getHeight(text) + 2*STANDART_PADDING));
        }else{
            setWidth(width);
            setHeight(height);
        }
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);

        g.setFont(font);
        g.drawText(text, horizontalPadding, verticalPadding + font.getHeight(text));
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
        horizontalPadding = getWidth() / 2 - font.getWidth(text) / 2;
    }

    private void adjustVerticalPadding(){
        verticalPadding = getHeight() / 2 - font.getHeight(text) / 2;
    }

    private void adjustWidth(){
        setWidth((int) (font.getWidth(text) + 2*horizontalPadding));
    }

    private void adjustHeight(){
        setHeight((int) (font.getHeight(text) + 2*verticalPadding));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        adjustWidth();
        adjustHeight();
    }

    public CFont getFont() {
        return font;
    }

    public void setFont(CFont font) {
        this.font = font;
        adjustHorizontalPadding();
        adjustVerticalPadding();
    }

    @Override
    public void setVerticalPadding(float padding){
        verticalPadding = padding;
        adjustHeight();
    }

    @Override
    public void setHorizontalPadding(float padding){
        horizontalPadding = padding;
        adjustWidth();
    }

    @Override
    public float getVerticalPadding() {
        return verticalPadding;
    }

    @Override
    public float getHorizontalPadding() {
        return horizontalPadding;
    }
}
