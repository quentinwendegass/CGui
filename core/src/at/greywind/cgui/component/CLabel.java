package at.greywind.cgui.component;

import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CColorGradient;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.text.CFont;

public class CLabel extends CComponent {

    private String text;
    private CFont font;

    public CLabel(CFont font){
        this("", font);
    }

    public CLabel(String text, CFont font){
        this(text, font, 0,0);
    }

    public CLabel(String text, CFont font, int x, int y){
        this(text, font, x, y, (int) font.getWidth(text),(int) font.getHeight(text));
    }

    public CLabel(String text, CFont font, int x, int y, int width, int height){
        this.text = text;
        this.font = font;
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setBackground(new CColorGradient(new CColor(0,0,0,0.0f)));
    }

    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);

        g.setFont(font);
        g.drawText(text, (getWidth() / 2 - font.getWidth(text) / 2), (getHeight() / 2 + font.getHeight(text) / 2));
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CFont getFont() {
        return font;
    }

    public void setFont(CFont font) {
        this.font = font;
    }
}
