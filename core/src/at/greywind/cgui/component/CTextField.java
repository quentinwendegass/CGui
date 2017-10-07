package at.greywind.cgui.component;

import at.greywind.cgui.event.KeyListener;
import at.greywind.cgui.graphic.CColor;
import at.greywind.cgui.graphic.CGraphics;
import at.greywind.cgui.text.CFont;

public class CTextField extends TextComponent implements KeyListener, Padable{

    private final static int STANDART_PADDING = 5;

    private int charOffset = 0;

    private float horizontalPadding;
    private float verticalPadding;


    public CTextField(CFont font){
        this(font, 0, 0);
    }

    public CTextField(CFont font, int x, int y){
        this(font, x, y, 0, 0);
    }

    public CTextField(CFont font, int x, int y, int width, int height){
      super(font, x, y, width, height);

        if(width == 0|| height == 0){
            setWidth(100);
            setHorizontalPadding(STANDART_PADDING);
            setHeight((int) (font.getHeight(text) + 2*STANDART_PADDING));
        }else{
            setWidth(width);
            setHeight(height);
            setHorizontalPadding(STANDART_PADDING);
        }

      addKeyListener(this);
    }


    @Override
    public void drawComponent(CGraphics g) {
        super.drawComponent(g);

        g.setFont(font);

        if(font.getHeight(text) < getHeight()){

            g.drawText(text.substring(charOffset), horizontalPadding, getHeight() / 2 + font.getHeight(text) / 2);

            if(drawCursor && cursorActive && isEnabled){
                g.setColor(CColor.BLACK);
                g.drawLine(horizontalPadding + font.getWidth(text.substring(charOffset, cursorPosition)), getHeight() / 6,
                        horizontalPadding + font.getWidth(text.substring(charOffset, cursorPosition)), getHeight() * 5/6);
            }
        }

        if(!isEnabled){
            g.setColor(DISABLED_BACKGROUND_OVERLAY);
            g.drawFilledRect(0,0, getWidth(), getHeight());
        }
    }



    @Override
    public void keyDown(int keycode, String key) {
        if(isEnabled){
            if(keycode == 22 && cursorPosition < text.length()){
                cursorPosition++;
            }else if(keycode == 21 && cursorPosition > 0){
                cursorPosition--;
            }
        }
    }

    @Override
    public void keyTyped(int keycode, String key) {

        if(isEnabled) {
            keyTyped(keycode);

            if (font.getWidth(text) > getWidth() - horizontalPadding * 2) {
                for (int i = 0; i < text.length(); i++) {
                    if (font.getWidth(text.substring(i)) < getWidth() - horizontalPadding * 2) {
                        charOffset = i;
                        break;
                    }
                }
            } else {
                charOffset = 0;
            }
        }
    }

    @Override
    public void keyUp(int keycode, String key) { }

    @Override
    public void touchDown(int x, int y) {
        if(isEnabled) {
            for (int i = charOffset; i < text.length(); i++) {
                if (font.getWidth(text.substring(charOffset, i)) < x - horizontalPadding && font.getWidth(text.substring(charOffset, i + 1)) > x - horizontalPadding) {
                    if (font.getWidth(Character.toString(text.charAt(i))) / 2 + font.getWidth(text.substring(charOffset, i)) > x - horizontalPadding) {
                        cursorPosition = i;
                    } else {
                        cursorPosition = i + 1;
                    }
                }
            }
        }
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        adjustVerticalPadding();
    }

    @Override
    public void setVerticalPadding(float padding) {
        verticalPadding = padding;
        adjustHeight();
    }

    @Override
    public void setHorizontalPadding(float padding) {
        horizontalPadding = padding;
    }

    @Override
    public float getVerticalPadding() {
        return verticalPadding;
    }

    @Override
    public float getHorizontalPadding() {
        return horizontalPadding;
    }


    private void adjustVerticalPadding(){
        verticalPadding = getHeight() / 2 - font.getHeight(text) / 2;
    }

    private void adjustHeight(){
        setHeight((int) (font.getHeight(text) + 2*verticalPadding));
    }
}
